package BaiTap.BaiTapVeNha.Bai5.dao;

import BaiTap.BaiTapVeNha.Bai5.model.Patient;

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PatientDAO {

    private Connection connection;

    public PatientDAO(Connection conn) {
        this.connection = conn;
    }

    // 1. Lấy danh sách
    public List<Patient> getAll() throws SQLException {
        List<Patient> list = new ArrayList<>();

        String sql = "select id, name, age, department from patients";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Patient p = new Patient(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("department")
            );
            list.add(p);
        }

        rs.close();
        preparedStatement.close();
        return list;
    }

    // 2. Thêm bệnh nhân
    public void insert(Patient p) throws SQLException {
        String sql = "insert into patients(name, age, department) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, p.getName());
        preparedStatement.setInt(2, p.getAge());
        preparedStatement.setString(3, p.getDepartment());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    // 3. Update
    public boolean updateDepartment(int id, String dept) throws SQLException {
        String sql = "update patients set department = ? where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, dept);
        preparedStatement.setInt(2, id);

        int rows = preparedStatement.executeUpdate();
        preparedStatement.close();

        return rows > 0;
    }

    // 4. Gọi stored procedure
    public String calculateFee(int id) throws SQLException {
        CallableStatement cstmt = connection.prepareCall("{call CALCULATE_DISCHARGE_FEE(?, ?)}");

        cstmt.setInt(1, id);
        cstmt.registerOutParameter(2, Types.DECIMAL);

        cstmt.execute();

        double fee = cstmt.getDouble(2);

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String result = nf.format(fee);

        cstmt.close();
        return result;
    }
}
