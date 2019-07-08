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
 * @create 2019/6/26
 * @Describe 点赞消息
 */
@MessageTag(value = "MM:LiveHeart", flag = MessageTag.STATUS)
public class HeartMessage extends MessageContent {
    private String type1;
    private String userId;
    private String name;
    private String vipLevel;
    /**
     * 点赞数
     */
    private String number;
    private String extra;

    public HeartMessage(UserModel userInfo, String s) {
        this.type1 = userInfo.getData().getType();
        this.userId = userInfo.getData().getUserId();
        this.name = userInfo.getData().getNickname();
        this.vipLevel = userInfo.getData().getVipLevel();
        this.number = s;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public HeartMessage(byte[] data) {
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
            if (jsonObj.has("userId"))
                userId = jsonObj.optString("userId");
            if (jsonObj.has("name"))
                name = jsonObj.optString("name");
            if (jsonObj.has("vipLevel"))
                vipLevel = jsonObj.optString("vipLevel");
            if (jsonObj.has("number"))
                number = jsonObj.optString("number");
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
            jsonObj.put("userId", userId);
            jsonObj.put("name", name);
            jsonObj.put("vipLevel", vipLevel);
            jsonObj.put("number", number);
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
        dest.writeString(userId);
        dest.writeString(name);
        dest.writeString(vipLevel);
        dest.writeString(number);
        dest.writeString(extra);
        ParcelUtils.writeToParcel(dest, getUserInfo());
    }

    protected HeartMessage(Parcel in) {
        type1 = in.readString();
        userId = in.readString();
        name = in.readString();
        vipLevel = in.readString();
        number = in.readString();
        extra = in.readString();
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    public static final Creator<HeartMessage> CREATOR = new Creator<HeartMessage>() {

        @Override
        public HeartMessage createFromParcel(Parcel source) {
            return new HeartMessage(source);
        }

        @Override
        public HeartMessage[] newArray(int size) {
            return new HeartMessage[size];
        }
    };
}
