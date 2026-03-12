package Bai4;

public class Bai4 {
    public static String evaluatePasswordStrength(String password) {
        if (password == null || password.length() < 8) {
            return "Yếu";
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        // Đủ tất cả các yếu tố
        if (hasUpper && hasLower && hasDigit && hasSpecial) {
            return "Mạnh";
        }

        // Các trường hợp chỉ có 1-2 loại ký tự dù độ dài >= 8 vẫn tính là yếu theo TC07, TC08
        int score = 0;
        if (hasUpper) score++;
        if (hasLower) score++;
        if (hasDigit) score++;
        if (hasSpecial) score++;

        if (score <= 2) {
            return "Yếu";
        }

        return "Trung bình";
    }
}