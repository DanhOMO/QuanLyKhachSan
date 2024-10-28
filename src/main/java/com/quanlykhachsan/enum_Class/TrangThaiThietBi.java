package com.quanlykhachsan.enum_Class;

public enum TrangThaiThietBi {
	DANG_HOAT_DONG("DANG_HOAT_DONG"),
	KHONG_HOAT_DONG("KHONG_HOAT_DONG");
	
	private String msg;

	TrangThaiThietBi(String msg) {
        this.msg = msg;
    }
     public static TrangThaiThietBi setTrangThaiThietBi(String trangthai) {
        for (TrangThaiThietBi trangThai : TrangThaiThietBi.values()) {
            if (trangthai.equalsIgnoreCase(trangThai.msg)) {
                
                
                return trangThai;  // Return the enum value (CaLamViec) instead of the message
            }
        }
        // If not found, throw an exception or return null
        throw new IllegalArgumentException("Trang Thai không tồn tại: " + trangthai);
    }

    public String getTrangThaiThietBi(){
        return msg;
    }
    public static String[] getALLThietBi() {
        return new String[] {
           DANG_HOAT_DONG.msg,
            KHONG_HOAT_DONG.msg
        };
    }
}
