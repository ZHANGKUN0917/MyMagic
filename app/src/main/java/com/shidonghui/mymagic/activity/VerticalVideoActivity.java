package com.shidonghui.mymagic.activity;

import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.facebook.drawee.view.SimpleDraweeView;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.adapter.DouYinAdapter;
import com.shidonghui.mymagic.base.BaseActivity;
import com.shidonghui.mymagic.databinding.ActivityVerticalVideoBinding;
import com.shidonghui.mymagic.request.VideoRequest;
import com.shidonghui.mymagic.widget.videowidget.DouYinController;
import com.shidonghui.mymagic.utils.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/5/28
 * @Describe
 */
public class VerticalVideoActivity extends BaseActivity {
    private ActivityVerticalVideoBinding verticalVideoBinding;
    private List<String> urlList;
    private List<String> picList;
    private int position;
    private int mCurrentItem;
    private int pageTo = 3;


    private IjkVideoView mIjkVideoView;
    private DouYinController mDouYinController;
    private DouYinAdapter mDouYinAdapter;
    private List<View> mViews = new ArrayList<>();
    SimpleDraweeView mCover;

    private int mPlayingPosition;
    private VideoRequest videoRequest;
    //private List<VideoModel.V9LG4B3A0Bean> listData;


    @Override
    protected void initView(ViewDataBinding viewDataBinding) {
        verticalVideoBinding = (ActivityVerticalVideoBinding) viewDataBinding;
        position = getIntent().getIntExtra("position", -1);
        mCurrentItem = position;

        mIjkVideoView = new IjkVideoView(this);
        PlayerConfig config = new PlayerConfig.Builder().setLooping().build();
        mIjkVideoView.setPlayerConfig(config);
        mDouYinController = new DouYinController(this);
        mIjkVideoView.setVideoController(mDouYinController);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIjkVideoView.resume();

        if (mDouYinController != null) {
            mDouYinController.setSelect(false);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mIjkVideoView.pause();
        if (mDouYinController != null) {
            mDouYinController.getIvPlay().setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIjkVideoView.release();
    }

    @Override
    protected void initData() {
        urlList = new ArrayList<>();
        picList = new ArrayList<>();
        urlList.add("http://flv3.bn.netease.com/tvmrepo/2018/6/M/C/EDJTRBIMC/SD/EDJTRBIMC-mobile.mp4");
        urlList.add("http://flv3.bn.netease.com/tvmrepo/2018/6/M/B/EDJTRBCMB/SD/EDJTRBCMB-mobile.mp4");
        urlList.add("http://flv3.bn.netease.com/tvmrepo/2018/6/6/P/EDJTRB66P/SD/EDJTRB66P-mobile.mp4");
        urlList.add("http://flv3.bn.netease.com/tvmrepo/2018/6/F/G/EDJTRB3FG/SD/EDJTRB3FG-mobile.mp4");
        urlList.add("http://flv3.bn.netease.com/tvmrepo/2018/6/9/K/EDJTRB29K/SD/EDJTRB29K-mobile.mp4");
        urlList.add("http://flv3.bn.netease.com/tvmrepo/2018/6/H/0/EDJTRAOH0/SD/EDJTRAOH0-mobile.mp4");
        urlList.add("http://flv3.bn.netease.com/tvmrepo/2018/6/K/I/EDJTRAHKI/SD/EDJTRAHKI-mobile.mp4");
        urlList.add("http://flv3.bn.netease.com/tvmrepo/2018/6/F/F/EDJTRAIFF/SD/EDJTRAIFF-mobile.mp4");
        urlList.add("http://flv3.bn.netease.com/tvmrepo/2018/6/9/R/EDJTRAD9R/SD/EDJTRAD9R-mobile.mp4");
        urlList.add("http://flv3.bn.netease.com/tvmrepo/2018/6/A/H/EDJTRA9AH/SD/EDJTRA9AH-mobile.mp4");
        picList.add("http://p9-dy.byteimg.com/large/264ce0008f58361347858.jpeg");
        picList.add("http://p9-dy.byteimg.com/large/2636c0008fe9f435cf17b.jpeg");
        picList.add("http://p3-dy.byteimg.com/large/2651100066caad6d65262.jpeg");
        picList.add("http://p3-dy.byteimg.com/large/25b3b00078d83a72baaed.jpeg");
        picList.add("http://p3-dy.byteimg.com/large/23396000817cb09fbb131.jpeg");
        picList.add("http://p9-dy.byteimg.com/large/26576000253cedbcf79ba.jpeg");
        picList.add("http://p3-dy.byteimg.com/large/265ce0000ccc49cee478f.jpeg");
        picList.add("http://p3-dy.byteimg.com/large/2656c000184c604afca5f.jpeg");
        picList.add("http://p3-dy.byteimg.com/large/262c80008ca01f7468925.jpeg");
        picList.add("http://p3-dy.byteimg.com/large/2648500085c8d6368e6c4.jpeg");
        //getVideoData();
        getImageData();


    }

    private void startPlay() {
        View view = mViews.get(mCurrentItem);
        FrameLayout frameLayout = view.findViewById(R.id.container);
        mCover = view.findViewById(R.id.cover_img);

        mDouYinController.setSelect(false);

        if (mCover != null && mCover.getDrawable() != null) {
            mDouYinController.getThumb().setImageDrawable(mCover.getDrawable());
        }

        ViewGroup parent = (ViewGroup) mIjkVideoView.getParent();

        if (parent != null) {
            parent.removeAllViews();
        }

        frameLayout.addView(mIjkVideoView);
        mIjkVideoView.setUrl(urlList.get(mCurrentItem));
        mIjkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_DEFAULT);
        mIjkVideoView.start();
        mPlayingPosition = mCurrentItem;
    }

    public void getImageData() {
        for (int i = 0; i < picList.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.view_video_item, null);
            mCover = view.findViewById(R.id.cover_img);
            mCover.setImageURI(picList.get(i));
            mViews.add(view);
        }
        mDouYinAdapter = new DouYinAdapter(mViews);
        verticalVideoBinding.verticalViewPager.setAdapter(mDouYinAdapter);


        if (position != -1) {
            verticalVideoBinding.verticalViewPager.setCurrentItem(position);
        }


        verticalVideoBinding.verticalViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                mCurrentItem = position;
                mIjkVideoView.pause();
                /*if (mCurrentItem == listData.size() - 1) {
                    pageTo++;
                    getVideoData();
                }*/


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mPlayingPosition == mCurrentItem) {
                    return;
                }
                if (state == VerticalViewPager.SCROLL_STATE_IDLE) {
                    mIjkVideoView.release();
                    ViewParent parent = mIjkVideoView.getParent();
                    if (parent != null && parent instanceof FrameLayout) {
                        ((FrameLayout) parent).removeView(mIjkVideoView);
                    }
                    startPlay();
                }

            }

        });


        verticalVideoBinding.verticalViewPager.post(new Runnable() {
            @Override
            public void run() {
                startPlay();
            }
        });


    }

    @Override
    protected int setContentView() {
        return R.layout.activity_vertical_video;
    }

   /* private void getVideoData() {
        videoRequest = MyApp.getRetrofit2().create(VideoRequest.class);
        Observable<VideoModel> video = videoRequest.video(pageTo);
        video.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoModel videoModel) {
                        listData = videoModel.getV9LG4B3A0();
                        getImageData();
                        Log.i("asdf", "我走这了" + listData.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("asdf", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }*/
}
