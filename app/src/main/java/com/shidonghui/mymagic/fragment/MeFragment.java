package com.shidonghui.mymagic.fragment;

import android.databinding.ViewDataBinding;

import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.base.BaseFragment;
import com.shidonghui.mymagic.databinding.MeFragmentBinding;
import com.shidonghui.mymagic.request.LiveRequest;
import com.shidonghui.mymagic.request.MeRequest;

/**
 * @author ZhangKun
 * @create 2019/4/25
 * @Describe
 */
public class MeFragment extends BaseFragment  {
    private MeFragmentBinding meFragmentBinding;
    private LiveRequest liveRequest;
    private MeRequest meRequest;



    @Override
    protected int setContentView() {
        return R.layout.me_fragment;
    }

    @Override
    protected void initView(ViewDataBinding dataBinding) {
        meFragmentBinding = (MeFragmentBinding) dataBinding;


    }

    @Override
    protected void initData() {

    }




}
