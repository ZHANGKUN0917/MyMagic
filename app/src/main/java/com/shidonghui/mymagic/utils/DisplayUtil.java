package com.shidonghui.mymagic.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;


import com.shidonghui.mymagic.MyApp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author gaoxingfei
 */
public class DisplayUtil {

    public static int screenWidthPx; //屏幕宽 px
    public static int screenhightPx; //屏幕高 px
    public static float density;//屏幕密度
    public static int densityDPI;//屏幕密度
    public static float screenWidthDip;//  dp单位
    public static float screenHightDip;//  dp单位

    public static int dip2px(double dipValue) {
        return dip2px(MyApp.getContext(), dipValue);
    }

    public static int dip2px(Context context, double dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, double pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(float spValue) {
        final float fontScale = MyApp.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getDisplayWidth() {
        return getDisplayWidth(MyApp.getContext());
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getDisplayHeight() {
        return getDisplayHeight(MyApp.getContext());
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getDisplayWidth(Context context) {
        // 获取屏幕真实的分辨率
        Point mScreenRealSize = getRealScreenSize(context);
        if (mScreenRealSize != null) {
            return mScreenRealSize.x;
        }
        return 0;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getDisplayHeight(Context context) {
        // 获取屏幕真实的分辨率
        Point mScreenRealSize = getRealScreenSize(context);
        if (mScreenRealSize != null) {
            return mScreenRealSize.y;
        }
        return 0;
    }

    public static int dpToPx(Context context, int dps) {
        return dip2px(context, dps);
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c;
        Object obj;
        Field field;
        int x, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取NavigationBar的高度
     */
    public static int getNavigationBarHeight(Context mContext) {
        if (!hasNavigationBar(mContext)) {
            return 0;
        }
        Resources resources = mContext.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 是否存在NavigationBar
     */
    public static boolean hasNavigationBar(Context mContext) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = getWindowManager(mContext).getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.x != size.x || realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(mContext).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            return !(menu || back);
        }
    }

    /**
     * 获取WindowManager。
     */
    public static WindowManager getWindowManager(Context mContext) {
        if (mContext == null) {
            return null;
        }
        return (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * 获取屏幕分辨率
     */
    private static Point getRealScreenSize(Context context) {
        Point screenSize = null;
        try {
            screenSize = new Point();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display defaultDisplay = windowManager.getDefaultDisplay();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                defaultDisplay.getRealSize(screenSize);
            } else {
                try {
                    Method mGetRawW = Display.class.getMethod("getRawWidth");
                    Method mGetRawH = Display.class.getMethod("getRawHeight");
                    screenSize.set(
                            (Integer) mGetRawW.invoke(defaultDisplay),
                            (Integer) mGetRawH.invoke(defaultDisplay)
                    );
                } catch (Exception e) {
                    screenSize.set(defaultDisplay.getWidth(), defaultDisplay.getHeight());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenSize;
    }

}