package com.mit.persona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        TextView eventDetails = (TextView) findViewById(R.id.textView6);
        eventDetails.setText(pageDetails.eventDetails);
        TextView date = (TextView) findViewById(R.id.textView5);
        date.setText(pageDetails.eventDate);

    }
}
