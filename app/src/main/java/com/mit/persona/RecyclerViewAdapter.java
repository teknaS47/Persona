package com.mit.persona;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

        try {
            String imagename = eventsList.get(i).getId().toLowerCase();;
            int res = mContext.getResources().getIdentifier(imagename, "drawable", mContext.getPackageName());
            //myViewHolder.e_img.setImageResource(res);
            Picasso.get().load(res).fit().into(myViewHolder.e_img);



        } catch (Exception e) {
            Log.e("Image Set:", e.toString());
        }
        //    myViewHolder.img.setImageResource(eventsList.get(i).getName());
    }

    @Override
    public int getItemCount() {

        return eventsList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView e_name;
        private TextView e_date;
        private ImageView e_img;
        private CardView cardView;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            e_name = (TextView) itemView.findViewById(R.id.event_name);
            e_date = (TextView) itemView.findViewById(R.id.event_date);
            e_img = itemView.findViewById(R.id.event_img);





    //        img = (TextView) itemView.findViewById(R.id.event_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(),Event.class);
                    i.putExtra("ID",getAdapterPosition());
                    i.putExtra("e_name",eventsList.get(getAdapterPosition()).getName());
                    i.putExtra("e_desc",eventsList.get(getAdapterPosition()).getDescription());
                    i.putExtra("e_date",eventsList.get(getAdapterPosition()).getDate());
                    i.putExtra("e_type",eventsList.get(getAdapterPosition()).getType());
                    i.putExtra("e_venue",eventsList.get(getAdapterPosition()).getVenue());
                    i.putExtra("e_category",eventsList.get(getAdapterPosition()).getCategory());
                    i.putExtra("prize_1_amt",eventsList.get(getAdapterPosition()).getE_1_prize());
                    i.putExtra("prize_2_amt",eventsList.get(getAdapterPosition()).getE_2_prize());
                    i.putExtra("prize_3_amt",eventsList.get(getAdapterPosition()).getE_3_prize());
                    i.putExtra("e_venue",eventsList.get(getAdapterPosition()).getVenue());
                    i.putExtra("event_e_staff_1",eventsList.get(getAdapterPosition()).getEvent_e_staff_1());
                    i.putExtra("event_e_staff_1_email",eventsList.get(getAdapterPosition()).getEvent_e_staff_1_email());
                    i.putExtra("event_e_staff_1_phone",eventsList.get(getAdapterPosition()).getEvent_e_staff_1_phone());
                    i.putExtra("event_e_staff_2",eventsList.get(getAdapterPosition()).getEvent_e_staff_2());
                    i.putExtra("event_e_staff_2_email",eventsList.get(getAdapterPosition()).getEvent_e_staff_2_email());
                    i.putExtra("event_e_staff_2_phone",eventsList.get(getAdapterPosition()).getEvent_e_staff_2_phone());
                    i.putExtra("event_e_student_1",eventsList.get(getAdapterPosition()).getEvent_e_student_1());
                    i.putExtra("event_e_student_1_email",eventsList.get(getAdapterPosition()).getEvent_e_student_1_email());
                    i.putExtra("event_e_student_1_phone",eventsList.get(getAdapterPosition()).getEvent_e_student_1_phone());
                    i.putExtra("event_e_student_2",eventsList.get(getAdapterPosition()).getEvent_e_student_2());
                    i.putExtra("event_e_student_2_email",eventsList.get(getAdapterPosition()).getEvent_e_student_2_email());
                    i.putExtra("event_e_student_2_phone",eventsList.get(getAdapterPosition()).getEvent_e_student_2_phone());
                    i.putExtra("event_e_whatsappLink",eventsList.get(getAdapterPosition()).getEvent_e_whatsappLink());
                    i.putExtra("event_e_likes",eventsList.get(getAdapterPosition()).getEvent_e_likes());
                    i.putExtra("e_rules",eventsList.get(getAdapterPosition()).getE_rules());

                    itemView.getContext().startActivity(i);
                }
            });


        }
    }
}
