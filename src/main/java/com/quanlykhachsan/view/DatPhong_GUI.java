/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.quanlykhachsan.dao.HoaDon_DAO;
import com.quanlykhachsan.dao.KhachHang_DAO;
import com.quanlykhachsan.dao.KhuVuc_DAO;
import com.quanlykhachsan.dao.LoaiPhong_DAO;
import com.quanlykhachsan.dao.Phong_DAO;
import com.quanlykhachsan.entity.DichVu;
import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.KhuVuc;
import com.quanlykhachsan.enum_Class.TrangThaiPhong;
import com.quanlykhachsan.entity.LoaiPhong;
import com.quanlykhachsan.entity.Phong;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author liemh
 */
public class DatPhong_GUI extends javax.swing.JPanel {

	private Phong_DAO p_dao;
	private HoaDon_DAO hd_dao;
	private KhuVuc_DAO kv_dao;
	private LoaiPhong_DAO lp_dao;
	private KhachHang_DAO kh_dao = new KhachHang_DAO();
	private List<Phong> dsLoc = new ArrayList<Phong>();
	/**
	 * Creates new form DatPhong
	 */
	public DatPhong_GUI() {
		initComponents();

		p_dao = new Phong_DAO();
		hd_dao = new HoaDon_DAO();
		kv_dao = new KhuVuc_DAO();
		lp_dao = new LoaiPhong_DAO();
		jDateChooserCheckIn.setDate(java.sql.Date.valueOf(LocalDate.now()));
		loadComboxLoaiPhong();
		loadComboxKhuVuc();
		spinnerSL.setValue(1);
		p_dao.docTuBang();
		dsLoc = p_dao.getList();
		showAllRooms(dsLoc);
	}

	private void loadComboxKhuVuc() {
		jComboBoxKhuVuc.removeAllItems();
		jComboBoxKhuVuc.addItem("Tất cả");
		ArrayList<KhuVuc> dskv = new ArrayList<KhuVuc>();
		dskv = kv_dao.getDsKhuVuc();
		dskv.stream().forEach(x -> {
			jComboBoxKhuVuc.addItem(x.getTenKhuVuc());
		});
	}

