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
import android.view.MenuItem;

import com.example.abdul.popeke.AboutFragment;
import com.example.abdul.popeke.DonateFragment;
import com.example.abdul.popeke.GalleryFragment;
import com.example.abdul.popeke.HymnsFragment;
import com.example.abdul.popeke.MapsFragment;
import com.example.abdul.popeke.PrayersFragment;
import com.example.abdul.popeke.Program.ProgramFragment;
import com.example.abdul.popeke.R;
import com.example.abdul.popeke.RegisterFragment;
import com.example.abdul.popeke.SettingsFragment;
import com.example.abdul.popeke.NewsItems.TabFragment;


public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                if (menuItem.getItemId() == R.id.nav_item_gallery) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new GalleryFragment()).commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_hymns) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new HymnsFragment()).commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_directions) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new MapsFragment()).commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_prayers) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new PrayersFragment()).commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_program) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new ProgramFragment()).commit();
                }
                if (menuItem.getItemId() == R.id.navigation_item_register) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new RegisterFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_item_settings) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new SettingsFragment()).commit();
                }
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

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }
}
