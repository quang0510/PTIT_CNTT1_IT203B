package com.traffic.engine;

import com.traffic.entity.Vehicle;
import com.traffic.exception.TrafficJamException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Quản lý hàng đợi xe
 */
public class Lane {

    private static final int MAX_CAPACITY = 50;
    // BlockingQueue là hàng đợi thread-safe cho nhiều producer/consumer.
    private final BlockingQueue<Vehicle> queue = new LinkedBlockingQueue<>(MAX_CAPACITY);

    public void addVehicle(Vehicle v) {
        if (!queue.offer(v)) {
            // Queue đầy => xem như tình huống kẹt xe.
            throw new TrafficJamException("Lane capacity exceeded for " + v.getId());
        }
    }

    public Vehicle pollVehicle() {
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }
}
