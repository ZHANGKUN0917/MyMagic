package com.shidonghui.mymagic.widget.dialog;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.databinding.PopupwindowLiveBeautyBinding;


/**
 * 美颜
 */
public class LiveBeautyPopupWindow extends PopupWindow {

    private PopupwindowLiveBeautyBinding dataBinding;
    private static LiveBeautyPopupWindow popWindow;
    private Activity mActivity;
    private boolean mIsNeedFB;
    private boolean isFromUser;
    private int beauty_level;
    private int whiten_level;
    private int redden_level;

    public static LiveBeautyPopupWindow with(Activity activity, boolean isNeedFB, float var1, float var2, float var3) {
        if (popWindow == null) {
            popWindow = new LiveBeautyPopupWindow(activity, isNeedFB, var1, var2, var3);
        }
        return popWindow;
    }

    @SuppressLint("InflateParams")
    public LiveBeautyPopupWindow(Activity activity, final boolean isNeedFB, float var1, float var2, float var3) {
        mActivity = activity;
        this.mIsNeedFB = isNeedFB;
        beauty_level = (int) (var1 * 100);
        whiten_level = (int) (var2 * 100);
        redden_level = (int) (var3 * 100);
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.popupwindow_live_beauty, null, false);

        // 设置SelectPicPopupWindow的View
        setContentView(dataBinding.getRoot());
        // 设置SelectPicPopupWindow弹出窗体的宽
        setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        setFocusable(true);
        setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        setBackgroundDrawable(dw);
        // 设置PopupWindow弹出窗体动画效果
        setAnimationStyle(R.style.dialogWindowAnim);

        dataBinding.btnLiveClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        dataBinding.swIsNeedBeauty.setChecked(mIsNeedFB);
        dataBinding.swIsNeedBeauty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mOnStatusChangeListener != null) {
                    mIsNeedFB = isChecked;
                    if (mIsNeedFB && !isFromUser) {
                        dataBinding.seekbarBeautify.setProgress(100);
                        dataBinding.seekbarWhiten.setProgress(100);
                        dataBinding.seekbarRedden.setProgress(100);
                    } else {
                        isFromUser = false;
                    }
                    mOnStatusChangeListener.onBeautySwitch(buttonView, dataBinding.seekbarBeautify, dataBinding.seekbarWhiten, dataBinding.seekbarRedden);
                }
            }
        });
        dataBinding.seekbarBeautify.setMax(100);
        dataBinding.seekbarBeautify.setProgress(beauty_level);
        dataBinding.seekbarBeautify.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mOnStatusChangeListener != null) {
                    beauty_level = progress;
                    isFromUser = fromUser;
                    if (mIsNeedFB && isFromUser) {
                        if (beauty_level == 0 && whiten_level == 0 && redden_level == 0) {
                            dataBinding.swIsNeedBeauty.setChecked(false);
                        }
                    } else if (isFromUser) {
                        dataBinding.swIsNeedBeauty.setChecked(true);
                    }
                    mOnStatusChangeListener.onBeautifyChangeListener(seekBar, progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dataBinding.seekbarWhiten.setMax(100);
        dataBinding.seekbarWhiten.setProgress(whiten_level);
        dataBinding.seekbarWhiten.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mOnStatusChangeListener != null) {
                    whiten_level = progress;
                    isFromUser = fromUser;
                    if (mIsNeedFB && isFromUser) {
                        if (beauty_level == 0 && whiten_level == 0 && redden_level == 0) {
                            dataBinding.swIsNeedBeauty.setChecked(false);
                        }
                    } else if (isFromUser) {
                        dataBinding.swIsNeedBeauty.setChecked(true);
                    }
                    mOnStatusChangeListener.onWhitenChangeListener(seekBar, progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dataBinding.seekbarRedden.setMax(100);
        dataBinding.seekbarRedden.setProgress(redden_level);
        dataBinding.seekbarRedden.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mOnStatusChangeListener != null) {
                    redden_level = progress;
                    isFromUser = fromUser;
                    if (mIsNeedFB && isFromUser) {
                        if (beauty_level == 0 && whiten_level == 0 && redden_level == 0) {
                            dataBinding.swIsNeedBeauty.setChecked(false);
                        }
                    } else if (isFromUser) {
                        dataBinding.swIsNeedBeauty.setChecked(true);
                    }
                    mOnStatusChangeListener.onReddenChangeListener(seekBar, progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (popWindow != null) {
            popWindow = null;
        }
    }

    /**
     * 显示popupWindow
     *
     * @param view
     */
    public void show(View view) {
        showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public interface OnStatusChangeListener {
        void onBeautySwitch(CompoundButton buttonView, SeekBar seekBar1, SeekBar seekBar2, SeekBar seekBar3);

        void onBeautifyChangeListener(View view, int progress);

        void onWhitenChangeListener(View view, int progress);

        void onReddenChangeListener(View view, int progress);
    }

    private OnStatusChangeListener mOnStatusChangeListener;

    public LiveBeautyPopupWindow setOnStatusChangeLitener(OnStatusChangeListener mOnStatusChangeListener) {
        this.mOnStatusChangeListener = mOnStatusChangeListener;
        return popWindow;
    }
}
