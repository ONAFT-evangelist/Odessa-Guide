package com.example.georg.odessaguide.rvfeatures;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.georg.odessaguide.OnItemClickListener;
import com.example.georg.odessaguide.R;
import com.example.georg.odessaguide.pojo.Calls;

import java.util.ArrayList;

public class callAdapter extends RecyclerView.Adapter<callAdapter.callHolder> {

    Context context;
    ArrayList<Calls> calls;

    public callAdapter(Context context, ArrayList<Calls> calls) {
        this.context = context;
        this.calls = calls;
    }

    @NonNull
    @Override
    public callHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new callHolder(LayoutInflater.from(context).inflate(R.layout.number_card , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull callHolder holder, int position) {
        holder.call.setText((calls.get(position).getCall()));
        holder.name.setText(calls.get(position).getName());
        holder.type.setText(calls.get(position).getType());
        holder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String phone = calls.get(position).getCall();
                Intent intent = new Intent(Intent.ACTION_DIAL , Uri.fromParts("tel" , phone , null));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return calls.size();
    }

    public static class callHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView call;
        TextView type;
        TextView name;
        View itemView;
        OnItemClickListener onItemClickListener;

        public callHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            final Context context = itemView.getContext();
            call = itemView.findViewById(R.id.call);
            type = itemView.findViewById(R.id.type);
            name = itemView.findViewById(R.id.name);
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
