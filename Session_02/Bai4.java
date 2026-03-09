package Session_02;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Bai4 {
    public static void main(String[] args) {
        Supplier<User> userFactory = User::new;

        List<User> users = new ArrayList<>();
        users.add(new User("Alice"));
        users.add(new User("Bob"));
        users.add(new User("Charlie"));

        System.out.println("--- Danh sách Username ---");
        users.stream()
                .map(User::getUsername)
                .forEach(System.out::println);
    }
}

 class User {
    private String username;

    public User() { this.username = "Default User"; }

    public User(String username) { this.username = username; }

    public String getUsername() { return username; }
}