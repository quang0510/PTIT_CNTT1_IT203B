package com.traffic.pattern.factory;

import com.traffic.entity.*;
import com.traffic.engine.IntersectionManager;

import java.util.Random;

/**
 * Factory tạo xe random
 */
public class VehicleFactory {

    private static int count = 0;
    private static Random rand = new Random();

    public static Vehicle createVehicle(IntersectionManager manager) {
        count++;

        int type = rand.nextInt(4);

        switch (type) {
            case 0:
                return new StandardVehicle("Motor-" + count, 40, VehicleType.MOTORBIKE, manager);
            case 1:
                return new StandardVehicle("Car-" + count, 60, VehicleType.CAR, manager);
            case 2:
                return new StandardVehicle("Truck-" + count, 35, VehicleType.TRUCK, manager);
            default:
                return new PriorityVehicle("Ambulance-" + count, 80, manager);
        }
    }
}