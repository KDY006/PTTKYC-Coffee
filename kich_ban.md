# KỊCH BẢN THUYẾT TRÌNH BẢO VỆ ĐỒ ÁN & KỊCH BẢN KIỂM THỬ THỰC HÀNH
## ĐỀ TÀI: HỆ THỐNG QUẢN LÝ QUÁN CÀ PHÊ – SANGRIMAR CAFE & LOUNGE
*(Presentation & Live Verification Script - Version 2.0)*

---

> **HỌC PHẦN:** Phân tích và Thiết kế Yêu cầu (Mã môn: `502050`) – Trường Đại học Tôn Đức Thắng  
> **GIẢNG VIÊN HƯỚNG DẪN:** TS. Hồ Thị Linh  
> **THƯƠNG HIỆU ÁP DỤNG:** **Sangrimar Cafe & Lounge**  
> **NHÓM BÁO CÁO:**  
> - **Nguyễn Văn Duy (Nhóm trưởng – 34%):** Mở đầu, Bối cảnh F&B, Kiến trúc Phân tầng Spring Boot, Phân hệ Kho & Định mức BOM 12 món, Luồng trừ kho chống Deadlock `@Transactional`, Báo cáo KPI tài chính & Điều phối Q&A.  
> - **Trần Văn Quý (Thành viên – 33%):** Kỹ nghệ yêu cầu, MoSCoW, Sơ đồ Use Case, Chu trình Phục vụ Tại bàn (SOP), Sơ đồ bàn 3 màu viền, Thực đơn Soft Reservation & **[LIVE DEMO 1: BÁN HÀNG POS & BỘ 3 PHIẾU IN]**.  
> - **Lê Tấn Đạt (Thành viên – 33%):** Yêu cầu phi chức năng (NFR), Thiết kế CSDL 11 bảng chuẩn 3NF, Phân hệ Nhân sự, 7 Quy tắc Chấm công thời gian thực & **[LIVE DEMO 2: CHẤM CÔNG & PHÂN CA TUẦN]**.  
> **TỔNG THỜI LƯỢNG TRÌNH BÀY & DEMO:** $12\text{--}15\text{ phút}$ (Thuyết trình: ~8 phút; Live Demo thực nghiệm: ~5 phút; Q&A Phản biện: 5–8 phút).

---

## I. MA TRẬN PHÂN BỔ THỜI GIAN & PHÂN VAI TRÌNH BÀY CHI TIẾT

| Mốc Thời Gian | Nội Dung Trình Bày & Kiểm Thử Trực Tiếp | Người Phụ Trách | Slide / Màn Hình Tương Ứng |
| :---: | :--- | :---: | :---: |
| **00:00 – 02:00** | Mở đầu, Giới thiệu Thương hiệu Sangrimar Cafe & Lounge, 4 Điểm nghẽn (*As-Is*), Mục tiêu (*To-Be*) & Kiến trúc 3 tầng | **Duy** | Slide 1, 2, 3, 4 |
| **02:00 – 03:30** | Yêu cầu chức năng, Bảng MoSCoW, Biểu đồ Use Case tổng quát & Phân quyền RBAC 3 vai trò | **Quý** | Slide 5, 6 |
| **03:30 – 05:00** | Phân hệ Bán hàng POS, Sơ đồ bàn 3 màu viền, Menu Soft Reservation chống bán khống & Bộ 3 phiếu in | **Quý** | Slide 7, 8 |
| **05:00 – 07:00** | **[LIVE DEMO 1: BÁN HÀNG POS, IN BAR DELTA, PHIẾU TẠM TÍNH & THANH TOÁN]** | **Quý** | Trình duyệt: `banhang.html` |
| **07:00 – 08:30** | Thiết kế CSDL 11 bảng chuẩn 3NF, Sổ cái `lich_su_kho`, Phân hệ Nhân sự & 7 Quy tắc chấm công | **Đạt** | Slide 9, 10, 11 |
| **08:30 – 10:00** | **[LIVE DEMO 2: CHẤM CÔNG CÁ NHÂN, PHÂN CA TUẦN CẢNH BÁO THIẾU THU NGÂN & ĐIỀU CHỈNH CÔNG]** | **Đạt** | Trình duyệt: `nhansu.html` |
| **10:00 – 11:30** | Phân hệ Kho, Định mức BOM 12 món, Thuật toán trừ kho chống Deadlock & Giá vốn WAC | **Duy** | Slide 12, 13 |
| **11:30 – 13:00** | **[LIVE DEMO 3: TRỪ KHO TỰ ĐỘNG, SỔ NHẬT KÝ LỊCH SỬ KHO, NHẬP KHO WAC & KPI DASHBOARD]** | **Duy** | Trình duyệt: `kho.html` |
| **13:00 – 14:00** | Minh chứng Kiểm thử Tích hợp (19/19 Test Cases Pass 100%), Kết luận & Khởi động Q&A | **Duy** | Slide 14, 15 |
| **Sau 14:00** | Điều phối trả lời câu hỏi phản biện của Giảng viên (Q&A: 5 – 8 phút) | **Cả nhóm** | Slide kết thúc & Source Code |

