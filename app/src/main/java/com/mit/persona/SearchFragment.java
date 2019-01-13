package com.mit.persona;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    List<Events> eventsList = Persona.myAppDatabase.myDao().listEvents();


    public SearchFragment() {
        // Required empty public constructor
    }


    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_search, container, false);


        listView = view.findViewById(R.id.listView);

        editText = view.findViewById(R.id.txtsearch);

        String[] myArray = new String[eventsList.size()];

        for (int i=0;i<eventsList.size();i++)
        {
            myArray[i] = eventsList.get(i).getName();

        }

        items = myArray;

        listItems = new ArrayList<>(Arrays.asList(myArray));

        adapter = new ArrayAdapter<>(view.getContext(), R.layout.list_items, R.id.txtitem, myArray);

        listView.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equals("")){
                    String[] myArray = new String[eventsList.size()];

                    for (int i=0;i<eventsList.size();i++)
                    {
                        myArray[i] = eventsList.get(i).getName();
                    }

                    items = myArray;

                    listItems = new ArrayList<>(Arrays.asList(myArray));

                    adapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_items,R.id.txtitem,listItems);
                    listView.setAdapter(adapter);




                }
                else searchItem(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        return view;
    }

    public void searchItem(String textToSearch){
        for (String item:items){
            if (!item.contains(textToSearch)){
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

}