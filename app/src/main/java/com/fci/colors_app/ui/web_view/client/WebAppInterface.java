package com.fci.colors_app.ui.web_view.client;

import android.content.Context;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebAppInterface {
    private Context mContext;
    private WebView webView;
    private ProgressBar progressBar;

    /** Instantiate the interface and set the context */
    public WebAppInterface(Context c, WebView webView, ProgressBar progressBar) {
        mContext = c;
       this.webView=webView;
       this.progressBar=progressBar;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void GoBack() {
        webView.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                webView.goBack();
            }
        });

    }
}
