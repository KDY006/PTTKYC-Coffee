# BÁO CÁO KỸ THUẬT VÀ HIỆN THỰC HỆ THỐNG (SYSTEM TECHNICAL REPORT)
# HỆ THỐNG QUẢN LÝ QUÁN CÀ PHÊ (COFFEE POS & INVENTORY SYSTEM)

> **Mã học phần:** `502050` – Phân tích và Thiết kế Yêu cầu  
> **Khoa:** Công nghệ Thông tin – Trường Đại học Tôn Đức Thắng  
> **Tài liệu:** Báo cáo Đặc tả Kiến trúc Kỹ thuật, Thành phần Đối tượng, Luồng Nghiệp vụ và Cơ chế Bảo mật Hệ thống  
> **Phiên bản:** `1.0.0-RELEASE`  
> **Ngày phát hành:** 19/11/2026  
> **Đơn vị phát triển:** Nhóm sinh viên thực hiện (Duy – Quý – Đạt)  
> **Phân biệt với `BAO_CAO.md`:** Tệp tin này tập trung chuyên sâu vào **toàn bộ khía cạnh kỹ thuật thực tế** có trong mã nguồn (`02_SourceCode_WebJava_Prototype`), cấu trúc cây thư mục, chi tiết các lớp đối tượng Java, DTO, Repository, Service, Controller, lược đồ 10 bảng CSDL MySQL, ma trận phân quyền RBAC 4 tầng, cơ chế kiểm soát đồng thời (Optimistic/Pessimistic Locking), giao dịch nguyên tử `@Transactional` và bảo mật phiên.

---

## MỤC LỤC TỔNG QUAN

