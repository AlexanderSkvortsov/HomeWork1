package com.example.skvortsov.homework1.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity // определение таблицы в базе данных
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "event_name")
    private     String eventName;

    @ColumnInfo(name = "event_body")
    private     String eventBody;


    //private     Date   startDate;
    //private     Date   endDate;


   public  Event ( String eventName,
//           Date startDate,
//           Date endDate,
           String eventBody)
   {
       this.eventBody = eventBody;
       this.eventName = eventName ;
//       this.startDate = startDate;
//       this.endDate = endDate;

   }

//    public String getEventStartDate() {  return startDate.toString();  }

//    public String getEventEndDate() {     return endDate.toString();  }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventBody() {
        return eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


}
