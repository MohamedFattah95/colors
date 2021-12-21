package com.fci.colors_app.ui.web_view.client;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fci.colors_app.R;


public class CustomWebClient extends WebViewClient {
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;

    public CustomWebClient(ProgressBar progressBar, SwipeRefreshLayout swipeRefreshLayout, Context context){
        this.progressBar = progressBar;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.context = context;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        view.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        progressBar.setVisibility(View.VISIBLE);

        if (url.startsWith("tel:") || url.startsWith("sms:") || url.startsWith("smsto:") || url.startsWith("mailto:") || url.startsWith("mms:") || url.startsWith("mmsto:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
            progressBar.setVisibility(View.INVISIBLE);
            //return true;
        }else {
            view.loadUrl(url);
        }
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        Toast.makeText(context, R.string.check_internet_conn, Toast.LENGTH_LONG).show();
        view.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }


}
