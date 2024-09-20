package com.example.flashcardapp;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface Questiondao{
    @Query("SELECT * FROM DataEntry ORDER BY RANDOM() LIMIT 1")
    DataEntry getRandomQuestion();
}
