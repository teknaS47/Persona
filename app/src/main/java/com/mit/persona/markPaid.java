package com.mit.persona;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class markPaid extends AppCompatActivity {

    private static Context ctx;
    private static String tmpemail;
    static LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_paid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ctx = this;
}
    private static boolean isValidMailstatic(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static void continueSearch()
    {
        if(pageDetails.eventsFound == false){
            Toast toast = Toast.makeText(ctx,
                    "No registered event found",
                    Toast.LENGTH_SHORT);
            toast.show();
        }

        if(isValidMailstatic(tmpemail) == true) {

            Button myButton = new Button(ctx);
            myButton.setText("Push Me");


            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButton, lp);


        }
        Log.d("findRegisteredEvents: ",""+pageDetails.registeredEvent);
    }
    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void displayEvents(markPaid view, final ArrayList<String> registeredEventsList, boolean eventsFound) {

        if(!eventsFound) {
            Log.e("Events called", "Not registered for any events!" );
            Toast.makeText(ctx, "Not registered for any events!", Toast.LENGTH_LONG).show();
        }
        else {
            ArrayAdapter adapter = new ArrayAdapter<String>(ctx, R.layout.volunteer_registered, registeredEventsList);

            ListView listView = view.findViewById(R.id.markPaidListEvents);
            listView.setAdapter(adapter);



            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());

                    Toast.makeText(view.getContext(), registeredEventsList.get(position), Toast.LENGTH_LONG).show();

                    alertDialogBuilder.setTitle(registeredEventsList.get(position));
                    alertDialogBuilder.setMessage("Have you accepted his payment for this event?");
                    LinearLayout layout = new LinearLayout(ctx);
                    layout.setOrientation(LinearLayout.VERTICAL);

                    final EditText accepted_amount = new EditText(ctx);
                    accepted_amount.setHint("Payment of Rupees? ₹");
                    layout.addView(accepted_amount);

                    alertDialogBuilder.setView(layout);

                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                    if (TextUtils.isEmpty(accepted_amount.getText())) {
                                        Toast.makeText(ctx, "AMOUNT IS EMPTY, AMOUNT CAN'T BE EMPTY", Toast.LENGTH_LONG).show();
                                        accepted_amount.setError("Amount is required!");
                                    } else {


                                        pageDetails.eventPaymentReceieved = registeredEventsList.get(position);
                                        Random random = new Random();
                                        pageDetails.eventAmountAccepted = accepted_amount.getText().toString();
                                        Log.e("event amount: ", pageDetails.eventAmountAccepted);
                                        pageDetails.transactionID = String.format("%06d", random.nextInt(100000000));
                                        String URL_payment = String.valueOf("http://139.59.82.57:5000/event_registrations?where={\"$and\":[{\"username\":\"" + pageDetails.volunteer_userEmail + "\"},{\"event_name\":\"" + pageDetails.eventPaymentReceieved + "\"}]}");

                                        databaseOperations.markAsPaid(view.getContext(), URL_payment);
                                        PaymentVerificationMail backgroundWorker = new PaymentVerificationMail(view.getContext());
                                        backgroundWorker.execute();

                                    /*
                                    databaseOperations.event_register(Event.this);
                                    pageDetails.registeredEventName = getIntent().getStringExtra("e_name");
                                    emailRegistrationEvent backroundWorker = new emailRegistrationEvent(view.getContext());
                                    backroundWorker.execute();
                                    Toast.makeText(Event.this, "" + pageDetails.firstname + " registered for " + getIntent().getStringExtra("e_name"), Toast.LENGTH_LONG).show();
                                    Button registerButton = view.findViewById(R.id.event_reg_btn);
                                    registerButton.setVisibility(View.GONE);

*/
                                    }
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
            });

            Log.e("displayEvents: ", registeredEventsList.toString());

        }


    }
    public void findRegisteredEvents(View view) {

        EditText email = (EditText) findViewById(R.id.findEmailed);
        String tempEmail = email.getText().toString();
        tmpemail = tempEmail;
        String email_url = "http://139.59.82.57:5000/event_registrations?where={" + "\"username\"" + ":\"" + tempEmail + "\"}";

        Log.e("email_url", email_url);
        Log.e("eventsFound", String.valueOf(pageDetails.eventsFound));
        if (isValidMail(tempEmail) == false) {
            Toast.makeText(this, "Enter a valid Email-id", Toast.LENGTH_SHORT).show();
            return;
        }else{
            pageDetails.volunteer_userEmail = tmpemail;
            databaseOperations.fetchEventData(this,email_url);
        }



    }
}
