package com.shidonghui.mymagic.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.shidonghui.mymagic.activity.PermissionActivity;
import com.shidonghui.mymagic.utils.CustomToast;

/**
 * @author ZhangKun
 * @create 2019/5/18
 * @Describe
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ViewDataBinding viewDataBinding;
    public Context mContext;
    public CustomToast customToast;

    private static final int PERMISSION_REQUEST_CODE = 1088;
    private String mPermissionDes;
    private BaseActivity.CallBack mCallBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        customToast = CustomToast.getInstance();
        viewDataBinding = DataBindingUtil.setContentView(this, setContentView());
        initView(viewDataBinding);
        initData();
    }

    /**
     * 返回viewDataBinding
     *
     * @param viewDataBinding
     */
    protected abstract void initView(ViewDataBinding viewDataBinding);

    /**
     * 得到数据
     */
    protected abstract void initData();

    /**
     * 得到布局
     *
     * @return
     */
    protected abstract int setContentView();
    /**
     * 权限申请使用方法
     *
     * @param permissionDes 权限说明
     * @param callBack      申请回调
     * @param permissions   申请权限
     */
    protected void requestPermission(String permissionDes, BaseActivity.CallBack callBack, @NonNull String... permissions) {
        mCallBack = callBack;
        mPermissionDes = permissionDes;
        if (checkPermission(permissions)){
            mCallBack.hasPermission();
        }
        else {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * 判断系统版本大于6.0的时候
     *
     * @param permissions 申请权限
     * @return
     */
    protected boolean checkPermission(@NonNull String... permissions) {
        //大于6.0的时候需要动态申请权限.小于6.0的时候如果用户手动关闭权限,程序即崩 需要做Try处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermissions(permissions);
        }
        return true;
    }

    /**
     * 检查权限是否已经授权
     *
     * @param permissions 申请权限
     * @return
     */
    private boolean checkSelfPermissions(@NonNull String... permissions) {
        boolean flag = true;
        for (String p : permissions) {
            if (ActivityCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean hasAllGranted = true;
        for (int i = 0; i < grantResults.length; ++i) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                hasAllGranted = false;
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                    showDialogPrompt();
                } else {
                    //权限申请被拒绝 ，但用户未选择'不再提示'选项
                    mCallBack.lossPermission();
                }
                break;
            }
        }
        if (hasAllGranted) {
            mCallBack.hasPermission();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 由于用户手动关闭权限提示。APP需要做人性化提示
     */
    private void showDialogPrompt() {
        new AlertDialog.Builder(this)
                .setTitle("权限申请")
                .setMessage(mPermissionDes)
                .setCancelable(false)
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //引导用户至设置页手动授权
                        getAppSetting();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //引导用户手动授权，权限请求失败
                        mCallBack.lossPermission();
                    }
                }).show();
    }

    /**
     * 跳转设置 应用设置界面
     *
     * @return
     */
    private Intent getAppSetting() {
        Intent localIntent = null;
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(localIntent);
        return localIntent;
    }


    /**
     * 权限申请回调
     */
    public interface CallBack {
        void hasPermission();

        void lossPermission();
    }
}
