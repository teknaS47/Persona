package com.mit.persona;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class markPaid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_paid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public void findRegisteredEvents(View view) {

        EditText email = (EditText) findViewById(R.id.findEmailed);
        String tempEmail = email.getText().toString();
        String email_url = "http://139.59.82.57:5000/event_registrations?where={" + "\"email\"" + ":\"" + tempEmail + "\"}";
        databaseOperations.fetchEventData(this,email_url);
        if (isValidMail(tempEmail) == false) {
            Toast.makeText(this, "Enter a valid Email-id", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pageDetails.eventsFound == false){
            Toast toast = Toast.makeText(this,
                    "No registered event found",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        Log.d("findRegisteredEvents: ",""+pageDetails.registeredEvent);
    }
}
