package com.example.abdul.popeke;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.abdul.popeke.Activities.MainActivity;
import com.example.abdul.popeke.R;

/**
 * Created by kiprop on 11/15/15.
 */
public class BiographyFragment extends Fragment {

    Toolbar toolbar;
    WebView webView;
    String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        String prayer_name = this.getArguments().getString("prayer");
//        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);

        //  toolbar.setTitle(prayer_name);


                url = "file:///android_asset/biography.html";

        View rootView = inflater.inflate(R.layout.show_prayer, container, false);


        //webView.setWebChromeClient(new MyWebChromeClient());
        webView = (WebView) rootView.findViewById(R.id.webViewShowPrayer);
        webView.getSettings().setJavaScriptEnabled(true);


        // https://twitter.com/Pontifex


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // Handle the error
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


        webView.loadUrl(url);


        return rootView;
    }



}

