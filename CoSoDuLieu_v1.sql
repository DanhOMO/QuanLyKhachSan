use master
drop database QuanLyKhachSan
create database QuanLyKhachSan
use QuanLyKhachSan
-- Bảng DichVu
CREATE TABLE DichVu (
    maDichVu VARCHAR(50) PRIMARY KEY,
    tenDichVu VARCHAR(100),
    moTa TEXT,
    giaDichVu float
);
-- Bảng LoaiPhong
CREATE TABLE LoaiPhong (
    maLoaiPhong VARCHAR(50) PRIMARY KEY,
    tenLoaiPhong VARCHAR(100),
    moTa TEXT,
    soLuongNguoi INT,
    giaThuePhong float
);


CREATE TABLE ThietBi (
    maThietBi VARCHAR(50) PRIMARY KEY,
    tenThietBi VARCHAR(100),
    trangThai VARCHAR(50)
);
-- Bảng KhuVuc
CREATE TABLE KhuVuc (
    maKhuVuc VARCHAR(50) PRIMARY KEY,
    tenKhuVuc VARCHAR(100),
    moTa TEXT
);


-- Bảng KhachHang
CREATE TABLE KhachHang (
    maKhachHang VARCHAR(50) PRIMARY KEY,
    tenKhachHang VARCHAR(100),
    soDienThoai VARCHAR(15),
    gioiTinh varchar(50),
    diaChi TEXT,
    ngaySinh DATE,
    cccd VARCHAR(50),
    email VARCHAR(100),
);

-- Bảng Voucher
CREATE TABLE Voucher (
    maVoucher VARCHAR(50) PRIMARY KEY,
    tenVoucher VARCHAR(100),
    giaGiam float,
    ngayBatDau DATE,
    ngayKetThuc DATE
);
-- Bảng Phong
CREATE TABLE Phong (
    maPhong VARCHAR(50) PRIMARY KEY,
    tenPhong NVARCHAR(50) ,
    trangThaiPhong varchar(50),
    maLoaiPhong VARCHAR(50),
	maKhuVuc VARCHAR(50),
    FOREIGN KEY (maLoaiPhong) REFERENCES LoaiPhong(maLoaiPhong),
	FOREIGN KEY (maKhuVuc) REFERENCES KhuVuc(maKhuVuc),
	CONSTRAINT CHK_TrangThaiPhong CHECK (trangThaiPhong IN ('TRONG', 'DA_DAT', 'BAO_TRI', 'DON_DEP', 'DA_COC')),
);
CREATE TABLE Phong_ThietBi (
    maThietBi VARCHAR(50),
    maPhong VARCHAR(50),
    PRIMARY KEY (maPhong, maThietBi),
    FOREIGN KEY (maThietBi) REFERENCES ThietBi(maThietBi),
    FOREIGN KEY (maPhong) REFERENCES Phong(maPhong)
);
-- Bảng ChiTietHoaDon
CREATE TABLE ChiTietHoaDon (
    maChiTietHoaDon VARCHAR(50) PRIMARY KEY,
    ngapLapHoaDon DATE,
    giaDatHang float
);
CREATE TABLE LichSuDatPhong (
    maChiTietHoaDon VARCHAR(50),
    maPhong VARCHAR(50),
	soLuong INT,
	thoiGianDatPhong datetime,
    PRIMARY KEY (maChiTietHoaDon, maPhong),
    FOREIGN KEY (maChiTietHoaDon) REFERENCES ChiTietHoaDon(maChiTietHoaDon),
    FOREIGN KEY (maPhong) REFERENCES Phong(maPhong)
);
CREATE TABLE LichSuDatDichVu (
    maChiTietHoaDon VARCHAR(50),
    maDichVu VARCHAR(50),
	thoiGianDatDichVu datetime,
	 soLuongDatHang INT,
    PRIMARY KEY (maChiTietHoaDon, maDichVu),
    FOREIGN KEY (maChiTietHoaDon) REFERENCES ChiTietHoaDon(maChiTietHoaDon),
    FOREIGN KEY (maDichVu) REFERENCES DichVu(maDichVu)
);





-- Bảng LoaiNhanVien
CREATE TABLE LoaiNhanVien (
    maLoaiNhanVien VARCHAR(50) PRIMARY KEY,
    tenLoaiNhanVien VARCHAR(100)
);

