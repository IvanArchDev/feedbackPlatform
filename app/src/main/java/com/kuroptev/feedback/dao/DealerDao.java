package com.kuroptev.feedback.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kuroptev.feedback.pojo.Dealer;

import java.util.List;

@Dao
public interface DealerDao {

    @Query("SELECT * from dealer")
    List<Dealer> getAllDealer();

    @Query("SELECT * from dealer WHERE dealer = :dealerTag")
    List<Dealer> getDelearByTag(String dealerTag);

    @Insert
    void addDealer(Dealer... dealers);

    @Delete
    void deleteDealer(Dealer... dealers);
}
