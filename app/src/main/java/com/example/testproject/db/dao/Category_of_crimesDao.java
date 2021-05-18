package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.testproject.db.entities.Accused;
import com.example.testproject.db.entities.Category_of_crimes;

import java.util.List;


@Dao
public interface Category_of_crimesDao {
    @Query("INSERT INTO Category_of_crimes VALUES" +
            "('1', 'Тяжкое')," +
            "('2', 'Небольшой тяжести')," +
            "('3', 'Средней тяжести')," +
            "('4', 'Особо тяжкое')")
    void insertStartData();

    @Query("SELECT * FROM Category_of_crimes")
    List<Category_of_crimes> getAll();
}
