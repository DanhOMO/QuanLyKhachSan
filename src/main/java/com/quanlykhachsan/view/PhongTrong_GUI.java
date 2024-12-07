/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.quanlykhachsan.controller.ChuyenManHinh;
import com.quanlykhachsan.dao.Phong_DAO;
import com.quanlykhachsan.entity.Phong;
import com.quanlykhachsan.enum_Class.TrangThaiPhong;

/**
 *
 * @author liemh
 */
public class PhongTrong_GUI extends javax.swing.JPanel {

	/**
	 * Creates new form PhongTrong_GUI
	 */
	private Phong phong;
	private Phong_DAO p_dao = new Phong_DAO(); 
	public PhongTrong_GUI(Phong phong) {
		this.phong = phong;
		initComponents();
	}



	public Phong getPhong() {
		return phong;
	}



	public void setPhong(Phong phong) {
		this.phong = phong;
	}



	public javax.swing.JLabel getjLabeltenPhong() {
		return jLabeltenPhong;
	}

	public void setjLabeltenPhong(String tenPhong) {
		this.jLabeltenPhong.setText(tenPhong);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabeltenPhong = new javax.swing.JLabel();
        jCheckBoxDuocChon = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(150, 150));

        jPanel2.setBackground(new java.awt.Color(255, 253, 192));
        jPanel2.setMinimumSize(new java.awt.Dimension(150, 150));
        jPanel2.setPreferredSize(new java.awt.Dimension(150, 150));

        jLabel1.setText("Phòng-KV: ");

        jLabeltenPhong.setText("PhongXXX-A");

        jCheckBoxDuocChon.setText("Chọn");
        jCheckBoxDuocChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxDuocChonActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/icons8-room-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabeltenPhong))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jCheckBoxDuocChon)))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabeltenPhong))
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jCheckBoxDuocChon)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxDuocChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxDuocChonActionPerformed
        if(jCheckBoxDuocChon.isSelected()) {
        	phong.setTrangThai(TrangThaiPhong.DA_CHON);
        }else {
        	phong.setTrangThai(TrangThaiPhong.TRONG);
        }
    }//GEN-LAST:event_jCheckBoxDuocChonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxDuocChon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabeltenPhong;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
