package Session_06.BaiTap.XuatSac.Bai2;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


class Ticket {
    String id;
    long price;
    boolean isSold = false;

    public Ticket(String id, long price) {
        this.id = id;
        this.price = price;
    }
}


class TicketPool {
    String roomName;
    List<Ticket> tickets = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();

    public TicketPool(String roomName, int count) {
        this.roomName = roomName;
        for (int i = 1; i <= count; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", i), 50000));
        }
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public Ticket sellOne() {
        lock.lock();
        try {
            for (Ticket t : tickets) {
                if (!t.isSold) {
                    t.isSold = true;
                    return t;
                }
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public long getSoldInfo() {
        return tickets.stream().filter(t -> t.isSold).count();
    }
}


class SimulationManager {
    private List<TicketPool> pools = new ArrayList<>();
    private ExecutorService ticketCounters;
    private final ReentrantLock pauseLock = new ReentrantLock();
    private final Condition unpaused = pauseLock.newCondition();
    private final AtomicBoolean isPaused = new AtomicBoolean(false);
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final AtomicInteger totalRevenue = new AtomicInteger(0);

    public void setup(int numRooms, int ticketsPerRoom) {
        pools.clear();
        totalRevenue.set(0);
        for (int i = 0; i < numRooms; i++) {
            pools.add(new TicketPool("Phòng " + (char) ('A' + i), ticketsPerRoom));
        }
    }

    public void start(int numCounters) {
        if (isRunning.get()) return;
        isRunning.set(true);
        ticketCounters = Executors.newFixedThreadPool(numCounters);

        for (int i = 1; i <= numCounters; i++) {
            int counterId = i;
            ticketCounters.execute(() -> {
                while (isRunning.get()) {
                    checkPause();

                    TicketPool pool = pools.get(new Random().nextInt(pools.size()));
                    Ticket t = pool.sellOne();
                    if (t != null) {
                        totalRevenue.addAndGet((int) t.price);
                        System.out.println("  Quầy " + counterId + " đã bán vé " + t.id);
                    }
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });
        }
    }

    private void checkPause() {
        pauseLock.lock();
        try {
            while (isPaused.get()) unpaused.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            pauseLock.unlock();
        }
    }

    public void pause() {
        isPaused.set(true);
    }

    public void resume() {
        pauseLock.lock();
        try {
            isPaused.set(false);
            unpaused.signalAll();
        } finally {
            pauseLock.unlock();
        }
    }

    public void stop() {
        isRunning.set(false);
        resume();
        if (ticketCounters != null) ticketCounters.shutdownNow();
    }

    public void showStats() {
        System.out.println("\n=== THỐNG KÊ HIỆN TẠI ===");
        for (TicketPool p : pools) {
            System.out.println("  " + p.roomName + ": Đã bán " + p.getSoldInfo() + "/" + p.tickets.size() + " vé");
        }
        System.out.println("  Tổng doanh thu: " + totalRevenue.get() + " VNĐ");
    }
}


class DeadlockDetector {
    public static void check() {
        System.out.println("Đang quét deadlock...");
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] threadIds = bean.findDeadlockedThreads();

        if (threadIds != null) {
            System.err.println("!!! PHÁT HIỆN DEADLOCK tại các Thread ID: " + Arrays.toString(threadIds));
            ThreadInfo[] infos = bean.getThreadInfo(threadIds);
            for (ThreadInfo info : infos) {
                System.err.println("Thread " + info.getThreadName() + " đang chờ lock: " + info.getLockName());
            }
        } else {
            System.out.println("  Không phát hiện deadlock.");
        }
    }
}


public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SimulationManager manager = new SimulationManager();

        while (true) {
            System.out.println("\n--- MENU HỆ THỐNG RẠP CHIẾU PHIM ---");
            System.out.println("1. Bắt đầu mô phỏng");
            System.out.println("2. Tạm dừng mô phỏng");
            System.out.println("3. Tiếp tục mô phỏng");
            System.out.println("4. Xem thống kê");
            System.out.println("5. Phát hiện deadlock");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Nhập số phòng: ");
                    int r = sc.nextInt();
                    System.out.print("Nhập số vé/phòng: ");
                    int t = sc.nextInt();
                    System.out.print("Nhập số quầy: ");
                    int q = sc.nextInt();
                    manager.setup(r, t);
                    manager.start(q);
                    System.out.println("Đã khởi tạo hệ thống với " + r + " phòng, " + (r * t) + " vé, " + q + " quầy.");
                    break;
                case 2:
                    manager.pause();
                    System.out.println("Đã tạm dừng tất cả quầy bán vé.");
                    break;
                case 3:
                    manager.resume();
                    System.out.println("Đã tiếp tục hoạt động.");
                    break;
                case 4:
                    manager.showStats();
                    break;
                case 5:
                    DeadlockDetector.check();
                    break;
                case 6:
                    manager.stop();
                    System.out.println("Đang dừng hệ thống... Kết thúc chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}