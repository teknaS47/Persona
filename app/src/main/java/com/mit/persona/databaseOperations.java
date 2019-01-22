package com.mit.persona;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class databaseOperations {

    private static final String URL_events = "http://139.59.82.57:5000/events";
    private static final String URL_users = "http://139.59.82.57:5000/users";
    private static final String URL_registrations = "http://139.59.82.57:5000/event_registrations";
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
                            //Log.e("local_version2 : ", String.valueOf(local_version.get(0).getVersion()));

                            if (local_version.size() == 0) {
                                Log.e("local_version == 0", String.valueOf(version[0]));
                                l_version.setVersion(version[0]);
                                myAppDatabase.myDao().addVersion(l_version);

                                // INSERT EVENTS DIRECTLY
                                pageDetails.insertEvents = true;

                            } else if (local_version.size() == 1) {
                                Log.e("local_version == 1: ", String.valueOf(local_version.get(0).getVersion()));
                                Log.e("local_version == 1: ", String.valueOf(local_version.get(0).getId()));
                                try {
                                    if (local_version.get(0).getVersion() < version[0]) {
                                        pageDetails.insertEvents = true;
                                        myAppDatabase.myDao().updateVersion(version[0]);
                                        myAppDatabase.myDao().deleteAllEvents();
                                    } else {
                                        pageDetails.insertEvents = false;
                                    }
                                } catch (Exception e) {
                                    Log.e("local_version == 1: ", e.toString());
                                }
                            }

                            if (pageDetails.insertEvents) {

                                for (int i = 1; i < items.length(); i++) {
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
                                    } catch (Exception e) {
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
            public Map<String, String> getHeaders() {
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
            public Map<String, String> getHeaders() {
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
                        Integer user_type = null;
                        String college = null;
                        String firstname = null;
                        String lastname = null;
                        String branch = null;
                        String username = null;
                        String verified_by = "null";
                        String mobile = null;
                        try {
                            items = response.getJSONArray("_items");
                            JSONObject jsonobject = items.getJSONObject(0);
                            password = jsonobject.getString("password");
                            username = jsonobject.getString("email");
                            firstname = jsonobject.getString("firstname");
                            lastname = jsonobject.getString("lastname");
                            branch = jsonobject.getString("branch");
                            college = jsonobject.getString("college");
                            mobile = jsonobject.getString("mobile");
                            user_type = jsonobject.getInt("user_type");
                            if(jsonobject.has("verified_by")){
                                verified_by = jsonobject.getString("verified_by");
                            }
                            Log.e("USER TYPE: ", String.valueOf(user_type));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (String.valueOf(entered_password).equals(String.valueOf(password))) {
                            pageDetails.login_successful = true;
                            Log.e("Am i here?", String.valueOf(pageDetails.login_successful));
                        } else {
                            pageDetails.login_successful = false;
                            Log.e("Or Am i here?", String.valueOf(pageDetails.login_successful));

                        }

                        com.mit.persona.loginActivity.FinishLogin(user_type, username, firstname, lastname, mobile, college, branch, verified_by);
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

    public static void mailExists(Register register, String email_url, final String entered_Email) {

        Log.e("Call Successful: ", "mailExists");

        RequestQueue requestQueue = Volley.newRequestQueue(register);
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
            public Map<String, String> getHeaders() {
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
                        Integer user_type = null;
                        Boolean emailExists = true;
                        String username, objectId = null;
                        String etag = null;
                        try {
                            items = response.getJSONArray("_items");
                            if (items.length() == 0) {
                                emailExists = false;
                            } else {
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
            public Map<String, String> getHeaders() {
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

    public static void fetchEventData(final markPaid markPaid, String email_url) {

        Log.e("Call Successful", "Verify coordinator mail");

        RequestQueue requestQueue = Volley.newRequestQueue(markPaid);

        final String URL_email = email_url;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_email, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray items = null;
                        JSONObject jsonobject;
                        String email = null;
                        Integer user_type = null;
                        Boolean emailExists = true;
                        String username, objectId = null;
                        String etag = null;
                        try {
                            items = response.getJSONArray("_items");
                            Log.e("Items length:", String.valueOf(items.length()));
                            if (items.length() == 0) {
                                pageDetails.eventsFound = false;
                            } else {
                                pageDetails.eventsFound = true;
                                pageDetails.registeredEventsList.clear();
                                //pageDetails.registeredEvent = items;
                                for (int i= 0; i<items.length(); i++)   {
                                    jsonobject = items.getJSONObject(i);
                                    Log.e("onResponse: ", jsonobject.getString("event_name"));
                                    pageDetails.registeredEventsList.add(jsonobject.getString("event_name"));
                                }
                                Log.e("Events found?  ", String.valueOf(pageDetails.eventsFound));
                                com.mit.persona.markPaid.displayEvents(markPaid, pageDetails.registeredEventsList, pageDetails.eventsFound);
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
            public Map<String, String> getHeaders() {
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
            postparams.put("verified_by", pageDetails.username);
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

//

    public static void event_register(Event event) {

        Log.e("Call Successful", "REGISTER EVENT");

        JSONObject postparams = null;
        try {
            postparams = new JSONObject();
            postparams.put("event_name", pageDetails.event_name);
            postparams.put("event_type", pageDetails.event_type);
            //String group_list[]= {};
/*            ArrayList<String> group_list = pageDetails.group_list;
            group_list.add("abc");
            group_list.add("pqr");
            group_list.add("lmn");
            JSONArray list = new JSONArray(group_list);*/

            //  postparams.put("group_list", group_list);
            postparams.put("paid_by", "null");
            postparams.put("payment_status", "Unpaid");
            postparams.put("username", pageDetails.username);
            Log.e("EVENT REGISTERED", "event_register() returned:\nEvent name " + pageDetails.event_name + "\nEvent type " + pageDetails.event_type + "\nUsername " + pageDetails.reg_email + " / " + pageDetails.entered_Email + "\nParams " + postparams);
        } catch (Exception e) {
            Log.e("event_register:", e.toString());
        }

        RequestQueue requestQueue = Volley.newRequestQueue(event);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_registrations, postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("REST Response: ", response.toString());
                        Table_registeredEvents event = new Table_registeredEvents();
                        event.setEvent_name(pageDetails.event_name);
                        event.setEvent_type(pageDetails.event_type);
                        Persona.myAppDatabase.myDao().addRegisteredEvent(event);
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
                //params.put("If-Match", "b7d17aa524b9bd9c5e4cc010ee3d0596422909cf");

                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);

    }

    public static void userEditFetch(final UserPanelEdit userPanelEdit, String user_url, final String session_email) {

        Log.e("Call Successful", "Verify coordinator mail");

        RequestQueue requestQueue = Volley.newRequestQueue(userPanelEdit);

        final String User_url = user_url;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, User_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray items = null;
                        String email = null;
                        Integer user_type = null;
                        Boolean emailExists = true;
                        String username, objectId = null;
                        String etag = null;
                        try {
                            items = response.getJSONArray("_items");
                            if (items.length() == 0) {
                                emailExists = false;
                            } else {
                                JSONObject jsonobject = items.getJSONObject(0);
                                if (jsonobject.has("email")) {
                                    email = jsonobject.getString("email");
                                    if (String.valueOf(session_email).equals(String.valueOf(email))) {
                                        etag = jsonobject.getString("_etag");
                                        objectId = jsonobject.getString("_id");
                                    }
                                }
                            }
                            userPanelEdit.changeInfo(etag, String.valueOf(URL_users + "/" + objectId));

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
            public Map<String, String> getHeaders() {
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

    public static void updateUserInfo(final UserPanelEdit userPanelEdit, final String etag, String URL, final String fname, final String lname, final String phoneno, final String collegename, final String branch) {

        Log.e("Updating user info", etag);
        Log.e("Updating user info", String.valueOf(fname + " " + lname + " " + phoneno + " " + collegename + " " + branch));

        RequestQueue requestQueue = Volley.newRequestQueue(userPanelEdit);

        Persona.myAppDatabase.myDao().clearSessionTable();
        Table_Sessions session = new Table_Sessions();
        session.setUsername(pageDetails.username);
        session.setBranch(branch);
        session.setCollege(collegename);
        session.setFirstname(fname);
        session.setLastname(lname);
        session.setMobile(phoneno);
        myAppDatabase.myDao().addSession(session);
        pageDetails.login_successful = true;
        pageDetails.college = collegename;
        pageDetails.branch = branch;
        pageDetails.firstname = fname;
        pageDetails.lastname = lname;
        pageDetails.mobile = phoneno;


        JSONObject postparams = null;
        try {
            postparams = new JSONObject();
            postparams.put("firstname", fname);
            postparams.put("lastname", lname);
            postparams.put("mobile", phoneno);
            postparams.put("college", collegename);
            postparams.put("branch", branch);

            Log.e("updateUserInfo: ", "### here after getting var");


        } catch (JSONException e) {
            Log.e(e.toString(), "");
        }

//        final String URL_user_type = user_type;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH, URL, postparams,
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
                params.put("If-Match", etag);
                return params;
            }
        };
        jsonObjectRequest.setShouldCache(false);

        requestQueue.add(jsonObjectRequest);

    }

    public static boolean verify_group(Event event, String email_url) {

        Log.e("Call Successful", "Verify coordinator mail");

        RequestQueue requestQueue = Volley.newRequestQueue(event);


        final String URL_email = email_url;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, email_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray items = null;
                        try {
                            Boolean email_found = false;
                            items = response.getJSONArray("_items");
                            Log.e("", "onResponse: " + items);
                            if (items.length() == 0) {
                                email_found = false;
                                Log.e("", "email not found: ");

                            } else {
                                email_found = true;
                                Log.e("", "email found: ");

//                                JSONObject jsonobject = items.getJSONObject(0);
//                                if (jsonobject.has("email")) {
//                                    email = jsonobject.getString("email");
//                                    if (String.valueOf(fetched_email).equals(String.valueOf(email))) {
//                                        etag = jsonobject.getString("_etag");
//                                        objectId = jsonobject.getString("_id");
//                                    }
//                                }
                            }
                            com.mit.persona.Event.emailVerificationTeam(email_found);

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
            public Map<String, String> getHeaders() {
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


        return false;
    }

    public static void registerEventDatabase(Context mContext, final String URL_verification) {

        Log.e("Call Successful: ", "registerEventVerification");

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_verification, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("RESPONSE CHECK: ", response.toString());
                        try {
                            JSONArray items = response.getJSONArray("_items");
                            JSONObject jsonobject;

                            for (int i = 1; i < items.length(); i++) {
                                jsonobject = items.getJSONObject(i);
                                Table_registeredEvents event = new Table_registeredEvents();
                                event.setEvent_name(jsonobject.getString("event_name"));
                                event.setEvent_type(jsonobject.getString("event_type"));
                                myAppDatabase.myDao().addRegisteredEvent(event);
                                Log.i(String.valueOf(i), ". REGISTERED EVENTS updated :)\n");
                            }
                        }catch (Exception e) {
                                Log.e("Registered Events:", e.toString());
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
            public Map<String, String> getHeaders() {
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

    public static void markAsPaid(final Context context, String url_payment) {

        Log.e("Call Successful", "Mark as Paid");

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        final String[] etag = new String[1];
        final String[] objectId = new String[1];

        Log.e("markAsPaid: ", url_payment);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_payment, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray items = null;
                        try {
                            items = response.getJSONArray("_items");
                            Log.e("onResponse: ",response.toString() );
                            if (items.length() == 0) {

                            } else {
                                JSONObject jsonobject;
                                jsonobject = items.getJSONObject(0);
                                etag[0] = jsonobject.getString("_etag");
                                objectId[0] = jsonobject.getString("_id");
                                //Log.e( "ETAG: ", etag[0]);
                                //Log.e( "objectid: ", objectId[0]);
                                markAsPaid2(context, etag[0], objectId[0]);
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
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put(
                        "Authorization",
                        String.format("Basic %s", Base64.encodeToString(
                                String.format("%s:%s", "r00t", "abrakadabra!!").getBytes(), Base64.DEFAULT)));

                return params;
            }
        };
        jsonObjectRequest.setShouldCache(false);

        requestQueue.add(jsonObjectRequest);

    }

    public static void markAsPaid2(Context context, final String etag, String objectID) {

        RequestQueue requestQueue1 = Volley.newRequestQueue(context);

        JSONObject postparams = null;
        try {
            postparams = new JSONObject();
            postparams.put("paid_by", pageDetails.username);
            postparams.put("payment_status", "paid");
            postparams.put("transaction_id", pageDetails.transactionID);
            postparams.put("payment_amount",pageDetails.eventAmountAccepted);
        } catch (JSONException e) {
            Log.e(e.toString(), "");
        }

        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.PATCH, String.valueOf(URL_registrations + "/" + objectID), postparams,
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
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put(
                        "Authorization",
                        String.format("Basic %s", Base64.encodeToString(
                                String.format("%s:%s", "r00t", "abrakadabra!!").getBytes(), Base64.DEFAULT)));
                params.put("If-Match", etag);
                return params;
            }
        };
        jsonObjectRequest1.setShouldCache(false);

        requestQueue1.add(jsonObjectRequest1);


    }


    //String email_url = "http://139.59.82.57:5000/users?where={" + "\"email\"" + ":\"" + string[i] + "\"}";






}