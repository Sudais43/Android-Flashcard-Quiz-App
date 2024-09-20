package com.example.flashcardapp;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataEntry.class},version = 1,exportSchema = false)
public abstract class  Appdatabase extends RoomDatabase {

    public static final String LOG_TAG = "database";
    public static final Object LOCK = new Object();
    public static final String DATABASE_NAME = "DataEntry";

    private static Appdatabase sInstance;

    public static Appdatabase getInstance(Context context){
        if(sInstance==null){
            synchronized (LOCK){
                Log.d(LOG_TAG,"Creating table");

                sInstance = Room.databaseBuilder(context,Appdatabase.class,"DataEntry").allowMainThreadQueries().build();
            }
        }
        Log.d(LOG_TAG,"getting database instace");

        return sInstance;
    }

    public abstract dao taskdao();
    public abstract Questiondao questiondao();
}
