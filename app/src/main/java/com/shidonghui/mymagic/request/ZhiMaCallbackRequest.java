package com.shidonghui.mymagic.request;
/**
 * @author ZhangKun 
 * @create 2019/5/31
 * @Describe 
 */

public class ZhiMaCallbackRequest {
    String params;
    String sign;

    public ZhiMaCallbackRequest(String params, String sign) {
        this.params = params;
        this.sign = sign;
    }
}
