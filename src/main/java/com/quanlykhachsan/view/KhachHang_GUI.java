/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import com.quanlykhachsan.dao.KhachHang_DAO;
import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.enum_Class.GioiTinh;
import com.quanlykhachsan.enum_Class.TrangThaiNhanVien;
import com.quanlykhachsan.model.ConnectDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class KhachHang_GUI extends javax.swing.JPanel implements ActionListener, MouseListener{

        private KhachHang_DAO list = new KhachHang_DAO();
    /**
     * Creates new form KhachHangĐÂSASDASDASSWQWEQQW_GUI
     */
    public KhachHang_GUI() {
        initComponents();
        docDuLieuVaoBang();
        jButtonSua.addActionListener(this);
        jButtonThem.addActionListener(this);
        jButtonXoaTrang.addActionListener(this);
        jTableKhachHang.addMouseListener(this);
        jTextFieldMaKhachHang.setEnabled(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableKhachHang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        
        jRadioButtonNu = new javax.swing.JRadioButton();
        jRadioButtonNam = new javax.swing.JRadioButton();
        jTextFieldDiaChi = new javax.swing.JTextField();
        jDateChooserNgaySinh = new com.toedter.calendar.JDateChooser();
        Email = new javax.swing.JTextField();
        jTextFieldSoDienThoai = new javax.swing.JTextField();
        jTextFieldTenKhachHang = new javax.swing.JTextField();
        jTextFieldMaKhachHang = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonThem = new javax.swing.JButton();
        jButtonXoaTrang = new javax.swing.JButton();
        jButtonSua = new javax.swing.JButton();
        jTextFieldCCCD = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1220, 868));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("THÔNG TIN KHÁCH HÀNG");

        jTableKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Giới Tính", "Ngày Sinh", "Email", "Địa Chỉ", "CCCD"
            }
        ));
        jScrollPane1.setViewportView(jTableKhachHang);

