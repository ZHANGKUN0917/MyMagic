package com.shidonghui.mymagic.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;


import com.shidonghui.mymagic.MyApp;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.databinding.PopupwindowLiveTreasureBoxBinding;
import com.shidonghui.mymagic.db.TreasureChestModel;
import com.shidonghui.mymagic.live.activity.LivePlayerActivity;
import com.shidonghui.mymagic.model.RewardGiftModel;
import com.shidonghui.mymagic.request.LiveDetailsRequest;
import com.shidonghui.mymagic.request.LiveRequest;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 宝箱
 */
public class LiveTreasureBoxPopupWindow extends PopupWindow implements View.OnClickListener {

    private PopupwindowLiveTreasureBoxBinding dataBinding;
    private LivePlayerActivity mActivity;
    private static LiveTreasureBoxPopupWindow popWindow;
    private TreasureChestModel mLiveTreasureBoxRecord;
    private boolean isIndexChanged = true;
    private LiveRequest liveRequest;
    private String liveId;

    public static LiveTreasureBoxPopupWindow with(LivePlayerActivity activity, TreasureChestModel liveTreasureBoxRecord) {
        if (popWindow == null) {
            popWindow = new LiveTreasureBoxPopupWindow(activity, liveTreasureBoxRecord);
        }
        return popWindow;
    }

    @SuppressLint("InflateParams")
    public LiveTreasureBoxPopupWindow(LivePlayerActivity activity, TreasureChestModel liveTreasureBoxRecord) {
        mActivity = activity;
        liveId = liveTreasureBoxRecord.getLiveId();
        mLiveTreasureBoxRecord = liveTreasureBoxRecord;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.popupwindow_live_treasure_box, null, false);

        dataBinding.ivTreasureBox1.setOnClickListener(this);
        dataBinding.ivTreasureBox2.setOnClickListener(this);
        dataBinding.ivTreasureBox3.setOnClickListener(this);
        dataBinding.ivTreasureBox4.setOnClickListener(this);
        dataBinding.ivTreasureBox5.setOnClickListener(this);
        dataBinding.ivTreasureBox6.setOnClickListener(this);
        dataBinding.tvBox1Status.setOnClickListener(this);
        dataBinding.tvBox2Status.setOnClickListener(this);
        dataBinding.tvBox3Status.setOnClickListener(this);
        dataBinding.tvBox4Status.setOnClickListener(this);
        dataBinding.tvBox5Status.setOnClickListener(this);
        dataBinding.tvBox6Status.setOnClickListener(this);
        dataBinding.tvShareLiveStatus.setOnClickListener(this);
        dataBinding.tvAttentionAnchorStatus.setOnClickListener(this);

        // 设置SelectPicPopupWindow的View
        setContentView(dataBinding.getRoot());
        // 设置SelectPicPopupWindow弹出窗体的宽
        setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        setFocusable(true);
        setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener，设置其他控件变化等操作
        setBackgroundDrawable(dw);
        // 设置PopupWindow弹出窗体动画效果
        setAnimationStyle(R.style.dialogWindowAnim);

        setBox1Status();
        setBox2Status();
        setBox3Status();
        setBox4Status();
        setBox5Status();
        setBox6Status();
        setShareStatus();
        setFavoriteStatus();