-- Bảng NhanVien
CREATE TABLE NhanVien (
    maNhanVien VARCHAR(50) PRIMARY KEY,
    tenNhanVien VARCHAR(100),
    soDienThoai VARCHAR(15),
    gioiTinh varchar(50),
    diaChi TEXT,
    ngaySinh DATE,
    email VARCHAR(100),
    maLoaiNhanVien VARCHAR(50),
    trangThai varchar(50),
    FOREIGN KEY (maLoaiNhanVien) REFERENCES LoaiNhanVien(maLoaiNhanVien),

);
CREATE TABLE CaLamViec(
	maCaLamVien varchar(50) PRIMARY KEY,
	tenCaLamViec varchar(50) ,
	ngayLamviec date,
	tongTienTrongCa float,
	maNhanVien varchar(50),
	FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
	
)
-- Bảng HoaDon
CREATE TABLE HoaDon (
    maHoaDon VARCHAR(50) PRIMARY KEY,
    ngayLapHoaDon DATE,
    maNhanVien VARCHAR(50),
    maVoucher VARCHAR(50),
	maKhachHang VARCHAR(50),
	maChiTietHoaDon VARCHAR(50),
    VAT float,
    trangThai bit,
	checkIN BIT,
    checkOUT BIT,
	datCoc float,
	tienPhat float,
	tongTien float,
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien),
    FOREIGN KEY (maVoucher) REFERENCES Voucher(maVoucher),
	FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKhachHang),
	FOREIGN KEY (maChiTietHoaDon) REFERENCES ChiTietHoaDon(maChiTietHoaDon)
);

-- Bảng TaiKhoan
CREATE TABLE TaiKhoan (
    tenTaiKhoan VARCHAR(50) PRIMARY KEY,
    matKhau VARCHAR(100),
    trangThai VARCHAR(50),
	maNhanVien VARCHAR(50),
	FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
);


-- Ràng Buộc 
ALTER TABLE NhanVien
add CONSTRAINT chk_maNV_Format
CHECK (
    maNhanVien LIKE 'NV[0-9][0-9][0-9]'
);
ALTER TABLE NhanVien
ADD CONSTRAINT chk_tenNhanVien_Format
CHECK (
    tenNhanVien IS NOT NULL AND -- Không được NULL
    tenNhanVien NOT LIKE '%[^A-Za-z ]%' AND -- Không chứa ký tự số và ký tự đặc biệt (chỉ cho phép chữ cái và dấu cách)
    tenNhanVien LIKE '[A-Z]%' AND -- Chữ đầu phải viết hoa
    tenNhanVien NOT LIKE '%  %' AND -- Không được có hai dấu cách liên tiếp
    LEN(LTRIM(RTRIM(tenNhanVien))) = LEN(tenNhanVien) -- Không có khoảng trắng ở đầu hoặc cuối
);

ALTER TABLE NhanVien
ADD CONSTRAINT chk_TuoiHopLe
CHECK (
    YEAR(GETDATE()) - YEAR(ngaySinh) >= 15
);

ALTER TABLE NhanVien
ADD CONSTRAINT chk_email_Format
CHECK (
    email IS NOT NULL AND -- Không được NULL
    email LIKE '%@gmail.com' AND -- Có đuôi @gmail.com
    email NOT LIKE '%[^A-Za-z0-9@._%+-]%' -- Chỉ chứa chữ cái, số, và các ký tự đặc biệt hợp lệ
);

ALTER TABLE LoaiNhanVien
add CONSTRAINT chk_maLoaiNhanVien_Format
CHECK (
    maLoaiNhanVien LIKE 'MLNV[0-9][0-9]' 
);
ALTER TABLE LoaiNhanVien
ADD CONSTRAINT chk_tenLoaiNhanVien_Format
CHECK (
    tenLoaiNhanVien IS NOT NULL AND -- Không được NULL
    tenLoaiNhanVien NOT LIKE '%[^A-Za-z ]%' AND -- Không chứa ký tự số và ký tự đặc biệt (chỉ cho phép chữ cái và dấu cách)
    tenLoaiNhanVien LIKE '[A-Z]%' AND -- Chữ đầu phải viết hoa
    tenLoaiNhanVien NOT LIKE '%  %' AND -- Không được có hai dấu cách liên tiếp
    LEN(LTRIM(RTRIM(tenLoaiNhanVien))) = LEN(tenLoaiNhanVien) -- Không có khoảng trắng ở đầu hoặc cuối
);
-- Dung trigger rang buoc mat khau


ALTER TABLE Voucher
ADD CONSTRAINT chk_voucher_Format
CHECK (
    maVoucher LIKE 'VC[0-9][0-9][0-9][0-9][0-9][0-9][0-9]-[0-9][0-9]'
);

