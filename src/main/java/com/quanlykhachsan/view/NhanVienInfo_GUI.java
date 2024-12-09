/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.view;

/**
 *
 * @author nguye
 */
import com.quanlykhachsan.controller.RoundBorder;
import com.quanlykhachsan.dao.NhanVien_DAO;
import com.quanlykhachsan.entity.NhanVien;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NhanVienInfo_GUI {
    private JFrame frame;
    private JPanel panelInfo;

    public NhanVienInfo_GUI(String maNV) {
        // Tạo JFrame
        frame = new JFrame("Thông Tin Nhân Viên");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Tạo JLabel để click
        JLabel labelClick = new JLabel("Thông Tin Nhân Viên", SwingConstants.CENTER);
        labelClick.setFont(new Font("Arial", Font.BOLD, 16));
        labelClick.setOpaque(true);
        labelClick.setBackground(new Color(58,186,178)); // Màu nền xanh
        labelClick.setForeground(Color.WHITE); // Màu chữ
        labelClick.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelClick.setBorder(new RoundBorder(10));
        // Tạo panel để hiển thị thông tin
        panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(0, 2, 10, 10));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

     
         NhanVien nhanVien = getNhanVienData(maNV);
                showNhanVienInfo(nhanVien);
        frame.add(labelClick, BorderLayout.NORTH);
        frame.add(panelInfo, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Hàm giả lập lấy thông tin nhân viên
    private NhanVien getNhanVienData(String maNV) {
        NhanVien_DAO a = new NhanVien_DAO();
        return a.timTheoMa(maNV);
    }

    // Hàm hiển thị thông tin nhân viên
    private void showNhanVienInfo(NhanVien nhanVien) {
        panelInfo.removeAll(); // Xóa nội dung cũ
        panelInfo.setLayout(new GridLayout(0, 2, 10, 10));

        // Thêm thông tin nhân viên
        panelInfo.add(new JLabel("Mã Nhân Viên:"));
        panelInfo.add(new JLabel(nhanVien.getMaNhanVien()));

        panelInfo.add(new JLabel("Tên Nhân Viên:"));
        panelInfo.add(new JLabel(nhanVien.getTenNhanVien()));

        panelInfo.add(new JLabel("Giới Tính:"));
        panelInfo.add(new JLabel(nhanVien.getGioiTinh().toString()));

        panelInfo.add(new JLabel("Ngày Sinh:"));
        panelInfo.add(new JLabel(nhanVien.getNgaySinh().toString()));

        panelInfo.add(new JLabel("Số Điện Thoại:"));
        panelInfo.add(new JLabel(nhanVien.getSoDienThoai()));

        panelInfo.add(new JLabel("Địa Chỉ:"));
        panelInfo.add(new JLabel(nhanVien.getDiaChi()));

        panelInfo.add(new JLabel("Email:"));
        panelInfo.add(new JLabel(nhanVien.getEmail()));

        panelInfo.add(new JLabel("Loại Nhân Viên:"));
        panelInfo.add(new JLabel(nhanVien.getLoaiNhanVien().getMaLoaiNhanVien().equals("MLNV01")? "Nhan Vien":"Quan Ly"));

        panelInfo.add(new JLabel("Trạng Thái:"));
        panelInfo.add(new JLabel(nhanVien.getTrangThai().toString()));

        panelInfo.revalidate();
        panelInfo.repaint();
    }

   
}
