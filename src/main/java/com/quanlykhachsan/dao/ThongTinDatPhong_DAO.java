package com.quanlykhachsan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.DocFlavor.STRING;

import com.quanlykhachsan.entity.ChiTietHoaDon;
import com.quanlykhachsan.entity.DichVu;
import com.quanlykhachsan.entity.LichSuDatDichVu;
import com.quanlykhachsan.entity.ThongTinDatPhong;
import com.quanlykhachsan.model.ConnectDB;

public class ThongTinDatPhong_DAO {

	public ThongTinDatPhong_DAO() {
		// TODO Auto-generated constructor stub
	}
	private List<ThongTinDatPhong> list = new ArrayList<>();
	public List<ThongTinDatPhong> getList() {
		return list;
	}
	public void docTuBang() {
	    String sql = "SELECT * FROM ThongTinDatPhong";
	    list.clear();

	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {   
	            ThongTinDatPhong ttdp = new ThongTinDatPhong(
	                new ChiTietHoaDon(rs.getString("maChiTietHoaDon")), 
	                rs.getString("hoVaTen"),                            
	                rs.getBoolean("laNguoiLon")                         
	            );
	            list.add(ttdp); 
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(ThongTinDatPhong_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	public boolean them(ThongTinDatPhong tt) {
	    String sql = "INSERT INTO ThongTinDatPhong (maChiTietHoaDon, hoVaTen, laNguoiLon) VALUES (?, ?, ?)";
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        // Thiết lập các tham số cho PreparedStatement
	        ps.setString(1, tt.getMaChiTietHoaHon().getMaChiTietHoaDon());  // mã chi tiết hóa đơn
	        ps.setString(2, tt.getHoVaTen());                             // họ và tên
	        ps.setBoolean(3, tt.isLaNguoiLon());                          // là người lớn hay không

	        // Thực thi câu lệnh và kiểm tra kết quả
	        int rowsInserted = ps.executeUpdate();
	        return rowsInserted > 0; // Trả về true nếu thêm thành công
	    } catch (SQLException ex) {
	        Logger.getLogger(ThongTinDatPhong_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return false; // Trả về false nếu thêm thất bại
	}

        public List<ThongTinDatPhong> timTTTheoListMaCTHD(List<String> maCTHD) {
    List<ThongTinDatPhong> dsThongTinDatPhong = new ArrayList<>();
    
    // Xây dựng chuỗi tham số cho câu lệnh SQL (danh sách các maChiTietHoaDon)
    StringBuilder sql = new StringBuilder("SELECT tt.* FROM ChiTietHoaDon ct " +
                                          "JOIN ThongTinDatPhong tt ON ct.maChiTietHoaDon = tt.maChiTietHoaDon " +
                                          "WHERE ct.maChiTietHoaDon IN (");

    // Thêm các dấu hỏi chấm vào câu truy vấn theo số lượng phần tử trong danh sách
    for (int i = 0; i < maCTHD.size(); i++) {
        sql.append("?");
        if (i < maCTHD.size() - 1) {
            sql.append(", ");
        }
    }
    sql.append(")");

    // Thực thi câu truy vấn
    try (Connection con = ConnectDB.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(sql.toString())) {
        
        // Gán giá trị cho các dấu hỏi chấm trong câu lệnh SQL
        for (int i = 0; i < maCTHD.size(); i++) {
            ps.setString(i + 1, maCTHD.get(i)); // Gán giá trị maCTHD vào các tham số trong PreparedStatement
        }
        
        // Thực thi truy vấn
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Khởi tạo đối tượng ThongTinDatPhong từ dữ liệu trong ResultSet
                ThongTinDatPhong ttdp = new ThongTinDatPhong(
                    new ChiTietHoaDon(rs.getString("maChiTietHoaDon")), // Mã chi tiết hóa đơn
                    rs.getString("hoVaTen"),                             // Họ và tên
                    rs.getBoolean("laNguoiLon")                          // Có phải người lớn hay không
                );
                dsThongTinDatPhong.add(ttdp);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(ThongTinDatPhong_DAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return dsThongTinDatPhong;
}

	public List<ThongTinDatPhong> timTTTheoMaCTHD(String maCTHD) {
	    List<ThongTinDatPhong> dsThongTinDatPhong = new ArrayList<>();
	    
	    String sql = "SELECT tt.* " +
	                 "FROM ChiTietHoaDon ct " +
	                 "JOIN ThongTinDatPhong tt ON ct.maChiTietHoaDon = tt.maChiTietHoaDon " +
	                 "WHERE ct.maChiTietHoaDon = ?";
	    
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        ps.setString(1, maCTHD);  // Gán giá trị `maCTHD` vào câu truy vấn
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                // Khởi tạo đối tượng ThongTinDatPhong từ dữ liệu trong ResultSet
	                ThongTinDatPhong ttdp = new ThongTinDatPhong(
	                    new ChiTietHoaDon(rs.getString("maChiTietHoaDon")), // Mã chi tiết hóa đơn
	                    rs.getString("hoVaTen"),                             // Họ và tên
	                    rs.getBoolean("laNguoiLon")                          // Có phải người lớn hay không
	                );
	                dsThongTinDatPhong.add(ttdp);
	            }
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(ThongTinDatPhong_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
	    return dsThongTinDatPhong;
	}
	public boolean xoaALL(String maCTHD) {
	    String sql = "DELETE FROM ThongTinDatPhong WHERE maChiTietHoaDon = ?";  // Câu lệnh SQL để xóa bản ghi theo mã chi tiết hóa đơn

	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        // Gán giá trị của maCTHD vào câu lệnh SQL
	        ps.setString(1, maCTHD);

	        int rowsAffected = ps.executeUpdate();  // Thực thi câu lệnh xóa

	        // Kiểm tra nếu có bản ghi bị xóa
	        return rowsAffected > 0;  // Trả về true nếu xóa thành công, false nếu không xóa được
	    } catch (SQLException ex) {
	        Logger.getLogger(ThongTinDatPhong_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    return false;  // Trả về false nếu có lỗi hoặc không xóa được bản ghi
	}



}
