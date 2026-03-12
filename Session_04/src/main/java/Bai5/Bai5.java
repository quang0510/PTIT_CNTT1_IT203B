package Bai5;

public class Bai5 {
    public static enum Role {
        ADMIN, MODERATOR, USER
    }

    public static enum Action {
        DELETE_USER, LOCK_USER, VIEW_PROFILE
    }
    public static boolean canPerformAction(Role role, Action action) {
        if (role == null || action == null) return false;

        return switch (role) {
            case ADMIN -> true;
            case MODERATOR -> action != Action.DELETE_USER;
            case USER -> action == Action.VIEW_PROFILE;
            default -> false;
        };
    }
}



