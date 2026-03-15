package Session_06;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demo1 extends Thread {

    List<String> names = new ArrayList<>(List.of("trần trí dương", "tôn ngộ không", "Toàn què"));
    Random rd = new Random();

    @Override
    public void run() {
        while (true) {
            int index = rd.nextInt(names.size());

            System.out.printf("1 thằng ngu được chọn: %s\n", names.get(index));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}