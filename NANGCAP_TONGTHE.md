# ĐẶC TẢ NÂNG CẤP TỔNG THỂ HỆ THỐNG (PHIÊN BẢN 2.0 – MASTER SPEC)

### HỆ THỐNG QUẢN LÝ QUÁN CÀ PHÊ – SANGRIMAR CAFE & LOUNGE

* **Tên thương hiệu chuẩn:** **Sangrimar Cafe & Lounge**
* **Học phần chuẩn:** **Phân tích và Thiết kế Yêu cầu** (Mã học phần: `502050`)
* **Đơn vị đào tạo:** Khoa Công nghệ Thông tin – Trường Đại học Tôn Đức Thắng
* **Giảng viên hướng dẫn:** TS. Hồ Thị Linh
* **Nhóm thực hiện:**
  * Nguyễn Văn Duy (`[CẬP NHẬT MSSV SAU]` – Nhóm trưởng – $34\%$)
  * Trần Văn Quý (`[CẬP NHẬT MSSV SAU]` – Thành viên – $33\%$)
  * Lê Tấn Đạt (`[CẬP NHẬT MSSV SAU]` – Thành viên – $33\%$)
* **Nguồn dữ liệu chuẩn duy nhất (Single Source of Truth):** Mọi cấu trúc bảng, ràng buộc và dữ liệu khởi tạo được thống nhất tại tệp **`database_mau.sql` (Phiên bản 2.0 – 11 bảng)** và đồng bộ xuyên suốt 3 tài liệu phân hệ: `NANG_CAP_POS.md`, `NANG_CAP_KHOKPI.md`, `NANG_CAP_CHAMCONG.md`.

---

## 1. CHUẨN HÓA NHẬN DIỆN & TÍNH NHẤT QUÁN HỒ SƠ (TRACEABILITY)

Toàn bộ mã nguồn Java, giao diện Web, ảnh chụp màn hình đưa vào Mục 2.7, quyển báo cáo Word/PDF và Slide thuyết trình bắt buộc thống nhất theo bảng chuẩn sau:

| Hạng mục | Tên cũ / Chưa đồng bộ (Loại bỏ) | Chuẩn chính thức v2.0 (Bắt buộc) |
| :--- | :--- | :--- |
| **Tên quán / Thương hiệu** | `COFFEE POS`, `Mộc An`, `Quán cà phê Mộc An` | **Sangrimar Cafe & Lounge** |
| **Tên học phần** | `Phát triển và Thiết kế Hệ thống Thông tin` | **Phân tích và Thiết kế Yêu cầu** |
| **Mã số sinh viên** | `521H0001`, `521H0002`, `521H0003` *(giả định)* | `[CẬP NHẬT MSSV SAU]` *(cập nhật MSSV thật trước khi nộp)* |
| **Vai trò & Tài khoản (RBAC)** | `ADMIN`, `MANAGER`, `STAFF` (5 tài khoản cũ) | **`QUAN_LY`**, **`THU_NGAN`**, **`PHUC_VU`** (3 tài khoản chuẩn) |
| **Trạng thái & Màu viền Bàn** | `TRONG` (Xanh lá), `CO_KHACH` (Đỏ) | **`TRONG`** (Xanh dương `#0D6EFD`), **`DANG_DUNG_NUOC`** (Xanh lá `#198754`), **`CHO_THANH_TOAN`** (Vàng hổ phách `#D97706`) |
| **Quy mô Thực đơn & Kho mẫu** | 20 món – 10 nguyên liệu *(nhiều món mô phỏng)* | **12 món (4 nhóm, tối đa 4 món/nhóm) – 13 nguyên liệu thật $100\%$ (34 BOM)** |
| **Cơ sở dữ liệu** | 10 bảng (v1.0) | **11 bảng chuẩn 3NF** (bổ sung `lich_su_kho` và các cột nâng cấp POS/Chấm công) |

---

## 2. QUY CHUẨN 3 VAI TRÒ, 3 TÀI KHOẢN & LUỒNG ĐĂNG NHẬP

