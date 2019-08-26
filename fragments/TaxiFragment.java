package com.example.georg.odessaguide.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.georg.odessaguide.R;
import com.example.georg.odessaguide.pojo.Calls;
import com.example.georg.odessaguide.rvfeatures.callAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TaxiFragment extends Fragment {
    ArrayList<Calls> calls;
    callAdapter adapter;
    private static View NumberView;
    RecyclerView call_rv;
    DatabaseReference freference;


    public static NumbersFragment newInstance () {
        NumbersFragment fragment = new NumbersFragment();
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        calls = new ArrayList<Calls>();
        NumberView = inflater.inflate(R.layout.fragment_call , container , false);
        call_rv = NumberView.findViewById(R.id.call_rv);
        call_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        freference = FirebaseDatabase.getInstance().getReference().child("taxi");
        freference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Calls call = dataSnapshot1.getValue(Calls.class);
                    calls.add(call);
                }
                adapter = new callAdapter(getContext() , calls);
                call_rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return NumberView;
    }
}
