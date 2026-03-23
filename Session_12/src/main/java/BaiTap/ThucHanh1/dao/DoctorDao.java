package BaiTap.ThucHanh1.dao;

import BaiTap.ThucHanh1.database.MyDatabase;
import BaiTap.ThucHanh1.model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao {

    public static List<Doctor> findDoctorBySpecialty(String specialty){
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = MyDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from doctors where specialty=?");
        ) {
            preparedStatement.setString(1,specialty);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("doctor_id"));
                doctor.setFullName(rs.getString("full_name"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setExpYears(rs.getInt("experience_years"));
                doctor.setBaseSalary(rs.getDouble("base_salary"));
                doctor.setPassword(rs.getString("password"));
                doctors.add(doctor);
            }

        }catch (Exception e){
            System.out.println("Lỗi : " + e.getMessage());
        }
        return doctors;
    }

    public static boolean updatePassword(int id, String newPass){
        boolean result = false;
        try (Connection connection = MyDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(" update doctors set password = ? where doctor_id = ?");
        ){
            preparedStatement.setString(1,newPass);
            preparedStatement.setInt(2 , id);
            int rs = preparedStatement.executeUpdate();
            return rs > 0 ;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static double calculateDutyFee(int doctor_id){
        double fee = 0 ;
        try (

                Connection connection = MyDatabase.getConnection();
                CallableStatement callableStatement = connection.prepareCall(" {call calculate_duty_fee(?,?)}");
        ) {
            callableStatement.setInt(1,doctor_id);
            callableStatement.registerOutParameter(2,Types.DOUBLE);
            callableStatement.execute();
            fee = callableStatement.getDouble(2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return fee ;
    }

    public static boolean checkDoctorId(int doctor_id){
        boolean found = false;

        try (
                Connection connection = MyDatabase.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select doctor_id from doctors where doctor_id = ?");

        ){
            preparedStatement.setInt(1,doctor_id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                found = true ;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return found ;
    }
}
