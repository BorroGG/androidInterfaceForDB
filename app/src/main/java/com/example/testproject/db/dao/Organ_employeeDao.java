package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testproject.db.entities.Organ_employee;

import java.util.List;


import io.reactivex.rxjava3.core.Single;

@Dao
public interface Organ_employeeDao {
    @Query("SELECT * FROM Organ_employee")
    List<Organ_employee> getAll();

    @Query("SELECT * FROM Organ_employee WHERE id_organ_employee IN (:userIds)")
    List<Organ_employee> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Organ_employee WHERE firstname LIKE :first AND " +
            "lastname LIKE :last LIMIT 1")
    Organ_employee findByName(String first, String last);

    @Insert
    void insertAll(Organ_employee... users);

    @Insert
    void insert(Organ_employee user);

    @Delete
    void delete(Organ_employee user);

    @Update
    void update(Organ_employee user);

    @Query("INSERT INTO Organ_employee VALUES" +
            "('001101', 'Добролюбов', 'Тимофей', 'Степанович', 'Дежурный в ДЧ', 'Старший лейтенант', '4956879398', 1, 1, '$2y$10$u.hYaT8j30aqBZeOATdvy.U90HSxetSs0O.MNbu3Fjfi/0E6ZQ0I6', 'dobrolyubov_timofei')," +
            "('001102', 'Язов', 'Ефим', 'Федорович', 'Дежурный в ДЧ', 'Старший лейтенант', '4958843741', 1, 1, '$2y$10$GaAogRQtMA4fPdhObYqW/uvEACK2b4zNa2bc0rW/zIMarCKVuOCDW', 'yazov_efim')," +
            "('002201', 'Будаев', 'Василий', 'Прокопьевич', 'Дежурный в ДЧ', 'Старший лейтенант', '4956195717', 1, 2, '$2y$10$y6klNnimJtVq52SaP4twE.2ckNHGEYZBCwIeeP4tArPs41ssU9hpi', 'budaev_vasilii')," +
            "('002202', 'Дьяконов', 'Филипп', 'Валерьевич', 'Дежурный в ДЧ', 'Старший лейтенант', '4954796390', 1, 2, '$2y$10$NMsOApYqn34Ek5inc2jv4O0N7psf4zfJSX4/qQDYecTnRpCpY8oHu', 'dyakonov_filipp')," +
            "('003301', 'Чупракова', 'Любовь', 'Алексеевна', 'Прокурор', 'Генерал-лейтенант юстиции', '4956574461', 2, 3, '$2y$10$cOiD6niljyoijY8rvCc7Huq9Ia11rFaA0Gwq82VVwWkcGMja3Cz46', 'chuprakova_love')," +
            "('004401', 'Теплухин', 'Павел', 'Игнатьевич', 'Оперуполномоченный', 'Капитан внутренней службы', '4957714440', 1, 4, '$2y$10$uaRQAl8weA4dbAHwJmWMduLOG5T6S1HBQKYO.wIsbO48ZwPSmGsYm', 'teplukhin_pavel')," +
            "('005501', 'Райков', 'Александр', 'Егорович', 'Следователь', 'Подполковник юстиции', '4958118915', 3, 5, '$2y$10$ZYRwoSza5nI4ybgdcO6ca.QWPoDMRcytA23G/IC3fkEaTljRQwEAa', 'raikov_aleksander')")
    void insertStartData();
}
