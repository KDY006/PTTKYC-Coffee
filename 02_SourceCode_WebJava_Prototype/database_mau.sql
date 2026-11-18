-- ==============================================================================
-- KỊCH BẢN KHỞI TẠO CSDL VÀ NẠP DỮ LIỆU MẪU CHUẨN (DATABASE_MAU.SQL)
-- HỌC PHẦN: PHÂN TÍCH VÀ THIẾT KẾ YÊU CẦU (MÃ MÔN: 502050)
-- ĐỀ TÀI: HỆ THỐNG QUẢN LÝ QUÁN CÀ PHÊ
-- ==============================================================================

CREATE DATABASE IF NOT EXISTS `quanly_cafe` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `quanly_cafe`;

-- 1. BẢNG NHÂN VIÊN
DROP TABLE IF EXISTS `phan_ca`;
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
) ENGINE=InnoDB;

-- 2. BẢNG CA LÀM
CREATE TABLE `ca_lam` (
    `ma_ca` INT AUTO_INCREMENT PRIMARY KEY,
    `ten_ca` VARCHAR(50) NOT NULL,
    `gio_bat_dau` TIME NOT NULL,
    `gio_ket_thuc` TIME NOT NULL,
    `he_so_luong` DOUBLE NOT NULL DEFAULT 1.0
) ENGINE=InnoDB;

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
) ENGINE=InnoDB;

-- 3.1 BẢNG PHÂN CA THEO NGÀY
CREATE TABLE `phan_ca` (
    `ma_phan_ca` INT AUTO_INCREMENT PRIMARY KEY,
    `ma_nv` INT NOT NULL,
    `ma_ca` INT NOT NULL,
    `ngay_lam` DATE NOT NULL,
    `ghi_chu` VARCHAR(255),
    CONSTRAINT `uk_phan_ca_nhan_vien_ngay_ca` UNIQUE (`ma_nv`, `ngay_lam`, `ma_ca`),
    CONSTRAINT `fk_pc_nhanvien` FOREIGN KEY (`ma_nv`) REFERENCES `nhan_vien`(`ma_nv`) ON DELETE CASCADE,
    CONSTRAINT `fk_pc_calam` FOREIGN KEY (`ma_ca`) REFERENCES `ca_lam`(`ma_ca`) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 4. BẢNG BÀN
CREATE TABLE `ban` (
    `ma_ban` INT AUTO_INCREMENT PRIMARY KEY,
    `ten_ban` VARCHAR(50) NOT NULL,
    `vi_tri` VARCHAR(50),
    `trang_thai` VARCHAR(20) NOT NULL DEFAULT 'TRONG'
) ENGINE=InnoDB;

-- 5. BẢNG MÓN NƯỚC
CREATE TABLE `mon_nuoc` (
    `ma_mon` INT AUTO_INCREMENT PRIMARY KEY,
    `ten_mon` VARCHAR(100) NOT NULL,
    `danh_muc` VARCHAR(50) NOT NULL,
    `don_gia` DOUBLE NOT NULL,
    `hinh_anh` VARCHAR(255),
    `trang_thai` VARCHAR(20) NOT NULL DEFAULT 'DANG_BAN'
) ENGINE=InnoDB;

-- 6. BẢNG HÓA ĐƠN
CREATE TABLE `hoa_don` (
    `ma_hoa_don` INT AUTO_INCREMENT PRIMARY KEY,
    `ma_ban` INT NULL,
    `ma_nv` INT NOT NULL,
    `thoi_gian_tao` DATETIME NOT NULL,
    `loai_don` VARCHAR(20) NOT NULL DEFAULT 'TAI_BAN',
    `tong_tien_goc` DOUBLE NOT NULL DEFAULT 0,
    `chiet_khau` DOUBLE NOT NULL DEFAULT 0,
    `tong_tien_thanh_toan` DOUBLE NOT NULL DEFAULT 0,
    `phuong_thuc_tt` VARCHAR(20) DEFAULT 'TIEN_MAT',
    `trang_thai` VARCHAR(20) NOT NULL DEFAULT 'CHO_THANH_TOAN',
    CONSTRAINT `fk_hd_ban` FOREIGN KEY (`ma_ban`) REFERENCES `ban`(`ma_ban`) ON DELETE SET NULL,
    CONSTRAINT `fk_hd_nhanvien` FOREIGN KEY (`ma_nv`) REFERENCES `nhan_vien`(`ma_nv`)
) ENGINE=InnoDB;

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
) ENGINE=InnoDB;

