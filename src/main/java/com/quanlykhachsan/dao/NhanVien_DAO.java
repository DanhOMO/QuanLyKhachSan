package com.quanlykhachsan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.quanlykhachsan.entity.LoaiNhanVien;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.enum_Class.CaLamViec;
import com.quanlykhachsan.enum_Class.GioiTinh;
import com.quanlykhachsan.enum_Class.TrangThaiNhanVien;
import com.quanlykhachsan.model.ConnectDB;

public class NhanVien_DAO {
	
	public NhanVien_DAO() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<NhanVien> hienBangNV() {
		ArrayList<NhanVien> dsnv = new ArrayList<>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String sdt = rs.getString(3);
				GioiTinh gioiTinh = GioiTinh.valueOf(rs.getString(4));
				String diaChi = rs.getString(5);
				LocalDate ngaySinh = rs.getDate(6).toLocalDate();
				String email = rs.getString(7);
				String maLoaiNhanVien = rs.getString(8);
				LoaiNhanVien lnv = new LoaiNhanVien(maLoaiNhanVien);
				TrangThaiNhanVien trangThai = TrangThaiNhanVien.valueOf(rs.getString(9));
				NhanVien nv = new NhanVien(ma, ten, sdt, gioiTinh, diaChi, ngaySinh, email, lnv, trangThai);
				dsnv.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsnv;
	}

	public boolean themNV(NhanVien nv) {
	    Connection con = ConnectDB.getInstance().getConnection();
	    PreparedStatement state = null;
	    int n = 0;
	    
	    try {
	        String sql = "INSERT INTO NhanVien (MaNhanVien, TenNhanVien, SoDienThoai, GioiTinh, DiaChi, NgaySinh, Email, MaLoaiNhanVien, TrangThai)"
	                   + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        state = con.prepareStatement(sql);

	        state.setString(1, nv.getMaNhanVien());
	        state.setString(2, nv.getTenNhanVien());
	        state.setString(3, nv.getSoDienThoai());
	        state.setString(4, nv.getGioiTinh().name()); // Chuyển enum GioiTinh thành String
	        state.setString(5, nv.getDiaChi());
	        state.setDate(6, java.sql.Date.valueOf(nv.getNgaySinh())); // Chuyển LocalDate thành java.sql.Date
	        state.setString(7, nv.getEmail());
	        state.setString(8, nv.getLoaiNhanVien().getMaLoaiNhanVien());
	        state.setString(9, nv.getTrangThai().name()); // Chuyển enum TrangThaiNhanVien thành String

	        n = state.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (state != null) state.close();
	            if (con != null) con.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	    return n != 0;
	}


	public boolean capnhatNV(NhanVien nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement state = null;
		int n = 0;
		try {
			String sql = "UPDATE NhanVien SET tenNhanVien = ?, soDienThoai = ?, ngaySinh = ?, diaChi = ?, ngaySinh = ?, email = ?, maLoaiNhanVien = ?, trangThai = ? "
					+ "WHERE maNhanVien = ?";
			state = con.prepareStatement(sql);
			state.setString(1, nv.getTenNhanVien());
			state.setString(2, nv.getSoDienThoai());
			state.setString(3, nv.getGioiTinh().name()); // Chuyển enum GioiTinh thành String
			state.setString(4, nv.getDiaChi());
			state.setDate(5, java.sql.Date.valueOf(nv.getNgaySinh())); // Chuyển LocalDate thành java.sql.Date
			state.setString(6, nv.getEmail());
			state.setString(7, nv.getLoaiNhanVien().getMaLoaiNhanVien());
			state.setString(8, nv.getTrangThai().name()); // Chuyển enum TrangThaiNhanVien thành StringS
			state.setString(9, nv.getMaNhanVien()); // Điều kiện WHERE
			n = state.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				state.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return n != 0;
	}

}
