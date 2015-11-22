package com.example.abdul.popeke.YouTube;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.abdul.popeke.R;

/**
 * Created by kiprop on 11/22/15.
 */
public class YouTubeFragmentActivity extends ActionBarActivity {

    public static final String KEY_VIDEO_ID = "KEY_VIDEO_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.youtube_fragment_activity);

        final Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey(KEY_VIDEO_ID)) {
            final String videoId = bundle.getString(KEY_VIDEO_ID);
            final YouTubeFragment fragment = (YouTubeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_youtube);
            fragment.setVideoId(videoId);
        }

    }
}