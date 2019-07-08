package com.shidonghui.mymagic.widget.livewidget.message;

import android.content.Context;
import android.view.LayoutInflater;


import com.shidonghui.mymagic.R;

import io.rong.imlib.model.MessageContent;
/**
 * @author ZhangKun
 * @create 2019/6/11
 * @Describe
 */
public class UnknownMsgView extends BaseMsgView {

    public UnknownMsgView(Context context) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.msg_unknown_view, this);
    }

    @Override
    public void setContent(MessageContent msgContent) {
    }
}
