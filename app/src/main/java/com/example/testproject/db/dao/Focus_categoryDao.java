package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;



@Dao
public interface Focus_categoryDao {

    @Query("INSERT INTO Focus_category VALUES" +
            "('1', 'Общеуголовное')," +
            "('2', 'Экономическое')," +
            "('3', 'Налоговое')")
    void insertStartData();
}
