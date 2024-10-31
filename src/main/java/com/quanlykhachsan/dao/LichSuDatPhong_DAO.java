package com.quanlykhachsan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.quanlykhachsan.entity.ChiTietHoaDon;
import com.quanlykhachsan.entity.LichSuDatPhong;
import com.quanlykhachsan.entity.Phong;
import com.quanlykhachsan.model.ConnectDB;

public class LichSuDatPhong_DAO {

    private ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO();
    private Phong_DAO p_dao = new Phong_DAO();
    private List<LichSuDatPhong> listLishSuDatPhong = new ArrayList<>();
    private Connection con; // Khai báo Connection ở trên

    public LichSuDatPhong_DAO() {
        // Đọc dữ liệu từ bảng khi khởi tạo
        
        docTuBang();
    }

    public List<LichSuDatPhong> getList() {
        return listLishSuDatPhong;
    }

    public void docTuBang() {
        String sql = "SELECT * FROM LichSuDatPhong";
        listLishSuDatPhong.clear();
        
            con = ConnectDB.getInstance().getConnection(); // Khởi tạo kết nối
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
                

            while (rs.next()) {
                
                LichSuDatPhong lsdp = new LichSuDatPhong(new ChiTietHoaDon(rs.getString("maChiTietHoaDon")), new Phong(rs.getString("maPhong")), rs.getInt("soLuong"),
                        rs.getDate("thoiGianDatPhong").toLocalDate());
                listLishSuDatPhong.add(lsdp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }

    public boolean themLichSuDatPhong(LichSuDatPhong lsdp) {
        String sql = "INSERT INTO LichSuDatPhong (maChiTietHoaDon, maPhong, soLuong, thoiGianDatPhong) VALUES (?, ?, ?, ?)";
        
        try {
            con = ConnectDB.getInstance().getConnection(); // Khởi tạo kết nối
            PreparedStatement ps = con.prepareStatement(sql);
            
            // Set giá trị cho các tham số trong câu lệnh SQL
            ps.setString(1, lsdp.getChiTietHoaDon().getMaChiTietHoaDon());
            ps.setString(2, lsdp.getPhong().getMaPhong());
            ps.setInt(3, lsdp.getSoLuong());
            ps.setDate(4, java.sql.Date.valueOf(lsdp.getThoiGianDatPhong())); // Chuyển LocalDate thành java.sql.Date

            // Thực thi câu lệnh và kiểm tra kết quả
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu thêm thành công

        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatPhong_DAO.class.getName()).log(Level.SEVERE, "Error inserting into LichSuDatPhong table", ex);
        } 
        return false; // Trả về false nếu xảy ra lỗi
    }
}
