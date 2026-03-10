package Session_03.BaiTap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    public record User(String username, String email) { }
    private final List<User> users;


    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User("Dương anime","anime@gmail.com"));
        users.add(new User("Toàn què","toan@gmail.com"));
        users.add(new User("tôn ngộ ko","huy@yahoo"));
    }


    public Optional<User> findUserByUsername(String username) {
        return users.stream().filter(u -> u.username().equalsIgnoreCase(username)).findFirst();
    }

}
