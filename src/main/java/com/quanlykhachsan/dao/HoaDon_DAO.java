package com.quanlykhachsan.dao;

<<<<<<< HEAD
import com.quanlykhachsan.entity.ChiTietHoaDon;
import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.model.ConnectDB;
import com.quanlykhachsan.entity.Voucher;
import java.lang.reflect.Array;
=======
>>>>>>> 419024f766aed4c99926820b34f384cbdfd8a518
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
import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.entity.Voucher;
import com.quanlykhachsan.model.ConnectDB;

public class HoaDon_DAO {
<<<<<<< HEAD
	public static void main(String[] args) {
		try {
			ConnectDB con = new ConnectDB();
			con.connect();
			HoaDon_DAO a = new HoaDon_DAO();
			a.getList().forEach(x -> System.out.println(x));
=======

	private List<HoaDon> listHoaDon = new ArrayList<>();
	private  KhachHang_DAO kh_dao = new KhachHang_DAO();
	public List<HoaDon> getList() {
		return listHoaDon;
	}

	public void docTuBang() {
		String sql = "SELECT * FROM HoaDon";
		listHoaDon.clear();

		try (Connection con = ConnectDB.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ArrayList<ChiTietHoaDon> dscthd = new ArrayList<>();
				dscthd.add(new ChiTietHoaDon(rs.getString("maChiTietHoaDon")));

				HoaDon hd = new HoaDon(rs.getString("maHoaDon"), rs.getDate("ngayLapHoaDon").toLocalDate(),
						new NhanVien(rs.getString("maNhanVien")), new Voucher(rs.getString("maVoucher")),
						kh_dao.timTheoMa(rs.getString("maKhachHang")), dscthd, rs.getDouble("VAT"),
						rs.getBoolean("trangThai"), rs.getDate("checkIn").toLocalDate(),
						rs.getDate("checkOut").toLocalDate(), rs.getDouble("datCoc"), rs.getDouble("tienPhat"),
						rs.getDouble("tongTien"));
				listHoaDon.add(hd);
			}
>>>>>>> 419024f766aed4c99926820b34f384cbdfd8a518
		} catch (SQLException ex) {
			Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

<<<<<<< HEAD
	private List<HoaDon> ca = new ArrayList<>();
	private KhachHang_DAO kh_dao = new KhachHang_DAO();
	public HoaDon_DAO() {
		docTuBang();
	}

	public List<HoaDon> getList() {
		return ca;
	}

	public void docTuBang() {

		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM HoaDon";
			PreparedStatement ps = con.prepareCall(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HoaDon calamviec = new HoaDon(rs.getString("maHoaDon"), rs.getDate("ngayLapHoaDon").toLocalDate(),
						new NhanVien(rs.getString("maNhanVien")), new Voucher(rs.getString("maVoucher")),
						new KhachHang(rs.getString("maKhachHang")), new ChiTietHoaDon(rs.getString("maChiTietHoaDon")),
						rs.getDouble("VAT"), rs.getBoolean("trangThai"), rs.getDate("checkIN").toLocalDate(),
						rs.getDate("checkOUT").toLocalDate(), rs.getDouble("datCoc"), rs.getDouble("tienPhat"),
						rs.getDouble("tongTien"));
				ca.add(calamviec);
			}

			ps.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
=======
>>>>>>> 419024f766aed4c99926820b34f384cbdfd8a518
	public List<HoaDon> timTheoMaPhong(String maPhong) {
	    List<HoaDon> hoaDonsTheoPhong = new ArrayList<>();// Danh sách mới cho mỗi phòng
	  
	    String sql = """
	        SELECT hd.* 
	        FROM phong p 
	        JOIN LichSuDatPhong ls ON p.maPhong = ls.maPhong
	        JOIN HoaDon hd ON hd.maChiTietHoaDon = ls.maChiTietHoaDon
	        WHERE p.maPhong = ?
	    """;

	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        ps.setString(1, maPhong);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                ArrayList<ChiTietHoaDon> dscthd = new ArrayList<>();
	                dscthd.add(new ChiTietHoaDon(rs.getString("maChiTietHoaDon")));
	                
	                HoaDon hd = new HoaDon(
	                    rs.getString("maHoaDon"),
	                    rs.getDate("ngayLapHoaDon").toLocalDate(),
	                    new NhanVien(rs.getString("maNhanVien")),
	                    new Voucher(rs.getString("maVoucher")),
	                    kh_dao.timTheoMa(rs.getString("maKhachHang")),
<<<<<<< HEAD
	                    dscthd.get(0),
=======
	                    dscthd,
>>>>>>> 419024f766aed4c99926820b34f384cbdfd8a518
	                    rs.getDouble("VAT"),
	                    rs.getBoolean("trangThai"),
	                    rs.getDate("checkIn").toLocalDate(),
	                    rs.getDate("checkOut").toLocalDate(),
	                    rs.getDouble("datCoc"),
	                    rs.getDouble("tienPhat"),
	                    rs.getDouble("tongTien")
	                );

	                hoaDonsTheoPhong.add(hd);
	            }
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return hoaDonsTheoPhong; // Trả về danh sách hóa đơn cho phòng cụ thể
	}

<<<<<<< HEAD
	private void getDouble(String tienPhat) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}
=======

>>>>>>> 419024f766aed4c99926820b34f384cbdfd8a518
}
