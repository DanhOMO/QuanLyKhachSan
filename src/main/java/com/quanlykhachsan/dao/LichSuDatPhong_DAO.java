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
import com.quanlykhachsan.entity.LichSuDatPhong;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.entity.Phong;
import com.quanlykhachsan.model.ConnectDB;

public class LichSuDatPhong_DAO {

	public LichSuDatPhong_DAO() {
		// TODO Auto-generated constructor stub
	}
	private ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO();
	private Phong_DAO p_dao = new Phong_DAO();
	private List<LichSuDatPhong> listLishSuDatPhong = new ArrayList<>();
	public List<LichSuDatPhong> getList() {
		return listLishSuDatPhong;
	}
	
	public void docTuBang() {
		String sql = "SELECT * FROM LishSuDatPhong";
		listLishSuDatPhong.clear();

		try (Connection con = ConnectDB.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ChiTietHoaDon cthd = cthd_dao.timChiTietHoaDonTheoMa(rs.getString("ChiTietHoaDon"));
				p_dao.docTuBang();
				Phong p = p_dao.timTheoMa(rs.getString("maPhong"));
				LichSuDatPhong lsdp = new LichSuDatPhong(cthd, p,rs.getInt("soLuong"),
						rs.getDate("thoiGianDatPhong").toLocalDate());
				listLishSuDatPhong.add(lsdp);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public boolean themLichSuDatPhong(LichSuDatPhong lsdp) {
	    String sql = "INSERT INTO LichSuDatPhong (ChiTietHoaDon, maPhong, soLuong, thoiGianDatPhong) VALUES (?, ?, ?, ?)";
	    
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        // Set giá trị cho các tham số trong câu lệnh SQL
	        ps.setString(1, lsdp.getChiTietHoaDon().getMaChiTietHoaDon());  // Giả sử getMaChiTietHoaDon là phương thức lấy mã chi tiết hóa đơn
	        ps.setString(2, lsdp.getPhong().getMaPhong());  // Lấy mã phòng từ đối tượng Phong trong lsdp
	        ps.setInt(3, lsdp.getSoLuong());
	        ps.setDate(4, java.sql.Date.valueOf(lsdp.getThoiGianDatPhong()));  // Chuyển LocalDate thành java.sql.Date
	        
	        // Thực thi câu lệnh và kiểm tra kết quả
	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0; // Trả về true nếu thêm thành công

	    } catch (SQLException ex) {
	        Logger.getLogger(LichSuDatPhong_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return false; // Trả về false nếu xảy ra lỗi
	}


	
}