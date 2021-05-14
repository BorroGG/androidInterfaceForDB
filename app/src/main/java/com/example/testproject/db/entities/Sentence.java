package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sentence {
    @PrimaryKey
    @NonNull
    public String id_sentence;
    public String date_of_issue;
    public String content;
    public String id_judge; /*REFERENCES judge(id_judge)*/

    public Sentence() {
    }
}
