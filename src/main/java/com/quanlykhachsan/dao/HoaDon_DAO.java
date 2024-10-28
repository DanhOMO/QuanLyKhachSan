/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.model.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class HoaDon_DAO {
     public static void main(String[] args) {
        try {
            ConnectDB con =  new ConnectDB();
            con.connect();
            HoaDon_DAO a = new HoaDon_DAO();
            a.getList().forEach(x -> System.out.println(x));
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private   List<HoaDon> ca = new ArrayList<>();
    public HoaDon_DAO(){
//        docTuBang();
    }
    public List<HoaDon> getList(){
        return ca;
    }
//    public void docTuBang() {
//        
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//            String sql = "SELECT * FROM HoaDon";
//            PreparedStatement ps = con.prepareCall(sql);
//            ResultSet rs = ps.executeQuery(); 
//
//            while (rs.next()) {
//                HoaDon calamviec = new HoaDon(
//                    rs.getString("maCaLamVien"),  // Chỉnh sửa tên cột từ "maCaLamVien" thành "maHoaDon"
//                    com.quanlykhachsan.enum_Class.HoaDon.getHoaDon(rs.getString("tenHoaDon")),
//                    rs.getObject("ngayLamViec", LocalDate.class),
//                    rs.getDouble("tongTienTrongCa"),
//                    new NhanVien(rs.getString("maNhanVien"))  // Lấy giá trị thực tế từ ResultSet
//                );
//                ca.add(calamviec);
//            }
//
//            ps.close();
//            rs.close();
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//    }
}
