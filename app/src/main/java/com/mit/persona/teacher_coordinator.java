package com.mit.persona;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class teacher_coordinator extends AppCompatActivity {

    //private TextInputEditText t;
    public static Context mContext;
    static String email_url;
    static String email;
    private TextInputEditText t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_coordinator);
        mContext=this;
        t = findViewById(R.id.add_coordinator_text_field);
    }

    public void verify(View view)
    {

        email = t.getText().toString();
        pageDetails.entered_Email = email;
        if(!email.isEmpty()) {
            try {
                email_url = "http://139.59.82.57:5000/users?where={" + "\"email\"" + ":\"" + email + "\"}";
                databaseOperations.verify(this, email_url, pageDetails.entered_Email);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Toast toast = Toast.makeText(this, "Enter email of the coordinator", Toast.LENGTH_LONG);
            toast.show();
            //Toast.makeText(getApplicationContext(),"Enter Password or email ID and try again",Toast.LENGTH_LONG).show();
        }
    }

    public void backToMain(View view){
        Intent i = new Intent(mContext,Persona.class);
        startActivity(i);
        finish();
    }
    public static void addCoordinator(String etag, String ObjectId, String url) {
        /*String tmp = pageDetails.user_info;

        String fetchedEmail = "";
        String fetchedEtag = "";

        if(tmp== null){
            Log.d("error","returned string is null");
        }
        else {
            Log.d("searched data",""+tmp);
        }

        int startIndex = tmp.indexOf(",\"email\"") + 10;
        int endIndex = tmp.indexOf(",\"mobile")-1;
        int startIndexe = tmp.indexOf(",\"_etag\"") + 10;
        int endIndexe = tmp.indexOf(",\"_links")-1;
        try {
            fetchedEmail = pageDetails.user_info.substring(startIndex, endIndex);
            fetchedEtag = pageDetails.user_info.substring(startIndexe,endIndexe);
        }catch (Exception e){

        }
        Log.d("fetched etag is", "" + fetchedEtag);
        //Log.d("fetched password is", "" + endIndex);
        Log.d("fetched Email is", "" + fetchedEmail);
        Log.d("entered Email is", "" + email);
        */
        try{
            // TODO: 09-01-2019 Add functionality

                databaseOperations.userTypeChange((teacher_coordinator) mContext, etag, String.valueOf(url + ObjectId));

                Log.d("etag_sent","etag_sent");
                Toast toast = Toast.makeText(mContext,
                        "Verified as Coordinator",
                        Toast.LENGTH_SHORT);
                toast.show();

            }catch (Exception e){
            e.printStackTrace();
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

}

