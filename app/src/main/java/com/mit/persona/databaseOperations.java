package com.mit.persona;

import android.arch.persistence.room.Room;
import android.content.Context;
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
    private static final String URL_localDbVersion = "http://139.59.82.57:5000/localDbVersion";
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
                            Log.e("Number of Events: ", String.valueOf(items.length()));

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
                                    catch (Exception e){
                                        Log.e("Events Insert: ", e.toString());
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

    public static void register(Enter_OTP enter_otp) {

        Log.e("Call Successful", "REGISTER EVENTS");

        RequestQueue requestQueue = Volley.newRequestQueue(enter_otp);

        JSONObject postparams = null;
        try {
            postparams = new JSONObject();
            postparams.put("firstname", pageDetails.reg_firstname);
            postparams.put("lastname", pageDetails.reg_lastname);
            postparams.put("email", pageDetails.reg_email);
            postparams.put("mobile", pageDetails.reg_phno);
            postparams.put("address", pageDetails.reg_add);
            postparams.put("gender", pageDetails.reg_gender);
            postparams.put("dob", pageDetails.reg_dob);
            postparams.put("college", pageDetails.reg_clgName);
            postparams.put("branch", pageDetails.reg_branch);
            postparams.put("city", pageDetails.reg_clgcity);
            postparams.put("password", pageDetails.reg_password);
        } catch (JSONException e) {
            Log.e(e.toString(), "");
        }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_users, postparams,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("REST Response: ", response.toString());
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
            };requestQueue.add(jsonObjectRequest);
//            postparams.put("e_id", "A5");
            //          postparams.put("e_type", "group");
            //        postparams.put("e_category", "cse");


    }

    public static void login(final loginActivity loginActivity, String entered_Email) {

        Log.e("Call Successful", "Login");

        RequestQueue requestQueue = Volley.newRequestQueue(loginActivity);


        /*JSONObject postparams = null;
        try {
            postparams = new JSONObject();
            postparams.put("firstname", pageDetails.reg_firstname);
            postparams.put("lastname", pageDetails.reg_lastname);
            postparams.put("email", pageDetails.reg_email);
            postparams.put("mobile", pageDetails.reg_phno);
            postparams.put("address", pageDetails.reg_add);
            postparams.put("gender", pageDetails.reg_gender);
            postparams.put("dob", pageDetails.reg_dob);
            postparams.put("college", pageDetails.reg_clgName);
            postparams.put("branch", pageDetails.reg_branch);
            postparams.put("city", pageDetails.reg_clgcity);
            postparams.put("password", pageDetails.reg_password);
        } catch (JSONException e) {
            Log.e(e.toString(), "");
        }
*/          String URL_email = entered_Email;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_email, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("REST Response: ", response.toString());
                        String tmp;
                        tmp = response.toString();
                        pageDetails.user_info = tmp;
                        Log.e("returned details",""+pageDetails.user_info);
                        Log.e("REST Response: ", pageDetails.user_info);
                        com.mit.persona.loginActivity.contilogin();
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
jsonObjectRequest.setShouldCache(false);

        requestQueue.add(jsonObjectRequest);
//            postparams.put("e_id", "A5");
        //          postparams.put("e_type", "group");
        //        postparams.put("e_category", "cse");


    }

    public static void mailExists(Register register, String entered_Email) {

        Log.e("Call Successful", "Login");

        RequestQueue requestQueue = Volley.newRequestQueue(register);


        /*JSONObject postparams = null;
        try {
            postparams = new JSONObject();
            postparams.put("firstname", pageDetails.reg_firstname);
            postparams.put("lastname", pageDetails.reg_lastname);
            postparams.put("email", pageDetails.reg_email);
            postparams.put("mobile", pageDetails.reg_phno);
            postparams.put("address", pageDetails.reg_add);
            postparams.put("gender", pageDetails.reg_gender);
            postparams.put("dob", pageDetails.reg_dob);
            postparams.put("college", pageDetails.reg_clgName);
            postparams.put("branch", pageDetails.reg_branch);
            postparams.put("city", pageDetails.reg_clgcity);
            postparams.put("password", pageDetails.reg_password);
        } catch (JSONException e) {
            Log.e(e.toString(), "");
        }
*/          String URL_email = entered_Email;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_email, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("REST Response: ", response.toString());
                        String tmp;
                        tmp = response.toString();
                        pageDetails.user_info = tmp;
                        Log.e("returned details",""+pageDetails.user_info);
                        Log.e("REST Response: ", pageDetails.user_info);
                        com.mit.persona.Register.contilogin();
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
        jsonObjectRequest.setShouldCache(false);

        requestQueue.add(jsonObjectRequest);
//            postparams.put("e_id", "A5");
        //          postparams.put("e_type", "group");
        //        postparams.put("e_category", "cse");


    }
    public static void verify(teacher_coordinator teacher_coordinator, String email) {

        Log.e("Call Successful", "Verify coordinator mail");

        RequestQueue requestQueue = Volley.newRequestQueue(teacher_coordinator);


        String URL_email = email;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_email, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("REST Response: ", response.toString());
                        String tmp;
                        tmp = response.toString();
                        pageDetails.user_info = tmp;
                        Log.e("returned details",""+pageDetails.user_info);
                        Log.e("REST Response: ", pageDetails.user_info);
                        com.mit.persona.teacher_coordinator.addCoordinator();
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
        jsonObjectRequest.setShouldCache(false);

        requestQueue.add(jsonObjectRequest);
//            postparams.put("e_id", "A5");
        //          postparams.put("e_type", "group");
        //        postparams.put("e_category", "cse");


    }

}
