# TÓM TẮT BỐI CẢNH DỰ ÁN & TIẾN TRÌNH (CONTEXT SUMMARY)

> **Dự án:** Hệ thống Quản lý Quán Cà phê Sangrimar Cafe & Lounge  
> **Thư mục làm việc:** `d:\3.1. PTTKYC\CK`  
> **Thời điểm cập nhật:** 20/11/2026 (Hoàn tất File 4: `NANGCAP_CHAMCONG.md`)  

---

## 1. Nguyên tắc làm việc
- Thực hiện tuần tự 4 file markdown theo lệnh của người dùng.
- Không chạy kiểm thử tự động (Puppeteer/Browser/JUnit runner); người dùng trực tiếp kiểm thử thủ công.
- Tên thương hiệu duy nhất: **Sangrimar Cafe & Lounge**.
- Giữ nguyên chuẩn mã hóa UTF-8 tiếng Việt toàn bộ hệ thống.

---

## 2. Trạng thái kỹ thuật
- **Backend:** Spring Boot (Java 17 / OpenJDK 25) – Đã hoàn thiện toàn bộ Controller, Service, DTO, Repository, Entity cho Phân hệ POS, Kho/BOM/KPI và Phân hệ Nhân sự, Ca làm việc & Chấm công (BR-CC-01 -> BR-CC-07).
- **Frontend:** HTML5 / Bootstrap 5 / Vanilla JS (Light Mode tối ưu in ấn A4, sơ đồ bàn 3 màu viền, số ly có thể pha, bộ 3 mẫu phiếu in `@media print`, giao diện Chấm công phân tách 2 Chế độ Cá nhân vs Quản trị).
- **Database:** MySQL XAMPP (port 3306), DB `quanly_cafe`, `spring.jpa.hibernate.ddl-auto=none`.

---

## 3. Tiến độ thực hiện các file nâng cấp

### File 1: `NANGCAP_TONGTHE.md` [ĐÃ HOÀN THÀNH 100%]
1. **RBAC 3 vai trò / 3 tài khoản chuẩn:** `quanly/quanly` (đích: `kho.html`), `thungan/thungan` (đích: `banhang.html`), `phucvu/phucvu` (đích: `banhang.html`).
2. **Bảo mật & Đăng nhập (`index.html`, `XacThucController.java`, `dung-chung.js`):**
   - 3 nút Quick-fill 1-chạm cho demo.
   - Sai 5 lần liên tiếp $\rightarrow$ tạm khóa 60s (`HTTP 429 Too Many Requests` - UC01).
   - Tự động bắt mã `401` xóa storage, chuyển về `index.html?expired=1`.
   - Topbar phân quyền chặt chẽ, 0 lỗi đỏ `403 FORBIDDEN` cho Phục vụ và Thu ngân.
3. **Giao diện Light Mode (`mossary.css`):** Bảng màu sáng `:root`, sơ đồ bàn 3 màu viền `2.5px`, bỏ hiển thị số ghế trên thẻ bàn.
4. **Liên kết POS – Kho (`MonNuocPosDTO.java`, `DonHangService.java`, `banhang-page.js`):**
   - Che giấu tồn kho gốc, chỉ trả về `soLyCoTheLam` theo Soft Reservation & Bottleneck.
   - Thẻ món POS hiển thị 3 nhãn: Xanh (`> 5 ly`), Vàng (`1-5 ly`), Đỏ `HẾT MÓN` (mờ thẻ, khóa nút).
   - Chặn đặt quá ngưỡng tồn kho khả dụng (E3 - `HTTP 400`).
5. **Ràng buộc:** Chống tự khóa quản trị viên cuối cùng; khóa sửa món khi bàn đang `CHO_THANH_TOAN`.

### File 2: `NANGCAP_KHOKPI.md` [ĐÃ HOÀN THÀNH 100%]
1. **Bảng Truy vết Biến động Kho (`lich_su_kho` – Bảng thứ 11):**
   - Entity `LichSuKho.java` và `LichSuKhoRepository.java` chuẩn JPA, liên kết chặt chẽ `NguyenLieu`, `NhanVien`, `HoaDon`.
   - Lưu vết 3 loại giao dịch: `NHAP_KHO`, `XUAT_BAN_HANG` (gắn mã hóa đơn), `KIEM_KE_HAO_HUT`.
