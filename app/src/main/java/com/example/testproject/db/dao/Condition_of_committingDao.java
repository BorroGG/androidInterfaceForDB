package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;



@Dao
public interface Condition_of_committingDao {

    @Query("INSERT INTO Condition_of_committing VALUES" +
            "('0', 'Трезвое состояние')," +
            "('1', 'Алкогольное опьянение')," +
            "('2', 'Наркотическое опьянение')," +
            "('3', 'Токсическое опьянение')," +
            "('4', 'Хронический алкоголизм')," +
            "('5', 'Хроническая наркозависимость')," +
            "('6', 'Хроническая токсикомания')")
    void insertStartData();
}
