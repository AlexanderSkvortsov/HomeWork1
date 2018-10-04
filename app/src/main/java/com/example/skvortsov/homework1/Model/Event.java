package com.example.skvortsov.homework1.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.example.skvortsov.homework1.Model.converters.DateTimeConverter;
import com.google.firebase.firestore.Exclude;

import java.util.Date;

// определение таблицы в базе данных
@Entity (indices = {@Index(value = {"remote_id"}, unique =  true)})
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    // одинаковые записи не дублируются в базе данных
    @ColumnInfo(name = "remote_id")
    private String remoteId;

    @ColumnInfo(name = "event_name")
    private String eventName;

    @ColumnInfo(name = "event_body")
    private String eventBody;

    @ColumnInfo(name = "start_date")
    @TypeConverters({DateTimeConverter.class})
    private Date startDate;

    @ColumnInfo(name = "end_date")
    @TypeConverters({DateTimeConverter.class})
    private Date endDate;


    public Event(String eventName,
                 Date startDate,
                 Date endDate,
                 String eventBody) {
        this.eventBody = eventBody;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public Event(){}

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // не включать в Firestore
    @Exclude
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


    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }
}
