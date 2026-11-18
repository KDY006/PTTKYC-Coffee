# KỊCH BẢN THUYẾT TRÌNH BẢO VỆ ĐỒ ÁN CUỐI KỲ
## ĐỀ TÀI: HỆ THỐNG QUẢN LÝ QUÁN CÀ PHÊ (COFFEE POS & INVENTORY SYSTEM)

---

> **HỌC PHẦN:** Phân tích và Thiết kế Yêu cầu (Mã môn: `502050`) – Trường Đại học Tôn Đức Thắng  
> **GIẢNG VIÊN HƯỚNG DẪN:** TS. Hồ Thị Linh  
> **NHÓM BÁO CÁO:**  
> - **Nguyễn Văn Duy (Nhóm trưởng – 34%):** Mở đầu, Bối cảnh, Kiến trúc 3 tầng, Phân hệ Kho & Định mức BOM, Luồng trừ kho `@Transactional`, Kết luận & Điều phối Q&A.  
> - **Trần Văn Quý (Thành viên – 33%):** Yêu cầu chức năng, MoSCoW, Sơ đồ Use Case, Luồng nghiệp vụ Bán hàng POS & Demo POS.  
> - **Lê Tấn Đạt (Thành viên – 33%):** Yêu cầu phi chức năng (NFR), Thiết kế CSDL 9 bảng (ERD), Phân hệ Nhân sự & Chấm công thời gian thực & Demo Chấm công.  
> **TỔNG THỜI LƯỢNG THUYẾT TRÌNH & DEMO:** $12\text{--}15\text{ phút}$ (Thuyết trình: ~9 phút; Live Demo: ~4.5 phút; Q&A: 5–8 phút).

---

## I. MA TRẬN PHÂN BỔ THỜI GIAN & PHÂN VAI CHI TIẾT

| Mốc thời gian | Nội dung trình bày | Người phụ trách | Slide tương ứng |
| :---: | :--- | :---: | :---: |
| **00:00 – 02:30** | Mở đầu, Bối cảnh quán cà phê, 3 Điểm nghẽn (*As-Is*), Mục tiêu (*To-Be*) & Kiến trúc 3 tầng | **Duy** | Slide 1, 2, 3, 4 |
| **02:30 – 04:30** | Yêu cầu chức năng (15 FRs), Bảng MoSCoW, Biểu đồ Use Case tổng quát | **Quý** | Slide 5, 6 |
| **04:30 – 06:30** | Phân hệ Bán hàng POS, Sơ đồ 10 bàn, Luồng tạo đơn & Thanh toán + **[DEMO 1: BÁN HÀNG POS]** | **Quý** | Slide 7, 8, 9 |
| **06:30 – 08:30** | Yêu cầu phi chức năng (NFR), Mô hình ERD CSDL 9 bảng, Phân hệ Nhân sự | **Đạt** | Slide 10, 11 |
| **08:30 – 10:00** | Quy trình Chấm công ca làm việc đối chiếu thời gian thực + **[DEMO 2: CHẤM CÔNG NHÂN SỰ]** | **Đạt** | Slide 12 |
| **10:00 – 12:00** | Phân hệ Kho, Định mức công thức BOM, Biểu đồ Tuần tự Trừ kho `@Transactional` | **Duy** | Slide 13, 14 |
| **12:00 – 13:30** | **[DEMO 3: TRỪ KHO TỰ ĐỘNG & CẢNH BÁO TỒN ĐỎ]** | **Duy** | Slide 15 |
| **13:30 – 14:30** | Kết quả kiểm thử (100% Pass), Chiến lược chuyển giao (Direct Cutover) & Kết luận | **Duy** | Slide 16 |
| **Sau 14:30** | Điều phối trả lời câu hỏi phản biện của Giảng viên (Q&A: 5 – 8 phút) | **Cả nhóm** | Slide kết thúc |

---

## II. LỜI THOẠI CHI TIẾT THEO TỪNG SLIDE (PRESENTATION SCRIPT)

---

### SLIDE 1: TRANG TIÊU ĐỀ & GIỚI THIỆU NHÓM
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 30 giây
* **Nội dung trên slide:** Tên đề tài, GVHD: TS. Hồ Thị Linh, Thành viên nhóm (Duy, Quý, Đạt).
* **Vị trí hình ảnh trên slide:** Note ô logo Trường ĐH Tôn Đức Thắng và biểu tượng đồ án.