---

## II. LỜI THOẠI CHI TIẾT THEO TỪNG PHẦN THUYẾT TRÌNH (PRESENTATION SCRIPT)

---

### PHẦN 1: TỔNG QUAN ĐỀ TÀI, BỐI CẢNH & KIẾN TRÚC HỆ THỐNG
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 2 phút (Mốc 00:00 – 02:00)

> 🗣️ **LỜI THOẠI (DUY):**  
> *"Kính thưa Cô TS. Hồ Thị Linh và toàn thể các bạn sinh viên đang có mặt trong buổi báo cáo bảo vệ đồ án hôm nay!*  
> *Em tên là Nguyễn Văn Duy – Trưởng nhóm thực hiện. Nhóm chúng em gồm 3 thành viên: em – Nguyễn Văn Duy, bạn Trần Văn Quý và bạn Lê Tấn Đạt. Hôm nay, nhóm chúng em xin được đại diện bảo vệ đồ án môn học Phân tích và Thiết kế Yêu cầu với đề tài: **'Hệ Thống Quản Lý Quán Cà Phê – Sangrimar Cafe & Lounge'**.*  
> 
> *Thưa Cô, xuất phát từ việc khảo sát thực tế tại mô hình quán cà phê kết hợp Lounge, nhóm nhận diện **4 điểm nghẽn nghiệp vụ chí mạng**:*  
> 1. *Thứ nhất: Nhân viên nhận order theo cảm tính, không nắm được nguyên liệu quầy Bar còn hay hết, dẫn đến tình trạng **bán khống**, khách đợi 15 phút mới xin lỗi đổi món.*  
> 2. *Thứ hai: Nguy cơ **thất thoát doanh thu tại quầy thu ngân**, nhân viên phục vụ có thể cấu kết hủy đơn tiền mặt hoặc tự ý áp chiết khấu giảm giá bỏ túi riêng.*  
> 3. *Thứ ba: **Kho nguyên vật liệu quản lý mù mờ**, không đo lường được giá vốn hàng bán (COGS) và không thể truy vết nguyên nhân hao hụt đổ vỡ.*  
> 4. *Thứ tư: **Bất công trong chấm công và thiếu hụt nhân sự trong ca**, nhân viên đi trễ về sớm, quên bấm thẻ gây tranh cãi bảng lương cuối tháng.*  
> 
> *Để giải quyết triệt để 4 bài toán trên, nhóm đã xây dựng giải pháp phần mềm hoàn chỉnh theo mô hình **All-in-One Modular Monolith (Kiến trúc phân tầng 3-Tier Layered Architecture)**:*  
> * **Tầng Client:** Giao diện Web SPA tông sáng hiện đại, tối ưu in ấn (*Print-friendly*), không phụ thuộc framework cồng kềnh, tải trang dưới $50\text{ms}$.  
> * **Tầng Application:** Máy chủ Spring Boot 3.2.5 (cổng 8080) với 9 REST Controllers, giao dịch nguyên tử `@Transactional` chống Deadlock, lập lịch chốt ca tự động `@Scheduled` và tiêm `java.time.Clock` kiểm soát mốc giờ.  
> * **Tầng Database:** MySQL 8.0 chuẩn 3NF gồm 11 bảng quan hệ toàn vẹn ACID.*  
> 
> *Sau đây, em xin kính mời bạn **Trần Văn Quý** trình bày về phân hệ Bán hàng POS và trực tiếp Demo quy trình phục vụ tại bàn."*

---

### PHẦN 2: PHÂN HỆ BÁN HÀNG POS & QUY TRÌNH PHỤC VỤ TẠI BÀN
* **Người trình bày:** Trần Văn Quý
* **Thời lượng:** 1.5 phút (Mốc 02:00 – 03:30)

