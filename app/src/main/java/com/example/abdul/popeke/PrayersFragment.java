package com.example.abdul.popeke;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.abdul.popeke.Activities.MainActivity;
import com.example.abdul.popeke.Prayers.ExpandbleListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrayersFragment extends Fragment {

    //prayers listview stuff
    ExpandbleListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    Context c;
View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_prayers, null);
        view = inflater.inflate(R.layout.fragment_prayers, container, false);


        //set up expandable list for prayers

        // get the listview

        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandbleListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        /**
         * Ends here
         */

        // Handle click items za child items kwa hio expandable list
        expListView.setOnChildClickListener(new

                                                    ExpandableListView.OnChildClickListener() {

                                                        @Override
                                                        public boolean onChildClick(ExpandableListView parent, View v,
                                                                                    int groupPosition, int childPosition, long id) {
                                                            Toast.makeText(getActivity(),
                                                                    listDataHeader.get(groupPosition)
                                                                            + " : "
                                                                            + listDataChild.get(
                                                                            listDataHeader.get(groupPosition)).get(
                                                                            childPosition), Toast.LENGTH_SHORT)
                                                                    .show();
                                                            return false;
                                                        }
                                                    }

        );


        // now it will not fail here

        return view;
    }
    /*
     * Preparing the list data for Prayers Fragment
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data

        listDataHeader.add("Common Prayers");
        listDataHeader.add("Holy Rosary");
        listDataHeader.add("Stations of The Cross");
        listDataHeader.add("Liturgy Of The Mass");

        // Adding child data
        List<String> daily_prayers = new ArrayList<String>();
        daily_prayers.add("Basic Prayers");
        daily_prayers.add("The Apostles Creed");
        daily_prayers.add("Hail Holy Queen");
        daily_prayers.add("Act of Contrition");
        daily_prayers.add("Act of Hope , Act Of Love");
        daily_prayers.add("The Angelus");
        daily_prayers.add("Act of Faith");

        List<String> rosary = new ArrayList<String>();
        rosary.add("Introduction");
        rosary.add("Joyful Mystery (Monday, Saturday)");
        rosary.add("Sorrowful Mystery (Tuesday, Friday)");
        rosary.add("Glorious Mystery (Wednesday, Sunday)");
        rosary.add("Luminous Mystery (Thursday)");
        rosary.add("Final Rosary Prayers");

        List<String>missal = new ArrayList<String>();
       missal.add("Roman Catholic Eucharist Mass");


        List<String>cross = new ArrayList<String>();
        cross.add("First Station: Jesus is condemned to death");
        cross.add("Second Station: Jesus carries his cross");
        cross.add("Third Station: Jesus is condemned to death");
        cross.add("Fourth Station: Jesus is condemned to death");
        cross.add("Fifth Station: Jesus is condemned to death");
        cross.add("Sixth Station: Jesus is condemned to death");
        cross.add("Seventh Station: Jesus is condemned to death");
        cross.add("Eighth Station: Jesus is condemned to death");
        cross.add("Ninth Station: Jesus is condemned to death");
        cross.add("Tenth Station: Jesus is condemned to death");
        cross.add("Eleventh Station: Jesus is condemned to death");
        cross.add("Twelfth Station: Jesus is condemned to death");
        cross.add("Thirteenth Station: Jesus is condemned to death");
        cross.add("Fourteenth Station: Jesus is condemned to death");


        listDataChild.put(listDataHeader.get(0), daily_prayers); // Header, Child data
        listDataChild.put(listDataHeader.get(1), rosary);
        listDataChild.put(listDataHeader.get(2),cross);
        listDataChild.put(listDataHeader.get(3),missal);
    }

}