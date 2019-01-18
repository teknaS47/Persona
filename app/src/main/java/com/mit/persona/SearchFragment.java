package com.mit.persona;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
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
    String eventName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_search, container, false);


        listView = view.findViewById(R.id.listView);

        editText = view.findViewById(R.id.txtsearch);

        final String[] myArray = new String[eventsList.size()];

        for (int j=0;j<eventsList.size();j++)
        {
            myArray[j] = eventsList.get(j).getName();

        }

        items = myArray;

        listItems = new ArrayList<>(Arrays.asList(myArray));

        adapter = new ArrayAdapter<>(view.getContext(), R.layout.list_items, R.id.txtitem, myArray);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                int temppos = 0;
                Intent i;
                i = new Intent(view.getContext(), Event.class);
                Log.e("event name : ",String.valueOf(position));
                //i.putExtra("ID",position);
                //String number = eventsList.get(position).getId();
                Object obj=listView.getAdapter().getItem(position);
                String str=obj.toString();

                for (int j=0;j<eventsList.size();j++)
                {
                    if (str.equals(myArray[j]))
                    {
                        temppos = j;
                        break;
                    }
                }

                i.putExtra("ID",temppos);
                i.putExtra("e_name",eventsList.get(temppos).getName());
                i.putExtra("e_desc",eventsList.get(temppos).getDescription());
                i.putExtra("e_date",eventsList.get(temppos).getDate());
                i.putExtra("e_type",eventsList.get(temppos).getType());
                i.putExtra("e_venue",eventsList.get(temppos).getVenue());
                i.putExtra("e_category",eventsList.get(temppos).getCategory());
                i.putExtra("prize_1_amt",eventsList.get(temppos).getE_1_prize());
                i.putExtra("prize_2_amt",eventsList.get(temppos).getE_2_prize());
                i.putExtra("prize_3_amt",eventsList.get(temppos).getE_3_prize());
                i.putExtra("e_venue",eventsList.get(temppos).getVenue());
                i.putExtra("event_e_staff_1",eventsList.get(temppos).getEvent_e_staff_1());
                i.putExtra("event_e_staff_1_email",eventsList.get(temppos).getEvent_e_staff_1_email());
                i.putExtra("event_e_staff_1_phone",eventsList.get(temppos).getEvent_e_staff_1_phone());
                i.putExtra("event_e_staff_2",eventsList.get(temppos).getEvent_e_staff_2());
                i.putExtra("event_e_staff_2_email",eventsList.get(temppos).getEvent_e_staff_2_email());
                i.putExtra("event_e_staff_2_phone",eventsList.get(temppos).getEvent_e_staff_2_phone());
                i.putExtra("event_e_student_1",eventsList.get(temppos).getEvent_e_student_1());
                i.putExtra("event_e_student_1_email",eventsList.get(temppos).getEvent_e_student_1_email());
                i.putExtra("event_e_student_1_phone",eventsList.get(temppos).getEvent_e_student_1_phone());
                i.putExtra("event_e_student_2",eventsList.get(temppos).getEvent_e_student_2());
                i.putExtra("event_e_student_2_email",eventsList.get(temppos).getEvent_e_student_2_email());
                i.putExtra("event_e_student_2_phone",eventsList.get(temppos).getEvent_e_student_2_phone());
                i.putExtra("event_e_whatsappLink",eventsList.get(temppos).getEvent_e_whatsappLink());
                i.putExtra("event_e_likes",eventsList.get(temppos).getEvent_e_likes());
                i.putExtra("e_rules",eventsList.get(temppos).getE_rules());
                startActivity(i);

            }
        });


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(s.toString().equals("")){
                    final String[] myArray = new String[eventsList.size()];

                    for (int j=0;j<eventsList.size();j++)
                    {
                        myArray[j] = eventsList.get(j).getName();
                    }

                    items = myArray;

                    listItems = new ArrayList<>(Arrays.asList(myArray));

                    adapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_items,R.id.txtitem,listItems);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                            int temppos = 0;
                            Intent i;
                            i = new Intent(view.getContext(), Event.class);
                            Log.e("event name : ",String.valueOf(position));
                            //i.putExtra("ID",position);
                            //String number = eventsList.get(position).getId();
                            Object obj=listView.getAdapter().getItem(position);
                            String str=obj.toString();

                            for (int j=0;j<eventsList.size();j++)
                            {
                                if (str.equals(myArray[j]))
                                {
                                    temppos = j;
                                    break;
                                }
                            }


                            i.putExtra("ID",temppos);
                            i.putExtra("e_name",eventsList.get(temppos).getName());
                            i.putExtra("e_desc",eventsList.get(temppos).getDescription());
                            i.putExtra("e_date",eventsList.get(temppos).getDate());
                            i.putExtra("e_type",eventsList.get(temppos).getType());
                            i.putExtra("e_venue",eventsList.get(temppos).getVenue());
                            i.putExtra("e_category",eventsList.get(temppos).getCategory());
                            i.putExtra("prize_1_amt",eventsList.get(temppos).getE_1_prize());
                            i.putExtra("prize_2_amt",eventsList.get(temppos).getE_2_prize());
                            i.putExtra("prize_3_amt",eventsList.get(temppos).getE_3_prize());
                            i.putExtra("e_venue",eventsList.get(temppos).getVenue());
                            i.putExtra("event_e_staff_1",eventsList.get(temppos).getEvent_e_staff_1());
                            i.putExtra("event_e_staff_1_email",eventsList.get(temppos).getEvent_e_staff_1_email());
                            i.putExtra("event_e_staff_1_phone",eventsList.get(temppos).getEvent_e_staff_1_phone());
                            i.putExtra("event_e_staff_2",eventsList.get(temppos).getEvent_e_staff_2());
                            i.putExtra("event_e_staff_2_email",eventsList.get(temppos).getEvent_e_staff_2_email());
                            i.putExtra("event_e_staff_2_phone",eventsList.get(temppos).getEvent_e_staff_2_phone());
                            i.putExtra("event_e_student_1",eventsList.get(temppos).getEvent_e_student_1());
                            i.putExtra("event_e_student_1_email",eventsList.get(temppos).getEvent_e_student_1_email());
                            i.putExtra("event_e_student_1_phone",eventsList.get(temppos).getEvent_e_student_1_phone());
                            i.putExtra("event_e_student_2",eventsList.get(temppos).getEvent_e_student_2());
                            i.putExtra("event_e_student_2_email",eventsList.get(temppos).getEvent_e_student_2_email());
                            i.putExtra("event_e_student_2_phone",eventsList.get(temppos).getEvent_e_student_2_phone());
                            i.putExtra("event_e_whatsappLink",eventsList.get(temppos).getEvent_e_whatsappLink());
                            i.putExtra("event_e_likes",eventsList.get(temppos).getEvent_e_likes());
                            i.putExtra("e_rules",eventsList.get(temppos).getE_rules());
                            startActivity(i);

                        }
                    });


                }
                else searchItem(s.toString());

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equals("")){
                    final String[] myArray = new String[eventsList.size()];

                    for (int j=0;j<eventsList.size();j++)
                    {
                        myArray[j] = eventsList.get(j).getName();
                    }

                    items = myArray;

                    listItems = new ArrayList<>(Arrays.asList(myArray));

                    adapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_items,R.id.txtitem,listItems);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                            int temppos = 0;
                            Intent i;
                            i = new Intent(view.getContext(), Event.class);
                            Log.e("event name : ",String.valueOf(position));
                            //i.putExtra("ID",position);
                            //String number = eventsList.get(position).getId();
                            Object obj=listView.getAdapter().getItem(position);
                            String str=obj.toString();

                            for (int j=0;j<eventsList.size();j++)
                            {
                                if (str.equals(myArray[j]))
                                {
                                    temppos = j;
                                    break;
                                }
                            }



                            i.putExtra("ID",temppos);
                            i.putExtra("e_name",eventsList.get(temppos).getName());
                            i.putExtra("e_desc",eventsList.get(temppos).getDescription());
                            i.putExtra("e_date",eventsList.get(temppos).getDate());
                            i.putExtra("e_type",eventsList.get(temppos).getType());
                            i.putExtra("e_venue",eventsList.get(temppos).getVenue());
                            i.putExtra("e_category",eventsList.get(temppos).getCategory());
                            i.putExtra("prize_1_amt",eventsList.get(temppos).getE_1_prize());
                            i.putExtra("prize_2_amt",eventsList.get(temppos).getE_2_prize());
                            i.putExtra("prize_3_amt",eventsList.get(temppos).getE_3_prize());
                            i.putExtra("e_venue",eventsList.get(temppos).getVenue());
                            i.putExtra("event_e_staff_1",eventsList.get(temppos).getEvent_e_staff_1());
                            i.putExtra("event_e_staff_1_email",eventsList.get(temppos).getEvent_e_staff_1_email());
                            i.putExtra("event_e_staff_1_phone",eventsList.get(temppos).getEvent_e_staff_1_phone());
                            i.putExtra("event_e_staff_2",eventsList.get(temppos).getEvent_e_staff_2());
                            i.putExtra("event_e_staff_2_email",eventsList.get(temppos).getEvent_e_staff_2_email());
                            i.putExtra("event_e_staff_2_phone",eventsList.get(temppos).getEvent_e_staff_2_phone());
                            i.putExtra("event_e_student_1",eventsList.get(temppos).getEvent_e_student_1());
                            i.putExtra("event_e_student_1_email",eventsList.get(temppos).getEvent_e_student_1_email());
                            i.putExtra("event_e_student_1_phone",eventsList.get(temppos).getEvent_e_student_1_phone());
                            i.putExtra("event_e_student_2",eventsList.get(temppos).getEvent_e_student_2());
                            i.putExtra("event_e_student_2_email",eventsList.get(temppos).getEvent_e_student_2_email());
                            i.putExtra("event_e_student_2_phone",eventsList.get(temppos).getEvent_e_student_2_phone());
                            i.putExtra("event_e_whatsappLink",eventsList.get(temppos).getEvent_e_whatsappLink());
                            i.putExtra("event_e_likes",eventsList.get(temppos).getEvent_e_likes());
                            i.putExtra("e_rules",eventsList.get(temppos).getE_rules());
                            startActivity(i);

                        }
                    });



                }
                else searchItem(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().equals("")){
                    final String[] myArray = new String[eventsList.size()];




                    for (int j=0;j<eventsList.size();j++)
                    {
                        myArray[j] = eventsList.get(j).getName();


                    }

                    items = myArray;

                    listItems = new ArrayList<>(Arrays.asList(myArray));

                    adapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_items,R.id.txtitem,listItems);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int temppos = 0;
                            Intent i;
                            i = new Intent(view.getContext(), Event.class);
                            Log.e("event name : ",String.valueOf(position));
                            //i.putExtra("ID",position);
                            //String number = eventsList.get(position).getId();
                            Object obj=listView.getAdapter().getItem(position);
                            String str=obj.toString();

                            for (int j=0;j<eventsList.size();j++)
                            {
                                if (str.equals(myArray[j]))
                                {
                                    temppos = j;
                                    break;
                                }
                            }

                            i.putExtra("ID",temppos);
                            i.putExtra("e_name",eventsList.get(temppos).getName());
                            i.putExtra("e_desc",eventsList.get(temppos).getDescription());
                            i.putExtra("e_date",eventsList.get(temppos).getDate());
                            i.putExtra("e_type",eventsList.get(temppos).getType());
                            i.putExtra("e_venue",eventsList.get(temppos).getVenue());
                            i.putExtra("e_category",eventsList.get(temppos).getCategory());
                            i.putExtra("prize_1_amt",eventsList.get(temppos).getE_1_prize());
                            i.putExtra("prize_2_amt",eventsList.get(temppos).getE_2_prize());
                            i.putExtra("prize_3_amt",eventsList.get(temppos).getE_3_prize());
                            i.putExtra("e_venue",eventsList.get(temppos).getVenue());
                            i.putExtra("event_e_staff_1",eventsList.get(temppos).getEvent_e_staff_1());
                            i.putExtra("event_e_staff_1_email",eventsList.get(temppos).getEvent_e_staff_1_email());
                            i.putExtra("event_e_staff_1_phone",eventsList.get(temppos).getEvent_e_staff_1_phone());
                            i.putExtra("event_e_staff_2",eventsList.get(temppos).getEvent_e_staff_2());
                            i.putExtra("event_e_staff_2_email",eventsList.get(temppos).getEvent_e_staff_2_email());
                            i.putExtra("event_e_staff_2_phone",eventsList.get(temppos).getEvent_e_staff_2_phone());
                            i.putExtra("event_e_student_1",eventsList.get(temppos).getEvent_e_student_1());
                            i.putExtra("event_e_student_1_email",eventsList.get(temppos).getEvent_e_student_1_email());
                            i.putExtra("event_e_student_1_phone",eventsList.get(temppos).getEvent_e_student_1_phone());
                            i.putExtra("event_e_student_2",eventsList.get(temppos).getEvent_e_student_2());
                            i.putExtra("event_e_student_2_email",eventsList.get(temppos).getEvent_e_student_2_email());
                            i.putExtra("event_e_student_2_phone",eventsList.get(temppos).getEvent_e_student_2_phone());
                            i.putExtra("event_e_whatsappLink",eventsList.get(temppos).getEvent_e_whatsappLink());
                            i.putExtra("event_e_likes",eventsList.get(temppos).getEvent_e_likes());
                            i.putExtra("e_rules",eventsList.get(temppos).getE_rules());

                            startActivity(i);

                        }
                    });


                }
                else searchItem(s.toString());

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