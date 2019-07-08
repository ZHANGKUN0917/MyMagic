package com.shidonghui.mymagic.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

;import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.utils.DisplayUtil;


/**
 * @author ZhangKun
 * @create 2019/6/5
 * @Describe 关闭直播提示
 */

public class LiveCloseDialog extends Dialog {

    private static LiveCloseDialog dialog;
    private Context mContext;

    public static LiveCloseDialog with(Context context) {
        if (dialog == null) {
            dialog = new LiveCloseDialog(context);
        }
        return dialog;
    }

    public LiveCloseDialog(@NonNull Context context) {
        super(context, R.style.custom_dialog);
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_live_close, null); // 得到加载view

        setCancelable(false);// 不可以用"返回键"取消
        setContentView(v, new LinearLayout.LayoutParams(
                DisplayUtil.getDisplayWidth(context) * 11 / 15,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        TextView tv_continue = (TextView) findViewById(R.id.tv_continue);
        TextView tv_finish = (TextView) findViewById(R.id.tv_finish);

        tv_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mOnItemClickLitener != null) {
                    mOnItemClickLitener.onContinueClick(v);
                }
            }
        });
        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mOnItemClickLitener != null) {
                    mOnItemClickLitener.onFinishClick(v);
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
        if (mContext != null) {
            mContext = null;
        }
    }

    public interface OnItemClickLitener {
        void onContinueClick(View view);

        void onFinishClick(View view);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public LiveCloseDialog setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
        return dialog;
    }
}
