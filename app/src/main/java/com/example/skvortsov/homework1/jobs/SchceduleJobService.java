package com.example.skvortsov.homework1.jobs;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

import com.example.skvortsov.homework1.notifications.ScheduleNotificationManager;

public class SchceduleJobService extends JobService {
    @Override


    // вызывается когда вызывается job, false - после работы остановились, true - работа продолжена
    public boolean onStartJob(JobParameters jobParameters) {

        //jobFinished();
        switch (jobParameters.getJobId())
        {
            case 1:
                //Toast.makeText(getApplicationContext(),"Start Time Jobs", Toast.LENGTH_LONG).show();
                ScheduleNotificationManager.showNotification(getApplicationContext(),1,"Schedule", "Start Job","12345\r\n12345\n12345\n12345\n");
                break;
            case 2:
                //Toast.makeText(getApplicationContext(),"End Time Jobs", Toast.LENGTH_LONG).show();
                ScheduleNotificationManager.showNotification(getApplicationContext(),1,"Schedule", "End Job","66666\r\n66666\n66666\n66666\n");
                break;

        }
        return false;
    }

    @Override
    // вызывается когда завершается job, false - не перезапускается , true - перезапускается c учетом backoff
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
