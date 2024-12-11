package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.ChiTietHoaDon;
import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.model.ConnectDB;
import com.quanlykhachsan.entity.Voucher;
import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HoaDon_DAO {

	public static void main(String[] args) {
		try {
			ConnectDB con = new ConnectDB();
			con.connect();
			HoaDon_DAO a = new HoaDon_DAO();
			a.layDanhSachHoaDon().forEach(x->System.out.println(x));
		} catch (SQLException ex) {
			Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private List<HoaDon> ca = new ArrayList<>();
	private KhachHang_DAO kh_dao = new KhachHang_DAO();
        private ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
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
						new KhachHang(rs.getString("maKhachHang")),
						rs.getDouble("VAT"), rs.getBoolean("trangThai"), rs.getTimestamp("checkIN").toLocalDateTime(),
						rs.getTimestamp("checkOUT").toLocalDateTime(), rs.getDouble("datCoc"), rs.getDouble("tienPhat"),
						rs.getDouble("tongTien"));
				ca.add(calamviec);
			}

			ps.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public List<HoaDon> timTheoMaPhong(String maPhong) {
	    List<HoaDon> hoaDonsTheoPhong = new ArrayList<>();// Danh sách mới cho mỗi phòng
	  
	    String sql = "SELECT hd.* " +
                "FROM Phong p " +
                "JOIN ChiTietHoaDon ct ON ct.maPhong = p.maPhong " +
                "JOIN HoaDon hd ON hd.maHoaDon = ct.maHoaDon " +
                "WHERE p.maPhong = ? AND hd.trangThai = 0";
               

	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        ps.setString(1, maPhong);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                HoaDon hd = new HoaDon(
	                    rs.getString("maHoaDon"),
	                    rs.getDate("ngayLapHoaDon").toLocalDate(),
	                    new NhanVien(rs.getString("maNhanVien")),
	                    new Voucher(rs.getString("maVoucher")),
	                    kh_dao.timTheoMa(rs.getString("maKhachHang")),
	                    rs.getDouble("VAT"),
	                    rs.getBoolean("trangThai"),
	                    rs.getTimestamp("checkIn").toLocalDateTime(),
	                    rs.getTimestamp("checkOut").toLocalDateTime(),
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
        
	public List<HoaDon> timTheoCheckInCheckOut(String maPhong, java.sql.Date checkIn, java.sql.Date checkOut) {
	    List<HoaDon> danhSachHoaDon = new ArrayList<>();

	    String sql = "SELECT hd.* " +
	                 "FROM Phong p " +
	                 "JOIN ChiTietHoaDon ct ON ct.maPhong = p.maPhong " +
	                 "JOIN HoaDon hd ON hd.maHoaDon = ct.maHoaDon " +
	                 "WHERE p.maPhong = ? AND hd.trangThai = 0" +//
	                 "AND    ((? BETWEEN hd.checkIN AND DATEADD(DAY, 1, hd.checkOut)) "+
		             "    OR (? BETWEEN hd.checkIN AND DATEADD(DAY, 1, hd.checkOut))"
		             + " OR (hd.checkIn >= ?)) "+
	                 "ORDER BY hd.checkIN ASC";

	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement preparedStatement = con.prepareStatement(sql)) {

	        preparedStatement.setString(1, maPhong);
	        preparedStatement.setDate(2, checkIn);
	        preparedStatement.setDate(3, checkOut);
	        preparedStatement.setDate(4, checkIn);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                HoaDon hoaDon = new HoaDon(
	                    resultSet.getString("maHoaDon"),
	                    resultSet.getDate("ngayLapHoaDon").toLocalDate(),
	                    new NhanVien(resultSet.getString("maNhanVien")),
	                    new Voucher(resultSet.getString("maVoucher")),
	                    kh_dao.timTheoMa(resultSet.getString("maKhachHang")),
	                    resultSet.getDouble("VAT"),
	                    resultSet.getBoolean("trangThai"),
	                    resultSet.getTimestamp("checkIn").toLocalDateTime(),
	                    resultSet.getTimestamp("checkOut").toLocalDateTime(),
	                    resultSet.getDouble("datCoc"),
	                    resultSet.getDouble("tienPhat"),
	                    resultSet.getDouble("tongTien")
	                );

	                danhSachHoaDon.add(hoaDon);
	            }
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return danhSachHoaDon; // Trả về danh sách hóa đơn có khoảng thời gian giao nhau
	}
	
	public List<HoaDon> timTheoCheckIn(String maPhong, java.sql.Date checkIn) {
	    List<HoaDon> danhSachHoaDon = new ArrayList<>();

	    String sql = "SELECT hd.* " +
	                 "FROM Phong p " +
	                 "JOIN ChiTietHoaDon ct ON ct.maPhong = p.maPhong " +
	                 "JOIN HoaDon hd ON hd.maHoaDon = ct.maHoaDon " +
	                 "WHERE p.maPhong = ? " +
	                 "and DATEADD(DAY, 1, hd.checkOut) >= ? and hd.trangThai = 0"+
	                 "ORDER BY hd.checkIN ASC";


	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement preparedStatement = con.prepareStatement(sql)) {

	        preparedStatement.setString(1, maPhong);
	        preparedStatement.setDate(2, checkIn);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                HoaDon hoaDon = new HoaDon(
	                    resultSet.getString("maHoaDon"),
	                    resultSet.getDate("ngayLapHoaDon").toLocalDate(),
	                    new NhanVien(resultSet.getString("maNhanVien")),
	                    new Voucher(resultSet.getString("maVoucher")),
	                    kh_dao.timTheoMa(resultSet.getString("maKhachHang")),
	                    resultSet.getDouble("VAT"),
	                    resultSet.getBoolean("trangThai"),
	                    resultSet.getTimestamp("checkIn").toLocalDateTime(),
	                    resultSet.getTimestamp("checkOut").toLocalDateTime(),
	                    resultSet.getDouble("datCoc"),
	                    resultSet.getDouble("tienPhat"),
	                    resultSet.getDouble("tongTien")
	                );

	                danhSachHoaDon.add(hoaDon);
	            }
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return danhSachHoaDon; // Trả về danh sách hóa đơn có khoảng thời gian giao nhau
	}
	
	
	public void capNhatHuyPhong() {
	    CallableStatement callableStatement = null;
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        callableStatement = con.prepareCall("{call HuyPhong()}");
	        callableStatement.execute();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (callableStatement != null) {
	            try {
	                callableStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

    public ArrayList<HoaDon> layDanhSachHoaDon() {

        dsHoaDon = new ArrayList<HoaDon>();
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("select maHoaDon,maKhachHang,tongTien,trangThai from HoaDon");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("maHoaDon"));
                hd.setKhachHang(new KhachHang(rs.getString(2)));
                hd.setTongTien(rs.getDouble("tongTien"));
                hd.setTrangThai(rs.getBoolean("trangThai"));
                dsHoaDon.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

    public HoaDon timHoaDon(String string) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("select * from HoaDon where maHoaDon = ?");
            ps.setString(1, string);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("maHoaDon"));
                hd.setThoiGianLapHoaDon(rs.getDate("ngayLapHoaDon").toLocalDate());
                hd.setNhanVien(new NhanVien(rs.getString("maNhanVien")));
                hd.setVoucher(new Voucher(rs.getString("maVoucher")));
                KhachHang kh = kh_dao.timTheoMa(rs.getString("maKhachHang"));
                hd.setKhachHang(kh);
                hd.setVAT(rs.getDouble("VAT"));
                hd.setTrangThai(rs.getBoolean("trangThai"));
                hd.setCheckIn(rs.getTimestamp("checkIn").toLocalDateTime());
                hd.setCheckOut(rs.getTimestamp("checkOut").toLocalDateTime());
                hd.setTienCoc(rs.getDouble("datCoc"));
                hd.setTienPhat(rs.getDouble("tienPhat"));
                hd.setTongTien(rs.getDouble("tongTien"));
                return hd;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean capNhatHoaDon(String maHoaDon, double tongTien, boolean trangThai, Voucher voucherHD,
            double tienPhat) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "update HoaDon set tongTien = ?, trangThai = ?, maVoucher = ?, tienPhat = ? where maHoaDon = ?");
            ps.setDouble(1, tongTien);
            ps.setBoolean(2, trangThai);
            ps.setString(3, voucherHD.getMaVoucher());
            ps.setDouble(4, tienPhat);
            ps.setString(5, maHoaDon);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean themHoaDon(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (maHoaDon, ngayLapHoaDon, maNhanVien, maVoucher, maKhachHang, VAT, trangThai, checkIN, checkOUT, datCoc, tienPhat, tongTien) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hd.getMaHoaDon());
            ps.setDate(2, java.sql.Date.valueOf(hd.getThoiGianLapHoaDon()));
            ps.setString(3, hd.getNhanVien().getMaNhanVien());
            if (hd.getVoucher() == null) {
                ps.setString(4, null);
            } else {
                ps.setString(4, hd.getVoucher().getMaVoucher());
            }
            if(hd.getKhachHang() == null) {
            	ps.setString(5, null);
            }else {
            	ps.setString(5, hd.getKhachHang().getMaKhachHang());
            }
            
            ps.setDouble(6, hd.getVAT());
            ps.setBoolean(7, hd.getTrangThai());
            ps.setTimestamp(8, Timestamp.valueOf(hd.getCheckIn()));
            ps.setTimestamp(9, Timestamp.valueOf(hd.getCheckOut()));
            ps.setDouble(10, hd.getTienCoc());
            ps.setDouble(11, hd.getTienPhat());
            ps.setDouble(12, hd.getTongTien());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu thêm thành công
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List<HoaDon> timTheoSoDienThoaiKhachHang(String sdt) {
        List<HoaDon> hoaDonsTheoKhachHang = new ArrayList<>(); // Danh sách mới cho hóa đơn theo khách hàng

        String sql = "SELECT hd.* FROM HoaDon hd "
                + "JOIN KhachHang kh ON hd.maKhachHang = kh.maKhachHang "
                + "WHERE kh.soDienThoai = ?";

        try (Connection con = ConnectDB.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sdt); // Gán số điện thoại vào tham số đầu tiên
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDon hd = new HoaDon(
                            rs.getString("maHoaDon"),
                            rs.getDate("ngayLapHoaDon").toLocalDate(),
                            new NhanVien(rs.getString("maNhanVien")),
                            new Voucher(rs.getString("maVoucher")),
                            kh_dao.timTheoMa(rs.getString("maKhachHang")), // Lấy thông tin khách hàng
                            rs.getDouble("VAT"),
                            rs.getBoolean("trangThai"),
                            rs.getTimestamp("checkIn").toLocalDateTime(),
                            rs.getTimestamp("checkOut").toLocalDateTime(),
                            rs.getDouble("datCoc"),
                            rs.getDouble("tienPhat"),
                            rs.getDouble("tongTien")
                    );

                    hoaDonsTheoKhachHang.add(hd); // Thêm hóa đơn vào danh sách
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

	


        return hoaDonsTheoKhachHang; // Trả về danh sách hóa đơn cho khách hàng cụ thể
    }

    public boolean capNhatKhachVaTongTien(String maKhachHang, double tongTien, String maHoaDon) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            // Kết nối cơ sở dữ liệu
            con = ConnectDB.getInstance().getConnection();
            
            // Cập nhật tổng tiền và mã khách hàng trong hóa đơn
            String updateHoaDonSQL = "UPDATE HoaDon SET tongTien = ?, maKhachHang = ? WHERE maHoaDon = ?";
            ps = con.prepareStatement(updateHoaDonSQL);
            ps.setDouble(1, tongTien);         // Gán tổng tiền
            ps.setString(2, maKhachHang);      // Gán mã khách hàng
            ps.setString(3, maHoaDon);         // Gán mã hóa đơn (thêm tham số vào câu lệnh SQL)

            // Thực thi câu lệnh
            int rowsUpdated = ps.executeUpdate();

            // Trả về true nếu cập nhật thành công, false nếu không thành công
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Trả về false nếu có lỗi
        } 
        
    }

    public boolean xoaHoaDon(String maHoaDon) {
        String sql = "DELETE FROM HoaDon WHERE maHoaDon = ?";

        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maHoaDon); // Gán giá trị `maHoaDon` vào câu truy vấn
            
            // Thực thi câu lệnh và kiểm tra số bản ghi bị ảnh hưởng
            int rowsDeleted = ps.executeUpdate();
            
            // Trả về true nếu có ít nhất một bản ghi bị xóa
            return rowsDeleted > 0;

        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Trả về false nếu có lỗi
        }
    }

