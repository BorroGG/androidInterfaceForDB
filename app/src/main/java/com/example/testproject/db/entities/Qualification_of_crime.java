package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Qualification_of_crime {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id_qualification_of_crime;
    public String article;
    public String sign;
    public String part;
    public String item;

    public Qualification_of_crime() {
    }
}
