package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.CaLamViec;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.model.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ThongKe_DAO {
    List<CaLamViec> caLamViec = null;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<CaLamViec> getList() {
        List<CaLamViec> ca = new ArrayList<>();
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM CaLamViec";
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CaLamViec calamviec = new CaLamViec(
                    rs.getString("maCaLamVien"),  // Chỉnh sửa tên cột từ "maCaLamVien" thành "maCaLamViec"
                    com.quanlykhachsan.enum_Class.CaLamViec.getCaLamViec(rs.getString("tenCaLamViec")),
                    rs.getObject("ngayLamViec", LocalDate.class),
                    rs.getDouble("tongTienTrongCa"),
                    new NhanVien(rs.getString("maNhanVien"))  // Lấy giá trị thực tế từ ResultSet
                );
                ca.add(calamviec);
            }

            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ca;  // Thêm return để trả về danh sách CaLamViec
    }
}
