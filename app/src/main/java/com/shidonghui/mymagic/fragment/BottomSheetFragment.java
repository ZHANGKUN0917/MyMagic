package com.shidonghui.mymagic.fragment;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.adapter.ChannelAdapter;
import com.shidonghui.mymagic.databinding.MoreBottomDialogBinding;
import com.shidonghui.mymagic.utils.ItemDragHelperCallback;
import com.shidonghui.mymagic.utils.ListDataSaveWithSharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/5/23
 * @Describe
 */
public class BottomSheetFragment extends BottomSheetDialogFragment implements ChannelAdapter.UpdateData {
    private MoreBottomDialogBinding moreBottomDialogBinding;
    private List<String> TitleList;
    private List<String> OtherTitleList;
    private BottomSheetBehavior<FrameLayout> behavior;
    // 顶部向下偏移量
    private int topOffest = 0;
    private ChannelAdapter adapter;
    UpdateTablayout updateTablayout;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getContext() == null) {
            return super.onCreateDialog(savedInstanceState);
        }
        return new BottomSheetDialog(getContext(), R.style.TransparentBottomSheetStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        moreBottomDialogBinding = DataBindingUtil.inflate(inflater, R.layout.more_bottom_dialog, container, false);
        initView();
        return moreBottomDialogBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initView() {
        moreBottomDialogBinding.ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                getBehavior().setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });


    }

    private void initData() {
        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(moreBottomDialogBinding.recyclerView);
        TitleList = new ArrayList<>();
        OtherTitleList = new ArrayList<>();
        TitleList = ListDataSaveWithSharedPreferences.getList(getContext(), "selectedChannel");
        OtherTitleList = ListDataSaveWithSharedPreferences.getList(getContext(), "unSelectedChannel");
        adapter = new ChannelAdapter(getActivity()
                , helper, TitleList, OtherTitleList);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        moreBottomDialogBinding.recyclerView.setLayoutManager(manager);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                int itemType = adapter.getItemViewType(i);
                return itemType == ChannelAdapter.TYPE_MY ||
                        itemType == ChannelAdapter.TYPE_OTHER ?
                        1 : 4;
            }
        });
        moreBottomDialogBinding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        adapter.setUpdateData(this);
        super.onDestroy();

    }

    @Override
    public void onStart() {
        super.onStart();
        // 设置软键盘不自动弹出
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        FrameLayout bottomSheet = dialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
        if (bottomSheet != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
            layoutParams.height = getHeight();
            behavior = BottomSheetBehavior.from(bottomSheet);
            // 初始为展开状态
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            behavior.setSkipCollapsed(true);
        }
    }

    private int getHeight() {
        int height = 1920;
        if (getContext() != null) {
            WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            Point point = new Point();
            if (windowManager != null) {
                // 使用point已经减去了状态栏的距离
                windowManager.getDefaultDisplay().getSize(point);
                height = point.y - getTopOffest();
            }
        }
        return height;
    }

    private int getTopOffest() {
        return topOffest;
    }

    public void setTopOffest(int topOffest) {
        this.topOffest = topOffest;
    }

    public BottomSheetBehavior<FrameLayout> getBehavior() {
        return behavior;
    }

    @Override
    public void update(List<String> myChannelList, List<String> otherChannelList) {
        /**
         * 更新本地存储数据
         */
        ListDataSaveWithSharedPreferences.saveList(getActivity(), "selectedChannel", myChannelList);
        ListDataSaveWithSharedPreferences.saveList(getActivity(), "unSelectedChannel", otherChannelList);
        updateTablayout.updateTablayout(myChannelList, otherChannelList);
    }

    public interface UpdateTablayout {
        void updateTablayout(List<String> myChannelList, List<String> otherChannelList);
    }

    public void setUpdateTablayout(UpdateTablayout updateTablayout) {
        this.updateTablayout = updateTablayout;
    }
}
