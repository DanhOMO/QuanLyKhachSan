package com.quanlykhachsan.entity;

import com.quanlykhachsan.enum_Class.TrangThaiThietBi;
import java.util.Objects;


public class ThietBi {
	private String maThietBi;
	private String tenThietBi;
	private TrangThaiThietBi trangThai;
	public ThietBi() {
		super();
		// TODO Auto-generated constructor stub
	}
        public ThietBi(String ten, String ma){
            super();
            this.maThietBi =ma;
            this.tenThietBi = ten;
        }
	public ThietBi(String tenTaiKhoan, String matKhau, TrangThaiThietBi trangThai) {
		super();
		this.maThietBi = tenTaiKhoan;
		this.tenThietBi = matKhau;
		this.trangThai = trangThai;
	}
	public ThietBi(String tenTaiKhoan) {
		this.maThietBi = tenTaiKhoan;
		this.tenThietBi = "";
		this.trangThai = TrangThaiThietBi.KHONG_HOAT_DONG;
	}
	public String getMaThietBi() {
		return maThietBi;
	}
	public void setMaThietBi(String maThietBi) {
		this.maThietBi = maThietBi;
	}
	public String getTenThietBi() {
		return tenThietBi;
	}
	public void setTenThietBi(String tenThietBi) {
	    if (tenThietBi == null || tenThietBi.trim().isEmpty()) {
	        throw new IllegalArgumentException("Tên thiết bị không được để trống.");
	    }
	    
	    // Kiểm tra nếu tên thiết bị chứa ký số hoặc ký tự đặc biệt
	    if (!tenThietBi.matches("[A-Z][a-z]*(\\s[A-Z][a-z]*)*")) {
	        throw new IllegalArgumentException("Tên thiết bị phải bắt đầu bằng chữ hoa, không chứa ký số, ký tự đặc biệt, và chỉ có một dấu cách giữa các chữ.");
	    }
	    
	    this.tenThietBi = tenThietBi;
	}

	public TrangThaiThietBi getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(TrangThaiThietBi trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maThietBi);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThietBi other = (ThietBi) obj;
		return Objects.equals(maThietBi, other.maThietBi);
	}

    @Override
    public String toString() {
        return "ThietBi{" + "maThietBi=" + maThietBi + ", tenThietBi=" + tenThietBi + ", trangThai=" + trangThai + '}';
    }
	
	
}
