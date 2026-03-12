package Bai5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Bai5Test {

    @Test
    @DisplayName("ADMIN có quyền xóa người dùng")
    void canPerformAction() {
        assertTrue(Bai5.canPerformAction(Bai5.Role.ADMIN, Bai5.Action.DELETE_USER));
    }

    @Test
    @DisplayName("ADMIN có quyền khóa người dùng")
    void canPerformAction1() {
        assertTrue(Bai5.canPerformAction(Bai5.Role.ADMIN, Bai5.Action.LOCK_USER));
    }

    @Test
    @DisplayName("ADMIN có thể xem thông tin")
    void canPerformAction2() {
        assertTrue(Bai5.canPerformAction(Bai5.Role.ADMIN, Bai5.Action.VIEW_PROFILE));
    }

    @Test
    @DisplayName("MODERATOR không được xóa user")
    void canPerformAction3() {
        assertFalse(Bai5.canPerformAction(Bai5.Role.MODERATOR, Bai5.Action.DELETE_USER));
    }

    @Test
    @DisplayName("MODERATOR có quyền khóa user")
    void canPerformAction4() {
        assertTrue(Bai5.canPerformAction(Bai5.Role.MODERATOR, Bai5.Action.LOCK_USER));
    }

    @Test
    @DisplayName("MODERATOR được xem thông tin")
    void canPerformAction5() {
        assertTrue(Bai5.canPerformAction(Bai5.Role.MODERATOR, Bai5.Action.VIEW_PROFILE));
    }

    @Test
    @DisplayName("USER không có quyền xóa")
    void canPerformAction6() {
        assertFalse(Bai5.canPerformAction(Bai5.Role.USER, Bai5.Action.DELETE_USER));
    }

    @Test
    @DisplayName("USER không có quyền khóa")
    void canPerformAction7() {
        assertFalse(Bai5.canPerformAction(Bai5.Role.USER, Bai5.Action.LOCK_USER));
    }

    @Test
    @DisplayName("USER được xem thông tin cá nhân")
    void canPerformAction8() {
        assertTrue(Bai5.canPerformAction(Bai5.Role.USER, Bai5.Action.VIEW_PROFILE));
    }
}