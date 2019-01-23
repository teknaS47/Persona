package com.mit.persona;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class registeredEvents extends AppCompatActivity {

    private static String tmpemail;
    private static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        ctx = this;


        String tempEmail = pageDetails.username;
        tmpemail = tempEmail;
        String email_url = "http://139.59.82.57:5000/event_registrations?where={" + "\"username\"" + ":\"" + tempEmail + "\"}";

        Log.e("email_url", email_url);
        Log.e("eventsFound", String.valueOf(pageDetails.eventsFound));

        pageDetails.volunteer_userEmail = tmpemail;
        databaseOperations.fetchEventRegister(this,email_url);




    }


    public static void displayEvents(registeredEvents view, final ArrayList<String> displayRegisteredEvent, boolean eventsFound) {

        if(!eventsFound) {
            Log.e("Events called", "Not registered for any events!" );
            Toast.makeText(ctx, "Not registered for any events!", Toast.LENGTH_LONG).show();
        }
        else {
            ArrayAdapter adapter = new ArrayAdapter<String>(ctx, R.layout.volunteer_registered, displayRegisteredEvent);

            ListView listView = view.findViewById(R.id.displayRegisteredEvents);
            listView.setAdapter(adapter);



            Log.e("displayEvents: ", displayRegisteredEvent.toString());

        }


    }



}
