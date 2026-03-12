package Bai3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProcessorTest {
    private UserProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new UserProcessor();
    }

    @Test
    @DisplayName("Email hợp lệ: Có ký tự @ và có tên miền")
    void testValidEmail() {
        String result = processor.isValidEmail("user@gmail.com");
        assertEquals("user@gmail.com", result, "Email hợp lệ phải được giữ nguyên định dạng thường");
    }

    @Test
    @DisplayName("Email không hợp lệ: Thiếu ký tự @")
    void testEmailMissingAtSign() {
        assertThrows(IllegalArgumentException.class, () -> {
            processor.isValidEmail("usergmail.com");
        }, "Phải ném ra ngoại lệ khi thiếu ký tự @");
    }

    @Test
    @DisplayName("Email không hợp lệ: Có @ nhưng thiếu tên miền")
    void testEmailMissingDomain() {
        assertThrows(IllegalArgumentException.class, () -> {
            processor.isValidEmail("user@");
        }, "Phải ném ra ngoại lệ khi thiếu tên miền sau ký tự @");
    }

    @Test
    @DisplayName("Chuẩn hóa email: Chuyển toàn bộ về chữ thường (lowercase)")
    void testEmailNormalization() {
        String result = processor.isValidEmail("Example@Gmail.com");
        assertEquals("example@gmail.com", result, "Email phải được chuyển về dạng chữ thường");
    }
}