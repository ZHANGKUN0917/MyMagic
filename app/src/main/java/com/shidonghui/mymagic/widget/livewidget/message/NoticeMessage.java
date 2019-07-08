package com.shidonghui.mymagic.widget.livewidget.message;

import android.os.Parcel;
import android.os.Parcelable;

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
 * @Describe
 */
@MessageTag(value = "MM:Notice", flag = MessageTag.STATUS)
public class NoticeMessage extends MessageContent {
    private String type1;
    private String name;
    private String userId;
    private String vipLevel;
    private String content;
    private String extra;

    public NoticeMessage(String content) {
        this.content = content;
    }

    public NoticeMessage(String type1, String name, String userId, String vipLevel, String content, String extra) {
        this.type1 = type1;
        this.name = name;
        this.userId = userId;
        this.vipLevel = vipLevel;
        this.content = content;
        this.extra = extra;
    }

    public String getContent() {
        return content;
    }

    public NoticeMessage(byte[] data) {
        String jsonStr = null;
        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            type1 = jsonObj.optString("type1");
            if (jsonObj.has("name"))
                name = jsonObj.optString("name");
            if (jsonObj.has("userId"))
                userId = jsonObj.optString("userId");
            if (jsonObj.has("vipLevel"))
                vipLevel = jsonObj.optString("vipLevel");
            if (jsonObj.has("content"))
                content = jsonObj.optString("content");
            if (jsonObj.has("extra"))
                extra = jsonObj.optString("extra");
            if (jsonObj.has("user"))
                setUserInfo(parseJsonToUserInfo(jsonObj.getJSONObject("user")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("type1", type1);
            jsonObj.put("name", name);
            jsonObj.put("userId", userId);
            jsonObj.put("vipLevel", vipLevel);
            jsonObj.put("content", content);
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
        dest.writeString(content);
        dest.writeString(extra);
        ParcelUtils.writeToParcel(dest, getUserInfo());
    }

    protected NoticeMessage(Parcel in) {
        type1 = in.readString();
        name = in.readString();
        userId = in.readString();
        vipLevel = in.readString();
        content = in.readString();
        extra = in.readString();
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    public static final Parcelable.Creator<NoticeMessage> CREATOR = new Parcelable.Creator<NoticeMessage>() {
        @Override
        public NoticeMessage createFromParcel(Parcel source) {
            return new NoticeMessage(source);
        }

        @Override
        public NoticeMessage[] newArray(int size) {
            return new NoticeMessage[size];
        }
    };

}
