package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category_of_crimes {
    @PrimaryKey
    @NonNull
    public int id_category_of_crimes;
    public String category;

    public Category_of_crimes() {
    }
}