ALTER TABLE Voucher
ADD CONSTRAINT chk_tenVoucher_Format
CHECK (
    tenVoucher IS NOT NULL AND -- Không được NULL
    tenVoucher NOT LIKE '%[^A-Za-z0-9% ]%' AND -- Cho phép chữ cái, số, dấu phần trăm và dấu cách
    tenVoucher LIKE '[A-Z]%' AND -- Chữ đầu phải viết hoa
    tenVoucher NOT LIKE '%  %' AND -- Không được có hai dấu cách liên tiếp
    LEN(LTRIM(RTRIM(tenVoucher))) = LEN(tenVoucher) -- Không có khoảng trắng ở đầu hoặc cuối
);


ALTER TABLE Voucher
ADD CONSTRAINT chk_giamGia_Format
CHECK (
    giaGiam > 0 AND -- Giảm giá phải lớn hơn 0
    giaGiam IS NOT NULL -- Không được NULL
);



ALTER TABLE Voucher
ADD CONSTRAINT chk_ngayKT_Format
CHECK (

    ngayKetThuc >= ngayBatDau -- Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu
);

ALTER TABLE KhachHang
ADD CONSTRAINT chk_maKhachHang_Format
CHECK (
    maKhachHang LIKE 'KH[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]'
);

ALTER TABLE KhachHang
ADD CONSTRAINT chk_tenKhachHang_Format
CHECK (
    tenKhachHang IS NOT NULL AND -- Không được NULL
    tenKhachHang NOT LIKE '%[^A-Za-z ]%' AND -- Không chứa ký tự số và ký tự đặc biệt (chỉ cho phép chữ cái và dấu cách)
    tenKhachHang LIKE '[A-Z]%' AND -- Chữ đầu phải viết hoa
    tenKhachHang NOT LIKE '%  %' AND -- Không được có hai dấu cách liên tiếp
    LEN(LTRIM(RTRIM(tenKhachHang))) = LEN(tenKhachHang) -- Không có khoảng trắng ở đầu hoặc cuối
);

ALTER TABLE KhachHang
ADD CONSTRAINT chk_cccd_Format
CHECK (
    LEN(cccd) = 10
);



ALTER TABLE KhachHang
ADD CONSTRAINT chk_TuoiHopLe_KhachHang
CHECK (
    YEAR(GETDATE()) - YEAR(ngaySinh) >= 15
);

ALTER TABLE KhachHang
ADD CONSTRAINT chk_email_Format_KhachHang
CHECK (
    email IS NOT NULL AND -- Không được NULL
    email LIKE '%@gmail.com' AND -- Có đuôi @gmail.com
    email NOT LIKE '%[^A-Za-z0-9@._%+-]%' -- Chỉ chứa chữ cái, số, và các ký tự đặc biệt hợp lệ
);


ALTER TABLE HoaDon
ADD CONSTRAINT chk_maHoaDon_Format
CHECK (
    maHoaDon LIKE 'HD[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]' -- 8 ký tự sau 'HD' và 3 ký tự sau '-'
);



ALTER TABLE LoaiPhong
ADD CONSTRAINT chk_maLoaiPhong_Format
CHECK (
    maLoaiPhong LIKE 'LP[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]' AND
    maLoaiPhong IS NOT NULL AND
    maLoaiPhong <> '' -- Không được rỗng
);

ALTER TABLE LoaiPhong
ADD CONSTRAINT chk_tenLoaiPhong_Format
CHECK (
    tenLoaiPhong IS NOT NULL AND -- Không được NULL
    tenLoaiPhong NOT LIKE '%[^A-Za-z ]%' AND -- Không chứa ký tự số và ký tự đặc biệt (chỉ cho phép chữ cái và dấu cách)
    tenLoaiPhong LIKE '[A-Z]%' AND -- Chữ đầu phải viết hoa
    tenLoaiPhong NOT LIKE '%  %' AND -- Không được có hai dấu cách liên tiếp
    LEN(LTRIM(RTRIM(tenLoaiPhong))) = LEN(tenLoaiPhong) -- Không có khoảng trắng ở đầu hoặc cuối
);

ALTER TABLE LoaiPhong
ADD CONSTRAINT chk_soLuongNguoi
CHECK (
    soLuongNguoi IS NOT NULL AND -- Không được NULL
    soLuongNguoi >= 0 AND       -- Phải lớn hơn hoặc bằng 0
    soLuongNguoi <= 10          -- Phải nhỏ hơn hoặc bằng 10
);

ALTER TABLE LoaiPhong
ADD CONSTRAINT chk_giaThuePhong
CHECK (
    giaThuePhong >= 0 AND  -- Phải lớn hơn hoặc bằng 0
    giaThuePhong IS NOT NULL -- Không được NULL
);

