package com.quanlykhachsan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.quanlykhachsan.entity.ChiTietHoaDon;
import com.quanlykhachsan.entity.DichVu;
import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.LichSuDatDichVu;
import com.quanlykhachsan.entity.LichSuDatPhong;
import com.quanlykhachsan.entity.Phong;
import com.quanlykhachsan.model.ConnectDB;

public class ChiTietHoaDon_DAO {

	public ChiTietHoaDon_DAO() {
		
	}
	
	private List<ChiTietHoaDon> listCTHoaDon = new ArrayList<>();
	public List<ChiTietHoaDon> getList() {
		return listCTHoaDon;
	}
        public List<String> timMaCTHD(String maPhong) {
        List<String> maCTHDList = new ArrayList<>();
        String sql = "select ct.maChiTietHoaDon from HoaDon hd\n" +
                     "join ChiTietHoaDon ct on hd.maHoaDon = ct.maHoaDon\n" +
                     "join Phong p on p.maPhong = ct.maPhong\n" +
                     "where p.maPhong = ? and hd.trangThai = 0";

        // Kết nối cơ sở dữ liệu và thực hiện truy vấn
        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Gán giá trị tham số maPhong
            ps.setString(1, maPhong);

            // Thực hiện truy vấn và lấy kết quả
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Lấy maChiTietHoaDon và thêm vào danh sách
                    maCTHDList.add(rs.getString("maChiTietHoaDon"));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();  // In ra lỗi nếu có
        }
        return maCTHDList;  // Trả về danh sách các mã chi tiết hóa đơn
}

	public void docTuBang() {
		String sql = "SELECT * FROM ChiTietHoaDon";
		listCTHoaDon.clear();

		try (Connection con = ConnectDB.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon(rs.getString("maChiTietHoaDon")
                                                ,new Phong(rs.getString("maPhong"))
						, rs.getDate("thoiGianDatPhong").toLocalDate()
						, rs.getDouble("giaDatHang")
                                                , new HoaDon(rs.getString("maHoaDon")));
				listCTHoaDon.add(cthd);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
	    String sql = "INSERT INTO ChiTietHoaDon (maChiTietHoaDon, maPhong, thoiGianDatPhong ,giaDatHang, maHoaDon) VALUES (?, ?, ?, ?,?)";
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, cthd.getMaChiTietHoaDon());
                ps.setString(2, cthd.getMaPhong().getMaPhong());
	        ps.setDate(3, java.sql.Date.valueOf(cthd.getNgayLapHoaDon()));
	        ps.setDouble(4, cthd.getGiaDatPhong());
	        ps.setString(5, cthd.getMaHoaDon().getMaHoaDon());
	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0; // Trả về true nếu thêm thành công
	    } catch (SQLException ex) {
	        Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
	        return false;
	    }
	}
	public boolean capNhatGiaTheoMa(String maPhong, double giaDatHang) {
	    String sql = "UPDATE ChiTietHoaDon SET giaDatHang = ? WHERE maPhong = ?";
	    
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        // Gán giá trị cho câu lệnh SQL
	        ps.setDouble(1, giaDatHang);  // Gán giá trị giaDatHang
	        ps.setString(2, maPhong);  // Gán giá trị maChiTietHoaDon
	        
	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0; // Trả về true nếu cập nhật thành công
	    } catch (SQLException ex) {
	        Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
	        return false;
	    }
	}
        public boolean capNhatGiaDatHang(String maChiTietHoaDon, double giaDatHang) {
	    String sql = "UPDATE ChiTietHoaDon SET giaDatHang = ? WHERE maChiTietHoaDon = ?";
	    
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        // Gán giá trị cho câu lệnh SQL
	        ps.setDouble(1, giaDatHang);  // Gán giá trị giaDatHang
	        ps.setString(2, maChiTietHoaDon);  // Gán giá trị maChiTietHoaDon
	        
	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0; // Trả về true nếu cập nhật thành công
	    } catch (SQLException ex) {
	        Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
	        return false;
	    }
	}

	
	public boolean suaChiTietHoaDon(ChiTietHoaDon cthd) {
	    String sql = "UPDATE ChiTietHoaDon SET maPhong = ?, thoiGianDatPhong = ?, giaDatHang = ? WHERE maHoaDon = ?";
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setDate(1, java.sql.Date.valueOf(cthd.getNgayLapHoaDon()));
                ps.setString(2, cthd.getMaPhong().getMaPhong());
	        ps.setDouble(3, cthd.getGiaDatPhong());
	        ps.setString(4, cthd.getMaHoaDon().getMaHoaDon());

	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0; // Trả về true nếu cập nhật thành công
	    } catch (SQLException ex) {
	        Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
	        return false;
	    }
	}
	
	public List<ChiTietHoaDon> timChiTietHoaDonTheoMa(String maChiTietHoaDon) {
	    String sql = "SELECT * FROM ChiTietHoaDon WHERE maHoaDon = ?";
	    List<ChiTietHoaDon> list= new ArrayList<>();
	    
	    try (Connection con = ConnectDB.getInstance().getConnection();
	    		PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, maChiTietHoaDon);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            ChiTietHoaDon cthd = new ChiTietHoaDon(
	                rs.getString("maChiTietHoaDon"),
                        new Phong(rs.getString("maPhong")),
	                rs.getDate("thoiGianDatPhong").toLocalDate(),
	                rs.getDouble("giaDatHang"),
                    new HoaDon(rs.getString("maHoaDon"))
	            );
                    list.add(cthd);
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    return list;
	}
	
	public ChiTietHoaDon timChiTietHoaDonTheoMaCT(String maChiTietHoaDon) {
	    String sql = "SELECT * FROM ChiTietHoaDon WHERE maChiTietHoaDon = ?";	    
	    try (Connection con = ConnectDB.getInstance().getConnection();
	    		PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, maChiTietHoaDon);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            ChiTietHoaDon cthd = new ChiTietHoaDon(
	                rs.getString("maChiTietHoaDon"),
                        new Phong(rs.getString("maPhong")),
	                rs.getDate("thoiGianDatPhong").toLocalDate(),
	                rs.getDouble("giaDatHang"),
                    new HoaDon(rs.getString("maHoaDon"))
	            );
	            	return cthd;
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    return null;
	}

	public ArrayList<ChiTietHoaDon> dsLichSuDatPhong(String maHoaDon) {
		try {
			
			ArrayList<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
			String sql = "select maPhong,giaDatHang from ChiTietHoaDon\r\n" + //
								"where maHoaDon=?";
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maHoaDon);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon();
				Phong_DAO pDao = new Phong_DAO();
				Phong p = pDao.timPhongTheoMa(rs.getString("maPhong"));
				cthd.setMaPhong(p);
				cthd.setGiaDatPhong(rs.getDouble("giaDatHang"));
				ds.add(cthd);
			}
                        return ds;
		} catch (Exception e) {
			e.printStackTrace();
		}
                return null;
	}

    public ArrayList<LichSuDatDichVu> dsLichSuDichVu(String maHoaDon) {
        try {
			ArrayList<LichSuDatDichVu> ds = new ArrayList<LichSuDatDichVu>();
			String sql = "select dv.maDichVu,soLuongDatHang from ChiTietHoaDon cthd\r\n" + //
								"right join LichSuDatDichVu lsdv on lsdv.maChiTietHoaDon=cthd.maChiTietHoaDon\r\n" + //
								"left join DichVu dv on dv.maDichVu=lsdv.maDichVu\r\n" + //
								"where cthd.maHoaDon=?";
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maHoaDon);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LichSuDatDichVu lsdv = new LichSuDatDichVu();
				DichVu_DAO dvDao = new DichVu_DAO();
				DichVu dv = dvDao.timDichVu(rs.getString("maDichVu"));
				lsdv.setDichVu(dv);
				lsdv.setSoLuong(rs.getInt("soLuongDatHang"));
				ds.add(lsdv);
			}
			return ds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }

       public List<Object[]> docTuBangChiTietCaHoaDon() {
            String sql = "select c.maChiTietHoaDon, giaDatHang, ldv.maDichVu, ldv.thoiGianDatDichVu, ldv.soLuongDatHang, " +
                         "ldp.maPhong, ldp.thoiGianDatPhong, ldp.soLuong " +
                         "from ChiTietHoaDon c " +
                         "join LichSuDatDichVu ldv on c.maChiTietHoaDon = ldv.maChiTietHoaDon " +
                         "join LichSuDatPhong ldp on c.maChiTietHoaDon = ldp.maChiTietHoaDon";

            List<Object[]> dataList = new ArrayList<>();

            try (Connection con = ConnectDB.getInstance().getConnection();
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Object[] list = new Object[8];
                    list[0] = rs.getString("maChiTietHoaDon");
                    list[1] = rs.getDouble("giaDatHang");
                    list[2] = rs.getString("maDichVu");
                    list[3] = rs.getDate("thoiGianDatDichVu");
                    list[4] = rs.getInt("soLuongDatHang");
                    list[5] = rs.getString("maPhong");
                    list[6] = rs.getInt("soLuong");
                    list[7] = rs.getDate("thoiGianDatPhong");
                    dataList.add(list);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            return dataList;
        }
       public List<String>  getListMAHD(){
            docTuBang();
             return listCTHoaDon.stream()
                       .map(item -> item.getMaHoaDon().getMaHoaDon()) // Assuming getMaChiTietHoaDon() returns a String
                       .toList();
       }
       public List<String> getListMaCTHD() { 
           docTuBang();
             return listCTHoaDon.stream()
                       .map(item -> item.getMaChiTietHoaDon()) // Assuming getMaChiTietHoaDon() returns a String
                       .toList();
    }
       public double getTongTien(String ma){
           return listCTHoaDon.stream().filter( x -> x.getMaChiTietHoaDon().equalsIgnoreCase(ma)).findFirst().orElse(null).getGiaDatPhong();
       }

       public boolean xoaTheoMa(String maCTHD) {
    	    String sql = "DELETE FROM ChiTietHoaDon WHERE maChiTietHoaDon = ?";

    	    try (Connection con = ConnectDB.getInstance().getConnection();
    	         PreparedStatement ps = con.prepareStatement(sql)) {

    	        ps.setString(1, maCTHD); // Gán giá trị `maCTHD` vào câu truy vấn
    	        
    	        // Thực thi câu lệnh và kiểm tra số bản ghi bị ảnh hưởng
    	        int rowsDeleted = ps.executeUpdate();
    	        
    	        // Trả về true nếu có ít nhất một bản ghi bị xóa
    	        return rowsDeleted > 0;

    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	        return false; // Trả về false nếu có lỗi
    	    }
    	}
       
       public List<LichSuDatDichVu> getLichSuDatDichVuByMaChiTietHoaDon(String maChiTietHoaDon) {
    List<LichSuDatDichVu> lichSuList = new ArrayList<>();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // Kết nối tới cơ sở dữ liệu
        con = ConnectDB.getInstance().getConnection();

        // Câu lệnh SQL để lấy lịch sử đặt dịch vụ dựa trên mã chi tiết hóa đơn
        String sql = "SELECT * FROM LichSuDatDichVu WHERE maChiTietHoaDon = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, maChiTietHoaDon);

        // Thực thi truy vấn
        rs = ps.executeQuery();

        // Duyệt qua kết quả và thêm vào danh sách
        while (rs.next()) {
            // Tạo đối tượng LichSuDatDichVu và lấy thông tin từ kết quả truy vấn
            LichSuDatDichVu lichSu = new LichSuDatDichVu();
            DichVu dichVu = new DichVu();

            lichSu.setMaChiTietHoaDon(rs.getString("maChiTietHoaDon"));
            dichVu.setMaDichVu(rs.getString("maDichVu"));
            dichVu.setTenDichVu(rs.getString("tenDichVu"));
            dichVu.setGiaDichVu(rs.getDouble("giaDichVu")); // Giả sử cột giá dịch vụ là 'giaDichVu'
            lichSu.setDichVu(dichVu);
            lichSu.setSoLuong(rs.getInt("soLuong")); // Giả sử cột số lượng là 'soLuong'

            // Thêm vào danh sách kết quả
            lichSuList.add(lichSu);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng tài nguyên
        if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
    }

    return lichSuList;
}





}