> 🗣️ **LỜI THOẠI (QUÝ):**  
> *"Em xin chào Cô và các bạn! Em tên là Trần Văn Quý.*  
> *Em xin phép trình bày về Phân hệ Bán hàng POS – trái tim vận hành của Sangrimar Cafe & Lounge:*  
> * Hệ thống thiết lập ma trận phân quyền RBAC 3 cấp: **`PHUC_VU` $\subset$ `THU_NGAN` $\subset$ `QUAN_LY`**. Phục vụ chỉ mở bàn, gọi món và bấm yêu cầu tính tiền; Thu ngân chốt bill, đóng bàn; và duy nhất Quản lý mới có quyền áp dụng chiết khấu giảm giá.  
> * Sơ đồ bàn trực quan hóa **3 trạng thái màu sắc viền chuẩn mực**:  
>   - **Màu Xanh dương (`TRONG`):** Bàn trống sẵn sàng đón khách.  
>   - **Màu Xanh lá (`DANG_DUNG_NUOC`):** Khách đang ngồi dùng nước, hiển thị kèm Icon tiền và Tổng tiền tạm tính thời gian thực.  
>   - **Màu Vàng hổ phách (`CHO_THANH_TOAN`):** Khách đã xin tính tiền, hệ thống tự động khóa quyền thêm/sửa món của phục vụ để tránh sai lệch số liệu đối soát.  
> * Điểm đột phá của thực đơn POS là cơ chế **'Giữ chỗ mềm' (Soft Reservation) và tính 'Số ly có thể làm' (`soLyCoTheLam`)** theo nguyên lý Thắt cổ chai nguyên liệu. Hệ thống che giấu tồn kho gốc, chỉ trả về số ly có thể phục vụ (Xanh $> 5$ ly, Vàng $1 - 5$ ly, Đỏ HẾT MÓN và khóa nút thêm món).  
> * Đồng thời, hệ thống chuẩn hóa **Bộ 3 mẫu phiếu in nghiệp vụ**: Phiếu Pha Chế (Bar Ticket), Phiếu Tạm Tính (Proforma Bill kèm mã VietQR động) và Hóa Đơn Bán Hàng chính thức.*  
> 
> *Và ngay sau đây, em xin phép chuyển sang trình duyệt để **Demo trực tiếp Luồng Bán hàng POS**."*

---

### PHẦN 3: [LIVE DEMO 1] TRÌNH DIỄN BÁN HÀNG POS, IN BAR & THANH TOÁN
* **Người thực hiện Demo:** Trần Văn Quý
* **Thời lượng:** 2 phút (Mốc 03:30 – 05:00)
* **Trang web thao tác:** `http://localhost:8080/index.html` $\rightarrow$ `banhang.html`

> 🗣️ **KỊCH BẢN THAO TÁC DEMO THỰC TẾ (QUÝ):**  
> *(Quý mở trình duyệt Chrome, chia sẻ màn hình giao diện Web)*  
> 
> 1. **Bước 1 (Đăng nhập vai trò Phục vụ):**  
>    *"Thưa Cô, tại trang đăng nhập `index.html`, em sử dụng nút điền nhanh **'Phục vụ (phucvu)'** để đăng nhập 1 chạm. Hệ thống chuyển ngay vào `banhang.html`. Trên thanh Topbar, hai tab 'Kho & KPI' và 'Nhân sự Quản trị' đã tự động ẩn hoàn toàn để bảo vệ phân quyền."*  
> 
> 2. **Bước 2 (Mở bàn & Gọi món theo số ly khả dụng):**  
>    *"Em chọn **Bàn 03** đang trống (viền Xanh dương). Quan sát thực đơn bên dưới, món **Cà phê Muối Huế** hiển thị nhãn xanh 'Còn 8 ly', món **Caffe Latte** 'Còn 5 ly'. Em gọi 1 ly Cà phê Muối, 1 ly Caffe Latte và ghi chú **'Ít đường, sữa nóng'**. Tạm tính là 77.000 đ.  
>    Em bấm **'Mở bàn & Tạo đơn'** $\rightarrow$ Hệ thống sinh Hóa đơn mới, và Bàn 03 lập tức chuyển sang **viền Xanh lá (`DANG_DUNG_NUOC`)**, trên thẻ bàn hiện rõ số tiền 77.000 đ."*  
> 
> 3. **Bước 3 (In lệnh Quầy Bar & Gọi thêm món theo cơ chế Delta):**  
>    *"Em bấm nút **'In Bar'** $\rightarrow$ Phiếu Pha Chế xuất hiện gửi lệnh xuống quầy Bar (chỉ gồm tên bàn, món, số lượng, ghi chú, tuyệt đối không hiện giá).  
>    Bây giờ khách tại Bàn 03 gọi thêm 1 ly **Trà Đào Cam Sả**. Em bấm thêm vào giỏ. Khi em bấm 'In Bar' lần 2 $\rightarrow$ Nhờ cơ chế **In Delta**, phiếu in chỉ hiển thị duy nhất 1 ly Trà Đào mới gọi, không hề in lặp lại 2 ly trước đó!"*  
> 
> 4. **Bước 4 (Yêu cầu thanh toán & Khóa bàn):**  
>    *"Khách báo tính tiền, em bấm **'Yêu cầu thanh toán'** $\rightarrow$ Bàn 03 chuyển sang **viền Vàng hổ phách (`CHO_THANH_TOAN`)**. Lúc này quyền sửa món bị khóa chặt để bảo đảm số liệu."*  
> 
> 5. **Bước 5 (Thu ngân in Phiếu Tạm Tính & Đóng bàn):**  
>    *(Quý đăng xuất, dùng nút Quick-fill đăng nhập tài khoản **thungan / thungan**)*  
>    *"Thu ngân vào hệ thống, thấy Bàn 03 màu vàng:  
>    - Thu ngân bấm **'In Tạm Tính'** $\rightarrow$ Xuất Phiếu Tạm Tính có mã VietQR động để phục vụ mang ra bàn cho khách đối soát.  
>    - Khách trả 150.000 đ tiền mặt. Thu ngân nhập số tiền khách đưa $\rightarrow$ Hệ thống tự động tính tiền thối lại là 35.000 đ.  
>    - Cô có thể thấy ô Chiết khấu bị khóa disabled hoàn toàn, Thu ngân không thể tự ý giảm giá.  
>    - Thu ngân bấm **'Xác nhận thanh toán & Đóng bàn'** $\rightarrow$ Hóa Đơn Bán Hàng nhiệt chính thức xuất hiện, hệ thống tự động trừ kho BOM ngầm, và Bàn 03 lập tức quay về **màu Xanh dương TRỐNG** sẵn sàng đón khách mới!"*  
> 
> *"Phần tiếp theo về Thiết kế CSDL 11 bảng và Chấm công nhân sự, em xin nhường lời cho bạn **Lê Tấn Đạt**."*

