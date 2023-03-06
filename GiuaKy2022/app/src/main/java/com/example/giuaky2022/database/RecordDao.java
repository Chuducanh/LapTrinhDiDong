package com.example.giuaky2022.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.giuaky2022.model.Record;

import java.util.List;

@Dao
public interface RecordDao {
    @Query("SELECT * FROM records")
    List<Record> getAll();

    @Query("SELECT * FROM records WHERE id = :id")
    Record findRecordById(int id);

    @Insert
    void insert(Record... records);

    @Delete
    void delete(Record record);
}
