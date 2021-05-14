package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Damage_category {
    @PrimaryKey
    @NonNull
    public int id_damage_category;
    public String type_of_damage;

    public Damage_category() {
    }
}
