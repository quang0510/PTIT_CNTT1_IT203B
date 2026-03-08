package Session_01;

public class Bai3 {
    public static class User{
        int age;

        public User(int age) {
            this.age = age;
        }

        public void setAge(int age) {
            if(age < 0){
                throw new IllegalArgumentException("Tuổi không thể âm!");
            }
            this.age = age;
        }
    }
    public static void main(String[] args) {
        User user1 = new User(11);
        user1.setAge(12);
        User user2 = new User(11);
        user2.setAge(-1);

    }
}
