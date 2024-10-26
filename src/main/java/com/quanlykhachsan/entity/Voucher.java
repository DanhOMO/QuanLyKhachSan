package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Voucher {
	private String maVoucher;
	private String tenVoucher;
	private double giamGia;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;	
	public Voucher(String maVoucher,String tenVoucher, double giamGia, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        this.tenVoucher = tenVoucher;
        this.giamGia = giamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.maVoucher = maVoucher;
    }
	public Voucher(String maVoucher) {
	    this(maVoucher, "", 0, null, null);
	}
	public Voucher() {
		super();
	}
	public String getTenVoucher() {
		return tenVoucher;
	}
	public void setTenVoucher(String tenVoucher) {
	    // Kiểm tra không được null
	    if (tenVoucher == null||tenVoucher.isEmpty()) {
	        throw new IllegalArgumentException("Tên voucher không được để trống.");
	    }

	    // Kiểm tra định dạng của tên voucher
	    if (!tenVoucher.matches("^[A-Z][a-z]*( [A-Z][a-z]*)*$")) {
	        throw new IllegalArgumentException("Tên voucher không chứa số, ký tự đặc biệt, phải viết hoa chữ cái đầu của mỗi từ và cách nhau bởi một dấu cách.");
	    }

	    // Gán giá trị nếu hợp lệ
	    this.tenVoucher = tenVoucher;
	}

	public double getGiamGia() {
		return giamGia;
	}
	public void setGiamGia(double giamGia) {
		if (Double.toString(giamGia).isEmpty()|| Double.toString(giamGia) == null) {
	        throw new IllegalArgumentException("Giảm giá không được trống.");
	    }
		
		if (giamGia < 0 || giamGia > 1) {
	        throw new IllegalArgumentException("Giảm giá phải lớn hơn 0 và nhỏ hơn 1.");
	    }
	    
	    this.giamGia = giamGia;
	}

	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
	    // Kiểm tra ngày bắt đầu phải không được null
	    if (ngayBatDau == null) {
	        throw new IllegalArgumentException("Ngày bắt đầu không được null.");
	    }
	    
	    // Kiểm tra ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại
	    if (ngayBatDau.isBefore(LocalDate.now())) {
	        throw new IllegalArgumentException("Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại.");
	    }
	    
	    // Gán giá trị nếu hợp lệ
	    this.ngayBatDau = ngayBatDau;
	}

	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		if (ngayKetThuc == null) {
	        throw new IllegalArgumentException("Ngày kết thúc không được null.");
	    }
	    
	    // Kiểm tra ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu
	    if (ngayKetThuc.isBefore(this.ngayBatDau)) {
	        throw new IllegalArgumentException("Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu.");
	    }
	    this.ngayKetThuc = ngayKetThuc;
	}
	public String getMaVoucher() {
		return maVoucher;
	}
	
	
	public void setMaVoucher(String maVoucher) {
		this.maVoucher = maVoucher;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maVoucher);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voucher other = (Voucher) obj;
		return Objects.equals(maVoucher, other.maVoucher);
	}
	
}