### 2.1. Bảng quy chuẩn đúng 3 tài khoản mặc định

| `ma_nv` | Tên đăng nhập | Mật khẩu | Vai trò (`vai_tro`) | Họ tên hiển thị | SĐT | Trang đích | Tab hiển thị trên Unified Topbar |
| :---: | :--- | :--- | :---: | :--- | :---: | :--- | :--- |
| **`1`** | `quanly` | `quanly` | `QUAN_LY` | Nguyễn Văn Duy (Quản lý) | `0901112222` | `kho.html` | `Bán hàng (POS)` · `Kho & KPI` *(có badge đỏ)* · `Nhân sự & Chấm công` |
| **`2`** | `thungan` | `thungan` | `THU_NGAN` | Trần Văn Quý (Thu ngân) | `0903334444` | `banhang.html` | `Bán hàng (POS)` · `Chấm công & Lịch ca` *(Chế độ Cá nhân)* |
| **`3`** | `phucvu` | `phucvu` | `PHUC_VU` | Lê Tấn Đạt (Phục vụ) | `0905556666` | `banhang.html` | `Bán hàng (POS)` · `Chấm công & Lịch ca` *(Chế độ Cá nhân)* |

*(Cả 3 mật khẩu đều có độ dài $\ge 6$ ký tự nên vẫn giữ nguyên chuẩn xác thực độ dài mật khẩu tối thiểu $6$ ký tự).*

### 2.2. Nâng cấp bảo mật Đăng nhập & Phiên làm việc (`index.html`, `XacThucController`, `dung-chung.js`)

1. **Nút điền nhanh phục vụ Demo (`index.html`):** Bên dưới nút **"Đăng nhập"**, bố trí 3 nút phụ (*Quick-fill*): `Quản lý (quanly)` · `Thu ngân (thungan)` · `Phục vụ (phucvu)` giúp chuyển vai trò trong 1 chạm khi demo trên lớp.
2. **Khóa tạm khi đăng nhập sai liên tiếp (Khớp đặc tả `UC01`):** Lưu `soLanDangNhapSai` và `khoaDangNhapDen` trong `HttpSession`. Nhập sai liên tiếp $5$ lần $\rightarrow$ tạm khóa $60\text{ giây}$, trả về `HTTP 429 Too Many Requests`. Đăng nhập thành công đặt lại bộ đếm về $0$.
3. **Tự động xử lý hết phiên (`401 Unauthorized`):** Hàm `apiFetch()` trong `dung-chung.js` tự động bắt mã `401`, xóa sạch `localStorage` & `sessionStorage` và chuyển về `index.html?expired=1` kèm thông báo *"Phiên đăng nhập đã hết hạn, vui lòng đăng nhập lại"*.
4. **Chống rò rỉ API Kho trên Topbar:** Chỉ gọi `GET /api/kho/sap-het` để hiển thị badge đỏ trên Topbar khi `user.vaiTro === 'QUAN_LY'`, đảm bảo **0 lỗi đỏ `403 FORBIDDEN`** trên Console đối với `PHUC_VU` và `THU_NGAN`.

### 2.3. Ma trận phân quyền RBAC 4 tầng (UI – Route Guard – Component – REST API)

