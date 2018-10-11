package com.example.skvortsov.homework1;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;

import com.example.skvortsov.homework1.DAO.DaoTaskDelete;
import com.example.skvortsov.homework1.Model.Event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


public class UndoHandler extends Handler {

    private  Event event;
    private  static  final long UNDO_DELAY  = 5000;
    private Snackbar snackbar;
    private DaoTaskDelete.OnDeleteDoneListener onDeleteDoneListener;


    public  void stop()
    {
        removeCallbacks(undoRunnable);
    }

    private Runnable undoRunnable = new Runnable() {
        @Override
        public void run() {
            // удаляем из firebase
            App.getInsance().getFirebaseCollection()
                    .document(event.getRemoteId())
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // удаляем в локальной базе если Success
                            final DaoTaskDelete daoTaskDelete = new DaoTaskDelete(onDeleteDoneListener, event);

                            daoTaskDelete.execute();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                }
            });
            snackbar.dismiss();
        }
    };

    public UndoHandler (Looper looper, Event event, DaoTaskDelete.OnDeleteDoneListener onDeleteDoneListener)
    {
        super(looper);
        this.event = event;
        this.onDeleteDoneListener = onDeleteDoneListener;
    }
    public  void  startUndo(Snackbar snackbar)
    {
        this.snackbar=snackbar;
        postDelayed(undoRunnable, UNDO_DELAY);
    }
}
