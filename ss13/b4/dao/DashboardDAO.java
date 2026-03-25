package ss13.b4.dao;

import java.sql.*;
import java.util.*;
import ss13.b4.business.BenhNhanDTO;
import ss13.b4.database.DbConn;

public class DashboardDAO {
    public List<BenhNhanDTO> getFullData() throws Exception {
        String sql = "select b.id, b.ten, d.ten_dv " +
                     "from BenhNhan b " +
                     "left join DichVu d on b.id = d.bn_id " +
                     "order by b.id";
        
        Map<Integer, BenhNhanDTO> map = new LinkedHashMap<>();
        
        try (Connection c = DbConn.getC(); 
             PreparedStatement p = c.prepareStatement(sql);
             ResultSet r = p.executeQuery()) {
            
            while (r.next()) {
                int id = r.getInt("id");
                BenhNhanDTO dto = map.get(id);
                if (dto == null) {
                    dto = new BenhNhanDTO(id, r.getString("ten"));
                    map.put(id, dto);
                }
                
                String dv = r.getString("ten_dv");
                if (dv != null) {
                    dto.dsDv.add(dv);
                }
            }
        }
        return new ArrayList<>(map.values());
    }
}
