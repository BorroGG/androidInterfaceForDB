package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Focus_category {
    @PrimaryKey
    @NonNull
    public int id_focus_category;
    public String type_of_focus;

    public Focus_category() {
    }
}
