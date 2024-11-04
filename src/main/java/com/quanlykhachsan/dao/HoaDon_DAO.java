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
            System.out.println(hd.getCheckIn());
            System.out.println(hd.getCheckOut());
            ps.setString(1, hd.getMaHoaDon());
            ps.setDate(2, java.sql.Date.valueOf(hd.getThoiGianLapHoaDon()));
            ps.setString(3, hd.getNhanVien().getMaNhanVien());
            if (hd.getVoucher() == null) {
                ps.setString(4, null);
            } else {
                ps.setString(4, hd.getVoucher().getMaVoucher());
            }
            ps.setString(5, hd.getKhachHang().getMaKhachHang());
            ps.setDouble(6, hd.getVAT());
            ps.setBoolean(7, hd.getTrangThai());
            ps.setDate(8, java.sql.Date.valueOf(hd.getCheckIn()));
            ps.setDate(9, java.sql.Date.valueOf(hd.getCheckOut()));
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
                            rs.getDate("checkIn").toLocalDate(),
                            rs.getDate("checkOut").toLocalDate(),
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
}
