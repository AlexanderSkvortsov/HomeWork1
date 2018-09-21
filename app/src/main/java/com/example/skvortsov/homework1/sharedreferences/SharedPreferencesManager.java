package com.example.skvortsov.homework1.sharedreferences;

import android.content.Context;
import android.content.SharedPreferences;

public  class SharedPreferencesManager {

    private static final String PREFERENCES ="preferences";

    public  static  SharedPreferences getPreferences(Context context)
    {
        return context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    public  static SharedPreferences.Editor getEditor(Context context)
    {
        return  getPreferences(context).edit();
    }

    public  static void putStringPreference(Context context , String key, String value)
    {
        getEditor(context).putString(key,value).apply();
    }

    public  static String getStringPreference(Context context , String key )
    {
        return  getPreferences(context).getString(key,"");
    }

}
