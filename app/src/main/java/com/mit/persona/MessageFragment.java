package com.mit.persona;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private RecyclerView myRecycleView;
    private ListView listView;

    private List<Table_Messages> message = Persona.myAppDatabase.myDao().getMessages();

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e("NOTIFICATIONS: ",  String.valueOf(message.size()));

        View view = inflater.inflate(R.layout.fragment_message, container, false);

        listView = (ListView) view.findViewById(R.id.message_list);
        ListViewCustomAdapter listViewCustomAdapter= new ListViewCustomAdapter(getActivity(), message);
        listView.setAdapter(listViewCustomAdapter);

        return view;
    }

}
