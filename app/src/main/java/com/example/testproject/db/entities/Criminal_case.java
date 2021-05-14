package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Criminal_case {
    @PrimaryKey
    @NonNull
    public int id_criminal_case;
    public String date_of_excitement;
    public String date_of_filing;
    public int id_statement; /*REFERENCES statement(id_statement),*/
    public String id_organ_employee; /*REFERENCES organ_employee(id_organ_employee),*/
    public int id_court; /*REFERENCES court(id_court),*/
    public String id_judge; /*REFERENCES judge(id_judge)*/

    public Criminal_case() {
    }
}
