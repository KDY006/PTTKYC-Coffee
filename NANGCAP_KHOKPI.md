# ĐẶC TẢ NÂNG CẤP PHÂN HỆ KHO, BẢNG BOM & KPI DASHBOARD (PHIÊN BẢN 2.0)

### TRUY VẾT BIẾN ĐỘNG KHO (AUDIT TRAIL), BẢNG ĐỊNH MỨC TRỰC TIẾP, KIỂM KÊ HAO HỤT & BÁO CÁO TÀI CHÍNH ĐA KỲ

* **Thương hiệu chuẩn:** **Sangrimar Cafe & Lounge**
* **Học phần:** **Phân tích và Thiết kế Yêu cầu** (Mã môn: `502050`)
* **Tài liệu gốc đồng bộ:** `NANG_CAP_TONGTHE.md` & `database_mau.sql` (v2.0 – 11 bảng, 3 tài khoản, 12 món – 13 nguyên liệu thật).
* **Phạm vi nâng cấp:**
  1. Bảng truy vết biến động kho **`lich_su_kho`** cho 3 nghiệp vụ: `NHAP_KHO`, `XUAT_BAN_HANG` và `KIEM_KE_HAO_HUT`.
  2. Cập nhật giá vốn `don_gia_nhap` theo phương pháp **Bình quân gia quyền (Weighted Average Cost)** khi nhập lô hàng mới.
  3. Hiển thị trực tiếp **Bảng Định mức Công thức (BOM) cho toàn bộ 12 món** ngay trên trang `kho.html` (kèm giá vốn/ly, biên lợi nhuận gộp và số ly khả dụng).
  4. Chuẩn hóa **KPI Dashboard đa kỳ** (`HOM_NAY`, `BAY_NGAY`, `THANG_NAY`, `TAT_CA`), tách bạch đối soát Tiền mặt vs Chuyển khoản, tính Lợi nhuận gộp ước tính và **Top 5 món bán chạy**.

---

## 1. THIẾT KẾ BẢNG TRUY VẾT BIẾN ĐỘNG KHO (`lich_su_kho` – BẢNG THỨ 11)

Cấu trúc bảng `lich_su_kho` và dữ liệu mẫu khớp toán học $100\%$ đã được hợp nhất trong tệp **`database_mau.sql` (v2.0)**.

### 1.1. Từ điển dữ liệu Bảng `lich_su_kho`

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc / Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- |
| `ma_gd` | `INT` | **PK** | No | `AUTO_INCREMENT` – Mã giao dịch kho duy nhất |
| `ma_nl` | `INT` | **FK** | No | Tham chiếu `nguyen_lieu(ma_nl)` (`ON DELETE CASCADE`) |
| `ma_nv` | `INT` | **FK** | No | Nhân sự thực hiện: `QUAN_LY` (nhập/kiểm kê) hoặc `THU_NGAN`/`QUAN_LY` (chốt đơn xuất bán) |
| `ma_hoa_don` | `INT` | **FK** | Yes | Chỉ có giá trị khi `loai_gd = 'XUAT_BAN_HANG'` (`ON DELETE SET NULL`) |
| `loai_gd` | `VARCHAR(30)` | | No | `CHECK`: `'NHAP_KHO'`, `'XUAT_BAN_HANG'`, `'KIEM_KE_HAO_HUT'` |
| `so_luong_thay_doi` | `DOUBLE` | | No | Dương ($> 0$) khi nhập/dư; Âm ($< 0$) khi xuất bán/hao hụt |
| `ton_truoc` | `DOUBLE` | | No | Tồn kho ngay trước giao dịch ($\ge 0$) |
| `ton_sau` | `DOUBLE` | | No | Tồn kho ngay sau giao dịch ($\text{ton\_sau} = \text{ton\_truoc} + \text{so\_luong\_thay\_doi} \ge 0$) |
| `don_gia` | `DOUBLE` | | No | Đơn giá lô nhập hoặc đơn giá vốn bình quân tại thời điểm phát sinh ($\ge 0$) |
| `thanh_tien` | `DOUBLE` | | No | $= |\text{so\_luong\_thay\_doi}| \times \text{don\_gia}$ (VNĐ) |
| `nha_cung_cap` | `VARCHAR(120)` | | Yes | Tên nhà cung cấp (dùng cho `NHAP_KHO`) |
| `thoi_gian` | `DATETIME` | | No | `DEFAULT CURRENT_TIMESTAMP` |
| `ghi_chu` | `VARCHAR(255)` | | Yes | Bắt buộc nhập lý do khi `KIEM_KE_HAO_HUT` |