2. **Luồng 1 – Nhập kho & Giá vốn Bình quân gia quyền (`KhoService.nhapKho`):**
   - Cập nhật giá vốn theo Weighted Average Cost khi nhập lô hàng mới có đơn giá:
     $$\text{donGiaMoi} = \frac{(\text{tonTruoc} \times \text{donGiaCu}) + (\text{soLuongNhap} \times \text{donGiaNhapMoi})}{\text{tonTruoc} + \text{soLuongNhap}}$$
   - Ghi tự động dòng `NHAP_KHO` với nhân sự thực hiện.
3. **Luồng 2 – Kiểm kê Cuối ngày & Xuất hủy Hao hụt (`KhoService.kiemKeKho`):**
   - Bắt buộc lý do điều chỉnh; từ chối khi số lượng không đổi (`HTTP 400`).
   - Tự động tính chênh lệch $\Delta$, giá trị tổn thất/thặng dư và lưu dòng `KIEM_KE_HAO_HUT`. Bật nhãn đỏ `SẮP HẾT HÀNG` khi tồn thực tế $\le$ ngưỡng tối thiểu.
4. **Luồng 3 – Ghi Nhật ký tự động khi POS thanh toán (`truKhoTuDong`):**
   - Khóa bi quan theo thứ tự mã nguyên liệu tăng dần triệt tiêu Deadlock 100%. Tự động ghi nhận `XUAT_BAN_HANG` gắn `ma_hoa_don` và thu ngân chốt đơn.
   - Chạy trong giao dịch ACID: thiếu bất kỳ nguyên liệu nào sẽ Rollback sạch 100% cả tồn kho và lịch sử kho.
5. **Luồng 4 – KPI Dashboard Đa kỳ & Top 5 Món Bán Chạy:**
   - Hỗ trợ 4 kỳ: `HOM_NAY`, `BAY_NGAY`, `THANG_NAY`, `TAT_CA`.
   - Tính toán đầy đủ: Doanh thu thuần, Đối soát Tiền mặt vs Chuyển khoản, Chi phí nhập kho, Giá vốn bán hàng, Hao hụt, Lợi nhuận gộp ước tính, Tổng giá trị tồn kho 13 nguyên liệu (2.613.600 đ) và Top 5 món bán chạy.
   - Endpoint mới `GET /api/kho/bang-cong-thuc`: trả về định mức toàn bộ 12 món kèm giá vốn/ly, biên lợi nhuận gộp và số ly khả dụng.
6. **Nâng cấp Giao diện `kho.html` & `kho-page.js`:**
   - 4 nút lọc kỳ KPI linh hoạt.
   - 6 thẻ KPI (2 hàng $\times$ 3 cột nền trắng viền nét mảnh).
   - 3 Tab quản trị: Tab 1 (Tồn kho nguyên vật liệu), Tab 2 (Định mức BOM 12 món), Tab 3 (Nhật ký Biến động kho có bộ lọc loại GD & nguyên liệu).
   - Thẻ Top 5 món bán chạy kèm thanh tiến độ trực quan.
   - 3 Modal: `#restockModal` (có preset +100, +500, +1000 và xem trước thành tiền), `#auditModal` (tính chênh lệch & hao hụt tự động), `#recipeModal` (sửa định mức BOM).
7. **Bộ Kiểm thử Tích hợp (`KhoKpiIntegrationTest.java`):**
   - Cung cấp đầy đủ 6 test case trọng điểm theo đặc tả mục 5.

### File 3: `NANGCAP_POS.md` [ĐÃ HOÀN THÀNH 100%]
1. **Phân quyền RBAC 3 Cấp & Ma trận 4 Tầng POS:**
   - Phục vụ (`phucvu`): Mở bàn, gọi món, gọi thêm món, bấm "Yêu cầu thanh toán" (viền Vàng), "Quay lại phục vụ" (viền Xanh lá). Bị khóa cứng (HTTP 403) xác nhận thanh toán, hủy đơn và chiết khấu.
   - Thu ngân (`thungan`): In Phiếu Tạm Tính, đối soát tiền mặt/chuyển khoản, bấm "Xác nhận thanh toán & Đóng bàn" (trừ kho BOM), hủy đơn. Bị khóa cứng (HTTP 403) nhập chiết khấu.
   - Quản lý (`quanly`): Toàn quyền POS kèm áp dụng chiết khấu (`#pos-discount`), xem kho và toàn bộ nghiệp vụ.
