package com.example.skvortsov.homework1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EventInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_on_click);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        TextView txtLine = findViewById(R.id.itemClickedName);
        txtLine.setText(bundle.getString("myName", ""));

    }
}
