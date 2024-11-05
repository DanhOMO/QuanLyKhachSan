/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.entity;

/**
 *
 * @author nguye
 */
public class ThongTinDatPhong {
    private ChiTietHoaDon maChiTietHoaHon;
    private String hoVaTen;
    private boolean laNguoiLon;

    public ThongTinDatPhong(ChiTietHoaDon maChiTietHoaHon, String hoVaTen, boolean laNguoiLon) {
        this.maChiTietHoaHon = maChiTietHoaHon;
        this.hoVaTen = hoVaTen;
        this.laNguoiLon = laNguoiLon;
    }

    public ChiTietHoaDon getMaChiTietHoaHon() {
        return maChiTietHoaHon;
    }

    public void setMaChiTietHoaHon(ChiTietHoaDon maChiTietHoaHon) {
        this.maChiTietHoaHon = maChiTietHoaHon;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public boolean isLaNguoiLon() {
        return laNguoiLon;
    }

    public void setLaNguoiLon(boolean laNguoiLon) {
        this.laNguoiLon = laNguoiLon;
    }
    public ThongTinDatPhong(ChiTietHoaDon ma){
        this.maChiTietHoaHon = ma;
        this.hoVaTen = "";
        this.laNguoiLon = false;
    }
    public ThongTinDatPhong(){
        super();
    }

    @Override
    public String toString() {
        return "ThongTinDatPhong{" + "maChiTietHoaHon=" + maChiTietHoaHon.getMaChiTietHoaDon() + ", hoVaTen=" + hoVaTen + ", laNguoiLon=" + laNguoiLon + '}';
    }
    
}
