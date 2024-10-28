package com.quanlykhachsan.entity;

import java.util.Objects;

public class DichVu {
	private String maDichVu;
	private String tenDichVu;
	private String moTa;
	private double giaDichVu;
	public DichVu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DichVu(String maKhuVuc, String tenKhuVuc, String moTa,double giaDichVu) {
		super();
		this.maDichVu = maKhuVuc;
		this.tenDichVu = tenKhuVuc;
		this.moTa = moTa;
		this.giaDichVu = giaDichVu;
	}
	public DichVu(String maKhuVuc) {
		super();
		this.maDichVu = maKhuVuc;
		this.tenDichVu = "";
		this.moTa = "";
		this.giaDichVu = 0.0;
	}
	public String getMaDichVu() {
		return maDichVu;
	}
	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}
	public String getTenDichVu() {
		return tenDichVu;
	}
	public void setTenDichVu(String tenDichVu) {
	    if (tenDichVu == null || tenDichVu.trim().isEmpty()) {
	        throw new IllegalArgumentException("Tên dịch vụ không được rỗng.");
	    }

	    // Kiểm tra chữ đầu phải viết hoa và không chứa ký số, ký tự đặc biệt
	    if (!tenDichVu.matches("^[A-Z][a-zA-Z ]+$")) {
	        throw new IllegalArgumentException("Tên dịch vụ không hợp lệ. Chỉ chứa chữ cái và khoảng trắng, bắt đầu bằng chữ hoa.");
	    }

	    // Kiểm tra khoảng trắng chỉ có một khoảng trắng giữa các từ
	    if (tenDichVu.contains("  ")) {
	        throw new IllegalArgumentException("Tên dịch vụ không hợp lệ. Không được có nhiều hơn 1 khoảng trắng giữa các từ.");
	    }

	    this.tenDichVu = tenDichVu;
	}

	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
	    if (moTa == null || moTa.trim().isEmpty()) {
	        throw new IllegalArgumentException("Mô tả không được rỗng hoặc NULL.");
	    }

	    // Kiểm tra mô tả không chứa ký tự đặc biệt
	    if (!moTa.matches("^[a-zA-Z0-9 ]+$")) {
	        throw new IllegalArgumentException("Mô tả không được chứa ký tự đặc biệt.");
	    }

	    this.moTa = moTa;
	}

	public double getGiaDichVu() {
		return giaDichVu;
	}
	public void setGiaDichVu(double giaDichVu) {
		if(Double.toString(giaDichVu).isEmpty()||Double.toString(giaDichVu)==null)
			throw new IllegalArgumentException("Giá dịch vụ không được để trống.");
		if(giaDichVu <= 0 )
			throw new IllegalArgumentException("Giá dịch vụ phải > 0");
		this.giaDichVu = giaDichVu;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maDichVu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(maDichVu, other.maDichVu);
	}
	
	
}
