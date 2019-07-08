package com.shidonghui.mymagic.widget.dialog;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;


import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.live.adapter.LiveReceivedGiftListAdapter;
import com.shidonghui.mymagic.databinding.PopupwindowLiveReceivedGiftBinding;
import com.shidonghui.mymagic.model.GiftEntity;

import java.util.List;
/**
 * @author ZhangKun
 * @create 2019/6/10
 * @Describe 主播收到的礼物
 */

public class LiveReceivedGiftPopupWindow extends PopupWindow {

    private PopupwindowLiveReceivedGiftBinding dataBinding;
    private Activity mActivity;
    private static LiveReceivedGiftPopupWindow popWindow;

    public static LiveReceivedGiftPopupWindow with(Activity activity, List<GiftEntity> mArrivedGiftList) {
        if (popWindow == null) {
            popWindow = new LiveReceivedGiftPopupWindow(activity, mArrivedGiftList);
        }
        return popWindow;
    }

    @SuppressLint("InflateParams")
    public LiveReceivedGiftPopupWindow(Activity activity, List<GiftEntity> mArrivedGiftList) {
        mActivity = activity;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.popupwindow_live_received_gift, null, false);

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
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        setBackgroundDrawable(dw);
        // 设置PopupWindow弹出窗体动画效果
        setAnimationStyle(R.style.dialogWindowAnim);

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append("共收到");
        String giftAmount = String.valueOf(mArrivedGiftList.size());
        SpannableStringBuilder colorStringBuilder = new SpannableStringBuilder(giftAmount);
        colorStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#fff000")), 0, giftAmount.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        stringBuilder.append(colorStringBuilder);
        stringBuilder.append("个礼物");
        dataBinding.tvReceivedGiftAmount.setText(stringBuilder);
        double totalAmount = 0;
        for (GiftEntity entity : mArrivedGiftList) {
            totalAmount += entity.getPrice();
        }
        dataBinding.tvReceivedMoney.setText(totalAmount + "币");

        LiveReceivedGiftListAdapter adapter = new LiveReceivedGiftListAdapter(mArrivedGiftList);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        dataBinding.recyclerView.setAdapter(adapter);
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
        super.dismiss();
        if (popWindow != null) {
            popWindow = null;
        }
    }

}
