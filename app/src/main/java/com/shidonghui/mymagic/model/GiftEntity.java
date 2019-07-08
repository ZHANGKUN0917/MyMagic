package com.shidonghui.mymagic.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.shidonghui.mymagic.utils.GiftMessage;


/**
 * @author ZhangKun
 * @create 2019/6/10
 * @Describe
 */

public class GiftEntity {
    private String type;
    private String giftId;//礼物标识
    private String userId;
    private String name;
    private String portraitUri;
    private String vipLevel;
    private String extra;
    private String giftName;//礼物描述+名字
    private String imgUrl;//礼物图片
    private int number;//礼物数量
    private double price;

    /**
     * 主播端
     *
     * @param giftMessage
     * @param giftData
     */
    public GiftEntity(GiftMessage giftMessage, LiveGiftModel.DataBean giftData) {
        this.type = giftMessage.getType1();
        this.giftId = giftMessage.getGiftId();
        this.userId = giftMessage.getUserId();
        this.name = giftMessage.getName();
        this.portraitUri = giftMessage.getPortraitUri();
        this.vipLevel = giftMessage.getVipLevel();
        this.giftName = giftData.getRewardMsg();
        if (!TextUtils.isEmpty(giftMessage.getNumber())) {
            this.number = Integer.valueOf(giftMessage.getNumber());
        }
        this.imgUrl = giftData.getImgUrl2();
        this.price = giftData.getPrice();
    }

    /**
     * 观看端
     *
     * @param //userInfo
     * @param// gift
     */
    public GiftEntity(LiveGiftModel.DataBean gift, UserModel.DataBean userInfo) {
        this.userId = userInfo.getUserId();
        this.name = userInfo.getNickname();
        this.type = userInfo.getType();
        this.portraitUri = userInfo.getAvatar();
        this.vipLevel = userInfo.getVipLevel();
        this.giftId = gift.getId() + "";
        this.giftName = gift.getRewardMsg();
        this.imgUrl = gift.getImgUrl2();
        this.number = 1;
    }

    protected GiftEntity(Parcel in) {
        type = in.readString();
        giftId = in.readString();
        userId = in.readString();
        name = in.readString();
        portraitUri = in.readString();
        vipLevel = in.readString();
        extra = in.readString();
        giftName = in.readString();
        imgUrl = in.readString();
        number = in.readInt();
        price = in.readDouble();
    }

  /*  public static final Creator<GiftEntity> CREATOR = new Creator<GiftEntity>() {
        @Override
        public GiftEntity createFromParcel(Parcel in) {
            return new GiftEntity(in);
        }

        @Override
        public GiftEntity[] newArray(int size) {
            return new GiftEntity[size];
        }
    };*/


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
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

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   /* @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(giftId);
        dest.writeString(userId);
        dest.writeString(name);
        dest.writeString(portraitUri);
        dest.writeString(vipLevel);
        dest.writeString(extra);
        dest.writeString(giftName);
        dest.writeString(imgUrl);
        dest.writeInt(number);
        dest.writeDouble(price);
    }*/

}
