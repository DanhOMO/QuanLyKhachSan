/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.ThietBi;
import com.quanlykhachsan.enum_Class.TrangThaiThietBi;
import com.quanlykhachsan.model.ConnectDB;
import entity.LoaiPhong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
       public void docTuBang(){
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
                con.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
          
       }
}
