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
import com.quanlykhachsan.entity.DichVu;
import com.quanlykhachsan.entity.LichSuDatDichVu;
import com.quanlykhachsan.model.ConnectDB;

public class LichSuDatDichVu_DAO {

	public LichSuDatDichVu_DAO() {
		// TODO Auto-generated constructor stub
	}
	
	private ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO();
	private DichVu_DAO dv_dao = new DichVu_DAO();
	private List<LichSuDatDichVu> listLishSuDatDichVu = new ArrayList<>();
	public List<LichSuDatDichVu> getList() {
		return listLishSuDatDichVu;
	}
	
	public void docTuBang() {
		String sql = "SELECT * FROM LishSuDatDichVu";
		listLishSuDatDichVu.clear();

		try (Connection con = ConnectDB.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ChiTietHoaDon cthd = cthd_dao.timChiTietHoaDonTheoMa(rs.getString("ChiTietHoaDon"));
				DichVu dv = dv_dao.timDichVu(rs.getString("maDichVu"));
				LichSuDatDichVu lsddv = new LichSuDatDichVu(cthd, dv
						,rs.getDate("thoiGianDatDichVu").toLocalDate()
						,rs.getInt("soLuongDatHang"));
						
				listLishSuDatDichVu.add(lsddv);
			}
		} catch (SQLException ex) {
			Logger.getLogger(LichSuDatDichVu_DAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public boolean themLichSuDatDichVu(LichSuDatDichVu lsddv) {
	    String sql = "INSERT INTO LichSuDatDichVu (ChiTietHoaDon, maDichVu, thoiGianDatDichVu, soLuongDatHang) VALUES (?, ?, ?, ?)";
	    
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        // Đặt giá trị cho các tham số trong câu lệnh SQL
	        ps.setString(1, lsddv.getChiTietHoaDon().getMaChiTietHoaDon());  // Lấy mã chi tiết hóa đơn từ đối tượng ChiTietHoaDon
	        ps.setString(2, lsddv.getDichVu().getMaDichVu());  // Lấy mã dịch vụ từ đối tượng DichVu
	        ps.setDate(3, java.sql.Date.valueOf(lsddv.getThoiGianDatDichVu()));  // Chuyển LocalDate thành java.sql.Date
	        ps.setInt(4, lsddv.getSoLuong());  // Đặt số lượng đặt hàng
	        
	        // Thực thi câu lệnh và kiểm tra kết quả
	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0; // Trả về true nếu thêm thành công

	    } catch (SQLException ex) {
	        Logger.getLogger(LichSuDatDichVu_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return false; // Trả về false nếu xảy ra lỗi
	}

}
