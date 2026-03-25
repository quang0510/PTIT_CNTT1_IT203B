package ss13.b3.business;

import ss13.b3.dao.ExitDAO;
import ss13.b3.database.DbConn;
import java.sql.*;

public class ExitService {
    private ExitDAO d = new ExitDAO();

    public void process(int pId, double fee) {
        Connection c = null;
        try {
            c = DbConn.getC();
            c.setAutoCommit(false);
            System.out.println("bat dau xu ly xuat vien");

            double bal = d.checkB(c, pId);
            if (bal < 0) throw new Exception("loi id khong ton tai");
            if (bal < fee) throw new Exception("loi so du khong du");
            System.out.println("kiem tra so du thanh cong");

            if (d.subMoney(c, pId, fee) == 0) throw new Exception("loi tru tien");
            System.out.println("da tru tien thanh cong");

            if (d.clearB(c, pId) == 0) throw new Exception("loi khong tim thay giuong");
            System.out.println("da giai phong giuong benh");

            if (d.setS(c, pId) == 0) throw new Exception("loi cap nhat trang thai");
            System.out.println("da cap nhat ho so");

            c.commit();
            System.out.println("giao dich thanh cong tat ca du lieu da chot");

        } catch (Exception e) {
            System.out.println("that bai " + e.getMessage());
            try {
                if (c  !=  null) {
                    c.rollback();
                    System.out.println("da thuc hien rollback toan bo");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
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