<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.enum_Class.GioiTinh;
import com.quanlykhachsan.enum_Class.TrangThaiNhanVien;
import com.quanlykhachsan.model.ConnectDB;
import com.quanlykhachsan.entity.KhuVuc;
import com.quanlykhachsan.entity.LoaiNhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class NhanVien_DAO {
     private List<NhanVien> list = new ArrayList<>();
        public NhanVien_DAO(){
            docTuBang();
        }
    private Connection con = null;
          public List<NhanVien> getList(){
           return list;
       }
       public boolean themMoi(NhanVien a){
           if(list.contains(a)) throw new IllegalArgumentException("NhanVien Da TOn TAI !!!");
           list.add(a); return true;
       }
       public boolean capNhatNhanVien(NhanVien a) throws SQLException{
           con = ConnectDB.getInstance().getConnection();
           String sql = "update NhanVien set tenNhanVien = ?, soDienThoai = ?, gioiTinh = ?, diaChi = ?, ngaySinh = ?, email = ?, maLoaiNhanVien = ?  ,trangThai = ?"
                   + "where maNhanVien = ?";
           PreparedStatement state = con.prepareStatement(sql);
           state.setString(1, a.getTenNhanVien());
           state.setString(2, a.getSoDienThoai());
           state.setString(3,a.getGioiTinh().getGioiTinh()) ;
           state.setString(4,a.getDiaChi()) ;
           state.setDate(5, Date.valueOf(a.getNgaySinh()));
           state.setString(6,a.getEmail()) ;
           System.out.println(a.getLoaiNhanVien().getMaLoaiNhanVien());
           state.setString(7,a.getLoaiNhanVien().getMaLoaiNhanVien()) ;
           state.setString(8,a.getTrangThai().getTrangThaiNhanVien());
           state.setString(9, a.getMaNhanVien()                );
           state.executeUpdate(); 
           state.close();
           return true;
       }
   public DefaultTableModel docDuLieuVaoBan() {
    // Thêm tên cột vào DefaultTableModel
    DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Nhân Viên", "Tên Nhân Viên", "Số Điện Thoại", "Địa Chỉ", "Ngày Sinh", "Email", "Loại Nhân Viên", "Trạng Thái"}, 0);

    // Thêm dữ liệu vào DefaultTableModel
    list.stream().forEach(x -> {
        dtm.addRow(new Object[]{
            x.getMaNhanVien(), 
            x.getTenNhanVien(), 
            x.getSoDienThoai(),
            x.getDiaChi(),
            x.getNgaySinh(),
            x.getEmail(),
            x.getLoaiNhanVien().getMaLoaiNhanVien(),
            x.getTrangThai().getTrangThaiNhanVien()
        });
    });
    
    return dtm;
}
        public NhanVien timTheoMa(String ma){
            return list.stream().filter(x -> x.getMaNhanVien().equalsIgnoreCase(ma)).findFirst().orElse(null);
        }
       public void docTuBang(){
           list.clear();
           con =  ConnectDB.getInstance().getConnection();
           String sql = "select * from NhanVien";
           
           try {
               PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    NhanVien phong = new NhanVien(
                    rs.getString("maNhanVien"),
                    rs.getString("tenNhanVien"),
                    rs.getString("soDienThoai"),
                            GioiTinh.setGioiTinh(rs.getString("gioiTinh")),
                    rs.getString("diaChi"),
                            rs.getDate("ngaySinh").toLocalDate(),
                            rs.getString("email"),
                            new LoaiNhanVien(rs.getString("maLoaiNhanVien")),
                            TrangThaiNhanVien.setTrangThaiNhanVien(rs.getString("trangThai"))
                    );
                   list.add(phong); 
               }
             rs.close();
             ps.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
        
       }
=======
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

>>>>>>> 419024f766aed4c99926820b34f384cbdfd8a518
}
