package com.traffic.pattern.state;

public class RedState implements TrafficLightState {

    @Override
    public void handle(TrafficLight context) {
        sleep(4000);
        context.setState(new GreenState());
    }

    @Override
    public String getName() {
        return "RED";
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (Exception e) {}
    }
}