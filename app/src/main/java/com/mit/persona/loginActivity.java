package com.mit.persona;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity /*implements OnClickListener*/{

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private Button login_btn;
    private TextView email,password;
    private String e,p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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


        TextView skip_bt = findViewById(R.id.skip);
        skip_bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this, Persona.class));
            }
        });


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    public void gotoregister(View view) {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
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
