package com.example.skvortsov.homework1.Acync.AsynkTask;

import android.os.AsyncTask;

//джененрики входные данные и прогрсс результат
public class LoaderTask extends AsyncTask<Void, Integer, Void> {


    @Override
    // thread start
    protected void onPreExecute() {
        super.onPreExecute();
        onLoadListener.onStartLoad();
    }

    @Override
    // thead stop
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        onLoadListener.onEndLoad();
    }

    public interface OnLoadListener {
        void onStartLoad();

        void onEndLoad();

        void onProrgess(int progress);
    }

    private OnLoadListener onLoadListener;

    public LoaderTask(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(50);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        onLoadListener.onProrgess(values[0]);
    }
}
