use QuanLyKhachSan
-- Du lieu mau cho bang DichVu


INSERT INTO DichVu (maDichVu, tenDichVu, moTa, giaDichVu) VALUES
('DV00000001-001', 'Bua Sang', 'Bua sang buffet voi nhieu mon ngon', 150000),
('DV00000002-002', 'Bua Trua', 'Bua trua buffet voi nhieu mon ngon', 120000),
('DV00000003-003', 'Bua Chieu', 'Bua chieu buffet voi nhieu mon ngon', 200000),
('DV00000004-004', 'Giat Ui', 'Dich vu giat ui nhanh chong', 50000),
('DV00000005-005', 'Dua Don San Bay', 'Dich vu dua don tai san bay', 700000);

-- Du lieu mau cho bang LoaiPhong
INSERT INTO LoaiPhong (maLoaiPhong, tenLoaiPhong, moTa, soLuongNguoi, giaThuePhong) VALUES
('LP00000001-001', 'Phong Don', 'Phong cho 1 nguoi', 1, 600000),
('LP00000002-002', 'Phong Doi', 'Phong cho 2 nguoi', 2, 800000),
('LP00000003-003', 'Phong Gia Dinh', 'Phong cho 4 nguoi', 4, 1200000),
('LP00000004-004', 'Phong Sang Trong', 'Phong voi cac tien nghi cao cap', 2, 2000000);

-- Du lieu mau cho bang ThietBi
INSERT INTO ThietBi (maThietBi, tenThietBi, trangThai) VALUES
('TB01', 'TV', 'DANG_HOAT_DONG'),
('TB02', 'May lanh', 'KHONG_HOAT_DONG'),
('TB03', 'Tu lanh', 'DANG_HOAT_DONG'),
('TB04', 'Ban ghe', 'DANG_HOAT_DONG'),
('TB05', 'TV', 'KHONG_HOAT_DONG'),
('TB06', 'May lanh', 'DANG_HOAT_DONG'),
('TB07', 'Tu lanh', 'KHONG_HOAT_DONG'),
('TB08', 'Ban ghe', 'KHONG_HOAT_DONG');

-- Du lieu mau cho bang KhuVuc
INSERT INTO KhuVuc (maKhuVuc, tenKhuVuc, moTa) VALUES
('KA', 'Khu Vuc A', 'Khu vuc A cua khach san'),
('KB', 'Khu Vuc B', 'Khu vuc B cua khach san'),
('KC', 'Khu Vuc C', 'Khu vuc C cua khach san');

-- Du lieu mau cho bang KhachHang
INSERT INTO KhachHang (maKhachHang, tenKhachHang, soDienThoai, gioiTinh, diaChi, ngaySinh, cccd, email) VALUES
('KH0001-001', 'Nguyen Van A', '0123456789', 'Nam', 'Ha Noi', '1985-05-15', '1234567890', 'a@gmail.com'),
('KH0002-002', 'Tran Thi B', '0987654321', 'Nu', 'Da Nang', '1990-08-22', '9876543210', 'b@gmail.com'),
('KH0003-003', 'Le Van C', ' ', 'Nam', 'TP.HCM', '1988-03-12', '1112233440', 'c@gmail.com');

-- Du lieu mau cho bang Voucher
INSERT INTO Voucher (maVoucher, tenVoucher, giaGiam, ngayBatDau, ngayKetThuc) VALUES
('VC2023101-01', 'Giam Gia 10%', 10, '2024-11-01', '2024-12-31'), -- Ngay bat dau da thay doi
('VC2023102-03', 'Giam Gia 10%', 10, '2023-10-01', '2023-12-31'),
('VC2023103-02', 'Giam Gia 20%', 20, '2024-10-01', '2024-11-30');

