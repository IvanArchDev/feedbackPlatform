package com.kuroptev.feedback.pojo;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "dealer")
public class Dealer {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    public String dealer;

    public String getNameDealer() {
        return dealer;
    }

    public void setNameDealer(String nameDealer) {
        this.dealer = nameDealer;
    }
}
