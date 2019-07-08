package com.shidonghui.mymagic.activity;

import android.Manifest;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.base.BaseActivity;
import com.shidonghui.mymagic.databinding.ActivityMainBinding;
import com.shidonghui.mymagic.fragment.FindFragment;
import com.shidonghui.mymagic.fragment.HomeFragment;
import com.shidonghui.mymagic.fragment.MeFragment;
import com.shidonghui.mymagic.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/5/22
 * @Describe
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private ActivityMainBinding activityMainBinding;
    private List<Fragment> fragmentList;
    private String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void initView(ViewDataBinding viewDataBinding) {
        activityMainBinding = (ActivityMainBinding) viewDataBinding;
        requestPermission("请在-设置-应用-魔法-权限中开启存储空间权限，以正常使用魔法各项功能", new CallBack() {
            @Override
            public void hasPermission() {
                Toast.makeText(MainActivity.this, "您已经申请了权限", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void lossPermission() {
                Toast.makeText(MainActivity.this, "您拒绝了申请权限", Toast.LENGTH_SHORT).show();
            }
        }, PERMISSIONS);
        activityMainBinding.radioGroup.setOnCheckedChangeListener(this);
        Drawable home = ContextCompat.getDrawable(this, R.drawable.select_home);
        /**
         * 设置图片宽高
         */
        home.setBounds(0, 0, 70, 70);
        /**
         *   设置图片在文字的哪个方向,分别对应左，上，右，下
         */
        activityMainBinding.rdHome.setCompoundDrawables(null, home, null, null);

        Drawable video = ContextCompat.getDrawable(this, R.drawable.select_video);
        video.setBounds(0, 0, 70, 70);
        activityMainBinding.rdVideo.setCompoundDrawables(null, video, null, null);

        Drawable find = ContextCompat.getDrawable(this, R.drawable.select_find);
        find.setBounds(0, 0, 70, 70);
        activityMainBinding.rdFind.setCompoundDrawables(null, find, null, null);

        Drawable me = ContextCompat.getDrawable(this, R.drawable.select_me);
        me.setBounds(0, 0, 70, 70);
        activityMainBinding.rdPersonal.setCompoundDrawables(null, me, null, null);

    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new VideoFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new MeFragment());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragmentList.get(0));
        transaction.add(R.id.fragment_container, fragmentList.get(1));
        transaction.add(R.id.fragment_container, fragmentList.get(2));
        transaction.add(R.id.fragment_container, fragmentList.get(3));
        transaction.commit();
        activityMainBinding.radioGroup.check(R.id.rd_home);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rd_home:
                hideAllFragment();
                FragmentTransaction rdHomeTransaction = getSupportFragmentManager().beginTransaction();
                rdHomeTransaction.show(fragmentList.get(0)).commit();
                break;
            case R.id.rd_video:

                hideAllFragment();
                FragmentTransaction rdVideoTransaction = getSupportFragmentManager().beginTransaction();
                rdVideoTransaction.show(fragmentList.get(1)).commit();
                break;
            case R.id.rd_find:
                hideAllFragment();
                FragmentTransaction rdFindTransaction = getSupportFragmentManager().beginTransaction();
                rdFindTransaction.show(fragmentList.get(2)).commit();
                break;
            case R.id.rd_personal:
                hideAllFragment();
                FragmentTransaction rdPersonalTransaction = getSupportFragmentManager().beginTransaction();
                rdPersonalTransaction.show(fragmentList.get(3)).commit();
                break;
            default:

        }
    }

    public void hideAllFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragmentList.get(0));
        transaction.hide(fragmentList.get(1));
        transaction.hide(fragmentList.get(2));
        transaction.hide(fragmentList.get(3));
        transaction.commit();

    }

}
