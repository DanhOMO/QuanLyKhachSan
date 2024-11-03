/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import com.quanlykhachsan.dao.HoaDon_DAO;
import com.quanlykhachsan.entity.HoaDon;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JFrame;
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
    }
     public DefaultTableModel docDuLieuVaoBanHoaDon(){
         // Thêm tên cột vào DefaultTableModel
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Hóa Đơn", "Ngày Lập", "Mã Nhân Viên", "Mã Voucher", "Mã Khách Hàng","Đặt Cọc", "Tiền Phạt", "Tổng Tiền" }, 0);
        
    hoaDonDAO.getList().forEach(x -> System.out.println(x));
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableHoaDon;
    // End of variables declaration//GEN-END:variables
}
