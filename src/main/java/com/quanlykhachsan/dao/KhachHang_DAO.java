package com.quanlykhachsan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.enum_Class.CaLamViec;
import com.quanlykhachsan.enum_Class.GioiTinh;
import com.quanlykhachsan.enum_Class.TrangThaiNhanVien;
import com.quanlykhachsan.model.ConnectDB;
import java.sql.Date;
import java.sql.SQLException;

public class KhachHang_DAO {

	public KhachHang_DAO() {
		
	}

	public ArrayList<KhachHang> hienBangNV() {
		ArrayList<KhachHang> dsnv = new ArrayList<>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM KhachHang";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String ma =  rs.getString(1);
				String ten = rs.getString(2);
				String sdt = rs.getString(3);
				
				String diaChi = rs.getString(5);
				LocalDate ngaySinh = rs.getDate(6).toLocalDate();
				String cccd = rs.getString(7);
				String email = rs.getString(8);
				KhachHang nv = new KhachHang(ma, ten, sdt, GioiTinh.setGioiTinh(rs.getString("gioiTinh")), diaChi, ngaySinh,cccd ,email);
				dsnv.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsnv;
	}
          public boolean capNhatKhachHang(KhachHang a) throws SQLException{
              
           Connection con = ConnectDB.getInstance().getConnection();
           String sql = "update KhachHang set tenKhachHang = ?, soDienThoai = ?, gioiTinh = ?, diaChi = ?, ngaySinh = ?, email = ?,  cccd = ?"
                   + "where maKhachHang = ?";
           PreparedStatement state = con.prepareStatement(sql);
           state.setString(1, a.getTenKhachHang());
           state.setString(2, a.getSoDienThoai());
           state.setString(3,a.getGioiTinh().getGioiTinh()) ;
           state.setString(4,a.getDiaChi()) ;
           state.setDate(5, Date.valueOf(a.getNgaySinh()));
           state.setString(6,a.getEmail()) ;
           state.setString(7,a.getCCCD()) ;
           state.setString(8, a.getMaKhachHang());
           state.executeUpdate(); 
           state.close();
           return true;
       }
	public boolean themKhachHang(KhachHang a){
            
             try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO KhachHang VALUES(?,?,?,?,?, ? , ? , ?)");
            ps.setString(1, a.getMaKhachHang());
            ps.setString(2, a.getTenKhachHang());
            ps.setString(3, a.getSoDienThoai());
            ps.setString(4, (a.getGioiTinh().getGioiTinh()));
            ps.setString(5 , a.getDiaChi());
            ps.setDate(6, Date.valueOf(a.getNgaySinh()));
            ps.setString(7, a.getCCCD());
            ps.setString(8, a.getEmail());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        }
	public KhachHang timTheoMa(String maKhachHang) {
            if (maKhachHang == null || maKhachHang.equals("")) {
    return new KhachHang();
}
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
	            
	            String diaChi = rs.getString(5);
	            LocalDate ngaySinh = rs.getDate(6).toLocalDate();
	            String cccd = rs.getString(7);
	            String email = rs.getString(8);
	            // Tạo đối tượng KhachHang
	            khachHang = new KhachHang(maKhachHang, ten, sdt, 	            GioiTinh.setGioiTinh(rs.getString("gioiTinh"))
, diaChi, ngaySinh, cccd, email);
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
//	            GioiTinh gioiTinh = GioiTinh.NAM;
	            //GioiTinh gioiTinh = GioiTinh.valueOf(rs.getString(4));
	            String diaChi = rs.getString(5);
	            LocalDate ngaySinh = rs.getDate(6).toLocalDate();
	            String cccd = rs.getString(7);
	            String email = rs.getString(8);
	            // Tạo đối tượng KhachHang
	            khachHang = new KhachHang(maKhachHang, ten, sdt, 	            GioiTinh.setGioiTinh(rs.getString("gioiTinh"))
, diaChi, ngaySinh, cccd, email);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return khachHang; // Trả về khách hàng tìm thấy hoặc null nếu không tìm thấy
	}


}
