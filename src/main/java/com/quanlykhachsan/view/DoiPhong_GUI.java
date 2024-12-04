/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.quanlykhachsan.view;

import com.quanlykhachsan.dao.ChiTietHoaDon_DAO;
import com.quanlykhachsan.dao.DichVu_DAO;
import com.quanlykhachsan.dao.HoaDon_DAO;
import com.quanlykhachsan.dao.KhachHang_DAO;
import com.quanlykhachsan.dao.LichSuDatDichVu_DAO;
import com.quanlykhachsan.dao.LichSuDatPhong_DAO;
import com.quanlykhachsan.dao.LoaiPhong_DAO;
import com.quanlykhachsan.dao.NhanVien_DAO;
import com.quanlykhachsan.dao.Phong_DAO;
import com.quanlykhachsan.dao.Voucher_DAO;
import com.quanlykhachsan.entity.ChiTietHoaDon;
import com.quanlykhachsan.entity.DichVu;
import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.KhuVuc;
import com.quanlykhachsan.entity.LichSuDatDichVu;
import com.quanlykhachsan.entity.LoaiPhong;
import com.quanlykhachsan.entity.NhanVien;
import com.quanlykhachsan.entity.Phong;
import com.quanlykhachsan.entity.Voucher;
import com.quanlykhachsan.enum_Class.TrangThaiPhong;
import com.quanlykhachsan.enum_Class.TrangThaiTaiKhoan;
import com.quanlykhachsan.model.ConnectDB;
import static com.quanlykhachsan.model.ConnectDB.con;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author liemh
 */
public class DoiPhong_GUI extends javax.swing.JFrame implements MouseListener {

      // Ensure this is initialized
    private NhanVien_DAO nv_dao= new NhanVien_DAO();
    private HoaDon_DAO hd_dao= new HoaDon_DAO();
    private Phong_DAO p_dao= new Phong_DAO();
    private LoaiPhong_DAO lp_dao= new LoaiPhong_DAO();
    private ArrayList<Phong> dsPhong;
    private ArrayList<LoaiPhong> dsLoaiPhong;
    private final DefaultTableModel modalPhong;
    private Phong phong;
    private ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO();
    private List<ChiTietHoaDon> dsMaCTHD;
    private LichSuDatDichVu_DAO lsdv_dao;
    private DichVu_DAO dv_dao;
    
   
	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}
        
   public DoiPhong_GUI(Phong phong) {
    this.phong = phong;
    
    initComponents(); // Initialize components first
    
    jPhong.setText(phong.getMaPhong());
    nv_dao.timNhanVienTheoTrangThaiTaiKhoan(TrangThaiTaiKhoan.DANG_HOAT_DONG);
    	List<NhanVien> dsnv = nv_dao.getList();
    	NhanVien nv = dsnv.get(0);//nghiệp vụ chỉ có 1 nhân viên đang onl
    jNhanVien.setText(nv.getTenNhanVien());
     List<HoaDon> dshd = new ArrayList<HoaDon>();
				dshd = hd_dao.timTheoMaPhong(phong.getMaPhong());
        if (dshd.size() >= 1) {
					HoaDon hd = dshd.get(dshd.size() - 1);// getLast
					jKhachHang.setText(hd.getKhachHang().getTenKhachHang());
        }
    
    
    
    modalPhong = new DefaultTableModel(new String[]{"Mã phòng", "Tên phòng", "Trạng thái", "Loại phòng", "Giá","Số lượng người"}, 0);
    tablePhong.setModel(modalPhong); // Set the model after the table has been initialized
    docTuBang(phong.getLoaiPhong().getSoLuongNguoi(),phong.getLoaiPhong().getGiaThuePhong()); // Load data into the table
    tablePhong.revalidate();
    tablePhong.repaint();

}
   
   
   private void docTuBang(int nguoi , double gia) {
    dsPhong = p_dao.loadData(); // Load all rooms from the database

    for (Phong phong : dsPhong) {
        // Check if the room is available and matches the specified room type
        if (phong.getTrangThai() == TrangThaiPhong.TRONG && phong.getLoaiPhong().getSoLuongNguoi()>=nguoi) {

            // Add matching room data to the table model
            modalPhong.addRow(new Object[]{
                phong.getMaPhong(), 
                phong.getTenPhong(), 
                phong.getTrangThai(), 
                phong.getLoaiPhong().getTenLoaiPhong(), 
                phong.getLoaiPhong().getGiaThuePhong(),
                phong.getLoaiPhong().getSoLuongNguoi()
                    
            });
        }
    }
}


       
  public String getjPhong() {
		return jPhong.getText();
	}


	public void setjPhong(String jPhong) {
		this.jPhong.setText(jPhong);
	}


	public String getjNhanVien() {
		return jNhanVien.getText();
	}


	public void setjNhanVien(String jNhanVien) {
		this.jNhanVien.setText(jNhanVien);
	}


	public String getjKhachHang() {
		return jKhachHang.getText();
	}


	public void setjLabelTenKhachHang(String jTenKhachHang) {
		this.jKhachHang.setText(jTenKhachHang);
	}
