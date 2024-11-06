package com.quanlykhachsan.dao;

import java.sql.Connection;
import java.sql.Date;
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
		docTuBang();
                
	}
	
	private ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO();
	private DichVu_DAO dv_dao = new DichVu_DAO();
	private List<LichSuDatDichVu> listLishSuDatDichVu = new ArrayList<>();
	public List<LichSuDatDichVu> getList() {
		return listLishSuDatDichVu;
	}
	
	public void docTuBang() {
		String sql = "SELECT * FROM LichSuDatDichVu";
		listLishSuDatDichVu.clear();

		try (Connection con = ConnectDB.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				
				
				LichSuDatDichVu lsddv = new LichSuDatDichVu(new ChiTietHoaDon(rs.getString("maChiTietHoaDon")), new DichVu(rs.getString("maDichVu"))
						,rs.getDate("thoiGianDatDichVu").toLocalDate()
						,rs.getInt("soLuongDatHang"));
						
				listLishSuDatDichVu.add(lsddv);
			}
		} catch (SQLException ex) {
			Logger.getLogger(LichSuDatDichVu_DAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public boolean themLichSuDatDichVu(LichSuDatDichVu lsddv) {
	    String sql = "INSERT INTO LichSuDatDichVu (maChiTietHoaDon, maDichVu, thoiGianDatDichVu, soLuongDatHang) VALUES (?, ?, ?, ?)";
	    
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
	public void suaLichSuDatDichVu(LichSuDatDichVu x) {
	    String sql = "UPDATE LichSuDatDichVu SET soLuongDatHang = ? WHERE maChiTietHoaDon = ? AND maDichVu = ?";
	    
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        // Set các giá trị cho câu lệnh SQL
	        ps.setInt(1, x.getSoLuong());
	        ps.setString(2, x.getChiTietHoaDon().getMaChiTietHoaDon()); // Điều kiện WHERE
	        ps.setString(3, x.getDichVu().getMaDichVu()); // Điều kiện WHERE

	        // Thực thi câu lệnh
	        ps.executeUpdate();
	    } catch (SQLException ex) {
	        Logger.getLogger(LichSuDatDichVu_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	public boolean xoaTheoMaCTVaMaDV(String maChiTietHoaDon, String maDichVu) {
	    String sql = "DELETE FROM LichSuDatDichVu WHERE maChiTietHoaDon = ? AND maDichVu = ?";
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        // Gán giá trị cho câu lệnh SQL
	        ps.setString(1, maChiTietHoaDon);  // Gán maChiTietHoaDon vào câu truy vấn
	        ps.setString(2, maDichVu);          // Gán maDichVu vào câu truy vấn

	        // Thực thi câu lệnh xóa
	        int rowsAffected = ps.executeUpdate();
	        
	        // Nếu xóa thành công, rowsAffected sẽ lớn hơn 0
	        return rowsAffected > 0;
	    } catch (SQLException ex) {
	        Logger.getLogger(LichSuDatDichVu_DAO.class.getName()).log(Level.SEVERE, null, ex);
	        return false;  // Nếu có lỗi xảy ra, trả về false
	    }
	}
	public boolean xoaAll() {
	    String sql = "DELETE FROM LichSuDatDichVu"; // Xóa tất cả dữ liệu trong bảng LichSuDatDichVu
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        // Thực thi câu lệnh xóa tất cả dữ liệu
	        int rowsAffected = ps.executeUpdate();
	        
	        // Nếu xóa thành công, rowsAffected sẽ lớn hơn 0
	        return rowsAffected > 0;
	    } catch (SQLException ex) {
	        Logger.getLogger(LichSuDatDichVu_DAO.class.getName()).log(Level.SEVERE, null, ex);
	        return false;  // Nếu có lỗi xảy ra, trả về false
	    }
	}



        public List<LichSuDatDichVu> traVeListTheoMa(String ma){
            return listLishSuDatDichVu.stream().filter( x -> x.getChiTietHoaDon().getMaChiTietHoaDon().equalsIgnoreCase(ma)).toList();
        }
        
        public List<LichSuDatDichVu> timLichSuDatDichVuTheoMaCTHD(String maCTHD) {
            List<LichSuDatDichVu> dsLichSu = new ArrayList<>();
            
            String sql = "SELECT dv.* " +
                         "FROM Phong p " +
                         "JOIN ChiTietHoaDon ct ON ct.maPhong = p.maPhong " +
                         "JOIN HoaDon hd ON hd.maHoaDon = ct.maHoaDon " +
                         "JOIN LichSuDatDichVu dv ON ct.maChiTietHoaDon = dv.maChiTietHoaDon " +
                         "WHERE ct.maChiTietHoaDon = ?";
            
            try (Connection con = ConnectDB.getInstance().getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                
                ps.setString(1, maCTHD);  // Gán giá trị `maCTHD` vào câu truy vấn
                
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Khởi tạo đối tượng LichSuDatDichVu từ kết quả của ResultSet
                        LichSuDatDichVu lsddv = new LichSuDatDichVu(
                            new ChiTietHoaDon(rs.getString("maChiTietHoaDon")), 
                            new DichVu(rs.getString("maDichVu")),
                            rs.getDate("thoiGianDatDichVu").toLocalDate(),
                            rs.getInt("soLuongDatHang")
                        );
                        dsLichSu.add(lsddv);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(LichSuDatDichVu_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return dsLichSu;
        }


}
