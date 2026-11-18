# HƯỚNG DẪN CÀI ĐẶT VÀ KHỞI CHẠY HỆ THỐNG QUẢN LÝ QUÁN CÀ PHÊ
> **Học phần:** Phân tích và Thiết kế Yêu cầu (Mã môn: `502050`) – Trường ĐH Tôn Đức Thắng  
> **Backend:** Java Spring Boot 3.x (Spring Web, Spring Data JPA, Hibernate, MySQL Driver)  
> **Frontend:** HTML5, Bootstrap 5.3, JavaScript Fetch API (Đặt trực tiếp trong `src/main/resources/static/`)  
> **Database:** MySQL 8.0 (chạy qua XAMPP trên cổng `3306`)

---

## 1. YÊU CẦU MÔI TRƯỜNG MÁY TÍNH
* **JDK:** Java 17 trở lên (Hỗ trợ tốt Java 17, 21, 25).
* **XAMPP:** Đã bật dịch vụ **Apache** và **MySQL** (cổng mặc định `3306`).
* **Trình duyệt Web:** Chrome, Edge, Firefox.
* **IDE khuyên dùng:** IntelliJ IDEA, Eclipse, hoặc Visual Studio Code (Extension Pack for Java).

---

## 2. CÁC BƯỚC KHỞI CHẠY ỨNG DỤNG

### Bước 1: Khởi động CSDL MySQL trên XAMPP
1. Mở phần mềm **XAMPP Control Panel**.
2. Nhấn nút **Start** ở cả 2 module: **Apache** và **MySQL**.
3. Mở trình duyệt truy cập: `http://localhost/phpmyadmin/`.

### Bước 2: Nạp dữ liệu mẫu (`database_mau.sql`)
1. Trong phpMyAdmin, chọn tab **Nhập (Import)**.
2. Chọn tệp `database_mau.sql` nằm ngay tại thư mục gốc của dự án này.
3. Bấm nút **Thực hiện (Import / Go)** ở cuối trang.
4. Hệ thống sẽ tự động tạo cơ sở dữ liệu `quanly_cafe` và nạp sẵn 10 bàn, 20 món nước, 10 nguyên liệu, công thức pha chế, 5 nhân viên và chấm công mẫu.

### Bước 3: Mở và Khởi chạy ứng dụng Spring Boot
* **Cách 1: Khởi chạy bằng IntelliJ IDEA / Eclipse / VS Code:**
  1. Mở thư mục `02_SourceCode_WebJava_Prototype` như một Maven Project.
  2. Tìm đến file: `src/main/java/com/cafe/QuanLyCafeApplication.java`.
  3. Bấm chuột phải chọn **Run 'QuanLyCafeApplication'** (hoặc phím tắt `Shift + F10`).
* **Cách 2: Khởi chạy bằng dòng lệnh Terminal / PowerShell (nếu máy có cài Maven):**
  ```bash
  mvn spring-boot:run
  ```

### Bước 4: Truy cập giao diện Web Prototype
Khi console hiện dòng chữ:
`>>> HE THONG QUAN LY QUAN CA PHE DA KHOI CHAY THANH CONG! <<<`
Mở trình duyệt truy cập các liên kết sau:
* **Màn hình Đăng nhập:** `http://localhost:8080/index.html` (admin / 123456)
* **Phân hệ Bán hàng POS:** `http://localhost:8080/banhang.html`
* **Phân hệ Quản lý Kho & KPI:** `http://localhost:8080/kho.html`
* **Phân hệ Nhân sự & Chấm công:** `http://localhost:8080/nhansu.html`

---

## 3. DANH SÁCH TÀI KHOẢN MẪU ĐỂ TEST
| Tên Đăng Nhập | Mật Khẩu | Vai Trò | Quyền Hạn |
| :---: | :---: | :---: | :--- |
| `admin` | `123456` | ADMIN | Toàn quyền quản trị hệ thống, áp dụng chiết khấu |
| `manager` | `123456` | MANAGER | Quản lý kho, công thức, xếp ca, áp dụng chiết khấu |
| `staff1` | `123456` | STAFF | Thu ngân, phục vụ, chấm công (không được tự ý chiết khấu) |
| `staff2` | `123456` | STAFF | Thu ngân, phục vụ, chấm công (không được tự ý chiết khấu) |

---

