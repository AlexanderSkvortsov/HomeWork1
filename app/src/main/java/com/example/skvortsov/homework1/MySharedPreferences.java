package com.example.skvortsov.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.skvortsov.homework1.sharedreferences.SharedPreferencesManager;

public class MySharedPreferences extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private EditText text1;
    private EditText text2;
    private EditText text3;

    public static final String KEY1 ="Key1";
    public static final String KEY2 ="Key2";
    public static final String KEY3 ="Key3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shared_preferences);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button1= findViewById(R.id.change_button);
        button2=findViewById(R.id.exit_button);
        text1 =findViewById(R.id.editText1);
        text2 =findViewById(R.id.editText2);
        text3 =findViewById(R.id.editText3);

    //    SharedPreferencesManager.putStringPreference(this, KEY1, "TEST_VALUE");
        text1.setText(SharedPreferencesManager.getStringPreference(this,KEY1));
        text2.setText(SharedPreferencesManager.getStringPreference(this,KEY2));
        text3.setText(SharedPreferencesManager.getStringPreference(this,KEY3));


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferencesManager.putStringPreference(MySharedPreferences.this, KEY1,text1.getText().toString());
                SharedPreferencesManager.putStringPreference(MySharedPreferences.this, KEY2,text2.getText().toString());
                SharedPreferencesManager.putStringPreference(MySharedPreferences.this, KEY3,text3.getText().toString());
                finish();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finishAndRemoveTask();

            }
        });

    }


}
