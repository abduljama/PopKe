package com.example.abdul.popeke.NewsItems;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

import com.example.abdul.popeke.Activities.MainActivity;
import com.example.abdul.popeke.Prayers.ExpandbleListAdapter;
import com.example.abdul.popeke.R;
import com.example.abdul.popeke.Twitter.List.Searches;
import com.example.abdul.popeke.Twitter.List.UserList;
import com.example.abdul.popeke.Twitter.Main.Authenticated;
import com.example.abdul.popeke.Twitter.Model.SearchResults;
import com.example.abdul.popeke.Twitter.Model.Wrapper;
import com.example.abdul.popeke.Twitter.ViewHolder.TweetViewHolder;
import com.example.abdul.popeke.Twitter.ViewHolder.UserViewHolder;
import com.example.abdul.popeke.YouTube.YouTubeRecyclerViewFragment;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;


/**
 * Created by kiprop on 11/21/15.
 */
public class TwitterFragmentAllMentions extends Fragment{



    public RecyclerView list;
    public RecyclerView listtitle;


    public Searches searches;
    public UserList users;
    ProgressDialog pd;
    String Key = null;
    String Secret = null;
    private SharedPreferences preferences;
    private Wrapper w;

    FragmentTransaction mFragmentTransaction;

    //prayers listview stuff
    ExpandbleListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    Context c;
    View view, viewlltweets , viewllmentions;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setRetainInstance(true);
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_prayers, null);
        view = inflater.inflate(R.layout.twitter_activity_main, container, false);
        viewlltweets=inflater.inflate(R.layout.twitter_activity_all_tweets, container, false);
        viewllmentions=inflater.inflate(R.layout.twitter_activity_mentions, container, false);




        //listtitle = (RecyclerView) view.findViewById(R.id.listtitle);
//        list = (RecyclerView) view.findViewById(R.id.listallmentions);
//
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        list.setLayoutManager(layoutManager);















        //ends here


        Key = getStringFromManifest("CONSUMER_KEY");
        Secret = getStringFromManifest("CONSUMER_SECRET");



        //listtitle = (RecyclerView) view.findViewById(R.id.listtitle);
        list = (RecyclerView) viewllmentions.findViewById(R.id.listallmentions);

        final LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(layoutManager2);


        downloadSearches();


