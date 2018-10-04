package com.example.skvortsov.homework1.Model;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;
// описание базы version 1 без дат
@Database(entities = Event.class,version = 2, exportSchema = false)

public abstract class EventDatabase extends RoomDatabase {

    public  abstract EventDao eventDao();


}
