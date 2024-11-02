package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.ChiTietHoaDon;
import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.model.ConnectDB;
import com.quanlykhachsan.entity.Voucher;
import java.lang.reflect.Array;
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
import java.sql.Date;
import java.time.LocalDate;

public class HoaDon_DAO {

    public static void main(String[] args) {
        try {
            ConnectDB con = new ConnectDB();
            con.connect();
            HoaDon_DAO a = new HoaDon_DAO();
            a.layDanhSachHoaDon().forEach(x -> System.out.println(x));
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<HoaDon> ca = new ArrayList<>();
    private KhachHang_DAO kh_dao = new KhachHang_DAO();
    private ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
    private Voucher_DAO voucherDao = new Voucher_DAO();

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

    public List<HoaDon> timTheoMaPhong(String maPhong) {
        List<HoaDon> hoaDonsTheoPhong = new ArrayList<>();// Danh sách mới cho mỗi phòng

        String sql = "select hd.*  "
                + "from Phong p  "
                + "join LichSuDatPhong lp on p.maPhong = lp.maPhong "
                + "join ChiTietHoaDon ct on ct.maChiTietHoaDon = lp.maChiTietHoaDon "
                + "join HoaDon hd on hd.maHoaDon = ct.maHoaDon "
                + "where p.maPhong = ?";

        try (Connection con = ConnectDB.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

    public ArrayList<HoaDon> layDanhSachHoaDon() {
        dsHoaDon = new ArrayList<HoaDon>();
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("select maHoaDon,maKhachHang,tongTien,maVoucher,trangThai from HoaDon");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("maHoaDon"));
                hd.setKhachHang(new KhachHang(rs.getString("maKhachHang")));
                hd.setTongTien(rs.getDouble("tongTien"));
                hd.setTrangThai(rs.getBoolean("trangThai"));
                Voucher temp = voucherDao.timKhuyenMai(rs.getString("maVoucher"));
                hd.setVoucher(temp);
                dsHoaDon.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

    public HoaDon timHoaDon(String maHoaDon) {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement("select * from HoaDon where maHoaDon = ?");
			ps.setString(1, maHoaDon);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(rs.getString("maHoaDon"));
				hd.setThoiGianLapHoaDon(rs.getDate("ngayLapHoaDon").toLocalDate());
				hd.setNhanVien(new NhanVien(rs.getString("maNhanVien")));
				hd.setVoucher(new Voucher(rs.getString("maVoucher")));
				hd.setKhachHang(new KhachHang(rs.getString("maKhachHang")));
				hd.setVAT(rs.getDouble("VAT"));
				hd.setTrangThai(rs.getBoolean("trangThai"));
				hd.setCheckIn(rs.getDate("checkIn").toLocalDate());
				hd.setCheckOut(rs.getDate("checkOut").toLocalDate());
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

}
