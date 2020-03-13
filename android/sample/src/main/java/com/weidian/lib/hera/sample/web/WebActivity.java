package com.weidian.lib.hera.sample.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.weidian.lib.hera.page.view.PageWebView;
import com.weidian.lib.hera.sample.R;

/**
 * @author kylingo
 * @since 2020/03/13 13:04
 */
public class WebActivity extends AppCompatActivity {

    public static final String URL_MGTV = "https://m.mgtv.com/channel/home";
    private static final String KEY_URL = "key_url";

    private PageWebView mWebView;
    private String mUrl;

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);
        initView();

        parseIntent();
        loadUrl();
    }

    private void initView() {
        findViewById(R.id.ivWebBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FrameLayout flWebContainer = findViewById(R.id.flWebContainer);
        mWebView = new PageWebView(this);
        flWebContainer.addView(mWebView);
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            mUrl = intent.getStringExtra(KEY_URL);
        }

        if (TextUtils.isEmpty(mUrl)) {
            mUrl = URL_MGTV;
        }
    }

    private void loadUrl() {
        mWebView.loadUrl(mUrl);
    }
}
