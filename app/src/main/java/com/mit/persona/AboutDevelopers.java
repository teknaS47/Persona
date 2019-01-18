package com.mit.persona;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

public class AboutDevelopers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developers);



        CardView c1 = (CardView) findViewById(R.id.devCard1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "( ͡° ͜ʖ ͡°)",
                        Toast.LENGTH_LONG).show();
            }
        });


        CardView c2 = (CardView) findViewById(R.id.devCard2);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "¯\\_(ツ)_/¯",
                        Toast.LENGTH_LONG).show();
            }
        });


        CardView c3 = (CardView) findViewById(R.id.devCard3);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "१✌˚◡˚✌५",
                        Toast.LENGTH_LONG).show();
            }
        });


        CardView c4 = (CardView) findViewById(R.id.devCard4);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "｡◕‿◕｡",
                        Toast.LENGTH_LONG).show();
            }
        });


        CardView c5 = (CardView) findViewById(R.id.devCard5);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "(¬‿¬)",
                        Toast.LENGTH_LONG).show();
            }
        });
    }





}
