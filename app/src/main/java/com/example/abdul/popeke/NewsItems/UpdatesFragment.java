package com.example.abdul.popeke.NewsItems;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdul.popeke.R;

/**
 * Created by Ratan on 7/29/2015.
 */
public class UpdatesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.updates_layout,null);
    }
}
