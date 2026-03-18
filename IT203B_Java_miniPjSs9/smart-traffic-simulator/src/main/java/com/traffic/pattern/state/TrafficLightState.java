package com.traffic.pattern.state;

import com.traffic.pattern.state.TrafficLight;

public interface TrafficLightState {
    void handle(TrafficLight context);
    String getName();
}