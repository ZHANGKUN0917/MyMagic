package com.shidonghui.mymagic.live.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.model.LiveGiftModel;

import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/6/27
 * @Describe
 */
public class GiftViewAdapter extends BaseAdapter {
    private List<LiveGiftModel.DataBean> giftList;
    private int pageSize;
    private LayoutInflater layoutInflater;
    private int currentIndex;
    private Context mContext;

    public GiftViewAdapter(Context mContext, List<LiveGiftModel.DataBean> giftList, int pageSize, int currentIndex) {
        layoutInflater = LayoutInflater.from(mContext);
        this.giftList = giftList;
        this.pageSize = pageSize;
        this.currentIndex = currentIndex;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return giftList.size() > (currentIndex + 1) * pageSize ? pageSize : (giftList.size() - currentIndex * pageSize);
    }

    @Override
    public Object getItem(int position) {
        return giftList.get(position + currentIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + currentIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_gift_info, parent, false);
            holder = new ViewHolder();
            holder.sdv_gift = convertView.findViewById(R.id.gift_img);
            holder.tv_price = convertView.findViewById(R.id.gift_price);
            holder.tv_title = convertView.findViewById(R.id.gift_title);
            holder.relativeLayout = convertView.findViewById(R.id.item_layout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + curIndex * pageSize
         */
        int pos = position + currentIndex * pageSize;
        holder.tv_title.setText(giftList.get(pos).getTitle());
        holder.sdv_gift.setImageURI(giftList.get(pos).getImgUrl());
        if (giftList.get(pos).isSelected()) {
            holder.relativeLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.item_gift_selector));
        } else {
            holder.relativeLayout.setBackground(null);
        }
        if (giftList.get(pos).getPrice() == 0) {
            holder.tv_price.setText("免费");
            holder.tv_price.setTextColor(ContextCompat.getColor(mContext, R.color.red));
        } else {
            holder.tv_price.setText(giftList.get(pos).getPrice() + "积分");
            holder.tv_price.setTextColor(ContextCompat.getColor(mContext, R.color.live_chat_list_name));
        }
        return convertView;
    }

    class ViewHolder {
        private TextView tv_price, tv_title, tv_line;
        private SimpleDraweeView sdv_gift;
        private RelativeLayout relativeLayout;

    }
}
