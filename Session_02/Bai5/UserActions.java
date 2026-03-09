package Session_02.Bai5;

public interface UserActions {
    default void logActivity(String activity){
        System.out.println("user : " +activity);
    }
}