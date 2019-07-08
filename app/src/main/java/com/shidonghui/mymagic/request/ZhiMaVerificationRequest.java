package com.shidonghui.mymagic.request;

/**
 * @author ZhangKun 
 * @create 2019/5/31
 * @Describe 
 */

public class ZhiMaVerificationRequest {
    String name;
    String IDcardNo;
    String returnurl;

    public ZhiMaVerificationRequest(String name, String IDcardNo, String returnurl) {
        this.name = name;
        this.IDcardNo = IDcardNo;
        this.returnurl = returnurl;
    }
}
