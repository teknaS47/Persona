package com.mit.persona;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class techFragment extends Fragment {
    private RecyclerView myRecycleView0;
    private RecyclerView myRecycleView1;
    private RecyclerView myRecycleView2;
    private RecyclerView myRecycleView3;
    private RecyclerView myRecycleView4;
    private RecyclerView myRecycleView5;




    private List<Events> electronics = Persona.myAppDatabase.myDao().getEvents("Electronics Engineering");
    private List<Events> civil = Persona.myAppDatabase.myDao().getEvents("Civil Engineering");
    private List<Events> mechanical = Persona.myAppDatabase.myDao().getEvents("Mechanical Engineering");
    private List<Events> aerospace = Persona.myAppDatabase.myDao().getEvents("Aerospace Engineering");
    private List<Events> cse = Persona.myAppDatabase.myDao().getEvents("Computer & IT Engineering");
    private List<Events> foootech = Persona.myAppDatabase.myDao().getEvents("Food Technology");
    public techFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tech, container, false);

        LinearLayoutManager layoutManager0= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager4= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager5= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);




        myRecycleView0 = (RecyclerView) view.findViewById(R.id.electronics_Cards);
        RecyclerViewAdapter recyclerViewAdapter8 = new RecyclerViewAdapter(getContext(), electronics);
        myRecycleView0.setLayoutManager(layoutManager0);
        myRecycleView0.setAdapter(recyclerViewAdapter8);

        myRecycleView1 = (RecyclerView) view.findViewById(R.id.civil_Cards);
        RecyclerViewAdapter recyclerViewAdapter9 = new RecyclerViewAdapter(getContext(), civil);
        myRecycleView1.setLayoutManager(layoutManager1);
        myRecycleView1.setAdapter(recyclerViewAdapter9);

        myRecycleView2 = (RecyclerView) view.findViewById(R.id.mechanical_Cards);
        RecyclerViewAdapter recyclerViewAdapter10 = new RecyclerViewAdapter(getContext(), mechanical);
        myRecycleView2.setLayoutManager(layoutManager2);
        myRecycleView2.setAdapter(recyclerViewAdapter10);

        myRecycleView3 = (RecyclerView) view.findViewById(R.id.aerospace_Cards);
        RecyclerViewAdapter recyclerViewAdapter11 = new RecyclerViewAdapter(getContext(), aerospace);
        myRecycleView3.setLayoutManager(layoutManager3);
        myRecycleView3.setAdapter(recyclerViewAdapter11);

        myRecycleView4 = (RecyclerView) view.findViewById(R.id.cse_Cards);
        RecyclerViewAdapter recyclerViewAdapter12 = new RecyclerViewAdapter(getContext(), cse);
        myRecycleView4.setLayoutManager(layoutManager4);
        myRecycleView4.setAdapter(recyclerViewAdapter12);

        myRecycleView5 = (RecyclerView) view.findViewById(R.id.foodTech_Cards);
        RecyclerViewAdapter recyclerViewAdapter13 = new RecyclerViewAdapter(getContext(), foootech);
        myRecycleView5.setLayoutManager(layoutManager5);
        myRecycleView5.setAdapter(recyclerViewAdapter13);

        return view;
    }


}
