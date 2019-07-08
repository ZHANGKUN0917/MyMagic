package com.shidonghui.mymagic.live;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.databinding.PopupwindowLiveSendGiftBinding;
import com.shidonghui.mymagic.live.adapter.GiftViewAdapter;
import com.shidonghui.mymagic.live.adapter.ViewPagerAdapter;
import com.shidonghui.mymagic.model.LiveGiftModel;
import com.shidonghui.mymagic.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ZhangKun
 * @create 2019/6/26
 * @Describe 礼物列表
 */
public class LiveSendGiftPopupWindow extends PopupWindow {
    private LiveGiftModel.DataBean currentIndexModel;
    private PopupwindowLiveSendGiftBinding dataBinding;
    private static LiveSendGiftPopupWindow popWindow;
    private Activity mActivity;
    private List<LiveGiftModel.DataBean> mGiftList = new ArrayList<>();
    private LayoutInflater mInflater;
    private int pageSize;
    /**
     * 当前显示是第几页
     */
    private int currentIndex = 0;
    /**
     * 页的总数
     */
    private int pageCount;
    private List<View> listGridView;


    public static LiveSendGiftPopupWindow with(Activity activity, boolean mIsEncOrientationPort, List<LiveGiftModel.DataBean> giftList) {
        if (popWindow == null) {
            popWindow = new LiveSendGiftPopupWindow(activity, mIsEncOrientationPort, giftList);
        }
        return popWindow;
    }

    public LiveSendGiftPopupWindow(final Activity activity, final boolean mIsEncOrientationPort, final List<LiveGiftModel.DataBean> giftList) {
        mActivity = activity;
        mGiftList = giftList;
        listGridView = new ArrayList<>();
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.popupwindow_live_send_gift, null, false);
        // 设置SelectPicPopupWindow的View
        setContentView(dataBinding.getRoot());
        // 设置SelectPicPopupWindow弹出窗体的宽
        setWidth(LayoutParams.MATCH_PARENT);

        // 设置SelectPicPopupWindow弹出窗体可点击
        setFocusable(true);
        setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        setBackgroundDrawable(dw);
        // 设置PopupWindow弹出窗体动画效果
        setAnimationStyle(R.style.dialogWindowAnim);
        currentIndexModel = mGiftList.get(0);
        currentIndexModel.setSelected(true);
        if (mIsEncOrientationPort) {
            //竖屏展示两行
            pageSize = 8;
            // 设置SelectPicPopupWindow弹出窗体的高
            setHeight(DisplayUtil.dip2px(250));
        } else {
            //横屏展示一行
            pageSize = DisplayUtil.getDisplayWidth() / DisplayUtil.dip2px(100);
            setHeight(DisplayUtil.dip2px(150));
        }
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(giftList.size() * 1.0 / pageSize);
        for (int i = 0; i < pageCount; i++) {
            GridView gridView = (GridView) inflater.inflate(R.layout.item_grid, null);
            if (mIsEncOrientationPort) {
                gridView.setNumColumns(4);
            } else {
                gridView.setNumColumns(pageSize);
            }
            final GiftViewAdapter giftViewAdapter = new GiftViewAdapter(mActivity, giftList, pageSize, i);
            gridView.setAdapter(giftViewAdapter);
            listGridView.add(gridView);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(activity, mGiftList.get(position).getTitle(), Toast.LENGTH_LONG).show();
                    updateGiftStatus(id, giftViewAdapter);
                }
            });
        }
        //设置适配器
        dataBinding.viewpager.setAdapter(new ViewPagerAdapter(listGridView));
        //送礼物
        dataBinding.tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnGiftSendListener != null) {
                    mOnGiftSendListener.onGiftSend(currentIndexModel);
                }
            }
        });

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
        currentIndexModel.setSelected(false);
        if (mActivity != null) {
            mActivity = null;
        }
        if (popWindow != null) {
            popWindow = null;
        }
    }

    private OnGiftSendListener mOnGiftSendListener;

    public interface OnGiftSendListener {
        void onGiftSend(LiveGiftModel.DataBean gift);
    }

    public LiveSendGiftPopupWindow setOnGiftSendListener(OnGiftSendListener onGiftSendListener) {
        this.mOnGiftSendListener = onGiftSendListener;
        return popWindow;
    }

    /**
     * 更新礼物状态
     *
     * @param id
     * @param gridAdapter
     */
    private void updateGiftStatus(long id, GiftViewAdapter gridAdapter) {
        for (int i = 0; i < mGiftList.size(); i++) {
            LiveGiftModel.DataBean gift = mGiftList.get(i);
            if (i == id) {
                Toast.makeText(mActivity, mGiftList.get(i).getTitle(), Toast.LENGTH_LONG).show();
                currentIndexModel = gift;
                gift.setSelected(true);
            } else {
                gift.setSelected(false);
            }
        }
        gridAdapter.notifyDataSetChanged();
    }


}
