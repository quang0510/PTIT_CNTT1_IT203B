package ss13.b2.dao;

import java.sql.*;

public class PaymentDAO {
    public void deduct(Connection c, int pId, double amt) throws SQLException {
        String sql = "update Patient_Wallet set balance = balance - ? where patient_id = ?";
        PreparedStatement p = c.prepareStatement(sql);
        p.setDouble(1, amt);
        p.setInt(2, pId);
        p.executeUpdate();
        p.close();
    }

    public void updateInv(Connection c, int invId) throws SQLException {
        String sql = "update Invoicesss set status = 'PAID' where invoice_id = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, invId);
        ps.executeUpdate();
        ps.close();
    }
}
