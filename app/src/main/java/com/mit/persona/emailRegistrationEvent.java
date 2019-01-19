package com.mit.persona;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class emailRegistrationEvent extends AsyncTask<String,Void,String> {

    public emailRegistrationEvent(Context register) {
    }

    protected String doInBackground(String... params) {
        //String type = params[0];
        
                try {
                    Random random = new Random();
                    String id = String.format("%04d", random.nextInt(10000));
                    final String from = "registration.mitpersonafest@mituniversity.edu.in";
                    final String password = "PersonaFest2019";
                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.socketFactory.port", "587");
                    props.put("mail.smtp.starttls.enable", "true");

                  /*  Session s = Session.getInstance(props, new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(from, password);
                        }
                    });*/
                    Session s = Session.getInstance(props, null);
                    s.setDebug(true);

                    MimeMessage msg = new MimeMessage(s);

                    String fromEmail = "" + from;

                    String addressTo = ""+pageDetails.username;
                    InternetAddress to = new InternetAddress(addressTo);

                    InternetAddress fromAddr = new InternetAddress(fromEmail);

                    msg.setSentDate(new java.util.Date());
                    msg.setFrom(fromAddr);
                    msg.addRecipient(Message.RecipientType.TO, to);

                    msg.setSubject("[Persona Fest 2019] Event Registration confirmation");
                    //msg.setContent(""+id,"text/plain");
                    pageDetails.otp = id;
                    msg.setContent("Thank you for registering to " + pageDetails.registeredEventName + "\nSee you soon! :) :)", "text/plain");
//TODO: try it for text/html.

                    Transport tr = s.getTransport("smtp");
                    String smtp_server = props.getProperty("mail.smtp.host");

                    tr.connect(smtp_server, from, password);
                    msg.saveChanges();
                    tr.sendMessage(msg, msg.getAllRecipients());
                    tr.close();

                } catch (Exception e) {
                    e.printStackTrace();

                }
        return null;
    }
}
