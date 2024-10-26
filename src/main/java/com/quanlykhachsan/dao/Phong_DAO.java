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

/**
 *
 * @author nguye
 */
public class Phong_DAO {
       private List<Phong> listPhong = new ArrayList<>();
       private Connection con = null;
       
       public List<Phong> getList(){
           return listPhong;
       }
       public void docTuBang(){
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
                   listPhong.add(phong);
               }
                rs.close();
                ps.close();
                con.close();
           } catch (SQLException ex) {
               Logger.getLogger(Phong_DAO.class.getName()).log(Level.SEVERE, null, ex);
           }
          
       }
}   
