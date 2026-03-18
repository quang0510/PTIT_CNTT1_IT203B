package com.traffic.pattern.observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String signal);
}