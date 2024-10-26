package com.quanlykhachsan.entity;

import com.quanlykhachsan.enum_Class.GioiTinh;
import java.time.LocalDate;
import java.util.Objects;


public class KhachHang {
	private String maKhachHang;
	private String tenKhachHang;
	private String soDienThoai;
	private GioiTinh gioiTinh;
	private String diaChi;
	private LocalDate ngaySinh;
	private String CCCD;
	private String email;
	public KhachHang(String maKhachHang ,String tenKhachHang, String soDienThoai, GioiTinh gioiTinh, String diaChi,
			LocalDate ngaySinh, String cCCD, String email) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		CCCD = cCCD;
		this.email = email;
		
	
	}
	public KhachHang() {
		this.maKhachHang = "";
		this.tenKhachHang = "";
		this.soDienThoai = "";
		this.gioiTinh = GioiTinh.NAM;
		this.diaChi = "";
		this.ngaySinh = LocalDate.now();
		CCCD = "";
		this.email = "";
	}
	public KhachHang(String maKhachHang) {
		this("","","",GioiTinh.NAM,"",LocalDate.now(),"","");
	}
	
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
	    if (tenKhachHang == null||tenKhachHang.isEmpty()) {
	        throw new IllegalArgumentException("Tên khách hàng không được để trống.");
	    }

	    // Kiểm tra định dạng của tên khách hàng
	    if (!tenKhachHang.matches("^[A-Z][a-z]*( [A-Z][a-z]*)*$")) {
	        throw new IllegalArgumentException("Tên khách hàng không chứa số, ký tự đặc biệt, phải viết hoa chữ cái đầu của mỗi từ và cách nhau bởi một dấu cách.");
	    }

	    // Gán giá trị nếu hợp lệ
	    this.tenKhachHang = tenKhachHang;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
	    // Kiểm tra nếu số điện thoại bị trống
	    if (soDienThoai == null || soDienThoai.isEmpty()) {
	        throw new IllegalArgumentException("Số điện thoại không được để trống.");
	    }
	    
	    // Kiểm tra nếu số điện thoại không đúng 10 chữ số
	    if (!soDienThoai.matches("\\d{10}")) {
	        throw new IllegalArgumentException("Số điện thoại phải gồm 10 chữ số.");
	    }
	    
	    // Gán giá trị nếu hợp lệ
	    this.soDienThoai = soDienThoai;
	}
	public GioiTinh getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(GioiTinh gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
	    // Kiểm tra nếu địa chỉ không được vượt quá 255 ký tự
	    if (diaChi != null && diaChi.length() <= 255) {
	        this.diaChi = diaChi;
	    } else {
	        throw new IllegalArgumentException("Địa chỉ không được vượt quá 255 ký tự.");
	    }
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getCCCD() {
		return CCCD;
	}
	public void setCCCD(String cCCD) {
	    // Kiểm tra không được null hoặc rỗng
	    if (cCCD == null || cCCD.trim().isEmpty()) {
	        throw new IllegalArgumentException("CCCD không được trống.");
	    }

	    // Kiểm tra định dạng: phải là 12 chữ số
	    if (!cCCD.matches("^\\d{12}$")) {
	        throw new IllegalArgumentException("CCCD phải gồm 12 chữ số.");
	    }

	    // Gán giá trị nếu hợp lệ
	    this.CCCD = cCCD;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
	    // Kiểm tra email không được null
	    if (email == null||email.isEmpty()) {
	        throw new IllegalArgumentException("Email không được để trống.");
	    }
	    
	    // Kiểm tra định dạng email và đuôi @gmail.com
	    if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
	        throw new IllegalArgumentException("Email phải có định dạng hợp lệ và kết thúc bằng @gmail.com.");
	    }
	    
	    // Gán giá trị email nếu hợp lệ
	    this.email = email;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	
	private int tinhTuoi() {
		int namSinh = ngaySinh.getYear();
		int namHienTai = LocalDate.now().getYear();
		return namHienTai - namSinh;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maKhachHang);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKhachHang, other.maKhachHang);
	}
	
	
}