///////////////////////////////
public void capNhatGiaDatHang(String maPhong) throws SQLException {
    String queryGiaDatHang = 
        "UPDATE ChiTietHoaDon " +
        "SET giaDatHang = ( " +
        "    SELECT lp.giaThuePhong + COALESCE(SUM(dv.giaDichVu), 0) " +
        "    FROM ChiTietHoaDon cthd " +
        "    LEFT JOIN LichSuDatDichVu lsdv ON lsdv.maChiTietHoaDon = cthd.maChiTietHoaDon " +
        "    LEFT JOIN DichVu dv ON dv.maDichVu = lsdv.maDichVu " +
        "    LEFT JOIN Phong p ON p.maPhong = cthd.maPhong " +
        "    LEFT JOIN LoaiPhong lp ON lp.maLoaiPhong = p.maLoaiPhong " +
        "    WHERE cthd.maPhong = ? " +
        "    GROUP BY lp.giaThuePhong " +
        ") " +
        "WHERE maPhong = ?";

    try (Connection con = ConnectDB.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(queryGiaDatHang)) {

        ps.setString(1, maPhong);
        ps.setString(2, maPhong);

        ps.executeUpdate();
        System.out.println("Successfully updated giaDatHang.");
    } catch (SQLException ex) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error in capNhatGiaDatHang", ex);
    }
}



