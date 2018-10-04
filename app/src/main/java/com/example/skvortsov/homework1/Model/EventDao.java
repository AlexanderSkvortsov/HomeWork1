package com.example.skvortsov.homework1.Model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EventDao {

    @Query("SELECT * FROM event")
    List<Event> getEvent();

    @Insert
    void insertEvents(Event ... events); // "..." много параметров

    // если remID одинаковые то старое замещается новым
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllEvents(List<Event> events); // "..." много параметров


    @Delete
    void delete(Event event);


}
