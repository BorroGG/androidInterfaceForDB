package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testproject.db.entities.Victim;

import java.util.List;


import io.reactivex.rxjava3.core.Single;

@Dao
public interface VictimDao {
    @Query("SELECT * FROM Victim")
    List<Victim> getAll();

    @Query("SELECT * FROM Victim WHERE id_victim IN (:userIds)")
    List<Victim> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Victim WHERE firstname LIKE :first AND " +
            "lastname LIKE :last LIMIT 1")
    Victim findByName(String first, String last);

    @Insert
    void insertAll(Victim... users);

    @Delete
    void delete(Victim user);

    @Query("INSERT INTO Victim(lastname, firstname, middle_name, gender, birthday, passport, citizenship, social_status, official_position, phone) VALUES" +
            "('Смирнов', 'Виктор', 'Иванович', '1', '1987-08-13', '4215098764', 'Россия', 'Наемный рабочий'," +
            "'Охранник', '79152762244')," +
            "('Эмскиха', 'Татьяна', 'Анатольевна', '2', '1982-05-19', '4560356412', 'Россия', 'Собственник (владелец предприятия, землевладелец)'," +
            "'Сфера строительства', '79849969051')," +
            "('Кумирова', 'Зоя', 'Венедиктовна', '2', '1995-09-02', '4958550474', 'Россия', 'Наемный рабочий'," +
            "'Бухгалтер', '79612305687')," +
            "('Дубин', 'Арсений', 'Федорович', '1', '1961-04-25', '4049287072', 'Россия', 'Пенсионер по выслуге лет'," +
            "'Федеральная служба судебных приставов (ФССП)', '79669617928')," +
            "('Адаксин', 'Юлиан', 'Севастьянович', '1', '1980-04-14', '4253181718', 'Россия', 'Лицо, занимающееся мелкооптовой торговлей'," +
            "'Сфера торговли', '79202681964')," +
            "('Корнейчук', 'Алена', 'Феоктистовна', '2', '1970-05-03', '4095378092', 'Россия', 'Служащий'," +
            "'Министерство юстиции', '79711747098')," +
            "('Елешева', 'Дарья', 'Адамовна', '2', '1973-02-11', '4240870382', 'Россия', 'Работник культуры и искусства'," +
            "'Сфера культуры и искусства', '79617758261')," +
            "('Привалов', 'Ефрем', 'Михаилович', '1', '1950-01-08', '4751324871', 'Нигерия', 'Пенсионер по старости'," +
            "'Фермер', '79409489066')")
    void insertStartData();
}
