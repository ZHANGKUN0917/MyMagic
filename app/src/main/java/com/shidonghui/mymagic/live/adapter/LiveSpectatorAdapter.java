package com.shidonghui.mymagic.live.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import com.shidonghui.mymagic.MyApp;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.model.LiveDetailsModel;

import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/6/21
 * @Describe 观众列表
 */
public class LiveSpectatorAdapter extends RecyclerView.Adapter<LiveSpectatorAdapter.MyViewHolder> {

    private List<LiveDetailsModel.DataBean.ItemsBean> spectatorList;

    public LiveSpectatorAdapter(List<LiveDetailsModel.DataBean.ItemsBean> spectatorList) {
        this.spectatorList = spectatorList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(MyApp.getContext()).inflate(R.layout.item_live_spectator, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        LiveDetailsModel.DataBean.ItemsBean userInfo = spectatorList.get(position);
        holder.avatar.setImageURI(userInfo.getPortraitUri());
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickLitener != null) {
                    mOnItemClickLitener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return spectatorList == null ? 0 : spectatorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView avatar;

        public MyViewHolder(View itemView) {
            super(itemView);
            avatar = (SimpleDraweeView) itemView.findViewById(R.id.avatar);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
