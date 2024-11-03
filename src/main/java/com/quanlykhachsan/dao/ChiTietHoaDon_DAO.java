package com.quanlykhachsan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.quanlykhachsan.entity.ChiTietHoaDon;
import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.model.ConnectDB;

public class ChiTietHoaDon_DAO {

	public ChiTietHoaDon_DAO() {
		// TODO Auto-generated constructor stub
	}
	
	private List<ChiTietHoaDon> listCTHoaDon = new ArrayList<>();
	public List<ChiTietHoaDon> getList() {
		return listCTHoaDon;
	}

	public void docTuBang() {
		String sql = "SELECT * FROM ChiTietHoaDon";
		listCTHoaDon.clear();

		try (Connection con = ConnectDB.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon(rs.getString("maChiTietHoaDon")
						, rs.getDate("ngapLapHoaDon").toLocalDate()
						, rs.getDouble("giaDatHang")
                        , new HoaDon(rs.getString("maHoaDon")));
				listCTHoaDon.add(cthd);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
	    String sql = "INSERT INTO ChiTietHoaDon (maChiTietHoaDon, ngapLapHoaDon ,giaDatHang, maHoaDon) VALUES (?, ?, ?, ?)";
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, cthd.getMaChiTietHoaDon());
	        ps.setDate(2, java.sql.Date.valueOf(cthd.getNgayLapHoaDon()));
	        ps.setDouble(3, cthd.getGiaDatPhong());
	        ps.setString(4, cthd.getMaHoaDon().getMaHoaDon());
	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0; // Trả về true nếu thêm thành công
	    } catch (SQLException ex) {
	        Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
	        return false;
	    }
	}
	
	public boolean suaChiTietHoaDon(ChiTietHoaDon cthd) {
	    String sql = "UPDATE ChiTietHoaDon SET ngapLapHoaDon = ?, giaDatHang = ? WHERE maHoaDon = ?";
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setDate(1, java.sql.Date.valueOf(cthd.getNgayLapHoaDon()));
	        ps.setDouble(2, cthd.getGiaDatPhong());
	        ps.setString(3, cthd.getMaChiTietHoaDon());

	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0; // Trả về true nếu cập nhật thành công
	    } catch (SQLException ex) {
	        Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
	        return false;
	    }
	}
	
	public ChiTietHoaDon timChiTietHoaDonTheoMa(String maChiTietHoaDon) {
	    String sql = "SELECT * FROM ChiTietHoaDon WHERE maHoaDon = ?";
	    ChiTietHoaDon cthd = null;
	    
	    try (Connection con = ConnectDB.getInstance().getConnection();
PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, maChiTietHoaDon);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            cthd = new ChiTietHoaDon(
	                rs.getString("maHoaDon"),
	                rs.getDate("ngapLapHoaDon").toLocalDate(),
	                rs.getDouble("giaDatHang"),
                        new HoaDon(rs.getString("maHoaDon"))
	            );
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    return cthd;
	}

}