---

## 2. CHI TIẾT CÁC LUỒNG NGHIỆP VỤ NÂNG CẤP

### 2.1. Luồng 1: Nhập kho & Cập nhật Giá vốn Bình quân Gia quyền (`KhoService.nhapKho`)
* **Phân quyền:** Chỉ `QUAN_LY` (`access.requireQuanLy(session)`).
* **Đầu vào (`NhapKhoDTO`):** `maNL` (bắt buộc), `soLuongNhap` ($> 0$), `donGiaNhapMoi` (tùy chọn, $\ge 0$), `nhaCungCap`, `ghiChu`.
* **Xử lý trong `@Transactional(rollbackFor = Exception.class)`:**
  1. Khóa dòng nguyên liệu bằng `khoaNguyenLieu(maNL)` (`PESSIMISTIC_WRITE`).
  2. Lưu `tonTruoc`, tính `tonSau = tonTruoc + soLuongNhap`.
  3. Nếu có `donGiaNhapMoi > 0`, cập nhật đơn giá vốn bình quân gia quyền (làm tròn 2 chữ số thập phân bằng `BigDecimal`):
     $$\text{donGiaMoi} = \frac{(\text{tonTruoc} \times \text{donGiaCu}) + (\text{soLuongNhap} \times \text{donGiaNhapMoi})}{\text{tonTruoc} + \text{soLuongNhap}}$$
  4. Lưu bảng `nguyen_lieu` và chèn dòng `NHAP_KHO` vào `lich_su_kho` gắn `ma_nv = maNhanVienDangNhap`.

### 2.2. Luồng 2: Kiểm kê Cuối ngày & Xuất hủy Hao hụt (`KhoService.kiemKeKho`)
* **Phân quyền:** Chỉ `QUAN_LY`.
* **Đầu vào (`KiemKeKhoDTO`):** `maNL` (bắt buộc), `soLuongThucTe` ($\ge 0$), `ghiChu` (bắt buộc không rỗng).
* **Xử lý trong `@Transactional`:**
  1. Khóa dòng nguyên liệu `khoaNguyenLieu(maNL)`.
  2. Tính chênh lệch $\Delta = \text{soLuongThucTe} - \text{tonTruoc}$. Nếu $\Delta = 0$ $\rightarrow$ trả `HTTP 400 Bad Request` (*"Tồn thực tế khớp hệ thống, không cần điều chỉnh"*).
  3. Cập nhật `soLuongTon = soLuongThucTe`, ghi dòng `KIEM_KE_HAO_HUT` vào `lich_su_kho` với `soLuongThayDoi = ` $\Delta$ và `thanhTien = ` $|\Delta| \times \text{donGiaNhap}$.
  4. Nếu `soLuongThucTe <= mucTonToiThieu` $\rightarrow$ bật nhãn đỏ `SẮP HẾT HÀNG`.

### 2.3. Luồng 3: Ghi Nhật ký tự động trong `truKhoTuDong(HoaDon, NhanVien)`
* Giữ nguyên cơ chế gom nhu cầu bằng `TreeMap<Integer, BigDecimal>` và khóa `SELECT ... FOR UPDATE` theo `ma_nl` tăng dần để triệt tiêu Deadlock.
* Ngay sau khi trừ lùi tồn kho mỗi nguyên liệu, tạo bản ghi `LichSuKho` (`loaiGD = 'XUAT_BAN_HANG'`, gắn `hoaDon` và `nhanVienChotThanhToan`, `soLuongThayDoi = -tongCanTru`, `thanhTien = tongCanTru * donGiaNhap`).
* Chạy chung trong `@Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)`: thiếu bất kỳ nguyên liệu nào sẽ Rollback sạch $100\%$ cả tồn kho lẫn `lich_su_kho`.

### 2.4. Luồng 4: Tính toán KPI Dashboard Đa kỳ & Top 5 Món Bán Chạy
* **Kỳ lọc `phamVi`:** `HOM_NAY` *(mặc định)* | `BAY_NGAY` | `THANG_NAY` | `TAT_CA`, lọc theo `COALESCE(hd.thoi_gian_thanh_toan, hd.thoi_gian_tao)` với các đơn `trang_thai = 'DA_THANH_TOAN'`.
* **Công thức các chỉ số:**
  * $\text{tongDoanhThu} = \sum \text{hd.tong\_tien\_thanh\_toan}$, tách riêng `doanhThuTienMat` / `doanhThuChuyenKhoan` và `tongChietKhau`.
  * $\text{giaVonBanHang} = \sum \text{lsk.thanh\_tien}$ (`loai_gd = 'XUAT_BAN_HANG'`).
  * $\text{giaTriHaoHut} = \sum \text{lsk.thanh\_tien}$ (`loai_gd = 'KIEM_KE_HAO_HUT'` và `so_luong_thay_doi < 0`).
  * $\text{loiNhuanGop} = \text{tongDoanhThu} - \text{giaVonBanHang} - \text{giaTriHaoHut}$.
  * $\text{tongGiaTriTonKho} = \sum (\text{nl.so\_luong\_ton} \times \text{nl.don\_gia\_nhap})$ trên toàn bộ $13$ nguyên liệu.
  * `topMonBanChay`: Top 5 món theo $\sum \text{chi\_tiet\_hoa\_don.so\_luong}$ giảm dần trong kỳ.

