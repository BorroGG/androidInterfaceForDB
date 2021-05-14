package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;



@Dao
public interface CrimeDao {

    @Query("INSERT INTO Crime(number_of_criminal_case, date_and_time_of_crime, id_criminal_case, " +
            "id_qualification_of_crime, id_type_of_group, id_category_of_crimes, id_focus_category, id_damage_category) VALUES" +
            "('1', '2021-03-03 19:06:11', 501, 1, '0000', '1', '1', '1')," +
            "('1', '2021-03-10 15:17:20', 502, 2, '0000', '2', '1', '1')," +
            "('1', '2021-03-15 04:34:54', 503, 3, '0000', '3', '1', '3')," +
            "('1', '2021-03-18 16:15:08', 504, 4, '0000', '3', '2', '2')," +
            "('1', '2021-03-21 11:04:12', 505, 5, '0400', '2', '1', '1')," +
            "('1', '2021-03-26 21:47:01', 506, 6, '0000', '1', '1', '2')," +
            "('1', '2021-04-01 23:43:39', 507, 7, '0001', '2', '1', '1')," +
            "('1', '2021-04-03 18:08:29', 508, 8, '0200', '1', '1', '2')")
    void insertStartData();
}
