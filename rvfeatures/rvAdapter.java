package com.example.georg.odessaguide.rvfeatures;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georg.odessaguide.OnItemClickListener;
import com.example.georg.odessaguide.activities.ExcursionActivity;
import com.example.georg.odessaguide.activities.PlaceCollapsed;
import com.example.georg.odessaguide.maputils.MapActivity;
import com.example.georg.odessaguide.pojo.Beach;
import com.example.georg.odessaguide.pojo.Categories;
import com.example.georg.odessaguide.pojo.Excursion;
import com.example.georg.odessaguide.R;
import com.example.georg.odessaguide.pojo.Religion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class rvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    View itemView;
    ArrayList<Categories> categories;
    ArrayList<Excursion> excursions;
    ArrayList<Beach> beaches;
    ArrayList<Religion> religions;
    public static final String key = "excursion";
    private final int TYPE_ITEM1 = 0;
    private final int TYPE_ITEM2 = 1;
    private final int TYPE_ITEM3 = 2;
    private final int TYPE_ITEM4 = 3;


    public rvAdapter(Context context, ArrayList<Categories> categories, ArrayList<Excursion> excursions, ArrayList<Beach> beaches, ArrayList<Religion> religions) {
        this.context = context;
        this.categories = categories;
        this.excursions = excursions;
        this.beaches = beaches;
        this.religions = religions;
    }

    @Override
    public int getItemViewType(int position) {
        if (categories == null&religions == null& beaches == null) {
            return TYPE_ITEM2;
        }
        if ( excursions == null&religions == null& beaches == null) {
            return TYPE_ITEM1;
        }
        if (excursions == null &categories == null&beaches == null) {
            return  TYPE_ITEM3;
        }
        if (excursions == null &categories == null& religions == null ) {
            return  TYPE_ITEM4;
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM1) {
            itemView = LayoutInflater.from(context).inflate(R.layout.exc_category , parent , false);
            return new rvHolder(itemView);
        }
        if (viewType == TYPE_ITEM2) {
            itemView = LayoutInflater.from(context).inflate(R.layout.excursion_card , parent , false);
            return new gemHolder(itemView);
        }
        if (viewType == TYPE_ITEM3) {
            itemView = LayoutInflater.from(context).inflate(R.layout.rel_card , parent , false);
            return new relHolder(itemView);
        }
        if (viewType == TYPE_ITEM4) {
            itemView = LayoutInflater.from(context).inflate(R.layout.place_cards3 , parent , false);
            return new beachHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof rvHolder) {
            ((rvHolder)holder).title.setText(categories.get(position).getTitle());
            Picasso.get().load(categories.get(position).getPic()).into(((rvHolder)holder).excursionImage);
            ((rvHolder)holder).setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    String redir = categories.get(position).getTitle();
                    if (redir.length() >0) {
                        Intent intent = new Intent(context , ExcursionActivity.class);
                        intent.putExtra(key, redir);
                        context.startActivity(intent);
                    } else {

                    }
                }
            });
        }
        if (holder instanceof gemHolder) {
            ((gemHolder)holder).excursionTitle.setText(excursions.get(position).getTitle());
            ((gemHolder)holder).duration.setText(excursions.get(position).getDuration());
            Picasso.get().load(excursions.get(position).getPic()).into(((gemHolder)holder).excursionImage);
            ((gemHolder)holder).setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent(context, PlaceCollapsed.class);
                    intent.putExtra("key" , 2);
                    intent.putExtra("pic" , excursions.get(position).getPic());
                    intent.putExtra("places" , excursions.get(position).getLocation());
                    intent.putExtra("detail" , excursions.get(position).getDescr());
                    context.startActivity(intent);
                }
            });
        }
        if (holder instanceof relHolder) {
            ((relHolder)holder).excursionTitle2.setText(religions.get(position).getTitle());
            ((relHolder)holder).duration2.setText(religions.get(position).getDuration());
            ((relHolder)holder).cost.setText(religions.get(position).getCost());
            Picasso.get().load(religions.get(position).getPic()).into(((relHolder)holder).relImage);
            ((relHolder)holder).setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent(context, com.example.georg.odessaguide.activities.Religion.class);
                    intent.putExtra("cexcl" , religions.get(position).getCexcl());
                    intent.putExtra("cincl" , religions.get(position).getCincl());
                    intent.putExtra("descr" , religions.get(position).getDescr());
                    intent.putExtra("pic" , religions.get(position).getPic());
                    context.startActivity(intent);

                }
            });
        }
        if (holder instanceof beachHolder) {
            ((beachHolder)holder).title.setText(beaches.get(position).getTitle());
            Picasso.get().load(beaches.get(position).getPic()).into(((beachHolder)holder).excursionImage);
            ((beachHolder)holder).setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent(context, com.example.georg.odessaguide.activities.Beach.class);
                    intent.putExtra("cap" , beaches.get(position).getCap());
                    intent.putExtra("day1" , beaches.get(position).getDay1());
                    intent.putExtra("day2" , beaches.get(position).getDay2());
                    intent.putExtra("day3" , beaches.get(position).getDay3());
                    intent.putExtra("day4" , beaches.get(position).getDay4());
                    intent.putExtra("descr" , beaches.get(position).getDescr());
                    intent.putExtra("pic" , beaches.get(position).getPic());
                    intent.putExtra("location" , beaches.get(position).getLocation());

                    context.startActivity(intent);
                }
            });
            }
    }

    @Override
    public int getItemCount() {
        if (categories == null&religions == null& beaches == null) {
            return excursions.size();
        }
        if ( excursions == null&religions == null& beaches == null) {
            return categories.size();
        }
        if (excursions == null &categories == null&religions == null) {
            return  beaches.size();
        }
        if (excursions == null &categories == null& beaches == null ) {
            return  religions.size();
        }
        return -1;
    }

    public static class rvHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        ImageView excursionImage;
        TextView title;
        private OnItemClickListener itemClickListener;
        CardView exc_category;

        public rvHolder(View itemView1) {
            super(itemView1);
            excursionImage = itemView.findViewById(R.id.category_img);
            title = itemView.findViewById(R.id.category_title);
            exc_category = itemView.findViewById(R.id.exc_category);
            exc_category.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view , getAdapterPosition());
        }

        public void setOnItemClickListener (OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    public static class gemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView excursionImage;
        TextView excursionTitle, duration;
        private OnItemClickListener itemClickListener;
        CardView exc_maincard;

        public gemHolder(View itemView2) {
            super(itemView2);
            excursionImage = itemView.findViewById(R.id.excursionImage);
            excursionTitle = itemView.findViewById(R.id.excursionTitle);
            duration = itemView.findViewById(R.id.duration);
            exc_maincard = itemView.findViewById(R.id.exc_maincard);
            exc_maincard.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view , getAdapterPosition());
        }

        public void setOnItemClickListener (OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    public static class relHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView relImage;
        TextView excursionTitle2, duration2, cost;
        private OnItemClickListener itemClickListener;
        CardView exc_religion;
        public relHolder(View itemView3) {
            super(itemView3);
            relImage = itemView.findViewById(R.id.relImage);
            excursionTitle2 = itemView.findViewById(R.id.excursionTitle2);
            duration2 = itemView.findViewById(R.id.duration2);
            cost = itemView.findViewById(R.id.cost);
            exc_religion = itemView.findViewById(R.id.exc_religion);
            exc_religion.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view , getAdapterPosition());
        }

        public void setOnItemClickListener (OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    public static class beachHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        ImageView excursionImage;
        TextView title;
        private OnItemClickListener itemClickListener;
        CardView beach_card;

        public beachHolder(View itemView1) {
            super(itemView1);
            excursionImage = itemView.findViewById(R.id.beach_main);
            title = itemView.findViewById(R.id.textView2);
            beach_card = itemView.findViewById(R.id.beach_card);
            beach_card.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view , getAdapterPosition());
        }

        public void setOnItemClickListener (OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }
}
