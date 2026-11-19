# ĐẶC TẢ NÂNG CẤP PHÂN HỆ NHÂN SỰ, CA LÀM & CHẤM CÔNG (PHIÊN BẢN 2.0)

### KHÉP KÍN VÒNG ĐỜI NGHIỆP VỤ BA: PHÂN CA CÂN BẰNG VAI TRÒ, CHẤM CÔNG KIỂM SOÁT CỬA SỔ THỜI GIAN, XỬ LÝ NGOẠI LỆ & BẢNG CÔNG QUY ĐỔI

* **Thương hiệu chuẩn:** **Sangrimar Cafe & Lounge**
* **Học phần:** **Phân tích và Thiết kế Yêu cầu** (Mã môn: `502050`)
* **Tài liệu gốc đồng bộ:** `NANG_CAP_TONGTHE.md` & `database_mau.sql` (v2.0 – 11 bảng, đúng 3 tài khoản chuẩn: `quanly`, `thungan`, `phucvu`).
* **Trọng tâm Phân tích Thiết kế Yêu cầu (BA):**
  1. Giải quyết dứt điểm mâu thuẫn giữa phân quyền RBAC và quyền tự chấm công của nhân viên (`Chế độ Cá nhân` vs `Chế độ Quản trị`).
  2. Áp dụng đủ 3 loại sự kiện **Event Decomposition** (*External, Temporal, State*) và chuẩn hóa **7 Quy tắc nghiệp vụ (`BR-CC-01` $\rightarrow$ `BR-CC-07`)**.
  3. Xử lý trọn vẹn ngoại lệ thực tế: *Đổi ca đột xuất, Cảnh báo ca thiếu Thu ngân trực quầy, Quên Check-out, Về sớm, Chốt vắng mặt cuối ca*.
  4. Khép kín chuỗi giá trị bằng **Bảng tổng hợp công theo kỳ** khai thác cột `ca_lam.he_so_luong`.

---

## 1. PHÂN RÃ SỰ KIỆN & TỪ ĐIỂN 7 QUY TẮC NGHIỆP VỤ (`BR-CC`)

### 1.1. Bảng phân rã sự kiện nghiệp vụ (Event Decomposition)

| Mã SK | Tên sự kiện nghiệp vụ | Loại sự kiện | Nguồn kích hoạt | Phản ứng hệ thống | Use Case |
| :---: | :--- | :---: | :--- | :--- | :--- |
| **E-NS-01** | Quản lý lập lịch phân ca tuần | **External** | `QUAN_LY` | Lưu `phan_ca`, chặn trùng ca/nhân viên đã nghỉ, gắn cờ cảnh báo nếu ca thiếu `THU_NGAN`/`QUAN_LY`. | `UC08` |
| **E-NS-02** | Nhân viên xin đổi ca đột xuất | **External** | `QUAN_LY` | Chuyển suất `phan_ca` chưa chấm công sang người thay thế, lưu lý do đổi ca và kiểm tra lại cân bằng vai trò. | `UC08` |
| **E-NS-03** | Nhân viên đến quán vào ca | **External** | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Đối chiếu `phan_ca` hôm nay + cửa sổ Check-in; lưu `gio_check_in`, phân loại `DUNG_GIO`/`DI_TRE` kèm `so_phut_tre`. | `UC07` |
| **E-NS-04** | Nhân viên kết thúc ca ra về | **External** | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Lưu `gio_check_out`, tính `so_gio_lam` & `gio_cong_quy_doi`, phát hiện về sớm (`VE_SOM`/`DI_TRE_VE_SOM`). | `UC07` |
| **E-NS-05** | Qua mốc kết thúc ca $+ 30\text{ phút}$ | **Temporal** | `@Scheduled` hoặc `QUAN_LY` bấm Chốt ca | Chưa Check-in $\rightarrow$ sinh bản ghi `VANG` (`gio_check_in = NULL`); chưa Check-out $\rightarrow$ chuyển `QUEN_CHECK_OUT`. | `UC09` |
| **E-NS-06** | Phát hiện `QUEN_CHECK_OUT` / sai giờ | **State** | `QUAN_LY` | Điều chỉnh giờ vào/ra thực tế, bắt buộc nhập lý do, lưu `nguoi_dieu_chinh` và tính lại giờ công. | `UC09` |
| **E-NS-07** | Tổng hợp bảng công tuần/tháng | **External** | `QUAN_LY` *(toàn quán)* / `PHUC_VU`, `THU_NGAN` *(cá nhân)* | Tổng hợp số ca, vi phạm giờ giấc, giờ thực tế và giờ công quy đổi theo `he_so_luong`. | `UC10` |

