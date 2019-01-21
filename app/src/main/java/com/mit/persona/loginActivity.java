package com.mit.persona;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.util.List;

public class loginActivity extends AppCompatActivity /*implements OnClickListener*/{
    static loginActivity instance;
    private static List<Table_registeredEvents> registeredEventsList;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private Button login_btn;
    private TextView email,password;
    private String e,p;
    public static MyAppDatabase myAppDatabase;
    public  loginActivity loginactivity;
    private List<Table_Sessions> session;
    private static final boolean VERBOSE = true;
    public static ProgressDialog progress;



    private static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "eventdb").allowMainThreadQueries().build();
        session = myAppDatabase.myDao().getsession();

         progress = new ProgressDialog(loginActivity.this);

        if (session.size() == 1) {
            pageDetails.username = session.get(0).getUsername();
            pageDetails.college = session.get(0).getCollege();
            pageDetails.branch = session.get(0).getBranch();
            pageDetails.firstname = session.get(0).getFirstname();
            pageDetails.lastname = session.get(0).getLastname();
            pageDetails.mobile = session.get(0).getMobile();
            pageDetails.user_type = session.get(0).getUser_type();

            Log.e("Session check: ", "session found");
            pageDetails.login_successful = true;
            startActivity(new Intent(loginActivity.this, Persona.class ));
        }
        else {
            Log.e("Session check: ", "session NOT found");
        }

        databaseOperations.updateLocalDB(this);

        /*try {
            myAppDatabase.myDao().clearSessionTable();K
        }
        catch (Exception e) {
            Log.e("Clear Session Table: ", e.toString());
        }
        Button t_login = findViewById(R.id.t_login_button);
        t_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pageDetails.user_info = null;
                startActivity(new Intent(loginActivity.this, teacher_coordinator.class ));
            }
        });*/
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
                progress.setTitle("Generating Events");
                progress.setMessage("Wait for it...");
                progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                progress.show();
                startActivity(new Intent(loginActivity.this, Persona.class ));
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        databaseOperations.updateLocalDB(this);
        progress.dismiss();
        Log.e("onResume (Session Size)",String.valueOf(session.size()));

        if (session.size() == 1) {

            pageDetails.username = session.get(0).getUsername();
            pageDetails.college = session.get(0).getCollege();
            pageDetails.branch = session.get(0).getBranch();
            pageDetails.firstname = session.get(0).getFirstname();
            pageDetails.lastname = session.get(0).getLastname();
            pageDetails.mobile = session.get(0).getMobile();
            pageDetails.user_type = session.get(0).getUser_type();

            Log.e("Session check: ", "session found");
            pageDetails.login_successful = true;
            startActivity(new Intent(loginActivity.this, Persona.class ));
        }
        else {
            Log.e("Session check: ", "session NOT found");
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void gotoregister(View view) {

        if (isNetworkAvailable()) {
            Intent i = new Intent(this, Register.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Please connect to the internet! :) ", Toast.LENGTH_SHORT).show();
        }


    }
    /*public void gotopersona(View view) {
        Intent i = new Intent(this, Persona.class);
        startActivity(i);
    }
    */
    public void loginprocess(View view) {

        if(!isNetworkAvailable()) {
            Toast.makeText(this, "Please connect to the internet! :) ", Toast.LENGTH_SHORT).show();
        }
        else {

            progress.setTitle("Setting up everything...");
            progress.setMessage("Wait while logging in...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();


            instance = this;
            String entered_Email;
            String entered_Password;
            EditText email_EditText = (EditText) findViewById(R.id.email_text);
            entered_Email = email_EditText.getText().toString();
            pageDetails.entered_Email = entered_Email;
            EditText password_EditText = (EditText) findViewById(R.id.password_text);
            entered_Password = password_EditText.getText().toString();
            pageDetails.entered_Password = entered_Password;
            if (!entered_Email.isEmpty() && !entered_Password.isEmpty()) {
                try {  //find user
                    Log.d("finding user for login", "starting finding user");
                    entered_Email = "http://139.59.82.57:5000/users?where={" + "\"email\"" + ":\"" + entered_Email + "\"}";
                    Log.d("created email query", "" + entered_Email);
                    //Log.d("searched data", "" + pageDetails.user_info);
                    databaseOperations.login(this, entered_Email, pageDetails.entered_Password);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Toast toast = Toast.makeText(mContext,
                        "Enter mail or password",
                        Toast.LENGTH_SHORT);
                toast.show();
                //Toast.makeText(getApplicationContext(),"Enter Password or email ID and try again",Toast.LENGTH_LONG).show();
            }
        }

    }

    public static void FinishLogin(Integer user_type, String username, String firstname, String lastname, String mobile, String college, String branch, String verified_by)    {

        myAppDatabase = Room.databaseBuilder(loginActivity.mContext, MyAppDatabase.class, "eventdb").allowMainThreadQueries().build();
        registeredEventsList = myAppDatabase.myDao().getRegisteredEvents();

        loginActivity l = new loginActivity();
        Log.e("LOGIN_SUCCESSFUL:", String.valueOf(pageDetails.login_successful));
        if (pageDetails.login_successful == true) {
            myAppDatabase.myDao().clearSessionTable();
            myAppDatabase.myDao().clearRegisteredEventsTable();
            Table_Sessions session = new Table_Sessions();
            session.setUsername(username);
            session.setUser_type(user_type);
            session.setBranch(branch);
            session.setCollege(college);
            session.setFirstname(firstname);
            session.setLastname(lastname);
            session.setMobile(mobile);
            if (verified_by.equals("null")) {

            }
            else {
                session.setVerifiedBy(verified_by);
            }
            myAppDatabase.myDao().addSession(session);
            pageDetails.login_successful = true;
            pageDetails.username = username;
            pageDetails.college = college;
            pageDetails.branch = branch;
            pageDetails.firstname = firstname;
            pageDetails.lastname = lastname;
            pageDetails.mobile = mobile;
            pageDetails.user_type = user_type;
            pageDetails.verifiedBy = verified_by;

            Log.d("password status","password matched going to main page");
            Intent i = new Intent(mContext,Persona.class);

            mContext.startActivity(i);


        }
        else {
            progress.dismiss();
            Toast toast = Toast.makeText(mContext,
                    "Email or Password is Wrong",
                    Toast.LENGTH_SHORT);
            toast.show();

            Log.d("Login Unsuccessful","Passwords may not have been matched");
        }

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            View v=getCurrentFocus();
            if(v instanceof EditText){
                Rect outRect= new Rect();
                v.getGlobalVisibleRect(outRect);
                if(!outRect.contains((int)event.getRawX(),(int)event.getRawY())){
                    v.clearFocus();
                    InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
   /* public void gotopersona()
    {
        Intent i = new Intent(mContext,Persona.class);
        startActivity(i);
    }*/
/*public static void contilogin()
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
    int endIndex = tmp.indexOf(",\"user_type")-1;
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


*/
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
