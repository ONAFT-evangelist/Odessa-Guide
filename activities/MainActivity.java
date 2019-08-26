package com.example.georg.odessaguide.activities;

import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.georg.odessaguide.MainApplication;
import com.example.georg.odessaguide.R;
import com.example.georg.odessaguide.fragments.EventsFragment;
import com.example.georg.odessaguide.fragments.ExcursionsFragment;
import com.example.georg.odessaguide.fragments.OtherFragment;
import com.example.georg.odessaguide.fragments.PlacesFragment;
import com.example.georg.odessaguide.rvfeatures.rvAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(navigationListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container , new PlacesFragment()).commit();
        PreferenceManager.setDefaultValues(this , R.xml.app_preferences , false);
           }

    BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment choosenFragment = null;

                    switch (item.getItemId()) {
                        case R.id.places:
                            choosenFragment = new PlacesFragment();
                            break;
                        case R.id.categories:
                            choosenFragment = new ExcursionsFragment();
                            break;
                        case R.id.events:
                            choosenFragment = new EventsFragment();
                            break;
                        case R.id.other:
                            choosenFragment = new OtherFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container , choosenFragment).commit();
                    return true;
                }
            };

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainApplication.getRefWatcher(this).watch(this);
    }
}
