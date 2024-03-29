package com.mit.persona;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class managementFragment extends Fragment {



    private RecyclerView myRecycleView0;

    private List<Events> arts = Persona.myAppDatabase.myDao().getEvents("Management & Other Events");


    public managementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_management, container, false);

        LinearLayoutManager layoutManager0= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);


        myRecycleView0 = (RecyclerView) view.findViewById(R.id.management_Cards);
        RecyclerViewAdapter recyclerViewAdapter8 = new RecyclerViewAdapter(getContext(), arts);
        myRecycleView0.setLayoutManager(layoutManager0);
        myRecycleView0.setAdapter(recyclerViewAdapter8);

        return view;
    }

}
