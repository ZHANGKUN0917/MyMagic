package com.shidonghui.mymagic.widget.rongyun;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * @author ZhangKun
 * @create 2019/6/12
 * @Describe 融云监听相关集合
 */
public class SealAppContext implements RongIMClient.OnReceiveMessageListener {
    @Override
    public boolean onReceived(Message message, int i) {
        return false;
    }
}
