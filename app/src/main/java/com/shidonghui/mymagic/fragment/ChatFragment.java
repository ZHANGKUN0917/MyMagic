package com.shidonghui.mymagic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shidonghui.mymagic.R;

import io.rong.imkit.fragment.ConversationListFragment;

/**
 * @author ZhangKun
 * @create 2019/7/8
 * @Describe
 */
public class ChatFragment extends ConversationListFragment {
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        linearLayout = findViewById(view, R.id.rc_status_bar);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnStatusBarClickLitener != null) {
                    mOnStatusBarClickLitener.onStatusBarClick();
                }
            }
        });
        return view;
    }

    private OnStatusBarClickLitener mOnStatusBarClickLitener;

    /**
     * 实现网络无连接提示的点击事件
     */
    public interface OnStatusBarClickLitener {
        void onStatusBarClick();
    }

    public void setOnItemClickLitener(OnStatusBarClickLitener mOnStatusBarClickLitener) {
        this.mOnStatusBarClickLitener = mOnStatusBarClickLitener;
    }
}
