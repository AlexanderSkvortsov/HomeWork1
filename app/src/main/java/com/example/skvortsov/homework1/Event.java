package com.example.skvortsov.homework1;

import java.util.Date;

public class Event {
    private     String eventName;
    private     Date   startDate;
    private     Date   endDate;
    private     String eventBody;

   public  Event ( String eventName,
           Date startDate,
           Date endDate,
           String eventBody)
   {

       this.eventName = eventName ;
       this.startDate = startDate;
       this.endDate = endDate;
       this.eventBody = eventBody;
   }

    public String getEventName() {
        return eventName;
    }

    public String getEventBody() {
        return eventBody;
    }

    public String getEventStartDate() {
        return startDate.toString();
    }

    public String getEventEndDate() {
        return endDate.toString();
    }



}