---

### PHẦN 4: THIẾT KẾ CSDL 11 BẢNG & 7 QUY TẮC CHẤM CÔNG NHÂN SỰ
* **Người trình bày:** Lê Tấn Đạt
* **Thời lượng:** 1.5 phút (Mốc 05:00 – 06:30)

> 🗣️ **LỜI THOẠI (ĐẠT):**  
> *"Em xin kính chào Cô và các bạn! Em tên là Lê Tấn Đạt.*  
> *Về mặt lưu trữ và cấu trúc dữ liệu, hệ thống được thiết kế gồm **11 bảng thực thể quan hệ chuẩn hóa 3NF**:*  
> * Bảng thứ 11 mới bổ sung là **`lich_su_kho`** – đóng vai trò cuốn Sổ cái điện tử, ghi nhận biến động 3 loại giao dịch: `NHAP_KHO`, `XUAT_BAN_HANG` (liên kết khóa ngoại tới `hoa_don`) và `KIEM_KE_HAO_HUT`.  
> * Các bảng nghiệp vụ được bổ sung đầy đủ trường kiểm soát: `so_luong_da_in_bar` chống in trùng, `phien_ban` khóa lạc quan, `he_so_luong` ca đêm và `gio_cong_quy_doi`.  
> 
> *Điểm nhấn sáng tạo nhất của Phân hệ Nhân sự là **7 Quy tắc Nghiệp vụ Chấm công chuẩn hóa (BR-CC-01 đến BR-CC-07)**:*  
> 1. *Ràng buộc 1:1 Phân ca - Chấm công, chống chấm công hộ.*  
> 2. *Cửa sổ check-in nghiêm ngặt trong khoảng $[\text{start} - 30p, \text{end})$.*  
> 3. *Quy tắc du di 15 phút: trễ $\le 15p$ tính đúng giờ, $> 15p$ tính đi trễ.*  
> 4. *Tính giờ công thực tế, không cộng dồn giờ ảo khi đến sớm, phát hiện về sớm $> 15p$, nhân hệ số lương ca tối $1.2$.*  
> 5. *Tự động chốt ca quá hạn bằng `@Scheduled`: đánh dấu `VANG` hoặc `QUEN_CHECK_OUT`.*  
> 6. *Quản lý điều chỉnh chấm công bắt buộc nhập lý do và lưu vết kiểm toán.*  
> 7. *Quy tắc cân bằng vai trò: Bắt buộc có Thu ngân hoặc Quản lý trong mỗi ca trực.*  
> 
> *Sau đây, em xin phép **Demo trực tiếp Phân hệ Nhân sự & Chấm công**."*

---

### PHẦN 5: [LIVE DEMO 2] TRÌNH DIỄN CHẤM CÔNG & PHÂN CA CÂN BẰNG
* **Người thực hiện Demo:** Lê Tấn Đạt
* **Thời lượng:** 1.5 phút (Mốc 06:30 – 08:00)
* **Trang web thao tác:** `http://localhost:8080/nhansu.html`

