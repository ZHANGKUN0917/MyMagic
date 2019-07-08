package com.shidonghui.mymagic.im.widget;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONException;
import com.shidonghui.mymagic.db.DBManager;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;
import retrofit2.Response;

/**
 * Created by wangmingqiang on 16/9/10.
 * Company RongCloud
 * 数据库访问接口,目前接口有同步和异步之分
 * 第一次login时从app server获取数据,之后数据库读,数据的更新使用IMKit里的通知类消息
 * 因为存在app server获取数据失败的情况,代码里有很多这种异常情况的判断处理并重新从app server获取数据
 * 1.add...类接口为插入或更新数据库
 * 2.get...类接口为读取数据库
 * 3.delete...类接口为删除数据库数据
 * 4.sync...为同步接口,因为存在去掉sync相同名称的异步接口,有些同步类接口不带sync
 * 5.fetch...,pull...类接口都是从app server获取数据并存数据库,不同的只是返回值不一样,此类接口全部为private
 */
public class SealUserInfoManager {

    private static SealUserInfoManager sInstance;
    private final Context mContext;

    private DBManager mDBManager;
    static Handler mHandler;

    public static SealUserInfoManager getInstance() {
        return sInstance;
    }

    public SealUserInfoManager(Context context) {
        mContext = context;
        mHandler = new Handler(Looper.getMainLooper());

    }

    public static void init(Context context) {

        sInstance = new SealUserInfoManager(context);
    }

    /**
     * 修改数据库的存贮路径为.../appkey/userID/,
     * 必须确保userID存在后才能初始化DBManager
     */
    public void openDB() {
        if (mDBManager == null || !mDBManager.isInitialized()) {
            mDBManager = DBManager.init(mContext);

        }

    }

    public void closeDB() {

        if (mDBManager != null) {
            mDBManager.uninit();
            mDBManager = null;

        }
    }
}
