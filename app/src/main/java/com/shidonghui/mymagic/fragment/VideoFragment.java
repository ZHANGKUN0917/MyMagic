package com.shidonghui.mymagic.fragment;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shidonghui.mymagic.MyApp;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.activity.VerticalVideoActivity;
import com.shidonghui.mymagic.adapter.PictureAdapter;
import com.shidonghui.mymagic.base.BaseFragment;
import com.shidonghui.mymagic.databinding.VideoFragmentBinding;
import com.shidonghui.mymagic.model.PictureModel;
import com.shidonghui.mymagic.request.VideoRequest;

import java.util.ArrayList;
import java.util.List;

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
public class VideoFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    private VideoFragmentBinding fragmentBinding;
    private VideoRequest videoRequest;
    private int pageSize = 10;
    private int pageTo = 1;
    private List<PictureModel.ResultsBean> results;
    private List<PictureModel.ResultsBean> allList;
    private PictureAdapter adapter;
    private boolean isRefresh;

    @Override
    protected int setContentView() {
        return R.layout.video_fragment;
    }

    @Override
    protected void initView(ViewDataBinding dataBinding) {
        fragmentBinding = (VideoFragmentBinding) dataBinding;
        videoRequest = MyApp.getRetrofit1().create(VideoRequest.class);
        fragmentBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                pageTo = 1;
                getData();
            }
        });
        fragmentBinding.ivScrollTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentBinding.recyclerView.scrollToPosition(0);
            }
        });

    }

    @Override
    protected void initData() {
        allList = new ArrayList<>();
        fragmentBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        adapter = new PictureAdapter(R.layout.video_picture, allList);
        fragmentBinding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        //adapter.setOnLoadMoreListener(VideoFragment.this, fragmentBinding.recyclerView);
        getData();
    }

    private void getData() {
        Observable<PictureModel> picture = videoRequest.picture(pageSize, pageTo);
        picture.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PictureModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PictureModel pictureModel) {
                fragmentBinding.swipeRefresh.setRefreshing(false);
                results = pictureModel.getResults();
                if (isRefresh) {
                    isRefresh = false;
                    allList.clear();
                }
                if (results != null) {
                    allList.addAll(results);
                    adapter.loadMoreComplete();
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable e) {
                Log.i("error", e.getMessage());
                fragmentBinding.swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                fragmentBinding.swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        pageTo++;
        getData();

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext, VerticalVideoActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
