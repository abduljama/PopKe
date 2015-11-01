package com.example.abdul.popeke.Program.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdul.popeke.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by abdul on 11/1/15.
 */
public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.MyViewHolder>  {

    public LayoutInflater inflater;
    private Context context;

    List<ProgramItems> data =  Collections.emptyList();

    public ProgramAdapter(Context context, List<ProgramItems> data  ) {
        inflater = LayoutInflater.from(context);
        this.data = data ;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.d("Method One ","Oncreate  ViewHolder  has  been  called");
        View view =  inflater.inflate(R.layout.program_items,parent,false );
        MyViewHolder holder =  new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //      Log.d("Method two ", "OnBindViewHolder has been  Called ");
        ProgramItems current = data.get(position);
        holder.pTitle.setText(current.pTitle);
        holder.pTime.setText("Time : "+ current.pTime);
        holder.location.setText("Venue : " + current.pLocation);
        holder.pDesc.setText(current.pDesc);
        holder.image.setImageResource(current.pImage);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView location , pDesc , pTime ,pTitle;
        ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);

            pTitle = (TextView)itemView.findViewById(R.id.pTitle);
            location = (TextView)itemView.findViewById(R.id.pVenue);

            pDesc= (TextView)itemView.findViewById(R.id.program_desc);

            pTime = (TextView)itemView.findViewById(R.id.time);
            image = (ImageView) itemView.findViewById(R.id.image);



        }


    }
}
