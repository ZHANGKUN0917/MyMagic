package com.shidonghui.mymagic.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.utils.DisplayUtil;

/**
 * @author ZhangKun
 * @create 2019/6/4
 * @Describe 相机打开失败警告
 */

public class LiveWarnDialog extends Dialog {

    private static LiveWarnDialog dialog;

    public static LiveWarnDialog with(Context context) {
        if (dialog == null) {
            dialog = new LiveWarnDialog(context);
        }
        return dialog;
    }

    public LiveWarnDialog(@NonNull Context context) {
        super(context, R.style.custom_dialog);

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_live_warn, null); // 得到加载view

        setCancelable(false);// 不可以用"返回键"取消
        setContentView(v, new LinearLayout.LayoutParams(
                DisplayUtil.getDisplayWidth(context) * 11 / 15,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView tv_confirm = (TextView) v.findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mOnItemClickLitener != null) {
                    mOnItemClickLitener.onConfirmClick(v);
                }
            }
        });

    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (dialog != null) {
            dialog = null;
        }
    }

    public interface OnItemClickLitener {
        void onConfirmClick(View view);

    }

    private OnItemClickLitener mOnItemClickLitener;

    public LiveWarnDialog setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
        return dialog;
    }
}
