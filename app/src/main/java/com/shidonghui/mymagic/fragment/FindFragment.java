package com.shidonghui.mymagic.fragment;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.View;

import com.shidonghui.mymagic.MyApp;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.activity.FlowLayoutActivity;
import com.shidonghui.mymagic.activity.RealNameActivity;
import com.shidonghui.mymagic.activity.ThemeActivity;
import com.shidonghui.mymagic.live.activity.LiveListActivity;
import com.shidonghui.mymagic.live.activity.LivePushActivity;
import com.shidonghui.mymagic.base.BaseFragment;
import com.shidonghui.mymagic.databinding.FindFragmentBinding;
import com.shidonghui.mymagic.model.LivePushModel;
import com.shidonghui.mymagic.model.UserModel;
import com.shidonghui.mymagic.request.LivePushRequest;
import com.shidonghui.mymagic.request.LiveRequest;
import com.shidonghui.mymagic.utils.SharedPreferencesUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @author ZhangKun
 * @create 2019/4/25
 * @Describe
 */
public class FindFragment extends BaseFragment implements View.OnClickListener {
    private FindFragmentBinding findFragmentBinding;
    private LiveRequest liveRequest;

    @Override
    protected int setContentView() {
        return R.layout.find_fragment;
    }

    @Override
    protected void initView(ViewDataBinding dataBinding) {
        findFragmentBinding = (FindFragmentBinding) dataBinding;
        liveRequest = MyApp.getRetrofit4().create(LiveRequest.class);
        findFragmentBinding.rlFlow.setOnClickListener(this);
        findFragmentBinding.rlLive.setOnClickListener(this);
        findFragmentBinding.rlTheme.setOnClickListener(this);
        findFragmentBinding.rlWatchLive.setOnClickListener(this);
        findFragmentBinding.rlRealName.setOnClickListener(this);
        userInfo();
    }

    @Override
    protected void initData() {

    }

    /**
     * 开启直播
     */
    private void startLive() {
        Observable<LivePushModel> livePush = liveRequest.livePush(new LivePushRequest("", "蜡毛小新", "超凡大师", 0));
        livePush.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LivePushModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LivePushModel livePushModel) {
                        Intent intent3 = new Intent(mContext, LivePushActivity.class);
                        intent3.putExtra("liveInfo", livePushModel.getData());
                        startActivity(intent3);
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

    /**
     * 用户信息
     */
    private void userInfo() {
        Observable<UserModel> user = liveRequest.user();
        user.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserModel userModel) {
                SharedPreferencesUtils.saveUserInfo(mContext, userModel);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_flow:
                Intent intent = new Intent(mContext, FlowLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_live:
                startLive();
                break;
            case R.id.rl_watch_live:
                Intent intent1 = new Intent(mContext, LiveListActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_theme:
                Intent intent2 = new Intent(mContext, ThemeActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_real_name:
                Intent intent3 = new Intent(mContext, RealNameActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}
