package com.shidonghui.mymagic.live.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLOnAudioFrameListener;
import com.pili.pldroid.player.PLOnBufferingUpdateListener;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnVideoFrameListener;
import com.pili.pldroid.player.PLOnVideoSizeChangedListener;
import com.pili.pldroid.player.widget.PLVideoView;
import com.shidonghui.mymagic.MyApp;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.base.BaseActivity;
import com.shidonghui.mymagic.databinding.ActivityLivePlayerBinding;
import com.shidonghui.mymagic.db.DBManager;
import com.shidonghui.mymagic.db.TreasureChestModel;
import com.shidonghui.mymagic.db.TreasureChestModelDao;
import com.shidonghui.mymagic.live.LiveSendGiftPopupWindow;
import com.shidonghui.mymagic.live.adapter.ChatListAdapter;
import com.shidonghui.mymagic.live.adapter.LiveSpectatorAdapter;
import com.shidonghui.mymagic.model.GiftEntity;
import com.shidonghui.mymagic.model.GiftListModel;
import com.shidonghui.mymagic.model.LiveDetailsModel;
import com.shidonghui.mymagic.model.LiveGiftModel;
import com.shidonghui.mymagic.model.LiveListModel;
import com.shidonghui.mymagic.model.LivePopularityGiftModel;
import com.shidonghui.mymagic.model.RewardGiftModel;
import com.shidonghui.mymagic.model.UserModel;
import com.shidonghui.mymagic.request.LiveDetailsRequest;
import com.shidonghui.mymagic.request.LiveGiftRequest;
import com.shidonghui.mymagic.request.LiveRequest;
import com.shidonghui.mymagic.utils.GiftMessage;
import com.shidonghui.mymagic.utils.NetworkUtil;
import com.shidonghui.mymagic.utils.SharedPreferencesUtils;
import com.shidonghui.mymagic.widget.LiveTreasureBoxPopupWindow;
import com.shidonghui.mymagic.widget.livewidget.LiveKit;
import com.shidonghui.mymagic.widget.livewidget.message.HeartMessage;
import com.shidonghui.mymagic.widget.livewidget.message.NoticeMessage;
import com.shidonghui.mymagic.widget.livewidget.message.NotificationMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.greenrobot.dao.query.CloseableListIterator;
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
 * @Describe 播放端
 */
