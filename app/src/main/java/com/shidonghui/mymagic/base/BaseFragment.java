package com.shidonghui.mymagic.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shidonghui.mymagic.utils.CustomToast;

/**
 * @author ZhangKun
 * @create 2019/5/14
 * @Describe
 */
public abstract  class BaseFragment extends Fragment {
    public Context mContext;
    public CustomToast customToast;
    private ViewDataBinding viewDataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, setContentView(), container, false);
        mContext = getContext();
        customToast = CustomToast.getInstance();
        initView(viewDataBinding);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 加载布局
     *
     * @return
     */
    protected abstract int setContentView();

    /**
     * 初始化布局
     *
     * @param dataBinding
     */
    protected abstract void initView(ViewDataBinding dataBinding);

    /**
     * 初始化数据
     */
    protected abstract void initData();

}
