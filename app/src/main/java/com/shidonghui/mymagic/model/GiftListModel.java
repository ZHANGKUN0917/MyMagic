package com.shidonghui.mymagic.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author ZhangKun 
 * @create 2019/6/10
 * @Describe 
 */

public class GiftListModel extends ResponseBaseModel implements Parcelable {

    /**
     * errMsg :
     * data : [{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift1.png","rewardMsg":"送了一朵鲜花","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_1.png","menderTime":"1509940132158","price":0,"showTime":"0","mender":"newOam_admin","del":0,"id":1001,"sort":1,"title":"鲜花"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift2.png","rewardMsg":"送了一朵小西瓜","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_2.png","menderTime":"1509938169271","price":0,"showTime":"0","mender":"newOam_admin","del":0,"id":1002,"sort":3,"title":"小西瓜"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift3.png","rewardMsg":"送了一朵鼓掌","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_3.png","menderTime":"","price":1,"showTime":"0","mender":"","del":0,"id":1003,"sort":6,"title":"鼓掌"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift4.png","rewardMsg":"送了一朵棒棒糖","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_4.png","menderTime":"","price":2,"showTime":"0","mender":"","del":0,"id":1004,"sort":10,"title":"棒棒糖"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift5.png","rewardMsg":"送了一朵气球","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_5.png","menderTime":"","price":2,"showTime":"0","mender":"","del":0,"id":1005,"sort":15,"title":"气球"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift6.png","rewardMsg":"送了一朵啤酒","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_6.png","menderTime":"","price":5,"showTime":"0","mender":"","del":0,"id":1006,"sort":20,"title":"啤酒"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift8.png","rewardMsg":"送了一朵冰淇淋","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_8.png","menderTime":"","price":5,"showTime":"0","mender":"","del":0,"id":1008,"sort":30,"title":"冰淇淋"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift9.png","rewardMsg":"送了一你最棒","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_9.png","menderTime":"","price":6,"showTime":"0","mender":"","del":0,"id":1009,"sort":35,"title":"你最棒"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift10.png","rewardMsg":"送了一亲一口","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_10.png","menderTime":"","price":8,"showTime":"0","mender":"","del":0,"id":1010,"sort":40,"title":"亲一口"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift11.png","rewardMsg":"送了一朵主播辛苦了","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_11.png","menderTime":"","price":10,"showTime":"0","mender":"","del":0,"id":1011,"sort":45,"title":"主播辛苦了"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift12.png","rewardMsg":"送了一朵女神","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_12.png","menderTime":"","price":52,"showTime":"0","mender":"","del":0,"id":1012,"sort":50,"title":"女神"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift13.png","rewardMsg":"送了一朵男神","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_13.png","menderTime":"","price":52,"showTime":"0","mender":"","del":0,"id":1013,"sort":55,"title":"男神"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift14.png","rewardMsg":"送了一朵钻戒","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_14.png","menderTime":"","price":99,"showTime":"0","mender":"","del":0,"id":1014,"sort":60,"title":"钻戒"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift15.png","rewardMsg":"送了一辆兰博基尼","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_15.png","menderTime":"","price":520,"showTime":"0","mender":"","del":0,"id":1015,"sort":65,"title":"兰博基尼"},{"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift16.png","rewardMsg":"送了一艘豪华游轮","expirTime":"0","imgUrl2":"http://cdn.huataiyihe.com/qiniuLive_gift2_16.png","menderTime":"","price":1314,"showTime":"0","mender":"","del":0,"id":1016,"sort":70,"title":"豪华游轮"}]
     */

    private String errMsg;
    private List<DataBean> data;

    protected GiftListModel(Parcel in) {
        errMsg = in.readString();
        data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<GiftListModel> CREATOR = new Creator<GiftListModel>() {
        @Override
        public GiftListModel createFromParcel(Parcel in) {
            return new GiftListModel(in);
        }

        @Override
        public GiftListModel[] newArray(int size) {
            return new GiftListModel[size];
        }
    };

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(errMsg);
        dest.writeTypedList(data);
    }

    public static class DataBean implements Parcelable {
        /**
         * imgUrl : http://cdn.huataiyihe.com/qiniuLive_gift1.png
         * rewardMsg : 送了一朵鲜花
         * expirTime : 0
         * imgUrl2 : http://cdn.huataiyihe.com/qiniuLive_gift2_1.png
         * menderTime : 1509940132158
         * price : 0.0
         * showTime : 0
         * mender : newOam_admin
         * del : 0
         * id : 1001
         * sort : 1
         * title : 鲜花
         */

        private String imgUrl;
        private String rewardMsg;
        private String expirTime;
        private String imgUrl2;
        private String menderTime;
        private double price;
        private String showTime;
        private String mender;
        private int del;
        private int id;
        private int sort;
        private String title;
        private boolean isSelected;
        private int freeNum;//人气礼物数量

        protected DataBean(Parcel in) {
            imgUrl = in.readString();
            rewardMsg = in.readString();
            expirTime = in.readString();
            imgUrl2 = in.readString();
            menderTime = in.readString();
            price = in.readDouble();
            showTime = in.readString();
            mender = in.readString();
            del = in.readInt();
            id = in.readInt();
            sort = in.readInt();
            title = in.readString();
            isSelected = in.readByte() != 0;
            freeNum = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public int getFreeNum() {
            return freeNum;
        }

        public void setFreeNum(int freeNum) {
            this.freeNum = freeNum;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getRewardMsg() {
            return rewardMsg;
        }

        public void setRewardMsg(String rewardMsg) {
            this.rewardMsg = rewardMsg;
        }

        public String getExpirTime() {
            return expirTime;
        }

        public void setExpirTime(String expirTime) {
            this.expirTime = expirTime;
        }

        public String getImgUrl2() {
            return imgUrl2;
        }

        public void setImgUrl2(String imgUrl2) {
            this.imgUrl2 = imgUrl2;
        }

        public String getMenderTime() {
            return menderTime;
        }

        public void setMenderTime(String menderTime) {
            this.menderTime = menderTime;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getShowTime() {
            return showTime;
        }

        public void setShowTime(String showTime) {
            this.showTime = showTime;
        }

        public String getMender() {
            return mender;
        }

        public void setMender(String mender) {
            this.mender = mender;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(imgUrl);
            dest.writeString(rewardMsg);
            dest.writeString(expirTime);
            dest.writeString(imgUrl2);
            dest.writeString(menderTime);
            dest.writeDouble(price);
            dest.writeString(showTime);
            dest.writeString(mender);
            dest.writeInt(del);
            dest.writeInt(id);
            dest.writeInt(sort);
            dest.writeString(title);
            dest.writeByte((byte) (isSelected ? 1 : 0));
            dest.writeInt(freeNum);
        }
    }
}
