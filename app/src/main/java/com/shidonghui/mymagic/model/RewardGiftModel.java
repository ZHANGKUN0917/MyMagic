package com.shidonghui.mymagic.model;

/**
 * @author ZhangKun 
 * @create 2019/7/3
 * @Describe 
 */
public class RewardGiftModel extends ResponseBaseModel {

    /**
     * errMsg :
     * data : {"imgUrl":"http://cdn.huataiyihe.com/qiniuLive_gift5.png","giftId":1005,"num":1,"title":"气球"}
     */

    private String errMsg;
    private DataBean data;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imgUrl : http://cdn.huataiyihe.com/qiniuLive_gift5.png
         * giftId : 1005
         * num : 1
         * title : 气球
         */

        private String imgUrl;
        private int giftId;
        private int num;
        private String title;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getGiftId() {
            return giftId;
        }

        public void setGiftId(int giftId) {
            this.giftId = giftId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