| Nghiệp vụ / Tính năng | `PHUC_VU` | `THU_NGAN` | `QUAN_LY` | Kiểm soát kỹ thuật |
| :--- | :---: | :---: | :---: | :--- |
| Đăng nhập / Đăng xuất an toàn | Có | Có | Có | `HttpSession` + `POST /api/auth/logout` |
| Xem sơ đồ bàn, tổng tiền tạm tính & **số ly có thể làm** | Có | Có | Có | Chỉ nhận `soLyCoTheLam` (không lộ tồn kho gốc) |
| Mở bàn, gọi món, thêm món & đẩy phiếu in Quầy Bar | Có | Có | Có | `POST /api/order/tao-don`, `PUT /api/order/{id}/mon` |
| Bấm **"Yêu cầu thanh toán"** (Bàn sang viền Vàng) | Có | Có | Có | `POST /api/order/{id}/yeu-cau-thanh-toan` |
| Bấm **"Quay lại phục vụ"** (Vàng $\rightarrow$ Xanh lá) | Có | Có | Có | `POST /api/order/{id}/quay-lai-phuc-vu` |
| In Phiếu Tạm Tính & **Xác nhận Thanh toán / Đóng bàn** | **KHÓA (403)** | **Có** | **Có** | `PhanQuyenSession.requireThuNganHoacQuanLy()` |
| Hủy đơn hàng đã lưu trên bàn | **KHÓA (403)** | **Có** | **Có** | Ngăn phục vụ tự xóa đơn đã ra nước |
| Áp dụng chiết khấu giảm giá hóa đơn | **KHÓA (403)** | **KHÓA (403)** | **Có** | Mở `#pos-discount` duy nhất cho `QUAN_LY` |
| Xem & Tự Check-in / Check-out ca hôm nay của mình | Có | Có | Có | `nhansu.html` Chế độ Cá nhân; lấy `maNV` từ Session |
| Quản lý Kho, Bảng BOM, Nhập kho, Kiểm kê & KPI | **Ẩn / 403** | **Ẩn / 403** | **Có** | `PhanQuyenSession.requireQuanLy()` + Route Guard |
| Quản lý Hồ sơ Nhân sự, Phân ca/Đổi ca, Chốt ca, Điều chỉnh công | **Ẩn / 403** | **Ẩn / 403** | **Có** | `PhanQuyenSession.requireQuanLy()` + Chế độ Quản trị |

---

## 3. QUY CHUẨN GIAO DIỆN MÀU SÁNG TỐI ƯU IN ẤN (PRINT-FRIENDLY LIGHT MODE)

Toàn bộ giao diện Web được chuẩn hóa sang **tông sáng độ tương phản cao** (`mossary.css`) để ảnh chụp màn hình đưa vào Mục 2.7 Quyển báo cáo in trên giấy A4 luôn sắc nét, sáng sủa và không bết mực.

### 3.1. Hệ thống biến màu chuẩn (`:root`)

```css
:root {
  /* Nền sáng tối ưu in ấn A4 */
  --bg-page: #F8F9FA;
  --bg-surface: #FFFFFF;
  --bg-surface-subtle: #F1F5F9;
  --bg-topbar: #FFFFFF;

  /* Chữ tương phản cao (không dùng xám quá nhạt) */
  --text-primary: #111827;
  --text-secondary: #374151;
  --text-muted: #4B5563;

  /* Viền phân khối rõ nét thay vì phụ thuộc box-shadow */
  --border-default: #CBD5E1;
  --border-strong: #94A3B8;

  /* Nhận diện thương hiệu Sangrimar Cafe & Lounge */
  --brand-primary: #5D4037;
  --brand-accent: #8D6E63;
  --brand-tint: #FBF8F4;

  /* 3 màu viền Sơ đồ Bàn (dày 2.5px) */
  --table-empty-border: #0D6EFD;     /* TRONG: Xanh dương */
  --table-empty-bg: #EFF6FF;
  --table-serving-border: #198754;   /* DANG_DUNG_NUOC: Xanh lá cây */
  --table-serving-bg: #ECFDF5;
  --table-paying-border: #D97706;    /* CHO_THANH_TOAN: Vàng hổ phách */
  --table-paying-bg: #FFFBEB;

  /* Nhãn trạng thái số ly còn lại trên thẻ Món POS */
  --stock-ok-text: #15803D;
  --stock-ok-bg: #F0FDF4;
  --stock-low-text: #B45309;
  --stock-low-bg: #FFFBEB;
  --stock-out-text: #B91C1C;
  --stock-out-bg: #FEF2F2;
}
```

### 3.2. Nguyên tắc bố cục & In ấn

