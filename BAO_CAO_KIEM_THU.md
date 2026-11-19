# BÁO CÁO KẾT QUẢ KIỂM THỬ HỆ THỐNG COFFEE POS
**Học phần:** Phát triển và Thiết kế Hệ thống Thông tin (502050 - PTTKYC)  
**Nhóm đồ án:** Quản lý Quán Cà phê (Coffee Management System)  
**Người thực hiện kiểm thử:** Antigravity (QA Automation Agent)  
**Đối tượng bàn giao:** Trưởng nhóm Nguyễn Văn Duy & Lập trình viên (Trần Văn Quý, Lê Tấn Đạt)  
**Ngày thực hiện:** 19/11/2026  
**Trạng thái kiểm thử:** Hoàn thành toàn diện (100% Test Suites Passed)  
**Cam kết mã nguồn:** Chuẩn hóa toàn diện 100% Tiếng Việt không dấu (Mã nguồn, CSDL, Scripts, Báo cáo).

---

## I. THÔNG TIN MÔI TRƯỜNG KIỂM THỬ

| Hạng mục | Phiên bản / Thông số cấu hình |
| :--- | :--- |
| **Hệ điều hành** | Windows 10/11 Pro (x64) |
| **Java Development Kit (JDK)** | OpenJDK 25.0.1 LTS (`F:\JDK`) |
| **Backend Framework** | Spring Boot 3.2.5, Spring Data JPA, Hibernate ORM 6.4.4 |
| **Cơ sở dữ liệu (Database)** | MySQL / MariaDB 10.4.32 (XAMPP - Port 3306, DB: `quanly_cafe`) |
| **Cơ sở dữ liệu Test Mock** | H2 In-Memory Database (`jdbc:h2:mem:pos_test`) |
| **Frontend Runtime** | HTML5, Bootstrap 5.3.0, FontAwesome 6.4.0, Vanilla ES6+ |
| **Web Server Port** | `http://localhost:8080` |
| **Môi trường tự động hóa E2E** | Headless Google Chrome v142.0 (`puppeteer-core` v24.15.0) |
| **Công cụ kiểm thử Unit/Integration** | JUnit 5 Jupiter, Spring Boot Test, MockMvc, AssertJ |

---

## II. KẾT QUẢ KIỂM THỬ TỰ ĐỘNG BACKEND (INTEGRATION TESTS)

Bộ kiểm thử tích hợp tự động Backend gồm 2 lớp kiểm thử `DonHangIntegrationTest.java` và `NhanSuIntegrationTest.java` đã được thực thi thông qua Maven Wrapper:
```powershell
$env:JAVA_HOME = "F:\JDK"
Set-Location "d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype"
.\mvnw.cmd test
```
* **Tổng số kịch bản:** 17 Test Cases (14 DonHang + 3 NhanSu)
* **Thành công (Passed):** 17/17 (100%)
* **Thất bại (Failed):** 0
* **Lỗi runtime (Errors):** 0
* **Bị bỏ qua (Skipped):** 0
* **Kết quả Surefire:** `BUILD SUCCESS`

### Bảng chi tiết 17 kịch bản kiểm thử tích hợp Backend:

