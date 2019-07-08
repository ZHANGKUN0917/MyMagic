package com.shidonghui.mymagic.live.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.shidonghui.mymagic.MyApp;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.live.adapter.LiveListAdapter;
import com.shidonghui.mymagic.base.BaseActivity;
import com.shidonghui.mymagic.databinding.ActivityLiveListBinding;
import com.shidonghui.mymagic.model.LiveListModel;
import com.shidonghui.mymagic.request.LiveListRequest;
import com.shidonghui.mymagic.request.LiveRequest;
import com.shidonghui.mymagic.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ZhangKun
 * @create 2019/6/13
 * @Describe
 */
public class LiveListActivity extends BaseActivity implements BaseMultiItemQuickAdapter.OnItemClickListener, BaseMultiItemQuickAdapter.RequestLoadMoreListener {
    private ActivityLiveListBinding liveListBinding;
    private LiveRequest liveRequest;
    private List<LiveListModel.DataBean> allList;
    private LiveListModel.DataBean dataBean;
    private LiveListAdapter adapter;
    private String lastRowid = "";
    private boolean isRefresh;
    private View headerView;
    private SimpleDraweeView simpleDraweeView;
    private TextView tv_live_title, tv_live_time, tv_live_status;
    /**
     * isFirstAdd是第一次添加到集合里面(每次数据请求之后都要把数据放到大集合里面，
     * 但要把第一次请求的数据的第一个拿出来当成headerview的数据源，
     * 移除第一次请求的数据的第一个当做recyclerview的数据源)
     */
    private boolean isFirstAdd = true;


    @Override
    protected void initView(ViewDataBinding viewDataBinding) {
        liveListBinding = (ActivityLiveListBinding) viewDataBinding;
        headerView = View.inflate(mContext, R.layout.list_item_live_list, null);
        liveRequest = MyApp.getRetrofit4().create(LiveRequest.class);
        liveListBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                lastRowid = "";
                isFirstAdd = true;
                getData();
            }
        });
        liveListBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        simpleDraweeView = headerView.findViewById(R.id.sdv_cover);
        tv_live_title = headerView.findViewById(R.id.tv_live_title);
        tv_live_time = headerView.findViewById(R.id.tv_live_time);
        tv_live_status = headerView.findViewById(R.id.tv_live_little_status);
        liveListBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void initData() {
        allList = new ArrayList<>();
        adapter = new LiveListAdapter(R.layout.list_item_live_list, allList);
        liveListBinding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnLoadMoreListener(this);
        adapter.addHeaderView(headerView);
        getData();
    }

    private void getData() {
        Observable<LiveListModel> liveList = liveRequest.liveList(new LiveListRequest(lastRowid));
        liveList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LiveListModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LiveListModel liveListModel) {
                if (isRefresh) {
                    allList.clear();
                    isRefresh = false;
                }
                liveListBinding.swipeRefresh.setRefreshing(false);
                List<LiveListModel.DataBean> data = liveListModel.getData();
                if (data != null) {
                    allList.addAll(data);
                    if (isFirstAdd) {
                        dataBean = allList.get(0);
                        allList.remove(0);
                        isFirstAdd = false;
                    }
                    headerView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            jumpToLivePlayer(dataBean);

                        }
                    });
                    simpleDraweeView.setImageURI(dataBean.getLivePicture());
                    tv_live_title.setText(dataBean.getLiveTitle());
                    tv_live_time.setText(TimeUtils.getDateStrFromTimestamp(Long.valueOf(dataBean.getStartTime())));
                    switch (dataBean.getStatus()) {
                        case "STARTED":
                            tv_live_status.setText("直播中");
                            break;
                        case "PUSHING":
                            tv_live_status.setText("预告");
                            break;
                        case "FINISH":
                            tv_live_status.setText("回放");
                            break;
                        default:
                            break;
                    }
                    lastRowid = allList.get(allList.size() - 1).getLiveId();
                    adapter.setEnableLoadMore(true);
                    if (data.size() == 0) {
                        adapter.setEnableLoadMore(false);
                        adapter.loadMoreEnd(true);
                    }
                } else {
                    adapter.setEnableLoadMore(false);
                    adapter.loadMoreEnd(true);
                }
                adapter.loadMoreComplete();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                liveListBinding.swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                liveListBinding.swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_live_list;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (allList.get(position).getStatus()) {
            case "STARTED":
                jumpToLivePlayer(allList.get(position));
                break;
            case "PUSHING":
                tv_live_status.setText("预告");
                break;
            case "FINISH":
                tv_live_status.setText("回放");
                break;
            default:
                break;
        }


    }

    @Override
    public void onLoadMoreRequested() {
        getData();
    }

    private void jumpToLivePlayer(LiveListModel.DataBean liveInfo) {
        Intent intent = new Intent(mContext, LivePlayerActivity.class);
        intent.putExtra("liveInfo", liveInfo);
        startActivity(intent);
    }
}