//
//       public String getTxtPhong() {
//                return txtTim1.getText();
//}
//
//// Setter
//    public void setTxtPhong(String text) {
//        this.txtTim1.setText(text);
//    }
	

 




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jNhanVien = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPhong = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jKhachHang = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePhong = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnXacNhan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(58, 186, 178));
        jPanel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Đổi phòng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(258, 258, 258)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Phòng hiện tại");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Phòng");

        jNhanVien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jNhanVien.setText("XXX");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Nhân viên");

        jPhong.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPhong.setText("XXX");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Khách hàng");

        jKhachHang.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jKhachHang.setText("XXX");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Tìm Mã Phòng");

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(33, 33, 33))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(36, 36, 36)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(21, 21, 21)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                .addComponent(jNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(101, 101, 101)
                        .addComponent(jLabel9)
                        .addGap(65, 65, 65)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPhong))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jNhanVien))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jKhachHang)
                    .addComponent(jLabel9)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tablePhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã phòng", "Tên phòng", "Trạng thái", "Loại phòng", "Giá", "Số lượng người"
            }
        ));
        jScrollPane1.setViewportView(tablePhong);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(341, 341, 341)
                .addComponent(btnXacNhan)
                .addGap(30, 30, 30)
                .addComponent(btnHuy)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhan)
                    .addComponent(btnHuy))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    String loaiPhong = txtTim.getText().trim(); // Get the text from the input field
        List<Phong> dsP = new ArrayList<>();

        // Check if the room code length is 4
       
            Phong p = p_dao.timTheoMa(loaiPhong);
            if (p != null) {
                dsP.add(p); // Add the found room to the list

            }
        
        // Clear the table model before adding new data
        modalPhong.setRowCount(0);

        // Load data into the table model
        if (!dsP.isEmpty()) {
            for (Phong room : dsP) {
                if(room.getTrangThai()==TrangThaiPhong.TRONG){
                      Object[] rowData = {
                    room.getMaPhong(),
                    room.getTenPhong(),
                    room.getTrangThai(),
                    room.getLoaiPhong().getTenLoaiPhong(),
                    room.getLoaiPhong().getGiaThuePhong(),
                    room.getLoaiPhong().getSoLuongNguoi()
                };
                      modalPhong.addRow(rowData);
                }
              
                 // Add row data to the table model
            }
        } else {
              docTuBang(phong.getLoaiPhong().getSoLuongNguoi(),phong.getLoaiPhong().getGiaThuePhong());
        }	
    }//GEN-LAST:event_txtTimActionPerformed


    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {                                           
                                              
     int selectedRow = tablePhong.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a room to swap.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Retrieve the selected new room details
    String newRoomId = (String) modalPhong.getValueAt(selectedRow, 0);
    Phong newRoom = p_dao.timTheoMa(newRoomId);
    
    if (newRoom == null) {
        JOptionPane.showMessageDialog(this, "New room not found.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Retrieve the room type based on maLoaiPhong from newRoom
    LoaiPhong newRoomType = lp_dao.timLoaiPhong(newRoom.getLoaiPhong().getMaLoaiPhong());
    if (newRoomType == null) {
        JOptionPane.showMessageDialog(this, "Room type not found for the selected room.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    double newRoomPrice = newRoomType.getGiaThuePhong();
    if (newRoomPrice == 0) {
        JOptionPane.showMessageDialog(this, "Room price is not set.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Ensure lsdv_dao is properly initialized before usage
    if (lsdv_dao == null) {
        lsdv_dao = new LichSuDatDichVu_DAO();  // Initialize the DAO if it is null
    }

    // Lấy danh sách mã chi tiết hóa đơn (CTHD) từ phòng cũ
    List<String> maCTHDList = cthd_dao.timMaCTHD(phong.getMaPhong());
    double totalServicePrice = 0.0;

    // Duyệt qua danh sách mã chi tiết hóa đơn để tính tổng tiền dịch vụ
    for (String maCTHD : maCTHDList) {
        List<LichSuDatDichVu> serviceHistoryList = lsdv_dao.timLichSuDatDichVuTheoMaCTHD(maCTHD);
        
        // Duyệt qua danh sách dịch vụ đã đặt
        for (LichSuDatDichVu serviceHistory : serviceHistoryList) {
            // Lấy giá dịch vụ từ dịch vụ đã đặt
            DichVu service = serviceHistory.getDichVu();
            double servicePrice = service.getGiaDichVu(); // Giá của dịch vụ
            int quantity = serviceHistory.getSoLuong(); // Số lượng dịch vụ đã đặt

            // Tính tổng tiền dịch vụ
            totalServicePrice += servicePrice * quantity;
        }
    }

    System.out.println("Tổng tiền dịch vụ: " + totalServicePrice);

    // Update room statuses
    p_dao.doiMaPhong(phong.getMaPhong(), newRoom.getMaPhong());
    phong.setTrangThai(TrangThaiPhong.TRONG);
    newRoom.setTrangThai(TrangThaiPhong.DA_DAT);

    try {
        // Update room details in the database (using p_dao)
        p_dao.capNhatPhong(phong);
        p_dao.capNhatPhong(newRoom);

        // Update the room price in the HoaDon entity
        double totalPrice = newRoomPrice + totalServicePrice; // Tổng tiền phòng mới + tiền dịch vụ
        cthd_dao.capNhatGiaTheoMa(newRoom.getMaPhong(), totalPrice); // Cập nhật tổng tiền vào hóa đơn

        JOptionPane.showMessageDialog(this, "Thành công");
        dispose();

    } catch (SQLException ex) {
        Logger.getLogger(DoiPhong_GUI.class.getName()).log(Level.SEVERE, null, ex);
    }
}

        

    /**
     * @param args the command line arguments
     */
   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel jKhachHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jNhanVien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jPhong;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablePhong;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables


    @Override
    public void mouseClicked(MouseEvent e) {
     
//        int row = tablePhong.getSelectedRow();
//        if (row != -1) {
//            txtTim1.setText((String) modalPhong.getValueAt(row, 0));
            
//    }
//
//        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    
}

    
