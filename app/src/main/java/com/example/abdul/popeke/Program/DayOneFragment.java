package com.example.abdul.popeke.Program;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdul.popeke.Program.utils.ProgramAdapter;
import com.example.abdul.popeke.Program.utils.ProgramItems;
import com.example.abdul.popeke.R;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class DayOneFragment extends Fragment {
    RecyclerView  recyclerView;
    ProgramAdapter adapter;
    private List<ProgramItems> data;


    public DayOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView =inflater.inflate(R.layout.fragment_dayone, container, false);

        recyclerView =(RecyclerView)rootView.findViewById(R.id.rv1);
        adapter =  new ProgramAdapter(getActivity() ,getData() );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      //  recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));


        return rootView;
    }


    public List<ProgramItems> getData() {
          data = new ArrayList<>() ;
      //  List<ProgramItems> data = new ArrayList<>() ;

        int [] icons = {R.drawable.depature, R.drawable.pope_in_car, R.drawable.pope_francis, R.drawable.statehouse};


        String [] desc = new String[]  {getString(R.string.program_one),getString(R.string.program_one),
                getString(R.string.program_one),getString(R.string.program_one)};

        String []  title =  getResources().getStringArray(R.array.title_array);
        String []  time  =  getResources().getStringArray(R.array.time_array);
        String []  venue  =  getResources().getStringArray(R.array.venue_array);

        for ( int  i  = 0 ;  i < icons.length  && i < desc.length; i++ ) {
            ProgramItems    current  =  new ProgramItems() ;
            current.pImage = icons[i];
            current.pTitle= title[i];
            current.pLocation = venue[i];
current.pDirection="Show Direction";
            current.pTime =  time[i];
            data.add(current);


        }
        return data;
       // return data;
    }
}
