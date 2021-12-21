package com.fci.colors_app.ui.web_view.client;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class CustomChromeClient extends WebChromeClient {
    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        // android 6.0 is captured below by title
        if(title.contains("404")|| title.contains("505")){
            view.loadUrl("file:///android_asset/serverError.html");
        }
    }
}
