package com.example.abdul.popeke.Prayers;

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
public class ShowPrayerFragment extends Fragment {

    Toolbar toolbar;
    WebView webView;
    String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String prayer_name = this.getArguments().getString("prayer");
//        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);

        //  toolbar.setTitle(prayer_name);


        switch (prayer_name) {

            //common prayers
            case "Prayer of The Pope":
                url = "file:///android_asset/pope_prayer.html";
                break;

            case "Sign of The Cross":
                url = "file:///android_asset/sign_of_cross.html";
                break;
            case "The Apostles Creed":
                url = "file:///android_asset/apostles_creed.html";
                break;
            case "Hail Holy Queen":
                url = "file:///android_asset/hail_holy_queen.html";
                break;
            case "Act of Contrition":
                url = "file:///android_asset/act_of_contrition.html";
                break;
            case "Act of Hope , Love and Faith":
                url = "file:///android_asset/other_acts.html";
                break;
            case "The Angelus":
                url = "file:///android_asset/angelus.html";
                break;

            case "Prayer of St.Joseph":
                url = "file:///android_asset/stjoseph_prayer.html";
                break;


            //rosary
            case "Introduction":
                url = "file:///android_asset/rosary_introduction.html";
                break;
            case "Joyful Mystery (Monday, Saturday)":
                url = "file:///android_asset/rosary_joyful_mystery.html";
                break;
            case "Sorrowful Mystery (Tuesday, Friday)":
                url = "file:///android_asset/rosary_sorrowful_mystery.html";
                break;
            case "Glorious Mystery (Wednesday, Sunday)":
                url = "file:///android_asset/rosary_glorious_mystery.html";
                break;
            case "Luminous Mystery (Thursday)":
                url = "file:///android_asset/rosary_light_mystery.html";
                break;
            case "Final Rosary Prayers":
                url = "file:///android_asset/rosary_prayer_after.html";
                break;
            case "Roman Catholic Eucharist Mass":
                url = "file:///android_asset/new_missal.html";
                break;

            //way of cross

            case "First Station: Jesus is condemned to death":
                url = "file:///android_asset/station_one.html";
                break;
            case "Second Station: Jesus carries his cross":
                url = "file:///android_asset/station_two.html";
                break;
            case "Third Station: Jesus falls the first time":
                url = "file:///android_asset/station_three.html";
                break;
            case "Fourth Station: Jesus meets his mother":
                url = "file:///android_asset/station_four.html";
                break;
            case "Fifth Station: Simon of Cyrene helps Jesus to carry his cross":
                url = "file:///android_asset/station_five.html";
                break;
            case "Sixth Station: Veronica wipes the face of Jesus":
                url = "file:///android_asset/station_six.html";
                break;
            case "Seventh Station: Jesus falls the second time":
                url = "file:///android_asset/station_seven.html";
                break;
            case "Eighth Station: Jesus meets the women of Jerusalem":
                url = "file:///android_asset/station_eight.html";
                break;
            case "Ninth Station: Jesus falls a third time":
                url = "file:///android_asset/station_nine.html";
                break;
            case "Tenth Station: Jesus’ clothes are taken away":
                url = "file:///android_asset/station_ten.html";
                break;
            case "Eleventh Station: Jesus is nailed to the cross":
                url = "file:///android_asset/station_eleven.html";
                break;
            case "Twelfth Station: Jesus dies on the cross":
                url = "file:///android_asset/station_twelve.html";
                break;
            case "Thirteenth Station: The body of Jesus is taken down from the cross":
                url = "file:///android_asset/station_thirteen.html";
                break;
            case "Fourteenth Station: Jesus is laid in the tomb":
                url = "file:///android_asset/station_fourteen.html";
                break;


        }

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