	private void loadComboxLoaiPhong() {
		jComboBoxLoaiPhong.removeAllItems();
		jComboBoxLoaiPhong.addItem("Tất cả");
		List<LoaiPhong> dslp = new ArrayList<>();
		lp_dao.docTuBang();
		dslp = lp_dao.getList();

		dslp.stream().forEach(x -> {
			jComboBoxLoaiPhong.addItem(x.getTenLoaiPhong());
		});
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonPhongTrong = new javax.swing.JButton();
        jButtonPhongDaDat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxKhuVuc = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxLoaiPhong = new javax.swing.JComboBox<>();
        jButtonPhongDaCoc = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jTextFieldTimKiem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButtonLamMoi = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        spinnerSL = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        checkOut = new javax.swing.JLabel();
        radioNgay = new javax.swing.JRadioButton();
        radioGio = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jDateChooserCheckIn = new com.toedter.calendar.JDateChooser();
        jDateChooserCheckOut = new com.toedter.calendar.JDateChooser();
        jButtonDatPhong = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();

        setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        setPreferredSize(new java.awt.Dimension(1220, 727));

        jPanel1.setMinimumSize(new java.awt.Dimension(1220, 868));

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jButtonPhongTrong.setBackground(new java.awt.Color(153, 255, 153));
        jButtonPhongTrong.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonPhongTrong.setText("Phòng Trống");
        jButtonPhongTrong.setPreferredSize(new java.awt.Dimension(130, 30));
        jButtonPhongTrong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPhongTrong(evt);
            }
        });

        jButtonPhongDaDat.setBackground(new java.awt.Color(255, 0, 0));
        jButtonPhongDaDat.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonPhongDaDat.setText("Phòng Đã Đặt");
        jButtonPhongDaDat.setMinimumSize(new java.awt.Dimension(130, 30));
        jButtonPhongDaDat.setPreferredSize(new java.awt.Dimension(130, 30));
        jButtonPhongDaDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPhongDaDat(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Loại phòng:");

        jComboBoxKhuVuc.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBoxKhuVuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxKhuVuc(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Khu vực:");

        jComboBoxLoaiPhong.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBoxLoaiPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxLoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLoaiPhong(evt);
            }
        });

        jButtonPhongDaCoc.setBackground(new java.awt.Color(255, 153, 51));
        jButtonPhongDaCoc.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonPhongDaCoc.setText("Phòng Đã Cọc");
        jButtonPhongDaCoc.setMinimumSize(new java.awt.Dimension(130, 30));
        jButtonPhongDaCoc.setPreferredSize(new java.awt.Dimension(130, 30));
        jButtonPhongDaCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPhongDaCocActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(0, 204, 204));
        btnThanhToan.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setPreferredSize(new java.awt.Dimension(130, 30));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanjButtonPhongTrong(evt);
            }
        });

        jTextFieldTimKiem.setText("Nhập mã phòng"); // NOI18N
        jTextFieldTimKiem.setToolTipText("phòng");
        jTextFieldTimKiem.setPreferredSize(new java.awt.Dimension(150, 30));
        jTextFieldTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTimKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldTimKiemFocusLost(evt);
            }
        });
        jTextFieldTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTimKiemKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Tìm kiếm:");

        jButtonLamMoi.setBackground(new java.awt.Color(0, 204, 204));
        jButtonLamMoi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonLamMoi.setText("Refresh");
        jButtonLamMoi.setPreferredSize(new java.awt.Dimension(130, 30));
        jButtonLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLamMoiActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Số lượng khách");

        spinnerSL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerSLStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Checkin");

        checkOut.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        checkOut.setText("Checkout");

        radioNgay.setText("Ngày");

        radioGio.setText("Giờ");
        radioGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioGioActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Hình thức");

        jDateChooserCheckIn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserCheckInPropertyChange(evt);
            }
        });

        jDateChooserCheckOut.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserCheckOutPropertyChange(evt);
            }
        });

        jButtonDatPhong.setText("Đặt");
        jButtonDatPhong.setPreferredSize(new java.awt.Dimension(130, 30));
        jButtonDatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDatPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButtonPhongDaDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonPhongDaCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonPhongTrong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(275, 275, 275)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jDateChooserCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkOut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(radioNgay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioGio)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(5, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonPhongDaDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonPhongDaCoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonPhongTrong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioNgay)
                            .addComponent(radioGio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(spinnerSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserCheckOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserCheckIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(checkOut)
                                .addComponent(jButtonDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1259, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLamMoiActionPerformed
    	jComboBoxKhuVuc.setSelectedIndex(0);
		jComboBoxLoaiPhong.setSelectedIndex(0);
		spinnerSL.setValue(1);
		p_dao.docTuBang();
		dsLoc = p_dao.getList();
		showAllRooms(dsLoc);
    }//GEN-LAST:event_jButtonLamMoiActionPerformed

    private void btnThanhToanjButtonPhongTrong(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanjButtonPhongTrong
    	JFrame gd = new JFrame();
		gd.add(new ThanhToan_GUI());
		gd.setVisible(true);
		gd.setSize(810, 610);
    }//GEN-LAST:event_btnThanhToanjButtonPhongTrong

    private void jButtonPhongDaCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPhongDaCocActionPerformed
    	locDuLieu();
		List<com.quanlykhachsan.entity.Phong> dsPhong = new ArrayList<Phong>();
		dsPhong = dsLoc.stream().filter(x -> x.getTrangThai() == TrangThaiPhong.DA_COC).toList();
		showAllRooms(dsPhong);
    }//GEN-LAST:event_jButtonPhongDaCocActionPerformed

    private void jComboBoxLoaiPhong(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLoaiPhong
    	locDuLieu();
    	showAllRooms(dsLoc);
    }//GEN-LAST:event_jComboBoxLoaiPhong

    private void locTheoKhuVuc() {
    	int i = jComboBoxKhuVuc.getSelectedIndex();
		if (i == 0) {
			return;
		} else {
			List<KhuVuc> dskv = kv_dao.getDsKhuVuc();
			if (i > 0 && i <= dskv.size()) {
				KhuVuc kv = dskv.stream()
						.filter(x -> x.getTenKhuVuc().equals(jComboBoxKhuVuc.getSelectedItem().toString())).findFirst()
						.orElse(null);
				p_dao.timPhongTheoKhuVuc(kv);
				dsLoc = p_dao.getList();
			}
		}
		
	}

	private void jComboBoxKhuVuc(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxKhuVuc
    	locDuLieu();
    	showAllRooms(dsLoc);
    }//GEN-LAST:event_jComboBoxKhuVuc

    private void locTheoLoaiPhong() {
    	int i = jComboBoxLoaiPhong.getSelectedIndex();
		if (i == 0) {
			return;
		} else {
			List<LoaiPhong> dslp = lp_dao.getList();
			if (i > 0 && i <= dslp.size()) {
				LoaiPhong lp = dslp.stream()
						.filter(x -> x.getTenLoaiPhong().equals(jComboBoxLoaiPhong.getSelectedItem().toString()))
						.findFirst().orElse(null);
				p_dao.timPhongTheoLoaiPhong(lp.getMaLoaiPhong());
				dsLoc = p_dao.getList();
			}
		}	
	}

	private void jButtonPhongDaDat(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPhongDaDat
		locDuLieu();
		List<com.quanlykhachsan.entity.Phong> dsPhong = new ArrayList<Phong>();
		dsPhong = dsLoc.stream().filter(x -> x.getTrangThai() == TrangThaiPhong.DA_DAT).toList();
		showAllRooms(dsPhong);
    }//GEN-LAST:event_jButtonPhongDaDat

    private void jButtonPhongTrong(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPhongTrong
    	locDuLieu();
    	List<com.quanlykhachsan.entity.Phong> dsPhong = new ArrayList<Phong>();
		dsPhong = dsLoc.stream().filter(x -> x.getTrangThai() == TrangThaiPhong.TRONG).toList();
		showAllRooms(dsPhong);
    }//GEN-LAST:event_jButtonPhongTrong

    private void locDuLieu() {
		p_dao.docTuBang();
    	dsLoc = p_dao.getList();
    	locTheoLoaiPhong();
    	locTheoKhuVuc();
    	locTheoSoLuongKhach();
    	locTheoCheckIn();
	}

	private void locTheoCheckIn() {	
		java.util.Date checkIn = jDateChooserCheckIn.getDate();
		java.sql.Date checkInSqlDate = new java.sql.Date(checkIn.getTime());
		List<Phong> dsPhong = p_dao.TimPhongTheoThoiGianCheckIn(checkInSqlDate);
		List<Phong> temp = dsLoc.stream()
			    .filter(phong -> !dsPhong.contains(phong))
			    .collect(Collectors.toList());
		dsLoc = temp;
	}

	private void locTheoSoLuongKhach() {
		int soLuongKhach = (Integer) spinnerSL.getValue();
		List<Phong> dsPhong = p_dao.timPhongTheoSoLuongNguoi(soLuongKhach);
		List<Phong> temp = dsLoc.stream()
		           .filter(dsPhong::contains) // lọc những phòng có trong dsPhong
		           .collect(Collectors.toList()); // thu thập các phần tử chung vào danh sách
		dsLoc = temp;
	}

	private void radioGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioGioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioGioActionPerformed

    private void spinnerSLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerSLStateChanged
       locDuLieu();
       showAllRooms(dsLoc);
    }//GEN-LAST:event_spinnerSLStateChanged

    private void jDateChooserCheckInPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserCheckInPropertyChange
    	locDuLieu();
        showAllRooms(dsLoc);
    }//GEN-LAST:event_jDateChooserCheckInPropertyChange

    private void jDateChooserCheckOutPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserCheckOutPropertyChange
        locDuLieu();
        showAllRooms(dsLoc);
    }//GEN-LAST:event_jDateChooserCheckOutPropertyChange

    private void jButtonDatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDatPhongActionPerformed
        List<Phong> dsPhong = dsLoc.stream()
        		.filter(x->x.getTrangThai().equals(TrangThaiPhong.DA_DAT))
        		.toList();
    	try {
			JFrame jframe = new JFrame();
			ThongTinDatPhong ttdp = new ThongTinDatPhong(dsPhong, jframe);
			jframe.add(ttdp);
			jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
			jframe.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			// Có thể thêm thông báo cho người dùng về lỗi
		}
    }//GEN-LAST:event_jButtonDatPhongActionPerformed


	private void jTextFieldTimKiemKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextFieldTimKiemKeyReleased
		String maPhong = jTextFieldTimKiem.getText();
		List<Phong> dsP = new ArrayList<Phong>();
		if (maPhong.length() == 4) {
			p_dao.setList(dsLoc);
			Phong p = p_dao.timTheoMa(maPhong);
			if (p != null) {
				dsP.add(p);
				showAllRooms(dsP);
			}

		}
		showAllRooms(dsP);

	}// GEN-LAST:event_jTextFieldTimKiemKeyReleased

	private void jTextFieldTimKiemFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextFieldTimKiemFocusGained
		jTextFieldTimKiem.setText("");
	}// GEN-LAST:event_jTextFieldTimKiemFocusGained

	private void jTextFieldTimKiemFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextFieldTimKiemFocusLost
		jTextFieldTimKiem.setText("");
		jTextFieldTimKiem.setText("Nhập mã phòng");
		showAllRooms(dsLoc);
	}// GEN-LAST:event_jTextFieldTimKiemFocusLost

	private void showAllRooms(List<com.quanlykhachsan.entity.Phong> dsPhong) {
		jPanel3.removeAll();
		int tongPhong = dsPhong.size();
		int panelTemp = 20 - tongPhong;
		// Đặt layout cho jPanel3 để các phòng sắp xếp dễ nhìn
		jPanel3.setLayout(new java.awt.GridLayout(0, 4, 10, 10));
		for (com.quanlykhachsan.entity.Phong phong : dsPhong) {
			if (phong.getTrangThai() == TrangThaiPhong.TRONG) {
				PhongTrong_GUI phongTrong = new PhongTrong_GUI(phong);
				phongTrong.setjLabeltenPhong(phong.getTenPhong() + '-' + phong.getKhuVuc().getMaKhuVuc());
				phongTrong.setPreferredSize(new java.awt.Dimension(150, 150)); // Kích thước
				// của mỗi panel phòng
				jPanel3.add(phongTrong); 
			} else if (phong.getTrangThai() == TrangThaiPhong.DA_DAT) {
				PhongDaDat_GUI phongDaDat = new PhongDaDat_GUI(phong);
				phongDaDat.setjLabelTenPhong(phong.getTenPhong() + '-' + phong.getKhuVuc().getMaKhuVuc());
				List<HoaDon> dshd = new ArrayList<HoaDon>();
				dshd = hd_dao.timTheoMaPhong(phong.getMaPhong());
				if (dshd.size() >= 1) {
					HoaDon hd = dshd.get(dshd.size() - 1);// getLast
					phongDaDat.setjLabelTenKhachHang(hd.getKhachHang().getTenKhachHang());
					phongDaDat.setjLabelCheckIn(hd.getCheckIn().toString());
					phongDaDat.setjLabelCheckOut(hd.getCheckOut().toString());
					phongDaDat.setPreferredSize(new java.awt.Dimension(150, 150)); // Kích thước
					// của mỗi panel phòng
					jPanel3.add(phongDaDat);
				}
			} else if (phong.getTrangThai() == TrangThaiPhong.DA_COC) {
				PhongDaDatTruoc_GUI phongDaDat = new PhongDaDatTruoc_GUI(phong);
				phongDaDat.setjLabelTenPhong(phong.getTenPhong() + '-' + phong.getKhuVuc().getMaKhuVuc());
				hd_dao.timTheoMaPhong(phong.getMaPhong());
				List<HoaDon> dshd = new ArrayList<HoaDon>();
				dshd = hd_dao.getList();
				HoaDon hd = dshd.get(dshd.size() - 1);//getLast		
				KhachHang kh = kh_dao.timTheoMa(hd.getKhachHang().getMaKhachHang());
				if(kh == null)
					phongDaDat.setjLabelTenKhachHang("");
				phongDaDat.setjLabelTenKhachHang(kh.getTenKhachHang());
				phongDaDat.setjLabelCheckIn(hd.getCheckIn().toString());
				phongDaDat.setjLabelCheckOut("Chưa Xác Định");
				phongDaDat.setPreferredSize(new java.awt.Dimension(150, 150)); // Kích thước
				// của mỗi panel phòng
				jPanel3.add(phongDaDat);
			}
//			for (int i = 0; i < panelTemp; i++) {
//				JPanel temp = new JPanel();
//				temp.setPreferredSize(new java.awt.Dimension(150, 150));
//				jPanel3.add(temp);
//			}

		}

		// Cập nhật lại jPanel3
		jPanel3.revalidate();
		jPanel3.repaint();
	}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JLabel checkOut;
    private javax.swing.JButton jButtonDatPhong;
    private javax.swing.JButton jButtonLamMoi;
    private javax.swing.JButton jButtonPhongDaCoc;
    private javax.swing.JButton jButtonPhongDaDat;
    private javax.swing.JButton jButtonPhongTrong;
    private javax.swing.JComboBox<String> jComboBoxKhuVuc;
    private javax.swing.JComboBox<String> jComboBoxLoaiPhong;
    private com.toedter.calendar.JDateChooser jDateChooserCheckIn;
    private com.toedter.calendar.JDateChooser jDateChooserCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldTimKiem;
    private javax.swing.JRadioButton radioGio;
    private javax.swing.JRadioButton radioNgay;
    private javax.swing.JSpinner spinnerSL;
    // End of variables declaration//GEN-END:variables
}
