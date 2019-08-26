package com.example.georg.odessaguide.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.georg.odessaguide.R;
import com.example.georg.odessaguide.maputils.MapActivity;
import com.example.georg.odessaguide.pojo.Places;
import com.example.georg.odessaguide.pojo.Places2;
import com.example.georg.odessaguide.pojo.Places3;
import com.example.georg.odessaguide.rvfeatures.placeAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlacesFragment extends Fragment {
    private static View placesView;
    ArrayList<Places> places;
    ArrayList<Places2> places2;
    ArrayList<Places3> places3;

    placeAdapter adapter;
    RecyclerView place_rv;
    DatabaseReference freference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        placesView = inflater.inflate(R.layout.fragment_places, container, false);
        try {
            PopulateFragment(getArguments().getString("key"));
        } catch (NullPointerException e) {
            PopulateFragment("places");
        }
        return placesView;
    }

    public static PlacesFragment newInstance(String type , boolean state) {
        PlacesFragment placesFragment = new PlacesFragment();
        Bundle savedInstanceState = new Bundle();
        savedInstanceState.putString("key", type);
        savedInstanceState.putBoolean("state" , state);
        placesFragment.setArguments(savedInstanceState);
        return placesFragment;
    }

    @Nullable
    public void PopulateFragment(final String parent) {
        places = new ArrayList<Places>();
        places2 = new ArrayList<Places2>();
        places3 = new ArrayList<Places3>();
        place_rv = placesView.findViewById(R.id.place_rv);
            try {
                freference = FirebaseDatabase.getInstance().getReference().child(parent);
            } catch (NullPointerException ex) {
                Toast.makeText(placesView.getContext(), "your child is null " + parent, Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }
            freference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (dataSnapshot1.child("address").exists()) {
                                place_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                                Places2 place = dataSnapshot1.getValue(Places2.class);
                                places2.add(place);
                                adapter = new placeAdapter(getContext(), null, places2, null);
                                place_rv.setAdapter(adapter);
                            }
                            else if (dataSnapshot1.child("blueflag").exists() & !dataSnapshot1.child("address").exists()) {
                                place_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                                Places3 place = dataSnapshot1.getValue(Places3.class);
                                places3.add(place);
                                adapter = new placeAdapter(getContext(), null, null , places3);
                                place_rv.setAdapter(adapter);
                            }
                            else {
                                place_rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
                                Places place = dataSnapshot1.getValue(Places.class);
                                places.add(place);
                                adapter = new placeAdapter(getContext(), places, null , null);
                                place_rv.setAdapter(adapter);
                            }
                        }
                    } else {
                        Intent intent = new Intent (placesView.getContext() , MapActivity.class);
                        intent.putExtra("reply", 1);
                        intent.putExtra("loc" , parent);
                        placesView.getContext().startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }