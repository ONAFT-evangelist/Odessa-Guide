package com.example.georg.odessaguide.rvfeatures;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georg.odessaguide.OnItemClickListener;
import com.example.georg.odessaguide.R;

public class placeHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView beach_main;
    ImageView flag_img;
    TextView textView2;
    CardView beach_card;
    public View itemView;
    private OnItemClickListener itemClickListener;


    public placeHolder3(View itemView) {
        super(itemView);
        this.itemView = itemView;
        final Context context = itemView.getContext();
        beach_card = itemView.findViewById(R.id.beach_card);
        beach_card.setOnClickListener(this);
        textView2 = itemView.findViewById(R.id.textView2);
        flag_img = itemView.findViewById(R.id.flag_img);
        beach_main = itemView.findViewById(R.id.beach_main);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view , getAdapterPosition());
    }

    public void setOnItemClickListener (OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
