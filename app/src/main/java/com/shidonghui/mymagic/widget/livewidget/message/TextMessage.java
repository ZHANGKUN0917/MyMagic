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

@MessageTag(value = "HT:LiveTxtMsg", flag = MessageTag.STATUS)
public class TextMessage extends MessageContent {

    private String type1;
    private String name;
    private String portraitUri;
    private String userId;
    private String vipLevel;
    private String content;
    private String extra;

    public TextMessage(UserModel userInfo, String content) {
        this.type1 = userInfo.getData().getType();
        this.name = userInfo.getData().getNickname();
        this.userId = userInfo.getData().getUserId();
        this.vipLevel = userInfo.getData().getVipLevel();
        this.portraitUri = userInfo.getData().getAvatar();
        this.content = content;
    }

    public TextMessage(String type1, String name, String portraitUri, String userId, String vipLevel, String content, String extra) {
        this.type1 = type1;
        this.name = name;
        this.portraitUri = portraitUri;
        this.userId = userId;
        this.vipLevel = vipLevel;
        this.content = content;
        this.extra = extra;
    }

    public TextMessage(byte[] data) {
        String jsonStr = null;
        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            if (jsonObj.has("type1"))
                type1 = jsonObj.optString("type1");
            if (jsonObj.has("name"))
                name = jsonObj.optString("name");
            if (jsonObj.has("portraitUri"))
                portraitUri = jsonObj.optString("portraitUri");
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
            jsonObj.put("portraitUri", portraitUri);
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
        dest.writeString(portraitUri);
        dest.writeString(userId);
        dest.writeString(vipLevel);
        dest.writeString(content);
        dest.writeString(extra);
        ParcelUtils.writeToParcel(dest, getUserInfo());
    }

    protected TextMessage(Parcel in) {
        type1 = in.readString();
        name = in.readString();
        portraitUri = in.readString();
        userId = in.readString();
        vipLevel = in.readString();
        content = in.readString();
        extra = in.readString();
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    public static final Creator<TextMessage> CREATOR = new Creator<TextMessage>() {
        @Override
        public TextMessage createFromParcel(Parcel source) {
            return new TextMessage(source);
        }

        @Override
        public TextMessage[] newArray(int size) {
            return new TextMessage[size];
        }
    };

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

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}