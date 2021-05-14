package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Group_of_persons_directory {
    @PrimaryKey
    @NonNull
    public int id_type_of_group;
    public String structure;

    public Group_of_persons_directory() {
    }
}
