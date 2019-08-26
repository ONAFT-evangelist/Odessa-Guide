package com.example.georg.odessaguide.rvfeatures;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georg.odessaguide.OnItemClickListener;
import com.example.georg.odessaguide.activities.ContainerActivity;
import com.example.georg.odessaguide.activities.SettingsActivity;
import com.example.georg.odessaguide.pojo.Other;
import com.example.georg.odessaguide.R;

import java.util.ArrayList;
import java.util.Objects;

public class otherAdapter extends RecyclerView.Adapter<otherAdapter.otherHolder> {

    Context context;
    ArrayList<Other> gem_list;
    public static final String key = "key";

    public otherAdapter(Context context, ArrayList<Other> gem_list) {
        this.context = context;
        this.gem_list = gem_list;
    }

    @NonNull
    @Override
    public otherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new otherHolder(LayoutInflater.from(context).inflate(R.layout.other_card , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull otherHolder holder, int position) {
        Objects.requireNonNull(holder).title.setText(gem_list.get(position).getTitle());
        holder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = null;
             String msg = gem_list.get(position).getId();
             if (msg.length() > 0) {
                 if(msg.equals("Settings")) {
                         intent = new Intent(context, SettingsActivity.class);
                         intent.putExtra(key, msg);
                         context.startActivity(intent);
                 } else {
                     intent = new Intent(context, ContainerActivity.class);
                     intent.putExtra(key , msg);
                     context.startActivity(intent);
                 }
                 } else {
                 Toast.makeText(context , "Wrong class name "+msg , Toast.LENGTH_LONG).show();
             }
                 }
        });
    }

    @Override
    public int getItemCount() {
        return gem_list.size();
    }

    public static class otherHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        private String gem;
        private final Context context;
        OnItemClickListener onItemClickListener;


        public otherHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.gem_title);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.onItemClickListener.onItemClick(view , getAdapterPosition());
        }

        public void setOnItemClickListener (OnItemClickListener itemClickListener) {
            this.onItemClickListener = itemClickListener;
        }


    }
}
