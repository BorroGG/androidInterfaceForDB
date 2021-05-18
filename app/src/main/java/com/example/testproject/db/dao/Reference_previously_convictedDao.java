package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.testproject.db.entities.Accused;
import com.example.testproject.db.entities.Reference_previously_convicted;

import java.util.List;


@Dao
public interface Reference_previously_convictedDao {
    @Query("INSERT INTO Reference_previously_convicted VALUES" +
            "('00', 'Ранее не судим')," +
            "('01', 'За аналогичное преступление')," +
            "('02', 'За хищение')," +
            "('03', 'За вымогательство')," +
            "('04', 'За незаконное предпринимательство')," +
            "('05', 'За незаконную банковскую деятельность')," +
            "('06', 'За приобретение имущества')," +
            "('07', 'За сбыт имущества')," +
            "('08', 'Изготовление поддельных денег и ценных бумаг')," +
            "('09', 'Уклонение от уплаты налога')")
    void insertStartData();

    @Query("SELECT * FROM Reference_previously_convicted")
    List<Reference_previously_convicted> getAll();
}
