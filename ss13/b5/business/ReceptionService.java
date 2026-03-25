package ss13.b5.business;
import java.sql.*;
import ss13.b5.database.DbConn;
import ss13.b5.dao.ReceptionDAO;

public class ReceptionService {
    private ReceptionDAO dao = new ReceptionDAO();

    public void xemGiuong() {
        try (Connection c = DbConn.getC()) {
            dao.getEmpty(c);
        } catch (Exception e) {
            System.out.println("loi he thong " + e.getMessage());
        }
    }

    public void vaoVien(String ten, int tuoi, int gId, double tien) {
        Connection c = null;
        try {
            c = DbConn.getC();
            c.setAutoCommit(false);

            int bnId = dao.addBN(c, ten, tuoi, gId);
            if (bnId == -1) throw new Exception("ko tao dc bn");

            if (dao.updateG(c, gId) == 0) throw new Exception("giuong ko hop le");

            dao.addTC(c, bnId, tien);

            c.commit();
            System.out.println("tiep nhan thanh cong");
        } catch (Exception e) {
            System.out.println("that bai " + e.getMessage());
            try { if (c != null) c.rollback(); } catch (SQLException ex) {}
        } finally {
            try { if (c != null) c.close(); } catch (SQLException ex) {}
        }
    }
}
