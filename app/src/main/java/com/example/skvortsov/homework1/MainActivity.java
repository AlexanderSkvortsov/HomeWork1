package com.example.skvortsov.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = ListFragment.class.getName();
    private static int changeStep = 0;
    public static final String EXTRA_MESSAGE = "extraMessage";

    static int selected_position = -1;

     public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_start:

                //startActivity(new Intent(this, MySharedPreferences.class));
                Intent intent1 = new Intent(MainActivity.this,MySharedPreferences.class);
                this.startActivity(intent1);
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        if (intent != null) {
            String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            if (message !=null) {
                // Capture the layout's TextView and set the string as its text
                EditText textEdit = findViewById(R.id.editText2);
                textEdit.setText(message);
            }
        }

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
