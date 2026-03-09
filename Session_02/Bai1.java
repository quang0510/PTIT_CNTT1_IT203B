package Session_02;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Bai1 {
    public static class User {
        String name;
        String role;

        public User(String role, String name) {
            this.role = role;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        User user = new User("user", "name1");
        User user1 = new User("admin", "name2");
//        Kiểm tra xem một User có phải là Admin hay không (trả về true/false).
        Predicate<User> roleValidator = u -> u.role.equals("admin");
        System.out.println(roleValidator.test(user));
        System.out.println(roleValidator.test(user1));

//        Chuyển đổi một đối tượng User thành một chuỗi String chứa thông tin username.
        Function<User, String> getUsername = u -> u.name;
        System.out.println(getUsername.apply(user));

//        In thông tin chi tiết của User ra màn hình Console.
        Consumer<User> detail = u -> System.out.println(u.name);
        detail.accept(user);


//        Khởi tạo một đối tượng User mới với các giá trị mặc định.
        Supplier<User> userSupplier = () -> new User("admin", "name3");
        System.out.println(userSupplier.get().name);
    }
}
 /*
        1. Kiểm tra xem một User có phải là Admin hay không (trả về true/false)
        → Sử dụng: Predicate<User>
        Lý do:
        Predicate nhận một tham số đầu vào và trả về giá trị boolean.
        Trong trường hợp này, ta truyền vào một đối tượng User và kiểm tra
        xem role của User có phải là Admin hay không, nên kết quả trả về true/false.


        2. Chuyển đổi một đối tượng User thành chuỗi String chứa thông tin username
        → Sử dụng: Function<User, String>
        Lý do:
        Function dùng để chuyển đổi dữ liệu từ kiểu này sang kiểu khác.
        Ở đây ta nhận vào một User và trả về username dạng String.


        3. In thông tin chi tiết của User ra màn hình Console
        → Sử dụng: Consumer<User>
        Lý do:
        Consumer nhận một tham số đầu vào nhưng không trả về giá trị.
        Nó thường được dùng để thực hiện một hành động nào đó như in dữ liệu ra màn hình.


        4. Khởi tạo một đối tượng User mới với các giá trị mặc định
        → Sử dụng: Supplier<User>
        Lý do:
        Supplier không nhận tham số đầu vào nhưng trả về một đối tượng.
        Trong trường hợp này nó sẽ tạo và trả về một User mới với giá trị mặc định.
        */