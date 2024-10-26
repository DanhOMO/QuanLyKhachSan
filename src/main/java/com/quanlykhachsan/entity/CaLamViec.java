/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author nguye
 */
public class CaLamViec {
    private String maCaLamViec ;
    private com.quanlykhachsan.enum_Class.CaLamViec tenCaLamViec;
    private LocalDate ngayLamViec;
    private double tongTienTrongCa;
    private NhanVien nhanVien;

    public CaLamViec() {
        super();
    }

    public CaLamViec(String maCaLamViec, com.quanlykhachsan.enum_Class.CaLamViec tenCaLamViec, LocalDate ngayLamViec, double tongTienTrongCa, NhanVien nhanVien) {
        this.maCaLamViec = maCaLamViec;
        this.tenCaLamViec = tenCaLamViec;
        this.ngayLamViec = ngayLamViec;
        this.tongTienTrongCa = tongTienTrongCa;
        this.nhanVien = nhanVien;
    }

    public String getMaCaLamViec() {
        return maCaLamViec;
    }

    public void setMaCaLamViec(String maCaLamViec) {
        this.maCaLamViec = maCaLamViec;
    }

    public com.quanlykhachsan.enum_Class.CaLamViec getTenCaLamViec() {
        return tenCaLamViec;
    }

    public void setTenCaLamViec(com.quanlykhachsan.enum_Class.CaLamViec tenCaLamViec) {
        this.tenCaLamViec = tenCaLamViec;
    }

    public LocalDate getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(LocalDate ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    public double getTongTienTrongCa() {
        return tongTienTrongCa;
    }

    public void setTongTienTrongCa(double tongTienTrongCa) {
        this.tongTienTrongCa = tongTienTrongCa;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.maCaLamViec);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
    
        final CaLamViec other = (CaLamViec) obj;
        return Objects.equals(this.maCaLamViec, other.maCaLamViec);
    }

    @Override
    public String toString() {
        return "CaLamViec{" + "maCaLamViec=" + maCaLamViec + ", tenCaLamViec=" + tenCaLamViec + ", ngayLamViec=" + ngayLamViec + ", tongTienTrongCa=" + tongTienTrongCa + ", nhanVien=" + nhanVien + '}';
    }

    
    
   
  
    
   
    
}
