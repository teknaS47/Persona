package com.mit.persona;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Register extends AppCompatActivity {

    private static Context mContext;

    public static void contilogin() {


        String tmp = pageDetails.user_info;

        String fetchedEmail = "";

        if(tmp== null){
            Log.d("error","returned string is null");
        }
        else {
            Log.d("searched data",""+tmp);
        }

        int startIndex = tmp.indexOf(",\"email\"") + 10;
        int endIndex = tmp.indexOf(",\"mobile")-1;
        try {
            fetchedEmail = pageDetails.user_info.substring(startIndex, endIndex);
        }catch (Exception e){

        }
        //Log.d("fetched password is", "" + startIndex);
        //Log.d("fetched password is", "" + endIndex);
        Log.d("fetched Email is", "" + fetchedEmail);
        Log.d("entered Email is", "" + pageDetails.reg_email);
        try{
            if(pageDetails.reg_email.equals(fetchedEmail)==true){
                Log.d("Email status","Email matched");
                Toast toast = Toast.makeText(mContext,
                        "Email already exist",
                        Toast.LENGTH_SHORT);
                toast.show();

            }else {
                Log.d("Email matching","Email didnt matched new user found");
                databaseOperations.register(mContext);
            }}catch (Exception e){
            e.printStackTrace();
        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = this;
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

        EditText repassword = (EditText) findViewById(R.id.editText11);

        if(pageDetails.reg_password.equals(repassword.getText().toString()))
        {

        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Check the password",
                    Toast.LENGTH_SHORT);
            toast.show();
            return;
        }


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
        if(!pageDetails.reg_firstname.isEmpty() && !pageDetails.reg_lastname.isEmpty() && !pageDetails.reg_email.isEmpty() && !pageDetails.reg_phno.isEmpty()
                && !pageDetails.reg_add .isEmpty() && !pageDetails.reg_dob .isEmpty() && !pageDetails.reg_clgName.isEmpty() && !pageDetails.reg_branch .isEmpty()
                && !pageDetails.reg_clgcity.isEmpty() && !pageDetails.reg_password.isEmpty() && !pageDetails.reg_gender.isEmpty()){
            Log.d("Register Event:", "Detail Verification");
            String entered_Email = "http://139.59.82.57:5000/users?where={" + "\"email\"" + ":\"" + pageDetails.reg_email + "\"}";
            databaseOperations.mailExists(this,entered_Email);
            //databaseOperations.register(this);
        }
        else
        {
            Log.d("data entered","nope");
            Toast toast = Toast.makeText(mContext,
                    "Some value is missing",
                    Toast.LENGTH_SHORT);
            toast.show();

        }

    }

    public void male(View view) {
        pageDetails.reg_gender = "male";

    }

    public void female(View view) {

        pageDetails.reg_gender = "female";
    }
}