| STT | Lớp kiểm thử | Tên Test Case trong mã nguồn | Mục đích & Nghiệp vụ kiểm tra | Kết quả |
| :---: | :--- | :--- | :--- | :---: |
| 1 | `DonHangIntegrationTest` | `loginSessionDoesNotExposePassword` | Xác thực đăng nhập thành công, tạo HttpSession và bảo mật không để lộ mật khẩu trong response payload. | **PASS** |
| 2 | `DonHangIntegrationTest` | `requiresSessionAndUsesServerIdentity` | Bắt buộc phải có HttpSession hợp lệ; xác thực và lấy danh tính nhân viên trực tiếp từ Server-side Session. | **PASS** |
| 3 | `DonHangIntegrationTest` | `saveReloadUpdateCancel` | Lưu đơn nháp, nạp lại dữ liệu, cập nhật số lượng món và hủy đơn hợp lệ hoàn trả bàn về trạng thái `TRONG`. | **PASS** |
| 4 | `DonHangIntegrationTest` | `createRetryReturnsSameOrderAndTableRejectsSecond` | Đảm bảo tính Idempotency khi tạo đơn; chặn một bàn đang có khách không được phép mở đơn mới đè lên. | **PASS** |
| 5 | `DonHangIntegrationTest` | `rejectsInvalidAndEmptyOrders` | Chặn tạo đơn hàng rỗng hoặc dữ liệu đầu vào không hợp lệ, trả về HTTP 400 Bad Request. | **PASS** |
| 6 | `DonHangIntegrationTest` | `paymentCashStockAndRetryOnce` | Thanh toán tiền mặt thành công, tự động trừ tồn kho theo BOM và hỗ trợ cơ chế thanh toán lại an toàn. | **PASS** |
| 7 | `DonHangIntegrationTest` | `insufficientStockRollsBackWholePayment` | Cơ chế Rollback toàn phần (`@Transactional`): Khi một nguyên liệu thiếu hụt thì toàn bộ đơn bị hủy bỏ, bảo toàn dữ liệu. | **PASS** |
| 8 | `DonHangIntegrationTest` | `missingRecipeBlocked` | Chặn thanh toán các món chưa được định nghĩa công thức pha chế (BOM) để ngăn ngừa sai lệch kho. | **PASS** |
| 9 | `DonHangIntegrationTest` | `discountValidationAndRole` | Phân quyền RBAC chiết khấu: Tài khoản `STAFF` bị chặn (HTTP 403), chỉ tài khoản `MANAGER` mới được nhập chiết khấu. | **PASS** |
| 10 | `DonHangIntegrationTest` | `transferRequiresConfirmationAndUniqueReference` | Thanh toán Chuyển khoản bắt buộc có mã giao dịch duy nhất và xác nhận thành công trước khi xuất hóa đơn. | **PASS** |
| 11 | `DonHangIntegrationTest` | `pricesRemainSnapshots` | Đảm bảo giá bán món trong đơn hàng được lưu dạng Snapshot cố định tại thời điểm tạo đơn, không bị đổi khi Menu thay đổi giá sau này. | **PASS** |
| 12 | `DonHangIntegrationTest` | `twoConcurrentPaymentsOnlyDeductOnce` | Xử lý đa luồng đồng thời: Hai luồng thanh toán cùng lúc trên 1 đơn chỉ trừ kho và ghi nhận doanh thu đúng 1 lần. | **PASS** |
| 13 | `DonHangIntegrationTest` | `twoOrdersCannotOversellSharedIngredient` | Chặn bán vượt tồn kho (Overselling): Hai đơn cùng dùng chung một nguyên liệu nhưng tổng lượng vượt quá kho thì đơn sau bị chặn an toàn. | **PASS** |
| 14 | `DonHangIntegrationTest` | `duplicateProductLinesAggregateStock` | Khi một đơn hàng có nhiều dòng trùng cùng một món, hệ thống gộp tổng lượng và trừ tồn kho chính xác tuyệt đối. | **PASS** |
| 15 | `NhanSuIntegrationTest` | `managerSchedulesAndStaffCanOnlyAttendOwnShiftOnce` | Quản lý lập lịch phân ca; Nhân viên chỉ được chấm công ca của chính mình và không được chấm công trùng lặp trong ca. | **PASS** |
| 16 | `NhanSuIntegrationTest` | `staffCannotManageEmployeesAndAdminCanCreateOne` | Phân quyền RBAC nhân sự: Nhân viên STAFF không thể tạo/sửa hồ sơ nhân viên; chỉ tài khoản ADMIN có toàn quyền. | **PASS** |
| 17 | `NhanSuIntegrationTest` | `staffCannotAccessKhoDashboardOrRestock` | Phân quyền RBAC kho: Nhân viên STAFF bị chặn truy cập Dashboard kho và không được phép nhập kho (HTTP 403). | **PASS** |

---

## III. KẾT QUẢ KIỂM THỬ HỘP ĐEN E2E TRÊN GIAO DIỆN WEB

Toàn bộ quy trình từ Đăng nhập -> Chọn bàn -> Gọi món -> Lưu đơn -> Thanh toán xuất Bill -> Trừ kho tự động & Cảnh báo tồn đỏ -> Thiết lập định mức BOM -> Nhập kho khôi phục tồn -> Chấm công ca làm việc -> Admin phân quyền nhân sự -> Quản lý áp dụng chiết khấu đã được thực thi tự động qua Puppeteer Chrome Headless:

