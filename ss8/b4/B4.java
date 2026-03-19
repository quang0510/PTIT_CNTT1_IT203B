package b4;

public class B4 {
    public static void main(String[] args) {
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.attach(new Fan());
        sensor.attach(new Humidifier());

        sensor.setTemperature(18);
        sensor.setTemperature(26);
    }
}
