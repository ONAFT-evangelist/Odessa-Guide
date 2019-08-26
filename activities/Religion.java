package com.example.georg.odessaguide.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georg.odessaguide.R;
import com.squareup.picasso.Picasso;

public class Religion extends AppCompatActivity {

   private TextView true_descr, true_incl, true_excl;
   private ImageView rel_pic;
   private String true_descr1, true_incl1, true_excl1, rel_pic1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.religious);
        true_descr = findViewById(R.id.true_descr);
        true_incl = findViewById(R.id.true_incl);
        true_excl = findViewById(R.id.true_excl);
        rel_pic = findViewById(R.id.rel_pic);
        final Intent intent = getIntent();
        true_descr1 = intent.getStringExtra("descr");
        true_incl1 =  intent.getStringExtra("cincl");
        true_excl1 = intent.getStringExtra("cexcl");
        rel_pic1 = intent.getStringExtra("pic");
        true_descr.setText(true_descr1);
        true_incl.setText(true_incl1);
        true_excl.setText(true_excl1);
        Picasso.get().load(rel_pic1).into(rel_pic);
    }
}
