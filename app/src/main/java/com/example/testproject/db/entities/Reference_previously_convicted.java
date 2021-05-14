package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Reference_previously_convicted {
    @PrimaryKey
    @NonNull
    public String id_conviction;
    public String condition;

    public Reference_previously_convicted() {
    }
}
