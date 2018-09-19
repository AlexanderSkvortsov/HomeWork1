package com.example.skvortsov.homework1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.skvortsov.homework1.Acync.AsynkTask.LoaderTask;
import com.example.skvortsov.homework1.Acync.LoaderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventInfoActivity extends AppCompatActivity {
    private Button button ;
    private ProgressBar progressBar ;

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
    }


    // отписаться от событий
    public void onPause()
    {
        super.onPause();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
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
        TextView txtLine = findViewById(R.id.itemClickedName);
        txtLine.setText(bundle.getString("myName", ""));

        button = findViewById(R.id.button_load_async_task);
        progressBar = findViewById(R.id.loading_progress_bar);


        button.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {

                // Intetnt service sample
                // для Activity
                startService(new Intent(EventInfoActivity.this, LoaderService.class));
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
