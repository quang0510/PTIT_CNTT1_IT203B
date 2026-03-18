package com.traffic.entity;

import com.traffic.pattern.observer.Observer;
import com.traffic.engine.IntersectionManager;

/**
 * Abstract class Vehicle
 * Mỗi vehicle chạy như 1 thread
 */
public abstract class Vehicle implements Runnable, Observer {

    protected String id;
    protected int speed;
    protected VehicleType type;
    protected volatile boolean canMove = false;

    protected IntersectionManager intersectionManager;

    public Vehicle(String id, int speed, VehicleType type, IntersectionManager manager) {
        this.id = id;
        this.speed = speed;
        this.type = type;
        this.intersectionManager = manager;
    }

    public abstract int getPriority();

    @Override
    public void update(String signal) {
        // Observer callback: đèn xanh thì xe thường mới được đi.
        this.canMove = signal.equals("GREEN");
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Xe ưu tiên (priority > 5) có thể vượt đèn theo yêu cầu nghiệp vụ.
                if (canMove || getPriority() > 5) { // xe ưu tiên vượt đèn
                    intersectionManager.enterIntersection(this);
                    Thread.sleep(1000);
                } else {
                    // Đợi tín hiệu mới, tránh busy-wait tốn CPU.
                    Thread.sleep(300);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }
}
