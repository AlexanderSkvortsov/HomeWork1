package com.example.skvortsov.homework1.jobs;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.support.PersistableBundleCompat;
import com.example.skvortsov.homework1.App;
import com.example.skvortsov.homework1.MainActivity;
import com.example.skvortsov.homework1.R;
import com.example.skvortsov.homework1.notifications.ScheduleNotificationManager;

public class ScheduleJob extends Job {
    @NonNull

    public static String byIdName(Context context, String name) {
        Resources res = context.getResources();
        return res.getString(res.getIdentifier(name, "string", context.getPackageName()));
    }

    @Override
    protected Result onRunJob(@NonNull Params params) {

        String title, text, myBigText;
        PersistableBundleCompat requestExtras = params.getExtras();

        // byIdName(App.getInsance(),"ex_title").toString()
        title = requestExtras.getString("ex_title","");
        text = requestExtras.getString("ex_text","");
        myBigText = requestExtras.getString("ex_BigText","");

        ScheduleNotificationManager.showNotification(getContext(),1,title,text,myBigText);
        return Result.SUCCESS;
    }

    public  static void sheduleMe(long time,  String title, String text, String myBigText)
    {
        PersistableBundleCompat extras = new PersistableBundleCompat();
        extras.putString("ex_title", title);
        extras.putString("ex_text", text);
        extras.putString("ex_BigText", myBigText);

       new JobRequest.Builder(ScheduleJobCreator.SCHEDULE_TAG)
                .setExact(time)
                .setExtras(extras)
                .build()
                .schedule();
    }
}

/*
 ДЗ
В шедул ме передавать Title message bigtext
persistancebundlecompat

были разные по эти параметрам

еще в класс добавть полe description

*/