/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.Voucher;
import com.quanlykhachsan.model.ConnectDB;
<<<<<<< HEAD
import com.quanlykhachsan.entity.Voucher;
=======
>>>>>>> 419024f766aed4c99926820b34f384cbdfd8a518
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Voucher_DAO {
    ArrayList<Voucher> dsKhuyenMai;

    public ArrayList<Voucher> layDanhSachKhuyenMai(){
        dsKhuyenMai= new ArrayList<Voucher>();
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Voucher");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String maVoucher = rs.getString(1);
                String tenVoucher = rs.getString(2);
                double giamGia = rs.getDouble(3);
                Date ngayBatDau = rs.getDate(4);
                Date ngayKetThuc = rs.getDate(5);
                LocalDate ngayBatDauLocal = ngayBatDau.toLocalDate();
                LocalDate ngayKetThucLocal = ngayKetThuc.toLocalDate();
                Voucher test1= new Voucher(maVoucher, tenVoucher, giamGia, ngayBatDauLocal, ngayKetThucLocal); 
                dsKhuyenMai.add((test1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKhuyenMai;
    }
    public boolean themVoucher(Voucher voucher){
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Voucher VALUES(?,?,?,?,?)");
            ps.setString(1, voucher.getMaVoucher());
            ps.setString(2, voucher.getTenVoucher());
            ps.setDouble(3, voucher.getGiamGia());
            ps.setDate(4, Date.valueOf(voucher.getNgayBatDau()));
            ps.setDate(5, Date.valueOf(voucher.getNgayKetThuc()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Voucher timKhuyenMai(String maVoucher){
        try {
           PreparedStatement ps = ConnectDB.getInstance().getConnection().prepareStatement("Select * from Voucher where maVoucher = ?");
           ps.setString(1,maVoucher);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               String mavoucher = rs.getString(1);
               String tenVoucher = rs.getString(2);
               double giamGia = rs.getDouble(3);
               Date ngayBatDau = rs.getDate(4);
               Date ngayKetThu = rs.getDate(5);
               
               LocalDate ngayBatDauLC = ngayBatDau.toLocalDate();
               LocalDate ngayKetThucLC=ngayKetThu.toLocalDate();
               Voucher temp = new Voucher(mavoucher, tenVoucher, giamGia, ngayBatDauLC, ngayKetThucLC);
               return temp;
           }
           
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean capNhatVoucher(String maVoucher,Voucher voucher){
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Voucher SET tenVoucher = ?, giaGiam = ?, ngayBatDau = ?, ngayKetThuc = ? WHERE maVoucher = ?");
            ps.setString(1, voucher.getTenVoucher());
            ps.setDouble(2, voucher.getGiamGia());
            ps.setDate(3, Date.valueOf(voucher.getNgayBatDau()));
            ps.setDate(4, Date.valueOf(voucher.getNgayKetThuc()));
            ps.setString(5, maVoucher);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public static void main(String[] args) {
        ConnectDB con = new ConnectDB();
        Voucher_DAO a = new Voucher_DAO();
        a.layDanhSachKhuyenMai().forEach(x->System.out.println(x));
    }
}
