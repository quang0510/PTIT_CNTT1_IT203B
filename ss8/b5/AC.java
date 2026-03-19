package b5;

public class AC implements Observer {
    public void set(int t) { System.out.println("Điều hòa: Nhiệt độ = " + t); }
    public void update(int t) { if(t > 30) System.out.println("Điều hòa: Tự động giảm nhiệt độ phòng"); }
}
