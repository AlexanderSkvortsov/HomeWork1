package com.example.skvortsov.homework1;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;

import com.evernote.android.job.JobManager;
import com.example.skvortsov.homework1.Model.Event;
import com.example.skvortsov.homework1.Model.EventDao;
import com.example.skvortsov.homework1.Model.EventDatabase;
import com.example.skvortsov.homework1.Model.migration.EventMigration2;
import com.example.skvortsov.homework1.jobs.ScheduleJobCreator;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class App extends Application {

    private EventDatabase eventDatabase;

    private  static  App insance;
    private FirebaseFirestore firebaseFirestore ;

    @Override
    public void onCreate() {
        super.onCreate();
        insance = this;
        FirebaseApp.initializeApp(this);
        firebaseFirestore = FirebaseFirestore.getInstance();

        JobManager.create(this).addJobCreator(new ScheduleJobCreator());

        // создание базы данных Schedule
        eventDatabase = Room.databaseBuilder(this, EventDatabase.class, "Schedule")
//                .allowMainThreadQueries()
                .addMigrations(new EventMigration2()) // for migration 1 to 2
                .build();
    }

    public CollectionReference getFirebaseCollection()
    {
        return firebaseFirestore.collection("events");
    }

    public  FirebaseFirestore getFirebaseStore(){ return  firebaseFirestore;};

    public  static  App getInsance()
    {
      return  insance;
    }

    public  EventDatabase getEventDatabase()
    {
        return  eventDatabase;
    }


}