        mActivity.setOnTimeChangeListener(new LivePlayerActivity.OnTimeChangeListener() {
            @Override
            public void onBoxProgressChanged(int index, int position) {
                switch (index) {
                    case 0:
                        if (isIndexChanged) {
                            isIndexChanged = false;
                            dataBinding.ivTreasureBox1.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_can_open));
                            dataBinding.ivTreasureBox1.setEnabled(false);
                            dataBinding.tvBox1Status.setText("进行中");
                            dataBinding.tvBox1Status.setSelected(false);
                        }
                        dataBinding.ivBox1Status.setImageLevel(position);
                        break;
                    case 1:
                        if (isIndexChanged) {
                            isIndexChanged = false;
                            dataBinding.ivTreasureBox2.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_can_open));
                            dataBinding.ivTreasureBox2.setEnabled(false);
                            dataBinding.tvBox2Status.setText("进行中");
                            dataBinding.tvBox2Status.setSelected(false);
                        }
                        dataBinding.ivBox2Status.setImageLevel(position);
                        break;
                    case 2:
                        if (isIndexChanged) {
                            isIndexChanged = false;
                            dataBinding.ivTreasureBox3.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_can_open));
                            dataBinding.ivTreasureBox3.setEnabled(false);
                            dataBinding.tvBox3Status.setText("进行中");
                            dataBinding.tvBox3Status.setSelected(false);
                        }
                        dataBinding.ivBox3Status.setImageLevel(position);
                        break;
                    case 3:
                        if (isIndexChanged) {
                            isIndexChanged = false;
                            dataBinding.ivTreasureBox4.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_can_open));
                            dataBinding.ivTreasureBox4.setEnabled(false);
                            dataBinding.tvBox4Status.setText("进行中");
                            dataBinding.tvBox4Status.setSelected(false);
                        }
                        dataBinding.ivBox4Status.setImageLevel(position);
                        break;
                    case 4:
                        if (isIndexChanged) {
                            isIndexChanged = false;
                            dataBinding.ivTreasureBox5.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_can_open));
                            dataBinding.ivTreasureBox5.setEnabled(false);
                            dataBinding.tvBox5Status.setText("进行中");
                            dataBinding.tvBox5Status.setSelected(false);
                        }
                        dataBinding.ivBox5Status.setImageLevel(position);
                        break;
                    case 5:
                        if (isIndexChanged) {
                            isIndexChanged = false;
                            dataBinding.ivTreasureBox6.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_can_open));
                            dataBinding.ivTreasureBox6.setEnabled(false);
                            dataBinding.tvBox6Status.setText("进行中");
                            dataBinding.tvBox6Status.setSelected(false);
                        }
                        dataBinding.ivBox6Status.setImageLevel(position);
                        break;
                }
            }

            @Override
            public void onBoxProgressCompleted(int index) {
                isIndexChanged = true;
                switch (index) {
                    case 0:
                        setBox1Status();
                        break;
                    case 1:
                        setBox2Status();
                        break;
                    case 2:
                        setBox3Status();
                        break;
                    case 3:
                        setBox4Status();
                        break;
                    case 4:
                        setBox5Status();
                        break;
                    case 5:
                        setBox6Status();
                        break;
                }
            }
        });

    }

    /**
     * 设置关注主播领取状态
     */
    private void setFavoriteStatus() {
        switch (mLiveTreasureBoxRecord.getFavoriteStatus()) {
            case -1:
                dataBinding.tvAttentionAnchorStatus.setText("未完成");
                dataBinding.tvAttentionAnchorStatus.setSelected(true);
                dataBinding.tvAttentionAnchorStatus.setEnabled(false);
                break;
            case 0:
                dataBinding.tvAttentionAnchorStatus.setText("可领取");
                dataBinding.tvAttentionAnchorStatus.setSelected(false);
                dataBinding.tvAttentionAnchorStatus.setEnabled(true);
                break;
            case 1:
                dataBinding.tvAttentionAnchorStatus.setText("已领取");
                dataBinding.tvAttentionAnchorStatus.setSelected(true);
                dataBinding.tvAttentionAnchorStatus.setEnabled(false);
                break;
        }
    }

    /**
     * 设置分享领取状态
     */
    private void setShareStatus() {
        switch (mLiveTreasureBoxRecord.getShareStatus()) {
            case -1:
                dataBinding.tvShareLiveStatus.setText("未完成");
                dataBinding.tvShareLiveStatus.setSelected(true);
                dataBinding.tvShareLiveStatus.setEnabled(false);
                break;
            case 0:
                dataBinding.tvShareLiveStatus.setText("可领取");
                dataBinding.tvShareLiveStatus.setSelected(false);
                dataBinding.tvShareLiveStatus.setEnabled(true);
                break;
            case 1:
                dataBinding.tvShareLiveStatus.setText("已领取");
                dataBinding.tvShareLiveStatus.setSelected(true);
                dataBinding.tvShareLiveStatus.setEnabled(false);
                break;
        }
    }

    /**
     * 设置宝箱1开启状态
     */
    private void setBox1Status() {
        Log.i("asdf", mLiveTreasureBoxRecord.getBox1Status() + "-=---");
        switch (mLiveTreasureBoxRecord.getBox1Status()) {
            case -1:
                dataBinding.ivTreasureBox1.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_canot_open));
                dataBinding.ivTreasureBox1.setEnabled(false);
                dataBinding.tvBox1Status.setText("等待中");
                dataBinding.tvBox1Status.setSelected(true);
                dataBinding.tvBox1Status.setEnabled(false);
                dataBinding.ivBox1Status.setImageLevel(0);
                break;
            case 0:
                dataBinding.ivTreasureBox1.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_can_open));
                dataBinding.ivTreasureBox1.setEnabled(true);
                dataBinding.tvBox1Status.setText("可领取");
                dataBinding.tvBox1Status.setSelected(false);
                dataBinding.tvBox1Status.setEnabled(true);
                dataBinding.ivBox1Status.setImageLevel(10000);
                break;
            case 1:
                dataBinding.ivTreasureBox1.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_opened));
                dataBinding.ivTreasureBox1.setEnabled(false);
                dataBinding.tvBox1Status.setText("已领取");
                dataBinding.tvBox1Status.setSelected(true);
                dataBinding.tvBox1Status.setEnabled(false);
                dataBinding.ivBox1Status.setImageLevel(0);
                break;
        }
    }

    /**
     * 设置宝箱2开启状态
     */
    private void setBox2Status() {
        switch (mLiveTreasureBoxRecord.getBox2Status()) {
            case -1:
                dataBinding.ivTreasureBox2.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_canot_open));
                dataBinding.ivTreasureBox2.setEnabled(false);
                dataBinding.tvBox2Status.setText("等待中");
                dataBinding.tvBox2Status.setSelected(true);
                dataBinding.tvBox2Status.setEnabled(false);
                dataBinding.ivBox2Status.setImageLevel(0);
                break;
            case 0:
                dataBinding.ivTreasureBox2.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_can_open));
                dataBinding.ivTreasureBox2.setEnabled(true);
                dataBinding.tvBox2Status.setText("可领取");
                dataBinding.tvBox2Status.setSelected(false);
                dataBinding.tvBox2Status.setEnabled(true);
                dataBinding.ivBox2Status.setImageLevel(10000);
                break;
            case 1:
                dataBinding.ivTreasureBox2.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_opened));
                dataBinding.ivTreasureBox2.setEnabled(false);
                dataBinding.tvBox2Status.setText("已领取");
                dataBinding.tvBox2Status.setSelected(true);
                dataBinding.tvBox2Status.setEnabled(false);
                dataBinding.ivBox2Status.setImageLevel(0);
                break;
        }
    }

    /**
     * 设置宝箱3开启状态
     */
    private void setBox3Status() {
        switch (mLiveTreasureBoxRecord.getBox3Status()) {
            case -1:
                dataBinding.ivTreasureBox3.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_canot_open));
                dataBinding.ivTreasureBox3.setEnabled(false);
                dataBinding.tvBox3Status.setText("等待中");
                dataBinding.tvBox3Status.setSelected(true);
                dataBinding.tvBox3Status.setEnabled(false);
                dataBinding.ivBox3Status.setImageLevel(0);
                break;
            case 0:
                dataBinding.ivTreasureBox3.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_can_open));
                dataBinding.ivTreasureBox3.setEnabled(true);
                dataBinding.tvBox3Status.setText("可领取");
                dataBinding.tvBox3Status.setSelected(false);
                dataBinding.tvBox3Status.setEnabled(true);
                dataBinding.ivBox3Status.setImageLevel(10000);
                break;
            case 1:
                dataBinding.ivTreasureBox3.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_opened));
                dataBinding.ivTreasureBox3.setEnabled(false);
                dataBinding.tvBox3Status.setText("已领取");
                dataBinding.tvBox3Status.setSelected(true);
                dataBinding.tvBox3Status.setEnabled(false);
                dataBinding.ivBox3Status.setImageLevel(0);
                break;
        }
    }

    /**
     * 设置宝箱4开启状态
     */
    private void setBox4Status() {
        switch (mLiveTreasureBoxRecord.getBox4Status()) {
            case -1:
                dataBinding.ivTreasureBox4.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_canot_open));
                dataBinding.ivTreasureBox4.setEnabled(false);
                dataBinding.tvBox4Status.setText("等待中");
                dataBinding.tvBox4Status.setSelected(true);
                dataBinding.tvBox4Status.setEnabled(false);
                dataBinding.ivBox4Status.setImageLevel(0);
                break;
            case 0:
                dataBinding.ivTreasureBox4.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_can_open));
                dataBinding.ivTreasureBox4.setEnabled(true);
                dataBinding.tvBox4Status.setText("可领取");
                dataBinding.tvBox4Status.setSelected(false);
                dataBinding.tvBox4Status.setEnabled(true);
                dataBinding.ivBox4Status.setImageLevel(10000);
                break;
            case 1:
                dataBinding.ivTreasureBox4.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_opened));
                dataBinding.ivTreasureBox4.setEnabled(false);
                dataBinding.tvBox4Status.setText("已领取");
                dataBinding.tvBox4Status.setSelected(true);
                dataBinding.tvBox4Status.setEnabled(false);
                dataBinding.ivBox4Status.setImageLevel(0);
                break;
        }
    }

    /**
     * 设置宝箱5开启状态
     */
    private void setBox5Status() {
        switch (mLiveTreasureBoxRecord.getBox5Status()) {
            case -1:
                dataBinding.ivTreasureBox5.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_canot_open));
                dataBinding.ivTreasureBox5.setEnabled(false);
                dataBinding.tvBox5Status.setText("等待中");
                dataBinding.tvBox5Status.setSelected(true);
                dataBinding.tvBox5Status.setEnabled(false);
                dataBinding.ivBox5Status.setImageLevel(0);
                break;
            case 0:
                dataBinding.ivTreasureBox5.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_can_open));
                dataBinding.ivTreasureBox5.setEnabled(true);
                dataBinding.tvBox5Status.setText("可领取");
                dataBinding.tvBox5Status.setSelected(false);
                dataBinding.tvBox5Status.setEnabled(true);
                dataBinding.ivBox5Status.setImageLevel(10000);
                break;
            case 1:
                dataBinding.ivTreasureBox5.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_opened));
                dataBinding.ivTreasureBox5.setEnabled(false);
                dataBinding.tvBox5Status.setText("已领取");
                dataBinding.tvBox5Status.setSelected(true);
                dataBinding.tvBox5Status.setEnabled(false);
                dataBinding.ivBox5Status.setImageLevel(0);
                break;
        }
    }

    /**
     * 设置宝箱6开启状态
     */
    private void setBox6Status() {
        switch (mLiveTreasureBoxRecord.getBox6Status()) {
            case -1:
                dataBinding.ivTreasureBox6.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_canot_open));
                dataBinding.ivTreasureBox6.setEnabled(false);
                dataBinding.tvBox6Status.setText("等待中");
                dataBinding.tvBox6Status.setSelected(true);
                dataBinding.tvBox6Status.setEnabled(false);
                dataBinding.ivBox6Status.setImageLevel(0);
                break;
            case 0:
                dataBinding.ivTreasureBox6.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_can_open));
                dataBinding.ivTreasureBox6.setEnabled(true);
                dataBinding.tvBox6Status.setText("可领取");
                dataBinding.tvBox6Status.setSelected(false);
                dataBinding.tvBox6Status.setEnabled(true);
                dataBinding.ivBox6Status.setImageLevel(10000);
                break;
            case 1:
                dataBinding.ivTreasureBox6.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.box_opened));
                dataBinding.ivTreasureBox6.setEnabled(false);
                dataBinding.tvBox6Status.setText("已领取");
                dataBinding.tvBox6Status.setSelected(true);
                dataBinding.tvBox6Status.setEnabled(false);
                dataBinding.ivBox6Status.setImageLevel(0);
                break;
        }
    }

    /**
     * 显示popupWindow
     *
     * @param view
     */
    public void show(View view) {
        showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void dismiss() {
        if (mActivity != null) {
            mActivity.setOnTimeChangeListener(null);
            mActivity = null;
        }
        if (popWindow != null) {
            popWindow = null;
        }
        super.dismiss();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickLitener != null) {
            mOnItemClickLitener.onItemClick(v);
        }
        switch (v.getId()) {
            case R.id.tv_box1_status:
            case R.id.iv_treasure_box1:
                Log.i("asdf", "我点击饿了");
                getRewardGift(0);
                break;
            case R.id.tv_box2_status:
            case R.id.iv_treasure_box2:
                getRewardGift(1);
                break;
            case R.id.tv_box3_status:
            case R.id.iv_treasure_box3:
                getRewardGift(2);
                break;
            case R.id.tv_box4_status:
            case R.id.iv_treasure_box4:
                getRewardGift(3);
                break;
            case R.id.tv_box5_status:
            case R.id.iv_treasure_box5:
                getRewardGift(4);
                break;
            case R.id.tv_box6_status:
            case R.id.iv_treasure_box6:
                getRewardGift(5);
                break;
            case R.id.tv_share_live_status:
                getRewardGift(6);
                break;
            case R.id.tv_attention_anchor_status:
                getRewardGift(7);
                break;
        }
    }


    private void getRewardGift(final int index) {
        liveRequest = MyApp.getRetrofit4().create(LiveRequest.class);
        Observable<RewardGiftModel> rewardGift = liveRequest.rewardGift(new LiveDetailsRequest(liveId));
        rewardGift.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RewardGiftModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RewardGiftModel rewardGiftModel) {
                        switch (index) {
                            case 0:
                                mLiveTreasureBoxRecord.setBox1Status(1);
                                setBox1Status();
                                break;
                            case 1:
                                mLiveTreasureBoxRecord.setBox2Status(1);
                                setBox2Status();
                                break;
                            case 2:
                                mLiveTreasureBoxRecord.setBox3Status(1);
                                setBox3Status();
                                break;
                            case 3:
                                mLiveTreasureBoxRecord.setBox4Status(1);
                                setBox4Status();
                                break;
                            case 4:
                                mLiveTreasureBoxRecord.setBox5Status(1);
                                setBox5Status();
                                break;
                            case 5:
                                mLiveTreasureBoxRecord.setBox6Status(1);
                                setBox6Status();
                                break;
                            case 6:
                                mLiveTreasureBoxRecord.setShareStatus(1);
                                setShareStatus();
                                break;
                            case 7:
                                mLiveTreasureBoxRecord.setFavoriteStatus(1);
                                setFavoriteStatus();
                                break;
                        }
                        if (mOnGetRewardGiftLitener != null) {
                            mOnGetRewardGiftLitener.onGetRewardGift(rewardGiftModel.getData(), index);
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

    public interface OnGetRewardGiftLitener {
        void onGetRewardGift(RewardGiftModel.DataBean rewardGift, int index);
    }

    private OnGetRewardGiftLitener mOnGetRewardGiftLitener;

    public LiveTreasureBoxPopupWindow setOnGetRewardGiftLitener(OnGetRewardGiftLitener mOnGetRewardGiftLitener) {
        this.mOnGetRewardGiftLitener = mOnGetRewardGiftLitener;
        return popWindow;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public LiveTreasureBoxPopupWindow setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
        return popWindow;
    }

}
