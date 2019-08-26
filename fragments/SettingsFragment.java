package com.example.georg.odessaguide.fragments;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.georg.odessaguide.BuildConfig;
import com.example.georg.odessaguide.R;


public class SettingsFragment extends Fragment {
  //  private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  TextView version = view.findViewById(R.id.version_control);
     //   version.setText("version "+BuildConfig.VERSION_NAME);
        return inflater.inflate(R.layout.fragment_settings , container , false);
    }

}
