package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.testproject.db.entities.Log;

@Dao
public interface LogDao {

    @Insert
    long[] insertAll(Log... logs);
}
