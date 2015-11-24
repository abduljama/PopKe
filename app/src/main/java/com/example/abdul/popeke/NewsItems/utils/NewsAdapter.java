package com.example.abdul.popeke.NewsItems.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdul.popeke.R;
import com.example.abdul.popeke.YouTube.CustomLightBoxActivity;

import java.util.Collections;
import java.util.List;

/**
 * Created by abdul on 11/4/15.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>  {

public LayoutInflater inflater;
private Context context;
    FragmentManager mFragmentManager;

        List<NewsInfo> data =  Collections.emptyList();

public NewsAdapter(Context context, List<NewsInfo> data  ) {
        inflater = LayoutInflater.from(context);
        this.data = data ;


        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.d("Method One ","Oncreate  ViewHolder  has  been  called");
        View view =  inflater.inflate(R.layout.news_layout,parent,false );
        MyViewHolder holder =  new MyViewHolder(view);
        return holder;
        }

@Override
public void onBindViewHolder(MyViewHolder holder, final int position) {
        //      Log.d("Method two ", "OnBindViewHolder has been  Called ");
       NewsInfo current = data.get(position);
        holder.title.setText(current.title);
        holder.conent.setText(current.content);
        holder.image.setImageResource(current.image);



        }


@Override
public int getItemCount() {
        return data.size();
        }



class MyViewHolder extends RecyclerView.ViewHolder {
    TextView title, conent;
    ImageView image;


    public MyViewHolder(View itemView) {
        super(itemView);

        image = (ImageView) itemView.findViewById(R.id.image);
        title = (TextView)itemView.findViewById(R.id.nTitle);
        conent =(TextView)itemView.findViewById(R.id.news_content);



    }


}
}
