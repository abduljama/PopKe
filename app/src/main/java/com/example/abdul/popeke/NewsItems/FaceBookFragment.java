package com.example.abdul.popeke.NewsItems;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.abdul.popeke.R;

/**
 * Created by kiprop on 11/6/15.
 */
public class FaceBookFragment extends Fragment{
    WebView webView;
    String  url1 =  "http://m.facebook.com/";
    private ProgressBar progress;
    CoordinatorLayout coordinatorLayout;



    public FaceBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_facebook, container, false);


        //webView.setWebChromeClient(new MyWebChromeClient());
        webView  = (WebView)rootView.findViewById(R.id.webView2);
        progress = (ProgressBar)rootView.findViewById(R.id.progressBar);
        progress.setVisibility(View.GONE);
        coordinatorLayout = (CoordinatorLayout)rootView.findViewById(R.id.coordinatorLayout);
        webView.getSettings().setJavaScriptEnabled(true);


        String  url =  "https://www.facebook.com/PopeVisitsKenya/?fref=ts";

        https://twitter.com/Pontifex


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progress.setVisibility(View.GONE);
                progress.setProgress(100);

                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progress.setVisibility(View.VISIBLE);
                progress.setProgress(0);
                super.onPageStarted(view, url, favicon);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                view.loadUrl("about:blank");
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Failed to Load Page", Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                webView.loadUrl(url1);
                            }
                        });

// Changing message text color
                snackbar.setActionTextColor(Color.RED);
                snackbar.show();

            }


        });

        webView.loadUrl("http://developer.android.com");

        webView.loadUrl(url);



        webView.setOnKeyListener(new View.OnKeyListener()

        {

            @Override

            public boolean onKey(View v, int keyCode, KeyEvent event)

            {

                if (event.getAction() == KeyEvent.ACTION_DOWN)

                {


                    WebView webView = (WebView) v;
                    switch (keyCode)

                    {

                        case KeyEvent.KEYCODE_BACK:

                            if (webView.canGoBack())

                            {

                                webView.goBack();

                                return true;

                            }
                            break;
                    }
                }
                return false;

            }

        });
   //     webView.getSettings().setJavaScriptEnabled(true);
        progress.setVisibility(View.GONE);


      //  webView.loadUrl(url);

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

    }}