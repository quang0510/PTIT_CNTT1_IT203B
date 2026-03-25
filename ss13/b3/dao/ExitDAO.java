package ss13.b3.dao;

import java.sql.*;

public class ExitDAO {
    public double checkB(Connection c, int id) throws SQLException {
        String sql = "select balance from Patients where p_id = ?";
        PreparedStatement p = c.prepareStatement(sql);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        if (r.next()) return r.getDouble("balance");
        return -1;
    }

    public int subMoney(Connection c, int id, double amt) throws SQLException {
        String sql = "update Patients set balance = balance - ? where p_id = ?";
        PreparedStatement p = c.prepareStatement(sql);
        p.setDouble(1, amt);
        p.setInt(2, id);
        return p.executeUpdate();
    }

    public int clearB(Connection c, int pId) throws SQLException {
        String sql = "update Beds set status = 'Empty', p_id = null where p_id = ?";
        PreparedStatement p = c.prepareStatement(sql);
        p.setInt(1, pId);
        return p.executeUpdate();
    }

    public int setS(Connection c, int id) throws SQLException {
        String sql = "update Patients set status = 'Discharged' where p_id = ?";
        PreparedStatement p = c.prepareStatement(sql);
        p.setInt(1, id);
        return p.executeUpdate();
    }
}