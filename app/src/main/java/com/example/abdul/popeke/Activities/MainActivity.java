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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abdul.popeke.AboutFragment;
import com.example.abdul.popeke.DonateFragment;
import com.example.abdul.popeke.NewsItems.TabFragment;

import com.example.abdul.popeke.PrayersFragment;
import com.example.abdul.popeke.Program.ProgramFragment;
import com.example.abdul.popeke.R;
import com.example.abdul.popeke.Registration.RegisterFragment;
import com.example.abdul.popeke.YouTube.YouTubeRecyclerViewFragment;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
  DrawerLayout mDrawerLayout;
  NavigationView mNavigationView;
  FragmentManager mFragmentManager;
  FragmentTransaction mFragmentTransaction;
  Toolbar toolbar;
  Spinner spinner_nav;

    //youtube stuff

    private static final String YOUTUBE_PLAYLIST = "PLWz5rJ2EKKc_XOgcRukSoKKjewFJZrKV0";
    private YouTube mYoutubeDataApi;
    private final GsonFactory mJsonFactory = new GsonFactory();
    private final HttpTransport mTransport = AndroidHttp.newCompatibleTransport();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    toolbar = (Toolbar) findViewById(R.id.toolbar);

    setSupportActionBar(toolbar);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setTitle(R.string.app_name);

    /**
     *Setup the DrawerLayout and NavigationView
     */

    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    mNavigationView = (NavigationView) findViewById(R.id.menu) ;

    /**
     * Lets inflate the very first fragment
     * Here , we are inflating the TabFragment as the first Fragment
     */

    mFragmentManager = getSupportFragmentManager();
    mFragmentTransaction = mFragmentManager.beginTransaction();
    mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
    /**
     * Setup click events on the Navigation View Items.
     */

      //set up youtube

      mYoutubeDataApi = new YouTube.Builder(mTransport, mJsonFactory, null)
              .setApplicationName(getResources().getString(R.string.app_name))
              .build();



    mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(MenuItem menuItem) {
        mDrawerLayout.closeDrawers();



        if (menuItem.getItemId() == R.id.nav_item_about) {

          FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
          xfragmentTransaction.replace(R.id.containerView,new AboutFragment()).commit();
        }
        if (menuItem.getItemId() == R.id.nav_item_donate) {

          FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
          xfragmentTransaction.replace(R.id.containerView,new DonateFragment()).commit();
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

          FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
          xfragmentTransaction.replace(R.id.containerView,new PrayersFragment()).commit();
        }
        if (menuItem.getItemId() == R.id.nav_item_program) {

          FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
          xfragmentTransaction.replace(R.id.containerView,new ProgramFragment()).commit();
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.containerView, YouTubeRecyclerViewFragment.newInstance(mYoutubeDataApi, YOUTUBE_PLAYLIST))
//                    .commit();
        }
        if (menuItem.getItemId() == R.id.navigation_item_register) {


          FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
          xfragmentTransaction.replace(R.id.containerView,new RegisterFragment()).commit();
          //  addItemsToSpinner();

          /** if (toolbar != null) {
           setSupportActionBar(toolbar);
           getSupportActionBar().setDisplayShowTitleEnabled(false);
           getSupportActionBar().setHomeButtonEnabled(true);

           }
           addItemsToSpinner();
           **/
        }

             /*   if (menuItem.getItemId() == R.id.nav_item_settings) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new SettingsFragment()).commit();
                }
                */
        if (menuItem.getItemId() == R.id.nav_item_media) {
          FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
          xfragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
        }


        return false;
      }

    });

    /**
     * Setup Drawer Toggle of the Toolbar
     */


    ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
            R.string.app_name);


    mDrawerLayout.setDrawerListener(mDrawerToggle);

    mDrawerToggle.syncState();

  }
  private void addItemsToSpinner() {

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

  }
}