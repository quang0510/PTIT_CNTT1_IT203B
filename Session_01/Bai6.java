package Session_01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bai6 {
    public static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    public static class User{
        int age;

        public User(int age) {
            this.age = age;
        }

        public void setAge(int age) throws InvalidAgeException {
            if(age < 0){
                throw new InvalidAgeException("Tuổi không thể âm");
            }
            this.age = age;
        }
    }

    public static void main(String[] args) throws InvalidAgeException {
        User user = new User(11);
        try{
            user.setAge(10);
        }catch(InvalidAgeException e){
            logError(e.getMessage());
        }

        try{
            user.setAge(-1);
        }catch(InvalidAgeException e){
            logError(e.getMessage());
        }
    }
    public static void logError(String message) throws InvalidAgeException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.printf("[ERROR] [%s] [%s]\n", timestamp, message);
    }
}
