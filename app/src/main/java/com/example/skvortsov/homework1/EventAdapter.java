package com.example.skvortsov.homework1;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.skvortsov.homework1.Model.Event;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private List<Event> events;
    //new
    private static OnItemClickListener itemListener;
    private ConstraintLayout cardViewLayout;

    //public  EventAdapter(List <Event> events, new OnItemClickListener())
    public  EventAdapter(List <Event> events, OnItemClickListener itemListener)
    {
        this.events = events;
        //new
        this.itemListener = itemListener;
    }


    @NonNull
    @Override
    //создать
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_event,parent,false);

           return new EventViewHolder(view);
    }

    @Override
    // дать данные
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.bind(events.get(position));
        //cardViewLayout.setBackgroundColor((MainActivity.selected_position == position) ? 0x8bc34a :0xbef67a);

      //  this.notifyDataSetChanged();
    }

    public  void setEvents(List<Event> events)
    {
        this.events.clear();
        this.events.addAll(events);
        notifyDataSetChanged();
    }

    @Override
    // возвращает число всех элементов
    public int getItemCount() {
        return events.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder
    {

//        private TextView eventNameTextView;
        private TextView eventNameTextView;
        private TextView eventBodyTextView;
        private TextView startDateTextView;
        private TextView endDateTextView;



        public EventViewHolder(View itemView) {
            super(itemView);
          //  eventNameTextView = itemView.findViewById(R.id.event_name_text_view);
                   eventNameTextView = itemView.findViewById(R.id.rv_row_EventName);
                   eventBodyTextView = itemView.findViewById(R.id.rv_row_EventBody);
                   startDateTextView = itemView.findViewById(R.id.rv_row_EventDateStart);
                   endDateTextView = itemView.findViewById(R.id.rv_row_EventDateEnd);
             //new
        }

        public void  bind(final Event event)
        {
            //eventNameTextView.setText(event.getEventName());
            eventNameTextView.setText(event.getEventName());
            eventBodyTextView.setText(event.getEventBody());
            startDateTextView.setText(event.getStartDate().toString());
            endDateTextView.setText(event.getEndDate().toString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemListener.onItemClick(event);

                }
            });

        }
    }
}
