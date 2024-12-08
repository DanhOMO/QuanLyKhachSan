/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;


import com.quanlykhachsan.entity.Phong;
import com.quanlykhachsan.entity.ThietBi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.quanlykhachsan.entity.Phong_ThietBi;
import com.quanlykhachsan.model.ConnectDB;

/**
 *
 * @author nguye
 */



public class Phong_ThietBi_DAO {

    // Lấy tất cả dữ liệu từ bảng Phong_ThietBi
    public List<Phong_ThietBi> getAllPhongThietBi() {
        String sql = "SELECT Phong_ThietBi.maPhong, Phong_ThietBi.maThietBi, ThietBi.tenThietBi " +
                     "FROM Phong_ThietBi " +
                     "INNER JOIN ThietBi ON Phong_ThietBi.maThietBi = ThietBi.maThietBi";
        List<Phong_ThietBi> resultList = new ArrayList<>();
        Connection con = null;

        try {
            con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String maThietBi = rs.getString("maThietBi");
                String tenThietBi = rs.getString("tenThietBi");

                Phong phong = new Phong(maPhong); // Cần constructor thích hợp trong Phong
                ThietBi thietBi = new ThietBi(maThietBi, tenThietBi); // Cần constructor thích hợp trong ThietBi

                resultList.add(new Phong_ThietBi(phong, thietBi));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Phong_ThietBi_DAO.class.getName()).log(Level.SEVERE, "Error reading Phong_ThietBi", ex);
        }

        return resultList;
    }

    // Sửa thiết bị trong phòng
    public boolean updateThietBiInPhong(String maPhong, String oldMaThietBi, String newMaThietBi) {
        String sql = "UPDATE Phong_ThietBi SET maThietBi = ? WHERE maPhong = ? AND maThietBi = ?";
        Connection con = null;

        try {
            con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newMaThietBi);
            ps.setString(2, maPhong);
            ps.setString(3, oldMaThietBi);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Phong_ThietBi_DAO.class.getName()).log(Level.SEVERE, "Error updating Phong_ThietBi", ex);
        }

        return false;
    }

    // Thêm thiết bị vào phòng
    public boolean addThietBiToPhong(String maPhong, String maThietBi) {
        String sql = "INSERT INTO Phong_ThietBi (maPhong, maThietBi) VALUES (?, ?)";
        Connection con = null;

        try {
            con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maPhong);
            ps.setString(2, maThietBi);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Phong_ThietBi_DAO.class.getName()).log(Level.SEVERE, "Error adding ThietBi to Phong", ex);
        }

        return false;
    }

    // Tìm danh sách tên thiết bị trong phòng
    public List<String> getThietBiByPhong(String maPhong) {
        String sql = """
            SELECT ThietBi.tenThietBi 
            FROM Phong_ThietBi 
            INNER JOIN ThietBi ON Phong_ThietBi.maThietBi = ThietBi.maThietBi 
            WHERE Phong_ThietBi.maPhong = ?
        """;
        List<String> thietBiList = new ArrayList<>();
        Connection con = null;

        try {
            con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maPhong);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                thietBiList.add(rs.getString("tenThietBi"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Phong_ThietBi_DAO.class.getName()).log(Level.SEVERE, "Error retrieving devices for room", ex);
        }

        return thietBiList;
    }
}

