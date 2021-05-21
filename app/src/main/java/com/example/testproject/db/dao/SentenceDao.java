package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testproject.db.entities.Sentence;

import java.util.List;


import io.reactivex.rxjava3.core.Single;

@Dao
public interface SentenceDao {
    @Query("SELECT * FROM Sentence")
    List<Sentence> getAll();

    @Query("SELECT * FROM Sentence WHERE id_sentence IN (:userIds)")
    List<Sentence> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Sentence WHERE date_of_issue LIKE :first AND " +
            "content LIKE :last LIMIT 1")
    Sentence findByName(String first, String last);

    @Query("SELECT date_of_issue FROM sentence WHERE id_sentence = :id_sent")
    String getDate_Of_Issue(int id_sent);

    @Query("SELECT id_sentence FROM sentence")
    List<Integer> getIds();

    @Insert
    void insertAll(Sentence... users);

    @Delete
    void delete(Sentence user);

    @Update
    void update(Sentence user);

    @Query("INSERT INTO Sentence VALUES" +
            "(501, '2021-04-25 16:03:20', 'Наказывается лишением свободы на срок до 10 лет.', '102101')," +
            "(502, '2021-04-29 11:04:12', 'Наказывается исправительными работами на срок до двух лет.', '102201')," +
            "(503, '2021-04-30 09:34:21', 'Наказывается штрафом осужденного за период до одного года.', '112101')," +
            "(504, '2021-05-18 10:11:43', 'Осуждается арестом на срок до четырех месяцев.', '142303')," +
            "(505, '2021-05-19 17:54:32', 'Наказывается ограничением свободы на срок до четырех лет.', '102101')," +
            "(506, '2021-05-27 12:34:23', 'Наказывается принудительными работами на срок до пяти лет с лишением права занимать определенные должности.', '102201')," +
            "(507, '2021-06-01 10:23:10', 'Наказываются лишением свободы на срок до шести лет.', '112101')," +
            "(508, '2021-06-02 09:56:43', 'Наказывается лишением свободы на срок до семи лет со штрафом в размере до пятисот тысяч рублей.', '142303')")
    void insertStartData();
}