* **Unified Topbar nền trắng:** Đường viền đáy `2px solid #CBD5E1`, hiển thị tên **Sangrimar Cafe & Lounge** chữ nâu cà phê đậm `#5D4037`, tab hiện tại có nền `#FBF8F4` và gạch chân `3px solid #5D4037`. Bỏ hoàn toàn Sidebar trái.
* **Thẻ Bàn 3 màu viền trên `banhang.html`:** Không hiển thị số lượng khách ngồi. Chỉ hiển thị **Tên bàn + Vị trí**, **Nhãn trạng thái** (`TRỐNG` / `ĐANG DÙNG` / `CHỜ THANH TOÁN`), và **Icon tiền + Tổng tiền tạm tính** khi bàn có đơn.
* **Bảng dữ liệu:** Dùng `table table-bordered bg-white`, tiêu đề bảng nền xám sáng `#F1F5F9` chữ đen đậm, khung kẻ ô đầy đủ.
* **Hóa đơn & Phiếu in (`@media print`):** Tiêu đề **SANGRIMAR CAFE & LOUNGE**, nền trắng, chữ đen, viền nét đứt. Khi in chỉ xuất đúng khung phiếu.
* **Chuẩn chụp ảnh báo cáo:** Cố định cửa sổ trình duyệt $1280 \times 800\text{ px}$ (zoom $100\%$), font Google Fonts **Roboto**, **0 ký tự emoji**.

---

## 4. TINH GỌN THỰC ĐƠN 12 MÓN & 13 NGUYÊN LIỆU THẬT 100%

Hệ thống chuẩn hóa còn **12 món (4 nhóm, tối đa 4 món/nhóm)** khớp tuyệt đối **13 nguyên liệu thật** trong bảng `nguyen_lieu` với **34 dòng công thức BOM** thực tế (xóa bỏ 100% dữ liệu mô phỏng).

### 4.1. Danh mục 13 Nguyên liệu thật (`nguyen_lieu`)

| `ma_nl` | Tên nguyên liệu | ĐVT | Tồn khởi tạo | Ngưỡng tối thiểu | Đơn giá nhập (VNĐ) | Giá trị tồn khởi tạo |
| :---: | :--- | :---: | :---: | :---: | :---: | :---: |
| `1` | Cốt cà phê Robusta | `ml` | `2500` | `300` | `150` | 375.000 đ |
| `2` | **Sữa đặc Ngôi Sao** *(Sát ngưỡng Demo)* | `ml` | **`120`** | **`100`** | `80` | 9.600 đ |
| `3` | Sữa tươi thanh trùng | `ml` | `3000` | `500` | `35` | 105.000 đ |
| `4` | Trà đen Phúc Long | `g` | `1500` | `200` | `200` | 300.000 đ |
| `5` | Siro đào Teisseire | `ml` | `1200` | `200` | `180` | 216.000 đ |
| `6` | Đào ngâm miếng Kronos | `g` | `2000` | `300` | `120` | 240.000 đ |
| `7` | Đường nước mía | `ml` | `4000` | `500` | `25` | 100.000 đ |
| `8` | Bột Matcha Nhật Bản | `g` | `800` | `150` | `450` | 360.000 đ |
| `9` | Bột Chocolate nguyên chất | `g` | `1000` | `200` | `250` | 250.000 đ |
| `10` | Kem béo thực vật Rich | `ml` | `1800` | `300` | `90` | 162.000 đ |
| `11` | Trân châu đường đen | `g` | `1200` | `200` | `110` | 132.000 đ |
| `12` | Cam tươi | `g` | `6000` | `1000` | `35` | 210.000 đ |
| `13` | Dưa hấu tươi | `g` | `7000` | `1000` | `22` | 154.000 đ |
| | **TỔNG GIÁ TRỊ TỒN KHO HIỆN TẠI** | | | | | **2.613.600 đ** |

### 4.2. Bảng Định mức Công thức 12 Món (`mon_nuoc` & `cong_thuc`)