1. [TỔNG QUAN CÔNG NGHỆ & NGĂN XẾP PHẦN MỀM (TECH STACK)](#1-tổng-quan-công-nghệ--ngăn-xếp-phần-mềm-tech-stack)
2. [CẤU TRÚC THƯ MỤC DỰ ÁN & TỔ CHỨC MÃ NGUỒN](#2-cấu-trúc-thư-mục-dự-án--tổ-chức-mã-nguồn)
3. [DANH SÁCH & ĐẶC TẢ CHI TIẾT TẤT CẢ CHỨC NĂNG HỆ THỐNG](#3-danh-sách--đặc-tả-chi-tiết-tất-cả-chức-năng-hệ-thống)
4. [KIẾN TRÚC ĐỐI TƯỢNG & MÔ HÌNH DỮ LIỆU CHI TIẾT](#4-kiến-trúc-đối-tượng--mô-hình-dữ-liệu-chi-tiết)
   * 4.1. [Tầng Thực Thể Dữ Liệu (10 JPA Domain Entities)](#41-tầng-thực-thể-dữ-liệu-10-jpa-domain-entities)
   * 4.2. [Tầng Đối Tượng Truyền Dữ Liệu (8 DTOs)](#42-tầng-đối-tượng-truyền-dữ-liệu-8-dtos)
   * 4.3. [Tầng Truy Xuất Dữ Liệu (10 Spring Data JPA Repositories)](#43-tầng-truy-xuất-dữ-liệu-10-spring-data-jpa-repositories)
   * 4.4. [Tầng Nghiệp Vụ Cốt Lõi (3 Core Services)](#44-tầng-nghiệp-vụ-cốt-lõi-3-core-services)
   * 4.5. [Tầng Điều Khiển & Điểm Cuối REST API (9 Controllers + 2 Thành Phần Hỗ Trợ)](#45-tầng-điều-khiển--điểm-cuối-rest-api-9-controllers--2-thành-phần-hỗ-trợ)
   * 4.6. [Lược Đồ Cơ Sở Dữ Liệu Quan Hệ (10 Bảng MySQL 8.0)](#46-lược-đồ-cơ-sở-dữ-liệu-quan-hệ-10-bảng-mysql-80)
5. [CHI TIẾT CÁC LUỒNG HOẠT ĐỘNG & LOGIC NGHIỆP VỤ LÕI](#5-chi-tiết-các-luồng-hoạt-động--logic-nghiệp-vụ-lõi)
   * 5.1. [Luồng Bán Hàng POS & Vòng Đời Đơn Hàng 2 Pha](#51-luồng-bán-hàng-pos--vòng-đời-đơn-hàng-2-pha)
   * 5.2. [Luồng Trừ Kho Tự Động Ngầm & Sắp Xếp Khóa Chống Deadlock](#52-luồng-trừ-kho-tự-động-ngầm--sắp-xếp-khóa-chống-deadlock)
   * 5.3. [Luồng Cảnh Báo Tồn Kho Đỏ & Hồi Phục Khi Nhập Kho](#53-luồng-cảnh-báo-tồn-kho-đỏ--hồi-phục-khi-nhập-kho)
   * 5.4. [Luồng Quản Lý Ca & Chấm Công Nhận Diện Đúng Giờ / Đi Trễ](#54-luồng-quản-lý-ca--chấm-công-nhận-diện-đúng-giờ--đi-trễ)
6. [MA TRẬN PHÂN QUYỀN ĐA CẤP (ROLE-BASED ACCESS CONTROL - RBAC)](#6-ma-trận-phân-quyền-đa-cấp-role-based-access-control---rbac)
   * 6.1. [Bảng Ma Trận Phân Quyền Chi Tiết](#61-bảng-ma-trận-phân-quyền-chi-tiết)
   * 6.2. [Hiện Thực Bảo Vệ 4 Tầng (4-Layer Defense)](#62-hiện-thực-bảo-vệ-4-tầng-4-layer-defense)
7. [CÁC CƠ CHẾ BẢO MẬT & KIỂM SOÁT KỸ THUẬT NÂNG CAO](#7-các-cơ-chế-bảo-mật--kiểm-soát-kỹ-thuật-nâng-cao)
8. [HIỆN TRẠNG KIỂM THỬ TOÀN DIỆN & MINH CHỨNG CHẤT LƯỢNG](#8-hiện-trạng-kiểm-thử-toàn-diện--minh-chứng-chất-lượng)
9. [HƯỚNG DẪN KHỞI CHẠY, TRIỂN KHAI & BẢO TRÌ HỆ THỐNG](#9-hướng-dẫn-khởi-chạy-triển-khai--bảo-trì-hệ-thống)

---

## 1. TỔNG QUAN CÔNG NGHỆ & NGĂN XẾP PHẦN MỀM (TECH STACK)

Hệ thống được thiết kế theo kiến trúc **All-in-One Modular Monolith** hiện đại, kết hợp chặt chẽ giữa Backend Java Spring Boot mạnh mẽ và Frontend Web SPA (Single Page Application) tinh gọn:

```
+-----------------------------------------------------------------------------------+
|                        PRESENTATION TIER (Web Client Browser)                     |
| HTML5 · Bootstrap 5.3.3 · Bootstrap Icons · Google Fonts Roboto · Vanilla JS ES6+ |
|        Unified Topbar Architecture · Toast Notifications · Thermal Receipt        |
+-----------------------------------------------------------------------------------+
                                          │ HTTP REST JSON (Port 8080)
                                          ▼
+-----------------------------------------------------------------------------------+
|                     APPLICATION TIER (Java Spring Boot 3.2.5)                     |
|  - Embedded Apache Tomcat 10.x                                                    |
|  - Spring MVC: REST Controllers, PhanQuyenSession, NgoaiLeControllerAdvice         |
|  - Spring Service: Transactional Core, Deadlock-Free Stock Deduction, Idempotency |
|  - Spring Data JPA / Hibernate 6: ORM Mapping, Optimistic & Pessimistic Locks     |
|  - Connection Pool: HikariCP                                                      |
+-----------------------------------------------------------------------------------+
                                          │ JDBC Protocol (Port 3306)
                                          ▼
+-----------------------------------------------------------------------------------+
|                         DATABASE TIER (MySQL 8.0 / MariaDB)                       |
|   10 Bảng Chuẩn Hóa 3NF · Engine InnoDB · Collation utf8mb4_unicode_ci (ACID)     |
|   (H2 In-Memory Database được kích hoạt độc lập khi chạy @ActiveProfiles("test"))  |
+-----------------------------------------------------------------------------------+
```

### Chi tiết các thông số kỹ thuật:
* **Ngôn ngữ lập trình:** Java (Phiên bản JDK 17+ / JDK 25 LTS tại `F:\JDK`).
* **Framework Backend:** Spring Boot `3.2.5` với các Starter:
  * `spring-boot-starter-web`: Xây dựng RESTful API và máy chủ nhúng Tomcat.
  * `spring-boot-starter-data-jpa`: Tương tác CSDL qua Hibernate ORM và JPA Repositories.
  * `spring-boot-starter-validation`: Kiểm tra hợp lệ dữ liệu đầu vào theo chuẩn JSR-380.
  * `mysql-connector-j`: Trình điều khiển kết nối MySQL chính thức.
  * `spring-boot-starter-test` & `h2`: Môi trường kiểm thử tự động độc lập.
* **Hệ quản trị CSDL:** MySQL 8.0 / MariaDB 10.4 (Tích hợp trong bộ XAMPP tại `C:\xampp\mysql`).
* **Giao diện Client:** HTML5, CSS3 hiện đại (`mossary.css`), JavaScript ES6+ (Fetch API, `async/await`), không phụ thuộc nặng nề vào các framework cồng kềnh (React/Vue/Angular), giúp tối ưu hóa thời gian tải trang đạt mức dưới $50\text{ ms}$.

---

## 2. CẤU TRÚC THƯ MỤC DỰ ÁN & TỔ CHỨC MÃ NGUỒN

Mã nguồn được tổ chức khoa học theo nguyên lý phân tách trách nhiệm (*Separation of Concerns*):

```
D:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype
├── pom.xml                                  # Cấu hình Maven dependencies, Java version, plugins
├── database_mau.sql                         # Kịch bản DDL tạo 10 bảng và DML nạp dữ liệu mẫu sạch
├── mvnw.cmd & mvnw                          # Maven Wrapper thực thi độc lập không cần cài Maven toàn cục
├── .mvn/wrapper/                            # Cấu hình nhị phân Maven Wrapper
│
├── src
│   ├── main
│   │   ├── java/com/cafe
│   │   │   ├── QuanLyCafeApplication.java   # Điểm khởi chạy ứng dụng (@SpringBootApplication)
│   │   │   │
│   │   │   ├── controller                   # TẦNG ĐIỀU KHIỂN & ĐIỂM CUỐI REST API
│   │   │   │   ├── BanController.java       # Quản lý bàn: GET /api/ban
│   │   │   │   ├── CaLamController.java     # Danh mục ca làm: GET /api/ca-lam
│   │   │   │   ├── ChamCongController.java  # Chấm công: POST /check-in, /check-out, GET /lich-su
│   │   │   │   ├── DonHangController.java   # Bán hàng POS: /tao-don, /thanh-toan, /theo-ban, /huy
│   │   │   │   ├── KhoController.java       # Kho & BOM: GET /nguyen-lieu, POST /nhap-kho, /cong-thuc, GET /dashboard
│   │   │   │   ├── MonNuocController.java   # Thực đơn đồ uống: GET /api/mon-nuoc
│   │   │   │   ├── NgoaiLeControllerAdvice.java # Bộ đón bắt và chuẩn hóa ngoại lệ toàn cục (@RestControllerAdvice)
│   │   │   │   ├── NhanSuController.java    # Quản lý nhân viên: CRUD /api/nhan-su/nhan-vien
│   │   │   │   ├── PhanCaController.java    # Phân ca tuần: GET /tuan, POST /, DELETE /{id}
│   │   │   │   ├── PhanQuyenSession.java    # Thành phần phân quyền bảo mật phiên làm việc (RBAC Helper)
│   │   │   │   └── XacThucController.java   # Xác thực: POST /login, GET /me, POST /logout
│   │   │   │
│   │   │   ├── dto                          # ĐỐI TƯỢNG TRUYỀN DỮ LIỆU (DATA TRANSFER OBJECTS)
│   │   │   │   ├── ChamCongDTO.java         # Payload chấm công {maNV, maCa}
│   │   │   │   ├── ChiTietDonHangDTO.java   # Dòng món trong giỏ {maMon, soLuong, ghiChu}
│   │   │   │   ├── CongThucDTO.java         # Payload định mức BOM {maMon, maNL, dinhMuc, ghiChu}
│   │   │   │   ├── DangNhapDTO.java         # Payload đăng nhập {tenDangNhap, matKhau}
│   │   │   │   ├── DonHangDTO.java          # Payload tạo đơn {maBan, loaiDon, danhSachMon, yeuCauTao}
│   │   │   │   ├── NhapKhoDTO.java          # Payload nhập kho {maNL, soLuongNhap}
│   │   │   │   ├── PhanCaDTO.java           # Payload xếp ca {maNV, maCa, ngayLam, ghiChu}
│   │   │   │   └── ThanhToanDTO.java        # Payload thanh toán {chietKhau, phuongThucTT, tienKhachDua, ...}
│   │   │   │
│   │   │   ├── entity                       # CÁC THỰC THỂ DỮ LIỆU JPA (@Entity)
│   │   │   │   ├── Ban.java                 # Thực thể Bàn (10 bàn vật lý)
│   │   │   │   ├── CaLam.java               # Thực thể Ca làm việc (Sáng, Chiều, Tối)
│   │   │   │   ├── ChamCong.java            # Thực thể Chấm công thời gian thực
│   │   │   │   ├── ChiTietHoaDon.java       # Dòng chi tiết hóa đơn (Composition của HoaDon)
│   │   │   │   ├── CongThuc.java            # Định mức công thức pha chế (BOM N-N)
│   │   │   │   ├── HoaDon.java              # Thực thể Hóa đơn bán lẻ (@Version Optimistic Lock)
│   │   │   │   ├── MonNuoc.java             # Thực thể Món nước (Menu đồ uống)
│   │   │   │   ├── NguyenLieu.java          # Thực thể Nguyên liệu tồn kho
│   │   │   │   ├── NhanVien.java            # Thực thể Nhân viên và Tài khoản hệ thống
│   │   │   │   └── PhanCa.java              # Thực thể Lịch phân ca theo ngày
│   │   │   │
│   │   │   ├── repository                   # TẦNG TRUY XUẤT DỮ LIỆU (SPRING DATA JPA)
│   │   │   │   ├── BanRepository.java       # Truy vấn & khóa bi quan Ban
│   │   │   │   ├── CaLamRepository.java     # Truy vấn ca làm việc
│   │   │   │   ├── ChamCongRepository.java  # Truy vấn lịch sử chấm công
│   │   │   │   ├── ChiTietHoaDonRepository.java # Truy vấn món theo hóa đơn
│   │   │   │   ├── CongThucRepository.java  # Truy vấn định mức theo món nước
│   │   │   │   ├── HoaDonRepository.java    # Khóa ghi hóa đơn, kiểm tra Idempotency
│   │   │   │   ├── MonNuocRepository.java   # Lọc thực đơn theo danh mục và từ khóa
│   │   │   │   ├── NguyenLieuRepository.java# Khóa bi quan nguyên liệu, lọc cảnh báo sắp hết
│   │   │   │   ├── NhanVienRepository.java  # Xác thực đăng nhập và lọc vai trò
│   │   │   │   └── PhanCaRepository.java    # Truy vấn lịch tuần và kiểm tra trùng ca
│   │   │   │
│   │   │   └── service                      # TẦNG XỬ LÝ NGHIỆP VỤ LÕI (@Service)
│   │   │       ├── DonHangService.java      # Nghiệp vụ POS, quản lý giỏ hàng, tính giá server & thanh toán
│   │   │       ├── KhoService.java          # Nghiệp vụ trừ kho ngầm, khóa TreeMap, cảnh báo đỏ, KPI
│   │   │       └── NhanSuService.java       # Nghiệp vụ xác thực, quản lý nhân viên, chấm công & phân ca
│   │   │
│   │   └── resources
│   │       ├── application.properties       # Cấu hình Datasource MySQL, Hibernate, UTF-8, Jackson
│   │       └── static                       # TÀI NGUYÊN TĨNH GIAO DIỆN CLIENT
│   │           ├── index.html               # Trang Đăng nhập hệ thống
│   │           ├── banhang.html             # Trang Bán hàng POS, Sơ đồ bàn & Hóa đơn nhiệt
│   │           ├── kho.html                 # Trang Quản lý Kho, BOM & KPI Dashboard
│   │           ├── nhansu.html              # Trang Quản lý Nhân sự, Ca làm việc & Chấm công
│   │           ├── favicon.ico              # Icon tách cà phê chuẩn 16x16 ICO
│   │           ├── css
│   │           │   └── mossary.css          # Giao diện chính thức: Unified Topbar, Bảng màu Mộc An
│   │           ├── js
│   │           │   ├── dung-chung.js        # Module dùng chung: AppShell, Topbar, Session, Formatters
│   │           │   ├── banhang-page.js      # Nghiệp vụ POS: Chọn bàn, Menu, Giỏ hàng, Khóa chiết khấu
│   │           │   ├── kho-page.js          # Nghiệp vụ Kho: Bảng tồn, Nhập kho, Lập BOM, Cảnh báo đỏ
│   │           │   └── nhansu-page.js       # Nghiệp vụ Nhân sự: Danh sách NV, Ca kíp, Check-in/out
│   │           └── assets/images/           # Hình ảnh đồ uống thực tế sắc nét (WebP / JPG)
│   │
│   └── test                                 # TẦNG KIỂM THỬ TỰ ĐỘNG
│       ├── java/com/cafe
│       │   ├── DonHangIntegrationTest.java  # 14 ca kiểm thử tích hợp POS, Concurrency, Stock Rollback
│       │   └── NhanSuIntegrationTest.java   # 3 ca kiểm thử Nhân sự, Chấm công, Phân quyền API Kho 403
│       └── resources
│           └── application-test.properties  # Cấu hình CSDL H2 In-Memory tách biệt cho Test
```

---

## 3. DANH SÁCH & ĐẶC TẢ CHI TIẾT TẤT CẢ CHỨC NĂNG HỆ THỐNG

Toàn bộ hệ thống bao gồm **6 nhóm phân hệ chức năng hoàn chỉnh**:

### 3.1. Phân hệ 1: Xác thực Người dùng & Quản trị Phiên (Authentication)
* **Đăng nhập hệ thống (`POST /api/auth/login`):** Tiếp nhận tên đăng nhập và mật khẩu từ `DangNhapDTO`. Máy chủ đối chiếu trong bảng `nhan_vien`, kiểm tra trạng thái hoạt động (`DANG_LAM`). Khi xác thực thành công, máy chủ tạo một `HttpSession` độc lập phía server, lưu vết `maNV` và `vaiTro`.
* **Ẩn thông tin nhạy cảm:** Mật khẩu người dùng được cấu hình `@JsonProperty(access = Access.WRITE_ONLY)`, bảo đảm chuỗi mật khẩu không bao giờ bị trả về client trong phản hồi JSON.
* **Tra cứu thông tin phiên hiện tại (`GET /api/auth/me`):** Phục vụ giao diện kiểm tra trạng thái đăng nhập khi người dùng tải lại trang (*Page Reload*).
* **Đăng xuất an toàn (`POST /api/auth/logout`):** Hủy bỏ hoàn toàn phiên làm việc `session.invalidate()` trên máy chủ Spring Boot, đồng thời script Client xóa sạch khóa lưu trữ `localStorage` (`mocan_java_user`, `mocan_java_table_carts`).

### 3.2. Phân hệ 2: Bán hàng POS & Quản lý Sơ đồ Bàn Động (POS Terminal)
* **Sơ đồ 10 bàn trực quan:** Hiển thị trực quan trạng thái thực tế của 10 bàn (`Bàn 01` đến `Bàn 10`). Bàn trống có viền xanh lá (`TRONG`), bàn đang phục vụ có viền đỏ nổi bật kèm mã hóa đơn (`CO_KHACH`).
* **Đơn mang đi (Takeaway):** Hỗ trợ tạo đơn mang đi độc lập, không gắn với bất kỳ mã bàn vật lý nào.
* **Mở lại đơn đang phục vụ:** Nhấp vào một bàn đang có khách, hệ thống gửi request `GET /api/order/theo-ban/{id}` và khôi phục toàn bộ danh sách món đã gọi trước đó lên giỏ hàng màn hình POS.
* **Thực đơn món uống & Bộ lọc tìm kiếm:** Menu đồ uống được phân loại rõ ràng (Cà phê, Trà, Đá xay, Bánh pastry) kèm hình ảnh thực tế và giá tiền. Hỗ trợ tìm kiếm tức thì theo tên món không phân biệt chữ hoa/thường.
* **Tùy biến món nước:** Cho phép thêm bớt số lượng ($1 - 99$) và nhập ghi chú pha chế chuyên biệt (ví dụ: *"Ít đá, 50% đường"*, *"Nóng"*).
* **Lưu đơn hàng (`POST /api/order/tao-don`):** Tạo bản ghi `HoaDon` với trạng thái `CHO_THANH_TOAN`, chụp ảnh (*Snapshot*) đơn giá bán vào `chi_tiet_hoa_don`, chuyển trạng thái bàn sang `CO_KHACH`.
* **Cập nhật đơn hàng (`PUT /api/order/{id}/mon`):** Cho phép thu ngân gọi thêm món hoặc điều chỉnh số lượng cho bàn đang ngồi trước khi thanh toán.
* **Hủy đơn hàng (`POST /api/order/{id}/huy`):** Hủy đơn hàng đang chờ khi khách đổi ý ra về, giải phóng bàn về `TRONG` và không phát sinh trừ kho.
* **Thanh toán & Xuất hóa đơn nhiệt (`POST /api/order/{id}/thanh-toan`):**
  * Hỗ trợ thanh toán Tiền mặt (kiểm tra tiền khách đưa $\ge$ tổng tiền, tự động tính tiền thối).
  * Hỗ trợ thanh toán Chuyển khoản QR (hiển thị VietQR, yêu cầu thu ngân tích xác nhận tiền vào tài khoản và nhập mã tham chiếu giao dịch).
  * Chuyển hóa đơn sang trạng thái `DA_THANH_TOAN`, tự động giải phóng bàn về `TRONG`, hiển thị Popup Hóa đơn nhiệt (Thermal Receipt) chuẩn phong cách quán cà phê Mộc An.

### 3.3. Phân hệ 3: Quản trị Kho Nguyên Liệu & Định Mức BOM (Inventory & BOM)
* **Theo dõi danh mục nguyên liệu (`GET /api/kho/nguyen-lieu`):** Hiển thị chi tiết tên nguyên liệu, đơn vị tính ($ml, g, \text{hộp}, \text{gói}$), số lượng tồn thực tế, ngưỡng tồn tối thiểu an toàn và đơn giá nhập.
* **Thiết lập bảng định mức công thức pha chế BOM (`POST /api/kho/cong-thuc`):** Cấu hình định mức tiêu hao nguyên liệu cho từng món nước (Ví dụ: 1 ly *Cà phê sữa đá* tiêu tốn $25\text{ ml}$ Cốt cà phê, $30\text{ ml}$ Sữa đặc, $15\text{ ml}$ Sữa tươi).
* **Tra cứu công thức theo món (`GET /api/kho/cong-thuc/{maMon}`):** Cho phép quản lý kiểm tra các nguyên liệu thành phần tạo nên đồ uống.
* **Luồng trừ kho tự động ngầm khi thanh toán:** Tích hợp trong cùng giao dịch `@Transactional` với bước thanh toán đơn hàng. Tự động quy đổi số lượng món bán ra nhân với định mức công thức để trừ lùi tồn kho.
* **Cơ chế Cảnh báo tồn kho đỏ (`SẮP HẾT HÀNG`):** Tự động phát hiện khi số lượng tồn kho $\le$ mức tồn tối thiểu an toàn, lập tức kích hoạt nhãn đỏ cảnh báo trên màn hình Kho và hiển thị số lượng cảnh báo trên huy hiệu Topbar.
* **Nhập thêm nguyên liệu vào kho (`POST /api/kho/nhap-kho`):** Hỗ trợ nhập thêm số lượng thực tế, cộng dồn vào CSDL và tự động khôi phục nhãn trạng thái xanh `Đủ hàng`.

### 3.4. Phân hệ 4: Quản trị Nhân sự & Chấm Công Thời Gian Thực (HR & Shifts)
* **Quản lý danh sách nhân sự (`GET /api/nhan-su/nhan-vien`):** Hiển thị danh sách nhân viên, tài khoản, số điện thoại, vai trò và trạng thái làm việc.
* **Thêm hồ sơ nhân viên mới (`POST /api/nhan-su/nhan-vien`):** Cho phép Quản trị viên (Admin) tạo tài khoản mới với đầy đủ kiểm tra hợp lệ định dạng tên đăng nhập, độ dài mật khẩu và số điện thoại.
* **Cập nhật & Ngừng việc nhân viên (`PUT /nhan-vien/{id}` & `DELETE /nhan-vien/{id}`):** Hỗ trợ chỉnh sửa thông tin hoặc chuyển trạng thái sang `DA_NGHI` để khóa quyền truy cập.
* **Danh mục ca làm việc (`GET /api/ca-lam`):** Quản lý các ca làm việc tiêu chuẩn: Ca Sáng ($07:00 - 12:00$), Ca Chiều ($12:00 - 17:00$), Ca Tối ($17:00 - 22:00$).
* **Xếp lịch phân ca tuần (`GET /api/phan-ca/tuan` & `POST /api/phan-ca`):** Hỗ trợ phân công nhân viên vào các ca làm việc theo ngày trong tuần, có cơ chế chặn xếp ca cho nhân viên đã nghỉ việc hoặc ngày trong quá khứ.
* **Chấm công Check-in thông minh (`POST /api/cham-cong/check-in`):** Nhân viên chọn ca làm việc; hệ thống kiểm tra điều kiện nhân viên đã có lịch phân ca (`phan_ca`) trong ngày và lấy giờ đồng hồ máy chủ đối chiếu với giờ bắt đầu ca:
  * Bắt buộc nhân viên phải được phân công trong lịch ca của ngày (`phanCaRepository.findBy...`).
  * Nếu check-in trước giờ hoặc trễ $\le 15$ phút: Tự động ghi nhận trạng thái `DUNG_GIO` (Đúng giờ).
  * Nếu check-in trễ quá 15 phút: Tự động ghi nhận trạng thái `DI_TRE` (Đi trễ).
  * Chặn check-in 2 lần trong cùng một ca làm việc của ngày (ném `409 Conflict`).
* **Chấm công Check-out (`POST /api/cham-cong/check-out/{id}`):** Ghi nhận thời gian kết thúc ca làm việc thực tế của nhân viên.
* **Lịch sử chấm công (`GET /api/cham-cong/lich-su`):** Cho phép nhân viên xem lại công của mình và Quản lý xem toàn bộ lịch sử chấm công cửa hàng theo khoảng ngày.

### 3.5. Phân hệ 5: Bảng Điều Khiển KPI & Báo Cáo Doanh Thu (Dashboard)
* **Thống kê KPI Kho & Vận hành (`GET /api/kho/dashboard`):** Endpoint backend tổng hợp dữ liệu thời gian thực: `tongDoanhThu` (tổng doanh thu các đơn `DA_THANH_TOAN`), `tongDonHang` (tổng đơn hoàn tất), `soNguyenLieuSapHet` và `danhSachSapHet` (nguyên liệu tồn kho $\le$ mức tối thiểu).
* **Hiển thị 4 thẻ KPI trên giao diện Kho (`kho-page.js`):** Phối hợp giữa API Dashboard và API nguyên liệu để trực quan hóa:
  1. *Doanh thu hôm nay (VNĐ):* Hiển thị từ `dashboard.tongDoanhThu`.
  2. *Tổng đơn đã thanh toán:* Hiển thị từ `dashboard.tongDonHang`.
  3. *Nguyên liệu cảnh báo:* Số mặt hàng chạm hoặc giảm dưới ngưỡng an toàn (`lows.length`).
  4. *Tổng nguyên liệu:* Quy mô toàn bộ danh mục kho (`ingredients.length` từ `GET /api/kho/nguyen-lieu`).

### 3.6. Phân hệ 6: Xử Lý Lỗi Tập Trung & Phản Hồi Trải Nghiệm (Error Handling & UX)
* **Xử lý ngoại lệ toàn cục (`NgoaiLeControllerAdvice`):** Đón bắt mọi lỗi `ResponseStatusException`, `DataIntegrityViolationException`, `ConcurrencyFailureException` và `RuntimeException`, chuẩn hóa thành mã lỗi HTTP và thông điệp JSON thân thiện với người dùng.
* **Thông báo Toast không khóa luồng (`#liveToast`):** Thay thế toàn bộ hộp thoại `window.alert()` cũ bằng thông báo Bootstrap 5 Toast ở góc màn hình, tự động biến mất sau 3 giây, giữ luồng thao tác của thu ngân luôn mượt mà.
* **Nút Đồng bộ tức thì (`#btn-refresh-mysql`):** Cho phép nạp lại toàn bộ dữ liệu mới nhất từ CSDL MySQL về giao diện web chỉ với một cú nhấp chuột mà không cần tải lại toàn bộ trang web.

---

## 4. KIẾN TRÚC ĐỐI TƯỢNG & MÔ HÌNH DỮ LIỆU CHI TIẾT

### 4.1. Tầng Thực Thể Dữ Liệu (10 JPA Domain Entities)

```
                                  +------------------+
                                  |     NhanVien     |
                                  +------------------+
                                  | - maNV: Integer  |
                                  | - vaiTro: String |
                                  +------------------+
                                       ▲        ▲
                          lap boi      │        │ phan cong
                       ┌───────────────┘        └────────────────┐
                       │                                         │
              +------------------+                      +------------------+
              |      HoaDon      |                      |      PhanCa      |
              +------------------+                      +------------------+
              | - maHoaDon: int  |                      | - maPhanCa: int  |
              | - trangThai: str |                      | - ngayLam: Date  |
              +------------------+                      +------------------+
                       │ 1                                       │
            Cascade    │                                         │ thuoc ca
            Composition│                                         ▼
                       ▼ 1..*                           +------------------+
              +------------------+                      |      CaLam       |
              |  ChiTietHoaDon   |                      +------------------+
              +------------------+                      | - maCa: Integer  |
              | - soLuong: int   |                      | - gioBatDau: Time|
              | - donGiaBan: dbl |                      +------------------+
              +------------------+                               ▲
                       │ tham chieu                              │
                       ▼                                         │ thuoc ca
              +------------------+                      +------------------+
              |     MonNuoc      |                      |     ChamCong     |
              +------------------+                      +------------------+
              | - maMon: Integer |                      | - gioCheckIn: DT |
              | - donGia: Double |                      | - trangThai: str |
              +------------------+                      +------------------+
                       │ 1                                       ▲
              BOM      │                                         │ cham cong
           Association │                                         │
                       ▼ 0..*                           +------------------+
              +------------------+                      |  nhan_vien (NV)  |
              |     CongThuc     |                      +------------------+
              +------------------+
              | - dinhMuc: Double|
              +------------------+
                       ▲ 0..*
           dung trong  │
                       │ 1
              +------------------+
              |    NguyenLieu    |
              +------------------+
              | - soLuongTon: dbl|
              | - mucTonMin: dbl |
              +------------------+
```

1. **`NhanVien` (`com.cafe.entity.NhanVien`):**
   * Bảng CSDL: `nhan_vien`.
   * Các trường: `maNV` (`Integer`, PK, Auto-increment), `hoTen` (`String`, max 100), `soDienThoai` (`String`, max 15), `tenDangNhap` (`String`, unique, max 50), `matKhau` (`String`, max 255, `@JsonProperty(access = WRITE_ONLY)`), `vaiTro` (`String`: `ADMIN`, `MANAGER`, `STAFF`), `trangThai` (`String`: `DANG_LAM`, `DA_NGHI`).
2. **`Ban` (`com.cafe.entity.Ban`):**
   * Bảng CSDL: `ban`.
   * Các trường: `maBan` (`Integer`, PK, Auto-increment), `tenBan` (`String`, max 50), `viTri` (`String`, max 50), `trangThai` (`String`: `TRONG`, `CO_KHACH`).
3. **`MonNuoc` (`com.cafe.entity.MonNuoc`):**
   * Bảng CSDL: `mon_nuoc`.
   * Các trường: `maMon` (`Integer`, PK, Auto-increment), `tenMon` (`String`, max 100), `danhMuc` (`String`, max 50), `donGia` (`Double`), `hinhAnh` (`String`, max 255), `trangThai` (`String`: `DANG_BAN`, `HET_HANG`).
4. **`HoaDon` (`com.cafe.entity.HoaDon`):**
   * Bảng CSDL: `hoa_don`.
   * Các trường: `maHoaDon` (`Integer`, PK), `ban` (`@ManyToOne Ban`, nullable), `nhanVien` (`@ManyToOne NhanVien`), `thoiGianTao` (`LocalDateTime`), `thoiGianThanhToan` (`LocalDateTime`), `loaiDon` (`String`: `TAI_BAN`, `MANG_DI`), `tongTienGoc` (`Double`), `chietKhau` (`Double`), `tongTienThanhToan` (`Double`), `tienKhachDua` (`Double`), `phuongThucTT` (`String`: `TIEN_MAT`, `CHUYEN_KHOAN`), `maThamChieu` (`String`, unique), `trangThai` (`String`: `CHO_THANH_TOAN`, `DA_THANH_TOAN`, `DA_HUY`), `yeuCauTao` (`String(36)`, UUID unique), `yeuCauThanhToan` (`String(36)`, UUID unique), `phienBan` (`@Version Long`, phục vụ Khóa lạc quan).
   * Mối quan hệ: `@OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true)` với `ChiTietHoaDon`.
5. **`ChiTietHoaDon` (`com.cafe.entity.ChiTietHoaDon`):**
   * Bảng CSDL: `chi_tiet_hoa_don`.
   * Các trường: `maCTHD` (`Integer`, PK), `hoaDon` (`@ManyToOne HoaDon`), `monNuoc` (`@ManyToOne MonNuoc`), `soLuong` (`Integer`), `donGiaBan` (`Double`, Snapshot giá tại thời điểm bán), `thanhTien` (`Double`), `ghiChu` (`String`, max 255).
6. **`NguyenLieu` (`com.cafe.entity.NguyenLieu`):**
   * Bảng CSDL: `nguyen_lieu`.
   * Các trường: `maNL` (`Integer`, PK), `tenNL` (`String`, max 100), `donViTinh` (`String`, max 20), `soLuongTon` (`Double`), `mucTonToiThieu` (`Double`), `donGiaNhap` (`Double`).
   * Phương thức nghiệp vụ: `isSapHet()` $\rightarrow$ trả về `true` khi `soLuongTon <= mucTonToiThieu`.
7. **`CongThuc` (`com.cafe.entity.CongThuc`):**
   * Bảng CSDL: `cong_thuc` (Association Class nối giữa `MonNuoc` và `NguyenLieu`).
   * Các trường: `maCongThuc` (`Integer`, PK), `monNuoc` (`@ManyToOne MonNuoc`), `nguyenLieu` (`@ManyToOne NguyenLieu`), `dinhMuc` (`Double`), `ghiChu` (`String`).
8. **`CaLam` (`com.cafe.entity.CaLam`):**
   * Bảng CSDL: `ca_lam`.
   * Các trường: `maCa` (`Integer`, PK), `tenCa` (`String`, max 50), `gioBatDau` (`LocalTime`), `gioKetThuc` (`LocalTime`), `heSoLuong` (`Double`).
9. **`ChamCong` (`com.cafe.entity.ChamCong`):**
   * Bảng CSDL: `cham_cong`.
   * Các trường: `maChamCong` (`Integer`, PK), `nhanVien` (`@ManyToOne NhanVien`), `caLam` (`@ManyToOne CaLam`), `ngayLam` (`LocalDate`), `gioCheckIn` (`LocalDateTime`), `gioCheckOut` (`LocalDateTime`), `trangThai` (`String`: `DUNG_GIO`, `DI_TRE`, `VANG`), `ghiChu` (`String`).
10. **`PhanCa` (`com.cafe.entity.PhanCa`):**
    * Bảng CSDL: `phan_ca`.
    * Ràng buộc duy nhất: `uk_phan_ca_nhan_vien_ngay_ca` (`ma_nv`, `ngay_lam`, `ma_ca`).
    * Các trường: `maPhanCa` (`Integer`, PK), `nhanVien` (`@ManyToOne NhanVien`), `caLam` (`@ManyToOne CaLam`), `ngayLam` (`LocalDate`), `ghiChu` (`String`).

### 4.2. Tầng Đối Tượng Truyền Dữ Liệu (8 DTOs)
* `DangNhapDTO`: `{ tenDangNhap, matKhau }` – Xác thực tài khoản.
* `DonHangDTO`: `{ maBan, maNV, loaiDon, danhSachMon, yeuCauTao, phienBan }` – Tạo mới và cập nhật đơn hàng.
* `ChiTietDonHangDTO`: `{ maMon, soLuong, ghiChu }` – Dòng món nước trong đơn.
* `ThanhToanDTO`: `{ chietKhau, phuongThucTT, tienKhachDua, xacNhanChuyenKhoan, maThamChieu, yeuCauThanhToan, phienBan }` – Dữ liệu thanh toán.
* `NhapKhoDTO`: `{ maNL, soLuongNhap }` – Nhập thêm nguyên liệu.
* `CongThucDTO`: `{ maMon, maNL, dinhMuc, ghiChu }` – Thiết lập định mức BOM.
* `ChamCongDTO`: `{ maNV, maCa }` – Ghi nhận giờ vào ca.
* `PhanCaDTO`: `{ maNV, maCa, ngayLam, ghiChu }` – Phân ca làm việc.

### 4.3. Tầng Truy Xuất Dữ Liệu (10 Spring Data JPA Repositories)
Mỗi Repository kế thừa `JpaRepository<T, ID>` và được bổ sung các phương thức truy vấn tối ưu hóa:
* `BanRepository`: `khoaBan(Integer id)` sử dụng `@Lock(LockModeType.PESSIMISTIC_WRITE)` để khóa dòng bàn khi tạo đơn.
* `HoaDonRepository`: `khoaHoaDon(Integer id)` sử dụng `@Lock(LockModeType.PESSIMISTIC_WRITE)` để khóa đơn hàng khi thanh toán; `existsByMaThamChieu(String ref)` ngăn chặn trùng lặp mã chuyển khoản; `findByYeuCauTao(String key)` kiểm tra tính bất biến (Idempotency).
* `NguyenLieuRepository`: `khoaNguyenLieu(Integer id)` sử dụng `@Lock(LockModeType.PESSIMISTIC_WRITE)` để khóa nguyên liệu khi trừ kho; `findNguyenLieuSapHet()` dùng câu lệnh `@Query("SELECT nl FROM NguyenLieu nl WHERE nl.soLuongTon <= nl.mucTonToiThieu")`.
* `MonNuocRepository`: Lọc theo trạng thái kinh doanh `findByTrangThai("DANG_BAN")` và tìm kiếm tên không phân biệt hoa thường `findByTenMonContainingIgnoreCase(String q)`.
* `ChamCongRepository`: `findByNhanVien_MaNVAndCaLam_MaCaAndNgayLam(...)` ngăn chặn nhân viên check-in trùng ca trong ngày.
* `PhanCaRepository`: `findByNgayLamBetweenOrderByNgayLamAsc(...)` lấy lịch làm việc theo khoảng tuần.

### 4.4. Tầng Nghiệp Vụ Cốt Lõi (3 Core Services)

#### 1. `KhoService` (`com.cafe.service.KhoService`):
* `truKhoTuDong(HoaDon hoaDon)`: Đánh dấu `@Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)`. Đảm bảo luồng trừ kho bắt buộc phải chạy trong cùng transaction với luồng thanh toán đơn hàng.
  * Gom toàn bộ nguyên liệu cần tiêu hao vào `TreeMap<Integer, BigDecimal>` sắp xếp theo `maNL` tăng dần.
  * Lần lượt lấy khóa bi quan từng nguyên liệu, kiểm tra `soLuongTon >= nhuCau`.
  * Nếu thiếu bất kỳ nguyên liệu nào: Ném ngoại lệ `IllegalArgumentException` kích hoạt Rollback toàn bộ giao dịch.
  * Nếu đủ: Trừ lui số lượng tồn và lưu vào CSDL.
* `nhapKho(Integer maNL, Double soLuongNhap)`: Khóa nguyên liệu và cộng dồn số lượng nhập.
* `thietLapCongThuc(CongThucDTO dto)`: Thiết lập hoặc cập nhật định mức BOM cho món.
* `getDashboardKPI()`: Tổng hợp doanh thu ngày, tổng đơn và số mặt hàng chạm ngưỡng báo động.

#### 2. `DonHangService` (`com.cafe.service.DonHangService`):
* Đánh dấu mặc định `@Transactional(readOnly = true)`.
* `taoDonHang(DonHangDTO dto)`: Đánh dấu `@Transactional(rollbackFor = Exception.class)`. Kiểm tra Idempotency key `yeuCauTao`. Khóa bàn vật lý `khoaBan(maBan)`, sao chép đơn giá món hiện tại vào `donGiaBan`, lưu hóa đơn với trạng thái `CHO_THANH_TOAN` và chuyển bàn sang `CO_KHACH`.
* `thanhToanDonHang(Integer id, ThanhToanDTO dto)`: Đánh dấu `@Transactional(rollbackFor = Exception.class)`. Khóa hóa đơn `khoaHoaDon(id)`. Kiểm tra khóa lạc quan `phienBan`. Kiểm tra chiết khấu và tiền khách đưa. Gọi `khoService.truKhoTuDong(h)`. Cập nhật trạng thái `DA_THANH_TOAN`, lưu `yeuCauThanhToan` và giải phóng bàn về `TRONG`.
* `huyDonHang(Integer id, Long version)`: Chuyển trạng thái đơn sang `DA_HUY` và giải phóng bàn.

#### 3. `NhanSuService` (`com.cafe.service.NhanSuService`):
* `dangNhap(DangNhapDTO dto)`: Xác thực tài khoản và mật khẩu.
* `checkIn(Integer maNhanVienDangNhap, ChamCongDTO dto)`: Đánh dấu `@Transactional`. Kiểm tra không cho phép check-in trùng ca trong ngày. Tính toán khoảng cách phút so với giờ bắt đầu ca để gán nhãn `DUNG_GIO` (nếu trễ $\le 15$ phút) hoặc `DI_TRE` (nếu trễ $> 15$ phút).
* `checkOut(Integer maNhanVienDangNhap, boolean quanLy, Integer maChamCong)`: Ghi nhận giờ kết thúc ca làm việc thực tế `LocalDateTime.now()`.
* `phanCa(PhanCaDTO dto)` & `lichTuan(...)`: Quản lý phân ca làm việc theo tuần.

### 4.5. Tầng Điều Khiển & Điểm Cuối REST API (9 Controllers + 2 Thành Phần Hỗ Trợ)

Tầng Web API bao gồm 9 `@RestController` phục vụ các phân hệ chức năng, 1 `@Component` phân quyền phiên (`PhanQuyenSession`) và 1 `@RestControllerAdvice` xử lý lỗi tập trung (`NgoaiLeControllerAdvice`):

| Thành phần / Controller | Request Mapping | Endpoint chính & Phương thức | Vai trò bảo vệ qua `PhanQuyenSession` |
| :--- | :--- | :--- | :--- |
| `XacThucController` | `/api/auth`, `/api/xac-thuc` | `POST /login`<br>`GET /me`<br>`POST /logout` | Public cho login; kiểm tra phiên cho `/me` và hủy phiên cho `/logout`. |
| `BanController` | `/api/ban` | `GET /` | Bắt buộc đăng nhập (`access.userId(session)`). |
| `MonNuocController` | `/api/mon-nuoc` | `GET /` (hỗ trợ param `danhMuc`, `q`) | Bắt buộc đăng nhập (`access.userId(session)`). |
| `DonHangController` | `/api/order`, `/api/don-hang` | `GET /danh-sach`<br>`GET /{id}`<br>`GET /theo-ban/{id}`<br>`GET /theo-yeu-cau/{key}`<br>`POST /tao-don`<br>`PUT /{id}/mon`<br>`POST /{id}/thanh-toan`<br>`POST /{id}/huy` | Kiểm tra tài khoản bán hàng (`STAFF`, `MANAGER`, `ADMIN`). Chặn `STAFF` nhập chiết khấu $> 0$ (trả về HTTP 403 Forbidden). |
| `KhoController` | `/api/kho` | `GET /nguyen-lieu`<br>`GET /sap-het`<br>`POST /nhap-kho`<br>`POST /cong-thuc`<br>`GET /cong-thuc/{maMon}`<br>`GET /dashboard` | Các API quản trị kho (`/sap-het`, `/nhap-kho`, `/cong-thuc`, `/dashboard`) yêu cầu vai trò `access.requireManager(session)`. Staff truy cập trả về HTTP 403. |
| `NhanSuController` | `/api/nhan-su` | `GET /nhan-vien`<br>`POST /nhan-vien`<br>`PUT /nhan-vien/{id}`<br>`DELETE /nhan-vien/{id}`<br>`GET /ca-lam` | Xem danh sách nhân viên yêu cầu `MANAGER`/`ADMIN`. Thêm/sửa/ngừng việc nhân viên yêu cầu tối cao `access.requireAdmin(session)`. |
| `CaLamController` | `/api/ca-lam` | `GET /` | Bắt buộc đăng nhập (`access.userId(session)`). |
| `ChamCongController`| `/api/cham-cong`| `POST /check-in`<br>`POST /check-out/{id}`<br>`GET /lich-su` | Nhân viên check-in/out cho chính mình; Quản lý được phép check-out hộ và xem toàn bộ lịch sử. |
| `PhanCaController` | `/api/phan-ca` | `POST /`<br>`GET /tuan`<br>`DELETE /{id}` | Xếp ca và xóa phân ca yêu cầu `access.requireManager(session)`. |
| `PhanQuyenSession` | *(Component)* | `userId()`, `role()`, `requireManager()`, `requireAdmin()` | Thành phần kiểm tra quyền hạn phiên tập trung (RBAC Helper). |
| `NgoaiLeControllerAdvice` | *(@RestControllerAdvice)* | `@ExceptionHandler` xử lý các lớp ngoại lệ toàn cục | Chuẩn hóa mã lỗi HTTP 400, 401, 403, 404, 409 thành JSON có cấu trúc. |

### 4.6. Lược Đồ Cơ Sở Dữ Liệu Quan Hệ (10 Bảng MySQL 8.0)

Toàn bộ 10 bảng CSDL trong [database_mau.sql](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/database_mau.sql) được thiết lập Engine `InnoDB` hỗ trợ đầy đủ khóa ngoại và tính chất giao dịch ACID:

```sql
-- 1. BẢNG NHÂN VIÊN
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
    `trang_thai` VARCHAR(20) NOT NULL, -- DUNG_GIO, DI_TRE, VANG
    `ghi_chu` VARCHAR(255),
    CONSTRAINT `fk_cc_nhanvien` FOREIGN KEY (`ma_nv`) REFERENCES `nhan_vien`(`ma_nv`) ON DELETE CASCADE,
    CONSTRAINT `fk_cc_calam` FOREIGN KEY (`ma_ca`) REFERENCES `ca_lam`(`ma_ca`) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 4. BẢNG PHÂN CA THEO NGÀY
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

-- 5. BẢNG BÀN
CREATE TABLE `ban` (
    `ma_ban` INT AUTO_INCREMENT PRIMARY KEY,
    `ten_ban` VARCHAR(50) NOT NULL,
    `vi_tri` VARCHAR(50),
    `trang_thai` VARCHAR(20) NOT NULL DEFAULT 'TRONG' -- TRONG, CO_KHACH
) ENGINE=InnoDB;

-- 6. BẢNG MÓN NƯỚC
CREATE TABLE `mon_nuoc` (
    `ma_mon` INT AUTO_INCREMENT PRIMARY KEY,
    `ten_mon` VARCHAR(100) NOT NULL,
    `danh_muc` VARCHAR(50) NOT NULL,
    `don_gia` DOUBLE NOT NULL,
    `hinh_anh` VARCHAR(255),
    `trang_thai` VARCHAR(20) NOT NULL DEFAULT 'DANG_BAN'
) ENGINE=InnoDB;

-- 7. BẢNG HÓA ĐƠN
CREATE TABLE `hoa_don` (
    `ma_hoa_don` INT AUTO_INCREMENT PRIMARY KEY,
    `ma_ban` INT NULL,
    `ma_nv` INT NOT NULL,
    `thoi_gian_tao` DATETIME NOT NULL,
    `thoi_gian_thanh_toan` DATETIME,
    `loai_don` VARCHAR(20) NOT NULL DEFAULT 'TAI_BAN',
    `tong_tien_goc` DOUBLE NOT NULL DEFAULT 0,
    `chiet_khau` DOUBLE NOT NULL DEFAULT 0,
    `tong_tien_thanh_toan` DOUBLE NOT NULL DEFAULT 0,
    `tien_khach_dua` DOUBLE,
    `phuong_thuc_tt` VARCHAR(20) DEFAULT 'TIEN_MAT',
    `ma_tham_chieu` VARCHAR(100) UNIQUE,
    `trang_thai` VARCHAR(20) NOT NULL DEFAULT 'CHO_THANH_TOAN',
    `yeu_cau_tao` VARCHAR(36) UNIQUE,
    `yeu_cau_thanh_toan` VARCHAR(36) UNIQUE,
    `phien_ban` BIGINT DEFAULT 0,
    CONSTRAINT `fk_hd_ban` FOREIGN KEY (`ma_ban`) REFERENCES `ban`(`ma_ban`) ON DELETE SET NULL,
    CONSTRAINT `fk_hd_nhanvien` FOREIGN KEY (`ma_nv`) REFERENCES `nhan_vien`(`ma_nv`)
) ENGINE=InnoDB;

-- 8. BẢNG CHI TIẾT HÓA ĐƠN (COMPOSITION)
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

-- 9. BẢNG NGUYÊN LIỆU
CREATE TABLE `nguyen_lieu` (
    `ma_nl` INT AUTO_INCREMENT PRIMARY KEY,
    `ten_nl` VARCHAR(100) NOT NULL,
    `don_vi_tinh` VARCHAR(20) NOT NULL,
    `so_luong_ton` DOUBLE NOT NULL DEFAULT 0,
    `muc_ton_toi_thieu` DOUBLE NOT NULL DEFAULT 100,
    `don_gia_nhap` DOUBLE NOT NULL DEFAULT 0
) ENGINE=InnoDB;

-- 10. BẢNG CÔNG THỨC (ASSOCIATION CLASS)
CREATE TABLE `cong_thuc` (
    `ma_cong_thuc` INT AUTO_INCREMENT PRIMARY KEY,
    `ma_mon` INT NOT NULL,
    `ma_nl` INT NOT NULL,
    `dinh_muc` DOUBLE NOT NULL,
    `ghi_chu` VARCHAR(255),
    CONSTRAINT `fk_ct_monnuoc` FOREIGN KEY (`ma_mon`) REFERENCES `mon_nuoc`(`ma_mon`) ON DELETE CASCADE,
    CONSTRAINT `fk_ct_nguyenlieu` FOREIGN KEY (`ma_nl`) REFERENCES `nguyen_lieu`(`ma_nl`) ON DELETE CASCADE
) ENGINE=InnoDB;
```

---

## 5. CHI TIẾT CÁC LUỒNG HOẠT ĐỘNG & LOGIC NGHIỆP VỤ LÕI

### 5.1. Luồng Bán Hàng POS & Vòng Đời Đơn Hàng 2 Pha
Hệ thống áp dụng mô hình 2 pha (*Two-Phase POS Workflow*) mô phỏng chuẩn xác vận hành của một quán cà phê thực tế:

```
                  ┌──────────────────────┐
                  │    Bàn Trống (Xanh)  │
                  └──────────┬───────────┘
                             │ Thu ngân chọn bàn, chọn món
                             │ Bấm "Lưu đơn" (POST /api/order/tao-don)
                             ▼
                  ┌──────────────────────┐
                  │ Hóa đơn Đang Mở      │ ◄─── Có thể thêm/sửa món
                  │ (CHO_THANH_TOAN)     │ ◄─── Mở lại giỏ hàng cũ khi bấm vào bàn
                  │ Bàn Có Khách (Đỏ)    │
                  └──────────┬───────────┘
                             │
            ┌────────────────┴────────────────┐
            │ Khách thanh toán                │ Khách hủy đơn (ra về)
            ▼                                 ▼
┌───────────────────────────┐     ┌───────────────────────────┐
│  Hóa đơn ĐÃ THANH TOÁN    │     │   Hóa đơn ĐÃ HỦY (DA_HUY) │
│  - Trừ kho tự động        │     │   - Không trừ kho         │
│  - Bàn trở về TRỐNG (Xanh)│     │   - Bàn trở về TRỐNG      │
│  - Xuất hóa đơn nhiệt     │     └───────────────────────────┘
└───────────────────────────┘
```

1. **Pha 1 – Gọi món & Lưu đơn:**
   * Thu ngân chọn bàn trống $\rightarrow$ Chọn món từ menu $\rightarrow$ Nhập ghi chú pha chế $\rightarrow$ Nhấn "Lưu đơn".
   * Client sinh UUID `yeuCauTao` gửi lên `POST /api/order/tao-don`.
   * Server khóa bàn bằng `SELECT ... FOR UPDATE`, tạo `HoaDon` với trạng thái `CHO_THANH_TOAN`, chụp ảnh đơn giá bán vào `chi_tiet_hoa_don`, chuyển trạng thái bàn sang `CO_KHACH`.
   * Bàn trên màn hình đổi sang viền đỏ có khách. Thu ngân có thể chuyển sang phục vụ bàn khác.
2. **Pha 2 – Thanh toán & Khép vòng đời:**
   * Thu ngân nhấp vào bàn có khách, hệ thống tải lại toàn bộ giỏ hàng cũ.
   * Nhấn "Thanh toán & Xuất hóa đơn".
   * Kiểm tra quyền chiết khấu: Nếu là Staff, ô chiết khấu bị khóa cứng ($0\text{ đ}$). Nếu là Manager/Admin, cho phép nhập tiền giảm giá.
   * Kiểm tra tiền khách đưa hoặc mã tham chiếu chuyển khoản.
   * Gửi request `POST /api/order/{id}/thanh-toan` kèm `phienBan` và `yeuCauThanhToan`.
   * Server thực thi giao dịch nguyên tử `@Transactional`: Khóa hóa đơn $\rightarrow$ Kiểm tra phiên bản $\rightarrow$ Trừ kho tự động $\rightarrow$ Cập nhật hóa đơn `DA_THANH_TOAN` $\rightarrow$ Giải phóng bàn về `TRONG` $\rightarrow$ Xuất hóa đơn nhiệt.

### 5.2. Luồng Trừ Kho Tự Động Ngầm & Sắp Xếp Khóa Chống Deadlock
Nghiệp vụ trừ kho ngầm trong `KhoService.java` được thiết kế đạt chuẩn an toàn đa luồng cao cấp:

```
[HoaDon da chon Thanh toan]
          │
          ▼
Duyet danh sach ChiTietHoaDon
          │
          ▼
Tra cuu bang CongThuc theo ma_mon
          │
          ├────────────────────────────────────────┐
          ▼ (Chua co cong thuc)                    ▼ (Da co cong thuc)
Nem IllegalArgumentException               Gop nhu cau vao TreeMap<maNL, TongCanTru>
          │                                (Sap xep maNL tang dan)
          ▼                                        │
ROLLBACK TOAN BO GIAO DICH                         ▼
(Don giu nguyen, ban giu nguyen,           Duyet TreeMap theo maNL tang dan:
 kho khong bi tru)                         Khoa tung dong: SELECT FOR UPDATE
                                                   │
                                                   ├───────────────────────────┐
                                                   ▼ (tonKho < TongCanTru)     ▼ (Du ton kho)
                                           Nem IllegalArgumentException   Luu vao Map da khoa
                                                   │                                   │
                                                   ▼                                   ▼
                                           ROLLBACK GIAO DICH             Duyet Map da khoa:
                                                                          UPDATE nguyen_lieu
                                                                          SET so_luong_ton = ton - can
                                                                                       │
                                                                                       ▼
                                                                          COMMIT GIAO DICH THANH CONG
```

> [!IMPORTANT]
> **Giải pháp triệt tiêu Deadlock (Deadlock-Free Strategy):** Khi 2 thu ngân cùng bấm thanh toán 2 đơn hàng có chứa các nguyên liệu giống nhau nhưng thứ tự món khác nhau, nếu khóa ngẫu nhiên sẽ dẫn đến hiện tượng kẹp khóa (Deadlock giữa 2 luồng). Bằng cách đưa toàn bộ nhu cầu nguyên liệu vào `TreeMap<Integer, BigDecimal>`, hệ thống luôn luôn lấy khóa bi quan (`SELECT ... FOR UPDATE`) theo thứ tự `ma_nl` tăng dần ($1 \rightarrow 2 \rightarrow 3$). Nhờ vậy, nguy cơ Deadlock bị triệt tiêu $100\%$.

### 5.3. Luồng Cảnh Báo Tồn Kho Đỏ & Hồi Phục Khi Nhập Kho
1. Mỗi nguyên liệu có trường `muc_ton_toi_thieu` (Mức an toàn, mặc định $100\text{ ml}$ hoặc $100\text{ g}$).
2. Sau khi trừ kho ngầm, nếu `soLuongTon <= mucTonToiThieu`, entity phương thức `isSapHet()` trả về `true`.
3. Bảng điều khiển KPI tại trang `kho.html` cập nhật chỉ số "Nguyên liệu sắp hết", đồng thời dòng nguyên liệu đó được gắn nhãn màu đỏ rực rỡ `SẮP HẾT HÀNG`.
4. Trên thanh điều hướng Topbar của ứng dụng, huy hiệu badge màu đỏ hiển thị số lượng mặt hàng sắp hết để người quản lý nhận biết tức thì dù đang đứng ở bất kỳ phân hệ nào.
5. Khi người quản lý mở modal "Nhập kho", điền số lượng nhập thực tế $\rightarrow$ Hệ thống cộng dồn vào tồn kho $\rightarrow$ Số tồn vượt qua ngưỡng tối thiểu $\rightarrow$ Nhãn lập tức chuyển về màu xanh dịu mắt `Đủ hàng`.

### 5.4. Luồng Quản Lý Ca & Chấm Công Nhận Diện Đúng Giờ / Đi Trễ
* Hệ thống cấu hình 3 ca làm việc chuẩn:
  * Ca Sáng: $07:00:00 - 12:00:00$
  * Ca Chiều: $12:00:00 - 17:00:00$
  * Ca Tối: $17:00:00 - 22:00:00$
* Khi nhân viên bấm "Check-in Ca Lam":
  * Server kiểm tra ràng buộc phân ca: Tìm bản ghi phân ca trong `phan_ca` theo `maNV`, `maCa` và `ngayLam`. Nếu nhân viên chưa được xếp ca vào ngày hôm nay $\rightarrow$ Ném ngoại lệ HTTP 400 Bad Request (`"Bạn chưa được phân ca này hôm nay"`).
  * Kiểm tra tính duy nhất: Kiểm tra trong bảng `cham_cong`, nếu đã tồn tại bản ghi check-in cùng ca trong ngày $\rightarrow$ Ném ngoại lệ HTTP 409 Conflict (`"Ca này đã được check-in"`).
  * Server ghi nhận thời điểm hiện tại `now = LocalDateTime.now()`.
  * Tính độ chênh lệch thời gian so với giờ bắt đầu ca:
    $$\Delta t = \text{Duration.between}(\text{ca.getGioBatDau()}, \text{now.toLocalTime()}).\text{toMinutes()}$$
  * Thuật toán phân loại:
    * Nếu $\Delta t \le 15\text{ phút}$: Ghi nhận trạng thái `DUNG_GIO` (Đúng giờ).
    * Nếu $\Delta t > 15\text{ phút}$: Ghi nhận trạng thái `DI_TRE` (Đi trễ).
* Cuối ca, nhân viên bấm "Check-out" $\rightarrow$ Cập nhật trường `gio_check_out = LocalDateTime.now()`. Chặn nhân viên check-out 2 lần hoặc check-out hộ người khác (chỉ Quản lý/Admin mới có quyền check-out hộ).

---

## 6. MA TRẬN PHÂN QUYỀN ĐA CẤP (ROLE-BASED ACCESS CONTROL - RBAC)

Hệ thống thiết lập 3 cấp bậc vai trò độc lập:
1. **`STAFF` (Nhân viên phục vụ / Thu ngân):** Tập trung tuyệt đối vào tốc độ bán hàng tại quầy và chấm công ca làm việc cá nhân.
2. **`MANAGER` (Quản lý cửa hàng):** Quản lý vận hành hàng ngày, kho bãi, định mức công thức pha chế, chiết khấu hóa đơn và xếp ca nhân sự.
3. **`ADMIN` (Chủ cửa hàng / Quản trị viên hệ thống):** Nắm toàn quyền tối cao, bao gồm thêm mới, sửa đổi và ngừng hoạt động nhân viên.

### 6.1. Bảng Ma Trận Phân Quyền Chi Tiết

| Chức năng & API Endpoint | STAFF | MANAGER | ADMIN | Hành vi bảo vệ kỹ thuật |
| :--- | :---: | :---: | :---: | :--- |
| **Đăng nhập / Đăng xuất hệ thống** | Cho phép | Cho phép | Cho phép | `XacThucController`: Hủy session khi logout. |
| **Bán hàng POS & Quản lý bàn** | Cho phép | Cho phép | Cho phép | `DonHangController`: Đầy đủ quyền bán hàng tại quầy. |
| **Nhập chiết khấu giảm giá đơn hàng** | **BỊ KHÓA (0đ)** | Cho phép | Cho phép | Frontend khóa ô `#pos-discount`; Backend ném `403 FORBIDDEN` nếu Staff gửi chiết khấu $> 0$. |
| **Xem Menu đồ uống & Sơ đồ bàn** | Cho phép | Cho phép | Cho phép | `BanController`, `MonNuocController`: Yêu cầu session đăng nhập. |
| **Xem tồn kho nguyên liệu** | **Ẩn menu** | Cho phép | Cho phép | Frontend ẩn tab "Kho & KPI"; Route Guard chặn URL trực tiếp. |
| **Xem Dashboard KPI Kho & Doanh thu** | **BỊ CHẶN** | Cho phép | Cho phép | `GET /api/kho/dashboard` $\rightarrow$ `PhanQuyenSession.requireManager` trả về `403 FORBIDDEN`. |
| **Nhập thêm nguyên liệu vào kho** | **BỊ CHẶN** | Cho phép | Cho phép | `POST /api/kho/nhap-kho` $\rightarrow$ `PhanQuyenSession.requireManager` trả về `403 FORBIDDEN`. |
| **Thiết lập định mức công thức (BOM)**| **BỊ CHẶN** | Cho phép | Cho phép | `POST /api/kho/cong-thuc` $\rightarrow$ `PhanQuyenSession.requireManager` trả về `403 FORBIDDEN`. |
| **Xem danh sách nguyên liệu sắp hết** | **BỊ CHẶN** | Cho phép | Cho phép | `GET /api/kho/sap-het` $\rightarrow$ `PhanQuyenSession.requireManager` trả về `403 FORBIDDEN`. |
| **Tự Check-in / Check-out ca làm** | Cho phép | Cho phép | Cho phép | `ChamCongController`: Chỉ check-in/out cho chính `maNV` của mình. |
| **Check-out hộ nhân viên khác** | **BỊ CHẶN** | Cho phép | Cho phép | `NhanSuService.checkOut`: Chặn nhân viên thường check-out hộ người khác. |
| **Xem lịch sử chấm công cửa hàng** | Chỉ xem của mình | Xem tất cả | Xem tất cả | `ChamCongController.layLichSuChamCong`: Staff chỉ nạp lịch sử theo `maNV`. |
| **Xếp lịch phân ca tuần** | **Ẩn menu** | Cho phép | Cho phép | `POST /api/phan-ca` $\rightarrow$ `PhanQuyenSession.requireManager`. |
| **Xem danh bạ hồ sơ nhân viên** | **Ẩn menu** | Cho phép | Cho phép | `GET /api/nhan-su/nhan-vien` $\rightarrow$ `PhanQuyenSession.requireManager`. |
| **Thêm nhân viên mới (`+ Thêm NV`)** | **BỊ CHẶN** | **ẨN NÚT** | Cho phép | Frontend ẩn nút với Manager; `POST /api/nhan-su/nhan-vien` gọi `access.requireAdmin` trả về `403`. |
| **Cập nhật & Ngừng việc nhân viên** | **BỊ CHẶN** | **BỊ CHẶN** | Cho phép | `PUT/DELETE /api/nhan-su/nhan-vien/{id}` gọi `access.requireAdmin` trả về `403`. |

### 6.2. Hiện Thực Bảo Vệ 4 Tầng (4-Layer Defense)
Hệ thống thiết lập hàng rào bảo mật 4 lớp không thể vượt qua:

```
[NGƯỜI DÙNG THAO TÁC]
        │
        ▼
[TẦNG 1: Điều Hướng Giao Diện (dung-chung.js)]
- STAFF: Thanh Topbar CHỈ hiển thị tab "Bán hàng (POS)".
- MANAGER / ADMIN: Hiển thị đầy đủ "Bán hàng", "Kho & KPI", "Nhân sự & Chấm công".
        │
        ▼
[TẦNG 2: Bộ Chặn Đường Dẫn Route Guard (kho-page.js & nhansu-page.js)]
- Nếu STAFF gõ URL trực tiếp (ví dụ http://localhost:8080/kho.html):
  Route Guard phát hiện user.vaiTro === 'STAFF', lập tức hiện alert cảnh báo
  và điều hướng cưỡng bức quay về banhang.html.
        │
        ▼
[TẦNG 3: Khóa Thành Phần Nhập Liệu POS (banhang-page.js)]
- Tại màn hình POS: Nếu vai trò là STAFF, ô nhập chiết khấu #pos-discount bị disabled,
  hiển thị nhãn đỏ: "Chỉ Quản lý được chiết khấu".
        │
        ▼
[TẦNG 4: Kiểm Soát Máy Chủ Backend (PhanQuyenSession.java & Controllers)]
- Mọi request gửi lên REST API nhạy cảm đều đi qua PhanQuyenSession:
  + KhoController.getDashboardKPI() -> access.requireManager(session)
  + KhoController.nhapKho()         -> access.requireManager(session)
  + NhanSuController.themNhanVien() -> access.requireAdmin(session)
  + DonHangController.thanhToan()   -> Chặn STAFF chiết khấu > 0
  => Vi phạm lập tức ngắt luồng và trả về mã HTTP 403 FORBIDDEN.
```

---

## 7. CÁC CƠ CHẾ BẢO MẬT & KIỂM SOÁT KỸ THUẬT NÂNG CAO

### 7.1. Bảo Mật Quản Lý Phiên (Session Security)
* Sử dụng cơ chế phiên làm việc `HttpSession` tiêu chuẩn của máy chủ Servlet (Tomcat), định danh qua cookie mã hóa `JSESSIONID`.
* Máy chủ chỉ lưu trữ các định danh tối thiểu: `maNV` (`Integer`) và `vaiTro` (`String`).
* Khi người dùng đăng xuất, phương thức `session.invalidate()` hủy bỏ hoàn toàn phiên làm việc trên bộ nhớ RAM của server, ngăn chặn triệt để tấn công chiếm đoạt phiên (*Session Hijacking*).

### 7.2. Ẩn Mật Khẩu Tuyệt Đối (`WRITE_ONLY`)
* Trong entity [NhanVien.java](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/src/main/java/com/cafe/entity/NhanVien.java), getter `getMatKhau()` được gắn chú thích:
  ```java
  @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY)
  public String getMatKhau() { return matKhau; }
  ```
* Cơ chế này cho phép Jackson giải mã mật khẩu khi Client gửi lên đăng ký/đăng nhập, nhưng **tuyệt đối không bao giờ tuần tự hóa mật khẩu vào JSON phản hồi** khi tra cứu hồ sơ nhân viên.

### 7.3. Khóa Lạc Quan Chống Ghi Đè Dữ Liệu (Optimistic Locking)
* Trong entity [HoaDon.java](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/src/main/java/com/cafe/entity/HoaDon.java), trường `phienBan` được gắn chú thích JPA `@Version`:
  ```java
  @Version
  @Column(name = "phien_ban", columnDefinition = "bigint default 0")
  private Long phienBan = 0L;
  ```
* Khi 2 thu ngân cùng mở một hóa đơn trên 2 máy khác nhau, thu ngân A thanh toán thành công $\rightarrow$ `phienBan` tăng từ $0 \rightarrow 1$. Khi thu ngân B bấm gửi cập nhật với `phienBan = 0`, Hibernate phát hiện xung đột và ném ngoại lệ `OptimisticLockException` (hoặc `ConcurrencyFailureException`). Máy chủ trả về mã HTTP `409 Conflict`, ngăn chặn việc ghi đè mất mát dữ liệu (*Lost Update*).

### 7.4. Khóa Bi Quan Chống Bán Khống (Pessimistic Locking)
* Khi thanh toán đơn hàng và trừ kho, `KhoService` gọi:
  ```java
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("select x from NguyenLieu x where x.maNL = :id")
  Optional<NguyenLieu> khoaNguyenLieu(@Param("id") Integer id);
  ```
* Lệnh sinh ra câu truy vấn SQL `SELECT ... FOR UPDATE` trên CSDL MySQL InnoDB. Bất kỳ giao dịch thanh toán nào khác muốn chạm vào nguyên liệu này đều phải chờ giao dịch hiện tại hoàn tất, bảo đảm không bao giờ xảy ra hiện tượng tồn kho bị âm do bán khống đồng thời (*Oversell*).

### 7.5. Tính Bất Biến Giao Dịch (Idempotency Key)
* Client sinh mã UUID độc nhất: `yeuCauTao` khi tạo đơn và `yeuCauThanhToan` khi thanh toán.
* Nếu đường truyền mạng bị chập chờn khiến client gửi lặp lại 2 lần (hoặc thu ngân bấm đúp chuột):
  * Phương thức `layDonTheoYeuCau(request)` phát hiện hóa đơn với mã UUID này đã tồn tại $\rightarrow$ Trả về ngay hóa đơn đã tạo, không tạo đơn mới.
  * Phương thức `thanhToanDonHang` kiểm tra `yeuCauThanhToan` trùng khớp với đơn đã thanh toán $\rightarrow$ Trả về kết quả hiện tại, không thực hiện trừ kho lần thứ 2.

### 7.6. Snapshot Đơn Giá Bán (Price Auditing)
* Khi đồ uống được đưa vào đơn hàng, đơn giá tại thời điểm đó được sao chép nguyên vẹn vào cột `chi_tiet_hoa_don.don_gia_ban`.
* Dù người quản lý sau này có tăng giá món nước từ $25.000\text{ đ} \rightarrow 30.000\text{ đ}$, tổng tiền của các hóa đơn cũ trong quá khứ và các đơn đang mở vẫn được bảo toàn nguyên vẹn, đảm bảo tính toàn vẹn báo cáo kế toán.

### 7.7. Chống Tấn Công SQL Injection & Chuẩn Hóa UTF-8
* $100\%$ các câu truy vấn cơ sở dữ liệu đều sử dụng Spring Data JPA và Parameterized Queries (`PreparedStatement`), loại bỏ hoàn toàn nguy cơ bị tiêm mã độc SQL Injection.
* CSDL được cấu hình `utf8mb4_unicode_ci`, chuỗi kết nối JDBC bổ sung `characterEncoding=UTF-8&connectionCollation=utf8mb4_unicode_ci`, và font chữ nhúng Google Fonts `Roboto`, bảo đảm hiển thị tiếng Việt có dấu sắc nét, không bị lỗi vỡ font Mojibake.

---

## 8. HIỆN TRẠNG KIỂM THỬ TOÀN DIỆN & MINH CHỨNG CHẤT LƯỢNG

Hệ thống đã trải qua quy trình kiểm định chất lượng phần mềm nghiêm ngặt trên cả 2 phương diện Backend và Frontend:

### 8.1. Kiểm Thử Tích Hợp Tự Động Backend (17/17 Tests PASS - 100%)
* Môi trường thực thi: Spring Boot Test với CSDL H2 In-Memory (`@ActiveProfiles("test")`), hoàn toàn cách ly với CSDL MySQL thật.
* Lệnh thực thi:
  ```powershell
  $env:JAVA_HOME = "F:\JDK" ; Set-Location "d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype" ; .\mvnw.cmd test
  ```
* **Kết quả: 17/17 Tests ĐẠT (100% PASS), 0 Thất bại, 0 Lỗi:**
  * **14 ca kiểm thử trong `DonHangIntegrationTest.java`:**
    1. `loginSessionDoesNotExposePassword`: Xác thực an toàn và ẩn mật khẩu trong JSON.
    2. `requiresSessionAndUsesServerIdentity`: Chặn truy cập không session và định danh nhân viên phía server.
    3. `saveReloadUpdateCancel`: Vòng đời lưu đơn $\rightarrow$ tải lại $\rightarrow$ cập nhật $\rightarrow$ hủy đơn trả bàn về trống (không trừ kho).
    4. `createRetryReturnsSameOrderAndTableRejectsSecond`: Đảm bảo tính Idempotency và chặn mở 2 đơn trên cùng 1 bàn.
    5. `rejectsInvalidAndEmptyOrders`: Chặn đơn hàng số lượng âm hoặc giỏ hàng rỗng.
    6. `paymentCashStockAndRetryOnce`: Thanh toán tiền mặt trừ kho chính xác ($120\text{ ml} \rightarrow 90\text{ ml}$); chống trừ kho 2 lần khi retry.
    7. `insufficientStockRollsBackWholePayment`: Tồn kho không đủ kích hoạt `@Transactional` Rollback toàn bộ giao dịch.
    8. `missingRecipeBlocked`: Chặn thanh toán món chưa có định mức công thức pha chế.
    9. `discountValidationAndRole`: Chặn `STAFF` nhập chiết khấu (`403 Forbidden`); cho phép `MANAGER` chiết khấu hợp lệ.
    10. `transferRequiresConfirmationAndUniqueReference`: Chuyển khoản yêu cầu xác nhận tiền vào tài khoản và mã tham chiếu duy nhất.
    11. `pricesRemainSnapshots`: Đơn giá trong đơn giữ nguyên dù danh mục đổi giá sau đó.
    12. `twoConcurrentPaymentsOnlyDeductOnce`: Kiểm thử 2 luồng đồng thời: Khóa bi quan và `@Version` đảm bảo chỉ 1 luồng trừ kho thành công.
    13. `twoOrdersCannotOversellSharedIngredient`: Chống bán khống (Oversell) khi 2 đơn cùng tranh chấp lượng tồn kho có hạn.
    14. `duplicateProductLinesAggregateStock`: Gộp định mức các dòng trùng món trong đơn trước khi trừ kho.
  * **3 ca kiểm thử trong `NhanSuIntegrationTest.java`:**
    15. `managerSchedulesAndStaffCanOnlyAttendOwnShiftOnce`: Manager phân ca tuần thành công; Staff chỉ được phép check-in vào ca làm việc được phân công cho chính mình (`200 OK`) và bị chặn khi check-in lặp lại lần 2 trong cùng ca ngày (`409 Conflict`); Staff tra cứu lịch sử chỉ thấy dữ liệu cá nhân của mình.
    16. `staffCannotManageEmployeesAndAdminCanCreateOne`: Tài khoản Staff bị chặn `403 Forbidden` khi truy cập danh bạ nhân sự; Manager bị chặn `403 Forbidden` khi cố gắng tạo nhân viên mới; Admin tạo nhân viên mới thành công (`201 Created`) và mật khẩu được bảo vệ ẩn hoàn toàn (`$.matKhau doesNotExist()`).
    17. `staffCannotAccessKhoDashboardOrRestock`: Tài khoản Staff bị chặn `403 Forbidden` khi cố gắng gọi API `/api/kho/dashboard` và `/api/kho/nhap-kho`; đồng thời tài khoản Manager truy cập Dashboard KPI thành công (`200 OK`).

### 8.2. Kiểm Thử Tự Động Giao Diện Web E2E (Puppeteer Chrome)
* Thực hiện tự động thông qua Google Chrome Headless với độ phân giải màn hình tiêu chuẩn $1280 \times 800$.
* **Kết quả: 16/16 Kịch bản giao diện ĐẠT (100% PASS)** kèm kiểm thử độc lập cả 3 vai trò (`staff1`, `manager`, `admin`).
* **Hệ thống ảnh chụp minh chứng sắc nét tại thư mục [screenshots/](file:///d:/3.1.%20PTTKYC/CK/screenshots/):**
  * `fixed_staff_pos_clean.png`: Giao diện POS sạch sẽ của Staff, thanh Topbar chỉ hiện 1 tab Bán hàng, ô chiết khấu bị khóa có biểu tượng ổ khóa đỏ.
  * `fixed_manager_pos_clean.png`: Giao diện POS của Manager, hiển thị đầy đủ 3 tab, ô chiết khấu mở khóa.
  * `fixed_manager_kho_clean.png`: Màn hình Quản lý Kho của Manager, hiển thị thẻ KPI và bảng nguyên liệu.
  * `fixed_manager_nhansu_clean.png`: Màn hình Nhân sự của Manager (nút Thêm nhân viên bị ẩn an toàn).
  * `fixed_admin_nhansu_clean.png`: Màn hình Nhân sự của Admin (nút `+ Thêm nhân viên` hiển thị đầy đủ).
  * `08_kho_alert_kpi.png` & `11_kho_restocked.png`: Nhãn đỏ `SẮP HẾT HÀNG` bật sáng khi tồn kho $\le$ ngưỡng tối thiểu và chuyển sang xanh `Đủ hàng` sau khi nhập kho.
  * `07_receipt_modal.png`: Mẫu in hóa đơn nhiệt tiêu chuẩn quán cà phê.

---

## 9. HƯỚNG DẪN KHỞI CHẠY, TRIỂN KHAI & BẢO TRÌ HỆ THỐNG

### 9.1. Yêu Cầu Môi Trường
* Hệ điều hành: Windows 10/11, Linux (Ubuntu/Debian) hoặc macOS.
* Bộ cài đặt JDK: Java 17+ hoặc Java 25 LTS (Đường dẫn hiện hành: `F:\JDK`).
* Bộ cài đặt CSDL: XAMPP MySQL 8.0 / MariaDB (Đường dẫn hiện hành: `C:\xampp\mysql`).
* Trình duyệt Web: Google Chrome, Microsoft Edge hoặc Mozilla Firefox phiên bản mới nhất.

### 9.2. Trình Tự Khởi Chạy Từng Bước

#### Bước 1: Khởi động Dịch vụ MySQL
Mở PowerShell với quyền Administrator hoặc dòng lệnh thông thường:
```powershell
Start-Process -FilePath "C:\xampp\mysql\bin\mysqld.exe" -ArgumentList "--defaults-file=C:\xampp\mysql\bin\my.ini --standalone"
```

#### Bước 2: Nạp CSDL và Dữ liệu Mẫu Tiếng Việt Chuẩn
Thực hiện lệnh nạp trực tiếp qua lệnh SOURCE nội bộ của MySQL với bảng mã `utf8mb4`:
```cmd
C:\xampp\mysql\bin\mysql.exe --default-character-set=utf8mb4 -u root -e "SOURCE d:/3.1. PTTKYC/CK/02_SourceCode_WebJava_Prototype/database_mau.sql;"
```

#### Bước 3: Chạy Kiểm Thử Tự Động (Tùy chọn Kiểm định)
```powershell
$env:JAVA_HOME = "F:\JDK"
Set-Location "d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype"
.\mvnw.cmd test
```

#### Bước 4: Khởi Động Máy Chủ Spring Boot
```powershell
$env:JAVA_HOME = "F:\JDK"
Set-Location "d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype"
.\mvnw.cmd spring-boot:run
```
Máy chủ sẽ khởi động và lắng nghe tại cổng `8080`.

#### Bước 5: Truy Cập Ứng Dụng
Mở trình duyệt web và truy cập địa chỉ:
```
http://localhost:8080/index.html
```

**Danh sách tài khoản kiểm thử mặc định:**
* Quản trị viên (Toàn quyền): `admin` / Mật khẩu: `123456`
* Quản lý cửa hàng (Kho, BOM, Chiết khấu, Phân ca): `manager` / Mật khẩu: `123456`
* Nhân viên thu ngân 1 (Bán hàng POS, Chấm công): `staff1` / Mật khẩu: `123456`
* Nhân viên thu ngân 2 (Bán hàng POS, Chấm công): `staff2` / Mật khẩu: `123456`

---

## 10. KẾT LUẬN & ĐÁNH GIÁ ĐẶC TẢ KỸ THUẬT

Hệ thống Quản lý Quán Cà phê Mộc An là một sản phẩm phần mềm mẫu mực cho học phần **Phân tích và Thiết kế Yêu cầu (PTTKYC - 502050)**:
1. **Tính hoàn chỉnh nghiệp vụ:** Số hóa trọn vẹn và giải quyết dứt điểm 3 điểm nghẽn thực tế lớn nhất của ngành F&B (Gọi món sai sót, Thất thoát hao hụt kho nguyên liệu, và Trùng ca chấm công lộn xộn).
2. **Tính vững chắc về mặt kiến trúc:** Tuân thủ mô hình 3 tầng phân lớp rõ ràng, áp dụng giao dịch nguyên tử `@Transactional` kết hợp chiến lược khóa bi quan sắp xếp theo khóa chính triệt tiêu Deadlock và khóa lạc quan `@Version` chống mất dữ liệu.
3. **Bảo mật đa tầng mạnh mẽ:** Phân quyền RBAC 4 lớp từ giao diện, route guard, khóa thành phần nhập liệu đến kiểm soát tầng REST API bằng `PhanQuyenSession`.
4. **Mã nguồn sạch & Kiểm thử 100%:** Toàn bộ mã nguồn Java, lược đồ CSDL và giao diện người dùng đạt tiêu chuẩn kiểm thử tự động $100\%$ PASS trên cả Backend lẫn Frontend.

---
*Báo cáo kỹ thuật được biên soạn chi tiết và lưu trữ chính thức tại tệp tin [BAO_CAO_HE_THONG.md](file:///d:/3.1.%20PTTKYC/CK/BAO_CAO_HE_THONG.md).*
