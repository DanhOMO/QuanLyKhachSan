/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import com.quanlykhachsan.controller.RoundBorder;
import com.quanlykhachsan.dao.ChiTietHoaDon_DAO;
import com.quanlykhachsan.dao.DichVu_DAO;
import com.quanlykhachsan.dao.HoaDon_DAO;
import com.quanlykhachsan.dao.KhachHang_DAO;
import com.quanlykhachsan.dao.LichSuDatDichVu_DAO;
import com.quanlykhachsan.dao.LichSuDatPhong_DAO;
import com.quanlykhachsan.dao.LoaiPhong_DAO;
import com.quanlykhachsan.dao.NhanVien_DAO;
import com.quanlykhachsan.dao.Phong_DAO;
import javax.swing.JFrame;

import com.quanlykhachsan.entity.Phong;

/**
 *
 * @author liemh
 */
public class PhongDaDat_GUI extends javax.swing.JPanel {
    private Phong phong;
    private KhachHang_DAO kh_dao;
	private DichVu_DAO dv_dao;
	private LoaiPhong_DAO lp_dao;
	private ChiTietHoaDon_DAO cthd_dao;
	private LichSuDatDichVu_DAO lsddv_dao;
	private LichSuDatPhong_DAO lsdp_dao;
	private HoaDon_DAO hd_dao;
	private NhanVien_DAO nv_dao;
	private Phong_DAO p_dao;
  
	
	
	
        public Phong getPhong() {
		return phong;
	}



	public void setPhong(Phong phong) {
		this.phong = phong;
	}
    public PhongDaDat_GUI(Phong phong) {
        
        this.phong = phong;
        initComponents();
        jPanel1.setBorder(new RoundBorder(20));
    }
    

    public String getjLabelCheckIn() {
		return jLabelCheckIn.getText();
	}


	public void setjLabelCheckIn(String jLabelCheckIn) {
		this.jLabelCheckIn.setText(jLabelCheckIn);
	}


	public String getjLabelCheckOut() {
		return jLabelCheckOut.getText();
	}


	public void setjLabelCheckOut(String jLabelCheckOut) {
		this.jLabelCheckOut.setText(jLabelCheckOut);
	}


	public String getjLabelTenKhachHang() {
		return jLabelTenKhachHang.getText();
	}


	public void setjLabelTenKhachHang(String jLabelTenKhachHang) {
		this.jLabelTenKhachHang.setText(jLabelTenKhachHang);
	}


	public String getjLabelTenPhong() {
		return jLabelTenPhong.getText();
	}


	public void setjLabelTenPhong(String jLabelTenPhong) {
		this.jLabelTenPhong.setText(jLabelTenPhong);
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
        jButtonDoiPhong = new javax.swing.JButton();
        jButtonThemDichVu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelTenPhong = new javax.swing.JLabel();
        jLabelTenKhachHang = new javax.swing.JLabel();
        jLabelCheckIn = new javax.swing.JLabel();
        jLabelCheckOut = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(125, 125, 125));
        jPanel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel1.setMinimumSize(new java.awt.Dimension(150, 150));
        jPanel1.setPreferredSize(new java.awt.Dimension(150, 150));

        jButtonDoiPhong.setText("Đổi Phòng");
        jButtonDoiPhong.setPreferredSize(new java.awt.Dimension(100, 25));
        jButtonDoiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoiPhongActionPerformed(evt);
            }
        });

        jButtonThemDichVu.setText("Thêm Dịch Vụ");
        jButtonThemDichVu.setPreferredSize(new java.awt.Dimension(100, 25));
        jButtonThemDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemDichVuActionPerformed(evt);
            }
        });

        jLabel1.setText("Phòng-KV:");

        jLabel3.setText("Khách Hàng:");

        jLabel4.setText("Check In:");

        jLabel5.setText("Check Out:");

        jLabelTenPhong.setText("PhongXXX-A");

        jLabelTenKhachHang.setText("TQB");

        jLabelCheckIn.setText("14h-20/8");

        jLabelCheckOut.setText("14h-21/8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelTenPhong))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelCheckOut))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCheckIn)
                                    .addComponent(jLabelTenKhachHang)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonThemDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(jButtonDoiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(3, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelTenPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelTenKhachHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelCheckIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelCheckOut))
                .addGap(4, 4, 4)
                .addComponent(jButtonThemDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDoiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDoiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoiPhongActionPerformed
        // TODO add your handling code here:
        try {
			
			DoiPhong_GUI dp = new DoiPhong_GUI(phong);
			
			dp.setExtendedState(JFrame.MAXIMIZED_BOTH);
			dp.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			// Có thể thêm thông báo cho người dùng về lỗi
		}
        
    }//GEN-LAST:event_jButtonDoiPhongActionPerformed

    private void jButtonThemDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemDichVuActionPerformed
    	try {
			JFrame jframe = new JFrame();
			ThemDichVu_GUI ttdp = new ThemDichVu_GUI(phong, jframe);
			jframe.add(ttdp);
			jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
			jframe.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			// Có thể thêm thông báo cho người dùng về lỗi
		}

    }//GEN-LAST:event_jButtonThemDichVuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDoiPhong;
    private javax.swing.JButton jButtonThemDichVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelCheckIn;
    private javax.swing.JLabel jLabelCheckOut;
    private javax.swing.JLabel jLabelTenKhachHang;
    private javax.swing.JLabel jLabelTenPhong;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
