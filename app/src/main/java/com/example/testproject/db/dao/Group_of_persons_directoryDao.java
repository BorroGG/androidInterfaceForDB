package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.testproject.db.entities.Accused;
import com.example.testproject.db.entities.Group_of_persons_directory;

import java.util.List;


@Dao
public interface Group_of_persons_directoryDao {

    @Query("INSERT INTO Group_of_persons_directory VALUES" +
            "('0300', 'Группа лиц')," +
            "('0400', 'Группа лиц по предварительному сговору')," +
            "('0200', 'Организованная группа')," +
            "('0100', 'Преступное сообщество (организация)')," +
            "('0050', 'Группа лиц без гражданства')," +
            "('0040', 'Группа иностранцев')," +
            "('0020', 'Группа несовершенолетних')," +
            "('0001', 'Смешанная группа')," +
            "('0000', 'Без группы')")
    void insertStartData();

    @Query("SELECT * FROM Group_of_persons_directory")
    List<Group_of_persons_directory> getAll();
}
