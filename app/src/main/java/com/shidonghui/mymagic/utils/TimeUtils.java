package com.shidonghui.mymagic.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * @author ZhangKun
 * @create 2019/6/12
 * @Describe
 */
public class TimeUtils {
    /**
     * 时间格式化 00:00:00
     */
    public static String formattedTime(long second) {
        // 转换成字符串的时间
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

        long seconds = second % 60;

        long minutes = (second / 60) % 60;

        long hours = second / 3600;

        mFormatBuilder.setLength(0);
        return mFormatter.format("%02d:%02d:%02d", hours, minutes, seconds).toString();
    }
    /**
     * 时间戳转字符串--日期
     *
     * @param timeStamp 毫秒
     * @return
     */
    public static String getDateStrFromTimestamp(Long timeStamp) {
        String date = new SimpleDateFormat("yyyy.MM.dd").format(new Date(timeStamp));
        return date;
    }

}
