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

import com.example.georg.odessaguide.pojo.Other;
import com.example.georg.odessaguide.R;
import com.example.georg.odessaguide.rvfeatures.otherAdapter;

import java.util.ArrayList;

public class OtherFragment extends Fragment {
    ArrayList<Other> gems;
    otherAdapter adapter;
    RecyclerView other_rv;
    private View other_v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        other_v = inflater.inflate(R.layout.fragment_other , container , false);
        PopulateGems();
        return other_v;
    }

    public void PopulateGems () {
        gems = new ArrayList<Other>();
        other_rv = other_v.findViewById(R.id.other_rv);
        other_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        gems.clear();
        Other o = new Other();

        o.setId("Settings");
        o.setTitle("Settings");
        gems.add(o);

        o = new Other();
        o.setId("Share");
        o.setTitle("Share");
        gems.add(o);

        o = new Other();
        o.setId("Story");
        o.setTitle("History of Odessa");
        gems.add(o);

        o = new Other();
        o.setId("Numbers");
        o.setTitle("Useful numbers");
        gems.add(o);

        o = new Other();
        o.setId("Exchange");
        o.setTitle("Currency Exchange");
        gems.add(o);

        o = new Other();
        o.setId("Developer");
        o.setTitle("Team of Developers");
        gems.add(o);

        o = new Other();
        o.setId("Feedback");
        o.setTitle("Send Feedback");
        gems.add(o);

        o = new Other();
        o.setId("Taxi");
        o.setTitle("Taxi");
        gems.add(o);

        o = new Other();
        o.setId("Transport");
        o.setTitle("Public transport");
        gems.add(o);
        adapter = new otherAdapter(getContext() , gems);
        other_rv.setAdapter(adapter);
    }

}