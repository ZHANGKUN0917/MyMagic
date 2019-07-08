package com.shidonghui.mymagic.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.base.BaseActivity;
import com.shidonghui.mymagic.databinding.ActivityWebViewBinding;

/**
 * @author ZhangKun
 * @create 2019/6/6
 * @Describe
 */
public class WebViewActivity extends BaseActivity {
    private ActivityWebViewBinding webViewBinding;

    @Override
    protected void initView(ViewDataBinding viewDataBinding) {
        webViewBinding = (ActivityWebViewBinding) viewDataBinding;
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webViewBinding.wv.loadUrl(url);
        webViewBinding.wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                webViewBinding.tvTitle.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
        webViewBinding.wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
                //错拦截处理
            }
        });
        WebSettings webSettings = webViewBinding.wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webViewBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_web_view;
    }

}
