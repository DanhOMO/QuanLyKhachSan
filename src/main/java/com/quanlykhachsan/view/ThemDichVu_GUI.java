/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import java.awt.event.ActionListener;

/**
 *
 * @author liemh
 */
public class ThemDichVu_GUI extends javax.swing.JPanel {

	/**
	 * Creates new form ThongTinDatPhong
	 */
	private final double coc = 0.2;
	private int soLuongDV = 0;
	private double giaThuePhong = 0; 
	private KhachHang_DAO kh_dao;
	private DichVu_DAO dv_dao;
	private LoaiPhong_DAO lp_dao;
	private ChiTietHoaDon_DAO cthd_dao;
	private LichSuDatDichVu_DAO lsddv_dao;
	private LichSuDatPhong_DAO lsdp_dao;
	private HoaDon_DAO hd_dao;
	private NhanVien_DAO nv_dao;
	private Phong_DAO p_dao;
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

	public ThemDichVu_GUI(Phong phong, JFrame parentFrame, HoaDon hd) {
		this.phong = phong;
		this.hd = hd;
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
		jButtonXacNhan.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ChiTietHoaDon_DAO listCTHD = new ChiTietHoaDon_DAO();
                        listCTHD.docTuBang();
                        DefaultTableModel dtm = (DefaultTableModel)jTable2.getModel();
                        List<String> maCTHD = listCTHD.timMaCTHD(phong.getMaPhong());
                        for (int i = 0; i < dtm.getRowCount(); i++) {
                           lsddv_dao.themLichSuDatDichVu(new LichSuDatDichVu(
                            new ChiTietHoaDon(maCTHD.get(0)), 
                            new DichVu(dtm.getValueAt(i, 0).toString()), 
                            LocalDate.now(), 
                            Integer.parseInt(dtm.getValueAt(i, 2).toString()) // Đóng dấu ngoặc ở đây
                        )); // Đóng dấu ngoặc đóng của phương thức themLichSuDatDichVu

                        } JOptionPane.showMessageDialog(null, "Thêm dịch vụ thành công");
                    }
                });
