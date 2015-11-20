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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.abdul.popeke.Activities.MainActivity;
import com.example.abdul.popeke.NewsItems.MediaFeedTabView;
import com.example.abdul.popeke.Prayers.ExpandbleListAdapter;
import com.example.abdul.popeke.Prayers.ShowPrayerFragment;
import com.example.abdul.popeke.Program.ProgramFragment;
import com.example.abdul.popeke.YouTube.YouTubeRecyclerViewFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrayersFragment extends Fragment {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

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
        mFragmentManager = getFragmentManager();

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
                                                           /* Toast.makeText(getActivity(),
                                                                    listDataHeader.get(groupPosition)
                                                                            + " : "
                                                                            + listDataChild.get(
                                                                            listDataHeader.get(groupPosition)).get(
                                                                            childPosition), Toast.LENGTH_SHORT)
                                                                    .show();*/



                                                            FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();

                                                            Bundle bundle = new Bundle();
                                                            String prayer = listDataChild.get(
                                                                    listDataHeader.get(groupPosition)).get(
                                                                    childPosition);
                                                            bundle.putString("prayer", prayer);
                                                            ShowPrayerFragment prayerFragment = new ShowPrayerFragment();
                                                            prayerFragment.setArguments(bundle);

                                                            xfragmentTransaction.replace(R.id.containerView, prayerFragment).addToBackStack( "tag" ).commit();
                                                            return false;
                                                        }
                                                    }

        );


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

        daily_prayers.add("Prayer for The Pope");
        daily_prayers.add("Sign of The Cross");
        daily_prayers.add("The Apostles Creed");
        daily_prayers.add("Hail Holy Queen");
        daily_prayers.add("Act of Contrition");
        daily_prayers.add("Act of Hope , Love and Faith");
        daily_prayers.add("The Angelus");

        daily_prayers.add("Prayer of St.Joseph");


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
        cross.add("Third Station: Jesus falls the first time");
        cross.add("Fourth Station: Jesus meets his mother");
        cross.add("Fifth Station: Simon of Cyrene helps Jesus to carry his cross");
        cross.add("Sixth Station: Veronica wipes the face of Jesus");
        cross.add("Seventh Station: Jesus falls the second time");
        cross.add("Eighth Station: Jesus meets the women of Jerusalem");
        cross.add("Ninth Station: Jesus falls a third time");
        cross.add("Tenth Station: Jesus’ clothes are taken away");
        cross.add("Eleventh Station: Jesus is nailed to the cross");
        cross.add("Twelfth Station: Jesus dies on the cross");
        cross.add("Thirteenth Station: The body of Jesus is taken down from the cross");
        cross.add("Fourteenth Station: Jesus is laid in the tomb");


        listDataChild.put(listDataHeader.get(0), daily_prayers); // Header, Child data
        listDataChild.put(listDataHeader.get(1), rosary);
        listDataChild.put(listDataHeader.get(2),cross);
        listDataChild.put(listDataHeader.get(3),missal);
    }

}