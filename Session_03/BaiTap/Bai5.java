package Session_03.BaiTap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Bai5 {

    public record User(String username, String email, String status) {}

    public static void main(String[] args) {

        User user1 = new User("alexander", "alex@gmail.com", "ACTIVE");
        User user2 = new User("charlotte", "char@gmail.com", "ACTIVE");
        User user3 = new User("Benjamin", "ben@gmail.com", "INACTIVE");
        User user4 = new User("Tom", "tom@gmail.com", "ACTIVE");
        User user5 = new User("Anna", "anna@gmail.com", "INACTIVE");

        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);

        users.stream().sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed()).limit(3)
                .forEach(u -> System.out.println(u.username()));
    }
}