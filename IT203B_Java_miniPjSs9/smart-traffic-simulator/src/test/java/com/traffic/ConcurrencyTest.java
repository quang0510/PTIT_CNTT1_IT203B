package com.traffic;

import com.traffic.engine.IntersectionManager;
import com.traffic.entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConcurrencyTest {

    @Test
    void stressTest() throws InterruptedException {
        IntersectionManager manager = new IntersectionManager();
        ExecutorService executor = Executors.newFixedThreadPool(50);
        List<Throwable> errors = Collections.synchronizedList(new ArrayList<>());
        int vehicleCount = 100;

        for (int i = 0; i < vehicleCount; i++) {
            Vehicle v = new StandardVehicle("Test-" + i, 50, VehicleType.CAR, manager);
            executor.submit(() -> {
                try {
                    manager.enterIntersection(v);
                } catch (Throwable ex) {
                    errors.add(ex);
                }
            });
        }

        executor.shutdown();
        assertTrue(executor.awaitTermination(20, TimeUnit.SECONDS), "Executor should finish all vehicle tasks");
        assertTrue(errors.isEmpty(), "No collision-related exception should occur under lock protection");
        assertEquals(vehicleCount, manager.getTotalPassedVehicles(), "All vehicles should pass intersection");
    }
}