package com.mit.persona;
import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Event extends AppCompatActivity {

    public static MyAppDatabase myAppDatabase;
    public loginActivity loginactivity;
    private List<Table_Sessions> session;
    private Intent emailIntent;
    private List<EditText> group_email = new ArrayList<EditText>();
    Context mContext;
    private List<Table_registeredEvents> registeredEvents;;
    static boolean continue_group;

//    private String name = getIntent().getStringExtra("e_name"), desc=getIntent().getStringExtra("e_desc");


    @Override
    protected void onResume() {
        super.onResume();
        registeredEvents = myAppDatabase.myDao().getRegisteredEvents();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        registeredEvents = myAppDatabase.myDao().getRegisteredEvents();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mContext = this;
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "eventdb").allowMainThreadQueries().build();
        session = myAppDatabase.myDao().getsession();
        registeredEvents = myAppDatabase.myDao().getRegisteredEvents();


        final TextView event_name = findViewById(R.id.event_name);
        final TextView event_desc = findViewById(R.id.event_desc);
        final TextView event_date = findViewById(R.id.event_date);
        TextView prizeTitle = findViewById(R.id.prizeTitle);
        TextView prize_1_amt = findViewById(R.id.prize_1_amt);
        TextView prize_2_amt = findViewById(R.id.prize_2_amt);
        TextView prize_3_amt = findViewById(R.id.prize_3_amt);
        TextView prize_1_title = findViewById(R.id.prize_1_title);
        TextView prize_2_title = findViewById(R.id.prize_2_title);
        TextView prize_3_title = findViewById(R.id.prize_3_title);
        ImageView faculty_1_image = findViewById(R.id.faculty_1_image);
        TextView faculty_1_name = findViewById(R.id.faculty_1_name);
        final TextView faculty_1_email = findViewById(R.id.faculty_1_email);
        final TextView faculty_1_phone = findViewById(R.id.faculty_1_phone);
        ImageView faculty_2_image = findViewById(R.id.faculty_2_image);
        TextView faculty_2_name = findViewById(R.id.faculty_2_name);
        final TextView faculty_2_email = findViewById(R.id.faculty_2_email);
        final TextView faculty_2_phone = findViewById(R.id.faculty_2_phone);
        ImageView student_1_image = findViewById(R.id.student_1_image);
        TextView student_1_name = findViewById(R.id.student_1_name);
        final TextView student_1_email = findViewById(R.id.student_1_email);
        final TextView student_1_phone = findViewById(R.id.student_1_phone);
        ImageView student_2_image = findViewById(R.id.student_2_image);
        TextView student_2_name = findViewById(R.id.student_2_name);
        final TextView student_2_email = findViewById(R.id.student_2_email);
        final TextView student_2_phone = findViewById(R.id.student_2_phone);
        TextView totallikes = findViewById(R.id.totallikes);
        TextView rules = findViewById(R.id.rules);
        TextView rule_title = findViewById(R.id.rule_title);
        TextView student_title = findViewById(R.id.student_title);
        TextView faculty_title = findViewById(R.id.faculty_title);
        ImageView share_icon = findViewById(R.id.share);

        emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "[Query] Persona Fest 2019");
        final Intent callIntent = new Intent(Intent.ACTION_DIAL);


        final String shareBody = ("\n\nFor More information download our app: https://play.google.com/store/apps/details?id=com.mit.persona" + "\nOr log into our website: http://www.mituniversity.edu.in/mitpersonafest/ ");

        final Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");

        share_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, String.valueOf("MIT Persona Fest 2019 Presents to you " + event_name.getText() + ":\n" + event_desc.getText() + "\n" + shareBody));
                startActivity(Intent.createChooser(sharingIntent, "Share Using:"));
            }
        });


        faculty_1_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callIntent.setData(Uri.parse(String.valueOf("tel:" + faculty_1_phone.getText())));
                startActivity(callIntent);
            }
        });

        faculty_2_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callIntent.setData(Uri.parse(String.valueOf("tel:" + faculty_2_phone.getText())));
                startActivity(callIntent);
            }
        });

        student_1_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callIntent.setData(Uri.parse(String.valueOf("tel:" + student_1_phone.getText())));
                startActivity(callIntent);
            }
        });

        student_2_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callIntent.setData(Uri.parse(String.valueOf("tel:" + student_2_phone.getText())));
                startActivity(callIntent);
            }
        });


        faculty_1_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{String.valueOf(faculty_1_email.getText())});
                startActivity(Intent.createChooser(emailIntent, "Send a mail?"));
            }
        });

        faculty_2_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{String.valueOf(faculty_2_email.getText())});
                startActivity(Intent.createChooser(emailIntent, "Send a mail?"));
            }
        });

        student_1_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{String.valueOf(student_1_email.getText())});
                startActivity(Intent.createChooser(emailIntent, "Send a mail?"));
            }
        });

        student_2_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{String.valueOf(student_2_email.getText())});
                startActivity(Intent.createChooser(emailIntent, "Send a mail?"));
            }
        });


        //Button reg_btn = findViewById(R.id.event_reg_btn);


        //rules

        if (getIntent().getStringExtra("e_rules").equals("null")) {
            rules.setVisibility(View.GONE);
            rule_title.setVisibility(View.GONE);
        } else {
            rule_title.setVisibility(View.VISIBLE);
            rules.setText(getIntent().getStringExtra("e_rules"));
        }


        //likes

        if (getIntent().getStringExtra("event_e_likes").equals("null")) {
            totallikes.setVisibility(View.GONE);
        } else {
            totallikes.setText(getIntent().getStringExtra("totallikes"));
            Log.e("No of Likes", String.valueOf(getIntent().getStringExtra("totallikes")));
        }


        //staff1

        if (getIntent().getStringExtra("event_e_staff_1").equals("null")) {
            faculty_title.setVisibility(View.GONE);
            faculty_1_image.setVisibility(View.GONE);
            faculty_1_name.setVisibility(View.GONE);
        } else {
            faculty_1_image.setVisibility(View.VISIBLE);
            faculty_1_name.setText(getIntent().getStringExtra("event_e_staff_1"));
            Log.e("Staff 1", String.valueOf(getIntent().getStringExtra("event_e_staff_1")));

        }
        if (getIntent().getStringExtra("event_e_staff_1_email").equals("null")) {
            faculty_1_email.setVisibility(View.GONE);
        } else {
            faculty_1_email.setText(getIntent().getStringExtra("event_e_staff_1_email"));
            Log.e("Staff 1", String.valueOf(getIntent().getStringExtra("event_e_staff_1_email")));
        }
        if (getIntent().getStringExtra("event_e_staff_1_phone").equals("null")) {
            faculty_1_phone.setVisibility(View.GONE);
        } else {
            faculty_1_phone.setText(getIntent().getStringExtra("event_e_staff_1_phone"));
            Log.e("Staff 1", String.valueOf(getIntent().getStringExtra("event_e_staff_1_phone")));
        }

        //staff2

        if (getIntent().getStringExtra("event_e_staff_2").equals("null")) {
            faculty_2_image.setVisibility(View.GONE);
            faculty_2_name.setVisibility(View.GONE);
        } else {
            faculty_2_image.setVisibility(View.VISIBLE);
            faculty_2_name.setText(getIntent().getStringExtra("event_e_staff_2"));
            Log.e("Staff 2", String.valueOf(getIntent().getStringExtra("event_e_staff_2")));

        }
        if (getIntent().getStringExtra("event_e_staff_2_email").equals("null")) {
            faculty_2_email.setVisibility(View.GONE);
        } else {
            faculty_2_email.setText(getIntent().getStringExtra("event_e_staff_2_email"));
            Log.e("Staff 2", String.valueOf(getIntent().getStringExtra("event_e_staff_2_email")));
        }
        if (getIntent().getStringExtra("event_e_staff_2_phone").equals("null")) {
            faculty_2_phone.setVisibility(View.GONE);
        } else {
            faculty_2_phone.setText(getIntent().getStringExtra("event_e_staff_2_phone"));
            Log.e("Staff 2", String.valueOf(getIntent().getStringExtra("event_e_staff_2_phone")));
        }


        //student1

        if (getIntent().getStringExtra("event_e_student_1").equals("null")) {
            student_title.setVisibility(View.GONE);
            student_1_image.setVisibility(View.GONE);
            student_1_name.setVisibility(View.GONE);
        } else {
            student_1_image.setVisibility(View.VISIBLE);
            student_1_name.setText(getIntent().getStringExtra("event_e_student_1"));
            Log.e("Student 1", String.valueOf(getIntent().getStringExtra("event_e_student_1")));

        }
        if (getIntent().getStringExtra("event_e_student_1_email").equals("null")) {
            student_1_email.setVisibility(View.GONE);
        } else {
            student_1_email.setText(getIntent().getStringExtra("event_e_student_1_email"));
            Log.e("Student 1", String.valueOf(getIntent().getStringExtra("event_e_staff_1_email")));
        }
        if (getIntent().getStringExtra("event_e_student_1_phone").equals("null")) {
            student_1_phone.setVisibility(View.GONE);
        } else {
            student_1_phone.setText(getIntent().getStringExtra("event_e_student_1_phone"));
            Log.e("Student 1", String.valueOf(getIntent().getStringExtra("event_e_staff_1_phone")));
        }

        //student2

        if (getIntent().getStringExtra("event_e_student_2").equals("null")) {
            student_2_image.setVisibility(View.GONE);
            student_2_name.setVisibility(View.GONE);
        } else {
            student_2_image.setVisibility(View.VISIBLE);
            student_2_name.setText(getIntent().getStringExtra("event_e_student_2"));
            Log.e("Student 2", String.valueOf(getIntent().getStringExtra("event_e_student_2")));

        }
        if (getIntent().getStringExtra("event_e_student_2_email").equals("null")) {
            student_2_email.setVisibility(View.GONE);
        } else {
            student_2_email.setText(getIntent().getStringExtra("event_e_student_2_email"));
            Log.e("Student 2", String.valueOf(getIntent().getStringExtra("event_e_staff_2_email")));
        }
        if (getIntent().getStringExtra("event_e_student_2_phone").equals("null")) {
            student_2_phone.setVisibility(View.GONE);
        } else {
            student_2_phone.setText(getIntent().getStringExtra("event_e_student_2_phone"));
            Log.e("Student 2", String.valueOf(getIntent().getStringExtra("event_e_staff_2_phone")));
        }


        if (getIntent().getStringExtra("prize_1_amt").equals("null")) {
            prizeTitle.setVisibility(View.GONE);
            prize_1_title.setVisibility(View.GONE);
            prize_1_amt.setVisibility(View.GONE);
        } else {
            prizeTitle.setVisibility(View.VISIBLE);
            prize_1_title.setVisibility(View.VISIBLE);
            prize_1_amt.setText(getIntent().getStringExtra("prize_1_amt"));
            Log.e("Prize 1", String.valueOf(getIntent().getStringExtra("prize_1_amt")));
        }
        if (getIntent().getStringExtra("prize_2_amt").equals("null")) {
            prize_2_title.setVisibility(View.GONE);
            prize_2_amt.setVisibility(View.GONE);
        } else {
            prize_2_title.setVisibility(View.VISIBLE);
            prize_2_amt.setText(getIntent().getStringExtra("prize_2_amt"));
        }
        if (getIntent().getStringExtra("prize_3_amt").equals("null")) {
            prize_3_title.setVisibility(View.GONE);
            prize_3_amt.setVisibility(View.GONE);
        } else {
            prize_3_title.setVisibility(View.VISIBLE);
            prize_3_amt.setText(getIntent().getStringExtra("prize_3_amt"));
        }
        event_desc.setText(getIntent().getStringExtra("e_desc"));
        event_date.setText(getIntent().getStringExtra("e_date"));
        event_name.setText(getIntent().getStringExtra("e_name"));


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void registerevent(final View view) {

        if (isNetworkAvailable()) {

            Log.e("registerevent: ", String.valueOf(pageDetails.eventAlreadyRegistered));


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


            if (session.size() == 1) {

                if (getIntent().getStringExtra("e_type").equals("individual")) {

                    if (registeredEvents.isEmpty()) {
                        pageDetails.eventAlreadyRegistered = false;

                    }
                    else {
                        for(int i=0; i<registeredEvents.size(); i++) {
                            Log.e("registerevent: ", String.valueOf(registeredEvents.size()));
                            if ( registeredEvents.get(i).getEvent_name().equals(getIntent().getStringExtra("e_name"))) {
                                pageDetails.eventAlreadyRegistered = true;
                                break;
                            }
                            else {
                                pageDetails.eventAlreadyRegistered = false;
                            }
                        }
                    }

                    if(pageDetails.eventAlreadyRegistered) {
                        Toast.makeText(Event.this, "You've already registered for this event", Toast.LENGTH_SHORT).show();
                    }
                    else {


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
                                        pageDetails.registeredEventName = getIntent().getStringExtra("e_name");
                                        emailRegistrationEvent backroundWorker = new emailRegistrationEvent(mContext);
                                        backroundWorker.execute();
                                        Toast.makeText(Event.this, "" + pageDetails.firstname + " registered for " + getIntent().getStringExtra("e_name"), Toast.LENGTH_LONG).show();
                                        Button registerButton = view.findViewById(R.id.event_reg_btn);
                                        registerButton.setVisibility(View.GONE);

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
                }else if (getIntent().getStringExtra("e_type").equals("Team")) {

                alertDialogBuilder.setTitle("" + getIntent().getStringExtra("e_name"));
                alertDialogBuilder.setMessage("Are you sure you want to register for " + getIntent().getStringExtra("e_name") + ". Please enter email ids for all the team members. Make sure they have an account on the app.\n\nParticipant 1: " + pageDetails.username);

                final Context context = this;
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);


                final int max = 10;
                group_email.clear();
                for (int i = 2; i <= max; i++) {
                    final EditText participant_email = new EditText(context);
                    participant_email.setMaxLines(1);
                    participant_email.setInputType(32);
                    participant_email.setId(i);
                    participant_email.setSaveEnabled(true);
                    group_email.add(participant_email);

                    participant_email.setHint("Email of participant " + i);
                    layout.addView(participant_email);
                    Log.e("groupemail", "registerevent: " + group_email.size());
                }

                alertDialogBuilder.setView(layout)

                        .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                int size = group_email.size();
                                int count = 0;
                                String[] string = new String[size];
                                for (int i = 0; i < size; i++) {
                                    if (group_email.get(i).getText().toString().isEmpty()) {

                                        continue;
                                    } else {
                                        string[i] = group_email.get(i).getText().toString();
                                        count++;
                                        Log.e("string count", "Count: " + count);
                                    }
                                }

                                int min = 1;
                                if (count < min) {

                                    Toast.makeText(Event.this, "Enter minimum " + min + " email ids to proceed", Toast.LENGTH_SHORT).show();
                                } else {


                                    for (int i = 0; i < count; i++) {
                                        String email_url = "http://139.59.82.57:5000/users?where={\"email\":\"" + string[i] + "\"}";
                                        Log.e("", "EMAIL: "+string[i] );
                                        databaseOperations.verify_group(Event.this, email_url);

                                    }

                                    if (continue_group){
                                        for (int i = 0; i < count; i++) {
                                            pageDetails.group_list.add(string[i]);
                                            Log.d("Continue group", "onClick: Entered String" +string[i]);
                                        }
                                        Log.e("", "onClick: databaseOperations call" );
                                        databaseOperations.event_register(Event.this);
                                    }

                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                alertDialogBuilder.create();
                alertDialogBuilder.show();


            }
        } else {

            Log.e("Event register", "session not found : ");
            alertDialogBuilder.setTitle("" + getIntent().getStringExtra("e_name"));
            alertDialogBuilder.setMessage("Please Log in to register for " + getIntent().getStringExtra("e_name") + ". Do you want to log in or continue? ");
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
            alertDialogBuilder.create();
            alertDialogBuilder.show();

            Log.d("Not registered with app", "No session found: ");
            Toast.makeText(Event.this, "Please login to register for event " + pageDetails.username, Toast.LENGTH_SHORT).show();

        }

        } else {
            Toast.makeText(Event.this, "Please connect to the internet!", Toast.LENGTH_SHORT).show();
        }

    }
    public static void emailVerificationTeam(Boolean verified)
    {
        Log.e("Email:", "onClick: "+verified );

        if (!verified) {
            continue_group=false;
            //AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder();
            Toast.makeText(mContext, "Please make sure the users have registered on the app and try again.", Toast.LENGTH_LONG).show();
            return;
        } else {
            continue_group=true;

            Log.e("", "emailVerificationTeam: continue_group: "+continue_group );
            return;
        }
    }


}
