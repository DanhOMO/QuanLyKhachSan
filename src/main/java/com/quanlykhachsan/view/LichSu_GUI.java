/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import com.quanlykhachsan.controller.JLabelRenderer;
import com.quanlykhachsan.controller.RoundBorder;
import com.quanlykhachsan.dao.HoaDon_DAO;
import com.quanlykhachsan.dao.NhanVien_DAO;

import com.quanlykhachsan.dao.Voucher_DAO;
import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.entity.Voucher;
import com.quanlykhachsan.view.InHoaDon;
import com.quanlykhachsan.view.ScreenshotHelper;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.WIDTH;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author nguye
 */

public class LichSu_GUI extends javax.swing.JPanel {
    private HoaDon_DAO hoaDonDAO = new HoaDon_DAO();

    public static void main(String[] args) {
        JFrame a = new JFrame();
        a.add(new LichSu_GUI());
        a.setVisible(true);
        a.setSize(1229, 730);
        a.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Constructor initializing the GUI.
     */
    public LichSu_GUI() {
        initComponents();
         JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("Xem Chi Tiết");
        popupMenu.add(editItem);
        timTheoTongTien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  double tongtien= Double.valueOf(timTheoTongTien.getText());
            DefaultTableModel dtm = new DefaultTableModel(new String[]{
            "Mã Hóa Đơn", "Ngày Lập", "Tên Nhân Viên", "Tổng Tiền", "Trạng Thái"}, 0);

        hoaDonDAO.getList().stream()
            .filter(hoaDon -> hoaDon.getTongTien() >= tongtien )
            .forEach(hoaDon -> dtm.addRow(new Object[]{
                hoaDon.getMaHoaDon(),
                hoaDon.getThoiGianLapHoaDon(),
                hoaDon.getNhanVien().getMaNhanVien(),
                hoaDon.getTongTien(),
                 (hoaDon.getTrangThai())
            }));

        if (dtm.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với số tiền lớn hơn hoặc bằng: " + tongtien);
        } else {
            tableHoaDon.setModel(dtm);
        }
           tableHoaDon.getColumnModel().getColumn(4).setCellRenderer(new JLabelRenderer()
           );
            }
        });
        timNgayLapHD.addPropertyChangeListener("date", (evt) -> {
            LocalDate localDate = timNgayLapHD.getDate().toInstant()
                                       .atZone(ZoneId.systemDefault())
                                       .toLocalDate();
            DefaultTableModel dtm = new DefaultTableModel(new String[]{
            "Mã Hóa Đơn", "Ngày Lập", "Tên Nhân Viên", "Tổng Tiền", "Trạng Thái"}, 0);
            NhanVien_DAO a = new NhanVien_DAO();
        hoaDonDAO.getList().stream()
            .filter(hoaDon -> hoaDon.getThoiGianLapHoaDon().equals(localDate))
            .forEach(hoaDon -> dtm.addRow(new Object[]{
                hoaDon.getMaHoaDon(),
                hoaDon.getThoiGianLapHoaDon(),
                a.timTheoMa(hoaDon.getNhanVien().getMaNhanVien()).getTenNhanVien(),
                hoaDon.getTongTien(),
                 (hoaDon.getTrangThai())
            }));

        if (dtm.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với thời gian: " + localDate);
        } else {
            tableHoaDon.setModel(dtm);
        }
           tableHoaDon.getColumnModel().getColumn(4).setCellRenderer(new JLabelRenderer()
           );
        });
        // Thêm hành động cho các menu item
        editItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableHoaDon.getSelectedRow();
                if(hoaDonDAO.timHoaDon(tableHoaDon.getValueAt(selectedRow, 0).toString()).getTrangThai()){
                    // Thực hiện hành động chỉnh sửa ở đây
                InHoaDon gui_InHoaDon = new InHoaDon(hoaDonDAO.timHoaDon(tableHoaDon.getValueAt(selectedRow, 0).toString()));
                gui_InHoaDon.setSize(700, 850);
                gui_InHoaDon.setVisible(true);
                int askPrint = JOptionPane.showConfirmDialog(jPanel1, "Bạn có muốn in hóa đơn không",
                        "In Vé", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (askPrint == JOptionPane.YES_OPTION) {
                    BufferedImage bff = ScreenshotHelper.captureComponent(gui_InHoaDon);
                    ScreenshotHelper.printImage(bff);
                    gui_InHoaDon.setVisible(false);

                } 
                }
                else {
                    InHoaDon gui_InHoaDon = new InHoaDon(hoaDonDAO.timHoaDon(tableHoaDon.getValueAt(selectedRow, 0).toString()));
                gui_InHoaDon.setSize(700, 850);
                gui_InHoaDon.setVisible(true);
                gui_InHoaDon.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                }
            }
        });
        tableHoaDon.setModel(docDuLieuVaoBanHoaDon());
          tableHoaDon.getColumnModel().getColumn(4).setCellRenderer(new JLabelRenderer());
        tableHoaDon.addMouseListener(new MouseAdapter() {
              @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger() || SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1) {
                    int row = tableHoaDon.rowAtPoint(e.getPoint());
                    tableHoaDon.setRowSelectionInterval(row, row);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int row = tableHoaDon.rowAtPoint(e.getPoint());
                    tableHoaDon.setRowSelectionInterval(row, row);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
     

       

        hienDuLieuVaoCBBNhanVien();

        // Action listener for finding invoices by Ma Nhan Vien
        timTheoMaNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maNhanVien = timTheoMaNhanVien.getSelectedItem().toString();
                if (!maNhanVien.isEmpty()) {
                    searchByMaNhanVien(maNhanVien.trim());
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tên nhân viên!");
                }
            }
        });
    }

    /**
     * Populates ComboBox with available NhanVien IDs.
     */
    public void hienDuLieuVaoCBBNhanVien() {
        NhanVien_DAO nhanvienDAO = new NhanVien_DAO();
        for (NhanVien nhanVien : nhanvienDAO.getList()) {
            timTheoMaNhanVien.addItem(nhanVien.getTenNhanVien());
        }
    }

    /**
     * Creates a JPanel to display the status of Hoa Don in the table.
     */
    
  

    /**
     * Loads data into the table for HoaDon.
     */
    public DefaultTableModel docDuLieuVaoBanHoaDon() {
        NhanVien_DAO a = new NhanVien_DAO();
        DefaultTableModel dtm = new DefaultTableModel(new String[]{
            "Mã Hóa Đơn", "Ngày Lập", "Tên Nhân Viên", "Tổng Tiền", "Trạng Thái"}, 0);
        hoaDonDAO.getList().forEach(x -> {
            double tien = 0;
            if( x.getTongTien() > 0) tien =x.getTongTien();
            dtm.addRow(new Object[]{
                x.getMaHoaDon(),
                x.getThoiGianLapHoaDon(),
                a.timTheoMa(x.getNhanVien().getMaNhanVien()).getTenNhanVien(),
                tien,
                 (x.getTrangThai())
            });
        });
       

        return dtm;
    }

    /**
     * Searches for HoaDon by MaHoaDon and displays in table.
     */
    private void searchByMaHoaDon(String maHoaDonTim) {
        DefaultTableModel dtm = new DefaultTableModel(new String[]{
            "Mã Hóa Đơn", "Ngày Lập", "Tên Nhân Viên", "Tổng Tiền", "Trạng Thái"}, 0);

        hoaDonDAO.getList().stream()
            .filter(hoaDon -> hoaDon.getMaHoaDon().equals(maHoaDonTim))
            .forEach(hoaDon -> dtm.addRow(new Object[]{
                hoaDon.getMaHoaDon(),
                hoaDon.getThoiGianLapHoaDon(),
                hoaDon.getNhanVien().getMaNhanVien(),
                hoaDon.getTongTien(),
                 (hoaDon.getTrangThai())
            }));

        if (dtm.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với mã: " + maHoaDonTim);
        } else {
            tableHoaDon.setModel(dtm);
        }
    }

    /**
     * Searches for HoaDon by MaNhanVien and displays in table.
     */
    private void searchByMaNhanVien(String tenNV) {
        DefaultTableModel dtm = new DefaultTableModel(new String[]{
            "Mã Hóa Đơn", "Ngày Lập", "Tên Nhân Viên", "Tổng Tiền", "Trạng Thái"}, 0);
        NhanVien_DAO a = new NhanVien_DAO();
        String maNhanVien = a.getList().stream()
                .filter(x -> x.getTenNhanVien().equalsIgnoreCase(tenNV))
                .findFirst().orElse(null).getMaNhanVien();
        hoaDonDAO.getList().stream()
            .filter(hoaDon -> hoaDon.getNhanVien().getMaNhanVien().equalsIgnoreCase(maNhanVien))
            .forEach(hoaDon -> dtm.addRow(new Object[]{
                hoaDon.getMaHoaDon(),
                hoaDon.getThoiGianLapHoaDon(),
                a.timTheoMa(hoaDon.getNhanVien().getMaNhanVien()).getTenNhanVien(),
                hoaDon.getTongTien(),
                 (hoaDon.getTrangThai())
            }));

        if (dtm.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với tên nhân viên: " + maNhanVien);
        } else {
            tableHoaDon.setModel(dtm);
        }
           tableHoaDon.getColumnModel().getColumn(4).setCellRenderer(new JLabelRenderer()
           );
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
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        timNgayLapHD = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        timTheoMaNhanVien = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        timTheoTongTien = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoaDon = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(58, 186, 178));

        jPanel2.setBackground(new java.awt.Color(58, 186, 178));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Lịch Sử Hóa Đơn");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(520, 520, 520)
                .addComponent(jLabel1)
                .addContainerGap(677, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Theo Thời Gian Lập Hóa Đơn:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Theo Tên Nhân Viên:");

        timTheoMaNhanVien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Tổng Tiền Lớn Hơn Hoặc Bằng:");

        timTheoTongTien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timNgayLapHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(timTheoMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(jLabel5)
                .addGap(72, 72, 72)
                .addComponent(timTheoTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(216, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(timNgayLapHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(timTheoMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(timTheoTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        tableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableHoaDon;
    private com.toedter.calendar.JDateChooser timNgayLapHD;
    private javax.swing.JComboBox<String> timTheoMaNhanVien;
    private javax.swing.JTextField timTheoTongTien;
    // End of variables declaration//GEN-END:variables
}
