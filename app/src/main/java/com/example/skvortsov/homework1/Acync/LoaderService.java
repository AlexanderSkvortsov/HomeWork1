package com.example.skvortsov.homework1.Acync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

public class LoaderService extends IntentService {

    public  static final String START_LOADING = "startLoading";
    public  static final String LOADING_PROGRESS = "loadingProgress";
    public  static final String END_LOADING = "endLoading ";

    public static final String PROGRESS_EXTRA = "progress ";

    public LoaderService() {
        super("LOADER");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        LocalBroadcastManager localBroadcastManager= LocalBroadcastManager.getInstance(getApplicationContext());
        localBroadcastManager.sendBroadcast(new Intent(START_LOADING));
        for (int i =0;i<100;i++)
        {
            try {
                Thread.sleep(50);
                localBroadcastManager.sendBroadcast(new Intent(LOADING_PROGRESS).putExtra(PROGRESS_EXTRA,i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        localBroadcastManager.sendBroadcast(new Intent(END_LOADING));
    }
}
