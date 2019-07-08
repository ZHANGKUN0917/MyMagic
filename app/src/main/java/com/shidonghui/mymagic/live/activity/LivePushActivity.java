package com.shidonghui.mymagic.live.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.ViewDataBinding;
import android.hardware.Camera;
import android.media.AudioFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.http.DnspodFree;
import com.qiniu.android.dns.local.AndroidDnsServer;
import com.qiniu.android.dns.local.Resolver;
import com.qiniu.pili.droid.streaming.AudioSourceCallback;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.MicrophoneStreamingSetting;
import com.qiniu.pili.droid.streaming.StreamStatusCallback;
import com.qiniu.pili.droid.streaming.StreamingPreviewCallback;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingSessionListener;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.StreamingStateChangedListener;
import com.qiniu.pili.droid.streaming.SurfaceTextureCallback;
import com.qiniu.pili.droid.streaming.WatermarkSetting;
import com.qiniu.pili.droid.streaming.microphone.AudioMixer;
import com.qiniu.pili.droid.streaming.microphone.OnAudioMixListener;
import com.shidonghui.mymagic.MyApp;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.live.adapter.ChatListAdapter;
import com.shidonghui.mymagic.base.BaseActivity;
import com.shidonghui.mymagic.databinding.ActivityLivePushBinding;
import com.shidonghui.mymagic.live.adapter.LiveSpectatorAdapter;
import com.shidonghui.mymagic.model.GiftEntity;
import com.shidonghui.mymagic.model.LiveCloseModel;
import com.shidonghui.mymagic.model.LiveDetailsModel;
import com.shidonghui.mymagic.model.LiveGiftModel;
import com.shidonghui.mymagic.model.LivePushModel;
import com.shidonghui.mymagic.model.UserModel;
import com.shidonghui.mymagic.request.LiveCloseRequest;
import com.shidonghui.mymagic.request.LiveGiftRequest;
import com.shidonghui.mymagic.request.LiveRequest;
import com.shidonghui.mymagic.utils.GiftMessage;
import com.shidonghui.mymagic.utils.SharedPreferencesUtils;
import com.shidonghui.mymagic.utils.TimeUtils;
import com.shidonghui.mymagic.widget.livewidget.CameraConfig;
import com.shidonghui.mymagic.widget.livewidget.CameraPreviewFrameView;
import com.shidonghui.mymagic.widget.livewidget.EncodingConfig;
import com.shidonghui.mymagic.widget.livewidget.FBO;
import com.shidonghui.mymagic.widget.dialog.LiveBeautyPopupWindow;
import com.shidonghui.mymagic.widget.dialog.LiveCloseDialog;
import com.shidonghui.mymagic.widget.dialog.LiveWarnDialog;
import com.shidonghui.mymagic.widget.livewidget.LiveKit;
import com.shidonghui.mymagic.widget.livewidget.RotateLayout;
import com.shidonghui.mymagic.widget.livewidget.message.HeartMessage;
import com.shidonghui.mymagic.widget.livewidget.message.NoticeMessage;
import com.shidonghui.mymagic.widget.livewidget.message.NotificationMessage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.MessageContent;

/**
 * @author ZhangKun
 * @create 2019/5/31
 * @Describe
 */

