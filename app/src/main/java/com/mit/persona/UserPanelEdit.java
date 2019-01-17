package com.mit.persona;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class UserPanelEdit extends AppCompatActivity {

    public static Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel_edit);

        EditText firstnameedit = findViewById(R.id.firstname);
        firstnameedit.setText(pageDetails.firstname);

        EditText lastname = findViewById(R.id.lastname);
        lastname.setText(pageDetails.lastname);

        EditText email = findViewById(R.id.email);
        email.setText(pageDetails.username);

        EditText reg_phno = findViewById(R.id.phoneno);
        reg_phno.setText(pageDetails.mobile);

        EditText college_name = findViewById(R.id.college_name);
        college_name.setText(pageDetails.college);

        EditText branch = findViewById(R.id.branch);
        branch.setText(pageDetails.branch);

    }
    public void editUserDetails(View view){

        EditText fname = findViewById(R.id.firstname);
        EditText lname = findViewById(R.id.lastname);
        EditText phoneno = findViewById(R.id.phoneno);
        EditText collegename = findViewById(R.id.college_name);
        EditText branch = findViewById(R.id.branch);


        String s = String.valueOf(fname.getText());

        Log.e("TESTING VAR",s);
        String user_url = "http://139.59.82.57:5000/users?where={" + "\"email\"" + ":\"" + pageDetails.username + "\"}";

        databaseOperations.userEditFetch(this, user_url, pageDetails.username);
    }

    public void changeInfo(String etag, String URL_users){

        EditText fname = findViewById(R.id.firstname);
        EditText lname = findViewById(R.id.lastname);
        EditText phoneno = findViewById(R.id.phoneno);
        EditText collegename = findViewById(R.id.college_name);
        EditText branch = findViewById(R.id.branch);

        String fname1 = fname.getText().toString();
        String lname1 = lname.getText().toString();
        String phone1 = phoneno.getText().toString();
        String collegename1 = collegename.getText().toString();
        String branch1 = branch.getText().toString();

        databaseOperations.updateUserInfo(this, etag, URL_users,fname1,lname1,phone1,collegename1,branch1);



    }








}
