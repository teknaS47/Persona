package com.mit.persona;
import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class Event extends AppCompatActivity {

    public static MyAppDatabase myAppDatabase;
    public  loginActivity loginactivity;
    private List<Table_Sessions> session;

//    private String name = getIntent().getStringExtra("e_name"), desc=getIntent().getStringExtra("e_desc");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        TextView event_name = findViewById(R.id.event_name);
        TextView event_desc = findViewById(R.id.event_desc);
        TextView event_date = findViewById(R.id.event_date);
        TextView prizeTitle = findViewById(R.id.prizeTitle);
        TextView prize_1_amt = findViewById(R.id.prize_1_amt);
        TextView prize_2_amt = findViewById(R.id.prize_2_amt);
        TextView prize_3_amt = findViewById(R.id.prize_3_amt);
        TextView prize_1_title = findViewById(R.id.prize_1_title);
        TextView prize_2_title = findViewById(R.id.prize_2_title);
        TextView prize_3_title = findViewById(R.id.prize_3_title);
        ImageView faculty_1_image = findViewById(R.id.faculty_1_image);
        TextView faculty_1_name = findViewById(R.id.faculty_1_name);
        TextView faculty_1_email = findViewById(R.id.faculty_1_email);
        TextView faculty_1_phone = findViewById(R.id.faculty_1_phone);
        ImageView faculty_2_image = findViewById(R.id.faculty_2_image);
        TextView faculty_2_name = findViewById(R.id.faculty_2_name);
        TextView faculty_2_email = findViewById(R.id.faculty_2_email);
        TextView faculty_2_phone = findViewById(R.id.faculty_2_phone);
        ImageView student_1_image = findViewById(R.id.student_1_image);
        TextView student_1_name = findViewById(R.id.student_1_name);
        TextView student_1_email = findViewById(R.id.student_1_email);
        TextView student_1_phone = findViewById(R.id.student_1_phone);
        ImageView student_2_image = findViewById(R.id.student_2_image);
        TextView student_2_name = findViewById(R.id.student_2_name);
        TextView student_2_email = findViewById(R.id.student_2_email);
        TextView student_2_phone = findViewById(R.id.student_2_phone);
        TextView totallikes = findViewById(R.id.totallikes);
        TextView rules = findViewById(R.id.rules);
        TextView rule_title = findViewById(R.id.rule_title);
        TextView student_title = findViewById(R.id.student_title);
        TextView faculty_title = findViewById(R.id.faculty_title);

        //Button reg_btn = findViewById(R.id.event_reg_btn);



        //rules

        if (getIntent().getStringExtra("e_rules").equals("null"))
        {
            rules.setVisibility(View.GONE);
            rule_title.setVisibility(View.GONE);
        }
        else {
            rule_title.setVisibility(View.VISIBLE);
            rules.setText(getIntent().getStringExtra("e_rules"));
        }


        //likes

        if (getIntent().getStringExtra("event_e_likes").equals("null"))
        {
            totallikes.setVisibility(View.GONE);
        }
        else {
            totallikes.setText(getIntent().getStringExtra("totallikes"));
            Log.e("No of Likes",String.valueOf(getIntent().getStringExtra("totallikes")));
        }



        //staff1

        if (getIntent().getStringExtra("event_e_staff_1").equals("null"))
        {
            faculty_title.setVisibility(View.GONE);
            faculty_1_image.setVisibility(View.GONE);
            faculty_1_name.setVisibility(View.GONE);
        }
        else
        {
            faculty_1_image.setVisibility(View.VISIBLE);
            faculty_1_name.setText(getIntent().getStringExtra("event_e_staff_1"));
            Log.e("Staff 1",String.valueOf(getIntent().getStringExtra("event_e_staff_1")));

        }
        if (getIntent().getStringExtra("event_e_staff_1_email").equals("null"))
        {
            faculty_1_email.setVisibility(View.GONE);
        }
        else
        {
            faculty_1_email.setText(getIntent().getStringExtra("event_e_staff_1_email"));
            Log.e("Staff 1",String.valueOf(getIntent().getStringExtra("event_e_staff_1_email")));
        }
        if (getIntent().getStringExtra("event_e_staff_1_phone").equals("null"))
        {
            faculty_1_phone.setVisibility(View.GONE);
        }
        else
        {
            faculty_1_phone.setText(getIntent().getStringExtra("event_e_staff_1_phone"));
            Log.e("Staff 1",String.valueOf(getIntent().getStringExtra("event_e_staff_1_phone")));
        }

        //staff2

        if (getIntent().getStringExtra("event_e_staff_2").equals("null"))
        {
            faculty_2_image.setVisibility(View.GONE);
            faculty_2_name.setVisibility(View.GONE);
        }
        else
        {
            faculty_2_image.setVisibility(View.VISIBLE);
            faculty_2_name.setText(getIntent().getStringExtra("event_e_staff_2"));
            Log.e("Staff 2",String.valueOf(getIntent().getStringExtra("event_e_staff_2")));

        }
        if (getIntent().getStringExtra("event_e_staff_2_email").equals("null"))
        {
            faculty_2_email.setVisibility(View.GONE);
        }
        else
        {
            faculty_2_email.setText(getIntent().getStringExtra("event_e_staff_2_email"));
            Log.e("Staff 2",String.valueOf(getIntent().getStringExtra("event_e_staff_2_email")));
        }
        if (getIntent().getStringExtra("event_e_staff_2_phone").equals("null"))
        {
            faculty_2_phone.setVisibility(View.GONE);
        }
        else
        {
            faculty_2_phone.setText(getIntent().getStringExtra("event_e_staff_2_phone"));
            Log.e("Staff 2",String.valueOf(getIntent().getStringExtra("event_e_staff_2_phone")));
        }



        //student1

        if (getIntent().getStringExtra("event_e_student_1").equals("null"))
        {
            student_title.setVisibility(View.GONE);
            student_1_image.setVisibility(View.GONE);
            student_1_name.setVisibility(View.GONE);
        }
        else
        {
            student_1_image.setVisibility(View.VISIBLE);
            student_1_name.setText(getIntent().getStringExtra("event_e_student_1"));
            Log.e("Student 1",String.valueOf(getIntent().getStringExtra("event_e_student_1")));

        }
        if (getIntent().getStringExtra("event_e_student_1_email").equals("null"))
        {
            student_1_email.setVisibility(View.GONE);
        }
        else
        {
            student_1_email.setText(getIntent().getStringExtra("event_e_student_1_email"));
            Log.e("Student 1",String.valueOf(getIntent().getStringExtra("event_e_staff_1_email")));
        }
        if (getIntent().getStringExtra("event_e_student_1_phone").equals("null"))
        {
            student_1_phone.setVisibility(View.GONE);
        }
        else
        {
            student_1_phone.setText(getIntent().getStringExtra("event_e_student_1_phone"));
            Log.e("Student 1",String.valueOf(getIntent().getStringExtra("event_e_staff_1_phone")));
        }

        //student2

        if (getIntent().getStringExtra("event_e_student_2").equals("null"))
        {
            student_2_image.setVisibility(View.GONE);
            student_2_name.setVisibility(View.GONE);
        }
        else
        {
            student_2_image.setVisibility(View.VISIBLE);
            student_2_name.setText(getIntent().getStringExtra("event_e_student_2"));
            Log.e("Student 2",String.valueOf(getIntent().getStringExtra("event_e_student_2")));

        }
        if (getIntent().getStringExtra("event_e_student_2_email").equals("null"))
        {
            student_2_email.setVisibility(View.GONE);
        }
        else
        {
            student_2_email.setText(getIntent().getStringExtra("event_e_student_2_email"));
            Log.e("Student 2",String.valueOf(getIntent().getStringExtra("event_e_staff_2_email")));
        }
        if (getIntent().getStringExtra("event_e_student_2_phone").equals("null"))
        {
            student_2_phone.setVisibility(View.GONE);
        }
        else
        {
            student_2_phone.setText(getIntent().getStringExtra("event_e_student_2_phone"));
            Log.e("Student 2",String.valueOf(getIntent().getStringExtra("event_e_staff_2_phone")));
        }




        if (getIntent().getStringExtra("prize_1_amt").equals("null"))
        {
            prizeTitle.setVisibility(View.GONE);
            prize_1_title.setVisibility(View.GONE);
            prize_1_amt.setVisibility(View.GONE);
        }
        else {
                prizeTitle.setVisibility(View.VISIBLE);
                prize_1_title.setVisibility(View.VISIBLE);
            prize_1_amt.setText(getIntent().getStringExtra("prize_1_amt"));
            Log.e("Prize 1",String.valueOf(getIntent().getStringExtra("prize_1_amt")));
        }
        if (getIntent().getStringExtra("prize_2_amt").equals("null"))
        {
            prize_2_title.setVisibility(View.GONE);
            prize_2_amt.setVisibility(View.GONE);
        }
        else {
            prize_2_title.setVisibility(View.VISIBLE);
            prize_2_amt.setText(getIntent().getStringExtra("prize_2_amt"));
        }
        if (getIntent().getStringExtra("prize_3_amt").equals("null"))
        {
            prize_3_title.setVisibility(View.GONE);
            prize_3_amt.setVisibility(View.GONE);
        }
        else {
            prize_3_title.setVisibility(View.VISIBLE);
            prize_3_amt.setText(getIntent().getStringExtra("prize_3_amt"));
        }
        event_desc.setText(getIntent().getStringExtra("e_desc"));
        event_date.setText(getIntent().getStringExtra("e_date"));
        event_name.setText(getIntent().getStringExtra("e_name"));


        }

        public void registerevent(View view) {

            myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "eventdb").allowMainThreadQueries().build();
            session = myAppDatabase.myDao().getsession();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


            if (session.size() == 1) {

                if(getIntent().getStringExtra("e_type").equals("individual")) {

                    Log.e("Event register", "session found : ");
                    alertDialogBuilder.setTitle("" + getIntent().getStringExtra("e_name"));
                    alertDialogBuilder.setMessage("Are you sure you want to register for " + getIntent().getStringExtra("e_name"));
                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    pageDetails.event_name = getIntent().getStringExtra("e_name");
                                    pageDetails.event_type = getIntent().getStringExtra("e_type");


//                                pageDetails.event_name=getIntent().getStringExtra("e_name");
//                                pageDetails.event_name=getIntent().getStringExtra("e_name");
//                                pageDetails.event_name=getIntent().getStringExtra("e_name");

                                    databaseOperations.event_register(Event.this);
                                    Toast.makeText(Event.this, ""+pageDetails.username+"Registered for " + getIntent().getStringExtra("e_name"), Toast.LENGTH_LONG).show();
                                }
                            });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else if(getIntent().getStringExtra("e_type").equals("Team")) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(this);

                        builder.setTitle("" + getIntent().getStringExtra("e_name"));
                        builder.setMessage("Are you sure you want to register for " + getIntent().getStringExtra("e_name")+". Please enter email ids for all the team members. Make sure they have an account on the app.");
                        LayoutInflater inflater = Event.this.getLayoutInflater();

                        builder.setView(inflater.inflate(R.layout.dialog_group_registration, null))
                                // Add action buttons
                                .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        // sign in the user ...
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        builder.create();
                        builder.show();


                }
            }
            else {

                Log.e("Event register", "session not found : ");
                alertDialogBuilder.setTitle("" + getIntent().getStringExtra("e_name"));
                alertDialogBuilder.setMessage("Please Log in to register for " + getIntent().getStringExtra("e_name")+". Do you want to log in or continue? ");
                alertDialogBuilder.setPositiveButton("Log in",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent i = new Intent(Event.this, loginActivity.class);
                                startActivity(i);
                                finish();
                            }
                        });
                alertDialogBuilder.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                Log.d("Not registered with app", "No session found: ");
                Toast.makeText(Event.this, "Please login to register for event "+pageDetails.username, Toast.LENGTH_SHORT).show();

            }

    }


}
