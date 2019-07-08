package com.shidonghui.mymagic.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.utils.ThemeUtil;

/**
 * @author ZhangKun
 * @create 2019/5/30
 * @Describe 主题
 */
public class ThemeActivity extends AppCompatActivity implements ColorChooserDialog.ColorCallback {
    private TextView tv_theme;
    public static int theme = R.style.Blue;//设置默认主题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(theme);
        setContentView(R.layout.activity_theme);
        tv_theme = findViewById(R.id.tv_theme);
        tv_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ColorChooserDialog.Builder(ThemeActivity.this, R.string.theme)
                        .customColors(R.array.colors, null)
                        .doneButton(R.string.done)
                        .cancelButton(R.string.cancel)
                        .allowUserColorInput(false)
                        .allowUserColorInputAlpha(false)
                        .show();
            }
        });
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, int selectedColor) {
        ThemeUtil.onColorSelection(this, dialog, selectedColor);
    }
}
