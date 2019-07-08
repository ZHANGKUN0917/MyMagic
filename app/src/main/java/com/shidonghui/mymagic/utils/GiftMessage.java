package com.shidonghui.mymagic.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.shidonghui.mymagic.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

@MessageTag(value = "MM:GiftMsg", flag = MessageTag.STATUS)
public class GiftMessage extends MessageContent {

    private String type1;
    private String giftId;//礼物标识
    private String number;//未使用
    private String name;
    private String userId;
    private String portraitUri;
    private String vipLevel;
    private String extra;
    private String imgUrl;//礼物图片

    public GiftMessage(UserModel userInfo, String giftId, String number) {
        this.giftId = giftId;
        this.number = number;
        this.type1 = userInfo.getData().getType();
        this.name = userInfo.getData().getNickname();
        this.userId = userInfo.getData().getUserId();
        this.portraitUri = userInfo.getData().getAvatar();
        this.vipLevel = userInfo.getData().getVipLevel();
    }

    public GiftMessage(String type1, String giftId, String number, String name, String userId, String portraitUri, String vipLevel, String extra) {
        this.type1 = type1;
        this.giftId = giftId;
        this.number = number;
        this.name = name;
        this.userId = userId;
        this.portraitUri = portraitUri;
        this.vipLevel = vipLevel;
        this.extra = extra;
    }

    public GiftMessage(byte[] data) {
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
            if (jsonObj.has("giftId"))
                giftId = jsonObj.optString("giftId");
            if (jsonObj.has("number"))
                number = jsonObj.optString("number");
            if (jsonObj.has("name"))
                name = jsonObj.optString("name");
            if (jsonObj.has("userId"))
                userId = jsonObj.optString("userId");
            if (jsonObj.has("portraitUri"))
                portraitUri = jsonObj.optString("portraitUri");
            if (jsonObj.has("vipLevel"))
                vipLevel = jsonObj.optString("vipLevel");
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
            jsonObj.put("giftId", giftId);
            jsonObj.put("number", number);
            jsonObj.put("name", name);
            jsonObj.put("userId", userId);
            jsonObj.put("portraitUri", portraitUri);
            jsonObj.put("vipLevel", vipLevel);
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
        dest.writeString(giftId);
        dest.writeString(number);
        dest.writeString(name);
        dest.writeString(userId);
        dest.writeString(portraitUri);
        dest.writeString(vipLevel);
        dest.writeString(extra);
        ParcelUtils.writeToParcel(dest, getUserInfo());
    }

    protected GiftMessage(Parcel in) {
        type1 = in.readString();
        giftId = in.readString();
        number = in.readString();
        name = in.readString();
        userId = in.readString();
        portraitUri = in.readString();
        vipLevel = in.readString();
        extra = in.readString();
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    public static final Parcelable.Creator<GiftMessage> CREATOR = new Parcelable.Creator<GiftMessage>() {
        @Override
        public GiftMessage createFromParcel(Parcel source) {
            return new GiftMessage(source);
        }

        @Override
        public GiftMessage[] newArray(int size) {
            return new GiftMessage[size];
        }
    };

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}