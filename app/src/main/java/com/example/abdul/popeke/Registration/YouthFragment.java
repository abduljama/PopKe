package com.example.abdul.popeke.Registration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.abdul.popeke.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YouthFragment extends Fragment {
    WebView  webView;
String  url =  "https://docs.google.com/forms/d/1Foa_yHq9vCw6QFhmzHOzhO539_gtrXZKAXgXO8LzXzs/viewform?edit_requested=true";

    public YouthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  rootView = inflater.inflate(R.layout.fragment_youth, container, false);

         webView = (WebView)rootView.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(url);
        return  rootView;
    }


}
