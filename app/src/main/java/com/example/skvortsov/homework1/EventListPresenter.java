package com.example.skvortsov.homework1;

import android.os.Looper;

import com.example.skvortsov.homework1.Model.Event;
import com.example.skvortsov.homework1.Model.EventListModel;

import java.util.List;

public class EventListPresenter implements ListContract.Presenter {

    private EventListModel eventListModel;
    private ListContract.View view;


    public  EventListPresenter (EventListModel eventListModel)
    {
       this.eventListModel = eventListModel;
    }

    @Override
    public void onAddClicked() {
        view.showAddActivity();
    }

    @Override
    public void refresh() {
        if (view != null)
        eventListModel.getAllEvents(new EventListModel.OnEventsLoaded() {
            @Override
            public void onEventsLoaded(List<Event> events) {
                view.hideRefresh();
                view.showEvents(events);
            }
        });
    }

    @Override
    public void eventClicked(Event event) {
        view.showScheduleActivity();

    }

    @Override
    public void swipe(Event event, int position) {
        UndoHandler undoHandler = new UndoHandler(Looper.getMainLooper(),event, eventListModel, new EventListModel.OnEventDeleted() {
            @Override
            public void onEventDeleted() {

            }
        });
        undoHandler.startUndo(view,position);
    }


    @Override
    public void attach(final ListContract.View view) {
        this.view = view;
        eventListModel.getAllEvents(new EventListModel.OnEventsLoaded() {
            @Override
            public void onEventsLoaded(List<Event> events) {
                view.showEvents(events);
            }
        });
    }

    @Override
    public void deatach() {
        this.view = null;
    }
}
