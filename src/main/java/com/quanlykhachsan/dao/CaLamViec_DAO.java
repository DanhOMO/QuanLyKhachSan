/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.CaLamViec;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.model.ConnectDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class CaLamViec_DAO {
    public static void main(String[] args) {
        try {
            ConnectDB con =  new ConnectDB();
            con.connect();
            CaLamViec_DAO a = new CaLamViec_DAO();
            a.getList().forEach(x -> System.out.println(x));
        } catch (SQLException ex) {
            Logger.getLogger(CaLamViec_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private   List<CaLamViec> ca = new ArrayList<>();
    public CaLamViec_DAO(){
        docTuBang();
    }
 
    public List<CaLamViec> getList(){
        return ca;
    }
   public void capNhatTienCaLamViec(String maNhanVien, LocalDate ngayLamViec, double tongTienThem) {
    try {
        // Kết nối đến cơ sở dữ liệu
        Connection con = ConnectDB.getInstance().getConnection();

        // Câu lệnh SQL để lấy tổng tiền hiện tại của ca làm việc của nhân viên vào một ngày nhất định
        String selectSql = "SELECT tongTienTrongCa FROM CaLamViec WHERE maNhanVien = ? AND ngayLamViec = ?";
        PreparedStatement psSelect = con.prepareStatement(selectSql);
        psSelect.setString(1, maNhanVien);
        psSelect.setDate(2, Date.valueOf(ngayLamViec));  // Chuyển LocalDate sang Date

        // Thực thi câu lệnh SQL và lấy tổng tiền hiện tại
        ResultSet rs = psSelect.executeQuery();
        double tongTienHienTai = 0;

        if (rs.next()) {
            tongTienHienTai = rs.getDouble("tongTienTrongCa");
        } else {
            System.out.println("Không tìm thấy ca làm việc cho nhân viên " + maNhanVien + " vào ngày " + ngayLamViec);
            return; // Nếu không tìm thấy dữ liệu, thoát khỏi phương thức
        }

        // Tính tổng tiền mới sau khi cộng thêm
        double tongTienMoi = tongTienHienTai + tongTienThem;

        // Cập nhật tổng tiền mới vào cơ sở dữ liệu
        String updateSql = "UPDATE CaLamViec SET tongTienTrongCa = ? WHERE maNhanVien = ? AND ngayLamViec = ?";
        PreparedStatement psUpdate = con.prepareStatement(updateSql);
        psUpdate.setDouble(1, tongTienMoi);
        psUpdate.setString(2, maNhanVien);
        psUpdate.setDate(3, Date.valueOf(ngayLamViec));  // Chuyển LocalDate sang Date

        // Thực thi câu lệnh cập nhật
        int rowsAffected = psUpdate.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Cập nhật tổng tiền thành công cho ca làm việc của nhân viên " + maNhanVien + " vào ngày " + ngayLamViec);
        } else {
            System.out.println("Không thể cập nhật tổng tiền cho ca làm việc của nhân viên " + maNhanVien + " vào ngày " + ngayLamViec);
        }

        // Đóng kết nối
        psSelect.close();
        psUpdate.close();
        con.close();

    } catch (SQLException ex) {
        Logger.getLogger(CaLamViec_DAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}



    public void themMoi(CaLamViec a) {
    try {
        // Kết nối đến cơ sở dữ liệu
        Connection con = ConnectDB.getInstance().getConnection();

        // Câu lệnh SQL để chèn dữ liệu vào bảng CaLamViec
        String sql = "INSERT INTO CaLamViec (maCaLamVien, tenCaLamViec, ngayLamViec, tongTienTrongCa, maNhanVien) VALUES (?, ?, ?, ?, ?)";

        // Chuẩn bị câu lệnh SQL
        PreparedStatement ps = con.prepareStatement(sql);

        // Gán các tham số cho câu lệnh SQL
        ps.setString(1, a.getMaCaLamViec());  // Mã ca làm việc
        ps.setString(2, a.getTenCaLamViec().getCa());  // Tên ca làm việc (Giả sử TenCaLamViec là enum hoặc đối tượng)
        ps.setDate(3, Date.valueOf(a.getNgayLamViec()));  // Ngày làm việc
        ps.setDouble(4, a.getTongTienTrongCa());  // Tổng tiền trong ca
        ps.setString(5, a.getNhanVien().getMaNhanVien());  // Mã nhân viên

        // Thực thi câu lệnh SQL để thêm dữ liệu vào bảng
        ps.executeUpdate();

        // Đóng kết nối
        ps.close();
        con.close();

    } catch (SQLException ex) {
        Logger.getLogger(CaLamViec_DAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public void docTuBang() {
        
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
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
