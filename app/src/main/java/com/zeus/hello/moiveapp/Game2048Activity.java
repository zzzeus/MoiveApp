package com.zeus.hello.moiveapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Game2048Activity extends AppCompatActivity {
    @BindView(R.id.mywebview)
     WebView webView;
    private WebSettings webSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2048);

        ButterKnife.bind(this);
        webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/game2048.html");
//        webView.loadUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA5MzI3NjE2MA==&hid=1&sn=40fb5e8365e38f200457ff620bba18de#wechat_redirect");
    }
}
