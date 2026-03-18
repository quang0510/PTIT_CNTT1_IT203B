# DỰ ÁN: HỆ THỐNG MÔ PHỎNG GIAO THÔNG THÔNG MINH (SMART TRAFFIC SIMULATOR)

## I. THÔNG TIN CHUNG

- **Nhóm trưởng:** Đặng Quốc Toàn
- **Số lượng thành viên:** 10
- **Ngôn ngữ:** Java 8+

---

## II. CẤU TRÚC THƯ MỤC DỰ ÁN

Mọi người tuân thủ cấu trúc package này để khi merge code không bị lỗi.

```text
smart-traffic-simulator/
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/traffic/
    │   │       ├── entity/
    │   │       │   ├── Vehicle.java              <-- Abstract class (TV 01)
    │   │       │   ├── StandardVehicle.java      <-- Xe thường (TV 01)
    │   │       │   ├── PriorityVehicle.java      <-- Xe cứu thương (TV 01)
    │   │       │   └── VehicleType.java          <-- Enum: MOTORBIKE, CAR, AMBULANCE
    │   │       ├── engine/
    │   │       │   ├── SimulationEngine.java     <-- Quản lý Thread Pool (TV 06)
    │   │       │   ├── IntersectionManager.java  <-- Quản lý Lock ngã tư (TV 03)
    │   │       │   └── Lane.java                 <-- Quản lý hàng đợi xe (TV 03)
    │   │       ├── pattern/
    │   │       │   ├── state/
    │   │       │   │   ├── TrafficLight.java     <-- Context (TV 02)
    │   │       │   │   ├── TrafficLightState.java<-- Interface State (TV 02)
    │   │       │   │   ├── RedState.java         <-- Cụ thể (TV 02)
    │   │       │   │   ├── YellowState.java      <-- Cụ thể (TV 02)
    │   │       │   │   └── GreenState.java       <-- Cụ thể (TV 02)
    │   │       │   ├── observer/
    │   │       │   │   ├── Subject.java          <-- Interface cho đèn (TV 05)
    │   │       │   │   └── Observer.java         <-- Interface cho xe (TV 05)
    │   │       │   └── factory/
    │   │       │       └── VehicleFactory.java   <-- Tạo xe ngẫu nhiên (TV 04)
    │   │       ├── exception/
    │   │       │   ├── TrafficJamException.java  <-- Custom Exception (TV 08)
    │   │       │   └── CollisionException.java   <-- Custom Exception (TV 08)
    │   │       ├── util/
    │   │       │   ├── LoggerUtils.java          <-- In log màu mè cho đẹp (TV 07)
    │   │       │   ├── Constants.java            <-- Chứa thời gian đèn, vận tốc (TV 07)
    │   │       │   └── ConfigLoader.java         <-- Đọc file cấu hình (TV 07)
    │   │       └── Main.java                 <-- Menu & Khởi chạy
    │   └── resources/
    │       └── config.properties                 <-- File cấu hình thông số
    └── test/
        └── java/com/traffic/
            ├── TrafficLightTest.java             <-- Test logic đèn (TV 09)
            └── ConcurrencyTest.java              <-- Stress test đa luồng (TV 09)
```

## III. PHÂN CÔNG NHIỆM VỤ

| STT | Thành viên    | Nhiệm vụ chính                | Chi tiết                                                                                                    |
| :-- | :------------ | :---------------------------- | :---------------------------------------------------------------------------------------------------------- |
| 01  | Toàn (Leader) | Kiến trúc & Menu              | Tạo cấu trúc Project, viết file Main.java điều hướng, Review và Merge code.                                 |
| 02  |               | Vehicle Entities              | Thiết kế lớp Vehicle (Abstract) và các lớp con (Xe máy, Ô tô, Xe tải). Đảm bảo tính LSP và OCP.             |
| 03  |               | Traffic Light (State Pattern) | Cài đặt TrafficLight và 3 trạng thái (Xanh, Vàng, Đỏ). Xử lý logic chuyển trạng thái theo thời gian.        |
| 04  |               | Intersection Manager          | Quản lý "Vùng giao lộ". Sử dụng ReentrantLock hoặc Semaphore để giới hạn số xe trong giao lộ cùng lúc.      |
| 05  |               | Vehicle Factory               | Cài đặt VehicleFactory để sinh ngẫu nhiên xe. Quản lý các thuộc tính tốc độ, độ ưu tiên của xe cứu thương.  |
| 06  |               | Signal System (Observer)      | Cài đặt Observer Pattern. Đèn giao thông là Subject, các Xe là Observers để nhận biết khi nào được đi/dừng. |
| 07  |               | Simulation Engine             | Sử dụng ExecutorService (Thread Pool) để quản lý vòng đời của hàng trăm xe (Thread Lifecycle).              |
| 08  |               | Monitoring & Statistics       | Dùng Java 8 Stream API để thống kê lưu lượng. Ghi log trạng thái hệ thống theo thời gian thực.              |
| 09  |               | Exception & Safety            | Định nghĩa các Custom Exceptions. Viết cơ chế check va chạm (Collision) và kẹt xe (Traffic Jam).            |
| 10  |               | Quality Assurance (Tester)    | Viết Unit Test với JUnit 5. Thực hiện Stress Test (đẩy 100 xe cùng lúc) để kiểm tra Race Condition.         |

---

## IV. CÁCH HỆ THỐNG XỬ LÝ DEADLOCK

- Ngã tư được bảo vệ bởi một điểm khóa duy nhất (`ReentrantLock`) trong IntersectionManager, nên không có vòng chờ nhiều lock (không tạo circular-wait).
- Xe đi qua ngã tư theo cơ chế lock tuần tự, xe sau sẽ chờ xe trước nhả lock rồi mới vào giao lộ.
- Đèn giao thông chạy daemon thread độc lập, hàng đợi làn đường (Lane) tách riêng khỏi lock ngã tư để tránh lock lồng nhau.
- Khi dừng hệ thống, engine ngắt các thread sinh xe/điều phối và shutdown thread pool để tránh treo tiến trình.
