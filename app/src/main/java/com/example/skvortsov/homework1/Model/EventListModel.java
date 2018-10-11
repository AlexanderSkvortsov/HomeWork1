package com.example.skvortsov.homework1.Model;

import java.util.List;

public interface EventListModel {

    interface OnEventsLoaded{
        void onEventsLoaded(List<Event> events);
    }

    interface OnEventDeleted{
        void onEventDeleted();
    }


    void getAllEvents(OnEventsLoaded onEventsLoaded);

    void deleteEvent(Event event, OnEventDeleted onEventDeleted);
}
