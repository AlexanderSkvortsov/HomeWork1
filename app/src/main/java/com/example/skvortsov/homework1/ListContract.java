package com.example.skvortsov.homework1;

import android.app.usage.UsageEvents;

import com.example.skvortsov.homework1.Model.Event;
import com.example.skvortsov.homework1.mvp.BasePresenter;

import java.util.List;

interface ListContract {

    interface View {
        void showAddActivity();
        void showEvents(List<Event> events);
        void showScheduleActivity();
        void deleteFromList(Event event, int position);
        void restoreEvent(Event event, int position);

    }

    interface Presenter extends BasePresenter<View>{
          void onAddClicked();
          void refresh();
          void eventClicked(Event event);
          void swipe();
        }

}
