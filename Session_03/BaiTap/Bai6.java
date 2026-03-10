package Session_03.BaiTap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bai6 {
    public static void main(String[] args) {
        Post javaPost = new Post(Arrays.asList("java", "backend"));
        Post pythonPost = new Post(Arrays.asList("python", "data"));

        List<Post> posts = Arrays.asList(javaPost, pythonPost);

        for (Post string : posts){
            System.out.println(string.getTags());
        }

        List<String> allTags = posts.stream().flatMap(post -> post.getTags().stream()).collect(Collectors.toList());

        System.out.println(allTags);

    }
}
