package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testproject.db.entities.Statement;

import java.util.List;


import io.reactivex.rxjava3.core.Single;

@Dao
public interface StatementDao {
    @Query("SELECT * FROM Statement")
    List<Statement> getAll();

    @Query("SELECT * FROM Statement WHERE id_statement IN (:userIds)")
    List<Statement> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Statement WHERE registration_date LIKE :first AND " +
            "additional_information LIKE :last LIMIT 1")
    Statement findByName(String first, String last);

    @Insert
    void insertAll(Statement... users);

    @Delete
    void delete(Statement user);

    @Query("INSERT INTO Statement VALUES" +
            "(101, '2021-03-03', 'Рост: 170-175, среднего телосложения, волосы светлые, на вид 40-45 лет. Губы тонкие, на верхней челюсти золотой зуб.', 1, '001101')," +
            "(102, '2021-03-10', 'Рост: 180-185, худощавого телосложения, волосы русые, подбородок квадратной формы. На правой ноге на голени большой синяк.', 2, '002201')," +
            "(103, '2021-03-15', 'Рост: 172-177, среднего телосложения, волосы на голове короткие, на вид 50 лет. Лоб узкий, брови горизонтальные.', 3, '001102')," +
            "(104, '2021-03-21', 'Рост: 150-155, плотного телосложения, волосы светлые, на вид 40 лет. На правой руке обручальное кольцо, ногти короткие.', 4, '002202')," +
            "(105, '2021-03-21', 'Рост: 165-170, плотного телосложения, на вид 55 лет. На лице был наклеен пластырь телесного цвета.', 5, '002202')," +
            "(106, '2021-03-26', 'Рост: невысокий, на вид 35 лет. Татуировка на левой руке от плеча до кисти в виде дракона.', 6, '001102')," +
            "(107, '2021-04-01', 'Рост: 175-180, модельного телосложения, на вид 35 лет. Три серьги в ухе, голубые глаза, пухлые губы.', 7, '001101')," +
            "(108, '2021-04-05', 'Рост: 165-168, среднего телосложения, на вид 60-70 лет. Правый глаз искусственный (стеклянный).', 8, '002201')")
    void insertStartData();
}