---

## 3. ĐẶC TẢ REST API PHÂN HỆ KHO, BOM & KPI (CHỈ DÀNH CHO `QUAN_LY`)

Mọi endpoint dưới đây gọi `access.requireQuanLy(session)` (`401` nếu chưa đăng nhập, `403` nếu là `PHUC_VU` hoặc `THU_NGAN`):

| Method & Endpoint | Chức năng & Dữ liệu chính |
| :--- | :--- |
| `GET /api/kho/nguyen-lieu` | Lấy danh sách 13 nguyên liệu kèm cờ `sapHet` và `giaTriTon`. |
| `GET /api/kho/sap-het` | Lấy danh sách nguyên liệu có `soLuongTon <= mucTonToiThieu` (chỉ được Topbar gọi khi `vaiTro === 'QUAN_LY'`). |
| `GET /api/kho/bang-cong-thuc` | **API Mới:** Trả về toàn bộ 12 món kèm chi tiết danh sách nguyên liệu định lượng, `giaVonLy`, `bienLoiNhuan` và `soLyCoTheLam` để hiển thị trực tiếp tại Tab 2 trên `kho.html`. |
| `POST /api/kho/cong-thuc` | Thêm hoặc cập nhật cặp `(maMon, maNL)` trong bảng `cong_thuc` (tôn trọng ràng buộc `UNIQUE(ma_mon, ma_nl)`). |
| `POST /api/kho/nhap-kho` | Nhập kho theo `NhapKhoDTO`, cập nhật giá vốn bình quân và sinh dòng `NHAP_KHO` trong `lich_su_kho`. |
| `POST /api/kho/kiem-ke` | Kiểm kê điều chỉnh theo `KiemKeKhoDTO`, bắt buộc có `ghiChu`, sinh dòng `KIEM_KE_HAO_HUT`. |
| `GET /api/kho/lich-su?loaiGD=&maNL=&phamVi=` | Tra cứu Nhật ký biến động kho sắp xếp mới nhất lên đầu. |
| `GET /api/kho/dashboard?phamVi=HOM_NAY` | Trả về `DashboardKpiDTO` tổng hợp tài chính, tồn kho và Top 5 món bán chạy. |

### Ví dụ JSON `GET /api/kho/dashboard?phamVi=HOM_NAY` (Khớp 100% số liệu mẫu `database_mau.sql` v2.0)

```json
{
  "phamVi": "HOM_NAY",
  "tongDoanhThu": 125000.0,
  "tongDonHang": 2,
  "tongChietKhau": 5000.0,
  "doanhThuTienMat": 54000.0,
  "soDonTienMat": 1,
  "doanhThuChuyenKhoan": 71000.0,
  "soDonChuyenKhoan": 1,
  "chiPhiNhapKho": 1381925.0,
  "giaVonBanHang": 35625.0,
  "giaTriHaoHut": 700.0,
  "loiNhuanGop": 88675.0,
  "tongGiaTriTonKho": 2613600.0,
  "tongNguyenLieu": 13,
  "soNguyenLieuSapHet": 0,
  "danhSachSapHet": [],
  "topMonBanChay": [
    {
      "maMon": 5,
      "tenMon": "Trà đào cam sả",
      "danhMuc": "Trà",
      "tongSoLuong": 2,
      "tongDoanhThu": 76000.0
    },
    {
      "maMon": 2,
      "tenMon": "Cà phê sữa đá",
      "danhMuc": "Cà phê",
      "tongSoLuong": 1,
      "tongDoanhThu": 29000.0
    },
    {
      "maMon": 1,
      "tenMon": "Cà phê đen đá",
      "danhMuc": "Cà phê",
      "tongSoLuong": 1,
      "tongDoanhThu": 25000.0
    }
  ]
}
```

---

