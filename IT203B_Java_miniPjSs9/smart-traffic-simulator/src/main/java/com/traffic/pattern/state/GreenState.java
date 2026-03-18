package com.traffic.pattern.state;

public class GreenState implements TrafficLightState {

    @Override
    public void handle(TrafficLight context) {
        sleep(4000);
        context.setState(new YellowState());
    }

    @Override
    public String getName() {
        return "GREEN";
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (Exception e) {}
    }
}