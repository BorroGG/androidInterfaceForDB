package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.testproject.db.entities.Accused;
import com.example.testproject.db.entities.Damage_category;

import java.util.List;


@Dao
public interface Damage_categoryDao {

    @Query("INSERT INTO Damage_category VALUES" +
            "('1', 'Значительный')," +
            "('2', 'Крупный')," +
            "('3', 'Особо крупный')")
    void insertStartData();

    @Query("SELECT * FROM Damage_category")
    List<Damage_category> getAll();
}
