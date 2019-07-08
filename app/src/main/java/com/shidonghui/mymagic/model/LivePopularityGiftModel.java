package com.shidonghui.mymagic.model;

import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/6/26
 * @Describe
 */
public class LivePopularityGiftModel extends ResponseBaseModel {

    /**
     * errMsg :
     * data : [{"giftId":"1006","num":1},{"giftId":"1001","num":0},{"giftId":"1002","num":1},{"giftId":"1008","num":1}]
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
         * giftId : 1006
         * num : 1
         */

        private String giftId;
        private int num;

        public String getGiftId() {
            return giftId;
        }

        public void setGiftId(String giftId) {
            this.giftId = giftId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