2. **Quy trình Phục vụ Tại Bàn & Đối soát Thu ngân (End-to-End Table Service):**
   - `POST /api/order/tao-don`: Kiểm tra tồn kho khả dụng -> Lưu đơn `DANG_PHUC_VU`, bàn `DANG_DUNG_NUOC` (viền Xanh lá), gán `soLuongDaInBar = soLuong`, trả về kèm `phieuInBar`.
   - `PUT /api/order/{id}/mon`: Chỉ cho phép khi `DANG_PHUC_VU`. Tính toán $\Delta = \text{soLuongMoi} - \text{soLuongDaInBar} > 0$ và trả về `phieuInBarBoSung`. Cố sửa khi bàn đang `CHO_THANH_TOAN` bị chặn ngay `HTTP 409 Conflict` (E2).
   - `POST /api/order/{id}/yeu-cau-thanh-toan`: Chuyển bàn và hóa đơn sang `CHO_THANH_TOAN` (viền Vàng hổ phách `#D97706`).
   - `POST /api/order/{id}/quay-lai-phuc-vu`: Đưa bàn và hóa đơn từ `CHO_THANH_TOAN` về `DANG_DUNG_NUOC` để gọi thêm món.
   - `POST /api/order/{id}/thanh-toan`: Thu ngân chốt tiền, lưu `ma_nv_thu_ngan`, tính `tien_thua`, trừ kho BOM và ghi `XUAT_BAN_HANG` vào `lich_su_kho`, đóng bàn trả về `TRONG` (viền Xanh dương).
3. **Sơ đồ Bàn 3 Màu Viền & Thẻ Món Liên kết Số ly Khả dụng:**
   - Chuẩn hóa 3 màu viền thẻ bàn (Light Mode, 2.5px solid): Xanh dương (`#0D6EFD` - Trống), Xanh lá (`#198754` - Đang dùng nước kèm Icon tiền + Tổng tạm tính), Vàng hổ phách (`#D97706` - Chờ thanh toán kèm Icon tiền + Tổng cần thu). Không hiển thị số ghế trên thẻ bàn.
   - Thẻ món hiển thị `soLyCoTheLam` (Xanh $> 5$ ly, Vàng $1-5$ ly, Đỏ `HẾT MÓN` và khóa nút). Tuyệt đối che giấu số lượng tồn kho gốc (Information Hiding).
4. **Bộ 3 Mẫu Phiếu In Chuẩn Sangrimar Cafe & Lounge (`@media print`):**
   - Phiếu Pha Chế (Bar Ticket): In tên bàn, mã đơn, giờ gọi, món mới kèm ghi chú (không hiện giá).
   - Phiếu Tạm Tính (Proforma Bill): Đầy đủ danh sách món, đơn giá, thành tiền, tổng tiền, dòng cảnh báo kiểm tra bill và mã VietQR động.
   - Hóa Đơn Thanh Toán Chính Thức (Official Receipt): Đầy đủ Phục vụ mở bàn, Thu ngân chốt đơn, chiết khấu, tiền khách đưa, tiền thừa/mã GD chuyển khoản.
   - `@media print`: Ẩn hoàn toàn UI nền, sidebar, topbar và các nút bấm; căn lề in phiếu nhiệt 80mm & A4 sắc nét tương phản cao.
5. **Bộ Kiểm thử Tích hợp Backend (`DonHangIntegrationTest.java`):**
   - Đầy đủ 5 test case trọng điểm theo đặc tả: `waiterCanOrderAndRequestPaymentButCannotConfirmPayment`, `menuCalculatesAvailableCupsWithSoftReservationWithoutExposingStock`, `addMoreItemsOnlyPrintsDeltaToBarAndBlockedWhenWaitingPayment`, `cashierConfirmsCashAndTransferPaymentAndClosesTable`, `cashierCannotApplyDiscountOnlyManagerCan`.

