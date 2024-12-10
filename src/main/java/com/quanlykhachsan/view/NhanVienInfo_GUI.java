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
    private JButton btnThayDoiThongTin; // Nút thay đổi thông tin
    private NhanVien nhanVien; // Nhân viên hiện tại

    public NhanVienInfo_GUI(String maNV) {
        // Tạo JFrame
        frame = new JFrame("Thông Tin Nhân Viên");
        frame.setSize(500, 500);
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

        // Tạo nút thay đổi thông tin
        btnThayDoiThongTin = new JButton("Thay Đổi Thông Tin");
        btnThayDoiThongTin.setFont(new Font("Arial", Font.BOLD, 14));
        btnThayDoiThongTin.setBackground(new Color(72, 145, 220));
        btnThayDoiThongTin.setForeground(Color.WHITE);
        btnThayDoiThongTin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnThayDoiThongTin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Lấy thông tin nhân viên
        nhanVien = getNhanVienData(maNV);
        showNhanVienInfo(nhanVien);

        // Xử lý sự kiện nút "Thay Đổi Thông Tin"
        btnThayDoiThongTin.addActionListener(e -> thayDoiThongTin());

        frame.add(labelClick, BorderLayout.NORTH);
        frame.add(panelInfo, BorderLayout.CENTER);
        frame.add(btnThayDoiThongTin, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private NhanVien getNhanVienData(String maNV) {
        NhanVien_DAO a = new NhanVien_DAO();
        return a.timTheoMa(maNV);
    }

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
        panelInfo.add(new JLabel(nhanVien.getLoaiNhanVien().getMaLoaiNhanVien().equals("MLNV01") ? "Nhân Viên" : "Quản Lý"));

        panelInfo.add(new JLabel("Trạng Thái:"));
        panelInfo.add(new JLabel(nhanVien.getTrangThai().toString()));

        panelInfo.revalidate();
        panelInfo.repaint();
    }

    private void thayDoiThongTin() {
        // Hiển thị hộp thoại để thay đổi thông tin
        String newSoDienThoai = JOptionPane.showInputDialog(frame, "Nhập Số Điện Thoại Mới:", nhanVien.getSoDienThoai());
        String newDiaChi = JOptionPane.showInputDialog(frame, "Nhập Địa Chỉ Mới:", nhanVien.getDiaChi());
        String newEmail = JOptionPane.showInputDialog(frame, "Nhập Email Mới:", nhanVien.getEmail());

        // Kiểm tra thông tin hợp lệ
        if (newSoDienThoai != null && !newSoDienThoai.isEmpty() && !newSoDienThoai.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(frame, "Số điện thoại phải gồm 10 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (newEmail != null && !newEmail.isEmpty() && !newEmail.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
            JOptionPane.showMessageDialog(frame, "Email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cập nhật thông tin
        nhanVien.setSoDienThoai(newSoDienThoai);
        nhanVien.setDiaChi(newDiaChi);
        nhanVien.setEmail(newEmail);

        // Gọi DAO để cập nhật vào database
        NhanVien_DAO dao = new NhanVien_DAO();
        boolean success = false;
        try {
              success = dao.capNhatNhanVien(nhanVien);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (success) {
            JOptionPane.showMessageDialog(frame, "Cập nhật thông tin thành công!", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
            showNhanVienInfo(nhanVien); // Hiển thị lại thông tin sau khi cập nhật
        } else {
            JOptionPane.showMessageDialog(frame, "Cập nhật thông tin thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

