/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.quanlykhachsan.view;

import com.quanlykhachsan.dao.NhanVien_DAO;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.model.ConnectDB;
import static com.quanlykhachsan.view.FogotPassword.checkPhoneAndEmail;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author liemh
 */
public class QuenMatKhau_GUI extends javax.swing.JFrame {

    /**
     * Creates new form QuenMatKhau_GUI
     */
    public QuenMatKhau_GUI() {
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
         DangNhap_GUI a1= new DangNhap_GUI();
      a1.setVisible(true);
      a1.setSize(900,600);
      a1.setLocationRelativeTo(null);
      a1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      dispose();
        
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
      

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jQuayLai11 = new javax.swing.JLabel();
        jQuayLai = new javax.swing.JLabel();
        btnXacNhan = new javax.swing.JButton();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        txtXacNhanMK = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(2048, 1276));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        jLabel15.setText("Có PBLD đây !!!");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 240, -1, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        jLabel14.setText("Đặt phòng ngây");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/backGround_TachNEn.png"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, -1, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel12.setText("vụ chuyên nghiệp và không gian nghỉ dưỡng ");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, 359, 28));

        jLabel11.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel11.setText("lý tưởng, đáp ứng mọi nhu cầu của quý khách ");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 520, 359, 28));

        jLabel10.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel10.setText("hợp hoàn hảo giữa tiện nghi hiện đại, dịch");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 490, 359, 28));

        jLabel9.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel9.setText("PBLD tự tin  là khách sạn mang đến sự kết");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 370, 28));

        jQuayLai11.setForeground(new java.awt.Color(255, 0, 0));
        jQuayLai11.setText("Làm mới mật khẩu");
        getContentPane().add(jQuayLai11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, -1, -1));

        jQuayLai.setForeground(new java.awt.Color(255, 0, 0));
        jQuayLai.setText("Quay lại đăng nhập");
        jQuayLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jQuayLaiMouseClicked(evt);
            }
        });
        getContentPane().add(jQuayLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, -1, -1));

        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });
        getContentPane().add(btnXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 160, -1));

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });
        getContentPane().add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 200, -1));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 200, -1));

        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauActionPerformed(evt);
            }
        });
        getContentPane().add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 200, 30));

        txtXacNhanMK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        getContentPane().add(txtXacNhanMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 200, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Xác nhận mật khẩu");
        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Mật khẩu");
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Email");
        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 60, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Số điện thoại");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 130, -1));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("Quên mật khẩu");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/nen.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
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


    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void jQuayLaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jQuayLaiMouseClicked
        DangNhap_GUI a1= new DangNhap_GUI();
      a1.setVisible(true);
      a1.setSize(900,600);
      a1.setLocationRelativeTo(null);
      a1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      dispose();
    }//GEN-LAST:event_jQuayLaiMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuenMatKhau_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jQuayLai;
    private javax.swing.JLabel jQuayLai11;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JPasswordField txtXacNhanMK;
    // End of variables declaration//GEN-END:variables
}
