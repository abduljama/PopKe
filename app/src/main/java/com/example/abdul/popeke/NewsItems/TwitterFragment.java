package com.example.abdul.popeke.NewsItems;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.abdul.popeke.R;

/**
 * Created by kiprop on 11/6/15.
 */
public class TwitterFragment extends Fragment{
    WebView webView;
    String  url =  "https://twitter.com/@popeinkenya";



    public TwitterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_twitter, container, false);


     //webView.setWebChromeClient(new MyWebChromeClient());
        webView  = (WebView)rootView.findViewById(R.id.webView3);
        webView.getSettings().setJavaScriptEnabled(true);


            String  url =  "https://twitter.com/@popeinkenya";

        https://twitter.com/Pontifex


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

        webView.loadUrl("http://developer.android.com");

        webView.loadUrl(url);
        return rootView;
    }
    public class MyWebChromeClient extends WebChromeClient {
        //Handle javascript alerts:
        @Override
        public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
            Log.d("alert", message);

            result.confirm();
            return true;
        }

        ;


    }}