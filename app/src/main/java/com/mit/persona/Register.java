package com.mit.persona;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class Register extends AppCompatActivity {

    private static Context mContext;
    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isValidMobile(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            //if(phone.length() < 6 || phone.length() > 13) {
                 if(phone.length() != 10) {
                check = false;

            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }
    public static void contilogin(Boolean email_exists) {


        try{
            if(email_exists){
                Log.d("Email status","Email matched");
                Toast toast = Toast.makeText(mContext,
                        "Email already exist",
                        Toast.LENGTH_SHORT);
                toast.show();

            }else {
                Log.d("Email matching","Email didn't matched new user found");
                //databaseOperations.register(mContext);
                emailVerify backroundWorker = new emailVerify(mContext);
                backroundWorker.execute();
                Intent i = new Intent(mContext, Enter_OTP.class);
                mContext.startActivity(i);

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

        if (pageDetails.reg_password.equals(repassword.getText().toString())) {

        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Check the password",
                    Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (isValidMail(pageDetails.reg_email) == false) {
            Toast.makeText(this, "Enter a valid Email-id", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isValidMobile(pageDetails.reg_phno) == false) {
            Toast.makeText(this, "Enter a valid Mobile number", Toast.LENGTH_SHORT).show();
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
        if (!pageDetails.reg_firstname.isEmpty() && !pageDetails.reg_lastname.isEmpty() && !pageDetails.reg_email.isEmpty() && !pageDetails.reg_phno.isEmpty()
                && !pageDetails.reg_add.isEmpty() && !pageDetails.reg_dob.isEmpty() && !pageDetails.reg_clgName.isEmpty() && !pageDetails.reg_branch.isEmpty()
                && !pageDetails.reg_clgcity.isEmpty() && !pageDetails.reg_password.isEmpty() && !pageDetails.reg_gender.isEmpty()) {
            Log.d("Register Event:", "Detail Verification");
            String email_url = "http://139.59.82.57:5000/users?where={" + "\"email\"" + ":\"" + pageDetails.reg_email + "\"}";
            databaseOperations.mailExists(this, email_url, pageDetails.reg_email);
            //databaseOperations.register(this);
        } else {
            Log.d("data entered: ", "nope");
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

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(this);
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }
}
@SuppressLint("ValidFragment")
class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    Context context;
    public DatePickerFragment(Context context) {
        this.context = context;

    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        String date = ""+day+"/"+ (month+1)+"/"+year;
        pageDetails.reg_dob = date;
        TextView txtView = (TextView) (getActivity().findViewById(R.id.editText6));
        txtView.setText(date);


    }

}