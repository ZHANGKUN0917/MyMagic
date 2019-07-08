package com.shidonghui.mymagic;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.qiniu.pili.droid.streaming.StreamingEnv;
import com.shidonghui.mymagic.im.widget.SealAppContext;
import com.shidonghui.mymagic.im.widget.SealUserInfoManager;
import com.shidonghui.mymagic.widget.livewidget.LiveKit;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.rong.imageloader.core.DisplayImageOptions;
import io.rong.imageloader.core.display.FadeInBitmapDisplayer;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.RealTimeLocationMessageProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.ipc.RongExceptionHandler;
import io.rong.imlib.model.UserInfo;
import io.rong.push.RongPushClient;
import io.rong.push.pushconfig.PushConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ZhangKun
 * @create 2019/5/22
 * @Describe
 */
public class MyApp extends Application {

    private static Context context;
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;
    private static Retrofit retrofit1;
    private static Retrofit retrofit2;
    private static Retrofit retrofit3;
    private static Retrofit retrofit4;
    /**
     * 请求超时时间(秒)
     */
    private static int connectTimeout = 30;
    /**
     * 读取超时时间(秒)
     */
    private static int readTimeout = 90;
    /**
     * 写入超时时间(秒)
     */
    private static int writeTimeout = 90;
    /**
     * 新闻baseurl
     */
    private static String baseUrl = "http://api.tianapi.com/";
    /**
     * 图片baseurl
     */
    private static String baseUrl1 = "http://gank.io/api/";

    /**
     * 短视频baseurl
     */
    private static String baseUrl2 = "http://c.3g.163.com/";

    /**
     * 实名认证basurl
     */
    private static String baseUrl3 = "http://xd.shidonghui.cn/sdhui-xuedian/";

    /**
     * 直播
     */
    private static String baseUrl4 = "http://dev.shidonghui.cn/sdhui-sei/";
    private String rongToken = "QNgX3XGe5nuHMqaSRR5vGxevzwbo4eHonOKXOm3uluUQpks9hltEPXiTICfju+4ajR9HE7UoucpKBJBtVcm+OrVIY0hiYJ/4";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化七牛云直播
        StreamingEnv.init(getApplicationContext());
        Fresco.initialize(this);
        context = this;
        //初始化okHttp
        getOkHttpInstance();
        //初始化retrofit
        getRetrofit();
        getRetrofit1();
        getRetrofit3();
        getRetrofit4();
        //初始化融云
        initRongIM();
        //连接融云
        connect(rongToken);



    }


    static Interceptor mTokenInterceptorJson = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request authorised = originalRequest.newBuilder()
                    .header("token", "7011727918U")
                    .header("Content-Type", "application/json")
                    .build();
            okhttp3.Response response = chain.proceed(authorised);
            return response.newBuilder().build();
        }

    };

    public static Context getContext() {
        return context;
    }

    public static OkHttpClient getOkHttpInstance() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                    .readTimeout(readTimeout, TimeUnit.SECONDS)
                    .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addNetworkInterceptor(mTokenInterceptorJson)
                    .build();
            return okHttpClient;
        } else {
            return okHttpClient;
        }

    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit;
        } else {
            return retrofit;
        }
    }

    public static Retrofit getRetrofit1() {
        if (retrofit1 == null) {
            retrofit1 = new Retrofit.Builder()
                    .baseUrl(baseUrl1)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit1;
        } else {
            return retrofit1;
        }
    }

    public static Retrofit getRetrofit2() {
        if (retrofit2 == null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(baseUrl2)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit2;
        } else {
            return retrofit2;
        }
    }

    public static Retrofit getRetrofit3() {
        if (retrofit3 == null) {
            retrofit3 = new Retrofit.Builder()
                    .baseUrl(baseUrl4)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit3;
        } else {
            return retrofit3;
        }
    }

    public static Retrofit getRetrofit4() {
        if (retrofit4 == null) {
            retrofit4 = new Retrofit.Builder()
                    .baseUrl(baseUrl4)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit4;
        } else {
            return retrofit4;
        }
    }

    private void connect(String token) {
        Log.i("asdf",getApplicationInfo().packageName+"-=-=");
        Log.i("asdf",MyApp.getCurProcessName(getApplicationContext()));
        if (getApplicationInfo().packageName.equals(MyApp.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.i("asdf", "我走这里额了");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.i("asdf", userid + "oppo我连接成功了");
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.i("asdf", errorCode + "oppo");
                }
            });
        }
    }

    /**
     * 初始化融云IM/直播
     */
    private void initRongIM() {
        Log.i("asdf",getApplicationInfo().packageName+"-=-=1ppp");
        Log.i("asdf",MyApp.getCurProcessName(getApplicationContext())+"-=-=opp");
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {


            /**             * 注意：
             *
             * IMKit SDK调用第一步 初始化
             *
             * context上下文
             *
             * 只有两个进程需要初始化，主进程和 push 进程

             */
            RongIM.init(this);
            //直播管理类
            LiveKit.init(this);
            //初始化融云管理类
            SealAppContext.init(this);
            String current = getCurProcessName(this);
            String mainProcessName = getPackageName();
            if (mainProcessName.equals(current)) {
                SealUserInfoManager.getInstance().openDB();
            }


        }
    }

    public static String getCurProcessName(Context applicationContext) {
        ActivityManager activityManager = (ActivityManager) applicationContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = activityManager.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        int pid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }
}