> 🗣️ **KỊCH BẢN THAO TÁC DEMO THỰC TẾ (ĐẠT):**  
> *(Đạt mở trình duyệt, đăng nhập tài khoản phục vụ rồi quản lý)*  
> 
> 1. **Bước 1 (Chế độ Cá nhân - Phục vụ tự chấm công):**  
>    *"Thưa Cô, em đăng nhập tài khoản **phucvu / phucvu** và truy cập `nhansu.html`.  
>    - Phục vụ chỉ nhìn thấy **Chế độ Cá nhân**: Xem lịch phân ca của riêng mình và đồng hồ kỹ thuật số chạy thời gian thực.  
>    - Hôm nay em được xếp **Ca Sáng (07:00 - 12:00)**. Em bấm **'Check-in Ca Lam'** $\rightarrow$ Vì hiện tại đã quá 07:15, hệ thống lập tức ghi nhận trạng thái **'DI_TRE'** và tính chính xác số phút trễ.  
>    - Nút bấm tự động đổi thành **'Check-out Ca Lam'** sẵn sàng khi tan ca."*  
> 
> 2. **Bước 2 (Chế độ Quản trị - Cảnh báo Cân bằng Nhân sự):**  
>    *(Đạt đăng xuất, đăng nhập tài khoản **quanly / quanly** vào `nhansu.html`)*  
>    *"Khi Quản lý đăng nhập, giao diện mở khóa thêm **Chế độ Quản trị**:  
>    - Tại khối **Lịch Phân Ca Tuần**, kính mời Cô quan sát ô Ca Tối ngày Thứ Tư: Nhóm em chỉ xếp nhân viên Lê Tấn Đạt (vai trò Phục vụ). Ngay lập tức, hệ thống kích hoạt Quy tắc `BR-CC-07`: ô ca hiển thị **viền cam nét đứt** kèm huy hiệu cảnh báo hổ phách: **`CẢNH BÁO: THIẾU THU NGÂN / QUẢN LÝ`**.  
>    - Bây giờ em phân công thêm bạn Trần Văn Quý (Thu ngân) vào ca đó $\rightarrow$ Cảnh báo cam lập tức biến mất, viền trở lại bình thường, bảo đảm ca trực vận hành an toàn!"*  
> 
> 3. **Bước 3 (Quản lý duyệt điều chỉnh công có lưu vết):**  
>    *"Tại bảng Lịch sử Chấm công, nhân viên bị quên check-out hoặc đi trễ có giải trình, Quản lý bấm nút **'Điều chỉnh'**, sửa lại giờ ra và bắt buộc nhập lý do: 'Nhân viên ở lại dọn quầy, camera xác nhận 12:15'.  
>    Sau khi bấm Lưu, hệ thống tự động tính lại giờ công quy đổi và ghi nhận rõ người sửa, giờ sửa minh bạch $100\%$."*  
> 
> *"Tiếp theo, em xin kính mời Trưởng nhóm **Nguyễn Văn Duy** trình bày về Phân hệ Kho và Live Demo Trừ kho tự động."*

---

### PHẦN 6: PHÂN HỆ QUẢN TRỊ KHO, ĐỊNH MỨC BOM & GIÁ VỐN WAC
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 1.5 phút (Mốc 08:00 – 09:30)

> 🗣️ **LỜI THOẠI (DUY):**  
> *"Kính thưa Cô, em xin tiếp tục với Phân hệ Quản trị Kho – hạt nhân kỹ thuật kiểm soát tài chính của Sangrimar Cafe & Lounge:*  
> * Hệ thống chuẩn hóa **13 nguyên vật liệu thực tế $100\%$** (Hạt Robusta, Arabica, Sữa tươi, Sữa đặc, Trà đen Ceylon, Siro Teisseire, Bột Matcha Uji,...) cấu thành **34 dòng định mức BOM** cho 12 món nước.  
> * **Thuật toán Trừ kho Chống Deadlock:** Toàn bộ bước thanh toán được bọc trong `@Transactional(isolation = READ_COMMITTED)`. Hệ thống sử dụng cấu trúc `TreeMap` để tự động sắp xếp danh sách mã nguyên liệu cần trừ theo thứ tự ID tăng dần trước khi thực thi khóa bi quan `SELECT ... FOR UPDATE`. Kỹ thuật này triệt tiêu $100\%$ tình trạng khóa chết vòng tròn khi nhiều thu ngân bấm thanh toán cùng lúc!  
> * **Phương pháp Giá vốn Bình quân gia quyền (WAC):** Mỗi khi nhập lô hàng mới, đơn giá vốn được tính toán lại tự động theo công thức WAC, đảm bảo báo cáo giá vốn hàng bán (COGS) luôn sát với giá thị trường.  
> * **Sổ Nhật ký Biến động Kho (`lich_su_kho`):** Mọi hành vi nhập kho, xuất bán hay kiểm kê hao hụt đều được lưu vết vĩnh viễn, xóa bỏ hoàn toàn hiện tượng nguyên liệu bị thất thoát bí ẩn.*  
> 
> *Sau đây, em xin phép **Demo trực tiếp Phân hệ Kho & KPI Dashboard**."*

---

