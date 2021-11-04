package com.example.testproject.db.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Log implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id_log;
    public String userLogin;
    public String type;
    public String timestamp;
    public String phoneModel;
    public String description;

    public Log() {
    }

    public Log(String type, String userLogin, String timestamp, String phoneModel, String description) {
        this.type = type;
        this.userLogin = userLogin;
        this.timestamp = timestamp;
        this.phoneModel = phoneModel;
        this.description = description;
    }
}
