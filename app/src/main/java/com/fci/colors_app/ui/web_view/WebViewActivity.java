package com.fci.colors_app.ui.web_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fci.colors_app.R;
import com.fci.colors_app.di.component.ActivityComponent;
import com.fci.colors_app.ui.base.BaseActivity;
import com.fci.colors_app.ui.web_view.client.CustomChromeClient;
import com.fci.colors_app.ui.web_view.client.CustomWebClient;
import com.fci.colors_app.ui.web_view.client.WebAppInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity<WebViewModel> implements WebNavigator {

    @BindView(R.id.toolbarTitle)
    AppCompatTextView toolbarTitle;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private String link;
    private String title = null;

    public static Intent newIntent(Context context) {
        return new Intent(context, WebViewActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        mViewModel.setNavigator(this);

        link = getIntent().getStringExtra("link");
        title = getIntent().getStringExtra("title");

        if (title != null)
            toolbarTitle.setText(title);
        else
            toolbarTitle.setText(link);

        setUp(link);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setUp(String url) {
        mWebView.setWebViewClient(new CustomWebClient(progressBar, swipeRefreshLayout, this));
        mWebView.setWebChromeClient(new CustomChromeClient());
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.addJavascriptInterface(new WebAppInterface(this, mWebView, progressBar), "Android");
        mWebView.loadUrl(url);

        swipeRefreshLayout.setOnRefreshListener(() -> mWebView.loadUrl(url));
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }


    @OnClick(R.id.backButton)
    public void onBackClicked() {
        finish();
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void showMyApiMessage(String message) {

    }

    @OnClick(R.id.btnShare)
    public void onShareClicked() {
        shareLink(link);
    }



    public void shareLink(String link) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
        String appName = mViewModel.getDataManager().getSettingsObject().getAppTitle();
        String androidLink = mViewModel.getDataManager().getSettingsObject().getAppAndroidLnk();
        String iosLink = mViewModel.getDataManager().getSettingsObject().getAppIosLink();
        String shareBody = appName + "\n" + link + "\n" + "For Android: " + androidLink + "\n" + "For IOS: " + iosLink;
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        this.startActivity(Intent.createChooser(shareIntent, "Share via.."));
    }
}