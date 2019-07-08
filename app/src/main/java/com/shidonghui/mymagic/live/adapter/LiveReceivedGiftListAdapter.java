package com.shidonghui.mymagic.live.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shidonghui.mymagic.MyApp;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.model.GiftEntity;


import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/6/10
 * @Describe
 */

public class LiveReceivedGiftListAdapter extends RecyclerView.Adapter<LiveReceivedGiftListAdapter.MyViewHolder> {
    private List<GiftEntity> mArrivedGiftList;//收到的礼物

    public LiveReceivedGiftListAdapter(List<GiftEntity> mArrivedGiftList) {
        this.mArrivedGiftList = mArrivedGiftList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(MyApp.getContext()).inflate(R.layout.item_live_received_gift, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GiftEntity giftEntity = mArrivedGiftList.get(position);
        holder.avatar.setImageURI(giftEntity.getPortraitUri());
        holder.tv_name.setText(giftEntity.getName());
        holder.tv_desc.setText(giftEntity.getGiftName());
        holder.tv_gift_price.setText("+" + giftEntity.getPrice() + "币");
    }

    @Override
    public int getItemCount() {
        return mArrivedGiftList == null ? 0 : mArrivedGiftList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private View view_divider;
        private SimpleDraweeView avatar;
        private TextView tv_name;
        private TextView tv_desc;
        private TextView tv_gift_price;

        public MyViewHolder(View itemView) {
            super(itemView);
            view_divider = itemView.findViewById(R.id.view_divider);
            avatar = (SimpleDraweeView) itemView.findViewById(R.id.avatar);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
            tv_gift_price = (TextView) itemView.findViewById(R.id.tv_gift_price);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

//        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
