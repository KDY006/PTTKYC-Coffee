# ĐẶC TẢ NÂNG CẤP PHÂN HỆ BÁN HÀNG POS (PHIÊN BẢN 2.0)

### QUY TRÌNH PHỤC VỤ TẠI BÀN, SƠ ĐỒ BÀN 3 MÀU VIỀN, LIÊN KẾT SỐ LY KHẢ DỤNG & PHÂN QUYỀN 3 VAI TRÒ

* **Thương hiệu chuẩn:** **Sangrimar Cafe & Lounge**
* **Học phần:** **Phân tích và Thiết kế Yêu cầu** (Mã môn: `502050`)
* **Tài liệu gốc đồng bộ:** `NANG_CAP_TONGTHE.md` & `database_mau.sql` (v2.0 – 11 bảng, 3 tài khoản, 12 món – 13 nguyên liệu thật).
* **Mục tiêu cốt lõi:** Phản ánh trung thực mô hình vận hành **Phục vụ tại bàn có đối soát thu ngân (Table Service & Cashier Verification)**, tối ưu giao diện màu sáng chuẩn in ấn A4 và liên kết tồn kho khả dụng không làm lộ dữ liệu kho gốc.

---

## 1. TINH GỌN ĐỐI TƯỢNG SỬ DỤNG & MA TRẬN PHÂN QUYỀN POS (RBAC 3 CẤP)

### 1.1. Mô hình 3 vai trò & 3 tài khoản chuẩn hóa

1. **`QUAN_LY` (`quanly / quanly` – Nguyễn Văn Duy):** Gộp quyền quản trị hệ thống và quản lý cửa hàng. Nắm toàn quyền trên hệ thống (Kho/BOM, KPI, Chiết khấu, Nhân sự/Phân ca/Chấm công, cùng mọi thao tác của Thu ngân và Phục vụ).
2. **`THU_NGAN` (`thungan / thungan` – Trần Văn Quý):** Tách riêng khỏi `QUAN_LY` theo nguyên tắc **phân tách trách nhiệm kiểm soát nội bộ (Segregation of Duties)**. Nắm quyền chốt tài chính tại quầy: tạo/sửa đơn, in Phiếu Tạm Tính, kiểm tra tiền mặt/minh chứng chuyển khoản, tính tiền thối, bấm **"Xác nhận thanh toán & Đóng bàn"** (trừ kho tự động) và hủy đơn; nhưng **không có quyền** chiết khấu, quản lý kho hay xếp ca.
3. **`PHUC_VU` (`phucvu / phucvu` – Lê Tấn Đạt):** Tập trung tốc độ tiếp nhận yêu cầu tại bàn: xem sơ đồ bàn & số ly có thể pha, mở bàn gọi món, gọi thêm món (đẩy lệnh in quầy bar), bấm **"Yêu cầu thanh toán"** và tự chấm công cá nhân. **Bị khóa cứng** nút xác nhận hoàn tất thanh toán, hủy đơn đã lưu, chiết khấu và phân hệ Kho.

> **Cập nhật Biểu đồ Use Case (UML Generalization):**
> Actor tổng quát `Nhân viên` (`Đăng nhập`, `Chấm công cá nhân`) $\rightarrow$ `Phục vụ` (`Xem sơ đồ bàn`, `Tạo/Cập nhật đơn & In Bar`, `Yêu cầu thanh toán`) $\rightarrow$ `Thu ngân` (`In Phiếu Tạm Tính`, `Xác nhận Thanh toán & Đóng bàn`, `Hủy đơn`) $\rightarrow$ `Quản lý` (`Áp dụng Chiết khấu`, `Quản lý Kho & BOM`, `Quản lý Nhân sự & Phân ca`, `Xem KPI Dashboard`).

### 1.2. Ma trận phân quyền thao tác POS 4 tầng

