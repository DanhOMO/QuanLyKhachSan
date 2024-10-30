/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.KhuVuc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.quanlykhachsan.model.ConnectDB;
import java.sql.Connection;

/**
 *
 * @author Admin
 */
public class KhuVuc_DAO {
    ArrayList<KhuVuc> dsKhuVuc ;

    public ArrayList<KhuVuc> getDsKhuVuc() {
       dsKhuVuc = new ArrayList<KhuVuc>();
       try {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement("select * from KhuVuc");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String maKhuVuc = rs.getString(1);
            String tenKhuVuc = rs.getString(2);
            String moTaKhuVuc = rs.getString(3);
            KhuVuc kv = new KhuVuc(maKhuVuc, tenKhuVuc, moTaKhuVuc);
            dsKhuVuc.add(kv);
        }
       } catch (SQLException e) {
        e.printStackTrace();
       }
         return dsKhuVuc;
    }
    public boolean themKhuVuc(KhuVuc khuVucCanThem){
        try {
            PreparedStatement ps = ConnectDB.getInstance().getConnection().prepareStatement("insert into KhuVuc values(?,?,?)");
            ps.setString(1, khuVucCanThem.getMaKhuVuc());
            ps.setString(2, khuVucCanThem.getTenKhuVuc());
            ps.setString(3, khuVucCanThem.getMoTa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean capNhatKhuvuc(KhuVuc KhuVucCapNhat, String maKhuVucCanCapNhap){
        try {
            PreparedStatement ps = ConnectDB.getInstance().getConnection()
            .prepareStatement("update KhuVuc set MaKhuVuc = ?, TenKhuVuc = ?, MoTaKhuVuc = ? where MaKhuVuc = ?");
            ps.setString(1, KhuVucCapNhat.getMaKhuVuc());
            ps.setString(2, KhuVucCapNhat.getTenKhuVuc());
            ps.setString(3, KhuVucCapNhat.getMoTa());
            ps.setString(4, maKhuVucCanCapNhap);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public KhuVuc timKhuVuc (String maKhuVucCanTim){
        try {
            PreparedStatement ps = ConnectDB.getInstance().getConnection()
            .prepareStatement("select * from KhuVuc where MaKhuVuc = ?");
            ps.setString(1, maKhuVucCanTim);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String maKhuVuc = rs.getString(1);
                String tenKhuVuc = rs.getString(2);
                String moTaKhuVuc = rs.getString(3);
                return new KhuVuc(maKhuVuc, tenKhuVuc, moTaKhuVuc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return null;
}
    public static void main(String[] args) throws SQLException {
        ConnectDB con = new ConnectDB();
        con.connect();
        KhuVuc_DAO kvDao = new KhuVuc_DAO();
        
        kvDao.getDsKhuVuc().forEach(x->System.out.println(x));
    }
}
