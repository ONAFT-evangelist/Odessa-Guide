package com.example.georg.odessaguide.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.georg.odessaguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ShareFragment extends Fragment {

    private static View shareView;
    private ImageView imageView;
    private CardView cardView;
    String link = "https://drive.google.com/file/d/17EcQT5iOdCoTdbDRY1BX7QU37zy1tQyr/view?usp=sharing"
            , pic = "https://firebasestorage.googleapis.com/v0/b/odessa-guide-e4788.appspot.com/o/qr%2Fqrcode.png?alt=media&token=f536c3de-bf97-4d8f-a0c5-386a5e265cf3";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        shareView = inflater.inflate(R.layout.share_layout , container , false);
        imageView = shareView.findViewById(R.id.share_pic);
        cardView = shareView.findViewById(R.id.share_btn);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                               //возможно, лучшим решением будет заменить прямую ссылку на облако
                Uri uri = Uri.parse(link);                                 //просто копированием ее в буфер, чтобы можно было отправить кому-то
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);       //вместо того, чтоб начиналась загрузка
                startActivity(intent);                                     // тогда просто поменяю этот метод
            }
        });
        Picasso.get().load(pic).into(imageView);
        return shareView;
    }
}
