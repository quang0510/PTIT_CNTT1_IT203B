package Session_06.BaiTap.XuatSac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Ticket {
    String id;
    String roomName;
    boolean isSold = false;
    boolean isHeld = false;
    boolean isVIP = false;
    long holdExpiryTime = 0;

    public Ticket(String id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }

    public synchronized boolean isAvailable() {
        if (isSold) return false;
        if (!isHeld) return true;
        return System.currentTimeMillis() > holdExpiryTime;
    }
}

class TicketPool {
    String roomName;
    List<Ticket> tickets;

    public TicketPool(String roomName, int count) {
        this.roomName = roomName;
        this.tickets = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", i), roomName));
        }
    }

    public synchronized Ticket holdTicket(boolean asVIP) {
        for (Ticket t : tickets) {
            if (t.isAvailable()) {
                t.isHeld = true;
                t.isVIP = asVIP;
                t.holdExpiryTime = System.currentTimeMillis() + 5000;
                return t;
            }
        }
        return null;
    }

    public synchronized void sellHeldTicket(Ticket t) {
        if (t != null && t.isHeld) {
            t.isSold = true;
            t.isHeld = false;
            t.holdExpiryTime = 0;
        }
    }

    public synchronized void releaseExpiredTickets() {
        long now = System.currentTimeMillis();
        for (Ticket t : tickets) {
            if (t.isHeld && !t.isSold && now > t.holdExpiryTime) {
                System.out.println(">>> [TimeoutManager]: Vé " + t.id + " hết hạn giữ, đã trả lại kho");
                t.isHeld = false;
                t.holdExpiryTime = 0;
                t.isVIP = false;
            }
        }
    }
}

class BookingCounter extends Thread {
    String counterName;
    TicketPool pool;
    boolean isVIP;
    boolean customerWillPay;

    public BookingCounter(String name, TicketPool pool, boolean isVIP, boolean pay) {
        this.counterName = name;
        this.pool = pool;
        this.isVIP = isVIP;
        this.customerWillPay = pay;
    }

    @Override
    public void run() {
        Ticket t = pool.holdTicket(isVIP);
        if (t != null) {
            System.out.println(counterName + ": Đã giữ vé " + t.id + (isVIP ? " (VIP)" : "") + ". Vui lòng thanh toán trong 5s");

            try {
                Thread.sleep(3000);

                if (customerWillPay) {
                    pool.sellHeldTicket(t);
                    System.out.println(counterName + ": Thanh toán thành công vé " + t.id);
                } else {
                    System.out.println(counterName + ": Khách không thực hiện thanh toán...");
                }
            } catch (InterruptedException e) {
                System.out.println(counterName + " bị gián đoạn.");
            }
        } else {
            System.out.println(counterName + ": Phòng " + pool.roomName + " đang bận hoặc hết vé, vui lòng chờ...");
        }
    }
}

class TimeoutManager extends Thread {
    List<TicketPool> pools;

    public TimeoutManager(List<TicketPool> pools) {
        this.pools = pools;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (TicketPool p : pools) {
                    p.releaseExpiredTickets();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
        }
    }
}

public class Bai1 {
    public static void main(String[] args) throws InterruptedException {
        TicketPool poolA = new TicketPool("A", 10);
        TicketPool poolB = new TicketPool("B", 10);
        TicketPool poolC = new TicketPool("C", 10);
        List<TicketPool> allPools = Arrays.asList(poolA, poolB, poolC);

        TimeoutManager manager = new TimeoutManager(allPools);
        manager.start();

        System.out.println("--- HỆ THỐNG BÁN VÉ BẮT ĐẦU ---");

        BookingCounter q1 = new BookingCounter("Quầy 1", poolA, true, true);
        q1.start();

        Thread.sleep(1000);
        BookingCounter q2 = new BookingCounter("Quầy 2", poolA, false, false);
        q2.start();


        Thread.sleep(10000);

        System.out.println("\n--- Sau 10 giây ---");
        BookingCounter q3 = new BookingCounter("Quầy 3", poolA, false, true);
        q3.start();

        q3.join();
        System.out.println("--- KẾT THÚC PHIÊN LÀM VIỆC ---");
    }
}