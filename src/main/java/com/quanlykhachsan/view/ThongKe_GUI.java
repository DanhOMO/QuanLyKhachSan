/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import com.quanlykhachsan.dao.CaLamViec_DAO;
import com.quanlykhachsan.dao.NhanVien_DAO;
import com.quanlykhachsan.dao.ThongKe_DAO;
import com.quanlykhachsan.entity.CaLamViec;
import com.quanlykhachsan.model.ConnectDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class ThongKe_GUI extends javax.swing.JPanel {
    public static void main(String[] args) {
        try {
            JFrame test = new JFrame();
        ConnectDB con = new ConnectDB();
            
        con.connect();
        test.add(new ThongKe_GUI("Nhan Vien"));
        test.setVisible(true);
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        test.setSize(1220, 868);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Creates new form ThongKe_GUI
     */

    public ThongKe_GUI(String chucvu) {
        initComponents();
        ThongKe_DAO thongke = new ThongKe_DAO();
         if(chucvu.equalsIgnoreCase("Nhan Vien")){
             
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setSelectedIndex(0);
         }
      
       btnRefresh1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thongke.setDataToBarhart(jpView2);
                thongke.setDataToPie(jpView3);  
                
                thongke.setDataToThongKeSoDonDatPhong(lbSoLuongDatPhong);
                thongke.setDataToThongKeDichVU(lbSoLuongDichVuDaDat);
                
            }
       });
        thongke.setDataToChartThongKeGiaoCa(jpView, LocalDate.now());
        thongke.setDataToBarhart(jpView2);
        thongke.setDataToPie(jpView3);
        thongke.setDataToThongKeSoDonDatPhong(lbSoLuongDatPhong);
        thongke.setDataToThongKeSoKhachHang(lbSoLuongKhachHang);
        thongke.setDataToThongKeDichVU(lbSoLuongDichVuDaDat);
        
        btnTraCuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               thongke.setDataToBarhart(jpView2, LocalDate.of((int)spinnerNam.getValue(), (int)spinnerThang.getValue(), (int)spinnerNgay.getValue()) );
               
               thongke.setDataToThongKeSoDonDatPhong(lbSoLuongDatPhong,  LocalDate.of((int)spinnerNam.getValue(), (int)spinnerThang.getValue(), (int)spinnerNgay.getValue()));
               thongke.setDataToThongKeDichVU(lbSoLuongDichVuDaDat, LocalDate.of((int)spinnerNam.getValue(), (int)spinnerThang.getValue(), (int)spinnerNgay.getValue()));
            }
        });
        
        btnXoaTrang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thongke.setDataToChartThongKeGiaoCa(jpView, LocalDate.now());
                tableThongKeGiaoCa.setModel(thongke.docDuLieuVaoBan());
                ngayThongKe.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }
        });
        
        tableThongKeGiaoCa.setModel(thongke.docDuLieuVaoBan());
        thongke.setDataToChartThongKeDoanhThuTrongCa(jpView1, Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
      ngayThongKe.addPropertyChangeListener(new PropertyChangeListener() {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Check if the event is related to the date selection
        if ("date".equals(evt.getPropertyName())) {
            // Update chart data based on selected date
            Date selectedDate = ngayThongKe.getCalendar().getTime();
            thongke.setDataToChartThongKeDoanhThuTrongCa(jpView1, selectedDate);
            thongke.setDataToChartThongKeGiaoCa(jpView, selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            // Initialize table model with column names
            DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Ca", "Tên Ca", "Ngày Làm Việc", "Tổng Tiền", "Tên Nhân Viên"}, 0);
            thongke.setDataToChartThongKeGiaoCa(jpView, selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            // Fetch and filter work shifts by the selected date
            CaLamViec_DAO ca = new CaLamViec_DAO();
            LocalDate date = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            for (CaLamViec caLamViec : ca.getList()) {
                // Check if the work shift date matches the selected date
                if (caLamViec.getNgayLamViec().equals(date)) {
                    NhanVien_DAO nv = new NhanVien_DAO();
                    dtm.addRow(new Object[]{
                        caLamViec.getMaCaLamViec(),
                        caLamViec.getTenCaLamViec().getCa(),
                        caLamViec.getNgayLamViec(),
                        caLamViec.getTongTienTrongCa(),
                        nv.getTenNV(caLamViec.getNhanVien().getMaNhanVien())
                    });
                }
            }

            // Set the updated model to the table
            tableThongKeGiaoCa.setModel(dtm);
        }
    }
});

       btnTim.addActionListener((e) -> {
    // Initialize table model with column headers
    DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Ca", "Tên Ca", "Ngày Làm Việc", "Tổng Tiền", "Tên Nhân Viên"}, 0);

    // Retrieve the search term
    CaLamViec_DAO ca = new CaLamViec_DAO();
    String tim = txtTim.getText().trim();

    // Iterate through the work shifts list and add rows that match the search criteria
    for (CaLamViec caLamViec : ca.getList()) {
        if (caLamViec.getMaCaLamViec().startsWith(tim) || 
                 caLamViec.getTenCaLamViec().getCa().startsWith(tim) || 
                 caLamViec.getNhanVien().getMaNhanVien().startsWith(tim))  {

                NhanVien_DAO nv = new NhanVien_DAO();
            dtm.addRow(new Object[]{
                caLamViec.getMaCaLamViec(), 
                caLamViec.getTenCaLamViec().getCa(), 
                caLamViec.getNgayLamViec(), 
                caLamViec.getTongTienTrongCa(), 
                 nv.getTenNV(caLamViec.getNhanVien().getMaNhanVien())
            });
        }
    }

    // Update the table with the new model
    tableThongKeGiaoCa.setModel(dtm);
}); 
   
    
    }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnTim = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        ngayThongKe = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        label1 = new java.awt.Label();
        btnXoaTrang = new javax.swing.JButton();
        jpView = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableThongKeGiaoCa = new javax.swing.JTable();
        jpView1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        spinnerNgay = new javax.swing.JSpinner();
        jLabel51 = new javax.swing.JLabel();
        spinnerThang = new javax.swing.JSpinner();
        jLabel52 = new javax.swing.JLabel();
        spinnerNam = new javax.swing.JSpinner();
        btnTraCuu = new javax.swing.JButton();
        jpView2 = new javax.swing.JPanel();
        jpView3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbSoLuongDatPhong = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbSoLuongKhachHang = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbSoLuongDichVuDaDat = new javax.swing.JLabel();
        btnRefresh1 = new javax.swing.JButton();

        jTabbedPane1.setForeground(new java.awt.Color(20, 70, 97));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jPanel8.setForeground(new java.awt.Color(20, 70, 97));
        jPanel8.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N

        jPanel15.setForeground(new java.awt.Color(20, 70, 97));

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Theo Ngày:");

        label1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        label1.setText("Tên ca làm việc:");

        btnXoaTrang.setText("Refresh");
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtTim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTim))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(ngayThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addGap(132, 132, 132)
                        .addComponent(btnXoaTrang))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ngayThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(btnXoaTrang, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTim)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpViewLayout = new javax.swing.GroupLayout(jpView);
        jpView.setLayout(jpViewLayout);
        jpViewLayout.setHorizontalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );
        jpViewLayout.setVerticalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
        );

        tableThongKeGiaoCa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tableThongKeGiaoCa.setForeground(new java.awt.Color(20, 70, 97));
        tableThongKeGiaoCa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Ca Làm VIệc", "Ca Làm Việc", "Ngày giao ca", "Tổng tiền", "Tên Nhân Viên"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableThongKeGiaoCa);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpView1.setMaximumSize(new java.awt.Dimension(488, 300));
        jpView1.setMinimumSize(new java.awt.Dimension(488, 300));
        jpView1.setPreferredSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout jpView1Layout = new javax.swing.GroupLayout(jpView1);
        jpView1.setLayout(jpView1Layout);
        jpView1Layout.setHorizontalGroup(
            jpView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );
        jpView1Layout.setVerticalGroup(
            jpView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 335, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpView1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jpView1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 819, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Thông Kê Giao Ca", jPanel8);

        jPanel13.setForeground(new java.awt.Color(20, 70, 97));
        jPanel13.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N

        jLabel50.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(20, 70, 97));
        jLabel50.setText("Chọn Ngày:");

        spinnerNgay.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        spinnerNgay.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));

        jLabel51.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(20, 70, 97));
        jLabel51.setText("Chọn Tháng:");

        spinnerThang.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        spinnerThang.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));

        jLabel52.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(20, 70, 97));
        jLabel52.setText("Chọn Năm:");

        spinnerNam.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        spinnerNam.setModel(new javax.swing.SpinnerNumberModel(2024, 2000, 2024, 1));

        btnTraCuu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnTraCuu.setLabel("Tìm");
        btnTraCuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraCuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel50)
                .addGap(2, 2, 2)
                .addComponent(spinnerNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel51)
                .addGap(2, 2, 2)
                .addComponent(spinnerThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel52)
                .addGap(2, 2, 2)
                .addComponent(spinnerNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTraCuu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(spinnerNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51)
                    .addComponent(spinnerThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52)
                    .addComponent(spinnerNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTraCuu))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpView2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jpView2Layout = new javax.swing.GroupLayout(jpView2);
        jpView2.setLayout(jpView2Layout);
        jpView2Layout.setHorizontalGroup(
            jpView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 711, Short.MAX_VALUE)
        );
        jpView2Layout.setVerticalGroup(
            jpView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpView3Layout = new javax.swing.GroupLayout(jpView3);
        jpView3.setLayout(jpView3Layout);
        jpView3Layout.setHorizontalGroup(
            jpView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );
        jpView3Layout.setVerticalGroup(
            jpView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        jPanel1.setMaximumSize(new java.awt.Dimension(1220, 98));

        jPanel4.setBackground(new java.awt.Color(48, 206, 235));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setMaximumSize(new java.awt.Dimension(313, 98));

        jPanel3.setBackground(new java.awt.Color(48, 206, 235));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/doorr.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        jPanel2.setBackground(new java.awt.Color(48, 206, 235));
        jPanel2.setMaximumSize(new java.awt.Dimension(191, 88));

        jLabel2.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel2.setText("Tổng Số Đơn Đặt Phòng");

        lbSoLuongDatPhong.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSoLuongDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSoLuongDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel9.setBackground(new java.awt.Color(65, 217, 158));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setMaximumSize(new java.awt.Dimension(313, 98));

        jPanel19.setBackground(new java.awt.Color(65, 217, 158));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/Customer2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(14, 14, 14))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(65, 217, 158));

        jLabel3.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel3.setText("Tổng Số Khách Hàng ");

        lbSoLuongKhachHang.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lbSoLuongKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSoLuongKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        jPanel24.setBackground(new java.awt.Color(255, 102, 102));
        jPanel24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel24.setMaximumSize(new java.awt.Dimension(313, 98));

        jPanel25.setBackground(new java.awt.Color(255, 102, 102));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlykhachsan/img/DichVU.png"))); // NOI18N

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jPanel26.setBackground(new java.awt.Color(255, 102, 102));

        jLabel5.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel5.setText("Tổng Số Dịch Vụ Được Đặt");

        lbSoLuongDichVuDaDat.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lbSoLuongDichVuDaDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSoLuongDichVuDaDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnRefresh1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnRefresh1.setText("Refresh");
        btnRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jpView2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jpView3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRefresh1)
                                .addGap(100, 100, 100))))))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(btnRefresh1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jpView3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jpView2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        jpView2.getAccessibleContext().setAccessibleDescription("");
        jPanel1.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 84, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thống Kê Doanh Thu", jPanel13);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimActionPerformed

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaTrangActionPerformed

    private void btnTraCuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraCuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTraCuuActionPerformed

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefresh1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTraCuu;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpView;
    private javax.swing.JPanel jpView1;
    private javax.swing.JPanel jpView2;
    private javax.swing.JPanel jpView3;
    private java.awt.Label label1;
    private javax.swing.JLabel lbSoLuongDatPhong;
    private javax.swing.JLabel lbSoLuongDichVuDaDat;
    private javax.swing.JLabel lbSoLuongKhachHang;
    private com.toedter.calendar.JDateChooser ngayThongKe;
    private javax.swing.JSpinner spinnerNam;
    private javax.swing.JSpinner spinnerNgay;
    private javax.swing.JSpinner spinnerThang;
    private javax.swing.JTable tableThongKeGiaoCa;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
