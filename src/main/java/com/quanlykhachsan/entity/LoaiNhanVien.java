package entity;

import java.util.Objects;

public class LoaiNhanVien {
	private String maLoaiNhanVien;
	private String tenLoaiNhanVien;
	public LoaiNhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiNhanVien(String maLoaiNhanVien, String tenLoaiNhanVien) {
		super();
		this.maLoaiNhanVien = maLoaiNhanVien;
		this.tenLoaiNhanVien = tenLoaiNhanVien;
	}
	public LoaiNhanVien(String maLoaiNhanVien) {
		super();
		this.maLoaiNhanVien = maLoaiNhanVien;
		this.tenLoaiNhanVien = "";
	}
	public String getMaLoaiNhanVien() {
		return maLoaiNhanVien;
	}
	public void setMaLoaiNhanVien(String maLoaiNhanVien) {
		this.maLoaiNhanVien = maLoaiNhanVien;
	}
	public String getTenLoaiNhanVien() {
		return tenLoaiNhanVien;
	}
	public void setTenLoaiNhanVien(String tenLoaiNhanVien) {
	    // Kiểm tra không được null và không trống
	    if (tenLoaiNhanVien == null || tenLoaiNhanVien.trim().isEmpty()) {
	        throw new IllegalArgumentException("Tên loại nhân viên không được null và không được để trống.");
	    }
	    
	    // Kiểm tra định dạng của tên loại nhân viên
	    if (!tenLoaiNhanVien.matches("^[A-Z][a-z]+( [A-Z][a-z]+)*$")) {
	        throw new IllegalArgumentException("Tên loại nhân viên không chứa số, ký tự đặc biệt, phải viết hoa chữ cái đầu của mỗi từ và cách nhau bởi một dấu cách.");
	    }
	    
	    // Gán giá trị nếu hợp lệ
	    this.tenLoaiNhanVien = tenLoaiNhanVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoaiNhanVien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiNhanVien other = (LoaiNhanVien) obj;
		return Objects.equals(maLoaiNhanVien, other.maLoaiNhanVien);
	}
	
}
