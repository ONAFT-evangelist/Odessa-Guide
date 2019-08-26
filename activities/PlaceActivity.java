package com.example.georg.odessaguide.activities;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.example.georg.odessaguide.fragments.PlacesFragment;
        import com.example.georg.odessaguide.rvfeatures.placeAdapter;

public class PlaceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String type = intent.getStringExtra(placeAdapter.key);
        PlacesFragment placesFragment = PlacesFragment.newInstance(type , true);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, placesFragment)
                .commit();
    }

}
