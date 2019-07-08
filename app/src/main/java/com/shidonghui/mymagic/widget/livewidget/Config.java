package com.shidonghui.mymagic.widget.livewidget;

import android.content.pm.ActivityInfo;
/**
 * @author ZhangKun 
 * @create 2019/6/3
 * @Describe 
 */
public class Config {
    public static final boolean DEBUG_MODE = false;
    public static final boolean FILTER_ENABLED = false;
    public static final int SCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    public static final String HINT_ENCODING_ORIENTATION_CHANGED =
            "Encoding orientation had been changed. Stop streaming first and restart streaming will take effect";
}
