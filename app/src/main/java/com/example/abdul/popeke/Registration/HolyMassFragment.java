package com.example.abdul.popeke.Registration;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.abdul.popeke.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HolyMassFragment extends Fragment {

    WebView webView;

    private ProgressBar progress;
    String url =  "https://docs.google.com/forms/d/1sOXcyn59vtCOG3mVYU2OKWA4YGJzV1f9l_gVU6bYvGU/viewform";

    CoordinatorLayout coordinatorLayout;


    public HolyMassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_holy_mass, container, false);

        webView  = (WebView)rootView.findViewById(R.id.webView1);
        progress  = (ProgressBar)rootView.findViewById(R.id.progressBar);
        webView.getSettings().setJavaScriptEnabled(true);
        progress.setVisibility(View.GONE);

        webView.loadUrl(url);
        webView.setWebViewClient( new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url ) {
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
                        .make(coordinatorLayout, "Failed to Load Page", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                webView.loadUrl(url);
                            }
                        });

// Changing message text color
                snackbar.setActionTextColor(Color.RED);

            }


        });


        webView.loadUrl(url);
        return rootView;
    }


}
