package com.example.skvortsov.homework1.DAO;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.skvortsov.homework1.App;
import com.example.skvortsov.homework1.Model.Event;

import java.util.ArrayList;
import java.util.List;

public class DaoTaskLoadAll extends AsyncTask <Void, Integer, Void> {

    private List<Event> daoEvents = new ArrayList<>();
    private OnLoadDoneListener onLoadDoneListener;

    @Override
    protected Void doInBackground(Void... voids) {

        daoEvents = App.getInsance()
                .getEventDatabase()
                .eventDao()
                .getEvent();
/*
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onLoadDoneListener.onStartLoad();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        onLoadDoneListener.onEndLoad(daoEvents);
    }

    public interface OnLoadDoneListener {
        void onEndLoad(List<Event> events);
        void onStartLoad();
    }

    public List<Event> getDaoEvents()
    {
        return this.daoEvents;
    }

    public DaoTaskLoadAll(OnLoadDoneListener onLoadDoneListener)
    {
        this.onLoadDoneListener=onLoadDoneListener;
    }
}
