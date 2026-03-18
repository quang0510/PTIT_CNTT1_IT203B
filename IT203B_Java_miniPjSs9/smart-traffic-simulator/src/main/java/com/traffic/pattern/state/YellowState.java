package com.traffic.pattern.state;

public class YellowState implements TrafficLightState {

    @Override
    public void handle(TrafficLight context) {
        sleep(2000);
        context.setState(new RedState());
    }

    @Override
    public String getName() {
        return "YELLOW";
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (Exception e) {}
    }
}