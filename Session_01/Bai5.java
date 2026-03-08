package Session_01;

public class Bai5 {
    public static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    public static class User{
        int age;

        public User(int age) {
            this.age = age;
        }

        public void setAge(int age) throws InvalidAgeException {
            if(age < 0){
                throw new InvalidAgeException("Tuổi không thể âm!");
            }
            this.age = age;
        }
    }
    public static void main(String[] args) throws  InvalidAgeException{

        User user = new User(11);

        try{
            user.setAge(-1);
        }catch(InvalidAgeException e){
            System.out.println(e.getMessage());
        }

    }

}