-- 8. BẢNG NGUYÊN LIỆU
CREATE TABLE `nguyen_lieu` (
    `ma_nl` INT AUTO_INCREMENT PRIMARY KEY,
    `ten_nl` VARCHAR(100) NOT NULL,
    `don_vi_tinh` VARCHAR(20) NOT NULL,
    `so_luong_ton` DOUBLE NOT NULL DEFAULT 0,
    `muc_ton_toi_thieu` DOUBLE NOT NULL DEFAULT 100,
    `don_gia_nhap` DOUBLE NOT NULL DEFAULT 0
) ENGINE=InnoDB;

-- 9. BẢNG CÔNG THỨC (ASSOCIATION CLASS)
CREATE TABLE `cong_thuc` (
    `ma_cong_thuc` INT AUTO_INCREMENT PRIMARY KEY,
    `ma_mon` INT NOT NULL,
    `ma_nl` INT NOT NULL,
    `dinh_muc` DOUBLE NOT NULL,
    `ghi_chu` VARCHAR(255),
    CONSTRAINT `fk_ct_monnuoc` FOREIGN KEY (`ma_mon`) REFERENCES `mon_nuoc`(`ma_mon`) ON DELETE CASCADE,
    CONSTRAINT `fk_ct_nguyenlieu` FOREIGN KEY (`ma_nl`) REFERENCES `nguyen_lieu`(`ma_nl`) ON DELETE CASCADE
) ENGINE=InnoDB;

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

-- 2.1 Lịch phân ca mẫu trong tuần hiện tại để thao tác check-in/check-out có dữ liệu hợp lệ
INSERT INTO `phan_ca` (`ma_nv`, `ma_ca`, `ngay_lam`, `ghi_chu`) VALUES
(3, 1, CURRENT_DATE(), 'Thu ngân ca sáng'),
(4, 2, CURRENT_DATE(), 'Thu ngân ca chiều'),
(5, 3, CURRENT_DATE(), 'Pha chế ca tối'),
(3, 2, DATE_ADD(CURRENT_DATE(), INTERVAL 1 DAY), 'Thu ngân'),
(4, 3, DATE_ADD(CURRENT_DATE(), INTERVAL 1 DAY), 'Thu ngân'),
(5, 1, DATE_ADD(CURRENT_DATE(), INTERVAL 1 DAY), 'Pha chế');

-- 3. Nạp Danh Sách Bàn (10 Bàn: 8 Trống, 2 Có Khách)
INSERT INTO `ban` (`ma_ban`, `ten_ban`, `vi_tri`, `trang_thai`) VALUES
(1, 'Bàn 01', 'Tầng 1', 'TRONG'),
(2, 'Bàn 02', 'Tầng 1', 'TRONG'),
(3, 'Bàn 03', 'Tầng 1', 'CO_KHACH'),
(4, 'Bàn 04', 'Tầng 1', 'TRONG'),
(5, 'Bàn 05', 'Tầng 1', 'TRONG'),
(6, 'Bàn 06', 'Tầng 2', 'TRONG'),
(7, 'Bàn 07', 'Tầng 2', 'CO_KHACH'),
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

-- 6. Nạp Bảng Định Mức Công Thức cho đủ 20 món (Recipe Mapping)
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

-- Cà phê muối Huế (ma_mon = 4): cốt cà phê + sữa đặc + kem béo
(14, 4, 1, 30, 'Cốt cà phê đậm'),
(15, 4, 2, 15, 'Sữa đặc tạo vị ngọt'),
(16, 4, 10, 30, 'Kem béo mô phỏng lớp kem muối'),

-- Espresso nguyên chất (ma_mon = 5): cốt cà phê cô đặc
(17, 5, 1, 40, 'Cốt cà phê espresso'),

-- Trà vải lài (ma_mon = 7): nền trà, siro và đường
(18, 7, 4, 15, 'Nền trà lài mẫu'),
(19, 7, 5, 20, 'Siro tạo hương vải mẫu'),
(20, 7, 7, 15, 'Đường nước'),

-- Trà ô long sen vàng (ma_mon = 8): trà, sữa tươi và sữa đặc
(21, 8, 4, 20, 'Nền trà ô long mẫu'),
(22, 8, 3, 60, 'Sữa tươi'),
(23, 8, 2, 20, 'Sữa đặc'),

-- Trà sữa trân châu hoàng kim (ma_mon = 9): trà, sữa và đường
(24, 9, 4, 20, 'Nền trà đen'),
(25, 9, 3, 100, 'Sữa tươi'),
(26, 9, 2, 20, 'Sữa đặc'),
(27, 9, 7, 15, 'Đường nước, định mức mẫu cho topping'),

-- Trà chanh hoa đậu biếc (ma_mon = 10): nền trà và đường
(28, 10, 4, 15, 'Nền trà mẫu'),
(29, 10, 7, 20, 'Đường nước'),

