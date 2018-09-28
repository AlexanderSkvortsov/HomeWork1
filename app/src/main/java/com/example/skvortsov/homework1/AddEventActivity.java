package com.example.skvortsov.homework1;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.skvortsov.homework1.Model.Event;
import com.example.skvortsov.homework1.R;

public class AddEventActivity extends Activity {

    private EditText nameEditText;
    private EditText nameEditBody;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        nameEditText = findViewById(R.id.editEventName);

        nameEditBody = findViewById(R.id.editEventBody);
        okButton = findViewById(R.id.buttonOk);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getInsance()
                        .getEventDatabase()
                        .eventDao()
                        .insertEvents(new Event(nameEditText.getText().toString(),nameEditBody.getText().toString()));
            }
        });
    }

}
