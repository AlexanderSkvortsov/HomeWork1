package com.example.skvortsov.homework1.Model;

import android.support.annotation.NonNull;

import com.example.skvortsov.homework1.App;
import com.example.skvortsov.homework1.DAO.DaoTaskDelete;
import com.example.skvortsov.homework1.DAO.DaoTaskInsert;
import com.example.skvortsov.homework1.DAO.DaoTaskLoadAll;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EventListModelImpl implements EventListModel {

    @Override
    public void getAllEvents(final OnEventsLoaded onEventsLoaded) {

        //showProgressDialog("FireBase Loading. Please wait...");
        App.getInsance().getFirebaseCollection().get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Event> events = new ArrayList<>();
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    Event event = documentSnapshot.toObject(Event.class);
                    event.setRemoteId(documentSnapshot.getReference().getId());
                    events.add(event);
                }

               // dialog.dismiss();
                DaoTaskInsert daoTaskInsert = new DaoTaskInsert(new DaoTaskInsert.OnInsertDoneListener(){
                    @Override
                    public void onEndInsert() {
                        // do nothing
                       // dialog.dismiss();
                        final DaoTaskLoadAll daoTaskLoadAll = new DaoTaskLoadAll(new DaoTaskLoadAll.OnLoadDoneListener(){
                            @Override
                            public void onEndLoad(List<Event> events) {

                                onEventsLoaded.onEventsLoaded(events);
                                //numberOfItems = events.size();
                                //recyclerAdapter.setEvents(events);
                         //       dialog.dismiss();

                                // спрятать swipe
                           //     swipeRefreshLayout.setRefreshing(false);
                            }

                            @Override
                            public void onStartLoad() {
                             //   showProgressDialog("SQL Loading. Please wait...");
                            }
                        });

                        daoTaskLoadAll.execute();
                    }

                    @Override
                    public void onStartInsert() {
                        //showProgressDialog("Inserting. Please wait...");
                    }

                }, events);

                daoTaskInsert.execute();
            }
        });

    }

    @Override
    public void deleteEvent(final Event event, final OnEventDeleted onEventDeleted) {
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
                        },event);

                        daoTaskDelete.execute();
                    }
                });
    }

}