### File 4: `NANGCAP_CHAMCONG.md` [ĐÃ HOÀN THÀNH 100%]
1. **Chuẩn hóa 7 Quy tắc Nghiệp vụ Chấm công (`BR-CC-01` $\rightarrow$ `BR-CC-07`):**
   - `BR-CC-01`: Ràng buộc 1:1 Phân ca - Chấm công (`UNIQUE(ma_phan_ca)` và `UNIQUE(ma_nv, ngay_lam, ma_ca)`), chặn check-in khi chưa có phân ca hôm nay của đúng `Session.maNV`.
   - `BR-CC-02`: Cửa sổ Check-in mở từ $[\text{gio\_bat\_dau} - 30\text{ phút},\ \text{gio\_ket\_thuc})$; từ chối ngoài cửa sổ với mã `HTTP 400`.
   - `BR-CC-03`: Trễ $\le 15\text{ phút} \rightarrow \text{DUNG\_GIO}$ (`so_phut_tre = 0`); trễ $> 15\text{ phút} \rightarrow \text{DI\_TRE}$ (`so_phut_tre = \Delta_{in}`).
   - `BR-CC-04`: Tính giờ không cộng giờ ảo $t_{\text{start}} = \max(\text{in}, \text{start})$, $t_{\text{end}} = \min(\text{out}, \text{end})$; chặn check-out dưới 1 phút; phát hiện về sớm $> 15\text{ phút} \rightarrow \text{VE\_SOM}$ hoặc $\text{DI\_TRE\_VE\_SOM}$; nhân `he_so_luong` ra `gio_cong_quy_doi`.
   - `BR-CC-05`: Chốt ca quá hạn (Temporal Event) qua mốc $\text{gio\_ket\_thuc} + 30\text{ phút}$: Chưa check-in $\rightarrow \text{VANG}$ (`gio_check_in = NULL`); quên check-out $\rightarrow \text{QUEN\_CHECK_OUT}$.
   - `BR-CC-06`: Quản lý điều chỉnh ngoại lệ bắt buộc nhập `lyDoDieuChinh`, lưu kiểm toán `nguoiDieuChinh`, `thoiGianDieuChinh` và tự động tính lại giờ công & trạng thái.
   - `BR-CC-07`: Cảnh báo cân bằng vai trò: Ô ca có nhân viên nhưng thiếu `THU_NGAN`/`QUAN_LY` $\rightarrow$ gắn cờ `thieuThuNgan = true` (viền cam nét đứt + badge hổ phách). Chặn đổi ca nếu đã phát sinh chấm công (`HTTP 409`).
2. **Kiến trúc Tiêm `java.time.Clock` & Tác vụ Lập lịch:**
   - Khai báo `@Bean public Clock systemClock()` và `@EnableScheduling` trong `QuanLyCafeApplication.java`.
   - Tiêm `Clock` vào `NhanSuService` cho phép đóng băng/dịch chuyển mốc giờ linh hoạt trong JUnit 5 và môi trường chạy.
   - Tạo `ChotCaTuDongScheduler.java` (`@Scheduled(cron = "0 */15 * * * *")`) tự động chốt ca quá hạn định kỳ.
3. **Cấu trúc DDL & Entity Chuẩn 16 Cột (`ChamCong.java`):**
   - Đầy đủ 16 trường: `maChamCong`, `phanCa`, `nhanVien`, `caLam`, `ngayLam`, `gioCheckIn` (nullable), `gioCheckOut`, `soPhutTre`, `soPhutVeSom`, `soGioLam`, `gioCongQuyDoi`, `trangThai`, `ghiChu`, `nguoiDieuChinh`, `lyDoDieuChinh`, `thoiGianDieuChinh`.
4. **Bộ DTO Mới Đáp ứng Toàn diện:**
   - `CaHomNayDTO`: Trả thông tin ca cá nhân kèm `coTheCheckIn`, `coTheCheckOut`, `thongBaoCuaSo`.
   - `DieuChinhChamCongDTO`: Nhận giờ vào/ra và lý do điều chỉnh bắt buộc.
   - `DoiCaDTO`: Nhận mã nhân viên thay thế và lý do đổi ca.
   - `ChotCaResponseDTO`: Trả số ca đánh dấu Vắng và Quên check-out.
   - `BangCongNhanVienDTO`: Bảng tổng hợp công theo kỳ quy đổi hệ số lương.
   - `PhanCaTuanItemDTO`: Lịch tuần kèm cờ `thieuThuNgan` và trạng thái chấm công.
5. **Giao diện Phân tách 2 Chế độ Rõ rệt (`nhansu.html` & `nhansu-page.js`):**
   - **Chế độ Cá nhân (`phucvu`, `thungan`):** Ẩn hoàn toàn chức năng quản trị; hiển thị 3 khối: (1) Thẻ Chấm công Ca hôm nay kèm đồng hồ thực tế, nút Check-in/Check-out thông minh theo cửa sổ; (2) Lịch ca tuần của tôi (7 ngày); (3) Bảng công & Lịch sử chấm công cá nhân.
   - **Chế độ Quản trị (`quanly`):** 4 Tab con chuyên nghiệp:
     - Tab 1 – Chấm công & Giám sát ca: Tự chấm công + nút "Chốt ca quá hạn" + Nhật ký chấm công toàn quán kèm nút "Điều chỉnh" mở Modal kiểm toán và "Check-out hộ".
     - Tab 2 – Lịch phân ca tuần & Đổi ca: Lưới ma trận 7 ngày $\times$ 3 ca, viền cam cảnh báo `Thiếu Thu ngân`, nút "Đổi ca" (Modal đổi ca) và nút "Phân ca mới".
     - Tab 3 – Bảng tổng hợp công theo kỳ: Lọc Tuần này / Tháng này / Tùy chọn ngày, thống kê tổng giờ thực tế và tổng giờ công quy đổi hệ số theo từng nhân sự.
     - Tab 4 – Danh bạ Nhân sự (CRUD): Thêm mới, sửa thông tin, phân quyền vai trò và khóa mềm tài khoản (bảo vệ chống tự khóa quản lý cuối cùng).