public void capNhatTongTien(String maPhong) throws SQLException {
    String queryTongTien = 
        "UPDATE HoaDon " +
        "SET tongTien = ( " +
        "    SELECT SUM(cthd.giaDatHang) " +
        "    FROM ChiTietHoaDon cthd " +
        "    WHERE cthd.maHoaDon = ( " +
        "        SELECT maHoaDon FROM ChiTietHoaDon WHERE maPhong = ? " +
        "    )" +
        ") " +
        "WHERE maHoaDon = ( " +
        "    SELECT maHoaDon FROM ChiTietHoaDon WHERE maPhong = ? " +
        ")";

    try (Connection con = ConnectDB.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(queryTongTien)) {

        ps.setString(1, maPhong);
        ps.setString(2, maPhong);

        ps.executeUpdate();
        System.out.println("Successfully updated tongTien.");
    } catch (SQLException ex) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error in capNhatTongTien", ex);
    }
}
public boolean kiemtraTrungDatPhong(HoaDon hd, int ngayGiaHan) {
    List<String> dsMaPhong = new ArrayList<>();

    // Lấy danh sách mã phòng trong hóa đơn `hd`
    String sqlMaPhong = "SELECT p.maPhong " +
                        "FROM Phong p " +
                        "JOIN ChiTietHoaDon ct ON ct.maPhong = p.maPhong " +
                        "JOIN HoaDon hd ON hd.maHoaDon = ct.maHoaDon " +
                        "WHERE hd.maHoaDon = ?";
    try (Connection con = ConnectDB.getInstance().getConnection();
         PreparedStatement preparedStatement = con.prepareStatement(sqlMaPhong)) {

        preparedStatement.setString(1, hd.getMaHoaDon());
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                dsMaPhong.add(resultSet.getString("maPhong"));
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        return false; // Nếu xảy ra lỗi, trả về false
    }

    // Kiểm tra trùng lịch đặt phòng
    String sqlKiemTra = "SELECT h.checkIn, h.checkOut " +
                        "FROM HoaDon h " +
                        "JOIN ChiTietHoaDon ct ON ct.maHoaDon = h.maHoaDon " +
                        "WHERE ct.maPhong = ? AND h.maHoaDon != ? AND h.trangThai = 0 " +
                        "AND (h.checkOut >= ? AND h.checkIn <= ?)";
    try (Connection con = ConnectDB.getInstance().getConnection();
         PreparedStatement preparedStatement = con.prepareStatement(sqlKiemTra)) {

        for (String maPhong : dsMaPhong) {
            preparedStatement.setString(1, maPhong);
            preparedStatement.setString(2, hd.getMaHoaDon());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(hd.getCheckIn()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(hd.getCheckOut().plusDays(ngayGiaHan)));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true; // Nếu có trùng lịch
                }
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        return false; // Nếu xảy ra lỗi, trả về false
    }

    return false; // Không có trùng lịch
}
	public boolean capNhatHoaDonCheckOut(HoaDon hd, LocalDateTime checkOutMoi) {
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        PreparedStatement ps = con.prepareStatement(
	                "UPDATE HoaDon SET checkOut = ? WHERE maHoaDon = ?");
	        
	        // Đặt giá trị checkOutMoi và maHoaDon
	        ps.setTimestamp(1, Timestamp.valueOf(checkOutMoi)); // Chuyển LocalDateTime sang Timestamp
	        ps.setString(2, hd.getMaHoaDon()); // Mã hóa đơn cần cập nhật
	
	        // Thực thi truy vấn
	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0; // Trả về true nếu có ít nhất 1 dòng được cập nhật
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false; // Trả về false nếu có lỗi xảy ra
	}










		
	
	

}
