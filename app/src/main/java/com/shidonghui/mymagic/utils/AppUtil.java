package com.shidonghui.mymagic.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;


import com.shidonghui.mymagic.MyApp;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/5/22
 * @Describe
 */
public class AppUtil {

    /**
     * 获取包信息
     *
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            return pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("TAG", e.getLocalizedMessage());
        }
        return new PackageInfo();
    }

    /**
     * 获取包名称
     *
     * @return
     */
    public static String getPackageName() {
        return MyApp.getContext().getPackageName();
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到版本名（例：android:versionName="1.0.0"）
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            versionName = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 得到版本号
     */
    public static String getAppBuild(Context context) {
        String appBuild = "";
        try {
            appBuild = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appBuild;
    }


    /**
     * 判断某个服务是否正在运行的方法
     *
     * @param mContext
     * @param serviceName 是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public static boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(100);
        if (myList.size() <= 0) {
            return false;
        }
        int size = myList.size();
        for (int i = 0; i < size; i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }

    /**
     * 获取设备的唯一标识，deviceId
     *
     * @param context
     * @return
     *//*
    public static String getDeviceId(Context context) {
        String deviceToken = (String) SharedPreferencesUtil.getData(context, "deviceToken", "");
        if (!TextUtils.isEmpty(deviceToken)) {
            //这里因为android获取设备ID方式众多，但没有找到特别靠谱的，所以用友盟生成的唯一设备号
            *//*
            device token是友盟+生成的用于标识设备的id，长度为44位，不能定制和修改。同一台设备上不同应用对应的device token不一样。
             *//*
            return deviceToken;
        } else {
            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                @SuppressLint("MissingPermission") String deviceId = tm.getDeviceId();
                if (deviceId == null) {
                    return "-";
                } else {
                    return deviceId;
                }
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    return android.os.Build.SERIAL;
                } catch (Exception e1) {
                    e1.printStackTrace();
                    return "";
                }
            }
        }
    }*/

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getPhoneBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机Android API等级（22、23 ...）
     *
     * @return
     */
    public static int getBuildLevel() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本（4.4、5.0、5.1 ...）
     *
     * @return
     */
    public static String getBuildVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取Ipv4地址
     *
     * @return
     */
    public static String getIpv4Address() {
        try {
            for (Enumeration<NetworkInterface> enNetI = NetworkInterface
                    .getNetworkInterfaces(); enNetI.hasMoreElements(); ) {
                NetworkInterface netI = enNetI.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = netI
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (inetAddress instanceof Inet4Address && !inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "-";
    }

    /**
     * 获取app当前的渠道号
     *
     * @return
     */
    public static String getChannelNumber() {
        return getAppMetaData(MyApp.getContext(), "BaiduMobAd_CHANNEL");
    }

    /**
     * 获取app当前的渠道号或application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值 ， 或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String channelNumber = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelNumber = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelNumber;
    }

    /**
     * 判断某个应用是否安装。
     *
     * @param packageName 要检查是否安装的应用包名
     * @return 安装返回true，否则返回false。
     */
    public static boolean isInstalled(String packageName) {
        try {
            PackageInfo packageInfo = MyApp.getContext().getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo != null) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断手机是否安装了QQ。
     */
    public static boolean isQQInstalled() {
        return isInstalled("com.tencent.mobileqq");
    }

    /**
     * 判断手机是否安装了微信。
     */
    public static boolean isWechatInstalled() {
        return isInstalled("com.tencent.mm");
    }
}