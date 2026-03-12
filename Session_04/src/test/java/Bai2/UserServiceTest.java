package Bai2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    @DisplayName("Kiểm tra giá trị biên hợp lệ (đủ 18 tuổi)")
    void checkRegistrationAge() {
        assertTrue(UserService.checkRegistrationAge(18));
    }

    @Test
    @DisplayName("Kiểm tra nhỏ hơn tuổi yêu cầu")
    void checkRegistrationAge1() {
        assertFalse(UserService.checkRegistrationAge(17));
    }

    @Test
    @DisplayName("Kiểm tra dữ liệu không hợp lệ (tuổi âm)")
    void checkRegistrationAge2() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserService.checkRegistrationAge(-1);
        });
    }
}