package com.example.abdul.popeke;

/**
 * Created by kiprop on 11/22/15.
 */
import android.content.Intent;
import android.graphics.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class TestMapActivity extends ActionBarActivity {

    private static  LatLng LOCATION =new LatLng(2.0,2.0);

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);


       // get co -ordinates from program

                Intent intent = getIntent();
        Double latitude = Double.valueOf(intent.getStringExtra("latitude"));
        Double longitude = Double.valueOf(intent.getStringExtra("longitude"));
       String name = intent.getStringExtra("location");

        LOCATION= new LatLng(latitude, longitude);

        //get izo locations


        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        if (name.equals("Fiumicino airport")){

            map.addMarker(new MarkerOptions().position(LOCATION).title("Italy").snippet(name));
        }
        else {

            map.addMarker(new MarkerOptions().position(LOCATION).title("Nairobi City").snippet(name));
        }

        // zoom in the camera to Davao city
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION, 15));

        // animate the zoom process
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }


}
