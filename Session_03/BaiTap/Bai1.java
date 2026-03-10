package Session_03.BaiTap;

import java.util.ArrayList;
import java.util.List;

public class Bai1 {
    public static void main(String[] args) {
        record User(String UserName , String email , String Status ){};

        User u1 = new User("Nguyễn Văn A" , "nva@gmail.com" , "INACTIVE");
        User u2 = new User("Nguyễn Văn B" , "nvb@gmail.com" , "INACTIVE");
        User u3 = new User("Nguyễn Văn C" , "nvc@gmail.com" , "ACTIVE");

        List<User> users = new ArrayList<>(List.of(u1 ,u2 ,u3));

//        users.stream().forEach(System.out::println);
        users.stream().forEach(e -> System.out.println(e));

    }
}
