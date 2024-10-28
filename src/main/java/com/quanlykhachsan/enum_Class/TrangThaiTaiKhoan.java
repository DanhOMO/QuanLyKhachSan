package com.quanlykhachsan.enum_Class;

public enum TrangThaiTaiKhoan {
	DANG_HOAT_DONG("DANG_HOAT_DONG"),
	KHONG_HOAT_DONG("KHONG_HOAT_DONG");
	
	private String msg;

	TrangThaiTaiKhoan(String msg) {
        this.msg = msg;
    }
 public static TrangThaiTaiKhoan setTrangThaiTaiKhoan(String trangthai) {
        for (TrangThaiTaiKhoan trangThai : TrangThaiTaiKhoan.values()) {
            if (trangthai.equalsIgnoreCase(trangThai.msg)) {
                
                
                return trangThai;  // Return the enum value (CaLamViec) instead of the message
            }
        }
        // If not found, throw an exception or return null
        throw new IllegalArgumentException("Trang Thai không tồn tại: " + trangthai);
    }

    public String getTrangThaiTaiKhoan(){
        return msg;
    }
    @Override
    public String toString() {
        return this.msg;
    }
}
