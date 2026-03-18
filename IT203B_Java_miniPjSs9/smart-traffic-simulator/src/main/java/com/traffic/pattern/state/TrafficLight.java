package com.traffic.pattern.state;

import com.traffic.pattern.observer.Observer;
import com.traffic.pattern.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Context + Subject
 */
public class TrafficLight implements Subject {

    private TrafficLightState state;
    private List<Observer> observers = new ArrayList<>();

    public void setState(TrafficLightState state) {
        // Mỗi lần đổi state sẽ phát tín hiệu cho toàn bộ xe đang đăng ký.
        this.state = state;
        notifyObservers(state.getName());
    }

    public String getCurrentSignal() {
        if (state == null) {
            return "UNKNOWN";
        }
        return state.getName();
    }

    public void changeState() {
        // Ủy quyền logic chuyển trạng thái cho từng concrete State.
        state.handle(this);
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers(String signal) {
        for (Observer o : observers) {
            o.update(signal);
        }
    }
}