# TỔNG QUAN BỐI CẢNH DỰ ÁN & HỒ SƠ HỆ THỐNG
## SANGRIMAR CAFE & LOUNGE v2.0
*(Tài liệu Tóm tắt Bối cảnh Cốt lõi, Toàn cảnh Nâng cấp, Kiến trúc, Nghiệp vụ & Bàn giao Hệ thống)*

> **Đơn vị đào tạo:** Trường Đại học Tôn Đức Thắng – Khoa Công nghệ Thông tin  
> **Môn học:** Phân tích Thiết kế Yêu cầu (PTTKYC)  
> **Thư mục làm việc:** `d:\3.1. PTTKYC\CK`  
> **Phiên bản hệ thống:** v2.0 (Bản nâng cấp toàn diện tháng 11/2026 – Sản phẩm hoàn chỉnh)  
> **Thời điểm cập nhật:** 20/11/2026  

---

## MỤC LỤC TỔNG QUAN
1. [Thông tin Đồ án & Thành viên thực hiện](#1-thông-tin-đồ-án--thành-viên-thực-hiện)
2. [Bối cảnh Doanh nghiệp & Bài toán F&B Thực tế](#2-bối-cảnh-doanh-nghiệp--bài-toán-fb-thực-tế)
3. [Tổng hợp Toàn diện 4 Phân hệ Nâng cấp Cốt lõi](#3-tổng-hợp-toàn-diện-4-phân-hệ-nâng-cấp-cốt-lõi)
   - 3.1. Phân hệ 1: Bảo mật, Trải nghiệm & Phân quyền RBAC (Tổng thể)
   - 3.2. Phân hệ 2: Quản trị Kho, Định mức BOM & KPI Điều hành
   - 3.3. Phân hệ 3: Bán hàng POS, Điều phối Bàn & Bộ 3 Phiếu In Chuẩn
   - 3.4. Phân hệ 4: Quản trị Nhân sự, Phân ca & 7 Quy tắc Chấm công
4. [Kiến trúc Phần mềm & Mô hình Cơ sở Dữ liệu 11 Bảng](#4-kiến-trúc-phần-mềm--mô-hình-cơ-sở-dữ-liệu-11-bảng)
5. [Hệ thống Biểu đồ Thiết kế & Tài liệu UML Đồng bộ](#5-hệ-thống-biểu-đồ-thiết-kế--tài-liệu-uml-đồng-bộ)
6. [Danh mục Bộ Tài liệu Bàn giao Sau Dọn dẹp](#6-danh-mục-bộ-tài-liệu-bàn-giao-sau-dọn-dẹp)
7. [Hướng dẫn Khởi chạy Hệ thống & Kịch bản Nghiệm thu Kiểm thử](#7-hướng-dẫn-khởi-chạy-hệ-thống--kịch-bản-nghiệm-thu-kiểm-thử)

---

## 1. THÔNG TIN ĐỒ ÁN & THÀNH VIÊN THỰC HIỆN

### 1.1. Thông tin Đồ án
- **Tên đề tài:** Hệ thống Quản lý Quán Cà phê Sangrimar Cafe & Lounge v2.0.
- **Mã lớp học phần:** 502050 – Phân tích Thiết kế Yêu cầu.
- **Giảng viên hướng dẫn:** TS. Hồ Thị Linh.
- **Mục tiêu dự án:** Xây dựng phần mềm quản lý toàn diện ngành F&B (Mô hình Dining & Lounge) đáp ứng trọn vẹn cả 3 khía cạnh: (1) Chu trình nghiệp vụ vận hành thực tế không lỗ hổng; (2) Kiến trúc phần mềm chuẩn mực Clean Architecture trên nền Java Spring Boot 3; (3) Bộ hồ sơ phân tích thiết kế, kịch bản thuyết trình và báo cáo đồ án quy chuẩn Khoa CNTT.

### 1.2. Phân công Vai trò Thành viên Nhóm

| STT | Họ và Tên | Mã số Sinh viên | Vai trò Kỹ thuật | Trọng tâm Nghiệp vụ đảm nhiệm | Tỷ lệ đóng góp |
| :---: | :--- | :---: | :--- | :--- | :---: |
| 1 | **Nguyễn Văn Duy** | **52400010** | **Nhóm trưởng / Fullstack Developer** | Phân hệ Quản trị Kho, Định mức pha chế (BOM), Giá vốn WAC, Sổ cái biến động kho, Kiểm kê hao hụt & KPI Dashboard điều hành. | 100% |
| 2 | **Trần Văn Quý** | **52400007** | **Backend Developer / BA** | Phân hệ Bán hàng POS, Chu trình SOP phục vụ tại bàn, Sơ đồ bàn 3 màu viền, In Bar Delta, Phiếu tạm tính & Bộ 3 mẫu phiếu in nghiệp vụ. | 100% |
| 3 | **Lê Tấn Đạt** | **5240105** | **Fullstack Developer / QA** | Phân hệ Quản trị Nhân sự, Lưới phân ca tuần, 7 Quy tắc Chấm công (BR-CC-01 -> BR-CC-07), Bộ đếm Clock, Tác vụ ngầm Scheduler & Báo cáo kiểm thử. | 100% |

---

## 2. BỐI CẢNH DOANH NGHIỆP & BÀI TOÁN F&B THỰC TẾ

### 2.1. Mô hình Kinh doanh Sangrimar Cafe & Lounge
Sangrimar Cafe & Lounge vận hành theo mô hình **Full-service Table Dining kết hợp Quầy Bar & Lounge cao cấp**. Không giống các quầy take-away hay Fast-casual thông thường (nơi khách trả tiền trước tại quầy rồi nhận đồ uống), mô hình Dining & Lounge có đặc thù:
- Khách hàng ngồi tại bàn, được phục vụ tại chỗ theo nhiều giai đoạn (Gọi món ban đầu $\rightarrow$ Nhâm nhi $\rightarrow$ Gọi thêm món $\rightarrow$ Yêu cầu tính tiền $\rightarrow$ Đối soát hóa đơn $\rightarrow$ Thanh toán).
- Quầy Bar tiếp nhận yêu cầu pha chế theo thời gian thực và pha chế theo từng đợt gọi.
- Két thu ngân đối soát chặt chẽ giữa Tiền mặt và Chuyển khoản QR động.

### 2.2. Bốn Điểm nghẽn Nghiệp vụ Nghiêm trọng (*As-Is*) & Giải pháp Chuyển đổi Số (*To-Be*)

```
+---------------------------------------------------------------------------------------------------+
|                        4 ĐIỂM NGHẼN NGHIỆP VỤ F&B & GIẢI PHÁP SỐ HÓA                             |
+---------------------------------------------------------------------------------------------------+
| 1. LỆCH SỔ KHO & THẤT THOÁT NGUYÊN LIỆU:                                                         |
|    - As-Is: Cuối tháng mới đếm kho; không rõ hao hụt do pha hỏng, đổ vỡ hay nhân viên biển thủ. |
|    - To-Be: Tự động trừ kho theo định mức BOM nguyên tử; Sổ cái lich_su_kho truy vết 3 loại GD;   |
|             Cập nhật giá vốn bình quân gia quyền (WAC); Kiểm kê hao hụt bắt buộc lý do.          |
+---------------------------------------------------------------------------------------------------+
| 2. GIAN LẬN HÓA ĐƠN & THẤT THOÁT TIỀN MẶT:                                                        |
|    - As-Is: Nhân viên tự hủy bill sau khi khách trả tiền mặt; áp mã giảm giá khống để đút túi.   |
|    - To-Be: Quy trình Dual Control (Phục vụ không thể thanh toán; Thu ngân không thể sửa giảm giá;|
|             Chỉ Quản lý được áp chiết khấu); In Phiếu Tạm Tính bắt buộc trước khi thu tiền;       |
|             Trạng thái bàn chuyển từ Đang dùng nước -> Chờ thanh toán -> Đóng bàn.               |
+---------------------------------------------------------------------------------------------------+
| 3. XUNG ĐỘT QUẦY BAR KHI KHÁCH GỌI THÊM MÓN:                                                      |
|    - As-Is: Gọi thêm 1 ly thì quầy Bar pha lại toàn bộ 3 ly cũ do phiếu in lại toàn bộ đơn.      |
|    - To-Be: Cơ chế In Bar Delta (Delta = Số mới - Số đã in Bar), chỉ in món mới phát sinh;       |
|             Cơ chế Soft Reservation che giấu tồn kho thô, chỉ hiện "Số ly có thể pha" khả dụng.  |
+---------------------------------------------------------------------------------------------------+
| 4. CHẤM CÔNG ẢO & GIAN LẬN GIỜ LÀM VIỆC:                                                          |
|    - As-Is: Nhân viên điểm danh hộ, chấm công ngoài ca, làm tròn giờ có lợi cho bản thân.        |
|    - To-Be: 7 Quy tắc Chấm công (BR-CC-01 -> BR-CC-07); Cửa sổ check-in mở trước ca 30 phút;     |
|             Chốt ca tự động ngầm qua Scheduler (đánh dấu VẮNG / QUÊN CHECK-OUT khi hết ca);       |
|             Quản lý điều chỉnh ngoại lệ bắt buộc lưu vết kiểm toán (Người sửa, Lý do, Giờ sửa).  |
+---------------------------------------------------------------------------------------------------+
```

---

## 3. TỔNG HỢP TOÀN DIỆN 4 PHÂN HỆ NÂNG CẤP CỐT LÕI
*(Toàn bộ tinh hoa nghiệp vụ và kỹ thuật từ 4 tài liệu nâng cấp `NANGCAP_TONGTHE.md`, `NANGCAP_KHOKPI.md`, `NANGCAP_POS.md`, `NANGCAP_CHAMCONG.md` đã được triển khai hoàn tất và tổng hợp trọn vẹn tại đây)*

### 3.1. Phân hệ 1: Nâng cấp Tổng thể, Bảo mật & Phân quyền RBAC
1. **Phân quyền RBAC 3 Vai trò / 3 Cấp bậc truy cập:**
   - `QUAN_LY`: Toàn quyền hệ thống, quản trị kho hàng, điều chỉnh BOM, xem KPI tài chính, phân ca nhân sự, duyệt sửa công, áp dụng chiết khấu POS (Trang đích: `kho.html`).
   - `THU_NGAN`: Tiếp nhận yêu cầu thanh toán, in Phiếu Tạm Tính, xác nhận thu tiền mặt/chuyển khoản, in Hóa đơn chính thức, hủy đơn có kiểm soát. Bị khóa cứng (HTTP 403) nhập chiết khấu và quản trị kho (Trang đích: `banhang.html`).
   - `PHUC_VU`: Mở bàn, gọi món, gọi thêm món, gửi lệnh in Bar, gửi yêu cầu thanh toán, quay lại phục vụ. Bị khóa cứng (HTTP 403) xác nhận thanh toán, hủy đơn và chiết khấu (Trang đích: `banhang.html`).
2. **Kiến trúc Bảo mật Đăng nhập:**
   - Đăng nhập 1-chạm (Quick-fill Buttons) trên giao diện `index.html` phục vụ demo và bảo vệ chấm thi nhanh chóng.
   - Cơ chế Brute-force Throttling: Đăng nhập sai 5 lần liên tiếp sẽ tự động khóa tài khoản trong 60 giây (trả về mã chuẩn `HTTP 429 Too Many Requests`).
   - Quản lý phiên Session Auth: Tự động bắt mã `HTTP 401 Unauthorized` để xóa cache cục bộ và điều hướng về `index.html?expired=1`.
   - Bảo vệ an toàn tài khoản: Chống tự khóa tài khoản quản trị viên cuối cùng trong hệ thống.
3. **Chuẩn hóa Giao diện Light Mode Hiện đại:**
   - Thiết kế chuẩn CSS `:root` Light Mode thanh lịch, độ tương phản cao, tối ưu tuyệt đối cho máy in nhiệt K80 và in ấn tài liệu A4.
   - Sơ đồ bàn chuẩn hóa 3 màu viền nét mảnh (`2.5px solid`), loại bỏ số ghế thừa, tập trung vào trạng thái vận hành và doanh thu tích lũy.

### 3.2. Phân hệ 2: Quản trị Kho, Định mức BOM & KPI Điều hành
1. **Sổ cái Biến động Kho (`lich_su_kho` – Bảng thứ 11 trong CSDL):**
   - Entity `LichSuKho.java` và `LichSuKhoRepository.java` chuẩn JPA, liên kết khóa ngoại với `NguyenLieu`, `NhanVien`, `HoaDon`.
   - Ghi nhận nhật ký bất biến (Immutable Audit Log) cho 3 loại giao dịch:
     - `NHAP_KHO`: Nhập bổ sung nguyên liệu từ nhà cung cấp.
     - `XUAT_BAN_HANG`: Tự động trừ kho khi POS chốt hóa đơn (gắn liền với `ma_hoa_don` và thu ngân thực hiện).
     - `KIEM_KE_HAO_HUT`: Điều chỉnh kho cuối ngày do hư hỏng, đổ vỡ, sai lệch số cân.
2. **Luồng Nhập kho & Giá vốn Bình quân Gia quyền (Weighted Average Cost - WAC):**
   - Mỗi lần nhập lô hàng mới có đơn giá khác nhau, hệ thống tự động tính lại đơn giá vốn bình quân theo công thức chuẩn kế toán tài chính:
     $$\text{donGiaMoi} = \frac{(\text{tonTruoc} \times \text{donGiaCu}) + (\text{soLuongNhap} \times \text{donGiaNhapMoi})}{\text{tonTruoc} + \text{soLuongNhap}}$$
   - Đảm bảo giá trị hàng tồn kho và giá vốn hàng bán (COGS) luôn phản ánh chính xác chi phí thực tế.
3. **Luồng Trừ kho Tự động Nguyên tử & Chống Deadlock Triệt để:**
   - Khi thu ngân bấm xác nhận thanh toán, hệ thống truy xuất công thức BOM của toàn bộ các món trong đơn hàng.
   - **Thuật toán sắp xếp khóa tài nguyên:** Các nguyên liệu cần trừ được tập hợp vào một cấu trúc `TreeMap<Long, Double>` tự động sắp xếp theo `ma_nguyen_lieu` tăng dần. Khi thực thi khóa bi quan `PESSIMISTIC_WRITE`, các luồng giao dịch đồng thời luôn khóa tài nguyên theo đúng một thứ tự duy nhất, triệt tiêu 100% nguy cơ Deadlock chu trình.
   - Giao dịch thực thi dưới annotation `@Transactional`: Nếu thiếu bất kỳ nguyên liệu nào hoặc vi phạm ràng buộc, toàn bộ lệnh trừ kho và ghi sổ cái sẽ tự động Rollback sạch sẽ.
4. **Luồng Kiểm kê Hao hụt Cuối ngày:**
   - Quản lý nhập số lượng thực tế đếm được tại quầy. Bắt buộc nhập `lyDoDieuChinh`; từ chối nếu số tồn không đổi (`HTTP 400`).
   - Hệ thống tự động tính chênh lệch $\Delta = \text{tonThucTe} - \text{tonHeThong}$, quy đổi ra giá trị tổn thất/thặng dư và lưu dòng nhật ký `KIEM_KE_HAO_HUT`.
5. **Hệ thống Báo cáo KPI Đa kỳ & Menu Engineering:**
   - Bộ lọc linh hoạt 4 kỳ: Hôm nay (`HOM_NAY`), 7 ngày gần nhất (`BAY_NGAY`), Tháng này (`THANG_NAY`), Toàn thời gian (`TAT_CA`).
   - Tính toán đầy đủ 6 chỉ số tài chính trọng yếu: Doanh thu thuần, Đối soát Doanh thu Tiền mặt vs Chuyển khoản QR, Tổng chi phí nhập kho, Giá vốn hàng bán (COGS), Giá trị hao hụt kiểm kê, Lợi nhuận gộp ước tính và Tổng giá trị tồn kho 13 nguyên liệu (giá trị cơ sở: 2.613.600 đ).
   - Biểu đồ Top 5 món bán chạy nhất (Best-sellers) kèm tỷ trọng đóng góp và thanh tiến độ trực quan.
   - Endpoint `GET /api/kho/bang-cong-thuc`: Cung cấp định mức chi tiết 12 món nước, giá vốn/ly, giá bán, biên lợi nhuận gộp và số ly khả dụng theo thời gian thực.

### 3.3. Phân hệ 3: Bán hàng POS, Điều phối Bàn & Bộ 3 Phiếu In Chuẩn
1. **Chu trình Chuẩn SOP 9 Bước Phục vụ Tại Bàn (End-to-End Table Service):**
   - **Bước 1:** Khách đến quán, Phục vụ hướng dẫn vào bàn trống $\rightarrow$ Chọn bàn trên POS (Bàn màu Xanh dương `TRONG`).
   - **Bước 2:** Phục vụ chọn món theo thực đơn thông minh $\rightarrow$ Bấm "Mở bàn / Gọi món" (`POST /api/order/tao-don`). Hệ thống chuyển bàn sang màu Xanh lá (`DANG_DUNG_NUOC`), tạo đơn hàng ở trạng thái `DANG_PHUC_VU`, gán `soLuongDaInBar = soLuong`.
   - **Bước 3:** Hệ thống sinh Phiếu Pha Chế (Bar Ticket) gửi về quầy Bar.
   - **Bước 4:** Khách thưởng thức đồ uống tại bàn.
   - **Bước 5 (Gọi thêm):** Khách gọi thêm món $\rightarrow$ Phục vụ mở lại đơn hàng, thêm món mới $\rightarrow$ Bấm "Gửi Bar món mới" (`PUT /api/order/{id}/mon`).
   - **Bước 6 (In Bar Delta):** Hệ thống tính toán $\Delta = \text{soLuongMoi} - \text{soLuongDaInBar}$. Chỉ những món có $\Delta > 0$ mới xuất hiện trên Phiếu In Bar Bổ Sung. Cập nhật `soLuongDaInBar = soLuongMoi`.
   - **Bước 7 (Yêu cầu thanh toán):** Khách báo tính tiền $\rightarrow$ Phục vụ bấm "Yêu cầu thanh toán" (`POST /api/order/{id}/yeu-cau-thanh-toan`). Bàn chuyển sang màu Vàng hổ phách (`CHO_THANH_TOAN`), đơn hàng khóa tính năng gọi thêm món thông thường (chặn `HTTP 409` nếu cố ý chỉnh sửa).
   - **Bước 8 (Đối soát & In Tạm Tính):** Thu ngân in Phiếu Tạm Tính (Proforma Bill) kèm mã VietQR động mang ra cho khách kiểm tra. Nếu khách muốn ngồi tiếp gọi thêm món, Phục vụ có thể bấm "Quay lại phục vụ" để mở lại bàn về màu Xanh lá.
   - **Bước 9 (Xác nhận & Đóng bàn):** Khách thanh toán $\rightarrow$ Thu ngân chọn phương thức (Tiền mặt / Chuyển khoản), bấm "Xác nhận thanh toán & Đóng bàn" (`POST /api/order/{id}/thanh-toan`). Hệ thống ghi nhận mã thu ngân, tiền thừa, trừ kho BOM nguyên tử, ghi sổ cái `lich_su_kho`, chuyển đơn hàng sang `DA_THANH_TOAN` và trả bàn về màu Xanh dương `TRONG`.
2. **Sơ đồ Bàn 3 Màu Viền & Thông tin Trực quan:**
   - **Xanh dương (`#0D6EFD` - TRỐNG):** Bàn sẵn sàng đón khách mới.
   - **Xanh lá (`#198754` - ĐANG DÙNG NƯỚC):** Bàn đang phục vụ, hiển thị thời gian mở bàn, icon đồ uống và tổng tiền tạm tính tích lũy.
   - **Vàng hổ phách (`#D97706` - CHỜ THANH TOÁN):** Bàn đang đợi thu ngân thu tiền, hiển thị icon tiền tệ và tổng tiền cần thu.
3. **Cơ chế Thực đơn Thông minh Soft Reservation & Bottleneck:**
   - Che giấu hoàn toàn số lượng tồn kho nguyên liệu thô đối với nhân viên phục vụ (Information Hiding).
   - Hệ thống tự động tính toán số ly có thể pha (`soLyCoTheLam`) của từng món dựa trên nguyên liệu khan hiếm nhất trong BOM (Bottleneck Principle), trừ đi lượng nguyên liệu đã được giữ chỗ tạm thời (Soft Reserved) bởi các bàn đang mở phục vụ khác.
   - Thẻ món POS hiển thị 3 nhãn màu trực quan: Xanh lá (`> 5 ly` - Sẵn sàng phục vụ), Vàng (`1-5 ly` - Sắp hết món), Đỏ (`HẾT MÓN` - mờ thẻ món và khóa nút bấm đặt món). Chặn đặt quá số ly khả dụng (`HTTP 400`).
4. **Bộ Ba Mẫu Phiếu In Chuẩn Nghiệp vụ Sangrimar Cafe & Lounge (`@media print`):**
   - **Phiếu Pha Chế (Bar Ticket):** Khổ 80mm, hiển thị tên bàn, mã đơn, thời gian gọi, danh sách món mới phát sinh kèm ghi chú độ ngọt/đá; hoàn toàn không hiển thị đơn giá để tối ưu thao tác quầy Bar.
   - **Phiếu Tạm Tính (Proforma Bill):** Khổ 80mm, hiển thị chi tiết tên món, số lượng, đơn giá, tổng tiền, lời nhắc kiểm tra bill trước khi trả tiền và mã VietQR động chứa số tiền chính xác.
   - **Hóa Đơn Thanh Toán Chính Thức (Official Receipt):** Khổ 80mm / A4, hiển thị đầy đủ thông tin thương hiệu, mã hóa đơn, nhân viên phục vụ mở bàn, thu ngân chốt đơn, chiết khấu, tổng thanh toán, tiền khách đưa, tiền thừa thối lại và mã giao dịch chuyển khoản ngân hàng.
   - Tối ưu CSS `@media print`: Ẩn 100% thanh điều hướng, nút bấm, nền giao diện; đảm bảo độ tương phản đen trắng sắc nét khi in qua máy in nhiệt K80/K58.

### 3.4. Phân hệ 4: Quản trị Nhân sự, Phân ca & 7 Quy tắc Chấm công
1. **Chuẩn hóa 7 Quy tắc Nghiệp vụ Chấm công (`BR-CC-01` $\rightarrow$ `BR-CC-07`):**
   - `BR-CC-01` (Ràng buộc Phân ca 1:1): Mỗi nhân viên chỉ được chấm công đúng 1 lần cho 1 ca phân công trong ngày (`UNIQUE(ma_phan_ca)` và `UNIQUE(ma_nv, ngay_lam, ma_ca)`). Chặn chấm công nếu chưa có lịch phân ca hôm nay của đúng `Session.maNV`.
   - `BR-CC-02` (Cửa sổ Check-in hợp lệ): Cửa sổ điểm danh mở từ $[\text{gio\_bat\_dau} - 30\text{ phút},\ \text{gio\_ket\_thuc})$. Từ chối mọi yêu cầu check-in ngoài cửa sổ này với mã `HTTP 400 Bad Request`.
   - `BR-CC-03` (Phân loại Đi trễ theo ngưỡng 15 phút): Nhân viên check-in trễ $\le 15\text{ phút} \rightarrow$ Ghi nhận `DUNG_GIO` (`so_phut_tre = 0`); check-in trễ $> 15\text{ phút} \rightarrow$ Ghi nhận `DI_TRE` (`so_phut_tre = \Delta_{in}`).
   - `BR-CC-04` (Quy đổi Giờ công thực tế & Về sớm): Tuyệt đối không cộng giờ ảo: $t_{\text{start}} = \max(\text{in}, \text{start})$, $t_{\text{end}} = \min(\text{out}, \text{end})$. Chặn check-out dưới 1 phút. Check-out sớm $> 15\text{ phút} \rightarrow$ Chuyển trạng thái sang `VE_SOM` hoặc `DI_TRE_VE_SOM`. Nhân với `he_so_luong` của ca làm (ví dụ Ca Tối hệ số 1.2) để ra `gio_cong_quy_doi`.
   - `BR-CC-05` (Chốt ca quá hạn Temporal Event): Tiến trình ngầm quét tự động sau mốc $\text{gio\_ket\_thuc} + 30\text{ phút}$: Nếu ca chưa có ai check-in $\rightarrow$ Tự động đánh dấu `VANG` (`gio_check_in = NULL`, `so_gio_lam = 0`); Nếu đã check-in nhưng quên check-out $\rightarrow$ Đánh dấu `QUEN_CHECK_OUT` (`gio_check_out = NULL`, `so_gio_lam = 0`).
   - `BR-CC-06` (Điều chỉnh Ngoại lệ có Lưu vết Kiểm toán): Quản lý duyệt điều chỉnh công bắt buộc nhập `lyDoDieuChinh`. Hệ thống lưu vết `nguoiDieuChinh`, `thoiGianDieuChinh` và tự động tính toán lại số phút trễ, giờ công quy đổi và cập nhật trạng thái hợp lệ.
   - `BR-CC-07` (Cảnh báo Cân bằng Vai trò Lịch tuần): Khi một ô ca làm việc có nhân viên nhưng thiếu vai trò `THU_NGAN` hoặc `QUAN_LY` $\rightarrow$ Tự động gắn cờ `thieuThuNgan = true` (Hiển thị viền cam nét đứt và badge cảnh báo trên UI phân ca). Chặn đổi ca/xóa ca nếu đã phát sinh chấm công thực tế (`HTTP 409 Conflict`).
2. **Kiến trúc Tiêm `java.time.Clock` & Tác vụ Lập lịch `ChotCaTuDongScheduler`:**
   - Khai báo `@Bean public Clock systemClock()` và `@EnableScheduling` trong `QuanLyCafeApplication.java`.
   - Tiêm Bean `Clock` vào toàn bộ tầng `NhanSuService`, cho phép đóng băng thời gian hoặc giả lập mốc giờ tùy ý trong các bài kiểm thử tự động JUnit 5 mà không làm ảnh hưởng đồng hồ phần cứng của hệ điều hành.
   - Tác vụ lập lịch `ChotCaTuDongScheduler.java` kích hoạt định kỳ 15 phút một lần (`@Scheduled(cron = "0 */15 * * * *")`), tự động đóng ca quá hạn cho toàn bộ nhân sự theo đúng quy tắc `BR-CC-05`.
3. **Giao diện Phân tách Rõ rệt 2 Chế độ:**
   - **Chế độ Cá nhân (`PHUC_VU`, `THU_NGAN`):** Ẩn hoàn toàn các chức năng quản trị. Cung cấp: (1) Thẻ Chấm công ca hôm nay kèm đồng hồ số thời gian thực, nút Check-in/Check-out tự động đổi màu theo cửa sổ ca; (2) Lịch ca tuần cá nhân 7 ngày; (3) Bảng lịch sử chấm công cá nhân.
   - **Chế độ Quản trị (`QUAN_LY`):** 4 Tab chức năng chuyên nghiệp:
     - Tab 1 – Chấm công & Giám sát ca: Tự chấm công cá nhân + Nút kích hoạt "Chốt ca quá hạn" thủ công + Bảng nhật ký chấm công toàn quán kèm nút "Điều chỉnh công" và "Check-out hộ".
     - Tab 2 – Lịch phân ca tuần & Đổi ca: Lưới ma trận 7 ngày $\times$ 3 ca làm việc, cờ cảnh báo `Thiếu Thu ngân`, Modal đổi ca an toàn và Phân ca mới.
     - Tab 3 – Bảng tổng hợp công theo kỳ: Lọc Tuần này / Tháng này / Tùy chọn ngày, tự động tính tổng giờ thực tế và tổng giờ công quy đổi hệ số theo từng nhân sự phục vụ tính lương.
     - Tab 4 – Danh bạ Nhân sự (CRUD): Thêm mới, chỉnh sửa thông tin, phân quyền chức vụ và khóa mềm tài khoản (chống tự khóa quản lý cuối cùng).

---

## 4. KIẾN TRÚC PHẦN MỀM & MÔ HÌNH CƠ SỞ DỮ LIỆU 11 BẢNG

### 4.1. Ngăn xếp Công nghệ Hiện đại (Technology Stack)
- **Backend Framework:** Spring Boot 3.2.5 (Spring Framework 6.1.6).
- **Ngôn ngữ lập trình:** Java 17 LTS / OpenJDK 25.
- **Máy chủ Web nhúng:** Apache Tomcat 10.1.20 (Port 8080).
- **Tầng ORM / Truy cập dữ liệu:** Spring Data JPA, Hibernate ORM 6.4.4.Final.
- **Connection Pool:** HikariCP 5.0.1 (Hiệu năng cao, tối đa 10 connections trong pool).
- **Hệ quản trị Cơ sở dữ liệu:** MySQL 8.0.x / MariaDB 10.4.x (XAMPP Environment, Port 3306, Charset `utf8mb4`).
- **Frontend Architecture:** Vanilla JavaScript ES6+, HTML5 Semantic, CSS3 Variables, Bootstrap 5.3.3, Bootstrap Icons 1.11.3 (Thuần Web Standard, không phụ thuộc framework nặng, tải trang tức thì dưới 100ms).

### 4.2. Kiến trúc 3 Tầng Modular Monolith & Luồng Dữ liệu
```
[ Browser Client / Desktop POS / Tablet ]
                  |  HTTP REST (JSON) / Session Cookie
                  v
+-------------------------------------------------------+
| 1. PRESENTATION LAYER (REST Controllers)              |
|    - XacThucController (Auth & Brute-force Throttling)|
|    - DonHangController (POS SOP, Table State, Print)  |
|    - KhoController (BOM, WAC, Stock Audit, KPI)       |
|    - NhanSuController (Staff, Shifts, Attendance, BR) |
+-------------------------------------------------------+
                  |  DTO Transfer & Authorization Guard
                  v
+-------------------------------------------------------+
| 2. BUSINESS SERVICE LAYER (@Transactional, ACID)      |
|    - DonHangService (Soft Reservation, Delta In Bar)  |
|    - KhoService (TreeMap Anti-deadlock, WAC Formula)  |
|    - NhanSuService (Clock, BR-CC Rules, Audit Trail)  |
|    - ChotCaTuDongScheduler (@Scheduled Background)    |
+-------------------------------------------------------+
                  |  Spring Data JPA / JPQL Optimizations
                  v
+-------------------------------------------------------+
| 3. DATA ACCESS LAYER (11 Repositories & Entities)     |
|    - Optimistic Locking (@Version phienBan)           |
|    - Pessimistic Locking (PESSIMISTIC_WRITE FOR UPDATE)|
+-------------------------------------------------------+
                  |  HikariCP JDBC Connection Pool
                  v
+-------------------------------------------------------+
| DATABASE: MySQL (11 Tables 3NF, Engine InnoDB, UTF-8) |
+-------------------------------------------------------+
```

### 4.3. Mô hình Thực thể Quan hệ CSDL 11 Bảng Chuẩn 3NF (`database_mau.sql`)
Hệ thống lưu trữ trên cơ sở dữ liệu `quanly_cafe` gồm 11 bảng chuẩn hóa 3NF:
1. `nhan_vien`: Hồ sơ nhân sự (ma_nv, ten_dang_nhap, mat_khau, ho_ten, vai_tro, trang_thai, he_so_luong).
2. `danh_muc`: Phân nhóm đồ uống (ma_danh_muc, ten_danh_muc).
3. `mon_nuoc`: Danh mục món đồ uống (ma_mon, ten_mon, gia, hinh_anh, trang_thai, ma_danh_muc).
4. `nguyen_lieu`: Danh mục nguyên vật liệu tồn kho (ma_nguyen_lieu, ten_nguyen_lieu, don_vi_tinh, so_luong_ton, muc_ton_toi_thieu, don_gia_nhap).
5. `dinh_muc_mon`: Bảng định mức pha chế BOM (ma_dinh_muc, ma_mon, ma_nguyen_lieu, so_luong_can).
6. `ban`: Danh bạ bàn và trạng thái phục vụ (ma_ban, ten_ban, khu_vuc, trang_thai, hoa_don_hien_tai_id).
7. `hoa_don`: Hóa đơn bán hàng (ma_hoa_don, ma_ban, ma_nv_phuc_vu, ma_nv_thu_ngan, thoi_gian_tao, thoi_gian_thanh_toan, tong_tien, giam_gia, thanh_tien, phuong_thuc_thanh_toan, trang_thai, phien_ban).
8. `chi_tiet_hoa_don`: Chi tiết từng món trong hóa đơn kèm Price Snapshot (ma_chi_tiet, ma_hoa_don, ma_mon, so_luong, don_gia, thanh_tien, so_luong_da_in_bar, ghi_chu).
9. `ca_lam`: Danh mục các ca làm việc (ma_ca, ten_ca, gio_bat_dau, gio_ket_thuc, he_so_ca).
10. `phan_ca`: Lịch phân công ca làm việc (ma_phan_ca, ma_nv, ma_ca, ngay_lam, ghi_chu).
11. `cham_cong`: Bảng dữ liệu điểm danh và chấm công 16 cột (ma_cham_cong, ma_phan_ca, ma_nv, ma_ca, ngay_lam, gio_check_in, gio_check_out, so_phut_tre, so_phut_ve_som, so_gio_lam, gio_cong_quy_doi, trang_thai, ghi_chu, nguoi_dieu_chinh, ly_do_dieu_chinh, thoi_gian_dieu_chinh).
12. `lich_su_kho` *(Bảng thứ 11 trong quan hệ Kho)*: Sổ cái biến động kho bất biến (ma_lich_su, ma_nguyen_lieu, loai_giao_dich, so_luong_thay_doi, ton_truoc, ton_sau, don_gia_von, gia_tri_thay_doi, thoi_gian, ma_nv_thuc_hien, ma_hoa_don, ghi_chu).

### 4.4. Ba Cơ chế Kiểm soát Kỹ thuật Đặc thù
1. **Thuật toán Chống Deadlock Kho bằng `TreeMap`:** Sắp xếp danh sách `ma_nguyen_lieu` tăng dần trước khi thực hiện câu lệnh `SELECT ... FOR UPDATE`, đảm bảo mọi giao dịch đồng thời luôn tranh chấp tài nguyên theo cùng một trật tự xác định.
2. **Khóa Lạc quan Optimistic Locking (`@Version`):** Đặt trường `@Version Long phienBan` trên thực thể `HoaDon`, phát hiện và ngăn chặn ngay lập tức hiện tượng mất mát dữ liệu (Lost Updates) khi hai thu ngân cùng thao tác trên một đơn hàng.
3. **Cơ chế Đóng băng Giá vốn & Đơn giá bán (Price Snapshotting):** Trường `don_gia` trong `chi_tiet_hoa_don` và `don_gia_von` trong `lich_su_kho` luôn lưu giá trị tại thời điểm giao dịch phát sinh, bảo vệ tính trung thực của báo cáo doanh thu khi giá niêm yết trong menu thay đổi trong tương lai.

---

## 5. HỆ THỐNG BIỂU ĐỒ THIẾT KẾ & TÀI LIỆU UML ĐỒNG BỘ

Toàn bộ 13 file biểu đồ PlantUML nằm trong thư mục [puml/](file:///d:/3.1.%20PTTKYC/CK/puml/) và các file vector đồ họa SVG tương ứng trong thư mục [diagram/](file:///d:/3.1.%20PTTKYC/CK/diagram/) đã được đồng bộ chuẩn xác 100% với mã nguồn Java thực tế:
- **100% Tiếng Việt không dấu:** Loại bỏ hoàn toàn lỗi hiển thị font chữ và ký tự đặc biệt.
- **Khớp chuẩn định danh mã nguồn:** Giữ nguyên tên Entity JPA, Service, Controller, Scheduler, DTO, Repository, Table, Column.
- **Chuẩn cú pháp PlantUML v1.2026+:** 13/13 file biên dịch sạch, không cảnh báo cú pháp.

### Bảng Ánh xạ 13 Biểu đồ Thiết kế Hệ thống

| STT | Tên Biểu đồ UML | File Nguồn PlantUML | File Vector Đồ họa SVG | Ý nghĩa Nghiệp vụ & Kỹ thuật |
| :---: | :--- | :--- | :--- | :--- |
| 1 | **Kiến trúc Tổng thể Hệ thống** | [01_KienTruc_TongThe.puml](file:///d:/3.1.%20PTTKYC/CK/puml/01_KienTruc_TongThe.puml) | [KienTrucTongThe.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/KienTrucTongThe.svg) | Mô hình 3 tầng Spring Boot, MySQL, HikariCP, Tomcat, Scheduler & 3 vai trò RBAC. |
| 2 | **Mô hình Triển khai Vật lý** | [02_TrienKhai_HeThong.puml](file:///d:/3.1.%20PTTKYC/CK/puml/02_TrienKhai_HeThong.puml) | [TrienKhaiHeThong.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/TrienKhaiHeThong.svg) | Hạ tầng mạng LAN nội bộ quán, Server máy chủ, POS Client, Máy in K80 Quầy Bar & Thu ngân. |
| 3 | **Biểu đồ Use Case Tổng quát** | [03_UseCase_TongQuat.puml](file:///d:/3.1.%20PTTKYC/CK/puml/03_UseCase_TongQuat.puml) | [UseCaseTongQuat.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/UseCaseTongQuat.svg) | 3 Actor người dùng (`PhucVu`, `ThuNgan`, `QuanLy`) kế thừa quyền hạn + Actor Hệ thống (Scheduler). |
| 4 | **Biểu đồ Lớp Thiết kế Chi tiết** | [04_Lop_ThietKe.puml](file:///d:/3.1.%20PTTKYC/CK/puml/04_Lop_ThietKe.puml) | [LopThietKe.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/LopThietKe.svg) | Đầy đủ 11 Entity JPA, 21 DTO, 11 Repository, 3 Service, 1 Scheduler, 9 Controller & các hàm lõi. |
| 5 | **Biểu đồ Thực thể Quan hệ (ERD)** | [05_ERD_DuLieu.puml](file:///d:/3.1.%20PTTKYC/CK/puml/05_ERD_DuLieu.puml) | [ERD_DuLieu.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/ERD_DuLieu.svg) | Cấu trúc dữ liệu 11 bảng chuẩn 3NF, đầy đủ khóa chính (PK), khóa ngoại (FK), ràng buộc UNIQUE. |
| 6 | **Biểu đồ Hoạt động – Bán hàng POS** | [07_Activity_BanHang.puml](file:///d:/3.1.%20PTTKYC/CK/puml/07_Activity_BanHang.puml) | [Activity_BanHang.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_BanHang.svg) | Phân làn 5 bên: Khách hàng - Phục vụ - Quầy Bar - Thu ngân - Hệ thống theo chuẩn SOP 9 bước. |
| 7 | **Biểu đồ Hoạt động – Trừ kho BOM** | [06_Activity_TruKho.puml](file:///d:/3.1.%20PTTKYC/CK/puml/06_Activity_TruKho.puml) | [Activity_TruKho.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_TruKho.svg) | Luồng giao dịch `@Transactional`, sắp xếp TreeMap chống Deadlock, ghi nhận sổ cái `lich_su_kho`. |
| 8 | **Biểu đồ Hoạt động – Chấm công** | [08_Activity_ChamCong.puml](file:///d:/3.1.%20PTTKYC/CK/puml/08_Activity_ChamCong.puml) | [Activity_ChamCong.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_ChamCong.svg) | 4 luồng: Check-in trong cửa sổ, Check-out thời gian thực, Scheduler quét chốt ca và Quản lý sửa công. |
| 9 | **Biểu đồ Tuần tự – Bán hàng POS** | [10_Sequence_BanHang.puml](file:///d:/3.1.%20PTTKYC/CK/puml/10_Sequence_BanHang.puml) | [Sequence_BanHang.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_BanHang.svg) | Tương tác đa tầng: Mở bàn, In Bar Delta, Yêu cầu thanh toán, In tạm tính, Thu tiền & Đóng bàn. |
| 10 | **Biểu đồ Tuần tự – Trừ kho BOM** | [09_Sequence_TruKho.puml](file:///d:/3.1.%20PTTKYC/CK/puml/09_Sequence_TruKho.puml) | [Sequence_TruKho.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_TruKho.svg) | Chi tiết gọi hàm Service, Repository, Pessimistic Locking và Rollback khi thiếu hụt nguyên liệu. |
| 11 | **Biểu đồ Tuần tự – Chấm công** | [11_Sequence_ChamCong.puml](file:///d:/3.1.%20PTTKYC/CK/puml/11_Sequence_ChamCong.puml) | [Sequence_ChamCong.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_ChamCong.svg) | Gọi hàm Check-in, tính phút trễ, quy đổi hệ số công, và Scheduler quét ngầm qua Bean `Clock`. |
| 12 | **Biểu đồ Máy Trạng thái Thực thể** | [12_StateMachine_TrangThai.puml](file:///d:/3.1.%20PTTKYC/CK/puml/12_StateMachine_TrangThai.puml) | [StateMachine_TrangThai.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/StateMachine_TrangThai.svg) | 5 vòng đời thực thể: Bàn (3 trạng thái), Hóa đơn (4 trạng thái), Chấm công (6 trạng thái), Kho & Nhân sự. |
| 13 | **Biểu đồ Đóng gói Package** | [13_Package_SpringBoot.puml](file:///d:/3.1.%20PTTKYC/CK/puml/13_Package_SpringBoot.puml) | [Package_SpringBoot.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Package_SpringBoot.svg) | Kiến trúc phân tầng gói mã nguồn: `controller`, `dto`, `entity`, `repository`, `scheduler`, `service`, `static`. |

---

## 6. DANH MỤC BỘ TÀI LIỆU BÀN GIAO SAU DỌN DẸP

Sau khi dọn dẹp toàn bộ các file nháp tạm thời (`NANGCAP_*.md`, `KICH_BAN.md`, `BAO_CAO_KIEM_THU.md`, `node_modules/`, `package.json`), hệ thống được tổ chức tinh gọn, khoa học và phân định chức năng rõ ràng giữa các tài liệu:

```
d:\3.1. PTTKYC\CK\
│
├── 02_SourceCode_WebJava_Prototype/       # Toàn bộ mã nguồn sản phẩm thực tế
│   ├── database_mau.sql                  # CSDL MySQL 11 bảng chuẩn v2.0 (UTF-8 tiếng Việt)
│   ├── pom.xml                           # Cấu hình phụ thuộc Maven Spring Boot 3.2.5
│   └── src/main/java/ & resources/static/# Toàn bộ Controller, Service, Entity, DTO, Web UI
│
├── puml/                                 # Thư mục 13 file mã nguồn sơ đồ PlantUML v1.2026
├── diagram/                              # Thư mục 13 file vector SVG tương ứng hiển thị sắc nét
├── screenshots/                          # Ảnh chụp minh chứng giao diện hệ thống thực tế
│
├── BAO_CAO.md                            # [TÀI LIỆU 1] Báo cáo Đồ án Học phần theo chuẩn Khoa CNTT (155 KB)
├── BAO_CAO_NGHIEP_VU.md                  # [TÀI LIỆU 2] Báo cáo Chuyên sâu Nghiệp vụ Vận hành F&B (75 KB)
├── BAO_CAO_HE_THONG.md                   # [TÀI LIỆU 3] Báo cáo Kỹ thuật & Hiện thực Phần mềm (74 KB)
├── slide.html                            # [TÀI LIỆU 4] Slide Trình chiếu Tương tác 16 Slide Notebook UI (96 KB)
├── THUYET_TRINH.md                       # [TÀI LIỆU 5] Kịch bản Thuyết trình Báo cáo & 15 Câu hỏi Q&A (84 KB)
└── CONTEXT_TOM_TAT.md                    # [TÀI LIỆU 6] Tóm tắt Bối cảnh Cốt lõi & Toàn cảnh Hệ thống (File này)
```

### Ma trận Phân định Chức năng & Đối tượng Độc giả của Bộ Tài liệu

| Tên Tài liệu | Định dạng & Dung lượng | Đối tượng Độc giả Phù hợp | Trọng tâm Nội dung Chính |
| :--- | :---: | :--- | :--- |
| **`BAO_CAO.md`** | Markdown<br>`~155 KB` | Hội đồng Giảng viên chấm thi học phần PTTKYC | Chuẩn hóa theo đề cương tốt nghiệp Khoa CNTT - ĐH Tôn Đức Thắng: Lời cam đoan, Lời cảm ơn, Phân tích hiện trạng, Kỹ nghệ yêu cầu MoSCoW, Đặc tả Use Case chi tiết, Thiết kế hệ thống, Kịch bản kiểm thử tích hợp (19 test cases) & Kết luận. |
| **`BAO_CAO_NGHIEP_VU.md`** | Markdown<br>`~75 KB` | Ban Giám đốc, Quản lý Vận hành F&B, Chủ đầu tư | Đi sâu vào bài toán kinh doanh F&B: 4 điểm nghẽn As-Is vs To-Be, SOP 9 bước phục vụ tại bàn, công thức WAC, Sổ cái kho, Kiểm kê hao hụt, 7 quy tắc chấm công (BR-CC-01 -> BR-CC-07), Ma trận rủi ro vận hành & Hiệu quả kinh tế. |
| **`BAO_CAO_HE_THONG.md`** | Markdown<br>`~74 KB` | Kỹ sư phần mềm, Kiến trúc sư hệ thống, Đội ngũ DevOps | Đặc tả kỹ thuật chuyên sâu: Ngăn xếp công nghệ, 32 REST APIs, 11 Entity JPA, 21 DTO, Cơ chế chống Deadlock qua TreeMap, Optimistic Locking (`@Version`), Bean Clock, Tác vụ Scheduler và 19 bài kiểm thử tích hợp tự động. |
| **`slide.html`** | Interactive HTML5<br>`~96 KB` | Buổi bảo vệ đồ án trước Hội đồng chấm thi | 16 Slide trình chiếu tương tác hiện đại dạng Notebook/Canvas, nhúng trực tiếp các sơ đồ SVG độ nét cao, bảng ma trận so sánh, công thức toán học và danh sách thành viên nhóm chính xác. |
| **`THUYET_TRINH.md`** | Markdown<br>`~84 KB` | Sinh viên thuyết trình và phản biện đồ án | Kịch bản thuyết trình từng câu chữ (12-15 phút), phân công lời thoại cho 3 sinh viên, 3 kịch bản Live Demo hệ thống thực tế và 15 câu hỏi - câu trả lời phản biện Q&A hóc búa nhất của Hội đồng chấm thi. |
| **`CONTEXT_TOM_TAT.md`** | Markdown<br>`~30 KB` | Toàn bộ thành viên nhóm & AI trợ lý | Tổng hành dinh tóm tắt bối cảnh, lưu giữ toàn bộ quyết định kỹ thuật, quy tắc nghiệp vụ cốt lõi, danh mục tài liệu bàn giao và hướng dẫn vận hành hệ thống. |

---

## 7. HƯỚNG DẪN KHỞI CHẠY HỆ THỐNG & KỊCH BẢN NGHIỆM THU KIỂM THỬ

### 7.1. Hướng dẫn Khởi chạy Hệ thống Thực tế
1. **Bước 1: Khởi động Cơ sở Dữ liệu MySQL (XAMPP):**
   - Mở XAMPP Control Panel và Start module **MySQL** (mặc định cổng 3306).
   - Nạp file cơ sở dữ liệu `database_mau.sql` (v2.0) vào MySQL:
     ```bash
     cd "d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype"
     mysql -u root -p quanly_cafe < database_mau.sql
     ```
   - *Lưu ý:* File `database_mau.sql` đã tích hợp sẵn lệnh `SET FOREIGN_KEY_CHECKS = 0;` và `SET NAMES utf8mb4;`, đảm bảo nạp thành công 100% tiếng Việt chuẩn UTF-8.
2. **Bước 2: Biên dịch và Khởi động Backend Spring Boot:**
   - Mở Terminal tại thư mục `02_SourceCode_WebJava_Prototype`:
     ```bash
     ./mvnw clean compile
     ./mvnw spring-boot:run
     ```
   - Khi console hiển thị `Started QuanLyCafeApplication in X.XXX seconds`, hệ thống đã sẵn sàng tại địa chỉ `http://localhost:8080`.
3. **Bước 3: Truy cập Giao diện Web Người dùng:**
   - Mở trình duyệt Web (Chrome, Edge, Firefox) và truy cập: `http://localhost:8080/index.html`.

### 7.2. Danh sách 3 Tài khoản Đăng nhập Chuẩn (Có nút Quick-fill 1-chạm)

| Tài khoản | Mật khẩu | Chức vụ | Trang Đích Mặc Định | Quyền hạn Vận hành |
| :--- | :--- | :--- | :--- | :--- |
| **`quanly`** | `quanly` | Quản lý Cửa hàng | `http://localhost:8080/kho.html` | Toàn quyền Quản trị Kho hàng, BOM, KPI, Phân ca Nhân sự, Duyệt sửa công, Áp dụng chiết khấu POS. |
| **`thungan`** | `thungan` | Thu ngân Quầy | `http://localhost:8080/banhang.html` | In Phiếu Tạm Tính, Đối soát Tiền mặt / Chuyển khoản VietQR, Xác nhận thanh toán & Đóng bàn (Trừ kho BOM). |
| **`phucvu`** | `phucvu` | Nhân viên Phục vụ | `http://localhost:8080/banhang.html` | Mở bàn, Gọi món mới, Gửi lệnh In Bar, Gọi thêm món (In Bar Delta), Yêu cầu thanh toán, Quay lại phục vụ. |

### 7.3. Kịch bản Nghiệm thu Kiểm thử Tích hợp Tự động (19/19 Test Cases PASS 100%)
Toàn bộ mã nguồn đã được kiểm thử tự động toàn diện qua 3 bộ Test Suite tích hợp chuyên sâu, đạt tỷ lệ thành công 100%:
- **`KhoKpiIntegrationTest.java` (6 ca kiểm thử):**
  1. `restockCalculatesWeightedAverageCostCorrectly`: Nhập kho tính đúng giá vốn WAC.
  2. `stockAuditRequiresReasonAndRejectsZeroDelta`: Kiểm kê bắt buộc lý do, chặn chênh lệch bằng 0.
  3. `paymentDeductsBOMWithPessimisticLockAndPreventsDeadlock`: Trừ kho theo BOM và chống Deadlock.
  4. `insufficientStockRollsBackEntireTransactionAndKeepsAuditClean`: Thiếu nguyên liệu Rollback 100%.
  5. `kpiDashboardCalculatesCOGSAndGrossProfitAccurately`: Tính chính xác COGS, doanh thu và lợi nhuận gộp.
  6. `recipeListExposesMarginsAndAvailableCupsPerItem`: Trả về đúng biên lợi nhuận và số ly khả dụng.
- **`DonHangIntegrationTest.java` (5 ca kiểm thử):**
  1. `waiterCanOrderAndRequestPaymentButCannotConfirmPayment`: Phục vụ mở bàn được nhưng bị chặn thanh toán.
  2. `menuCalculatesAvailableCupsWithSoftReservationWithoutExposingStock`: Tính đúng số ly khả dụng và che giấu kho thô.
  3. `addMoreItemsOnlyPrintsDeltaToBarAndBlockedWhenWaitingPayment`: Thêm món chỉ in Delta và bị chặn khi đang chờ tính tiền.
  4. `cashierConfirmsCashAndTransferPaymentAndClosesTable`: Thu ngân chốt bill tiền mặt/chuyển khoản và đóng bàn về TRỐNG.
  5. `cashierCannotApplyDiscountOnlyManagerCan`: Thu ngân bị chặn nhập chiết khấu, chỉ Quản lý được áp dụng.
- **`NhanSuChamCongIntegrationTest.java` (7 ca kiểm thử):**
  1. `checkInRejectedOutsideWindowAndAcceptedWithin30Minutes`: Chặn check-in ngoài cửa sổ, chấp nhận trong khoảng 30 phút.
  2. `checkInLateRecordsLateMinutesAndClampsWorkedHours`: Đi trễ ghi đúng số phút và không tính giờ ảo.
  3. `checkOutEarlyMarksVeSomAndCombinesWithDiTre`: Về sớm ghi nhận VE_SOM và kết hợp DI_TRE_VE_SOM.
  4. `closeExpiredShiftsMarksAbsentAndForgotCheckout`: Chốt ca ngầm tự động ghi nhận VẮNG và QUÊN CHECK-OUT.
  5. `managerAdjustsForgotCheckoutWithAuditTrailAndRecalculates`: Quản lý sửa công lưu vết kiểm toán và tính lại giờ công.
  6. `shiftSwapUpdatesAssigneeAndDetectsMissingCashier`: Đổi ca cập nhật đúng nhân sự và bật cờ cảnh báo thiếu thu ngân.
  7. `timesheetSummaryMultipliesShiftCoefficientAndIsolatesStaff`: Tổng hợp bảng công nhân đúng hệ số ca và cách ly dữ liệu.
- **`BruteForceThrottlingIntegrationTest.java` (1 ca kiểm thử):**
  1. `failedLoginFiveTimesLocksAccountForSixtySeconds`: Đăng nhập sai 5 lần liên tiếp kích hoạt khóa 60 giây (`HTTP 429`).

---

> **KẾT LUẬN TỔNG THỂ:**  
> Hệ thống Quản lý Quán Cà phê **Sangrimar Cafe & Lounge v2.0** đã hoàn thiện toàn diện, đạt độ chín muồi xuất sắc về mặt giải thuật công nghệ và chiều sâu nghiệp vụ thực tế. Toàn bộ tài liệu báo cáo, slide trình chiếu, kịch bản thuyết trình và mã nguồn phần mềm đã sẵn sàng 100% cho buổi bảo vệ đồ án chính thức trước Hội đồng Giảng viên Khoa Công nghệ Thông tin.
