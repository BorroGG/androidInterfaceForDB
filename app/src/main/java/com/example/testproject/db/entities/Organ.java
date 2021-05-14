package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Organ {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id_organ;
    public String title_organ;

    public Organ() {
    }
}
