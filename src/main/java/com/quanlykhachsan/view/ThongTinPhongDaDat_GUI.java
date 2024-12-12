
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.quanlykhachsan.dao.ChiTietHoaDon_DAO;
import com.quanlykhachsan.dao.DichVu_DAO;
import com.quanlykhachsan.dao.HoaDon_DAO;
import com.quanlykhachsan.dao.KhachHang_DAO;
import com.quanlykhachsan.dao.LichSuDatDichVu_DAO;
import com.quanlykhachsan.dao.LichSuDatPhong_DAO;
import com.quanlykhachsan.dao.LoaiPhong_DAO;
import com.quanlykhachsan.dao.NhanVien_DAO;
import com.quanlykhachsan.dao.Phong_DAO;
import com.quanlykhachsan.entity.ChiTietHoaDon;
import com.quanlykhachsan.entity.DichVu;
import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.LichSuDatDichVu;
import com.quanlykhachsan.entity.LichSuDatPhong;

import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.entity.Phong;

import com.quanlykhachsan.enum_Class.TrangThaiHoaDon;
import com.quanlykhachsan.enum_Class.TrangThaiPhong;
import com.quanlykhachsan.enum_Class.TrangThaiTaiKhoan;
import com.quanlykhachsan.entity.LoaiPhong;
import com.quanlykhachsan.entity.Voucher;
import java.awt.event.ActionEvent;

/**
 *
 * @author liemh
 */
public class ThongTinPhongDaDat_GUI extends javax.swing.JPanel {

	/**
	 * Creates new form ThongTinDatPhong
	 */
	private final double coc = 0.2;
	private KhachHang_DAO kh_dao;
	private DichVu_DAO dv_dao;
	private LoaiPhong_DAO lp_dao;
	private ChiTietHoaDon_DAO cthd_dao;
	private LichSuDatDichVu_DAO lsddv_dao;
	private LichSuDatPhong_DAO lsdp_dao;
	private HoaDon_DAO hd_dao;
	private NhanVien_DAO nv_dao;
	private Phong_DAO p_dao;
	private boolean thayDoi=false;
	private DefaultTableModel modelDichVu = new DefaultTableModel(new String [] {
            "Mã Dịch vụ", "Tên Dịch Vụ", "Số Lượng", "Thành Tiền"
        }, 0);
	private Phong phong;
	private HoaDon hd;
	private JFrame parentFrame;
	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public ThongTinPhongDaDat_GUI(Phong phong, JFrame parentFrame,HoaDon hd) {
		this.hd = hd;
		this.phong = phong;
		this.parentFrame = parentFrame;
		initComponents();
		p_dao = new Phong_DAO();
		kh_dao = new KhachHang_DAO();
		dv_dao = new DichVu_DAO();
		lp_dao = new LoaiPhong_DAO();
		cthd_dao = new ChiTietHoaDon_DAO();
		lsddv_dao = new LichSuDatDichVu_DAO();
		lsdp_dao = new LichSuDatPhong_DAO();
		hd_dao = new HoaDon_DAO();
		nv_dao = new NhanVien_DAO();
		//
		//List<HoaDon> dshd = new ArrayList<HoaDon>();
		//dshd = hd_dao.timTheoMaPhong(phong.getMaPhong());
		//HoaDon hd = dshd.get(dshd.size() - 1);//getLast	
		long daysBetween = ChronoUnit.DAYS.between(hd.getCheckIn(), hd.getCheckOut());
		jSpinFieldThoiGianDat.setValue(Math.round(daysBetween)+1);
		nv_dao.timNhanVienTheoTrangThaiTaiKhoan(TrangThaiTaiKhoan.DANG_HOAT_DONG);
    	List<NhanVien> dsnv = nv_dao.getList();
		jTextFieldTenNhanVien.setText(dsnv.get(0).getTenNhanVien());
		KhachHang kh = kh_dao.timTheoMa(hd.getKhachHang().getMaKhachHang());
                if(kh != null){
		jTextFieldTenKhachHang.setText(kh.getTenKhachHang());
		jTextFieldSoDienThoai.setText(kh.getSoDienThoai());
                }else{
	                jTextFieldTenKhachHang.setText("");
	                jTextFieldSoDienThoai.setText("");
                }
		// Chuyển đổi từ LocalDate sang java.util.Date
		LocalDateTime checkInDate = hd.getCheckIn();
		LocalDateTime checkOutDate = hd.getCheckOut();

		// Chuyển LocalDateTime sang java.util.Date
		java.util.Date checkInUtilDate = Date.from(checkInDate.atZone(ZoneId.systemDefault()).toInstant());
		java.util.Date checkOutUtilDate = Date.from(checkOutDate.atZone(ZoneId.systemDefault()).toInstant());

		// Gán vào jDateChooser
		
		jTextFieldTienCoc.setText(String.valueOf(hd.getTienCoc()));
		jTextFieldTongTien.setText(String.valueOf(hd.getTongTien()));
		jDateChooserCheckOut.setEnabled(true);
		jDateChooserCheckIn.setDate(checkInUtilDate);
		jDateChooserCheckOut.setDate(checkOutUtilDate);
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
        jLabel21 = new javax.swing.JLabel();
        jSpinFieldThoiGianDat = new com.toedter.components.JSpinField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldTienCoc = new javax.swing.JTextField();
        jTextFieldTongTien = new javax.swing.JTextField();
        jButtonGiaHan = new javax.swing.JButton();
        jButtonHuy = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Tên khách hàng:");

        jTextFieldSoDienThoai.setEnabled(false);
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

        jDateChooserCheckOut.setEnabled(false);
        jDateChooserCheckOut.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserCheckOutPropertyChange(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Check-out:");

        jDateChooserCheckIn.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setText("Thời gian đặt:");

        jSpinFieldThoiGianDat.setEnabled(false);
        jSpinFieldThoiGianDat.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinFieldThoiGianDatPropertyChange(evt);
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
                    .addComponent(jLabel17)
                    .addComponent(jLabel11))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTenNhanVien)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jDateChooserCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextFieldSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(16, 16, 16)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jDateChooserCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)))
                            .addComponent(jSpinFieldThoiGianDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Tiền Cọc:");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Tổng tiền:");

        jTextFieldTienCoc.setEditable(false);
        jTextFieldTienCoc.setEnabled(false);
        jTextFieldTienCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTienCocActionPerformed(evt);
            }
        });

        jTextFieldTongTien.setEnabled(false);
        jTextFieldTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTongTienActionPerformed(evt);
            }
        });

        jButtonGiaHan.setText("Gia Hạn");
        jButtonGiaHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGiaHanActionPerformed(evt);
            }
        });

        jButtonHuy.setText("Hủy");
        jButtonHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldTienCoc, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)))
                .addGap(230, 230, 230))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(jButtonGiaHan)
                .addGap(40, 40, 40)
                .addComponent(jButtonHuy)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jTextFieldTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGiaHan)
                    .addComponent(jButtonHuy))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 646, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 507, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 470, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSpinFieldThoiGianDatPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinFieldThoiGianDatPropertyChange
    //	jTextFieldTongTien.setText( Double.toString(tinhTongTien()));
    }//GEN-LAST:event_jSpinFieldThoiGianDatPropertyChange

    private void jButtonGiaHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGiaHanActionPerformed
