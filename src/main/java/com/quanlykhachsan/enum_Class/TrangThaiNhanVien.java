package com.quanlykhachsan.enum_Class;

public enum TrangThaiNhanVien {
	DANG_LAM_VIEC("DANG_LAM_VIEC"),
	NGHI_PHEP("NGHI_PHEP"),
	NGHI_VIEC("NGHI_VIEC");
	
	private String msg;

	TrangThaiNhanVien(String msg) {
        this.msg = msg;
    }
        public static TrangThaiNhanVien setTrangThaiNhanVien(String trangthai) {
        for (TrangThaiNhanVien trangThai : TrangThaiNhanVien.values()) {
            if (trangthai.equalsIgnoreCase(trangThai.msg)) {
                
                
                return trangThai;  // Return the enum value (CaLamViec) instead of the message
            }
        }
        // If not found, throw an exception or return null
        throw new IllegalArgumentException("Trang Thai không tồn tại: " + trangthai);
    }

    public String getTrangThaiNhanVien(){
        return msg;
    }
    @Override
    public String toString() {
        return this.msg;
    }
}