-- Cà phê đá xay caramel (ma_mon = 12): cà phê, sữa, kem và đường
(30, 12, 1, 30, 'Cốt cà phê'),
(31, 12, 3, 100, 'Sữa tươi'),
(32, 12, 2, 20, 'Sữa đặc'),
(33, 12, 10, 30, 'Kem béo'),
(34, 12, 7, 20, 'Nền syrup caramel mẫu'),

-- Chocolate đá xay hạnh nhân (ma_mon = 13): bột chocolate, sữa và kem
(35, 13, 9, 25, 'Bột chocolate'),
(36, 13, 3, 120, 'Sữa tươi'),
(37, 13, 2, 15, 'Sữa đặc'),
(38, 13, 10, 30, 'Kem béo, mô phỏng topping hạnh nhân'),

-- Cookie xay kem sữa (ma_mon = 14): nền sữa, kem và đường
(39, 14, 3, 130, 'Sữa tươi'),
(40, 14, 2, 20, 'Sữa đặc'),
(41, 14, 10, 40, 'Kem béo'),
(42, 14, 7, 20, 'Nền cookie mẫu'),

-- Sữa chua việt quất đá xay (ma_mon = 15): nền sữa, kem và sữa đặc
(43, 15, 3, 150, 'Sữa tươi, nền sữa chua mẫu'),
(44, 15, 2, 20, 'Sữa đặc'),
(45, 15, 10, 30, 'Kem béo, mô phỏng topping việt quất'),

-- Nước ép cam nguyên chất (ma_mon = 16): định mức nền nước ép mẫu
(46, 16, 7, 15, 'Đường nước'),
(47, 16, 5, 10, 'Siro trái cây mẫu'),

-- Nước ép dưa hấu (ma_mon = 17): định mức nền nước ép mẫu
(48, 17, 7, 20, 'Đường nước, nền dưa hấu mẫu'),

-- Nước ép táo cần tây (ma_mon = 18): định mức nền nước ép mẫu
(49, 18, 7, 10, 'Đường nước, nền táo cần tây mẫu'),

-- Sinh tố xoài cát (ma_mon = 19): sữa, sữa đặc và đường
(50, 19, 3, 80, 'Sữa tươi, nền xoài mẫu'),
(51, 19, 2, 15, 'Sữa đặc'),
(52, 19, 7, 20, 'Đường nước'),

-- Sinh tố bơ sáp Đắk Lắk (ma_mon = 20): sữa, sữa đặc, kem và đường
(53, 20, 3, 100, 'Sữa tươi, nền bơ mẫu'),
(54, 20, 2, 25, 'Sữa đặc'),
(55, 20, 10, 30, 'Kem béo'),
(56, 20, 7, 15, 'Đường nước');

-- 7. Nạp Chấm Công Mẫu Hôm Nay
INSERT INTO `cham_cong` (`ma_cham_cong`, `ma_nv`, `ma_ca`, `ngay_lam`, `gio_check_in`, `gio_check_out`, `trang_thai`, `ghi_chu`) VALUES
(1, 3, 1, CURRENT_DATE(), CONCAT(CURRENT_DATE(), ' 06:55:00'), NULL, 'DUNG_GIO', 'Check-in đúng giờ'),
(2, 4, 1, CURRENT_DATE(), CONCAT(CURRENT_DATE(), ' 07:22:00'), NULL, 'DI_TRE', 'Trễ 22 phút');

-- 8. Nạp Hóa Đơn Mẫu Đã Thanh Toán (Để có sẵn doanh thu trên KPI Dashboard)
INSERT INTO `hoa_don` (`ma_hoa_don`, `ma_ban`, `ma_nv`, `thoi_gian_tao`, `loai_don`, `tong_tien_goc`, `chiet_khau`, `tong_tien_thanh_toan`, `phuong_thuc_tt`, `trang_thai`) VALUES
(1, 1, 1, NOW(), 'TAI_BAN', 54000, 0, 54000, 'TIEN_MAT', 'DA_THANH_TOAN'),
(2, NULL, 2, NOW(), 'MANG_DI', 76000, 5000, 71000, 'CHUYEN_KHOAN', 'DA_THANH_TOAN');

INSERT INTO `chi_tiet_hoa_don` (`ma_cthd`, `ma_hoa_don`, `ma_mon`, `so_luong`, `don_gia_ban`, `thanh_tien`, `ghi_chu`) VALUES
(1, 1, 1, 1, 25000, 25000, 'Ít đường'),
(2, 1, 2, 1, 29000, 29000, 'Bình thường'),
(3, 2, 6, 2, 38000, 76000, 'Nhiều đá mang đi');
