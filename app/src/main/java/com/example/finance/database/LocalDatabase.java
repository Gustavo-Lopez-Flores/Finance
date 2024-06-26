package com.example.finance.database;

import com.example.finance.entities.User;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase{
    public abstract User usuarioDao();
    private static LocalDatabase INSTANCE;
    public static LocalDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, "MeuBD").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