public class LivePlayerActivity extends BaseActivity implements View.OnClickListener, Handler.Callback {
    /**
     * 直播id
     */
    private String liveId;
    /**
     * 聊天室id
     */
    private String chatRoomId;
    /**
     * 观看直播地址
     */
    private String rtmpDownAddress;
    private ActivityLivePlayerBinding livePlayerBinding;
    private PLVideoView videoView;
    private Handler handler = new Handler(this);
    private LiveRequest liveRequest;
    /**
     * 直播详细信息
     */
    private LiveDetailsModel.DataBean liveInfo;
    private List<LiveDetailsModel.DataBean.ItemsBean> mSpectatorList = new ArrayList<>();
    private ChatListAdapter chatListAdapter;
    /**
     * 是否滑动到底部
     */
    private boolean isScrollToBottom = true;
    private final int RESTART_AUTO_SCROLL = 100;
    private UserModel userInfo;
    /**
     * 屏幕方向改变
     */
    private boolean mOrientationChanged;
    private Random random = new Random();
    /**
     * 发送点赞的数量
     */
    private int sentHeartCount;
    /**
     * 接收点赞的数量
     */
    private int arrivedHeartCount;
    private List<LiveGiftModel.DataBean> giftList = new ArrayList<>();
    private LiveSpectatorAdapter liveSpectatorAdapter;
    private DBManager dbManager;
    private TreasureChestModelDao treasureBoxRecordDao;
    private TreasureChestModel treasureBoxRecordByID;
    private final int START_TIMING = 666;
    private long mSecond;//观看时长/s
    private final long WAITING_TIME = 60;//宝箱领取等待时间/s
    private int index;//当前宝箱
    private int position;//当前宝箱领取进度


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = SharedPreferencesUtils.getUserInfo(mContext);
        if (savedInstanceState != null) {

        } else {
            Intent intent = getIntent();
            LiveListModel.DataBean liveInfo = intent.getParcelableExtra("liveInfo");
            liveId = liveInfo.getLiveId();
            chatRoomId = liveInfo.getChatroomId();
            rtmpDownAddress = liveInfo.getRtmpDownAddress();
            /**
             * 获取直播详细信息
             */
            getLiveDetails();
            /**
             * 获取直播礼物列表
             */

            LiveGiftData(false);
        }
        setVideo(rtmpDownAddress);


    }

    @Override
    protected void initView(ViewDataBinding viewDataBinding) {
        livePlayerBinding = (ActivityLivePlayerBinding) viewDataBinding;
        liveRequest = MyApp.getRetrofit4().create(LiveRequest.class);
        livePlayerBinding.btnLiveClose.setOnClickListener(this);
        livePlayerBinding.btnAddAttention.setOnClickListener(this);
        livePlayerBinding.ivTreasureBox.setOnClickListener(this);
        //livePlayerBinding.bottomBar.btnDefinition.setOnClickListener(this);
        livePlayerBinding.bottomBar.btnGift.setOnClickListener(this);
        livePlayerBinding.bottomBar.btnHeart.setOnClickListener(this);
        livePlayerBinding.bottomBar.btnMore.setOnClickListener(this);
        //livePlayerBinding.bottomBar.btnPort.setOnClickListener(this);
        livePlayerBinding.bottomBar.btnShare.setOnClickListener(this);
        livePlayerBinding.bottomBar.btnInput.setOnClickListener(this);
        //livePlayerBinding.bottomBar.btnScreenRecord.setOnClickListener(this);
        //livePlayerBinding.bottomBar.btnScreenShoot.setOnClickListener(this);
        //聊天列表
        chatListAdapter = new ChatListAdapter();
        livePlayerBinding.chatListview.setLayoutManager(new LinearLayoutManager(mContext));
        livePlayerBinding.chatListview.setAdapter(chatListAdapter);
        livePlayerBinding.chatListview.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
    }

    @Override
    protected void initData() {
        handler.sendEmptyMessageDelayed(START_TIMING, 1000);
    }

    /**
     * 获取直播详细信息
     *
     * @return
     */
    private void getLiveDetails() {
        final Observable<LiveDetailsModel> liveDetails = liveRequest.liveDetails(new LiveDetailsRequest(liveId));
        liveDetails.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LiveDetailsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LiveDetailsModel liveDetailsModel) {
                        liveInfo = liveDetailsModel.getData();
                        setLiveInfo();
                        openDB();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 设置直播信息
     */
    private void setLiveInfo() {
        //头像
        livePlayerBinding.ivAvatar.setImageURI(liveInfo.getAvatar());
        //人数
        livePlayerBinding.tvWatchNumber.setText(liveInfo.getPlayerNum() + "人");
        //观众列表适配器
        liveSpectatorAdapter = new LiveSpectatorAdapter(mSpectatorList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        livePlayerBinding.rcSpectatorList.setLayoutManager(linearLayoutManager);
        livePlayerBinding.rcSpectatorList.setAdapter(liveSpectatorAdapter);
        mSpectatorList.addAll(liveInfo.getItems());
        liveSpectatorAdapter.notifyDataSetChanged();
        LiveKit.addEventHandler(handler);
        if (!mOrientationChanged) {
            joinChatRoom(chatRoomId);
        } else {
            /**
             * 直接插入直播间提醒消息
             */
            chatListAdapter.addMessage(new NoticeMessage(getString(R.string.live_join_notice)));
            refreshChatListView();
        }

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_live_player;
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
                Log.i("asdf", errorCode.getValue() + "-=-=" + errorCode.getMessage());
                Toast.makeText(mContext, "聊天室加入失败,请检查登录状态后重试", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    /**
     * 刷新会话列表
     */
    private void refreshChatListView() {
        chatListAdapter.notifyDataSetChanged();
        if (isScrollToBottom) {
            //自动滚到底部,显示最后一条消息
            livePlayerBinding.chatListview.scrollToPosition(chatListAdapter.getItemCount() - 1);
        }
    }

    @Override
    protected void onDestroy() {


        if (handler != null) {
            LiveKit.removeEventHandler(handler);
        }
        boolean b = treasureBoxRecordDao != null;
        boolean b1 = treasureBoxRecordByID != null;

        if (treasureBoxRecordDao != null && treasureBoxRecordByID != null) {

            treasureBoxRecordByID.setWatchSeconds(mSecond);
            treasureBoxRecordDao.insertOrReplace(treasureBoxRecordByID);
        }


        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_attention:
                break;
            case R.id.btn_live_close:
                //关闭直播
                quitChatRoom();
                break;
            case R.id.btn_input:
                break;
            case R.id.btn_gift:
                //发送礼物
                createGiftPopupWindow(v);
                break;
            case R.id.btn_heart:
                //发送点赞
                sendHeartMessage();
                break;
            case R.id.btn_screen_record:
                break;
            case R.id.btn_screen_shoot:
                break;
            case R.id.btn_share:
                break;
            case R.id.iv_treasure_box:
                //开启宝箱
                LiveTreasureBoxPopupWindow.with(LivePlayerActivity.this, treasureBoxRecordByID).setOnGetRewardGiftLitener(new LiveTreasureBoxPopupWindow.OnGetRewardGiftLitener() {
                    @Override
                    public void onGetRewardGift(RewardGiftModel.DataBean rewardGift, int index) {
                        for (LiveGiftModel.DataBean gift : giftList) {
                            if (gift.getId() == Integer.valueOf(rewardGift.getGiftId())) {
                                //遍历把所有人气礼物一一插入礼物表
                                gift.setFreeNum(rewardGift.getNum());
                                break;
                            }
                        }
//                        if (index != 6 && index != 7) {//只有开启宝箱才发消息
//                            TreasureBoxMessage treasureBoxMessage = new TreasureBoxMessage("恭喜 " + mUserInfo.nickname + " 开启宝箱获得" + rewardGift.getTitle(), null);
//                            LiveKit.sendMessage(treasureBoxMessage);
//                        }
                    }
                }).show(v);
                break;
            case R.id.btn_more:
                break;
            case R.id.btn_definition:
                break;
            case R.id.btn_port:
                break;
            default:
                break;
        }
    }

    /**
     * 观看时间变化监听
     */
    private OnTimeChangeListener mOnTimeChangeListener;

    public interface OnTimeChangeListener {
        void onBoxProgressChanged(int index, int position);

        void onBoxProgressCompleted(int index);
    }

    public void setOnTimeChangeListener(OnTimeChangeListener mOnTimeChangeListener) {
        this.mOnTimeChangeListener = mOnTimeChangeListener;
    }

    /**
     * 发送点赞消息（冒泡）
     */
    private void sendHeartMessage() {
        final int heartIndex = random.nextInt(5);
        livePlayerBinding.heartLayout.post(new Runnable() {
            @Override
            public void run() {
                livePlayerBinding.heartLayout.addHeart(heartIndex);
            }
        });
        sentHeartCount++;
    }

    private void openDB() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                treasureBoxRecordDao = getTreasureBoxRecordDao();
                boolean b = treasureBoxRecordDao != null;
                clearExpireTreasureBoxRecord();
                treasureBoxRecordByID = getTreasureBoxRecordByID(liveId);
                boolean bb = treasureBoxRecordByID != null;
                int favoriteStatus = liveInfo.getIsFavor() == 0 ? -1 : 0;
                if (treasureBoxRecordByID == null) {
                    treasureBoxRecordByID = new TreasureChestModel(liveInfo.getLiveId(),
                            liveInfo.getStartTime(),
                            mSecond,
                            -1, -1, -1, -1, -1, -1, favoriteStatus, -1);
                } else {
                    mSecond += treasureBoxRecordByID.getWatchSeconds();
                    if (treasureBoxRecordByID.getFavoriteStatus() != 1) {
                        treasureBoxRecordByID.setFavoriteStatus(favoriteStatus);
                    }
                }
            }
        });
    }

    private TreasureChestModelDao getTreasureBoxRecordDao() {
        dbManager = DBManager.getInstance();
        boolean b = dbManager != null;

        if (dbManager != null && dbManager.getDaoSession() != null) {
            return dbManager.getDaoSession().getTreasureChestModelDao();
        } else {
            return null;
        }
    }

    public TreasureChestModel getTreasureBoxRecordByID(String liveID) {

        if (TextUtils.isEmpty(liveID)) {
            return null;
        } else {
            if (dbManager != null) {
                CloseableListIterator<TreasureChestModel> treasureChestModelCloseableListIterator = treasureBoxRecordDao.queryBuilder().listIterator();
                while (treasureChestModelCloseableListIterator.hasNext()) {
                    TreasureChestModel next = treasureChestModelCloseableListIterator.next();

                }
                return treasureBoxRecordDao.queryBuilder().where(TreasureChestModelDao.Properties.LiveId.eq(liveID)).unique();
            } else {
                return null;
            }
        }
    }

    /**
     * 删除过期记录
     *
     * @return
     */
    public void clearExpireTreasureBoxRecord() {
        if (treasureBoxRecordDao != null) {
            List<TreasureChestModel> allTreasureBoxRecord = treasureBoxRecordDao.loadAll();
            if (allTreasureBoxRecord == null || allTreasureBoxRecord.isEmpty()) {
                return;
            }
            for (TreasureChestModel record : allTreasureBoxRecord) {
                long startTime = Long.valueOf(record.getStartTime());
                long expireTime = 259200000000L;//三天时间戳
//                long expireTime = 780000L;//13分钟时间戳（测试）
                if (System.currentTimeMillis() - startTime > expireTime) {
                    treasureBoxRecordDao.delete(record);
                    return;
                }
            }
        }
    }

    /**
     * 播放视频
     */
    private void setVideo(String streamUrl) {
        videoView = livePlayerBinding.VideoView;
        AVOptions options = new AVOptions();
        //硬解
        options.setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_SW_DECODE);
        //打开视频单次http请求的超时时间，一次打开过程最多5次
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        //设置拖动模式
        options.setInteger(AVOptions.KEY_SEEK_MODE, 1);
        videoView.setAVOptions(options);
        //设置全屏模式
        videoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_FIT_PARENT);
        //用于监听播放器的状态信息
        videoView.setOnInfoListener(mOnInfoListener);
        // 用于监听播放器尺寸的变化
        videoView.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
        //用于监听当前播放器缓冲量占整个视频时长的百分比
        videoView.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
        //用于监听播放完成
        videoView.setOnCompletionListener(mOnCompletionListener);
        //用于监听播放错误信息
        videoView.setOnErrorListener(mOnErrorListener);
        //用于监听解码后音视频毁回调数据
        videoView.setOnVideoFrameListener(mOnVideoFrameListener);
        videoView.setOnAudioFrameListener(mOnAudioFrameListener);
        videoView.setVideoPath(streamUrl);
        videoView.start();

    }

    private PLOnInfoListener mOnInfoListener = new PLOnInfoListener() {
        @Override
        public void onInfo(int i, int extra) {
            switch (i) {
                case PLOnInfoListener.MEDIA_INFO_BUFFERING_START:
                    break;
                case PLOnInfoListener.MEDIA_INFO_BUFFERING_END:
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_RENDERING_START:
                    Log.i("tag", "first video render time: " + extra + "ms");
                    break;
                case PLOnInfoListener.MEDIA_INFO_AUDIO_RENDERING_START:
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_FRAME_RENDERING:
                    Log.i("tag", "video frame rendering, ts = " + extra);
                    break;
                case PLOnInfoListener.MEDIA_INFO_AUDIO_FRAME_RENDERING:
                    Log.i("tag", "audio frame rendering, ts = " + extra);
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_GOP_TIME:
                    Log.i("tag", "Gop Time: " + extra);
                    break;
                case PLOnInfoListener.MEDIA_INFO_SWITCHING_SW_DECODE:
                    Log.i("tag", "Hardware decoding failure, switching software decoding!");
                    break;
                case PLOnInfoListener.MEDIA_INFO_METADATA:
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_BITRATE:
                case PLOnInfoListener.MEDIA_INFO_VIDEO_FPS:
                    /**
                     * 比特率和帧率的变化
                     */
                    updateStatInfo();
                    break;
                case PLOnInfoListener.MEDIA_INFO_CONNECTED:
                    Log.i("tag", "Connected !");
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                    Log.i("tag", "Rotation changed: " + extra);
                default:
                    break;
            }
        }
    };
    private PLOnVideoSizeChangedListener mOnVideoSizeChangedListener = new PLOnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(int width, int height) {
            Log.i("tag", "onVideoSizeChanged: width = " + width + ", height = " + height);
        }
    };
    private PLOnBufferingUpdateListener mOnBufferingUpdateListener = new PLOnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(int precent) {
            Log.i("tag", "onBufferingUpdate: " + precent);
        }
    };

    private void updateStatInfo() {
        long bitrate = videoView.getVideoBitrate() / 1024;
        final String stat = bitrate + "kbps, " + videoView.getVideoFps() + "fps";

    }

    private PLOnCompletionListener mOnCompletionListener = new PLOnCompletionListener() {
        @Override
        public void onCompletion() {
            Log.i("tag", "Play Completed !");
            finish();
        }
    };
    private PLOnErrorListener mOnErrorListener = new PLOnErrorListener() {
        @Override
        public boolean onError(int errorCode) {
            Log.i("tag", "Error happened, errorCode = " + errorCode);
            switch (errorCode) {
                case PLOnErrorListener.ERROR_CODE_IO_ERROR:
                    /**
                     * SDK will do reconnecting automatically
                     */
                    Log.i("tag", "IO Error!");
                    if (!NetworkUtil.isNetworkAvailable(mContext)) {
                        customToast.warnToast("网络异常");
                        finish();
                        return true;
                    }
                    return false;
                case PLOnErrorListener.ERROR_CODE_OPEN_FAILED:
                    Log.i("tag", "failed to open player !");
                    break;
                case PLOnErrorListener.ERROR_CODE_SEEK_FAILED:
                    Log.i("tag", "failed to seek !");
                    break;
                default:
                    Log.i("tag", "unknown error !");
                    break;
            }
            return true;
        }
    };
    private PLOnVideoFrameListener mOnVideoFrameListener = new PLOnVideoFrameListener() {
        @Override
        public void onVideoFrameAvailable(byte[] data, int size, int width, int height, int format, long ts) {
            Log.i("tag", "onVideoFrameAvailable: " + size + ", " + width + " x " + height + ", " + format + ", " + ts);

        }
    };

    private PLOnAudioFrameListener mOnAudioFrameListener = new PLOnAudioFrameListener() {
        @Override
        public void onAudioFrameAvailable(byte[] data, int size, int samplerate, int channels, int datawidth, long ts) {
            Log.i("tag", "onAudioFrameAvailable: " + size + ", " + samplerate + ", " + channels + ", " + datawidth + ", " + ts);
        }
    };

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case RESTART_AUTO_SCROLL:
                isScrollToBottom = true;
                break;
            case LiveKit.MESSAGE_SENT:
                MessageContent content = (MessageContent) msg.obj;
                if (content instanceof NotificationMessage) {
                    NotificationMessage notificationMessage = (NotificationMessage) content;
                    if (notificationMessage.getIsJoin().equals("0")) {
                        LiveKit.quitChatRoom(new RongIMClient.OperationCallback() {
                            @Override
                            public void onSuccess() {
                                finish();
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode errorCode) {

                            }
                        });
                        return false;
                    }

                } else if (content instanceof GiftMessage) {
                    return false;
                }
                chatListAdapter.addMessage(content);
                break;
            case LiveKit.MESSAGE_SEND_ERROR:
                Toast.makeText(LivePlayerActivity.this, "消息发送失败！", Toast.LENGTH_SHORT).show();
                break;
            case LiveKit.HEART_SENT:
                if (sentHeartCount != 0) {
                    HeartMessage heartMessage = new HeartMessage(userInfo, sentHeartCount + "");
                    LiveKit.sendMessage(heartMessage);
                    sentHeartCount = 0;
                }
                handler.sendEmptyMessageDelayed(LiveKit.HEART_SENT, 3000);
                break;
            case LiveKit.HEART_ARRIVED:
                if (arrivedHeartCount != 0) {
                    livePlayerBinding.heartLayout.addHeart(random.nextInt(5));
                    arrivedHeartCount--;
                }
                handler.sendEmptyMessageDelayed(LiveKit.HEART_ARRIVED, 150);
                break;
            case START_TIMING:
                mSecond++;
                //表示几个宝箱待领取状态
                index = (int) (mSecond / WAITING_TIME);
                //msecond%WATING_TIME表示某一个宝箱正在进行的进度
                position = (int) (mSecond % WAITING_TIME * 10000 / WAITING_TIME);
                switch (index) {
                    case 0:
                        if (mOnTimeChangeListener != null) {
                            mOnTimeChangeListener.onBoxProgressChanged(index, position);
                        }
                        if (mSecond >= 59) {
                            treasureBoxRecordByID.setBox1Status(0);
                            if (mOnTimeChangeListener != null) {
                                mOnTimeChangeListener.onBoxProgressCompleted(index);
                            }
                        }
                        break;
                    case 1:
                        if (mOnTimeChangeListener != null) {
                            mOnTimeChangeListener.onBoxProgressChanged(index, position);
                        }
                        if (mSecond >= 119) {
                            treasureBoxRecordByID.setBox2Status(0);
                            if (mOnTimeChangeListener != null) {
                                mOnTimeChangeListener.onBoxProgressCompleted(index);
                            }
                        }
                        break;
                    case 2:
                        if (mOnTimeChangeListener != null) {
                            mOnTimeChangeListener.onBoxProgressChanged(index, position);
                        }
                        if (mSecond >= 179) {
                            treasureBoxRecordByID.setBox3Status(0);
                            if (mOnTimeChangeListener != null) {
                                mOnTimeChangeListener.onBoxProgressCompleted(index);
                            }
                        }
                        break;
                    case 3:
                        if (mOnTimeChangeListener != null) {
                            mOnTimeChangeListener.onBoxProgressChanged(index, position);
                        }
                        if (mSecond >= 239) {
                            treasureBoxRecordByID.setBox4Status(0);
                            if (mOnTimeChangeListener != null) {
                                mOnTimeChangeListener.onBoxProgressCompleted(index);
                            }
                        }
                        break;
                    case 4:
                        if (mOnTimeChangeListener != null) {
                            mOnTimeChangeListener.onBoxProgressChanged(index, position);
                        }
                        if (mSecond >= 299) {
                            treasureBoxRecordByID.setBox5Status(0);
                            if (mOnTimeChangeListener != null) {
                                mOnTimeChangeListener.onBoxProgressCompleted(index);
                            }
                        }
                        break;
                    case 5:
                        if (mOnTimeChangeListener != null) {
                            mOnTimeChangeListener.onBoxProgressChanged(index, position);
                        }
                        if (mSecond >= 359) {
                            treasureBoxRecordByID.setBox6Status(0);
                            if (mOnTimeChangeListener != null) {
                                mOnTimeChangeListener.onBoxProgressCompleted(index);
                            }
                        }
                        break;
                    default:
                        break;
                }
                handler.sendEmptyMessageDelayed(START_TIMING, 1000);
                break;
            default:
                break;

        }
        refreshChatListView();
        return false;
    }

    /**
     * 退出聊天室
     */
    private void quitChatRoom() {
        NotificationMessage message = new NotificationMessage(userInfo, "退出直播间", "1", "0");
        LiveKit.sendMessage(message);
    }

    /**
     * 直播礼物列表
     */
    private void LiveGiftData(final boolean isPopupWindow) {
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
                        LivePopularityGiftData(isPopupWindow);

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 人气礼物列表
     */
    private void LivePopularityGiftData(final boolean isPopupWindow) {
        final Observable<LivePopularityGiftModel> livePopularityGift = liveRequest.livePopularityGift();
        livePopularityGift
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LivePopularityGiftModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LivePopularityGiftModel livePopularityGiftModel) {
                        if (!giftList.isEmpty()) {
                            if (livePopularityGiftModel.getData() != null && !livePopularityGiftModel.getData().isEmpty()) {
                                for (LiveGiftModel.DataBean gift : giftList) {
                                    for (LivePopularityGiftModel.DataBean popularGift : livePopularityGiftModel.getData()) {
                                        if (gift.getId() == Integer.valueOf(popularGift.getGiftId())) {
                                            //遍历把所有人气礼物一一插入礼物表
                                            gift.setFreeNum(popularGift.getNum());
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (isPopupWindow) {
                            LiveSendGiftPopupWindow.with(LivePlayerActivity.this, true, giftList).setOnGiftSendListener(new LiveSendGiftPopupWindow.OnGiftSendListener() {
                                @Override
                                public void onGiftSend(LiveGiftModel.DataBean gift) {

                                }

                            }).show(livePlayerBinding.getRoot());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void createGiftPopupWindow(View v) {
        if (giftList.isEmpty()) {
            LiveGiftData(true);
        }
        LiveSendGiftPopupWindow.with(LivePlayerActivity.this, true, giftList).setOnGiftSendListener(new LiveSendGiftPopupWindow.OnGiftSendListener() {
            @Override
            public void onGiftSend(LiveGiftModel.DataBean gift) {
                livePlayerBinding.giftRoot.loadGift(new GiftEntity(gift, userInfo.getData()));
                GiftMessage giftMessage = new GiftMessage(userInfo, gift.getId() + "", "1");
                LiveKit.sendMessage(giftMessage);
            }
        }).show(v);


    }

}
