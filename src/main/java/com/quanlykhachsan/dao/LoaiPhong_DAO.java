/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.LoaiPhong;
import com.quanlykhachsan.model.ConnectDB;
import com.quanlykhachsan.entity.LoaiPhong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class LoaiPhong_DAO {
    private List<LoaiPhong> list = new ArrayList<>();
    private Connection con = null;
          public List<LoaiPhong> getList(){
           return list;
       }
            public boolean themLoaiPhong(LoaiPhong voucher){
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO LoaiPhong VALUES(?,?,?, ? , ?)");
            ps.setString(1, voucher.getMaLoaiPhong());
            ps.setString(2, voucher.getTenLoaiPhong());
            ps.setString(3, voucher.getMoTa());
            ps.setInt(4, voucher.getSoLuongNguoi());
            ps.setDouble(5, voucher.getGiaThuePhong());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
       public boolean capNhatLoaiPhong(LoaiPhong a) throws SQLException{
           con = ConnectDB.getInstance().getConnection();
           String sql = "update LoaiPhong set tenLoaiPhong = ? , soLuongNguoi = ?, moTa = ? , giaThuePhong = ? "
                   + "where maLoaiPhong = ?";
           PreparedStatement state = con.prepareStatement(sql);
           state.setString(1, a.getTenLoaiPhong());
           state.setInt(2, a.getSoLuongNguoi());
           state.setString(3, a.getMoTa());
           state.setDouble(4, a.getGiaThuePhong());
           state.setString(5, a.getMaLoaiPhong());
           state.executeUpdate();
           state.close();
           return true;
       }
      public String[] getListName() {
        return list.stream()
               .map(LoaiPhong::getTenLoaiPhong)  // Lấy tên loại phòng
               .toArray(String[]::new);          // Chuyển thành mảng String[]
}
      public LoaiPhong getLoaiPhongTuTen(String a){
          return list.stream().filter(x -> x.getTenLoaiPhong().equalsIgnoreCase(a)).findFirst().orElse(null);
      }
       public DefaultTableModel docDuLieuVaoBan() {
    // Thêm tên cột vào DefaultTableModel
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Loại Phòng", "Tên Loại Phòng", "Số Lượng Người"}, 0);
    
    // Thêm dữ liệu vào DefaultTableModel
          list.stream().forEach(x -> {
        dtm.addRow(new Object[]{
            x.getMaLoaiPhong(), x.getTenLoaiPhong(), x.getSoLuongNguoi()
        });
    });
    
    return dtm;
}

     public LoaiPhong timLoaiPhong(String maLoaiPhong) {
    LoaiPhong loaiPhong = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // Kết nối đến cơ sở dữ liệu
        con = ConnectDB.getInstance().getConnection();

        // Câu lệnh SQL để lấy thông tin LoaiPhong dựa trên mã loại phòng
        String sql = "SELECT * FROM LoaiPhong WHERE maLoaiPhong = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, maLoaiPhong);

        // Thực thi truy vấn
        rs = ps.executeQuery();

        // Nếu tìm thấy, khởi tạo đối tượng LoaiPhong với dữ liệu từ cơ sở dữ liệu
        if (rs.next()) {
            loaiPhong = new LoaiPhong();
            loaiPhong.setMaLoaiPhong(rs.getString("maLoaiPhong"));
            loaiPhong.setTenLoaiPhong(rs.getString("tenLoaiPhong"));
            loaiPhong.setMoTa(rs.getString("moTa"));
            loaiPhong.setGiaThuePhong(rs.getDouble("giaThuePhong")); // Giả sử cột giá thuê là 'giaThuePhong'
            loaiPhong.setSoLuongNguoi(rs.getInt("soLuongNguoi"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng tài nguyên
        if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
    
    return loaiPhong;
}


    
       
       
        public LoaiPhong timTheoMa(String ma){
            return list.stream().filter(x -> x.getMaLoaiPhong().equalsIgnoreCase(ma)).findFirst().orElse(null);
        }
       public void docTuBang(){
           list.clear();
           con =  ConnectDB.getInstance().getConnection();
           String sql = "select * from LoaiPhong";
           
           try {
               PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    LoaiPhong phong = new LoaiPhong(rs.getString("maLoaiPhong"),
                           rs.getString("tenLoaiPhong"),
                           rs.getString("moTa"),
                           rs.getInt("soLuongNguoi"),
                           rs.getDouble("giaThuePhong"));
                   list.add(phong);
               }
             rs.close();
             ps.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
       }
       public LoaiPhong timTheoMa02(String ma){
            try {
                con = ConnectDB.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement("select * from LoaiPhong where maLoaiPhong = ?");
                ps.setString(1, ma);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    LoaiPhong lp = new LoaiPhong();
                    lp.setMaLoaiPhong(rs.getString("maLoaiPhong"));
                    lp.setTenLoaiPhong(rs.getString("tenLoaiPhong"));
                    lp.setMoTa(rs.getString("moTa"));
                    lp.setSoLuongNguoi(rs.getInt("soLuongNguoi"));
                    lp.setGiaThuePhong(rs.getDouble("giaThuePhong"));
                    return lp;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
       }
}
