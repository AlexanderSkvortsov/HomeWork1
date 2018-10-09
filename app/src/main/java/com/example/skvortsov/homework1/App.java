package com.example.skvortsov.homework1;

import android.app.Activity;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.evernote.android.job.JobManager;
import com.example.skvortsov.homework1.Model.Event;
import com.example.skvortsov.homework1.Model.EventDao;
import com.example.skvortsov.homework1.Model.EventDatabase;
import com.example.skvortsov.homework1.Model.migration.EventMigration2;
import com.example.skvortsov.homework1.jobs.NotificationJob;
import com.example.skvortsov.homework1.jobs.ScheduleJobCreator;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class App extends Application {

    private EventDatabase eventDatabase;
    private final MyActivityLifecycleCallbacks mCallbacks = new MyActivityLifecycleCallbacks();

    private  static  App insance;
    private FirebaseFirestore firebaseFirestore ;
    private int isOnDestroyed = 0;

    public boolean getOnDestroyed()
    {
        return isOnDestroyed == 0;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        insance = this;
        FirebaseApp.initializeApp(this);
        JobManager.create(this).addJobCreator(new ScheduleJobCreator());
        NotificationJob.sheduleMe();

        firebaseFirestore = FirebaseFirestore.getInstance();

        // создание базы данных Schedule
        eventDatabase = Room.databaseBuilder(this, EventDatabase.class, "Schedule")
//                .allowMainThreadQueries()
                .addMigrations(new EventMigration2()) // for migration 1 to 2
                .build();

        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
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

    private final class MyActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

            Log.e("","onActivityCreated:" + activity.getLocalClassName());
        }

        public void onActivityDestroyed(Activity activity) {

            Log.e("","onActivityDestroyed:" + activity.getLocalClassName());
        }

        public void onActivityPaused(Activity activity) {
            isOnDestroyed--;
            Log.e("","onActivityPaused:" + activity.getLocalClassName());
        }

        public void onActivityResumed(Activity activity) {
            Log.e("","onActivityResumed:" + activity.getLocalClassName());
        }

        public void onActivitySaveInstanceState(Activity activity,
                                                Bundle outState) {
            Log.e("","onActivitySaveInstanceState:" + activity.getLocalClassName());
        }

        public void onActivityStarted(Activity activity) {
            isOnDestroyed++;
            Log.e("","onActivityStarted:" + activity.getLocalClassName());
        }

        public void onActivityStopped(Activity activity) {

            Log.e("","onActivityStopped:" + activity.getLocalClassName());
        }
    }

}
