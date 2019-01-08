package com.mit.persona;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        TextView event_name = findViewById(R.id.event_name);
        TextView event_desc = findViewById(R.id.event_desc);
        TextView event_date = findViewById(R.id.event_date);

        event_desc.setText(getIntent().getStringExtra("e_desc"));
        event_date.setText(getIntent().getStringExtra("e_date"));
        event_name.setText(getIntent().getStringExtra("e_name"));




    }
}
