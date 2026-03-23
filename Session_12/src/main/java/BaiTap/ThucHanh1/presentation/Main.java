package BaiTap.ThucHanh1.presentation;

import BaiTap.ThucHanh1.business.DoctorBusiness;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Tìm kiếm bác sĩ theo chuyên khoa
//        DoctorBusiness.findDoctorBySpecialty(sc);
//        DoctorBusiness.updatePassword(sc);
        DoctorBusiness.calculatorFeeOfDoctor(sc);

        sc.close();
    }
}
