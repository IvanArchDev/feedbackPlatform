package com.kuroptev.feedback.screens;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kuroptev.feedback.R;
import com.kuroptev.feedback.adapters.RecyclerManager;
import com.kuroptev.feedback.dao.ManagersDao;
import com.kuroptev.feedback.data.FeedbackDataBase;
import com.kuroptev.feedback.pojo.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ManagerScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Manager> list;
    String department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_screen);
        department = getIntent().getExtras().getString("FLAG");
        recyclerView = (RecyclerView) findViewById(R.id.manager_screen);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            List<Manager> list= getList();
            recyclerView.setAdapter(new RecyclerManager(list, this));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<Manager> getList() throws ExecutionException, InterruptedException {
        list = new ArrayList<>();
        Task task = new Task();
        task.execute(department);
        return task.get();
    }

    class Task extends AsyncTask<String, Void, List<Manager>>{

        @Override
        protected List<Manager> doInBackground(String... strings) {
            String department = (String) strings[0];
            //return FeedbackDataBase.getDataBase(ManagerScreen.this).getManagersDao().getAllManagers();
            return FeedbackDataBase.getDataBase(ManagerScreen.this).getManagersDao().getManagersDealer(department);
        }
    }
}
