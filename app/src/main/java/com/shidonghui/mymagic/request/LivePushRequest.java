package com.shidonghui.mymagic.request;

/**
 * @author ZhangKun
 * @create 2019/6/4
 * @Describe
 */
public class LivePushRequest {
    private String livePicture;
    private String liveTitle;
    private String masterTitles;
    private int isScreenVertical;

    public LivePushRequest(String livePicture, String liveTitle, String masterTitles, int isScreenVertical) {
        this.livePicture = livePicture;
        this.liveTitle = liveTitle;
        this.masterTitles = masterTitles;
        this.isScreenVertical = isScreenVertical;
    }
}
