package com.mit.persona;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void registerevent(View view) {

        Log.d("On Click:", "Register Event");

        EditText firstName = (EditText) findViewById(R.id.editText2);
        pageDetails.reg_firstname = firstName.getText().toString();
        EditText lastName = (EditText) findViewById(R.id.editText12);
        pageDetails.reg_lastname = lastName.getText().toString();
        EditText email = (EditText) findViewById(R.id.editText3);
        pageDetails.reg_email = email.getText().toString();
        EditText phone = (EditText) findViewById(R.id.editText4);
        pageDetails.reg_phno = phone.getText().toString();

        /*
        EditText phone = (EditText) findViewById(R.id.editText4);
        pageDetails.reg_phno = phone.getText().toString();
        */

        EditText add = (EditText) findViewById(R.id.editText5);
        pageDetails.reg_add = add.getText().toString();

        EditText dob = (EditText) findViewById(R.id.editText6);
        pageDetails.reg_dob = dob.getText().toString();

        EditText clgName = (EditText) findViewById(R.id.editText7);
        pageDetails.reg_clgName = clgName.getText().toString();

        EditText branch = (EditText) findViewById(R.id.editText8);
        pageDetails.reg_branch = branch.getText().toString();

        EditText clgCity = (EditText) findViewById(R.id.editText9);
        pageDetails.reg_clgcity = clgCity.getText().toString();

        EditText password = (EditText) findViewById(R.id.editText10);
        pageDetails.reg_password = password.getText().toString();
       /* try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(
                    pageDetails.reg_password.getBytes(StandardCharsets.UTF_8));
            String sha256hex = new String(Hex.encode(hash));
            Log.d("hashed password",sha256hex);

        }catch (Exception e)
        {
            Log.e("error in password hash",e.toString());
        }
*/
        if(pageDetails.reg_firstname!= null && pageDetails.reg_lastname!= null && pageDetails.reg_email!= null && pageDetails.reg_phno!= null
                && pageDetails.reg_add != null && pageDetails.reg_dob != null && pageDetails.reg_clgName != null && pageDetails.reg_branch != null
                && pageDetails.reg_clgcity != null && pageDetails.reg_password != null && pageDetails.reg_gender != null){
            Log.d("Register Event:", "Detail Verification");
            databaseOperations.register(this);
        }

    }

    public void male(View view) {
        pageDetails.reg_gender = "male";

    }

    public void female(View view) {

        pageDetails.reg_gender = "female";
    }
}
