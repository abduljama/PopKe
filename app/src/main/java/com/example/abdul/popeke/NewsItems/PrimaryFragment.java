package com.example.abdul.popeke.NewsItems;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdul.popeke.NewsItems.utils.HandleXml;
import com.example.abdul.popeke.NewsItems.utils.NewsAdapter;
import com.example.abdul.popeke.NewsItems.utils.NewsInfo;
import com.example.abdul.popeke.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratan on 7/29/2015.
 */
public class PrimaryFragment extends Fragment {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    private List<NewsInfo> data;
    private HandleXml obj;
    private String finalUrl="http://www.catholicnews.com/rss/cns-top-story.xml";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.primary_layout,null);
        recyclerView =(RecyclerView)rootView.findViewById(R.id.rvPrimary);
        adapter =  new NewsAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

      // recyclerView.setItemAnimator(new SlideInLeftAnimator());
        //recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f));

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        return rootView;


    }

    public List<NewsInfo> getData() {
        data = new ArrayList<>() ;
        //  List<ProgramItems> data = new ArrayList<>() ;

         int size = 10  ;
        String []  title = new  String [size];
        int [] icons = new int [size] ;
        String [] desc = new String[size];
        obj = new HandleXml(finalUrl);
        obj.fetchXML();



        while(obj.parsingComplete) {

            for (int i = 0; i < title.length ; i++) {
                title[i] = obj.getTitle();

                desc[i] = obj.getDescription();
                icons[i] = R.drawable.pope_francis;
                size++;
            }
        }

        for ( int  i  = 0 ;   i < desc.length; i++ ) {

            NewsInfo current = new NewsInfo();
           current.image = icons[i];
            current.title = title[i];
            current.content = desc[i];

            data.add(current);


        }
       // System.out.print(Arrays.toString(title)+"\n");
      //  System.out.print((desc.length) + "\n");
        return data;
        // return data;
    }
}