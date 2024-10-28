package com.quanlykhachsan.entity;
import java.time.LocalDate;
import java.util.Objects;

public class LichSuDatDichVu {
	
	private ChiTietHoaDon chiTietHoaDon;
	private DichVu dichVu;
	private LocalDate thoiGianDatDichVu;
	private int soLuong;
	
	public LichSuDatDichVu(ChiTietHoaDon chiTietHoaDon, DichVu dichVu, LocalDate thoiGianDatDichVu, int soLuong) {
		super();
		this.chiTietHoaDon = chiTietHoaDon;
		this.dichVu = dichVu;
		this.thoiGianDatDichVu = thoiGianDatDichVu;
		this.soLuong = soLuong;
	}

	public LichSuDatDichVu() {
		super();
	}
	public LichSuDatDichVu(ChiTietHoaDon chiTietHoaDon, DichVu dichVu) {
		this(chiTietHoaDon, dichVu, LocalDate.now(),0);
	}

	public ChiTietHoaDon getChiTietHoaDon() {
		return chiTietHoaDon;
	}

	public void setChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		this.chiTietHoaDon = chiTietHoaDon;
	}

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public LocalDate getThoiGianDatDichVu() {
		return thoiGianDatDichVu;
	}

	public void setThoiGianDatDichVu(LocalDate thoiGianDatDichVu) {
		this.thoiGianDatDichVu = thoiGianDatDichVu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chiTietHoaDon, dichVu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LichSuDatDichVu other = (LichSuDatDichVu) obj;
		return Objects.equals(chiTietHoaDon, other.chiTietHoaDon) && Objects.equals(dichVu, other.dichVu);
	}
	
	
}
