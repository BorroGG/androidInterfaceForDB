package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"id_crime", "id_accused", "id_conviction", "id_condition_of_committing"})
public class Participants_in_crime {
    @NonNull
    public int id_crime;
    @NonNull
    public int id_accused;
    @NonNull
    public String id_conviction;
    @NonNull
    public String id_condition_of_committing;

    public Participants_in_crime() {
    }
}
