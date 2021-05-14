package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;



@Dao
public interface Participants_in_crimeDao {

    @Query("INSERT INTO Participants_in_crime VALUES" +
            "(1, 1, '00', '3')," +
            "(2, 2, '08', '0')," +
            "(3, 3, '00', '1')," +
            "(4, 4, '04', '0')," +
            "(5, 5, '00', '0')," +
            "(6, 6, '00', '1')," +
            "(7, 7, '00', '0')," +
            "(8, 8, '01', '5')")
    void insertStartData();
}