### 1.2. Từ điển 7 Quy tắc Nghiệp vụ (`BR-CC-01` đến `BR-CC-07`)

| Mã quy tắc | Tên quy tắc | Định nghĩa chính xác & Công thức áp dụng |
| :---: | :--- | :--- |
| **`BR-CC-01`** | **Ràng buộc 1:1 Phân ca – Chấm công** | Chỉ được Check-in khi có lịch `phan_ca` hôm nay của đúng `Session.maNV`. Mỗi suất `phan_ca` tối đa 1 bản ghi `cham_cong` (`UNIQUE(ma_phan_ca)` và `UNIQUE(ma_nv, ngay_lam, ma_ca)`). |
| **`BR-CC-02`** | **Cửa sổ Check-in hợp lệ** | Mở từ $[\text{gio\_bat\_dau} - 30\text{ phút},\ \text{gio\_ket\_thuc})$. Ngoài khung giờ này $\rightarrow$ Từ chối (`HTTP 400`). |
| **`BR-CC-03`** | **Phân loại Đúng giờ / Đi trễ** | $\Delta_{\text{in}} = \text{minutesBetween}(\text{gio\_bat\_dau},\ \text{gio\_check\_in})$. Nếu $\Delta_{\text{in}} \le 15$ $\rightarrow$ `DUNG_GIO` (`so_phut_tre = 0`); nếu $\Delta_{\text{in}} > 15$ $\rightarrow$ `DI_TRE` (`so_phut_tre = ` $\Delta_{\text{in}}$). |
| **`BR-CC-04`** | **Kiểm soát Về sớm & Tính giờ không cộng giờ ảo** | $$t_{\text{bắt đầu}} = \max(\text{gio\_check\_in},\ \text{gio\_bat\_dau}), \qquad t_{\text{kết thúc}} = \min(\text{gio\_check\_out},\ \text{gio\_ket\_thuc})$$<br>$$\text{so\_gio\_lam} = \max\left(0,\ \frac{\text{minutesBetween}(t_{\text{bắt đầu}},\ t_{\text{kết thúc}})}{60.0}\right), \qquad \text{gio\_cong\_quy\_doi} = \text{so\_gio\_lam} \times \text{he\_so\_luong}$$<br>Nếu $\Delta_{\text{out}} = \text{minutesBetween}(\text{gio\_check\_out},\ \text{gio\_ket\_thuc}) > 15$ $\rightarrow$ `so_phut_ve_som = ` $\Delta_{\text{out}}$ và đổi trạng thái sang `VE_SOM` (hoặc `DI_TRE_VE_SOM`). Chặn Check-out nếu chưa đủ 1 phút sau Check-in. |
| **`BR-CC-05`** | **Chốt vắng mặt & Quên Check-out (Temporal Event)** | Qua mốc $\text{gio\_ket\_thuc} + 30\text{ phút}$: suất `phan_ca` không có `cham_cong` $\rightarrow$ chèn dòng `VANG` (`gio_check_in = NULL`, `so_gio_lam = 0`); bản ghi có `gio_check_in` nhưng `gio_check_out IS NULL` $\rightarrow$ chuyển `QUEN_CHECK_OUT`. |
| **`BR-CC-06`** | **Điều chỉnh ngoại lệ có kiểm toán** | Chỉ `QUAN_LY` được sửa giờ vào/ra; bắt buộc nhập `ly_do_dieu_chinh` không rỗng; lưu `nguoi_dieu_chinh`, `thoi_gian_dieu_chinh` và tự động tính lại toàn bộ giờ công & trạng thái. |
| **`BR-CC-07`** | **Cảnh báo cân bằng vai trò khi Phân/Đổi ca** | Ô ca có nhân viên nhưng không có ai mang vai trò `THU_NGAN` hoặc `QUAN_LY` $\rightarrow$ gắn cờ `thieuThuNgan = true` (nhãn cảnh báo cam *"Thiếu người chốt quầy"*). Chặn đổi ca nếu đã phát sinh `cham_cong`. |

