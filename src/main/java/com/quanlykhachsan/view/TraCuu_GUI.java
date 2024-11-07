/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import com.quanlykhachsan.controller.RoundBorder;
import com.quanlykhachsan.dao.DichVu_DAO;
import com.quanlykhachsan.dao.KhachHang_DAO;
import com.quanlykhachsan.dao.KhuVuc_DAO;
import com.quanlykhachsan.dao.LoaiPhong_DAO;
import com.quanlykhachsan.dao.NhanVien_DAO;
import com.quanlykhachsan.dao.Phong_DAO;
import com.quanlykhachsan.dao.ThietBi_DAO;
import com.quanlykhachsan.dao.Voucher_DAO;
import com.quanlykhachsan.entity.DichVu;
import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.KhuVuc;
import com.quanlykhachsan.entity.LoaiPhong;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.entity.Phong;
import com.quanlykhachsan.entity.ThietBi;
import com.quanlykhachsan.entity.Voucher;
import com.quanlykhachsan.enum_Class.TrangThaiPhong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class TraCuu_GUI extends javax.swing.JPanel {

    /**
     * Creates new form TraCuu_GUI
     */
    public static void main(String[] args) {
        JFrame a  = new JFrame();
        a.add(new TraCuu_GUI());
        a.setSize(1189, 868);
        a.setVisible(true);
        a.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
 
          
    public TraCuu_GUI() {
        initComponents();
        txtTimKiem.setBorder(new RoundBorder(10));
        boxSearch.setBorder(new RoundBorder(5));
        btnrefresh.setBorder(new RoundBorder(5));
        
        JPopupMenu menu = new JPopupMenu();
        JMenuItem item1 = new JMenuItem("Xem thông tin phòng");
        JMenuItem item2 = new JMenuItem("Đổi Phòng");
        JMenuItem item3 = new JMenuItem("Thanh Toán");
        JPopupMenu menu1 = new JPopupMenu();
        menu1.add(item1);
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
         Phong_DAO list = new Phong_DAO();
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Phòng", "Tên Phòng", "Trạng Thái Phòng", "Loại Phòng", "Khu Vực"}, 0);
          tableTraCuu.addMouseListener(new MouseAdapter() {
             @Override
            public void mousePressed(MouseEvent e) {
                // Check for valid row selection
                int row = tableTraCuu.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tableTraCuu.setRowSelectionInterval(row, row);

                    // String comparison fix
                    if (tableTraCuu.getValueAt(row, 2).equals("DA_DAT")) {
                        if (e.isPopupTrigger() || (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1)) {
                            menu.show(e.getComponent(), e.getX(), e.getY());
                        }
                    } else {
                        if (e.isPopupTrigger() || (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1)) {
                            menu1.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
            }

           
        });
        for (Phong phong : list.getList()) {
            

                dtm.addRow(new Object[]{
                    phong.getMaPhong(),
                    phong.getTenPhong(),
                    phong.getTrangThai().getTrangThaiPhong(),
                    phong.getLoaiPhong().getMaLoaiPhong(),
                    phong.getKhuVuc().getMaKhuVuc()
                });
            
        }

        tableTraCuu.setModel(dtm);
        
        lbTitle.setText("Danh Sách Phòng ");
        txtTimKiem.addMouseListener(new MouseAdapter() {
             @Override
            public void mouseClicked(MouseEvent e) {
                txtTimKiem.selectAll();
            }
        });
        btnrefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                 lbTitle.setText("Danh Sách ");
                 tableTraCuu.setModel(new DefaultTableModel());
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(cbbTieuChi.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(null, "Vui Lòng Chọn Tiêu Chí Muốn Tìm !!!");
                    
                }else {
                    DefaultTableModel dtm;
                    String tieuChi = txtTimKiem.getText();
                switch (cbbTieuChi.getSelectedItem().toString()) {

    case "Phòng": {
        Phong_DAO list = new Phong_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Phòng", "Tên Phòng", "Trạng Thái Phòng", "Loại Phòng", "Khu Vực"}, 0);

        for (Phong phong : list.getList()) {
            if (phong.getMaPhong().equalsIgnoreCase(tieuChi) || 
                phong.getTenPhong().equalsIgnoreCase(tieuChi) || 
                phong.getTrangThai().getTrangThaiPhong().equalsIgnoreCase(tieuChi) || 
                phong.getKhuVuc().getMaKhuVuc().equalsIgnoreCase(tieuChi) || 
                phong.getLoaiPhong().getMaLoaiPhong().equalsIgnoreCase(tieuChi)) {

                dtm.addRow(new Object[]{
                    phong.getMaPhong(),
                    phong.getTenPhong(),
                    phong.getTrangThai().getTrangThaiPhong(),
                    phong.getLoaiPhong().getMaLoaiPhong(),
                    phong.getKhuVuc().getMaKhuVuc()
                });
            }
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Loại Phòng": {
        LoaiPhong_DAO list = new LoaiPhong_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Loại Phòng", "Tên Loại Phòng", "Số Lượng Người"}, 0);
        list.docTuBang();
        for (LoaiPhong loaiPhong : list.getList()) {
            if (loaiPhong.getMaLoaiPhong().equalsIgnoreCase(tieuChi) || 
                loaiPhong.getTenLoaiPhong().equalsIgnoreCase(tieuChi) || 
                String.valueOf(loaiPhong.getSoLuongNguoi()).equalsIgnoreCase(tieuChi)) {

                dtm.addRow(new Object[]{
                    loaiPhong.getMaLoaiPhong(),
                    loaiPhong.getTenLoaiPhong(),
                    loaiPhong.getSoLuongNguoi()
                });
            }
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Khách Hàng": {
        KhachHang_DAO list = new KhachHang_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Giới Tính", "Ngày Sinh", "Email", "Địa Chỉ", "CCCD"}, 0);

        for (KhachHang khachHang : list.hienBangNV()) {
            if (khachHang.getMaKhachHang().equalsIgnoreCase(tieuChi) || 
                khachHang.getTenKhachHang().equalsIgnoreCase(tieuChi) || 
                khachHang.getSoDienThoai().equalsIgnoreCase(tieuChi) || 
                khachHang.getGioiTinh().getGioiTinh().equalsIgnoreCase(tieuChi) || 
                khachHang.getNgaySinh().toString().equalsIgnoreCase(tieuChi) || 
                khachHang.getEmail().equalsIgnoreCase(tieuChi) || 
                khachHang.getDiaChi().equalsIgnoreCase(tieuChi) || 
                khachHang.getCCCD().equalsIgnoreCase(tieuChi)) {

                dtm.addRow(new Object[]{
                    khachHang.getMaKhachHang(),
                    khachHang.getTenKhachHang(),
                    khachHang.getSoDienThoai(),
                    khachHang.getGioiTinh().getGioiTinh(),
                    khachHang.getNgaySinh(),
                    khachHang.getEmail(),
                    khachHang.getDiaChi(),
                    khachHang.getCCCD()
                });
            }
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Nhân Viên": {
        NhanVien_DAO list = new NhanVien_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Nhân Viên", "Tên Nhân Viên", "Số Điện Thoại", "Địa Chỉ", "Giới Tính", "Ngày Sinh", "Email", "Loại Nhân Viên", "Trạng Thái"}, 0);

        for (NhanVien nhanVien : list.getList()) {
            if (nhanVien.getMaNhanVien().equalsIgnoreCase(tieuChi) || 
                nhanVien.getTenNhanVien().equalsIgnoreCase(tieuChi) || 
                nhanVien.getSoDienThoai().equalsIgnoreCase(tieuChi) || 
                nhanVien.getDiaChi().equalsIgnoreCase(tieuChi) || 
                nhanVien.getGioiTinh().getGioiTinh().equalsIgnoreCase(tieuChi) || 
                nhanVien.getNgaySinh().toString().equalsIgnoreCase(tieuChi) || 
                nhanVien.getEmail().equalsIgnoreCase(tieuChi) || 
                nhanVien.getLoaiNhanVien().getMaLoaiNhanVien().equalsIgnoreCase(tieuChi) || 
                nhanVien.getTrangThai().getTrangThaiNhanVien().equalsIgnoreCase(tieuChi)) {

                dtm.addRow(new Object[]{
                    nhanVien.getMaNhanVien(),
                    nhanVien.getTenNhanVien(),
                    nhanVien.getSoDienThoai(),
                    nhanVien.getDiaChi(),
                    nhanVien.getGioiTinh().getGioiTinh(),
                    nhanVien.getNgaySinh(),
                    nhanVien.getEmail(),
                    nhanVien.getLoaiNhanVien().getMaLoaiNhanVien(),
                    nhanVien.getTrangThai().getTrangThaiNhanVien()
                });
            }
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Voucher": {
        Voucher_DAO list = new Voucher_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Khuyến Mãi", "Tên Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giảm Giá"}, 0);

        for (Voucher voucher : list.layDanhSachKhuyenMai()) {
            if (voucher.getMaVoucher().equalsIgnoreCase(tieuChi) || 
                voucher.getTenVoucher().equalsIgnoreCase(tieuChi) || 
                voucher.getNgayBatDau().toString().equalsIgnoreCase(tieuChi) || 
                voucher.getNgayKetThuc().toString().equalsIgnoreCase(tieuChi) || 
                String.valueOf(voucher.getGiamGia()).equalsIgnoreCase(tieuChi)) {

                dtm.addRow(new Object[]{
                    voucher.getMaVoucher(),
                    voucher.getTenVoucher(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.getGiamGia()
                });
            }
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Thiết Bị": {
        ThietBi_DAO list = new ThietBi_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Thiết Bị", "Tên Thiết Bị", "Trạng Thái"}, 0);
        list.docTuBang();
        for (ThietBi thietBi : list.getList()) {
            if (thietBi.getMaThietBi().equalsIgnoreCase(tieuChi) || 
                thietBi.getTenThietBi().equalsIgnoreCase(tieuChi) || 
                thietBi.getTrangThai().getTrangThaiThietBi().equalsIgnoreCase(tieuChi)) {

                dtm.addRow(new Object[]{
                    thietBi.getMaThietBi(),
                    thietBi.getTenThietBi(),
                    thietBi.getTrangThai().getTrangThaiThietBi()
                });
            }
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Khu Vực": {
        KhuVuc_DAO list = new KhuVuc_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Khu Vực", "Tên Khu Vực"}, 0);

        for (KhuVuc khuVuc : list.getDsKhuVuc()) {
            if (khuVuc.getMaKhuVuc().equalsIgnoreCase(tieuChi) || 
                khuVuc.getTenKhuVuc().equalsIgnoreCase(tieuChi)) {

                dtm.addRow(new Object[]{
                    khuVuc.getMaKhuVuc(),
                    khuVuc.getTenKhuVuc()
                });
            }
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Dịch Vụ": {
        DichVu_DAO list = new DichVu_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Dịch Vụ", "Tên Dịch Vụ", "Mô Tả"}, 0);

        for (DichVu dichVu : list.getDsDichVu()) {
            if (dichVu.getMaDichVu().equalsIgnoreCase(tieuChi) || 
                dichVu.getTenDichVu().equalsIgnoreCase(tieuChi) || 
                dichVu.getMoTa().equalsIgnoreCase(tieuChi)) {

                dtm.addRow(new Object[]{
                    dichVu.getMaDichVu(),
                    dichVu.getTenDichVu(),
                    dichVu.getMoTa()
                });
            }
        }
        tableTraCuu.setModel(dtm);
        break;

    }
        
                    
                    
                }
                
                }
            }
        });
        
        cbbTieuChi.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent e) {
                
                if(cbbTieuChi.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(null, "Vui Lòng Chọn Tiêu Chí Muốn Tìm !!!");
                    
                }else {
                    DefaultTableModel dtm;
                switch (cbbTieuChi.getSelectedItem().toString()) {

    case "Phòng": {
        Phong_DAO list = new Phong_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Phòng", "Tên Phòng", "Trạng Thái Phòng", "Loại Phòng", "Khu Vực"}, 0);

        for (Phong phong : list.getList()) {
         

                dtm.addRow(new Object[]{
                    phong.getMaPhong(),
                    phong.getTenPhong(),
                    phong.getTrangThai().getTrangThaiPhong(),
                    phong.getLoaiPhong().getMaLoaiPhong(),
                    phong.getKhuVuc().getMaKhuVuc()
                });
            
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Loại Phòng": {
        LoaiPhong_DAO list = new LoaiPhong_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Loại Phòng", "Tên Loại Phòng", "Số Lượng Người"}, 0);
        list.docTuBang();
        for (LoaiPhong loaiPhong : list.getList()) {
            
                dtm.addRow(new Object[]{
                    loaiPhong.getMaLoaiPhong(),
                    loaiPhong.getTenLoaiPhong(),
                    loaiPhong.getSoLuongNguoi()
                });
            
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Khách Hàng": {
        KhachHang_DAO list = new KhachHang_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Giới Tính", "Ngày Sinh", "Email", "Địa Chỉ", "CCCD"}, 0);

        for (KhachHang khachHang : list.hienBangNV()) {
           

                dtm.addRow(new Object[]{
                    khachHang.getMaKhachHang(),
                    khachHang.getTenKhachHang(),
                    khachHang.getSoDienThoai(),
                    khachHang.getGioiTinh().getGioiTinh(),
                    khachHang.getNgaySinh(),
                    khachHang.getEmail(),
                    khachHang.getDiaChi(),
                    khachHang.getCCCD()
                });
            
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Nhân Viên": {
        NhanVien_DAO list = new NhanVien_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Nhân Viên", "Tên Nhân Viên", "Số Điện Thoại", "Địa Chỉ", "Giới Tính", "Ngày Sinh", "Email", "Loại Nhân Viên", "Trạng Thái"}, 0);

        for (NhanVien nhanVien : list.getList()) {
            

                dtm.addRow(new Object[]{
                    nhanVien.getMaNhanVien(),
                    nhanVien.getTenNhanVien(),
                    nhanVien.getSoDienThoai(),
                    nhanVien.getDiaChi(),
                    nhanVien.getGioiTinh().getGioiTinh(),
                    nhanVien.getNgaySinh(),
                    nhanVien.getEmail(),
                    nhanVien.getLoaiNhanVien().getMaLoaiNhanVien(),
                    nhanVien.getTrangThai().getTrangThaiNhanVien()
                });
            
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Voucher": {
        Voucher_DAO list = new Voucher_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Khuyến Mãi", "Tên Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giảm Giá"}, 0);

        for (Voucher voucher : list.layDanhSachKhuyenMai()) {
           

                dtm.addRow(new Object[]{
                    voucher.getMaVoucher(),
                    voucher.getTenVoucher(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.getGiamGia()
                });
            
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Thiết Bị": {
        ThietBi_DAO list = new ThietBi_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Thiết Bị", "Tên Thiết Bị", "Trạng Thái"}, 0);
        list.docTuBang();
        for (ThietBi thietBi : list.getList()) {
           
                dtm.addRow(new Object[]{
                    thietBi.getMaThietBi(),
                    thietBi.getTenThietBi(),
                    thietBi.getTrangThai().getTrangThaiThietBi()
                });
            
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Khu Vực": {
        KhuVuc_DAO list = new KhuVuc_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Khu Vực", "Tên Khu Vực"}, 0);

        for (KhuVuc khuVuc : list.getDsKhuVuc()) {
           

                dtm.addRow(new Object[]{
                    khuVuc.getMaKhuVuc(),
                    khuVuc.getTenKhuVuc()
                });
            
        }

        tableTraCuu.setModel(dtm);
        break;
    }

    case "Dịch Vụ": {
        DichVu_DAO list = new DichVu_DAO();
        dtm = new DefaultTableModel(new String[]{"Mã Dịch Vụ", "Tên Dịch Vụ", "Mô Tả"}, 0);

        for (DichVu dichVu : list.getDsDichVu()) {
          
               

                dtm.addRow(new Object[]{
                    dichVu.getMaDichVu(),
                    dichVu.getTenDichVu(),
                    dichVu.getMoTa()
                });
            
        }
        tableTraCuu.setModel(dtm);
        break;

    }
        
                    
                    
                }
                
                }
            }
});

        
    }
    public void hienCbbTieuChi(){
        for (TrangThaiPhong value : TrangThaiPhong.values()) {
            cbbTieuChi.addItem(value.getTrangThaiPhong());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        boxSearch = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTraCuu = new javax.swing.JTable();
        lbTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbbTieuChi = new javax.swing.JComboBox<>();
        btnrefresh = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        boxSearch.setBackground(new java.awt.Color(58, 186, 178));
        boxSearch.setBorder(new RoundBorder(10));

        txtTimKiem.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTimKiem.setText("Tìm Kiếm");
        txtTimKiem.setBorder(new RoundBorder(10));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(58, 186, 178));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/search.png"))); // NOI18N
        btnSearch.setBorderPainted(false);

        javax.swing.GroupLayout boxSearchLayout = new javax.swing.GroupLayout(boxSearch);
        boxSearch.setLayout(boxSearchLayout);
        boxSearchLayout.setHorizontalGroup(
            boxSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, boxSearchLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );
        boxSearchLayout.setVerticalGroup(
            boxSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boxSearchLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(boxSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(58, 186, 178));

        tableTraCuu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableTraCuu.setToolTipText("");
        jScrollPane1.setViewportView(tableTraCuu);

        lbTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(426, Short.MAX_VALUE)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(355, 355, 355))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Tìm Theo:");

        cbbTieuChi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbbTieuChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng", "Loại Phòng", "Khách Hàng", "Voucher", "Thiết Bị", "Khu Vực", "Nhân Viên", "Dịch Vụ" }));

        btnrefresh.setBackground(new java.awt.Color(58, 186, 178));
        btnrefresh.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnrefresh.setText("Refresh");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbTieuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190)
                        .addComponent(boxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnrefresh)
                .addGap(111, 111, 111))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbTieuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boxSearch;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JComboBox<String> cbbTieuChi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTable tableTraCuu;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
