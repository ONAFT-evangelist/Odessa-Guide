package com.example.georg.odessaguide.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.georg.odessaguide.fragments.ExcursionsFragment;
import com.example.georg.odessaguide.fragments.PlacesFragment;
import com.example.georg.odessaguide.rvfeatures.placeAdapter;
import com.example.georg.odessaguide.rvfeatures.rvAdapter;

public class ExcursionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String type = intent.getStringExtra(rvAdapter.key);
        ExcursionsFragment excursionsFragment = ExcursionsFragment.newInstance(type);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, excursionsFragment)
                .commit();
    }
}
