package com.example.georg.odessaguide.rvfeatures;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georg.odessaguide.pojo.Categories;
import com.example.georg.odessaguide.pojo.Excursion;
import com.example.georg.odessaguide.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class excExpanded extends RecyclerView.Adapter<excExpanded.excHolder> {
    Context context;
    ArrayList<Excursion> excursions;

    public excExpanded(Context context, ArrayList<Excursion> excursions) {
        this.context = context;
        this.excursions = excursions;
    }

    @NonNull
    @Override

    public excHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new excHolder(LayoutInflater.from(context).inflate(R.layout.excursion_card , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull excHolder holder, int position) {
        holder.title.setText(excursions.get(position).getTitle());
        holder.duration.setText(excursions.get(position).getDuration());
        Picasso.get().load(excursions.get(position).getPic()).into(holder.excursionImage);
    }

    @Override
    public int getItemCount() {
        return excursions.size();
    }

    public static class excHolder extends RecyclerView.ViewHolder {

        ImageView excursionImage;
        TextView title, duration;

        public excHolder(View itemView) {
            super(itemView);
            excursionImage = itemView.findViewById(R.id.excursionImage);
            title = itemView.findViewById(R.id.excursionTitle);
            duration = itemView.findViewById(R.id.duration);
        }
    }
}