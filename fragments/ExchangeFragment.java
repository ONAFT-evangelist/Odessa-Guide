package com.example.georg.odessaguide.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georg.odessaguide.AsyncWorker;
import com.example.georg.odessaguide.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ExchangeFragment extends Fragment {

    private View finance_view;
    private Button exchange_btn;
    private EditText exchange_in;
    private TextView exchange_out;
    private final static String urlp1 = "";
    private final static String urlp2 = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            finance_view = inflater.inflate(R.layout.currency_layout , container , false);
            exchange_btn = finance_view.findViewById(R.id.convert_btn);
            exchange_in = finance_view.findViewById(R.id.curr_in);
            exchange_out = finance_view.findViewById(R.id.curr_out);
            final Spinner from = finance_view.findViewById(R.id.val1);
            final Spinner to = finance_view.findViewById(R.id.val2);
            ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(finance_view.getContext() , R.array.currencies , android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            from.setAdapter(adapter);
            to.setAdapter(adapter);
            exchange_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                @NonNull
                public void onClick(View view) {
                    try {
                        int amount = Integer.valueOf(exchange_in.getText().toString());
                        String value1 = from.getSelectedItem().toString();
                        String value2 = to.getSelectedItem().toString();
                        if (amount != 0 && !value1.equals(value2)) {
                            exchange_out.setText("Waiting for response");
                            String query = urlp1  + value1 + "_" + value2 + urlp2;
                            double response = new AsyncWorker().execute(query).get();
                            response = amount*response;
                            DecimalFormat f = new DecimalFormat("##.00");
                            exchange_out.setText(""+f.format(response));
                        } else {
                            Toast.makeText(finance_view.getContext(), "Check input data!", Toast.LENGTH_LONG).show();
                        }

                    } catch (NumberFormatException e) {
                        Toast.makeText(finance_view.getContext(), "Check input data!", Toast.LENGTH_LONG).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
      return finance_view;
    }
}
