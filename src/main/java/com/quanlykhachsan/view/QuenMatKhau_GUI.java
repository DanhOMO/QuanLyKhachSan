/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.view;

/**
 *
 * @author nguye
 */
import com.quanlykhachsan.dao.NhanVien_DAO;
import com.quanlykhachsan.entity.NhanVien;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuenMatKhau_GUI extends JFrame {
    private JTextField inputField;
    private JButton submitButton;
    private JLabel resultLabel;

    public QuenMatKhau_GUI() {
        // Thiết lập giao diện
        setTitle("Quên Mật Khẩu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JLabel instructionLabel = new JLabel("Nhập số điện thoại hoặc email của bạn:");
        inputField = new JTextField();
        submitButton = new JButton("Xác nhận");
        resultLabel = new JLabel("", SwingConstants.CENTER);

        add(instructionLabel);
        add(inputField);
        add(submitButton);
        add(resultLabel);

        // Sự kiện nhấn nút "Xác nhận"
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePasswordReset();
            }
        });
    }

    private void handlePasswordReset() {
        String input = inputField.getText();
        Random random = new Random();
        int soRD = 1000 + random.nextInt(9000);
        NhanVien_DAO nv = new NhanVien_DAO();

        if (input != null && !input.isEmpty()) {
            if (isValidEmail(input)) {
                if (checkEmailInDatabase(input)) {
                    NhanVien a = nv.timNhanVienTheoEmail(input);
                    try {
                        nv.capNhatMKtuEmail(a.getMaNhanVien(), a.getEmail(), soRD + "");
                        resultLabel.setText("Mật khẩu mới: " + soRD);
                    } catch (SQLException ex) {
                        Logger.getLogger(QuenMatKhau_GUI.class.getName()).log(Level.SEVERE, null, ex);
                        resultLabel.setText("Lỗi khi cập nhật mật khẩu.");
                    }
                } else {
                    resultLabel.setText("Email không tồn tại trong hệ thống.");
                }
            } else if (isValidPhoneNumber(input)) {
                if (checkPhoneNumberInDatabase(input)) {
                    NhanVien a = nv.timNhanVienTheoSoDienThoai(input);
                    try {
                        nv.capNhatMKtuSDT(a.getMaNhanVien(), a.getSoDienThoai(), soRD + "");
                        resultLabel.setText("Mật khẩu mới: " + soRD);
                    } catch (SQLException ex) {
                        Logger.getLogger(QuenMatKhau_GUI.class.getName()).log(Level.SEVERE, null, ex);
                        resultLabel.setText("Lỗi khi cập nhật mật khẩu.");
                    }
                } else {
                    resultLabel.setText("Số điện thoại không tồn tại trong hệ thống.");
                }
            } else {
                resultLabel.setText("Số điện thoại hoặc email không hợp lệ.");
            }
        } else {
            resultLabel.setText("Bạn cần nhập thông tin.");
        }
    }

    // Hàm kiểm tra email hợp lệ
    private boolean isValidEmail(String email) {
        // Thêm logic kiểm tra email tại đây
        return email.contains("@");
    }

    // Hàm kiểm tra số điện thoại hợp lệ
    private boolean isValidPhoneNumber(String phone) {
        // Thêm logic kiểm tra số điện thoại tại đây
        return phone.matches("\\d{10}");
    }

    // Hàm kiểm tra email trong cơ sở dữ liệu (mô phỏng)
    private boolean checkEmailInDatabase(String email) {
        // Thêm logic truy vấn cơ sở dữ liệu tại đây
        return true; // Giả sử email tồn tại
    }

    // Hàm kiểm tra số điện thoại trong cơ sở dữ liệu (mô phỏng)
    private boolean checkPhoneNumberInDatabase(String phone) {
        // Thêm logic truy vấn cơ sở dữ liệu tại đây
        return true; // Giả sử số điện thoại tồn tại
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuenMatKhau_GUI gui = new QuenMatKhau_GUI();
            gui.setVisible(true);
        });
    }
}
