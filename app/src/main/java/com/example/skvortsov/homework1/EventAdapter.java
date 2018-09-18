package com.example.skvortsov.homework1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private List<Event> events;
    //new
    private static OnItemClickListener itemListener;

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
      //  this.notifyDataSetChanged();
    }

    public  void setEvents(List<Event> events)
    {
        this.events.clear();
        this.events.addAll(events);
    }

    @Override
    // возвращает число всех элементов
    public int getItemCount() {
        return events.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder
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
            startDateTextView.setText(event.getEventStartDate());
            endDateTextView.setText(event.getEventEndDate());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemListener.onItemClick(event);
                }
            });

        }
    }
}