| Nghiệp vụ / Tính năng POS | `PHUC_VU` | `THU_NGAN` | `QUAN_LY` | Cơ chế kiểm soát kỹ thuật |
| :--- | :---: | :---: | :---: | :--- |
| Xem sơ đồ bàn, tổng tiền tạm tính & **số ly có thể pha** | Có | Có | Có | `GET /api/ban`, `GET /api/mon-nuoc` (không lộ tồn kho) |
| Mở bàn, tạo đơn & đẩy lệnh in Quầy Bar | Có | Có | Có | `POST /api/order/tao-don` |
| Gọi thêm món khi bàn đang `DANG_DUNG_NUOC` | Có | Có | Có | `PUT /api/order/{id}/mon` (chỉ in phần món mới thêm) |
| Bấm **"Yêu cầu thanh toán"** (Bàn sang viền Vàng) | Có | Có | Có | `POST /api/order/{id}/yeu-cau-thanh-toan` |
| Bấm **"Quay lại phục vụ"** (Vàng $\rightarrow$ Xanh lá) | Có | Có | Có | `POST /api/order/{id}/quay-lai-phuc-vu` |
| In Phiếu Tạm Tính đưa khách kiểm tra | **Ẩn** | **Có** | **Có** | Hiển thị nút in tạm tính cho `THU_NGAN`/`QUAN_LY` |
| **Xác nhận Thanh toán & Đóng bàn** (Trừ kho BOM) | **KHÓA (403)** | **Có** | **Có** | `PhanQuyenSession.requireThuNganHoacQuanLy()` |
| Hủy đơn hàng đã lưu trên bàn | **KHÓA (403)** | **Có** | **Có** | `PhanQuyenSession.requireThuNganHoacQuanLy()` |
| Nhập chiết khấu giảm giá hóa đơn | **KHÓA (403)** | **KHÓA (403)** | **Có** | Chỉ mở `#pos-discount` cho `QUAN_LY` |

---

## 2. QUY TRÌNH NGHIỆP VỤ PHỤC VỤ TẠI BÀN & ĐỐI SOÁT THANH TOÁN

### 2.1. Sơ đồ luồng tổng thể (End-to-End Flow)

```text
[Khách vào bàn] (Bàn viền XANH DƯƠNG: TRONG)
       │
       ▼
(1) PHUC_VU mở bàn, xem số ly khả dụng trên thực đơn -> chọn món + ghi chú
    -> Bấm "Xác nhận & In Bill Bar"
       │ -> Kiểm tra tồn kho khả dụng (Soft Reservation)
       │ -> Lưu HoaDon (DANG_PHUC_VU), lưu so_luong_da_in_bar = so_luong
       │ -> Bàn đổi viền XANH LÁ CÂY (DANG_DUNG_NUOC) + Hiện [icon tiền | tổng tiền]
       │ -> Xuất Phiếu Pha Chế (Bar Ticket) xuống máy in Quầy Bar
       ▼
(2) Quầy Bar nhận bill, pha chế và ra món cho khách
       │
       ├──► [Luồng phụ A: Khách gọi thêm nước khi đang dùng]
       │    PHUC_VU mở lại bàn (Xanh lá) -> Thêm món mới -> Bấm "Cập nhật & In món mới"
       │    -> Hệ thống CHỈ in phần chênh lệch (so_luong - so_luong_da_in_bar) xuống Bar
       │    -> Cộng dồn tổng tiền tạm tính hiển thị trên thẻ bàn
       ▼
(3) Khách dùng xong, gọi thanh toán -> PHUC_VU bấm "Yêu cầu thanh toán" trên web
       │ -> HoaDon & Ban chuyển sang trạng thái CHO_THANH_TOAN
       │ -> Bàn đổi viền VÀNG HỔ PHÁCH (#D97706) trên mọi màn hình POS
       │ -> Khóa thêm/sửa món của bàn này để khớp số tiền trên bill tạm tính
       ▼
(4) THU_NGAN thấy bàn viền VÀNG -> Bấm "In Bill Tạm Tính" đưa cho PHUC_VU
       │
       ├──► [Luồng phụ B: Khách muốn gọi thêm nước sau khi đã gọi tính tiền]
       │    PHUC_VU hoặc THU_NGAN bấm "Quay lại phục vụ"
       │    -> Bàn & HoaDon chuyển từ CHO_THANH_TOAN (Vàng) về DANG_DUNG_NUOC (Xanh lá)
       ▼
(5) PHUC_VU đưa Bill Tạm Tính cho khách kiểm tra -> Nhận thanh toán:
       │  • Tiền mặt: Nhận tiền mặt từ khách
       │  • Chuyển khoản: Khách quét QR -> PHUC_VU xem/chụp ảnh giao dịch thành công
       ▼
(6) PHUC_VU bàn giao tiền mặt (hoặc minh chứng chuyển khoản) cho THU_NGAN
       │
       ▼
(7) THU_NGAN đối soát & bấm "Xác nhận thanh toán & Đóng bàn":
       │  • TIEN_MAT: Nhập tiền khách đưa -> hệ thống tự tính tiền thừa (tien_thua)
       │  • CHUYEN_KHOAN: Tick xác nhận minh chứng + nhập mã tham chiếu duy nhất
       │  • Lưu ma_nv_thu_ngan = ID của Thu ngân/Quản lý đang đăng nhập
       │  • Giao dịch nguyên tử @Transactional:
       │       [a] Trừ kho tự động theo BOM & ghi vết XUAT_BAN_HANG vào lich_su_kho
       │       [b] Cập nhật HoaDon -> DA_THANH_TOAN & xuất Hóa đơn chính thức
       │       [c] Đóng bàn: Ban -> TRONG (Bàn trở lại viền XANH DƯƠNG)
       ▼
[Nếu tiền mặt có tiền thừa] THU_NGAN giao tiền thối cho PHUC_VU trả khách -> Kết thúc
```

