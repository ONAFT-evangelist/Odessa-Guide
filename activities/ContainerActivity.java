package com.example.georg.odessaguide.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.georg.odessaguide.rvfeatures.otherAdapter;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String frag = intent.getStringExtra(otherAdapter.key);
        try{
            Fragment fragment = (Fragment) Class.forName("com.example.georg.odessaguide.fragments."+ frag + "Fragment").newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, fragment)
                    .commit();
        } catch(ClassNotFoundException | InstantiationException  | IllegalAccessException ex) {
            Toast.makeText(this , "Fragment loading error" , Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }
}
