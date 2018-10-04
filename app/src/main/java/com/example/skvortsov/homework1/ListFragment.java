package com.example.skvortsov.homework1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.skvortsov.homework1.DAO.DaoTaskInsert;
import com.example.skvortsov.homework1.DAO.DaoTaskLoadAll;
import com.example.skvortsov.homework1.Model.Event;
import com.example.skvortsov.homework1.jobs.ScheduleActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//new
public class ListFragment extends Fragment implements OnItemClickListener {

    private static RecyclerView  recyclerView;
    private final List<Event> eventList = new ArrayList<>();
    private FloatingActionButton floatingActionButton;
    ProgressDialog dialog ;
    ListFragment listFragment;
    static int numberOfItems=0;

    //new
    public void onItemClick(Event event) {
        /*
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);
        intent.putExtra(EventInfoActivity.ITEM_BUNDLE_NAME, event.getEventName());
        startActivity(intent);
        */
        Intent intent = new Intent(getActivity(), ScheduleActivity.class);
        startActivity(intent);
    }

    public static EventAdapter getRecyclerViewAdapter()
    {
        return (EventAdapter) recyclerView.getAdapter();
    }

    public ListFragment() {
        listFragment = this;
    }

    // as other
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("TEST", "TEST1");
        View view = null;
        try {
            view = inflater.inflate(R.layout.f_list, container, false);
        } catch (Exception ex) {
            Log.e("TEST", "TEST");
        } finally {

            return view;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.list);
        dialog= new ProgressDialog(this.getContext()); // this = YourActivity

        //Button button = view.findViewById(R.id.change_list_button);

        floatingActionButton  = view.findViewById(R.id.add_event_floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddEventActivity.class));
            }
        });

        for (int i = 0; i < 100; i++)
            eventList.add((new Event("Test " + i, new Date(), new Date(), "Body " + i)));
            //eventList.add((new Event("Test " + i, "Body " + i)));

        final EventAdapter eventAdapter = new EventAdapter(eventList, this);

       // recyclerView.setAdapter(new EventAdapter(eventList, this));
        recyclerView.setAdapter(eventAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(),linearLayoutManager.getOrientation());

        recyclerView.addItemDecoration(decoration);
/*
        // change list here
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Event> newEvents = new ArrayList<>();

                if (( MainActivity.getChangeStep() & 1) == 0) {

                    for (int i = 99; i >= 0; i--)
                        newEvents.add((new Event("Test " + i, new Date(), new Date(), "Body " + i)));
                        //newEvents.add((new Event("Test " + i,  "Body " + i)));
                }
                else
                    for (int i = 0; i < 100; i++)
                        newEvents.add((new Event("Test " + i, new Date(), new Date(), "Body " + i)));
                       // newEvents.add((new Event("Test " + i, "Body " + i)));

                MainActivity.setNewStep();

               eventAdapter.setEvents(newEvents);
            }
        });
        */
    }


    @Override
    public void onResume()
    {
        super.onResume();
        final DaoTaskLoadAll daoTaskLoadAll = new DaoTaskLoadAll(new DaoTaskLoadAll.OnLoadDoneListener(){
            @Override
            public void onEndLoad(List<Event> events) {
                dialog.dismiss();
                numberOfItems = events.size();
                ListFragment.getRecyclerViewAdapter().setEvents(events);
            }

            @Override
            public void onStartLoad() {
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage("Loading. Please wait...");
                    dialog.setIndeterminate(true);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                }
        });

        daoTaskLoadAll.execute();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // for fragments
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //
}
