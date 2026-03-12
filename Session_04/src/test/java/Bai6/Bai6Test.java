package Bai6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Bai6Test {
    private Bai6.User currentUser;
    private List<Bai6.User> userList;
    @BeforeEach
    void setUp() {
        userList = new ArrayList<>();

        currentUser = new Bai6.User("U01", "old@gmail.com", LocalDate.of(2000, 1, 1));
        userList.add(currentUser);
        userList.add(new Bai6.User("U02", "other@gmail.com", LocalDate.of(1995, 5, 5)));
    }

    @Test
    @DisplayName("Cập nhật hồ sơ bình thường")
    void updateProfile() {
        Bai6.UserProfile newProfile = new Bai6.UserProfile("new@gmail.com", LocalDate.of(1999, 12, 31));
        Bai6.User result = Bai6.updateProfile(currentUser, newProfile, userList);

        assertNotNull(result, "Hồ sơ phải được cập nhật thành công");
        assertEquals("new@gmail.com", result.getEmail());
    }

    @Test
    @DisplayName("Kiểm tra ràng buộc ngày sinh không hợp lệ")
    void updateProfile1() {
        Bai6.UserProfile newProfile = new Bai6.UserProfile("valid@gmail.com", LocalDate.now().plusDays(1));
        Bai6.User result = Bai6.updateProfile(currentUser, newProfile, userList);

        assertNull(result, "Hệ thống phải từ chối nếu ngày sinh ở tương lai");
    }

    @Test
    @DisplayName("Kiểm tra ràng buộc email trùng")
    void updateProfile2() {
        Bai6.UserProfile newProfile = new Bai6.UserProfile("other@gmail.com", LocalDate.of(2000, 1, 1));
        Bai6.User result = Bai6.updateProfile(currentUser, newProfile, userList);

        assertNull(result, "Hệ thống phải từ chối nếu email đã tồn tại");
    }

    @Test
    @DisplayName("Kiểm tra trường hợp không thay đổi email")
    void updateProfile3() {
        Bai6.UserProfile newProfile = new Bai6.UserProfile("old@gmail.com", LocalDate.of(1990, 1, 1));
        Bai6.User result = Bai6.updateProfile(currentUser, newProfile, userList);

        assertNotNull(result, "Phải cho phép cập nhật nếu email không thay đổi");
        assertEquals(LocalDate.of(1990, 1, 1), result.getDob());
    }

    @Test
    @DisplayName("Kiểm tra hệ thống khi không có user khác")
    void updateProfile4() {
        Bai6.UserProfile newProfile = new Bai6.UserProfile("unique@gmail.com", LocalDate.of(2000, 1, 1));
        Bai6.User result = Bai6.updateProfile(currentUser, newProfile, null);

        assertNotNull(result, "Cập nhật bình thường khi không có dữ liệu đối chiếu email");
    }

    @Test
    @DisplayName("Kiểm tra trường hợp vi phạm nhiều ràng buộc")
    void updateProfile5() {
        Bai6.UserProfile newProfile = new Bai6.UserProfile("other@gmail.com", LocalDate.now().plusYears(10));
        Bai6.User result = Bai6.updateProfile(currentUser, newProfile, userList);

        assertNull(result, "Hệ thống phải từ chối khi có nhiều lỗi ràng buộc");
    }


}