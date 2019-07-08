package com.shidonghui.mymagic.model;

/**
 * @author ZhangKun 
 * @create 2019/5/31
 * @Describe 
 */

public class ZhiMaCallbackModel extends ResponseBaseModel {

    /**
     * errMsg :
     * data : {}
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
        private String result;//T:成功  F:失败

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
