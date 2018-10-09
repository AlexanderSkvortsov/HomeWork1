package com.example.skvortsov.homework1.jobs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

public class ScheduleJobCreator implements JobCreator {
    static  final  String SCHEDULE_TAG = "scheduleTag";
    static  final  String NOTIFICATION_TAG = "notificationTag";
    @Nullable
    @Override
    public Job create(@NonNull String tag) {
        switch (tag)
        {
            case SCHEDULE_TAG: return  new ScheduleJob();
            case NOTIFICATION_TAG: return  new NotificationJob();
            default: return null;
        }
    }
}
