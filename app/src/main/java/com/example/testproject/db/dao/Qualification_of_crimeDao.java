package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testproject.db.entities.Accused;
import com.example.testproject.db.entities.Qualification_of_crime;


@Dao
public interface Qualification_of_crimeDao {

    @Query("INSERT INTO Qualification_of_crime(article, sign, part, item) VALUES" +
            "('111', '', '2', 'д')," +
            "('158', '', '2', 'г')," +
            "('168', '', '2', '')," +
            "('159', '1', '1', '')," +
            "('239', '', '1', '')," +
            "('264', '', '2', 'а')," +
            "('240', '', '2', 'а')," +
            "('228', '1', '2', 'б')")
    void insertStartData();

    @Insert
    long[] insertAll(Qualification_of_crime... users);
}
