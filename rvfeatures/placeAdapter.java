package com.example.georg.odessaguide.rvfeatures;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.georg.odessaguide.OnItemClickListener;
import com.example.georg.odessaguide.maputils.MapActivity;
import com.example.georg.odessaguide.activities.PlaceActivity;
import com.example.georg.odessaguide.activities.PlaceCollapsed;
import com.example.georg.odessaguide.pojo.Places;
import com.example.georg.odessaguide.R;
import com.example.georg.odessaguide.pojo.Places2;
import com.example.georg.odessaguide.pojo.Places3;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class placeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
     Context context;
    View itemView;
     ArrayList<Places> places;
    ArrayList<Places2> places2;
    ArrayList<Places3> places3;
    private final int TYPE_ITEM1 = 0;
    private final int TYPE_ITEM2 = 1;
    private final int TYPE_ITEM3 = 2;
    public static final String key = "place";

    public placeAdapter() {
    }

    public placeAdapter(Context context, ArrayList<Places> places ,ArrayList<Places2> places2 , ArrayList<Places3> places3 ) {
        this.context = context;
        this.places = places;
        this.places2 = places2;
        this.places3 = places3;
    }

    @Override
    public int getItemViewType(int position) {
        if (places2 == null & places3 == null) {
            return TYPE_ITEM1;
        }
        if (places == null & places3 == null) {
            return TYPE_ITEM2;
        }
        if (places == null & places2 == null){
            return TYPE_ITEM3;
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM1) {
            itemView = LayoutInflater.from(context).inflate(R.layout.place_cards , parent , false);
                return new placeHolder1(itemView);
        }
        if (viewType == TYPE_ITEM2) {
            itemView = LayoutInflater.from(context).inflate(R.layout.place_cards2 , parent , false);
            return new placeHolder2(itemView);
        }
        if (viewType == TYPE_ITEM3) {
            itemView = LayoutInflater.from(context).inflate(R.layout.place_cards3 , parent , false);
            return new placeHolder3(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof placeHolder1) {
            ((placeHolder1) holder).place_title.setText(places.get(position).getTitle());
            Picasso.get().load(places.get(position).getPic()).into(((placeHolder1) holder).place_pic);
            ((placeHolder1) holder).setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    String redir = places.get(position).getTitle();
                        if (redir.length()>0) {
                            Intent intent = new Intent(context , PlaceActivity.class);
                            intent.putExtra(key, redir);
                            context.startActivity(intent);
                    } else {
                       // Toast.makeText(context , "NO ANOTHER WAY" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (context , MapActivity.class);
                        intent.putExtra("reply", 1);
                        intent.putExtra("loc" , redir);
                        context.startActivity(intent);
                    }
                }
            });
        }
        if(holder instanceof placeHolder2) {
            ((placeHolder2) holder).title_placeholder.setText(places2.get(position).getTitle());
            ((placeHolder2) holder).addr_placeholder.setText(places2.get(position).getAddress());
            Picasso.get().load(places2.get(position).getPic()).into(((placeHolder2) holder).place_pic2);
            ((placeHolder2) holder).setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                   Intent intent = new Intent (context , PlaceCollapsed.class);
                    intent.putExtra("pic" , places2.get(position).getPic());
                   intent.putExtra("detail" , places2.get(position).getDescr_long());
                   intent.putExtra("address" , places2.get(position).getAddress());
                   intent.putExtra("key", 0);
                   context.startActivity(intent);
                }
            });
        }
        if(holder instanceof placeHolder3) {
            ((placeHolder3) holder).textView2.setText(places3.get(position).getTitle());
            Picasso.get().load(places3.get(position).getPic()).into(((placeHolder3) holder).beach_main);
            if (places3.get(position).isBlueflag()){
                ((placeHolder3) holder).flag_img.setVisibility(View.VISIBLE);
            }
            if (!places3.get(position).isBlueflag()){
                ((placeHolder3) holder).flag_img.setVisibility(View.GONE);
            }
            ((placeHolder3) holder).setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent (context , PlaceCollapsed.class);
                    intent.putExtra("pic" , places3.get(position).getPic());
                    intent.putExtra("detail" , places3.get(position).getDescr());
                    intent.putExtra("address" , places3.get(position).getTitle());
                    intent.putExtra("key", 1);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(places2 == null & places3 == null){
            return places.size();
        }
        if (places == null & places3 == null ) {
            return places2.size();
        }
        if (places == null & places2 == null){
            return places3.size();
        }
        return -1;
    }
}