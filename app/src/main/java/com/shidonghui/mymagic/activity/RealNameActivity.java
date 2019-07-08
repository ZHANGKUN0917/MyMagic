package com.shidonghui.mymagic.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.shidonghui.mymagic.MyApp;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.base.BaseActivity;
import com.shidonghui.mymagic.databinding.ActivityRealNameBinding;
import com.shidonghui.mymagic.model.ZhiMaCallbackModel;
import com.shidonghui.mymagic.model.ZhiMaVerificationModel;
import com.shidonghui.mymagic.request.MeRequest;
import com.shidonghui.mymagic.request.VideoRequest;
import com.shidonghui.mymagic.request.ZhiMaCallbackRequest;
import com.shidonghui.mymagic.request.ZhiMaVerificationRequest;
import com.shidonghui.mymagic.utils.CustomToast;

import java.net.URLEncoder;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ZhangKun
 * @create 2019/5/30
 * @Describe 实名认证
 */
public class RealNameActivity extends BaseActivity {

    private ActivityRealNameBinding realNameBinding;
    private Animation shake;
    private MeRequest meRequest;

    @Override
    protected void onNewIntent(Intent intent) {
        Uri uri = intent.getData();
        if (uri != null) {
            // 完整的url信息
            String url = uri.toString();
            Log.i("asdf", url);
            String params = uri.getQueryParameter("params");
            Log.i("asdf", params);
            String sign = uri.getQueryParameter("sign");
            Log.i("asdf", sign);
            zhiMaVerificationCallback(params, sign);
        } else {
            customToast.failedToast(mContext, "认证失败");
        }
        super.onNewIntent(intent);
    }

    @Override
    protected void initView(ViewDataBinding viewDataBinding) {
        realNameBinding = (ActivityRealNameBinding) viewDataBinding;
        shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
        meRequest = MyApp.getRetrofit3().create(MeRequest.class);
    }

    @Override
    protected void initData() {
        realNameBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        realNameBinding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String realName = realNameBinding.etRealname.getText().toString().trim();
                final String idNumber = realNameBinding.etIdNumber.getText().toString().trim();
                if (TextUtils.isEmpty(realName))

                {
                    realNameBinding.etRealname.startAnimation(shake);
                    customToast.toastCreate(mContext, CustomToast.warn, "姓名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(idNumber)) {
                    realNameBinding.etIdNumber.startAnimation(shake);
                    customToast.toastCreate(mContext, CustomToast.warn, "身份证号码不能为空");
                    return;
                }
                if (idNumber.length() != 18) {
                    customToast.toastCreate(mContext, CustomToast.warn, "身份证号码必须为18位");
                    return;
                }
                Observable<ZhiMaVerificationModel> zhiMaVerificationModelObservable = meRequest.zhiMaVerification(new ZhiMaVerificationRequest(realName, idNumber, "mymagic://mayirenzheng"));
                zhiMaVerificationModelObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ZhiMaVerificationModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhiMaVerificationModel zhiMaVerificationModel) {
                        try {
                            String url = zhiMaVerificationModel.getData().getUrl();
                            if (TextUtils.isEmpty(url)) {
                                customToast.errorToast(mContext, "服务器返回数据为空");
                                return;
                            }
                            doVerify(url);
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (e.getMessage().contains("已认证成功")) {
                                customToast.successToast("认证成功");
                                finish();
                            } else {
                                customToast.failedToast(mContext, e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("asdf", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

            }


        });
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_real_name;
    }

    /**
     * 启动支付宝进行认证
     *
     * @param url 开放平台返回的URL
     */
    private void doVerify(String url) {
        if (hasApplication()) {
            Intent action = new Intent(Intent.ACTION_VIEW);
            StringBuilder builder = new StringBuilder();
            // 这里使用固定appid 20000067
            builder.append("alipays://platformapi/startapp?appId=20000067&url=");
            builder.append(URLEncoder.encode(url));
            action.setData(Uri.parse(builder.toString()));
            startActivity(action);
        } else {
            // 处理没有安装支付宝的情况
            new AlertDialog.Builder(mContext)
                    .setMessage("是否下载并安装支付宝完成认证?")
                    .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent action = new Intent(Intent.ACTION_VIEW);
                            action.setData(Uri.parse("https://m.alipay.com"));
                            startActivity(action);
                        }
                    }).setNegativeButton("算了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    }

    /**
     * 判断是否安装了支付宝
     *
     * @return true 为已经安装
     */
    private boolean hasApplication() {
        PackageManager manager = mContext.getPackageManager();
        Intent action = new Intent(Intent.ACTION_VIEW);
        action.setData(Uri.parse("alipays://"));
        List list = manager.queryIntentActivities(action, PackageManager.GET_RESOLVED_FILTER);
        return list != null && list.size() > 0;
    }

    private void zhiMaVerificationCallback(String params, String sign) {
        Observable<ZhiMaCallbackModel> zhiMaCallBack = meRequest.zhiMaCallBack(new ZhiMaCallbackRequest(params, sign));
        zhiMaCallBack.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ZhiMaCallbackModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ZhiMaCallbackModel zhiMaCallbackModel) {
                try {
                    String result = zhiMaCallbackModel.getData().getResult();
                    if ("T".equals(result)) {
                        customToast.successToast(mContext, "认证成功");
                        finish();
                    } else {
                        customToast.failedToast(mContext, "认证失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    customToast.failedToast(e.getMessage());
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e("asdf", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }


}
