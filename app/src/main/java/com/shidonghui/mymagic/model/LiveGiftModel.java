package com.shidonghui.mymagic.model;

import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/6/26
 * @Describe
 */
public class LiveGiftModel extends ResponseBaseModel{


    /**
     * errMsg :
     * data : [{"rewardMsg":"送了一朵鲜花","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_1.png","showTime":"0","del":0,"sort":1,"title":"鲜花","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift1.png","expirTime":"0","menderTime":"1513585960946","price":0,"isPopGift":0,"mender":"newOam_admin","id":1001},{"rewardMsg":"送了一个小西瓜","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_2.png","showTime":"0","del":0,"sort":3,"title":"小西瓜","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift2.png","expirTime":"0","menderTime":"1509938169271","price":0.5,"isPopGift":1,"mender":"newOam_admin","id":1002},{"rewardMsg":"送了一个鼓掌","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_3.png","showTime":"0","del":0,"sort":6,"title":"鼓掌","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift3.png","expirTime":"0","menderTime":"","price":0.5,"isPopGift":1,"mender":"","id":1003},{"rewardMsg":"送了一个棒棒糖","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_4.png","showTime":"0","del":0,"sort":10,"title":"棒棒糖","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift4.png","expirTime":"0","menderTime":"","price":1,"isPopGift":1,"mender":"","id":1004},{"rewardMsg":"送了一个气球","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_5.png","showTime":"0","del":0,"sort":15,"title":"气球","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift5.png","expirTime":"0","menderTime":"","price":2,"isPopGift":0,"mender":"","id":1005},{"rewardMsg":"送了一杯啤酒","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_6.png","showTime":"0","del":0,"sort":20,"title":"啤酒","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift6.png","expirTime":"0","menderTime":"","price":5,"isPopGift":0,"mender":"","id":1006},{"rewardMsg":"送了一个荧光棒","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_7.png","showTime":"0","del":0,"sort":25,"title":"荧光棒","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift7.png","expirTime":"0","menderTime":"","price":5,"isPopGift":0,"mender":"","id":1007},{"rewardMsg":"送了一个冰淇淋","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_8.png","showTime":"0","del":0,"sort":30,"title":"冰淇淋","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift8.png","expirTime":"0","menderTime":"","price":5,"isPopGift":0,"mender":"","id":1008},{"rewardMsg":"送了一个你最棒","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_9.png","showTime":"0","del":0,"sort":35,"title":"你最棒","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift9.png","expirTime":"0","menderTime":"","price":6,"isPopGift":0,"mender":"","id":1009},{"rewardMsg":"送了一个亲一口","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_10.png","showTime":"0","del":0,"sort":40,"title":"亲一口","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift10.png","expirTime":"0","menderTime":"","price":8,"isPopGift":0,"mender":"","id":1010},{"rewardMsg":"送了一朵主播辛苦了","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_11.png","showTime":"0","del":0,"sort":45,"title":"主播辛苦了","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift11.png","expirTime":"0","menderTime":"","price":10,"isPopGift":0,"mender":"","id":1011},{"rewardMsg":"送了一个女神","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_12.png","showTime":"0","del":0,"sort":50,"title":"女神","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift12.png","expirTime":"0","menderTime":"","price":52,"isPopGift":0,"mender":"","id":1012},{"rewardMsg":"送了一个男神","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_13.png","showTime":"0","del":0,"sort":55,"title":"男神","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift13.png","expirTime":"0","menderTime":"","price":52,"isPopGift":0,"mender":"","id":1013},{"rewardMsg":"送了一个钻戒","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_14.png","showTime":"0","del":0,"sort":60,"title":"钻戒","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift14.png","expirTime":"0","menderTime":"","price":99,"isPopGift":0,"mender":"","id":1014},{"rewardMsg":"送了一辆兰博基尼","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_15.png","showTime":"0","del":0,"sort":65,"title":"兰博基尼","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift15.png","expirTime":"0","menderTime":"","price":520,"isPopGift":0,"mender":"","id":1015},{"rewardMsg":"送了一艘豪华游轮","imgUrl2":"http://cdn.sdh365.com/qiniuLive_gift2_16.png","showTime":"0","del":0,"sort":70,"title":"豪华游轮","imgUrl":"http://cdn.sdh365.com/qiniuLive_gift16.png","expirTime":"0","menderTime":"","price":1314,"isPopGift":0,"mender":"","id":1016}]
     */

    private String errMsg;
    private List<DataBean> data;

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

    public static class DataBean {
        /**
         * rewardMsg : 送了一朵鲜花
         * imgUrl2 : http://cdn.sdh365.com/qiniuLive_gift2_1.png
         * showTime : 0
         * del : 0
         * sort : 1
         * title : 鲜花
         * imgUrl : http://cdn.sdh365.com/qiniuLive_gift1.png
         * expirTime : 0
         * menderTime : 1513585960946
         * price : 0
         * isPopGift : 0
         * mender : newOam_admin
         * id : 1001
         */

        private String rewardMsg;
        private String imgUrl2;
        private String showTime;
        private int del;
        private int sort;
        private String title;
        private String imgUrl;
        private String expirTime;
        private String menderTime;
        private double price;
        private int isPopGift;
        private String mender;
        private int id;
        private boolean isSelected;
        public boolean isSelected() {
            return isSelected;
        }
        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        private int freeNum;//人气礼物数量

        public String getRewardMsg() {
            return rewardMsg;
        }

        public void setRewardMsg(String rewardMsg) {
            this.rewardMsg = rewardMsg;
        }

        public String getImgUrl2() {
            return imgUrl2;
        }

        public void setImgUrl2(String imgUrl2) {
            this.imgUrl2 = imgUrl2;
        }

        public String getShowTime() {
            return showTime;
        }

        public void setShowTime(String showTime) {
            this.showTime = showTime;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
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

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getExpirTime() {
            return expirTime;
        }

        public void setExpirTime(String expirTime) {
            this.expirTime = expirTime;
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

        public int getIsPopGift() {
            return isPopGift;
        }

        public void setIsPopGift(int isPopGift) {
            this.isPopGift = isPopGift;
        }

        public String getMender() {
            return mender;
        }

        public void setMender(String mender) {
            this.mender = mender;
        }

        public int getId() {
            return id;
        }

        public int getFreeNum() {
            return freeNum;
        }

        public void setFreeNum(int freeNum) {
            this.freeNum = freeNum;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
