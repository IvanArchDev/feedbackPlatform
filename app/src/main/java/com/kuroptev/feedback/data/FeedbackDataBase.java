package com.kuroptev.feedback.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.kuroptev.feedback.dao.DealerDao;
import com.kuroptev.feedback.dao.EmailDao;
import com.kuroptev.feedback.dao.ManagersDao;
import com.kuroptev.feedback.pojo.Dealer;
import com.kuroptev.feedback.pojo.Email;
import com.kuroptev.feedback.pojo.Manager;

@Database(entities = {Manager.class, Dealer.class, Email.class}, version = 1, exportSchema = false)
public abstract class FeedbackDataBase extends RoomDatabase{

    private static final String DN_NAME = "FeedbackDB";
    private static volatile FeedbackDataBase dataBase;

    public static synchronized FeedbackDataBase getDataBase(Context context){
        if (dataBase == null){
            dataBase = create(context);
        }
        return dataBase;
    }

    private static FeedbackDataBase create(final Context context){
        return Room.databaseBuilder(context, FeedbackDataBase.class, DN_NAME).build();
    }

    public abstract ManagersDao getManagersDao();
    public abstract EmailDao getEmail();
    public abstract DealerDao getDealer();


}
