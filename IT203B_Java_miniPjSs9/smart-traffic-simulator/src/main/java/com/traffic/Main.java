package com.traffic;

import com.traffic.engine.SimulationEngine;

import java.util.Scanner;

/**
 * Main menu controller
 */
public class Main {

    private static boolean isRunning = false;

    // giữ instance engine để start/stop
    private static SimulationEngine engine = new SimulationEngine();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            printMenu();
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    startSimulation();
                    break;
                case 2:
                    stopSimulation();
                    break;
                case 3:
                    viewStatistics();
                    break;
                case 4:
                    checkSystemHealth();
                    break;
                case 0:
                    System.out.println("Đang thoát hệ thống...");
                    stopSimulation();
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== SMART TRAFFIC SIMULATOR - MENU CHÍNH ===");
        System.out.println("1. Bắt đầu mô phỏng (Start Simulation)");
        System.out.println("2. Dừng mô phỏng (Stop Simulation)");
        System.out.println("3. Xem thống kê lưu lượng (View Statistics)");
        System.out.println("4. Kiểm tra trạng thái hệ thống (Health Check)");
        System.out.println("0. Thoát");
        System.out.println("============================================");
    }

    private static void startSimulation() {
        if (!isRunning) {
            System.out.println("[System] Khởi động mô phỏng...");
            engine.start();
            isRunning = true;
        } else {
            System.out.println("[Warning] Hệ thống đang chạy!");
        }
    }

    private static void stopSimulation() {
        if (isRunning) {
            System.out.println("[System] Dừng mô phỏng...");
            engine.stop();
            isRunning = false;
        } else {
            System.out.println("[Warning] Hệ thống chưa chạy!");
        }
    }

    private static void viewStatistics() {
        System.out.println("--- BÁO CÁO GIÁM SÁT ---");
        engine.printStatistics();
    }

    private static void checkSystemHealth() {
        System.out.println("[System] Checking system...");
        engine.healthCheck();
    }
}