### PHẦN 7: [LIVE DEMO 3] TRÌNH DIỄN TRỪ KHO BOM, SỔ NHẬT KÝ & KPI
* **Người thực hiện Demo:** Nguyễn Văn Duy
* **Thời lượng:** 1.5 phút (Mốc 09:30 – 11:00)
* **Trang web thao tác:** `http://localhost:8080/kho.html`

> 🗣️ **KỊCH BẢN THAO TÁC DEMO THỰC TẾ (DUY):**  
> *(Duy mở trang `kho.html` trên tài khoản Quản lý)*  
> 
> 1. **Bước 1 (Minh chứng Trừ kho Tự động từ Demo 1):**  
>    *"Kính thưa Cô, khi nãy bạn Quý đã thanh toán đơn hàng Bàn 03 gồm: 1 Cà phê Muối, 1 Caffe Latte và 1 Trà Đào Cam Sả.  
>    Tại Tab 1 'Tồn kho nguyên liệu', Cô có thể thấy:  
>    - Hạt cà phê Robusta đã tự động trừ đúng $25\text{ g}$ ($15\text{g}$ từ Cà phê muối + $10\text{g}$ từ Latte).  
>    - Sữa tươi tiệt trùng tự động trừ đúng $180\text{ ml}$.  
>    - Siro Đào Teisseire tự động trừ đúng $25\text{ ml}$.  
>    Số tồn kho giảm ngay tức thì mà không cần thủ kho phải nhập tay bất kỳ thao tác nào!"*  
> 
> 2. **Bước 2 (Kiểm tra Sổ Nhật ký Biến động Kho - Tab 3):**  
>    *"Em chuyển sang Tab 3 **'Nhật ký Biến động kho'**:  
>    Ba dòng giao dịch loại **`XUAT_BAN_HANG`** xuất hiện rõ ràng, ghi nhận chính xác thời gian, gắn liền mã Hóa đơn của Bàn 03, tên Thu ngân thực hiện, tồn trước và tồn sau khớp từng con số!"*  
> 
> 3. **Bước 3 (Nhập kho cập nhật Giá vốn WAC & Cảnh báo đỏ):**  
>    *"Tại dòng Bột Matcha Uji, tồn kho hiện tại chỉ còn $80\text{ g}$, nhỏ hơn ngưỡng tối thiểu $100\text{ g}$, nên hệ thống đang bật nhãn đỏ nhấp nháy **`SẮP HẾT HÀNG`**.  
>    Em bấm nút **'Nhập kho'**, sử dụng nút preset nhập nhanh **+500 g** với đơn giá mới.  
>    Sau khi bấm Xác nhận: Tồn kho tăng lên $580\text{ g}$, đơn giá vốn được tính lại theo WAC, nhãn đỏ biến mất và lập tức khôi phục nhãn xanh **`Đủ hàng`**!"*  
> 
> 4. **Bước 4 (Thực hiện Kiểm kê Cuối ngày & Xử lý Hao hụt):**  
>    *"Cuối ca, Quản lý cân đo thực tế thấy Siro Đào bị đổ vỡ mất $50\text{ ml}$. Em bấm **'Kiểm kê'**, nhập số thực tế và nhập lý do: 'Vỡ nắp chai khi pha chế'. Hệ thống ghi nhận dòng **`KIEM_KE_HAO_HUT`**, tự động tính giá trị tổn thất tài chính đưa vào báo cáo."*  
> 
> 5. **Bước 5 (Executive KPI Dashboard đa chu kỳ):**  
>    *"Phía trên cùng là 6 thẻ KPI Dashboard: Doanh thu thuần, Giá vốn COGS, Lợi nhuận gộp đạt $79,0\%$, đối soát Tiền mặt vs Chuyển khoản QR và biểu đồ Top 5 món bán chạy nhất với Cà phê Muối dẫn đầu. Quản lý có thể lọc linh hoạt theo Hôm nay, 7 ngày, Tháng này hoặc Toàn thời gian chỉ với một cú nhấp chuột."*

---

### PHẦN 8: MINH CHỨNG KIỂM THỬ TÍCH HỢP & KẾT LUẬN
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 1 phút (Mốc 11:00 – 12:00)

> 🗣️ **LỜI THOẠI (DUY):**  
> *"Kính thưa Cô, để chứng minh độ tin cậy tuyệt đối của hệ thống trước khi nghiệm thu:*  
> * Toàn bộ mã nguồn đã vượt qua xuất sắc **19/19 ca kiểm thử tích hợp tự động Backend (100% BUILD SUCCESS)** chạy trên môi trường H2 In-Memory độc lập:  
>   - 5 ca kiểm thử POS, Soft Reservation và phân quyền RBAC (`DonHangIntegrationTest.java`).  
>   - 6 ca kiểm thử Nhập kho WAC, trừ kho BOM và chống Deadlock (`KhoKpiIntegrationTest.java`).  
>   - 8 ca kiểm thử Chấm công, du di 15 phút, hệ số ca đêm và chốt ca tự động (`NhanSuChamCongIntegrationTest.java`).  
> * Hệ thống đã hoàn thiện trọn vẹn cả về mặt học thuật Kỹ nghệ yêu cầu lẫn giá trị ứng dụng thực tiễn cho ngành F&B.*  
> 
> *Bài thuyết trình và báo cáo kiểm thử của nhóm Sangrimar Cafe & Lounge đến đây xin được phép khép lại. Chúng em xin chân thành cảm ơn Cô đã lắng nghe và kính mời Cô đặt câu hỏi phản biện cho nhóm ạ!"*

