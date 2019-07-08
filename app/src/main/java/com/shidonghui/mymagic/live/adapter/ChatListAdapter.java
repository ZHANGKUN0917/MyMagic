package com.shidonghui.mymagic.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.shidonghui.mymagic.MyApp;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.widget.livewidget.message.BaseMsgView;
import com.shidonghui.mymagic.widget.livewidget.LiveKit;
import com.shidonghui.mymagic.widget.livewidget.message.NoticeMessage;
import com.shidonghui.mymagic.widget.livewidget.message.NoticeMsgView;
import com.shidonghui.mymagic.widget.livewidget.message.NotificationMessage;
import com.shidonghui.mymagic.widget.livewidget.message.NotificationMsgView;
import com.shidonghui.mymagic.widget.livewidget.message.TextMessage;
import com.shidonghui.mymagic.widget.livewidget.message.TextMsgView;
import com.shidonghui.mymagic.widget.livewidget.message.UnknownMsgView;

import java.util.ArrayList;

import io.rong.imlib.model.MessageContent;

/**
 * @author ZhangKun
 * @create 2019/6/11
 * @Describe
 */
public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.MyViewHolder> {

    private ArrayList<MessageContent> msgList;

    public ChatListAdapter() {
        msgList = new ArrayList<>();
    }

    @Override
    public ChatListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(MyApp.getContext()).inflate(R.layout.msg_base_view, parent, false));
    }

    @Override
    public void onBindViewHolder(final ChatListAdapter.MyViewHolder holder, final int position) {
        holder.item_layout.removeViews(0, holder.item_layout.getChildCount());
        final MessageContent msgContent = msgList.get(position);
        Class<? extends BaseMsgView> msgViewClass = LiveKit.getRegisterMessageView(msgContent.getClass());

        if (msgContent instanceof NotificationMessage) {
            try {
                NotificationMsgView notificationMsgView = (NotificationMsgView) msgViewClass.getConstructor(Context.class).newInstance(MyApp.getContext());
                notificationMsgView.setContent(msgContent);
                holder.item_layout.addView(notificationMsgView);
            } catch (Exception e) {
                throw new RuntimeException("baseMsgView newInstance failed.");
            }
        } else if (msgContent instanceof NoticeMessage) {
            try {
                NoticeMsgView noticeMsgView = (NoticeMsgView) msgViewClass.getConstructor(Context.class).newInstance(MyApp.getContext());
                noticeMsgView.setContent(msgContent);
                holder.item_layout.addView(noticeMsgView);
            } catch (Exception e) {
                throw new RuntimeException("baseMsgView newInstance failed.");
            }
        } else if (msgContent instanceof TextMessage) {
            try {
                TextMsgView textMsgView = (TextMsgView) msgViewClass.getConstructor(Context.class).newInstance(MyApp.getContext());
                textMsgView.setContent(msgContent);
                holder.item_layout.addView(textMsgView);
            } catch (Exception e) {
                throw new RuntimeException("baseMsgView newInstance failed.");
            }
        } else {
            holder.item_layout.addView(new UnknownMsgView(MyApp.getContext()));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickLitener != null) {
                    mOnItemClickLitener.onItemClick(msgContent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout item_layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_layout = (FrameLayout) itemView.findViewById(R.id.item_layout);
        }
    }

    public void addMessage(MessageContent msg) {
        msgList.add(msg);
    }

    public interface OnItemClickLitener {
        void onItemClick(MessageContent msgContent);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
