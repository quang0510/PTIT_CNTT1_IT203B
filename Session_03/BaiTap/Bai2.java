package Session_03.BaiTap;

import java.util.ArrayList;
import java.util.List;

public class Bai2 {
    public static void main(String[] args) {
        record User(String UserName , String email , String Status ){};

        User u1 = new User("Nguyễn Văn A" , "alice@gmail.com" , "INACTIVE");
        User u2 = new User("Nguyễn Văn B" , "bob@yahoo.com" , "INACTIVE");
        User u3 = new User("Nguyễn Văn C" , "charlie@gmail.com" , "ACTIVE");

        List<User> users = new ArrayList<>(List.of(u1 ,u2 ,u3));

        users.stream().filter(e -> e.email.endsWith("gmail.com")).forEach(System.out::println);
    }
}
