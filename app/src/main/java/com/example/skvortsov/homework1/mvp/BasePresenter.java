package com.example.skvortsov.homework1.mvp;

public interface BasePresenter <V>{
    void  attach(V view);

    void deatach();
}
