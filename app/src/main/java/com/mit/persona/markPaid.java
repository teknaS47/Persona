package com.mit.persona;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class markPaid extends AppCompatActivity {

    private static Context ctx;
    private static String tmpemail;
    static LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_paid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ctx = this;
        ll = (LinearLayout) findViewById(R.id.myLinearLayout);
        }
    private static boolean isValidMailstatic(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static void continueSearch()
    {
        if(pageDetails.eventsFound == false){
            Toast toast = Toast.makeText(ctx,
                    "No registered event found",
                    Toast.LENGTH_SHORT);
            toast.show();
        }

        if(isValidMailstatic(tmpemail) == true) {

            Button myButton = new Button(ctx);
            myButton.setText("Push Me");


            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButton, lp);


        }
        Log.d("findRegisteredEvents: ",""+pageDetails.registeredEvent);
    }
    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public void findRegisteredEvents(View view) {

        EditText email = (EditText) findViewById(R.id.findEmailed);
        String tempEmail = email.getText().toString();
        tmpemail = tempEmail;
        String email_url = "http://139.59.82.57:5000/event_registrations?where={" + "\"username\"" + ":\"" + tempEmail + "\"}";

        Log.e("email_url", email_url);
        Log.e("eventsFound", String.valueOf(pageDetails.eventsFound));
        if (isValidMail(tempEmail) == false) {
            Toast.makeText(this, "Enter a valid Email-id", Toast.LENGTH_SHORT).show();
            return;
        }else{
            databaseOperations.fetchEventData(this,email_url);
        }



    }
}
