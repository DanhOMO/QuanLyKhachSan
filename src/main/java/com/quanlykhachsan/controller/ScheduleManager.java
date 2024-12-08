/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.controller;

/**
 *
 * @author nguye
 */
import java.time.DayOfWeek;
import java.time.LocalDate;

public class ScheduleManager {
    private LocalDate lastRunDate = null; // Biến lưu ngày chạy gần nhất

    public void autoCreateSchedule() {
        LocalDate today = LocalDate.now();

        // Kiểm tra xem hôm nay đã chạy chưa
        if (lastRunDate == null || !lastRunDate.equals(today)) {
            // Chỉ chạy nếu hôm nay là Chủ Nhật
            if (today.getDayOfWeek() == DayOfWeek.SUNDAY) {
                new WorkScheduleGenerator(today.plusDays(1), today.plusDays(6));
                System.out.println("Schedule generated for: " + today);
            }

            // Cập nhật ngày cuối cùng đã chạy
            lastRunDate = today;
        } else {
            System.out.println("Task already executed for today: " + today);
        }
    }

    
}
