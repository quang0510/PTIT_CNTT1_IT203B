package b4;

public class Humidifier implements Observer {
    @Override
    public void update(int t) {
        System.out.println("Máy tạo ẩm: Điều chỉnh độ ẩm cho nhiệt độ " + t);
    }
}