---

## III. BỘ CÂU HỎI PHẢN BIỆN (Q&A) CHUYÊN SÂU & GỢI Ý TRẢ LỜI CỦA GIẢNG VIÊN

---

### Câu hỏi 1 (Về Concurrency, Trừ kho & Chống Deadlock):
> **Giảng viên hỏi:** *"Tại sao nhóm lại dùng Khóa bi quan (`SELECT ... FOR UPDATE`) kết hợp `TreeMap` khi trừ kho mà không dùng Khóa lạc quan (`@Version`)?"*  
> **Sinh viên trả lời (Duy):**  
> *"Thưa Cô, Khóa lạc quan (`@Version`) hoạt động theo nguyên tắc ném lỗi và Rollback khi phát hiện phiên bản bị thay đổi. Cơ chế này rất tốt cho Hóa đơn (nơi xác suất 2 người cùng sửa một bill là thấp). Tuy nhiên, đối với Tồn kho nguyên liệu trong quán cà phê giờ cao điểm, hàng chục đơn hàng đều có chung thành phần như Sữa tươi hay Cà phê Robusta. Nếu dùng Khóa lạc quan, cứ một đơn thanh toán thành công thì 9 đơn còn lại sẽ bị văng lỗi bắt thu ngân bấm lại từ đầu, gây tê liệt quầy thu ngân.  
> Vì vậy, nhóm chọn **Khóa bi quan (`PESSIMISTIC_WRITE`)**. Đặc biệt, nhóm áp dụng cấu trúc `TreeMap` để sắp xếp các mã nguyên liệu theo thứ tự ID tăng dần $(1 \rightarrow 2 \rightarrow 3)$ trước khi chiếm giữ khóa. Nhờ vậy, mọi giao dịch thanh toán đều xếp hàng lấy khóa theo cùng một trật tự, triệt tiêu $100\%$ điều kiện Circular Wait, bảo đảm hệ thống vừa không bao giờ bị Deadlock, vừa không bao giờ bán khống âm kho!"*

---

### Câu hỏi 2 (Về Nghiệp vụ POS & Cơ chế Soft Reservation):
> **Giảng viên hỏi:** *"Cơ chế 'Soft Reservation' (Giữ chỗ mềm) và 'Số ly có thể làm' giải quyết bài toán nghiệp vụ gì, và tại sao phải giấu số tồn kho thô với nhân viên phục vụ?"*  
> **Sinh viên trả lời (Quý):**  
> *"Thưa Cô, trong thực tế, nếu chỉ hiển thị món mà không tính tồn kho, nhân viên phục vụ sẽ nhận order bừa bãi. Đến khi quầy Bar pha chế mới thấy hết sữa, phục vụ phải ra xin lỗi khách đổi món, phá hỏng trải nghiệm khách hàng.  
> Cơ chế **Soft Reservation** của nhóm tự động quét tất cả các bàn đang mở đơn, tạm giữ lượng nguyên liệu đó và tính toán ra **Số ly có thể làm (`soLyCoTheLam`)** theo nguyên liệu thắt cổ chai ít nhất.  
> Đồng thời, việc **giấu số tồn kho thô (Information Hiding)** là nguyên tắc bảo mật thông tin nội bộ: Nhân viên phục vụ chỉ cần biết còn phục vụ được bao nhiêu ly (Xanh: $>5$ ly, Vàng: $1-5$ ly, Đỏ: Hết món), không cần và không được phép biết trong kho còn bao nhiêu ký cà phê hay bao nhiêu hộp sữa, tránh lộ bí quyết định lượng và rò rỉ dữ liệu tài sản của quán."*

---

