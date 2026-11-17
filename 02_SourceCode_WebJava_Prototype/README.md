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
| `admin` | `123456` | ADMIN | Toàn quyền quản trị hệ thống |
| `manager` | `123456` | MANAGER | Quản lý kho, công thức, xếp ca |
| `staff1` | `123456` | STAFF | Thu ngân, phục vụ, chấm công |
| `staff2` | `123456` | STAFF | Thu ngân, phục vụ, chấm công |
