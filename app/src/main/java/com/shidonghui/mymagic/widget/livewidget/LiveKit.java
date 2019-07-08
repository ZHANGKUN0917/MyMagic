package com.shidonghui.mymagic.widget.livewidget;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.shidonghui.mymagic.utils.GiftMessage;
import com.shidonghui.mymagic.widget.livewidget.message.BaseMsgView;
import com.shidonghui.mymagic.widget.livewidget.message.HeartMessage;
import com.shidonghui.mymagic.widget.livewidget.message.NoticeMessage;
import com.shidonghui.mymagic.widget.livewidget.message.NoticeMsgView;
import com.shidonghui.mymagic.widget.livewidget.message.NotificationMessage;
import com.shidonghui.mymagic.widget.livewidget.message.NotificationMsgView;
import com.shidonghui.mymagic.widget.livewidget.message.TextMessage;
import com.shidonghui.mymagic.widget.livewidget.message.TextMsgView;

import java.util.ArrayList;
import java.util.HashMap;

import io.rong.imlib.AnnotationNotFoundException;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

/**
 * @author ZhangKun
 * @create 2019/6/10
 * @Describe 直播管理类
 */
public class LiveKit {
    /**
     * 事件代码
     */
    /**
     * 接收消息
     */
    public static final int MESSAGE_ARRIVED = 0;
    public static final int MESSAGE_SENT = 1;//发送消息
    public static final int HEART_SENT = 2;//发送点赞
    public static final int HEART_ARRIVED = 3;//接收点赞
    public static final int REFRESH_GIFT_CACHE = 4;//刷新礼物缓存池
    /**
     * 当前聊天室房间id
     */
    private static String currentRoomId;
    /**
     * 事件监听者列表
     */
    private static ArrayList<Handler> eventHandlerList = new ArrayList<>();
    /**
     * 事件错误代码
     */
    public static final int MESSAGE_SEND_ERROR = -1;

    /**
     * 初始化方法，在整个应用程序全局只需要调用一次，建议在Application 继承类中调用。
     * <p/>
     * <strong>注意：</strong>其余方法都需要在这之后调用。
     *
     * @param context Application类的Context
     */
    public static void init(Context context) {
        registerMessageType(GiftMessage.class);
        registerMessageType(HeartMessage.class);
        registerMessageType(NotificationMessage.class);
        registerMessageView(TextMessage.class, TextMsgView.class);
        registerMessageView(NoticeMessage.class, NoticeMsgView.class);
        registerMessageView(NotificationMessage.class, NotificationMsgView.class);

    }

    /**
     * 消息类与消息UI展示类对应表
     */
    private static HashMap<Class<? extends MessageContent>, Class<? extends BaseMsgView>> msgViewMap = new HashMap<>();

    /**
     * 注册自定义消息。
     *
     * @param msgType 自定义消息类型
     */
    public static void registerMessageType(Class<? extends MessageContent> msgType) {
        try {
            RongIMClient.registerMessageType(msgType);
        } catch (AnnotationNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向当前聊天室发送消息。
     * </p>
     * <strong>注意：</strong>此函数为异步函数，发送结果将通过handler事件返回。
     *
     * @param msgContent 消息对象
     */
    public static void sendMessage(final MessageContent msgContent) {
        Message msg = Message.obtain(currentRoomId, Conversation.ConversationType.CHATROOM, msgContent);
        RongIMClient.getInstance().sendMessage(msg, null, null, new RongIMClient.SendMessageCallback() {
            @Override
            public void onSuccess(Integer integer) {
                handleEvent(MESSAGE_SENT, msgContent);
            }

            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
                handleEvent(MESSAGE_SEND_ERROR, errorCode.getValue(), 0, msgContent);
            }
        }, new RongIMClient.ResultCallback<Message>() {
            @Override
            public void onSuccess(Message message) {
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
            }
        });
    }

    /**
     * 获取注册消息对应的UI展示类。
     *
     * @param msgContent 注册的消息类型
     * @return 对应的UI展示类
     */
    public static Class<? extends BaseMsgView> getRegisterMessageView(Class<? extends MessageContent> msgContent) {
        return msgViewMap.get(msgContent);
    }

    /**
     * 注册消息展示界面类。
     *
     * @param msgContent 消息类型
     * @param msgView    对应的界面展示类
     */
    public static void registerMessageView(Class<? extends MessageContent> msgContent, Class<? extends BaseMsgView> msgView) {
        msgViewMap.put(msgContent, msgView);
    }

    /**
     * 加入聊天室。如果聊天室不存在，sdk 会创建聊天室并加入，如果已存在，则直接加入。加入聊天室时，可以选择拉取聊天室消息数目。
     *
     * @param roomId          聊天室 Id
     * @param defMessageCount 默认开始时拉取的历史记录条数
     * @param callback        状态回调
     */
    public static void joinChatRoom(String roomId, int defMessageCount, final RongIMClient.OperationCallback callback) {
        currentRoomId = roomId;
        RongIMClient.getInstance().joinChatRoom(currentRoomId, defMessageCount, callback);
    }

    /**
     * 退出聊天室，不再接收其消息。
     */
    public static void quitChatRoom(final RongIMClient.OperationCallback callback) {
        RongIMClient.getInstance().quitChatRoom(currentRoomId, callback);
    }

    public static void handleEvent(int what) {
        for (Handler handler : eventHandlerList) {
            android.os.Message m = android.os.Message.obtain();
            m.what = what;
            handler.sendMessage(m);
        }
    }

    public static void handleEvent(int what, Object obj) {
        for (Handler handler : eventHandlerList) {
            android.os.Message m = android.os.Message.obtain();
            m.what = what;
            m.obj = obj;
            handler.sendMessage(m);
        }
    }

    public static void handleEvent(int what, int arg1, int arg2, Object obj) {
        for (Handler handler : eventHandlerList) {
            android.os.Message m = android.os.Message.obtain();
            m.what = what;
            m.arg1 = arg1;
            m.arg2 = arg2;
            m.obj = obj;
            handler.sendMessage(m);
        }
    }

    /**
     * 添加IMLib 事件接收者
     *
     * @param handler
     */
    public static void addEventHandler(Handler handler) {
        if (!eventHandlerList.contains(handler)) {
            eventHandlerList.add(handler);
            handler.sendEmptyMessage(HEART_SENT);
            handler.sendEmptyMessage(HEART_ARRIVED);
            handler.sendEmptyMessage(REFRESH_GIFT_CACHE);


        }
    }

    /**
     * 移除IMLib 事件接收者。
     *
     * @param handler
     */
    public static void removeEventHandler(Handler handler) {
        handler.removeCallbacksAndMessages(null);
        eventHandlerList.remove(handler);
    }

}
