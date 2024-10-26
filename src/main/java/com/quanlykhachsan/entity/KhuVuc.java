package entity;

import java.util.Objects;

public class KhuVuc {
	private String maKhuVuc;
	private String tenKhuVuc;
	private String moTa;
	public KhuVuc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KhuVuc(String maKhuVuc, String tenKhuVuc, String moTa) {
		super();
		this.maKhuVuc = maKhuVuc;
		this.tenKhuVuc = tenKhuVuc;
		this.moTa = moTa;
	}
	public KhuVuc(String maKhuVuc) {
		super();
		this.maKhuVuc = maKhuVuc;
		this.tenKhuVuc = "";
		this.moTa = "";
	}
	public String getMaKhuVuc() {
		return maKhuVuc;
	}
	public void setMaKhuVuc(String maKhuVuc) {
	    if (maKhuVuc == null || maKhuVuc.trim().isEmpty()) {
	        throw new IllegalArgumentException("Mã không rỗng và phải theo dạng KX với X là các chữ cái viết in (A, B, C, D).");
	    }
	    
	    // Kiểm tra nếu mã khu vực không theo dạng "KX" với X là A, B, C, hoặc D
	    if (!maKhuVuc.matches("K[A-D]")) {
	        throw new IllegalArgumentException("Mã không rỗng và phải theo dạng KX với X là các chữ cái viết in (A, B, C, D).");
	    }
	    
	    this.maKhuVuc = maKhuVuc;
	}

	public String getTenKhuVuc() {
		return tenKhuVuc;
	}
	public void setTenKhuVuc(String tenKhuVuc) {
	    if (tenKhuVuc == null || tenKhuVuc.trim().isEmpty()) {
	        throw new IllegalArgumentException("Tên khu vực không được để trống.");
	    }
	    
	    // Kiểm tra nếu tên khu vực chứa ký số hoặc ký tự đặc biệt
	    if (!tenKhuVuc.matches("[A-Z][a-z]*(\\s[A-Z][a-z]*)*")) {
	        throw new IllegalArgumentException("Tên khu vực không chứa ký số, ký tự đặc biệt, chữ đầu phải viết hoa, và giữa các từ có 1 dấu cách.");
	    }
	    
	    this.tenKhuVuc = tenKhuVuc;
	}

	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
	    if (moTa == null || moTa.trim().isEmpty()) {
	        throw new IllegalArgumentException("Mô tả không được để trống.");
	    }
	    this.moTa = moTa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKhuVuc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhuVuc other = (KhuVuc) obj;
		return Objects.equals(maKhuVuc, other.maKhuVuc);
	}
	
	
}
