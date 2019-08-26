package com.example.georg.odessaguide.rvfeatures;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georg.odessaguide.OnItemClickListener;
import com.example.georg.odessaguide.R;

public class placeHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView place_pic;
    TextView place_title;
    CardView place_card;
    Button btn;
    public View itemView;
    private OnItemClickListener listener;

    public placeHolder1(final View itemView) {
        super(itemView);
        this.itemView = itemView;
        final Context context = itemView.getContext();
        place_pic = itemView.findViewById(R.id.place_pic);
        place_title = itemView.findViewById(R.id.place_title);
        place_card = itemView.findViewById(R.id.place_card);
        place_card.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.listener.onItemClick(view , getAdapterPosition());
    }

    public void setOnItemClickListener (OnItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }
}