public class LivePushActivity extends BaseActivity implements
        StreamingSessionListener,
        StreamStatusCallback,
        StreamingStateChangedListener,
        AudioSourceCallback,
        StreamingPreviewCallback,
        CameraPreviewFrameView.Listener,
        SurfaceTextureCallback,
        Handler.Callback,
        View.OnClickListener {
    private FBO mFBO = new FBO();
    /**
     * 是否开始推流
     */
    protected boolean mIsReady;
    private MediaStreamingManager mMediaStreamingManager;
    private StreamingProfile mProfile;
    private EncodingConfig mEncodingConfig;
    private RotateLayout mRotateLayout;
    /**
     * 当前屏幕方向-是否竖屏
     */
    private boolean mIsEncOrientationPort;
    /**
     * 立体声
     */
    private boolean mAudioStereoEnable;
    private CameraStreamingSetting mCameraStreamingSetting;
    private AudioMixer mAudioMixer;
    private CameraConfig mCameraConfig;
    /**
     * 美颜等级
     */
    private float beauty_level;
    /**
     * 美白等级
     */
    private float whiten_level;
    /**
     * 红润等级
     */
    private float redden_level;
    /**
     * 美颜
     */
    private boolean mIsNeedFB;
    /**
     * 预览镜像（主播看自己是镜像，观看端不是）
     */
    private boolean mIsPreviewMirror;
    /**
     * 编码镜像（观看端可以看到主播镜像）
     */
    private boolean mIsEncodingMirror;
    /**
     * 当前摄像头1：前置0：后置
     */
    private int mCurrentCamFacingIndex;
    /**
     * 当前缩放值
     */
    private int mCurrentZoom = 0;
    /**
     * 最大缩放值
     */
    private int mMaxZoom = 0;
    private ActivityLivePushBinding livePushBinding;
    private LiveRequest liveRequest;
    private LivePushModel.DataBean data;
    /**
     * 是否用户手动关闭直播
     */
    private boolean closeFromUser;
    private String liveId;
    private LivePushModel.DataBean liveInfo;
    private String upstreamAddress;
    /**
     * 图片是否在推流中
     */
    private boolean mIsPictureStreaming = false;
    /**
     * 图片推流handler
     */
    private Handler mHandler;
    /**
     * 图片推流时长
     */
    private int mTimes = 0;
    private ImageSwitcher mImageSwitcher;
    private static final String TAG = "LivePusherActivity";
    private Switcher switcher = new Switcher();
    private EncodingOrientationSwitcher mEncodingOrientationSwitcher = new EncodingOrientationSwitcher();
    /**
     * 屏幕方向改变
     */
    private boolean mOrientationChanged;
    /**
     * 收到的礼物
     */
    private List<GiftEntity> mArrivedGiftList;
    /**
     * 当前聊天室id
     */
    private String roomId;
    private Handler handler = new Handler(this);
    private ChatListAdapter chatListAdapter;
    private UserModel userInfo;
    /**
     * 是否滑动到底部
     */
    private boolean isScrollToBottom = true;
    /**
     * 自动滑动到底部消息
     */
    private final int RESTART_AUTO_SCROLL = 100;
    /**
     * 更新直播时间消息
     */
    private final int UPDATE_LIVE_TIME = 101;
    /**
     * 直播时长s
     */
    private long mSecond = 0;

    /**
     * 观众列表
     */
    private List<LiveDetailsModel.DataBean.ItemsBean> mSpectatorList = new ArrayList<>();
    private LiveSpectatorAdapter liveSpectatorAdapter;
    /**
     * 接收点赞的数量
     */
    private int arrivedHeartCount;
    private Random random = new Random();
    private List<LiveGiftModel.DataBean> giftList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = SharedPreferencesUtils.getUserInfo(mContext);
        liveSpectatorAdapter = new LiveSpectatorAdapter(mSpectatorList);
        if (savedInstanceState != null) {
            mEncodingConfig = (EncodingConfig) savedInstanceState.getSerializable("EncodingConfig");
            mCameraConfig = (CameraConfig) savedInstanceState.getSerializable("CameraConfig");
            mOrientationChanged = savedInstanceState.getBoolean("OrientationChanged");
            liveInfo = savedInstanceState.getParcelable("LiveInfo");
            mSecond = savedInstanceState.getLong("liveTime");
        } else {
            Intent intent = getIntent();
            liveInfo = intent.getParcelableExtra("liveInfo");
            mEncodingConfig = EncodingConfig.buildEncodingConfig();
            mArrivedGiftList = new ArrayList<>();
            roomId = liveInfo.getChatroomId();
            LiveGiftData();

        }
        //编码配置
        initEncodingProfile();
        //照相机参数配置
        initCameraStreamingSetting();
        //推流管理
        initStreamingManager();
        getData();


    }

    @Override
    protected void initView(ViewDataBinding viewDataBinding) {
        //屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        livePushBinding = (ActivityLivePushBinding) viewDataBinding;
        liveRequest = MyApp.getRetrofit4().create(LiveRequest.class);
        livePushBinding.btnLiveClose.setOnClickListener(this);
        //美颜
        livePushBinding.bottomBar.btnBeauty.setOnClickListener(this);
        //切换摄像头
        livePushBinding.bottomBar.btnCameraSwitch.setOnClickListener(this);
        //礼物
        livePushBinding.bottomBar.btnGift.setOnClickListener(this);
        //点赞动画
        livePushBinding.bottomBar.btnHeart.setOnClickListener(this);
        //分享
        livePushBinding.bottomBar.btnShare.setOnClickListener(this);
        //横竖屏切换
        livePushBinding.bottomBar.btnOrientationSwitch.setOnClickListener(this);


    }

    private void getData() {
        chatListAdapter = new ChatListAdapter();
        livePushBinding.chatListView.setLayoutManager(new LinearLayoutManager(mContext));
        livePushBinding.chatListView.setAdapter(chatListAdapter);
        livePushBinding.chatListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) {
                    //dy<0下滑 dy>0上滑
                    isScrollToBottom = false;
                    handler.removeMessages(RESTART_AUTO_SCROLL);
                    handler.sendEmptyMessageDelayed(RESTART_AUTO_SCROLL, 3000);
                }
            }
        });
        LiveKit.addEventHandler(handler);
        if (!mOrientationChanged) {
            joinChatRoom(roomId);
        } else {
            mOrientationChanged = false;
            /**
             * 直接插入直播间提醒消息
             */
            chatListAdapter.addMessage(new NoticeMessage(getString(R.string.live_join_notice)));
            refreshChatListView();
        }
    }

    @Override
    protected void initData() {


    }

    /**
     * 进入聊天室，如果不存在就创建
     *
     * @param roomId
     */
    private void joinChatRoom(final String roomId) {
        LiveKit.joinChatRoom(roomId, -1, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                //直接插入直播间提醒消息
                chatListAdapter.addMessage(new NoticeMessage(getString(R.string.live_join_notice)));
                refreshChatListView();
                NotificationMessage notificationMessage = new NotificationMessage(userInfo, userInfo.getData().getNickname() + "进入直播间", "1", "1");
                LiveKit.sendMessage(notificationMessage);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.i("asdf", "聊天室加入失败! errorCode = " + errorCode);
                Toast.makeText(mContext, "聊天室加入失败! errorCode = " + errorCode, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMediaStreamingManager.resume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        normalPause();

    }

    private void normalPause() {
        mIsReady = false;
        mIsPictureStreaming = false;
        if (mHandler != null) {
            mHandler.getLooper().quit();
        }
        mMediaStreamingManager.pause();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_live_push;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mCameraConfig.mFrontFacing = mCurrentCamFacingIndex == 1 ? true : false;
        mEncodingConfig.mVideoOrientationPortrait = mIsEncOrientationPort;
        outState.putSerializable("EncodingConfig", mEncodingConfig);
        outState.putSerializable("CameraConfig", mCameraConfig);
        outState.putBoolean("OrientationChanged", mOrientationChanged);
        outState.putParcelable("LiveInfo", liveInfo);
        outState.putLong("liveTime", mSecond);
    }

    /**
     * 刷新会话列表
     */
    private void refreshChatListView() {
        chatListAdapter.notifyDataSetChanged();
        if (isScrollToBottom) {
            //自动滚到底部,显示最后一条消息
            livePushBinding.chatListView.scrollToPosition(chatListAdapter.getItemCount() - 1);
        }
    }

    /**
     * 调焦
     *
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (mIsReady) {
            setFocusAreaIndicator();
            mMediaStreamingManager.doSingleTapUp((int) e.getX(), (int) e.getY());
            return true;
        }
        return false;
    }

    /**
     * 缩放
     *
     * @param factor
     * @return
     */
    @Override
    public boolean onZoomValueChanged(float factor) {
        if (mIsReady && mMediaStreamingManager.isZoomSupported()) {
            mCurrentZoom = (int) (mMaxZoom * factor);
            mCurrentZoom = Math.min(mCurrentZoom, mMaxZoom);
            mCurrentZoom = Math.max(0, mCurrentZoom);
            mMediaStreamingManager.setZoomValue(mCurrentZoom);
        }
        return false;
    }

    @Override
    public boolean onRecordAudioFailedHandled(int i) {
        //音频读取失败
        return false;
    }

    @Override
    public boolean onRestartStreamingHandled(int i) {
        //重连
        return false;
    }

    @Override
    public Camera.Size onPreviewSizeSelected(List<Camera.Size> list) {
        //Camera预览size  可以自定义  返回null放弃自定义
        return null;
    }

    @Override
    public int onPreviewFpsSelected(List<int[]> list) {
        //Camera 预览帧率  可以自定义 返回null表示放弃自定义
        return -1;
    }

    @Override
    public void notifyStreamStatusChanged(StreamingProfile.StreamStatus streamStatus) {
        //notifyStreamStatusChanged不会再主线程里面运行 该方法是显示直播时音视频的帧率变化和音视频比特率变化
    }

    @Override
    public void onStateChanged(StreamingState streamingState, Object o) {
        Log.i("asdf", "StreamingState streamingState:" + streamingState + ",extra:" + o);
        //推流的状态  包括准备推流  开始推流  推流断开   推流关闭 闪光灯状态  相机摄像头状态等
        switch (streamingState) {
            case PREPARING:
                Log.i("asdf", "准备中");
                break;
            case READY:
                mIsReady = true;
                mMaxZoom = mMediaStreamingManager.getMaxZoom();
                if (mMediaStreamingManager != null) {
                    startStreaming();
                }
                handler.removeMessages(UPDATE_LIVE_TIME);
                handler.sendEmptyMessage(UPDATE_LIVE_TIME);
                Log.i("asdf", "准备完成");
                break;
            case STREAMING:
                Log.i("asdf", "推流中");
                break;
            case SHUTDOWN:
                Log.i("asdf", "推流关闭");
                break;
            case DISCONNECTED:
                Log.i("asdf", "连接失败，推流断开");
                if (!closeFromUser) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            customToast.failedToast("网络无连接，直播已断开");
                            closeLive(liveInfo.getLiveId());
                        }
                    });
                }
                break;
            case OPEN_CAMERA_FAIL:
                Log.i("asdf", "打开相机失败");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LiveWarnDialog.with(mContext).setOnItemClickLitener(new LiveWarnDialog.OnItemClickLitener() {
                            @Override
                            public void onConfirmClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
                break;
            case CAMERA_SWITCHED:
                //摄像头切换
                break;
            case TORCH_INFO:
                break;
        }

    }

    @Override
    public void onAudioSourceAvailable(ByteBuffer byteBuffer, int i, long l, boolean b) {
        //获取当前音频数据，可以实现自定义音视频处理
    }

    @Override
    public boolean onPreviewFrame(byte[] bytes, int i, int i1, int i2, int i3, long l) {
        //软编模式滤镜实现  可以自定义
        return true;
    }


    @Override
    public void onSurfaceCreated() {
        mFBO.initialize(this);
    }

    @Override
    public void onSurfaceChanged(int i, int i1) {
        mFBO.updateSurfaceSize(i, i1);
    }

    @Override
    public void onSurfaceDestroyed() {
        mFBO.release();
    }

    @Override
    public int onDrawFrame(int i, int i1, int i2, float[] floats) {
        //直接返回newTexId 代表放弃filter处理
        int newTexId = mFBO.drawFrame(i, i1, i2);
        return newTexId;
    }

    private void initEncodingProfile() {
        mProfile = new StreamingProfile();
        StreamingProfile.AudioProfile aProfile = null;
        StreamingProfile.VideoProfile vProfile = null;

        if (!mEncodingConfig.mIsAudioOnly) {
            // video quality
            if (mEncodingConfig.mIsVideoQualityPreset) {
                mProfile.setVideoQuality(mEncodingConfig.mVideoQualityPreset);
            } else {
                vProfile = new StreamingProfile.VideoProfile(
                        mEncodingConfig.mVideoQualityCustomFPS,
                        mEncodingConfig.mVideoQualityCustomBitrate * 1024,
                        mEncodingConfig.mVideoQualityCustomMaxKeyFrameInterval,
                        mEncodingConfig.mVideoQualityCustomProfile
                );
            }

            // video size
            if (mEncodingConfig.mIsVideoSizePreset) {
                mProfile.setEncodingSizeLevel(mEncodingConfig.mVideoSizePreset);
            } else {
                mProfile.setPreferredVideoEncodingSize(mEncodingConfig.mVideoSizeCustomWidth, mEncodingConfig.mVideoSizeCustomHeight);
            }

            // video misc
            mProfile.setEncodingOrientation(mEncodingConfig.mVideoOrientationPortrait ? StreamingProfile.ENCODING_ORIENTATION.PORT : StreamingProfile.ENCODING_ORIENTATION.LAND);
            mProfile.setEncoderRCMode(mEncodingConfig.mVideoRateControlQuality ? StreamingProfile.EncoderRCModes.QUALITY_PRIORITY : StreamingProfile.EncoderRCModes.BITRATE_PRIORITY);
            mProfile.setBitrateAdjustMode(mEncodingConfig.mBitrateAdjustMode);
            mProfile.setFpsControllerEnable(mEncodingConfig.mVideoFPSControl);
            //mProfile.setYuvFilterMode(mEncodingConfig.mYuvFilterMode);
            if (mEncodingConfig.mBitrateAdjustMode == StreamingProfile.BitrateAdjustMode.Auto) {
                mProfile.setVideoAdaptiveBitrateRange(mEncodingConfig.mAdaptiveBitrateMin * 1024, mEncodingConfig.mAdaptiveBitrateMax * 1024);
            }
        }

        // audio quality
        if (mEncodingConfig.mIsAudioQualityPreset) {
            mProfile.setAudioQuality(mEncodingConfig.mAudioQualityPreset);
        } else {
            aProfile = new StreamingProfile.AudioProfile(
                    mEncodingConfig.mAudioQualityCustomSampleRate,
                    mEncodingConfig.mAudioQualityCustomBitrate * 1024
            );
        }

        // custom
        if (aProfile != null || vProfile != null) {
            StreamingProfile.AVProfile avProfile = new StreamingProfile.AVProfile(vProfile, aProfile);
            mProfile.setAVProfile(avProfile);
        }

        mProfile.setDnsManager(getMyDnsManager())
                .setStreamStatusConfig(new StreamingProfile.StreamStatusConfig(3))
                .setSendingBufferProfile(new StreamingProfile.SendingBufferProfile(0.2f, 0.8f, 3.0f, 20 * 1000));
        try {
            mProfile.setPublishUrl(liveInfo.getUpstreamAddress());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    private static DnsManager getMyDnsManager() {
        IResolver r0 = null;
        IResolver r1 = new DnspodFree();
        IResolver r2 = AndroidDnsServer.defaultResolver();
        try {
            r0 = new Resolver(InetAddress.getByName("119.29.29.29"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new DnsManager(NetworkInfo.normal, new IResolver[]{r0, r1, r2});
    }

    protected void setFocusAreaIndicator() {
        if (mRotateLayout == null) {
            mRotateLayout = findViewById(R.id.focus_indicator_rotate_layout);
            mMediaStreamingManager.setFocusAreaIndicator(mRotateLayout,
                    mRotateLayout.findViewById(R.id.focus_indicator));
        }
    }

    /**
     * 推流管理
     */
    private void initStreamingManager() {
        mMediaStreamingManager = new MediaStreamingManager(this, livePushBinding.cameraPreviewSurfaceView, mEncodingConfig.mCodecType);
        if (mEncodingConfig.mIsPictureStreamingEnabled) {
            if (mEncodingConfig.mPictureStreamingFilePath == null) {
                mProfile.setPictureStreamingResourceId(mIsEncOrientationPort ? R.drawable.pause_publish_port : R.drawable.pause_publish_land);
            } else {
                mProfile.setPictureStreamingFilePath(mEncodingConfig.mPictureStreamingFilePath);
            }
        }
        MicrophoneStreamingSetting microphoneStreamingSetting = null;
        if (mAudioStereoEnable) {
            microphoneStreamingSetting = new MicrophoneStreamingSetting();
            microphoneStreamingSetting.setChannelConfig(AudioFormat.CHANNEL_IN_STEREO);
        }
        mMediaStreamingManager.prepare(mCameraStreamingSetting, microphoneStreamingSetting, buildWatermarkSetting(), mProfile);
        if (mCameraConfig.mIsCustomFaceBeauty) {
            mMediaStreamingManager.setSurfaceTextureCallback(this);
        }
        livePushBinding.cameraPreviewSurfaceView.setListener(this);
        mMediaStreamingManager.setStreamingSessionListener(this);
        mMediaStreamingManager.setStreamStatusCallback(this);
        mMediaStreamingManager.setAudioSourceCallback(this);
        mMediaStreamingManager.setStreamingStateListener(this);

        mAudioMixer = mMediaStreamingManager.getAudioMixer();
        mAudioMixer.setOnAudioMixListener(new OnAudioMixListener() {
            @Override
            public void onStatusChanged(MixStatus mixStatus) {
            }

            @Override
            public void onProgress(long l, long l1) {
            }
        });
    }

    /**
     * Accept only 32 bit png (ARGB)
     * 设置水印
     *
     * @return
     */
    private WatermarkSetting buildWatermarkSetting() {
        if (!mEncodingConfig.mIsWatermarkEnabled) {
            return null;
        }
        WatermarkSetting watermarkSetting = new WatermarkSetting(this);
        watermarkSetting.setResourceId(R.drawable.water_mark_sdh);
        watermarkSetting.setAlpha(mEncodingConfig.mWatermarkAlpha);
        watermarkSetting.setSize(mEncodingConfig.mWatermarkSize);
        if (mEncodingConfig.mIsWatermarkLocationPreset) {
            watermarkSetting.setLocation(mEncodingConfig.mWatermarkLocationPreset);
        } else {
            watermarkSetting.setCustomPosition(mEncodingConfig.mWatermarkLocationCustomX, mEncodingConfig.mWatermarkLocationCustomY);
        }

        return watermarkSetting;
    }

    /**
     * 相机参数设置
     *
     * @return
     */
    private void initCameraStreamingSetting() {
        mCameraConfig = CameraConfig.buildCameraConfig();
        mIsEncOrientationPort = mEncodingConfig.mVideoOrientationPortrait;
        mIsNeedFB = mCameraConfig.mIsFaceBeautyEnabled;
        mIsPreviewMirror = mCameraConfig.mPreviewMirror;
        mIsEncodingMirror = mCameraConfig.mEncodingMirror;
        mCurrentCamFacingIndex = mCameraConfig.mFrontFacing ? 1 : 0;
        beauty_level = mCameraConfig.mBeautyLevel;
        whiten_level = mCameraConfig.mWhitenLevel;
        redden_level = mCameraConfig.mBeautyLevel;
        mCameraStreamingSetting = new CameraStreamingSetting();
        mCameraStreamingSetting.setCameraId(mCameraConfig.mFrontFacing ? Camera.CameraInfo.CAMERA_FACING_FRONT : Camera.CameraInfo.CAMERA_FACING_BACK)
                .setCameraPrvSizeLevel(mCameraConfig.mSizeLevel)
                .setCameraPrvSizeRatio(mCameraConfig.mSizeRatio)
                .setFocusMode(mCameraConfig.mFocusMode)
                .setContinuousFocusModeEnabled(mCameraConfig.mContinuousAutoFocus)
                .setFrontCameraPreviewMirror(mCameraConfig.mPreviewMirror)
                .setFrontCameraMirror(mCameraConfig.mEncodingMirror).setRecordingHint(false)
                .setResetTouchFocusDelayInMs(2000)
                .setBuiltInFaceBeautyEnabled(!mCameraConfig.mIsCustomFaceBeauty)
                .setFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(beauty_level, whiten_level, redden_level));

        if (mCameraConfig.mIsFaceBeautyEnabled) {
            mCameraStreamingSetting.setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY);
        } else {
            mCameraStreamingSetting.setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_NONE);
        }
    }


    /**
     * 开始推流
     *
     * @return
     */
    private boolean startStreaming() {
        return mMediaStreamingManager.startStreaming();
    }

    /**
     * 停止推流
     *
     * @return
     */
    private boolean stopStreaming() {
        return mMediaStreamingManager.stopStreaming();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case RESTART_AUTO_SCROLL:
                isScrollToBottom = true;
                break;
            case UPDATE_LIVE_TIME:
                livePushBinding.broadcastTime.setText(TimeUtils.formattedTime(mSecond));
                mSecond++;
                handler.sendEmptyMessageDelayed(UPDATE_LIVE_TIME, 1000);
                break;
            case LiveKit.MESSAGE_ARRIVED:
                MessageContent content = (MessageContent) msg.obj;
                if (content instanceof NotificationMessage) {
                    final NotificationMessage message = (NotificationMessage) content;
                    LiveDetailsModel.DataBean.ItemsBean itemsBean = new LiveDetailsModel.DataBean.ItemsBean(message.getPortraitUri(), message.getName(), message.getUserId());
                    /*
                    首先判断此消息是进入直播间还是退出直播间发送的
                    进入：
                        1.判断当前观众列表头像是否已达到50个，如果未达到，判断进入的是否真人（真人放前面，机器人放末尾）
                        2.如果头像已达到50个，判断进入的是否真人，是的话删去末尾的一个，添加头像到首位
                    退出：
                        1.如果退出的是真人，遍历头像列表中是否有此人的头像，有的话删除
                        2.退出直播间消息不展示，直接return
                   */
                    if ("1".equals(message.getIsJoin())) {
                        if (mSpectatorList.size() < 50) {
                            if ("1".equals(message.getIsReal())) {
//                                //有可能粉丝端退出时没网，推出直播间消息没有发送成功，再次进入时就会显示两个头像，所以此处把头像列表遍历删除掉已有的头像
//                                for (LiveInfoBean.ItemsBean bean : mSpectatorList) {
//                                    if (bean.getUserId().equals(message.getUserId())) {
//                                        mSpectatorList.remove(bean);
//                                    }
//                                }
                                mSpectatorList.add(0, itemsBean);
                            } else {
                                mSpectatorList.add(itemsBean);
                            }
                            liveSpectatorAdapter.notifyDataSetChanged();
                        } else {
                            if ("1".equals(message.getIsReal())) {
//                                //有可能粉丝端退出时没网，推出直播间消息没有发送成功，再次进入时就会显示两个头像，所以此处把头像列表遍历删除掉已有的头像
//                                for (LiveInfoBean.ItemsBean bean : mSpectatorList) {
//                                    if (bean.getUserId().equals(message.getUserId())) {
//                                        mSpectatorList.remove(bean);
//                                    }
//                                }
                                mSpectatorList.remove(49);
                                mSpectatorList.add(0, itemsBean);
                                liveSpectatorAdapter.notifyDataSetChanged();
                            }
                        }
                    } else {
                        if ("1".equals(message.getIsReal())) {
                            for (LiveDetailsModel.DataBean.ItemsBean itemBean : mSpectatorList) {
                                if (itemBean.getUserId().equals(itemsBean.getUserId())) {
                                    mSpectatorList.remove(itemBean);
                                    liveSpectatorAdapter.notifyDataSetChanged();
                                    break;
                                }
                            }
                        }
                        return false;
                    }
                } else if (content instanceof HeartMessage) {
                    HeartMessage heartMessage = (HeartMessage) content;
                    arrivedHeartCount += Integer.valueOf(heartMessage.getNumber());
                    return false;
                } else if (content instanceof GiftMessage) {
                    GiftMessage giftMessage = (GiftMessage) content;
                    for (LiveGiftModel.DataBean data : giftList) {
                        if (data.getId() == Integer.valueOf(giftMessage.getGiftId())) {
                            mArrivedGiftList.add(0, new GiftEntity(giftMessage, data));
                            livePushBinding.giftRoot.loadGift(new GiftEntity(giftMessage, data));
                            return false;
                        }
                    }
                }
                chatListAdapter.addMessage(content);
                break;
            case LiveKit.HEART_ARRIVED:
                if (arrivedHeartCount != 0) {
                    livePushBinding.heartLayout.addHeart(random.nextInt(5));
                    arrivedHeartCount--;
                }
                handler.sendEmptyMessageDelayed(LiveKit.HEART_ARRIVED, 150);
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * 摄像头切换
     */
    private class Switcher implements Runnable {
        @Override
        public void run() {
            mCurrentCamFacingIndex = (mCurrentCamFacingIndex + 1) % CameraStreamingSetting.getNumberOfCameras();
            CameraStreamingSetting.CAMERA_FACING_ID facingId;
            if (mCurrentCamFacingIndex == CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK.ordinal()) {
                facingId = CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK;
            } else if (mCurrentCamFacingIndex == CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT.ordinal()) {
                facingId = CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT;
            } else {
                facingId = CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_3RD;
            }
            Log.e(TAG, "switchCamera:" + facingId);
            mMediaStreamingManager.switchCamera(facingId);
        }
    }

    /**
     * 直播礼物列表
     */
    private void LiveGiftData() {
        Observable<LiveGiftModel> liveGift = liveRequest.liveGift(new LiveGiftRequest("0"));
        liveGift
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LiveGiftModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LiveGiftModel liveGiftModel) {
                        giftList.addAll(liveGiftModel.getData());


                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private boolean isPictureStreaming() {
        if (mIsPictureStreaming) {
            Toast.makeText(LivePushActivity.this, "is picture streaming, operation failed!", Toast.LENGTH_SHORT).show();
        }
        return mIsPictureStreaming;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_live_close:
                LiveCloseDialog.with(mContext).setOnItemClickLitener(new LiveCloseDialog.OnItemClickLitener() {
                    @Override
                    public void onContinueClick(View view) {

                    }

                    @Override
                    public void onFinishClick(View view) {
                        closeFromUser = true;
                        closeLive(liveInfo.getLiveId());


                    }
                }).show();
                break;
            case R.id.btn_beauty:
                LiveBeautyPopupWindow.with(this, mIsNeedFB, beauty_level, whiten_level, redden_level).setOnStatusChangeLitener(new LiveBeautyPopupWindow.OnStatusChangeListener() {
                    @Override
                    public void onBeautySwitch(CompoundButton buttonView, SeekBar seekBar1, SeekBar seekBar2, SeekBar seekBar3) {
                        mIsNeedFB = buttonView.isChecked();
                        mMediaStreamingManager.setVideoFilterType(mIsNeedFB ?
                                CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY
                                : CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_NONE);
                        if (mIsNeedFB) {
                            Log.e("TAG", "------------美颜已开启");
                        } else {
                            Log.e("TAG", "------------美颜已关闭");
                            seekBar1.setProgress(0);
                            seekBar2.setProgress(0);
                            seekBar3.setProgress(0);
                        }
                    }

                    @Override
                    public void onBeautifyChangeListener(View view, int progress) {
                        beauty_level = progress / 100f;
                        CameraStreamingSetting.FaceBeautySetting fbSetting = mCameraStreamingSetting.getFaceBeautySetting();
                        fbSetting.beautyLevel = beauty_level;
                        mMediaStreamingManager.updateFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(beauty_level, whiten_level, redden_level));
                    }

                    @Override
                    public void onWhitenChangeListener(View view, int progress) {
                        whiten_level = progress / 100f;
                        CameraStreamingSetting.FaceBeautySetting fbSetting = mCameraStreamingSetting.getFaceBeautySetting();
                        fbSetting.whiten = whiten_level;
                        mMediaStreamingManager.updateFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(beauty_level, whiten_level, redden_level));
                    }

                    @Override
                    public void onReddenChangeListener(View view, int progress) {
                        redden_level = progress / 100f;
                        CameraStreamingSetting.FaceBeautySetting fbSetting = mCameraStreamingSetting.getFaceBeautySetting();
                        fbSetting.redden = redden_level;
                        mMediaStreamingManager.updateFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(beauty_level, whiten_level, redden_level));
                    }
                }).show(v);
                break;
            case R.id.btn_camera_switch:
                if (isPictureStreaming()) {
                    return;
                }
                livePushBinding.bottomBar.btnCameraSwitch.removeCallbacks(switcher);
                livePushBinding.bottomBar.btnCameraSwitch.postDelayed(switcher, 100);
                break;
            case R.id.btn_gift:
                //LiveReceivedGiftPopupWindow.with(mContext, ).show();
                break;
            case R.id.btn_heart:
                break;
            case R.id.btn_orientation_switch:
                if (isPictureStreaming()) {
                    return;
                }

                livePushBinding.bottomBar.btnCameraSwitch.removeCallbacks(mEncodingOrientationSwitcher);
                livePushBinding.bottomBar.btnOrientationSwitch.postDelayed(mEncodingOrientationSwitcher, 100);
                break;
            case R.id.btn_share:
                break;

        }
    }

    /**
     * 屏幕方向状态切换
     */
    private void updateOrientationStatus() {
        if (mIsEncOrientationPort) {
            Log.e("TAG", "------------现在是竖屏");
            /*StatusMessage statusMessage = new StatusMessage(mUserInfo, "streamVertical");
            LiveKit.sendMessage(statusMessage);*/
        } else {
            Log.e("TAG", "------------现在是横屏");
           /* StatusMessage statusMessage = new StatusMessage(mUserInfo, "streamHorizontal");
            LiveKit.sendMessage(statusMessage);*/
        }
    }

    /**
     * 横竖屏切换
     */
    private class EncodingOrientationSwitcher implements Runnable {
        @Override
        public void run() {
            Log.e(TAG, "mIsEncOrientationPort:" + mIsEncOrientationPort);
            mOrientationChanged = true;
            mIsEncOrientationPort = !mIsEncOrientationPort;
            mProfile.setEncodingOrientation(mIsEncOrientationPort ? StreamingProfile.ENCODING_ORIENTATION.PORT : StreamingProfile.ENCODING_ORIENTATION.LAND);
            mMediaStreamingManager.setStreamingProfile(mProfile);
            stopStreaming();
            setRequestedOrientation(mIsEncOrientationPort ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//            setRequestedOrientation(mIsEncOrientationPort ? ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);//设置只支持横屏或只支持竖屏重力感应
            mMediaStreamingManager.notifyActivityOrientationChanged();
            updateOrientationStatus();
        }
    }

    private class ImageSwitcher implements Runnable {
        @Override
        public void run() {
            if (!mIsPictureStreaming) {
                Log.d("asdf", "is not picture streaming!!!");
                return;
            }

            if (mTimes % 2 == 0) {
                if (mEncodingConfig.mPictureStreamingFilePath != null) {
                    mMediaStreamingManager.setPictureStreamingFilePath(mEncodingConfig.mPictureStreamingFilePath);
                } else {
                    mMediaStreamingManager.setPictureStreamingResourceId(mIsEncOrientationPort ? R.drawable.pause_publish_port : R.drawable.pause_publish_land);
                }
            } else {
                mMediaStreamingManager.setPictureStreamingResourceId(mIsEncOrientationPort ? R.drawable.pause_publish_port : R.drawable.pause_publish_land);
            }
            mTimes++;
            if (mHandler != null && mIsPictureStreaming) {
                mHandler.postDelayed(this, 1000);
            }
        }
    }

    /**
     * 图片推流(不通过摄像头采集，将一组图片推送出去)
     */
    private void togglePictureStreaming() {
        boolean isOK = mMediaStreamingManager.togglePictureStreaming();
        if (!isOK) {
            Toast.makeText(mContext, "toggle picture streaming failed!", Toast.LENGTH_SHORT).show();
            return;
        }
        mIsPictureStreaming = !mIsPictureStreaming;
        mTimes = 0;
        if (mIsPictureStreaming) {
            if (mImageSwitcher == null) {
                mImageSwitcher = new ImageSwitcher();
            }

            HandlerThread handlerThread = new HandlerThread(TAG);
            handlerThread.start();
            mHandler = new Handler(handlerThread.getLooper());
            mHandler.postDelayed(mImageSwitcher, 1000);
        } else {
            if (mHandler != null) {
                mHandler.getLooper().quit();
            }
        }
    }

    /**
     * 关闭直播
     */
    private void closeLive(String liveId) {
        final Observable<LiveCloseModel> liveClose = liveRequest.liveClose(new LiveCloseRequest(liveId));
        liveClose.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LiveCloseModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LiveCloseModel liveCloseModel) {
                if (liveCloseModel.rstCde == 0) {
                    finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i("asdf", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}