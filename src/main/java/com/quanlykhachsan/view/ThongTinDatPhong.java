/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quanlykhachsan.view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
import com.quanlykhachsan.dao.ThongTinDatPhong_DAO;
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

/**
 *
 * @author liemh
 */
public class ThongTinDatPhong extends javax.swing.JPanel {

	/**
	 * Creates new form ThongTinDatPhong
	 */
	private final double coc = 0.2;
	private KhachHang_DAO kh_dao;
	private DichVu_DAO dv_dao;
	private LoaiPhong_DAO lp_dao;
	private ChiTietHoaDon_DAO cthd_dao;
	private LichSuDatDichVu_DAO lsddv_dao;
	private HoaDon_DAO hd_dao;
	private NhanVien_DAO nv_dao;
	private Phong_DAO p_dao;
	private ThongTinDatPhong_DAO ttdp_dao;
	private HoaDon hd;
	private List<String> dsMaCTHD;
	private double tienCoc=0.0;
	private int k = 0; //phongDangChon
	private List<com.quanlykhachsan.entity.ThongTinDatPhong> ttTemp = new ArrayList<com.quanlykhachsan.entity.ThongTinDatPhong>();
	private List<LichSuDatDichVu> lsdvTemp = new ArrayList<LichSuDatDichVu>();
	private DefaultTableModel modelDichVu = new DefaultTableModel(new String [] {
             "Tên Dịch Vụ", "Số Lượng", "Thành Tiền"
        }, 0);
	private DefaultTableModel modelKhachHang = new DefaultTableModel(new String [] {
            "Tên Khách Hàng", "Loại Người"
       }, 0);
	private DefaultTableModel modelPhong = new DefaultTableModel(new String [] {
            "Mã Phòng", "Loại Phòng", "Giá"
       }, 0);
	private List<Phong> dsPhong;
	private JFrame parentFrame;
	private Date ci;
	private Date co;
	private boolean htt;
	public List<Phong> getDsPhong() {
		return dsPhong;
	}

	public void setDsPhong(List<Phong> dsPhong) {
		this.dsPhong = dsPhong;
	}

	public ThongTinDatPhong(List<Phong> dsPhong, JFrame parentFrame,Date ci, Date co, boolean htt) {
		this.dsPhong = dsPhong;
		this.parentFrame = parentFrame;
		this.ci = ci;
		this.co= co;
		this.htt = htt;
		initComponents();
		p_dao = new Phong_DAO();
		kh_dao = new KhachHang_DAO();
		dv_dao = new DichVu_DAO();
		lp_dao = new LoaiPhong_DAO();
		cthd_dao = new ChiTietHoaDon_DAO();
		lsddv_dao = new LichSuDatDichVu_DAO();
		hd_dao = new HoaDon_DAO();
		nv_dao = new NhanVien_DAO();
		ttdp_dao = new ThongTinDatPhong_DAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH'h' dd/MM/yyyy");		
		labelCheckIn.setText(dateFormat.format(ci));
		labelCheckOut.setText(dateFormat.format(co));
		loadComboxDichVu();
		loadDulieuPhong(dsPhong.get(0));
		loadDSPhong();
		nv_dao.timNhanVienTheoTrangThaiTaiKhoan(TrangThaiTaiKhoan.DANG_HOAT_DONG);
    	List<NhanVien> dsnv = nv_dao.getList();
    	labelNhanVien.setText(dsnv.get(0).getTenNhanVien());
    	LocalDateTime checkInLocalDateTime = convertToLocalDateTime(labelCheckIn.getText());
    	LocalDateTime checkOutLocalDateTime = convertToLocalDateTime(labelCheckOut.getText());
    	if (!checkInLocalDateTime.toLocalDate().equals(LocalDate.now())||htt==true) {
    	    for(Phong phong:dsPhong) {
    	    	LoaiPhong lp = lp_dao.timTheoMa02(phong.getLoaiPhong().getMaLoaiPhong());
    	    	tienCoc = tienCoc + lp.getGiaThuePhong();
    	    }
    	}
    	if(!checkInLocalDateTime.toLocalDate().equals(LocalDate.now())||htt==false){
    		for(Phong phong:dsPhong) {
    	    	LoaiPhong lp = lp_dao.timTheoMa02(phong.getLoaiPhong().getMaLoaiPhong());
    	    	tienCoc = tienCoc + lp.getGiaThuePhong()/24;
    	    }
    	}
    	
    	hd = new HoaDon(taoMaHoaDon(),
    			LocalDate.now(),
    			dsnv.get(0),
    			null,
    			null,
    			0,
    			false,
    			checkInLocalDateTime,
    			checkOutLocalDateTime,
    			tienCoc*coc,
    			0,
    			0.0);
    	hd_dao.themHoaDon(hd);	
    	dsMaCTHD = taoNhieuMaChiTietHoaDon(dsPhong.size());
    	for(int i=0;i<dsPhong.size();i++) {
    		ChiTietHoaDon ct1 = new ChiTietHoaDon(dsMaCTHD.get(i), 
    			p_dao.timPhongTheoMa(dsPhong.get(i).getMaPhong()), 
    			LocalDate.now(), 
    			0.0	, 
    			hd);
    		cthd_dao.themChiTietHoaDon(ct1);
    	}

    	k = 0;	
	}
	
