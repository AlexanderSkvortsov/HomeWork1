package com.example.skvortsov.homework1.Acync.handlerthread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class LoaderThread extends HandlerThread {

    private  Loader loader;
    private  Loader loader1;

    public LoaderThread() {
        super("LOADER");
    }

    public void initLoader()
    {
        loader= new Loader(getLooper());
        loader1= new Loader(getLooper());
    }

    public void  startLoading(int what, Handler handler, Handler handler1)
    {
        loader.sendMessage(what,handler);
        loader1.sendMessage(what,handler1);
    }
}
