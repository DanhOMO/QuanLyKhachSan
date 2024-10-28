package com.quanlykhachsan.entity;

import java.time.LocalDate;
import java.util.Objects;

public class LichSuDatPhong {
	private ChiTietHoaDon chiTietHoaDon;
	private Phong phong;
	private int soLuong;
	private LocalDate thoiGianDatPhong;
	
	
	
	public LichSuDatPhong(ChiTietHoaDon chiTietHoaDon, Phong phong, int soLuong, LocalDate thoiGianDatPhong) {
		super();
		this.chiTietHoaDon = chiTietHoaDon;
		this.phong = phong;
		this.soLuong = soLuong;
		this.thoiGianDatPhong = thoiGianDatPhong;
	}

	public LichSuDatPhong() {
		super();
	}
	
	public LichSuDatPhong(ChiTietHoaDon chiTietHoaDon, Phong phong) {
		this(chiTietHoaDon,phong,0,LocalDate.now());
	}

	public ChiTietHoaDon getChiTietHoaDon() {
		return chiTietHoaDon;
	}

	public void setChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		this.chiTietHoaDon = chiTietHoaDon;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public LocalDate getThoiGianDatPhong() {
		return thoiGianDatPhong;
	}

	public void setThoiGianDatPhong(LocalDate thoiGianDatPhong) {
		this.thoiGianDatPhong = thoiGianDatPhong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chiTietHoaDon, phong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LichSuDatPhong other = (LichSuDatPhong) obj;
		return Objects.equals(chiTietHoaDon, other.chiTietHoaDon) && Objects.equals(phong, other.phong);
	}
	
	

}
