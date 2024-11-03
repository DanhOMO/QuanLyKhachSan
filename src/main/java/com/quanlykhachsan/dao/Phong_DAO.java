/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.KhuVuc;
import com.quanlykhachsan.entity.LoaiPhong;
import com.quanlykhachsan.entity.Phong;
import com.quanlykhachsan.enum_Class.TrangThaiPhong;

import com.quanlykhachsan.model.ConnectDB;
import com.quanlykhachsan.entity.KhuVuc;
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
public class Phong_DAO {
        private List<Phong> list = new ArrayList<>();
    private ArrayList<Phong> dsPhong;
        public Phong_DAO(){
            docTuBang();
        }
    private Connection con = null;
          public List<Phong> getList(){
           return list;
       }
            public boolean themPhong(Phong voucher){
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Phong VALUES(?,?,? , ? , ?)");
            ps.setString(1, voucher.getMaPhong());
            ps.setString(2, voucher.getTenPhong());
            ps.setString(3, voucher.getTrangThai().getTrangThaiPhong());
            ps.setString(4, voucher.getLoaiPhong().getMaLoaiPhong());
            ps.setString(5, voucher.getKhuVuc().getMaKhuVuc());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
       public boolean capNhatPhong(Phong a) throws SQLException{
           
           try {
                    con = ConnectDB.getInstance().getConnection();
                System.out.println(a);
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
           } catch (Exception e) {
               e.printStackTrace();
           }
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
       public void timPhongTheoLoaiPhong(LoaiPhong pl) {
    	List<Phong> temp;
    	temp = list.stream()
            .filter(x -> x.getLoaiPhong().equals(pl))
            .toList();
    	list.clear();
    	list.addAll(temp);
    }
       public void timPhongTheoKhuVuc(KhuVuc kv) {
       	List<Phong> temp;
       	temp = list.stream()
               .filter(x -> x.getKhuVuc().equals(kv))
               .toList();
       	list.clear();
       	list.addAll(temp);
       }
       
        public ArrayList<Phong> loadData(){
        dsPhong = new ArrayList<Phong>();
        try {
            con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("select * from Phong");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Phong p = new Phong();
				p.setMaPhong(rs.getString("maPhong"));
				p.setTenPhong(rs.getString("tenPhong"));
				p.setTrangThai(TrangThaiPhong.TRONG);
				p.setLoaiPhong(new LoaiPhong(rs.getString("maLoaiPhong")));
                                p.setKhuVuc(new KhuVuc(rs.getString("maKhuVuc")));
				dsPhong.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsPhong;
    }
}   
