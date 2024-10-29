/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import javax.swing.JOptionPane;

import com.quanlykhachsan.dao.KhachHang_DAO;
import com.quanlykhachsan.entity.KhachHang;

/**
 *
 * @author liemh
 */
public class ThongTinDatPhong extends javax.swing.JPanel {

	/**
	 * Creates new form ThongTinDatPhong
	 */
	private KhachHang_DAO kh_dao;
	public ThongTinDatPhong() {
		initComponents();
		kh_dao = new KhachHang_DAO();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldSoDienThoai = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldTenNhanVien = new javax.swing.JTextField();
        jTextFieldTenKhachHang = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jDateChooserCheckOut = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jDateChooserCheckIn = new com.toedter.calendar.JDateChooser();
        jRadioButtonDatTruoc = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        jRadioButtonDat = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        jRadioButtonNgay = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jSpinFieldThoiGianDat = new com.toedter.components.JSpinField();
        jRadioButtonGio = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxDichVu = new javax.swing.JComboBox<>();
        jSpinnerSoLuongDichVu = new javax.swing.JSpinner();
        jButtonThemDichVu = new javax.swing.JButton();
        jTextFieldGiaDichVu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldTongTien = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldTienCoc = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxVoucher = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jButtonXacNhan = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jButtonHuy = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Tên khách hàng:");

        jTextFieldSoDienThoai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldSoDienThoaiFocusLost(evt);
            }
        });
        jTextFieldSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSoDienThoaiActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Check-in:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Khách hàng");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Nhân viên:");

        jTextFieldTenNhanVien.setEnabled(false);
        jTextFieldTenNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTenNhanVienActionPerformed(evt);
            }
        });

        jTextFieldTenKhachHang.setEnabled(false);
        jTextFieldTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTenKhachHangActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Số điện thoại:");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Check-out:");

        jDateChooserCheckIn.setEnabled(false);

        buttonGroup1.add(jRadioButtonDatTruoc);
        jRadioButtonDatTruoc.setText("Đặt Trước");
        jRadioButtonDatTruoc.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonDatTruocStateChanged(evt);
            }
        });
        jRadioButtonDatTruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDatTruocActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText("Hình thức đặt:");

        buttonGroup1.add(jRadioButtonDat);
        jRadioButtonDat.setSelected(true);
        jRadioButtonDat.setText("Đặt ");
        jRadioButtonDat.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonDatStateChanged(evt);
            }
        });
        jRadioButtonDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDatActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Hình thức thuê:");

        buttonGroup2.add(jRadioButtonNgay);
        jRadioButtonNgay.setSelected(true);
        jRadioButtonNgay.setText("Ngày");
        jRadioButtonNgay.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonNgayStateChanged(evt);
            }
        });
        jRadioButtonNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNgayActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setText("Thời gian đặt:");

        buttonGroup2.add(jRadioButtonGio);
        jRadioButtonGio.setText("Giờ");
        jRadioButtonGio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonGioStateChanged(evt);
            }
        });
        jRadioButtonGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonGioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel16)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldSoDienThoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jTextFieldTenKhachHang, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooserCheckIn, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jSpinFieldThoiGianDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel11)
                            .addComponent(jLabel18))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTenNhanVien)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jDateChooserCheckOut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jRadioButtonDat)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jRadioButtonDatTruoc)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jRadioButtonNgay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonGio)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jDateChooserCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jTextFieldTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(jTextFieldSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(jRadioButtonDat)
                                .addComponent(jRadioButtonDatTruoc)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20)
                                    .addComponent(jRadioButtonNgay)
                                    .addComponent(jRadioButtonGio))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jDateChooserCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)))
                            .addComponent(jSpinFieldThoiGianDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Dịch vụ:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Số lượng:");

        jComboBoxDichVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDichVuActionPerformed(evt);
            }
        });

        jButtonThemDichVu.setText("Thêm dịch vụ");
        jButtonThemDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemDichVuActionPerformed(evt);
            }
        });

        jTextFieldGiaDichVu.setEnabled(false);
        jTextFieldGiaDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGiaDichVuActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Giá:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButtonThemDichVu)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSpinnerSoLuongDichVu)
                            .addComponent(jTextFieldGiaDichVu)
                            .addComponent(jComboBoxDichVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(141, 141, 141))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jComboBoxDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSpinnerSoLuongDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldGiaDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jButtonThemDichVu)
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Dịch vụ:");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Voucher:");

        jTextFieldTongTien.setEnabled(false);
        jTextFieldTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTongTienActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Tổng tiền:");

        jTextFieldTienCoc.setEnabled(false);
        jTextFieldTienCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTienCocActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Tiền Cọc:");

        jComboBoxVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVoucherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel19))
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldTienCoc)
                    .addComponent(jComboBoxVoucher, 0, 302, Short.MAX_VALUE)
                    .addComponent(jTextFieldTongTien))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBoxVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1141, Short.MAX_VALUE))
        );

        jButtonXacNhan.setText("Xác nhận");

        jButtonReset.setText("Reset");

        jButtonHuy.setText("Hủy");
        jButtonHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jButtonXacNhan)
                .addGap(18, 18, 18)
                .addComponent(jButtonReset)
                .addGap(18, 18, 18)
                .addComponent(jButtonHuy)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonXacNhan)
                    .addComponent(jButtonReset)
                    .addComponent(jButtonHuy))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Giá"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(243, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

	private void jRadioButtonDatStateChanged(javax.swing.event.ChangeEvent evt) {                                             
	    if (jRadioButtonDat.isSelected()) {
	        jDateChooserCheckIn.setEnabled(false);
	    	jDateChooserCheckOut.setEnabled(false);// Làm mờ, không cho phép chọn
	    } else {
	        jDateChooserCheckOut.setEnabled(false);  // Kích hoạt lại nếu bỏ chọn
	    }
	}


    private void jRadioButtonDatTruocStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonDatTruocStateChanged
    	if (jRadioButtonDatTruoc.isSelected()) {
    		jDateChooserCheckIn.setEnabled(true);
	        jDateChooserCheckOut.setEnabled(false); // Làm mờ, không cho phép chọn
	    } else {
	        jDateChooserCheckOut.setEnabled(false);
	        jDateChooserCheckIn.setEnabled(false);// Kích hoạt lại nếu bỏ chọn
	    }
    }//GEN-LAST:event_jRadioButtonDatTruocStateChanged

    private void jRadioButtonNgayStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonNgayStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonNgayStateChanged

    private void jRadioButtonGioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonGioStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonGioStateChanged

	private void jTextFieldSoDienThoaiFocusLost(java.awt.event.FocusEvent evt) {                                                 
	    String soDienThoai = jTextFieldSoDienThoai.getText(); // Lấy số điện thoại từ text field
	    KhachHang khachHang = kh_dao.timKhachHangTheoSoDienThoai(soDienThoai); // Gọi hàm tìm khách hàng theo số điện thoại
	    
	    if (khachHang != null) {
	        jTextFieldTenKhachHang.setText(khachHang.getTenKhachHang()); // Nếu tìm thấy, hiển thị tên khách hàng
	        // Bạn có thể đặt thêm các trường thông tin khác nếu cần, ví dụ:
	        // jTextFieldDiaChi.setText(khachHang.getDiaChi());
	    } else {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng với số điện thoại này.", "Thông báo",
	                JOptionPane.INFORMATION_MESSAGE); // Hiển thị thông báo nếu không tìm thấy khách hàng
	    }
	}


	private void jTextFieldSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldSoDienThoaiActionPerformed
	}// GEN-LAST:event_jTextFieldSoDienThoaiActionPerformed
	

	
	private void jTextFieldTenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldTenNhanVienActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextFieldTenNhanVienActionPerformed

	private void jComboBoxThietBiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxThietBiActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jComboBoxThietBiActionPerformed

	private void jComboBoxDichVuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxDichVuActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jComboBoxDichVuActionPerformed

	private void jTextFieldTongTienActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldTongTienActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextFieldTongTienActionPerformed

	private void jTextFieldTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldTenKhachHangActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextFieldTenKhachHangActionPerformed

	private void jTextFieldTienCocActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldTienCocActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextFieldTienCocActionPerformed

	private void jButtonThemThietBiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonThemThietBiActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButtonThemThietBiActionPerformed

	private void jButtonThemDichVuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonThemDichVuActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButtonThemDichVuActionPerformed

	private void jTextFieldGiaDichVuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldGiaDichVuActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextFieldGiaDichVuActionPerformed

	private void jTextFieldGiaThietBiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldGiaThietBiActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextFieldGiaThietBiActionPerformed

	private void jButtonHuyActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonHuyActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButtonHuyActionPerformed

	private void jRadioButtonDatTruocActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonDatTruocActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jRadioButtonDatTruocActionPerformed

	private void jRadioButtonDatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonDatActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jRadioButtonDatActionPerformed

	private void jComboBoxVoucherActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxVoucherActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jComboBoxVoucherActionPerformed

	private void jRadioButtonGioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonGioActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jRadioButtonGioActionPerformed

	private void jRadioButtonNgayActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonNgayActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jRadioButtonNgayActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButtonHuy;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonThemDichVu;
    private javax.swing.JButton jButtonXacNhan;
    private javax.swing.JComboBox<String> jComboBoxDichVu;
    private javax.swing.JComboBox<String> jComboBoxVoucher;
    private com.toedter.calendar.JDateChooser jDateChooserCheckIn;
    private com.toedter.calendar.JDateChooser jDateChooserCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButtonDat;
    private javax.swing.JRadioButton jRadioButtonDatTruoc;
    private javax.swing.JRadioButton jRadioButtonGio;
    private javax.swing.JRadioButton jRadioButtonNgay;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.components.JSpinField jSpinFieldThoiGianDat;
    private javax.swing.JSpinner jSpinnerSoLuongDichVu;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldGiaDichVu;
    private javax.swing.JTextField jTextFieldSoDienThoai;
    private javax.swing.JTextField jTextFieldTenKhachHang;
    private javax.swing.JTextField jTextFieldTenNhanVien;
    private javax.swing.JTextField jTextFieldTienCoc;
    private javax.swing.JTextField jTextFieldTongTien;
    // End of variables declaration//GEN-END:variables
}