### 2.2. Các luồng ngoại lệ bắt buộc xử lý (Exception Flows)
* **E1 – Phục vụ cố gọi API thanh toán/hủy đơn:** `DonHangController` gọi `access.requireThuNganHoacQuanLy(session)` trả ngay `HTTP 403 Forbidden`.
* **E2 – Chỉnh sửa món khi bàn đang `CHO_THANH_TOAN`:** Máy chủ chặn (`HTTP 409 Conflict`), yêu cầu bấm *"Quay lại phục vụ"* trước để tránh sai lệch với Bill Tạm Tính đã in.
* **E3 – Gọi vượt quá số ly khả dụng (hoặc tranh chấp nguyên liệu dùng chung trong cùng giỏ):** `DonHangService` cộng gộp nhu cầu nguyên liệu của giỏ hàng + các đơn đang mở khác so với `so_luong_ton`, trả về `HTTP 400 Bad Request` kèm thông báo thân thiện (không làm lộ tồn kho gốc) và làm mới lại `soLyCoTheLam`.
* **E4 – Thiếu tiền khách đưa hoặc thiếu xác nhận minh chứng chuyển khoản:** Trả `HTTP 400 Bad Request` (`tienKhachDua < tongTienThanhToan` hoặc `xacNhanChuyenKhoan != true` / trống `maThamChieu`).
* **E5 – Kho không đủ nguyên liệu tại thời điểm Thu ngân chốt thanh toán:** `@Transactional(rollbackFor = Exception.class)` lập tức **Rollback $100\%$** cả tồn kho, `lich_su_kho`, trạng thái hóa đơn và trạng thái bàn.

---

## 3. NÂNG CẤP GIAO DIỆN SƠ ĐỒ BÀN & THAO TÁC POS (`banhang.html`, `mossary.css`)

### 3.1. Chuẩn hóa 3 màu viền thẻ Bàn (Light Mode chuẩn in ấn A4)

Không hiển thị số lượng khách ngồi trên thẻ bàn. Mỗi thẻ bàn chỉ hiển thị **Tên bàn + Vị trí**, **Nhãn trạng thái** và **Icon tiền + Tổng tiền tạm tính** (khi có đơn):

| Mã trạng thái (`Ban.trangThai`) | Màu viền (`2.5px solid`) | Màu nền thẻ | Nội dung hiển thị trên thẻ Bàn |
| :--- | :--- | :--- | :--- |
| **`TRONG`** | **Xanh dương** (`#0D6EFD`) | `#EFF6FF` | Tên bàn (`Bàn 01`) • Vị trí (`Tầng 1`)<br>Nhãn: `TRỐNG` |
| **`DANG_DUNG_NUOC`** | **Xanh lá cây** (`#198754`) | `#ECFDF5` | Tên bàn (`Bàn 03`) • Nhãn `ĐANG DÙNG`<br>**Icon tiền + Tổng tạm tính:** `63.000 đ` |
| **`CHO_THANH_TOAN`** | **Vàng hổ phách** (`#D97706`) | `#FFFBEB` | Tên bàn (`Bàn 07`) • Nhãn `CHỜ THANH TOÁN`<br>**Icon tiền + Tổng cần thu:** `70.000 đ` |

