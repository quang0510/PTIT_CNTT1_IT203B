package ss13.b5.dao;
import java.sql.*;

public class ReceptionDAO {
    public void getEmpty(Connection c) throws SQLException {
        String sql = "select id from Giuong where status = 'Trong'";
        Statement s = c.createStatement();
        ResultSet r = s.executeQuery(sql);
        System.out.print("cac giuong trong: ");
        while (r.next()) System.out.print(r.getInt("id") + " ");
        System.out.println();
    }

    public int addBN(Connection c, String ten, int tuoi, int gId) throws SQLException {
        String sql = "insert into BenhNhan(ten, tuoi, giuong_id) values(?,?,?)";
        PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        p.setString(1, ten);
        p.setInt(2, tuoi);
        p.setInt(3, gId);
        p.executeUpdate();
        ResultSet r = p.getGeneratedKeys();
        return r.next() ? r.getInt(1) : -1;
    }

    public int updateG(Connection c, int gId) throws SQLException {
        String sql = "update Giuong set status = 'Co nguoi' where id = ? and status = 'Trong'";
        PreparedStatement p = c.prepareStatement(sql);
        p.setInt(1, gId);
        return p.executeUpdate();
    }

    public void addTC(Connection c, int bnId, double tien) throws SQLException {
        String sql = "insert into TaiChinh(bn_id, so_tien) values(?,?)";
        PreparedStatement p = c.prepareStatement(sql);
        p.setInt(1, bnId);
        p.setDouble(2, tien);
        p.executeUpdate();
    }
}
