package Bai4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Bai4Test {

    @Test
    @DisplayName("Mật khẩu có đủ: ≥8 ký tự, chữ hoa, chữ thường, số và ký tự đặc biệt")
    void evaluatePasswordStrength1() {
        assertEquals("Mạnh", Bai4.evaluatePasswordStrength("Abc123!@"));
    }

    @Test
    @DisplayName("Thiếu chữ hoa")
    void evaluatePasswordStrength2() {
        assertEquals("Trung bình",Bai4.evaluatePasswordStrength("abc123!@"));
    }

    @Test
    @DisplayName("Thiếu chữ thường")
    void evaluatePasswordStrength3() {
        assertEquals("Trung bình",Bai4.evaluatePasswordStrength("ABC123!@"));
    }

    @Test
    @DisplayName("Thiếu số")
    void evaluatePasswordStrength4() {
        assertEquals("Trung bình",Bai4.evaluatePasswordStrength("Abcdef!@"));
    }

    @Test
    @DisplayName("Thiếu ký tự đặc biệt")
    void evaluatePasswordStrength5() {
        assertEquals("Trung bình",Bai4.evaluatePasswordStrength("Abc12345"));
    }

    @Test
    @DisplayName("Mật khẩu quá ngắn (<8 ký tự)")
    void evaluatePasswordStrength6() {
        assertEquals("Yếu",Bai4.evaluatePasswordStrength("Ab1!"));
    }

    @Test
    @DisplayName("Chỉ có chữ thường")
    void evaluatePasswordStrength7() {
        assertEquals("Yếu",Bai4.evaluatePasswordStrength("password"));
    }

    @Test
    @DisplayName("Chỉ có chữ hoa và số")
    void evaluatePasswordStrength8() {
        assertEquals("Yếu",Bai4.evaluatePasswordStrength("ABC12345"));
    }

}