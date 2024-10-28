package com.quanlykhachsan.enum_Class;

public enum GioiTinh {
    NAM("NAM"),
    NU("NU");

    private String msg;

    GioiTinh(String msg) {
        this.msg = msg;
    }
 public static GioiTinh setGioiTinh(String trangthai) {
        for (GioiTinh trangThai : GioiTinh.values()) {
            if (trangthai.equalsIgnoreCase(trangThai.msg)) {
                
                
                return trangThai;  // Return the enum value (CaLamViec) instead of the message
            }
        }
        // If not found, throw an exception or return null
        throw new IllegalArgumentException("Trang Thai không tồn tại: " + trangthai);
    }

    public String getGioiTinh(){
        return msg;
    }
    @Override
    public String toString() {
        return this.msg;
    }
}

