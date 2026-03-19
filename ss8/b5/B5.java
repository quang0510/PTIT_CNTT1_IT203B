package b5;

public class B5 {
    public static void main(String[] args) {
        Light l = new Light();
        AC ac = new AC();
        Command sleep = new SleepModeCommand(l, ac);
        sleep.execute();
    }
}