| `ma_mon` | Tên món | Nhóm | Giá bán | Định mức nguyên liệu / 1 ly (BOM) | Giá vốn / ly | Số ly khả dụng ban đầu* |
| :---: | :--- | :--- | :---: | :--- | :---: | :---: |
| `1` | Cà phê đen đá | Cà phê | 25.000 đ | `Cốt cà phê`: $30\text{ ml}$ · `Đường nước`: $15\text{ ml}$ | 4.875 đ | 82 ly |
| `2` | **Cà phê sữa đá** | Cà phê | 29.000 đ | `Cốt cà phê`: $25\text{ ml}$ · `Sữa đặc`: $30\text{ ml}$ | 6.150 đ | **4 ly** *(Vàng)* |
| `3` | **Bạc xỉu Sài Gòn** | Cà phê | 32.000 đ | `Cốt cà phê`: $15\text{ ml}$ · `Sữa đặc`: $40\text{ ml}$ · `Sữa tươi`: $60\text{ ml}$ | 7.550 đ | **3 ly** *(Vàng)* |
| `4` | Espresso nguyên chất | Cà phê | 30.000 đ | `Cốt cà phê`: $40\text{ ml}$ | 6.000 đ | 61 ly |
| `5` | Trà đào cam sả | Trà | 38.000 đ | `Trà đen`: $15\text{ g}$ · `Siro đào`: $25\text{ ml}$ · `Đào ngâm`: $40\text{ g}$ | 12.300 đ | 47 ly |
| `6` | Trà sữa trân châu | Trà | 42.000 đ | `Trà đen`: $20\text{ g}$ · `Sữa tươi`: $100\text{ ml}$ · `Sữa đặc`: $20\text{ ml}$ · `Trân châu`: $40\text{ g}$ | 13.500 đ | 6 ly |
| `7` | Trà đen macchiato | Trà | 35.000 đ | `Trà đen`: $20\text{ g}$ · `Đường nước`: $15\text{ ml}$ · `Kem béo`: $30\text{ ml}$ | 7.075 đ | 59 ly |
| `8` | Matcha đá xay kem sữa | Đá xay | 48.000 đ | `Bột Matcha`: $15\text{ g}$ · `Sữa tươi`: $80\text{ ml}$ · `Sữa đặc`: $20\text{ ml}$ · `Kem béo`: $30\text{ ml}$ | 13.850 đ | 6 ly |
| `9` | Chocolate đá xay | Đá xay | 45.000 đ | `Bột Chocolate`: $25\text{ g}$ · `Sữa tươi`: $100\text{ ml}$ · `Sữa đặc`: $15\text{ ml}$ · `Kem béo`: $30\text{ ml}$ | 13.650 đ | 8 ly |
| `10` | Cà phê đá xay kem sữa | Đá xay | 45.000 đ | `Cốt cà phê`: $30\text{ ml}$ · `Sữa tươi`: $80\text{ ml}$ · `Sữa đặc`: $20\text{ ml}$ · `Kem béo`: $30\text{ ml}$ | 11.600 đ | 6 ly |
| `11` | Nước ép cam tươi | Nước ép | 35.000 đ | `Cam tươi`: $300\text{ g}$ · `Đường nước`: $10\text{ ml}$ | 10.750 đ | 19 ly |
| `12` | Nước ép dưa hấu | Nước ép | 30.000 đ | `Dưa hấu tươi`: $350\text{ g}$ · `Đường nước`: $10\text{ ml}$ | 7.950 đ | 20 ly |

*\*Đã tính khấu trừ dự giữ tồn kho ảo (Soft Reservation) cho Đơn #3 (Bàn 03: 1 Cà phê đen + 1 Trà đào = 63.000 đ) và Đơn #4 (Bàn 07: 1 Nước ép cam + 1 Trà đen macchiato = 70.000 đ).*

---

## 5. LIÊN KẾT POS – KHO: HIỂN THỊ "SỐ LY CÓ THỂ PHA" & BẢNG BOM TẠI KHO

### 5.1. Cơ chế tính `soLyCoTheLam` không làm lộ Tồn kho gốc (Information Hiding & Soft Reservation)

