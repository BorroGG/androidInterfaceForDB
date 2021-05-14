package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.testproject.db.entities.Department;
import com.example.testproject.db.entities.Organ;

import java.util.List;


import io.reactivex.rxjava3.core.Single;

@Dao
public interface DepartmentDao {

    @Query("INSERT INTO Department(title_department) VALUES" +
            "('Отдел МВД России по району Замоскворечье города Москвы')," +
            "('Отдел МВД России по Тверскому району г. Москвы')," +
            "('Прокуратура ЦАО г. Москвы')," +
            "('Управление экономической безопасности и противодействия коррупции ГУ МВД России по г. Москве')," +
            "('Следственный отдел по Басманному району')")
    void insertStartData();

    @Query("SELECT * FROM Department")
    List<Department> getAll();
}
