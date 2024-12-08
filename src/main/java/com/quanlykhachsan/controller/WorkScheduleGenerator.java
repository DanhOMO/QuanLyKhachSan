/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.controller;

/**
 *
 * @author nguye
 */
import com.quanlykhachsan.dao.CaLamViec_DAO;
import com.quanlykhachsan.dao.NhanVien_DAO;
import com.quanlykhachsan.entity.CaLamViec;
import com.quanlykhachsan.entity.NhanVien;
import java.time.*;
import java.util.*;
public class WorkScheduleGenerator {
    private CaLamViec_DAO caLamViecDao = new CaLamViec_DAO();

    public WorkScheduleGenerator(LocalDate a, LocalDate b) {
        taoLichLamViec(a, b);
    }

    public String taoMaCaLamViec() {
        caLamViecDao.docTuBang();
        return "CA" + (caLamViecDao.getList().size() + 1); // Tăng số thứ tự để tránh trùng mã
    }

    public void taoLichLamViec(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        
        if (ngayBatDau.isAfter(ngayKetThuc)) {
            throw new IllegalArgumentException("Ngày bắt đầu phải trước hoặc bằng ngày kết thúc.");
        }

        // Lấy danh sách nhân viên từ cơ sở dữ liệu
        NhanVien_DAO nhanVienDao = new NhanVien_DAO();
        List<NhanVien> danhSachNhanVien = nhanVienDao.getList().stream()
                .filter(x -> x.getLoaiNhanVien().getMaLoaiNhanVien().equalsIgnoreCase("MLNV01"))
                .filter(x -> x.getTrangThai().getTrangThaiNhanVien().equalsIgnoreCase("DANG_LAM_VIEC"))
                .toList();
        int soLuongNhanVien = danhSachNhanVien.size();

        if (soLuongNhanVien < 1) {
            System.out.println("Không đủ nhân viên để tạo ca làm việc. " );
            return;
        }

        // Biến đếm để đảm bảo phân công công bằng
        int count = 0;

        // Vòng lặp qua từng ngày trong khoảng thời gian
        LocalDate currentDate = ngayBatDau;
        while (!currentDate.isAfter(ngayKetThuc)) {
            // Thêm ca sáng
            caLamViecDao.themMoi(new CaLamViec(
                taoMaCaLamViec(),
                com.quanlykhachsan.enum_Class.CaLamViec.CA_SANG,
                currentDate,
                0,
                danhSachNhanVien.get(count++)
            ));
            if (count == soLuongNhanVien) count = 0;

            // Thêm ca trưa
            caLamViecDao.themMoi(new CaLamViec(
                taoMaCaLamViec(),
                com.quanlykhachsan.enum_Class.CaLamViec.CA_TRUA,
                currentDate,
                0,
                danhSachNhanVien.get(count++)
            ));
            if (count == soLuongNhanVien) count = 0;

            // Thêm ca tối
            caLamViecDao.themMoi(new CaLamViec(
                taoMaCaLamViec(),
                com.quanlykhachsan.enum_Class.CaLamViec.CA_TOI,
                currentDate,
                0,
                danhSachNhanVien.get(count++)
            ));
            if (count == soLuongNhanVien) count = 0;

            // Chuyển sang ngày tiếp theo
            currentDate = currentDate.plusDays(1);
        }

        System.out.println("Lịch làm việc từ " + ngayBatDau + " đến " + ngayKetThuc + " đã được tạo thành công!");
    }
}
