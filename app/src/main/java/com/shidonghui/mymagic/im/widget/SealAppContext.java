package com.shidonghui.mymagic.im.widget;

import android.content.Context;
import android.util.Log;

import com.shidonghui.mymagic.utils.GiftMessage;
import com.shidonghui.mymagic.widget.livewidget.LiveKit;
import com.shidonghui.mymagic.widget.livewidget.message.NotificationMessage;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

/**
 * @author ZhangKun
 * @create 2019/6/20
 * @Describe 融云监听集合
 */
public class SealAppContext implements RongIMClient.OnReceiveMessageListener {
    private Context mContext;

    private SealAppContext(Context mContext) {
        SealUserInfoManager.init(mContext);
        this.mContext = mContext;
    }

    public static SealAppContext sealAppContext;

    public static void init(Context context) {
        if (sealAppContext == null) {
            synchronized (SealAppContext.class) {
                if (sealAppContext == null) {
                    sealAppContext = new SealAppContext(context);
                }

            }
        }

    }

    @Override
    public boolean onReceived(Message message, int i) {
        MessageContent messageContent = message.getContent();
        if (messageContent instanceof GiftMessage || messageContent instanceof NotificationMessage) {
            LiveKit.handleEvent(LiveKit.MESSAGE_ARRIVED, message.getContent());
        }
        return false;
    }
}
