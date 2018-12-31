package com.mit.persona;

import android.arch.persistence.room.Room;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;


public class Persona extends AppCompatActivity implements allFragment.OnFragmentInteractionListener, artFragment.OnFragmentInteractionListener, designFragment.OnFragmentInteractionListener, techFragment.OnFragmentInteractionListener {

    private TabLayout tabLayout;
    public static MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.print("######################  On Create ###################");
        setContentView(R.layout.activity_persona);
        //databaseOperations.updateLocalDB(this);
        // DATABASE
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "eventdb").allowMainThreadQueries().build();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Art"));
        tabLayout.addTab(tabLayout.newTab().setText("Design"));
        tabLayout.addTab(tabLayout.newTab().setText("Technology"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager_id);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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


        JSONObject postparams = null;
        try {
            postparams = new JSONObject();
            postparams.put("e_name", "AndroidPatched");
//            postparams.put("e_id", "A5");
  //          postparams.put("e_type", "group");
    //        postparams.put("e_category", "cse");

        } catch (JSONException e) {
            e.printStackTrace();
        }


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

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
