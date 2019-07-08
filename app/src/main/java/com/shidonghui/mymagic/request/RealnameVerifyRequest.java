package com.shidonghui.mymagic.request;

/**
 * @author ZhangKun 
 * @create 2019/5/31
 * @Describe 
 */

public class RealnameVerifyRequest {
    private String name;
    private String IDcardNo;

    public RealnameVerifyRequest(String name, String IDcardNo) {
        this.name = name;
        this.IDcardNo = IDcardNo;
    }
}
