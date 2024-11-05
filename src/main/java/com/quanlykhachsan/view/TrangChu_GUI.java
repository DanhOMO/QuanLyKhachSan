/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.quanlykhachsan.view;

import com.formdev.flatlaf.extras.FlatInspector;
import com.quanlykhachsan.bean.DanhMucBean;
import com.quanlykhachsan.controller.ChuyenManHinh;
import com.quanlykhachsan.controller.Menu;
import com.quanlykhachsan.model.ConnectDB;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.Timer;
import javax.swing.UIManager;



/**
 *
 * @author nguye
 */
public class TrangChu_GUI extends javax.swing.JFrame {
    Menu menu ;
    /**
     * Creates new form TrangChu_GUI
     */
      
    public void eventSubMenu(JLabel a) {
    a.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
           if(a.getName().equalsIgnoreCase("jlbQuanLyThongTin") || a.getName().equalsIgnoreCase("jlbDanhMuc") || a.getName().equalsIgnoreCase("jlbPhong")){
                a.setForeground(Color.RED); // Đổi màu chữ khi di chuột vào
                a.setBackground(Color.red);
           }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(a.getName().equalsIgnoreCase("jlbQuanLyThongTin") || a.getName().equalsIgnoreCase("jlbDanhMuc") || a.getName().equalsIgnoreCase("jlbPhong")){
                a.setForeground(new Color(20,70,97)); // Đổi màu chữ khi di chuột vào
                a.setBackground(new Color(20,70,97));
           }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
 
            
            // Kiểm tra nếu `a` là một menu cha và có `subMenu`
            if (menu.isSubMenu(a)) {  // Dùng `a.getText()` thay vì `a.toString()` để lấy đúng nội dung JLabel
                int indexOfParent = menu.find_menuSub(a); // Tìm chỉ số của menu cha

                // Lấy các subMenu dựa trên chỉ số của menu cha
                JLabel[] subMenu = menu.subMenuOfParent(indexOfParent);
                // Đảo trạng thái hiển thị của subMenu
                boolean visual = subMenu[0].isVisible(); // Kiểm tra trạng thái hiện tại
                for (JLabel label : subMenu) {
                    label.setVisible(!visual); // Đảo ngược trạng thái hiển thị
                }
            } return;
        }
    });
}

    public TrangChu_GUI() {
       
//        sự kiện cho menu
    
         setTitle("Quản Lý Khách Sạn");
        initComponents();
        jlbVoucher.setVisible(false);
        jlbThietBi.setVisible(false);
        jlbKhuVuc.setVisible(false);
        jlbDichVu.setVisible(false);
        jlbQuanLyNhanVien.setVisible(false);
        jlbQuanLyKhachHang.setVisible(false);
        jlbQuanLyPhong.setVisible(false);
        jlbQuanLyLoaiPhong.setVisible(false);
         menu = new Menu(new JLabel[][]{
        {jlbQuanLyDatPhong},
        {jlbPhong, jlbQuanLyPhong, jlbQuanLyLoaiPhong},
        {jlbQuanLyThongTin, jlbQuanLyNhanVien, jlbQuanLyKhachHang},
        {jlbDanhMuc, jlbVoucher, jlbThietBi, jlbKhuVuc, jlbDichVu},
        {jlbThongKe},
        {jlbHelp},
        {jlbLichSu}
       });
        eventSubMenu(jlbPhong);
        eventSubMenu(jlbQuanLyLoaiPhong);
        eventSubMenu(jlbQuanLyPhong);
        eventSubMenu(jlbQuanLyDatPhong);
        eventSubMenu(jlbQuanLyThongTin);
        eventSubMenu(jlbQuanLyNhanVien);
        eventSubMenu(jlbQuanLyKhachHang);
        eventSubMenu(jlbDanhMuc);
        eventSubMenu(jlbVoucher);
        eventSubMenu(jlbThietBi);
        eventSubMenu(jlbKhuVuc);
        eventSubMenu(jlbDichVu);
        eventSubMenu(jlbThongKe);
        eventSubMenu(jlbTraCuu);
        eventSubMenu(jlbHelp);
        eventSubMenu(jlbLichSu);
        // Tạo Timer để cập nhật thời gian mỗi giây
        Timer timer = new Timer(1000, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 updateTime();
             }
        });
        timer.start(); // Bắt đầu Timer

        jPanel5.setLayout(new FlowLayout());
        // Thêm MouseListener để thay đổi màu khi hover
        btnDangXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                  btnDangXuat.setForeground(Color.RED); // Đổi màu chữ khi di chuột vào
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDangXuat.setForeground(new Color(20,70,97)); // Đổi màu chữ khi di chuột vào
               
            }
        });
        ChuyenManHinh controller = new ChuyenManHinh(jpView);
        controller.setView(jlbQuanLyDatPhong,jlbMain);
        
        List<DanhMucBean> listItem = new ArrayList<>();
        listItem.add(new DanhMucBean("TrangChu", jlbQuanLyDatPhong));
        listItem.add(new DanhMucBean("Phong", jlbQuanLyPhong));
        listItem.add(new DanhMucBean("LoaiPhong", jlbQuanLyLoaiPhong));
        listItem.add(new DanhMucBean("NhanVien", jlbQuanLyNhanVien));
        listItem.add(new DanhMucBean("KhachHang", jlbQuanLyKhachHang));
        listItem.add(new DanhMucBean("DichVu", jlbDichVu));
        listItem.add(new DanhMucBean("KhuVuc", jlbKhuVuc));
        listItem.add(new DanhMucBean("ThietBi", jlbThietBi));
        listItem.add(new DanhMucBean("Voucher", jlbVoucher));
        listItem.add(new DanhMucBean("TraCuu", jlbTraCuu));
        listItem.add(new DanhMucBean("ThongKe", jlbThongKe));
        listItem.add(new DanhMucBean("LichSu", jlbLichSu));
        listItem.add(new DanhMucBean("Help", jlbHelp));
