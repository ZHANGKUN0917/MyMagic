package com.shidonghui.mymagic.request;

import com.shidonghui.mymagic.model.PictureModel;
import com.shidonghui.mymagic.model.VideoModel;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * @author ZhangKun
 * @create 2019/5/28
 * @Describe
 */
public interface VideoRequest {
    /**
     * 图片
     *
     * @param size
     * @param page
     * @return
     */
    @GET("data/福利/{size}/{page}")
    Observable<PictureModel> picture(
            @Path("size") int size,
            @Path("page") int page);

    /**
     * 视频
     *
     * @param pageTo
     * @param
     * @return
     */
    @Headers("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11")
    @GET("nc/video/list/V9LG4B3A0/n/{startPage}-10.html")
    Observable<VideoModel> video(@Path("startPage") int pageTo);

}
