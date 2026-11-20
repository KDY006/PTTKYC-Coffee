# HƯỚNG DẪN CÀI ĐẶT VÀ KHỞI CHẠY HỆ THỐNG
## SANGRIMAR CAFE & LOUNGE v2.0

> **Học phần:** Phân tích và Thiết kế Yêu cầu (Mã môn: `502050`) – Trường Đại học Tôn Đức Thắng  
> **Giảng viên hướng dẫn:** TS. Hồ Thị Linh  
> **Sinh viên thực hiện:**
> - **Nguyễn Văn Duy** - MSSV: `52400010` (Nhóm trưởng / Fullstack Developer)
> - **Trần Văn Quý** - MSSV: `52400007` (Backend Developer / BA)
> - **Lê Tấn Đạt** - MSSV: `52400105` (Fullstack Developer / QA)
>
> **Công nghệ:** Java 17 LTS / OpenJDK 25, Spring Boot 3.2.5, Spring Data JPA / Hibernate 6, MySQL 8.0 (XAMPP), HTML5 / CSS3 / Vanilla JS ES6+ (Light Mode).

---

## MỤC LỤC
1. [Yêu cầu Môi trường Máy tính](#1-yêu-cầu-môi-trường-máy-tính)
2. [Các bước Cài đặt và Khởi chạy Hệ thống](#2-các-bước-cài-đặt-và-khởi-chạy-hệ-thống)
   - [Bước 1: Khởi động MySQL trên XAMPP](#bước-1-khởi-động-mysql-trên-xampp)
   - [Bước 2: Nạp Cơ sở Dữ liệu Mẫu (database_mau.sql)](#bước-2-nạp-cơ-sở-dữ-liệu-mẫu-database_mausql)
   - [Bước 3: Biên dịch và Khởi chạy Spring Boot Backend](#bước-3-biên-dịch-và-khởi-chạy-spring-boot-backend)
   - [Bước 4: Truy cập Giao diện Web Prototype](#bước-4-truy-cập-giao-diện-web-prototype)
3. [Danh sách Tài khoản Kiểm thử Chuẩn v2.0](#3-danh-sách-tài-khoản-kiểm-thử-chuẩn-v20)
4. [Hướng dẫn Kiểm thử Hệ thống (Testing Guide)](#4-hướng-dẫn-kiểm-thử-hệ-thống-testing-guide)
   - [4.1. Kiểm thử Tích hợp Tự động (Automated Integration Tests)](#41-kiểm-thử-tích-hợp-tự-động-automated-integration-tests)
   - [4.2. Kịch bản Kiểm thử Thủ công Liên thông trên Giao diện (Manual E2E)](#42-kịch-bản-kiểm-thử-thủ-công-liên-thông-trên-giao-diện-manual-e2e)
5. [Xử lý Sự cố Thường gặp (Troubleshooting FAQs)](#5-xử-lý-sự-cố-thường-gặp-troubleshooting-faqs)
6. [Cấu trúc Thư mục & Danh mục Tài liệu Bàn giao](#6-cấu-trúc-thư-mục--danh-mục-tài-liệu-bàn-giao)

---

## 1. YÊU CẦU MÔI TRƯỜNG MÁY TÍNH

Để khởi chạy và kiểm thử hệ thống ổn định, máy tính cần đáp ứng các điều kiện tiên quyết sau:
* **Java Development Kit (JDK):** Phiên bản **Java 17 LTS trở lên** (Hỗ trợ tương thích hoàn hảo Java 17, 21 và OpenJDK 25).
  - Kiểm tra phiên bản bằng lệnh: `java -version`.
* **XAMPP Control Panel:** Đã cài đặt và kích hoạt module **MySQL** (chạy trên cổng mặc định `3306`).
* **Apache Maven:** **Không bắt buộc cài đặt** trên máy vì dự án đã tích hợp sẵn Maven Wrapper (`mvnw` cho Linux/macOS và `mvnw.cmd` cho Windows).
* **Trình duyệt Web:** Google Chrome, Microsoft Edge hoặc Mozilla Firefox (hỗ trợ chuẩn CSS Grid, ES6 Modules và in nhiệt `@media print`).
* **IDE khuyên dùng (nếu muốn mở xem code):** IntelliJ IDEA, Visual Studio Code (kèm Extension Pack for Java), hoặc Eclipse.

---

## 2. CÁC BƯỚC CÀI ĐẶT VÀ KHỞI CHẠY HỆ THỐNG

### Bước 1: Khởi động MySQL trên XAMPP
1. Mở phần mềm **XAMPP Control Panel**.
2. Nhấn nút **Start** tại module **MySQL** (đảm bảo hiển thị màu xanh lá và cổng ghi nhận là `3306`).
3. *(Tùy chọn)* Nhấn **Start** tại module **Apache** nếu muốn sử dụng giao diện đồ họa **phpMyAdmin**.

---

### Bước 2: Nạp Cơ sở Dữ liệu Mẫu (`database_mau.sql`)
Tệp `database_mau.sql` nằm ngay tại thư mục `02_SourceCode_WebJava_Prototype/`. Tệp này đã được thiết lập sẵn các lệnh bảo đảm an toàn:
- `SET FOREIGN_KEY_CHECKS = 0;` (chống lỗi khóa ngoại khi nạp).
- `SET NAMES utf8mb4;` (bảo toàn 100% tiếng Việt có dấu chuẩn UTF-8).
- Cấu trúc 11 bảng chuẩn 3NF kèm đầy đủ dữ liệu demo (10 bàn, 12 món nước, 13 nguyên liệu tồn kho, công thức pha chế BOM, 5 tài khoản nhân sự, lịch phân ca tuần và dữ liệu chấm công).

Bạn có thể lựa chọn 1 trong 2 cách sau để nạp CSDL:

#### Cách A: Nạp bằng phpMyAdmin (Giao diện Web trực quan)
1. Mở trình duyệt web và truy cập: `http://localhost/phpmyadmin/`.
2. Tạo cơ sở dữ liệu mới mang tên `quanly_cafe` (nếu chưa có), chọn bảng mã **`utf8mb4_unicode_ci`**.
3. Chọn cơ sở dữ liệu `quanly_cafe` ở cột bên trái $\rightarrow$ Nhấn vào tab **Nhập (Import)** ở thanh menu trên cùng.
4. Nhấn **Chọn tệp (Choose File)** $\rightarrow$ Tìm và chọn tệp `database_mau.sql` trong thư mục `02_SourceCode_WebJava_Prototype/`.
5. Cuộn xuống cuối trang và nhấn nút **Thực hiện (Import / Go)**.
6. Khi màn hình hiện thông báo màu xanh báo nạp thành công các câu lệnh SQL, CSDL đã sẵn sàng.

#### Cách B: Nạp bằng dòng lệnh MySQL CLI (Nhanh chóng & Chính xác 100%)
Mở Terminal / PowerShell tại thư mục `02_SourceCode_WebJava_Prototype` và thực hiện lần lượt 2 lệnh sau:
```powershell
# 1. Tạo cơ sở dữ liệu nếu chưa tồn tại
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS quanly_cafe CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

# 2. Nạp dữ liệu từ file database_mau.sql
mysql -u root -p quanly_cafe < database_mau.sql
```
*(Nếu MySQL trên XAMPP không đặt mật khẩu cho tài khoản `root`, chỉ cần nhấn phím **Enter** khi được hỏi password).*

---

### Bước 3: Biên dịch và Khởi chạy Spring Boot Backend
Điều hướng vào thư mục chứa mã nguồn:
```powershell
cd "d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype"
```

Lựa chọn cách khởi chạy phù hợp với môi trường của bạn:

#### Cách 1: Sử dụng Maven Wrapper trên dòng lệnh (Khuyên dùng)
* **Trên Windows (PowerShell / Command Prompt):**
  ```powershell
  # Biên dịch sạch mã nguồn
  .\mvnw.cmd clean compile

  # Khởi chạy ứng dụng
  .\mvnw.cmd spring-boot:run
  ```
* **Trên Linux / macOS (Terminal):**
  ```bash
  # Phân quyền thực thi (nếu cần)
  chmod +x mvnw

  # Biên dịch và khởi chạy
  ./mvnw clean compile
  ./mvnw spring-boot:run
  ```

#### Cách 2: Sử dụng Maven đã cài sẵn trên máy (Global Maven)
```bash
mvn clean compile
mvn spring-boot:run
```

#### Cách 3: Khởi chạy trực tiếp từ IDE (IntelliJ IDEA / VS Code / Eclipse)
1. Mở thư mục `02_SourceCode_WebJava_Prototype` dưới dạng **Maven Project**.
2. Chờ IDE hoàn tất đồng bộ các thư viện Maven (Sync dependencies).
3. Tìm đến tệp mã nguồn chính:  
   `src/main/java/com/cafe/QuanLyCafeApplication.java`
4. Bấm chuột phải chọn **Run 'QuanLyCafeApplication'** (hoặc nhấn tổ hợp phím `Shift + F10`).

---

### Bước 4: Truy cập Giao diện Web Prototype
Khi cửa sổ dòng lệnh xuất hiện các dòng thông báo sau:
```text
Tomcat started on port 8080 (http) with context path ''
Started QuanLyCafeApplication in X.XXX seconds (process running for X.XXX)
>>> HE THONG QUAN LY QUAN CA PHE DA KHOI CHAY THANH CONG! <<<
```

Hệ thống đã sẵn sàng phục vụ. Mở trình duyệt Web (Chrome/Edge/Firefox) và truy cập các liên kết tương ứng:

| Phân hệ Giao diện | Địa chỉ URL Truy cập | Chức năng Phục vụ |
| :--- | :--- | :--- |
| **Màn hình Đăng nhập & Xác thực** | `http://localhost:8080/index.html`<br>*(hoặc `http://localhost:8080/`)* | Đăng nhập hệ thống, 3 nút Quick-fill 1-chạm, chống Brute-force khóa 60s sau 5 lần sai (`HTTP 429`). |
| **Phân hệ Bán hàng POS** | `http://localhost:8080/banhang.html` | Sơ đồ bàn 3 màu viền, mở bàn, đặt món, In Bar Delta, Yêu cầu thanh toán, In Phiếu Tạm Tính, Thu tiền & Đóng bàn. |
| **Phân hệ Quản trị Kho & KPI** | `http://localhost:8080/kho.html` | Quản trị tồn kho 13 nguyên liệu, Định mức BOM 12 món, Nhập kho WAC, Kiểm kê hao hụt, Sổ cái `lich_su_kho`, KPI Dashboard 4 kỳ & Top 5 bán chạy. |
| **Phân hệ Quản trị Nhân sự** | `http://localhost:8080/nhansu.html` | Chấm công thời gian thực (Cửa sổ 30p), Lịch phân ca tuần cảnh báo thiếu Thu ngân/Quản lý, Bảng tổng hợp công nhân hệ số ca, Điều chỉnh công có lưu vết. |

---

## 3. DANH SÁCH TÀI KHOẢN KIỂM THỬ CHUẨN v2.0

Hệ thống thiết lập sẵn **3 tài khoản đại diện cho 3 cấp bậc phân quyền RBAC**, tích hợp sẵn **3 nút bấm Quick-fill 1-chạm** ngay tại màn hình đăng nhập `index.html` giúp bạn đăng nhập tức thì mà không cần gõ phím:

```
+---------------------------------------------------------------------------------------------------------+
|                               DANH SÁCH TÀI KHOẢN PHÂN QUYỀN CHUẨN v2.0                                 |
+---------------------------------------------------------------------------------------------------------+
| Tài khoản | Mật khẩu | Chức vụ / Vai trò | Trang đích chuyển hướng | Quyền hạn Vận hành                 |
| :---:     | :---:    | :---:             | :---:                   | :---                               |
| quanly    | quanly   | QUẢN LÝ           | kho.html                | Toàn quyền hệ thống: Quản trị kho, |
|           |          | (QUAN_LY)         |                         | sửa BOM, xem KPI, phân ca tuần,    |
|           |          |                   |                         | duyệt sửa công, áp chiết khấu POS. |
| thungan   | thungan  | THU NGÂN          | banhang.html            | In Phiếu Tạm Tính, đối soát Tiền   |
|           |          | (THU_NGAN)        |                         | mặt / VietQR, bấm xác nhận thanh   |
|           |          |                   |                         | toán và đóng bàn (trừ kho BOM).    |
| phucvu    | phucvu   | PHỤC VỤ           | banhang.html            | Mở bàn, gọi món mới, gửi lệnh      |
|           |          | (PHUC_VU)         |                         | in Bar, gọi thêm món (in Delta),   |
|           |          |                   |                         | yêu cầu thanh toán, mở lại bàn.    |
+---------------------------------------------------------------------------------------------------------+
```

> [!IMPORTANT]
> **Cơ chế An ninh Brute-force Throttling:**
> Nếu nhập sai mật khẩu 5 lần liên tiếp trên bất kỳ tài khoản nào, hệ thống sẽ tạm khóa đăng nhập trong **60 giây** và trả về mã lỗi chuẩn `HTTP 429 Too Many Requests`.

---

## 4. HƯỚNG DẪN KIỂM THỬ HỆ THỐNG (TESTING GUIDE)

### 4.1. Kiểm thử Tích hợp Tự động (Automated Integration Tests)
Dự án được trang bị bộ kiểm thử tích hợp chuyên sâu gồm **33 ca kiểm thử tự động**, sử dụng **JUnit 5**, **Spring MockMvc** và cơ sở dữ liệu **H2 In-Memory Database** (chạy ở chế độ tương thích `MODE=MySQL`).
* **An toàn tuyệt đối:** Toàn bộ test suite chạy hoàn toàn trong bộ nhớ RAM, **không kết nối và không làm thay đổi bất kỳ dữ liệu nào** trong cơ sở dữ liệu MySQL thật trên XAMPP.
* **Lệnh chạy toàn bộ kiểm thử:**
  ```powershell
  # Trên Windows
  .\mvnw.cmd test

  # Trên Linux/macOS
  ./mvnw test
  ```
* **Phân bổ 33 ca kiểm thử tích hợp:**
  1. `DonHangIntegrationTest.java` (17 tests): Kiểm thử luồng bán hàng POS, mở bàn, bảo mật phiên session, chống bán vượt tồn kho (Soft Reservation), gọi thêm món in Delta, phân quyền chiết khấu của Quản lý, kiểm soát đồng thời `@Version` và khóa bi quan trừ kho BOM.
  2. `KhoKpiIntegrationTest.java` (6 tests): Kiểm thử nhập kho tính giá vốn bình quân gia quyền (WAC), kiểm kê hao hụt bắt buộc lý do, trừ kho tự động ghi nhật ký `lich_su_kho`, chống Deadlock bằng `TreeMap`, và báo cáo Dashboard KPI 4 kỳ.
  3. `NhanSuChamCongIntegrationTest.java` (7 tests): Kiểm thử 7 quy tắc chấm công (`BR-CC-01` $\rightarrow$ `BR-CC-07`), cửa sổ điểm danh $\pm 30$ phút, quy đổi giờ công theo hệ số ca tối, tiến trình chốt ca quá hạn ngầm qua Scheduler, điều chỉnh công lưu vết kiểm toán và cảnh báo thiếu Thu ngân trên lịch phân ca tuần.
  4. `NhanSuIntegrationTest.java` (3 tests): Kiểm thử phân ca tuần của Quản lý, nhân viên chỉ chấm công ca của chính mình, bảo vệ phân quyền CRUD danh bạ nhân sự và chặn nhân viên thường truy cập phân hệ Quản trị Kho.

---

### 4.2. Kịch bản Kiểm thử Thủ công Liên thông trên Giao diện (Manual E2E)

#### Kịch bản 1: Chu trình Bán hàng POS Phục vụ Tại Bàn (SOP 9 bước)
1. **Đăng nhập:** Nhấn nút Quick-fill **Phục vụ** (`phucvu/phucvu`) $\rightarrow$ Bấm **Đăng nhập** $\rightarrow$ Hệ thống điều hướng vào `banhang.html`.
2. **Mở bàn & Gọi món:**
   - Chọn **Bàn 01** (đang có viền Xanh dương – `TRỐNG`).
   - Chọn món: **1 Cà phê sữa đá** ($29.000\text{ đ}$) và **1 Trà đào cam sả** ($38.000\text{ đ}$).
   - Bấm **Mở bàn / Gọi món** $\rightarrow$ Bàn 01 lập tức chuyển sang viền Xanh lá (`ĐANG DÙNG NƯỚC`), hiển thị icon đồ uống và tổng tiền tạm tính $67.000\text{ đ}$. Hệ thống sinh Phiếu Pha Chế (Bar Ticket) lần 1.
3. **Khách gọi thêm món (Cơ chế In Bar Delta):**
   - Bấm lại vào Bàn 01 $\rightarrow$ Chọn thêm **1 Trà sữa trân châu** ($35.000\text{ đ}$).
   - Bấm **Gửi Bar món mới** $\rightarrow$ Phiếu In Bar Bổ Sung chỉ in duy nhất món mới thêm (Trà sữa trân châu), hoàn toàn không in lại 2 ly cũ đã pha.
4. **Yêu cầu thanh toán (Khóa bàn):**
   - Khách yêu cầu tính tiền $\rightarrow$ Bấm nút **Yêu cầu thanh toán**. Bàn 01 chuyển sang viền Vàng hổ phách (`CHỜ THANH TOÁN`). Tại trạng thái này, tính năng thêm món bị khóa an toàn để chống gian lận.
5. **Thu ngân In Tạm Tính & Chốt thanh toán:**
   - Đăng xuất $\rightarrow$ Đăng nhập bằng tài khoản **Thu ngân** (`thungan/thungan`).
   - Mở Bàn 01 $\rightarrow$ Bấm **In Phiếu Tạm Tính** (Proforma Bill) kèm mã VietQR động mang ra bàn đối soát.
   - Bấm **Thanh toán & Xuất hóa đơn** $\rightarrow$ Nhập tiền khách đưa $150.000\text{ đ}$ $\rightarrow$ Bấm **Xác nhận thanh toán** $\rightarrow$ Hệ thống hiển thị Hóa Đơn Chính Thức (tiền thừa $48.000\text{ đ}$), tự động trừ kho nguyên liệu theo định mức BOM, ghi sổ cái `lich_su_kho`, chuyển đơn hàng sang `ĐÃ THANH TOÁN` và trả Bàn 01 về viền Xanh dương (`TRỐNG`).

#### Kịch bản 2: Quản trị Kho, Nhập kho WAC & Kiểm kê Hao hụt
1. **Đăng nhập Quản lý:** Đăng nhập bằng tài khoản `quanly/quanly` $\rightarrow$ Hệ thống điều hướng vào `kho.html`.
2. **Kiểm tra Sổ cái Biến động Kho:** Chuyển sang **Tab 3: Nhật ký Biến động kho** $\rightarrow$ Kiểm tra dòng giao dịch `XUAT_BAN_HANG` vừa được tạo tự động từ đơn hàng Bàn 01 ở Kịch bản 1 (gắn liền với mã hóa đơn và tên thu ngân).
3. **Nhập kho theo Giá vốn WAC:**
   - Tại dòng nguyên liệu **Sữa đặc Ngôi Sao**, bấm nút **Nhập kho**.
   - Chọn số lượng nhập $+1.000\text{ml}$ với đơn giá nhập mới $65\text{ đ/ml}$ $\rightarrow$ Bấm **Xác nhận nhập kho**.
   - Kiểm tra đơn giá vốn của nguyên liệu tự động được tính lại theo công thức bình quân gia quyền (WAC) và hiển thị dòng `NHAP_KHO` trên sổ cái.
4. **Kiểm kê Hao hụt Cuối ngày:**
   - Bấm nút **Kiểm kê** tại nguyên liệu bất kỳ $\rightarrow$ Nhập số lượng thực tế đếm được $\rightarrow$ Nhập lý do: *"Hao hụt tự nhiên trong quá trình pha chế"* $\rightarrow$ Bấm **Lưu kiểm kê**.
   - Hệ thống tự động tính số lượng chênh lệch $\Delta$, giá trị hao hụt và ghi nhận giao dịch `KIEM_KE_HAO_HUT`.

#### Kịch bản 3: Quản trị Nhân sự, Phân ca Tuần & Chấm công Thời gian thực
1. **Chấm công Cá nhân (Phục vụ / Thu ngân):**
   - Đăng nhập `phucvu/phucvu` $\rightarrow$ Truy cập `nhansu.html`.
   - Giao diện tự động hiển thị ở **Chế độ Cá nhân**: Thẻ Ca hôm nay hiển thị đồng hồ thời gian thực và trạng thái cửa sổ điểm danh.
   - Bấm **Check-in Vào Ca** $\rightarrow$ Nếu trong cửa sổ $[\text{Bắt đầu} - 30\text{p},\ \text{Kết thúc}]$, hệ thống ghi nhận thành công và tự động đánh giá `ĐÚNG GIỜ` hoặc `ĐI TRỄ` theo ngưỡng 15 phút.
2. **Quản trị Lịch phân ca & Cảnh báo Cân bằng Vai trò (Quản lý):**
   - Đăng nhập `quanly/quanly` $\rightarrow$ Truy cập `nhansu.html` $\rightarrow$ Chuyển sang **Tab 2: Lịch phân ca tuần**.
   - Quan sát lưới ma trận 7 ngày $\times$ 3 ca làm việc: Những ca có nhân viên phục vụ nhưng thiếu vai trò Thu ngân hoặc Quản lý sẽ tự động bật **viền cam nét đứt** và badge cảnh báo **"Thiếu Thu ngân"** (Quy tắc `BR-CC-07`).
   - Bấm nút **Đổi ca** để hoán đổi nhân sự trực ca an toàn.
3. **Duyệt Điều chỉnh Ngoại lệ có Lưu vết Kiểm toán:**
   - Tại **Tab 1: Chấm công & Giám sát ca**, chọn một dòng chấm công cần chỉnh sửa $\rightarrow$ Bấm **Điều chỉnh**.
   - Điều chỉnh lại giờ Check-in/Check-out và bắt buộc nhập lý do điều chỉnh $\rightarrow$ Bấm **Lưu thay đổi**.
   - Hệ thống tự động tính lại số phút trễ, giờ công quy đổi, cập nhật trạng thái hợp lệ và lưu vết kiểm toán tên người sửa cùng thời gian sửa.

---

## 5. XỬ LÝ SỰ CỐ THƯỜNG GẶP (TROUBLESHOOTING FAQS)

### Sự cố 1: Lỗi cổng 8080 đã bị chiếm dụng (`Port 8080 was already in use`)
* **Nguyên nhân:** Có một tiến trình Spring Boot chạy trước đó chưa được tắt hẳn, hoặc phần mềm khác (như Jenkins, Tomcat ngoài) đang chiếm cổng 8080.
* **Cách xử lý trên Windows:**
  ```powershell
  # Tìm PID của tiến trình đang chiếm cổng 8080
  netstat -ano | findstr :8080

  # Buộc dừng tiến trình theo PID tìm được (ví dụ PID là 1234)
  taskkill /PID 1234 /F
  ```
  Sau đó thực hiện lệnh chạy lại: `.\mvnw.cmd spring-boot:run`.

---

### Sự cố 2: Lỗi không kết nối được MySQL (`Can't connect to MySQL server on 'localhost:3306' - Error 10061`)
* **Nguyên nhân:** Dịch vụ MySQL trên XAMPP chưa được bật, hoặc cổng 3306 bị phần mềm diệt virus/tường lửa chặn.
* **Cách xử lý:**
  1. Mở XAMPP Control Panel và kiểm tra module **MySQL** đã bấm **Start** chưa.
  2. Nếu cổng 3306 bị đụng độ với MySQL cài ngoài máy, mở `XAMPP > Config (my.ini)` đổi cổng sang 3307, đồng thời cập nhật dòng `spring.datasource.url` trong `src/main/resources/application.properties` tương ứng.

---

### Sự cố 3: Lỗi font chữ tiếng Việt bị biến thành dấu hỏi chấm `???` khi nạp CSDL
* **Nguyên nhân:** Sử dụng đường ống lệnh PowerShell (`Get-Content ... | mysql`) với bảng mã mặc định không phải UTF-8.
* **Cách xử lý:** Nạp trực tiếp bằng lệnh `source` bên trong MySQL CLI:
  ```sql
  mysql -u root -p
  USE quanly_cafe;
  SET NAMES utf8mb4;
  SOURCE d:/3.1. PTTKYC/CK/02_SourceCode_WebJava_Prototype/database_mau.sql;
  ```
  Hoặc nạp qua giao diện phpMyAdmin với tùy chọn ký tự mặc định là `utf8mb4`.

---

### Sự cố 4: Tài khoản bị khóa tạm thời (`HTTP 429 Too Many Requests`)
* **Nguyên nhân:** Nhập sai mật khẩu liên tiếp quá 5 lần trên giao diện đăng nhập (Cơ chế bảo vệ chống tấn công Brute-force).
* **Cách xử lý:** Chờ hết thời gian đếm ngược **60 giây** hiển thị trên màn hình, sau đó sử dụng các nút **Quick-fill 1-chạm** (`quanly`, `thungan`, `phucvu`) để đăng nhập an toàn.

---

## 6. CẤU TRÚC THƯ MỤC & DANH MỤC TÀI LIỆU BÀN GIAO

```
d:\3.1. PTTKYC\CK\
│
├── 02_SourceCode_WebJava_Prototype/       # Toàn bộ mã nguồn sản phẩm thực tế
│   ├── database_mau.sql                  # CSDL MySQL 11 bảng chuẩn v2.0 (UTF-8 tiếng Việt)
│   ├── pom.xml                           # Cấu hình Maven Spring Boot 3.2.5
│   ├── mvnw & mvnw.cmd                   # Maven Wrapper thực thi đa nền tảng
│   ├── README.md                         # Hướng dẫn cài đặt và khởi chạy dự án (File này)
│   └── src/main/java/ & resources/       # Tầng Controller, Service, Entity, DTO, Static Web
│
├── puml/                                 # 13 file mã nguồn sơ đồ thiết kế PlantUML v1.2026
├── diagram/                              # 13 file vector SVG chất lượng cao tương ứng
├── screenshots/                          # Ảnh chụp minh chứng giao diện thực tế hệ thống
│
├── BAO_CAO.md                            # [TÀI LIỆU 1] Báo cáo Đồ án Học phần theo chuẩn Khoa CNTT (155 KB)
├── BAO_CAO_NGHIEP_VU.md                  # [TÀI LIỆU 2] Báo cáo Chuyên sâu Nghiệp vụ Vận hành F&B (75 KB)
├── BAO_CAO_HE_THONG.md                   # [TÀI LIỆU 3] Báo cáo Kỹ thuật & Hiện thực Phần mềm Spring Boot (74 KB)
├── slide.html                            # [TÀI LIỆU 4] Slide Trình chiếu Tương tác 16 Slide Notebook UI (96 KB)
├── THUYET_TRINH.md                       # [TÀI LIỆU 5] Kịch bản Thuyết trình Báo cáo & 15 Câu hỏi Q&A Phản biện (84 KB)
├── CONTEXT_TOM_TAT.md                    # [TÀI LIỆU 6] Tóm tắt Bối cảnh Cốt lõi & Toàn cảnh Hệ thống (44 KB)
└── README.md                             # Hướng dẫn Khởi chạy Hệ thống tại thư mục gốc
```

---

> **SANGRIMAR CAFE & LOUNGE v2.0** – *Sẵn sàng 100% cho buổi bảo vệ đồ án trước Hội đồng Giảng viên Khoa Công nghệ Thông tin.*
