package BaiTap.ThucHanh1.business;

import BaiTap.ThucHanh1.dao.DoctorDao;
import BaiTap.ThucHanh1.model.Doctor;

import java.util.List;
import java.util.Scanner;

public class DoctorBusiness {

    public static void findDoctorBySpecialty(Scanner scanner){
        System.out.println("Nhập chuyên khoa muốn tìm : ");
        String specialty = scanner.nextLine();

        List<Doctor> listDoctor = DoctorDao.findDoctorBySpecialty(specialty);
        if(listDoctor.isEmpty()){
            System.out.println("Không tìm bác sĩ có chuyên khoa : " + specialty);
        }else {
            System.out.println("Danh sách bác si tìm thấy !" );
            listDoctor.forEach(Doctor::displayInfo);
        }
    }

    public static void updatePassword(Scanner scanner){
        System.out.println("Nhập id bác sĩ muốn sửa thông tin : ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Nhập mật khẩu mới : ");
        String newPassword = scanner.nextLine();

        boolean rs = DoctorDao.updatePassword(id ,newPassword);
        if(rs){
            System.out.println("cập nhật thành công");
        }else {
            System.out.println("Cập nhật thất bại , do id bác sĩ ko tồn tại ");
        }
    }


    public static void calculatorFeeOfDoctor(Scanner scanner){
        System.out.println("Nhập vào id bác sĩ muốn tính phí : ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean checkDoctorId = DoctorDao.checkDoctorId(id);
        if(checkDoctorId){
            double fee = DoctorDao.calculateDutyFee(id);
            System.out.println("Tiền trưực của bác sĩ có id : " + id + " là : " + fee);
        }else {
            System.out.println("Doctor id  ko tồn tại !");
        }


    }
}