1. **Nguyên tắc bảo mật:** `PHUC_VU` và `THU_NGAN` không được truy cập `/api/kho/**` và không nhận `so_luong_ton` hay `don_gia_nhap`. API `GET /api/mon-nuoc` trả về `MonNuocPosDTO` chứa đúng 1 chỉ số phái sinh duy nhất: **`soLyCoTheLam`** (`Integer`).
2. **Công thức Tồn kho Khả dụng (*Soft Reservation*):**
   $$\text{TonKhaDung}(i) = \max\left(0,\ \text{so\_luong\_ton}(i) - \sum_{hd \in \text{Đơn đang mở}} \sum_{ct \in hd} \text{ct.so\_luong} \times \text{dinh\_muc}(ct.\text{ma\_mon}, i)\right)$$
   *(Đơn đang mở gồm `trang_thai IN ('DANG_PHUC_VU', 'CHO_THANH_TOAN')`; hỗ trợ query param `boQuaHoaDonId` khi đang sửa chính đơn đó).*
3. **Công thức Điểm nghẽn (*Bottleneck Calculation*):**
   $$\text{soLyCoTheLam}(M) = \min_{i \in \text{BOM}(M)} \left\lfloor \frac{\text{TonKhaDung}(i)}{\text{dinh\_muc}(M, i)} \right\rfloor$$
   *(Nếu món $M$ chưa có định mức trong `cong_thuc` $\rightarrow$ trả về `0`).*
4. **Quy chuẩn hiển thị trên thẻ Món tại `banhang.html`:**
   * `soLyCoTheLam > 5`: Nhãn xanh (`Còn 82 ly`).
   * $1 \le \text{soLyCoTheLam} \le 5$: Nhãn vàng hổ phách (`Còn 4 ly`).
   * `soLyCoTheLam == 0`: Thẻ mờ nhẹ, nhãn đỏ **`HẾT MÓN`** và vô hiệu hóa nút chọn.
5. **Chống đặt quá ngưỡng nguyên liệu dùng chung trong cùng 1 giỏ hàng:** Khi bấm lưu/cập nhật đơn, `DonHangService` cộng gộp nhu cầu nguyên liệu của giỏ hiện tại + các đơn đang mở khác so với `so_luong_ton`. Nếu vượt quá, trả `HTTP 400 Bad Request` kèm thông báo thân thiện (không lộ tồn kho gốc) và làm mới lại số ly khả dụng trên POS.

### 5.2. Hiển thị trực tiếp Bảng Định mức Công thức (BOM) tại Phân hệ Kho (`kho.html`)

Dưới khu vực 6 thẻ KPI, `kho.html` chia làm **3 Tab con**:
1. **Tab 1 – Tồn kho Nguyên vật liệu** (13 nguyên liệu + nút `+ Nhập kho`, `Kiểm kê`).
2. **Tab 2 – Định mức Công thức (BOM)** (bảng liệt kê trực tiếp 12 món: chi tiết nguyên liệu & định lượng, giá vốn/ly, biên lợi nhuận gộp, số ly khả dụng và nút `Sửa định mức`).
3. **Tab 3 – Nhật ký Biến động Kho (`lich_su_kho`)** (`NHAP_KHO`, `XUAT_BAN_HANG`, `KIEM_KE_HAO_HUT`).

---

## 6. HỆ THỐNG RÀNG BUỘC TOÀN DIỆN (3 TẦNG: CSDL – BACKEND – FRONTEND)