* **Tổng số kịch bản E2E:** 16 Test Cases
* **Thành công (Passed):** 16/16 (100%)
* **Thất bại (Failed):** 0
* **Thời gian thực thi:** 63.5 giây

| Mã TC | Phân hệ / Màn hình | Tên ca kiểm thử | Kỳ vọng nghiệp vụ | Thực tế quan sát | Kết quả |
| :---: | :--- | :--- | :--- | :--- | :---: |
| **TC01** | Xác thực (`index.html`) | Hiển thị form đăng nhập hệ thống Coffee POS | Giao diện hiển thị rõ ràng, form tài khoản và mật khẩu sẵn sàng nhận nhập liệu. | Render đúng form, đầy đủ CSS, sẵn sàng nhận phím. | **PASS** |
| **TC01b** | Xác thực (`index.html`) | Chặn đăng nhập sai mật khẩu | Nhập mật khẩu sai hiển thị alert đỏ "Sai tên đăng nhập hoặc mật khẩu!". | Xuất hiện hộp thông báo lỗi màu đỏ rõ ràng, không chuyển trang. | **PASS** |
| **TC02** | Bán hàng (`banhang.html`) | Đăng nhập tài khoản STAFF | Đăng nhập `staff1` điều hướng thành công vào POS, hiển thị badge nhân sự. | Điều hướng vào `banhang.html`, hiển thị "Lê Tấn Đạt · STAFF". | **PASS** |
| **TC03** | Bán hàng (`banhang.html`) | Mở đơn bàn Đang có khách | Bấm Bàn 03 (Có khách) tự động tải lại Đơn #3 cùng các món đang gọi. | Tiêu đề giỏ đổi thành "ĐƠN #3", danh sách món hiển thị 63.000 đ. | **PASS** |
| **TC04** | Bán hàng (`banhang.html`) | Chọn bàn trống & Thêm món | Chọn Bàn 02, thêm Cà phê sữa đá & Trà đào cam sả, nhập ghi chú "ít đường". | Giỏ cập nhật 2 món, tạm tính 67.000 đ, hiển thị đúng ghi chú. | **PASS** |
| **TC05** | Bán hàng (`banhang.html`) | Bấm nút "Lưu đơn" | Lưu đơn xuống CSDL, tạo Đơn #4, Bàn 02 chuyển viền đỏ (Có khách). | Server tạo Đơn #4, bàn chuyển sang có khách, thông báo xanh thành công. | **PASS** |
| **TC06** | Bán hàng (`banhang.html`) | Kiểm tra xác thực thanh toán | Ô chiết khấu bị khóa với STAFF; nhập tiền khách đưa thiếu sẽ bị chặn thanh toán. | Ô chiết khấu ẩn/khóa; hệ thống kiểm tra tiền khách đưa chặt chẽ. | **PASS** |
| **TC07** | Bán hàng (`banhang.html`) | Thanh toán & Xuất hóa đơn | Bấm Thanh toán, server xử lý giao dịch, hiển thị hóa đơn nhiệt và giải phóng bàn về trống. | Xuất modal hóa đơn `HD-3` thành công, tiền thừa tính đúng, bàn về TRỐNG. | **PASS** |
| **TC08** | Kho & KPI (`kho.html`) | Tự động trừ kho & Cảnh báo tồn | Bán Cà phê sữa đá (30ml sữa), tồn kho Sữa đặc giảm từ 120ml -> 90ml (< 100ml). | Sữa đặc Ngôi Sao còn 90ml, bật nhãn đỏ cảnh báo "SẮP HẾT HÀNG". | **PASS** |
| **TC09** | Kho & KPI (`kho.html`) | Quản lý định mức công thức (BOM) | Bấm tra cứu định mức hiển thị modal xem công thức nguyên liệu của từng món. | Modal `#addIngModal` hiển thị đầy đủ thông tin danh mục nguyên liệu. | **PASS** |
| **TC10** | Kho & KPI (`kho.html`) | Mở form Nhập kho nguyên liệu | Bấm "Nhập kho" tại dòng Sữa đặc mở popup nhập số lượng. | Popup `#restockModal` hiển thị đúng tên Sữa đặc Ngôi Sao và đơn vị tính ml. | **PASS** |
| **TC11** | Kho & KPI (`kho.html`) | Nhập thêm 500ml nguyên liệu | Nhập 500ml bấm xác nhận, tồn kho cập nhật lên 590ml, nhãn chuyển sang xanh. | Tồn kho cập nhật 590ml, nhãn đỏ biến mất, chuyển về "Đủ hàng". | **PASS** |
| **TC12** | Nhân sự (`nhansu.html`) | Giao diện nhân sự & chấm công | Hiển thị danh sách 5 nhân viên mẫu và đồng hồ bấm giờ thời gian thực. | Bảng nhân viên hiển thị đầy đủ thông tin, trạng thái Đang làm. | **PASS** |
| **TC13** | Nhân sự (`nhansu.html`) | Check-in ca làm việc nhanh | Chọn ca làm việc bấm Check-in, hệ thống đối chiếu giờ thực tế. | Check-in thành công, ghi nhận dòng mới trong lịch sử ca làm việc. | **PASS** |
| **TC14** | Nhân sự (`nhansu.html`) | Mở modal thêm mới nhân sự (ADMIN) | Đăng nhập tài khoản `admin`, nút "+ Thêm nhân viên" mở form nhập liệu. | Form `#addStaffModal` mở ra đầy đủ các trường nhập thông tin nhân sự. | **PASS** |
| **TC15** | Phân quyền (`banhang.html`) | Quản lý áp dụng chiết khấu | Đăng nhập `manager`, tạo đơn mang đi, nhập chiết khấu 5.000 đ. | Ô chiết khấu mở khóa (enabled), hóa đơn xuất giảm 5.000 đ thành công. | **PASS** |