*(Vàng hổ phách `#D97706` được dùng thay vàng tươi `#FFC107` để đảm bảo viền thẻ sắc nét, không bị chói hoặc mất nét khi in báo cáo khổ A4).*

### 3.2. Hiển thị "Số ly có thể pha" trên Thẻ Món POS
Mỗi thẻ trong danh mục 12 món trên `banhang.html` hiển thị nhãn `soLyCoTheLam`:
* `soLyCoTheLam > 5`: Nhãn xanh lá (`Còn 82 ly`).
* $1 \le \text{soLyCoTheLam} \le 5$: Nhãn vàng hổ phách (`Còn 4 ly` cho Cà phê sữa đá, `Còn 3 ly` cho Bạc xỉu).
* `soLyCoTheLam == 0`: Mờ thẻ, nhãn đỏ **`HẾT MÓN`** và khóa nút chọn.

### 3.3. Bộ 3 mẫu phiếu in chuẩn thương hiệu **SANGRIMAR CAFE & LOUNGE** (`@media print`)
1. **Phiếu Pha Chế (Bar Ticket):** Chỉ in tên bàn, mã đơn, giờ gọi và **các món vừa gọi mới** kèm ghi chú pha chế (không hiện giá tiền).
2. **Phiếu Tạm Tính (Proforma Bill):** Danh sách đầy đủ món, đơn giá, thành tiền, tổng tiền dự kiến, dòng chữ *"PHIẾU TẠM TÍNH – QUÝ KHÁCH VUI LÒNG KIỂM TRA TRƯỚC KHI THANH TOÁN"* và mã QR chuyển khoản.
3. **Hóa Đơn Thanh Toán Chính Thức (Official Receipt):** Chỉ xuất sau khi `THU_NGAN`/`QUAN_LY` chốt đơn; hiển thị đầy đủ Người tạo đơn (`ma_nv`), Thu ngân chốt đơn (`ma_nv_thu_ngan`), Chiết khấu, Tiền khách đưa, Tiền thừa và Mã tham chiếu chuyển khoản.

---

## 4. THAY ĐỔI LƯỢC ĐỒ CSDL & BACKEND JAVA SPRING BOOT

### 4.1. Các thay đổi lược đồ đã hợp nhất vào `database_mau.sql` (v2.0)
* **`nhan_vien`:** `vai_tro` chỉ nhận `'QUAN_LY'`, `'THU_NGAN'`, `'PHUC_VU'` (3 tài khoản chuẩn: `quanly`, `thungan`, `phucvu`).
* **`ban`:** Thêm `so_ghe INT NOT NULL DEFAULT 4`; `trang_thai` nhận `'TRONG'`, `'DANG_DUNG_NUOC'`, `'CHO_THANH_TOAN'`.
* **`hoa_don`:** Bổ sung `ma_nv_thu_ngan` (FK $\rightarrow$ `nhan_vien`), `thoi_gian_yeu_cau_tt`, `tien_thua`, `xac_nhan_chuyen_khoan`, `minh_chung_chuyen_khoan`; `trang_thai` nhận `'DANG_PHUC_VU'`, `'CHO_THANH_TOAN'`, `'DA_THANH_TOAN'`, `'DA_HUY'`.
* **`chi_tiet_hoa_don`:** Bổ sung `so_luong_da_in_bar INT NOT NULL DEFAULT 0`.

### 4.2. Cập nhật `PhanQuyenSession.java`
```java
public static final String QUAN_LY = "QUAN_LY";
public static final String THU_NGAN = "THU_NGAN";
public static final String PHUC_VU = "PHUC_VU";

public Integer requireDangNhap(HttpSession session);

public void requireThuNganHoacQuanLy(HttpSession session) {
    String role = role(session);
    if (!THU_NGAN.equals(role) && !QUAN_LY.equals(role)) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN,
            "Chỉ Thu ngân hoặc Quản lý mới được thực hiện thao tác thanh toán/hủy đơn!");
    }
}

public void requireQuanLy(HttpSession session) {
    if (!QUAN_LY.equals(role(session))) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN,
            "Chức năng này chỉ dành cho Quản lý cửa hàng!");
    }
}
```

