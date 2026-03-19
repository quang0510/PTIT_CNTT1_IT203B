package b2;

public class SmartHomeFacade {
    public void leaveHome() {
        System.out.println("FACADE: Tắt đèn\nFACADE: Tắt quạt\nFACADE: Tắt điều hòa");
    }
    public void sleepMode() {
        System.out.println("FACADE: Tắt đèn\nFACADE: Điều hòa set 28°C\nFACADE: Quạt chạy tốc độ thấp");
    }
}
