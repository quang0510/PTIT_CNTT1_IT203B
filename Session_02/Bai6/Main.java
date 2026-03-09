package Session_02.Bai6;

public class Main {
    public static void main(String[] args) {
        User user = new User("son", "123");

        UserProcessor processor = UserUtils::convertToUpperCase;

        System.out.println(processor.process(user));

    }
}