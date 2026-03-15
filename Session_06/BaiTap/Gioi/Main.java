package Session_06.BaiTap.Gioi;

import java.util.ArrayList;
import java.util.List;

class Ticket {
    String ticketId;
    String roomName;
    boolean isSold;

    public Ticket(String ticketId, String roomName) {
        this.ticketId = ticketId;
        this.roomName = roomName;
        this.isSold = false;
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

    public synchronized Ticket internalGetTicket() {
        for (Ticket t : tickets) {
            if (!t.isSold) {
                t.isSold = true;
                return t;
            }
        }
        return null;
    }

    public synchronized void returnTicket(Ticket t) {
        if (t != null) t.isSold = false;
    }

    public synchronized boolean hasTickets() {
        for (Ticket t : tickets) {
            if (!t.isSold) return true;
        }
        return false;
    }
}

class BookingCounter implements Runnable {
    String counterName;
    TicketPool poolA;
    TicketPool poolB;
    int comboSoldCount = 0;

    public BookingCounter(String counterName, TicketPool poolA, TicketPool poolB) {
        this.counterName = counterName;
        this.poolA = poolA;
        this.poolB = poolB;
    }

    public void sellCombo() {
        Ticket vA = null;
        Ticket vB = null;

        synchronized (poolA) {
            System.out.println(counterName + ": Đã lấy khóa Phòng A");
            vA = poolA.internalGetTicket();

            if (vA != null) {
                synchronized (poolB) {
                    System.out.println(counterName + ": Đã lấy khóa Phòng B");
                    vB = poolB.internalGetTicket();

                    if (vB != null) {
                        comboSoldCount++;
                        System.out.println(counterName + " bán combo thành công: " + vA.ticketId + " & " + vB.ticketId);
                    } else {
                        poolA.returnTicket(vA);
                        System.out.println(counterName + ": Thất bại (Hết vé B), đã trả lại vé A");
                    }
                }
            } else {
                System.out.println(counterName + ": Thất bại (Hết vé A)");
            }
        }
    }

    @Override
    public void run() {
        while (poolA.hasTickets() && poolB.hasTickets()) {
            sellCombo();
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        BookingCounter c1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter c2 = new BookingCounter("Quầy 2", roomA, roomB);

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\n--- TỔNG KẾT ---");
        System.out.println("Quầy 1 bán được: " + c1.comboSoldCount + " combo");
        System.out.println("Quầy 2 bán được: " + c2.comboSoldCount + " combo");
        System.out.println("Tổng combo đã bán: " + (c1.comboSoldCount + c2.comboSoldCount));
    }
}