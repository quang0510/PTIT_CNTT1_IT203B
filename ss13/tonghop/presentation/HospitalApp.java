package ss13.tonghop.presentation;
import ss13.tonghop.business.HospitalService;


public class HospitalApp {
    public static void main(String[] args) {
        HospitalService s = new HospitalService();

        System.out.println("test 1 thanh cong");
        s.exitProcess(101, 500000.0, false);

        System.out.println("\ntest 2 loi rollback");
        s.exitProcess(101, 999999.0, true);
    }
}
