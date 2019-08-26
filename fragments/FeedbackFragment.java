package com.example.georg.odessaguide.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.georg.odessaguide.R;

public class FeedbackFragment extends Fragment {

    private static View FeedbackView;
    private String email = "app.father.d@gmail.com";
    private Button button;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FeedbackView = inflater.inflate(R.layout.contact_layout , container , false);
        button = FeedbackView.findViewById(R.id.btn_send);
        textView = FeedbackView.findViewById(R.id.feedback_input);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.setType("text/email");
                mail.putExtra(Intent.EXTRA_EMAIL , new String[]{email});
                mail.putExtra(Intent.EXTRA_SUBJECT, "Application Feedback");
                mail.putExtra(Intent.EXTRA_TEXT , textView.getText());
                startActivity(Intent.createChooser(mail, "Feedback"));
            }
        });

        return FeedbackView;
    }
}
