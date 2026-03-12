package Bai3;

public class UserProcessor {
    public String isValidEmail(String email) {
        if (email == null || !email.contains("@") || email.endsWith("@")) {
            throw new IllegalArgumentException("Định dạng email không hợp lệ");
        }
        return email.toLowerCase();
    }
}