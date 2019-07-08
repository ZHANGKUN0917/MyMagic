package com.shidonghui.mymagic.model;

/**
 * @author ZhangKun
 * @create 2019/5/31
 * @Describe
 */
public class ZhiMaVerificationModel extends ResponseBaseModel {

    /**
     * errMsg :
     * data : {"url":"https://zmopenapi.zmxy.com.cn/openapi.do?charset=UTF-8&method=zhima.customer.certification.certify&channel=apppc&sign=GHBcSmxRAAecEHX2lBVQ07XX%2FsF0ziiMcznulbFet%2F1PwlecbAKo1dhR584P71Ate0IVEoyVCOYtcG07jjd8Q%2FsugBqBTSehTEJmynFTvdOE%2FcRE9bkH7Z4WZYLY15WgDZ0NSxp7Wr6pMyhU8flpRS4Na%2BNQxwHcmc2CL7hFyN0%3D&version=1.0&app_id=1003820&sign_type=RSA&platform=zmop&params=cVimneKbBCLjQPHK%2BOuH4yuCVgJuHO3vH3UWbc9S073MchgcihibWcgcS4XeQ39wKWLrPfxiWF3zlBBsZG9n5nXfF0uX7P0PfBK7w3Pw2wAFcLxh5fUzfKf9QFDwLAAdhy1l%2FALf2VxteEl8JKfjMwHoizbHhBgVVDDWN%2BCq5HQ%3D"}
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
         * url : https://zmopenapi.zmxy.com.cn/openapi.do?charset=UTF-8&method=zhima.customer.certification.certify&channel=apppc&sign=GHBcSmxRAAecEHX2lBVQ07XX%2FsF0ziiMcznulbFet%2F1PwlecbAKo1dhR584P71Ate0IVEoyVCOYtcG07jjd8Q%2FsugBqBTSehTEJmynFTvdOE%2FcRE9bkH7Z4WZYLY15WgDZ0NSxp7Wr6pMyhU8flpRS4Na%2BNQxwHcmc2CL7hFyN0%3D&version=1.0&app_id=1003820&sign_type=RSA&platform=zmop&params=cVimneKbBCLjQPHK%2BOuH4yuCVgJuHO3vH3UWbc9S073MchgcihibWcgcS4XeQ39wKWLrPfxiWF3zlBBsZG9n5nXfF0uX7P0PfBK7w3Pw2wAFcLxh5fUzfKf9QFDwLAAdhy1l%2FALf2VxteEl8JKfjMwHoizbHhBgVVDDWN%2BCq5HQ%3D
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
