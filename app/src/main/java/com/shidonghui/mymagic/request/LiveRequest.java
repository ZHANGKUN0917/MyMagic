package com.shidonghui.mymagic.request;

import com.shidonghui.mymagic.model.LiveCloseModel;
import com.shidonghui.mymagic.model.LiveDetailsModel;
import com.shidonghui.mymagic.model.LiveGiftModel;
import com.shidonghui.mymagic.model.LiveListModel;
import com.shidonghui.mymagic.model.LivePopularityGiftModel;
import com.shidonghui.mymagic.model.LivePushModel;
import com.shidonghui.mymagic.model.RewardGiftModel;
import com.shidonghui.mymagic.model.UserModel;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author ZhangKun
 * @create 2019/6/4
 * @Describe
 */
public interface LiveRequest {
    /**
     * 开启直播
     *
     * @param request
     * @return
     */
    @POST("in/u/l/qiniulive/start")
    Observable<LivePushModel> livePush(@Body LivePushRequest request);

    /**
     * 关闭直播
     *
     * @param request
     * @return
     */
    @POST("in/u/l/qiniulive/close")
    Observable<LiveCloseModel> liveClose(@Body LiveCloseRequest request);

    /**
     * 用户信息
     *
     * @return
     */
    @POST("appuser.act")
    Observable<UserModel> user();

    /**
     * 直播列表
     *
     * @param request
     * @return
     */
    @POST("in/u/f/qiniulive/list")
    Observable<LiveListModel> liveList(@Body LiveListRequest request);

    /**
     * 直播详细信息
     *
     * @param request
     * @return
     */
    @POST("in/u/l/qiniulive/liveDetail")
    Observable<LiveDetailsModel> liveDetails(@Body LiveDetailsRequest request);

    /**
     * 直播礼物列表
     *
     * @param request
     * @return
     */
    @POST("in/u/f/qiniulive/liveGiftList")
    Observable<LiveGiftModel> liveGift(@Body LiveGiftRequest request);

    /**
     * 直播人气礼物
     *
     * @return
     */
    @POST("in/u/l/qiniulive/giftAward")
    Observable<LivePopularityGiftModel> livePopularityGift();

    /**
     * 增加获取礼物列表
     *
     * @param request
     * @return
     */
    @POST("in/u/l/qiniulive/giftAwardRecord")
    Observable<RewardGiftModel> rewardGift(@Body LiveDetailsRequest request);

}
