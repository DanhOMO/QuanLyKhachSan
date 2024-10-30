/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.ChiTietHoaDon;
import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.model.ConnectDB;
import entity.Voucher;
import java.lang.reflect.Array;
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
        docTuBang();
    }
    public List<HoaDon> getList(){
        return ca;
    }
    public void docTuBang() {
        
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM HoaDon";
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery(); 

            while (rs.next()) {
                HoaDon calamviec = new HoaDon(
                   rs.getString("maHoaDon"),
                   rs.getDate("ngayLapHoaDon").toLocalDate(),
                   new NhanVien(rs.getString("maNhanVien")),
                   new Voucher(rs.getString("maVoucher")),
                   new KhachHang(rs.getString("maKhachHang")),
                   new ChiTietHoaDon(rs.getString("maChiTietHoaDon")),
                   rs.getDouble("VAT"),
                   rs.getBoolean("trangThai"),
                   rs.getDate("checkIN").toLocalDate(),
                   rs.getDate("checkOUT").toLocalDate(),
                   rs.getDouble("datCoc"),
                   rs.getDouble("tienPhat"),
                   rs.getDouble("tongTien")
                );
                ca.add(calamviec);
            }

            ps.close();
            rs.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private void getDouble(String tienPhat) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
