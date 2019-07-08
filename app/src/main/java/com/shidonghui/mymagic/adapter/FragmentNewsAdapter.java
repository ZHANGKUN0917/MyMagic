package com.shidonghui.mymagic.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.model.SociologyModel;

import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/5/23
 * @Describe
 */
public class FragmentNewsAdapter extends BaseQuickAdapter<SociologyModel.NewslistBean, BaseViewHolder> {

    public FragmentNewsAdapter(int layoutResId, @Nullable List<SociologyModel.NewslistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SociologyModel.NewslistBean item) {
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.sdv_cover);
        if (!TextUtils.isEmpty(item.getPicUrl())) {
            simpleDraweeView.setImageURI(item.getPicUrl());
        }
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_resource, item.getDescription());
    }
}