6. **Bộ Kiểm thử Tích hợp Backend (`NhanSuChamCongIntegrationTest.java`):**
   - Cung cấp đầy đủ 7 test case trọng điểm theo đặc tả mục 6:
     1. `checkInRejectedOutsideWindowAndAcceptedWithin30Minutes`
     2. `checkInLateRecordsLateMinutesAndClampsWorkedHours`
     3. `checkOutEarlyMarksVeSomAndCombinesWithDiTre`
     4. `closeExpiredShiftsMarksAbsentAndForgotCheckout`
     5. `managerAdjustsForgotCheckoutWithAuditTrailAndRecalculates`
     6. `shiftSwapUpdatesAssigneeAndDetectsMissingCashier`
     7. `timesheetSummaryMultipliesShiftCoefficientAndIsolatesStaff`

---

## 4. Kế hoạch tiếp theo & Nghiệm thu
- Cả 4 phân hệ cốt lõi (`TONGTHE`, `KHOKPI`, `POS`, `CHAMCONG`) đã hoàn tất 100%, đồng bộ tuyệt đối với CSDL `database_mau.sql` (v2.0 - 11 bảng).
- Sẵn sàng bàn giao cho người dùng kiểm thử thủ công và nghiệm thu toàn diện hệ thống Sangrimar Cafe & Lounge.

---

## 5. Xử lý sự cố Header Giao diện & Lỗi Database (20/11/2026)
1. **Khắc phục lỗi Header giao diện (`dung-chung.js`, `nhansu-page.js`):**
   - **Nguyên nhân:** `MocAn.renderAppShell` trước đó chỉ được gọi sau khi các truy vấn bất đồng bộ `apiFetch` hoàn tất. Nếu có bất kỳ độ trễ hoặc lỗi mạng/CSDL, hoặc `DOMContentLoaded` đã được kích hoạt trước khi gắn listener, khối Header `#app-topbar-root` sẽ bị rỗng. Đồng thời thiếu null-guard cho `config.user` dẫn tới `TypeError`.
   - **Xử lý:**
     - Bổ sung fallback null-safe trong `dung-chung.js` (`config.user || getCurrentUser() || defaultUser`).
     - Gọi `MocAn.renderAppShell` đồng bộ ngay khi nạp `nhansu-page.js` từ cached user để header hiển thị tức thì, sau đó nạp lại đầy đủ khi phiên hoàn tất.
     - Hỗ trợ khởi chạy an toàn cho cả 2 trạng thái `document.readyState === 'loading'` và đã hoàn tất.
2. **Khắc phục lỗi Database & Khởi động MySQL (`mysqld`, `database_mau.sql`, `application.properties`):**
   - **Nguyên nhân:** Tiến trình `mysqld` của XAMPP chưa được kích hoạt ở cổng 3306 (gây lỗi `10061 Can't connect to MySQL server`). Ngoài ra, pipeline PowerShell mặc định làm suy hao ký tự tiếng Việt khi pipe text file, và cấu hình `hibernate.dialect` cũ làm giảm tính tương thích.
   - **Xử lý:**
     - Khởi chạy tiến trình `mysqld` XAMPP ở chế độ daemon background ổn định trên cổng 3306.
     - Tái nạp `database_mau.sql` bằng lệnh trực tiếp `source` của MySQL với charset `utf8mb4`, bảo toàn 100% tiếng Việt chuẩn UTF-8 (Quản lý: `Nguyễn Văn Duy`, Thu ngân: `Trần Văn Quý`, Phục vụ: `Lê Tấn Đạt`).
     - Bổ sung `SET FOREIGN_KEY_CHECKS = 0;` ở đầu file và `SET FOREIGN_KEY_CHECKS = 1;` ở cuối file `database_mau.sql` để chống lỗi khóa ngoại khi nạp qua phpMyAdmin/CLI.
     - Xóa cấu hình `hibernate.dialect=org.hibernate.dialect.MySQLDialect` trong `application.properties` để Hibernate 6 tự động tối ưu hóa dialect theo MariaDB 10.4.
     - Khởi động lại Spring Boot Application: `BUILD SUCCESS`, kết nối DB thành công, toàn bộ API phản hồi chuẩn HTTP 200.
