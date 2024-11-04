/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import com.quanlykhachsan.dao.HoaDon_DAO;
import com.quanlykhachsan.dao.NhanVien_DAO;
import com.quanlykhachsan.dao.Voucher_DAO;
import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.entity.Voucher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

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
        a.setSize(1229 , 730);
        a.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    /**
     * Creates new form LichSu_GUI
     */
    public LichSu_GUI() {
        initComponents();
        tableHoaDon.setModel(docDuLieuVaoBanHoaDon());
        txtMaHD.addMouseListener(new MouseAdapter() {
                    @Override
           public void mouseClicked(MouseEvent e) {
               txtMaHD.selectAll();
           }
        });
            btnTim.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             // Kiểm tra nếu mã hóa đơn nhập trống
             if (txtMaHD.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(null, "Vui lòng nhập mã hóa đơn cần tìm !!!");
             } else {
                 String maHoaDonTim = txtMaHD.getText().trim();
                 // Khởi tạo DefaultTableModel chỉ một lần
                 DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Hóa Đơn", "Ngày Lập", "Mã Nhân Viên", "Mã Voucher", "Mã Khách Hàng", "Đặt Cọc", "Tiền Phạt", "Tổng Tiền"}, 0);

                 // Duyệt danh sách hóa đơn và tìm hóa đơn có mã khớp
                 for (HoaDon hoaDon : hoaDonDAO.getList()) {
                     if (hoaDon.getMaHoaDon().equals(maHoaDonTim)) {
                         dtm.addRow(new Object[]{
                             hoaDon.getMaHoaDon(),
                             hoaDon.getThoiGianLapHoaDon(),
                             hoaDon.getNhanVien().getMaNhanVien(),
                             hoaDon.getVoucher() != null ? hoaDon.getVoucher().getMaVoucher() : "",  // Kiểm tra nếu Voucher có thể null
                             hoaDon.getKhachHang().getMaKhachHang(),
                             hoaDon.getTienCoc(),
                             hoaDon.getTienPhat(),
                             hoaDon.getTongTien()
                         });
                     }
                 }

                 // Kiểm tra nếu không tìm thấy hóa đơn nào
                 if (dtm.getRowCount() == 0) {
                     JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với mã: " + maHoaDonTim);
                 } else {
                     // Cập nhật model cho table
                     tableHoaDon.setModel(dtm);
                 }
               }
              }
          });
        hienDuLieuVaoCBBNhanVien();
        timTheoMaNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                     // Kiểm tra nếu mã hóa đơn nhập trống
                  String maNhanVien = timTheoMaNhanVien.getSelectedItem().toString();
             if (maNhanVien.isEmpty()) {
                 JOptionPane.showMessageDialog(null, "Vui lòng nhập mã hóa đơn cần tìm !!!");
             } else {
                 String maHoaDonTim = maNhanVien.trim();
                 // Khởi tạo DefaultTableModel chỉ một lần
                 DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Hóa Đơn", "Ngày Lập", "Mã Nhân Viên", "Mã Voucher", "Mã Khách Hàng", "Đặt Cọc", "Tiền Phạt", "Tổng Tiền"}, 0);

                 // Duyệt danh sách hóa đơn và tìm hóa đơn có mã khớp
                 for (HoaDon hoaDon : hoaDonDAO.getList()) {
                     if (hoaDon.getNhanVien().getMaNhanVien().equalsIgnoreCase( maHoaDonTim)) {
                         dtm.addRow(new Object[]{
                             hoaDon.getMaHoaDon(),
                             hoaDon.getThoiGianLapHoaDon(),
                             hoaDon.getNhanVien().getMaNhanVien(),
                             hoaDon.getVoucher() != null ? hoaDon.getVoucher().getMaVoucher() : "",  // Kiểm tra nếu Voucher có thể null
                             hoaDon.getKhachHang().getMaKhachHang(),
                             hoaDon.getTienCoc(),
                             hoaDon.getTienPhat(),
                             hoaDon.getTongTien()
                         });
                     }
                 }

                 // Kiểm tra nếu không tìm thấy hóa đơn nào
                 if (dtm.getRowCount() == 0) {
                     JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với mã nhân viên : " + maHoaDonTim);
                 } else {
                     // Cập nhật model cho table
                     tableHoaDon.setModel(dtm);
                 }
               } 
            }
        });
        hienDuLieuVaoCBBVoucher();
         timTheoMaVoucher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                     // Kiểm tra nếu mã hóa đơn nhập trống
                  String maNhanVien = timTheoMaVoucher.getSelectedItem().toString();
             if (maNhanVien.isEmpty()) {
                 JOptionPane.showMessageDialog(null, "Vui lòng nhập mã hóa đơn cần tìm !!!");
             } else {
                 String maHoaDonTim = maNhanVien.trim();
                 // Khởi tạo DefaultTableModel chỉ một lần
                 DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Hóa Đơn", "Ngày Lập", "Mã Nhân Viên", "Mã Voucher", "Mã Khách Hàng", "Đặt Cọc", "Tiền Phạt", "Tổng Tiền"}, 0);

                 // Duyệt danh sách hóa đơn và tìm hóa đơn có mã khớp
                 for (HoaDon hoaDon : hoaDonDAO.getList()) {
                     if (hoaDon.getVoucher().getMaVoucher().equalsIgnoreCase( maHoaDonTim)) {
                         dtm.addRow(new Object[]{
                             hoaDon.getMaHoaDon(),
                             hoaDon.getThoiGianLapHoaDon(),
                             hoaDon.getNhanVien().getMaNhanVien(),
                             hoaDon.getVoucher() != null ? hoaDon.getVoucher().getMaVoucher() : "",  // Kiểm tra nếu Voucher có thể null
                             hoaDon.getKhachHang().getMaKhachHang(),
                             hoaDon.getTienCoc(),
                             hoaDon.getTienPhat(),
                             hoaDon.getTongTien()
                         });
                     }
                 }

                 // Kiểm tra nếu không tìm thấy hóa đơn nào
                 if (dtm.getRowCount() == 0) {
                     JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với mã Voucher : " + maHoaDonTim);
                 } else {
                     // Cập nhật model cho table
                     tableHoaDon.setModel(dtm);
                 }
               } 
            }
        });
        
}
    public void hienDuLieuVaoCBBNhanVien(){
        NhanVien_DAO nhanvien = new NhanVien_DAO();
        for (NhanVien nhanVien : nhanvien.getList()) {
            timTheoMaNhanVien.addItem(nhanVien.getMaNhanVien());
        }
    }
    public void hienDuLieuVaoCBBVoucher(){
        Voucher_DAO  nhanvien = new Voucher_DAO();
        for (Voucher nhanVien : nhanvien.layDanhSachKhuyenMai()) {
            timTheoMaNhanVien.addItem(nhanVien.getMaVoucher());
        }
    }
    
     public DefaultTableModel docDuLieuVaoBanHoaDon(){
         // Thêm tên cột vào DefaultTableModel
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Hóa Đơn", "Ngày Lập", "Mã Nhân Viên", "Mã Voucher", "Mã Khách Hàng","Đặt Cọc", "Tiền Phạt", "Tổng Tiền" }, 0);
        
    
    // Thêm dữ liệu vào DefaultTableModel
          hoaDonDAO.getList().stream().forEach(x -> {
        dtm.addRow(new Object[]{
            x.getMaHoaDon(), x.getThoiGianLapHoaDon(), x.getNhanVien().getMaNhanVien(), x.getVoucher().getMaVoucher(), x.getKhachHang().getMaKhachHang(), x.getTienCoc(), x.getTienPhat(), x.getTongTien()
        });
    });
    
    return dtm;
    }
    public DefaultTableModel docDuLieuVaoBanHoaDon(LocalDate ngayLap) {
    // Thêm tên cột vào DefaultTableModel
    DefaultTableModel dtm = new DefaultTableModel(new String[]{
            "Mã Hóa Đơn", "Ngày Lập", "Mã Nhân Viên", "Mã Voucher", 
            "Mã Khách Hàng", "Đặt Cọc", 
            "Tiền Phạt", "Tổng Tiền"}, 0);
    
    // Lấy danh sách hóa đơn và lọc theo ngày
    List<HoaDon> hoaDons = hoaDonDAO.getList(); // Giả sử hoaDonDAO.getList() trả về danh sách hóa đơn
    hoaDons.stream()
            .filter(x -> x.getThoiGianLapHoaDon().isEqual(ngayLap)) // Lọc theo ngày
            .forEach(x -> {
                dtm.addRow(new Object[]{
                    x.getMaHoaDon(), 
                    x.getThoiGianLapHoaDon(), // Giữ nguyên LocalDate, có thể định dạng sau
                    x.getNhanVien().getMaNhanVien(), 
                    x.getVoucher() != null ? x.getVoucher().getMaVoucher() : "", // Kiểm tra null
                    x.getKhachHang() != null ? x.getKhachHang().getMaKhachHang() : "", // Kiểm tra null
                    x.getTienCoc(), 
                    x.getTienPhat(), 
                    x.getTongTien()
                });
            });
    
    return dtm;
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
        jLabel4 = new javax.swing.JLabel();
        timTheoMaVoucher = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        timTheoTongTien = new javax.swing.JTextField();
        txtMaHD = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoaDon = new javax.swing.JTable();

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
        jLabel3.setText("Theo Mã Nhân Viên:");

        timTheoMaNhanVien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Theo Mã Voucher:");

        timTheoMaVoucher.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Tổng Tiền Lớn Hơn Hoặc Bằng:");

        timTheoTongTien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtMaHD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMaHD.setText("Mã Hóa Đơn");
        txtMaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHDActionPerformed(evt);
            }
        });

        btnTim.setText("Tìm Theo Mã Hóa Đơn");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTim)
                .addGap(91, 91, 91)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(timNgayLapHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timTheoMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(timTheoTongTien)
                    .addComponent(timTheoMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(timNgayLapHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTim))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(timTheoMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(timTheoMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(timTheoTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
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

    private void txtMaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableHoaDon;
    private com.toedter.calendar.JDateChooser timNgayLapHD;
    private javax.swing.JComboBox<String> timTheoMaNhanVien;
    private javax.swing.JComboBox<String> timTheoMaVoucher;
    private javax.swing.JTextField timTheoTongTien;
    private javax.swing.JTextField txtMaHD;
    // End of variables declaration//GEN-END:variables
}
