/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.view;

/**
 *
 * @author nguye
 */
import com.quanlykhachsan.dao.Phong_ThietBi_DAO;
import com.quanlykhachsan.dao.ThietBi_DAO;
import com.quanlykhachsan.entity.ThietBi;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ThemThietBi_GUI extends JFrame {
    private JTable table;
    private JButton btnAddAll;
    private String maPhong; // Mã phòng hiện tại
    private Phong_ThietBi_DAO phongThietBiDAO = new Phong_ThietBi_DAO(); // DAO để làm việc với cơ sở dữ liệu
    public static void main(String[] args) {
        new ThemThietBi_GUI("P001");
    }
    public ThemThietBi_GUI(String maPhong) {
        this.maPhong = maPhong; // Lưu mã phòng hiện tại
        initUI();
    }

    private void initUI() {
        setTitle("Thêm thiết bị vào phòng");
        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Lấy danh sách thiết bị chưa có trong phòng
        List<ThietBi> availableThietBi = getAvailableThietBi(maPhong);

        // Tạo dữ liệu cho bảng
        String[] columnNames = {"Chọn", "Mã thiết bị", "Tên thiết bị"};
        Object[][] data = new Object[availableThietBi.size()][3];
        for (int i = 0; i < availableThietBi.size(); i++) {
            ThietBi tb = availableThietBi.get(i);
            data[i][0] = false; // Checkbox mặc định chưa chọn
            data[i][1] = tb.getMaThietBi();
            data[i][2] = tb.getTenThietBi();
        }

        // Tạo bảng
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 ? Boolean.class : String.class; // Cột đầu là checkbox
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Chỉ cho phép chỉnh sửa checkbox
            }
        };

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Nút "Thêm Tất Cả"
        btnAddAll = new JButton("Thêm Tất Cả");
        btnAddAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSelectedThietBi();
            }
        });
        add(btnAddAll, BorderLayout.SOUTH);
    }

    private List<ThietBi> getAvailableThietBi(String maPhong) {
        // Lấy danh sách tất cả thiết bị
        ThietBi_DAO thietbi = new ThietBi_DAO();
        thietbi.docTuBang();
        List<ThietBi> allThietBi = thietbi.getList();

        // Lấy danh sách thiết bị đã tồn tại trong phòng
        List<String> existingThietBi = phongThietBiDAO.getThietBiByPhong(maPhong);

        // Lọc danh sách thiết bị chưa có trong phòng
        List<ThietBi> availableThietBi = new ArrayList<>();
        for (ThietBi tb : allThietBi) {
            if (!existingThietBi.contains(tb.getTenThietBi())) {
                availableThietBi.add(tb);
            }
        }
        return availableThietBi;
    }

    private void addSelectedThietBi() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<String> selectedThietBi = new ArrayList<>();

        // Duyệt qua bảng để tìm thiết bị đã được chọn
        for (int i = 0; i < model.getRowCount(); i++) {
            Boolean isChecked = (Boolean) model.getValueAt(i, 0);
            if (isChecked) {
                String maThietBi = (String) model.getValueAt(i, 1);
                selectedThietBi.add(maThietBi);
            }
        }

        // Thêm thiết bị vào phòng
        for (String string : selectedThietBi) {
             boolean success = phongThietBiDAO.addThietBiToPhong(maPhong, string);
        if (success) {
            JOptionPane.showMessageDialog(this, "Thêm thiết bị thành công!");
            dispose(); // Đóng cửa sổ
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thiết bị thất bại!");
        }
        }
    }
}