//		hd_dao.timTheoMaPhong(phong.getMaPhong());
//		List<HoaDon> dshd = new ArrayList<HoaDon>();
//		dshd = hd_dao.getList();
//		HoaDon hd = dshd.get(dshd.size() - 1);//getLast	
		nv_dao.timNhanVienTheoTrangThaiTaiKhoan(TrangThaiTaiKhoan.DANG_HOAT_DONG);
    	List<NhanVien> dsnv = nv_dao.getList();
		jTextFieldTenNhanVien.setText(dsnv.get(0).getTenNhanVien());//nv
		KhachHang kh = kh_dao.timTheoMa(hd.getKhachHang().getMaKhachHang());
		jTextFieldTenKhachHang.setText(kh.getTenKhachHang());//kh
		jTextFieldSoDienThoai.setText(kh.getSoDienThoai());//sdt
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH'h' dd/MM/yyyy");

		// Lấy giá trị LocalDateTime từ đối tượng hd
		LocalDateTime checkInDateTime = hd.getCheckIn();
		labelCheckIn.setText(checkInDateTime.format(dateTimeFormatter));
		LocalDateTime checkOutDateTime = hd.getCheckOut();
		labelCheckOut.setText(checkOutDateTime.format(dateTimeFormatter));
		loadComboxDichVu();
		jComboBoxDichVu.setSelectedIndex(1);
		jSpinnerSoLuongDichVu.setValue(1);
		int i = jComboBoxDichVu.getSelectedIndex(); // Lấy chỉ mục đã chọn từ jComboBox
	    ArrayList<DichVu> dsdv = dv_dao.getDsDichVu(); // Lấy danh sách dịch vụ từ dv_dao

	    // Kiểm tra nếu chỉ mục hợp lệ
	    if (i >= 0 && i < dsdv.size()) {
	        // Lấy số lượng từ jSpinner
	        int soLuong = (int) jSpinnerSoLuongDichVu.getValue();

	        // Tính tiền dựa trên số lượng và giá dịch vụ
	        double tien = soLuong * dsdv.get(i).getGiaDichVu();

	        // Hiển thị tổng tiền trong jTextFieldGiaDichVu
	        jTextFieldGiaDichVu.setText(String.valueOf(tien));
	    }
	    
	    //
		ArrayList<LichSuDatDichVu> dsddv = cthd_dao.dsLichSuDichVu(hd.getMaHoaDon());
		dsddv.stream().forEach(x -> {
			modelDichVu.addRow(new Object[] { x.getDichVu().getMaDichVu(), x.getDichVu().getTenDichVu(), x.getSoLuong(),
					x.getDichVu().getGiaDichVu() * x.getSoLuong() });

		});
		giaThuePhong = hd.getTongTien() - tinhTongTien();
		jTextFieldTongTien.setText(String.valueOf(hd.getTongTien()));
		soLuongDV = dsddv.size();
	}
	
	
		
	

	private void loadComboxDichVu() {
		jComboBoxDichVu.removeAllItems();

        ArrayList<DichVu> dsdv = new ArrayList<DichVu>();
        dsdv = dv_dao.getDsDichVu();
        dsdv.stream().forEach(x->{
        	jComboBoxDichVu.addItem(x.getTenDichVu());
        });
		
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
        jLabel17 = new javax.swing.JLabel();
        labelCheckIn = new javax.swing.JLabel();
        labelCheckOut = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxDichVu = new javax.swing.JComboBox<>();
        jSpinnerSoLuongDichVu = new javax.swing.JSpinner();
        jButtonThemDichVu = new javax.swing.JButton();
        jTextFieldGiaDichVu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldTongTien = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButtonXacNhan = new javax.swing.JButton();
        jButtonHuy = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Tên khách hàng:");

        jTextFieldSoDienThoai.setEditable(false);
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

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Check-out:");

        labelCheckIn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelCheckIn.setText("jLabel4");

        labelCheckOut.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelCheckOut.setText("jLabel5");

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
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextFieldSoDienThoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addComponent(jTextFieldTenKhachHang, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(labelCheckIn))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel11))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelCheckOut)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(labelCheckOut))
                        .addGap(39, 39, 39))
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
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(labelCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Dịch vụ:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Số lượng:");

        jComboBoxDichVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxDichVuMouseClicked(evt);
            }
        });
        jComboBoxDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDichVuActionPerformed(evt);
            }
        });
        jComboBoxDichVu.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxDichVuPropertyChange(evt);
            }
        });

        jSpinnerSoLuongDichVu.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerSoLuongDichVuStateChanged(evt);
            }
        });

        jButtonThemDichVu.setText("Thêm dịch vụ");
        jButtonThemDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemDichVuActionPerformed(evt);
            }
        });

        jTextFieldGiaDichVu.setEnabled(false);
        jTextFieldGiaDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGiaDichVuActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Giá:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButtonThemDichVu)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSpinnerSoLuongDichVu)
                            .addComponent(jTextFieldGiaDichVu)
                            .addComponent(jComboBoxDichVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(141, 141, 141))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jComboBoxDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSpinnerSoLuongDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldGiaDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jButtonThemDichVu)
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Dịch vụ:");

        jTextFieldTongTien.setEnabled(false);
        jTextFieldTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTongTienActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Tổng tiền:");

        jButtonXacNhan.setText("Xác nhận");
        jButtonXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXacNhanActionPerformed(evt);
            }
        });

        jButtonHuy.setText("Hủy");
        jButtonHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addComponent(jButtonXacNhan)
                .addGap(39, 39, 39)
                .addComponent(jButtonHuy)
                .addGap(232, 232, 232))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonXacNhan)
                    .addComponent(jButtonHuy))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(171, 171, 171)
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        jTable2.setModel(modelDichVu);
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents



    private void jComboBoxDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxDichVuMouseClicked
    }//GEN-LAST:event_jComboBoxDichVuMouseClicked

    private void jSpinnerSoLuongDichVuStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerSoLuongDichVuStateChanged
    	 int i = jComboBoxDichVu.getSelectedIndex(); // Lấy chỉ mục đã chọn từ jComboBox
    	    ArrayList<DichVu> dsdv = dv_dao.getDsDichVu(); // Lấy danh sách dịch vụ từ dv_dao

    	    // Kiểm tra nếu chỉ mục hợp lệ
    	    if (i >= 0 && i < dsdv.size()) {
    	        // Lấy số lượng từ jSpinner
    	        int soLuong = (int) jSpinnerSoLuongDichVu.getValue();

    	        // Tính tiền dựa trên số lượng và giá dịch vụ
    	        double tien = soLuong * dsdv.get(i).getGiaDichVu();

    	        // Hiển thị tổng tiền trong jTextFieldGiaDichVu
    	        jTextFieldGiaDichVu.setText(String.valueOf(tien));
    	    }
    	    
    }//GEN-LAST:event_jSpinnerSoLuongDichVuStateChanged

    private void jComboBoxDichVuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxDichVuPropertyChange
    }//GEN-LAST:event_jComboBoxDichVuPropertyChange

	private void jButtonXacNhanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonXacNhanActionPerformed
		