//        listItem.add(new DanhMucBean("ThongTin", jlbQuanLyThongTin));
//        listItem.add(new DanhMucBean("DanhMuc", jlbDanhMuc));
        
        
//        listItem.add(new DanhMucBean("Help", jlbHelp));
        
        controller.setEvent(listItem);
    }
     private void updateTime() {
        // Lấy thời gian hiện tại và định dạng
        String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
       lbTime.setText(currentTime); // Cập nhật JLabel với thời gian hiện tại
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpRoot = new javax.swing.JPanel();
        jlbMenu = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jlbQuanLyThongTin = new javax.swing.JLabel();
        jlbQuanLyKhachHang = new javax.swing.JLabel();
        jlbQuanLyNhanVien = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jlbQuanLyDatPhong = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jlbPhong = new javax.swing.JLabel();
        jlbQuanLyLoaiPhong = new javax.swing.JLabel();
        jlbQuanLyPhong = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jlbThongKe = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jlbTraCuu = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jlbHelp = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jlbDanhMuc = new javax.swing.JLabel();
        jlbVoucher = new javax.swing.JLabel();
        jlbThietBi = new javax.swing.JLabel();
        jlbKhuVuc = new javax.swing.JLabel();
        jlbDichVu = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jlbLichSu = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnDangXuat = new javax.swing.JButton();
        lbTime = new javax.swing.JLabel();
        jpMainLabel = new javax.swing.JPanel();
        jpView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpRoot.setBackground(new java.awt.Color(255, 204, 255));

        jlbMenu.setBackground(new java.awt.Color(255, 204, 255));
        jlbMenu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        jlbMenu.setForeground(new java.awt.Color(255, 255, 255));
        jlbMenu.setMaximumSize(new java.awt.Dimension(257, 630));
        jlbMenu.setMinimumSize(new java.awt.Dimension(257, 630));
        jlbMenu.setOpaque(false);
        jlbMenu.setPreferredSize(new java.awt.Dimension(257, 630));

        jPanel4.setBackground(new java.awt.Color(255, 204, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 94, Short.MAX_VALUE)
        );

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setFocusCycleRoot(true);
        jScrollPane1.setInheritsPopupMenu(true);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setName(""); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 204, 255));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel5.setMaximumSize(new java.awt.Dimension(237, 928));
        jPanel5.setMinimumSize(new java.awt.Dimension(237, 928));
        jPanel5.setPreferredSize(new java.awt.Dimension(237, 928));

        jPanel3.setBackground(new java.awt.Color(255, 204, 255));

        jPanel6.setBackground(new java.awt.Color(255, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlbQuanLyThongTin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbQuanLyThongTin.setForeground(new java.awt.Color(20, 70, 97));
        jlbQuanLyThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/many people.png"))); // NOI18N
        jlbQuanLyThongTin.setText("Quản Lý Thông Tin");
        jlbQuanLyThongTin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlbQuanLyThongTin.setName("jlbQuanLyThongTin"); // NOI18N

        jlbQuanLyKhachHang.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbQuanLyKhachHang.setForeground(new java.awt.Color(20, 70, 97));
        jlbQuanLyKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/Customer1.png"))); // NOI18N
        jlbQuanLyKhachHang.setText("Quản Lý Khách Hàng");
        jlbQuanLyKhachHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlbQuanLyKhachHang.setName("jlbQuanLyKhachHang"); // NOI18N

        jlbQuanLyNhanVien.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbQuanLyNhanVien.setForeground(new java.awt.Color(20, 70, 97));
        jlbQuanLyNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/Employee.png"))); // NOI18N
        jlbQuanLyNhanVien.setText("Quản Lý Nhân Viên");
        jlbQuanLyNhanVien.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlbQuanLyNhanVien.setName("jlbQuanLyNhanVien"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbQuanLyNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbQuanLyKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbQuanLyThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jlbQuanLyThongTin)
                .addGap(0, 0, 0)
                .addComponent(jlbQuanLyKhachHang)
                .addGap(0, 0, 0)
                .addComponent(jlbQuanLyNhanVien)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 204, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlbQuanLyDatPhong.setBackground(new java.awt.Color(255, 255, 255));
        jlbQuanLyDatPhong.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbQuanLyDatPhong.setForeground(new java.awt.Color(20, 70, 97));
        jlbQuanLyDatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/dat_phong.png"))); // NOI18N
        jlbQuanLyDatPhong.setText("Quản Lý Đặt Phòng");
        jlbQuanLyDatPhong.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlbQuanLyDatPhong.setName("jlbQuanLyDatPhong"); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbQuanLyDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jlbQuanLyDatPhong)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 204, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlbPhong.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbPhong.setForeground(new java.awt.Color(20, 70, 97));
        jlbPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/hotel.png"))); // NOI18N
        jlbPhong.setText("Quản Lý Phòng");
        jlbPhong.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlbPhong.setName("jlbPhong"); // NOI18N

        jlbQuanLyLoaiPhong.setBackground(new java.awt.Color(255, 204, 255));
        jlbQuanLyLoaiPhong.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbQuanLyLoaiPhong.setForeground(new java.awt.Color(20, 70, 97));
        jlbQuanLyLoaiPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/TypeRoom.png"))); // NOI18N
        jlbQuanLyLoaiPhong.setText("Quản Lý Loại Phòng");
        jlbQuanLyLoaiPhong.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlbQuanLyLoaiPhong.setName("jlbQuanLyLoaiPhong"); // NOI18N
        jlbQuanLyLoaiPhong.setOpaque(true);

        jlbQuanLyPhong.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbQuanLyPhong.setForeground(new java.awt.Color(20, 70, 97));
        jlbQuanLyPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/doorr.png"))); // NOI18N
        jlbQuanLyPhong.setText("Quản Lý Phòng");
        jlbQuanLyPhong.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlbQuanLyPhong.setName("jlbQuanLyPhong"); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlbQuanLyLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlbQuanLyPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbQuanLyPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbQuanLyLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 204, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlbThongKe.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbThongKe.setForeground(new java.awt.Color(20, 70, 97));
        jlbThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/thong_ke.png"))); // NOI18N
        jlbThongKe.setText("Thông Kê");
        jlbThongKe.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlbThongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jlbThongKe.setName("jlbThongKe"); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jlbThongKe)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 204, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlbTraCuu.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbTraCuu.setForeground(new java.awt.Color(20, 70, 97));
        jlbTraCuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/tra_cuu.png"))); // NOI18N
        jlbTraCuu.setText("Tra cứu");
        jlbTraCuu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlbTraCuu.setName("jlbTraCuu"); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jlbTraCuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jlbTraCuu, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 204, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlbHelp.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbHelp.setForeground(new java.awt.Color(20, 70, 97));
        jlbHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/Help.png"))); // NOI18N
        jlbHelp.setText("Help");
        jlbHelp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlbHelp.setName("jlbHelp"); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jlbHelp, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jlbHelp)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 204, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setName("jlbDanhMuc"); // NOI18N

        jlbDanhMuc.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbDanhMuc.setForeground(new java.awt.Color(20, 70, 97));
        jlbDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/list.png"))); // NOI18N
        jlbDanhMuc.setText("Danh Mục");
        jlbDanhMuc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlbDanhMuc.setName("jlbDanhMuc"); // NOI18N

        jlbVoucher.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbVoucher.setForeground(new java.awt.Color(20, 70, 97));
        jlbVoucher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/Voucher.png"))); // NOI18N
        jlbVoucher.setText("Voucher");
        jlbVoucher.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlbVoucher.setName("jlbVoucher"); // NOI18N

        jlbThietBi.setBackground(new java.awt.Color(58, 186, 178));
        jlbThietBi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbThietBi.setForeground(new java.awt.Color(20, 70, 97));
        jlbThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/device.png"))); // NOI18N
        jlbThietBi.setText("Thiết Bị");
        jlbThietBi.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlbThietBi.setName("jlbThietBi"); // NOI18N

        jlbKhuVuc.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbKhuVuc.setForeground(new java.awt.Color(20, 70, 97));
        jlbKhuVuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/KhuVuc.png"))); // NOI18N
        jlbKhuVuc.setText("Khu Vực");
        jlbKhuVuc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlbKhuVuc.setName("jlbKhuVuc"); // NOI18N

        jlbDichVu.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbDichVu.setForeground(new java.awt.Color(20, 70, 97));
        jlbDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/DichVU.png"))); // NOI18N
        jlbDichVu.setText("Dịch Vụ");
        jlbDichVu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlbDichVu.setName("jlbDichVu"); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbThietBi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbKhuVuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jlbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jlbVoucher)
                .addGap(0, 0, 0)
                .addComponent(jlbThietBi)
                .addGap(0, 0, 0)
                .addComponent(jlbKhuVuc)
                .addGap(0, 0, 0)
                .addComponent(jlbDichVu)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 204, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlbLichSu.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbLichSu.setForeground(new java.awt.Color(20, 70, 97));
        jlbLichSu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/history.png"))); // NOI18N
        jlbLichSu.setText("Lịch Sử");
        jlbLichSu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlbLichSu.setName("jlbLichSu"); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jlbLichSu, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jlbLichSu)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel5);

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setFocusTraversalPolicyProvider(true);

        btnDangXuat.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(20, 70, 97));
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.setToolTipText("");
        btnDangXuat.setFocusPainted(false);
        btnDangXuat.setFocusable(false);
        btnDangXuat.setRequestFocusEnabled(false);
        btnDangXuat.setRolloverEnabled(false);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        lbTime.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jlbMenuLayout = new javax.swing.GroupLayout(jlbMenu);
        jlbMenu.setLayout(jlbMenuLayout);
        jlbMenuLayout.setHorizontalGroup(
            jlbMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jlbMenuLayout.createSequentialGroup()
                .addGroup(jlbMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jlbMenuLayout.setVerticalGroup(
            jlbMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jlbMenuLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jpMainLabel.setBackground(new java.awt.Color(255, 204, 255));
        jpMainLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        jpMainLabel.setForeground(new Color(65, 217, 158));

        javax.swing.GroupLayout jpMainLabelLayout = new javax.swing.GroupLayout(jpMainLabel);
        jpMainLabel.setLayout(jpMainLabelLayout);
        jpMainLabelLayout.setHorizontalGroup(
            jpMainLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1244, Short.MAX_VALUE)
        );
        jpMainLabelLayout.setVerticalGroup(
            jpMainLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        jpView.setBackground(new java.awt.Color(255, 204, 255));
        jpView.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(58, 186, 178), 5, true));
        jpView.setForeground(new java.awt.Color(255, 51, 51));
        jpView.setMaximumSize(new java.awt.Dimension(1220, 868));
        jpView.setMinimumSize(new java.awt.Dimension(1220, 868));
        jpView.setPreferredSize(new java.awt.Dimension(1220, 868));

        javax.swing.GroupLayout jpViewLayout = new javax.swing.GroupLayout(jpView);
        jpView.setLayout(jpViewLayout);
        jpViewLayout.setHorizontalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1226, Short.MAX_VALUE)
        );
        jpViewLayout.setVerticalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpRootLayout = new javax.swing.GroupLayout(jpRoot);
        jpRoot.setLayout(jpRootLayout);
        jpRootLayout.setHorizontalGroup(
            jpRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRootLayout.createSequentialGroup()
                .addComponent(jlbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRootLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jpView, javax.swing.GroupLayout.DEFAULT_SIZE, 1242, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jpMainLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jpRootLayout.setVerticalGroup(
            jpRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRootLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jpRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                    .addGroup(jpRootLayout.createSequentialGroup()
                        .addComponent(jpMainLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpView, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
       
   
        try {
             dispose();
            updateTrangThai("KHONG_HOAT_DONG");
            DangNhap_GUI gd= new DangNhap_GUI();
      gd.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(TrangChu_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
        
    
    

    }//GEN-LAST:event_btnDangXuatActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public void updateTrangThai(String trangThai) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;

    try {
        // Kết nối đến cơ sở dữ liệu
        con = ConnectDB.getInstance().getConnection();

        // Chuẩn bị câu lệnh SQL để cập nhật trạng thái
        String query = "UPDATE TaiKhoan SET trangThai = ?";
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, trangThai); // Cập nhật trạng thái
              // Tên đăng nhập

        // Thực thi câu lệnh cập nhật
        int rowsUpdated = pstmt.executeUpdate();
        
        // Kiểm tra nếu có dòng nào được cập nhật
        if (rowsUpdated > 0) {
            System.out.println("Trạng thái tài khoản đã được cập nhật thành công.");
        } else {
            System.out.println("Không tìm thấy tài khoản để cập nhật trạng thái.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Lỗi khi cập nhật trạng thái tài khoản: " + e.getMessage());
    } finally {
        // Đóng kết nối và các tài nguyên
        if (pstmt != null) {
            pstmt.close();
        }
        if (con != null) {
            con.close();
        }
    }
}
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrangChu_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChu_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChu_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChu_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChu_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbDanhMuc;
    private javax.swing.JLabel jlbDichVu;
    private javax.swing.JLabel jlbHelp;
    private javax.swing.JLabel jlbKhuVuc;
    private javax.swing.JLabel jlbLichSu;
    private javax.swing.JPanel jlbMenu;
    private javax.swing.JLabel jlbPhong;
    private javax.swing.JLabel jlbQuanLyDatPhong;
    private javax.swing.JLabel jlbQuanLyKhachHang;
    private javax.swing.JLabel jlbQuanLyLoaiPhong;
    private javax.swing.JLabel jlbQuanLyNhanVien;
    private javax.swing.JLabel jlbQuanLyPhong;
    private javax.swing.JLabel jlbQuanLyThongTin;
    private javax.swing.JLabel jlbThietBi;
    private javax.swing.JLabel jlbThongKe;
    private javax.swing.JLabel jlbTraCuu;
    private javax.swing.JLabel jlbVoucher;
    private javax.swing.JPanel jpMainLabel;
    private javax.swing.JPanel jpRoot;
    private javax.swing.JPanel jpView;
    private javax.swing.JLabel lbTime;
    // End of variables declaration//GEN-END:variables
        
}