### Câu hỏi 3 (Về Tính Bất biến của Hóa đơn & Price Snapshot):
> **Giảng viên hỏi:** *"Tại sao trong bảng `chi_tiet_hoa_don` lại phải lưu trường `don_gia_ban` trong khi bảng `mon_nuoc` đã có `don_gia` rồi?"*  
> **Sinh viên trả lời (Đạt):**  
> *"Thưa Cô, đây là nguyên lý thiết kế **Price Snapshot (Chụp ảnh đơn giá)** bắt buộc trong kỹ nghệ phần mềm tài chính. Giá bán của món nước trong bảng `mon_nuoc` có thể thay đổi theo thời gian (ví dụ: tuần sau quán tăng giá Cà phê Muối từ 35.000 đ lên 40.000 đ). Nếu chi tiết hóa đơn không lưu `don_gia_ban` tại thời điểm mua mà chỉ nối khóa ngoại sang `mon_nuoc`, thì khi chạy báo cáo doanh thu tháng trước, toàn bộ các hóa đơn cũ sẽ bị tính lại theo giá mới 40.000 đ, làm sai lệch toàn bộ sổ sách kế toán. Lưu `don_gia_ban` bảo đảm tính bất biến vĩnh viễn của chứng từ lịch sử."*

---

### Câu hỏi 4 (Về An ninh Phân quyền & Kiểm soát Gian lận Nội bộ):
> **Giảng viên hỏi:** *"Tại sao chức năng Chiết khấu bắt buộc phải chặn tài khoản Thu ngân mà chỉ cho Quản lý thao tác?"*  
> **Sinh viên trả lời (Quý):**  
> *"Thưa Cô, đây là quy tắc kiểm soát nội bộ trọng yếu của ngành F&B nhằm triệt tiêu gian lận dòng tiền mặt. Nếu Thu ngân có quyền tự nhập chiết khấu, họ có thể thu đủ 100.000 đ tiền mặt từ khách, nhưng trên phần mềm lại nhập chiết khấu $20\%$, nộp lại két 80.000 đ và bỏ túi riêng 20.000 đ chênh lệch. Bằng cách khóa cứng ô chiết khấu ở cả tầng giao diện lẫn tầng REST API Backend đối với Thu ngân, quán bảo vệ $100\%$ doanh thu và buộc mọi ưu đãi giảm giá đều phải qua sự phê duyệt của Quản lý."*

---

### Câu hỏi 5 (Về Trừu tượng hóa Thời gian & Chấm công Tự động):
> **Giảng viên hỏi:** *"Tại sao nhóm lại tạo `@Bean public Clock systemClock()` thay vì dùng trực tiếp `LocalDateTime.now()` trong mã nguồn?"*  
> **Sinh viên trả lời (Đạt):**  
> *"Thưa Cô, việc gọi trực tiếp `LocalDateTime.now()` phụ thuộc cứng vào đồng hồ phần cứng của máy chủ, khiến cho việc viết Unit Test và Integration Test kiểm thử tự động trở nên bất khả thi (ví dụ: không thể nào đợi đúng 15 phút hay đợi đến 22:30 đêm để xem hệ thống có chốt ca tự động hay không).  
> Bằng cách tiêm Spring Bean `java.time.Clock`, trong mã nguồn chính vẫn lấy giờ thực tế, nhưng trong các lớp kiểm thử tích hợp (`NhanSuChamCongIntegrationTest`), nhóm có thể dùng `Clock.fixed()` hoặc dịch chuyển mốc thời gian tùy ý sang mốc 07:16 để kiểm thử đi trễ, dịch sang 22:31 để kiểm thử tiến trình chốt ca quá hạn `@Scheduled` chạy tự động, đạt độ bao phủ kiểm thử $100\%$!"*

---

### Câu hỏi 6 (Về Sổ Nhật ký Kho & Kiểm kê Hao hụt):
> **Giảng viên hỏi:** *"Tại sao nhóm phải tạo thêm bảng thứ 11 `lich_su_kho` mà không cộng trừ trực tiếp vào cột `so_luong_ton` của bảng `nguyen_lieu` là đủ rồi?"*  
> **Sinh viên trả lời (Duy):**  
> *"Thưa Cô, nếu chỉ cập nhật đè lên cột `so_luong_ton`, chủ quán chỉ biết kho còn bao nhiêu, nhưng hoàn toàn không thể trả lời được câu hỏi: Tại sao tồn kho lại bị giảm? Giảm do bán đơn hàng nào? Giảm do đổ vỡ hay do ai nhập thêm vào?  
> Bảng thứ 11 `lich_su_kho` chính là **Sổ cái kiểm toán (Audit Trail)**. Mỗi biến động đều lưu rõ: Loại giao dịch (`NHAP_KHO`, `XUAT_BAN_HANG`, `KIEM_KE_HAO_HUT`), số lượng thay đổi, tồn trước, tồn sau, nhân viên thực hiện và gắn liền với mã hóa đơn bán ra. Nhờ vậy, cuối ngày nếu kiểm kê thấy hụt $100\text{g}$ cà phê, Quản lý có thể đối chiếu ngay lập tức xem do Bar làm hỏng hay do thất thoát ngoài luồng."*

---
*Kịch bản thuyết trình và kiểm thử thực hành hoàn tất ngày 20 tháng 11 năm 2026 – Nhóm Sinh viên Đề tài Sangrimar Cafe & Lounge.*
