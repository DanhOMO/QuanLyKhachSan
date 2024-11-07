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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
          
            public void setList(List<Phong> list) {
			this.list = list;
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
       public void timPhongTheoLoaiPhong(String ma) {
    	List<Phong> temp = list.stream()
            .filter(x -> x.getLoaiPhong().getMaLoaiPhong().equals(ma))
            .collect(Collectors.toList());
    	list.clear();
    	list.addAll(temp);
       }
       public List<Phong> TimPhongTheoThoiGianCheckIn(Date checkInDate) {
    	    List<Phong> dsPhong = new ArrayList<>();
    	    String sql = "SELECT p.* FROM Phong p "
    	               + "JOIN ChiTietHoaDon ct ON ct.maPhong = p.maPhong "
    	               + "JOIN HoaDon hd ON hd.maHoaDon = ct.maHoaDon "
    	               + "WHERE hd.checkOut >= ? and hd.trangThai = 0";
    	    
    	    try {
    	        // Kết nối cơ sở dữ liệu
    	        con = ConnectDB.getInstance().getConnection();
    	        PreparedStatement ps = con.prepareStatement(sql);

    	        // Thiết lập giá trị cho tham số (ngày check-in)
    	        ps.setDate(1, new java.sql.Date(checkInDate.getTime()));

    	        // Thực thi truy vấn
    	        ResultSet rs = ps.executeQuery();

    	        // Duyệt kết quả và thêm vào danh sách
    	        while (rs.next()) {
    	            Phong phong = new Phong(
    	                rs.getString("maPhong"),
    	                rs.getString("tenPhong"),
    	                TrangThaiPhong.setTrangThaiPhong(rs.getString("trangThaiPhong")),
    	                new LoaiPhong(rs.getString("maLoaiPhong")),
    	                new KhuVuc(rs.getString("maKhuVuc"))
    	            );
    	            dsPhong.add(phong);
    	        }

    	        // Đóng kết nối và result set
    	        rs.close();
    	        ps.close();
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	    }
    	    return dsPhong;
    	}

       
       
       public List<Phong> timPhongTheoSoLuongNguoi(int soLuong) {
    	    // Xóa danh sách cũ trước khi thêm kết quả mới
    	    List<Phong> dsPhong = new ArrayList<Phong>();

    	    // Câu lệnh SQL với điều kiện lọc số lượng người
    	    String sql = "SELECT p.* FROM Phong p "
    	               + "JOIN LoaiPhong lp ON lp.maLoaiPhong = p.maLoaiPhong "
    	               + "WHERE lp.soLuongNguoi >= ?";

    	    try {
    	        // Kết nối cơ sở dữ liệu
    	        con = ConnectDB.getInstance().getConnection();
    	        PreparedStatement ps = con.prepareStatement(sql);
    	        
    	        // Thiết lập giá trị tham số (số lượng người)
    	        ps.setInt(1, soLuong);

    	        // Thực thi truy vấn
    	        ResultSet rs = ps.executeQuery();

    	        // Duyệt kết quả và thêm vào danh sách
    	        while (rs.next()) {
    	            Phong phong = new Phong(
    	                rs.getString("maPhong"),
    	                rs.getString("tenPhong"),
    	                TrangThaiPhong.setTrangThaiPhong(rs.getString("trangThaiPhong")),
    	                new LoaiPhong(rs.getString("maLoaiPhong")),
    	                new KhuVuc(rs.getString("maKhuVuc"))
    	            );
    	            dsPhong.add(phong);
    	        }
    	       
    	        // Đóng kết nối và result set
    	        rs.close();
    	        ps.close();
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	    }
			return dsPhong;
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
				p.setTrangThai(TrangThaiPhong.valueOf(rs.getString("trangThaiPhong").toUpperCase()));
				p.setLoaiPhong(new LoaiPhong(rs.getString("maLoaiPhong")));
                                p.setKhuVuc(new KhuVuc(rs.getString("maKhuVuc")));
				dsPhong.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsPhong;
    }
    public Phong timPhong(String maHoaDon){
        try {
            PreparedStatement ps = ConnectDB.getInstance().getConnection().prepareStatement("select  maPhong from ChiTietHoaDon\r\n" + //
                                "where maHoaDon=?");
            ps.setString(1, maHoaDon);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Phong p = new Phong();
                Phong_DAO phongDao = new Phong_DAO();
                p = phongDao.timPhongTheoMa(rs.getString("maPhong"));
                return p;
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
    public Phong timPhongTheoMa(String maPhong){
        try {
            PreparedStatement ps = ConnectDB.getInstance().getConnection().prepareStatement("select * from Phong where maPhong = ?");
            ps.setString(1, maPhong);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Phong p = new Phong();
                p.setMaPhong(rs.getString("maPhong"));
                p.setTenPhong(rs.getString("tenPhong"));
                p.setTrangThai(TrangThaiPhong.TRONG);
                LoaiPhong_DAO lp_dao = new LoaiPhong_DAO();
                LoaiPhong lp = lp_dao.timTheoMa02(rs.getString("maLoaiPhong"));
                p.setLoaiPhong(lp);
                p.setKhuVuc(new KhuVuc(rs.getString("maKhuVuc")));
                return p;
            }
        } catch (Exception e) {
           e.printStackTrace();
        
        }
        return null;
    }
}
