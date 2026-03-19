package b3;

public class B3 {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        RemoteControl remote = new RemoteControl();

        Command lightOn = new LightOnCommand(livingRoomLight);
        remote.setCommand(lightOn);
        System.out.println("Gán nút 1: Bật đèn");
        remote.pressButton();

        Command lightOff = new LightOffCommand(livingRoomLight);
        remote.setCommand(lightOff);
        System.out.println("Gán nút 2: Tắt đèn");
        remote.pressButton();

        remote.pressUndo();
    }
}
