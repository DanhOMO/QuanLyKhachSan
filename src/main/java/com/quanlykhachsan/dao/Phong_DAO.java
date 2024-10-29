/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.Phong;
import com.quanlykhachsan.enum_Class.TrangThaiPhong;

import com.quanlykhachsan.model.ConnectDB;
import entity.KhuVuc;
import entity.LoaiPhong;


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
public class Phong_DAO {
        private List<Phong> list = new ArrayList<>();
        public Phong_DAO(){
            docTuBang();
        }
    private Connection con = null;
          public List<Phong> getList(){
           return list;
       }
       public boolean themMoi(Phong a){
           if(list.contains(a)) throw new IllegalArgumentException("Phong Da TOn TAI !!!");
           list.add(a); return true;
       }
       public boolean capNhatPhong(Phong a) throws SQLException{
           con = ConnectDB.getInstance().getConnection();
           String sql = "update Phong set tenPhong = ? ,trangThaiPhong = ?, maLoaiPhong = ?, maKhuVuc = ? "
                   + "where maPhong = ?";
           PreparedStatement state = con.prepareStatement(sql);
           state.setString(1, a.getTenPhong());
           state.setString(2,a.getTrangThai().getTrangThaiPhong());
           state.setString(3, a.getLoaiPhong().getMaLoaiPhong());
           state.setString(4, a.getKhuVuc().getMaKhuVuc());
           state.setString(5, a.getMaPhong());
           state.executeUpdate();
           state.close();
           return true;
       }
       public DefaultTableModel docDuLieuVaoBan() {
    // Thêm tên cột vào DefaultTableModel
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Phòng", "Tên Phòng", "Loại Phòng", "Khu Vực", "Trạng Thái Phòng"}, 0);
    
    // Thêm dữ liệu vào DefaultTableModel
          list.stream().forEach(x -> {
        dtm.addRow(new Object[]{
            x.getMaPhong(), x.getTenPhong(), x.getLoaiPhong().getMaLoaiPhong(), x.getKhuVuc().getMaKhuVuc(), x.getTrangThai().getTrangThaiPhong()
        });
    });
    
    return dtm;
}

        public Phong timTheoMa(String ma){
            return list.stream().filter(x -> x.getMaPhong().equalsIgnoreCase(ma)).findFirst().orElse(null);
        }
       public void docTuBang(){
           list.clear();
           con =  ConnectDB.getInstance().getConnection();
           String sql = "select * from Phong";
           
           try {
               PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Phong phong = new Phong(rs.getString("maPhong"),
                           rs.getString("tenPhong"),
                           TrangThaiPhong.setTrangThaiPhong(rs.getString("trangThaiPhong")),
                           new LoaiPhong(rs.getString("maLoaiPhong")),
                           new KhuVuc(rs.getString("maKhuVuc")));
                   list.add(phong);
               }
             rs.close();
             ps.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
        
       }
}   
