package com.traffic.entity;

import com.traffic.engine.IntersectionManager;

/**
 * Xe ưu tiên (ambulance)
 */
public class PriorityVehicle extends Vehicle {

    public PriorityVehicle(String id, int speed, IntersectionManager manager) {
        super(id, speed, VehicleType.AMBULANCE, manager);
    }

    @Override
    public int getPriority() {
        return 10;
    }
}
