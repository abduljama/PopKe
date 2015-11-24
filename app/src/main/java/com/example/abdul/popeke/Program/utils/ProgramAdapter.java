package com.example.abdul.popeke.Program.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdul.popeke.Activities.MainActivity;
import com.example.abdul.popeke.MapsFragment;
import com.example.abdul.popeke.R;
import com.example.abdul.popeke.TestMapActivity;
import com.example.abdul.popeke.YouTube.CustomLightBoxActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import java.util.Collections;
import java.util.List;

/**
 * Created by abdul on 11/1/15.
 */
public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.MyViewHolder>  {

    public LayoutInflater inflater;
    private Context context;
    FragmentManager mFragmentManager;

    private GoogleMap map;

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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //      Log.d("Method two ", "OnBindViewHolder has been  Called ");
        final  ProgramItems current = data.get(position);
        holder.pTitle.setText(current.pTitle);

//        holder.pDirection.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });

        holder.pTime.setText("Time: " + current.pTime);
        holder.location.setText("Venue: " + current.pLocation);
        holder.pDesc.setText(current.pDesc);
        holder.image.setImageResource(current.pImage);
        holder.pDirection.setText(current.pDirection);


        holder.pDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//here i can either call the light box activity or hii ya youtube fragment activity
                //lakini naona light box inakaa better

               /* Intent intent = new Intent(v.getContext(), YouTubeFragmentActivity.class);
                //you can pass on the Pojo with PARCELABLE
                intent.putExtra(YouTubeFragmentActivity.KEY_VIDEO_ID, video.getId());
                v.getContext().startActivity(intent);*/


                Intent intent = new Intent(v.getContext(), TestMapActivity.class);
                //you can pass on the Pojo with PARCELABLE
//                intent.putExtra(CustomLightBoxActivity.KEY_VIDEO_ID, video.getId());
                switch (current.pLocation){
                    case "Fiumicino airport":
                        intent.putExtra("latitude", "41.8003");
                        intent.putExtra("longitude", "12.2389");
                        break;
                    case "JKIA Nairobi":
                        intent.putExtra("latitude", "-1.322431");

                        intent.putExtra("longitude", "36.926016");
                        break;
                    case "State House, Nairobi":

                        intent.putExtra("latitude", "-1.282407");
                        intent.putExtra("longitude", "36.802295");
                        break;
                    case "Apostolic Nunciature, Nairobi":

                        intent.putExtra("latitude", "-1.266136");
                        intent.putExtra("longitude", "36.767281");
                        break;
                    case "University of Nairobi":

                        intent.putExtra("latitude", "-1.280765");
                        intent.putExtra("longitude", "36.815763");
                        break;
                    case "St. Mary’s School Nairobi":


                        intent.putExtra("latitude", "-1.264143");
                        intent.putExtra("longitude", "36.780113");
                        break;
                    case "UNEP/UNON":
                        intent.putExtra("latitude", "-1.232694");
                        intent.putExtra("longitude", "36.815542");
                        break;
                    case "Kangemi , Nairobi":
                        intent.putExtra("latitude", " -1.13577");
                        intent.putExtra("longitude", "36.4856");
                        break;
                    case "Kasarani Stadium, Nairobi":

                        intent.putExtra("latitude", "-1.228036");
                        intent.putExtra("longitude", "36.890730");
                        break;
                    case "VIP lounge at Kasarani Stadium":
                        intent.putExtra("latitude", "-1.228036");
                        intent.putExtra("longitude", "36.890730");
                        break;


                 default:


                }

                intent.putExtra("location", current.pLocation);
                v.getContext().startActivity(intent);
//            }
//        })
//                ///Toast.makeText(context,"venue is ",Toast.LENGTH_LONG);
//                System.out.println("venue is "+current.pLocation);
//
//            }
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView location , pDesc , pTime ,pTitle , pDirection;
        ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);

            pTitle = (TextView)itemView.findViewById(R.id.pTitle);
            location = (TextView)itemView.findViewById(R.id.pVenue);

            pDirection = (TextView)itemView.findViewById(R.id.showDirection);



            pDesc= (TextView)itemView.findViewById(R.id.program_desc);

            pTime = (TextView)itemView.findViewById(R.id.time);
            image = (ImageView) itemView.findViewById(R.id.image);



        }


    }
}