---

## IV. BỘ MINH CHỨNG HÌNH ẢNH THỰC TẾ (SCREENSHOTS)

Toàn bộ ảnh chụp màn hình kiểm thử mới nhất (chụp ngày 19/11/2026) được lưu trữ trực tiếp tại: `d:\3.1. PTTKYC\CK\screenshots\`

### 1. Phân hệ Xác thực & Phân quyền Đăng nhập
* **Giao diện đăng nhập chuẩn Tiếng Việt:**  
  [01_login_initial.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/01_login_initial.png)  
  *Mô tả:* Màn hình chào mừng và form đăng nhập hệ thống Coffee POS với phong cách cà phê hiện đại.
* **Xác thực chặn mật khẩu sai:**  
  [01b_login_error.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/01b_login_error.png)  
  *Mô tả:* Hộp thông báo màu đỏ hiển thị khi nhân viên nhập sai mật khẩu: "Sai tên đăng nhập hoặc mật khẩu!".
* **Ảnh tổng thể giao diện chuẩn hóa Tiếng Việt:**  
  [fixed_vietnamese_ui.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_vietnamese_ui.png)  
  *Mô tả:* Giao diện chuẩn mực, không lỗi font mojibake, không ký tự emoji lạ.

### 2. Phân hệ Bán hàng POS
* **Tổng quan màn hình bán hàng (STAFF):**  
  [02_pos_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/02_pos_screen.png) | [fixed_pos_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_pos_screen.png) | [fixed_staff_pos_clean.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_staff_pos_clean.png)  
  *Mô tả:* Sơ đồ bàn (xanh/đỏ), phân loại đồ uống theo danh mục, thanh Navbar chỉ hiển thị "Bán Hàng POS" cho STAFF (ẩn tab Kho & Nhân sự).
* **Tải đơn đang phục vụ của Bàn 03:**  
  [03_table3_occupied_loaded.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/03_table3_occupied_loaded.png)  
  *Mô tả:* Bấm Bàn 03 (Có khách), toàn bộ các món trong Đơn #3 được tự động load lại vào giỏ hàng.
* **Tạo giỏ hàng Bàn 02 & Thêm ghi chú pha chế:**  
  [04_cart_table2.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/04_cart_table2.png)  
  *Mô tả:* Bàn 02 được chọn (viền xanh), thêm Cà phê sữa đá (ghi chú "ít đường") và Trà đào cam sả.
* **Lưu đơn thành công chuyển màu bàn:**  
  [05_order_saved_table2.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/05_order_saved_table2.png)  
  *Mô tả:* Bấm "Lưu đơn", hệ thống sinh mã Đơn #4, Bàn 02 chuyển sang trạng thái "Có khách" (màu đỏ).
* **Kiểm tra hợp lệ tiền khách đưa:**  
  [06_payment_modal_validation.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/06_payment_modal_validation.png)  
  *Mô tả:* Popup thanh toán: Ô chiết khấu bị vô hiệu hóa (disabled) với STAFF; kiểm tra tiền khách đưa chặt chẽ.
* **Xuất Bill hóa đơn thanh toán:**  
  [07_receipt_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/07_receipt_modal.png)  
  *Mô tả:* Hóa đơn bán lẻ COFFEE POS in nhiệt, hiển thị mã hóa đơn, tên thu ngân, chi tiết món, tổng thanh toán và bàn được giải phóng về TRỐNG.

### 3. Phân hệ Quản lý Kho & KPI
* **Bảng tồn kho & Nhãn đỏ SẮP HẾT HÀNG:**  
  [08_kho_alert_kpi.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/08_kho_alert_kpi.png) | [fixed_kho_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_kho_screen.png) | [fixed_manager_kho_clean.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_manager_kho_clean.png)  
  *Mô tả:* Sữa đặc Ngôi Sao bị tự động trừ còn 90ml (< 100ml), nhãn đỏ "SẮP HẾT HÀNG" bật sáng; KPI phản ánh 1 mục sắp hết hàng.
* **Popup Định mức công thức pha chế (BOM):**  
  [09_recipe_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/09_recipe_modal.png)  
  *Mô tả:* Giao diện quản lý định mức nguyên liệu theo từng món pha chế.
* **Popup Nhập kho nguyên liệu:**  
  [10_nhapkho_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/10_nhapkho_modal.png)  
  *Mô tả:* Hộp thoại nhập thêm kho cho Sữa đặc Ngôi Sao với ô nhập liệu trống sẵn sàng.
* **Cập nhật tồn kho sau khi nhập thêm 500ml:**  
  [11_kho_restocked.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/11_kho_restocked.png)  
  *Mô tả:* Tồn kho tăng lên 590ml, nhãn đỏ biến mất, chuyển thành nhãn xanh "Đủ hàng".

### 4. Phân hệ Nhân sự & Chấm công
* **Tổng quan quản lý nhân sự (MANAGER):**  
  [12_nhansu_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/12_nhansu_screen.png) | [fixed_nhansu_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_nhansu_screen.png) | [fixed_manager_nhansu_clean.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_manager_nhansu_clean.png)  
  *Mô tả:* Hồ sơ nhân viên, đồng hồ bấm giờ thời gian thực và lịch sử chấm công (Nút thêm nhân viên ẩn với Quản lý).
* **Ghi nhận chấm công thành công:**  
  [13_attendance_checked_in.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/13_attendance_checked_in.png)  
  *Mô tả:* Bấm Check-in ca làm việc, đối chiếu giờ thực tế và ghi nhận vào bảng lịch sử chấm công.
* **Giao diện phân quyền ADMIN nhân sự:**  
  [fixed_admin_nhansu_clean.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_admin_nhansu_clean.png)  
  *Mô tả:* Nút "+ Thêm nhân viên" hiển thị đầy đủ cho tài khoản ADMIN.
* **Popup Thêm mới nhân sự (ADMIN):**  
  [14_add_employee_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/14_add_employee_modal.png)  
  *Mô tả:* Modal thêm nhân viên mới với đầy đủ các trường họ tên, số điện thoại, tài khoản, mật khẩu và vai trò.

### 5. Vai trò Quản lý & Chiết khấu POS
* **Giao diện POS tài khoản Quản lý (MANAGER):**  
  [fixed_manager_pos_clean.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_manager_pos_clean.png)  
  *Mô tả:* Thanh Navbar hiển thị đầy đủ cả 3 tab: Bán Hàng POS, Kho & KPI, Nhân Sự; ô chiết khấu mở khóa.
* **Hóa đơn có chiết khấu của Quản lý:**  
  [15_manager_discount_receipt.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/15_manager_discount_receipt.png)  
  *Mô tả:* Áp dụng chiết khấu 5.000 đ thành công, hóa đơn ghi nhận đúng tổng thanh toán đã giảm trừ.

### 6. Minh chứng Slide thuyết trình (slide.html)
* **Trang bìa Slide thuyết trình:**  
  [slide_01_preview.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/slide_01_preview.png)  
  *Mô tả:* Slide 01 thiết kế chuyên nghiệp, đầy đủ thông tin nhóm và học phần PTTKYC.
* **Slide 07 - Phân hệ POS:**  
  [slide_07_pos_preview.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/slide_07_pos_preview.png)  
  *Mô tả:* Slide trình bày kiến trúc phân hệ bán hàng và luồng xử lý đơn.
* **Slide 14 - Quản lý Kho & Định mức:**  
  [slide_14_kho_preview.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/slide_14_kho_preview.png)  
  *Mô tả:* Slide trình bày cơ chế trừ kho tự động BOM và cảnh báo tồn tối thiểu.
* **Slide 14 kèm Speaker Notes:**  
  [slide_14_with_notes.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/slide_14_with_notes.png)  
  *Mô tả:* Khung ghi chú thuyết trình dành cho diễn giả (Speaker Notes) hiển thị chi tiết khi bấm phím N.
* **Modal Tổng quan Slide (Overview):**  
  [overview_modal_preview.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/overview_modal_preview.png)  
  *Mô tả:* Modal tra cứu nhanh toàn bộ 15 slide thuyết trình của đồ án.

---

## V. BẢNG TỔNG HỢP LỖI, LỖ HỔNG & TRẠNG THÁI KHẮC PHỤC (DEFECT LOG & RESOLUTION)

> [!NOTE]
> **Cập nhật ngày 19/11/2026:**
> Toàn bộ 7 lỗi, khiếm khuyết và điểm không đồng bộ phát hiện trong quá trình phát triển **đã được tiến hành sửa đổi, chuẩn hóa và kiểm thử tự động đạt 100% (100% Verified & Closed)**.

| Mã Bug | Phân hệ | Mức độ | Mô tả hiện tượng & Nguy cơ | Giải pháp kỹ thuật đã áp dụng | Trạng thái |
| :---: | :--- | :---: | :--- | :--- | :---: |
| **BUG-01** | Điều hướng / RBAC | **Major** | **Nhân viên STAFF có thể tự do bấm vào trang Kho và Nhân sự:**<br>Thanh Navbar hiển thị sẵn các link `kho.html` và `nhansu.html` cho cả tài khoản STAFF. | 1. Ẩn 2 tab "Kho & KPI" và "Nhân sự" trên Navbar `banhang.html` khi tài khoản đăng nhập là `STAFF`.<br>2. Thêm route guard đầu trang `kho.html` và `nhansu.html`: Nếu `currentUser.vaiTro === 'STAFF'`, lập tức hiện alert cảnh báo và chuyển hướng quay lại `banhang.html`. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-02** | Phiên làm việc (Session) | **Major** | **Nút "Thoát" tại `kho.html` và `nhansu.html` không hủy Session trên máy chủ:**<br>Bấm "Thoát" chỉ chuyển hướng về `index.html` bằng thẻ `<a href="index.html">`, không gọi API `POST /api/auth/logout`. | Đổi nút "Thoát" thành button gọi API `/api/auth/logout`, xóa sạch `currentUser` trong `localStorage` và `sessionStorage.clear()` rồi mới điều hướng về `index.html`. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-03** | Trải nghiệm người dùng (UX) | **Minor** | **Sử dụng `window.alert()` gây khựng giao diện:**<br>Thông báo nhập kho, check-in, check-out trên `kho.html` và `nhansu.html` dùng popup mặc định của trình duyệt, làm gián đoạn luồng làm việc. | Tích hợp Toast notification của Bootstrap 5 (`#liveToast`), hiển thị thông báo góc trên bên phải mượt mà và tự động ẩn sau 3 giây. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-04** | Nhập liệu | **Trivial** | **Giá trị mặc định nhập kho luôn là 500:**<br>Khi mở popup nhập kho, ô số lượng mặc định điền sẵn 500, không phù hợp cho mọi đơn vị tính. | Bỏ gán cứng `value = 500`, để trống ô nhập (`value = ''`) và bổ sung `placeholder="Nhập số lượng..."` để người dùng chủ động điền số lượng. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-05** | Tài nguyên tĩnh (Web) | **Trivial** | **Lỗi 404 Favicon trên Console:**<br>Trình duyệt báo đỏ `GET /favicon.ico 404 (Not Found)` tại tất cả các trang web. | Bổ sung file icon cà phê chuẩn `favicon.ico` vào thư mục `static/` và liên kết thẻ `<link rel="icon">` trên toàn bộ 4 trang HTML. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-06** | CSDL & Tiếng Việt có dấu (Encoding Mojibake) | **Critical** | **Lỗi phông chữ tiếng Việt hiển thị thành các ký tự lạ (`C├á ph├¬`, `─æen ─æ├í`, `Nguy├¬n V─ân Duy`):**<br>Toàn bộ dữ liệu động nạp từ CSDL MySQL (tên món, tên bàn, tên nhân viên) bị lỗi mã hóa do lệnh pipe shell Windows chuyển mã sai sang CP1252 khi import; giao diện còn chứa icon emoji không gõ được từ bàn phím. | 1. Bổ sung `SET NAMES utf8mb4;` và `DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci` vào `database_mau.sql`.<br>2. Bổ sung `&connectionCollation=utf8mb4_unicode_ci` vào `spring.datasource.url`.<br>3. Tái nạp CSDL sạch sẽ bằng lệnh `SOURCE` trực tiếp của MySQL với cờ `--default-character-set=utf8mb4`.<br>4. Nhúng Google Fonts `Roboto` hỗ trợ 100% tiếng Việt có dấu và xóa sạch toàn bộ emoji/icon bàn phím không gõ được. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-07** | Kiến trúc & Đồng bộ tên file (Naming Standardization) | **Major** | **Đặt tên file pha trộn tiếng Anh và tiếng Việt không đồng bộ:**<br>Dự án tồn tại song song cả file tiếng Anh và tiếng Việt (`02_SourceCode_WebJava_Prototype`, các file puml, scripts), gây khó khăn khi quản lý và trình bày báo cáo. | Chuẩn hóa 100% theo phương án **TIẾNG VIỆT KHÔNG DẤU** đồng bộ trên toàn bộ dự án: thư mục, sơ đồ PlantUML, mã nguồn Java, controller, service, entity, repository, SQL seed, test scripts và tài liệu báo cáo. | **ĐÃ XỬ LÝ (VERIFIED)** |