> 🗣️ **LỜI THOẠI (DUY):**  
> *"Kính thưa Cô Hồ Thị Linh và toàn thể các bạn sinh viên đang có mặt trong buổi báo cáo ngày hôm nay!*  
> *Em tên là Nguyễn Văn Duy – Trưởng nhóm đề tài. Hôm nay, nhóm chúng em gồm 3 thành viên: em – Nguyễn Văn Duy, bạn Trần Văn Quý và bạn Lê Tấn Đạt rất vinh dự được đại diện nhóm trình bày báo cáo bảo vệ đồ án môn học Phân tích và Thiết kế Yêu cầu với đề tài: **'Hệ Thống Quản Lý Quán Cà Phê – Coffee POS & Inventory Management System'**.*  
> *Sau đây, em xin phép được bắt đầu phần trình bày của nhóm."*

---

### SLIDE 2: BỐI CẢNH THỰC TẾ & 3 "ĐIỂM NGHẼN" VẬN HÀNH THỦ CÔNG
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 1 phút
* **Nội dung trên slide:** Quy trình As-Is thủ công, 3 điểm nghẽn (Order nhầm lẫn, Thất thoát kho ngầm, Lịch ca xung đột).
* **Vị trí hình ảnh trên slide:** Note sơ đồ 3 nút thắt nghiệp vụ thủ công.

> 🗣️ **LỜI THOẠI (DUY):**  
> *"Thưa Cô, xuất phát từ quá trình khảo sát thực tế tại các mô hình quán cà phê vừa và nhỏ hiện nay, nhóm nhận thấy hầu hết các quán vẫn đang vận hành theo cách thủ công thô sơ, dẫn đến 3 'điểm nghẽn' chí mạng:*  
> * **Thứ nhất:** Nhân viên phục vụ ghi order trên giấy viết tay, vào giờ cao điểm rất dễ thất lạc phiếu, nhầm bàn, phục vụ chậm khiến khách hàng bức xúc.  
> * **Thứ hai:** Nhân viên pha chế làm theo thói quen cảm tính; cuối ngày chủ quán kiểm kho chỉ đếm số lượng lon sữa, gói trà còn thừa mà hoàn toàn không thể đối chiếu xem hao hụt thực tế có khớp với số lượng ly đã bán hay do thất thoát, đổ vỡ ngầm.  
> * **Thứ ba:** Lực lượng nhân viên phần lớn là sinh viên làm việc part-time đổi ca liên tục. Bảng phân ca giấy bị gạch xóa nhầm lẫn, cuối tháng rất khó tổng hợp chính xác số giờ công thực tế.*  
> *Ba điểm nghẽn này chính là lý do thôi thúc nhóm em xây dựng một giải pháp phần mềm số hóa toàn diện."*

---

### SLIDE 3: MỤC TIÊU HỆ THỐNG & PHẠM VI DỰ ÁN (IN-SCOPE & OUT-OF-SCOPE)
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 45 giây
* **Nội dung trên slide:** Mục tiêu To-Be; Bảng phạm vi In-Scope (POS, Kho định mức BOM, Chấm công nhân sự) & Out-of-Scope (Kế toán thuế, Cổng thanh toán ngân hàng trực tiếp).
* **Vị trí hình ảnh trên slide:** Note bảng đối chiếu phạm vi In-Scope vs Out-of-Scope.

> 🗣️ **LỜI THOẠI (DUY):**  
> *"Về mục tiêu hệ thống (*To-Be*), nhóm hướng tới xây dựng một ứng dụng Web tập trung, kết nối đồng bộ và tức thì luồng dữ liệu giữa: **Gọi món $\rightarrow$ Trừ kho ngầm tự động $\rightarrow$ Chấm công nhân sự $\rightarrow$ Báo cáo KPI**.*  
> *Để đảm bảo tính khả thi và tập trung sâu vào chất lượng, nhóm xác định rõ ranh giới:*  
> * **Phạm vi thực hiện (In-Scope):** Bao gồm 3 phân hệ cốt lõi: Phân hệ Bán hàng POS 10 bàn; Phân hệ Kho bóc tách công thức pha chế (BOM) và cảnh báo tồn tối thiểu; Phân hệ Nhân sự phân ca và chấm công thời gian thực.  
> * **Ngoại phạm vi (Out-of-Scope):** Nhóm không bao gồm module hạch toán kế toán chuyên sâu về thuế VAT và không tích hợp cổng thanh toán ngân hàng trực tiếp qua thẻ mà mô phỏng thanh toán tiền mặt và chuyển khoản QR."*

