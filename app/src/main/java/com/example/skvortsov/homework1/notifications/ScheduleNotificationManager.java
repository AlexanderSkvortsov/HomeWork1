package com.example.skvortsov.homework1.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.skvortsov.homework1.R;

public class ScheduleNotificationManager {
    private  static  final String CHANNEL_ID = "Schedule";

    private static Notification createNotification(Context context, String title, String text) {

        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_ac_unit)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("123\n123\n123\n123\n"))
                .build();
    }

    private static void createChannel(Context context)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // для Android 8
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public static  void  showNotification (Context context, int id, String title, String text)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // если нет то создаем
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null )
            {
                    createChannel(context);
            }
        }
        notificationManager.notify(id, createNotification(context,title,text));
    }
}
