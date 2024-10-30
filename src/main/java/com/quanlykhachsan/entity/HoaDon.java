package com.quanlykhachsan.entity;

<<<<<<< HEAD
import com.quanlykhachsan.entity.Voucher;
=======
>>>>>>> 419024f766aed4c99926820b34f384cbdfd8a518
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class HoaDon {
	private String maHoaDon;
	private LocalDate thoiGianLapHoaDon;
	private NhanVien nhanVien;
	private Voucher voucher;
	private KhachHang khachHang;
	private ChiTietHoaDon chiTietHoaDon;
	private double VAT;
	private boolean trangThai;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private double tienCoc;
	private double tienPhat;
	private double tongTien;
	public HoaDon() {
		super();
	}

	public HoaDon(String maHoaDon, LocalDate thoiGianLapHoaDon, NhanVien nhanVien, Voucher voucher, KhachHang khachHang,
			ChiTietHoaDon chiTiet, double vAT, boolean trangThai, LocalDate checkIn,
			LocalDate checkOut, double tienCoc, double tienPhat, double tongTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.thoiGianLapHoaDon = thoiGianLapHoaDon;
		this.nhanVien = nhanVien;
		this.voucher = voucher;
		this.khachHang = khachHang;
		this.chiTietHoaDon= chiTiet;
		VAT = vAT;
		this.trangThai = trangThai;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.tienCoc = tienCoc;
		this.tienPhat = tienPhat;
		this.tongTien = tongTien;
	}

	public HoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
		this.thoiGianLapHoaDon = null;
		this.nhanVien = null;
		this.voucher = null;
		this.khachHang = null;
		this.chiTietHoaDon = null;
		this.VAT = 0.0;
		this.trangThai = true;
		this.checkIn = null;
		this.checkOut = null;
		this.tienCoc = 0.0;
		this.tienPhat = 0.0;
		this.tongTien = 0.0;
	}
	
	

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public LocalDate getThoiGianLapHoaDon() {
		return thoiGianLapHoaDon;
	}

	public void setThoiGianLapHoaDon(LocalDate thoiGianLapHoaDon) {
		this.thoiGianLapHoaDon = thoiGianLapHoaDon;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

    public ChiTietHoaDon getChiTietHoaDon() {
        return chiTietHoaDon;
    }

    public void setChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        this.chiTietHoaDon = chiTietHoaDon;
    }

	

	public double getVAT() {
		return VAT;
	}

	public void setVAT(Double vAT) {
	    if (vAT == null||Double.toString(VAT).isEmpty()) {
	        throw new IllegalArgumentException("VAT không được để trống.");
	    }

	    // Kiểm tra VAT phải lớn hơn 0 và nhỏ hơn 1
	    if (vAT < 0 || vAT > 1) {
	        throw new IllegalArgumentException("VAT phải lớn hơn 0 và nhỏ hơn 1.");
	    }

	    // Gán giá trị nếu hợp lệ
	    this.VAT = vAT;
	}


	public boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
	    // Kiểm tra checkIn không được null
	    if (checkIn == null) {
	        throw new IllegalArgumentException("Ngày check-in không được null.");
	    }

	    // Kiểm tra checkIn phải lớn hơn hoặc bằng ngày hiện tại
	    if (checkIn.isBefore(LocalDate.now())) {
	        throw new IllegalArgumentException("Ngày check-in phải lớn hơn hoặc bằng ngày hiện tại.");
	    }

	    this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
	    // Kiểm tra checkOut không được null
	    if (checkOut == null) {
	        throw new IllegalArgumentException("Ngày check-out không được null.");
	    }

	    // Kiểm tra checkIn đã được thiết lập trước khi so sánh với checkOut
	    if (this.checkIn == null) {
	        throw new IllegalStateException("Ngày check-in chưa được thiết lập. Không thể thiết lập ngày check-out.");
	    }

	    // Kiểm tra checkOut phải lớn hơn hoặc bằng checkIn
	    if (checkOut.isBefore(this.checkIn)) {
	        throw new IllegalArgumentException("Ngày check-out phải lớn hơn hoặc bằng ngày check-in.");
	    }

	    // Gán giá trị nếu hợp lệ
	    this.checkOut = checkOut;
	}

	public double getTienCoc() {
		return tienCoc;
	}

	public void setTienCoc(Double tienCoc) {
	    if (tienCoc < 0) {
	        throw new IllegalArgumentException("Tiền cọc phải lớn hơn hoặc bằng 0.");
	    }
	    this.tienCoc = tienCoc;
	}


	public double getTienPhat() {
		return tienPhat;
	}

	public void setTienPhat(Double tienPhat) {
		if (tienPhat < 0) {
	        throw new IllegalArgumentException("Tiền Phạt phải lớn hơn hoặc bằng 0.");
	    }
	    this.tienPhat = tienPhat;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", thoiGianLapHoaDon=" + thoiGianLapHoaDon + ", nhanVien=" + nhanVien.getMaNhanVien() + ", voucher=" + voucher + ", khachHang=" + khachHang + ", chiTietHoaDon=" + chiTietHoaDon.getMaChiTietHoaDon() + ", VAT=" + VAT + ", trangThai=" + trangThai + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", tienCoc=" + tienCoc + ", tienPhat=" + tienPhat + ", tongTien=" + tongTien + '}';
    }
        

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}
	
	

}
