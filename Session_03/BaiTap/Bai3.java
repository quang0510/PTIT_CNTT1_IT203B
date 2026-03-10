package Session_03.BaiTap;
import java.util.Optional;

public class Bai3 {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        Optional<UserRepository.User> user =  userRepository.findUserByUsername("Dương anime");
//        Optional<UserRepository.User> user =  userRepository.findUserByUsername("quang");

        if (!user.isEmpty()) {
            System.out.println("Welcome a : " + user.get().username());
        }else {
            System.out.println("Guest login");
        }
    }
}
