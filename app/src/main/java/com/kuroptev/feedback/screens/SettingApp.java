package com.kuroptev.feedback.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kuroptev.feedback.R;

public class SettingApp extends AppCompatActivity implements View.OnClickListener{
    private Button addUserBtn;
    private Button deleteUserBtn;
    private Button addEmailBtn;
    private Button deleteEmailBtn;
    private Button addUDealerBtn;
    private Button deleteDealerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_app);

        addUserBtn = (Button) findViewById(R.id.addUser);
        addUserBtn.setOnClickListener(this);
        deleteUserBtn = (Button) findViewById(R.id.deleteUser);
        deleteUserBtn.setOnClickListener(this);
        addEmailBtn = (Button) findViewById(R.id.addEmail);
        addEmailBtn.setOnClickListener(this);
        deleteEmailBtn = (Button) findViewById(R.id.deleteEmail);
        deleteEmailBtn.setOnClickListener(this);
        addUDealerBtn = (Button) findViewById(R.id.addDealer);
        addUDealerBtn.setOnClickListener(this);
        deleteDealerBtn = (Button) findViewById(R.id.deleteDealer);
        deleteDealerBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addUser:
                Intent intent = new Intent(this, NewManagerCreator.class);
                startActivity(intent);
        }

    }
}
