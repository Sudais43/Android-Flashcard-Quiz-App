package com.example.flashcardapp;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface dao {

    @Query("SELECT * FROM DataEntry")
    List<DataEntry> loadalltask();

    @Insert
    void insertTask(DataEntry taskEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(DataEntry taskEntity);

    @Delete
    void deleteTask(DataEntry taskEntity);
}
