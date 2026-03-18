package com.traffic;

import com.traffic.pattern.state.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrafficLightTest {

    @Test
    void testStateTransition() {
        TrafficLight light = new TrafficLight();
        light.setState(new GreenState());
        assertEquals("GREEN", light.getCurrentSignal());

        light.setState(new YellowState());
        assertEquals("YELLOW", light.getCurrentSignal());

        light.setState(new RedState());
        assertEquals("RED", light.getCurrentSignal());
    }

    @Test
    void testHandleTransitions() {
        TrafficLight light = new TrafficLight();

        light.setState(new GreenState());
        light.changeState();
        assertEquals("YELLOW", light.getCurrentSignal());

        light.changeState();
        assertEquals("RED", light.getCurrentSignal());
    }
}