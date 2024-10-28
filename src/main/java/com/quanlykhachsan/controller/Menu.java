package com.quanlykhachsan.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Menu extends JComponent {
    private JLabel[][] menuIteams; // Mảng 2 chiều chứa các JLabel

    public Menu(JLabel[][] menu) {
        // Khai báo mảng 2D hợp lệ và gán vào biến lớp menuIteams
        this.menuIteams = menu;
    }
    public JLabel[][] getListMenu(){
        return menuIteams;
    }
    // Phương thức trả về danh sách các JLabel cho menu chính
    public JLabel[] list_Menu() {
        JLabel[] list_Menu = new JLabel[menuIteams.length];
        for (int i = 0; i < menuIteams.length; i++) {
            list_Menu[i] = menuIteams[i][0];
        }
        return list_Menu;
    }

    // Phương thức trả về danh sách tất cả các sub-menu dưới dạng JLabel
    public JLabel[] list_MenuSub() {
        List<JLabel> menuSubList = new ArrayList<>();

        for (int i = 0; i < menuIteams.length; i++) {
            for (int j = 1; j < menuIteams[i].length; j++) {
                menuSubList.add(menuIteams[i][j]);
            }
        }

        return menuSubList.toArray(new JLabel[0]);
    }

    // Phương thức trả về sub-menu của một menu cha dưới dạng JLabel[]
    public JLabel[] subMenuOfParent(int parent) {
        if (parent < 0 || parent >= menuIteams.length) {
            throw new IllegalArgumentException("Chỉ số parent không hợp lệ");
        }

        List<JLabel> subMenu = new ArrayList<>();
        
        for (int i = 1; i < menuIteams[parent].length; i++) {
            
            subMenu.add(menuIteams[parent][i]);
        }

        return subMenu.toArray(new JLabel[0]);
    }

    // Phương thức kiểm tra menu cha có chứa sub-menu không
    public boolean isSubMenu(JLabel parent) {
        int parentIndex = find_menuSub(parent);

        if (parentIndex != -1 && menuIteams[parentIndex].length > 1) {
            return true;
        }
        return false;
    }

    // Phương thức tìm chỉ số của menu cha dựa trên tên
    public int find_menuSub(JLabel parent) {
        for (int i = 0; i < menuIteams.length; i++) {
            if (menuIteams[i][0].equals(parent)) {
                return i;
            }
        }
        return -1; // Không tìm thấy menu cha
    }
}
