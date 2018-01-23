package com.kuroptev.feedback.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kuroptev.feedback.pojo.Email;

import java.util.List;

@Dao
public interface EmailDao {

    @Query("SELECT * FROM email")
    List<Email> getAllEmails();

    @Query("SELECT * FROM email WHERE  dealer = :dealer AND department = :department")
    List<Email> getEmailsByDepartment(String dealer, String department);

    @Insert
    void addEmail(Email... emails);

    @Delete
    void deleteEmail(Email... emails);
}