-- Du lieu mau cho bang Phong
INSERT INTO Phong (maPhong, tenPhong, trangThaiPhong, maLoaiPhong, maKhuVuc) VALUES
-- Khu vuc KA
('P001', 'Phong 101', 'TRONG', 'LP00000001-001', 'KA'),
('P002', 'Phong 102', 'TRONG', 'LP00000001-001', 'KA'),
('P003', 'Phong 103', 'TRONG', 'LP00000001-001', 'KA'),
('P004', 'Phong 104', 'TRONG', 'LP00000001-001', 'KA'),
('P005', 'Phong 105', 'DADAT', 'LP00000001-001', 'KA'),
('P006', 'Phong 106', 'TRONG', 'LP00000001-001', 'KA'),
('P007', 'Phong 107', 'BAOTRI', 'LP00000001-001', 'KA'),
('P008', 'Phong 108', 'TRONG', 'LP00000001-001', 'KA'),
('P009', 'Phong 109', 'DANGDONDEP', 'LP00000001-001', 'KA'),
('P010', 'Phong 110', 'TRONG', 'LP00000001-001', 'KA'),

-- Khu vuc KB
('P011', 'Phong 201', 'TRONG', 'LP00000002-002', 'KB'),
('P012', 'Phong 202', 'TRONG', 'LP00000002-002', 'KB'),
('P013', 'Phong 203', 'DADAT', 'LP00000002-002', 'KB'),
('P014', 'Phong 204', 'TRONG', 'LP00000002-002', 'KB'),
('P015', 'Phong 205', 'TRONG', 'LP00000002-002', 'KB'),
('P016', 'Phong 206', 'BAOTRI', 'LP00000002-002', 'KB'),
('P017', 'Phong 207', 'TRONG', 'LP00000002-002', 'KB'),
('P018', 'Phong 208', 'DANGDONDEP', 'LP00000002-002', 'KB'),
('P019', 'Phong 209', 'TRONG', 'LP00000002-002', 'KB'),
('P020', 'Phong 210', 'TRONG', 'LP00000002-002', 'KB'),

-- Khu vuc KC
('P021', 'Phong 301', 'TRONG', 'LP00000003-003', 'KC'),
('P022', 'Phong 302', 'TRONG', 'LP00000003-003', 'KC'),
('P023', 'Phong 303', 'TRONG', 'LP00000003-003', 'KC'),
('P024', 'Phong 304', 'TRONG', 'LP00000003-003', 'KC'),
('P025', 'Phong 305', 'DADAT', 'LP00000003-003', 'KC'),
('P026', 'Phong 306', 'TRONG', 'LP00000003-003', 'KC'),
('P027', 'Phong 307', 'BAOTRI', 'LP00000003-003', 'KC'),
('P028', 'Phong 308', 'TRONG', 'LP00000003-003', 'KC'),
('P029', 'Phong 309', 'DANGDONDEP', 'LP00000003-003', 'KC'),
('P030', 'Phong 310', 'TRONG', 'LP00000003-003', 'KC');

select * from Phong
-- Du lieu mau cho bang ChiTietHoaDon
INSERT INTO ChiTietHoaDon (maChiTietHoaDon, ngapLapHoaDon, giaDatHang) VALUES
('CTHD000000001-001', '2024-10-14', 750000),
('CTHD000000002-002', '2024-10-15', 850000),
('CTHD000000003-003', '2024-10-16', 950000),
('CTHD000000004-004', '2024-10-17', 600000),
('CTHD000000005-005', '2024-10-18', 1200000);

-- Chen du lieu vao bang LichSuDatPhong
INSERT INTO LichSuDatPhong (maChiTietHoaDon, maPhong, soLuong, thoiGianDatPhong) VALUES
('CTHD000000001-001', 'P003', 1, '2024-10-14 14:00:00'),
('CTHD000000001-001', 'P004', 1, '2024-10-14 14:00:00'),
('CTHD000000002-002', 'P013', 1, '2024-10-15 12:00:00'),
('CTHD000000003-003', 'P022', 1, '2024-10-16 10:00:00'),
('CTHD000000004-004', 'P023', 1, '2024-10-17 11:00:00'),
('CTHD000000005-005', 'P024', 1, '2024-10-18 15:00:00');

-- Chen du lieu vao bang LichSuDatDichVu
INSERT INTO LichSuDatDichVu (maChiTietHoaDon, maDichVu, soLuongDatHang, thoiGianDatDichVu) VALUES
('CTHD000000001-001', 'DV00000001-001', 2, '2024-10-14 15:00:00'),
('CTHD000000001-001', 'DV00000004-004', 1, '2024-10-14 16:00:00'),
('CTHD000000002-002', 'DV00000003-003', 1, '2024-10-15 13:00:00'),
('CTHD000000003-003', 'DV00000002-002', 1, '2024-10-16 11:00:00'),
('CTHD000000004-004', 'DV00000005-005', 1, '2024-10-17 12:00:00');

