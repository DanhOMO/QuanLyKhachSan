/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import com.quanlykhachsan.dao.Voucher_DAO;
import  com.quanlykhachsan.entity.Voucher;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class KhuyenMai_GUI extends javax.swing.JPanel implements ActionListener{
     
   private Voucher_DAO voucherDao = new Voucher_DAO();
    private final DefaultTableModel modalKhuyenMai;

    
    /**
     * Creates new form KhuyenMai_GUI
     */
    public KhuyenMai_GUI() {
        initComponents();
        modalKhuyenMai = new DefaultTableModel(new String[]{"Mã Khuyến Mãi","Tên Khuyến Mãi","Ngày Bắt Đầu","Ngày Kết Thúc","Giảm Giá"},0);
        loadDuLieuVaoBang();
        tableKhuyenMai.setModel(modalKhuyenMai);
        jTextField1.setText(phatSinhMaVoucher());
        btnThem.addActionListener(this);
        btnXoaTrang.addActionListener(this);
        btnCapNhat.addActionListener(this);
        
    }
    private void loadDuLieuVaoBang(){
        ArrayList<Voucher> dsKhuyenMai = voucherDao.layDanhSachKhuyenMai();
        for(int i=0;i<dsKhuyenMai.size();i++){
            String maKhuyenMai = dsKhuyenMai.get(i).getMaVoucher();
            String tenKhuyenMai = dsKhuyenMai.get(i).getTenVoucher();
            LocalDate ngayBD = dsKhuyenMai.get(i).getNgayBatDau();
            LocalDate ngayKT = dsKhuyenMai.get(i).getNgayKetThuc();
            double phanTram = dsKhuyenMai.get(i).getGiamGia();
            modalKhuyenMai.addRow( new Object[]{maKhuyenMai, tenKhuyenMai, ngayBD, ngayKT, phanTram});
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKhuyenMai = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableKhuyenMai1 = new javax.swing.JTable();

        jPanel1.setPreferredSize(new java.awt.Dimension(1220, 868));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Mã Khuyến Mãi");

        jLabel3.setText("Tên Khuyễn Mãi");

        jLabel4.setText("Thời Gian Bắt Đầu");

        jLabel5.setText("Thời Gian Kết Thúc");

        jTextField1.setEditable(false);

        jLabel6.setText("Phần Trăm % :");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoaTrang.setText("Xóa Trắng");
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(58, 186, 178));

        jLabel1.setBackground(new java.awt.Color(58, 186, 178));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thông Tin Khuyễn Mãi");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(58, 186, 178));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Chức năng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField2))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField1))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField3)))
                            .addComponent(btnXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(292, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(btnXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 85, Short.MAX_VALUE))
        );

        tableKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableKhuyenMai.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableKhuyenMai);

        tableKhuyenMai1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableKhuyenMai1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableKhuyenMai1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKhuyenMai1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableKhuyenMai1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaTrangActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void tableKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKhuyenMaiMouseClicked
       int row = tableKhuyenMai.getSelectedRow();
        jTextField1.setText((String) modalKhuyenMai.getValueAt(row, 0));
        jTextField2.setText((String) modalKhuyenMai.getValueAt(row, 1));
        LocalDate ngayBatDau= (LocalDate) modalKhuyenMai.getValueAt(row, 2);
        LocalDate ngayKetThuc = (LocalDate) modalKhuyenMai.getValueAt(row, 3);
        jDateChooser1.setDate(Date.from(ngayBatDau.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        jDateChooser2.setDate(Date.from(ngayKetThuc.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        double giamGia = (double) modalKhuyenMai.getValueAt(row, 4);
        jTextField3.setText(String.valueOf(giamGia));
    }//GEN-LAST:event_tableKhuyenMaiMouseClicked

    private void tableKhuyenMai1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKhuyenMai1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableKhuyenMai1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoaTrang;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tableKhuyenMai;
    private javax.swing.JTable tableKhuyenMai1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
    if (o == btnThem) {
        // Lấy dữ liệu từ các trường nhập
        String maVoucher = jTextField1.getText().trim();
        String tenVoucher = jTextField2.getText().trim();
        String phanTramStr = jTextField3.getText().trim();

        // Validate dữ liệu
        if (!validateForm()) {
            return; // Nếu không hợp lệ, dừng lại
        }

        // Chuyển đổi dữ liệu
        LocalDate ngayBD = jDateChooser1.getDate().toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId()).toLocalDate();
        LocalDate ngayKT = jDateChooser2.getDate().toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId()).toLocalDate();
        double phanTram = Double.parseDouble(phanTramStr);

        Voucher voucher = new Voucher(maVoucher, tenVoucher, phanTram, ngayBD, ngayKT);
        if (voucherDao.themVoucher(voucher)) {
            modalKhuyenMai.addRow(new Object[]{maVoucher, tenVoucher, ngayBD, ngayKT, phanTram});
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
        }
    } else if (o == btnXoaTrang) {
        // Xóa trắng các trường nhập
        jTextField1.setText(phatSinhMaVoucher());
        jTextField2.setText("");
        jTextField3.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);

    } else if (o == btnCapNhat) {
        // Lấy dòng được chọn
        int row = tableKhuyenMai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Chọn 1 dòng để cập nhật");
            return;
        }

        // Lấy dữ liệu từ các trường nhập
        String maVoucher = jTextField1.getText().trim();
        String tenVoucher = jTextField2.getText().trim();
        String phanTramStr = jTextField3.getText().trim();

        // Validate dữ liệu
        if (!validateForm()) {
            return; // Nếu không hợp lệ, dừng lại
        }

        // Chuyển đổi dữ liệu
        LocalDate ngayBD = jDateChooser1.getDate().toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId()).toLocalDate();
        LocalDate ngayKT = jDateChooser2.getDate().toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId()).toLocalDate();
        double phanTram = Double.parseDouble(phanTramStr);

        Voucher voucher = new Voucher(maVoucher, tenVoucher, phanTram, ngayBD, ngayKT);
        if (voucherDao.capNhatVoucher(maVoucher, voucher)) {
            modalKhuyenMai.setValueAt(tenVoucher, row, 1);
            modalKhuyenMai.setValueAt(ngayBD, row, 2);
            modalKhuyenMai.setValueAt(ngayKT, row, 3);
            modalKhuyenMai.setValueAt(phanTram, row, 4);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
        }
    }
    }
    private String phatSinhMaVoucher() {
        ArrayList<Voucher> dsVoucher = voucherDao.layDanhSachKhuyenMai();
        LocalDate currentDate = LocalDate.now();
        String datePrefix = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    
        if (dsVoucher.isEmpty()) {
            return "VC" + datePrefix + "-01";
        } else {
            int maxNumber = 0;
            for (Voucher voucher : dsVoucher) {
                String code = voucher.getMaVoucher();
                if (code.startsWith("VC" + datePrefix)) {
                    String[] parts = code.split("-");
                    if (parts.length == 2) {
                        try {
                            int number = Integer.parseInt(parts[1]);
                            maxNumber = Math.max(maxNumber, number);
                        } catch (NumberFormatException e) {
                            // Bỏ qua nếu không thể chuyển đổi thành số
                        }
                    }
                }
            }
            int nextNumber = maxNumber + 1;
            String formattedNumber = String.format("%02d", nextNumber); // Định dạng số tự động thành 2 chữ số
            return "VC" + datePrefix + "-" + formattedNumber;
        }
    }

    public boolean validateForm() {
    String maVoucher = jTextField1.getText().trim();
    String tenVoucher = jTextField2.getText().trim();
    String giaGiamStr = jTextField3.getText().trim();
    

    // Kiểm tra mã voucher
    if (maVoucher.isEmpty() || !maVoucher.matches("^VC\\d{8}-\\d{2}$")) {
        JOptionPane.showMessageDialog(null, "Mã voucher không hợp lệ! Định dạng phải là VCXXXXXXX-XX.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        jTextField1.requestFocus();
        return false;
    }

    // Kiểm tra tên voucher
    if (tenVoucher.isEmpty() || !tenVoucher.matches("^[A-Z][a-z]*(\\s[A-Z][a-z]*)*(\\s\\d+%?)?$")) {
    JOptionPane.showMessageDialog(this, "Tên voucher không hợp lệ! Tên phải bắt đầu bằng chữ cái viết hoa, có thể chứa số và dấu '%'.");
    jTextField2.requestFocus();
    return false;
}

    // Kiểm tra giá giảm
    try {
        Double giaGiam = Double.valueOf(giaGiamStr);
        if (giaGiam <= 0 || giaGiam >= 100) {
            JOptionPane.showMessageDialog(null, "Giá giảm phải nằm trong khoảng 0 < giá giảm < 1.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            jTextField3.requestFocus();
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Giá giảm phải là một số thực.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        jTextField3.requestFocus();
        return false;
    }
    
    // Kiểm tra ngày bắt đầu
    if(jDateChooser1.getDate() == null){
        JOptionPane.showMessageDialog(null, "Ngày bắt đầu không được để trống", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        jDateChooser1.requestFocus();
        return false;
    }
    LocalDate ngayBD = jDateChooser1.getDate().toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId()).toLocalDate();
    if (ngayBD.isBefore(LocalDate.now())) {
        JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        jDateChooser1.requestFocus();
        return false;
    }

    // Kiểm tra ngày kết thúc
    if(jDateChooser2.getDate() == null){
        JOptionPane.showMessageDialog(null, "Ngày kết thúc không được để trống", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        jDateChooser2.requestFocus();
        return false;
    }
    LocalDate ngayKT = jDateChooser2.getDate().toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId()).toLocalDate();
    if (ngayKT.isBefore(ngayBD)) {
        JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        jDateChooser2.requestFocus();
        return false;
    }

    // Nếu tất cả hợp lệ
    return true;
}
}
