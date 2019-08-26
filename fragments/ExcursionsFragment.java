package com.example.georg.odessaguide.fragments;

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

import com.example.georg.odessaguide.pojo.Beach;
import com.example.georg.odessaguide.pojo.Categories;
import com.example.georg.odessaguide.R;
import com.example.georg.odessaguide.pojo.Excursion;
import com.example.georg.odessaguide.pojo.Religion;
import com.example.georg.odessaguide.rvfeatures.rvAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExcursionsFragment extends Fragment {

    ArrayList<Categories> categories;
    ArrayList<Religion> religions;
    ArrayList<Beach> beaches;
    ArrayList<Excursion> excursions;
    rvAdapter adapter;
    private static View ExcursionView;
    RecyclerView excursionrv;
    DatabaseReference freference;

    public static ExcursionsFragment newInstance(String type) {
        Toast.makeText(ExcursionView.getContext(), "your child is in instance: " + type, Toast.LENGTH_LONG).show();
        ExcursionsFragment excursionsFragment = new ExcursionsFragment();
        Bundle savedInstanceState = new Bundle();
        savedInstanceState.putString("key", type);
        excursionsFragment.setArguments(savedInstanceState);
        return excursionsFragment;
    }

    @Nullable
    @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ExcursionView = inflater.inflate(R.layout.fragment_excursion , container , false);
        try {
            PopulateExcursions (getArguments().getString("key"));
        } catch (NullPointerException e) {
            PopulateExcursions("excursion_type");
        }
        return ExcursionView;
    }

    private void PopulateExcursions(String parent) {
        categories = new ArrayList<Categories>();
        excursions = new ArrayList<Excursion>();
        religions = new ArrayList<Religion>();
        beaches = new ArrayList<Beach>();
        excursionrv = ExcursionView.findViewById(R.id.exk_rv);
        try {
            freference = FirebaseDatabase.getInstance().getReference().child(parent);
        } catch (NullPointerException ex) {
            Toast.makeText(ExcursionView.getContext(), "your child is null " + parent, Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
        freference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if (dataSnapshot.exists()){
                   for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                   {
                       if ( dataSnapshot1.child("descr").exists() & !dataSnapshot1.child("cap").exists() & !dataSnapshot1.child("cost").exists()){
                           Toast.makeText(ExcursionView.getContext(), "cats", Toast.LENGTH_LONG).show();
                            excursionrv.setLayoutManager(new LinearLayoutManager(getContext()));
                            Excursion excursion = dataSnapshot1.getValue(Excursion.class);
                            excursions.add(excursion);
                           adapter = new rvAdapter( getContext(), null , excursions , null , null);
                           excursionrv.setAdapter(adapter);}
                       else if (dataSnapshot1.child("cap").exists() & !dataSnapshot1.child("cost").exists()) {
                           Toast.makeText(ExcursionView.getContext(), "sea", Toast.LENGTH_LONG).show();
                           excursionrv.setLayoutManager(new LinearLayoutManager(getContext()));
                           Beach beach = dataSnapshot1.getValue(Beach.class);
                           beaches.add(beach);
                           adapter = new rvAdapter( getContext(), null , null , beaches, null);
                           excursionrv.setAdapter(adapter);
                       }
                       else if (dataSnapshot1.child("cost").exists() & !dataSnapshot1.child("cap").exists()) {
                           Toast.makeText(ExcursionView.getContext(), "religion", Toast.LENGTH_LONG).show();
                           excursionrv.setLayoutManager(new LinearLayoutManager(getContext()));
                           Religion religion = dataSnapshot1.getValue(Religion.class);
                           religions.add(religion);
                           adapter = new rvAdapter( getContext(), null , null , null , religions);
                           excursionrv.setAdapter(adapter);
                       }

                        else {
                           Toast.makeText(ExcursionView.getContext(), "else", Toast.LENGTH_SHORT).show();
                           excursionrv.setLayoutManager(new GridLayoutManager(getContext() , 2));
                           Categories excursion = dataSnapshot1.getValue(Categories.class);
                           categories.add(excursion);
                           adapter = new rvAdapter( getContext(), categories , null , null , null);
                           excursionrv.setAdapter(adapter);
                       }
                   }
               } else {
                   Toast.makeText(ExcursionView.getContext(), "your child is null ", Toast.LENGTH_LONG).show();
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}