package Session_01;

import java.io.IOException;

public class Bai4 {
    public static void main(String[] args) {
        System.out.println("--- Bắt đầu chương trình ---");

        try {
            processUserData();
            System.out.println("Lưu dữ liệu thành công");

        } catch (IOException e) {
            System.err.println("Lỗi hệ thống: " + e.getMessage());
            System.out.println("Hướng giải quyết: Kiểm tra lại quyền ghi file hoặc dung lượng ổ cứng.");
        }

        System.out.println("--- Chương trình kết thúc an toàn ---");
    }

    public static void processUserData() throws IOException {
        System.out.println("Đang xử lý dữ liệu người dùng...");
        saveToFile();
    }

    public static void saveToFile() throws IOException {
        System.out.println("Đang kết nối tới ổ cứng...");

        boolean isError = true;

        if (isError) {
            throw new IOException("Không thể truy cập file: Ổ cứng đã bị rút hoặc đầy.");
        }

        System.out.println("Ghi dữ liệu vào file thành công.");
    }
    }

