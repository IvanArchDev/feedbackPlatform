package com.kuroptev.feedback.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kuroptev.feedback.pojo.Manager;

import java.util.List;

@Dao
public interface ManagersDao {

    @Query("SELECT * from managers")
    List<Manager> getAllManagers();

    @Query("SELECT * from managers WHERE department = :department")
    List<Manager> getManagersDealer(String department);

    @Insert
    void addManager(Manager... managers);

    @Delete
    void deleteManager(Manager... managers);
}