## 4. HƯỚNG DẪN KIỂM THỬ HỆ THỐNG (TESTING GUIDE)

Dự án trang bị đầy đủ **Kiểm thử tích hợp tự động (Automated Integration Tests)** và **Kịch bản kiểm thử hộp đen liên thông (Manual E2E Testing)**.

### 4.1. Kiểm thử Tích hợp Tự động (`PosIntegrationTest.java`)
Bộ kiểm thử tự động nằm tại `src/test/java/com/cafe/PosIntegrationTest.java`, sử dụng **JUnit 5**, **Spring MockMvc** và cơ sở dữ liệu **H2 In-Memory** (`src/test/resources/application-test.properties` với `@ActiveProfiles("test")`).
* **An toàn dữ liệu:** Kiểm thử chạy hoàn toàn trên H2 trong bộ nhớ RAM (chế độ `MODE=MySQL`), **không kết nối và không làm ảnh hưởng** đến CSDL MySQL thật trên XAMPP của người dùng.
* **Cách chạy kiểm thử tự động:**
  * **Trên IDE (IntelliJ IDEA / VS Code / Eclipse):** Mở file `src/test/java/com/cafe/PosIntegrationTest.java` $\rightarrow$ Bấm chuột phải chọn **Run 'PosIntegrationTest'**.
  * **Qua dòng lệnh Maven:**
    ```bash
    mvn test
    ```
* **Danh sách 13 Kịch bản Kiểm thử Tự động (Test Cases):**

| STT | Tên phương thức Test | Nội dung kiểm thử | Kỳ vọng |
| :---: | :--- | :--- | :---: |
| 1 | `loginSessionDoesNotExposePassword` | Đăng nhập `/api/auth/login` tạo `HttpSession` và ẩn trường `matKhau` trong JSON trả về | `200 OK` |
| 2 | `requiresSessionAndUsesServerIdentity` | Chặn truy cập trái phép khi chưa đăng nhập (`401`) và lấy `maNV` từ session phía server | `401 / 201` |
| 3 | `saveReloadUpdateCancel` | Tạo đơn tại bàn (`CO_KHACH`), mở lại đơn theo bàn, cập nhật số lượng món, kiểm tra khóa phiên (`@Version`) và hủy đơn trả bàn về `TRONG` (không trừ kho) | `201 / 200 / 409` |
| 4 | `createRetryReturnsSameOrderAndTableRejectsSecond` | Chống tạo trùng đơn khi gửi lại cùng `yeuCauTao` (Idempotency) và chặn tạo đơn mở thứ 2 trên cùng 1 bàn | `201 / 409` |
| 5 | `rejectsInvalidAndEmptyOrders` | Chặn đơn có số lượng $\le 0$, đơn tại bàn thiếu mã bàn, hoặc giỏ hàng rỗng | `400 Bad Request` |
| 6 | `paymentCashStockAndRetryOnce` | Chặn thanh toán khi khách đưa thiếu tiền; thanh toán đủ tiền tự động trừ kho (`120ml -> 90ml`), chống trừ kho 2 lần khi retry cùng `yeuCauThanhToan` | `400 / 200 / 409` |
| 7 | `insufficientStockRollsBackWholePayment` | Khi nguyên liệu tồn kho không đủ: Rollback toàn bộ giao dịch `@Transactional`, giữ nguyên tồn kho `120ml`, đơn vẫn `CHO_THANH_TOAN` và bàn vẫn `CO_KHACH` | `400 Bad Request` |
| 8 | `missingRecipeBlocked` | Chặn thanh toán và rollback nếu trong đơn có món chưa được thiết lập định mức công thức | `400 Bad Request` |
| 9 | `discountValidationAndRole` | Phân quyền chiết khấu: chặn `STAFF` nhập chiết khấu (`403`), chặn chiết khấu âm hoặc vượt tổng tiền (`400`), cho phép `MANAGER` áp dụng chiết khấu hợp lệ | `403 / 400 / 200` |
| 10 | `transferRequiresConfirmationAndUniqueReference` | Thanh toán chuyển khoản bắt buộc tích xác nhận tiền vào tài khoản và kiểm tra mã tham chiếu giao dịch duy nhất (`UNIQUE`) | `400 / 200 / 409` |
| 11 | `pricesRemainSnapshots` | Giữ nguyên đơn giá đã chụp (snapshot) của món trong đơn đang mở ngay cả khi danh mục món đổi giá | `200 OK` |
| 12 | `twoConcurrentPaymentsOnlyDeductOnce` | Kiểm thử đa luồng (2 luồng thanh toán cùng lúc cho 1 đơn): khóa bi quan (`SELECT ... FOR UPDATE`) và `@Version` đảm bảo chỉ 1 luồng thành công, kho chỉ trừ 1 lần | `1 Pass / 1 Conflict` |
| 13 | `twoOrdersCannotOversellSharedIngredient` & `duplicateProductLinesAggregateStock` | Kiểm thử 2 đơn tranh chấp cùng nguyên liệu tồn kho và gộp định mức nhiều dòng trùng món trong cùng đơn | `Rollback đơn thiếu` |

