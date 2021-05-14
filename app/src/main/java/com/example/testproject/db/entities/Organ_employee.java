package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Organ_employee implements Serializable {
    @PrimaryKey
    @NonNull
    public String id_organ_employee;
    public String lastname;
    public String firstname;
    public String middle_name;
    public String position;
    public String rank;
    public String phone;
    public int id_organ; /*REFERENCES organ(id_organ)*/
    public int id_department;  /*REFERENCES department(id_department)*/
    public String password;
    public String login;

    public Organ_employee() {
    }
}
