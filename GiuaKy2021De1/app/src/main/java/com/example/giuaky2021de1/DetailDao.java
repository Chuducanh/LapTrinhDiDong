package com.example.giuaky2021de1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface DetailDao {
    @Query("SELECT * FROM details")
    List<Detail> getAll();

    @Query("SELECT * FROM details WHERE id = :id")
    Detail findContactById(int id);

    @Insert
    void insert(Detail... details);

    @Delete
    void delete(Detail detail);

    @Query("DELETE FROM details")
    void deleteAllDetails();
}