### 4.3. Danh sách REST API Endpoints phân hệ POS

| Method & Endpoint | Vai trò được phép | Dữ liệu & Logic xử lý |
| :--- | :--- | :--- |
| `GET /api/ban` *(hoặc `/api/ban/so-do`)* | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Trả về `List<BanHienThiDTO>` gồm `maBan`, `tenBan`, `viTri`, `soGhe`, `trangThai`, `maHoaDonHienTai`, `tongTienTamTinh`, `thoiGianMoBan`. |
| `GET /api/mon-nuoc?boQuaHoaDonId=` | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Trả về `List<MonNuocPosDTO>` gồm thông tin món và **`soLyCoTheLam`** (tính theo *Soft Reservation*, tuyệt đối không trả tồn kho gốc). |
| `POST /api/order/tao-don` | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Kiểm tra đủ tồn kho khả dụng $\rightarrow$ tạo `HoaDon` (`DANG_PHUC_VU`), `Ban` (`DANG_DUNG_NUOC`), gán `soLuongDaInBar = soLuong`, trả về hóa đơn kèm `phieuInBar`. |
| `PUT /api/order/{id}/mon` | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Chỉ cho phép khi `trangThai == DANG_PHUC_VU`. Kiểm tra tồn kho khả dụng, tính phần món mới $\Delta = \text{soLuongMoi} - \text{soLuongDaInBar}$ trả về `phieuInBarBoSung`. |
| `POST /api/order/{id}/yeu-cau-thanh-toan` | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Chuyển `HoaDon` & `Ban` từ `DANG_PHUC_VU`/`DANG_DUNG_NUOC` sang `CHO_THANH_TOAN` (viền Vàng), ghi `thoiGianYeuCauTT = NOW()`. |
| `POST /api/order/{id}/quay-lai-phuc-vu` | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Chuyển `HoaDon` & `Ban` từ `CHO_THANH_TOAN` (Vàng) về lại `DANG_PHUC_VU`/`DANG_DUNG_NUOC` (Xanh lá). |
| `POST /api/order/{id}/thanh-toan` | **`THU_NGAN`, `QUAN_LY`** | Chặn `PHUC_VU` (`403`). Chặn `THU_NGAN` gửi `chietKhau > 0` (`403`). Lưu `ma_nv_thu_ngan`, tính `tien_thua`, chạy `@Transactional` gọi `khoService.truKhoTuDong(hoaDon, thuNgan)` $\rightarrow$ `DA_THANH_TOAN` $\rightarrow$ `Ban.TRONG`. |
| `POST /api/order/{id}/huy` | **`THU_NGAN`, `QUAN_LY`** | Chặn `PHUC_VU` (`403`). Chuyển `HoaDon` sang `DA_HUY`, trả `Ban` về `TRONG`, giải phóng tồn kho dự giữ. |

### 4.4. JSON mẫu trả về của `GET /api/ban` (Khớp dữ liệu mẫu `database_mau.sql` v2.0)

```json
[
  {
    "maBan": 1,
    "tenBan": "Bàn 01",
    "viTri": "Tầng 1",
    "soGhe": 2,
    "trangThai": "TRONG",
    "maHoaDonHienTai": null,
    "tongTienTamTinh": 0,
    "thoiGianMoBan": null
  },
  {
    "maBan": 3,
    "tenBan": "Bàn 03",
    "viTri": "Tầng 1",
    "soGhe": 4,
    "trangThai": "DANG_DUNG_NUOC",
    "maHoaDonHienTai": 3,
    "tongTienTamTinh": 63000,
    "thoiGianMoBan": "2026-11-19T23:30:00"
  },
  {
    "maBan": 7,
    "tenBan": "Bàn 07",
    "viTri": "Tầng 2",
    "soGhe": 4,
    "trangThai": "CHO_THANH_TOAN",
    "maHoaDonHienTai": 4,
    "tongTienTamTinh": 70000,
    "thoiGianMoBan": "2026-11-19T23:15:00"
  }
]
```

---

## 5. KẾ HOẠCH KIỂM THỬ & KỊCH BẢN DEMO 3 VAI TRÒ LIÊN HOÀN