//        jRadioButtonKhac.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
//        jRadioButtonKhac.setText("Khác");

        jRadioButtonNu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jRadioButtonNu.setText("Nữ");

        jRadioButtonNam.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jRadioButtonNam.setText("Nam");
        jRadioButtonNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNamActionPerformed(evt);
            }
        });

        jTextFieldDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDiaChiActionPerformed(evt);
            }
        });

        jTextFieldTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTenKhachHangActionPerformed(evt);
            }
        });

        jTextFieldMaKhachHang.setEditable(false);
        jTextFieldMaKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMaKhachHangActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("CCCD:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Địa chỉ:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Email:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Ngày sinh:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Giới tính:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Số điện thoại:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Tên Khách Hàng:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Mã Khách Hàng:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tác Vụ:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        jButtonThem.setText("Thêm Khách Hàng");
        jButtonThem.setPreferredSize(new java.awt.Dimension(120, 20));
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jButtonXoaTrang.setText("Xóa Trắng");
        jButtonXoaTrang.setPreferredSize(new java.awt.Dimension(120, 20));
        jButtonXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaTrangActionPerformed(evt);
            }
        });

        jButtonSua.setText("Sửa Khách Hàng");
        jButtonSua.setPreferredSize(new java.awt.Dimension(120, 20));
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonSua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(jButtonThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonXoaTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButtonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButtonXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextFieldCCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCCCDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldMaKhachHang)
                            .addComponent(jTextFieldTenKhachHang)
                            .addComponent(jTextFieldSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRadioButtonNam)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonNu)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonKhac = new JRadioButton())))
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Email)
                    .addComponent(jDateChooserNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jTextFieldDiaChi)
                    .addComponent(jTextFieldCCCD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel6)
                                .addComponent(jTextFieldMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooserNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jRadioButtonNam)
                            .addComponent(jRadioButtonNu)
                            .addComponent(jRadioButtonKhac)
                            .addComponent(jTextFieldCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(513, 513, 513))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );
      
        
       
    }// </editor-fold>//GEN-END:initComponents
    public void docDuLieuVaoBang(){
         
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Giới Tính", "Ngày Sinh", "Email", "Địa Chỉ", "CCCD"}, 0);
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
        jTableKhachHang.setModel(dtm);
    }
    private void jRadioButtonNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonNamActionPerformed

    private void jTextFieldTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTenKhachHangActionPerformed

    private void jTextFieldMaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMaKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMaKhachHangActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jButtonXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaTrangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonXoaTrangActionPerformed

    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSuaActionPerformed

    private void jTextFieldCCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCCCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCCCDActionPerformed

    private void jTextFieldDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDiaChiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Email;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonXoaTrang;
    private com.toedter.calendar.JDateChooser jDateChooserNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonKhac;
    private javax.swing.JRadioButton jRadioButtonNam;
    private javax.swing.JRadioButton jRadioButtonNu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableKhachHang;
    private javax.swing.JTextField jTextFieldCCCD;
    private javax.swing.JTextField jTextFieldDiaChi;
    private javax.swing.JTextField jTextFieldMaKhachHang;
    private javax.swing.JTextField jTextFieldSoDienThoai;
    private javax.swing.JTextField jTextFieldTenKhachHang;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
          Object o = e.getSource();
    
    if (o.equals(jButtonXoaTrang)) {
        jTextFieldMaKhachHang.setText("");
        jTextFieldTenKhachHang.setText("");
        jTextFieldSoDienThoai.setText("");
        jRadioButtonNam.setSelected(false);
        jRadioButtonNu.setSelected(false);
        Email.setText("");
        jTextFieldCCCD.setText("");
        jTextFieldDiaChi.setText("");
        
    }

    if (o.equals(jButtonThem)) {
        // Kiểm tra xem có trường nào bị rỗng không
        String ma = taoMaKhachHang();
        if (jTextFieldMaKhachHang.getText().isEmpty() || jTextFieldTenKhachHang.getText().isEmpty() || jTextFieldSoDienThoai.getText().isEmpty() ||
            jDateChooserNgaySinh.getCalendar() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
            return; // Dừng lại nếu có trường rỗng
        }
        System.out.println(ma);
        list.themKhachHang(new KhachHang(
                ma,
                jTextFieldTenKhachHang.getText(), 
                jTextFieldSoDienThoai.getText(),
                GioiTinh.setGioiTinh(jRadioButtonNam.isSelected() ? "NAM": "NU"),
                jTextFieldDiaChi.getText(),
                jDateChooserNgaySinh.getCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
                jTextFieldCCCD.getText(),
                Email.getText()
            ));
        
         jTextFieldMaKhachHang.setText("");
        jTextFieldTenKhachHang.setText("");
        jTextFieldSoDienThoai.setText("");
        jRadioButtonNam.setSelected(false);
        jRadioButtonNu.setSelected(false);
        Email.setText("");
        jTextFieldCCCD.setText("");
        jTextFieldDiaChi.setText("");
        
          list.hienBangNV();
                
          docDuLieuVaoBang();
                
       
       
        
    }

    if (o.equals(jButtonSua)) {
       // Kiểm tra xem có trường nào bị rỗng không
        
        if (jTextFieldMaKhachHang.getText().isEmpty() || jTextFieldTenKhachHang.getText().isEmpty() || jTextFieldSoDienThoai.getText().isEmpty() ||
            jDateChooserNgaySinh.getCalendar() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
            return; // Dừng lại nếu có trường rỗng
        }

        try {
            list.capNhatKhachHang(new KhachHang(
                jTextFieldMaKhachHang.getText(),
                jTextFieldTenKhachHang.getText(), 
                jTextFieldSoDienThoai.getText(),
                GioiTinh.setGioiTinh(jRadioButtonNam.isSelected() ? "NAM": "NU"),
                jTextFieldDiaChi.getText(),
                jDateChooserNgaySinh.getCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
                jTextFieldCCCD.getText(),
                Email.getText()
            ));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        }
        
         jTextFieldMaKhachHang.setText("");
        jTextFieldTenKhachHang.setText("");
        jTextFieldSoDienThoai.setText("");
        jRadioButtonNam.setSelected(false);
        jRadioButtonNu.setSelected(false);
        Email.setText("");
        jTextFieldCCCD.setText("");
        jTextFieldDiaChi.setText("");
        
          list.hienBangNV();
                
          docDuLieuVaoBang();
                
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
          int row = jTableKhachHang.getSelectedRow();
        if (row >= 0){
           DefaultTableModel dtm = (DefaultTableModel) jTableKhachHang.getModel();
           KhachHang a = list.timTheoMa(dtm.getValueAt(row, 0).toString());
           jTextFieldMaKhachHang.setText(a.getMaKhachHang());
           jTextFieldTenKhachHang.setText(a.getTenKhachHang());
           if(a.getGioiTinh().getGioiTinh().equals("NAM"))
               jRadioButtonNam.setSelected(true);
           else jRadioButtonNu.setSelected(true);
           jTextFieldSoDienThoai.setText(a.getSoDienThoai());
           jDateChooserNgaySinh.setDate(java.sql.Date.valueOf(a.getNgaySinh()));
           Email.setText(a.getEmail());
            jTextFieldDiaChi.setText(a.getDiaChi());
            jTextFieldCCCD.setText(a.getCCCD());
            
        } else throw new IllegalArgumentException("Khong ton tai dong trong table kHACH HANG");
    }

    @Override
    public void mousePressed(MouseEvent e) {
         
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         
    }

    @Override
    public void mouseEntered(MouseEvent e) {
         
    }

    @Override
    public void mouseExited(MouseEvent e) {
         
    }
     private String taoMaKhachHang() {
		
		int i = list.hienBangNV().size();
                        	    // Kết hợp thành mã chi tiết hóa đơn
	    return "KH" + String.format("%04d", LocalDate.now().getYear()) + "-" + String.format("%03d", ++i) ;
	}
   
}
