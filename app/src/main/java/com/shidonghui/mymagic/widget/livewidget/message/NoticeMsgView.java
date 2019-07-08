package com.shidonghui.mymagic.widget.livewidget.message;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.shidonghui.mymagic.R;

import io.rong.imlib.model.MessageContent;
/**
 * @author ZhangKun
 * @create 2019/6/11
 * @Describe
 */
public class NoticeMsgView extends BaseMsgView {

    private TextView msgText;

    public NoticeMsgView(Context context) {
        super(context);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.msg_text_view, this);
        msgText =  view.findViewById(R.id.msg_text);
        msgText.setTextColor(ContextCompat.getColor(context, R.color.live_chat_list_name));
    }

    @Override
    public void setContent(MessageContent msgContent) {
        NoticeMessage msg = (NoticeMessage) msgContent;
        msgText.setText(msg.getContent());
    }
}