## 4. NÂNG CẤP GIAO DIỆN `kho.html` (TÔNG SÁNG IN ẤN & 3 TAB CON)

1. **Bộ lọc kỳ KPI:** `Hôm nay` *(mặc định)* | `7 ngày` | `Tháng này` | `Tất cả`.
2. **6 Thẻ KPI (2 hàng $\times$ 3 cột nền trắng viền nét mảnh):**
   * *Hàng 1:* Doanh thu thuần (kèm số đơn & chiết khấu) · Lợi nhuận gộp ước tính (kèm giá vốn & hao hụt) · Đối soát Tiền mặt vs Chuyển khoản.
   * *Hàng 2:* Tổng giá trị tồn kho (`2.613.600 đ`) · Chi phí nhập kho trong kỳ · Cảnh báo nguyên liệu sắp hết (`0 / 13` hoặc đỏ nổi bật khi $> 0$).
3. **Khu vực Tab quản trị 3 tab (bên cạnh thẻ Top 5 Món Bán Chạy):**
   * **Tab 1 – Tồn kho Nguyên vật liệu:** Bảng 13 nguyên liệu, tồn hiện tại, ngưỡng tối thiểu, giá vốn bình quân, tổng giá trị tồn, trạng thái (`Đủ hàng` / `SẮP HẾT HÀNG`) + 2 nút **`+ Nhập kho`** và **`Kiểm kê`**.
   * **Tab 2 – Định mức Công thức (BOM):** Bảng liệt kê trọn bộ **12 món nước** với chi tiết nguyên liệu & định lượng cụ thể (VD: `Cốt cà phê Robusta: 25 ml` • `Sữa đặc Ngôi Sao: 30 ml`), giá vốn/ly, biên lợi nhuận gộp, khả năng pha tối đa (`X ly`) và nút **`Sửa định mức`**.
   * **Tab 3 – Nhật ký Biến động Kho (`lich_su_kho`):** Có lọc theo Loại giao dịch và Nguyên liệu; gắn màu trực quan: `NHAP_KHO` (xanh lá), `XUAT_BAN_HANG` (xanh dương, kèm `#HD-x`), `KIEM_KE_HAO_HUT` (đỏ cam, hiện rõ lý do).
4. **2 Modal thao tác (dùng Bootstrap 5 Toast):**
   * `#restockModal` (Nhập kho): Số lượng nhập (ô trống có placeholder), Đơn giá nhập lô mới, Nhà cung cấp, Ghi chú + xem trước tổng tiền nhập.
   * `#auditModal` (Kiểm kê): Tồn hệ thống hiện tại, Số lượng tồn thực tế, tự động tính chênh lệch & giá trị hao hụt, Lý do điều chỉnh (bắt buộc).

---

## 5. KẾ HOẠCH KIỂM THỬ & ĐIỂM NHẤN DEMO (`KhoKpiIntegrationTest.java`)

1. `restockUpdatesWeightedAverageCostAndLogsHistory`: `quanly` nhập $500\text{ ml}$ Sữa đặc (cũ $120\text{ ml}$ giá $80\text{ đ}$) với giá lô mới $90\text{ đ}$ $\rightarrow$ tồn $= 620\text{ ml}$, giá bình quân $= 88.06\text{ đ}$, sinh 1 dòng `NHAP_KHO`.
2. `inventoryAuditRecordsLossAndTriggersLowStockAlert`: Kiểm kê Sữa đặc xuống $80\text{ ml}$ kèm lý do `"Sữa quá hạn"` $\rightarrow$ `sapHet = true`, sinh dòng `KIEM_KE_HAO_HUT` (`-40 ml`, `3.200 đ`).
3. `inventoryAuditRejectsEmptyReasonOrSameQuantity`: Thiếu lý do hoặc số lượng không đổi $\rightarrow$ `HTTP 400`.
4. `paymentAutoLogsSaleDeductionAndRollsBackHistoryOnFailure`: Thanh toán thành công sinh đủ dòng `XUAT_BAN_HANG` gắn `ma_hoa_don`; thiếu kho Rollback sạch cả `lich_su_kho`.
5. `dashboardFiltersByTimeRangeAndCalculatesTopSellers`: Xác minh `HOM_NAY` trả đúng doanh thu `125.000 đ`, lợi nhuận gộp `88.675 đ`, tổng tồn kho `2.613.600 đ` và Top 1 `Trà đào cam sả`.
6. `cashierAndWaiterBlockedFromKhoAndAuditEndpoints`: Cả `phucvu` và `thungan` bị chặn `HTTP 403 Forbidden` trên toàn bộ API `/api/kho/**`.