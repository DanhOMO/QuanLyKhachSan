/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.ThietBi;

import com.quanlykhachsan.enum_Class.TrangThaiThietBi;
import com.quanlykhachsan.enum_Class.TrangThaiThietBi;
import com.quanlykhachsan.model.ConnectDB;
import com.quanlykhachsan.entity.KhuVuc;
import com.quanlykhachsan.entity.ThietBi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class ThietBi_DAO {
        private List<ThietBi> list = new ArrayList<>();
    private Connection con = null;
          public List<ThietBi> getList(){
           return list;
       }
       public boolean themThietBi(ThietBi voucher){
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO ThietBi VALUES(?,?,?)");
            ps.setString(1, voucher.getMaThietBi());
            ps.setString(2, voucher.getTenThietBi());
            ps.setString(3, voucher.getTrangThai().getTrangThaiThietBi());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
       public boolean capNhatThietBi(ThietBi a) throws SQLException{
           con = ConnectDB.getInstance().getConnection();
           String sql = "update ThietBi set tenThietBi = ? ,trangThai = ?"
                   + "where maThietBi = ?";
           PreparedStatement state = con.prepareStatement(sql);
           state.setString(1, a.getTenThietBi());
           state.setString(2,a.getTrangThai().getTrangThaiThietBi());
           state.setString(3, a.getMaThietBi());
           state.executeUpdate();
           state.close();
           return true;
       }
       public DefaultTableModel docDuLieuVaoBan() {
    // Thêm tên cột vào DefaultTableModel
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Thiết Bị", "Tên Thiết Bị", "Trạng Thái"}, 0);
    
    // Thêm dữ liệu vào DefaultTableModel
          list.stream().forEach(x -> {
        dtm.addRow(new Object[]{
            x.getMaThietBi(), x.getTenThietBi(), x.getTrangThai().getTrangThaiThietBi()
        });
    });
    
    return dtm;
}

        public ThietBi timTheoMa(String ma){
            return list.stream().filter(x -> x.getMaThietBi().equalsIgnoreCase(ma)).findFirst().orElse(null);
        }
       public void docTuBang(){
           list.clear();
           con =  ConnectDB.getInstance().getConnection();
           String sql = "select * from ThietBi";
           
           try {
               PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ThietBi phong = new ThietBi(rs.getString("maThietBi"),
                           rs.getString("tenThietBi"),
                           TrangThaiThietBi.setTrangThaiThietBi(rs.getString("trangThai")));
                   list.add(phong);
               }
             rs.close();
             ps.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
        
       }
}
