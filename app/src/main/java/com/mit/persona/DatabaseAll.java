package com.mit.persona;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class DatabaseAll extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_all);

        TextView a = findViewById(R.id.textView4);

        List<Events> events = Persona.myAppDatabase.myDao().getEvents();

        String info = "";

        for(Events events1: events) {
            String id = events1.getId();
            String name = events1.getName();
            String desc = events1.getDescription();
            String venue = events1.getVenue();
            String date = events1.getDate();
            String time = events1.getTime();

            info += "\n\n" + "ID: " + id + "\nName: " + name + "\nDesc: " + desc + "\nVenue: " + venue + "\nDate: " + date + "\nTime: " + time;
        }

        a.setText(info);

    }
}
