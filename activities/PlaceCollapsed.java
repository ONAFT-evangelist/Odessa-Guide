package com.example.georg.odessaguide.activities;

import android.content.Context;
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

public class PlaceCollapsed extends AppCompatActivity {

    private Button navigate;
    private ImageView toolbar_placeholder;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_expanded);
        toolbar_placeholder = findViewById(R.id.toolbar_placeholder);
        navigate = findViewById(R.id.map_btn_place);
        final Intent intent = getIntent();
        String descr = intent.getStringExtra("detail");
        final int key = intent.getIntExtra("key" , 0);
        final String coordinates = intent.getStringExtra("address");
        final ArrayList<String> places = intent.getStringArrayListExtra("places");
        final String pic = intent.getStringExtra("pic");
        TextView textView = findViewById(R.id.content_descr);
        Picasso.get().load(pic).into(toolbar_placeholder);
        textView.setText(descr);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), MapActivity.class);
                intent2.putExtra("loc" , coordinates);
                intent2.putExtra("places" , places);
                intent2.putExtra("reply" ,key);
                startActivity(intent2);
            }
        });
    }
}
