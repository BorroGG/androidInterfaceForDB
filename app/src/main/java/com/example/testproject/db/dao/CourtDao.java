package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.testproject.db.entities.Accused;
import com.example.testproject.db.entities.Court;

import java.util.List;


@Dao
public interface CourtDao {

    @Query("INSERT INTO Court(title, address) VALUES" +
            "('Арбитражный Суд города Москвы', '115191, г.Москва, Большая Тульская улица, дом 17')," +
            "('Басманный районный суд ЦАО города Москвы', '107078, г.Москва, Каланчёвская улица, дом 11, строение 1')," +
            "('Таганский районный суд ЦАО города Москвы', '109147, г.Москва, Марксистский переулок, дом 1/32')")
    void insertStartData();

    @Query("SELECT * FROM Court")
    List<Court> getAll();
}