ALTER TABLE DichVu
ADD CONSTRAINT chk_maDichVu_Format
CHECK (
    maDichVu LIKE 'DV[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]' AND
    maDichVu IS NOT NULL AND
    maDichVu <> '' -- Không được rỗng
);


ALTER TABLE DichVu
ADD CONSTRAINT chk_tenDichVu_Format
CHECK (
    tenDichVu IS NOT NULL AND -- Không được NULL
    tenDichVu NOT LIKE '%[^A-Za-z ]%' AND -- Không chứa ký tự số và ký tự đặc biệt (chỉ cho phép chữ cái và dấu cách)
    tenDichVu LIKE '[A-Z]%' AND -- Chữ đầu phải viết hoa
    tenDichVu NOT LIKE '%  %' AND -- Không được có hai dấu cách liên tiếp
    LEN(LTRIM(RTRIM(tenDichVu))) = LEN(tenDichVu) -- Không có khoảng trắng ở đầu hoặc cuối
);


ALTER TABLE DichVu
ADD CONSTRAINT chk_giaDichVu
CHECK (
    giaDichVu >= 0 AND  -- Phải lớn hơn hoặc bằng 0
    giaDichVu IS NOT NULL -- Không được NULL
);




ALTER TABLE ChiTietHoaDon
add CONSTRAINT chk_maChiTietHoaDon_Format
CHECK (
    maChiTietHoaDon LIKE 'CTHD[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]' AND
    maChiTietHoaDon IS NOT NULL AND
    maChiTietHoaDon <> '' -- Không được rỗng
);

ALTER TABLE LichSuDatPhong
ADD CONSTRAINT chk_soLuongPhong
CHECK (
    soLuong > 0 AND  -- Phải lớn hơn 0
    soLuong IS NOT NULL -- Không được NULL
);

ALTER TABLE LichSuDatDichVu
ADD CONSTRAINT chk_soLuongDatHang
CHECK (
    soLuongDatHang > 0 AND  -- Phải lớn hơn 0
    soLuongDatHang IS NOT NULL -- Không được NULL
);



ALTER TABLE ChiTietHoaDon
ADD CONSTRAINT chk_giaDatHang
CHECK (
    giaDatHang >= 0 AND        -- Phải lớn hơn hoặc bằng 0
    giaDatHang IS NOT NULL     -- Không được NULL
);

ALTER TABLE Phong
ADD CONSTRAINT chk_maPhong_Format
CHECK (
    maPhong LIKE 'P[0-9][0-9][0-9]' AND          -- Đảm bảo định dạng PXXX
    CAST(SUBSTRING(maPhong, 2, 3) AS INT) BETWEEN 1 AND 999 AND -- Đảm bảo số từ 1 đến 999
    maPhong IS NOT NULL AND                        -- Không được NULL
    maPhong <> ''                                  -- Không được rỗng
);

ALTER TABLE ThietBi
ADD CONSTRAINT chk_maThietBi_Format
CHECK (
    maThietBi LIKE 'TB[0-9][0-9]' AND                     -- Đảm bảo định dạng TBXX
               
    maThietBi IS NOT NULL AND                              -- Không được NULL
    maThietBi <> ''                                        -- Không được rỗng
);

ALTER TABLE KhuVuc
ADD CONSTRAINT chk_maKhuVuc_Format
CHECK (
    maKhuVuc LIKE 'K[A-D]' AND       -- Đảm bảo định dạng KX với X là A, B, C, D
    maKhuVuc IS NOT NULL AND         -- Không được NULL
    maKhuVuc <> ''                   -- Không được rỗng
);

-- Rang buoc Trang Thai Nhan Vien
ALTER TABLE NhanVien
add CONSTRAINT CK_TrangThai_NV CHECK (TrangThai IN ('NGHI_PHEP', 'DANG_LAM_VIEC', 'NGHI_VIEC'))

-- Rang buoc Trang Thai Thiet Bi
ALTER TABLE ThietBi
add CONSTRAINT CK_TrangThai_TB CHECK (TrangThai IN ('DANG_HOAT_DONG', 'KHONG_HOAT_DONG'))

-- Rang buoc Trang Thai Tai Khoan
ALTER TABLE TaiKhoan
add CONSTRAINT CK_TrangThai_TK CHECK (TrangThai IN ('DANG_HOAT_DONG', 'KHONG_HOAT_DONG'))

ALTER TABLE NhanVien
ADD CONSTRAINT CK_GioiTin_NV CHECK (gioiTinh IN ('NAM', 'NU'));

ALTER TABLE KhachHang
ADD CONSTRAINT CK_GioiTinh_KH CHECK (gioiTinh IN ('NAM', 'NU'));
select * from HoaDon