package com.shidonghui.mymagic.request;

import com.shidonghui.mymagic.model.SociologyModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author ZhangKun
 * @create 2019/5/22
 * @Describe
 */
public interface HomeRequest {
    /**
     * 社会
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("social/")
    Observable<SociologyModel> sociology(@Query("key") String key,@Query("num") int num, @Query("rand") int rand);

    /**
     * 国内
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("guonei/?key=0d04e5f2e6e958023505eb156bc56ef9")
    Observable<SociologyModel> guoNei(@Query("num") int num, @Query("rand") int rand);

    /**
     * 国际
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("world/?key=0d04e5f2e6e958023505eb156bc56ef9")
    Observable<SociologyModel> international(@Query("num") int num, @Query("rand") int rand);

    /**
     * 娱乐
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("huabian/?key=0d04e5f2e6e958023505eb156bc56ef9")
    Observable<SociologyModel> entertainment(@Query("num") int num, @Query("rand") int rand);

    /**
     * 科技
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("keji/?key=0d04e5f2e6e958023505eb156bc56ef9")
    Observable<SociologyModel> science(@Query("num") int num, @Query("rand") int rand);

    /**
     * 体育
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("tiyu/?key=0d04e5f2e6e958023505eb156bc56ef9")
    Observable<SociologyModel> sport(@Query("num") int num, @Query("rand") int rand);

    /**
     * 健康
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("health/?key=0d04e5f2e6e958023505eb156bc56ef9")
    Observable<SociologyModel> health(@Query("num") int num, @Query("rand") int rand);

    /**
     * 旅游
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("travel/?key=0d04e5f2e6e958023505eb156bc56ef9")
    Observable<SociologyModel> tour(@Query("num") int num, @Query("rand") int rand);

    /**
     * IT
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("it/?key=0d04e5f2e6e958023505eb156bc56ef9")
    Observable<SociologyModel> it(@Query("num") int num, @Query("rand") int rand);

    /**
     * 军事
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("military/?key=0d04e5f2e6e958023505eb156bc56ef9")
    Observable<SociologyModel> military(@Query("num") int num, @Query("rand") int rand);

    /**
     * NBA
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("nba/?key=0d04e5f2e6e958023505eb156bc56ef9")
    Observable<SociologyModel> nba(@Query("num") int num, @Query("rand") int rand);

    /**
     * 足球
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("football/?key=0d04e5f2e6e958023505eb156bc56ef9")
    Observable<SociologyModel> football(@Query("num") int num, @Query("rand") int rand);

    /**
     * 区块链
     *
     * @param num
     * @param rand
     * @return
     */
    @GET("blockchain/?key=0d04e5f2e6e958023505eb156bc56ef9")
    Observable<SociologyModel> blockChain(@Query("num") int num, @Query("rand") int rand);

}