-- Chen du lieu vao bang LoaiNhanVien
INSERT INTO LoaiNhanVien (maLoaiNhanVien, tenLoaiNhanVien) VALUES
('MLNV01', 'Nhan Vien'),
('MLNV02', 'Quan Ly');


-- Dữ liệu mẫu cho bảng NhanVien
INSERT INTO NhanVien (maNhanVien, tenNhanVien, soDienThoai, gioiTinh, diaChi, ngaySinh, email, maLoaiNhanVien, trangThai) VALUES
('NV001', 'Nguyen Cong Danh', '0123456789', 'NAM',  'Ha Noi', '1990-04-01', 'd@gmail.com', 'MLNV01', 'DANG_LAM_VIEC'),
('NV003', 'Tran Quoc Bao', '0123456781', 'NAM',  'Ho Chi Minh', '1990-05-01', 'd1@gmail.com', 'MLNV02', 'DANG_LAM_VIEC'),
('NV004', 'Dang Tran Tan Phat', '0923456781', 'NAM',  'Ho Chi Minh', '1991-04-01', 'd1@gmail.com', 'MLNV02', 'NGHI_PHEP'),
('NV002', 'Huynh Thanh Liem', '7654321', 'NU', 'Da Nang', '1995-02-22', 'e@gmail.com', 'MLNV01', 'NGHI_VIEC');

-- Dữ liệu mẫu cho bảng HoaDon
INSERT INTO HoaDon (maHoaDon, ngayLapHoaDon, maNhanVien, maVoucher, maKhachHang, maChiTietHoaDon, VAT, trangThai, checkIN, checkOUT, datCoc, tienPhat, tongTien) VALUES
('HD00000001-003', '2024-10-14', 'NV001', NULL, 'KH0001-001', 'CTHD000000001-001', 10, 1, 1, 0, 150000, 0, 900000),
('HD00000001-004', '2024-10-15', 'NV002', 'VC2023101-01', 'KH0001-001', 'CTHD000000002-002', 10, 1, 1, 0, 200000, 0, 1100000),
('HD00000001-005', '2024-10-16', 'NV001', NULL, 'KH0001-001', 'CTHD000000003-003', 10, 1, 1, 0, 250000, 0, 950000),
('HD00000001-006', '2024-10-17', 'NV002', 'VC2023103-02', 'KH0002-002', 'CTHD000000004-004', 10, 1, 1, 0, 300000, 0, 1200000),
('HD00000001-007', '2024-10-18', 'NV001', NULL, 'KH0001-001', 'CTHD000000005-005', 10, 1, 1, 0, 150000, 0, 1350000);


-- Dữ liệu mẫu cho bảng TaiKhoan
INSERT INTO TaiKhoan (tenTaiKhoan, matKhau, trangThai, maNhanVien) VALUES
('danomo', '123', 'KHONG_HOAT_DONG', 'NV001'),
('liem', '123', 'KHONG_HOAT_DONG', 'NV002'),
('bao', '123', 'KHONG_HOAT_DONG', 'NV003'),
('phat', '123', 'KHONG_HOAT_DONG', 'NV004');
-- Dữ liệu ca làm việc
INSERT INTO CaLamViec (maCaLamVien, tenCaLamViec, ngayLamviec, tongTienTrongCa, maNhanVien) VALUES
('CA1', 'CA_SANG', '2024-10-10', 700000, 'NV001'),
('CA2', 'CA_TRUA', '2024-10-10', 0, 'NV001'),
('CA3', 'CA_TOI', '2024-10-10', 0, 'NV001'),
('CA4', 'CA_SANG', '2024-10-11', 800000, 'NV002'),
('CA5', 'CA_TRUA', '2024-10-11', 0, 'NV002'),
('CA6', 'CA_TOI', '2024-10-11', 0, 'NV002'),
('CA7', 'CA_SANG', '2024-10-14', 900000, 'NV001'),
('CA8', 'CA_TRUA', '2024-10-14', 0, 'NV001'),
('CA9', 'CA_SANG', '2024-10-15', 1100000, 'NV002'),
('CA10', 'CA_TRUA', '2024-10-15', 0, 'NV002');



