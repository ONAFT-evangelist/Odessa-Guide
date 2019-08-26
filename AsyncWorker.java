package com.example.georg.odessaguide;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class AsyncWorker extends AsyncTask<String, String, Double> {
        private  HttpURLConnection urlConnection;
        String response;
        double val;
        @Override
        protected Double doInBackground(String... urls) {
            URL url = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                response = reader.readLine();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            try {
                JSONObject object = new JSONObject(response);
                Iterator<String> i = object.keys();
                while (i.hasNext()) {
                    String key = i.next();
                    val = object.getDouble(key);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return val;
        }
}
