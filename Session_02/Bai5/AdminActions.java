package Session_02.Bai5;

public interface AdminActions {
    default void logActivity(String activity){
        System.out.println("admin : " +activity);
    }
}