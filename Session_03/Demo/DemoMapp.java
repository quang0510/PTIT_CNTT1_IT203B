package Session_03.Demo;

import java.util.Arrays;
import java.util.List;

public class DemoMapp {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("Hiền lương" , " huy dz " , "Trần trí anime");

        //chuyển hết thành chữ hoa
        list1.stream().map(String :: toUpperCase ).forEach(System.out::println);  // cách 1
        list1.stream().map(e -> e.toUpperCase()).forEach(e -> System.out.println(e) );  // cách 2
    }
}
