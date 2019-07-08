package com.shidonghui.mymagic.live.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.model.LiveListModel;
import com.shidonghui.mymagic.utils.TimeUtils;

import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/6/14
 * @Describe
 */
public class LiveListAdapter extends BaseQuickAdapter<LiveListModel.DataBean, BaseViewHolder>{

    public LiveListAdapter(int layoutResId, @Nullable List<LiveListModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveListModel.DataBean item) {
        SimpleDraweeView simpleDraweeView=helper.getView(R.id.sdv_cover);
        simpleDraweeView.setImageURI(item.getLivePicture());
        helper.setText(R.id.tv_live_title,item.getLiveTitle())
              .setText(R.id.tv_live_time,TimeUtils.getDateStrFromTimestamp(Long.valueOf(item.getStartTime())));
        switch (item.getStatus()) {
            case "STARTED":
                helper.setText(R.id.tv_live_little_status,"直播中");
                break;
            case "PUSHING":
                helper.setText(R.id.tv_live_little_status,"预告");
                break;
            case "FINISH":
                helper.setText(R.id.tv_live_little_status,"回放");
                break;
            default:
                break;
        }


    }
}
