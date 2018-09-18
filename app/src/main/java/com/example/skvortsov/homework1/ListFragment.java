package com.example.skvortsov.homework1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

//new
public class ListFragment extends Fragment implements OnItemClickListener {

    private RecyclerView recyclerView;
    private final List<Event> eventList = new ArrayList<>();

    //new
    public void onItemClick(Event event) {
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);
        intent.putExtra("myName", event.getEventName());
        startActivity(intent);
    }


    public ListFragment() {

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
        for (int i = 0; i < 100; i++)
            eventList.add((new Event("Test " + i, new Date(), new Date(), "Body " + i)));

        recyclerView.setAdapter(new EventAdapter(eventList, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    @Override
    public void onResume() {
        super.onResume();
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
