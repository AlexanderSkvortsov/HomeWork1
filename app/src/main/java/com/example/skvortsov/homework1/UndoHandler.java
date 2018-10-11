package com.example.skvortsov.homework1;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;

import com.example.skvortsov.homework1.DAO.DaoTaskDelete;
import com.example.skvortsov.homework1.Model.Event;
import com.example.skvortsov.homework1.Model.EventListModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


public class UndoHandler extends Handler {

    private Event event;
    private static final long UNDO_DELAY = 5000;
    private ListContract.View view;
    private DaoTaskDelete.OnDeleteDoneListener onDeleteDoneListener;
    private EventListModel eventListModel;
    private EventListModel.OnEventDeleted onEventDeleted;


    public void stop() {
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
                            final DaoTaskDelete daoTaskDelete = new DaoTaskDelete(new DaoTaskDelete.OnDeleteDoneListener() {
                                @Override
                                public void onEndDelete() {
                                    onEventDeleted.onEventDeleted();
                                }

                                @Override
                                public void onStartDelete() {

                                }
                            }, event);

                            daoTaskDelete.execute();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                }
            });

            eventListModel.deleteEvent(event,onEventDeleted);
            view.hideSnackBar();

        }
    };

    public UndoHandler (Looper looper, Event event, EventListModel eventListModel, EventListModel.OnEventDeleted onEventDeleted)
    {
        super(looper);
        this.event = event;
        this.eventListModel= eventListModel;
        this.onEventDeleted = onEventDeleted;


    }
    public  void  startUndo(ListContract.View view, int position)
    {
        this.view=view;
        view.showSnackBar();
        view.deleteFromList(event, position);
        postDelayed(undoRunnable, UNDO_DELAY);
    }
}
