package com.quanlykhachsan.enum_Class;

public enum TrangThaiPhong {
	TRONG("TRONG"),
	DA_DAT("DA_DAT"),
	DA_COC("DA_COC");
	
	private String msg;

	TrangThaiPhong(String msg) {
        this.msg = msg;
    }

    // Method to get CaLamViec by name
    public static TrangThaiPhong setTrangThaiPhong(String trangthai) {
        for (TrangThaiPhong trangThai : TrangThaiPhong.values()) {
            if (trangthai.equalsIgnoreCase(trangThai.msg)) {
                
                
                return trangThai;  // Return the enum value (CaLamViec) instead of the message
            }
        }
        // If not found, throw an exception or return null
        throw new IllegalArgumentException("Trang Thai không tồn tại: " + trangthai);
    }

    public String getTrangThaiPhong(){
        return msg;
    }
     public static String[] getAllTrangThaiPhong() {
        return new String[] {
            TRONG.msg,
            DA_DAT.msg,
            DA_COC.msg
        };
    }

    public boolean equalsIgnoreCase(String trong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
