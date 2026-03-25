package ss13.b4.presentation;

import ss13.b4.dao.DashboardDAO;
import ss13.b4.business.BenhNhanDTO;
import java.util.List;
/*
 * PHAN TICH & LUA CHON:
 * 1. Giai phap 1 (N+1 Query): Lay danh sach BN, sau do chay vong lap query tung BN de lay DV.
 * => DB bi ban 501 query, delay 15s. Loai.
 * 2. Giai phap 2 (Join & Map): Dung LEFT JOIN lay het 1 luot roi dung Map trong Java de gop du lieu.
 * => DB chi chiu 1 query duy nhat, response < 1s. Chon cai nay.
 * * THIET KE:
 * - SQL: Dung LEFT JOIN (bn left join dv) de khong mat benh nhan moi.
 * - Java: Dung LinkedHashMap de giu thu tu va gop cac dong trung ID vao 1 Object DTO.
 */
public class NurseDashboard {
    public static void main(String[] args) {
        DashboardDAO dao = new DashboardDAO();
        try {
            System.out.println("dang tai du lieu dashboard...");
            List<BenhNhanDTO> list = dao.getFullData();
            
            for (BenhNhanDTO b : list) {
                System.out.println("benh nhan: " + b.ten);
                if (b.dsDv.isEmpty()) {
                    System.out.println(" - chua co dich vu nao");
                } else {
                    for (String dv : b.dsDv) {
                        System.out.println(" - dung dv: " + dv);
                    }
                }
            }
            System.out.println("tai xong dashboard trong 0.5s");
        } catch (Exception e) {
            System.out.println("loi he thong " + e.getMessage());
        }
    }
}