---

### SLIDE 4: KIẾN TRÚC PHÂN TẦNG VÀ THÀNH PHẦN (3-TIER ARCHITECTURE)
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 45 giây
* **Nội dung trên slide:** Mô hình 3-Tier Layered Architecture (Presentation -> Application -> Database).
* **Vị trí hình ảnh trên slide:** Note vị trí chèn `diagram/KienTrucTongThe.svg`.

> 🗣️ **LỜI THOẠI (DUY):**  
> *"Về mặt kiến trúc kỹ thuật, hệ thống được nhóm thiết kế tuân thủ nghiêm ngặt mô hình **3-Tier Layered Architecture** chuẩn mực của Java Spring Boot:*  
> * **Tầng Presentation:** Hoạt động trên trình duyệt Client với HTML5, Bootstrap 5.3, phông chữ Roboto tiếng Việt và JavaScript Fetch API bất đồng bộ.  
> * **Tầng Application:** Máy chủ Spring Boot chạy trên cổng 8080, phân tách trách nhiệm rành mạch giữa Controller điều hướng, Service xử lý logic nghiệp vụ và Repository truy vấn ORM.  
> * **Tầng Database:** Cơ sở dữ liệu quan hệ MySQL chạy trên cổng 3306, đảm bảo tính toàn vẹn dữ liệu giao dịch ACID.*  
> *Tiếp theo đây, em xin mời bạn **Trần Văn Quý** trình bày chi tiết về phần phân tích yêu cầu và luồng Bán hàng POS."*

---

### SLIDE 5: DANH MỤC YÊU CẦU CHỨC NĂNG (15 FRS) & BẢNG PHÂN LOẠI MOSCOW
* **Người trình bày:** Trần Văn Quý
* **Thời lượng:** 1 phút
* **Nội dung trên slide:** Danh mục 15 yêu cầu chức năng (FR-01 đến FR-15) chia 4 nhóm; Bảng phân loại ưu tiên MoSCoW.
* **Vị trí hình ảnh trên slide:** Note bảng tổng hợp 15 FRs và ma trận MoSCoW.

> 🗣️ **LỜI THOẠI (QUÝ):**  
> *"Em xin chào Cô và các bạn! Em tên là Trần Văn Quý.*  
> *Trong đồ án này, em phụ trách việc phân tích yêu cầu và xây dựng phân hệ Bán hàng POS. Nhóm đã xác định và đánh mã đầy đủ **15 yêu cầu chức năng cốt lõi (FR-01 đến FR-15)**:*  
> * Nhóm 1: Xác thực đăng nhập và bảo vệ phiên làm việc.  
> * Nhóm 2: Sơ đồ bàn động, gọi món tại bàn, mang đi, ghi chú pha chế, áp dụng chiết khấu và xuất hóa đơn nhiệt.  
> * Nhóm 3: Quản lý danh mục nguyên liệu, thiết lập định mức công thức BOM, tự động trừ kho và cảnh báo hết hàng.  
> * Nhóm 4: Quản lý hồ sơ nhân viên và chấm công đối chiếu thời gian thực.*  
> *Qua kỹ thuật phân loại **MoSCoW**, nhóm xếp toàn bộ các tính năng cốt lõi vào nhóm **Must-have** và nhóm đã cài đặt thực tế đạt 100% trên phần mềm chạy thật."*

---

### SLIDE 6: MÔ HÌNH HÓA USE CASE TỔNG QUÁT TOÀN HỆ THỐNG
* **Người trình bày:** Trần Văn Quý
* **Thời lượng:** 1 phút
* **Nội dung trên slide:** Biểu đồ Use Case tổng quát, Ranh giới hệ thống, 3 tác nhân (Staff, Manager, Admin), quan hệ `<<include>>`, `<<extend>>`.
* **Vị trí hình ảnh trên slide:** Note vị trí chèn `diagram/UseCaseTongQuat.svg`.

