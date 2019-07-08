package com.shidonghui.mymagic.widget.livewidget.message;

import android.content.Context;
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
public class NotificationMsgView extends BaseMsgView {

    private TextView infoText;

    public NotificationMsgView(Context context) {
        super(context);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.msg_notification_view, this);
        infoText = (TextView) view.findViewById(R.id.info_text);
    }

    @Override
    public void setContent(MessageContent msgContent) {
        NotificationMessage msg = (NotificationMessage) msgContent;
        infoText.setText(msg.getMessage());
    }
}
