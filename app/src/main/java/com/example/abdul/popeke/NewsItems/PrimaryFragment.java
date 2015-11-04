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

import com.example.abdul.popeke.NewsItems.utils.NewsAdapter;
import com.example.abdul.popeke.NewsItems.utils.NewsInfo;
import com.example.abdul.popeke.R;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by Ratan on 7/29/2015.
 */
public class PrimaryFragment extends Fragment {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    private List<NewsInfo> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.primary_layout,null);
        recyclerView =(RecyclerView)rootView.findViewById(R.id.rvPrimary);
        adapter =  new NewsAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       recyclerView.setItemAnimator(new SlideInLeftAnimator());
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

        int [] icons = {R.drawable.strathmore, R.drawable.strathmore , R.drawable.pope_francis, R.drawable.pope_francis};

        String [] desc = new String[]  {getString(R.string.program_one),getString(R.string.program_one),
                getString(R.string.program_one),getString(R.string.program_one)};

        String []  title =  getResources().getStringArray(R.array.title_array);


        for ( int  i  = 0 ;  i < icons.length  && i < desc.length; i++ ) {
           NewsInfo current  =  new NewsInfo() ;
            current.image = icons[i];
            current.title = title[i];
            current.content= desc[i];

            data.add(current);


        }
        return data;
        // return data;
    }
}