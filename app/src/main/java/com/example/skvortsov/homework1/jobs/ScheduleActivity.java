package com.example.skvortsov.homework1.jobs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.skvortsov.homework1.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ScheduleActivity extends Activity {

    private EditText startEditText;
    private EditText endEditText;
    private Button okButton;

    private int year;
    private int month;
    private int dayOfMonth;

    private static final DateFormat EVENT_DATE = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

    private Calendar calendar = Calendar.getInstance(Locale.getDefault());

    private TimePickerDialog.OnTimeSetListener startTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            calendar.set(year, month, dayOfMonth, i, i1, 0);
            startEditText.setText(EVENT_DATE.format(calendar.getTime()));
        }
    };

    private DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            ScheduleActivity.this.year = i;
            ScheduleActivity.this.month = i1;
            ScheduleActivity.this.dayOfMonth = i2;

            new TimePickerDialog(datePicker.getContext(), startTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
        }
    };


    private TimePickerDialog.OnTimeSetListener endTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            calendar.set(year, month, dayOfMonth, i, i1, 0);
            endEditText.setText(EVENT_DATE.format(calendar.getTime()));
        }
    };

    private DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            ScheduleActivity.this.year = i;
            ScheduleActivity.this.month = i1;
            ScheduleActivity.this.dayOfMonth = i2;

            new TimePickerDialog(datePicker.getContext(), endTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        startEditText = findViewById(R.id.editShedulerStartText);
        endEditText = findViewById(R.id.editShedulerEndText);
        okButton = findViewById(R.id.button_ok);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Date startDate = EVENT_DATE.parse(startEditText.getText().toString());
                    Date endDate = EVENT_DATE.parse(endEditText.getText().toString());
                    calendar = Calendar.getInstance(Locale.getDefault());

                    long startTimeMillis= startDate.getTime() - calendar.getTimeInMillis();
                    long endTimeMillis =endDate.getTime() - calendar.getTimeInMillis();

                    ComponentName jobServiceName = new ComponentName(v.getContext(), SchceduleJobService.class);
                    JobInfo startJobInfo = new JobInfo.Builder(1, jobServiceName)
                            .setMinimumLatency(startTimeMillis)//.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                            .build();

                    JobInfo endJobInfo = new JobInfo.Builder(2, jobServiceName)
                            .setMinimumLatency(endTimeMillis)//.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)

                            .build();

                    JobScheduler jobScheduler = (JobScheduler) v.getContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
                    int firstJob = jobScheduler.schedule(startJobInfo);
                    int secondJob = jobScheduler.schedule(endJobInfo);

                    if (firstJob == JobScheduler.RESULT_SUCCESS &&
                            secondJob == JobScheduler.RESULT_SUCCESS) {
                        Toast.makeText(v.getContext(), "Jobs Scheduled", Toast.LENGTH_LONG).show();
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        startEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            // event как нажали
            // Action_Down просто нажали
            // Action_Up убрали
            // Action_Move двинули пальцем
            //  false обработали и передали глубже
            //  true обработали и больше не нужен (глубже не передаем)
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    calendar = Calendar.getInstance(Locale.getDefault());
                    new DatePickerDialog(view.getContext(),
                            startDateSetListener,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)).show();
                    return true;
                }

                return false;
            }
        });

        endEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            // event как нажали
            // Action_Down просто нажали
            // Action_Up убрали
            // Action_Move двинули пальцем
            //  false обработали и передали глубже
            //  true обработали и больше не нужен (глубже не передаем)
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    calendar = Calendar.getInstance(Locale.getDefault());
                    new DatePickerDialog(view.getContext(),
                            endDateSetListener,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)).show();
                    return true;
                }

                return false;
            }
        });
    }


}
