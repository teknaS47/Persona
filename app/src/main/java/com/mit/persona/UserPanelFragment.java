package com.mit.persona;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserPanelFragment extends Fragment {

    private TextView t;
    public static MyAppDatabase myAppDatabase;
    private List<Table_Sessions> session_list;



    public UserPanelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user_panel, container, false);
        TextView signOut = view.findViewById(R.id.signOut);

        LinearLayout header_panel = view.findViewById(R.id.user_info_header);
        TextView user_name = view.findViewById(R.id.user_name);
        TextView user_email = view.findViewById(R.id.user_email);
        TextView user_mobile = view.findViewById(R.id.user_mobile);
        TextView add_volunteers = view.findViewById(R.id.addvolunteer);
        TextView registeredEventsButton = view.findViewById(R.id.registeredEventsButton);

        myAppDatabase = Room.databaseBuilder(view.getContext(), MyAppDatabase.class, "eventdb").allowMainThreadQueries().build();

        if ( !pageDetails.login_successful )  {

            signOut.setVisibility(view.GONE);
            header_panel.setVisibility(view.GONE);
            registeredEventsButton.setVisibility(View.GONE);
        }
        else {
            registeredEventsButton.setVisibility(View.GONE);
//            registeredEventsButton.setVisibility(View.VISIBLE);
            registeredEventsButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(view.getContext(), registeredEvents.class ));
                }
            });

            Log.e("USER TYPE: ", String.valueOf(pageDetails.user_type) );
            if (pageDetails.user_type > 1 ) {
                add_volunteers.setVisibility(view.GONE);
            }
            else if (pageDetails.user_type == 1 || pageDetails.user_type == 0) {
                add_volunteers.setVisibility(view.VISIBLE);
                add_volunteers.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        pageDetails.user_info = null;
                        startActivity(new Intent(view.getContext(), teacher_coordinator.class ));
                    }
                });
            }
            user_name.setText(String.valueOf(pageDetails.firstname + " " + pageDetails.lastname));
            user_email.setText(String.valueOf(pageDetails.username));
            user_mobile.setText(String.valueOf(pageDetails.mobile));
        }

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Log out Successful", Toast.LENGTH_SHORT).show();
                myAppDatabase.myDao().clearSessionTable();
                myAppDatabase.myDao().clearRegisteredEventsTable();
                session_list = myAppDatabase.myDao().getsession();
                pageDetails.firstname = null;
                pageDetails.lastname = null;
                pageDetails.mobile = null;
                pageDetails.username = null;
                pageDetails.user_type = null;
                Log.e("Session size:", String.valueOf(session_list.size()));
                pageDetails.login_successful = false;
                startActivity(new Intent(view.getContext(), loginActivity.class));
            }
        });

        TextView bulkregi = view.findViewById(R.id.bulkregi);

        bulkregi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(view.getContext(), eventRegistration.class));
            }
        });

        LinearLayout useredit = view.findViewById(R.id.user_info_header);


        useredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(view.getContext(), UserPanelEdit.class));
            }
        });

        CardView aboutdevelopercard = view.findViewById(R.id.aboutdevelopercard);

        aboutdevelopercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(view.getContext(), AboutDevelopers.class));
            }
        });

        return view;
    }

}
