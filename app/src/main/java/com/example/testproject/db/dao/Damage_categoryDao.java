package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;



@Dao
public interface Damage_categoryDao {

    @Query("INSERT INTO Damage_category VALUES" +
            "('1', 'Значительный')," +
            "('2', 'Крупный')," +
            "('3', 'Особо крупный')")
    void insertStartData();
}
