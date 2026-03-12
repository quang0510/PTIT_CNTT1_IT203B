package Bai1;

public class UserValidator {
    public static boolean isValidUsername(String username){
        if (username.contains(" ")) {
            throw new IllegalArgumentException("Tên không được chứa khoảng trắng");
        }
        return username.trim().length() >= 6 && username.trim().length() <= 20;
    }
}