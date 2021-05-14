package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Department {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id_department;
    public String title_department;

    public Department() {
    }
}
