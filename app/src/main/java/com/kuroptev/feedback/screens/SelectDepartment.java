package com.kuroptev.feedback.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kuroptev.feedback.Constants;
import com.kuroptev.feedback.R;
import com.kuroptev.feedback.pojo.Manager;

public class SelectDepartment extends AppCompatActivity implements View.OnClickListener{

    ImageButton service;
    ImageButton sale;
    ImageButton osik;
    ImageButton ozch;
    ImageButton bodyshop;
    ImageButton other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_view_feedback);

        service = (ImageButton) findViewById(R.id.service);
        service.setOnClickListener(this);
        sale = (ImageButton) findViewById(R.id.sale);
        sale.setOnClickListener(this);
        osik = (ImageButton) findViewById(R.id.osik);
        osik.setOnClickListener(this);
        ozch = (ImageButton) findViewById(R.id.ozch);
        ozch.setOnClickListener(this);
        bodyshop = (ImageButton) findViewById(R.id.bodyshop);
        bodyshop.setOnClickListener(this);
        other = (ImageButton) findViewById(R.id.other);
        other.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service:
                startWithFlag(Constants.SERVICE);
                break;
            case R.id.sale:
                startWithFlag(Constants.SALE);
                break;
            case R.id.osik:
                startWithFlag(Constants.FINANCE);
                break;
            case R.id.ozch:
                startWithFlag(Constants.PARTS);
                break;
            case R.id.bodyshop:
                startWithFlag(Constants.BODY_SHOP);
                break;
            case R.id.other:
                startWithFlag(Constants.OTHER);
                break;
        }
    }

    private void startWithFlag(String flag){
        Intent intent = new Intent(this, ManagerScreen.class);
        intent.putExtra("FLAG", flag);
        startActivity(intent);
    }
}
