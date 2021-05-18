package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Sentence implements Serializable {
    @PrimaryKey
    @NonNull
    public int id_sentence;
    public String date_of_issue;
    public String content;
    public String id_judge; /*REFERENCES judge(id_judge)*/

    public Sentence() {
    }
}