	private void loadDSPhong() {
		dsPhong.stream().forEach(x->{
			modelPhong.addRow(new Object[] {
        		x.getMaPhong(), 
        		lp_dao.timTheoMa02(x.getLoaiPhong().getMaLoaiPhong()).getTenLoaiPhong(), 
        		lp_dao.timTheoMa02(x.getLoaiPhong().getMaLoaiPhong()).getGiaThuePhong() 
			});
		});
		
	}

	private void loadDulieuPhong(Phong phong) {
		labelPhong.setText(phong.getTenPhong());
		LoaiPhong lp = lp_dao.timTheoMa02(phong.getLoaiPhong().getMaLoaiPhong());
//		labelLoaiPhong.setText(lp.getTenLoaiPhong());
		labelGia.setText(String.valueOf(lp.getGiaThuePhong()));
	}

	private void loadComboxDichVu() {
			comBoBoxDV.removeAllItems();
            ArrayList<DichVu> dsdv = new ArrayList<DichVu>();
            dsdv = dv_dao.getDsDichVu();
            dsdv.stream().forEach(x->{
            	comBoBoxDV.addItem(x.getTenDichVu());
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

        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablePhong = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        labelPhong = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelGia = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        labelNhanVien = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTenKHCT = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnThemKH = new javax.swing.JButton();
        btnXoaKH = new javax.swing.JButton();
        btnXoaKHToanBo = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        radioTreEm = new javax.swing.JRadioButton();
        radioNguoiLon = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableKH = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        comBoBoxDV = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        spinnerSL = new javax.swing.JSpinner();
        jLabel21 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDV = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        btnThemDV = new javax.swing.JButton();
        btnXoaDV = new javax.swing.JButton();
        btnXoaToanBoDV = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelCheckIn = new javax.swing.JLabel();
        labelCheckOut = new javax.swing.JLabel();
        labelTongTien = new javax.swing.JLabel();
        btnHuy = new javax.swing.JButton();
        btnDatPhong = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Phòng Đã Đặt "));

        tablePhong.setModel(modelPhong);
        tablePhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePhongMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablePhong);

        jPanel6.setBackground(new java.awt.Color(58, 186, 178));

        labelPhong.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        labelPhong.setText("XXXXXXXXX");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Phòng");

        labelGia.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        labelGia.setText("XXXXXXXXX");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setText("Giá");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addComponent(labelPhong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(41, 41, 41)
                .addComponent(labelGia)
                .addGap(126, 126, 126))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPhong)
                    .addComponent(jLabel4)
                    .addComponent(labelGia)
                    .addComponent(jLabel14))
                .addGap(19, 19, 19))
        );

        labelNhanVien.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        labelNhanVien.setText("XXXXXXXXX");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setText("Nhân viên");

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Khách Hàng"));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Khách hàng đại diện");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Tên khách hàng");

        txtTenKH.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTenKH.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Số điện thoại");

        txtSDT.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtSDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSDTFocusLost(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel19.setText("CCCD");

        txtCCCD.setEditable(false);
        txtCCCD.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel19))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(txtTenKH)
                            .addComponent(txtCCCD)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel16.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel16.setText("Thông tin khách hàng");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel18.setText("Tên khách hàng");

        txtTenKHCT.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTenKHCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHCTActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel20.setText("Loại người");

        btnThemKH.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnThemKH.setText("Thêm");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        btnXoaKH.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnXoaKH.setText("Xóa");
        btnXoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKHActionPerformed(evt);
            }
        });

        btnXoaKHToanBo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnXoaKHToanBo.setText("Xóa toàn bộ");
        btnXoaKHToanBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKHToanBoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 493, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnThemKH)
                .addGap(32, 32, 32)
                .addComponent(btnXoaKH)
                .addGap(41, 41, 41)
                .addComponent(btnXoaKHToanBo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKH)
                    .addComponent(btnXoaKH)
                    .addComponent(btnXoaKHToanBo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        buttonGroup3.add(radioTreEm);
        radioTreEm.setText("Trẻ em");

        buttonGroup3.add(radioNguoiLon);
        radioNguoiLon.setSelected(true);
        radioNguoiLon.setText("Người lớn");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(39, 39, 39)
                        .addComponent(txtTenKHCT, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(34, 34, 34)
                        .addComponent(radioTreEm)
                        .addGap(18, 18, 18)
                        .addComponent(radioNguoiLon)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel16)
                .addGap(139, 139, 139))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTenKHCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(radioTreEm)
                    .addComponent(radioNguoiLon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        tableKH.setModel(modelKhachHang);
        jScrollPane3.setViewportView(tableKH);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(51, 51, 51))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Dịch Vụ"));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("Số lượng");

        comBoBoxDV.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        comBoBoxDV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comBoBoxDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comBoBoxDVActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel15.setText("Dịch vụ");

        spinnerSL.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        spinnerSL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerSLStateChanged(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel21.setText("Giá");

        txtGia.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtGia.setEnabled(false);
        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        tableDV.setModel(modelDichVu);
        jScrollPane2.setViewportView(tableDV);
        if (tableDV.getColumnModel().getColumnCount() > 0) {
            tableDV.getColumnModel().getColumn(2).setResizable(false);
        }

        btnThemDV.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnThemDV.setText("Thêm");
        btnThemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDVActionPerformed(evt);
            }
        });

        btnXoaDV.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnXoaDV.setText("Xóa");
        btnXoaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDVActionPerformed(evt);
            }
        });

        btnXoaToanBoDV.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnXoaToanBoDV.setText("Xóa toàn bộ");
        btnXoaToanBoDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaToanBoDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(btnThemDV)
                .addGap(60, 60, 60)
                .addComponent(btnXoaDV)
                .addGap(47, 47, 47)
                .addComponent(btnXoaToanBoDV)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemDV)
                    .addComponent(btnXoaDV)
                    .addComponent(btnXoaToanBoDV))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel21)
                                .addComponent(jLabel15))
                            .addGap(68, 68, 68)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spinnerSL, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comBoBoxDV, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 46, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comBoBoxDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Từ Ngày:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Đến Ngày:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Tổng Tiền");

        labelCheckIn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelCheckIn.setText("XXXXXXXX");

        labelCheckOut.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelCheckOut.setText("XXXXXXXX");

        labelTongTien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelTongTien.setText("XXXXXXXXXX");

        btnHuy.setBackground(new java.awt.Color(255, 102, 102));
        btnHuy.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnDatPhong.setBackground(new java.awt.Color(58, 186, 178));
        btnDatPhong.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnDatPhong.setText("Xác nhận");
        btnDatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(labelTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelNhanVien))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 2, Short.MAX_VALUE))))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(btnDatPhong)
                        .addGap(76, 76, 76)
                        .addComponent(btnHuy)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(labelNhanVien))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(labelCheckIn)
                            .addComponent(labelCheckOut)
                            .addComponent(labelTongTien))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDatPhong)
                            .addComponent(btnHuy))
                        .addGap(107, 107, 107))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 300, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 86, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comBoBoxDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comBoBoxDVActionPerformed
	 int i = comBoBoxDV.getSelectedIndex(); // Lấy chỉ mục đã chọn từ jComboBox
	    ArrayList<DichVu> dsdv = dv_dao.getDsDichVu(); // Lấy danh sách dịch vụ từ dv_dao
	    if (i >= 0 && i < dsdv.size()) {
	        int soLuong = (int) spinnerSL.getValue();
	        double tien = soLuong * dsdv.get(i).getGiaDichVu();
	        txtGia.setText(String.valueOf(tien));
	    }
    }//GEN-LAST:event_comBoBoxDVActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

	private void btnXoaDVActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXoaDVActionPerformed
		int i = tableDV.getSelectedRow();
		if (i >= 0) { // Kiểm tra xem hàng đã được chọn
			lsddv_dao.xoaTheoMaCTVaMaDV(lsdvTemp.get(i).getChiTietHoaDon().getMaChiTietHoaDon(),
					lsdvTemp.get(i).getDichVu().getMaDichVu());
			modelDichVu.removeRow(i);
			lsdvTemp.remove(i);
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
		}

	}// GEN-LAST:event_btnXoaDVActionPerformed

	private void spinnerSLStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_jSpinnerSoLuongDichVuStateChanged
		int i = comBoBoxDV.getSelectedIndex(); // Lấy chỉ mục đã chọn từ jComboBox
		int soLuong = (int) spinnerSL.getValue();
		if(soLuong<=0) {
			JOptionPane.showMessageDialog(this, "Số Lượng phải >=1", "Thông báo",
	                JOptionPane.INFORMATION_MESSAGE);
			spinnerSL.setValue(1);
			return;
		}
		ArrayList<DichVu> dsdv = dv_dao.getDsDichVu(); // Lấy danh sách dịch vụ từ dv_dao
		if (i >= 0 && i < dsdv.size()) {
			double tien = soLuong * dsdv.get(i).getGiaDichVu();
			txtGia.setText(String.valueOf(tien));
		}

	}// GEN-LAST:event_jSpinnerSoLuongDichVuStateChanged


    private void txtSDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSDTFocusLost
    	String soDienThoai = txtSDT.getText(); // Lấy số điện thoại từ text field
	    KhachHang khachHang = kh_dao.timKhachHangTheoSoDienThoai(soDienThoai); // Gọi hàm tìm khách hàng theo số điện thoại
	    
	    if (khachHang != null) {
	        txtTenKH.setText(khachHang.getTenKhachHang()); // Nếu tìm thấy, hiển thị tên khách hàng
	        txtCCCD.setText(khachHang.getCCCD());
	    } else {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng với số điện thoại này.", "Thông báo",
	                JOptionPane.INFORMATION_MESSAGE); // Hiển thị thông báo nếu không tìm thấy khách hàng
	    }
    }//GEN-LAST:event_txtSDTFocusLost
    private void btnThemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVActionPerformed
    	int i = comBoBoxDV.getSelectedIndex(); // Lấy chỉ mục đã chọn từ jComboBox
    	int soLuong = (int) spinnerSL.getValue();
    	if(soLuong<=0) {
			JOptionPane.showMessageDialog(this, "Số Lượng phải >=1", "Thông báo",
	                JOptionPane.INFORMATION_MESSAGE);
			spinnerSL.setValue(1);
			return;
		}
    	ArrayList<DichVu> dsdv = dv_dao.getDsDichVu(); // Lấy danh sách dịch vụ từ dv_dao
	    if (i >= 0 && i < dsdv.size()) {
	        double tien = soLuong * dsdv.get(i).getGiaDichVu();
	        modelDichVu.addRow(new Object[] {
	        		dsdv.get(i).getTenDichVu(),soLuong,tien 
	        });
	    }
	    k = tablePhong.getSelectedRow();
	    String maCTHD = (k < 0) ? dsMaCTHD.get(0) : dsMaCTHD.get(k);

	    lsdvTemp.add(new LichSuDatDichVu(
	        new ChiTietHoaDon(maCTHD), 
	        new DichVu(dsdv.get(i).getMaDichVu()), 
	        LocalDate.now(), 
	        (int) spinnerSL.getValue()
	    ));
	    	
    }//GEN-LAST:event_btnThemDVActionPerformed

    private void btnDatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatPhongActionPerformed
    	if(txtSDT.getText().isEmpty()||txtSDT.getText()==null) {
    		JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng.", "Thông báo",
    	      JOptionPane.INFORMATION_MESSAGE); 
    		return;
    	}
    	if(dsMaCTHD.size()==1) {
    		lsdvTemp.stream().forEach(x->lsddv_dao.themLichSuDatDichVu(x));
    		ttTemp.stream().forEach(x->ttdp_dao.them(x));
    	}
    	if(!lsdvTemp.isEmpty())
	    	if(lsdvTemp.get(0)!=null) {
	    		lsddv_dao.xoaTheoMaCTHD(lsdvTemp.get(0).getChiTietHoaDon().getMaChiTietHoaDon());
	    		lsdvTemp.stream().forEach(x->lsddv_dao.themLichSuDatDichVu(x));
	    	}
    	if(!ttTemp.isEmpty())
	    	if(ttTemp.get(0)!=null) {
	    		ttdp_dao.xoaALL(ttTemp.get(0).getMaChiTietHoaHon().getMaChiTietHoaDon());
	    		ttTemp.stream().forEach(x->ttdp_dao.them(x));
	    	}
        dsMaCTHD.stream().forEach(x -> {
            double tongTienDV = 0.0;
            // Lấy danh sách dịch vụ đã đặt theo mã chi tiết hóa đơn
            List<LichSuDatDichVu> d = lsddv_dao.timLichSuDatDichVuTheoMaCTHD(x);
            for(LichSuDatDichVu ls : d) {
                DichVu o = dv_dao.timDichVu(ls.getDichVu().getMaDichVu());
                tongTienDV = tongTienDV + o.getGiaDichVu() * ls.getSoLuong();
            }
            Phong j = p_dao.timPhongTheoMa(cthd_dao.timChiTietHoaDonTheoMaCT(x).getMaPhong().getMaPhong());
            LocalDateTime dau = convertToLocalDateTime(labelCheckIn.getText());
            LocalDateTime sau = convertToLocalDateTime(labelCheckOut.getText());
            long soNgay = ChronoUnit.DAYS.between(dau, sau);
            double giaThue = lp_dao.timTheoMa02(j.getLoaiPhong().getMaLoaiPhong()).getGiaThuePhong();
            cthd_dao.capNhatGiaDatHang(x, tongTienDV+giaThue*soNgay);
        });
        cthd_dao.docTuBang();
        List<ChiTietHoaDon> m = cthd_dao.getList();
        m = m.stream().filter(x->
        	x.getMaHoaDon().getMaHoaDon().equalsIgnoreCase(hd.getMaHoaDon())
        ).toList();
        System.err.println(m);
        double tongTien = 0;
        for(ChiTietHoaDon ct :m) {
            tongTien = tongTien + ct.getGiaDatPhong();
        }
        hd_dao.capNhatKhachVaTongTien(kh_dao.timKhachHangTheoSoDienThoai(txtSDT.getText()).getMaKhachHang()
            ,tongTien
            ,hd.getMaHoaDon());
        for(Phong p: dsPhong) {
            try {
            	if(hd.getTienCoc()==0.0)
            		p.setTrangThai(TrangThaiPhong.DA_DAT);
            	else	
            		p.setTrangThai(TrangThaiPhong.DA_COC);
                p_dao.capNhatPhong(p);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(this, "Đặt Phòng Thành Công!!", "Thông báo",
            JOptionPane.INFORMATION_MESSAGE); // Hiển thị thông báo nếu không tìm thấy khách hàng
        parentFrame.dispose();
    }//GEN-LAST:event_btnDatPhongActionPerformed

    private void btnXoaKHToanBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKHToanBoActionPerformed
        modelKhachHang.setRowCount(0);
        ttTemp.clear();
    }//GEN-LAST:event_btnXoaKHToanBoActionPerformed

    private void btnXoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKHActionPerformed
        int i = tableKH.getSelectedRow();
        if (i >= 0) { // Kiểm tra xem hàng đã được chọn
            modelKhachHang.removeRow(i);
            ttTemp.remove(i);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
        }

    }//GEN-LAST:event_btnXoaKHActionPerformed

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
    	String ten = txtTenKHCT.getText();
    	if(ten.isEmpty()||ten==null) {
    		JOptionPane.showMessageDialog(this, "Tên Khách Hàng Không để trống", "Thông báo",
	               JOptionPane.INFORMATION_MESSAGE);
    		return;
    	}
    	k = tablePhong.getSelectedRow();
        String maCTHD = (k < 0) ? dsMaCTHD.get(0) : dsMaCTHD.get(k);
        ChiTietHoaDon ct = cthd_dao.timChiTietHoaDonTheoMaCT(maCTHD);
        Phong p = p_dao.timPhongTheoMa(ct.getMaPhong().getMaPhong());
        LoaiPhong lp = lp_dao.timTheoMa02(p.getLoaiPhong().getMaLoaiPhong());
    	if(modelKhachHang.getRowCount()==lp.getSoLuongNguoi()) {
    		JOptionPane.showMessageDialog(this, "Phòng này đã đủ số lượng người!", "Thông báo",
 	               JOptionPane.INFORMATION_MESSAGE);
    		return;
    	}
        boolean nguoiLon ;
        if(radioNguoiLon.isSelected())
        nguoiLon = true;
        else
        nguoiLon = false;
        modelKhachHang.addRow(new Object[] {
            ten, nguoiLon ? "Người Lớn" : "Trẻ Em"
        });

        ttTemp.add(new com.quanlykhachsan.entity.ThongTinDatPhong(
            new ChiTietHoaDon(maCTHD),
            ten,
            nguoiLon
        ));
        txtTenKHCT.setText("");
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void txtTenKHCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHCTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHCTActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
		dsMaCTHD.stream().forEach(x->{
			lsddv_dao.xoaTheoMaCTHD(x);
			ttdp_dao.xoaALL(x);
			cthd_dao.xoaTheoMa(x);	
		});
    	hd_dao.xoaHoaDon(hd.getMaHoaDon());
    	if (parentFrame != null) {
	        parentFrame.dispose(); // Đóng JFrame chứa JPanel này
	    }		
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXoaToanBoDVActionPerformed(java.awt.event.ActionEvent evt) {                                               
       modelDichVu.setRowCount(0);
       lsdvTemp.clear();
       lsddv_dao.xoaAll();
    }                                             

	private void tablePhongMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tablePhongMouseClicked
		System.err.println(k);
