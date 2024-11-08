package com.quanlykhachsan.entity;

import com.quanlykhachsan.enum_Class.TrangThaiPhong;
import com.quanlykhachsan.entity.KhuVuc;
import com.quanlykhachsan.entity.LoaiPhong;
import java.util.Objects;


public class Phong {
	private String maPhong;	
	private String tenPhong;
	private TrangThaiPhong trangThai;	
	private LoaiPhong loaiPhong;
	private KhuVuc khuVuc;
	
	public Phong() {
		super();
	}
	
	
	
	public Phong(String maPhong, String tenPhong, TrangThaiPhong trangThai, LoaiPhong loaiPhong, KhuVuc khuVuc) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.trangThai = trangThai;
		this.loaiPhong = loaiPhong;
		this.khuVuc = khuVuc;
	}
	public Phong(String maPhong) {
		this(maPhong,"",TrangThaiPhong.TRONG,null,null);
	}


	public String getMaPhong() {
		return maPhong;
	}



	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}



	public String getTenPhong() {
		return tenPhong;
	}



	public void setTenPhong(String tenPhong) {
	    if (tenPhong == null || tenPhong.trim().isEmpty()) {
	        throw new IllegalArgumentException("Tên phòng không được để trống.");
	    }
	    
	    // Kiểm tra nếu tên phòng chứa ký số hoặc ký tự đặc biệt
//	    if (!tenPhong.matches("[A-Z][a-z]*(\\s[A-Z][a-z]*)*")) {
//	        throw new IllegalArgumentException("Tên phòng phải bắt đầu bằng chữ hoa, không chứa ký số, ký tự đặc biệt, và chỉ có một dấu cách giữa các chữ.");
//	    }
	    
	    this.tenPhong = tenPhong;
	}




	public TrangThaiPhong getTrangThai() {
		return trangThai;
	}



	public void setTrangThai(TrangThaiPhong trangThai) {
		this.trangThai = trangThai;
	}



	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}



	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}



	public KhuVuc getKhuVuc() {
		return khuVuc;
	}



	public void setKhuVuc(KhuVuc khuVuc) {
		this.khuVuc = khuVuc;
	}



	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

//    @Override
//    public String toString() {
//        return "Phong{" + "maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", trangThai=" + trangThai.getTrangThaiPhong() + ", loaiPhong=" + loaiPhong.getMaLoaiPhong() + ", khuVuc=" + khuVuc.getMaKhuVuc() + '}';
//    }
        
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

    public void setTrangThaiPhong(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
	
	
}