//		List<HoaDon> dshd = hd_dao.timTheoMaPhong(phong.getMaPhong());
//		dshd = hd_dao.getList();
//		HoaDon hd = null;
//		if (!dshd.isEmpty()) {
//			if (dshd.size() == 1) {
//				hd = dshd.get(0);
//			} else {
//				hd = dshd.get(dshd.size() - 1); // getLast
//			}
//		}
		int soLuongTong = modelDichVu.getRowCount();
		List<ChiTietHoaDon> dsCTHD = cthd_dao.timChiTietHoaDonTheoMa(hd.getMaHoaDon());
		dsCTHD.stream().forEach(x->System.err.println(x.getMaChiTietHoaDon()));
			ChiTietHoaDon ct = dsCTHD.stream().filter(x -> x.getMaPhong().getMaPhong().equals(phong.getMaPhong()))
					.findFirst().orElse(null);
		if(ct==null) {
			ct = dsCTHD.getLast();
		}
		for (int i = 0; i < soLuongTong; i++) {
			DichVu dv = dv_dao.timDichVu(modelDichVu.getValueAt(i, 0).toString());
			int soLuong = Integer.parseInt(modelDichVu.getValueAt(i, 2).toString());
			LichSuDatDichVu lsdv = new LichSuDatDichVu(ct, dv, LocalDate.now(), soLuong);
			if(lsddv_dao.themLichSuDatDichVu(lsdv) == false) {
				lsddv_dao.suaLichSuDatDichVu(lsdv);
			}



		}
		cthd_dao.capNhatGiaDatHang(ct.getMaChiTietHoaDon(), Double.parseDouble(jTextFieldTongTien.getText()));
		cthd_dao.docTuBang();
        List<ChiTietHoaDon> m = cthd_dao.getList();
        String maHD = hd.getMaHoaDon();
        m = m.stream().filter(x->
        	x.getMaHoaDon().getMaHoaDon().equalsIgnoreCase(maHD)
        ).toList();
        double tongTien = 0;
        for(ChiTietHoaDon c :m) {
            tongTien = tongTien + c.getGiaDatPhong();
        }
        hd_dao.capNhatKhachVaTongTien(kh_dao.timKhachHangTheoSoDienThoai(jTextFieldSoDienThoai.getText()).getMaKhachHang()
            ,tongTien
            ,hd.getMaHoaDon());
		if (parentFrame != null) {
			parentFrame.dispose(); // Đóng JFrame chứa JPanel này
		}
		
	}// GEN-LAST:event_jButtonXacNhanActionPerformed

	
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

	private void jComboBoxDichVuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxDichVuActionPerformed
		 int i = jComboBoxDichVu.getSelectedIndex(); // Lấy chỉ mục đã chọn từ jComboBox
 	    ArrayList<DichVu> dsdv = dv_dao.getDsDichVu(); // Lấy danh sách dịch vụ từ dv_dao

 	    // Kiểm tra nếu chỉ mục hợp lệ
 	    if (i >= 0 && i < dsdv.size()) {
 	        // Lấy số lượng từ jSpinner
 	        int soLuong = (int) jSpinnerSoLuongDichVu.getValue();

 	        // Tính tiền dựa trên số lượng và giá dịch vụ
 	        double tien = soLuong * dsdv.get(i).getGiaDichVu();

 	        // Hiển thị tổng tiền trong jTextFieldGiaDichVu
 	        jTextFieldGiaDichVu.setText(String.valueOf(tien));
 	    }
 	    
	}// GEN-LAST:event_jComboBoxDichVuActionPerformed

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

	private void jButtonThemDichVuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonThemDichVuActionPerformed
	    int i = jComboBoxDichVu.getSelectedIndex(); // Lấy chỉ mục đã chọn từ jComboBox
	    ArrayList<DichVu> dsdv = dv_dao.getDsDichVu(); // Lấy danh sách dịch vụ từ dv_dao

	    if (i >= 0 && i < dsdv.size()) {
	        String ma = dsdv.get(i).getMaDichVu();
	        boolean found = false; // Biến flag để kiểm tra dịch vụ đã có trong bảng chưa

	        // Duyệt qua tất cả các dòng trong modelDichVu
	        for (int m = 0; m < modelDichVu.getRowCount(); m++) {
	            if (modelDichVu.getValueAt(m, 0).equals(ma)) {
	                found = true; // Dịch vụ đã có trong bảng

	                // Lấy số lượng và tính toán lại
	                int soLuong = (int) jSpinnerSoLuongDichVu.getValue();
	                int soLuongCu = (int) modelDichVu.getValueAt(m, 2);
	                modelDichVu.setValueAt(soLuong + soLuongCu, m, 2); // Cập nhật số lượng
	                double tien = (soLuong + soLuongCu) * dsdv.get(i).getGiaDichVu();
	                modelDichVu.setValueAt(tien, m, 3); // Cập nhật tiền
	                break; // Dừng vòng lặp khi tìm thấy dịch vụ
	            }
	        }

	        // Nếu dịch vụ chưa có trong bảng, thêm mới
	        if (!found) {
	            int soLuong = (int) jSpinnerSoLuongDichVu.getValue();
	            double tien = soLuong * dsdv.get(i).getGiaDichVu();
	            modelDichVu.addRow(new Object[] {
	                dsdv.get(i).getMaDichVu(),
	                dsdv.get(i).getTenDichVu(),
	                soLuong,
	                tien
	            });
	        }

	        // Cập nhật tổng tiền sau khi thay đổi
	        jTextFieldTongTien.setText(Double.toString(tinhTongTien()));
	    }
	}// GEN-LAST:event_jButtonThemDichVuActionPerformed

// GEN-LAST:event_jButtonThemDichVuActionPerformed

	private double tinhTongTien() {
		double tongtienDichVu = 0;
		for (int i = 0; i < modelDichVu.getRowCount(); i++) {
			tongtienDichVu += Double.parseDouble(modelDichVu.getValueAt(i, 3).toString());
		}
		return giaThuePhong + tongtienDichVu;
	}

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
    private javax.swing.JButton jButtonHuy;
    private javax.swing.JButton jButtonThemDichVu;
    private javax.swing.JButton jButtonXacNhan;
    private javax.swing.JComboBox<String> jComboBoxDichVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinnerSoLuongDichVu;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldGiaDichVu;
    private javax.swing.JTextField jTextFieldSoDienThoai;
    private javax.swing.JTextField jTextFieldTenKhachHang;
    private javax.swing.JTextField jTextFieldTenNhanVien;
    private javax.swing.JTextField jTextFieldTongTien;
    private javax.swing.JLabel labelCheckIn;
    private javax.swing.JLabel labelCheckOut;
    // End of variables declaration//GEN-END:variables
}
