package com.example.skvortsov.homework1.Acync.handlerthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class Loader extends Handler {

    public static final int START_LOADING = 0;
    public static final int LOADING_PROGRESS = 1;
    public static final int END_LOADING = 2;

    //обработчик соообщений
    private  Handler handler;

    @Override
    // обработчик сообщений
    public void handleMessage(Message msg) {
        switch (msg.what)
        {
            case START_LOADING:
                 for (int i=0;i<100;i++)
                 {
                     try {
                         Thread.sleep(50);
                         handler.obtainMessage(LOADING_PROGRESS,i).sendToTarget();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 handler.obtainMessage(END_LOADING).sendToTarget();
                break;
        }
    }


    public  Loader(Looper looper)
    {
        super(looper);
    }

    // вход handler UI
    public  void sendMessage(int what, Handler handler)
    {
            this.handler = handler;
            // получить мессадж и послать в задачу
            // создать и послать в handler, те самому себе в handle message
            obtainMessage(what).sendToTarget();
    }
}
