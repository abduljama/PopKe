package com.example.abdul.popeke;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by abdul on 11/21/15.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

       // ParseCrashReporting.enable(this);

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "QjpXM7TaA5v0f6JpnETITmFH5srVG7fr9VJKgBgM",
                "dmULcfLUq8GMSHCw3I4uwb4MW4MLP7fZ0KrgNkx3");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}