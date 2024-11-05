package com.quanlykhachsan.entity;

import java.time.LocalDate;
import java.util.Objects;

public class ChiTietHoaDon {
	private String maChiTietHoaDon;
        private Phong maPhong;
	private LocalDate ngayLapHoaDon;
	private double giaDatPhong;
        private HoaDon maHoaDon;

    public ChiTietHoaDon(String maChiTietHoaDon, Phong maPhong, LocalDate ngayLapHoaDon, double giaDatPhong, HoaDon maHoaDon) {
        this.maChiTietHoaDon = maChiTietHoaDon;
        this.maPhong = maPhong;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.giaDatPhong = giaDatPhong;
        this.maHoaDon = maHoaDon;
    }
	
	public ChiTietHoaDon() {
		super();
	}
	public ChiTietHoaDon(String maChiTietHoaDon) {
		this(maChiTietHoaDon  , new Phong(),LocalDate.now(),0.0, new HoaDon());
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

    public Phong getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(Phong maPhong) {
        this.maPhong = maPhong;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "maChiTietHoaDon=" + maChiTietHoaDon + ", maPhong=" + maPhong + ", ngayLapHoaDon=" + ngayLapHoaDon + ", giaDatPhong=" + giaDatPhong + ", maHoaDon=" + maHoaDon + '}';
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
