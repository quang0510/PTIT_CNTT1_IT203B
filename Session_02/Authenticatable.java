package Session_02;

public interface Authenticatable {
    abstract String getPassword();

    default boolean isAuthenticated() {
        String password = getPassword();
        return password != null && !password.isEmpty();
    }
}
