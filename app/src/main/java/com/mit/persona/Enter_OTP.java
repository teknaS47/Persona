package com.mit.persona;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Enter_OTP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter__otp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void checkotp(View view) {

        EditText enteredOtp = (EditText)findViewById(R.id.editText);
        String currentOtp = enteredOtp.getText().toString();
        Log.d("OTP generated",""+pageDetails.otp);
        if(currentOtp.equals(pageDetails.otp)){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You've been successfully registered!!",
                    Toast.LENGTH_SHORT);
            toast.show();
            databaseOperations.register(this);
            Intent i = new Intent(this,loginActivity.class);
            startActivity(i);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "OTP MAtch failed please try again",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
