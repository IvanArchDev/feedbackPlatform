package com.kuroptev.feedback.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kuroptev.feedback.R;

public class FeedbackCreator extends AppCompatActivity {
    TextView tx_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_creator);
        String manager = getIntent().getExtras().getString("Manager");

        tx_view = (TextView) findViewById(R.id.titleID);
        tx_view.setText(manager);
    }
}
