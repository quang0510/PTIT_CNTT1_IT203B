package ss13.tonghop.dao;
import java.sql.*;

public class HospitalDAO {
    public void createInv(Connection c, int pId, double amt) throws SQLException {
        String sql = "insert into Invoices (p_id, amount) values (?, ?)";
        PreparedStatement p = c.prepareStatement(sql);
        p.setInt(1, pId);
        p.setDouble(2, amt);
        p.executeUpdate();
        p.close();
    }

    public void updateP(Connection c, int pId) throws SQLException {
        String sql = "update Patients set status = 'Da xuat vien' where id = ?";
        PreparedStatement p = c.prepareStatement(sql);
        p.setInt(1, pId);
        p.executeUpdate();
        p.close();
    }

    public void releaseB(Connection c, int pId, boolean isErr) throws SQLException {
        String tbl = isErr ? "BEDZZZ" : "Beds";
        String sql = "update " + tbl + " set status = 'Trong', p_id = null where p_id = ?";
        PreparedStatement p = c.prepareStatement(sql);
        p.setInt(1, pId);
        p.executeUpdate();
        p.close();
    }
}
