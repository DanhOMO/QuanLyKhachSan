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
import com.quanlykhachsan.entity.Phong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
        cbbTieuChi.setSelectedIndex(-1);
        lbTitle.setText("Danh Sách ");
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
                    if(phong.getMaPhong().equalsIgnoreCase(tieuChi) || phong.getTenPhong().equalsIgnoreCase(tieuChi) || phong.getTrangThai().getTrangThaiPhong().equalsIgnoreCase(tieuChi) || phong.getKhuVuc().getMaKhuVuc().equalsIgnoreCase(tieuChi) || phong.getLoaiPhong().getMaLoaiPhong().equalsIgnoreCase(tieuChi))
                    {
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
                list.docTuBang();
                dtm = new DefaultTableModel(new String[]{"Mã Loại Phòng", "Tên Loại Phòng", "Số Lượng Người"}, 0);
                
                list.getList().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaLoaiPhong(),
                        x.getTenLoaiPhong(),
                        x.getSoLuongNguoi()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Khách Hàng": {
                KhachHang_DAO list = new KhachHang_DAO();
                dtm = new DefaultTableModel(new String[]{"Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Giới Tính", "Ngày Sinh", "Email", "Địa Chỉ", "CCCD"}, 0);

                list.hienBangNV().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaKhachHang(),
                        x.getTenKhachHang(),
                        x.getSoDienThoai(),
                        x.getGioiTinh().getGioiTinh(),
                        x.getNgaySinh(),
                        x.getEmail(),
                        x.getDiaChi(),
                        x.getCCCD()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Nhân Viên": {
                NhanVien_DAO list = new NhanVien_DAO();
                dtm = new DefaultTableModel(new String[]{"Mã Nhân Viên", "Tên Nhân Viên", "Số Điện Thoại", "Địa Chỉ", "Giới Tính", "Ngày Sinh", "Email", "Loại Nhân Viên", "Trạng Thái"}, 0);

                list.getList().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaNhanVien(),
                        x.getTenNhanVien(),
                        x.getSoDienThoai(),
                        x.getDiaChi(),
                        x.getGioiTinh().getGioiTinh(),
                        x.getNgaySinh(),
                        x.getEmail(),
                        x.getLoaiNhanVien().getMaLoaiNhanVien(),
                        x.getTrangThai().getTrangThaiNhanVien()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Voucher": {
                Voucher_DAO list = new Voucher_DAO();
                dtm = new DefaultTableModel(new String[]{"Mã Khuyến Mãi", "Tên Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giảm Giá"}, 0);

                list.layDanhSachKhuyenMai().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaVoucher(),
                        x.getTenVoucher(),
                        x.getNgayBatDau(),
                        x.getNgayKetThuc(),
                        x.getGiamGia()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Thiết Bị": {
                ThietBi_DAO list = new ThietBi_DAO();
                list.docTuBang();
                dtm = new DefaultTableModel(new String[]{"Mã Thiết Bị", "Tên Thiết Bị", "Trạng Thái"}, 0);

                list.getList().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaThietBi(),
                        x.getTenThietBi(),
                        x.getTrangThai().getTrangThaiThietBi()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Khu Vực": {
                KhuVuc_DAO list = new KhuVuc_DAO();
                dtm = new DefaultTableModel(new String[]{"Mã Khu Vực", "Tên Khu Vực"}, 0);

                list.getDsKhuVuc().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaKhuVuc(),
                        x.getTenKhuVuc()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Dịch Vụ": {
                DichVu_DAO list = new DichVu_DAO();
                dtm = new DefaultTableModel(new String[]{"Mã Dịch Vụ", "Tên Dịch Vụ", "Mô Tả"}, 0);

                list.getDsDichVu().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaDichVu(),
                        x.getTenDichVu(),
                        x.getMoTa()
                    });
                });
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
        DefaultTableModel dtm;
        
        switch (cbbTieuChi.getSelectedItem().toString()) {
            case "Phòng": {
                Phong_DAO list = new Phong_DAO();
                dtm = new DefaultTableModel(new String[]{"Mã Phòng", "Tên Phòng", "Trạng Thái Phòng", "Loại Phòng", "Khu Vực"}, 0);

                list.getList().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaPhong(),
                        x.getTenPhong(),
                        x.getTrangThai().getTrangThaiPhong(),
                        x.getLoaiPhong().getMaLoaiPhong(),
                        x.getKhuVuc().getMaKhuVuc()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Loại Phòng": {
                LoaiPhong_DAO list = new LoaiPhong_DAO();
                list.docTuBang();
                dtm = new DefaultTableModel(new String[]{"Mã Loại Phòng", "Tên Loại Phòng", "Số Lượng Người"}, 0);
                
                list.getList().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaLoaiPhong(),
                        x.getTenLoaiPhong(),
                        x.getSoLuongNguoi()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Khách Hàng": {
                KhachHang_DAO list = new KhachHang_DAO();
                dtm = new DefaultTableModel(new String[]{"Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Giới Tính", "Ngày Sinh", "Email", "Địa Chỉ", "CCCD"}, 0);

                list.hienBangNV().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaKhachHang(),
                        x.getTenKhachHang(),
                        x.getSoDienThoai(),
                        x.getGioiTinh().getGioiTinh(),
                        x.getNgaySinh(),
                        x.getEmail(),
                        x.getDiaChi(),
                        x.getCCCD()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Nhân Viên": {
                NhanVien_DAO list = new NhanVien_DAO();
                dtm = new DefaultTableModel(new String[]{"Mã Nhân Viên", "Tên Nhân Viên", "Số Điện Thoại", "Địa Chỉ", "Giới Tính", "Ngày Sinh", "Email", "Loại Nhân Viên", "Trạng Thái"}, 0);

                list.getList().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaNhanVien(),
                        x.getTenNhanVien(),
                        x.getSoDienThoai(),
                        x.getDiaChi(),
                        x.getGioiTinh().getGioiTinh(),
                        x.getNgaySinh(),
                        x.getEmail(),
                        x.getLoaiNhanVien().getMaLoaiNhanVien(),
                        x.getTrangThai().getTrangThaiNhanVien()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Voucher": {
                Voucher_DAO list = new Voucher_DAO();
                dtm = new DefaultTableModel(new String[]{"Mã Khuyến Mãi", "Tên Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giảm Giá"}, 0);

                list.layDanhSachKhuyenMai().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaVoucher(),
                        x.getTenVoucher(),
                        x.getNgayBatDau(),
                        x.getNgayKetThuc(),
                        x.getGiamGia()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Thiết Bị": {
                ThietBi_DAO list = new ThietBi_DAO();
                list.docTuBang();
                dtm = new DefaultTableModel(new String[]{"Mã Thiết Bị", "Tên Thiết Bị", "Trạng Thái"}, 0);

                list.getList().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaThietBi(),
                        x.getTenThietBi(),
                        x.getTrangThai().getTrangThaiThietBi()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Khu Vực": {
                KhuVuc_DAO list = new KhuVuc_DAO();
                dtm = new DefaultTableModel(new String[]{"Mã Khu Vực", "Tên Khu Vực"}, 0);

                list.getDsKhuVuc().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaKhuVuc(),
                        x.getTenKhuVuc()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
            case "Dịch Vụ": {
                DichVu_DAO list = new DichVu_DAO();
                dtm = new DefaultTableModel(new String[]{"Mã Dịch Vụ", "Tên Dịch Vụ", "Mô Tả"}, 0);

                list.getDsDichVu().stream().forEach(x -> {
                    dtm.addRow(new Object[]{
                        x.getMaDichVu(),
                        x.getTenDichVu(),
                        x.getMoTa()
                    });
                });
                tableTraCuu.setModel(dtm);
                break;
            }
        }
        
        // Gán DefaultTableModel cho JTable
        lbTitle.setText("Danh Sách " + cbbTieuChi.getSelectedItem().toString());
    }
});

        
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
        cbbTieuChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng", "Loại Phòng", "Khách Hàng", "Nhân Viên", "Voucher", "Thiết Bị", "Khu Vực", "Dịch Vụ" }));

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
