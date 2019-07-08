package com.shidonghui.mymagic.request;

import com.shidonghui.mymagic.model.ResponseBaseModel;
import com.shidonghui.mymagic.model.RongYunTokenModel;
import com.shidonghui.mymagic.model.ZhiMaCallbackModel;
import com.shidonghui.mymagic.model.ZhiMaVerificationModel;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author ZhangKun
 * @create 2019/5/30
 * @Describe
 */
public interface MeRequest {
    /**
     * 芝麻认证
     *
     * @param request
     * @return
     */
    @POST("in/u/l/zhima/startAuthentication")
    Observable<ZhiMaVerificationModel> zhiMaVerification(@Body ZhiMaVerificationRequest request);

    /**
     * 实名认证回调
     *
     * @param request
     * @return
     */
    @POST("in//u/l/zhima/usercallback")
    Observable<ZhiMaCallbackModel> zhiMaCallBack(@Body ZhiMaCallbackRequest request);

    /**
     * 认证信息验证
     *
     * @param request
     * @return
     */
    @POST("in/u/l/zhima/messageVerify")
    Observable<ResponseBaseModel> message(@Body RealnameVerifyRequest request);

    /**
     * 获取融云token
     *
     * @return
     */
    @POST("user/rongtoken.act")
    Observable<RongYunTokenModel> rongYunToken();

}
