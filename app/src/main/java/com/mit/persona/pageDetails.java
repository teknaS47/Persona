package com.mit.persona;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class pageDetails {
    public static String reg_firstname;
    public static String reg_lastname;
    public static String reg_email;
    public static String reg_phno;
    public static String reg_add;
    public static String reg_dob;
    public static String reg_clgName;
    public static String reg_branch;
    public static String reg_clgcity;
    public static String reg_password;
    public static String reg_gender;

    public static String user_info;
    public static String entered_Password;

    public static String event_name;
    public static String event_type;
    public static ArrayList<String> group_list;
    public static String paid_by;
    public static String payment_status;
    public static boolean email_exists;

    public static String username;
    public static String firstname;
    public static String lastname;
    public static String mobile;
    public static String college;
    public static String branch;
    public static String verifiedBy;
    public static Integer user_type;

    public static boolean alreadyExists;
    public static String otp;
    public static String entered_Email;

    public static boolean insertEvents;

    public static String registeredEventName;

    public static boolean login_successful;
    public static JSONArray registeredEvent;
    public static  boolean eventsFound;

    public static ArrayList<String> registeredEventsList = new ArrayList<String>();

    public static ArrayList<String> displayRegisteredEvent = new ArrayList<String>();


    public static boolean eventAlreadyRegistered = true;


    public static String eventPaymentReceieved;
    public static String transactionID;
    public static String eventAmountAccepted;

    public static String volunteer_userEmail;
}
