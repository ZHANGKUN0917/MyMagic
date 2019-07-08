package com.shidonghui.mymagic.model;

/**
 * @author ZhangKun
 * @create 2019/6/12
 * @Describe 获取融云token
 */
public class RongYunTokenModel {

    /**
     * msg :
     * errMsg :
     * rstCde : 0
     * data : {"rongtoken":"QNgX3XGe5nuHMqaSRR5vGxevzwbo4eHonOKXOm3uluUQpks9hltEPXiTICfju+4ajR9HE7UoucpKBJBtVcm+OrVIY0hiYJ/4"}
     */

    private String msg;
    private String errMsg;
    private int rstCde;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getRstCde() {
        return rstCde;
    }

    public void setRstCde(int rstCde) {
        this.rstCde = rstCde;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * rongtoken : QNgX3XGe5nuHMqaSRR5vGxevzwbo4eHonOKXOm3uluUQpks9hltEPXiTICfju+4ajR9HE7UoucpKBJBtVcm+OrVIY0hiYJ/4
         */

        private String rongtoken;

        public String getRongtoken() {
            return rongtoken;
        }

        public void setRongtoken(String rongtoken) {
            this.rongtoken = rongtoken;
        }
    }
}
