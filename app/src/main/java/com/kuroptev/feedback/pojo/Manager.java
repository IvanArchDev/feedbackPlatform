package com.kuroptev.feedback.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;
import android.widget.Switch;

import com.kuroptev.feedback.Constants;

@Entity(tableName = "managers")
public class Manager {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    public String name;
    public String surname;
    public String middleName;
    public String department;
    public String dealer;
    public String urlPhoto;

    public Uri getUrlPhoto() {

        return Uri.parse(urlPhoto);
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }


    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFullName(){
        return surname + " " + name + " " + middleName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if(department.equals("Отдел сервиса")){
            this.department = Constants.SERVICE;
        }else if(department.equals("Отдел продаж")){
            this.department = Constants.SALE;
        }else if(department.equals("Отдел ЗЧ и ДО")){
            this.department = Constants.PARTS;
        }else if(department.equals("Финансовый отдел")){
            this.department = Constants.FINANCE;
        }else if(department.equals("Малярно-кузовной цех")) {
            this.department = Constants.BODY_SHOP;
        }
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }
}

