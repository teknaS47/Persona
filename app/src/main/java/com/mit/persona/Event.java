package com.mit.persona;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        TextView event_name = findViewById(R.id.event_name);
        TextView event_desc = findViewById(R.id.event_desc);
        TextView event_date = findViewById(R.id.event_date);
        TextView prizeTitle = findViewById(R.id.prizeTitle);
        TextView prize_1_amt = findViewById(R.id.prize_1_amt);
        TextView prize_2_amt = findViewById(R.id.prize_2_amt);
        TextView prize_3_amt = findViewById(R.id.prize_3_amt);
        TextView prize_1_title = findViewById(R.id.prize_1_title);
        TextView prize_2_title = findViewById(R.id.prize_2_title);
        TextView prize_3_title = findViewById(R.id.prize_3_title);
        TextView faculty_1_name = findViewById(R.id.faculty_1_name);
        TextView faculty_2_name = findViewById(R.id.faculty_2_name);
        TextView student_1_name = findViewById(R.id.student_1_name);
        TextView student_2_name = findViewById(R.id.student_2_name);



        if (getIntent().getStringExtra("prize_1_amt").equals("null"))
        {
            prizeTitle.setVisibility(View.GONE);
            prize_1_title.setVisibility(View.GONE);
            prize_1_amt.setVisibility(View.GONE);
        }
        else {
                prizeTitle.setVisibility(View.VISIBLE);
                prize_1_title.setVisibility(View.VISIBLE);
            prize_1_amt.setText(getIntent().getStringExtra("prize_1_amt"));
            Log.e("Prize 1",String.valueOf(getIntent().getStringExtra("prize_1_amt")));
        }
        if (getIntent().getStringExtra("prize_2_amt").equals("null"))
        {
            prize_2_title.setVisibility(View.GONE);
            prize_2_amt.setVisibility(View.GONE);
        }
        else {
            prize_2_title.setVisibility(View.VISIBLE);
            prize_2_amt.setText(getIntent().getStringExtra("prize_2_amt"));
        }
        if (getIntent().getStringExtra("prize_3_amt").equals("null"))
        {
            prize_3_title.setVisibility(View.GONE);
            prize_3_amt.setVisibility(View.GONE);
        }
        else {
            prize_3_title.setVisibility(View.VISIBLE);
            prize_3_amt.setText(getIntent().getStringExtra("prize_3_amt"));
        }
        event_desc.setText(getIntent().getStringExtra("e_desc"));
        event_date.setText(getIntent().getStringExtra("e_date"));
        event_name.setText(getIntent().getStringExtra("e_name"));






    }
}