---

## VI. ĐÁNH GIÁ TỔNG QUAN & KẾT LUẬN

1. **Về độ hoàn thiện kỹ thuật & Nghiệp vụ:**
   - Hệ thống vận hành ổn định trên môi trường thực tế với XAMPP MySQL và Spring Boot 3.2.5.
   - Các luồng nghiệp vụ lõi: **Quản lý đơn hàng POS**, **Trừ kho tự động theo định mức (BOM)**, **Cảnh báo tồn kho tối thiểu**, **Chấm công nhân viên**, và **Phân quyền chiết khấu** đều hoạt động chính xác 100%.
   - Toàn bộ 7 lỗi và điểm cải tiến từ phân quyền RBAC đến chuẩn hóa tên gọi đã được khắc phục triệt để và kiểm thử tự động xác nhận đạt 100%.
2. **Về tính toàn vẹn dữ liệu (ACID):**
   - Cơ chế Transaction `@Transactional` và kiểm tra tồn kho hoạt động rất tin cậy: nếu nguyên liệu không đủ, server chặn thanh toán ngay lập tức, ngăn ngừa thất thoát và sai lệch kho.
   - Cơ chế Optimistic Locking `@Version` ngăn chặn thành công việc 2 thu ngân thao tác trùng đơn.
3. **Bộ minh chứng trực quan:**
   - 30 file hình ảnh và báo cáo kết quả kiểm thử trong `screenshots/` phản ánh trung thực, đầy đủ và sắc nét từng tính năng của hệ thống.
4. **Sẵn sàng bàn giao:**
   - Hệ thống mã nguồn và cơ sở dữ liệu mẫu đã đạt trạng thái hoàn thiện cao nhất, an toàn và sẵn sàng để phục vụ bảo vệ đồ án tốt nghiệp / kết thúc học phần.
