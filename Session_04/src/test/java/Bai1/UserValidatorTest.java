package Bai1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    @Test
    @DisplayName("Tên hợp lệ")
    void isValidUsername1() {
        assertTrue(UserValidator.isValidUsername("user123"));
    }

    @Test
    @DisplayName("Tên không hợp lệ")
    void isValidUsername2() {
        assertFalse(UserValidator.isValidUsername("abc"));
    }

    @Test
    @DisplayName("Tên không Chứa khoảng trắng")
    void isValidUsername3() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserValidator.isValidUsername("a b  c");
        });
    }

}