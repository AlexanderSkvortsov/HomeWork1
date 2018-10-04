package com.example.skvortsov.homework1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.skvortsov.homework1.Acync.AsynkTask.LoaderTask;
import com.example.skvortsov.homework1.DAO.DaoTaskInsert;
import com.example.skvortsov.homework1.Model.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AddEventActivity extends Activity {
    private ProgressDialog dialog ;
    private EditText nameEditText;
    private EditText nameEditBody;
    private Button okButton;
    List<Event> daoEvents = new ArrayList<>();
    private int itemCount=0;

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        dialog = new ProgressDialog(this);

        nameEditText = findViewById(R.id.editEventName);
        nameEditText.setText("Text "+ListFragment.numberOfItems);

        nameEditBody = findViewById(R.id.editEventBody);
        nameEditBody.setText("Body "+ListFragment.numberOfItems);


        okButton = findViewById(R.id.buttonOk);

/*
        new Thread(new Runnable() {
            List<Event> newEvents = new ArrayList<>();
            @Override
            public void run() {
                newEvents = App.getInsance()
                        .getEventDatabase()
                        .eventDao()
                        .getEvent();
                daoEvents = newEvents;
                itemCount = daoEvents.size();
                nameEditText.setText("Name"+itemCount +1);
                nameEditBody.setText("Body"+itemCount +1);
            }
        }).start();
*/


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                // run the sentence in a new thread
              new Thread(new Runnable() {
                    @Override
                    public void run() {
                        App.getInsance()
                                .getEventDatabase()
                                .eventDao()
//                                .allowMainThreadQueries()
                                .insertEvents(new Event(nameEditText.getText().toString(), nameEditBody.getText().toString()));
                    }
                }).start();
*/

                DaoTaskInsert daoTaskInsert = new DaoTaskInsert(new DaoTaskInsert.OnInsertDoneListener(){

                    @Override
                    public void onEndInsert() {
                        // do nothing
                       dialog.dismiss();
                       finish();
                    }


                    @Override
                    public void onStartInsert() {

                        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        dialog.setMessage("Inserting. Please wait...");
                        dialog.setIndeterminate(true);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();

                    }

                 },
                  Collections.singletonList(new Event(nameEditText.getText().toString(),new Date(),new Date(),nameEditBody.getText().toString())));

//                 new Event(nameEditText.getText().toString(),new Date(),new Date(),nameEditBody.getText().toString()));

                daoTaskInsert.execute();
/*
                new Thread(new Runnable() {
                    List<Event> newEvents = new ArrayList<>();
                    @Override
                    public void run() {
                        newEvents = App.getInsance()
                                                    .getEventDatabase()
                                                    .eventDao()
                                                    .getEvent();
                        daoEvents = newEvents;
                    }
                }).start();

                ListFragment.getRecyclerViewAdapter().setEvents(daoEvents);

*/

            }

        });
    }


}
