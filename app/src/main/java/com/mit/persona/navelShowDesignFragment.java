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
public class navelShowDesignFragment extends Fragment {

    private RecyclerView myRecycleView0;

    private List<Events> arts = Persona.myAppDatabase.myDao().getEvents("MANET & Institute of Design");

    public navelShowDesignFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navel_show_design, container, false);

        LinearLayoutManager layoutManager0= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);


        myRecycleView0 = (RecyclerView) view.findViewById(R.id.manat_and_design);
        RecyclerViewAdapter recyclerViewAdapter8 = new RecyclerViewAdapter(getContext(), arts);
        myRecycleView0.setLayoutManager(layoutManager0);
        myRecycleView0.setAdapter(recyclerViewAdapter8);

        return view;
    }

}
