package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testproject.db.entities.Criminal_case;

import java.util.List;


import io.reactivex.rxjava3.core.Single;

@Dao
public interface Criminal_caseDao {
    @Query("SELECT * FROM Criminal_case")
    List<Criminal_case> getAll();

    @Query("SELECT * FROM Criminal_case WHERE id_criminal_case IN (:userIds)")
    List<Criminal_case> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Criminal_case WHERE date_of_excitement LIKE :first AND " +
            "date_of_filing LIKE :last LIMIT 1")
    Criminal_case findByName(String first, String last);

    @Insert
    void insertAll(Criminal_case... users);

    @Delete
    void delete(Criminal_case user);

    @Query("INSERT INTO Criminal_case VALUES" +
            "(501, '2021-03-25', '2021-05-09', 101, '005501', 2, '102101')," +
            "(502, '2021-03-30', '2021-05-13', 102, '005501', 3, '102201')," +
            "(503, '2021-04-05', '2021-05-14', 103, '005501', 3, '112101')," +
            "(504, '2021-04-18', '2021-06-01', 104, '003301', 1, '142303')," +
            "(505, '2021-04-19', '2021-06-02', 105, '004401', 2, '102101')," +
            "(506, '2021-04-27', '2021-06-10', 106, '005501', 2, '102201')," +
            "(507, '2021-05-12', '2021-06-15', 107, '004401', 3, '112101')," +
            "(508, '2021-05-15', '2021-06-16', 108, '004401', 2, '142303')")
    void insertStartData();
}
