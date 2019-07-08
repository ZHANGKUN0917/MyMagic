package com.shidonghui.mymagic.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shidonghui.mymagic.MyApp;
import com.shidonghui.mymagic.R;

/**
 * @author ZhangKun
 * @create 2019/5/30
 * @Describe
 */
public class CustomToast {
    private Toast toast;
    public static final int error = 0;
    public static final int success = 1;
    public static final int failed = 2;
    public static final int warn = 3;

    private static CustomToast customToast;

    public static CustomToast getInstance() {
        if (customToast == null) {
            customToast = new CustomToast();
        }
        return customToast;
    }

    public Toast successToast(Context context, String info) {
        return toastCreate(context, success, info);
    }

    public Toast errorToast(Context context, String info) {
        return toastCreate(context, error, info);
    }

    public Toast failedToast(Context context, String info) {
        return toastCreate(context, failed, info);
    }

    public Toast warnToast(Context context, String info) {
        return toastCreate(context, warn, info);
    }

    public Toast successToast(String info) {
        return toastCreate(null, success, info);
    }

    public Toast errorToast(String info) {
        return toastCreate(null, error, info);
    }

    public Toast failedToast(String info) {
        return toastCreate(null, failed, info);
    }

    public Toast warnToast(String info) {
        return toastCreate(null, warn, info);
    }

    public Toast connectServerFailToast() {
        return connectServerFailToast(MyApp.getContext());
    }

    public Toast connectServerFailToast(Context context) {
        return toastCreate(context, failed, context.getString(R.string.connect_server_fail));
    }


    public Toast toastCreate(Context context, int type, String info) {

        context = MyApp.getContext();
        try {
            if (info == null || info.isEmpty()) {
                return null;
            }
            if (context == null) {
                return null;
            }
            LayoutInflater inflater = LayoutInflater.from(context);

            LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.toast_layout, null);
            TextView text = (TextView) layout.findViewById(R.id.info);
            ImageView imageView = (ImageView) layout.findViewById(R.id.image);
            text.setText(info);
            switch (type) {
                case 0:
                    imageView.setImageResource(R.mipmap.toast_error);
                    break;
                case 1:
                    imageView.setImageResource(R.mipmap.toast_success);
                    break;
                case 2:
                    imageView.setImageResource(R.mipmap.toast_failed);
                    break;
                case 3:
                    imageView.setImageResource(R.mipmap.toast_warn);
                    break;
            }
            toast = new Toast(context);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
            return toast;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 显示toast，自己定义显示时长
     * param1:activity_grid_item  传入context
     * param2:word   我们需要显示的toast的内容
     * param3:time length  long类型，我们传入的时间长度（如500）
     */
    public Toast toastCreate(final Activity activity, final int type, final String info, final long time) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        cancel();
                    }
                }, time);
            }
        });
        return toastCreate(activity, type, info);
    }

    public void cancel() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }

}
