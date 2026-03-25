package ss13.b1.presentation;

import ss13.b1.business.MedicineService;

/*
 * GIAI THICH LOGIC (PHAN 1):
 * 1. Tại sao thuốc vẫn bị trừ: JDBC mặc định chạy Auto-commit = true. Nghĩa là cứ xong một lệnh
 * executeUpdate() là nó chốt luôn vào DB. Khi thằng Junior viết code, lệnh trừ kho xong
 * là DB lưu ngay. Đến lệnh sau gặp lỗi thì lệnh trước không cứu lại được nữa.
 * 2. Cách sửa: Phải dùng c.setAutoCommit(false) để gom các lệnh vào một vùng chờ.
 * Chỉ khi nào gọi c.commit() thì DB mới thực sự thay đổi. Nếu có lỗi ở giữa,
 * ta dùng c.rollback() để đưa mọi thứ về trạng thái ban đầu, đảm bảo tính nguyên tử.
 */

public class MainApp {
    public static void main(String[] args) {
        MedicineService s = new MedicineService();

        System.out.println("TEST 1 MO PHONG LOI MANG");
        s.thucHien(1, 999, true);

        System.out.println("TEST 2 HE THONG CHAY OK");
        s.thucHien(1, 888, false);
    }
}