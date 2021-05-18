package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.testproject.db.entities.Accused;
import com.example.testproject.db.entities.Focus_category;

import java.util.List;


@Dao
public interface Focus_categoryDao {

    @Query("INSERT INTO Focus_category VALUES" +
            "('1', 'Общеуголовное')," +
            "('2', 'Экономическое')," +
            "('3', 'Налоговое')")
    void insertStartData();

    @Query("SELECT * FROM Focus_category")
    List<Focus_category> getAll();
}