---

### 4.2. Kịch bản Kiểm thử Hộp đen Liên thông trên Giao diện Web (Manual E2E Test)
Sau khi nạp `database_mau.sql` và khởi chạy ứng dụng tại `http://localhost:8080/index.html`, thực hiện kiểm thử theo luồng:
1. **Kiểm thử Đăng nhập & Phân quyền (`index.html`):**
   * Đăng nhập bằng `staff1 / 123456` $\rightarrow$ Hệ thống điều hướng vào `banhang.html` (trên Modal Thanh toán, ô Chiết khấu sẽ tự động khóa đối với `STAFF`).
   * Đăng nhập bằng `admin / 123456` hoặc `manager / 123456` $\rightarrow$ Cho phép nhập chiết khấu và quản lý kho.
2. **Kiểm thử Tạo đơn, Mở lại đơn & Thanh toán POS (`banhang.html`):**
   * Bấm vào **Bàn 03 (Đỏ - Có khách)** $\rightarrow$ Hệ thống tự động tải lại đơn đang mở `#3` của Bàn 03.
   * Bấm vào **Bàn 02 (Xanh - Trống)** $\rightarrow$ Chọn **1 Cà phê sữa đá** ($29.000\text{ đ}$, định mức $30\text{ml}$ Sữa đặc) và **1 Trà đào cam sả** ($38.000\text{ đ}$) $\rightarrow$ Bấm **Lưu đơn** $\rightarrow$ Bàn 02 chuyển sang màu Đỏ (`CO_KHACH`).
   * Bấm **Thanh toán & Xuất hóa đơn** $\rightarrow$ Nhập tiền khách đưa $100.000\text{ đ}$ $\rightarrow$ Bấm **Xác nhận thanh toán** $\rightarrow$ Hiển thị Bill thanh toán (tiền thừa $33.000\text{ đ}$), Bàn 02 tự động trả về màu Xanh (`TRONG`).
3. **Kiểm thử Trừ kho Tự động & Cảnh báo Tồn tối thiểu (`kho.html`):**
   * Chuyển sang tab **Kho & KPI (`kho.html`)**:
   * Nguyên liệu **Sữa đặc Ngôi Sao** ban đầu có tồn kho $120\text{ml}$ (ngưỡng tối thiểu $100\text{ml}$). Sau khi thanh toán 1 ly Cà phê sữa đá (trừ $30\text{ml}$), số lượng tồn tự động giảm còn **$90\text{ml}$** ($\le 100\text{ml}$).
   * Dòng Sữa đặc lập tức bật nhãn đỏ **"SẮP HẾT HÀNG"** và thẻ KPI **"NGUYÊN LIỆU SẮP HẾT"** tăng số đếm cảnh báo.
   * Bấm nút **Nhập kho** tại dòng Sữa đặc (nhập thêm $500\text{ml}$) $\rightarrow$ Tồn kho tăng lên $590\text{ml}$, nhãn cảnh báo chuyển về màu Xanh (**"An toàn"**).
4. **Kiểm thử Quản lý Nhân sự & Chấm công (`nhansu.html`):**
   * Chuyển sang tab **Nhân sự (`nhansu.html`)** $\rightarrow$ Chọn nhân viên và ca làm $\rightarrow$ Bấm **Check-in Vào Ca** $\rightarrow$ Hệ thống tự động đối chiếu giờ hiện tại với giờ bắt đầu ca ($\le 15\text{ phút}$ gắn nhãn **ĐÚNG GIỜ**, $> 15\text{ phút}$ gắn nhãn **ĐI TRỄ**).