### 5.1. Ca kiểm thử tích hợp Backend (`DonHangIntegrationTest.java`)
1. `waiterCanOrderAndRequestPaymentButCannotConfirmPayment`: `phucvu` tạo đơn (`DANG_DUNG_NUOC`), bấm yêu cầu thanh toán (`CHO_THANH_TOAN`) trả `200`, nhưng gọi `POST /thanh-toan` hay `POST /huy` bị chặn `403`.
2. `menuCalculatesAvailableCupsWithSoftReservationWithoutExposingStock`: Xác minh `GET /api/mon-nuoc` trả đúng `Cà phê sữa đá = 4 ly`, `Bạc xỉu = 3 ly`, không chứa trường `soLuongTon`; khi tạo đơn 2 ly Cà phê sữa đá (chưa thanh toán), số ly khả dụng lập tức giảm còn `2 ly`.
3. `addMoreItemsOnlyPrintsDeltaToBarAndBlockedWhenWaitingPayment`: Gọi thêm món ở `DANG_DUNG_NUOC` chỉ trả món mới trong `phieuInBarBoSung`; cố sửa món khi bàn ở `CHO_THANH_TOAN` trả `409 Conflict`.
4. `cashierConfirmsCashAndTransferPaymentAndClosesTable`: `thungan` chốt đơn Bàn 02, lưu đúng `maNvThuNgan = 2`, tính đúng `tienThua`, trừ kho BOM, ghi `lich_su_kho` và trả bàn về `TRONG`.
5. `cashierCannotApplyDiscountOnlyManagerCan`: `thungan` nhập `chietKhau > 0` trả `403`; `quanly` nhập `chietKhau = 5000` trả `200 OK`.

### 5.2. Kịch bản Demo 3 tài khoản chuẩn (`phucvu` $\rightarrow$ `thungan` $\rightarrow$ `quanly`)
1. **Đăng nhập `phucvu / phucvu` (`PHUC_VU`):**
   * Sơ đồ bàn hiển thị đủ 3 màu viền: Xanh dương (Trống), Xanh lá (Bàn 03 — `63.000 đ`), Vàng hổ phách (Bàn 07 — `70.000 đ`).
   * Chỉ rõ thực đơn 12 món hiện nhãn `Còn 4 ly` ở **Cà phê sữa đá** (`ma_mon = 2`) và `Còn 3 ly` ở **Bạc xỉu** (`ma_mon = 3`).
   * Chọn **Bàn 02** $\rightarrow$ Gọi `1 Cà phê sữa đá (ít đường)` + `1 Trà đào cam sả` (`ma_mon = 5`) $\rightarrow$ Bấm **"Xác nhận & In Bill Bar"** $\rightarrow$ Bàn 02 chuyển **viền Xanh lá (`67.000 đ`)**, Cà phê sữa đá cập nhật tức thì sang `Còn 3 ly`.
   * Bấm **"Yêu cầu thanh toán"** $\rightarrow$ Bàn 02 chuyển **viền Vàng (`CHO_THANH_TOAN`)**.
2. **Đăng nhập `thungan / thungan` (`THU_NGAN`):**
   * Chọn **Bàn 02 (viền Vàng)** $\rightarrow$ Bấm **"In Bill Tạm Tính"** (hiện phiếu tạm tính Sangrimar Cafe & Lounge).
   * Bấm **"Xác nhận Thanh toán & Đóng bàn"**, nhập tiền khách đưa `100.000 đ` $\rightarrow$ Tự tính tiền thừa `33.000 đ`, xuất Hóa đơn chính thức (Phục vụ: *Lê Tấn Đạt*, Thu ngân: *Trần Văn Quý*), Bàn 02 trả về **viền Xanh dương (`TRONG`)**.
3. **Đăng nhập `quanly / quanly` (`QUAN_LY`):**
   * Mở tab **Kho & KPI** (`kho.html`) kiểm chứng **Sữa đặc Ngôi Sao** vừa tự động trừ $30\text{ ml}$ từ $120\text{ ml} \rightarrow 90\text{ ml} \le 100\text{ ml}$ và bật nhãn đỏ **`SẮP HẾT HÀNG`** kèm dòng `XUAT_BAN_HANG` trong Nhật ký Kho.