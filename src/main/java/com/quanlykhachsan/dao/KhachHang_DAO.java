package com.quanlykhachsan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.LoaiNhanVien;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.enum_Class.CaLamViec;
import com.quanlykhachsan.enum_Class.GioiTinh;
import com.quanlykhachsan.enum_Class.TrangThaiNhanVien;
import com.quanlykhachsan.model.ConnectDB;

public class KhachHang_DAO {

	public KhachHang_DAO() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<KhachHang> hienBangNV() {
		ArrayList<KhachHang> dsnv = new ArrayList<>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM KhachHang";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String sdt = rs.getString(3);
				GioiTinh gioiTinh = GioiTinh.valueOf(rs.getString(4));
				String diaChi = rs.getString(5);
				LocalDate ngaySinh = rs.getDate(6).toLocalDate();
				String cccd = rs.getString(7);
				String email = rs.getString(8);
				KhachHang nv = new KhachHang(ma, ten, sdt, gioiTinh, diaChi, ngaySinh,cccd ,email);
				dsnv.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsnv;
	}
	
	public KhachHang timTheoMa(String maKhachHang) {
	    KhachHang khachHang = null; // Khởi tạo biến khách hàng
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        // Sử dụng câu lệnh SQL với điều kiện WHERE để tìm khách hàng theo mã
	        String sql = "SELECT * FROM KhachHang WHERE MaKhachHang = ?"; // Thay 'MaKhachHang' bằng tên cột thực tế
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        preparedStatement.setString(1, maKhachHang); // Đặt giá trị cho tham số

	        ResultSet rs = preparedStatement.executeQuery();

	        // Kiểm tra nếu có kết quả và lấy thông tin khách hàng
	        if (rs.next()) {
	            String ten = rs.getString(2);
	            String sdt = rs.getString(3);
	            //GioiTinh gioiTinh = GioiTinh.valueOf(rs.getString(4));
	            GioiTinh gioiTinh = GioiTinh.NAM;
	            String diaChi = rs.getString(5);
	            LocalDate ngaySinh = rs.getDate(6).toLocalDate();
	            String cccd = rs.getString(7);
	            String email = rs.getString(8);
	            // Tạo đối tượng KhachHang
	            khachHang = new KhachHang(maKhachHang, ten, sdt, gioiTinh, diaChi, ngaySinh, cccd, email);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return khachHang; // Trả về khách hàng tìm thấy hoặc null nếu không tìm thấy
	}

	public KhachHang timKhachHangTheoSoDienThoai(String soDienThoai) {
	    KhachHang khachHang = null; // Khởi tạo biến khách hàng
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        // Sử dụng câu lệnh SQL với điều kiện WHERE để tìm khách hàng theo số điện thoại
	        String sql = "SELECT * FROM KhachHang WHERE SoDienThoai = ?"; // Thay 'SoDienThoai' bằng tên cột thực tế
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        preparedStatement.setString(1, soDienThoai); // Đặt giá trị cho tham số

	        ResultSet rs = preparedStatement.executeQuery();

	        // Kiểm tra nếu có kết quả và lấy thông tin khách hàng
	        if (rs.next()) {
	            String maKhachHang = rs.getString(1); // Giả sử mã khách hàng ở cột đầu tiên
	            String ten = rs.getString(2);	            
	            String sdt = rs.getString(3);
	            GioiTinh gioiTinh = GioiTinh.NAM;
	            //GioiTinh gioiTinh = GioiTinh.valueOf(rs.getString(4));
	            String diaChi = rs.getString(5);
	            LocalDate ngaySinh = rs.getDate(6).toLocalDate();
	            String cccd = rs.getString(7);
	            String email = rs.getString(8);
	            // Tạo đối tượng KhachHang
	            khachHang = new KhachHang(maKhachHang, ten, sdt, gioiTinh, diaChi, ngaySinh, cccd, email);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return khachHang; // Trả về khách hàng tìm thấy hoặc null nếu không tìm thấy
	}


}
