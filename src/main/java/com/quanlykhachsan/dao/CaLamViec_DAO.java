/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.CaLamViec;
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
