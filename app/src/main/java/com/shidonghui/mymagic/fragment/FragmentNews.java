package com.shidonghui.mymagic.fragment;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shidonghui.mymagic.MyApp;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.activity.WebViewActivity;
import com.shidonghui.mymagic.adapter.FragmentNewsAdapter;
import com.shidonghui.mymagic.base.BaseFragment;
import com.shidonghui.mymagic.databinding.FragmentNewsBinding;
import com.shidonghui.mymagic.model.SociologyModel;
import com.shidonghui.mymagic.request.HomeRequest;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ZhangKun
 * @create 2019/5/22
 * @Describe
 */
public class FragmentNews extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    private FragmentNewsBinding fragmentNewsBinding;
    private HomeRequest homeRequest;
    private Observable<SociologyModel> observable;
    private List<SociologyModel.NewslistBean> newslist;

    @Override
    protected int setContentView() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(ViewDataBinding dataBinding) {
        fragmentNewsBinding = (FragmentNewsBinding) dataBinding;
        homeRequest = MyApp.getRetrofit().create(HomeRequest.class);
        fragmentNewsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        fragmentNewsBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

    }

    @Override
    protected void initData() {
        ArrayList<String> data = getArguments().getStringArrayList("title");
        int position = getArguments().getInt("position");
        switch (data.get(position)) {
            case "社会":
                observable = homeRequest.sociology("0d04e5f2e6e958023505eb156bc56ef9", 50, 1);
                break;
            case "国内":
                observable = homeRequest.guoNei(50, 1);
                break;
            case "娱乐":
                observable = homeRequest.entertainment(50, 1);
                break;
            case "科技":
                observable = homeRequest.science(50, 1);
                break;
            case "体育":
                observable = homeRequest.sport(50, 1);
                break;
            case "健康":
                observable = homeRequest.health(50, 1);
                break;
            case "旅游":
                observable = homeRequest.tour(50, 1);
                break;
            case "IT":
                observable = homeRequest.it(50, 1);
                break;
            case "军事":
                observable = homeRequest.military(50, 1);
                break;
            case "NBA":
                observable = homeRequest.nba(50, 1);
                break;
            case "足球":
                observable = homeRequest.football(50, 1);
                break;
            case "区块链":
                observable = homeRequest.blockChain(50, 1);
                break;
            default:
                break;

        }
        getData();
    }

    public static FragmentNews getInstance(List<String> titleList, int i) {
        FragmentNews fragmentNews = new FragmentNews();
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        bundle.putStringArrayList("title", (ArrayList<String>) titleList);
        fragmentNews.setArguments(bundle);
        return fragmentNews;
    }

    public void getData() {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SociologyModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SociologyModel sociologyModel) {
                        newslist = sociologyModel.getNewslist();
                        fragmentNewsBinding.swipeRefresh.setRefreshing(false);
                        FragmentNewsAdapter adapter = new FragmentNewsAdapter(R.layout.news_recyclerview_item, sociologyModel.getNewslist());
                        fragmentNewsBinding.recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(FragmentNews.this);
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra("url", newslist.get(position).getUrl());
        startActivity(intent);
    }
}
