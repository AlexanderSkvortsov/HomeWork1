package com.example.skvortsov.homework1.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.skvortsov.homework1.App;
import com.example.skvortsov.homework1.MainActivity;
import com.example.skvortsov.homework1.Model.Event;
import com.example.skvortsov.homework1.notifications.ScheduleNotificationManager;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseNotificatonService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        App.getInsance().getFirebaseCollection().addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if ((queryDocumentSnapshots != null) && (App.getInsance().getOnDestroyed())) {
                    for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            ScheduleNotificationManager.showNotification(getApplicationContext(), 1, "Shedule", "New Item add", dc.getDocument().toObject(Event.class).toString());
                        } else if (dc.getType() == DocumentChange.Type.MODIFIED) {
                            ScheduleNotificationManager.showNotification(getApplicationContext(), 1, "Shedule", "Item modified", dc.getDocument().toObject(Event.class).toString());

                        }
                    }
                }
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }
}
