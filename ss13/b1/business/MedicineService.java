package ss13.b1.business;

import ss13.b1.dao.MedicineDAO;
import ss13.b1.database.ConnectionDB;
import java.sql.*;

public class MedicineService {
    private MedicineDAO dao = new MedicineDAO();

    public void thucHien(int mId, int pId, boolean isErr) {
        Connection c = null;
        try {
            c = ConnectionDB.getConn();
            c.setAutoCommit(false);
            System.out.println("bat dau qua trinh cap thuoc");

            dao.updateInv(c, mId);
            System.out.println("buoc 1 da tru kho thuoc thanh cong");

            if (isErr) {
                System.out.println("dang gap su co mang bat ngo");
                int bug = 10 / 0;
            }

            dao.addHist(c, pId, mId);
            System.out.println("buoc 2 da ghi lich su vao ho so benh nhan");

            c.commit();
            System.out.println("xac nhan giao dich hoan tat du lieu da duoc luu");

        } catch (Exception e) {
            System.out.println("loi he thong " + e.getMessage());
            try {
                if (c  !=  null) {
                    c.rollback();
                    System.out.println("canh bao da rollback du lieu kho thuoc duoc giu nguyen");
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