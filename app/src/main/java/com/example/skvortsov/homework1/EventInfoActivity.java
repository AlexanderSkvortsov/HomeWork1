package com.example.skvortsov.homework1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.skvortsov.homework1.Acync.AsynkTask.LoaderTask;
import com.example.skvortsov.homework1.Acync.LoaderService;
import com.example.skvortsov.homework1.Acync.handlerthread.Loader;
import com.example.skvortsov.homework1.Acync.handlerthread.LoaderThread;
import com.example.skvortsov.homework1.sharedreferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventInfoActivity extends AppCompatActivity {
    private Button button ;
    private ProgressBar progressBar ;
    private ProgressBar progressBar1 ;
    private LoaderThread loaderThread;

    public static final String ITEM_BUNDLE_NAME= "ItemName";

    // handler UI потока
    Handler handler = new Handler(Looper.getMainLooper())
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Loader.LOADING_PROGRESS: {
                    int progress = (Integer) msg.obj;
                    progressBar.setProgress(progress);
                    break;
                }
                case Loader.END_LOADING: {

                    progressBar.setProgress(0);
                    break;
                }
            }
        }
    };

    Handler handler1 = new Handler(Looper.getMainLooper())
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Loader.LOADING_PROGRESS: {
                    int progress = (Integer) msg.obj;
                    progressBar1.setProgress(progress);
                    break;
                }
                case Loader.END_LOADING: {
                    progressBar1.setProgress(0);
                    break;
                }
            }
        }
    };

    //подписаться на события
    public  void onResume()
    {
        super.onResume();
        //LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);

        // фильтры на события
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LoaderService.END_LOADING);
        intentFilter.addAction(LoaderService.START_LOADING);
        intentFilter.addAction(LoaderService.LOADING_PROGRESS);
        localBroadcastManager.registerReceiver(broadcastReceiver,intentFilter);

        loaderThread = new LoaderThread();
        loaderThread.start();
        loaderThread.initLoader();
    }


    // отписаться от событий
    public void onPause()
    {
        super.onPause();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
        loaderThread.quit();
    }

    // получить событие, на которе он подписан
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(LoaderService.START_LOADING))
            {
                progressBar.setProgress(0);
            } else if (intent.getAction().equals(LoaderService.LOADING_PROGRESS)){
                progressBar.setProgress(intent.getExtras().getInt(LoaderService.PROGRESS_EXTRA, 0));
            } else if  (intent.getAction().equals(LoaderService.END_LOADING)){
                progressBar.setProgress(0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_on_click);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final TextView txtLine = findViewById(R.id.itemClickedName);
        txtLine.setText(bundle.getString(ITEM_BUNDLE_NAME, ""));

        button = findViewById(R.id.button_load_async_task);
        progressBar = findViewById(R.id.loading_progress_bar);
        progressBar1 = findViewById(R.id.loading_progress_bar1);


        // анонимный класс
        button.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {

                SharedPreferencesManager.putStringPreference(EventInfoActivity.this, "TEST", "TEST_VALUE");
                txtLine.setText(SharedPreferencesManager.getStringPreference(EventInfoActivity.this,"TEST"));
                // HandlerThread
               // loaderThread.startLoading(Loader.START_LOADING, handler,handler1  );

                // Intetnt service sample
                // для Activity
                //startService(new Intent(EventInfoActivity.this, LoaderService.class));
                // для Fragment
                // getActivity().startService(new Intent(getContext(), LoaderService.class));

                /*
                //AsyncTask sample
                LoaderTask loaderTask = new LoaderTask(new LoaderTask.OnLoadListener() {
                    @Override
                    public void onStartLoad() {
                        button.setText("LOADING");
                    }

                    @Override
                    public void onEndLoad() {
                        button.setText("LOAD ASYNC TASK");
                        progressBar.setProgress(0);
                    }

                    @Override
                    public void onProrgess(int progress) {
                        progressBar.setProgress(progress);
                    }
                });
                loaderTask.execute();
                */
            }
        });

    }
}