---

## 2. ĐẶC TẢ USE CASE TRỌNG TÂM (FULLY-DRESSED TÓM LƯỢC 9 TRƯỜNG)

* **`UC07` – Chấm công Ca làm việc Cá nhân:** Tác nhân `PHUC_VU`/`THU_NGAN`/`QUAN_LY` mở `nhansu.html` (Chế độ Cá nhân), xem các suất ca hôm nay từ `GET /api/cham-cong/ca-hom-nay` (không có dropdown chọn tên người khác). Bấm **"Check-in Vào Ca"** (`POST /api/cham-cong/check-in`) và **"Check-out Ra Ca"** (`POST /api/cham-cong/check-out/{id}`). Hệ thống lấy `maNV` tuyệt đối từ `HttpSession`, kiểm tra `BR-CC-01`..`04`, trả `400` nếu ngoài cửa sổ hoặc chưa có lịch phân ca, trả `409` nếu Check-in đúp.
* **`UC08` – Lập lịch Phân ca Tuần & Đổi ca Đột xuất:** Tác nhân `QUAN_LY` xem lưới lịch 7 ngày $\times$ 3 ca (`GET /api/phan-ca/tuan`), thêm suất ca (`POST /api/phan-ca`) hoặc đổi nhân sự cho suất ca chưa chấm công (`PUT /api/phan-ca/{id}/doi-ca` kèm `lyDoDoiCa`). Chặn `409` nếu ca đã có `cham_cong` hoặc người thay thế trùng ca.
* **`UC09` – Chốt ca Vắng mặt & Điều chỉnh Ngoại lệ:** Tác vụ `@Scheduled` mỗi 15 phút hoặc `QUAN_LY` bấm **"Chốt ca quá hạn"** (`POST /api/cham-cong/chot-ca`) áp dụng `BR-CC-05`. Với dòng `QUEN_CHECK_OUT`/`VANG`, `QUAN_LY` gọi `PUT /api/cham-cong/{id}/dieu-chinh` có lý do bắt buộc (`BR-CC-06`).

---

## 3. CẤU TRÚC BẢNG `cham_cong` & DỮ LIỆU MẪU ĐỒNG BỘ (`database_mau.sql` v2.0)

Cấu trúc bảng `cham_cong` (16 cột đầy đủ `ma_phan_ca`, `so_phut_tre`, `so_phut_ve_som`, `so_gio_lam`, `gio_cong_quy_doi`, `nguoi_dieu_chinh`, `ly_do_dieu_chinh`, `thoi_gian_dieu_chinh`, cho phép `gio_check_in NULL` với trạng thái `VANG`) đã được định nghĩa hoàn chỉnh trong **`database_mau.sql` (v2.0)**.

### Dữ liệu mẫu đồng bộ theo đúng 3 nhân viên (`ma_nv = 1, 2, 3`):
* **Hôm qua (đã kết thúc — phục vụ demo Bảng công & Điều chỉnh ngoại lệ):**
  1. `thungan` (`ma_nv = 2`), Ca Sáng: `06:55` – `12:02` $\rightarrow$ **`DUNG_GIO`**, `5.00 giờ` $\times 1.0 =$ `5.00 giờ công`.
  2. `phucvu` (`ma_nv = 3`), Ca Sáng: `07:22` – `12:00` $\rightarrow$ **`DI_TRE`** ($22\text{ phút}$), `4.63 giờ` $\times 1.0 =$ `4.63 giờ công`.
  3. `phucvu` (`ma_nv = 3`), Ca Chiều: `11:58` – `NULL` $\rightarrow$ **`QUEN_CHECK_OUT`** *(dùng để `quanly` demo Điều chỉnh công)*.
  4. `thungan` (`ma_nv = 2`), Ca Tối: `NULL` – `NULL` $\rightarrow$ **`VANG`** *(minh họa Temporal Event)*.
