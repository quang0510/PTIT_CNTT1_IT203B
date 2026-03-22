package Session_11.Bai5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASS = "123456";
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Doctor(rs.getString("doctor_id"), rs.getString("fullname"), rs.getString("specialty")));
            }
        }
        return list;
    }
    public boolean addDoctor(Doctor d) throws SQLException {
        String sql = "INSERT INTO Doctors VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getId());
            ps.setString(2, d.getName());
            ps.setString(3, d.getSpecialty());
            return ps.executeUpdate() > 0;
        }
    }
    public void showStatistics() throws SQLException {
        String sql = "SELECT specialty, COUNT(*) as total FROM Doctors GROUP BY specialty";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- THỐNG KÊ CHUYÊN KHOA ---");
            while (rs.next()) {
                System.out.println(rs.getString("specialty") + ": " + rs.getInt("total") + " bác sĩ");
            }
        }
    }
}