package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.testproject.db.entities.Organ;
import com.example.testproject.db.entities.Organ_employee;

import java.util.List;


import io.reactivex.rxjava3.core.Single;

@Dao
public interface OrganDao {

    @Query("INSERT INTO Organ(title_organ) VALUES" +
            "('Министерство внутренних дел Российской Федерации')," +
            "('Прокуратура Российской Федерации')," +
            "('Следственный комитет Российской Федерации')," +
            "('Федеральная служба безопасности Российской Федерации')")
    void insertStartData();

    @Query("SELECT * FROM Organ")
    List<Organ> getAll();
}
