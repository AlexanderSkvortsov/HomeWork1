package com.example.skvortsov.homework1.DAO;

import android.os.AsyncTask;

import com.example.skvortsov.homework1.App;
import com.example.skvortsov.homework1.ListFragment;
import com.example.skvortsov.homework1.Model.Event;

import java.util.ArrayList;
import java.util.List;

public class DaoTaskInsert extends AsyncTask<Void, Integer, Void> {

    private Event daoEvent;
    private OnInsertDoneListener onInsertDoneListener;
    public interface OnInsertDoneListener {
        void onEndInsert();
        void onStartInsert();
    }


    public DaoTaskInsert(OnInsertDoneListener onInsertDoneListener, Event daoEvent)
    {
        this.daoEvent = daoEvent;
        this.onInsertDoneListener=onInsertDoneListener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onInsertDoneListener.onStartInsert();
    }

    @Override
    // thread stop
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        onInsertDoneListener.onEndInsert();
    }


    @Override
    protected Void doInBackground(Void... voids) {

        App.getInsance()
        .getEventDatabase()
        .eventDao()
        .insertEvents(daoEvent);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
