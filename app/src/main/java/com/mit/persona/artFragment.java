package com.mit.persona;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class artFragment extends Fragment {

    private RecyclerView myRecycleView0;
    private RecyclerView myRecycleView1;
    private RecyclerView myRecycleView2;

    private List<Events> arts = Persona.myAppDatabase.myDao().getEvents("Arts and Creative Event");
    private List<Events> dance = Persona.myAppDatabase.myDao().getEvents("Dance and Music Event");
    private List<Events> cultural = Persona.myAppDatabase.myDao().getEvents("Cultural Events");


    public artFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_art, container, false);

        LinearLayoutManager layoutManager0= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        myRecycleView0 = (RecyclerView) view.findViewById(R.id.artnCreativeEvents_Cards);
        RecyclerViewAdapter recyclerViewAdapter8 = new RecyclerViewAdapter(getContext(), arts);
        myRecycleView0.setLayoutManager(layoutManager0);
        myRecycleView0.setAdapter(recyclerViewAdapter8);

        myRecycleView1 = (RecyclerView) view.findViewById(R.id.artnCreativeEvents_dance);
        RecyclerViewAdapter recyclerViewAdapter9 = new RecyclerViewAdapter(getContext(), dance);
        myRecycleView1.setLayoutManager(layoutManager1);
        myRecycleView1.setAdapter(recyclerViewAdapter9);

        myRecycleView2 = (RecyclerView) view.findViewById(R.id.artnCreativeEvents_cultural);
        RecyclerViewAdapter recyclerViewAdapter10 = new RecyclerViewAdapter(getContext(), cultural);
        myRecycleView2.setLayoutManager(layoutManager2);
        myRecycleView2.setAdapter(recyclerViewAdapter10);


        // Inflate the layout for this fragment
        return view;
    }

}