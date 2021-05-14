package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Accused {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id_accused;
    public String lastname;
    public String firstname;
    public String middle_name;
    public String gender;
    public String birthday;
    public String passport;
    public String citizenship;
    public String social_status;
    public String official_position;
    public String phone;

    public Accused() {
    }
}
