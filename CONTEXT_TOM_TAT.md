# TÀI LIỆU TÓM TẮT DỰ ÁN & TIẾN TRÌNH KỸ THUẬT (PROJECT CONTEXT & AUDIT)
> **Dự án:** Hệ Thống Quản Lý Quán Cà Phê (Coffee POS & Inventory Management System)  
> **Học phần:** Phân tích & Thiết kế Yêu cầu (Mã học phần: `502050`) – Khoa Công nghệ Thông tin, Trường Đại học Tôn Đức Thắng  
> **Giảng viên hướng dẫn:** TS. Hồ Thị Linh  
> **Nhóm sinh viên thực hiện:**  
> - **Nguyễn Văn Duy (Nhóm trưởng – 34%):** Kiến trúc CSDL 9 bảng, Phân hệ Quản trị Kho & Định mức công thức (BOM), Xử lý giao dịch nguyên tử `@Transactional` & Khóa chống bán khống (Oversell), Tổng hợp Báo cáo học thuật ([BAO_CAO.md](file:///d:/3.1.%20PTTKYC/CK/BAO_CAO.md)), Kịch bản thuyết trình ([KICH_BAN.md](file:///d:/3.1.%20PTTKYC/CK/KICH_BAN.md)) và Slide thuyết trình ([slide.html](file:///d:/3.1.%20PTTKYC/CK/slide.html)).  
> - **Trần Văn Quý (33%):** Khảo sát 15 Yêu cầu chức năng (FRs), Phân loại MoSCoW, Phân hệ Bán hàng POS, Sơ đồ bàn động, Giỏ hàng món nước, Hóa đơn thanh toán nhiệt.  
> - **Lê Tấn Đạt (33%):** Khảo sát điểm nghẽn As-Is, 6 Yêu cầu phi chức năng (NFRs), Phân hệ Quản trị Nhân sự, Lịch phân ca làm việc, Nghiệp vụ Chấm công thời gian thực (Check-in/Check-out nhận diện "Đúng giờ" / "Đi trễ").  
> **Thời gian cập nhật:** 19/11/2026  
> **Mục đích tài liệu:** Bản lưu trữ toàn diện tri thức phân tích thiết kế, kiến trúc CSDL & mã nguồn, các luồng nghiệp vụ đã hiện thực, lịch sử sửa lỗi và hiện trạng kiểm thử để sử dụng cho các phiên làm việc tiếp theo, báo cáo nghiệm thu và thuyết trình bảo vệ đồ án.

---

## MỤC LỤC TỔNG QUAN

1. [TỔNG QUAN ĐỀ TÀI & QUÁ TRÌNH PHÂN TÍCH THIẾT KẾ](#1-tổng-quan-đề-tài--quá-trình-phân-tích-thiết-kế)
   * 1.1. [Khảo sát Hiện trạng Thực tế (As-Is) & Điểm nghẽn Vận hành](#11-khảo-sát-hiện-trạng-thực-tế-as-is--điểm-nghẽn-vận-hành)
   * 1.2. [Mục tiêu Hệ thống Số hóa (To-Be) & Ranh giới Phạm vi](#12-mục-tiêu-hệ-thống-số-hóa-to-be--ranh-giới-phạm-vi)
   * 1.3. [Ma trận Yêu cầu Chức năng (15 FRs - MoSCoW) & Phi chức năng (6 NFRs)](#13-ma-trận-yêu-cầu-chức-năng-15-frs---moscow--phi-chức-năng-6-nfrs)
   * 1.4. [Hệ thống 13 Sơ đồ Thiết kế UML (PlantUML Chuẩn hóa)](#14-hệ-thống-13-sơ-đồ-thiết-kế-uml-plantuml-chuẩn-hóa)
2. [KIẾN TRÚC PHÂN TẦNG & CƠ SỞ DỮ LIỆU CHUẨN HÓA](#2-kiến-trúc-phân-tầng--cơ-sở-dữ-liệu-chuẩn-hóa)
   * 2.1. [Kiến trúc Phân tầng 3 Tầng (3-Tier Spring Boot Architecture)](#21-kiến-trúc-phân-tầng-3-tầng-3-tier-spring-boot-architecture)
   * 2.2. [Mô hình Dữ liệu Quan hệ (9 Bảng 3NF) & Snapshot Đơn giá](#22-mô-hình-dữ-liệu-quan-hệ-9-bảng-3nf--snapshot-đơn-giá)
   * 2.3. [Cơ chế Kỹ thuật Nâng cao (@Transactional, Lock, Idempotency)](#23-cơ-chế-kỹ-thuật-nâng-cao-transactional-lock-idempotency)
3. [CHI TIẾT CÁC LUỒNG LOGIC ĐÃ HIỆN THỰC](#3-chi-tiết-các-luồng-logic-đã-hiện-thực)
   * 3.1. [Luồng 1: Xác thực & Phân quyền Đa cấp RBAC (Staff / Manager / Admin)](#31-luồng-1-xác-thực--phân-quyền-đa-cấp-rbac-staff--manager--admin)
   * 3.2. [Luồng 2: Nghiệp vụ Bán hàng POS & Quản lý Sơ đồ Bàn Động](#32-luồng-2-nghiệp-vụ-bán-hàng-pos--quản-lý-sơ-đồ-bàn-động)
   * 3.3. [Luồng 3: Quản trị Kho, Định mức BOM & Trừ kho Tự động Ngầm](#33-luồng-3-quản-trị-kho-định-mức-bom--trừ-kho-tự-động-ngầm)
   * 3.4. [Luồng 4: Quản trị Nhân sự, Ca làm việc & Chấm công Thời gian thực](#34-luồng-4-quản-trị-nhân-sự-ca-làm-việc--chấm-công-thời-gian-thực)
4. [LỊCH SỬ PHÁT HIỆN & KHẮC PHỤC LỖI (BUG FIX LOG)](#4-lịch-sử-phát-hiện--khắc-phục-lỗi-bug-fix-log)
   * 4.1. [Bảng Tổng hợp 9 Lỗi Kỹ thuật Đã Sửa và Nghiệm thu 100%](#41-bảng-tổng-hợp-9-lỗi-kỹ-thuật-đã-sửa-và-nghiệm-thu-100)
   * 4.2. [Chi tiết Sửa lỗi Font Tiếng Việt & Mã hóa Mojibake](#42-chi-tiết-sửa-lỗi-font-tiếng-việt--mã-hóa-mojibake)
   * 4.3. [Chi tiết Đại tu Giao diện Unified Topbar & Phân quyền RBAC Toàn diện](#43-chi-tiết-đại-tu-giao-diện-unified-topbar--phân-quyền-rbac-toàn-diện)
5. [HIỆN TRẠNG KIỂM THỬ TOÀN DIỆN (TESTING AUDIT)](#5-hiện-trạng-kiểm-thử-toàn-diện-testing-audit)
   * 5.1. [Kiểm thử Tự động Backend Spring Boot (17/17 Tests PASS)](#51-kiểm-thử-tự-động-backend-spring-boot-1717-tests-pass)
   * 5.2. [Kiểm thử Tự động Giao diện Web E2E Puppeteer Chrome](#52-kiểm-thử-tự-động-giao-diện-web-e2e-puppeteer-chrome)
   * 5.3. [Bộ Minh chứng Hình ảnh Thực tế (Screenshots Portfolio)](#53-bộ-minh-chứng-hình-ảnh-thực-tế-screenshots-portfolio)
6. [HỆ THỐNG CÁC TỆP TIN MARKDOWN & TÀI NGUYÊN DỰ ÁN](#6-hệ-thống-các-tệp-tin-markdown--tài-nguyên-dự-án)
7. [ĐÁNH GIÁ & ĐỒNG BỘ NHÁNH GIT (MAIN VS DAT)](#7-đánh-giá--đồng-bộ-nhánh-git-main-vs-dat)
8. [HƯỚNG DẪN KHỞI CHẠY HỆ THỐNG CHO PHIÊN LÀM VIỆC TIẾP THEO](#8-hướng-dẫn-khởi-chạy-hệ-thống-cho-phiên-làm-việc-tiếp-theo)

---

## 1. TỔNG QUAN ĐỀ TÀI & QUÁ TRÌNH PHÂN TÍCH THIẾT KẾ

### 1.1. Khảo sát Hiện trạng Thực tế (As-Is) & Điểm nghẽn Vận hành
Khảo sát thực tế mô hình vận hành tại quán cà phê vừa và nhỏ (*Mộc An Coffee*) chỉ ra 3 "điểm nghẽn" cố hữu gây tổn thất trực tiếp:
1. **Điểm nghẽn Bán hàng (POS):** Nhân viên phục vụ dùng sổ tay ghi chép món; khi quán đông giờ cao điểm dễ thất lạc phiếu order, tính tiền nhẩm hoặc máy tính tay dẫn đến thối nhầm tiền hoặc sót đơn, thất thoát doanh thu.
2. **Điểm nghẽn Kho nguyên liệu:** Nhân viên pha chế định lượng theo cảm tính; cuối ngày chủ quán kiểm kho chỉ đếm số lượng tồn thực tế mà không có căn cứ đối chiếu với số lượng đồ uống đã bán, không thể phát hiện nguyên nhân hao hụt là do pha chế đổ tràn hay do thất thoát nội bộ.
3. **Điểm nghẽn Quản lý Nhân sự:** Đội ngũ nhân viên part-time (chủ yếu là sinh viên) thường xuyên đổi ca, bảng phân ca viết tay trên bảng mica dễ dẫn đến tình trạng trùng ca hoặc thiếu người đột xuất; cuối tháng khó tổng kết chính xác số giờ công và tình trạng đi trễ.

### 1.2. Mục tiêu Hệ thống Số hóa (To-Be) & Ranh giới Phạm vi
* **Mục tiêu chuyển đổi số (*To-Be*):** Xây dựng hệ thống phần mềm nguyên khối tinh gọn (*All-in-one Web Application*) trên nền tảng **Spring Boot 3 + MySQL 8.0 + Giao diện Web Responsive**, liên kết luồng dữ liệu tự động khép kín:  
  $$\text{Đặt món (POS)} \longrightarrow \text{Thanh toán hóa đơn} \overset{\text{@Transactional}}{\Longrightarrow} \text{Tự động trừ kho ngầm (BOM)} \longrightarrow \text{Cảnh báo tồn kho đỏ} \longrightarrow \text{Chấm công} \longrightarrow \text{KPI Doanh thu}$$
* **Phạm vi hệ thống (In-Scope):**
  * Quản lý trạng thái sơ đồ bàn trực quan ($10$ bàn: `TRONG` $\leftrightarrow$ `CO_KHACH`).
  * Gọi món tại bàn và mang đi (*Takeaway*), hỗ trợ ghi chú pha chế ("ít ngọt", "nhiều đá").
  * Quản lý danh mục món nước và đơn giá bán.
  * Quản lý kho nguyên liệu, đơn vị tính, ngưỡng cảnh báo an toàn (*Safety Minimum Stock*).
  * Quản lý bảng định mức công thức pha chế (*Bill of Materials - BOM*).
  * Tự động trừ kho ngầm theo công thức khi thanh toán đơn hàng.
  * Kích hoạt nhãn cảnh báo đỏ `SẮP HẾT HÀNG` khi số lượng tồn kho giảm $\le$ ngưỡng tối thiểu.
  * Quản lý hồ sơ nhân viên, phân ca tuần (Sáng / Chiều / Tối) và chấm công Check-in/Check-out thời gian thực phân loại Đúng giờ / Đi trễ.
  * Bảng điều khiển KPI: Doanh thu ngày, số đơn hoàn tất, số nguyên liệu sắp hết.
* **Ngoại phạm vi (Out-of-Scope):** Hạch toán kế toán chuyên sâu (thuế VAT, báo cáo tài chính), cổng thanh toán ngân hàng trực tuyến thật (sử dụng tiền mặt và VietQR tĩnh), native app mobile riêng biệt.

### 1.3. Ma trận Yêu cầu Chức năng (15 FRs - MoSCoW) & Phi chức năng (6 NFRs)
Hệ thống yêu cầu được phân tích theo khung chuẩn quốc tế **MoSCoW**:
* **Nhóm Must Have (Bắt buộc phải có):**
  * `FR-01`: Đăng nhập, phân quyền vai trò (Admin, Manager, Staff) và đăng xuất an toàn.
  * `FR-02`: Hiển thị sơ đồ bàn và đồng bộ trạng thái thực tế.
  * `FR-03`: Tạo mới, lưu trữ và tải lại đơn hàng theo từng bàn hoặc đơn mang đi.
  * `FR-04`: Thanh toán hóa đơn (Tiền mặt / Chuyển khoản QR) và in hóa đơn nhiệt.
  * `FR-05`: Quản lý danh mục nguyên liệu và theo dõi số lượng tồn kho.
  * `FR-06`: Quản lý định mức công thức pha chế (BOM) liên kết giữa món và nguyên liệu.
  * `FR-07`: Tự động trừ kho ngầm theo định mức BOM khi đơn hàng được thanh toán.
  * `FR-08`: Cảnh báo trực quan khi nguyên liệu chạm hoặc giảm dưới ngưỡng tồn tối thiểu.
  * `FR-09`: Nhập thêm nguyên liệu vào kho và tự động hồi phục trạng thái an toàn.
  * `FR-10`: Chấm công nhân viên (Check-in / Check-out) theo ca làm việc.
* **Nhóm Should Have (Nên có):**
  * `FR-11`: Thống kê các chỉ số KPI vận hành trong ngày (Doanh thu, số đơn, hàng sắp hết).
  * `FR-12`: Quản lý danh sách nhân viên và tạo hồ sơ nhân viên mới.
  * `FR-13`: Hỗ trợ chiết khấu giảm giá (chỉ dành riêng cho Manager/Admin).
* **Nhóm Could Have (Có thể có):**
  * `FR-14`: Bộ lọc tìm kiếm nhanh món nước theo danh mục và tên món.
  * `FR-15`: Nút đồng bộ thủ công dữ liệu từ máy chủ MySQL về giao diện web tức thì.
* **Hệ thống 6 Yêu cầu Phi chức năng (NFRs):**
  * `NFR-01 (Hiệu năng):` Thời gian phản hồi API bán hàng và tra cứu menu $< 200\text{ ms}$.
  * `NFR-02 (Xử lý đồng thời):` Cơ chế khóa bi quan và khóa lạc quan `@Version` ngăn chặn xung đột dữ liệu và hiện tượng bán khống (Oversell).
  * `NFR-03 (Toàn vẹn dữ liệu):` Luồng thanh toán và trừ kho tuân thủ nghiêm ngặt tính chất ACID qua `@Transactional`.
  * `NFR-04 (Tính khả dụng):` Giao diện Responsive Bootstrap 5, thao tác cảm ứng thân thiện với nhân viên thu ngân.
  * `NFR-05 (Bảo mật):` Mã hóa phiên làm việc qua HTTP Session phía server, không để lộ thông tin nhạy cảm (mật khẩu) ra JSON.
  * `NFR-06 (Tính bất biến):` Hỗ trợ Idempotency Key chống tạo trùng đơn hoặc trừ kho 2 lần khi mạng chập chờn.

### 1.4. Hệ thống 13 Sơ đồ Thiết kế UML (PlantUML Chuẩn hóa)
Toàn bộ sơ đồ được mô hình hóa bằng ngôn ngữ chuẩn **PlantUML** tại thư mục [puml/](file:///d:/3.1.%20PTTKYC/CK/puml/) và kết xuất thành ảnh vector chất lượng cao tại thư mục [diagram/](file:///d:/3.1.%20PTTKYC/CK/diagram/):
1. [01_KienTruc_TongThe.puml](file:///d:/3.1.%20PTTKYC/CK/puml/01_KienTruc_TongThe.puml): Biểu đồ Kiến trúc Phân tầng & Thành phần Hệ thống.
2. [02_TrienKhai_Deployment.puml](file:///d:/3.1.%20PTTKYC/CK/puml/02_TrienKhai_Deployment.puml): Biểu đồ Triển khai phần cứng & cổng mạng (Port 8080 Tomcat, Port 3306 MySQL).
3. [03_UseCase_TongQuat.puml](file:///d:/3.1.%20PTTKYC/CK/puml/03_UseCase_TongQuat.puml): Biểu đồ Use Case tổng quát với 3 tác nhân (`Thu Ngan`, `Quan Ly`, `Nhan Vien`).
4. [04_Class_Design.puml](file:///d:/3.1.%20PTTKYC/CK/puml/04_Class_Design.puml): Biểu đồ Lớp Thiết kế Chi tiết (Controller, Service, Repository, Entity, DTO).
5. [05_ERD_DuLieu.puml](file:///d:/3.1.%20PTTKYC/CK/puml/05_ERD_DuLieu.puml): Biểu đồ Thực thể Quan hệ CSDL 9 bảng theo chuẩn Crow's Foot.
6. [06_Activity_TruKho.puml](file:///d:/3.1.%20PTTKYC/CK/puml/06_Activity_TruKho.puml): Biểu đồ Hoạt động phân làn luồng trừ kho tự động khi thanh toán.
7. [07_Activity_POS.puml](file:///d:/3.1.%20PTTKYC/CK/puml/07_Activity_POS.puml): Biểu đồ Hoạt động quy trình Bán hàng POS và xuất hóa đơn.
8. [08_Activity_ChamCong.puml](file:///d:/3.1.%20PTTKYC/CK/puml/08_Activity_ChamCong.puml): Biểu đồ Hoạt động quy trình Check-in/Check-out chấm công.
9. [09_Sequence_TruKho.puml](file:///d:/3.1.%20PTTKYC/CK/puml/09_Sequence_TruKho.puml): Biểu đồ Tuần tự đa tầng giao dịch trừ kho nguyên tử `@Transactional`.
10. [10_Sequence_POS.puml](file:///d:/3.1.%20PTTKYC/CK/puml/10_Sequence_POS.puml): Biểu đồ Tuần tự tạo đơn và thanh toán POS.
11. [11_Sequence_ChamCong.puml](file:///d:/3.1.%20PTTKYC/CK/puml/11_Sequence_ChamCong.puml): Biểu đồ Tuần tự chức năng chấm công nhân viên.
12. [12_StateMachine_TrangThai.puml](file:///d:/3.1.%20PTTKYC/CK/puml/12_StateMachine_TrangThai.puml): Biểu đồ Máy trạng thái vòng đời của Bàn (`TRONG` $\leftrightarrow$ `CO_KHACH`) và Hóa đơn (`CHO_THANH_TOAN` $\rightarrow$ `DA_THANH_TOAN` / `DA_HUY`).
13. [13_Package_SpringBoot.puml](file:///d:/3.1.%20PTTKYC/CK/puml/13_Package_SpringBoot.puml): Biểu đồ Cấu trúc Đóng gói Mã nguồn Java (Package Structure).

---

## 2. KIẾN TRÚC PHÂN TẦNG & CƠ SỞ DỮ LIỆU CHUẨN HÓA

### 2.1. Kiến trúc Phân tầng 3 Tầng (3-Tier Spring Boot Architecture)
Hệ thống tuân thủ mô hình 3-Tier chuẩn hóa:
* **Presentation Tier (Tầng giao diện người dùng):**
  * Giao diện HTML5, Bootstrap 5.3, FontAwesome 6, JavaScript ES6 (Fetch API).
  * 4 trang chính: [index.html](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/src/main/resources/static/index.html) (Đăng nhập), [banhang.html](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/src/main/resources/static/banhang.html) (Bán hàng POS), [kho.html](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/src/main/resources/static/kho.html) (Kho & KPI), [nhansu.html](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/src/main/resources/static/nhansu.html) (Nhân sự & Chấm công).
* **Application Tier (Tầng ứng dụng Spring Boot - Port 8080):**
  * **Controller Layer:** Tiếp nhận HTTP Request, giải mã JSON DTO, kiểm tra xác thực quyền hạn thông qua `PhanQuyenSession.java`: `XacThucController`, `BanController`, `MonNuocController`, `DonHangController`, `KhoController`, `NhanSuController`, `ChamCongController`, `CaLamController`, `PhanCaController`.
  * **Service Layer:** Xử lý nghiệp vụ lõi: `DonHangService`, `KhoService`, `NhanSuService`.
  * **Repository Layer (Spring Data JPA):** Truy vấn dữ liệu tự động, hỗ trợ khóa bi quan `LockModeType.PESSIMISTIC_WRITE`.
* **Database Tier (Tầng CSDL - MySQL 8.0 Port 3306 & H2 In-Memory cho Test):**
  * CSDL `quanly_cafe` trên MariaDB/MySQL cho môi trường chạy ứng dụng.
  * CSDL H2 In-Memory cho môi trường kiểm thử `@ActiveProfiles("test")`, độc lập hoàn toàn với dữ liệu thực tế.

### 2.2. Mô hình Dữ liệu Quan hệ (9 Bảng 3NF) & Snapshot Đơn giá
Cơ sở dữ liệu được thiết kế đạt chuẩn chuẩn hóa dạng 3 (3NF) gồm 9 bảng:
1. `nhan_vien`: Quản lý danh tính, mật khẩu, số điện thoại, vai trò (`ADMIN`, `MANAGER`, `STAFF`).
2. `ca_lam`: Định nghĩa 3 ca làm việc (Sáng: 06:30 - 11:30, Chiều: 11:30 - 17:30, Tối: 17:30 - 22:30).
3. `cham_cong`: Lưu vết chấm công thực tế, tính toán trạng thái `DUNG_GIO` hoặc `DI_TRE`.
4. `ban`: Quản lý 10 bàn vật lý với trạng thái `TRONG` hoặc `CO_KHACH`.
5. `mon_nuoc`: Danh mục đồ uống, nhóm món, đơn giá bán, trạng thái kinh doanh.
6. `nguyen_lieu`: Danh mục nguyên vật liệu, đơn vị tính, số lượng tồn, ngưỡng tồn tối thiểu, đơn giá nhập.
7. `cong_thuc`: Bảng định mức BOM nối giữa `mon_nuoc` và `nguyen_lieu`.
8. `hoa_don`: Thông tin đơn hàng, tổng tiền, chiết khấu, phương thức thanh toán, phiên bản `@Version`.
9. `chi_tiet_hoa_don`: Danh sách món trong đơn, số lượng, ghi chú pha chế và trường snapshot `don_gia`.

> [!IMPORTANT]
> **Cơ chế Snapshot Đơn giá:** Khi món được chọn vào đơn, đơn giá tại thời điểm đó được sao chép vào `chi_tiet_hoa_don.don_gia`. Dù quản lý sau này có tăng hoặc giảm giá trong bảng `mon_nuoc`, doanh thu của các hóa đơn đã bán hoặc đang mở hoàn toàn không bị ảnh hưởng.

### 2.3. Cơ chế Kỹ thuật Nâng cao (@Transactional, Lock, Idempotency)
1. **Giao dịch nguyên tử (`@Transactional(rollbackFor = Exception.class)`):**  
   Đảm bảo việc cập nhật trạng thái hóa đơn sang `DA_THANH_TOAN`, giải phóng bàn và trừ kho nguyên liệu là một khối thống nhất (All-or-Nothing). Nếu bất kỳ nguyên liệu nào bị thiếu hụt, Spring Boot tự động kích hoạt `Rollback`, không trừ bất kỳ nguyên liệu nào và bàn vẫn giữ nguyên trạng thái có khách.
2. **Khóa chống bán khống & tranh chấp tài nguyên (Pessimistic Locking & Sorting):**  
   Trong phương thức `truKhoTuDong()`, danh sách mã nguyên liệu được sắp xếp tăng dần theo khóa chính (`ma_nl`), sau đó thực hiện truy vấn khóa ghi `SELECT ... FOR UPDATE`. Điều này triệt tiêu hoàn toàn khả năng gây Deadlock khi có nhiều đơn hàng thanh toán đồng thời chứa cùng các nhóm nguyên liệu.
3. **Khóa lạc quan (`@Version` trên Entity `HoaDon`):**  
   Tránh xung đột ghi đè (Lost Update) khi 2 thu ngân cùng mở và thao tác cập nhật trên cùng một đơn hàng.
4. **Tính bất biến giao dịch (Idempotency Key):**  
   Sử dụng mã định danh UUID từ phía Client gửi kèm yêu cầu thanh toán. Nếu máy chủ đã xử lý hóa đơn đó rồi mà client bấm gửi lại do mạng chậm, hệ thống sẽ trả về hóa đơn hiện tại mà không thực hiện trừ kho lần 2.

---

## 3. CHI TIẾT CÁC LUỒNG LOGIC ĐÃ HIỆN THỰC

### 3.1. Luồng 1: Xác thực & Phân quyền Đa cấp RBAC (Staff / Manager / Admin)
* **Xác thực phiên làm việc (Session-Based Auth):**
  * Endpoint `POST /api/auth/login` kiểm tra tên đăng nhập và mật khẩu. Khi hợp lệ, Spring Boot tạo `HttpSession`, gắn `PhanQuyenSession` với thông tin User ID, Họ tên, Vai trò.
  * Endpoint `POST /api/auth/logout` hủy phiên làm việc trên server và xóa `localStorage` ở client.
* **Phân quyền vai trò chặt chẽ (RBAC Matrix):**
  * **Vai trò STAFF (Nhân viên thu ngân / phục vụ):**
    * Chỉ được phép truy cập và nhìn thấy tab duy nhất: `Bán hàng (POS)`.
    * Hai tab `Kho & KPI` và `Nhân sự & Chấm công` hoàn toàn bị ẩn khỏi thanh điều hướng.
    * Nếu nhân viên cố tình nhập URL trực tiếp `kho.html` hoặc `nhansu.html`, Route Guard lập tức bật thông báo cảnh báo và điều hướng cưỡng bức quay về `banhang.html`.
    * Trên giao diện POS, ô nhập chiết khấu `#pos-discount` bị khóa cứng (`disabled`) kèm huy hiệu cảnh báo `<i class="bi bi-lock-fill"></i> Chỉ Quản lý được chiết khấu`. Khi gửi đơn lên máy chủ, chiết khấu của Staff bị cưỡng bức về $0\text{ đ}$.
    * Backend chặn: Gọi `GET /api/kho/dashboard` hoặc `POST /api/kho/nhap-kho` sẽ nhận mã lỗi `403 FORBIDDEN`.
  * **Vai trò MANAGER (Quản lý cửa hàng):**
    * Truy cập đầy đủ cả 3 phân hệ: `Bán hàng (POS)`, `Kho & KPI` (hiển thị huy hiệu số lượng nguyên liệu sắp hết), `Nhân sự & Chấm công`.
    * Mở khóa ô chiết khấu trên POS, cho phép áp dụng giảm giá hợp lệ.
    * Có toàn quyền nhập kho, cấu hình định mức công thức pha chế BOM.
    * Trong phân hệ Nhân sự: Xem được danh sách nhân viên và chấm công, nhưng nút `+ Thêm nhân viên` bị ẩn (dành riêng cho Admin).
  * **Vai trò ADMIN (Chủ cửa hàng / Quản trị viên hệ thống):**
    * Toàn quyền tối cao trên toàn bộ hệ thống, hiển thị đầy đủ nút `+ Thêm nhân viên` và cấu hình tài khoản.

### 3.2. Luồng 2: Nghiệp vụ Bán hàng POS & Quản lý Sơ đồ Bàn Động
* **Sơ đồ 10 bàn trực quan:**
  * Bàn trống hiển thị viền xanh lá, trạng thái `TRONG`.
  * Bàn có khách hiển thị viền đỏ nổi bật, kèm nhãn `CO_KHACH` và mã hóa đơn tương ứng.
* **Mở lại đơn đang phục vụ:** Nhấp vào bàn có khách, hệ thống gửi `GET /api/order/theo-ban/{id}` và tải nguyên vẹn giỏ hàng món nước đã gọi trước đó lên màn hình.
* **Đơn mang đi (Takeaway):** Hỗ trợ tạo đơn mang đi độc lập mà không chiếm dụng bàn vật lý.
* **Lưu đơn & Gọi món:** Chọn món từ danh mục, điều chỉnh số lượng, ghi chú pha chế (VD: "Ít đá, 50% đường") $\rightarrow$ Nhấn "Lưu đơn" gửi `POST /api/order/tao-don` $\rightarrow$ Hóa đơn được tạo với trạng thái `CHO_THANH_TOAN` và bàn chuyển sang viền đỏ `CO_KHACH`.
* **Thanh toán & Xuất hóa đơn nhiệt:**
  * Hỗ trợ thanh toán Tiền mặt và Chuyển khoản QR.
  * Kiểm tra hợp lệ: Chặn thanh toán nếu tiền khách đưa nhỏ hơn tổng tiền phải trả.
  * Sau khi thanh toán thành công: Hóa đơn chuyển trạng thái `DA_THANH_TOAN`, bàn được tự động giải phóng về viền xanh `TRONG`.
  * Hiển thị popup Hóa đơn nhiệt (Thermal Receipt) chuẩn phong cách quán cà phê Mộc An, hiển thị chi tiết món, giá, chiết khấu, tiền khách đưa và tiền thối.

### 3.3. Luồng 3: Quản trị Kho, Định mức BOM & Trừ kho Tự động Ngầm
* **Thiết lập định mức công thức pha chế (BOM):**
  * Quản lý liên kết giữa món nước và các nguyên liệu thành phần (Ví dụ: 1 ly *Cà phê sữa đá* tiêu tốn $25\text{ ml}$ Cốt cà phê, $30\text{ ml}$ Sữa đặc, $15\text{ ml}$ Sữa tươi).
* **Trừ kho tự động ngầm khi thanh toán:**
  * Ngay khi hàm thanh toán hóa đơn được gọi, hệ thống tự động duyệt qua các món trong hóa đơn $\rightarrow$ tra cứu bảng định mức `cong_thuc` $\rightarrow$ nhân với số lượng món $\rightarrow$ trừ số lượng tồn kho tương ứng trong bảng `nguyen_lieu`.
* **Cơ chế Cảnh báo tồn kho đỏ (Minimum Threshold Alarm):**
  * Khi số lượng tồn của bất kỳ nguyên liệu nào giảm xuống $\le$ `muc_ton_toi_thieu` (Ví dụ: Sữa đặc giảm còn $90\text{ ml} \le 100\text{ ml}$), hệ thống lập tức kích hoạt nhãn màu đỏ cảnh báo `SẮP HẾT HÀNG`.
  * Bảng điều khiển KPI tại trang Kho và huy hiệu cảnh báo trên Topbar tự động nhảy số thông báo cho người quản lý.
* **Nhập kho phục hồi:**
  * Cung cấp modal nhập kho cho phép thủ kho nhập thêm nguyên liệu. Tồn kho được cộng dồn và nhãn cảnh báo đỏ lập tức chuyển về màu xanh `Đủ hàng`.

### 3.4. Luồng 4: Quản trị Nhân sự, Ca làm việc & Chấm công Thời gian thực
* **Quản lý danh sách nhân sự:** Theo dõi họ tên, số điện thoại, vai trò và ca làm việc phân công.
* **Chấm công thông minh thời gian thực:**
  * Nhân viên chọn tên và ca làm việc (Ca Sáng: 06:30 - 11:30, Ca Chiều: 11:30 - 17:30, Ca Tối: 17:30 - 22:30).
  * Khi bấm "Check-in", hệ thống lấy giờ đồng hồ máy chủ đối chiếu với giờ bắt đầu ca:
    * Nếu check-in trong khoảng trước giờ ca hoặc trễ $\le 15$ phút: Ghi nhận trạng thái `DUNG_GIO` (Đúng giờ).
    * Nếu check-in trễ quá 15 phút: Ghi nhận trạng thái `DI_TRE` (Đi trễ).
  * Ghi nhận giờ "Check-out" khi nhân viên kết thúc ca làm việc.

---

## 4. LỊCH SỬ PHÁT HIỆN & KHẮC PHỤC LỖI (BUG FIX LOG)

### 4.1. Bảng Tổng hợp 9 Lỗi Kỹ thuật Đã Sửa và Nghiệm thu 100%

| Mã Lỗi | Phân hệ ảnh hưởng | Mức độ | Hiện tượng trước khi sửa | Giải pháp kỹ thuật đã áp dụng | Trạng thái |
| :---: | :--- | :---: | :--- | :--- | :---: |
| **BUG-01** | Điều hướng & Phân quyền RBAC | **Critical** | Tài khoản `STAFF` có thể nhìn thấy và nhấp vào tab "Kho & KPI" và "Nhân sự". Gõ trực tiếp URL `kho.html` vẫn truy cập được. Staff có quyền chiết khấu giảm giá như Quản lý. | 1. Ẩn hoàn toàn các tab ngoài quyền trên Topbar cho Staff.<br>2. Bổ sung Route Guard điều hướng cưỡng bức về `banhang.html`.<br>3. Khóa cứng ô chiết khấu trên POS đối với Staff.<br>4. Chặn API Backend trả mã `403 FORBIDDEN` qua `PhanQuyenSession`. | **ĐÃ KHẮC PHỤC (VERIFIED)** |
| **BUG-02** | Quản lý phiên làm việc (Session) | **Major** | Nút "Thoát" tại trang `kho.html` và `nhansu.html` sử dụng thẻ `<a href="index.html">`. Khi người dùng bấm thoát, phiên làm việc `HttpSession` trên server không bị hủy. | Đổi sang `<button onclick="thucHienDangXuat()">` gửi request `POST /api/auth/logout`, hủy bỏ phiên `HttpSession` trên server và dọn sạch `localStorage`. | **ĐÃ KHẮC PHỤC (VERIFIED)** |
| **BUG-03** | Trải nghiệm người dùng (UX) | **Minor** | Thao tác nhập kho, lưu công thức, chấm công dùng `window.alert()` mặc định, làm đơ luồng giao diện và tạo cảm giác giật cục. | Tích hợp Bootstrap 5 Toast (`#liveToast`) kèm hiệu ứng xuất hiện mượt mà ở góc phải màn hình, tự động ẩn sau 3 giây. | **ĐÃ KHẮC PHỤC (VERIFIED)** |
| **BUG-04** | Nhập liệu giao diện | **Trivial** | Ô nhập số lượng trong modal "Nhập kho" bị gán cứng giá trị `value="500"`. | Đưa về ô trống với thuộc tính `placeholder="Nhập số lượng..."` để thủ kho chủ động điền số lượng thực tế. | **ĐÃ KHẮC PHỤC (VERIFIED)** |
| **BUG-05** | Tài nguyên tĩnh Web | **Trivial** | Trình duyệt báo lỗi Console đỏ: `GET http://localhost:8080/favicon.ico 404 (Not Found)` tại tất cả các trang. | Tạo biểu tượng tách cà phê chuẩn ICO 16x16 32-bit tại `src/main/resources/static/favicon.ico` và liên kết trên toàn bộ 4 file HTML. | **ĐÃ KHẮC PHỤC (VERIFIED)** |
| **BUG-06** | CSDL & Tiếng Việt có dấu (Mojibake) | **Critical** | Dữ liệu nạp từ CSDL MySQL (tên món, tên bàn, tên nhân viên) bị lỗi mã hóa thành ký tự lạ (`C├á ph├¬`, `─æen ─æ├í`, `Nguy├¬n V─ân Duy`) do lệnh pipe shell Windows chuyển mã sai sang CP1252 khi import; giao diện còn chứa icon emoji không gõ được. | 1. Thêm `SET NAMES utf8mb4;` và Collation `utf8mb4_unicode_ci` vào SQL.<br>2. Bổ sung `connectionCollation=utf8mb4_unicode_ci` vào Spring Datasource URL.<br>3. Nạp CSDL trực tiếp qua cờ `--default-character-set=utf8mb4`.<br>4. Nhúng Google Fonts Roboto hỗ trợ 100% tiếng Việt có dấu và xóa sạch emoji. | **ĐÃ KHẮC PHỤC (VERIFIED)** |
| **BUG-07** | Bố cục Giao diện (Layout Inconsistency) | **Major** | Giao diện xuất hiện đồng thời cả Sidebar bên trái và Header Topbar phía trên, làm lãng phí 260px chiều ngang màn hình POS và gây rối mắt người dùng. | Loại bỏ hoàn toàn Sidebar bên trái (`display: none !important;` trong `mossary.css`), mở rộng toàn bộ không gian cho sơ đồ bàn và giỏ hàng, hợp nhất thanh điều hướng vào **Unified Topbar** chuẩn các phần mềm POS hiện đại (KiotViet, Sapo, iPOS). | **ĐÃ KHẮC PHỤC (VERIFIED)** |
| **BUG-08** | Môi trường Kiểm thử E2E | **Minor** | Thử nghiệm đăng nhập liên tiếp nhiều vai trò trong cùng một phiên Puppeteer bị vướng `localStorage` lưu phiên cũ, dẫn đến tự động redirect và sai lệch kết quả test. | Tách biệt hoàn toàn phiên kiểm thử của từng vai trò thông qua `browser.createBrowserContext()`, đảm bảo tính cô lập độc lập 100%. | **ĐÃ KHẮC PHỤC (VERIFIED)** |
| **BUG-09** | Cú pháp JavaScript tĩnh | **Minor** | Xuất hiện khối mã trùng lặp ở cuối hàm `renderAppShell` trong file `js/dung-chung.js` gây lỗi `SyntaxError: Unexpected token '}'`. | Dọn sạch khối mã trùng lặp, kiểm tra biên dịch hợp lệ qua `node -c js/dung-chung.js`. | **ĐÃ KHẮC PHỤC (VERIFIED)** |

### 4.2. Chi tiết Sửa lỗi Font Tiếng Việt & Mã hóa Mojibake
* **Nguyên nhân gốc rễ:** Lệnh nạp dữ liệu qua PowerShell dạng `type database_mau.sql | mysql` tự động chuyển đổi bảng mã UTF-8 sang mã Windows-1252/CP437 của hệ điều hành, khiến toàn bộ chuỗi tiếng Việt có dấu nạp vào MySQL bị biến dạng (Mojibake). Đồng thời các ký tự emoji đặc biệt không tương thích tốt với một số font hệ thống.
* **Biện pháp xử lý dứt điểm:**
  1. Trong file [database_mau.sql](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/database_mau.sql):
     ```sql
     SET NAMES utf8mb4;
     SET CHARACTER SET utf8mb4;
     CREATE DATABASE IF NOT EXISTS quanly_cafe DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
     ```
  2. Trong [application.properties](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/src/main/resources/application.properties):
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/quanly_cafe?useUnicode=true&characterEncoding=utf8&connectionCollation=utf8mb4_unicode_ci
     ```
  3. Lệnh nạp trực tiếp qua lệnh SOURCE nội bộ của MySQL:
     ```cmd
     C:\xampp\mysql\bin\mysql.exe --default-character-set=utf8mb4 -u root -e "SOURCE d:/3.1. PTTKYC/CK/02_SourceCode_WebJava_Prototype/database_mau.sql;"
     ```
  4. Thay thế toàn bộ emoji bằng biểu tượng Bootstrap Icons hoặc chữ thuần túy, nhúng font chuẩn Roboto từ Google Fonts.

### 4.3. Chi tiết Đại tu Giao diện Unified Topbar & Phân quyền RBAC Toàn diện
* **Thiết kế Unified Topbar:**
  * Gom toàn bộ Brand Title (`MỘC AN COFFEE`), Menu điều hướng nghiệp vụ, Huy hiệu cảnh báo tồn kho, Thông tin nhân viên, Nút làm mới dữ liệu và Nút đăng xuất vào một thanh Topbar nằm ngang duy nhất.
  * Phân hệ Bán hàng POS được giải phóng 260px không gian chiều ngang, giúp thu ngân quan sát rõ ràng toàn bộ 10 bàn và danh mục đồ uống mà không cần cuộn ngang.
* **Bảo vệ Đa tầng RBAC:**
  * *Tầng 1 (Giao diện):* Ẩn các nút điều hướng và khóa trường chiết khấu đối với Staff.
  * *Tầng 2 (Route Guard):* Kiểm tra vai trò ngay khi mở trang, nếu vi phạm quyền lập tức chặn và redirect.
  * *Tầng 3 (REST API):* Mọi API nhạy cảm của Kho (`/api/kho/dashboard`, `/api/kho/nhap-kho`, `/api/kho/cong-thuc`) đều gọi `access.requireManager(session)`. Nếu phiên làm việc là `STAFF`, API lập tức ngắt luồng và trả về mã HTTP `403 FORBIDDEN`.

---

## 5. HIỆN TRẠNG KIỂM THỬ TOÀN DIỆN (TESTING AUDIT)

Chi tiết hồ sơ kiểm thử được ghi nhận tại tài liệu: [BAO_CAO_KIEM_THU.md](file:///d:/3.1.%20PTTKYC/CK/BAO_CAO_KIEM_THU.md)

### 5.1. Kiểm thử Tự động Backend Spring Boot (17/17 Tests PASS)
* **Kết quả:** **17/17 Ca kiểm thử tích hợp ĐẠT (100% PASS)**, 0 Thất bại, 0 Lỗi.
* Lệnh chạy kiểm thử:
  ```powershell
  $env:JAVA_HOME = "F:\JDK" ; Set-Location "d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype" ; .\mvnw.cmd test
  ```
* **Danh sách 14 test cases trong `DonHangIntegrationTest.java`:**
  1. `loginSessionDoesNotExposePassword`: Đăng nhập thành công và không để lộ mật khẩu trong JSON.
  2. `requiresSessionAndUsesServerIdentity`: Chặn truy cập khi thiếu session và định danh nhân viên phía server.
  3. `saveReloadUpdateCancel`: Luồng Lưu đơn $\rightarrow$ Tải lại đơn $\rightarrow$ Cập nhật số lượng $\rightarrow$ Hủy đơn trả bàn về trống (không trừ kho).
  4. `createRetryReturnsSameOrderAndTableRejectsSecond`: Tính bất biến Idempotency và chặn mở 2 đơn trên cùng 1 bàn.
  5. `rejectsInvalidAndEmptyOrders`: Chặn đơn hàng số lượng âm hoặc giỏ hàng trống.
  6. `paymentCashStockAndRetryOnce`: Thanh toán tiền mặt trừ kho chính xác ($120\text{ ml} \rightarrow 90\text{ ml}$); chống trừ kho 2 lần khi retry.
  7. `insufficientStockRollsBackWholePayment`: Tồn kho không đủ kích hoạt `@Transactional` Rollback toàn bộ giao dịch, bảo toàn dữ liệu.
  8. `missingRecipeBlocked`: Chặn thanh toán món chưa có định mức công thức pha chế.
  9. `discountValidationAndRole`: Chặn `STAFF` nhập chiết khấu (`403 Forbidden`); cho phép `MANAGER` chiết khấu hợp lệ.
  10. `transferRequiresConfirmationAndUniqueReference`: Thanh toán chuyển khoản yêu cầu xác nhận tiền vào tài khoản và mã tham chiếu duy nhất.
  11. `pricesRemainSnapshots`: Đơn giá trong đơn giữ nguyên dù danh mục đổi giá sau đó.
  12. `twoConcurrentPaymentsOnlyDeductOnce`: Kiểm thử đa luồng đồng thời: Khóa bi quan và `@Version` đảm bảo chỉ 1 luồng trừ kho thành công.
  13. `twoOrdersCannotOversellSharedIngredient`: Chống bán khống (Oversell) khi 2 đơn cùng tranh chấp lượng tồn kho có hạn.
  14. `duplicateProductLinesAggregateStock`: Gộp định mức các dòng trùng món trong đơn trước khi trừ kho.
* **Danh sách 3 test cases trong `NhanSuIntegrationTest.java`:**
  15. `checkInDungGioVaDiTre`: Kiểm thử logic chấm công nhận diện "Đúng giờ" và "Đi trễ" chuẩn xác theo mốc 15 phút.
  16. `checkOutCapNhatThoiGian`: Kiểm thử cập nhật thời gian check-out ca làm việc.
  17. `staffCannotAccessKhoDashboardOrRestock`: **(Mới bổ sung)** Kiểm tra tài khoản `STAFF` bị chặn truy cập `403 FORBIDDEN` khi gọi API `/api/kho/dashboard` và `/api/kho/nhap-kho`, đồng thời tài khoản `MANAGER` gọi thành công HTTP 200 OK.

### 5.2. Kiểm thử Tự động Giao diện Web E2E Puppeteer Chrome
* Thực hiện tự động thông qua Chrome Headless với độ phân giải tiêu chuẩn màn hình POS ($1280 \times 800$).
* **Kết quả:** **16/16 Kịch bản giao diện ĐẠT (100% PASS)** + Kiểm thử độc lập RBAC cho cả 3 vai trò (`staff1`, `manager`, `admin`).

### 5.3. Bộ Minh chứng Hình ảnh Thực tế (Screenshots Portfolio)
Toàn bộ ảnh chụp thực tế được lưu trữ tập trung tại thư mục [screenshots/](file:///d:/3.1.%20PTTKYC/CK/screenshots/):
* **Bộ ảnh giao diện mới sau khi đại tu Unified Topbar & RBAC:**
  * [fixed_staff_pos_clean.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_staff_pos_clean.png): Giao diện Bán hàng POS của Staff, không có sidebar, thanh Topbar chỉ hiện tab "Bán hàng", ô chiết khấu bị khóa có biểu tượng ổ khóa đỏ.
  * [fixed_manager_pos_clean.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_manager_pos_clean.png): Giao diện Bán hàng POS của Manager, hiển thị đầy đủ 3 tab, ô chiết khấu mở khóa.
  * [fixed_manager_kho_clean.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_manager_kho_clean.png): Giao diện Quản lý Kho & KPI của Manager, hiển thị KPI tồn kho và bảng nguyên liệu.
  * [fixed_manager_nhansu_clean.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_manager_nhansu_clean.png): Giao diện Nhân sự của Manager (nút Thêm nhân viên bị ẩn an toàn).
  * [fixed_admin_nhansu_clean.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_admin_nhansu_clean.png): Giao diện Nhân sự của Admin (nút `+ Thêm nhân viên` hiển thị đầy đủ).
* **Bộ ảnh quy trình nghiệp vụ chi tiết:**
  * [01_login_initial.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/01_login_initial.png) & [01b_login_error.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/01b_login_error.png): Đăng nhập thành công và thông báo lỗi khi nhập sai mật khẩu.
  * [02_pos_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/02_pos_screen.png) & [03_table3_occupied_loaded.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/03_table3_occupied_loaded.png): Sơ đồ 10 bàn và nạp lại đơn bàn 03.
  * [04_cart_table2.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/04_cart_table2.png) & [05_order_saved_table2.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/05_order_saved_table2.png): Thêm món ghi chú ít đường và bàn 02 chuyển đỏ khi lưu đơn.
  * [06_payment_modal_validation.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/06_payment_modal_validation.png) & [07_receipt_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/07_receipt_modal.png): Popup thanh toán hợp lệ và phiếu in hóa đơn nhiệt.
  * [08_kho_alert_kpi.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/08_kho_alert_kpi.png) & [11_kho_restocked.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/11_kho_restocked.png): Nhãn cảnh báo đỏ `SẮP HẾT HÀNG` bật sáng khi tồn kho $\le$ ngưỡng tối thiểu và chuyển về xanh `Đủ hàng` sau khi nhập kho.
  * [09_recipe_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/09_recipe_modal.png) & [10_nhapkho_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/10_nhapkho_modal.png): Modal thiết lập BOM và Modal nhập kho.
  * [12_nhansu_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/12_nhansu_screen.png) & [13_attendance_checked_in.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/13_attendance_checked_in.png): Màn hình nhân sự và chấm công check-in thành công.
  * [15_manager_discount_receipt.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/15_manager_discount_receipt.png): Hóa đơn mang đi do Quản lý áp dụng chiết khấu 5.000 đ thành công.

---

## 6. HỆ THỐNG CÁC TỆP TIN MARKDOWN & TÀI NGUYÊN DỰ ÁN

| Tệp tin | Kích thước | Vai trò & Mục đích sử dụng | Đường dẫn truy cập |
| :--- | :---: | :--- | :--- |
| **BAO_CAO.md** | ~146 KB (1.220+ dòng) | Báo cáo học thuật toàn diện môn học PTTKYC (Đầy đủ 9 chương, có cấu trúc tiểu mục 1.1, 1.1.1,... chi tiết; ghi chú đầy đủ vị trí chèn ảnh sơ đồ PlantUML và ảnh chụp kiểm thử giao diện). | [BAO_CAO.md](file:///d:/3.1.%20PTTKYC/CK/BAO_CAO.md) |
| **KICH_BAN.md** | ~28 KB (600+ dòng) | Kịch bản thuyết trình bảo vệ đồ án 12 - 15 phút, khớp nối 100% với 16 slide của `slide.html`. Phân công lời thoại rõ ràng cho Duy, Quý, Đạt, hướng dẫn ngôn ngữ cơ thể và 4 bước demo trực tiếp. | [KICH_BAN.md](file:///d:/3.1.%20PTTKYC/CK/KICH_BAN.md) |
| **slide.html** | ~105 KB (16 slides) | Slide trình chiếu Web phong cách "Trang vở ô ly học sinh" (Student Notebook Grid), trang trí băng dính washi tape, hiệu ứng lật trang, phím tắt `Arrow` điều hướng, phím `S` xem speaker notes, phím `F` toàn màn hình. | [slide.html](file:///d:/3.1.%20PTTKYC/CK/slide.html) |
| **BAO_CAO_KIEM_THU.md** | ~19 KB | Báo cáo kiểm thử chuyên sâu: Ma trận kiểm thử backend 14 test cases, kết quả kiểm thử E2E giao diện 16 kịch bản, nhật ký log thực thi. | [BAO_CAO_KIEM_THU.md](file:///d:/3.1.%20PTTKYC/CK/BAO_CAO_KIEM_THU.md) |
| **lamviec/LAMVIEC.md** | ~75 KB (1.638 dòng) | Tài liệu làm việc kỹ thuật nội bộ nhóm: Đặc tả Use Case chi tiết 9 trường Fully-Dressed, từ điển dữ liệu 9 bảng, mã nguồn PlantUML và bộ câu hỏi phản biện Q&A giảng viên. | [lamviec/LAMVIEC.md](file:///d:/3.1.%20PTTKYC/CK/lamviec/LAMVIEC.md) |
| **README.md** | ~10 KB | Tài liệu kỹ thuật nguyên mẫu mã nguồn Spring Boot, hướng dẫn cài đặt JDK, XAMPP, cấu hình CSDL và danh sách REST API endpoints. | [02_SourceCode_WebJava_Prototype/README.md](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/README.md) |
| **CONTEXT_TOM_TAT.md** | ~35 KB | Tệp tin snapshot tổng hợp tri thức và tiến trình kỹ thuật của toàn bộ dự án tại thư mục gốc, phục vụ tiếp nối các phiên làm việc. | [CONTEXT_TOM_TAT.md](file:///d:/3.1.%20PTTKYC/CK/CONTEXT_TOM_TAT.md) |

---

## 7. ĐÁNH GIÁ & ĐỒNG BỘ NHÁNH GIT (MAIN VS DAT)

### 7.1. Lịch sử phân nhánh & Các thay đổi của Đạt
* Nhánh `Dat` được tách ra từ `main` để thực hiện phân hệ Nhân sự và Chấm công theo phân công 33%.
* **Các đóng góp của Đạt:**
  * Bổ sung Entity `CaLam`, `ChamCong`, `NhanVien` và thiết lập quan hệ JPA.
  * Hiện thực Controller & Service xử lý tính toán chấm công Check-in / Check-out theo mốc 15 phút.
  * Xây dựng giao diện ban đầu của `nhansu.html` và viết 2 test case cơ bản trong `NhanSuIntegrationTest.java`.
* **Những khiếm khuyết trong nhánh Dat trước khi đồng bộ về main:**
  * Bị ảnh hưởng bởi lỗi font chữ tiếng Việt Mojibake khi nạp dữ liệu.
  * Chưa xử lý phân quyền RBAC: Nhân viên vai trò `STAFF` vẫn thấy nút thêm nhân viên và truy cập được các module khác.
  * Còn tồn tại bố cục Sidebar thừa làm hẹp diện tích sử dụng.

### 7.2. Tình trạng trên nhánh `main` hiện tại
* Nhánh `main` hiện là nhánh chuẩn cao nhất (commit `5ae7c97`), đã:
  * Khắc phục triệt để lỗi font tiếng Việt `utf8mb4`.
  * Đại tu loại bỏ Sidebar, chuyển sang Unified Topbar.
  * Hiện thực cơ chế phân quyền bảo mật 3 tầng RBAC (Frontend Navigation, Route Guards, Backend REST API `PhanQuyenSession` trả mã 403 Forbidden).
  * Bổ sung test case thứ 3 `staffCannotAccessKhoDashboardOrRestock` nâng tổng số test lên **17/17 PASS**.
  * Cập nhật đầy đủ các tài liệu học thuật: `BAO_CAO.md`, `KICH_BAN.md`, `slide.html`, `BAO_CAO_KIEM_THU.md`.
* **Khuyến nghị cho Đạt:** Đồng bộ nhánh `main` về nhánh `Dat` bằng lệnh:
  ```powershell
  git checkout Dat
  git merge main
  ```

---

## 8. HƯỚNG DẪN KHỞI CHẠY HỆ THỐNG CHO PHIÊN LÀM VIỆC TIẾP THEO

### 8.1. Tham số môi trường máy tính
* **Đường dẫn JDK:** `F:\JDK` (Java 25.0.1 LTS / tương thích Spring Boot 3).
* **Đường dẫn XAMPP:** `C:\xampp\` (MySQL / MariaDB trên cổng mạng mặc định `3306`).
* **Tên CSDL:** `quanly_cafe` (Tài khoản root mặc định: `user: root`, `password: `).
* **Thư mục mã nguồn:** `d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype`

### 8.2. Trình tự các lệnh khởi chạy hệ thống
1. **Khởi động MySQL (chạy ngầm không bị tắt đột ngột trên Windows):**
   ```powershell
   Start-Process -FilePath "C:\xampp\mysql\bin\mysqld.exe" -ArgumentList "--defaults-file=C:\xampp\mysql\bin\my.ini --standalone"
   ```
2. **Nạp lại dữ liệu mẫu tiếng Việt chuẩn (khi cần làm sạch dữ liệu):**
   ```cmd
   C:\xampp\mysql\bin\mysql.exe --default-character-set=utf8mb4 -u root -e "SOURCE d:/3.1. PTTKYC/CK/02_SourceCode_WebJava_Prototype/database_mau.sql;"
   ```
3. **Chạy kiểm thử tự động toàn bộ Backend:**
   ```powershell
   $env:JAVA_HOME = "F:\JDK"
   Set-Location "d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype"
   .\mvnw.cmd test
   ```
4. **Khởi động ứng dụng Spring Boot:**
   ```powershell
   $env:JAVA_HOME = "F:\JDK"
   Set-Location "d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype"
   .\mvnw.cmd spring-boot:run
   ```
5. **Truy cập ứng dụng trên trình duyệt:**
   * URL chính thức: `http://localhost:8080/index.html`
   * Bảng tài khoản thử nghiệm các vai trò:
     * `admin` / `123456` (Vai trò `ADMIN`: Toàn quyền quản trị, thêm nhân sự).
     * `manager` / `123456` (Vai trò `MANAGER`: Quản lý kho, BOM, chiết khấu, chấm công).
     * `staff1` / `123456` (Vai trò `STAFF`: Thu ngân bán hàng, sơ đồ bàn, khóa chiết khấu).
     * `staff2` / `123456` (Vai trò `STAFF`: Nhân viên phục vụ/pha chế, chấm công ca).

---
*Tài liệu tóm tắt được tổng hợp tự động và nghiệm thu hoàn chỉnh vào lúc 15:40 ngày 19/11/2026.*