//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                search.setCursorVisible(true);
//            }
//        });

        return viewllmentions;

    }

    private String getStringFromManifest(String key) {
        String results = null;

        try {
            Context context = getActivity().getBaseContext();
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            results = (String) ai.metaData.get(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    // download twitter searches after first checking to see if there is a network connection
    public void downloadSearches() {
        ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            //pass the official hashtag to the method
            new DownloadTwitterTask().execute("@popeinkenya");
        } else {
            Log.v("Network", "No network connection available.");
            Snackbar.make(getView(), "No Network Connectivity", Snackbar.LENGTH_SHORT).show();
        }
    }

    // Use an AsyncTask to download data from Twitter
    private class DownloadTwitterTask extends AsyncTask<String, Void, Wrapper> {
        final static String TwitterTokenURL = "https://api.twitter.com/oauth2/token";
        final static String TwitterUserURL = "https://api.twitter.com/1.1/users/show.json?screen_name=";
        final static String TwitterSearchURL = "https://api.twitter.com/1.1/search/tweets.json?count=100&q=";
        final static String TwitterUserMentionsURL = "https://api.twitter.com/1.1/statuses/mentions_timeline.json?count=100&screen_name=";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading Media Feed");
            pd.show();
        }

        @Override
        protected Wrapper doInBackground(String... searchTerms) {

            w = new Wrapper();


            if (searchTerms.length > 0) {
                if (searchTerms[0].substring(0, 1).equals("@")) {
                    //w.userResult = getUserSearchStream(searchTerms[0]);
                    w.tweetResult = getSearchStream(searchTerms[0]);
                } else
                    w.tweetResult = getSearchStream(searchTerms[0]);
            }

            return w;
        }

        // onPostExecute convert the JSON results into a Twitter object (which is an Array list of tweets
        @Override
        protected void onPostExecute(Wrapper w) {

            if (pd != null) {
                pd.dismiss();
            }




            if (w.userResult != null) {
                users = jsonToUsers(w.userResult);
            }
            searches = jsonToSearches(w.tweetResult);


            if (w.userResult != null) {
                UserAdapter adapter = new UserAdapter();
                list.setAdapter(adapter);
            } else {
                Adapter adapter = new Adapter();
                list.setAdapter(adapter);
            }

        }


        private UserList jsonToUsers(String result) {

            UserList users = null;
            if (result != null && result.length() > 0) {
                try {
                    Gson gson = new Gson();

                    UserList sr = gson.fromJson(result, UserList.class);

                    users = sr;

                } catch (IllegalStateException ex) {

                }
            }
            return users;
        }

        // converts a string of JSON data into a SearchResults object
        private Searches jsonToSearches(String result) {
            Searches searches = null;

            if (result != null && result.length() > 0) {
                try {
                    Gson gson = new Gson();

                    SearchResults sr = gson.fromJson(result, SearchResults.class);

                    searches = sr.getStatuses();

                } catch (IllegalStateException ex) {
                    ex.printStackTrace();
                }
            }
            return searches;
        }

        // convert a JSON authentication object into an Authenticated object
        private Authenticated jsonToAuthenticated(String rawAuthorization) {
            Authenticated auth = null;
            if (rawAuthorization != null && rawAuthorization.length() > 0) {
                try {
                    Gson gson = new Gson();
                    auth = gson.fromJson(rawAuthorization, Authenticated.class);
                } catch (IllegalStateException ex) {
                    ex.printStackTrace();
                }
            }
            return auth;
        }

        private String getResponseBody(HttpRequestBase request) {
            StringBuilder sb = new StringBuilder();
            try {

                DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
                HttpResponse response = httpClient.execute(request);
                int statusCode = response.getStatusLine().getStatusCode();
                String reason = response.getStatusLine().getReasonPhrase();

                if (statusCode == 200) {

                    HttpEntity entity = response.getEntity();
                    InputStream inputStream = entity.getContent();

                    BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    String line = null;
                    while ((line = bReader.readLine()) != null) {
                        sb.append(line);
                    }
                } else {
                    sb.append(reason);
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
            return sb.toString();
        }

        private String getStream(String url) {
            String results = null;

            // Step 1: Encode consumer key and secret
            try {
                // URL encode the consumer key and secret
                String urlApiKey = URLEncoder.encode(Key, "UTF-8");
                String urlApiSecret = URLEncoder.encode(Secret, "UTF-8");

                // Concatenate the encoded consumer key, a colon character, and the encoded consumer secret
                String combined = urlApiKey + ":" + urlApiSecret;

                // Base64 encode the string
                String base64Encoded = Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);

                // Step 2: Obtain a bearer token
                HttpPost httpPost = new HttpPost(TwitterTokenURL);
                httpPost.setHeader("Authorization", "Basic " + base64Encoded);
                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                httpPost.setEntity(new StringEntity("grant_type=client_credentials"));
                String rawAuthorization = getResponseBody(httpPost);
                Authenticated auth = jsonToAuthenticated(rawAuthorization);

                // Applications should verify that the value associated with the
                // token_type key of the returned object is bearer
                if (auth != null && auth.token_type.equals("bearer")) {

                    // Step 3: Authenticate API requests with bearer token
                    HttpGet httpGet = new HttpGet(url);

                    // construct a normal HTTPS request and include an Authorization
                    // header with the value of Bearer <>
                    httpGet.setHeader("Authorization", "Bearer " + auth.access_token);
                    httpGet.setHeader("Content-Type", "application/json");
                    // update the results with the body of the response
                    results = getResponseBody(httpGet);

                }
            } catch (UnsupportedEncodingException | IllegalStateException ex) {
                ex.printStackTrace();
            }
            return results;
        }

        private String getSearchStream(String searchTerm) {
            String results = null;
            try {
                String encodedUrl = URLEncoder.encode(searchTerm, "UTF-8");
                results = getStream(TwitterSearchURL + encodedUrl);

            } catch (Exception ex) {
                ex.printStackTrace();
                Snackbar.make(getView(), "Whoops!! Something Happened", Snackbar.LENGTH_SHORT).show();
            }

            return results;
        }
        private String getUserSearchStream(String searchTerm) {
            String results = null;
            try {
                String encodedUrl = URLEncoder.encode(searchTerm, "UTF-8");
                results = getStream(TwitterUserURL + encodedUrl);

            } catch (Exception ex) {
                ex.printStackTrace();
                Snackbar.make(getView(), "Whoops!! Something Happened", Snackbar.LENGTH_SHORT).show();
            }

            return ('[' + results + ']');
        }
    }


    public class Adapter extends RecyclerView.Adapter<TweetViewHolder> {

        @Override
        public TweetViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new TweetViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.instance_tweet, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(TweetViewHolder viewHolder, int i) {
            viewHolder.update(searches.get(i));
        }

        @Override
        public int getItemCount() {
            return searches.size();
        }
    }

    public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public static final int USER = 0;
        public static final int TWEET = 1;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case USER:
                    return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.instance_user, parent, false));
                case TWEET:
                    return new TweetViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.instance_tweet, parent, false));
                default:
                    return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            switch (getItemViewType(position)) {

                case USER:
                    ((UserViewHolder) holder).update(users.get(position));
                    break;
                case TWEET:
                    ((TweetViewHolder) holder).update(searches.get(position - users.size()));
            }
        }

        @Override
        public int getItemCount() {
            return (users.size() + searches.size());
        }

        @Override
        public int getItemViewType(int position) {
            if (position == (0)) return USER;
            else return TWEET;
        }
    }



}
