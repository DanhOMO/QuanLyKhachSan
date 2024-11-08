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
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.*;

public class WorkScheduleGenerator {
    private CaLamViec_DAO caLamViecDao = new CaLamViec_DAO();
 
   public WorkScheduleGenerator() {
    taoLichLamViecMoi();
    }

    private long getInitialDelay() {
        // Calculate the next Friday at midnight
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextFriday = now.with(DayOfWeek.FRIDAY).withHour(0).withMinute(0).withSecond(0).withNano(0);

        // If today is Friday and the current time is after midnight (meaning today is not the next Friday), schedule for next Friday
        if (now.isAfter(nextFriday)) {
            nextFriday = nextFriday.plusWeeks(1); // If today is Friday but time is after midnight, schedule for next Friday
        }

        // Return the delay until the next Friday
        return Date.from(nextFriday.atZone(ZoneId.systemDefault()).toInstant()).getTime() - System.currentTimeMillis();
    }
    public String taoMaCaLamViec(){
        caLamViecDao.docTuBang();
        return "CA" + caLamViecDao.getList().size();
    }
  public void taoLichLamViecMoi() {
    // Get the list of employees from the database
    NhanVien_DAO nhanVienDao = new NhanVien_DAO();
    List<NhanVien> danhSachNhanVien = nhanVienDao.getList();
    int soLuongNhanVien = danhSachNhanVien.size();
      

    if (soLuongNhanVien < 3) {
        System.out.println("Không đủ nhân viên để tạo ca làm việc.");
        return;
    }

    // Shuffle the list to ensure fair distribution

    // Determine the upcoming Monday
    LocalDate monday = LocalDate.now().plusDays(3);
    
    int count = 0;
    for (int i = 0; i < 7; i++) { // Loop for 7 days
        LocalDate ngayLamViec = monday.plusDays(i);
        
        // Assign employees to three shifts
        caLamViecDao.themMoi(new CaLamViec(
            taoMaCaLamViec(),
            com.quanlykhachsan.enum_Class.CaLamViec.CA_SANG,
            ngayLamViec,
            0,
            danhSachNhanVien.get(count++)
        ));
        if(count == soLuongNhanVien) count = 0;

        caLamViecDao.themMoi(new CaLamViec(
            taoMaCaLamViec(),
            com.quanlykhachsan.enum_Class.CaLamViec.CA_TRUA,
            ngayLamViec,
            0,
            danhSachNhanVien.get(count++)
        ));
       if(count == soLuongNhanVien) count = 0;

        caLamViecDao.themMoi(new CaLamViec(
            taoMaCaLamViec(),
            com.quanlykhachsan.enum_Class.CaLamViec.CA_TOI,
            ngayLamViec,
            0,
            danhSachNhanVien.get(count++)
        ));
      if(count == soLuongNhanVien) count = 0;
    }

    System.out.println("Lịch làm việc từ thứ Hai đến Chủ Nhật tuần sau đã được tạo thành công!");
}

}
