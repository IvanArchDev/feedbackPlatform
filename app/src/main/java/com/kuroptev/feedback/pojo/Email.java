package com.kuroptev.feedback.pojo;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "email")
public class Email {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    public String email;
    public String dealer;
    public String department;

    public Email(String email, String dealer, String department) {
        this.email = email;
        this.dealer = dealer;
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
