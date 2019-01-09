package com.mit.persona;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loginActivity extends AppCompatActivity /*implements OnClickListener*/{
    static loginActivity instance;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private Button login_btn;
    private TextView email,password;
    private String e,p;
    public static MyAppDatabase myAppDatabase;
    public  loginActivity loginactivity;

    private static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseOperations.updateLocalDB(this);

        /*TextView email = findViewById(R.id.email_text);
        TextView password = findViewById(R.id.password_text);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            email.setText(loginPreferences.getString("e", ""));
            password.setText(loginPreferences.getString("p", ""));
        }
*/

        mContext = this;

        TextView skip_bt = findViewById(R.id.skip);
        skip_bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pageDetails.user_info = null;
                startActivity(new Intent(loginActivity.this, Persona.class ));
            }
        });

        Button t_login = findViewById(R.id.t_login_button);
        t_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pageDetails.user_info = null;
                startActivity(new Intent(loginActivity.this, teacher_coordinator.class ));
            }
        });


    }



    public void gotoregister(View view) {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }
    /*public void gotopersona(View view) {
        Intent i = new Intent(this, Persona.class);
        startActivity(i);
    }
    */

    public void loginprocess(View view) {
        instance = this;
        String entered_Email;
        String entered_Password;
        EditText email_EditText = (EditText) findViewById(R.id.email_text);
        entered_Email = email_EditText.getText().toString();
        EditText password_EditText = (EditText) findViewById(R.id.password_text);
        entered_Password = password_EditText.getText().toString();
        pageDetails.entered_Password = entered_Password;
        if(!entered_Email.isEmpty() && !entered_Password.isEmpty()){
          try {  //find user
              Log.d("finding user for login", "starting finding user");
              entered_Email = "http://139.59.82.57:5000/users?where={" + "\"email\"" + ":\"" + entered_Email + "\"}";
              Log.d("created email query", "" + entered_Email);
              Log.d("searched data", "" + pageDetails.user_info);
              databaseOperations.login(this, entered_Email);

          }catch (Exception e)
          {
              e.printStackTrace();
          }

        }
        else {
            Toast toast = Toast.makeText(mContext,
                    "Enter mail or password",
                    Toast.LENGTH_SHORT);
            toast.show();
            //Toast.makeText(getApplicationContext(),"Enter Password or email ID and try again",Toast.LENGTH_LONG).show();
        }

    }

   /* public void gotopersona()
    {
        Intent i = new Intent(mContext,Persona.class);
        startActivity(i);
    }*/
public static void contilogin()
{

    loginActivity l = new loginActivity();
    String tmp = pageDetails.user_info;

    String fetchedPassword="";

    if(tmp== null){
        Log.d("error","returned string is null");
    }
    else {
        Log.d("searched data",""+tmp);
    }

    int startIndex = tmp.indexOf(",\"password\"") + 13;
    int endIndex = tmp.indexOf(",\"_updated")-1;
    try {
        fetchedPassword = pageDetails.user_info.substring(startIndex, endIndex);
    }catch (Exception e)
    {

    }
    //Log.d("fetched password is", "" + startIndex);
    //Log.d("fetched password is", "" + endIndex);
    Log.d("fetched password is", "" + fetchedPassword);
    Log.d("entered password is", "" + pageDetails.entered_Password);
    try{
    if(pageDetails.entered_Password.equals(fetchedPassword)==true){
        Log.d("password status","password matched going to main page");
        Intent i = new Intent(mContext,Persona.class);
        mContext.startActivity(i);
    }else {
        Log.d("password matching","something went wrong");
    }}catch (Exception e){
        e.printStackTrace();
    }
}



    //private static final String TAG = "loginActivity ###################";
    /*public void onClick(View view) {
        if (view == login_btn) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(email.getWindowToken(), 0);

            e = email.getText().toString();
            p = password.getText().toString();

            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("e", e);
            loginPrefsEditor.putString("p", p);
            loginPrefsEditor.commit();
        }
        next();

    }
    public void next() {
        //Log.d(TAG, "onClick: #############");
        startActivity(new Intent(loginActivity.this, Persona.class));
        loginActivity.this.finish();
    }*/
}
