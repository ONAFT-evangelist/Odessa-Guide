package com.example.georg.odessaguide.rvfeatures;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georg.odessaguide.OnItemClickListener;
import com.example.georg.odessaguide.R;

public class placeHolder2 extends RecyclerView.ViewHolder implements  View.OnClickListener {

    ImageView place_pic2;
    TextView title_placeholder;
    TextView addr_placeholder;
    CardView place_card2;
    public View itemView;
    private OnItemClickListener listener;

    public placeHolder2(View itemView) {
        super(itemView);
        this.itemView = itemView;
        final Context context = itemView.getContext();
        place_pic2 = itemView.findViewById(R.id.place_pic2);
        title_placeholder = itemView.findViewById(R.id.title_placeholder);
        addr_placeholder = itemView.findViewById(R.id.addr_placeholder);
        place_card2 = itemView.findViewById(R.id.place_card2);
        place_card2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        this.listener.onItemClick(view , getAdapterPosition());
    }

    public void setOnItemClickListener (OnItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }
}
