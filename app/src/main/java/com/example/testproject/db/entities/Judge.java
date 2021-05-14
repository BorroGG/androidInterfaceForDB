package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class Judge implements Serializable {
    @PrimaryKey
    @NonNull
    public String id_judge;
    public String lastname;
    public String firstname;
    public String middle_name;
    public String password;
    public String login;

    public Judge() {
    }
}
