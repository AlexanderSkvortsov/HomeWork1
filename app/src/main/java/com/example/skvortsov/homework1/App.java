package com.example.skvortsov.homework1;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.skvortsov.homework1.Model.EventDatabase;

public class App extends Application {

    private EventDatabase eventDatabase;

    private  static  App insance;

    @Override
    public void onCreate() {
        super.onCreate();
        insance = this;

        // создание базы данных Schedule
        eventDatabase = Room.databaseBuilder(this, EventDatabase.class, "Schedule").build();
    }

    public  static  App getInsance()
    {
      return  insance;
    }

    public  EventDatabase getEventDatabase()
    {
        return  eventDatabase;
    }
}
