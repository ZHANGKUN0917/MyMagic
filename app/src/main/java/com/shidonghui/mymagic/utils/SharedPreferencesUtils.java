package com.shidonghui.mymagic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.shidonghui.mymagic.model.UserModel;

/**
 * @author ZhangKun
 * @create 2019/6/11
 * @Describe
 */
public class SharedPreferencesUtils {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private final String name = "name";

    public SharedPreferencesUtils(Context ctx) {
        this.context = ctx;
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * 存入数据
     *
     * @param key   键
     * @param value 值
     */
    public void putValue(String key, Object value) {
        if (value instanceof String) {
            editor.putString(key, String.valueOf(value));
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        }
        editor.apply();
    }

    public Object getValue(String key, Object defaultValue) {
        if (defaultValue instanceof String) {
            return sharedPreferences.getString(key, String.valueOf(defaultValue));
        } else if (defaultValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static SharedPreferences getDefaultShared(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferences.Editor getDefaultSharedEditor(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).edit();
    }
    /**
     * 保存用户信息
     *
     * @param context
     * @param userInfoBean
     */
    public static void saveUserInfo(Context context, UserModel userInfoBean) {
        SharedPreferences.Editor editor = getDefaultSharedEditor(context);
        String userInfoJson = JsonMananger.beanToJson(userInfoBean);
        editor.putString("userInfo", userInfoJson);
        editor.apply();
    }

    public static UserModel getUserInfo(Context context) {
        SharedPreferences sharedPreferences = getDefaultShared(context);
        String userInfoJson = sharedPreferences.getString("userInfo", "");
        UserModel userInfoBean = JsonMananger.jsonToBean(userInfoJson, UserModel.class);
        return userInfoBean;
    }
}
