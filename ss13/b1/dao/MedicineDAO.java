package ss13.b1.dao;

import java.sql.*;

public class MedicineDAO {
    public void updateInv(Connection c, int id) throws SQLException {
        String sql = "update Medicine_Inventory set quantity = quantity - 1 where medicine_id = ?";
        PreparedStatement p = c.prepareStatement(sql);
        p.setInt(1, id);
        p.executeUpdate();
        p.close();
    }

    public void addHist(Connection c, int pId, int mId) throws SQLException {
        String sql = "insert into Prescription_History (patient_id, medicine_id) values (?, ?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, pId);
        ps.setInt(2, mId);
        ps.executeUpdate();
        ps.close();
    }
}