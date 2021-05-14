package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testproject.db.entities.Judge;
import com.example.testproject.db.entities.Organ_employee;

import java.util.List;


@Dao
public interface JudgeDao {

    @Insert
    void insert(Judge user);

    @Query("INSERT INTO Judge VALUES" +
            "('102101', 'Худякова', 'Елизавета', 'Николаевна', '$2y$10$HZ26VSOm3AuNt9nLhQ0ij.v1vxT21COuMLxBHO3AuJsXPHS/h89iK', 'khudyakova_elizaveta')," +
            "('102201', 'Новицкий', 'Федор', 'Афанасьевич', '$2y$10$pzaJYbt4IIy3tk63BxIfr.3KWxv0zVo18IkhWIX/Dsgao2HWtsq/q', 'novitsky_fedor')," +
            "('112101', 'Рудникова', 'Марианна', 'Максимовна', '$2y$10$wSzXJPFD.Y6cm/Nx6SEBg.9Ol.NQ3i07oPk5xM4FCuOnYp3YMAwgm', 'rudnikova_marianna')," +
            "('142303', 'Безукладников', 'Максим', 'Антонович', '$2y$10$f9a8Z0gjEcs1kipOLxthtuoBTENsqRUrp7KBW2r6A7o1GcUPmQvI2', 'bezukladnikov_maxim')")
    void insertStartData();

    @Query("SELECT * FROM Judge")
    List<Judge> getAll();
}