//    	List<HoaDon> dshd = new ArrayList<HoaDon>();
//		dshd = hd_dao.timTheoMaPhong(phong.getMaPhong());
//		HoaDon hd = dshd.get(dshd.size() - 1);//getLast	
		long daysBetween = ChronoUnit.DAYS.between(hd.getCheckIn(), hd.getCheckOut());
		int i = Math.round(daysBetween)+1;
    	if(jSpinFieldThoiGianDat.getValue()==i) {
    		thayDoi = false;
    	}
    	if(thayDoi == false) {
    		JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày checkOut mới");
    		return;
    	}
    	
    	int soNgayGiaHan = jSpinFieldThoiGianDat.getValue() - i;
    	if(hd_dao.kiemtraTrungDatPhong(hd,soNgayGiaHan)==true) {
    		JOptionPane.showMessageDialog(this, "Phòng Đã Có người đặt trước không thể gia hạn");
    		return;
    	}
    	java.util.Date date = jDateChooserCheckOut.getDate();
    	LocalDateTime checkOutMoi = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    	checkOutMoi = checkOutMoi.withHour(12).withMinute(0).withSecond(0).withNano(0);
    	double giaMoi = Double.parseDouble(jTextFieldTongTien.getText()) ;
    	hd_dao.capNhatHoaDonCheckOut(hd,checkOutMoi,giaMoi);
    	JOptionPane.showMessageDialog(this, "Gia Hạn Thành Công");
    	parentFrame.dispose();
    }//GEN-LAST:event_jButtonGiaHanActionPerformed

    private void jDateChooserCheckOutPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserCheckOutPropertyChange
        LocalDateTime lastCheckOutDateTime = hd.getCheckOut();
        java.util.Date lastCheckOutUtilDate = Date.from(lastCheckOutDateTime.atZone(ZoneId.systemDefault()).toInstant());

        // Ngày được chọn từ JDateChooser
        java.util.Date selectedCheckOutDate = jDateChooserCheckOut.getDate();

        if (selectedCheckOutDate != null) {
            LocalDate selectedLocalDate = selectedCheckOutDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            // Chuyển đổi `lastCheckOutUtilDate` thành `LocalDate`
            LocalDate lastCheckOutLocalDate = lastCheckOutUtilDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            // Kiểm tra tính hợp lệ
            if (!selectedLocalDate.isBefore(lastCheckOutLocalDate)) {
            	long daysBetween = ChronoUnit.DAYS.between(hd.getCheckIn().toLocalDate(), selectedLocalDate);
        		jSpinFieldThoiGianDat.setValue(Math.round(daysBetween)+1);
        		java.util.Date date = jDateChooserCheckOut.getDate();
            	LocalDateTime checkOutMoi = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
            	checkOutMoi = checkOutMoi.withHour(12).withMinute(0).withSecond(0).withNano(0);
        		thayDoi = true;
        		if(thayDoi == true) {
        			double tienHienTai = hd.getTongTien();
            		tienHienTai = tienHienTai + hd_dao.tinhTienGiaHan(hd,checkOutMoi);
            		jTextFieldTongTien.setText(String.valueOf(tienHienTai));
        		}
            } else {
            	JOptionPane.showMessageDialog(this, "Ngày CheckOut mới phải > Ngày checkOut cũ");
            	jDateChooserCheckOut.setDate(lastCheckOutUtilDate);
            	thayDoi = false;
            }
        } else {
            System.out.println("Vui lòng chọn ngày trả phòng.");
        }
    }//GEN-LAST:event_jDateChooserCheckOutPropertyChange
                                            

	private void jTextFieldSoDienThoaiFocusLost(java.awt.event.FocusEvent evt) {                                                 
		
	}


	private void jTextFieldSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldSoDienThoaiActionPerformed
	}// GEN-LAST:event_jTextFieldSoDienThoaiActionPerformed
