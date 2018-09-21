package com.example.skvortsov.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = ListFragment.class.getName();
    private static int changeStep = 0;

    public static int getChangeStep()
    {
        return changeStep;
    }

    public static void setNewStep()
    {
        changeStep++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        Button button = findViewById(R.id.button2);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    //((ConstraintLayout)findViewById(R.id.rootView)).removeAllViews();

                    Log.e("TEST", "EXCEPTRION1");
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.container, new ListFragment(), TAG)
                            .addToBackStack(null)
                            .commit();
                } catch (Exception ex) {
                    Log.e("TEST", "EXCEPTRION2", ex);
                }
            }
        });
/*
        Log.e("TEST", "FIRST ACTIVITY");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtEdit = findViewById(R.id.editText);
                TextView txtEdit1 = findViewById(R.id.editText2);

                Intent intent = new Intent(MainActivity.this, TestActivity2.class);
                intent.putExtra("Line1",txtEdit.getText().toString());
                intent.putExtra("Line2",txtEdit1.getText().toString());

                startActivity(intent);
            }
        });
*/
    }
}
