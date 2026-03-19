package b2;

public class B2 {
    public static void main(String[] args) {
        TempSensor s = new ThermometerAdapter(new OldThermometer());
        System.out.println("Nhiệt độ hiện tại: " + String.format("%.1f", s.getCelsius()) + "°C");
        new SmartHomeFacade().sleepMode();
    }
}