// GEN-LAST:event_jTextFieldSoDienThoaiActionPerformed
	

	
	private void jTextFieldTenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldTenNhanVienActionPerformed
		// TODO add your handling code here:
	}
    // GEN-FIRST:event_jComboBoxThietBiActionPerformed
    // TODO add your handling code here:
    // GEN-LAST:event_jComboBoxThietBiActionPerformed
    // GEN-LAST:event_jTextFieldTenNhanVienActionPerformed

	private void jTextFieldTongTienActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldTongTienActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextFieldTongTienActionPerformed

	private void jTextFieldTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldTenKhachHangActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextFieldTenKhachHangActionPerformed
// GEN-LAST:event_jTextFieldTenKhachHangActionPerformed

	private void jTextFieldTienCocActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldTienCocActionPerformed
		// TODO add your handling code here:
	}
    // GEN-FIRST:event_jButtonThemThietBiActionPerformed
    // TODO add your handling code here:
    // GEN-LAST:event_jButtonThemThietBiActionPerformed
    // GEN-LAST:event_jTextFieldTienCocActionPerformed

// GEN-LAST:event_jButtonThemDichVuActionPerformed

	private void jTextFieldGiaDichVuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldGiaDichVuActionPerformed
		// TODO add your handling code here:
	}
    // GEN-FIRST:event_jTextFieldGiaThietBiActionPerformed
    // TODO add your handling code here:
    // GEN-LAST:event_jTextFieldGiaThietBiActionPerformed
    // GEN-LAST:event_jTextFieldGiaDichVuActionPerformed

	private void jButtonHuyActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonHuyActionPerformed
		if (parentFrame != null) {
            parentFrame.dispose(); // Đóng JFrame chứa JPanel này
        }	
	}// GEN-LAST:event_jButtonHuyActionPerformed
		
	private void jRadioButtonDatTruocActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonDatTruocActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jRadioButtonDatTruocActionPerformed
// GEN-LAST:event_jRadioButtonDatTruocActionPerformed

	private void jRadioButtonDatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonDatActionPerformed
		// TODO add your handling code here:
	}
    // GEN-FIRST:event_jComboBoxVoucherActionPerformed
    // TODO add your handling code here:
    // GEN-LAST:event_jComboBoxVoucherActionPerformed
    // GEN-LAST:event_jRadioButtonDatActionPerformed

	private void jRadioButtonGioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonGioActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jRadioButtonGioActionPerformed

	private void jRadioButtonNgayActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonNgayActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jRadioButtonNgayActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButtonGiaHan;
    private javax.swing.JButton jButtonHuy;
    private com.toedter.calendar.JDateChooser jDateChooserCheckIn;
    private com.toedter.calendar.JDateChooser jDateChooserCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private com.toedter.components.JSpinField jSpinFieldThoiGianDat;
    private javax.swing.JTextField jTextFieldSoDienThoai;
    private javax.swing.JTextField jTextFieldTenKhachHang;
    private javax.swing.JTextField jTextFieldTenNhanVien;
    private javax.swing.JTextField jTextFieldTienCoc;
    private javax.swing.JTextField jTextFieldTongTien;
    // End of variables declaration//GEN-END:variables
}
