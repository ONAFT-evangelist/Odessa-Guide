package com.example.georg.odessaguide.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georg.odessaguide.R;
import com.example.georg.odessaguide.maputils.MapActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Beach extends AppCompatActivity {

    private ImageView beach_pic;
    private Button showwy_btn;
    private TextView beach_descr, day1_text, day2_text, day3_text, day4_text, cap_text;
    private String beach_descr1, day1_text1, day2_text1, day3_text1, day4_text1 , beach_pic1, cap_text1;
    ArrayList <String> location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beachs);
        beach_pic = findViewById(R.id.beach_pic);
        beach_descr = findViewById(R.id.beach_descr);
        day1_text = findViewById(R.id.day1_text);
        day2_text = findViewById(R.id.day2_text);
        day3_text = findViewById(R.id.day3_text);
        day4_text = findViewById(R.id.day4_text);
        cap_text = findViewById(R.id.cap_text);
        showwy_btn = findViewById(R.id.showwy_btn);
        final Intent intent = getIntent();
        cap_text1 = intent.getStringExtra("cap");
        beach_descr1 = intent.getStringExtra("descr");
        day1_text1 = intent.getStringExtra("day1");
        day2_text1 = intent.getStringExtra("day2");
        day3_text1 = intent.getStringExtra("day3");
        day4_text1  = intent.getStringExtra("day4");
        beach_pic1 = intent.getStringExtra("pic");
        location = intent.getStringArrayListExtra("location");

        Picasso.get().load(beach_pic1).into(beach_pic);
        beach_descr.setText(beach_descr1);
        day1_text.setText(day1_text1);
        day2_text.setText(day2_text1);
        day3_text.setText(day3_text1);
        day4_text.setText(day4_text1);
        cap_text.setText(cap_text1);

        showwy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra("places", location);
                intent.putExtra("reply", 2);
                getApplicationContext().startActivity(intent);
            }
        });
    }
}
