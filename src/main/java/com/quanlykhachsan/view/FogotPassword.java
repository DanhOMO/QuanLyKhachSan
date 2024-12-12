/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import com.quanlykhachsan.dao.NhanVien_DAO;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.model.ConnectDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author liemh
 */
public class FogotPassword extends JPanel {

    /**
     * Creates new form FogotPassword
     */
    public FogotPassword() {
        initComponents();
        jQuayLai11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(jQuayLai11)).dispose();
                 String input = JOptionPane.showInputDialog("Nhập số điện thoại hoặc email của bạn:");
                Random random = new Random();
                NhanVien_DAO nv = new NhanVien_DAO();
       int soRD = 1000+  random.nextInt(9000);
        // Kiểm tra xem người dùng nhập số điện thoại hay email
        if (input != null && !input.isEmpty()) {
            if (isValidEmail(input)) {
                // Kiểm tra email trong cơ sở dữ liệu
                if (checkEmailInDatabase(input)) {
                        
                        NhanVien a =  nv.timNhanVienTheoEmail(input);
                    try {
                        nv.capNhatMKtuEmail(a.getMaNhanVien(), a.getEmail(), soRD + "");
                    } catch (SQLException ex) {
                        Logger.getLogger(DangNhap_GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        JOptionPane.showMessageDialog(null, "Mật khẩu mới là : "+ soRD);       
                        
                } else
                    JOptionPane.showMessageDialog(null, "Email không tồn tại trong hệ thống.");
                
            }else if (isValidPhoneNumber(input)) {
                // Kiểm tra số điện thoại trong cơ sở dữ liệu
                if (checkPhoneNumberInDatabase(input)) {
                    NhanVien a = nv.timNhanVienTheoSoDienThoai(input);
                    try {
                        nv.capNhatMKtuSDT(a.getMaNhanVien(), a.getSoDienThoai(), soRD +"");
                    } catch (SQLException ex) {
                        Logger.getLogger(DangNhap_GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        JOptionPane.showMessageDialog(null, "Mật khẩu mới là : " + soRD);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Số điện thoại không tồn tại trong hệ thống.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Số điện thoại hoặc email không hợp lệ.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn cần nhập thông tin.");
        }
        
            }
            
        });
         btnXacNhan.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            btnXacNhanActionPerformed(new ActionEvent(btnXacNhan, ActionEvent.ACTION_PERFORMED, null));
        }
    }
});
    
    }
     // Hàm kiểm tra email hợp lệ
    private boolean isValidEmail(String email) {
        // Thêm logic kiểm tra email tại đây
        return email.contains("@");
    }

    // Hàm kiểm tra số điện thoại hợp lệ
    private boolean isValidPhoneNumber(String phone) {
        // Thêm logic kiểm tra số điện thoại tại đây
        return phone.matches("\\d{10}");
    }

    // Hàm kiểm tra email trong cơ sở dữ liệu (mô phỏng)
    private boolean checkEmailInDatabase(String email) {
        NhanVien_DAO a = new NhanVien_DAO();
        if(a.timNhanVienTheoEmail(email) != null) return true;
        return false; // Giả sử email tồn tại
    }

    // Hàm kiểm tra số điện thoại trong cơ sở dữ liệu (mô phỏng)
    private boolean checkPhoneNumberInDatabase(String phone) {
        NhanVien_DAO a = new NhanVien_DAO();
        if(a.timNhanVienTheoSoDienThoai(phone) != null) return true;
        return false; // Giả sử số điện thoại tồn tại
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
//    public static String hashPassword(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hashedBytes = md.digest(password.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for (byte b : hashedBytes) {
//                sb.append(String.format("%02x", b));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    
     public static boolean checkPhoneAndEmail(String phoneNumber, String email) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM TaiKhoan t JOIN NhanVien nv ON nv.maNhanVien = t.maNhanVien WHERE soDienThoai = ? AND email = ? ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, email);
            ResultSet rs = pstmt.executeQuery();
            System.out.print("1");
            return rs.next(); // Trả về true nếu tìm thấy người dùng với thông tin đúng
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jMatKhau = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        txtXacNhanMK = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jQuayLai11 = new javax.swing.JLabel();
        btnXacNhan = new javax.swing.JButton();
        jQuayLai = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(500, 400));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Email");

        txtSDT.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtSDT.setText("Số điện thoại");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Số điên thoại");

        txtEmail.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jMatKhau.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jMatKhau.setText("Mật khẩu");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Xác nhận mật khẩu");

        txtMatKhau.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        txtXacNhanMK.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(58, 186, 178));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Thay Đổi Mật Khẩu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(133, 133, 133)
                    .addComponent(jLabel1)
                    .addContainerGap(153, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 62, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(58, 186, 178));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jQuayLai11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jQuayLai11.setForeground(new java.awt.Color(255, 51, 51));
        jQuayLai11.setText("Làm Mới Mật Khẩu");
        jQuayLai11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jQuayLai11MouseClicked(evt);
            }
        });
        jPanel3.add(jQuayLai11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 26));

        btnXacNhan.setBackground(new java.awt.Color(255, 51, 102));
        btnXacNhan.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });
        jPanel3.add(btnXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 154, -1));

        jQuayLai.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jQuayLai.setForeground(new java.awt.Color(255, 51, 51));
        jQuayLai.setText("Quay lại đăng nhập?");
        jQuayLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jQuayLaiMouseClicked(evt);
            }
        });
        jPanel3.add(jQuayLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, -1, 26));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jMatKhau))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(txtMatKhau)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtXacNhanMK)))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMatKhau)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtXacNhanMK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 338, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(318, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        String mail=txtEmail.getText();
        String phone=txtSDT.getText();
        checkPhoneAndEmail(phone, mail);
        String hashedPassword1 = (txtMatKhau.getText());
        String hashedPassword2 = (txtXacNhanMK.getText());
        System.out.print("2");
        if(!hashedPassword1.equals(hashedPassword2)){
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận sai");
        }
        String hashedNewPassword = (hashedPassword1);

     try {
    Connection con = ConnectDB.getInstance().getConnection();
    System.out.println("Connection established");
    
    String sql = "UPDATE t\n" +
"SET t.matKhau = ?\n" +
"FROM TaiKhoan t\n" +
"JOIN NhanVien nv ON nv.maNhanVien = t.maNhanVien\n" +
"WHERE nv.soDienThoai = ?";
    PreparedStatement pstmt = con.prepareStatement(sql);
    pstmt.setString(1, hashedNewPassword);
    pstmt.setString(2, phone);

    int rowsAffected = pstmt.executeUpdate();
    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
    } else {
        JOptionPane.showMessageDialog(null, "Lỗi khi thay đổi mật khẩu. Không tìm thấy số điện thoại.");
    }
} catch (SQLException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    System.out.println("SQL Error: " + e.getSQLState());
    System.out.println("Error Code: " + e.getErrorCode());
}


        
        
        
        
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void jQuayLai11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jQuayLai11MouseClicked
      
        
    }//GEN-LAST:event_jQuayLai11MouseClicked

    private void jQuayLaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jQuayLaiMouseClicked
         ((JFrame) SwingUtilities.getWindowAncestor(jQuayLai11)).dispose();
    }//GEN-LAST:event_jQuayLaiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jMatKhau;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jQuayLai;
    private javax.swing.JLabel jQuayLai11;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JPasswordField txtXacNhanMK;
    // End of variables declaration//GEN-END:variables
}
