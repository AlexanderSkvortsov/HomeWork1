package com.example.skvortsov.homework1.DAO;

import android.os.AsyncTask;

import com.example.skvortsov.homework1.App;
import com.example.skvortsov.homework1.Model.Event;

import java.util.List;

public class DaoTaskDelete extends AsyncTask<Void, Integer, Void> {

    //    private Event daoEvent;
    private Event daoEvent;
    private DaoTaskDelete.OnDeleteDoneListener onDeleteDoneListener;
    public interface OnDeleteDoneListener {
        void onEndDelete();
        void onStartDelete();
    }

    /*
        public DaoTaskInsert(OnInsertDoneListener onInsertDoneListener, Event daoEvent)
        {
            this.daoEvent = daoEvent;
            this.onInsertDoneListener=onInsertDoneListener;
        }
    */
    public DaoTaskDelete(DaoTaskDelete.OnDeleteDoneListener onDeleteDoneListener, Event daoEvent)
    {
        this.daoEvent = daoEvent;
        this.onDeleteDoneListener=onDeleteDoneListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onDeleteDoneListener.onStartDelete();
    }

    @Override
    // thread stop
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        onDeleteDoneListener.onEndDelete();
    }


    @Override
    protected Void doInBackground(Void... voids) {

        App.getInsance()
                .getEventDatabase()
                .eventDao()
                .delete(daoEvent);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
