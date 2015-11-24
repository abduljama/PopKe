package com.example.abdul.popeke;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Ratan on 7/29/2015.
 */

public class MapsFragment extends Fragment {
    private GoogleMap map;

    private static final LatLng LOCATION = new LatLng(7.0722, 125.6131);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map,
                container, false);

        // create map
        map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        // zoom in the camera to Davao city
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION, 15));

        // animate the zoom process
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);


        return rootView;
    }
}