> 🗣️ **LỜI THOẠI (QUÝ):**  
> *"Trên màn hình là Biểu đồ Use Case tổng quát của hệ thống theo chuẩn UML 2.5:*  
> * Hệ thống có 3 tác nhân chính: **Staff** (Nhân viên thu ngân/phục vụ), **Manager** (Quản lý cửa hàng) và **Admin** (Quản trị viên).  
> * Tất cả các Use Case nghiệp vụ chính đều bắt buộc `<<include>>` tới Use Case **Xác thực đăng nhập** để bảo mật hệ thống.  
> * Đặc biệt, hai quan hệ `<<extend>>` thể hiện nghiệp vụ thực tế rất rõ nét: Chức năng **Áp dụng chiết khấu** mở rộng cho **Thanh toán hóa đơn** (chỉ dành riêng cho Quản lý); và chức năng **Cảnh báo tồn tối thiểu** mở rộng cho luồng **Tự động trừ kho** khi nguyên liệu chạm ngưỡng an toàn."*

---

### SLIDE 7: PHÂN HỆ BÁN HÀNG POS & SƠ ĐỒ 10 BÀN THỜI GIAN THỰC
* **Người trình bày:** Trần Văn Quý
* **Thời lượng:** 1 phút
* **Nội dung trên slide:** Giao diện POS, Sơ đồ 10 bàn đổi màu động (Xanh: Trống, Đỏ: Có khách), Thực đơn 20 món và Giỏ hàng.
* **Vị trí hình ảnh trên slide:** Note vị trí chèn `screenshots/fixed_pos_screen.png`.

> 🗣️ **LỜI THOẠI (QUÝ):**  
> *"Tiếp theo là Phân hệ Bán hàng POS – giao diện tương tác chính của thu ngân:*  
> * Góc trên bên trái là **Sơ đồ 10 bàn trực quan** đổi màu theo thời gian thực: Bàn trống hiển thị viền xanh lá, bàn đang có khách phục vụ hiển thị viền đỏ kèm mã đơn hàng. Thu ngân chỉ cần bấm vào bàn đỏ là toàn bộ món đang gọi được tải lại tức thì.  
> * Góc dưới là thực đơn 20 món nước phân loại theo danh mục (Cà phê, Trà, Đá xay, Nước ép) có ô tìm kiếm nhanh.  
> * Cột bên phải là Giỏ hàng thông minh, hỗ trợ tăng giảm số lượng, nhập ghi chú pha chế như 'ít đường, không đá' và tự động tính tổng tiền."*

---

### SLIDE 8: QUY TRÌNH GỌI MÓN, LƯU ĐƠN & THANH TOÁN XUẤT HÓA ĐƠN
* **Người trình bày:** Trần Văn Quý
* **Thời lượng:** 1 phút
* **Nội dung trên slide:** Biểu đồ Hoạt động & Tuần tự luồng POS; Modal thanh toán xác thực tiền khách đưa, Hóa đơn nhiệt in ra.
* **Vị trí hình ảnh trên slide:** Note vị trí chèn `diagram/Activity_POS.svg` và `screenshots/07_receipt_modal.png`.

> 🗣️ **LỜI THOẠI (QUÝ):**  
> *"Quy trình nghiệp vụ bán hàng diễn ra chặt chẽ theo 3 bước:*  
> * **Bước 1 – Lưu đơn:** Khi bấm Lưu đơn, máy chủ tạo bản ghi `HoaDon` ở trạng thái `CHO_THANH_TOAN` và chuyển bàn sang màu đỏ. Đơn giá tại thời điểm này được chụp lại (*Price Snapshot*) không bị ảnh hưởng nếu sau này menu đổi giá.  
> * **Bước 2 – Kiểm tra thanh toán:** Modal thanh toán kiểm tra nghiêm ngặt: nếu tiền khách đưa nhỏ hơn tổng tiền phải thu, hệ thống lập tức chặn lại và báo lỗi. Nếu là tài khoản Staff, ô chiết khấu tự động bị khóa để chống gian lận.  
> * **Bước 3 – Xuất hóa đơn:** Khi thanh toán đủ tiền, hệ thống xuất hóa đơn nhiệt COFFEE POS hoàn chỉnh, tính đúng tiền thối và giải phóng bàn về lại màu xanh trống.*  
> *Và ngay sau đây, em xin phép chuyển sang màn hình ứng dụng để **Demo trực tiếp luồng Bán hàng POS**."*

---

### SLIDE 9: [LIVE DEMO PHẦN 1] TRÌNH DIỄN LUỒNG BÁN HÀNG POS
* **Người trình bày:** Trần Văn Quý
* **Thời lượng:** 1.5 phút
* **Nội dung trên slide:** Các bước kịch bản Demo POS thực tế trên trình duyệt `http://localhost:8080/banhang.html`.
* **Vị trí hình ảnh trên slide:** Note khung giao diện demo trực tiếp.

