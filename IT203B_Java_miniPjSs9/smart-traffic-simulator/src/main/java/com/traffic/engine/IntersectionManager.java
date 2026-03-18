package com.traffic.engine;

import com.traffic.entity.Vehicle;
import com.traffic.exception.CollisionException;
import com.traffic.util.LoggerUtils;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Quản lý giao lộ (Critical Section)
 */
public class IntersectionManager {

    // Fair lock: xe vào giao lộ theo thứ tự chờ để giảm starvation.
    private final ReentrantLock lock = new ReentrantLock(true);
    private int totalPassedVehicles = 0;

    public void enterIntersection(Vehicle v) {
        // Critical section: mỗi thời điểm chỉ một xe được đi qua giao lộ.
        lock.lock();
        try {
            LoggerUtils.log(v.getId() + " is passing intersection");
            Thread.sleep(100);
            incrementPassedCount();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CollisionException("Interrupted while crossing: " + v.getId());
        } finally {
            lock.unlock();
        }
    }

    private synchronized void incrementPassedCount() {
        // Đồng bộ để bộ đếm chính xác khi nhiều thread cập nhật.
        totalPassedVehicles++;
    }

    public synchronized int getTotalPassedVehicles() {
        return totalPassedVehicles;
    }
}