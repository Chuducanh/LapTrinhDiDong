package com.example.giuaky2022.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.giuaky2022.model.Record;

@Database(entities = Record.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RecordDao recordDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "records").allowMainThreadQueries().build();
        }
        return instance;
    }

}