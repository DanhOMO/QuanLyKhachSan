package com.quanlykhachsan.enum_Class;

public enum TrangThaiHoaDon {
	DA_THANH_TOAN("DA_THANH_TOAN"),//1
	CHUA_THANH_TOAN("CHUA_THANH_TOAN")//0 da checkin - da dat
	,DA_DAT_TRUOC("DA_COC")//
	,BO_COC("BO_COC");
	private String msg;

	TrangThaiHoaDon(String msg) {
        this.msg = msg;
    }
 public static TrangThaiHoaDon setTrangThaiHoaDon(String trangthai) {
        for (TrangThaiHoaDon trangThai : TrangThaiHoaDon.values()) {
            if (trangthai.equalsIgnoreCase(trangThai.msg)) {
                
                
                return trangThai;  // Return the enum value (CaLamViec) instead of the message
            }
        }
        // If not found, throw an exception or return null
        throw new IllegalArgumentException("Trang Thai không tồn tại: " + trangthai);
    }

    public String getTrangThaiHoaDon(){
        return msg;
    }
    @Override
    public String toString() {
        return this.msg;
    }
}