> 🗣️ **LỜI THOẠI THAO TÁC DEMO (QUÝ):**  
> *(Quý thao tác trực tiếp trên trình duyệt)*  
> *"Thưa Cô, em đăng nhập tài khoản **staff1 / 123456**.  
> 1. Giao diện điều hướng vào `banhang.html`. Cô có thể thấy trên thanh Navbar, hai tab 'Kho' và 'Nhân sự' đã tự động ẩn hoàn toàn để bảo vệ phân quyền.  
> 2. Em chọn **Bàn 02** đang trống (màu xanh). Em gọi **1 ly Cà phê sữa đá**, nhập ghi chú **'ít đường'** và thêm **1 ly Trà đào cam sả**. Tạm tính là 67.000 đ.  
> 3. Em bấm **'Lưu đơn'** $\rightarrow$ Hệ thống lập tức sinh **Đơn #4** và Bàn 02 chuyển sang **viền đỏ có khách**.  
> 4. Bây giờ em bấm **'Thanh toán & Xuất hóa đơn'**. Em thử nhập tiền khách đưa là 50.000 đ $\rightarrow$ Modal báo lỗi đỏ chặn lại ngay: 'Tiền khách đưa chưa đủ'. Đồng thời ô chiết khấu bị khóa disabled.  
> 5. Em nhập đúng 100.000 đ và bấm Xác nhận $\rightarrow$ Popup hóa đơn nhiệt xuất hiện với đầy đủ thông tin thu ngân, tính đúng tiền thừa 33.000 đ và Bàn 02 đã tự động quay về màu xanh trống!  
> *Phần tiếp theo về Cơ sở dữ liệu và Nhân sự, em xin nhường lời cho bạn **Lê Tấn Đạt**."*

---

