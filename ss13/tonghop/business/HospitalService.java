package ss13.tonghop.business;
import java.sql.*;
import ss13.tonghop.database.DbConn;
import ss13.tonghop.dao.HospitalDAO;

public class HospitalService {
    private HospitalDAO d = new HospitalDAO();

    public void exitProcess(int pId, double m, boolean isErr) {
        Connection c = null;
        try {
            c = DbConn.getC();
            c.setAutoCommit(false);
            System.out.println("bat dau xu ly xuat vien cho " + pId);

            d.createInv(c, pId, m);
            System.out.println("da tao hoa don");

            d.updateP(c, pId);
            System.out.println("da cap nhat trang thai benh nhan");

            d.releaseB(c, pId, isErr);
            System.out.println("da giai phong giuong");

            c.commit();
            System.out.println("xong giao dich thanh cong");

        } catch (Exception e) {
            System.out.println("loi " + e.getMessage());
            try {
                if (c  !=  null) {
                    c.rollback();
                    System.out.println("da rollback du lieu an toan");
                }
            } catch (SQLException ex) {}
        } finally {
            try { if (c  !=  null) c.close(); } catch (SQLException e) {}
        }
    }
}
