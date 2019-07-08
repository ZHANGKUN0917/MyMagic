package com.shidonghui.mymagic.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.model.PictureModel;

import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/5/28
 * @Describe
 */
public class PictureAdapter extends BaseQuickAdapter<PictureModel.ResultsBean, BaseViewHolder> {

    public PictureAdapter(int layoutResId, @Nullable List<PictureModel.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PictureModel.ResultsBean item) {
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.sdv_head);
        simpleDraweeView.setImageURI(item.getUrl());

    }
}
