package com.mit.persona;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Events> eventsList;

    public RecyclerViewAdapter(Context mContext, List<Events> eventsList) {
        this.mContext = mContext;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.event_card, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.e_name.setText(eventsList.get(i).getName());
        myViewHolder.e_date.setText(eventsList.get(i).getDate());
    //    myViewHolder.img.setImageResource(eventsList.get(i).getName());



    }

    @Override
    public int getItemCount() {

        return eventsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView e_name;
        private TextView e_date;
        private ImageView img;
        private CardView cardView;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            e_name = (TextView) itemView.findViewById(R.id.event_name);
            e_date = (TextView) itemView.findViewById(R.id.event_date);
    //        img = (TextView) itemView.findViewById(R.id.event_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(),Event.class);
                    i.putExtra("ID",getAdapterPosition());
                    i.putExtra("e_name",eventsList.get(getAdapterPosition()).getName());
                    i.putExtra("e_desc",eventsList.get(getAdapterPosition()).getDescription());
                    i.putExtra("e_date",eventsList.get(getAdapterPosition()).getDate());
                    i.putExtra("e_venue",eventsList.get(getAdapterPosition()).getVenue());
                    itemView.getContext().startActivity(i);
                }
            });


        }
    }
}
