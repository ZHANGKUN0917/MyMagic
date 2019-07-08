package com.shidonghui.mymagic.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.base.BaseActivity;
import com.shidonghui.mymagic.databinding.ActivityFlowlayoutBinding;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

/**
 * @author ZhangKun
 * @create 2019/5/29
 * @Describe
 */
public class FlowLayoutActivity extends BaseActivity {
    private ActivityFlowlayoutBinding flowLayoutBinding;
    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView"};

    @Override
    protected void initView(ViewDataBinding viewDataBinding) {
        flowLayoutBinding = (ActivityFlowlayoutBinding) viewDataBinding;
        flowLayoutBinding.idFlowlayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView tv = (TextView) View.inflate(mContext, R.layout.tag, null);
                tv.setText(o);
                return tv;
            }
        });
        flowLayoutBinding.idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(mContext, mVals[position], Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_flowlayout;
    }
}
