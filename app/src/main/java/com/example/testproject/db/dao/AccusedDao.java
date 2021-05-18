package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testproject.db.entities.Accused;

import java.util.List;

@Dao
public interface AccusedDao {
    @Query("SELECT * FROM Accused")
    List<Accused> getAll();

    @Query("SELECT * FROM Accused WHERE id_accused IN (:userIds)")
    List<Accused> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Accused WHERE firstname LIKE :first AND " +
            "lastname LIKE :last LIMIT 1")
    Accused findByName(String first, String last);

    @Insert
    long[] insertAll(Accused... users);

    @Delete
    void delete(Accused user);

    @Update
    void update(Accused user);

    @Query("INSERT INTO Accused(lastname, firstname, middle_name, gender, birthday, passport, citizenship, social_status, official_position, phone) VALUES" +
            "('Лисицын', 'Кирилл', 'Семенович', '1', '1976-03-09', '4438300865', 'Россия', 'Иждивенец'," +
            "'Иные', '79146714773')," +
            "('Вольваков', 'Герман', 'Семенович', '1', '1962-08-13', '4136261373', 'Россия', 'Наемный рабочий'," +
            "'Кассир', '79155378657')," +
            "('Аглиуллин', 'Максим', 'Лаврентиич', '1', '1969-08-23', '4681350579', 'Белоруссия', 'Лицо без постоянного источника дохода', 'Колхозник', '79889669745')," +
            "('Ломтева', 'Вера', 'Яковна', '2', '1981-07-10', '4412970191', 'Россия', 'Рантье'," +
            "'Предприниматель без образования юридического лица', '79974877120')," +
            "('Добрынин', 'Василий', 'Даниилович', '1', '1968-01-17', '4819282942', 'Россия', 'Наемный рабочий'," +
            "'Консультант, советник', '79749086538')," +
            "('Кумиров', 'Герасим', 'Леонидович', '1', '1988-09-10', '4679851105', 'Россия', 'Обучающиеся в негосударственном образовательном учреждении'," +
            "'Экономист', '79973308216')," +
            "('Лаврова', 'Оксана', 'Михайловна', '2', '1978-06-04', '4846501465', 'Россия', 'Представитель научной или творческой интеллигенции'," +
            "'Сфера науки', '79596129650')," +
            "('Водянов', 'Павел', 'Тимофеевич', '1', '1960-08-12', '4490860113', 'Росиия', 'Наемный рабочий'," +
            "'Водитель грузового транспорта', '79653275695')")
    void insertStartData();
}
