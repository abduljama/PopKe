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
public class HolyMassFragment extends Fragment {

    WebView webView;
    String  url =  "https://docs.google.com/forms/d/1gexGCxic86MH2VS09gpHIT8veQ0vwHlTSDjH2aGiWlA/viewform?c=0&w=1";


    public HolyMassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_holy_mass, container, false);

        webView  = (WebView)rootView.findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(url);
        return rootView;
    }


}
