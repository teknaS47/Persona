package com.mit.persona;

import android.arch.persistence.room.Room;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class databaseOperations {

    private static final String URL_events = "http://139.59.82.57:5000/events";
    private static final String URL_users = "http://139.59.82.57:5000/users";
    private static final String URL_registrations = "http://139.59.82.57:5000/registrations";

    public static MyAppDatabase myAppDatabase;


    public static void updateLocalDB(loginActivity persona) {

        myAppDatabase = Room.databaseBuilder(persona.getApplicationContext(), MyAppDatabase.class, "eventdb").allowMainThreadQueries().build();

        final Double local_verion = -0.1;
        final Double[] version = new Double[1];

        RequestQueue requestQueue = Volley.newRequestQueue(persona);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_events, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("REST Response: ", response.toString());
                        try {
                            JSONArray items = response.getJSONArray("_items");
                            JSONObject jsonobject = items.getJSONObject(0);
                            version[0] = jsonobject.getDouble("version");
                            Log.e("VERSION: ", version[0].toString());

                            if (local_verion < version[0]) {

                                for (int i=1; i<items.length(); i++) {
                                    jsonobject = items.getJSONObject(i);
                                    Events event = new Events();
                                    event.setName(jsonobject.getString("e_name"));
                                    event.setDate(jsonobject.getString("e_date"));
                                    event.setDescription(jsonobject.getString("e_desc"));
                                    event.setId(jsonobject.getString("e_id"));
                                    event.setVenue(jsonobject.getString("e_venue"));
                                    event.setType(jsonobject.getString("e_type"));
                                    event.setCategory(jsonobject.getString("e_category"));
                                    //event.setImg(R.drawable.ic_tag_faces_black);
                                    try {
                                        myAppDatabase.myDao().addEvent(event);
                                        Log.i(String.valueOf(i), ". DATA ADDED :)\n");
                                    }
                                    catch (Exception e) {
                                        Log.e("Events Insert:", e.toString());
                                    }

                                }

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REST Error: ", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders()  {
                Map<String, String> params = new HashMap<>();
                params.put(
                        "Authorization",
                        String.format("Basic %s", Base64.encodeToString(
                                String.format("%s:%s", "r00t", "abrakadabra!!").getBytes(), Base64.DEFAULT)));
                //params.put("If-Match", "b7d17aa524b9bd9c5e4cc010ee3d0596422909cf");

                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }




/*
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

*/
}
