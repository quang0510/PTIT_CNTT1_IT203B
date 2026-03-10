package Session_03.BaiTap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bai4 {
    public record User(String username, String email,String status) { }

    public static void main(String[] args) {
        User user1 = new User("Dương", "duong@gmail.com", "ACTIVE");
        User user2 = new User("Quang", "quang@gmail.com", "INACTIVE");
        User user3 = new User("Dương", "duong@gmail.com", "ACTIVE");
        User user4 = new User("HUy", "huy@gmail.com", "INACTIVE");

        List<User> lists = Arrays.asList(user1, user2, user3, user4);
        Set<String> users = new HashSet<>();

        List<User> userFilter = lists.stream().filter(value -> users.add(value.username())).toList();

        userFilter.forEach(System.out::println);
    }
}
