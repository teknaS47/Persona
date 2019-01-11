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
import java.util.List;
import java.util.Map;

public class databaseOperations {

    private static final String URL_events = "http://139.59.82.57:5000/events";
    private static final String URL_users = "http://139.59.82.57:5000/users";
    private static final String URL_registrations = "http://139.59.82.57:5000/registrations";
    public static MyAppDatabase myAppDatabase;
    private static List<Table_DbVersionCheck> local_version;


    public static void updateLocalDB(loginActivity persona) {

        myAppDatabase = Room.databaseBuilder(persona.getApplicationContext(), MyAppDatabase.class, "eventdb").allowMainThreadQueries().build();

        final Double local_verion = -0.1;
        final Double[] version = new Double[1];
        local_version = myAppDatabase.myDao().getVersion();
        final Table_DbVersionCheck l_version = new Table_DbVersionCheck();

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

                            Log.e("global_version: ", String.valueOf(version[0]));
                            Log.e("local_version: ", String.valueOf(l_version.getVersion()));
                            Log.e("local_version2 : ", String.valueOf(local_version.get(0).getVersion()));

                            if ( local_version.size() == 0 ) {
                                Log.e("local_version == 0", String.valueOf(version[0]));
                                l_version.setVersion(version[0]);
                                myAppDatabase.myDao().addVersion(l_version);

                                // INSERT EVENTS DIRECTLY
                                pageDetails.insertEvents = true;

                            }
                            else if ( local_version.size() == 1 ) {
                                Log.e("local_version == 1: ", String.valueOf(local_version.get(0).getVersion()));
                                Log.e("local_version == 1: ", String.valueOf(local_version.get(0).getId()));
                                try {
                                    if (local_version.get(0).getVersion() < version[0]) {
                                        pageDetails.insertEvents = true;
                                        myAppDatabase.myDao().updateVersion(version[0]);
                                        myAppDatabase.myDao().deleteAllEvents();
                                    }
                                    else {
                                        pageDetails.insertEvents = false;
                                    }
                                }
                                catch (Exception e){
                                    Log.e("local_version == 1: ", e.toString());
                                }
                            }

                            if (pageDetails.insertEvents) {

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
                                    event.setE_1_prize(jsonobject.getString("e_1_prize"));
                                    event.setE_2_prize(jsonobject.getString("e_2_prize"));
                                    event.setE_3_prize(jsonobject.getString("e_3_prize"));
                                    event.setEvent_e_staff_1(jsonobject.getString("e_staff_1"));
                                    event.setEvent_e_staff_1_email(jsonobject.getString("e_staff_1_email"));
                                    event.setEvent_e_staff_1_phone(jsonobject.getString("e_staff_1_phone"));
                                    event.setEvent_e_staff_2(jsonobject.getString("e_staff_2"));
                                    event.setEvent_e_staff_2_email(jsonobject.getString("e_staff_2_email"));
                                    event.setEvent_e_staff_2_phone(jsonobject.getString("e_staff_2_phone"));
                                    event.setEvent_e_student_1(jsonobject.getString("e_student_1"));
                                    event.setEvent_e_student_1_email(jsonobject.getString("e_student_1_email"));
                                    event.setEvent_e_student_1_phone(jsonobject.getString("e_student_1_phone"));
                                    event.setEvent_e_student_2(jsonobject.getString("e_student_2"));
                                    event.setEvent_e_student_2_email(jsonobject.getString("e_student_2_email"));
                                    event.setEvent_e_student_2_phone(jsonobject.getString("e_student_2_phone"));
                                    event.setEvent_e_whatsappLink(jsonobject.getString("e_whatsappLink"));
                                    event.setEvent_e_likes(jsonobject.getString("e_likes"));
                                    event.setE_rules(jsonobject.getString("e_rules"));

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

    public static void login(final loginActivity loginActivity, String entered_Email, final String entered_password) {

        Log.e("Call Successful", "Login");

        RequestQueue requestQueue = Volley.newRequestQueue(loginActivity);

        String URL_email = entered_Email;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_email, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray items = null;
                        String password = null;
                        Integer user_type = 10;
                        String username = null;
                        try {
                            items = response.getJSONArray("_items");
                            JSONObject jsonobject = items.getJSONObject(0);
                            password = jsonobject.getString("password");
                            username = jsonobject.getString("email");
                            if (jsonobject.has("user_type")) {
                                user_type = jsonobject.getInt("user_type");
                            }
                            Log.e("Password: ", password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (String.valueOf(entered_password).equals(String.valueOf(password))) {
                            pageDetails.login_successful = true;
                            Log.e("Am i here?", String.valueOf(pageDetails.login_successful));
                        }
                        else {
                            pageDetails.login_successful = false;
                            Log.e("Or Am i here?", String.valueOf(pageDetails.login_successful));

                        }

                        //JSONObject jsonobject = response.getJSONObject();
                        //Log.e("REST Response: ", jsonobject.toString());
                        //String tmp;
                        //tmp = response.toString();
                        //pageDetails.user_info = tmp;
                        //Log.e("returned details",""+pageDetails.user_info);
                        //Log.e("REST Response: ", pageDetails.user_info);
                        com.mit.persona.loginActivity.FinishLogin(user_type, username);
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
    public static void mailExists(Register register,String email_url, final String entered_Email) {

        Log.e("Call Successful: ", "mailExists");

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
*/
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, email_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("RESPONSE CHECK: ", response.toString());
                        JSONArray items = null;
                        String email = null;
                        Boolean email_exists = true;
                        try {
                            items = response.getJSONArray("_items");
                            if (items.length() == 0) {
                                email_exists = false;
                            }

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        com.mit.persona.Register.contilogin(email_exists);

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

    }
    public static void verify(teacher_coordinator teacher_coordinator, String email_url, final String fetched_email) {

        Log.e("Call Successful", "Verify coordinator mail");

        RequestQueue requestQueue = Volley.newRequestQueue(teacher_coordinator);

        final String URL_email = email_url;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_email, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray items = null;
                        String email = null;
                        Integer user_type = 10;
                        Boolean emailExists = true;
                        String username, objectId = null;
                        String etag = null;
                        try {
                            items = response.getJSONArray("_items");
                            if (items.length() == 0) {
                                emailExists = false;
                            }
                            else {
                                JSONObject jsonobject = items.getJSONObject(0);
                                if (jsonobject.has("email")) {
                                    email = jsonobject.getString("email");
                                    if (String.valueOf(fetched_email).equals(String.valueOf(email))) {
                                        etag = jsonobject.getString("_etag");
                                        objectId = jsonobject.getString("_id");
                                    }
                                }
                            }
                            com.mit.persona.teacher_coordinator.addCoordinator(etag, objectId, URL_users, emailExists);

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
        jsonObjectRequest.setShouldCache(false);

        requestQueue.add(jsonObjectRequest);
//            postparams.put("e_id", "A5");
        //          postparams.put("e_type", "group");
        //        postparams.put("e_category", "cse");



    }
    public static void userTypeChange(teacher_coordinator teacher_coordinator, final String e_tag, final String url) {

        Log.e("Call Successful", "Verify coordinator mail");

        RequestQueue requestQueue = Volley.newRequestQueue(teacher_coordinator);


        JSONObject postparams = null;
        try {
            postparams = new JSONObject();
            postparams.put("user_type", 2);
        } catch (JSONException e) {
            Log.e(e.toString(), "");
        }

//        final String URL_user_type = user_type;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH, url, postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("REST Response: ", response.toString());
//                        String tmp;
//                        tmp = response.toString();
//                        pageDetails.user_info = tmp;
                        Log.e("returned details", "" + pageDetails.user_info);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REST Error: ", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put(
                        "Authorization",
                        String.format("Basic %s", Base64.encodeToString(
                                String.format("%s:%s", "r00t", "abrakadabra!!").getBytes(), Base64.DEFAULT)));
                params.put("If-Match", e_tag);
                return params;
            }
        };
        jsonObjectRequest.setShouldCache(false);

        requestQueue.add(jsonObjectRequest);
    }

}
