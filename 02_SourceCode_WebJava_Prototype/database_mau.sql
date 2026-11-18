-- ==============================================================================
-- KỊCH BẢN KHỞI TẠO CSDL VÀ NẠP DỮ LIỆU MẪU CHUẨN (DATABASE_MAU.SQL)
-- HỌC PHẦN: PHÂN TÍCH VÀ THIẾT KẾ YÊU CẦU (MÃ MÔN: 502050)
-- ĐỀ TÀI: HỆ THỐNG QUẢN LÝ QUÁN CÀ PHÊ
-- ==============================================================================

CREATE DATABASE IF NOT EXISTS `quanly_cafe` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `quanly_cafe`;
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

-- 1. BẢNG NHÂN VIÊN
DROP TABLE IF EXISTS `cham_cong`;
DROP TABLE IF EXISTS `chi_tiet_hoa_don`;
DROP TABLE IF EXISTS `hoa_don`;
DROP TABLE IF EXISTS `cong_thuc`;
DROP TABLE IF EXISTS `nguyen_lieu`;
DROP TABLE IF EXISTS `mon_nuoc`;
DROP TABLE IF EXISTS `ban`;
DROP TABLE IF EXISTS `ca_lam`;
DROP TABLE IF EXISTS `nhan_vien`;

CREATE TABLE `nhan_vien` (
    `ma_nv` INT AUTO_INCREMENT PRIMARY KEY,
    `ho_ten` VARCHAR(100) NOT NULL,
    `so_dien_thoai` VARCHAR(15),
    `ten_dang_nhap` VARCHAR(50) NOT NULL UNIQUE,
    `mat_khau` VARCHAR(255) NOT NULL,
    `vai_tro` VARCHAR(20) NOT NULL DEFAULT 'STAFF',
    `trang_thai` VARCHAR(20) NOT NULL DEFAULT 'DANG_LAM'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. BẢNG CA LÀM
CREATE TABLE `ca_lam` (
    `ma_ca` INT AUTO_INCREMENT PRIMARY KEY,
    `ten_ca` VARCHAR(50) NOT NULL,
    `gio_bat_dau` TIME NOT NULL,
    `gio_ket_thuc` TIME NOT NULL,
    `he_so_luong` DOUBLE NOT NULL DEFAULT 1.0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. BẢNG CHẤM CÔNG
CREATE TABLE `cham_cong` (
    `ma_cham_cong` INT AUTO_INCREMENT PRIMARY KEY,
    `ma_nv` INT NOT NULL,
    `ma_ca` INT NOT NULL,
    `ngay_lam` DATE NOT NULL,
    `gio_check_in` DATETIME NOT NULL,
    `gio_check_out` DATETIME,
    `trang_thai` VARCHAR(20) NOT NULL,
    `ghi_chu` VARCHAR(255),
    CONSTRAINT `fk_cc_nhanvien` FOREIGN KEY (`ma_nv`) REFERENCES `nhan_vien`(`ma_nv`) ON DELETE CASCADE,
    CONSTRAINT `fk_cc_calam` FOREIGN KEY (`ma_ca`) REFERENCES `ca_lam`(`ma_ca`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. BẢNG BÀN
CREATE TABLE `ban` (
    `ma_ban` INT AUTO_INCREMENT PRIMARY KEY,
    `ten_ban` VARCHAR(50) NOT NULL,
    `vi_tri` VARCHAR(50),
    `trang_thai` VARCHAR(20) NOT NULL DEFAULT 'TRONG'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 5. BẢNG MÓN NƯỚC
CREATE TABLE `mon_nuoc` (
    `ma_mon` INT AUTO_INCREMENT PRIMARY KEY,
    `ten_mon` VARCHAR(100) NOT NULL,
    `danh_muc` VARCHAR(50) NOT NULL,
    `don_gia` DOUBLE NOT NULL,
    `hinh_anh` VARCHAR(255),
    `trang_thai` VARCHAR(20) NOT NULL DEFAULT 'DANG_BAN'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 6. BẢNG HÓA ĐƠN
CREATE TABLE `hoa_don` (
    `ma_hoa_don` INT AUTO_INCREMENT PRIMARY KEY,
    `ma_ban` INT NULL,
    `ma_nv` INT NOT NULL,
    `thoi_gian_tao` DATETIME NOT NULL,
    `thoi_gian_thanh_toan` DATETIME NULL,
    `loai_don` VARCHAR(20) NOT NULL DEFAULT 'TAI_BAN',
    `tong_tien_goc` DOUBLE NOT NULL DEFAULT 0,
    `chiet_khau` DOUBLE NOT NULL DEFAULT 0,
    `tong_tien_thanh_toan` DOUBLE NOT NULL DEFAULT 0,
    `tien_khach_dua` DOUBLE NULL,
    `phuong_thuc_tt` VARCHAR(20) DEFAULT 'TIEN_MAT',
    `ma_tham_chieu` VARCHAR(100) NULL UNIQUE,
    `trang_thai` VARCHAR(20) NOT NULL DEFAULT 'CHO_THANH_TOAN',
    `yeu_cau_tao` VARCHAR(36) NULL UNIQUE,
    `yeu_cau_thanh_toan` VARCHAR(36) NULL UNIQUE,
    `phien_ban` BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT `fk_hd_ban` FOREIGN KEY (`ma_ban`) REFERENCES `ban`(`ma_ban`) ON DELETE SET NULL,
    CONSTRAINT `fk_hd_nhanvien` FOREIGN KEY (`ma_nv`) REFERENCES `nhan_vien`(`ma_nv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 7. BẢNG CHI TIẾT HÓA ĐƠN (COMPOSITION)
CREATE TABLE `chi_tiet_hoa_don` (
    `ma_cthd` INT AUTO_INCREMENT PRIMARY KEY,
    `ma_hoa_don` INT NOT NULL,
    `ma_mon` INT NOT NULL,
    `so_luong` INT NOT NULL DEFAULT 1,
    `don_gia_ban` DOUBLE NOT NULL,
    `thanh_tien` DOUBLE NOT NULL,
    `ghi_chu` VARCHAR(255),
    CONSTRAINT `fk_cthd_hoadon` FOREIGN KEY (`ma_hoa_don`) REFERENCES `hoa_don`(`ma_hoa_don`) ON DELETE CASCADE,
    CONSTRAINT `fk_cthd_monnuoc` FOREIGN KEY (`ma_mon`) REFERENCES `mon_nuoc`(`ma_mon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 8. BẢNG NGUYÊN LIỆU
CREATE TABLE `nguyen_lieu` (
    `ma_nl` INT AUTO_INCREMENT PRIMARY KEY,
    `ten_nl` VARCHAR(100) NOT NULL,
    `don_vi_tinh` VARCHAR(20) NOT NULL,
    `so_luong_ton` DOUBLE NOT NULL DEFAULT 0,
    `muc_ton_toi_thieu` DOUBLE NOT NULL DEFAULT 100,
    `don_gia_nhap` DOUBLE NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 9. BẢNG CÔNG THỨC (ASSOCIATION CLASS)
CREATE TABLE `cong_thuc` (
    `ma_cong_thuc` INT AUTO_INCREMENT PRIMARY KEY,
    `ma_mon` INT NOT NULL,
    `ma_nl` INT NOT NULL,
    `dinh_muc` DOUBLE NOT NULL,
    `ghi_chu` VARCHAR(255),
    CONSTRAINT `fk_ct_monnuoc` FOREIGN KEY (`ma_mon`) REFERENCES `mon_nuoc`(`ma_mon`) ON DELETE CASCADE,
    CONSTRAINT `fk_ct_nguyenlieu` FOREIGN KEY (`ma_nl`) REFERENCES `nguyen_lieu`(`ma_nl`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==============================================================================
-- DỮ LIỆU MẪU KHỞI TẠO (SEED DATA)
-- ==============================================================================

-- 1. Nạp Tài Khoản Nhân Viên (Admin, Manager, Staff)
INSERT INTO `nhan_vien` (`ma_nv`, `ho_ten`, `so_dien_thoai`, `ten_dang_nhap`, `mat_khau`, `vai_tro`, `trang_thai`) VALUES
(1, 'Nguyễn Văn Duy (Nhóm trưởng)', '0901112222', 'admin', '123456', 'ADMIN', 'DANG_LAM'),
(2, 'Trần Văn Quý (POS)', '0903334444', 'manager', '123456', 'MANAGER', 'DANG_LAM'),
(3, 'Lê Tấn Đạt (Nhân sự)', '0905556666', 'staff1', '123456', 'STAFF', 'DANG_LAM'),
(4, 'Phạm Thu Trang (Thu ngân)', '0907778888', 'staff2', '123456', 'STAFF', 'DANG_LAM'),
(5, 'Hoàng Minh Quân (Pha chế)', '0909990000', 'staff3', '123456', 'STAFF', 'DANG_LAM');

-- 2. Nạp Ca Làm Việc
INSERT INTO `ca_lam` (`ma_ca`, `ten_ca`, `gio_bat_dau`, `gio_ket_thuc`, `he_so_luong`) VALUES
(1, 'Ca Sáng', '07:00:00', '12:00:00', 1.0),
(2, 'Ca Chiều', '12:00:00', '17:00:00', 1.0),
(3, 'Ca Tối', '17:00:00', '22:00:00', 1.2);

-- 3. Nạp Danh Sách Bàn (10 Bàn: 9 Trống, 1 Có Khách đang mở đơn tại Bàn 03)
INSERT INTO `ban` (`ma_ban`, `ten_ban`, `vi_tri`, `trang_thai`) VALUES
(1, 'Bàn 01', 'Tầng 1', 'TRONG'),
(2, 'Bàn 02', 'Tầng 1', 'TRONG'),
(3, 'Bàn 03', 'Tầng 1', 'CO_KHACH'),
(4, 'Bàn 04', 'Tầng 1', 'TRONG'),
(5, 'Bàn 05', 'Tầng 1', 'TRONG'),
(6, 'Bàn 06', 'Tầng 2', 'TRONG'),
(7, 'Bàn 07', 'Tầng 2', 'TRONG'),
(8, 'Bàn 08', 'Tầng 2', 'TRONG'),
(9, 'Bàn 09', 'Ngoài trời', 'TRONG'),
(10, 'Bàn 10', 'Ngoài trời', 'TRONG');

-- 4. Nạp Thực Đơn Đồ Uống (20 Món Chuẩn Đẹp)
INSERT INTO `mon_nuoc` (`ma_mon`, `ten_mon`, `danh_muc`, `don_gia`, `trang_thai`) VALUES
(1, 'Cà phê đen đá', 'Cà phê', 25000, 'DANG_BAN'),
(2, 'Cà phê sữa đá', 'Cà phê', 29000, 'DANG_BAN'),
(3, 'Bạc xỉu Sài Gòn', 'Cà phê', 32000, 'DANG_BAN'),
(4, 'Cà phê muối Huế', 'Cà phê', 35000, 'DANG_BAN'),
(5, 'Espresso nguyên chất', 'Cà phê', 30000, 'DANG_BAN'),
(6, 'Trà đào cam sả', 'Trà', 38000, 'DANG_BAN'),
(7, 'Trà vải lài', 'Trà', 38000, 'DANG_BAN'),
(8, 'Trà ô long sen vàng', 'Trà', 42000, 'DANG_BAN'),
(9, 'Trà sữa trân châu hoàng kim', 'Trà', 45000, 'DANG_BAN'),
(10, 'Trà chanh hoa đậu biếc', 'Trà', 28000, 'DANG_BAN'),
(11, 'Matcha đá xay kem cheese', 'Đá xay', 48000, 'DANG_BAN'),
(12, 'Cà phê đá xay caramel', 'Đá xay', 48000, 'DANG_BAN'),
(13, 'Chocolate đá xay hạnh nhân', 'Đá xay', 45000, 'DANG_BAN'),
(14, 'Cookie xay kem sữa', 'Đá xay', 45000, 'DANG_BAN'),
(15, 'Sữa chua việt quất đá xay', 'Đá xay', 42000, 'DANG_BAN'),
(16, 'Nước ép cam nguyên chất', 'Nước ép', 35000, 'DANG_BAN'),
(17, 'Nước ép dưa hấu', 'Nước ép', 30000, 'DANG_BAN'),
(18, 'Nước ép táo cần tây', 'Nước ép', 40000, 'DANG_BAN'),
(19, 'Sinh tố xoài cát', 'Nước ép', 38000, 'DANG_BAN'),
(20, 'Sinh tố bơ sáp Đắk Lắk', 'Nước ép', 45000, 'DANG_BAN');

-- 5. Nạp Kho Nguyên Liệu (10 Nguyên Liệu)
-- LƯU Ý DEMO: Đặt sẵn Sữa đặc (ma_nl = 2) ở mức 120ml (Mức tối thiểu 100ml).
-- Khi Quý order 1 Cà phê sữa (dùng 30ml), tồn kho giảm còn 90ml <= 100ml -> BẬT CẢNH BÁO ĐỎ!
INSERT INTO `nguyen_lieu` (`ma_nl`, `ten_nl`, `don_vi_tinh`, `so_luong_ton`, `muc_ton_toi_thieu`, `don_gia_nhap`) VALUES
(1, 'Cốt cà phê Robusta', 'ml', 2500, 300, 150),
(2, 'Sữa đặc Ngôi Sao', 'ml', 120, 100, 80),          -- SÁT NGƯỠNG ĐỂ DEMO CẢNH BÁO ĐỎ
(3, 'Sữa tươi thanh trùng', 'ml', 3000, 500, 35),
(4, 'Trà đen Phúc Long', 'g', 1500, 200, 200),
(5, 'Siro đào Teisseire', 'ml', 1200, 200, 180),
(6, 'Đào ngâm miếng Kronos', 'g', 2000, 300, 120),
(7, 'Đường nước mía', 'ml', 4000, 500, 25),
(8, 'Bột Matcha Nhật Bản', 'g', 800, 150, 450),
(9, 'Bột Chocolate nguyên chất', 'g', 1000, 200, 250),
(10, 'Kem béo thực vật Rich', 'ml', 1800, 300, 90);

-- 6. Nạp Bảng Định Mức Công Thức (Recipe Mapping)
INSERT INTO `cong_thuc` (`ma_cong_thuc`, `ma_mon`, `ma_nl`, `dinh_muc`, `ghi_chu`) VALUES
-- Cà phê đen đá (ma_mon = 1): 30ml cốt cà phê + 15ml đường nước
(1, 1, 1, 30, 'Cốt cà phê đậm đặc'),
(2, 1, 7, 15, 'Đường nước ngọt vừa'),

-- Cà phê sữa đá (ma_mon = 2): 25ml cốt cà phê + 30ml sữa đặc
(3, 2, 1, 25, 'Cốt cà phê'),
(4, 2, 2, 30, 'Sữa đặc béo ngọt'),

-- Bạc xỉu (ma_mon = 3): 15ml cốt cà phê + 40ml sữa đặc + 60ml sữa tươi
(5, 3, 1, 15, 'Cốt cà phê nhẹ'),
(6, 3, 2, 40, 'Sữa đặc'),
(7, 3, 3, 60, 'Sữa tươi thanh trùng'),

-- Trà đào cam sả (ma_mon = 6): 15g trà đen + 25ml siro đào + 40g đào miếng
(8, 6, 4, 15, 'Cốt trà đen ủ lạnh'),
(9, 6, 5, 25, 'Siro đào thơm'),
(10, 6, 6, 40, 'Đào ngâm giòn'),

-- Matcha đá xay (ma_mon = 11): 15g bột matcha + 80ml sữa tươi + 20ml sữa đặc
(11, 11, 8, 15, 'Bột matcha rây mịn'),
(12, 11, 3, 80, 'Sữa tươi xay cùng đá'),
(13, 11, 2, 20, 'Sữa đặc tạo ngọt'),

-- Định mức cho các món còn lại trong thực đơn (đảm bảo 20/20 món đều thanh toán & trừ kho hợp lệ)
(14, 4, 1, 25, 'Cốt cà phê'),
(15, 4, 10, 20, 'Kem béo muối'),
(16, 5, 1, 35, 'Cốt Espresso nguyên chất'),
(17, 7, 4, 15, 'Cốt trà lài'),
(18, 7, 7, 20, 'Đường nước'),
(19, 8, 4, 15, 'Cốt trà ô long'),
(20, 8, 10, 25, 'Kem sữa sen vàng'),
(21, 9, 4, 20, 'Cốt trà đen đậm'),
(22, 9, 3, 60, 'Sữa tươi'),
(23, 10, 4, 12, 'Cốt trà chanh'),
(24, 10, 7, 25, 'Đường nước mía'),
(25, 12, 1, 30, 'Cốt cà phê xay'),
(26, 12, 3, 60, 'Sữa tươi'),
(27, 13, 9, 25, 'Bột Chocolate'),
(28, 13, 3, 80, 'Sữa tươi'),
(29, 14, 9, 15, 'Bột cacao cookie'),
(30, 14, 10, 30, 'Kem béo'),
(31, 15, 3, 80, 'Sữa tươi thanh trùng'),
(32, 15, 7, 20, 'Đường nước'),
(33, 16, 7, 20, 'Đường nước pha nước ép cam'),
(34, 17, 7, 15, 'Đường nước pha dưa hấu'),
(35, 18, 7, 15, 'Đường nước pha táo cần tây'),
(36, 19, 3, 50, 'Sữa tươi xay sinh tố xoài'),
(37, 20, 3, 60, 'Sữa tươi xay sinh tố bơ');

-- 7. Nạp Chấm Công Mẫu Hôm Nay
INSERT INTO `cham_cong` (`ma_cham_cong`, `ma_nv`, `ma_ca`, `ngay_lam`, `gio_check_in`, `gio_check_out`, `trang_thai`, `ghi_chu`) VALUES
(1, 3, 1, CURRENT_DATE(), CONCAT(CURRENT_DATE(), ' 06:55:00'), NULL, 'DUNG_GIO', 'Check-in đúng giờ'),
(2, 4, 1, CURRENT_DATE(), CONCAT(CURRENT_DATE(), ' 07:22:00'), NULL, 'DI_TRE', 'Trễ 22 phút');

-- 8. Nạp Hóa Đơn Mẫu (2 Đơn Đã Thanh Toán + 1 Đơn Đang Mở tại Bàn 03)
INSERT INTO `hoa_don` (`ma_hoa_don`, `ma_ban`, `ma_nv`, `thoi_gian_tao`, `thoi_gian_thanh_toan`, `loai_don`, `tong_tien_goc`, `chiet_khau`, `tong_tien_thanh_toan`, `tien_khach_dua`, `phuong_thuc_tt`, `ma_tham_chieu`, `trang_thai`, `phien_ban`) VALUES
(1, 1, 1, NOW(), NOW(), 'TAI_BAN', 54000, 0, 54000, 60000, 'TIEN_MAT', NULL, 'DA_THANH_TOAN', 0),
(2, NULL, 2, NOW(), NOW(), 'MANG_DI', 76000, 5000, 71000, 71000, 'CHUYEN_KHOAN', 'VCB-DEMO-001', 'DA_THANH_TOAN', 0),
(3, 3, 3, NOW(), NULL, 'TAI_BAN', 63000, 0, 63000, NULL, 'TIEN_MAT', NULL, 'CHO_THANH_TOAN', 0);

INSERT INTO `chi_tiet_hoa_don` (`ma_cthd`, `ma_hoa_don`, `ma_mon`, `so_luong`, `don_gia_ban`, `thanh_tien`, `ghi_chu`) VALUES
(1, 1, 1, 1, 25000, 25000, 'Ít đường'),
(2, 1, 2, 1, 29000, 29000, 'Bình thường'),
(3, 2, 6, 2, 38000, 76000, 'Nhiều đá mang đi'),
(4, 3, 1, 1, 25000, 25000, 'Không đá'),
(5, 3, 6, 1, 38000, 38000, 'Ít ngọt');
