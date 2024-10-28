package com.quanlykhachsan.entity;

import com.quanlykhachsan.enum_Class.CaLamViec;
import com.quanlykhachsan.enum_Class.GioiTinh;
import com.quanlykhachsan.enum_Class.TrangThaiNhanVien;
import entity.LoaiNhanVien;
import java.time.LocalDate;
import java.util.Objects;


public class NhanVien {
	private String maNhanVien;
    private String tenNhanVien;
    private LoaiNhanVien loaiNhanVien;
    private GioiTinh gioiTinh;
    private LocalDate ngaySinh;
    private String soDienThoai;
    private String diaChi;
    private String email;
    private TrangThaiNhanVien trangThai;
    private CaLamViec caLamViec;  // Use enum here

    public NhanVien() {
        super();
    }

    public NhanVien(String maNhanVien, String tenNhanVien, String soDienThoai, GioiTinh gioiTinh, String diaChi,
                    LocalDate ngaySinh, String email, LoaiNhanVien loaiNhanVien, TrangThaiNhanVien trangThai, CaLamViec caLamViec) {
        super();
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.loaiNhanVien = loaiNhanVien;
        this.trangThai = trangThai;
        this.caLamViec = caLamViec;
    }

    public NhanVien(String maNhanVien) {
        this(maNhanVien, "", "", GioiTinh.NAM, "", null, "", null, TrangThaiNhanVien.DANG_LAM_VIEC, CaLamViec.CA_SANG);
    }

	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
	    if (tenNhanVien.matches("^[a-zA-Z\\s]+$")) {
	        this.tenNhanVien = tenNhanVien;
	    } else {
	        throw new IllegalArgumentException("Tên nhân viên không được chứa ký tự số hoặc ký tự đặc biệt.");
	    }
	}
	public LoaiNhanVien getLoaiNhanVien() {
		return loaiNhanVien;
	}
	public void setLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
		this.loaiNhanVien = loaiNhanVien;
	}
	public GioiTinh getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(GioiTinh gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
	    LocalDate today = LocalDate.now();
	    int age = today.getYear() - ngaySinh.getYear();
	    
	    // Kiểm tra nếu ngày sinh chưa đến trong năm hiện tại
	    if (ngaySinh.plusYears(age).isAfter(today)) {
	        age--;  // Giảm tuổi đi 1 nếu ngày sinh chưa xảy ra trong năm nay
	    }
	    
	    if (age >= 15) {
	        this.ngaySinh = ngaySinh;
	    } else {
	        throw new IllegalArgumentException("Nhân viên phải ít nhất 15 tuổi.");
	    }
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
	    // Kiểm tra nếu số điện thoại bị trống
	    if (soDienThoai == null || soDienThoai.isEmpty()) {
	        throw new IllegalArgumentException("Số điện thoại không được để trống.");
	    }
	    
	    // Kiểm tra nếu số điện thoại không đúng 10 chữ số
	    if (!soDienThoai.matches("\\d{10}")) {
	        throw new IllegalArgumentException("Số điện thoại phải gồm 10 chữ số.");
	    }
	    
	    // Gán giá trị nếu hợp lệ
	    this.soDienThoai = soDienThoai;
	}

	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
	    // Kiểm tra nếu địa chỉ không được vượt quá 255 ký tự
	    if (diaChi != null && diaChi.length() <= 255) {
	        this.diaChi = diaChi;
	    } else {
	        throw new IllegalArgumentException("Địa chỉ không được vượt quá 255 ký tự.");
	    }
	}

    public CaLamViec getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
    }
	
        
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
	    // Kiểm tra email không được null
	    if (email == null||email.isEmpty()) {
	        throw new IllegalArgumentException("Email không được để trống.");
	    }
	    
	    // Kiểm tra định dạng email và đuôi @gmail.com
	    if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
	        throw new IllegalArgumentException("Email phải có định dạng hợp lệ và kết thúc bằng @gmail.com.");
	    }
	    
	    // Gán giá trị email nếu hợp lệ
	    this.email = email;
	}

	public TrangThaiNhanVien getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(TrangThaiNhanVien trangThai) {
		this.trangThai = trangThai;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}
	
	
	
	
}
