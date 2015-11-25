package com.example.abdul.popeke.Activities;

/**
 * Created by kiprop on 10/27/15.
 */

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Spinner;

import com.example.abdul.popeke.AboutFragment;
import com.example.abdul.popeke.BiographyFragment;
import com.example.abdul.popeke.DonateFragment;
import com.example.abdul.popeke.MapsFragment;
import com.example.abdul.popeke.NewsItems.MediaFeedTabView;
import com.example.abdul.popeke.PrayersFragment;
import com.example.abdul.popeke.Program.ProgramFragment;
import com.example.abdul.popeke.R;
import com.example.abdul.popeke.Registration.RegisterFragment;
import com.example.abdul.popeke.SupportWithTheRosaryFragment;
import com.example.abdul.popeke.YouTube.YouTubeRecyclerViewFragment;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;


public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    Toolbar toolbar;
    private static final String YOUTUBE_PLAYLIST = "PLwHV_QFJijrxibV2k5SyrEYan0I42MaCi";
    private YouTube mYoutubeDataApi;
    private final GsonFactory mJsonFactory = new GsonFactory();
    private final HttpTransport mTransport = AndroidHttp.newCompatibleTransport();
    Spinner spinner_nav;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Parse.enableLocalDatastore(this);
//
//        Parse.initialize(this, "QjpXM7TaA5v0f6JpnETITmFH5srVG7fr9VJKgBgM", "dmULcfLUq8GMSHCw3I4uwb4MW4MLP7fZ0KrgNkx3");
//        ParseInstallation.getCurrentInstallation().saveInBackground();
//
//
//        ParseObject gameScore = new ParseObject("GameScore");
//        gameScore.put("score", 1337);
//        gameScore.put("playerName", "Sean Plott");
//        gameScore.put("cheatMode", false);
//        gameScore.saveInBackground();



        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);

        //youtube stuff

        //Check for any issues kama the official youtube app is installed

        final YouTubeInitializationResult result = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this);

        if (result != YouTubeInitializationResult.SUCCESS) {
            //If there are any issues we can show an error dialog.
            result.getErrorDialog(this, 0).show();
        }

        mYoutubeDataApi = new YouTube.Builder(mTransport, mJsonFactory, null)
                .setApplicationName(getResources().getString(R.string.app_name))
                .build();

        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.menu);

        mDrawerLayout.openDrawer(Gravity.LEFT);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the BiographyFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();

        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new BiographyFragment()
        ).commit();




        /**
         * Setup click events on the Navigation View Items.
         */


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();


                if (menuItem.getItemId() == R.id.nav_item_about) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new AboutFragment()).commit();
                    toolbar.setTitle("About");
                }
                if (menuItem.getItemId() == R.id.nav_item_donate) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new DonateFragment()).commit();

                    toolbar.setTitle("Donate");
                }

                if (menuItem.getItemId() == R.id.nav_item_videos ) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new YouTubeRecyclerViewFragment().newInstance(mYoutubeDataApi, YOUTUBE_PLAYLIST)).commit();

                    toolbar.setTitle("Videos");
                }





             /*   if (menuItem.getItemId() == R.id.nav_item_gallery) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new GalleryFragment()).commit();
                }
                */
               /* if (menuItem.getItemId() == R.id.nav_item_hymns) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new HymnsFragment()).commit();
                }*/
               /* if (menuItem.getItemId() == R.id.nav_item_directions) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new MapsFragment()).commit();
                }

                */
                if (menuItem.getItemId() == R.id.nav_item_prayers) {

                   // WebView wv;
                  //  wv = (WebView) findViewById(R.id.webView2);
//                    wv.loadUrl("file:///android_asset/pope_prayer.html");

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new PrayersFragment()).commit();
                    toolbar.setTitle("Pray With Pope Francis");
                }
                if (menuItem.getItemId() == R.id.nav_item_biography) {

                    // WebView wv;
                    //  wv = (WebView) findViewById(R.id.webView2);
//                    wv.loadUrl("file:///android_asset/pope_prayer.html");

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new BiographyFragment()).commit();
                    toolbar.setTitle("Biography of the Pope");
                }
                if (menuItem.getItemId() == R.id.nav_item_program) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new ProgramFragment()).commit();
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.containerView, YouTubeRecyclerViewFragment.newInstance(mYoutubeDataApi, YOUTUBE_PLAYLIST))
//                    .commit();
                    toolbar.setTitle("Program");
                }
                if (menuItem.getItemId() == R.id.navigation_item_register) {


                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new RegisterFragment()).commit();
                    //  addItemsToSpinner();

                    /** if (toolbar != null) {
                     setSupportActionBar(toolbar);
                     getSupportActionBar().setDisplayShowTitleEnabled(false);
                     getSupportActionBar().setHomeButtonEnabled(true);

                     }
                     addItemsToSpinner();
                     **/
                    toolbar.setTitle("Register");
                }
                if (menuItem.getItemId() == R.id.nav_item_support_pope) {


                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new SupportWithTheRosaryFragment()).commit();
                    //  addItemsToSpinner();

                    /** if (toolbar != null) {
                     setSupportActionBar(toolbar);
                     getSupportActionBar().setDisplayShowTitleEnabled(false);
                     getSupportActionBar().setHomeButtonEnabled(true);

                     }
                     addItemsToSpinner();
                     **/
                    toolbar.setTitle("Pray The Rosary");
                }

             /*   if (menuItem.getItemId() == R.id.nav_item_settings) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new SettingsFragment()).commit();
                }
                */
                if (menuItem.getItemId() == R.id.nav_item_media) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new MediaFeedTabView()).commit();

                    toolbar.setTitle("Media Feed");
                }


                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */


        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);


        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }







    //below not used
/*  private void addItemsToSpinner() {

    if (toolbar != null) {
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    ArrayList<String> list = new ArrayList<String>();
    list.add("Holy  Mass ");
    list.add("Youth Meeting ");
    list.add("Volunteers");




    CustomSpinnerAdapter spinAdapter = new CustomSpinnerAdapter(
            getApplicationContext(), list);
    spinner_nav.setAdapter(spinAdapter);

    spinner_nav.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

      @Override
      public void onItemSelected(AdapterView<?> adapter, View v,
                                 int position, long id) {
        // On selecting a spinner item
        String item = adapter.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(getApplicationContext(), "Selected  : " + item,
                Toast.LENGTH_LONG).show();
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

      }
    });

  }*/
}