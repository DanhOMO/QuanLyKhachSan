/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.controller;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author nguye
 */
  public class JLabelRenderer extends JLabel implements TableCellRenderer {
        public JLabelRenderer() {
            setOpaque(true); // Cần thiết để màu nền hiển thị
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Kiểm tra và hiển thị JLabel tùy thuộc vào giá trị trong ô
            if (value.equals(true)) {
                setText("Đã Thanh Toán"); 
                setIcon(new ImageIcon("/com/quanlykhachsan/img/logo.png")); // Đường dẫn đến biểu tượng hoạt động
                setBackground(Color.GREEN);
                setForeground(Color.black);
            } else if (value.equals(false)) {
                setText("Chưa thanh Toán");
                setIcon(new ImageIcon("/com/quanlykhachsan/img/logo.png")); // Đường dẫn đến biểu tượng không hoạt động
                setBackground(Color.BLACK.RED);
                setForeground(Color.WHITE);
            } else {
                setText(value.toString());
                setIcon(null);
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }

            // Đổi màu nền nếu ô được chọn
            if (isSelected) {
                setBackground(getBackground().darker());
            }
            setBorder(new RoundBorder(10));
            return this;
        }
    }
