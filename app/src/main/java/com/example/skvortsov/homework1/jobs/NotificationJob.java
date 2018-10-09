package com.example.skvortsov.homework1.jobs;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.support.PersistableBundleCompat;
import com.example.skvortsov.homework1.services.FirebaseNotificatonService;

// Запуск сервиса за контролем нашей базы FireBase
public class NotificationJob extends Job {
    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {

        getContext().startService(new Intent( getContext(), FirebaseNotificatonService.class));
     return  Result.SUCCESS;
    }
    public  static void sheduleMe()
    {
        new JobRequest.Builder(ScheduleJobCreator.NOTIFICATION_TAG)
                .startNow()
                .build()
                .schedule();
    }

}
