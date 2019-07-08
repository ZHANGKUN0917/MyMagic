package com.shidonghui.mymagic.fragment;

import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;

import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.base.BaseFragment;
import com.shidonghui.mymagic.databinding.HomeFragmentBinding;
import com.shidonghui.mymagic.utils.ListDataSaveWithSharedPreferences;

import java.util.ArrayList;
import java.util.List;

;

/**
 * @author ZhangKun
 * @create 2019/4/25
 * @Describe 首页
 */
public class HomeFragment extends BaseFragment   {

    private List<String> titleList;
    private List<String> otherTitleList;
    private HomeFragmentBinding homeFragmentBinding;
    private BottomSheetFragment bottomSheetFragment;
    private MyFragmentAdapter adapter;

    @Override
    protected int setContentView() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView(ViewDataBinding dataBinding) {
        homeFragmentBinding = (HomeFragmentBinding) dataBinding;


    }

    @Override
    protected void initData() {
        bottomSheetFragment = new BottomSheetFragment();
        titleList = new ArrayList<>();
        otherTitleList = new ArrayList<>();
        //第一次从本地存储取值为空，为true
        if (ListDataSaveWithSharedPreferences.getIsFirst(getActivity())) {
            titleList.add("社会");
            titleList.add("国内");
            titleList.add("娱乐");
            titleList.add("科技");
            titleList.add("体育");
            titleList.add("健康");
            titleList.add("旅游");
            titleList.add("IT");

            otherTitleList.add("军事");
            otherTitleList.add("NBA");
            otherTitleList.add("足球");
            otherTitleList.add("区块");
            // 设置不是第一次
            ListDataSaveWithSharedPreferences.saveIsFirst(getActivity());
            // 存储已选频道
            ListDataSaveWithSharedPreferences.saveList(getActivity(), "selectedChannel", titleList);
            ListDataSaveWithSharedPreferences.saveList(getActivity(), "unSelectedChannel", otherTitleList);
        }
        titleList = ListDataSaveWithSharedPreferences.getList(getActivity(), "selectedChannel");
        for (int i = 0; i < titleList.size(); i++) {
            homeFragmentBinding.xTab.addTab(homeFragmentBinding.xTab.newTab().setText(titleList.get(i)));
        }
        adapter = new MyFragmentAdapter(getActivity().getSupportFragmentManager(), titleList);
        homeFragmentBinding.viewpager.setAdapter(adapter);
        homeFragmentBinding.xTab.setupWithViewPager(homeFragmentBinding.viewpager);
        homeFragmentBinding.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bottomSheetFragment.isAdded()) {
                    bottomSheetFragment.show(getChildFragmentManager(), "Dialog");
                }

            }
        });
        bottomSheetFragment.setUpdateTablayout(new BottomSheetFragment.UpdateTablayout() {
            @Override
            public void updateTablayout(List<String> myChannelList, List<String> otherChannelList) {
                titleList.clear();
                titleList.addAll(myChannelList);
                adapter.setList(myChannelList);

            }
        });
    }

    class MyFragmentAdapter extends FragmentPagerAdapter {
        private List<String> titleList;

        public MyFragmentAdapter(FragmentManager fm, List<String> titleList) {
            super(fm);
            this.titleList = titleList;
        }

        @Override
        public Fragment getItem(int i) {
            return FragmentNews.getInstance(titleList, i);
        }

        // 首页类别名称更新
        public void setList(List<String> categoryNames) {
            this.titleList.clear();
            this.titleList.addAll(categoryNames);
            notifyDataSetChanged();
        }


        @Override
        public int getCount() {
            return titleList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }

}
