package b5;

public class SleepModeCommand implements Command {
    private Light l;
    private AC ac;
    public SleepModeCommand(Light l, AC ac) { this.l = l; this.ac = ac; }
    public void execute() {
        l.off();
        ac.set(28);
        System.out.println("SleepMode: Quạt tốc độ thấp\nQuạt: Chạy tốc độ thấp");
    }
}
