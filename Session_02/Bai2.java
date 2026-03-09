package Session_02;

import java.util.function.Predicate;

public class Bai2 {
    public static void main(String[] args) {
//        String password = "123456789";
        String password = "123456";

        Predicate<String> validator = e -> e.length() >= 8;

        System.out.println(validator.test(password));
    }
}