//		int flat = k;
		if(k==-1) {
			k = 0;
			return;
		}
//		if (k == flat) {
//			return;
//		}
		System.err.println(k);
		List<LichSuDatDichVu> temp1 = lsddv_dao.timLichSuDatDichVuTheoMaCTHD(dsMaCTHD.get(k));
		if (!temp1.isEmpty()) {
			lsdvTemp.stream().forEach(x -> {
				LichSuDatDichVu m = temp1.stream()
						.filter(y -> y.getDichVu().getMaDichVu().equals(x.getDichVu().getMaDichVu())).findFirst()
						.orElse(null);
				if (m == null)
					lsddv_dao.themLichSuDatDichVu(x);
				else
					lsddv_dao.suaLichSuDatDichVu(x);
			});
		} else {
			lsdvTemp.stream().forEach(x -> {
				lsddv_dao.themLichSuDatDichVu(x);
			});
		}
		List<com.quanlykhachsan.entity.ThongTinDatPhong> temp2 = ttdp_dao.timTTTheoMaCTHD(dsMaCTHD.get(k));
		if (!temp2.isEmpty()) {
			ttdp_dao.xoaALL(dsMaCTHD.get(k));
			ttTemp.stream().forEach(x -> {
				ttdp_dao.them(x);
			});
		} else {
			ttTemp.stream().forEach(x -> {
				ttdp_dao.them(x);
			});
		}

		lsdvTemp.clear();
		ttTemp.clear();
		k = tablePhong.getSelectedRow();
		
		if (k >= 0) {
			ChiTietHoaDon cthd = cthd_dao.timChiTietHoaDonTheoMaCT(dsMaCTHD.get(k));
			if (cthd != null) {
				List<LichSuDatDichVu> ds = lsddv_dao.timLichSuDatDichVuTheoMaCTHD(cthd.getMaChiTietHoaDon());// 903
				lsdvTemp = ds;
				modelDichVu.setRowCount(0);
				ds.stream().forEach(x -> {
					modelDichVu.addRow(
							new Object[] { dv_dao.timDichVu(x.getDichVu().getMaDichVu()).getTenDichVu(), x.getSoLuong(),
									x.getSoLuong() * dv_dao.timDichVu(x.getDichVu().getMaDichVu()).getGiaDichVu() });
				});
				List<com.quanlykhachsan.entity.ThongTinDatPhong> dstt = ttdp_dao
						.timTTTheoMaCTHD(cthd.getMaChiTietHoaDon());
				ttTemp = dstt;
				modelKhachHang.setRowCount(0);
				dstt.stream().forEach(x -> {
					modelKhachHang.addRow(new Object[] { x.getHoVaTen(), x.isLaNguoiLon() ? "Người Lớn" : "Trẻ Em" });
				});
			} else {
				modelDichVu.setRowCount(0);
				modelKhachHang.setRowCount(0);
				txtTenKHCT.setText("");
				String ma = dsMaCTHD.get(k);
				Phong p = p_dao.timPhongTheoMa(dsPhong.get(k).getMaPhong());
				LocalDate thoiGianDat = LocalDate.now();
				double tien = 0.0;
				ChiTietHoaDon newcthd = new ChiTietHoaDon(ma, p, thoiGianDat, tien, hd);
				cthd_dao.themChiTietHoaDon(newcthd);
			}

			loadDulieuPhong(dsPhong.get(k));
		}else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn Phòng để điền thông tin!!", "Thông báo",
	                JOptionPane.INFORMATION_MESSAGE);
		}

	}// GEN-LAST:event_tablePhongMouseClicked

	private String taoMaHoaDon() {
		hd_dao.docTuBang();
		int i = hd_dao.getList().size();
		LocalDate ngayLapHoaDon = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
	    String ngayFormatted = ngayLapHoaDon.format(formatter); // Định dạng ngày

	    // Tạo số tự động (YYY) với 3 chữ số
	    String soTuDong = String.format("%03d", i + 1); // i + 1 để bắt đầu từ 1

	    // Kết hợp thành mã chi tiết hóa đơn
	    return "HD" + ngayFormatted + "-" + soTuDong;
	}

	private List<String> taoNhieuMaChiTietHoaDon(int soLuong) {
	    cthd_dao.docTuBang();
	    int i = cthd_dao.getList().size(); // Giả sử i bắt đầu từ 0

	    LocalDate ngayLapHoaDon = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
	    String ngayFormatted = ngayLapHoaDon.format(formatter); // Định dạng ngày MMddyyyy

	    List<String> danhSachMa = new ArrayList<>();
	    
	    for (int j = 1; j <= soLuong; j++) {
	        String soTuDong = String.format("%03d", i + j); // i + j để tăng số tự động
	        String maChiTietHoaDon = "CTHD1" + ngayFormatted + "-" + soTuDong;
	        danhSachMa.add(maChiTietHoaDon);
	    }
	    
	    return danhSachMa;
	}	
	public LocalDateTime convertToLocalDateTime(String dateStr) {
	    try {
	        // Định dạng ngày giờ theo kiểu HH'h' dd/MM/yyyy
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH'h' dd/MM/yyyy");
	        
	        // Chuyển chuỗi thành LocalDateTime
	        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, formatter);
	        
	        // Thiết lập phút, giây, nano bằng 0
	        localDateTime = localDateTime.withMinute(0).withSecond(0).withNano(0);
	        
	        System.out.println(localDateTime);
	        return localDateTime;
	    } catch (Exception e) {
	        System.err.println("Lỗi khi chuyển đổi chuỗi: " + e.getMessage());
	        return null;
	    }
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatPhong;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThemDV;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnXoaDV;
    private javax.swing.JButton btnXoaKH;
    private javax.swing.JButton btnXoaKHToanBo;
    private javax.swing.JButton btnXoaToanBoDV;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> comBoBoxDV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelCheckIn;
    private javax.swing.JLabel labelCheckOut;
    private javax.swing.JLabel labelGia;
    private javax.swing.JLabel labelNhanVien;
    private javax.swing.JLabel labelPhong;
    private javax.swing.JLabel labelTongTien;
    private javax.swing.JRadioButton radioNguoiLon;
    private javax.swing.JRadioButton radioTreEm;
    private javax.swing.JSpinner spinnerSL;
    private javax.swing.JTable tableDV;
    private javax.swing.JTable tableKH;
    private javax.swing.JTable tablePhong;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenKHCT;
    // End of variables declaration//GEN-END:variables
}
