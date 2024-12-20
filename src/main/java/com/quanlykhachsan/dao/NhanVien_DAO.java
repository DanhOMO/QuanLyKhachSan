/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.enum_Class.GioiTinh;
import com.quanlykhachsan.enum_Class.TrangThaiNhanVien;
import com.quanlykhachsan.enum_Class.TrangThaiTaiKhoan;
import com.quanlykhachsan.model.ConnectDB;
import com.quanlykhachsan.entity.KhuVuc;
import com.quanlykhachsan.entity.LoaiNhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class NhanVien_DAO {
     private List<NhanVien> list = new ArrayList<>();
        public NhanVien_DAO(){
            docTuBang();
        }
    private Connection con = null;
          public List<NhanVien> getList(){
           return list;
       }
       public NhanVien getIndex(int i){
           return list.get(i);
       }
       public boolean themNhanVien(NhanVien voucher){
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO NhanVien VALUES(?,?,?,?,?, ? , ? , ? , ?)");
            ps.setString(1, voucher.getMaNhanVien());
            ps.setString(2, voucher.getTenNhanVien());
            ps.setString(3, voucher.getSoDienThoai());
            ps.setString(4, (voucher.getGioiTinh().getGioiTinh()));
            ps.setString(5 , voucher.getDiaChi());
            ps.setDate(6, Date.valueOf(voucher.getNgaySinh()));
            ps.setString(7, voucher.getEmail());
            ps.setString(8, voucher.getLoaiNhanVien().getMaLoaiNhanVien());
            ps.setString(9, voucher.getTrangThai().getTrangThaiNhanVien());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
       
       public boolean capNhatNhanVien(NhanVien a) throws SQLException{
           con = ConnectDB.getInstance().getConnection();
           String sql = "update NhanVien set tenNhanVien = ?, soDienThoai = ?, gioiTinh = ?, diaChi = ?, ngaySinh = ?, email = ?, maLoaiNhanVien = ?  ,trangThai = ?"
                   + "where maNhanVien = ?";
           PreparedStatement state = con.prepareStatement(sql);
           state.setString(1, a.getTenNhanVien());
           state.setString(2, a.getSoDienThoai());
           state.setString(3,a.getGioiTinh().getGioiTinh()) ;
           state.setString(4,a.getDiaChi()) ;
           state.setDate(5, Date.valueOf(a.getNgaySinh()));
           state.setString(6,a.getEmail()) ;
           state.setString(7,a.getLoaiNhanVien().getMaLoaiNhanVien()) ;
           state.setString(8,a.getTrangThai().getTrangThaiNhanVien());
           state.setString(9, a.getMaNhanVien()                );
           state.executeUpdate(); 
           state.close();
           return true;
       }
   public DefaultTableModel docDuLieuVaoBan() {
    // Thêm tên cột vào DefaultTableModel
    DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Nhân Viên", "Tên Nhân Viên", "Số Điện Thoại", "Địa Chỉ","Giới Tính", "Ngày Sinh", "Email", "Loại Nhân Viên", "Trạng Thái"}, 0);

    // Thêm dữ liệu vào DefaultTableModel
    list.stream().forEach(x -> {
        dtm.addRow(new Object[]{
            x.getMaNhanVien(), 
            x.getTenNhanVien(), 
            x.getSoDienThoai(),
            x.getDiaChi(),
            x.getGioiTinh().getGioiTinh(),
            x.getNgaySinh(),
            x.getEmail(),
            x.getLoaiNhanVien().getMaLoaiNhanVien(),
            x.getTrangThai().getTrangThaiNhanVien()
        });
    });
    
    return dtm;
}
        public NhanVien timTheoMa(String ma){
            return list.stream().filter(x -> x.getMaNhanVien().equalsIgnoreCase(ma)).findFirst().orElse(null);
        }
       public void docTuBang(){
           list.clear();
           con =  ConnectDB.getInstance().getConnection();
           String sql = "select * from NhanVien";
           
           try {
               PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    NhanVien phong = new NhanVien(
                    rs.getString("maNhanVien"),
                    rs.getString("tenNhanVien"),
                    rs.getString("soDienThoai"),
                            GioiTinh.setGioiTinh(rs.getString("gioiTinh")),
                    rs.getString("diaChi"),
                            rs.getDate("ngaySinh").toLocalDate(),
                            rs.getString("email"),
                            new LoaiNhanVien(rs.getString("maLoaiNhanVien")),
                            TrangThaiNhanVien.setTrangThaiNhanVien(rs.getString("trangThai"))
                    );
                   list.add(phong); 
               }
             rs.close();
             ps.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
        
       }
       public NhanVien timNhanVienTheoSoDienThoai(String soDienThoai) {
           NhanVien a = null;
    list.clear();
    con = ConnectDB.getInstance().getConnection();
    String sql = "select * from NhanVien where soDienThoai = ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, soDienThoai);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            NhanVien nhanVien = new NhanVien(
                rs.getString("maNhanVien"),
                rs.getString("tenNhanVien"),
                rs.getString("soDienThoai"),
                GioiTinh.setGioiTinh(rs.getString("gioiTinh")),
                rs.getString("diaChi"),
                rs.getDate("ngaySinh").toLocalDate(),
                rs.getString("email"),
                new LoaiNhanVien(rs.getString("maLoaiNhanVien")),
                TrangThaiNhanVien.setTrangThaiNhanVien(rs.getString("trangThai"))
            );
            a = nhanVien;
        }

        rs.close();
        ps.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return a;
}
       public NhanVien timNhanVienTheoEmail(String soDienThoai) {
           NhanVien a = null;
    list.clear();
    con = ConnectDB.getInstance().getConnection();
    String sql = "select * from NhanVien where email = ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, soDienThoai);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            NhanVien nhanVien = new NhanVien(
                rs.getString("maNhanVien"),
                rs.getString("tenNhanVien"),
                rs.getString("soDienThoai"),
                GioiTinh.setGioiTinh(rs.getString("gioiTinh")),
                rs.getString("diaChi"),
                rs.getDate("ngaySinh").toLocalDate(),
                rs.getString("email"),
                new LoaiNhanVien(rs.getString("maLoaiNhanVien")),
                TrangThaiNhanVien.setTrangThaiNhanVien(rs.getString("trangThai"))
            );
            a = nhanVien;
        }

        rs.close();
        ps.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return a;
}
       public void timNhanVienTheoTrangThaiTaiKhoan(TrangThaiTaiKhoan tt){
    	   list.clear();
           con =  ConnectDB.getInstance().getConnection();
           String sql = "select nv.* from NhanVien nv "
           		+ "join TaiKhoan tk on nv.maNhanVien = tk.maNhanVien "
           		+ "where tk.trangThai = ?";
           try {
               PreparedStatement ps = con.prepareStatement(sql);
               ps.setString(1, tt.toString()); // Giả sử tt.toString() trả về chuỗi trạng thái phù hợp

               ResultSet rs = ps.executeQuery();
               while (rs.next()) {
                   NhanVien nhanVien = new NhanVien(
                       rs.getString("maNhanVien"),
                       rs.getString("tenNhanVien"),
                       rs.getString("soDienThoai"),
                       GioiTinh.setGioiTinh(rs.getString("gioiTinh")),
                       rs.getString("diaChi"),
                       rs.getDate("ngaySinh").toLocalDate(),
                       rs.getString("email"),
                       new LoaiNhanVien(rs.getString("maLoaiNhanVien")),
                       TrangThaiNhanVien.setTrangThaiNhanVien(rs.getString("trangThai"))
                   );
                   list.add(nhanVien);
               }

               rs.close();
               ps.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
       }
       public String getTenNV(String maNV){
           NhanVien a = list.stream().filter(x -> x.getMaNhanVien().equalsIgnoreCase(maNV))
                   .findFirst().orElse(null);
           if(a.equals(null)) return "";
           return a.getTenNhanVien();
       }
      public boolean capNhatMKtuSDT(String ma, String sdt, String newPassword) throws SQLException {
        con = ConnectDB.getInstance().getConnection();

        String sql = "UPDATE TaiKhoan " +
                     "SET matKhau = ? " +
                     "WHERE maNhanVien = ? AND EXISTS (" +
                     "    SELECT 1 " +
                     "    FROM NhanVien " +
                     "    WHERE maNhanVien = ? AND soDienThoai = ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, ma);
            stmt.setString(3, ma);
            stmt.setString(4, sdt);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có dòng được cập nhật
        }
    }

    public boolean capNhatMKtuEmail(String ma, String email, String newPassword) throws SQLException {
        con = ConnectDB.getInstance().getConnection();
        

        String sql = "UPDATE TaiKhoan " +
                     "SET matKhau = ? " +
                     "WHERE maNhanVien = ? AND EXISTS (" +
                     "    SELECT 1 " +
                     "    FROM NhanVien " +
                     "    WHERE maNhanVien = ? AND email = ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, ma);
            stmt.setString(3, ma);
            stmt.setString(4, email);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có dòng được cập nhật
        }
    }
       
}
