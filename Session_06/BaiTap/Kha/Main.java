package Session_06.BaiTap.Kha;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        addTickets(count);
    }

    public synchronized void addTickets(int count) {
        int currentSize = tickets.size();
        for (int i = 1; i <= count; i++) {
            String id = roomName + "-" + String.format("%03d", currentSize + i);
            tickets.add(new Ticket(id, roomName));
        }
    }

    public synchronized Ticket sellTicket() {
        for (Ticket t : tickets) {
            if (!t.isSold) {
                t.isSold = true;
                return t;
            }
        }
        return null;
    }

    public synchronized boolean hasTickets() {
        for (Ticket t : tickets) {
            if (!t.isSold) return true;
        }
        return false;
    }
}

class TicketSupplier implements Runnable {
    TicketPool roomA;
    TicketPool roomB;
    int supplyCount;
    int interval;
    int rounds;

    public TicketSupplier(TicketPool roomA, TicketPool roomB, int supplyCount, int interval, int rounds) {
        this.roomA = roomA;
        this.roomB = roomB;
        this.supplyCount = supplyCount;
        this.interval = interval;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < rounds; i++) {
                Thread.sleep(interval);
                roomA.addTickets(supplyCount);
                System.out.println("Nhà cung cấp: Đã thêm " + supplyCount + " vé vào phòng A");
                roomB.addTickets(supplyCount);
                System.out.println("Nhà cung cấp: Đã thêm " + supplyCount + " vé vào phòng B");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class BookingCounter implements Runnable {
    String counterName;
    TicketPool poolA;
    TicketPool poolB;
    int soldCount = 0;
    Random random = new Random();

    public BookingCounter(String counterName, TicketPool poolA, TicketPool poolB) {
        this.counterName = counterName;
        this.poolA = poolA;
        this.poolB = poolB;
    }

    @Override
    public void run() {
        while (true) {
            boolean canContinue = poolA.hasTickets() || poolB.hasTickets();

            if (!canContinue) {
                try {
                    Thread.sleep(200);
                    if (!poolA.hasTickets() && !poolB.hasTickets()) break;
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }

            int choice = random.nextInt(2);
            Ticket soldTicket = (choice == 0) ? poolA.sellTicket() : poolB.sellTicket();

            if (soldTicket == null) {
                soldTicket = (choice == 0) ? poolB.sellTicket() : poolA.sellTicket();
            }

            if (soldTicket != null) {
                soldCount++;
                System.out.println(counterName + " đã bán vé " + soldTicket.ticketId);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        TicketSupplier supplier = new TicketSupplier(roomA, roomB, 3, 3000, 2);
        BookingCounter counter1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter counter2 = new BookingCounter("Quầy 2", roomA, roomB);

        Thread ts = new Thread(supplier);
        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);

        ts.start();
        t1.start();
        t2.start();

        ts.join();
        t1.join();
        t2.join();

        System.out.println("\nKết thúc chương trình");
        System.out.println("Quầy 1 bán được: " + counter1.soldCount + " vé");
        System.out.println("Quầy 2 bán được: " + counter2.soldCount + " vé");

        int remainA = 0;
        for (Ticket t : roomA.tickets) if (!t.isSold) remainA++;
        int remainB = 0;
        for (Ticket t : roomB.tickets) if (!t.isSold) remainB++;

        System.out.println("Vé còn lại phòng A: " + remainA);
        System.out.println("Vé còn lại phòng B: " + remainB);
    }
}