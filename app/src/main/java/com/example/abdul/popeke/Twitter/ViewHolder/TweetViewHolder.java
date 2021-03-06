package com.example.abdul.popeke.Twitter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.abdul.popeke.Twitter.Model.Search;
import com.squareup.picasso.Picasso;

import com.example.abdul.popeke.R;


public class TweetViewHolder extends RecyclerView.ViewHolder {

    private View itemView;

    private TextView username,tweet,name;
    private ImageView userpic;
    private Search reference;


    public TweetViewHolder(View itemView) {
        super(itemView);

        this.itemView=itemView;

        username = (TextView) itemView.findViewById(R.id.userName);
        tweet = (TextView) itemView.findViewById(R.id.tweetStatus);
        userpic = (ImageView) itemView.findViewById(R.id.userimage);
        name = (TextView) itemView.findViewById(R.id.Name);
        tweet.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void update (Search search){
        reference = search;
        username.setText("@"+search.getUser().getScreenName());
        tweet.setText(search.getText());
        name.setText(search.getUser().getName());

        Picasso.with(itemView.getContext()).load(search.getUser().getProfileImageUrl())
                .into(userpic);
    }
}
