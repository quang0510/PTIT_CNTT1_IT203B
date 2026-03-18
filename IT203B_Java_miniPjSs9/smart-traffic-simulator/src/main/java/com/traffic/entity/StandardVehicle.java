package com.traffic.entity;

import com.traffic.engine.IntersectionManager;

/**
 * Xe bình thường
 */
public class StandardVehicle extends Vehicle {

    public StandardVehicle(String id, int speed, VehicleType type, IntersectionManager manager) {
        super(id, speed, type, manager);
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
