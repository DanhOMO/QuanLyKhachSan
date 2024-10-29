package com.quanlykhachsan.entity;

import java.util.Objects;

public class LoaiPhong {
	private String maLoaiPhong;
	private String tenLoaiPhong;
	private String moTa;
	private int soLuongNguoi;
	private double giaThuePhong;
	public LoaiPhong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, String moTa, int soLuongNguoi, double giaThuePhong) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = tenLoaiPhong;
		this.moTa = moTa;
		this.soLuongNguoi = soLuongNguoi;
		this.giaThuePhong = giaThuePhong;
	}
	public LoaiPhong(String maLoaiPhong) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = "";
		this.moTa = "";
		this.soLuongNguoi = 1;
		this.giaThuePhong = 0.0f;
	}
	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}
	public void setMaLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}
	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}
	public void setTenLoaiPhong(String tenLoaiPhong) {
		if (tenLoaiPhong == null||tenLoaiPhong.isEmpty()) {
	        throw new IllegalArgumentException("Tên loại phòng không được để trống.");
	    }

	    // Kiểm tra định dạng của tên khách hàng
	    if (!tenLoaiPhong.matches("^[A-Z][a-z]*( [A-Z][a-z]*)*$")) {
	        throw new IllegalArgumentException("Tên loại phòng không chứa số, ký tự đặc biệt, phải viết hoa chữ cái đầu của mỗi từ và cách nhau bởi một dấu cách.");
	    }

	    // Gán giá trị nếu hợp lệ
	    this.tenLoaiPhong = tenLoaiPhong;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
	    // Kiểm tra moTa không được null
	    if (moTa == null||moTa.isEmpty()) {
	        throw new IllegalArgumentException("Mô tả không được để trống.");
	    }

	    // Kiểm tra moTa không chứa ký tự đặc biệt
	    if (!moTa.matches("^[a-zA-Z0-9\\s]+$")) {
	        throw new IllegalArgumentException("Mô tả không được chứa ký tự đặc biệt.");
	    }

	    // Gán giá trị nếu hợp lệ
	    this.moTa = moTa;
	}

	public int getSoLuongNguoi() {
		return soLuongNguoi;
	}
	public void setSoLuongNguoi(int soLuongNguoi) {
	    // Kiểm tra soLuongNguoi không được null
	    if (Integer.toString(soLuongNguoi).isEmpty()) {
	        throw new IllegalArgumentException("Số lượng người không được để trống.");
	    }

	    // Kiểm tra soLuongNguoi phải > 0 và <= 10
	    if (soLuongNguoi <= 0 || soLuongNguoi > 10) {
	        throw new IllegalArgumentException("Số lượng người phải nằm trong khoảng từ 0 đến 10.");
	    }

	    // Gán giá trị nếu hợp lệ
	    this.soLuongNguoi = soLuongNguoi;
	}

	public double getGiaThuePhong() {
		return giaThuePhong;
	}
	public void setGiaThuePhong(double giaThuePhong) {
	    // Kiểm tra giaThuePhong không được null
	    if (Double.toString(giaThuePhong).isEmpty()||Double.toString(giaThuePhong) == null) {
	        throw new IllegalArgumentException("Giá thuê phòng không được để trống.");
	    }

	    // Kiểm tra giaThuePhong phải lớn hơn 0
	    if (giaThuePhong <= 0) {
	        throw new IllegalArgumentException("Giá thuê phòng phải lớn hơn 0.");
	    }

	    // Gán giá trị nếu hợp lệ
	    this.giaThuePhong = giaThuePhong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoaiPhong);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiPhong other = (LoaiPhong) obj;
		return Objects.equals(maLoaiPhong, other.maLoaiPhong);
	}

    @Override
    public String toString() {
        return "LoaiPhong{" + "maLoaiPhong=" + maLoaiPhong + ", tenLoaiPhong=" + tenLoaiPhong + ", moTa=" + moTa + ", soLuongNguoi=" + soLuongNguoi + ", giaThuePhong=" + giaThuePhong + '}';
    }
	
}