* **Hôm nay:**
  5. `thungan` (`ma_nv = 2`), Ca Sáng: đã Check-in `06:57` (`DUNG_GIO`, đang làm việc).
  6. `phucvu` (`ma_nv = 3`), Ca Sáng: **chưa Check-in** *(dùng để `phucvu` demo bấm Check-in)*.
  7. `phucvu` (`ma_nv = 3`), Ca Chiều: **chỉ có 1 Phục vụ** *(kích hoạt cảnh báo `BR-CC-07` "Thiếu Thu ngân")*.
* **Ngày mai:**
  8. `phucvu` (`ma_nv = 3`), Ca Chiều: *(dùng để `quanly` demo Đổi ca sang `thungan` hoặc `quanly`)*.

---

## 4. THIẾT KẾ BACKEND SPRING BOOT & REST API

### 4.1. Nguyên tắc tiêm `java.time.Clock` để kiểm thử thời gian chính xác
* Khai báo `@Bean public Clock systemClock() { return Clock.systemDefaultZone(); }` trong `QuanLyCafeApplication`.
* Tiêm `Clock` vào `NhanSuService` thay vì gọi tĩnh `LocalDateTime.now()`, cho phép JUnit 5 dùng `Clock.fixed(...)` kiểm chứng mọi mốc giờ (`06:25`, `06:40`, `07:20`, `11:30`, `12:35`).
* Tạo `ChotCaTuDongScheduler` (`@EnableScheduling`, `@Scheduled(cron = "0 */15 * * * *")`) gọi `nhanSuService.chotCaQuaHan()`.

### 4.2. Đặc tả REST API Endpoints

| Method & Endpoint | Vai trò được phép | Chức năng & Quy tắc kiểm soát |
| :--- | :--- | :--- |
| `GET /api/cham-cong/ca-hom-nay` | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Lấy danh sách suất ca hôm nay của `Session.maNV` kèm `coTheCheckIn`, `coTheCheckOut`, `thongBaoCuaSo`. |
| `POST /api/cham-cong/check-in` | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Nhận `{ maPhanCa }`, lấy `maNV` từ Session; áp dụng `BR-CC-01`, `BR-CC-02`, `BR-CC-03`. |
| `POST /api/cham-cong/check-out/{id}` | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Nhân viên chỉ Check-out bản ghi của mình (`403` nếu khác); `QUAN_LY` được Check-out hộ. Áp dụng `BR-CC-04`. |
| `GET /api/cham-cong/lich-su?tuNgay=&denNgay=` | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | `PHUC_VU`/`THU_NGAN` chỉ nhận dữ liệu cá nhân; `QUAN_LY` xem toàn quán và lọc theo `maNV`/`trangThai`. |
| `POST /api/cham-cong/chot-ca` | **`QUAN_LY`** (`403` với còn lại) | Chạy thủ công tác vụ chốt ca quá hạn (`BR-CC-05`), trả `{ soCaDanhDauVang, soCaQuenCheckOut }`. |
| `PUT /api/cham-cong/{id}/dieu-chinh` | **`QUAN_LY`** (`403` với còn lại) | Nhận `{ gioCheckIn, gioCheckOut, lyDoDieuChinh }` (`BR-CC-06`), tính lại giờ công và lưu vết kiểm toán. |
| `GET /api/cham-cong/bang-cong?tuNgay=&denNgay=` | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Trả `List<BangCongNhanVienDTO>` quy đổi theo `he_so_luong`. Nhân viên chỉ thấy dòng của chính mình. |
| `GET /api/phan-ca/tuan?ngayBatDau=` | `PHUC_VU`, `THU_NGAN`, `QUAN_LY` | Trả lịch tuần kèm cờ cảnh báo `thieuThuNgan` (`BR-CC-07`). Nhân viên chỉ thấy lịch của bản thân. |
| `PUT /api/phan-ca/{maPhanCa}/doi-ca` | **`QUAN_LY`** (`403` với còn lại) | Nhận `{ maNVMoi, lyDoDoiCa }`, đổi nhân sự cho suất ca chưa phát sinh chấm công. |

---

