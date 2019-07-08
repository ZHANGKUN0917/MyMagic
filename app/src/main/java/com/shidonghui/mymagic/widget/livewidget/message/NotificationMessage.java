package com.shidonghui.mymagic.widget.livewidget.message;

import android.os.Parcel;

import com.shidonghui.mymagic.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

/**
 * @author ZhangKun
 * @create 2019/6/11
 * @Describe 聊天室提示信息
 */
@MessageTag(value = "MM:liveNotice", flag = MessageTag.STATUS)
public class NotificationMessage extends MessageContent {
    private String type1;
    private String name;
    private String userId;
    private String vipLevel;
    private String message;//消息内容
    private String portraitUri;//进来者的头像
    private String isReal;//是否真人
    private String isJoin;//是否进入消息
    private String extra;

    public NotificationMessage(UserModel userInfo, String message, String isReal, String isJoin) {
        this.type1 = userInfo.getData().getType();
        this.name = userInfo.getData().getNickname();
        this.userId = userInfo.getData().getUserId();
        this.vipLevel = userInfo.getData().getVipLevel();
        this.portraitUri = userInfo.getData().getAvatar();
        this.message = message;
        this.isReal = isReal;
        this.isJoin = isJoin;
    }
    /**
     * 给消息赋值
     * @param in
     */
    protected NotificationMessage(Parcel in) {
        type1 = in.readString();
        name = in.readString();
        userId = in.readString();
        vipLevel = in.readString();
        message = in.readString();
        portraitUri = in.readString();
        isReal = in.readString();
        isJoin = in.readString();
        extra = in.readString();
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getIsReal() {
        return isReal;
    }

    public void setIsReal(String isReal) {
        this.isReal = isReal;
    }

    public String getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(String isJoin) {
        this.isJoin = isJoin;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * 将消息封装成json串 将json串转换成byte数组
     *
     * @return
     */
    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("type1", type1);
            jsonObj.put("name", name);
            jsonObj.put("userId", userId);
            jsonObj.put("vipLevel", vipLevel);
            jsonObj.put("message", message);
            jsonObj.put("portraitUri", portraitUri);
            jsonObj.put("isReal", isReal);
            jsonObj.put("isJoin", isJoin);
            jsonObj.put("extra", extra);

            if (getJSONUserInfo() != null) {
                jsonObj.putOpt("user", getJSONUserInfo());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 先有byte转成json字符串，再将json中内容取出赋值给消息属性
     *
     * @param data
     */
    public NotificationMessage(byte[] data) {

        String jsonStr = null;
        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            if (jsonObj.has("type1")) {
                type1 = jsonObj.optString("type1");
            }

            if (jsonObj.has("name")) {
                name = jsonObj.optString("name");
            }

            if (jsonObj.has("userId")) {
                userId = jsonObj.optString("userId");
            }

            if (jsonObj.has("vipLevel")) {
                vipLevel = jsonObj.optString("vipLevel");
            }

            if (jsonObj.has("message")) {
                message = jsonObj.optString("message");
            }

            if (jsonObj.has("portraitUri")) {
                portraitUri = jsonObj.optString("portraitUri");
            }

            if (jsonObj.has("isReal")) {
                isReal = jsonObj.optString("isReal");
            }

            if (jsonObj.has("isJoin")) {
                isJoin = jsonObj.optString("isJoin");
            }

            if (jsonObj.has("extra")) {
                extra = jsonObj.optString("extra");
            }

            if (jsonObj.has("user")) {
                setUserInfo(parseJsonToUserInfo(jsonObj.getJSONObject("user")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type1);
        dest.writeString(name);
        dest.writeString(userId);
        dest.writeString(vipLevel);
        dest.writeString(message);
        dest.writeString(portraitUri);
        dest.writeString(isReal);
        dest.writeString(isJoin);
        dest.writeString(extra);
        ParcelUtils.writeToParcel(dest, getUserInfo());
    }

    public static final Creator<NotificationMessage> CREATOR = new Creator<NotificationMessage>() {
        @Override
        public NotificationMessage createFromParcel(Parcel source) {
            return new NotificationMessage(source);
        }

        @Override
        public NotificationMessage[] newArray(int size) {
            return new NotificationMessage[size];
        }
    };

    @Override
    public String toString() {
        return "NotificationMessage{" +
                "type1='" + type1 + '\'' +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", vipLevel='" + vipLevel + '\'' +
                ", message='" + message + '\'' +
                ", portraitUri='" + portraitUri + '\'' +
                ", isReal='" + isReal + '\'' +
                ", isJoin='" + isJoin + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
