package ss13.b2.business;

import ss13.b2.dao.PaymentDAO;
import ss13.b2.database.DbConn;
import java.sql.*;

public class PaymentService {
    private PaymentDAO dao = new PaymentDAO();

    public void pay(int pId, int invId, double amt) {
        Connection c = null;
        try {
            c = DbConn.getC();
            c.setAutoCommit(false);
            System.out.println("bat dau thanh toan vien phi");

            dao.deduct(c, pId, amt);
            System.out.println("da tru tien trong vi benh nhan");

            dao.updateInv(c, invId);
            System.out.println("da cap nhat trang thai hoa don");

            c.commit();
            System.out.println("thanh toan hoan tat");

        } catch (Exception e) {
            System.out.println("phat hien loi " + e.getMessage());
            if (c != null) {
                try {
                    c.rollback();
                    System.out.println("da thuc hien rollback tien da duoc tra lai vi");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (c  !=  null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
