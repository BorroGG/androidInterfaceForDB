package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Condition_of_committing {
    @PrimaryKey
    @NonNull
   public int id_condition_of_committing;
   public String condition;

    public Condition_of_committing() {
    }
}
