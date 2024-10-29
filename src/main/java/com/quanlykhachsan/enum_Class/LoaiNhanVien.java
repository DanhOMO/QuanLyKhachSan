/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.enum_Class;

/**
 *
 * @author nguye
 */
public enum LoaiNhanVien {
    NHANVIEN("Nhan Vien"),
    QuanLy("Quan ly");

 
    private final String msg;

    // Constructor
    LoaiNhanVien(String msg ) {
        this.msg = msg;
        

    }

    // Method to get CaLamViec by name
    public static LoaiNhanVien getLoaiNhanVien(String calamviec) {
        for (LoaiNhanVien ca : LoaiNhanVien.values()) {
            if (calamviec.equalsIgnoreCase(ca.msg)) {
                
                
                return ca;  // Return the enum value (CaLamViec) instead of the message
            }
        }
        // If not found, throw an exception or return null
        throw new IllegalArgumentException("Ca làm việc không tồn tại: " + calamviec);
    }
    
    public String getLNV(){
        return msg;
    }
     public static String[] getAllCaLamViec() {
        return new String[] {
            NHANVIEN.msg,
            QuanLy.msg
        };
    }


}
