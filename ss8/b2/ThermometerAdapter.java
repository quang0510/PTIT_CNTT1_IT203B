package b2;

public class ThermometerAdapter implements TempSensor {
    private OldThermometer old;
    public ThermometerAdapter(OldThermometer old) { this.old = old; }
    public double getCelsius() { return (old.getFahrenheit() - 32) * 5.0 / 9.0; }
}