### SLIDE 10: YÊU CẦU PHI CHỨC NĂNG (NFR) & THIẾT KẾ CSDL 9 BẢNG (ERD)
* **Người trình bày:** Lê Tấn Đạt
* **Thời lượng:** 1 phút
* **Nội dung trên slide:** 6 NFRs định lượng; Mô hình ERD 9 bảng chuẩn hóa 3NF (Crow's Foot Notation).
* **Vị trí hình ảnh trên slide:** Note vị trí chèn `diagram/ERD_DuLieu.svg`.

> 🗣️ **LỜI THOẠI (ĐẠT):**  
> *"Em xin kính chào Cô và các bạn! Em tên là Lê Tấn Đạt.*  
> *Về mặt yêu cầu phi chức năng (NFR), nhóm đặt ra các chỉ số định lượng khắt khe: thời gian phản hồi API dưới 500ms, tỷ lệ toàn vẹn giao dịch đạt 100%, và 0% rò rỉ mật khẩu trong JSON.*  
> *Để đáp ứng các yêu cầu đó, CSDL của hệ thống được thiết kế gồm **9 bảng thực thể chuẩn hóa dạng chuẩn 3NF**:  
> * Nhóm bán hàng gồm: `ban`, `mon_nuoc`, `hoa_don`, và `chi_tiet_hoa_don` (quan hệ hợp thành 1-N).  
> * Nhóm kho gồm: `nguyen_lieu` và bảng liên kết `cong_thuc` (BOM) giải quyết quan hệ nhiều - nhiều với món nước.  
> * Nhóm nhân sự gồm: `nhan_vien`, `ca_lam` và bảng ghi nhận `cham_cong`.*  
> *Toàn bộ CSDL sử dụng bảng mã `utf8mb4` hỗ trợ tiếng Việt có dấu hoàn hảo."*

---

### SLIDE 11: PHÂN HỆ QUẢN LÝ NHÂN SỰ & CHẤM CÔNG THỜI GIAN THỰC
* **Người trình bày:** Lê Tấn Đạt
* **Thời lượng:** 1 phút
* **Nội dung trên slide:** Giao diện Quản lý nhân sự `nhansu.html`; Biểu đồ Hoạt động chấm công phân làn; Logic nhận diện đi trễ (> 15 phút).
* **Vị trí hình ảnh trên slide:** Note vị trí chèn `diagram/Activity_ChamCong.svg` và `screenshots/fixed_nhansu_screen.png`.

> 🗣️ **LỜI THOẠI (ĐẠT):**  
> *"Điểm đặc sắc của Phân hệ Nhân sự là cơ chế **Chấm công thời gian thực**:*  
> * Trên giao diện trang bị một đồng hồ bấm giờ kỹ thuật số đồng bộ thời gian thực của máy chủ.  
> * Khi nhân viên chọn ca và bấm Check-in, hệ thống lấy giờ thực tế trừ đi giờ bắt đầu ca: Nếu đi trễ trong vòng 15 phút, hệ thống tự động ghi nhận nhãn xanh **'Đúng giờ'**; nếu trễ quá 15 phút, hệ thống tự động gắn nhãn đỏ **'Đi trễ'**.  
> * Cung cấp nút Check-out khi tan ca và modal thêm mới hồ sơ nhân sự phân quyền.*  
> *Và sau đây, em xin phép **Demo trực tiếp Phân hệ Chấm công**."*

---

### SLIDE 12: [LIVE DEMO PHẦN 2] TRÌNH DIỄN CHẤM CÔNG NHÂN SỰ
* **Người trình bày:** Lê Tấn Đạt
* **Thời lượng:** 1.5 phút
* **Nội dung trên slide:** Kịch bản Demo Chấm công thời gian thực và Thêm nhân viên trên `http://localhost:8080/nhansu.html`.
* **Vị trí hình ảnh trên slide:** Note khung giao diện demo trực tiếp.

> 🗣️ **LỜI THOẠI THAO TÁC DEMO (ĐẠT):**  
> *(Đạt thao tác trực tiếp trên trình duyệt)*  
> *"Thưa Cô, em đăng nhập tài khoản **manager / 123456** và vào trang `nhansu.html`:  
> 1. Đồng hồ thời gian thực tế đang chạy chính xác từng giây.  
> 2. Tại khối Chấm công, em chọn nhân viên **Lê Tấn Đạt** và chọn **Ca Chiều (12:00 - 17:00)**.  
> 3. Em bấm **'Check-in Vào Ca'** $\rightarrow$ Toast thông báo xanh xuất hiện ngay ở góc phải: 'Check-in thành công! Trạng thái: ĐI TRỄ' (do hiện tại là buổi chiều sau 12:15).  
> 4. Dòng chấm công mới lập tức được nạp vào bảng Lịch sử bên phải kèm nút **Check-out** sẵn sàng khi tan ca.  
> 5. Cột bên trái hiển thị danh sách 5 nhân viên; em mở modal **'Thêm Nhân Viên'** để thêm nhân viên mới nhanh chóng.*  
> *Tiếp theo, em xin trân trọng kính mời Trưởng nhóm **Nguyễn Văn Duy** trình bày nghiệp vụ lõi về Kho và Giao dịch trừ kho."*

---

### SLIDE 13: PHÂN HỆ QUẢN TRỊ KHO & ĐỊNH MỨC CÔNG THỨC PHA CHẾ (BOM)
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 1 phút
* **Nội dung trên slide:** Giao diện Kho & KPI `kho.html`; Bảng định mức BOM (Recipe); Cơ chế cảnh báo tồn tối thiểu `SẮP HẾT HÀNG`.
* **Vị trí hình ảnh trên slide:** Note vị trí chèn `screenshots/fixed_kho_screen.png` và `screenshots/09_recipe_modal.png`.

> 🗣️ **LỜI THOẠI (DUY):**  
> *"Kính thưa Cô, em xin tiếp tục với Phân hệ Quản trị Kho – trọng tâm kỹ thuật do em trực tiếp thiết kế và lập trình:*  
> * **Bảng định mức công thức (BOM - Recipe):** Mỗi món nước được số hóa định lượng hao phí chính xác. Ví dụ: 1 ly Cà phê sữa đá cần đúng $25\text{ ml}$ Cốt cà phê Robusta và $30\text{ ml}$ Sữa đặc Ngôi Sao.  
> * **Thẻ KPI Dashboard:** Tự động tính tổng doanh thu ngày, tổng số đơn đã hoàn thành và số mặt hàng sắp hết.  
> * **Cơ chế Cảnh báo tồn đỏ:** Mỗi nguyên liệu có một ngưỡng an toàn (*Minimum Threshold*). Khi tồn kho giảm xuống $\le$ ngưỡng này, hệ thống tự động kích hoạt nhãn đỏ **`SẮP HẾT HÀNG`** kèm hiệu ứng nhấp nháy để thủ kho chủ động nhập thêm."*

---

### SLIDE 14: LUỒNG TRỪ KHO TỰ ĐỘNG @TRANSACTIONAL & KHÓA CHỐNG BÁN KHỐNG
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 1 phút
* **Nội dung trên slide:** Biểu đồ Tuần tự Trừ kho; Giao dịch nguyên tử ACID; Khóa bi quan `SELECT ... FOR UPDATE`; Khóa lạc quan `@Version`.
* **Vị trí hình ảnh trên slide:** Note vị trí chèn `diagram/Sequence_TruKho.svg`.

> 🗣️ **LỜI THOẠI (DUY):**  
> *"Đây là bài toán kỹ thuật phức tạp nhất mà em đã giải quyết:*  
> * Toàn bộ luồng thanh toán và trừ kho được bọc trong annotation **`@Transactional(rollbackFor = Exception.class)`**.  
> * Khi duyệt qua các nguyên liệu trong công thức, hệ thống thực thi khóa bi quan **`SELECT ... FOR UPDATE`** theo thứ tự ID tăng dần để triệt tiêu Deadlock và ngăn chặn hoàn toàn nguy cơ 2 đơn cùng tranh chấp gây âm kho (chống bán khống Overselling).  
> * Nếu kho không đủ dù chỉ $1\text{ ml}$, hệ thống lập tức ném ngoại lệ và **Rollback 100%**: hóa đơn giữ nguyên chờ thanh toán, bàn vẫn đỏ có khách, kho giữ nguyên an toàn.  
> * Sau đây, em xin phép **Demo trực tiếp cơ chế Trừ kho tự động và Cảnh báo đỏ**."*

---

### SLIDE 15: [LIVE DEMO PHẦN 3] TRÌNH DIỄN TRỪ KHO & CẢNH BÁO TỒN ĐỎ
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 1.5 phút
* **Nội dung trên slide:** Kịch bản Demo Trừ kho ngầm sau khi bán hàng, nhãn đỏ SẮP HẾT HÀNG và Nhập thêm 500ml trên `http://localhost:8080/kho.html`.
* **Vị trí hình ảnh trên slide:** Note khung giao diện demo trực tiếp.

> 🗣️ **LỜI THOẠI THAO TÁC DEMO (DUY):**  
> *(Duy thao tác trực tiếp trên trình duyệt)*  
> *"Thưa Cô, em mở trang `kho.html`:  
> 1. Kính mời Cô quan sát dòng nguyên liệu số 2: **'Sữa đặc Ngôi Sao'**. Trước khi bạn Quý bán hàng, tồn kho là 120ml. Sau khi bạn Quý thanh toán Đơn #4 (chứa 1 ly Cà phê sữa đá có định mức 30ml sữa), hệ thống đã **tự động trừ ngầm đúng 30ml**, tồn kho hiện tại chỉ còn đúng **90ml**!  
> 2. Vì ngưỡng tối thiểu là 100ml, mà 90ml $\le$ 100ml, nên hệ thống đã **tự động bật nhãn đỏ cảnh báo nhấp nháy 'SẮP HẾT HÀNG'** và thẻ KPI báo động đỏ 'Nguyên liệu sắp hết: 1'.  
> 3. Bây giờ em bấm vào nút **'Nhập kho'** tại dòng Sữa đặc, em nhập thêm **500 ml** và bấm Xác nhận.  
> 4. Tồn kho lập tức cập nhật lên **590 ml**, nhãn cảnh báo đỏ biến mất và khôi phục nhãn xanh **'Đủ hàng'** ngay lập tức mà không cần tải lại trang!"*

---

### SLIDE 16: KẾT QUẢ KIỂM THỬ (100% PASS), CHIẾN LƯỢC CHUYỂN GIAO & KẾT LUẬN
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 1 phút
* **Nội dung trên slide:** Kết quả kiểm thử (14/14 Backend Tests Pass, 16/16 E2E Tests Pass); Chiến lược Direct Cutover; Lời cảm ơn và mời Q&A.
* **Vị trí hình ảnh trên slide:** Note bảng tổng hợp kết quả kiểm thử và lời cảm ơn.

> 🗣️ **LỜI THOẠI (DUY):**  
> *"Kính thưa Cô, để bảo chứng cho độ tin cậy của phần mềm:*  
> * Hệ thống đã vượt qua xuất sắc **14/14 ca kiểm thử tích hợp tự động Backend (`PosIntegrationTest.java`) đạt 100%** trên H2 In-Memory tách biệt.  
> * Vượt qua **16/16 kịch bản kiểm thử hộp đen E2E tự động trên trình duyệt Chrome đạt 100%**.  
> * Khắc phục triệt để 6 lỗi kỹ thuật từ phân quyền đến bảng mã tiếng Việt có dấu.  
> * Nhóm đề xuất chiến lược chuyển giao trực tiếp vào đầu tuần (**Direct Cutover on Monday**) để đưa phần mềm vào vận hành thực tế tại các quán cà phê vừa và nhỏ.*  
> *Bài thuyết trình của nhóm đến đây là kết thúc. Chúng em xin chân thành cảm ơn Cô đã chú ý lắng nghe và nhóm chúng em rất mong nhận được những câu hỏi phản biện và đóng góp quý báu từ Cô ạ!"*

---

## III. BỘ CÂU HỎI PHẢN BIỆN (Q&A) THƯỜNG GẶP & GỢI Ý TRẢ LỜI CỦA GIẢNG VIÊN

### Câu hỏi 1 (Về kiến trúc & Concurrency):
> **Hỏi:** *"Tại sao nhóm lại dùng Khóa bi quan (`SELECT ... FOR UPDATE`) khi trừ kho mà không dùng Khóa lạc quan (`@Version`)?"*  
> **Trả lời (Duy):** *"Thưa Cô, Khóa lạc quan (`@Version`) chỉ phù hợp cho các thực thể mà xác suất sửa đồng thời thấp, khi xung đột thì ném lỗi bắt người dùng làm lại từ đầu (như sửa đơn hàng). Nhưng đối với Tồn kho nguyên liệu, khi quán đông khách có 2 thu ngân cùng bấm thanh toán 2 đơn cà phê sữa tại cùng một giây, nếu dùng Khóa lạc quan thì một trong hai đơn sẽ bị văng lỗi bắt thu ngân thanh toán lại, gây tắc nghẽn quầy tính tiền. Vì vậy nhóm chọn **Khóa bi quan (`SELECT ... FOR UPDATE`)**: luồng thứ 2 sẽ xếp hàng chờ trong tích tắc (vài mili-giây) luồng thứ 1 trừ xong, rồi luồng 2 đọc tồn kho mới để trừ tiếp, giúp cả 2 đơn đều thanh toán mượt mà mà kho vẫn trừ chính xác, hoàn toàn không bị bán khống."*

### Câu hỏi 2 (Về thiết kế CSDL):
> **Hỏi:** *"Tại sao trong bảng `chi_tiet_hoa_don` lại lưu thuộc tính `don_gia_ban` trong khi bảng `mon_nuoc` đã có `don_gia` rồi?"*  
> **Trả lời (Đạt):** *"Thưa Cô, đây là giải pháp thiết kế **Price Snapshot (Chụp đơn giá bán)** trong phân tích thiết kế hệ thống thông tin. Nếu chi tiết hóa đơn không lưu đơn giá riêng mà chỉ liên kết khóa ngoại sang `mon_nuoc`, thì khi chủ quán tăng giá Cà phê sữa từ 29.000 đ lên 35.000 đ vào tuần sau, tất cả các hóa đơn lịch sử bán ra tuần trước sẽ bị tính toán lại theo giá mới, gây sai lệch báo cáo tài chính và sổ sách kế toán. Lưu `don_gia_ban` tại thời điểm bán đảm bảo tính bất biến của chứng từ lịch sử."*

### Câu hỏi 3 (Về nghiệp vụ & Phân quyền):
> **Hỏi:** *"Tại sao chức năng Chiết khấu bắt buộc phải chặn tài khoản STAFF mà chỉ cho MANAGER nhập?"*  
> **Trả lời (Quý):** *"Thưa Cô, đây là quy tắc kiểm soát nội bộ cốt lõi của ngành bán lẻ F&B nhằm chống thất thoát dòng tiền mặt. Nếu nhân viên thu ngân (Staff) có quyền tự ý nhập chiết khấu, nhân viên có thể thu đủ 100% tiền mặt từ khách nhưng nhập chiết khấu 20% trên máy để chiếm đoạt phần tiền chênh lệch vào túi riêng. Chặn Staff và chỉ cho Quản lý có thẩm quyền phê duyệt chiết khấu giúp bảo vệ tài chính minh bạch cho chủ quán."*

---
*Kịch bản được biên soạn ăn khớp 100% với file slide trình chiếu `slide.html` ngày 18/11/2026.*
