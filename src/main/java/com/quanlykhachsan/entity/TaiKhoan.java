package com.quanlykhachsan.entity;

import com.quanlykhachsan.enum_Class.TrangThaiTaiKhoan;
import java.util.Objects;


public class TaiKhoan {
	private String tenTaiKhoan;
	private String matKhau;
	private TrangThaiTaiKhoan trangThai;
	private NhanVien nhanVien;
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaiKhoan(String tenTaiKhoan, String matKhau, TrangThaiTaiKhoan trangThai, NhanVien nhanVien) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.trangThai = trangThai;
		this.nhanVien = nhanVien;
	}
	public TaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = "1111";
		this.trangThai = TrangThaiTaiKhoan.KHONG_HOAT_DONG;
		this.nhanVien = null;
	}
	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}
	public void setTenTaiKhoan(String tenTaiKhoan) {
	    // Kiểm tra không được null và không rỗng
	    if (tenTaiKhoan == null || tenTaiKhoan.trim().isEmpty()) {
	        throw new IllegalArgumentException("Tên tài khoản không được null hoặc trống.");
	    }
	    
	    // Kiểm tra xem tên tài khoản có giống với số điện thoại không
	    if (!this.nhanVien.getSoDienThoai().equals(tenTaiKhoan)) {
	        throw new IllegalArgumentException("Tên tài khoản phải giống với số điện thoại của nhân viên.");
	    }
	    
	    // Gán giá trị nếu tất cả điều kiện đều thỏa mãn
	    this.tenTaiKhoan = tenTaiKhoan;
	}


	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
	    // Kiểm tra không được null
	    if (matKhau == null||matKhau.isEmpty()) {
	        throw new IllegalArgumentException("Mật khẩu không được để trống.");
	    }
	    
	    // Kiểm tra độ dài mật khẩu
	    if (matKhau.length() < 10) {
	        throw new IllegalArgumentException("Mật khẩu phải có ít nhất 10 ký tự.");
	    }
	    
	    // Kiểm tra có ít nhất một chữ cái in hoa, một chữ số, và một ký tự đặc biệt
	    if (!matKhau.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$")) {
	        throw new IllegalArgumentException("Mật khẩu phải có ít nhất một chữ cái in hoa, một chữ số, và một ký tự đặc biệt.");
	    }
	    
	    // Gán giá trị nếu hợp lệ
	    this.matKhau = matKhau;
	}

	public TrangThaiTaiKhoan getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(TrangThaiTaiKhoan trangThai) {
		this.trangThai = trangThai;
	}
	
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(tenTaiKhoan);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(tenTaiKhoan, other.tenTaiKhoan);
	}
	
	
	
}
