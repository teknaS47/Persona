package com.mit.persona;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        myAppDatabase = Room.databaseBuilder(view.getContext(), MyAppDatabase.class, "eventdb").allowMainThreadQueries().build();

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Log out Successful", Toast.LENGTH_SHORT).show();
                myAppDatabase.myDao().clearSessionTable();
                session_list = myAppDatabase.myDao().getsession();
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

        return view;
    }

}
