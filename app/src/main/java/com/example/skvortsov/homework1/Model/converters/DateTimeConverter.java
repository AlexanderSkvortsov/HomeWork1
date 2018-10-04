package com.example.skvortsov.homework1.Model.converters;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeConverter {
    private static final DateFormat EVENT_DATE = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

    @TypeConverter
    public Date toDate (String date)
    {
        if (!TextUtils.isEmpty(date))
        {
            try {
                return EVENT_DATE.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return new Date();
    }

    @TypeConverter
    public String toString(Date date)
    {
        if (date != null)
        {
                return EVENT_DATE.format(date);
        }
        else
            return "";
    }
}
