package b1;

public class B1 {
    public static void main(String[] args) {
        HardwareConnection.getInstance();
        DeviceFactory factory = new LightFactory();
        Device d = factory.createDevice();
        d.turnOn();
    }
}
