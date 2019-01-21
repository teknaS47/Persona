package com.mit.persona;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.arch.persistence.room.Room;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.util.Log;

import java.util.List;


public class Persona extends AppCompatActivity {

    private TabLayout tabLayout;
    public static MyAppDatabase myAppDatabase;
    private static String DEFAULT_CHANNEL_ID = "default_channel";
    private static String DEFAULT_CHANNEL_NAME = "Default";
    private List<Table_Sessions> session;
    private List<Table_registeredEvents> registeredEventsList;

    int count = 1;
    @Override
    public void onBackPressed() {

        if(!pageDetails.login_successful) {
            Log.d("user logged in", "false");
            count = 0;
            super.onBackPressed();
        }
        if(pageDetails.login_successful) {
            count++;
            Log.d("user logged in", "true");
            if(count<=2) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Press back one more time to exit",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if(count>2){
            finish();
            moveTaskToBack(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String email_url = "http://139.59.82.57:5000/event_registrations?where={" + "\"username\"" + ":\"" + pageDetails.username + "\"}";
        databaseOperations.registerEventDatabase(this, email_url);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.print("######################  On Create ###################");
        setContentView(R.layout.activity_persona);

        //databaseOperations.updateLocalDB(this);
        // DATABASE


        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "eventdb").allowMainThreadQueries().build();
        session = myAppDatabase.myDao().getsession();
        registeredEventsList = myAppDatabase.myDao().getRegisteredEvents();

        String email_url;
        email_url = "http://139.59.82.57:5000/event_registrations?where={" + "\"username\"" + ":\"" + pageDetails.username + "\"}";
        databaseOperations.registerEventDatabase(this, email_url);
        Log.e("registeredEventsList", String.valueOf(registeredEventsList.size()));

        Log.e("Session: ", String.valueOf(session));
        try {
            Log.e("Session: ", String.valueOf(session.get(0).getId()));
        }
        catch (Exception e) {
            Log.e("Session Check: ", e.toString());
        }


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        // define your fragments here
        final Fragment fragment1 = new homePage();
        final Fragment fragment2 = new MessageFragment();
        final Fragment fragment3 = new UserPanelFragment();
        final Fragment fragment4 = new SearchFragment();
//        final Fragment fragment3 = new ThirdFragment();
        fragmentManager.beginTransaction().add(R.id.display, fragment1).commit();
        fragmentManager.beginTransaction().hide(fragment1).commit();
        fragmentManager.beginTransaction().add(R.id.display, fragment2,"message_fragment").commit();
        fragmentManager.beginTransaction().hide(fragment2).commit();

        fragmentManager.beginTransaction().add(R.id.display, fragment3,"user_panel").commit();
        fragmentManager.beginTransaction().hide(fragment3).commit();

        fragmentManager.beginTransaction().add(R.id.display, fragment4,"search_bar").commit();
        fragmentManager.beginTransaction().hide(fragment4).commit();

        // handle navigation selection
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment;
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                fragment = fragment1;
                                break;
                            case R.id.action_schedules:
                                fragment = fragment2;
                                break;
                            case R.id.action_music:
                                fragment = fragment3;
                                break;
                            case R.id.action_search:
                                fragment = fragment4;
                                break;
                            default:
                                fragment = fragment1;
                                break;
                        }
                        if (fragment == fragment2) {
                            fragmentManager.beginTransaction().hide(fragment3).hide(fragment4).hide(fragment1).show(fragment2).commit();
                        }
                        else if (fragment == fragment3)
                        {
                            fragmentManager.beginTransaction().hide(fragment1).hide(fragment4).hide(fragment2).show(fragment3).commit();
                        }
                        else if (fragment == fragment4)
                        {
                            fragmentManager.beginTransaction().hide(fragment1).hide(fragment3).hide(fragment2).show(fragment4).commit();
                        }
                        else {
                            fragmentManager.beginTransaction().hide(fragment3).hide(fragment4).hide(fragment2).show(fragment1).commit();
                        }

                        return true;
                    }
                });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_favorites);
    }




    // DATABASE

        /*try {
            Events event = new Events();
            event.setName("Painters Window");
            event.setDate("15-02-2018");
            event.setDescription("On-line Painting Competition");
            event.setId("A1");
            event.setVenue("");
            event.setTalentHub(true);
            event.setIndividual(true);
            //event.setImg(R.drawable.ic_tag_faces_black);
            Persona.myAppDatabase.myDao().addEvent(event);
            System.out.println("\n################################################### DATA ADDED :)");


            Events event1 = new Events();
            event1.setName("Persona Cut");
            event1.setDate("15-02-2018");
            event1.setDescription("Screening of Mock Product Ad Films-Online");
            event1.setId("A2");
            event1.setVenue("");
            event1.setTalentHub(true);
            event1.setGroup(true);
            //event.setImg(R.drawable.ic_tag_faces_black);
            Persona.myAppDatabase.myDao().addEvent(event1);
            System.out.println("\n################################################### DATA ADDED :)");

            Events event2 = new Events();
            event2.setName("Photography Workshop");
            event2.setDate("16-02-2018");
            event2.setDescription("");
            event2.setId("A3");
            event2.setVenue("");
            event2.setTalentHub(true);
            event2.setOpen_for_all(true);
            //event.setImg(R.drawable.ic_tag_faces_black);
            Persona.myAppDatabase.myDao().addEvent(event2);
            System.out.println("\n################################################### DATA ADDED :)");

            Events event3 = new Events();
            event3.setName("Psyched up!");
            event3.setDate("15-02-2018");
            event3.setDescription("");
            event3.setId("A8");
            event3.setVenue("");
            event3.setMastiMagic(true);
            event3.setIndividual(true);
            //event.setImg(R.drawable.ic_tag_faces_black);
            Persona.myAppDatabase.myDao().addEvent(event3);
            System.out.println("\n################################################### DATA ADDED :)");

            Events event4 = new Events();
            event4.setName("Selfie'sh!!");
            event4.setDate("16-02-2018");
            event4.setDescription("Competition will be continued until the 18th.");
            event4.setId("A9");
            event4.setVenue("");
            //event.setImg(R.drawable.ic_tag_faces_black);
            event4.setMastiMagic(true);
            event4.setGroup(true);
            Persona.myAppDatabase.myDao().addEvent(event4);
            System.out.println("\n################################################### DATA ADDED :)");

            Events event5 = new Events();
            event5.setName("Nartana");
            event5.setDate("15-02-2018");
            event5.setDescription("Solo Dance (Classical)");
            event5.setId("A17");
            event5.setVenue("");
            //event.setImg(R.drawable.ic_tag_faces_black);
            event5.setCulturalEvents(true);
            event5.setIndividual(true);
            Persona.myAppDatabase.myDao().addEvent(event5);
            System.out.println("\n################################################### DATA ADDED :)");

            Events event6 = new Events();
            event6.setName("Naval Show");
            event6.setDate("16-02-2018");
            event6.setDescription("");
            event6.setId("D1");
            event6.setVenue("");
            event6.setManetIOD(true);
            event6.setIndividual(true);
            //event.setImg(R.drawable.ic_tag_faces_black);
            Persona.myAppDatabase.myDao().addEvent(event6);
            System.out.println("\n################################################### DATA ADDED :)");

            Events event7 = new Events();
            event7.setName("Udaan");
            event7.setDate("15-02-2018");
            event7.setDescription("");
            event7.setId("D2");
            event7.setVenue("");
            event7.setManetIOD(true);
            event7.setGroup(true);
            //event.setImg(R.drawable.ic_tag_faces_black);
            Persona.myAppDatabase.myDao().addEvent(event7);
            System.out.println("\n################################################### DATA ADDED :)");

            Events event8 = new Events();
            event8.setName("Rhythm Divine Drum Circle");
            event8.setDate("15-02-2018");
            event8.setDescription("");
            event8.setId("V1");
            event8.setVenue("");
            event8.setVedicSciences(true);
            event8.setGroup(true);
            //event.setImg(R.drawable.ic_tag_faces_black);
            Persona.myAppDatabase.myDao().addEvent(event8);
            System.out.println("\n################################################### DATA ADDED :)");
        }

        catch (Exception e) {
            e.printStackTrace();
        }*/

        // Delete From Database
        /*
        Events event = new Events();
        event.setId("B1");
        Persona.myAppDatabase.myDao().deleteEvent(event); */



/*
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Register_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Persona.this, response, Toast.LENGTH_LONG).show();
                        System.out.println("---------------> " + response);
                        Log.d("RESPONSE: ", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Persona.this, error.toString(),Toast.LENGTH_LONG).show();
                        System.out.println("---------------> " + error.toString());
                        Log.d("ERROR: ", error.toString());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("firstname", "AKSHAY");
                params.put("lastname", "Yewale");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/


    public static void createNotificationChannel(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Create channel only if it is not already created
            if (notificationManager.getNotificationChannel(DEFAULT_CHANNEL_ID) == null) {
                notificationManager.createNotificationChannel(new NotificationChannel(
                        DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
                ));
            }
        }
    }

}
