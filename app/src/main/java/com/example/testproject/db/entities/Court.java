package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Court {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id_court;
    public String title;
    public String address;

    public Court() {
    }
}
