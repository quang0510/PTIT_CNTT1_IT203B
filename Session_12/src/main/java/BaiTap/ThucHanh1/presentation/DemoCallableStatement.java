package BaiTap.ThucHanh1.presentation;

import BaiTap.ThucHanh1.database.MyDatabase;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

public class DemoCallableStatement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter gender want search patient : ");
        String gender = sc.nextLine();

        try (
                Connection con = MyDatabase.getConnection();
                CallableStatement callableStatement = con.prepareCall(" { call get_patient_by_gender(?,?)}");
        ) {
            callableStatement.setString(1, gender);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            int numberPatient = callableStatement.getInt(2);
            System.out.println("Số bênh nhân có giới tinh " + gender +  "là  : " + numberPatient);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
