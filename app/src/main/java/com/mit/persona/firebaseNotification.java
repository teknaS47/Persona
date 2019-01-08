package com.mit.persona;

import android.app.Notification;
import android.app.NotificationManager;
import android.arch.persistence.room.Room;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import static android.content.ContentValues.TAG;


public class firebaseNotification extends FirebaseMessagingService {

    private NotificationManager mNotificationManager;
    private static String DEFAULT_CHANNEL_ID = "default_channel";
    private static String DEFAULT_CHANNEL_NAME = "Default";
    public static MyAppDatabase myAppDatabase;


    public firebaseNotification() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "eventdb").allowMainThreadQueries().build();

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.

            } else {
                // Handle message within 10 seconds

            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            Table_Messages message = new Table_Messages();
            message.setTitle(remoteMessage.getNotification().getTitle());
            message.setMessage(remoteMessage.getNotification().getBody());
            myAppDatabase.myDao().addMessage(message);
            Log.e("\nNOTIFICATION ADDED :)", "");




            //1.Get reference to Notification Manager
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Persona.createNotificationChannel(mNotificationManager);

            //2.Build Notification with NotificationCompat.Builder
            Notification notification = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID)
                    .setContentTitle(remoteMessage.getNotification().getTitle())   //Set the title of Notification
                    .setContentText(remoteMessage.getNotification().getBody())    //Set the text for notification
                    .setSmallIcon(android.R.drawable.ic_menu_view)   //Set the icon
                    .build();

            //Send the notification.
            mNotificationManager.notify(1, notification);



        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }


}
