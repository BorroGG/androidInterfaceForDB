package com.example.testproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Crime {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id_crime;
    public String number_of_criminal_case;
    public String date_and_time_of_crime;
    public int id_criminal_case; /*REFERENCES criminal_case(id_criminal_case),*/
    public int id_qualification_of_crime; /*REFERENCES qualification_of_crime(id_qualification_of_crime),*/
    public String id_type_of_group; /*REFERENCES group_of_persons_directory(id_type_of_group),*/
    public String id_category_of_crimes; /*REFERENCES category_of_crimes(id_category_of_crimes),*/
    public String id_focus_category; /*REFERENCES focus_category(id_focus_category),*/
    public String id_damage_category; /*REFERENCES damage_category(id_damage_category)*/

    public Crime() {
    }
}
