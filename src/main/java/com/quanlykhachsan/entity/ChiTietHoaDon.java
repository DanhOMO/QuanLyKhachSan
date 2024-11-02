package com.quanlykhachsan.entity;

import java.time.LocalDate;
import java.util.Objects;

public class ChiTietHoaDon {
	private String maChiTietHoaDon;
	private LocalDate ngayLapHoaDon;
	private double giaDatPhong;
    private HoaDon maHoaDon;
	public ChiTietHoaDon(String maChiTietHoaDon, LocalDate ngayLapHoaDon, double giaDatPhong, HoaDon maHoaDon) {
		super();
		this.maChiTietHoaDon = maChiTietHoaDon;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.giaDatPhong = giaDatPhong;
                this.maHoaDon = maHoaDon;
	}
	
	public ChiTietHoaDon() {
		super();
	}
	public ChiTietHoaDon(String maChiTietHoaDon) {
		this(maChiTietHoaDon,LocalDate.now(),0.0, new HoaDon());
	}

	public String getMaChiTietHoaDon() {
		return maChiTietHoaDon;
	}

	public void setMaChiTietHoaDon(String maChiTietHoaDon) {
		this.maChiTietHoaDon = maChiTietHoaDon;
	}

	public LocalDate getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(LocalDate ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}

	public double getGiaDatPhong() {
		return giaDatPhong;
	}

	public void setGiaDatPhong(double giaDatPhong) {
		this.giaDatPhong = giaDatPhong;
	}

    public HoaDon getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(HoaDon maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
        
	@Override
	public int hashCode() {
		return Objects.hash(maChiTietHoaDon);
	}

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "maChiTietHoaDon=" + maChiTietHoaDon + ", ngayLapHoaDon=" + ngayLapHoaDon + ", giaDatPhong=" + giaDatPhong + ", maHoaDon=" + maHoaDon.getMaHoaDon() + '}';
    }
        

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		return Objects.equals(maChiTietHoaDon, other.maChiTietHoaDon);
	}
	
	
}