## 5. GIAO DIỆN PHÂN TÁCH 2 CHẾ ĐỘ (`nhansu.html` – LIGHT MODE)

* **Với `PHUC_VU` và `THU_NGAN` (Chế độ Cá nhân — Tab Topbar tên *"Chấm công & Lịch ca"*):**
  * Ẩn hoàn toàn danh bạ nhân viên, nút `+ Thêm nhân viên`, xếp/đổi ca, chốt ca, điều chỉnh công.
  * Hiển thị 3 khối gọn gàng cho chính tài khoản đang đăng nhập: (1) Thẻ Chấm công Ca hôm nay + Đồng hồ thực tế, (2) Lịch ca tuần của tôi, (3) Bảng công & Lịch sử chấm công cá nhân.
* **Với `QUAN_LY` (Chế độ Quản trị — Tab Topbar tên *"Nhân sự & Chấm công"*, 4 tab con):**
  * *Tab 1 – Chấm công & Giám sát ca:* Tự chấm công + nút **"Chốt ca quá hạn"** + Lịch sử toàn quán kèm nút **"Điều chỉnh"** cho dòng `QUEN_CHECK_OUT`/`VANG`.
  * *Tab 2 – Lịch phân ca tuần & Đổi ca:* Lưới 7 ngày $\times$ 3 ca, viền cam cảnh báo **"Thiếu Thu ngân"** tại ô Ca Chiều hôm nay, nút **"Đổi ca"** trên từng suất ca.
  * *Tab 3 – Bảng tổng hợp công theo kỳ:* Lọc *Tuần này / Tháng này / Tùy chọn ngày*, hiển thị đủ tổng giờ làm thực tế và **Tổng giờ công quy đổi hệ số**.
  * *Tab 4 – Danh bạ Nhân sự (CRUD):* Quản lý nhân viên, gán vai trò `QUAN_LY`/`THU_NGAN`/`PHUC_VU` (chặn tự xóa tài khoản quản lý cuối cùng).

---

## 6. KẾ HOẠCH KIỂM THỬ & DEMO (`NhanSuChamCongIntegrationTest.java`)

1. `checkInRejectedOutsideWindowAndAcceptedWithin30Minutes` (`BR-CC-02`): `06:25` $\rightarrow$ `400`; `06:40` $\rightarrow$ `200 DUNG_GIO`.
2. `checkInLateRecordsLateMinutesAndClampsWorkedHours` (`BR-CC-03`, `BR-CC-04`): Check-in `07:20`, Check-out `12:10` $\rightarrow$ `DI_TRE`, `soPhutTre = 20`, `soGioLam = 4.67`.
3. `checkOutEarlyMarksVeSomAndCombinesWithDiTre` (`BR-CC-04`): Check-in `06:50`, Check-out `11:30` $\rightarrow$ `VE_SOM`, `soPhutVeSom = 30`, `soGioLam = 4.50`.
4. `closeExpiredShiftsMarksAbsentAndForgotCheckout` (`BR-CC-05`): Sau cuối ca $+30\text{ phút}$ sinh `VANG` và `QUEN_CHECK_OUT`.
5. `managerAdjustsForgotCheckoutWithAuditTrailAndRecalculates` (`BR-CC-06`): `quanly` điều chỉnh bản ghi #3 (`phucvu`, Ca Chiều hôm qua) sang `17:00` kèm lý do $\rightarrow$ lưu `nguoiDieuChinh = 1`, `soGioLam = 5.00`, `gioCongQuyDoi = 5.00`.
6. `shiftSwapUpdatesAssigneeAndDetectsMissingCashier` (`BR-CC-07`): Đổi ca ngày mai (`ma_phan_ca = 8`) từ `phucvu` sang `thungan` $\rightarrow$ `200 OK`; lịch tuần báo `thieuThuNgan = true` tại Ca Chiều hôm nay.
7. `timesheetSummaryMultipliesShiftCoefficientAndIsolatesStaff`: Ca Tối (`he_so_luong = 1.2`) làm $5.0\text{ giờ}$ tính đúng `6.0 giờ công`; `phucvu` gọi `/bang-cong` chỉ thấy dòng của chính mình và nhận `403` khi gọi `/chot-ca` hay `/dieu-chinh`.