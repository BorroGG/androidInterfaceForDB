package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Statement {
    @PrimaryKey
    @NonNull
    public int id_statement;
    public String registration_date;
    public String additional_information;
    public int id_victim; /*REFERENCES victim(id_victim),*/
    public String id_organ_employee; /*REFERENCES organ_employee(id_organ_employee)*/

    public Statement() {
    }

}