### 6.1. Ràng buộc tầng Cơ sở dữ liệu (11 Bảng MySQL 8.0 / MariaDB InnoDB)
* **Toàn vẹn duy nhất (`UNIQUE`):** `nhan_vien(ten_dang_nhap)`, `cong_thuc(ma_mon, ma_nl)`, `phan_ca(ma_nv, ngay_lam, ma_ca)`, `cham_cong(ma_phan_ca)`, `cham_cong(ma_nv, ngay_lam, ma_ca)`, `hoa_don(ma_tham_chieu)`, `hoa_don(yeu_cau_tao)`, `hoa_don(yeu_cau_thanh_toan)`.
* **Ràng buộc miền giá trị (`CHECK`):** `ban.so_ghe > 0`, `mon_nuoc.don_gia >= 0`, `nguyen_lieu(so_luong_ton >= 0, muc_ton_toi_thieu >= 0, don_gia_nhap >= 0)`, `cong_thuc.dinh_muc > 0`, `chi_tiet_hoa_don(so_luong BETWEEN 1 AND 99, so_luong_da_in_bar >= 0)`, `hoa_don(tong_tien_goc >= 0, chiet_khau >= 0, tong_tien_thanh_toan >= 0, tien_thua >= 0)`, `ca_lam(gio_ket_thuc > gio_bat_dau, he_so_luong > 0)`, `cham_cong(so_phut_tre >= 0, so_phut_ve_som >= 0, so_gio_lam >= 0, gio_cong_quy_doi >= 0)`.
* **Toàn vẹn tham chiếu (`FOREIGN KEY`):** `ON DELETE CASCADE` cho `chi_tiet_hoa_don`, `cong_thuc`, `phan_ca`, `cham_cong`; `ON DELETE SET NULL` cho `hoa_don.ma_ban`, `hoa_don.ma_nv_thu_ngan`, `lich_su_kho.ma_hoa_don`, `cham_cong.ma_phan_ca`, `cham_cong.nguoi_dieu_chinh`.

### 6.2. Ràng buộc tầng Backend & Frontend
* **Chống tự khóa quản trị:** Chặn `QUAN_LY` đang đăng nhập tự xóa/ngừng việc chính mình hoặc hạ vai trò nếu là `QUAN_LY` đang hoạt động cuối cùng.
* **Khóa sửa món khi bàn đang `CHO_THANH_TOAN`:** Muốn gọi thêm nước phải bấm **"Quay lại phục vụ"** (Vàng $\rightarrow$ Xanh lá) để không lệch số tiền trên Phiếu Tạm Tính.
* **Validation đầu vào:** `tenDangNhap` khớp `^[a-z0-9_]{3,50}$`, `matKhau` tối thiểu 6 ký tự, `soDienThoai` khớp `^0\d{9}$`.
* **Chống XSS & Double-submit:** Toàn bộ chuỗi nhập đi qua `escapeHtml()`; vô hiệu hóa nút khi đang gửi API; dùng $100\%$ Bootstrap 5 Toast; che giấu stacktrace tại `NgoaiLeControllerAdvice`.

---

## 7. QUY TRÌNH CHUYỂN ĐỔI AN TOÀN TỪ V1.0 LÊN V2.0

1. Tách nhánh Git riêng (`git checkout -b nang-cap-v2`) để bảo toàn nhánh `main` (v1.0) đang chạy ổn định.
2. Cập nhật trọn bộ mã nguồn **Backend Java** (Entities, Repositories, DTOs, Services, Controllers, `PhanQuyenSession`) và **Frontend** (`mossary.css`, `dung-chung.js`, 4 trang HTML/JS) theo đúng 4 tệp đặc tả v2.0.
3. Trong `application.properties`, đặt `spring.jpa.hibernate.ddl-auto=none` (hoặc `validate`) để Hibernate giữ nguyên toàn bộ ràng buộc `CHECK`/`UNIQUE` của MySQL; giữ `create-drop` tại `application-test.properties` cho H2.
4. Sau khi hoàn tất toàn bộ mã nguồn, thực hiện nạp sạch tệp **`database_mau.sql` (v2.0)** bằng lệnh UTF-8:
   ```cmd
   C:\xampp\mysql\bin\mysql.exe --default-character-set=utf8mb4 -u root -e "SOURCE d:/3.1. PTTKYC/CK/02_SourceCode_WebJava_Prototype/database_mau.sql;"
   ```
5. Khởi chạy ứng dụng (`.\mvnw.cmd spring-boot:run`), chạy bộ kiểm thử tích hợp (`.\mvnw.cmd test`) và chụp bộ ảnh màn hình chuẩn $1280 \times 800$ (Light Mode) cập nhật vào Mục 2.7 Quyển báo cáo.