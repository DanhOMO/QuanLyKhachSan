/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.model.ConnectDB;
import entity.LoaiPhong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
       public void docTuBang(){
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
                con.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
          
       }
}
