package com.mit.persona;

import android.content.Context;
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
    private static Context mContext;
    static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_coordinator);
        mContext=this;
    }

    public void verify()
    {
        TextInputEditText t = findViewById(R.id.add_coordinator_text);
        email = t.getText().toString();
        if(!email.isEmpty()) {
            try {
                email = "http://139.59.82.57:5000/users?where={" + "\"email\"" + ":\"" + email + "\"}";
                databaseOperations.verify(this, email);
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
    public static void addCoordinator()
    {
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
            if(email.equals(fetchedEmail)==true){

                // TODO: 09-01-2019 Add functionality

                Log.d("Email status","Email matched");
                Toast toast = Toast.makeText(mContext,
                        "Verified as Coordinator",
                        Toast.LENGTH_SHORT);
                toast.show();

            }else {
                Log.d("Email matching","Email didnt matched no user found");
                Toast.makeText(mContext, "User not found!", Toast.LENGTH_SHORT).show();
                //databaseOperations.register(mContext);
            }}catch (Exception e){
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

