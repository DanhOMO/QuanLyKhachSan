package com.quanlykhachsan.enum_Class;

public enum CaLamViec {
    CA_SANG("CA_SANG"),    // 6 -> 14
    CA_TRUA("CA_TRUA"),   // 14 -> 22
    CA_TOI("CA_TOI");      // 22 -> 6 (qua ngày hôm sau)

    private final String msg;

    // Constructor
    CaLamViec(String msg ) {
        this.msg = msg;
        

    }

    // Method to get CaLamViec by name
    public static CaLamViec getCaLamViec(String calamviec) {
        for (CaLamViec ca : CaLamViec.values()) {
            if (calamviec.equalsIgnoreCase(ca.msg)) {
                
                
                return ca;  // Return the enum value (CaLamViec) instead of the message
            }
        }
        // If not found, throw an exception or return null
        throw new IllegalArgumentException("Ca làm việc không tồn tại: " + calamviec);
    }

    public String getCa(){
        return msg;
    }
}
