package com.traffic.engine;

import com.traffic.entity.Vehicle;
import com.traffic.entity.VehicleType;
import com.traffic.exception.TrafficJamException;
import com.traffic.pattern.factory.VehicleFactory;
import com.traffic.pattern.state.GreenState;
import com.traffic.pattern.state.TrafficLight;
import com.traffic.util.LoggerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Engine điều khiển toàn bộ hệ thống
 */
public class SimulationEngine {

    private ExecutorService executor;
    private TrafficLight trafficLight;
    private IntersectionManager manager;
    private Lane lane;

    private final List<Vehicle> vehicles = new ArrayList<>();
    private Thread lightThread;
    private Thread spawnThread;
    private Thread dispatchThread;
    private int trafficJamCount = 0;

    private volatile boolean running = false;

    public SimulationEngine() {
        this.executor = Executors.newFixedThreadPool(20);
        this.trafficLight = new TrafficLight();
        this.manager = new IntersectionManager();
        this.lane = new Lane();
    }

    public void start() {

        running = true;

        // init traffic light
        trafficLight.setState(new GreenState());

        // Daemon thread: chỉ điều khiển chu kỳ trạng thái đèn.
        lightThread = new Thread(() -> {
            while (running) {
                trafficLight.changeState();
            }
        }, "traffic-light-thread");
        lightThread.setDaemon(true);
        lightThread.start();

        // Producer thread: sinh xe mới và đưa vào lane queue.
        spawnThread = new Thread(() -> {
            int count = 0;
            while (running && count < 100) {
                Vehicle v = VehicleFactory.createVehicle(manager);
                synchronized (vehicles) {
                    vehicles.add(v);
                }

                try {
                    lane.addVehicle(v);
                } catch (TrafficJamException e) {
                    // Lane đầy nghĩa là kẹt xe, ghi nhận sự kiện để báo cáo.
                    synchronized (this) {
                        trafficJamCount++;
                    }
                    LoggerUtils.log("Traffic jam detected: " + e.getMessage());
                }

                count++;

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "vehicle-spawn-thread");
        spawnThread.start();

        // Consumer thread: chỉ lấy xe ra khỏi lane khi tín hiệu là GREEN.
        dispatchThread = new Thread(() -> {
            while (running) {
                if ("GREEN".equals(trafficLight.getCurrentSignal())) {
                    Vehicle next = lane.pollVehicle();
                    if (next != null) {
                        // Xe được đăng ký observer để nhận tín hiệu đèn về sau.
                        trafficLight.registerObserver(next);
                        executor.submit(next);
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "lane-dispatch-thread");
        dispatchThread.start();
    }

    /**
     * Stop toàn bộ hệ thống
     */
    public void stop() {
        running = false;

        if (spawnThread != null) {
            spawnThread.interrupt();
        }
        if (dispatchThread != null) {
            dispatchThread.interrupt();
        }
        if (lightThread != null) {
            lightThread.interrupt();
        }

        // Dừng toàn bộ task vehicle đang chạy trong thread pool.
        executor.shutdownNow();

        System.out.println("[Engine] All threads stopped.");
    }

    /**
     * Thống kê sử dụng Stream API (SRS yêu cầu)
     */
    public void printStatistics() {
        int totalVehicles;
        long ambulance;
        long car;
        long bike;
        long truck;
        synchronized (vehicles) {
            // Khóa list để tránh ConcurrentModification khi thread spawn đang thêm xe.
            totalVehicles = vehicles.size();

            ambulance = vehicles.stream()
                    .filter(v -> v.getType() == VehicleType.AMBULANCE)
                    .count();

            car = vehicles.stream()
                    .filter(v -> v.getType() == VehicleType.CAR)
                    .count();

            bike = vehicles.stream()
                    .filter(v -> v.getType() == VehicleType.MOTORBIKE)
                    .count();

            truck = vehicles.stream()
                    .filter(v -> v.getType() == VehicleType.TRUCK)
                    .count();
        }

        System.out.println("Total vehicles: " + totalVehicles);

        System.out.println("Ambulance: " + ambulance);
        System.out.println("Car: " + car);
        System.out.println("Motorbike: " + bike);
        System.out.println("Truck: " + truck);
        System.out.println("Vehicles passed intersection: " + manager.getTotalPassedVehicles());
        System.out.println("Traffic jam events: " + getTrafficJamCount());
    }

    /**
     * Health check hệ thống
     */
    public void healthCheck() {
        if (!running) {
            System.out.println("System is NOT running");
            return;
        }

        if (executor.isShutdown()) {
            System.out.println("ThreadPool is DOWN!");
        } else {
            System.out.println("ThreadPool OK");
        }

        System.out.println("Signal: " + trafficLight.getCurrentSignal());
        synchronized (vehicles) {
            System.out.println("Vehicles active: " + vehicles.size());
        }
        System.out.println("Waiting in lane: " + lane.size());
        System.out.println("Traffic jams: " + getTrafficJamCount());
        System.out.println("System running normally");
    }

    private synchronized int getTrafficJamCount() {
        // Đồng bộ để đọc/ghi nhất quán giữa nhiều thread.
        return trafficJamCount;
    }
}