# KỊCH BẢN THUYẾT TRÌNH BÁO CÁO BẢO VỆ ĐỒ ÁN
## ĐỀ TÀI: HỆ THỐNG QUẢN LÝ QUÁN CÀ PHÊ – SANGRIMAR CAFE & LOUNGE
### PHÂN TÍCH CHUYÊN SÂU CÁC LUỒNG NGHIỆP VỤ & KỊCH BẢN KIỂM THỬ THỰC HÀNH LIVE DEMO
*(Tài liệu chuẩn phục vụ buổi bảo vệ đồ án kết thúc học phần – Version 2.0)*

---

> **TRƯỜNG ĐẠI HỌC TÔN ĐỨC THẮNG** 
> **KHOA CÔNG NGHỆ THÔNG TIN – BỘ MÔN KỸ THUẬT PHẦN MỀM** 
> **HỌC PHẦN:** Phân tích và Thiết kế Yêu cầu (Mã môn: `502050`) 
> **GIẢNG VIÊN HƯỚNG DẪN:** **TS. Hồ Thị Linh** 
> **THƯƠNG HIỆU DOANH NGHIỆP:** **Sangrimar Cafe & Lounge** (Mô hình F&B tích hợp Phục vụ tại bàn, Quầy Bar & Lounge) 
> **NHÓM SINH VIÊN THỰC HIỆN:** 
> 1. **Nguyễn Văn Duy** (MSSV: `52400010` – Trưởng nhóm – Đóng góp: **34%**) 
> 2. **Trần Văn Quý** (MSSV: `52400007` – Thành viên – Đóng góp: **33%**) 
> 3. **Lê Tấn Đạt** (MSSV: `52400105` – Thành viên – Đóng góp: **33%**) 
> **TỔNG THỜI LƯỢNG BÁO CÁO:** $12\text{ -- }15\text{ phút}$ (Thuyết trình lý thuyết & nghiệp vụ: ~8 phút | 3 Phân đoạn Live Demo: ~5 phút | Q&A Phản biện: 5 – 8 phút). 
> **FILE SLIDE ĐI KÈM:** [slide.html](file:///d:/3.1.%20PTTKYC/CK/slide.html) (Slide phong cách Notebook tương tác, phím `N` bật lời thoại, phím `O` mở mục lục, phím `F` toàn màn hình).

---

## MỤC LỤC TÀI LIỆU

- [PHẦN I: TỔNG QUAN KỊCH BẢN, MA TRẬN PHÂN BỔ THỜI GIAN & NGUYÊN TẮC PHỐI HỢP](#phần-i-tổng-quan-kịch-bản-ma-trận-phân-bổ-thời-gian--nguyên-tắc-phối-hợp)
 - [1.1. Ma trận phân vai & Phân bổ timeline chi tiết (12 - 15 phút)](#11-ma-trận-phân-vai--phân-bổ-timeline-chi-tiết-12---15-phút)
 - [1.2. Nguyên tắc chuyển giao quyền nói (Handover Protocol)](#12-nguyên-tắc-chuyển-giao-quyền-nói-handover-protocol)
 - [1.3. Cơ chế phối hợp màn hình: Slide Deck song song Live Demo Web](#13-cơ-chế-phối-hợp-màn-hình-slide-deck-song-song-live-demo-web)
- [PHẦN II: KỊCH BẢN THUYẾT TRÌNH CHI TIẾT 16 SLIDE (TẬP TRUNG LUỒNG NGHIỆP VỤ)](#phần-ii-kịch-bản-thuyết-trình-chi-tiết-16-slide-tập-trung-luồng-nghiệp-vụ)
 - [Slide 01: Tiêu Đề Đề Tài, Giới Thiệu Nhóm & Phân Bổ Thời Gian](#slide-01-tiêu-đề-đề-tài-giới-thiệu-nhóm--phân-bổ-thời-gian)
 - [Slide 02: Bối Cảnh Thực Tế & 4 Điểm Nghẽn Vận Hành Quán F&B](#slide-02-bối-cảnh-thực-tế--4-điểm-nghẽn-vận-hành-quán-fb)
 - [Slide 03: Mục Tiêu To-Be & Ma Trận Ranh Giới (In-Scope vs Out-of-Scope)](#slide-03-mục-tiêu-to-be--ma-trận-ranh-giới-in-scope-vs-out-of-scope)
 - [Slide 04: Kiến Trúc 3 Tầng Modular Monolith & An Ninh Phân Quyền RBAC](#slide-04-kiến-trúc-3-tầng-modular-monolith--an-ninh-phân-quyền-rbac)
 - [Slide 05: Danh Mục 15 Yêu Cầu Chức Năng (FRs) & Bảng Phân Cấp MoSCoW](#slide-05-danh-mục-15-yêu-cầu-chức-năng-frs--bảng-phân-cấp-moscow)
 - [Slide 06: Biểu Đồ Use Case Tổng Quát (30 Ca Sử Dụng) & 3 Vai Trò](#slide-06-biểu-đồ-use-case-tổng-quát-30-ca-sử-dụng--3-vai-trò)
 - [Slide 07: Nghiệp Vụ POS: Sơ Đồ Bàn 3 Màu Viền & Cơ Chế Soft Reservation](#slide-07-nghiệp-vụ-pos-sơ-đồ-bàn-3-màu-viền--cơ-chế-soft-reservation)
 - [Slide 08: Chu Trình SOP 9 Bước, Cơ Chế In Bar Delta & Bộ 3 Phiếu In](#slide-08-chu-trình-sop-9-bước-cơ-chế-in-bar-delta--bộ-3-phiếu-in)
 - [Slide 09: [LIVE DEMO 1] Bán Hàng POS: Mở Bàn, In Bar Delta & Đóng Bàn](#slide-09-live-demo-1-bán-hàng-pos-mở-bàn-in-bar-delta--đóng-bàn)
 - [Slide 10: Yêu Cầu Phi Chức Năng (NFR) & Thiết Kế CSDL 11 Bảng Chuẩn 3NF](#slide-10-yêu-cầu-phi-chức-năng-nfr--thiết-kế-csdl-11-bảng-chuẩn-3nf)
 - [Slide 11: Nghiệp Vụ Nhân Sự: Phân Ca Cân Bằng & 7 Quy Tắc Chấm Công BR-CC](#slide-11-nghiệp-vụ-nhân-sự-phân-ca-cân-bằng--7-quy-tắc-chấm-công-br-cc)
 - [Slide 12: [LIVE DEMO 2] Chấm Công Cá Nhân & Phân Ca Cân Bằng Vai Trò](#slide-12-live-demo-2-chấm-công-cá-nhân--phân-ca-cân-bằng-vai-trò)
 - [Slide 13: Nghiệp Vụ Kho: Định Mức BOM 12 Món & Công Thức Giá Vốn WAC](#slide-13-nghiệp-vụ-kho-định-mức-bom-12-món--công-thức-giá-vốn-wac)
 - [Slide 14: Nghiệp Vụ Kho: Thuật Toán Trừ Kho Chống Deadlock & Kiểm Kê Hao Hụt](#slide-14-nghiệp-vụ-kho-thuật-toán-trừ-kho-chống-deadlock--kiểm-kê-hao-hụt)
 - [Slide 15: [LIVE DEMO 3] Trừ Kho BOM, Sổ Nhật Ký, Nhập Kho WAC & KPI Dashboard](#slide-15-live-demo-3-trừ-kho-bom-sổ-nhật-ký-nhập-kho-wac--kpi-dashboard)
 - [Slide 16: Tổng Kết Kiểm Thử (19/19 Tests Pass), Đánh Giá Thực Tiễn & Q&A](#slide-16-tổng-kết-kiểm-thử-1919-tests-pass-đánh-giá-thực-tiễn--qa)
- [PHẦN III: HƯỚNG DẪN CHI TIẾT 3 PHÂN ĐOẠN LIVE DEMO THỰC CHIẾN](#phần-iii-hướng-dẫn-chi-tiết-3-phân-đoạn-live-demo-thực-chiến)
 - [3.1. Live Demo 1 (Trần Văn Quý): POS, Soft Reservation, In Bar Delta & Đóng Bàn](#31-live-demo-1-trần-văn-quý-pos-soft-reservation-in-bar-delta--đóng-bàn)
 - [3.2. Live Demo 2 (Lê Tấn Đạt): Chấm Công Cá Nhân, Cảnh Báo Thiếu Thu Ngân & Duyệt Công](#32-live-demo-2-lê-tấn-đạt-chấm-công-cá-nhân-cảnh-báo-thiếu-thu-ngân--duyệt-công)
 - [3.3. Live Demo 3 (Nguyễn Văn Duy): Khấu Trừ Kho BOM Tự Động, Sổ Nhật Ký, WAC & KPI](#33-live-demo-3-nguyễn-văn-duy-khấu-trừ-kho-bom-tự-động-sổ-nhật-ký-wac--kpi)
- [PHẦN IV: BỘ CÂU HỎI & TRẢ LỜI PHẢN BIỆN CHUYÊN SÂU (DEFENSE Q&A)](#phần-iv-bộ-câu-hỏi--trả-lời-phản-biện-chuyên-sâu-defense-qa)
 - [15 Bài toán nghiệp vụ & giải pháp kỹ thuật cốt lõi](#15-bài-toán-nghiệp-vụ--giải-pháp-kỹ-thuật-cốt-lõi)
- [PHẦN V: CHECKLIST CHUẨN BỊ TRƯỚC GIỜ BÁO CÁO & PHONG THÁI PHẢN BIỆN](#phần-v-checklist-chuẩn-bị-trước-giờ-báo-cáo--phong-thái-phản-biện)

---
# PHẦN I: TỔNG QUAN KỊCH BẢN, MA TRẬN PHÂN BỔ THỜI GIAN & NGUYÊN TẮC PHỐI HỢP

### 1.1. Ma trận phân vai & Phân bổ timeline chi tiết (12 - 15 phút)

Kịch bản được thiết kế theo cấu trúc đan xen nhịp nhàng giữa **Lý thuyết phân tích nghiệp vụ** và **Thực chứng bằng Live Demo**, giúp hội đồng theo dõi trực quan và kiểm chứng ngay lập tức mọi luận điểm kỹ thuật:

| Timeline (Phút) | Thời lượng | Người trình bày | Nội dung trọng tâm | Slide / Màn hình Web tương ứng |
| :--- | :---: | :---: | :--- | :--- |
| **00:00 – 02:00** | $2.0\text{ phút}$ | **Nguyễn Văn Duy** | Mở đầu, Bối cảnh F&B, 4 Điểm nghẽn vận hành, Mục tiêu To-Be, Ranh giới In/Out Scope & Kiến trúc 3 tầng Modular Monolith. | Slide 01, 02, 03, 04 |
| **02:00 – 03:30** | $1.5\text{ phút}$ | **Trần Văn Quý** | Danh mục 15 FRs, Ma trận MoSCoW, Biểu đồ Use Case tổng quát (30 UCs) & Phân quyền RBAC 3 cấp. | Slide 05, 06 |
| **03:30 – 04:30** | $1.0\text{ phút}$ | **Trần Văn Quý** | Nghiệp vụ POS: Sơ đồ bàn 3 màu viền, Soft Reservation thắt cổ chai, SOP 9 bước, In Bar Delta & Bộ 3 phiếu in. | Slide 07, 08 |
| **04:30 – 06:30** | $2.0\text{ phút}$ | **Trần Văn Quý** | **[LIVE DEMO 1]** Mở bàn 03, Chọn món Soft Reservation, In Bar Delta, Khóa bàn `CHO_THANH_TOAN`, In tạm tính VietQR & Thu tiền đóng bàn. | Slide 09 $\r\rightarrow$ Trình duyệt: `banhang.html` |
| **06:30 – 07:30** | $1.0\text{ phút}$ | **Lê Tấn Đạt** | Yêu cầu phi chức năng (NFR), Thiết kế CSDL 11 bảng chuẩn 3NF, Sổ nhật ký `lich_su_kho`, Price Snapshot & Khóa lạc quan `@Version`. | Slide 10 |
| **07:30 – 08:30** | $1.0\text{ phút}$ | **Lê Tấn Đạt** | Phân hệ Nhân sự: Lưới phân ca cảnh báo thiếu Thu ngân (`BR-CC-07`) & 7 Quy tắc Chấm công chuẩn mực (Window 30p, du di 15p, scheduler 03:00). | Slide 11 |
| **08:30 – 10:00** | $1.5\text{ phút}$ | **Lê Tấn Đạt** | **[LIVE DEMO 2]** Chế độ cá nhân chấm công trễ, Lưới phân ca cảnh báo viền cam nét đứt & Quản lý duyệt điều chỉnh công có lưu vết kiểm toán. | Slide 12 $\r\rightarrow$ Trình duyệt: `nhansu.html` |
| **10:00 – 11:00** | $1.0\text{ phút}$ | **Nguyễn Văn Duy** | Phân hệ Kho: 13 nguyên liệu thực tế, Định mức BOM 12 món, Công thức giá vốn WAC & Ngưỡng an toàn cảnh báo tồn đỏ. | Slide 13 |
| **11:00 – 12:00** | $1.0\text{ phút}$ | **Nguyễn Văn Duy** | Thuật toán trừ kho chống Deadlock bằng `TreeMap` tăng dần qua `@Transactional` & Quy trình kiểm kê hao hụt tài chính. | Slide 14 |
| **12:00 – 13:30** | $1.5\text{ phút}$ | **Nguyễn Văn Duy** | **[LIVE DEMO 3]** Minh chứng trừ kho tự động sau Demo 1, Sổ cái `lich_su_kho`, Nhập kho WAC xóa báo đỏ, Kiểm kê hao hụt & Executive KPI Dashboard. | Slide 15 $\r\rightarrow$ Trình duyệt: `kho.html` |
| **13:30 – 14:30** | $1.0\text{ phút}$ | **Nguyễn Văn Duy** | Minh chứng kiểm thử tích hợp tự động (19/19 Tests Pass 100%), Chiến lược chuyển giao Direct Cutover & Khởi động Q&A. | Slide 16 |
| **14:30 – 20:00** | $5.0\text{ -- }8.0\text{ phút}$ | **Cả nhóm** | Trả lời câu hỏi phản biện của Giảng viên hướng dẫn & Hội đồng đánh giá. | Điều phối bởi Trưởng nhóm Duy |

---

### 1.2. Nguyên tắc chuyển giao quyền nói (Handover Protocol)

Để bảo đảm tính liền mạch, phong thái chuyên nghiệp và không bị khoảng lặng (dead air), nhóm tuân thủ nghiêm ngặt 4 quy tắc chuyển giao:

1. **Câu kết thúc rõ ràng:** Người đang nói kết thúc phần của mình bằng câu chốt có danh xưng thành viên tiếp theo (Ví dụ: *"Sau đây, em xin kính mời bạn Trần Văn Quý trình bày về Phân hệ Bán hàng POS và thực hiện Live Demo 1."*).
2. **Chào tiếp nhận ngay tức thì:** Người nhận quyền nói tiếp lời trong vòng tối đa 2 giây: *"Em xin cảm ơn bạn Duy. Em xin kính chào Cô và các bạn..."*.
3. **Thao tác chuyển màn hình mượt mà:** Khi chuyển từ Slide sang Live Demo, người phụ trách kỹ thuật (Duy hoặc thành viên không nói) bấm `Alt + Tab` chuyển sang tab trình duyệt tương ứng đã mở sẵn, kiểm tra kích thước zoom $100\%$, sẵn sàng cho thao tác click.
4. **Hỗ trợ nhau trong Live Demo:** Trong khi một bạn thao tác chuột trên trình duyệt, các bạn còn lại quan sát để nhắc bài hoặc hỗ trợ giải thích nếu giảng viên ngắt lời đặt câu hỏi ngay trong lúc demo.

---

### 1.3. Cơ chế phối hợp màn hình: Slide Deck song song Live Demo Web

- Màn hình chính máy chiếu:
 - Slide thuyết trình: [slide.html](file:///d:/3.1.%20PTTKYC/CK/slide.html) hiển thị toàn màn hình (bấm phím `F`).
 - Trình duyệt Web Chrome đã mở sẵn 3 tab độc lập:
 - Tab 1: `http://localhost:8080/index.html` $\rightarrow$ Đăng nhập & `banhang.html`.
 - Tab 2: `http://localhost:8080/nhansu.html`.
 - Tab 3: `http://localhost:8080/kho.html`.
- Các nút preset "Quick Fill" tiện ích trên màn hình đăng nhập (`phucvu`, `thungan`, `quanly`) được chuẩn bị sẵn để chuyển đổi tài khoản chỉ với 1 cú click chuột, không mất thời gian gõ phím.

---
# PHẦN II: KỊCH BẢN THUYẾT TRÌNH CHI TIẾT 16 SLIDE (TẬP TRUNG LUỒNG NGHIỆP VỤ)

---

### Slide 01: Tiêu Đề Đề Tài, Giới Thiệu Nhóm & Phân Bổ Thời Gian
* **Người trình bày:** Nguyễn Văn Duy (Nhóm trưởng)
* **Thời lượng:** 30 giây (Mốc 00:00 – 00:30)
* **Minh chứng trực quan:** Sơ đồ kiến trúc tổng thể [diagram/KienTrucTongThe.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/KienTrucTongThe.svg).
* **Mục tiêu nghiệp vụ:** Xác lập thương hiệu Sangrimar Cafe & Lounge, khẳng định tính toàn diện của giải pháp công nghệ đáp ứng chuẩn mực Khoa CNTT – ĐH Tôn Đức Thắng.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - DUY):** 
> *"Kính thưa Cô TS. Hồ Thị Linh và toàn thể các bạn sinh viên đang có mặt trong buổi báo cáo bảo vệ đồ án hôm nay! 
> Em tên là Nguyễn Văn Duy – Trưởng nhóm đề tài. Nhóm chúng em gồm 3 thành viên: em – Nguyễn Văn Duy, bạn Trần Văn Quý và bạn Lê Tấn Đạt. Hôm nay, nhóm chúng em rất vinh dự được đại diện báo cáo đồ án môn học Phân tích và Thiết kế Yêu cầu với đề tài: **'Hệ Thống Quản Lý Quán Cà Phê – Sangrimar Cafe & Lounge'**. 
> Đồ án này tập trung giải quyết trọn vẹn và tự động hóa các luồng nghiệp vụ thực tế của ngành F&B từ Bán hàng POS, Quản trị Kho định mức BOM đến Chấm công nhân sự thời gian thực. 
> Buổi báo cáo của nhóm dự kiến diễn ra trong 12 đến 15 phút, kết hợp chặt chẽ giữa 8 phút thuyết trình lý thuyết nghiệp vụ và 5 phút Live Demo thực nghiệm trên hệ thống phần mềm đang chạy thực tế. 
> Sau đây, em xin phép được bắt đầu phần trình bày của nhóm."*

* **Chỉ dẫn thao tác:** Duy bấm phím Mũi tên Phải chuyển sang Slide 02.

---

### Slide 02: Bối Cảnh Thực Tế & 4 Điểm Nghẽn Vận Hành Quán F&B
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 1 phút (Mốc 00:30 – 01:30)
* **Minh chứng trực quan:** Sơ đồ máy trạng thái [diagram/StateMachine_TrangThai.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/StateMachine_TrangThai.svg).
* **Mục tiêu nghiệp vụ:** Phân tích sâu 4 "nỗi đau" (pain points) điển hình mà các quán cà phê quy mô vừa và lớn đang gặp phải khi vận hành thủ công hoặc sử dụng phần mềm rời rạc.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - DUY):** 
> *"Thưa Cô, xuất phát từ việc khảo sát thực tế tại mô hình quán cà phê kết hợp quầy pha chế Bar & Lounge, nhóm nhận diện **4 điểm nghẽn nghiệp vụ chí mạng** làm suy giảm lợi nhuận và uy tín thương hiệu: 
> 1. **Thứ nhất là Bán khống và Sai lệch Order:** Nhân viên phục vụ ghi order bằng giấy hoặc nhận miệng, hoàn toàn không nắm được nguyên liệu trong quầy Bar còn hay hết. Khi chuyển phiếu vào quầy pha chế mới phát hiện hết sữa hoặc hết siro đào, nhân viên phải ra xin lỗi khách đổi món sau khi khách đã đợi 15 phút. Điều này phá vỡ trải nghiệm khách hàng và gây hỗn loạn trong giờ cao điểm. 
> 2. **Thứ hai là Thất thoát tiền mặt và Gian lận thu ngân:** Khi thanh toán tiền mặt, thu ngân có thể tự ý áp chiết khấu giảm giá 20% hoặc cấu kết hủy hóa đơn sau khi khách rời bàn để bỏ túi riêng tiền mặt. Cuối ca, quản lý chỉ nhận được số tiền bàn giao mà không có bất kỳ cơ chế kiểm soát đối soát đối chiếu nào. 
> 3. **Thứ ba là Kho nguyên liệu mù mờ và Nguy cơ âm kho:** Các phần mềm hiện nay thường chỉ trừ kho đơn giản theo số lượng mà không tính được Giá vốn hàng bán bình quân gia quyền (WAC) khi giá nhập biến động. Đặc biệt, khi 2 thu ngân cùng bấm thanh toán các món dùng chung nguyên liệu sữa tươi hay cà phê, hệ thống không có cơ chế khóa đồng thời sẽ dẫn đến xung đột Deadlock hoặc số tồn kho bị âm! Đồng thời, các vụ việc đổ vỡ, hao hụt không có sổ nhật ký lưu vết khiến quản lý không thể truy cứu trách nhiệm. 
> 4. **Thứ tư là Bất công chấm công và Thiếu hụt nhân sự ca trực:** Tình trạng chấm công hộ, đi trễ về sớm gây bất mãn nội bộ và tranh cãi bảng lương. Nguy hiểm hơn, việc phân ca không có công cụ cảnh báo dẫn đến tình trạng có ca trực chỉ có nhân viên phục vụ mà không hề có Thu ngân hoặc Quản lý, khiến quầy thu ngân bị tê liệt hoàn toàn. 
> Để giải quyết triệt để 4 bài toán này, nhóm đã xây dựng giải pháp phần mềm toàn diện Sangrimar Cafe & Lounge."*

* **Chỉ dẫn thao tác:** Duy nhấn mạnh 4 điểm nghẽn, bấm Mũi tên Phải chuyển sang Slide 03.

---

### Slide 03: Mục Tiêu To-Be & Ma Trận Ranh Giới (In-Scope vs Out-of-Scope)
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 45 giây (Mốc 01:30 – 02:15)
* **Minh chứng trực quan:** Sơ đồ triển khai hệ thống [diagram/TrienKhaiHeThong.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/TrienKhaiHeThong.svg).
* **Mục tiêu nghiệp vụ:** Làm rõ mục tiêu hệ thống To-Be và xác định dứt khoát ranh giới phạm vi đề tài, chứng minh tính khả thi kỹ thuật.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - DUY):** 
> *"Về mục tiêu hệ thống (To-Be), nhóm hướng tới tự động hóa khép kín 3 trụ cột vận hành F&B: 
> - Chuẩn hóa quy trình phục vụ tại bàn theo chu trình SOP 9 bước có kiểm soát nguyên liệu thời gian thực. 
> - Tự động hóa khấu trừ nguyên vật liệu theo định mức BOM chống Deadlock và tính giá vốn bình quân gia quyền liên hoàn WAC. 
> - Minh bạch hóa công tác nhân sự với Lưới phân ca cảnh báo thiếu vai trò và 7 quy tắc chấm công chuẩn mực. 
> 
> Để đồ án đạt tính khả thi cao nhất và hoàn thành xuất sắc trong thời gian quy định, nhóm phân định ranh giới hệ thống rõ ràng: 
> - **Phạm vi Nội vi (In-Scope):** Bao gồm toàn bộ quy trình vận hành nội bộ quán: POS sơ đồ bàn 3 màu viền, Soft Reservation, In Bar Delta, Phiếu Tạm Tính VietQR, Đóng bàn; Quản lý kho BOM 12 món, Trừ kho TreeMap, Sổ nhật ký `lich_su_kho`, Nhập kho WAC, Kiểm kê hao hụt; Quản lý nhân sự 7 quy tắc BR-CC, Phân ca tuần cảnh báo thiếu thu ngân. 
> - **Phạm vi Ngoại vi (Out-of-Scope):** Nhóm chủ động loại trừ các nghiệp vụ không thuộc phạm vi đồ án môn học như: Xuất hóa đơn đỏ VAT điện tử tích hợp cơ quan thuế (e-Invoice); Tích hợp máy quẹt thẻ POS ngân hàng cứng (EDC); và Quản lý chuỗi đa chi nhánh nhượng quyền."*

* **Chỉ dẫn thao tác:** Duy bấm Mũi tên Phải chuyển sang Slide 04.

---

### Slide 04: Kiến Trúc 3 Tầng Modular Monolith & An Ninh Phân Quyền RBAC
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 45 giây (Mốc 02:15 – 03:00)
* **Minh chứng trực quan:** Sơ đồ cấu trúc gói Spring Boot [diagram/Package_SpringBoot.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Package_SpringBoot.svg).
* **Mục tiêu nghiệp vụ:** Trình bày kiến trúc phần mềm 3 tầng phân lập, cơ chế bảo đảm an toàn dữ liệu và phân cấp quyền hạn bảo vệ dòng tiền.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - DUY):** 
> *"Về mặt kiến trúc kỹ thuật, hệ thống được thiết kế theo mô hình **All-in-One Modular Monolith 3 tầng chuẩn mực**: 
> - **Tầng Giao diện (Presentation Tier):** Xây dựng bằng Single Page Application (SPA) với HTML5/CSS3 và Vanilla JavaScript thuần. Thiết kế tối ưu hóa cho màn hình cảm ứng POS và lệnh in nhiệt `@media print`, thời gian tải trang đạt mức thần tốc dưới $50\text{ms}$. 
> - **Tầng Ứng dụng (Application Tier):** Nền tảng Spring Boot 3.2.5 với 9 REST Controllers phân lập theo domain. Hệ thống kiểm soát tính toàn vẹn giao dịch qua `@Transactional(isolation = READ_COMMITTED)`, lập lịch chốt ca tự động qua `@Scheduled` lúc 03:00 sáng, và tiêm Spring Bean `java.time.Clock` để trừu tượng hóa thời gian phục vụ kiểm thử. 
> - **Tầng Dữ liệu (Database Tier):** Hệ quản trị CSDL quan hệ MySQL 8.0 chuẩn hóa 3NF gồm 11 bảng thực thể toàn vẹn ACID, đặc biệt có Sổ nhật ký biến động `lich_su_kho` đóng vai trò Audit Trail. 
> 
> Hệ thống thiết lập ma trận phân quyền RBAC 3 cấp phân tầng chặt chẽ: **`PHUC_VU` $\subset$ `THU_NGAN` $\subset$ `QUAN_LY`**. Phục vụ chỉ mở bàn, gọi món; Thu ngân nhận tiền đóng bàn; và duy nhất Quản lý mới có quyền áp dụng chiết khấu giảm giá. 
> Sau đây, em xin kính mời bạn **Trần Văn Quý** trình bày về Yêu cầu chức năng và phân hệ Bán hàng POS."*

* **Chỉ dẫn thao tác:** Duy chuyển giao quyền nói, bấm Mũi tên Phải sang Slide 05.

---

### Slide 05: Danh Mục 15 Yêu Cầu Chức Năng (FRs) & Bảng Phân Cấp MoSCoW
* **Người trình bày:** Trần Văn Quý
* **Thời lượng:** 1 phút (Mốc 03:00 – 04:00)
* **Minh chứng trực quan:** Luồng điều hướng giao diện [02_SourceCode_WebJava_Prototype/BanGiao_UML/UI_Navigation_Flow.svg](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/BanGiao_UML/UI_Navigation_Flow.svg).
* **Mục tiêu nghiệp vụ:** Minh chứng phương pháp phân loại yêu cầu khoa học theo chuẩn MoSCoW, làm rõ 8 chức năng cốt lõi bắt buộc phải có của hệ thống F&B.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - QUÝ):** 
> *"Em xin kính chào Cô và các bạn! Em tên là Trần Văn Quý. 
> Em xin phép trình bày về Danh mục 15 Yêu cầu chức năng được nhóm phân cấp chặt chẽ theo ma trận **MoSCoW**: 
> - **Nhóm Must Have (8 FRs sống còn):** Đây là các chức năng cốt lõi bắt buộc phải hoàn thành 100% để quán có thể mở cửa hoạt động: FR-01 Mở bàn gọi món; FR-02 In Bar Delta chống lặp món; FR-03 Phiếu Tạm Tính VietQR; FR-04 Thanh toán đóng bàn; FR-05 Khấu trừ kho BOM chống Deadlock; FR-06 Nhập kho tính giá vốn WAC; FR-07 Chấm công cá nhân; và FR-08 Phân ca kiểm soát cân bằng vai trò. 
> - **Nhóm Should Have (4 FRs quan trọng):** Giúp nâng cao trải nghiệm khách hàng và năng lực quản trị: FR-09 Thực đơn Soft Reservation tính số ly khả dụng; FR-10 Cảnh báo tồn kho đỏ nhấp nháy; FR-11 Quản lý duyệt điều chỉnh chấm công có lưu lý do; và FR-12 Executive KPI Dashboard đa chu kỳ. 
> - **Nhóm Could Have (2 FRs tiện ích):** Hỗ trợ tra cứu: FR-13 In lại hóa đơn lịch sử; và FR-14 Bộ lọc thời gian KPI linh hoạt (Hôm nay, 7 ngày, Tháng, Toàn thời gian). 
> - **Nhóm Won't Have (Giai đoạn này):** Được nhóm chủ động loại trừ để tránh phình to phạm vi: FR-15 Đặt bàn trực tuyến từ xa, Tích điểm thành viên CRM và Xuất hóa đơn VAT điện tử."*

* **Chỉ dẫn thao tác:** Quý bấm Mũi tên Phải chuyển sang Slide 06.

---

### Slide 06: Biểu Đồ Use Case Tổng Quát (30 Ca Sử Dụng) & 3 Vai Trò
* **Người trình bày:** Trần Văn Quý
* **Thời lượng:** 1 phút (Mốc 04:00 – 05:00)
* **Minh chứng trực quan:** Biểu đồ Use Case tổng quát [diagram/UseCaseTongQuat.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/UseCaseTongQuat.svg).
* **Mục tiêu nghiệp vụ:** Giải thích mô hình hóa Use Case UML 2.5 với 30 ca sử dụng, làm rõ quan hệ kế thừa giữa 3 tác tử và ranh giới phân quyền nghiêm ngặt.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - QUÝ):** 
> *"Trên màn hình là Biểu đồ Use Case tổng quát gồm **30 ca sử dụng** được thiết kế theo chuẩn UML 2.5 với 3 tác tử chính tương ứng 3 vai trò tại quán: 
> - **Tác tử Phục vụ (`PHUC_VU` – 10 UCs):** Tác nghiệp trực tiếp với bàn: Xem sơ đồ bàn thời gian thực, Mở bàn, Thêm món vào đơn, In Phiếu Pha Chế (Bar Ticket), Gọi thêm món, Yêu cầu thanh toán để khóa bàn, Xem lịch ca cá nhân và Tự bấm Check-in/Check-out. Phục vụ tuyệt đối không nhìn thấy các phân hệ Kho và Quản trị nhân sự. 
> - **Tác tử Thu ngân (`THU_NGAN` – Kế thừa Phục vụ + 8 UCs):** Thu ngân sở hữu toàn bộ quyền của Phục vụ, đồng thời được giao quyền quản lý dòng tiền: In Phiếu Tạm Tính kèm VietQR động, Nhập tiền khách đưa, Tính tiền thối tự động, Xác nhận thanh toán & Đóng bàn, In Hóa đơn bán hàng nhiệt, và Tra cứu lịch sử hóa đơn. Lưu ý: Thu ngân không có quyền sửa đơn giá hay tự ý áp chiết khấu giảm giá. 
> - **Tác tử Quản lý (`QUAN_LY` – Kế thừa Thu ngân + 12 UCs):** Quản lý nắm quyền quản trị tối cao: Nhập kho tính giá vốn WAC, Thực hiện kiểm kê kho hao hụt, Thiết lập định mức BOM, Phân ca tuần kiểm soát cân bằng, Duyệt điều chỉnh công có lưu vết, Áp dụng chiết khấu giảm giá (5% - 20%), và Theo dõi Báo cáo Executive KPI Dashboard tài chính."*

* **Chỉ dẫn thao tác:** Quý bấm Mũi tên Phải chuyển sang Slide 07.

---

### Slide 07: Nghiệp Vụ POS: Sơ Đồ Bàn 3 Màu Viền & Cơ Chế Soft Reservation
* **Người trình bày:** Trần Văn Quý
* **Thời lượng:** 1 phút (Mốc 05:00 – 06:00)
* **Minh chứng trực quan:** Ảnh chụp giao diện POS [screenshots/02_pos_phuc_vu_so_do_ban.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/02_pos_phuc_vu_so_do_ban.png).
* **Mục tiêu nghiệp vụ:** Phân tích sâu 2 giải pháp đột phá trên màn hình bán hàng POS: Chuẩn hóa 3 màu viền trạng thái bàn và Cơ chế thắt cổ chai Soft Reservation.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - QUÝ):** 
> *"Tiếp theo là Phân hệ Bán hàng POS – trái tim vận hành trực tiếp tại quán Sangrimar: 
> Trước hết, hệ thống trực quan hóa trạng thái bàn qua **3 màu viền chuẩn mực**: 
> - **Màu Xanh dương (`TRONG`):** Bàn trống hoàn toàn, sẵn sàng đón lượt khách mới. 
> - **Màu Xanh lá (`DANG_DUNG_NUOC`):** Bàn đang có khách ngồi dùng nước, trên thẻ bàn hiển thị kèm Icon tiền và Tổng tiền tạm tính thời gian thực. 
> - **Màu Vàng hổ phách (`CHO_THANH_TOAN`):** Khách đã gọi tính tiền, thẻ bàn chuyển sang màu vàng. Lúc này hệ thống **KHÓA CHẶT QUYỀN THÊM HOẶC BỚT MÓN** của nhân viên phục vụ, bảo đảm số tiền trên phiếu in đối soát với số tiền thu ngân thu là bất biến 100%! 
> 
> Thứ hai là cơ chế đột phá: **'Giữ chỗ mềm' (Soft Reservation) và tính 'Số ly có thể làm' (`soLyCoTheLam`)**: 
> Thay vì để nhân viên gọi món bừa bãi, hệ thống tự động quét tồn kho của 13 nguyên liệu, trừ đi lượng nguyên liệu đang bị các bàn mở khác tạm giữ, sau đó chia cho định mức BOM để tìm ra **nguyên liệu thắt cổ chai ít nhất**: 
> $$\text{soLyCoTheLam} = \min_{nl \in BOM(m)} \left\lfloor \frac{\text{Tồn kho}(nl) - \text{Đang giữ}(nl)}{\text{Định mức}(nl, m)} \right\rfloor$$ 
> Hệ thống áp dụng nguyên lý **Che giấu thông tin (Information Hiding)**: Phục vụ chỉ nhìn thấy số ly có thể phục vụ: 
> - Nhãn Xanh: Còn $> 5$ ly. 
> - Nhãn Vàng: Còn $1\text{ -- }5$ ly (báo động sắp hết). 
> - Nhãn Đỏ: HẾT MÓN (nút thêm món bị khóa xám disabled). 
> Nhờ cơ chế này, quán triệt tiêu $100\%$ hiện tượng bán khống!"*

* **Chỉ dẫn thao tác:** Quý bấm Mũi tên Phải chuyển sang Slide 08.

---

### Slide 08: Chu Trình SOP 9 Bước, Cơ Chế In Bar Delta & Bộ 3 Phiếu In
* **Người trình bày:** Trần Văn Quý
* **Thời lượng:** 1 phút (Mốc 06:00 – 07:00)
* **Minh chứng trực quan:** Sơ đồ tuần tự POS [diagram/Sequence_BanHang.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_BanHang.svg).
* **Mục tiêu nghiệp vụ:** Trình bày chu trình phục vụ chuẩn mực 9 bước (SOP), giải pháp kỹ thuật In Bar Delta chống lãng phí nguyên liệu và chuẩn hóa bộ 3 phiếu in nhiệt.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - QUÝ):** 
> *"Quy trình phục vụ tại bàn của Sangrimar Cafe & Lounge được chuẩn hóa theo **chu trình SOP 9 bước khép kín**: 
> 1. Khách vào quán chọn chỗ $\rightarrow$ 2. Phục vụ chọn bàn trống $\rightarrow$ 3. Phục vụ gọi món theo số ly khả dụng $\rightarrow$ 4. Bấm In Bar gửi lệnh pha chế $\rightarrow$ 5. Khách gọi thêm món $\rightarrow$ 6. Bấm In Bar lần 2 $\rightarrow$ 7. Khách xin bill, phục vụ bấm Yêu cầu thanh toán $\rightarrow$ 8. Thu ngân in Phiếu Tạm Tính VietQR mang ra bàn $\rightarrow$ 9. Khách thanh toán, Thu ngân xác nhận đóng bàn, hóa đơn in ra và kho tự trừ ngầm. 
> 
> Trong chu trình này, có 2 điểm sáng kỹ thuật rất lớn: 
> - **Thứ nhất là Cơ chế In Bar Delta ($\Delta$):** Tại bảng `chi_tiet_hoa_don`, nhóm bổ sung cột `so_luong_da_in_bar`. Khi khách gọi thêm món ở bước 5, lệnh in Bar lần 2 sẽ tính toán: 
> $$\Delta = \text{so\_luong} - \text{so\_luong\_da\_in\_bar} > 0$$ 
> Máy in quầy Bar chỉ in đúng món mới gọi thêm kèm tiêu đề *'Phiếu Pha Chế (Gọi thêm)'*, hoàn toàn không in lại các món cũ, giúp Barista không bị hoang mang và không bao giờ pha chế trùng lặp! 
> - **Thứ hai là Chuẩn hóa Bộ 3 mẫu phiếu in nghiệp vụ:** 
> - *Phiếu Pha Chế (Bar Ticket):* Chỉ gồm Tên bàn, Món, Số lượng, Ghi chú pha chế. **Tuyệt đối không hiển thị giá tiền** vì quầy Bar chỉ làm nhiệm vụ kỹ thuật. 
> - *Phiếu Tạm Tính (Proforma Bill):* In chi tiết từng món, đơn giá snapshot, tổng tiền và mã **VietQR động** chuyển khoản chính xác số tiền lẻ. 
> - *Hóa Đơn Bán Hàng nhiệt:* Xuất khi thu ngân đã thu tiền xong, đóng bàn vĩnh viễn, làm chứng từ đối soát tài chính. 
> 
> Sau đây, em xin phép chuyển sang trình duyệt để **Demo trực tiếp Live Demo 1**."*

* **Chỉ dẫn thao tác:** Quý chuyển giao diện sang trình duyệt Chrome tab `banhang.html`.

---
### Slide 09: [LIVE DEMO 1] Bán Hàng POS: Mở Bàn, In Bar Delta & Đóng Bàn
* **Người thực hiện Demo:** Trần Văn Quý
* **Thời lượng:** 2 phút (Mốc 07:00 – 09:00)
* **Trang web thao tác:** `http://localhost:8080/index.html` $\rightarrow$ `banhang.html`.
* **Minh chứng trực quan:** Sơ đồ hoạt động POS [diagram/Activity_BanHang.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_BanHang.svg).
* **Mục tiêu nghiệp vụ:** Thao tác thực chiến 5 bước: Mở Bàn 03, gọi món theo Soft Reservation, in Bar Delta, khóa bàn `CHO_THANH_TOAN`, in Phiếu Tạm Tính VietQR và đóng bàn giải phóng về màu Xanh dương.

> 🗣️ **LỜI THOẠI THAO TÁC THỰC CHIẾN (QUÝ):** 
> *(Quý chia sẻ màn hình Chrome, thực hiện các thao tác tuần tự)* 
> 
> 1. *"Thưa Cô, tại trang đăng nhập `index.html`, em sử dụng nút điền nhanh **'Phục vụ'** để đăng nhập 1 chạm. Hệ thống vào thẳng `banhang.html`. Trên thanh Topbar, hai phân hệ 'Kho' và 'Nhân sự' đã tự động ẩn đi để bảo vệ phân quyền.* 
> 2. *Em chọn **Bàn 03** đang trống (viền Xanh dương). Kính mời Cô quan sát thực đơn: món Cà phê Muối hiện nhãn xanh 'Còn 8 ly', Caffe Latte 'Còn 5 ly'. Em chọn 1 ly Cà phê Muối Huế, 1 ly Caffe Latte và nhập ghi chú **'Ít đường, sữa nóng'**. Tạm tính là 77.000 đ. 
> Em bấm nút **'Mở bàn & Tạo đơn'** $\rightarrow$ Hệ thống sinh Hóa đơn mới, và Bàn 03 lập tức chuyển sang **viền Xanh lá (`DANG_DUNG_NUOC`)**, trên thẻ bàn hiện rõ số tiền 77.000 đ.* 
> 3. *Em bấm nút **'In Bar'** $\rightarrow$ Phiếu Pha Chế xuất hiện gửi quầy Bar. 
> Bây giờ khách tại Bàn 03 gọi thêm 1 ly **Trà Đào Cam Sả**. Em bấm thêm vào giỏ. Khi em bấm 'In Bar' lần 2 $\rightarrow$ Nhờ cơ chế **In Delta**, phiếu in chỉ hiển thị duy nhất 1 ly Trà Đào mới gọi, không hề in lặp lại 2 ly trước đó!* 
> 4. *Khách báo tính tiền, em bấm **'Yêu cầu thanh toán'** $\rightarrow$ Bàn 03 lập tức chuyển sang **viền Vàng hổ phách (`CHO_THANH_TOAN`)**. Lúc này các nút thêm/bớt món bị khóa chặt.* 
> 5. *(Quý đăng xuất, dùng nút Quick-fill đăng nhập **thungan / thungan**)* 
> *Thu ngân vào hệ thống, thấy Bàn 03 màu vàng: 
> - Bấm **'In Tạm Tính'** $\rightarrow$ Phiếu Tạm Tính xuất hiện có kèm mã VietQR động để phục vụ mang ra bàn cho khách đối soát. 
> - Khách đưa 150.000 đ tiền mặt. Thu ngân nhập số tiền $\rightarrow$ Hệ thống tự tính tiền thối lại là 35.000 đ. 
> - Kính mời Cô quan sát: Ô Chiết khấu bị khóa disabled hoàn toàn, Thu ngân không thể tự ý giảm giá. 
> - Thu ngân bấm **'Xác nhận thanh toán & Đóng bàn'** $\rightarrow$ Hóa Đơn Bán Hàng chính thức in ra, hệ thống tự động trừ kho BOM ngầm, và Bàn 03 lập tức quay về **màu Xanh dương TRỐNG** sẵn sàng đón khách mới!* 
> 
> *Phần tiếp theo về Thiết kế CSDL và Phân hệ Nhân sự, em xin nhường lời cho bạn **Lê Tấn Đạt**."*

* **Chỉ dẫn thao tác:** Quý chuyển quyền nói cho Đạt, chuyển màn hình lại slide, bấm Mũi tên Phải sang Slide 10.

---

### Slide 10: Yêu Cầu Phi Chức Năng (NFR) & Thiết Kế CSDL 11 Bảng Chuẩn 3NF
* **Người trình bày:** Lê Tấn Đạt
* **Thời lượng:** 1 phút (Mốc 09:00 – 10:00)
* **Minh chứng trực quan:** Sơ đồ CSDL [diagram/ERD_DuLieu.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/ERD_DuLieu.svg).
* **Mục tiêu nghiệp vụ:** Phân tích các chỉ số NFR khắt khe, giải thích cấu trúc 11 bảng CSDL quan hệ 3NF và các trường thiết kế thông minh chống lỗi nghiệp vụ.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - ĐẠT):** 
> *"Em xin kính chào Cô và các bạn! Em tên là Lê Tấn Đạt. 
> Trước hết, về mặt phi chức năng (NFR), hệ thống đạt các chỉ số kỹ thuật rất ấn tượng: Thời gian phản hồi API dưới $200\text{ms}$, thời gian tải trang dưới $50\text{ms}$, độ khả dụng đạt $99.9\%$ và cam kết toàn vẹn dữ liệu ACID $100\%$. 
> 
> Về thiết kế lưu trữ, hệ thống gồm **11 bảng thực thể quan hệ chuẩn hóa 3NF** chia làm 3 cụm nghiệp vụ: 
> - **Cụm Bán hàng (4 bảng):** `ban_ca_phe`, `mon_nuoc`, `hoa_don`, `chi_tiet_hoa_don`. 
> - **Cụm Kho (3 bảng):** `nguyen_lieu`, `dinh_muc_mon`, và bảng thứ 11 mới bổ sung là **`lich_su_kho`** – đóng vai trò cuốn Sổ cái điện tử ghi nhận biến động 3 loại giao dịch: `NHAP_KHO`, `XUAT_BAN_HANG` (nối khóa ngoại sang `hoa_don`), và `KIEM_KE_HAO_HUT`. 
> - **Cụm Nhân sự (4 bảng):** `nhan_vien`, `ca_lam_viec`, `phan_ca`, `cham_cong`. 
> 
> Trong CSDL, nhóm áp dụng 3 kỹ thuật thiết kế chuyên sâu: 
> 1. **Price Snapshot (`don_gia_luc_ban`):** Trong bảng `chi_tiet_hoa_don`, ta lưu giá bán tại đúng thời điểm lập đơn. Nếu tuần sau quán tăng giá cà phê từ 35k lên 40k, thì báo cáo doanh thu của các hóa đơn cũ vẫn bất biến, không bị tính lại theo giá mới! 
> 2. **Kiểm soát in Delta (`so_luong_da_in_bar`):** Đảm bảo không in trùng phiếu pha chế. 
> 3. **Khóa lạc quan (`phien_ban` / `@Version`):** Chống việc 2 nhân viên cùng sửa đè lên một hóa đơn."*

* **Chỉ dẫn thao tác:** Đạt bấm Mũi tên Phải chuyển sang Slide 11.

---

### Slide 11: Nghiệp Vụ Nhân Sự: Phân Ca Cân Bằng & 7 Quy Tắc Chấm Công BR-CC
* **Người trình bày:** Lê Tấn Đạt
* **Thời lượng:** 1 phút (Mốc 10:00 – 11:00)
* **Minh chứng trực quan:** Sơ đồ hoạt động chấm công [diagram/Activity_ChamCong.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_ChamCong.svg).
* **Mục tiêu nghiệp vụ:** Phân tích quy tắc cân bằng vai trò trên lưới phân ca (`BR-CC-07`) và 7 quy tắc nghiệp vụ chấm công tự động (BR-CC-01..07) chống gian lận công.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - ĐẠT):** 
> *"Điểm sáng tạo nhất của Phân hệ Nhân sự là cơ chế kiểm soát cân bằng ca trực và **7 Quy tắc Chấm công chuẩn mực (BR-CC-01 đến BR-CC-07)**: 
> - **Quy tắc Cân bằng vai trò (`BR-CC-07`):** Trên Lưới phân ca tuần, hệ thống kiểm tra số lượng nhân sự theo từng vai trò. Bắt buộc mỗi ca trực phải có ít nhất 1 Thu ngân hoặc 1 Quản lý. Nếu trong ca chỉ có nhân viên Phục vụ, ô ca lập tức **bật viền cam nét đứt** và hiển thị cờ `thieuThuNgan = true` kèm huy hiệu cảnh báo *'CẢNH BÁO: THIẾU THU NGÂN / QUẢN LÝ'*. Quản lý buộc phải phân công bổ sung nhân sự trước khi chốt lịch! 
> - **Về quy tắc Chấm công tự động:** 
> - `BR-CC-01`: Ràng buộc 1:1 giữa Phân ca và Chấm công, chống việc bấm chấm công hộ. 
> - `BR-CC-02`: Cửa sổ check-in nghiêm ngặt trong khoảng $[\text{start} - 30p, \text{end})$. 
> - `BR-CC-03`: **Quy tắc Du di 15 phút (Grace Period):** Check-in trễ $\le 15$ phút vẫn tính `DUNG_GIO`; nếu quá 15 phút tính `DI_TRE` và ghi nhận chính xác số phút trễ. 
> - `BR-CC-04`: Tính giờ thực tế (Clamped), không cộng dồn giờ ảo khi đến sớm, phát hiện `VE_SOM` nếu ra trước 15 phút, và tự động nhân hệ số $1.2$ cho ca tối sau 22:00. 
> - `BR-CC-05`: Tiến trình ngầm `@Scheduled` chạy lúc 03:00 sáng tự động quét các ca chưa check-in đánh dấu `VANG`, và các ca quên check-out đánh dấu `QUEN_CHECK_OUT`. 
> - `BR-CC-06`: Quản lý điều chỉnh chấm công bắt buộc nhập lý do và lưu vết kiểm toán người sửa. 
> 
> Sau đây, em xin phép **Demo trực tiếp Phân hệ Nhân sự**."*

* **Chỉ dẫn thao tác:** Đạt chuyển giao diện sang trình duyệt Chrome tab `nhansu.html`.

---

### Slide 12: [LIVE DEMO 2] Chấm Công Cá Nhân & Phân Ca Cân Bằng Vai Trò
* **Người thực hiện Demo:** Lê Tấn Đạt
* **Thời lượng:** 1.5 phút (Mốc 11:00 – 12:30)
* **Trang web thao tác:** `http://localhost:8080/nhansu.html`.
* **Minh chứng trực quan:** Sơ đồ tuần tự chấm công [diagram/Sequence_ChamCong.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_ChamCong.svg).
* **Mục tiêu nghiệp vụ:** Trình diễn thực tế: 1) Chế độ cá nhân tự chấm công ghi nhận `DI_TRE`; 2) Lưới phân ca quản trị cảnh báo viền cam nét đứt và khắc phục; 3) Quản lý duyệt điều chỉnh công có lưu vết.

> 🗣️ **LỜI THOẠI THAO TÁC THỰC CHIẾN (ĐẠT):** 
> *(Đạt chia sẻ màn hình Chrome, thực hiện các thao tác tuần tự)* 
> 
> 1. *"Thưa Cô, em đăng nhập tài khoản **phucvu / phucvu** và vào `nhansu.html`. 
> - Phục vụ chỉ nhìn thấy **Chế độ Cá nhân**: Xem lịch phân ca của riêng mình và đồng hồ kỹ thuật số chạy thời gian thực. 
> - Hôm nay em được xếp **Ca Sáng (07:00 - 12:00)**. Em bấm **'Check-in Ca Lam'** $\rightarrow$ Vì hiện tại đã quá 07:15, hệ thống lập tức ghi nhận trạng thái **'DI_TRE'** và tính chính xác số phút trễ. 
> - Nút bấm tự động đổi thành **'Check-out Ca Lam'** sẵn sàng khi tan ca.* 
> 2. *(Đạt đăng xuất, đăng nhập tài khoản **quanly / quanly**)* 
> *Khi Quản lý đăng nhập, giao diện mở khóa thêm **Chế độ Quản trị**: 
> - Tại khối **Lịch Phân Ca Tuần**, kính mời Cô quan sát ô Ca Tối ngày Thứ Tư: Hiện tại chỉ có nhân viên Lê Tấn Đạt (vai trò Phục vụ). Lập tức Quy tắc `BR-CC-07` kích hoạt: ô ca hiển thị **viền cam nét đứt** kèm huy hiệu cảnh báo hổ phách: **`CẢNH BÁO: THIẾU THU NGÂN / QUẢN LÝ`**. 
> - Bây giờ em bấm phân công thêm bạn Trần Văn Quý (Thu ngân) vào ca đó $\rightarrow$ Cảnh báo cam lập tức biến mất, viền trở lại bình thường, bảo đảm ca trực an toàn!* 
> 3. *Tại bảng Lịch sử Chấm công phía dưới: Có một ca hôm qua nhân viên quên check-out, Quản lý bấm **'Điều chỉnh'**, sửa lại giờ ra 12:15 và nhập lý do: 'Nhân viên ở lại dọn quầy, camera xác nhận 12:15'. 
> Sau khi bấm Lưu, hệ thống tự động tính lại giờ công quy đổi và ghi nhận rõ người sửa, giờ sửa minh bạch 100%.* 
> 
> *Tiếp theo, em xin kính mời Trưởng nhóm **Nguyễn Văn Duy** trình bày về Phân hệ Kho và Live Demo Trừ kho tự động."*

* **Chỉ dẫn thao tác:** Đạt chuyển quyền nói cho Duy, chuyển slide sang Slide 13.

---

### Slide 13: Nghiệp Vụ Kho: Định Mức BOM 12 Món & Công Thức Giá Vốn WAC
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 1 phút (Mốc 12:30 – 13:30)
* **Minh chứng trực quan:** Sơ đồ lớp miền [diagram/LopThietKe.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/LopThietKe.svg).
* **Mục tiêu nghiệp vụ:** Phân tích cấu trúc định mức kỹ thuật 34 dòng BOM cấu thành 12 món từ 13 nguyên liệu thực tế, giải thích công thức tính giá vốn bình quân gia quyền liên hoàn WAC.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - DUY):** 
> *"Kính thưa Cô, em xin tiếp tục với Phân hệ Quản trị Kho – hạt nhân kỹ thuật kiểm soát tài chính của Sangrimar Cafe & Lounge: 
> - Hệ thống chuẩn hóa **13 nguyên vật liệu thực tế $100\%$** (như Hạt Robusta, Arabica, Sữa tươi tiệt trùng, Sữa đặc Ngôi Sao, Trà đen Ceylon, Siro Teisseire, Bột Matcha Uji,...) cấu thành **34 dòng định mức BOM** cho 12 món nước Sangrimar (Ví dụ: Cà phê muối gồm $15\text{g}$ Robusta + $25\text{ml}$ sữa đặc + $30\text{ml}$ kem béo). 
> - **Phương pháp Giá vốn Bình quân Gia quyền (WAC - Weighted Average Cost):** 
> Trong ngành F&B, giá nông sản cà phê và sữa biến động theo từng đợt nhập. Nếu dùng giá cố định sẽ làm sai lệch báo cáo lợi nhuận. Nhóm áp dụng công thức WAC liên hoàn tính toán tự động mỗi khi có phiếu nhập kho: 
> $$\text{WAC}_{\text{mới}} = \frac{(\text{Tồn cũ} imes \text{Đơn giá cũ}) + (\text{Số lượng nhập} imes \text{Đơn giá nhập})}{\text{Tồn cũ} + \text{Số lượng nhập}}$$ 
> Đơn giá WAC này sẽ được dùng để nhân với định mức BOM khi xuất bán, cho phép đo lường chính xác Giá vốn hàng bán (COGS) và Lợi nhuận gộp theo từng ngày. 
> - **Cảnh báo tồn kho đỏ:** Mỗi nguyên liệu có một `nguong_toi_thieu`. Khi tồn kho $\le$ ngưỡng, hệ thống lập tức bật nhãn đỏ nhấp nháy 'SẮP HẾT HÀNG' và gửi cảnh báo lên Dashboard."*

* **Chỉ dẫn thao tác:** Duy bấm Mũi tên Phải chuyển sang Slide 14.

---

### Slide 14: Nghiệp Vụ Kho: Thuật Toán Trừ Kho Chống Deadlock & Kiểm Kê Hao Hụt
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 1 phút (Mốc 13:30 – 14:30)
* **Minh chứng trực quan:** Sơ đồ tuần tự trừ kho [diagram/Sequence_TruKho.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_TruKho.svg).
* **Mục tiêu nghiệp vụ:** Phân tích bài toán kỹ thuật hóc búa nhất: Xung đột Deadlock khi trừ kho đồng thời và giải pháp sắp xếp khóa tăng dần bằng `TreeMap`, cùng quy trình kiểm kê hao hụt tài chính.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - DUY):** 
> *"Thưa Cô, đây là bài toán kỹ thuật phức tạp nhất mà em đã trực tiếp giải quyết: 
> **Nguy cơ Deadlock trong F&B giờ cao điểm:** Giả sử Bàn 01 thanh toán ly Cà phê sữa (dùng Sữa đặc và Robusta), Bàn 02 thanh toán ly Bạc xỉu (dùng Robusta và Sữa đặc). Nếu 2 giao dịch chạy đồng thời: 
> - Giao dịch 1 chiếm giữ khóa Sữa đặc rồi chờ khóa Robusta. 
> - Giao dịch 2 chiếm giữ khóa Robusta rồi chờ khóa Sữa đặc. 
> $\rightarrow$ Hệ quả là **Deadlock vòng tròn (Circular Wait)**, cả hai giao dịch bị treo cứng và cơ sở dữ liệu văng lỗi Rollback, quầy thu ngân bị tê liệt! 
> 
> **Thuật toán giải quyết của Sangrimar:** 
> 1. Toàn bộ quá trình thanh toán bọc trong `@Transactional(isolation = Isolation.READ_COMMITTED)`. 
> 2. Hệ thống gom tất cả định mức nguyên liệu cần trừ vào cấu trúc **`TreeMap<Long, Double>`**. Nhờ đặc tính của `TreeMap`, danh sách mã nguyên liệu được **tự động sắp xếp tăng dần theo ID ($1 
\rightarrow 2 
\rightarrow 3$)**. 
> 3. Hệ thống thực thi câu lệnh khóa bi quan `SELECT ... FOR UPDATE` tuần tự theo đúng thứ tự ID tăng dần. 
> Vì mọi giao dịch trong hệ thống đều xếp hàng xin khóa theo cùng một trật tự cố định, điều kiện Circular Wait bị triệt tiêu $100\%$! Hệ thống **vừa không bao giờ bị Deadlock, vừa không bao giờ bị âm kho**! 
> 
> Đồng thời, mọi biến động đều được lưu vết vào Sổ nhật ký `lich_su_kho`. Cuối ngày, Quản lý kiểm kê thực tế, nếu phát hiện chênh lệch do đổ vỡ (Shrinkage), hệ thống ghi dòng `KIEM_KE_HAO_HUT` và tính toán tổn thất tài chính đưa vào báo cáo. 
> Sau đây, em xin phép **Demo trực tiếp Phân hệ Kho & KPI Dashboard**."*

* **Chỉ dẫn thao tác:** Duy chuyển sang trình duyệt Chrome tab `kho.html`.

---

### Slide 15: [LIVE DEMO 3] Trừ Kho BOM, Sổ Nhật Ký, Nhập Kho WAC & KPI Dashboard
* **Người thực hiện Demo:** Nguyễn Văn Duy
* **Thời lượng:** 1.5 phút (Mốc 14:30 – 16:00)
* **Trang web thao tác:** `http://localhost:8080/kho.html`.
* **Minh chứng trực quan:** Sơ đồ hoạt động kho [diagram/Activity_TruKho.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_TruKho.svg).
* **Mục tiêu nghiệp vụ:** Trình diễn thực tế: 1) Tồn kho tự trừ ngầm sau Live Demo 1; 2) Sổ nhật ký `lich_su_kho` ghi nhận chi tiết; 3) Nhập kho tính WAC xóa báo đỏ; 4) Kiểm kê hao hụt; 5) Executive KPI Dashboard.

> 🗣️ **LỜI THOẠI THAO TÁC THỰC CHIẾN (DUY):** 
> *(Duy chia sẻ màn hình Chrome trang `kho.html` trên quyền Quản lý)* 
> 
> 1. *"Kính thưa Cô, khi nãy bạn Quý đã thanh toán đơn hàng Bàn 03 gồm: 1 Cà phê Muối, 1 Caffe Latte và 1 Trà Đào Cam Sả. 
> Tại Tab 1 'Tồn kho nguyên liệu', Cô có thể thấy: 
> - Hạt Robusta đã tự động trừ đúng $25\text{ g}$ ($15\text{g}$ từ Cà phê muối + $10\text{g}$ từ Latte). 
> - Sữa tươi tiệt trùng tự động trừ đúng $180\text{ ml}$. 
> - Siro Đào Teisseire tự động trừ đúng $25\text{ ml}$. 
> Số tồn kho giảm ngay tức thì mà không cần thủ kho phải nhập tay bất kỳ thao tác nào!* 
> 2. *Em chuyển sang Tab 3 **'Nhật ký Biến động kho'**: 
> Ba dòng giao dịch loại **`XUAT_BAN_HANG`** xuất hiện rõ ràng, ghi nhận chính xác thời gian, gắn liền mã Hóa đơn của Bàn 03, tên Thu ngân thực hiện, tồn trước và tồn sau khớp từng con số!* 
> 3. *Tại dòng Bột Matcha Uji, tồn kho hiện tại chỉ còn $80\text{ g}$, nhỏ hơn ngưỡng tối thiểu $100\text{ g}$, nên hệ thống đang bật nhãn đỏ nhấp nháy **`SẮP HẾT HÀNG`**. 
> Em bấm nút **'Nhập kho'**, sử dụng nút preset nhập nhanh **+500 g** với đơn giá mới. 
> Sau khi bấm Xác nhận: Tồn kho tăng lên $580\text{ g}$, đơn giá vốn được tính lại theo WAC, nhãn đỏ biến mất và lập tức khôi phục nhãn xanh **`Đủ hàng`**!* 
> 4. *Cuối ca, Quản lý cân đo thực tế thấy Siro Đào bị đổ vỡ mất $50\text{ ml}$. Em bấm **'Kiểm kê'**, nhập số thực tế và nhập lý do: 'Vỡ nắp chai khi pha chế'. Hệ thống ghi nhận dòng **`KIEM_KE_HAO_HUT`**, tự động tính giá trị tổn thất tài chính đưa vào báo cáo.* 
> 5. *Phía trên cùng là 6 thẻ **Executive KPI Dashboard**: Doanh thu thuần, Giá vốn COGS, Lợi nhuận gộp đạt $79,0\%$, đối soát Tiền mặt vs Chuyển khoản QR và biểu đồ Top 5 món bán chạy nhất với Cà phê Muối dẫn đầu. Quản lý có thể lọc linh hoạt theo Hôm nay, 7 ngày, Tháng này hoặc Toàn thời gian chỉ với một cú nhấp chuột."*

* **Chỉ dẫn thao tác:** Duy quay lại slide trình chiếu, bấm Mũi tên Phải sang Slide 16.

---

### Slide 16: Tổng Kết Kiểm Thử (19/19 Tests Pass), Đánh Giá Thực Tiễn & Q&A
* **Người trình bày:** Nguyễn Văn Duy
* **Thời lượng:** 1 phút (Mốc 16:00 – 17:00)
* **Minh chứng trực quan:** Bảng nghiệm thu kiểm thử [screenshots/01_dang_nhap.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/01_dang_nhap.png).
* **Mục tiêu nghiệp vụ:** Báo cáo kết quả kiểm thử tự động toàn diện (19/19 Integration Tests Pass 100%), đề xuất chiến lược triển khai Direct Cutover, gửi lời cảm ơn và khởi động phiên Q&A phản biện.

> 🗣️ **LỜI THOẠI CHI TIẾT (VERBATIM SPEECH - DUY):** 
> *"Kính thưa Cô, để bảo chứng cho độ tin cậy tuyệt đối của phần mềm trước khi nghiệm thu: 
> - Toàn bộ mã nguồn đã vượt qua xuất sắc **19/19 ca kiểm thử tích hợp tự động Backend (100% BUILD SUCCESS)** chạy trên môi trường H2 In-Memory độc lập: 
> - 5 ca kiểm thử POS, Soft Reservation và phân quyền RBAC (`DonHangIntegrationTest.java`). 
> - 6 ca kiểm thử Nhập kho WAC, trừ kho BOM và thuật toán chống Deadlock (`KhoKpiIntegrationTest.java`). 
> - 8 ca kiểm thử Chấm công, du di 15 phút, hệ số ca đêm và scheduler tự động chốt ca (`NhanSuChamCongIntegrationTest.java`). 
> - Đồng thời, hệ thống vượt qua **16/16 kịch bản kiểm thử hộp đen E2E tự động** trên trình duyệt Chrome. 
> 
> Về chiến lược triển khai, nhóm đề xuất phương pháp **Chuyển đổi trực tiếp đầu tuần (Direct Cutover on Monday)**: Thực hiện chốt kiểm kê vào tối Chủ Nhật, nạp số dư ban đầu và đưa hệ thống vào vận hành chính thức ngay sáng Thứ Hai, giúp tối ưu chi phí và không làm gián đoạn kinh doanh. 
> 
> Hệ thống Sangrimar Cafe & Lounge đã hoàn thiện trọn vẹn cả về mặt học thuật Kỹ nghệ yêu cầu lẫn giá trị ứng dụng thực tiễn cho ngành F&B. 
> Nhóm chúng em xin gửi lời cảm ơn chân thành và sâu sắc nhất đến Cô **TS. Hồ Thị Linh** đã tận tình định hướng và hướng dẫn nhóm trong suốt học kỳ vừa qua! 
> Sau đây, nhóm chúng em xin trân trọng kính mời Cô và Hội đồng đặt câu hỏi phản biện cho nhóm ạ!"*

* **Chỉ dẫn thao tác:** Cả 3 thành viên đứng nghiêm túc, sẵn sàng cho phiên Q&A.

---
# PHẦN III: HƯỚNG DẪN CHI TIẾT 3 PHÂN ĐOẠN LIVE DEMO THỰC CHIẾN

### 3.1. Live Demo 1 (Trần Văn Quý): POS, Soft Reservation, In Bar Delta & Đóng Bàn
- **Mục tiêu:** Chứng minh luồng nghiệp vụ bán hàng SOP 9 bước, cơ chế thắt cổ chai Soft Reservation, thuật toán in Bar Delta không lặp món và phân quyền thanh toán đóng bàn.
- **Tài khoản sử dụng:** `phucvu / phucvu` sau đó đổi sang `thungan / thungan`.
- **Bảng thao tác chi tiết:**

| Bước | Thao tác thực tế trên Web | Kết quả hiển thị trên màn hình | Lời thuyết minh đồng bộ |
| :---: | :--- | :--- | :--- |
| **1** | Mở `http://localhost:8080/index.html`, bấm nút Quick Fill **"Phục vụ"** $\rightarrow$ bấm Đăng nhập. | Chuyển vào `banhang.html`. Thanh Topbar chỉ thấy tab Bán hàng; Kho và Nhân sự ẩn. | *"Em đăng nhập tài khoản Phục vụ. Menu quản trị bị ẩn theo ma trận RBAC."* |
| **2** | Bấm chọn **Bàn 03** (đang viền Xanh dương). | Mở giỏ hàng Bàn 03. Thực đơn phía dưới hiển thị số ly khả dụng (Cà phê Muối 8 ly, Latte 5 ly). | *"Bàn 03 đang trống. Món Cà phê Muối còn 8 ly theo cơ chế thắt cổ chai."* |
| **3** | Bấm gọi 1 Cà phê Muối Huế (35k), 1 Caffe Latte (42k), gõ ghi chú: *"Ít đường, sữa nóng"*. Bấm nút **"Mở bàn & Tạo đơn"**. | Tổng tạm tính: 77.000 đ. Thẻ Bàn 03 đổi sang **viền Xanh lá (`DANG_DUNG_NUOC`)**, hiện tiền 77.000 đ. | *"Em gọi 2 ly, tạm tính 77k. Bàn 03 chuyển sang viền Xanh lá đang phục vụ."* |
| **4** | Bấm nút **"In Bar"** lần 1. | Modal Phiếu Pha Chế xuất hiện: Bàn 03, 1 Cà phê Muối, 1 Caffe Latte, ghi chú (không hiện giá). | *"Phiếu pha chế xuất lệnh quầy Bar, tuyệt đối không hiển thị giá tiền."* |
| **5** | Khách gọi thêm 1 ly **Trà Đào Cam Sả** (38k). Bấm thêm vào giỏ. Bấm **"In Bar"** lần 2. | Phiếu in lần 2 xuất hiện tiêu đề: *"Phiếu Pha Chế (Gọi thêm)"*, **chỉ gồm duy nhất 1 ly Trà Đào Cam Sả**! | *"Nhờ cơ chế In Bar Delta, lệnh in chỉ lấy món chênh lệch, không in lại 2 ly trước!"* |
| **6** | Bấm nút **"Yêu cầu thanh toán"**. | Thẻ Bàn 03 đổi sang **viền Vàng hổ phách (`CHO_THANH_TOAN`)**. Nút thêm/bớt món bị khóa. | *"Khách xin bill, em bấm Yêu cầu thanh toán để khóa bàn, chống sửa lệch số liệu."* |
| **7** | Bấm Đăng xuất $\rightarrow$ Bấm nút Quick Fill **"Thu ngân"** $\rightarrow$ Đăng nhập. | Thu ngân truy cập `banhang.html`, chọn Bàn 03 đang màu vàng. | *"Em đăng nhập Thu ngân để thực hiện thu tiền và đóng bàn."* |
| **8** | Bấm nút **"In Tạm Tính"**. | Xuất Phiếu Tạm Tính gồm 3 món (115.000 đ), kèm **mã VietQR động** chuyển khoản chính xác 115k. | *"Thu ngân in Phiếu Tạm Tính kèm mã VietQR động mang ra cho khách đối soát."* |
| **9** | Khách trả tiền mặt 150.000 đ. Thu ngân nhập 150000 vào ô Khách đưa. Quan sát ô Chiết khấu. | Tiền thối tự tính: 35.000 đ. Ô chiết khấu bị **khóa disabled**. | *"Ô chiết khấu bị khóa chặt. Khách đưa 150k, hệ thống tự tính tiền thối 35k."* |
| **10** | Bấm **"Xác nhận thanh toán & Đóng bàn"**. | Hóa đơn bán hàng nhiệt chính thức xuất hiện. Bàn 03 trở về **màu Xanh dương TRỐNG**. | *"Xác nhận thu tiền, hóa đơn in ra, kho trừ ngầm và Bàn 03 trở về trạng thái Trống!"* |

---

### 3.2. Live Demo 2 (Lê Tấn Đạt): Chấm Công Cá Nhân, Cảnh Báo Thiếu Thu Ngân & Duyệt Công
- **Mục tiêu:** Chứng minh 7 quy tắc chấm công BR-CC, quy tắc du di 15 phút, lưới phân ca kiểm soát cân bằng vai trò (`thieuThuNgan = true`) và audit log khi điều chỉnh công.
- **Tài khoản sử dụng:** `phucvu / phucvu` sau đó đổi sang `quanly / quanly`.
- **Bảng thao tác chi tiết:**

| Bước | Thao tác thực tế trên Web | Kết quả hiển thị trên màn hình | Lời thuyết minh đồng bộ |
| :---: | :--- | :--- | :--- |
| **1** | Mở `http://localhost:8080/nhansu.html` với tài khoản `phucvu`. | Chế độ Cá nhân hiển thị: Lịch ca cá nhân, đồng hồ số thời gian thực, nút Check-in màu xanh. | *"Phục vụ chỉ thấy Chế độ Cá nhân với lịch ca của mình và đồng hồ real-time."* |
| **2** | Quan sát Ca Sáng (07:00 - 12:00). Bấm nút **"Check-in Ca Lam"**. | Hệ thống ghi nhận trạng thái **`DI_TRE`** (vì quá 07:15). Nút bấm chuyển sang **"Check-out Ca Lam"**. | *"Do check-in sau 07:15, quy tắc BR-CC-03 ghi nhận Đi trễ và tính rõ số phút trễ."* |
| **3** | Đăng xuất $\rightarrow$ Đăng nhập tài khoản **`quanly / quanly`** $\rightarrow$ Vào `nhansu.html`. | Mở khóa thêm tab **Chế độ Quản trị**: Bảng lịch phân ca tuần và Lịch sử chấm công toàn bộ nhân viên. | *"Quản lý mở khóa Chế độ Quản trị để theo dõi ca trực toàn quán."* |
| **4** | Quan sát ô **Ca Tối ngày Thứ Tư** trên Lưới phân ca tuần. | Ô ca hiển thị **viền cam nét đứt** kèm huy hiệu: **`CẢNH BÁO: THIẾU THU NGÂN / QUẢN LÝ`**. | *"Ô Ca Tối Thứ Tư chỉ có Phục vụ. Quy tắc BR-CC-07 lập tức bật viền cam cảnh báo!"* |
| **5** | Bấm phân công thêm nhân viên Trần Văn Quý (Thu ngân) vào ô Ca Tối Thứ Tư $\rightarrow$ Bấm Lưu. | Ô ca cập nhật lại, **viền cam nét đứt biến mất**, huy hiệu cảnh báo ẩn đi. | *"Khi phân thêm Thu ngân, hệ thống xác nhận ca trực đủ vai trò và tắt cảnh báo."* |
| **6** | Cuộn xuống bảng Lịch sử chấm công, chọn ca bị quên check-out $\rightarrow$ Bấm **"Điều chỉnh"**. | Modal điều chỉnh xuất hiện: Nhập giờ ra 12:15, nhập lý do: *"Dọn quầy, camera xác nhận"*. | *"Quản lý duyệt sửa giờ công bắt buộc phải nhập lý do giải trình rõ ràng."* |
| **7** | Bấm **"Lưu điều chỉnh"**. | Bảng chấm công cập nhật giờ công quy đổi, hiển thị tag *"Đã điều chỉnh bởi: Quản lý"*. | *"Hệ thống lưu vết kiểm toán người sửa, giờ sửa minh bạch 100%."* |

---

### 3.3. Live Demo 3 (Nguyễn Văn Duy): Khấu Trừ Kho BOM Tự Động, Sổ Nhật Ký, WAC & KPI
- **Mục tiêu:** Chứng minh việc tự động khấu trừ kho BOM ngầm sau Demo 1, Sổ cái kiểm toán `lich_su_kho`, nhập kho tính giá vốn WAC xóa nhãn đỏ và Executive KPI Dashboard.
- **Tài khoản sử dụng:** `quanly / quanly`.
- **Bảng thao tác chi tiết:**

| Bước | Thao tác thực tế trên Web | Kết quả hiển thị trên màn hình | Lời thuyết minh đồng bộ |
| :---: | :--- | :--- | :--- |
| **1** | Mở `http://localhost:8080/kho.html` trên tài khoản `quanly`. | Mở Tab 1 "Tồn kho nguyên liệu". | *"Em truy cập phân hệ Kho trên quyền Quản lý."* |
| **2** | Quan sát tồn kho: Hạt Robusta, Sữa tươi tiệt trùng, Siro Đào Teisseire. | Robusta đã tự động trừ $25\text{ g}$, Sữa tươi trừ $180\text{ ml}$, Siro Đào trừ $25\text{ ml}$. | *"Kho đã tự trừ ngầm chính xác theo BOM của đơn Bàn 03 khi nãy!"* |
| **3** | Bấm chuyển sang **Tab 3: "Nhật ký biến động kho"**. | 3 dòng giao dịch **`XUAT_BAN_HANG`** xuất hiện, ghi rõ mã Hóa đơn Bàn 03, tên Thu ngân, tồn trước/tồn sau. | *"Bảng lich_su_kho lưu vết 3 dòng xuất bán gắn liền mã hóa đơn, khớp 100%."* |
| **4** | Chuyển lại Tab 1, tìm dòng **Bột Matcha Uji** (tồn $80\text{ g} \le$ ngưỡng $100\text{ g}$). | Nhãn cảnh báo màu đỏ nhấp nháy: **`SẮP HẾT HÀNG`**. | *"Matcha còn 80g dưới ngưỡng an toàn, hệ thống bật nhãn đỏ cảnh báo."* |
| **5** | Bấm nút **"Nhập kho"** tại dòng Matcha $\rightarrow$ bấm preset **"+500 g"**, giá 120 đ/g $\rightarrow$ Xác nhận. | Tồn kho tăng lên $580\text{ g}$, đơn giá WAC tính lại, nhãn đỏ đổi sang **nhãn xanh "Đủ hàng"**. | *"Nhập kho xong, giá vốn WAC tính lại tự động và cảnh báo đỏ biến mất."* |
| **6** | Tại dòng Siro Đào, bấm **"Kiểm kê"**, nhập số thực tế hụt $50\text{ ml}$, lý do: *"Vỡ nắp chai"*. | Ghi nhận giao dịch **`KIEM_KE_HAO_HUT`**, tự động tính giá trị tổn thất tài chính. | *"Kiểm kê hao hụt đổ vỡ được ghi nhận minh bạch vào báo cáo tài chính."* |
| **7** | Quan sát khối **Executive KPI Dashboard** phía trên cùng. | 6 thẻ KPI: Doanh thu thuần, COGS, Lợi nhuận gộp $79.0\%$, Tiền mặt vs QR, Top 5 món bán chạy nhất. | *"Dashboard tài chính trực quan, lọc linh hoạt theo Hôm nay, 7 ngày, Tháng này."* |

---
# PHẦN IV: BỘ CÂU HỎI & TRẢ LỜI PHẢN BIỆN CHUYÊN SÂU (DEFENSE Q&A)

### 15 Bài toán nghiệp vụ & giải pháp kỹ thuật cốt lõi

---

#### Câu hỏi 01: Tại sao nhóm phải xây dựng cơ chế Soft Reservation & Thắt cổ chai thay vì trừ kho ngay khi nhân viên click chọn món?
> **Giảng viên hỏi:** *"Tại sao hệ thống không trừ kho ngay khi nhân viên bấm thêm món vào bàn, mà lại dùng cơ chế Soft Reservation (Giữ chỗ mềm)?"* 
> **Sinh viên trả lời (Quý):** 
> *"Thưa Cô, trong thực tế phục vụ nhà hàng và quán cà phê, khách hàng gọi món nhưng hoàn toàn có thể đổi ý hủy món trước khi quầy Bar pha chế, hoặc khách đổi bàn. Nếu ta trừ kho vật lý ngay khi nhân viên vừa click chọn món: 
> 1. Thứ nhất: Kho sẽ bị trừ 'ảo', nếu khách hủy món thì hệ thống lại phải cộng bù kho, tạo ra hàng loạt giao dịch nhập xuất rác không có thật. 
> 2. Thứ hai: Nếu nhân viên mở bàn bấm thử món rồi để đó không phục vụ, kho bị trừ hết khiến khách bàn khác không thể gọi món. 
> Do đó, nhóm xây dựng cơ chế **Soft Reservation**: Hệ thống tính toán lượng nguyên liệu 'đang bị giữ' bởi các bàn đang mở đơn, kết hợp công thức thắt cổ chai $\min \lfloor (\text{Tồn} - \text{Giữ}) / \text{BOM} 
floor$ để tính ra số ly có thể phục vụ (`soLyCoTheLam`). Kho vật lý chỉ thực sự được khấu trừ nguyên tử khi Thu ngân bấm 'Xác nhận thanh toán & Đóng bàn'. Giải pháp này vừa phản ánh trung thực năng lực phục vụ, vừa bảo đảm tính toàn vẹn của kho!"*

---

#### Câu hỏi 02: Thuật toán chống Deadlock khi nhiều nhân viên cùng thanh toán các món dùng chung nguyên liệu hoạt động thế nào?
> **Giảng viên hỏi:** *"Tại sao việc nhiều thu ngân thanh toán cùng lúc lại có nguy cơ gây Deadlock và nhóm đã giải quyết bằng thuật toán gì?"* 
> **Sinh viên trả lời (Duy):** 
> *"Thưa Cô, trong giờ cao điểm F&B, hầu hết các món bán chạy như Cà phê sữa, Caffe Latte, Bạc xỉu đều dùng chung nguyên liệu Sữa tươi (ID: 3) và Cà phê Robusta (ID: 1). 
> Giả sử Thu ngân A thanh toán Đơn 1 cần trừ Sữa tươi trước rồi đến Robusta; Thu ngân B thanh toán Đơn 2 cần trừ Robusta trước rồi đến Sữa tươi. Khi chạy song song: Giao dịch A khóa Sữa tươi rồi chờ Robusta; Giao dịch B khóa Robusta rồi chờ Sữa tươi. Đây là điều kiện **Circular Wait (Chờ đợi vòng tròn)** gây ra Deadlock! Cả 2 giao dịch bị treo cứng và CSDL phải tự động hủy một trong hai đơn, làm tê liệt quầy thu ngân. 
> Để triệt tiêu Deadlock $100\%$, nhóm cài đặt thuật toán: 
> - Gom toàn bộ định mức nguyên liệu cần trừ vào cấu trúc **`TreeMap<Long, Double>`**. Do cơ chế cây đỏ đen của Java, danh sách mã nguyên liệu được **sắp xếp theo thứ tự ID tăng dần ($1 
\rightarrow 2 
\rightarrow 3$)**. 
> - Hệ thống thực thi khóa bi quan `SELECT ... FOR UPDATE` tuần tự theo đúng trật tự tăng dần này trong phạm vi `@Transactional(isolation = READ_COMMITTED)`. 
> Vì mọi giao dịch trong toàn hệ thống đều tuân thủ một trật tự xin khóa duy nhất từ bé đến lớn, điều kiện Circular Wait bị triệt tiêu hoàn toàn theo lý thuyết Coffman, bảo đảm hệ thống không bao giờ bị Deadlock!"*

---

#### Câu hỏi 03: Tại sao cần cơ chế In Bar Delta ($\Delta$) thay vì in lại toàn bộ danh sách món của bàn?
> **Giảng viên hỏi:** *"Cơ chế In Bar Delta giải quyết vấn đề gì trong vận hành quầy Bar?"* 
> **Sinh viên trả lời (Quý):** 
> *"Thưa Cô, trong thói quen đi cà phê của người Việt, khách ngồi lâu thường gọi thêm đồ uống (ví dụ: uống hết cà phê gọi thêm nước cam, hoặc bạn đến sau gọi thêm trà đào). 
> Nếu máy in Bar in lại toàn bộ hóa đơn của bàn (gồm cả 2 ly cũ và 1 ly mới), Barista trong lúc bận rộn sẽ không thể nhớ bàn đó đã pha món gì rồi, dẫn đến việc pha lại lần 2 hai ly cũ. Điều này gây lãng phí nguyên liệu và khách hàng bị tính tiền thừa. 
> Nhóm bổ sung trường `so_luong_da_in_bar` vào bảng `chi_tiet_hoa_don`. Mỗi lần in Bar, hệ thống chỉ lọc các món có $\text{so\_luong} > \text{so\_luong\_da\_in\_bar}$ và chỉ in phần chênh lệch $\Delta$. Sau khi in thành công, hệ thống cập nhật `so_luong_da_in_bar = so_luong`. Nhờ vậy, Barista chỉ nhận đúng lệnh cho những món mới gọi thêm!"*

---

#### Câu hỏi 04: Tại sao cần trạng thái bàn `CHO_THANH_TOAN` và khóa sửa đổi món?
> **Giảng viên hỏi:** *"Tại sao khi khách xin tính tiền, nhóm lại chuyển trạng thái sang `CHO_THANH_TOAN` và khóa chức năng thêm món của phục vụ?"* 
> **Sinh viên trả lời (Quý):** 
> *"Thưa Cô, đây là quy tắc kiểm soát chống gian lận và chống sai lệch chứng từ. Khi khách xin hóa đơn, nhân viên phục vụ hoặc thu ngân sẽ in Phiếu Tạm Tính mang ra bàn cho khách kiểm tra. 
> Nếu không khóa bàn, trong lúc khách đang xem phiếu tạm tính 100.000 đ, nhân viên phục vụ có thể vô tình hoặc cố ý thêm 1 ly nước vào hệ thống. Khách thanh toán 100.000 đ theo phiếu cầm trên tay, nhưng khi thu ngân bấm đóng bàn thì tổng tiền lại là 140.000 đ, gây ra xung đột cãi vã với khách hoặc làm lệch két tiền mặt. 
> Trạng thái `CHO_THANH_TOAN` khóa cứng toàn bộ quyền thêm/sửa/xóa món, bảo đảm con số trên Phiếu Tạm Tính và Hóa Đơn Cuối Cùng luôn trùng khớp $100\%$!"*

---

#### Câu hỏi 05: Công thức WAC (Weighted Average Cost) hoạt động thế nào và tại sao cần thiết hơn phương pháp FIFO?
> **Giảng viên hỏi:** *"Tại sao nhóm chọn tính giá vốn theo WAC mà không dùng FIFO?"* 
> **Sinh viên trả lời (Duy):** 
> *"Thưa Cô, phương pháp FIFO (Nhập trước xuất trước) yêu cầu phải theo dõi chi tiết từng lô hàng (Batch tracking) với ngày hết hạn và vị trí kho. Điều này quá phức tạp và không thực tế với mô hình quán cà phê vừa và nhỏ, nơi các hộp sữa tươi hay bao cà phê thường được đổ chung vào hộc chứa quầy Bar. 
> Phương pháp Giá vốn Bình quân Gia quyền liên hoàn (WAC) tính lại đơn giá vốn mỗi khi có phiếu nhập mới: 
> $$\text{WAC} = \frac{(\text{Tồn cũ} imes \text{Đơn giá cũ}) + (\text{Số lượng nhập} imes \text{Đơn giá nhập})}{\text{Tồn cũ} + \text{Số lượng nhập}}$$ 
> WAC phản ánh kịp thời sự biến động của giá thị trường mà không đòi hỏi thủ kho phải quản lý lô hàng rườm rà, giúp chủ quán luôn nắm được biên lợi nhuận gộp chính xác."*

---

#### Câu hỏi 06: Ý nghĩa của bảng `lich_su_kho` đối với việc kiểm toán chống thất thoát tài chính?
> **Giảng viên hỏi:** *"Tại sao nhóm phải tạo thêm bảng thứ 11 `lich_su_kho` mà không cộng trừ trực tiếp vào cột tồn kho của bảng `nguyen_lieu`?"* 
> **Sinh viên trả lời (Duy):** 
> *"Thưa Cô, nếu chỉ cập nhật đè lên cột `so_luong_ton`, chủ quán chỉ biết kho còn bao nhiêu, nhưng hoàn toàn mù tịt về nguyên nhân: Tại sao kho bị hụt? Hụt do bán đơn hàng nào? Hụt do pha hỏng hay do ai mang ra ngoài? 
> Bảng `lich_su_kho` chính là **Sổ cái kiểm toán (Audit Trail)** bất biến. Mọi biến động đều lưu rõ: Loại giao dịch (`NHAP_KHO`, `XUAT_BAN_HANG`, `KIEM_KE_HAO_HUT`), số lượng thay đổi, tồn trước, tồn sau, nhân viên thao tác và liên kết trực tiếp với mã `hoa_don_id`. Nhờ vậy, cuối ngày nếu kiểm kê thấy thiếu hụt nguyên liệu, Quản lý có thể đối soát chéo từng dòng giao dịch để truy cứu trách nhiệm."*

---

#### Câu hỏi 07: Cơ chế 7 quy tắc chấm công BR-CC-01..07 và tại sao phải dùng `java.time.Clock` bean thay vì `LocalDateTime.now()`?
> **Giảng viên hỏi:** *"Tại sao nhóm lại tạo `@Bean public Clock systemClock()` thay vì gọi trực tiếp `LocalDateTime.now()` trong mã nguồn?"* 
> **Sinh viên trả lời (Đạt):** 
> *"Thưa Cô, việc gọi trực tiếp `LocalDateTime.now()` phụ thuộc cứng vào đồng hồ phần cứng của máy chủ, khiến việc viết Unit Test và Integration Test kiểm thử tự động các quy tắc chấm công trở nên bất khả thi (ví dụ: ta không thể chờ 15 phút hay đợi đến 03:00 sáng để xem scheduler có chạy hay không). 
> Bằng cách tiêm Spring Bean `java.time.Clock`, trong môi trường Production hệ thống vẫn lấy giờ thực qua `Clock.systemDefaultZone()`. Nhưng trong các lớp kiểm thử tích hợp tự động (`NhanSuChamCongIntegrationTest.java`), nhóm có thể dùng `Clock.fixed()` hoặc dịch chuyển mốc thời gian tùy ý sang mốc 07:16 để kiểm thử đi trễ, dịch sang 03:01 để kiểm thử tiến trình `@Scheduled` tự động chốt vắng, đạt độ bao phủ kiểm thử tự động $100\%$!"*

---

#### Câu hỏi 08: Tại sao lưới phân ca cần cảnh báo `thieuThuNgan = true` viền cam nét đứt?
> **Giảng viên hỏi:** *"Quy tắc BR-CC-07 giải quyết bài toán vận hành gì trên Lưới phân ca tuần?"* 
> **Sinh viên trả lời (Đạt):** 
> *"Thưa Cô, trong ngành F&B, nhân viên phục vụ không được phân quyền thu tiền và đóng bàn để bảo vệ dòng tiền mặt. 
> Nếu người quản lý sơ suất xếp một ca trực chỉ toàn nhân viên Phục vụ mà không có Thu ngân hoặc Quản lý, thì khi khách thanh toán, quán sẽ rơi vào tình thế bế tắc: không ai có quyền in hóa đơn đóng bàn, dẫn đến việc phục vụ thu tiền ngoài luồng hoặc bàn bị treo. 
> Quy tắc `BR-CC-07` tự động kiểm tra ma trận phân ca: nếu ca trực thiếu vắng cả Thu ngân lẫn Quản lý, ô ca lập tức bật viền cam nét đứt và huy hiệu cảnh báo. Điều này ngăn chặn sai lầm của người quản lý ngay từ khâu lập lịch!"*

---

#### Câu hỏi 09: Tại sao hệ thống áp dụng Price Snapshot (`don_gia_luc_ban`) thay vì đọc từ bảng `mon_nuoc`?
> **Giảng viên hỏi:** *"Tại sao trong bảng `chi_tiet_hoa_don` lại phải lưu trường `don_gia_luc_ban` trong khi bảng `mon_nuoc` đã có `don_gia` rồi?"* 
> **Sinh viên trả lời (Đạt):** 
> *"Thưa Cô, đây là nguyên lý thiết kế **Price Snapshot (Chụp ảnh đơn giá)** bắt buộc trong kỹ nghệ phần mềm tài chính kế toán. Giá bán trong bảng `mon_nuoc` biến động theo thời gian (ví dụ: dịp lễ quán phụ thu hoặc điều chỉnh giá Cà phê muối từ 35k lên 40k). 
> Nếu `chi_tiet_hoa_don` không lưu đơn giá lúc bán mà chỉ nối khóa ngoại sang `mon_nuoc`, thì khi chạy báo cáo doanh thu tháng trước, toàn bộ các hóa đơn cũ sẽ bị nhân với giá mới 40k, làm sai lệch toàn bộ báo cáo doanh thu và thuế lịch sử. Lưu `don_gia_luc_ban` bảo đảm tính bất biến vĩnh viễn của chứng từ kế toán!"*

---

#### Câu hỏi 10: Tại sao phân quyền RBAC 3 cấp lại chặn Thu ngân áp chiết khấu giảm giá?
> **Giảng viên hỏi:** *"Tại sao chức năng Chiết khấu bắt buộc phải chặn tài khoản Thu ngân mà chỉ cho Quản lý thao tác?"* 
> **Sinh viên trả lời (Quý):** 
> *"Thưa Cô, đây là quy tắc kiểm soát nội bộ trọng yếu của ngành F&B nhằm triệt tiêu gian lận dòng tiền mặt. 
> Nếu Thu ngân có quyền tự nhập chiết khấu, họ có thể thu đủ 100.000 đ tiền mặt từ khách, nhưng trên phần mềm lại nhập chiết khấu $20\%$, nộp lại két 80.000 đ và bỏ túi riêng 20.000 đ chênh lệch. 
> Bằng cách khóa cứng ô chiết khấu ở cả tầng giao diện lẫn tầng REST API Backend đối với Thu ngân, quán bảo vệ $100\%$ doanh thu và buộc mọi ưu đãi giảm giá đều phải qua sự phê duyệt của Quản lý."*

---

#### Câu hỏi 11: Quy trình đối soát tiền mặt cuối ca được hệ thống hỗ trợ như thế nào?
> **Giảng viên hỏi:** *"Hệ thống kiểm soát việc bàn giao két tiền mặt giữa các ca trực ra sao?"* 
> **Sinh viên trả lời (Duy):** 
> *"Thưa Cô, hệ thống hỗ trợ công thức đối soát két tiền mặt chuẩn mực: 
> $$\text{Tiền mặt thực tế trong két} = \text{Tiền đầu ca} + \text{Doanh thu tiền mặt trong ca} - \text{Các khoản chi tiền mặt}$$ 
> Trên Dashboard KPI, hệ thống bóc tách rõ ràng giữa Doanh thu tiền mặt và Doanh thu chuyển khoản VietQR. Thu ngân kết thúc ca chỉ việc in bảng tổng kết ca, đếm tiền mặt thực tế trong két đối chiếu với số liệu trên phần mềm. Mọi khoản chênh lệch thừa/thiếu đều phải giải trình rõ ràng trước khi bàn giao cho ca sau."*

---

#### Câu hỏi 12: Vì sao nhóm chọn Kiến trúc Modular Monolith All-in-one Spring Boot thay vì Microservices?
> **Giảng viên hỏi:** *"Tại sao nhóm không chia hệ thống thành các Microservices riêng cho POS, Kho và Nhân sự?"* 
> **Sinh viên trả lời (Duy):** 
> *"Thưa Cô, đây là quyết định kiến trúc được cân nhắc kỹ lưỡng dựa trên bối cảnh và quy mô của quán F&B: 
> 1. **Tính nhất quán giao dịch (ACID):** Khi thanh toán hóa đơn, việc trừ kho BOM và cập nhật bàn cần tính toàn vẹn tuyệt đối. Nếu dùng Microservices, ta phải dùng Distributed Transactions (như Saga Pattern hay Two-Phase Commit), làm tăng độ trễ mạng và nguy cơ không nhất quán dữ liệu. 
> 2. **Hiệu năng và chi phí hạ tầng:** Quán chỉ cần triển khai trên một máy chủ cục bộ hoặc một Cloud VPS giá rẻ. Tải trang đạt dưới $50\text{ms}$ và không tốn chi phí vận hành cụm Kubernetes phức tạp. 
> Mô hình Modular Monolith của nhóm vẫn bảo đảm tính module hóa cao ở tầng mã nguồn (tách biệt rõ Controller, Service, Repository theo domain), sẵn sàng tách thành Microservices trong tương lai nếu quán mở rộng thành chuỗi lớn!"*

---

#### Câu hỏi 13: Cơ chế hoạt động của Scheduled Task lúc 03:00 sáng đối với ca quên check-out?
> **Giảng viên hỏi:** *"Nếu nhân viên về mà quên bấm Check-out thì hệ thống xử lý thế nào?"* 
> **Sinh viên trả lời (Đạt):** 
> *"Thưa Cô, nếu nhân viên quên check-out, ca làm việc sẽ bị treo ở trạng thái chưa hoàn thành, không thể tính được giờ công. 
> Nhóm cài đặt tiến trình nền `@Scheduled(cron = "0 0 3 * * ?")` chạy tự động lúc 03:00 sáng mỗi ngày: 
> - Quét các bản ghi phân ca trong ngày: Nếu nhân viên không hề check-in $\rightarrow$ Tự động đánh dấu `VANG` (Vắng mặt). 
> - Nếu nhân viên có check-in nhưng không có giờ check-out $\rightarrow$ Tự động đánh dấu `QUEN_CHECK_OUT` và tạm thời ghi nhận giờ công bằng 0. 
> Hôm sau, nhân viên phải làm đơn giải trình để Quản lý dùng chức năng 'Điều chỉnh chấm công' nhập giờ ra thực tế có kèm lý do và lưu vết kiểm toán."*

---

#### Câu hỏi 14: Quy trình xử lý khi kiểm kê kho phát hiện hao hụt (Shrinkage)?
> **Giảng viên hỏi:** *"Khi kiểm kê kho thực tế phát hiện thiếu hụt siro hay cà phê, hệ thống ghi nhận và hạch toán thế nào?"* 
> **Sinh viên trả lời (Duy):** 
> *"Thưa Cô, tại phân hệ Kho, Quản lý thực hiện kiểm kê định kỳ bằng cách nhập số lượng đo đạc thực tế: 
> - Hệ thống tự tính độ lệch: $\Delta = \text{Số thực tế} - \text{Tồn sổ sách}$. 
> - Nếu $\Delta < 0$ (hao hụt do đổ vỡ, bay hơi hoặc barista làm hỏng), hệ thống ghi nhận một bản ghi giao dịch loại **`KIEM_KE_HAO_HUT`** vào bảng `lich_su_kho`, bắt buộc nhập lý do (ví dụ: 'Vỡ nắp chai khi pha chế'). 
> - Hệ thống tự động nhân số lượng hao hụt với đơn giá vốn WAC để tính ra **Giá trị tổn thất tài chính** đưa vào báo cáo chi phí, đồng thời điều chỉnh tồn kho sổ sách về bằng đúng số thực tế để bắt đầu chu kỳ kinh doanh mới."*

---

#### Câu hỏi 15: Ranh giới hệ thống: Tại sao không làm xuất hóa đơn đỏ VAT điện tử và kết nối máy quẹt thẻ POS ngân hàng?
> **Giảng viên hỏi:** *"Tại sao nhóm đưa việc xuất hóa đơn VAT điện tử và tích hợp máy POS quẹt thẻ ngân hàng vào phần Out-of-Scope?"* 
> **Sinh viên trả lời (Duy):** 
> *"Thưa Cô, đây là quyết định phân định ranh giới kỹ thuật có chủ đích: 
> 1. **Về Hóa đơn điện tử VAT:** Việc tích hợp đòi hỏi chữ ký số token HSM doanh nghiệp, tài khoản kết nối cổng API của cơ quan thuế hoặc nhà cung cấp dịch vụ như VNPT/Viettel Invoice, có phát sinh chi phí duy trì tài khoản doanh nghiệp thực tế. 
> 2. **Về Máy quẹt thẻ ngân hàng (EDC):** Cần thiết bị phần cứng chuyên dụng của ngân hàng và chứng chỉ bảo mật PCI-DSS. 
> Trong phạm vi đồ án môn học Kỹ nghệ yêu cầu, nhóm đã tích hợp giải pháp **Mã VietQR động** – chuẩn thanh toán không tiền mặt phổ biến nhất Việt Nam hiện nay, cho phép quét mã chuyển khoản đúng từng đồng mà không cần đầu tư máy quẹt thẻ đắt tiền, vừa giải quyết trọn vẹn nhu cầu thanh toán vừa bảo đảm tính khả thi thực nghiệm!"*

---
# PHẦN V: CHECKLIST CHUẨN BỊ TRƯỚC GIỜ BÁO CÁO & PHONG THÁI PHẢN BIỆN

### 5.1. Checklist Kỹ thuật & Môi trường chạy thử (30 phút trước giờ G)

- [ ] **Môi trường Backend:** Máy chủ Spring Boot đang chạy ổn định trên cổng `8080` (kiểm tra log không có exception, H2/MySQL kết nối thành công).
- [ ] **Môi trường Database:** CSDL đã được nạp dữ liệu chuẩn bị: Bàn 03 đang trạng thái `TRONG`, Bột Matcha Uji tồn $80\text{ g}$ (dưới ngưỡng 100g để chuẩn bị bật báo đỏ), Ca Tối Thứ Tư được xếp sẵn chỉ có nhân viên Phục vụ (để kích hoạt cảnh báo cam `thieuThuNgan = true`).
- [ ] **Môi trường Trình duyệt Chrome:** Mở sẵn các tab:
 - Tab Slide: [slide.html](file:///d:/3.1.%20PTTKYC/CK/slide.html) (bấm phím `F` toàn màn hình).
 - Tab 1: `http://localhost:8080/index.html` $\rightarrow$ Đăng nhập & `banhang.html`.
 - Tab 2: `http://localhost:8080/nhansu.html`.
 - Tab 3: `http://localhost:8080/kho.html`.
- [ ] **Phím tắt điều khiển Slide:**
 - `Mũi tên Phải` / `Space`: Chuyển slide tiếp theo.
 - `Mũi tên Trái`: Quay lại slide trước.
 - `N`: Bật/Tắt ngăn kéo Lời thoại kịch bản (Speaker Notes).
 - `O`: Mở bảng Mục lục 16 slide để nhảy cóc nhanh khi trả lời câu hỏi phản biện.
 - `F`: Bật/Tắt chế độ Toàn màn hình.

---

### 5.2. Checklist Phong thái & Tác phong bảo vệ đồ án

1. **Trang phục & Tác phong:** Đồng phục chỉnh tề, phong thái tự tin, đứng thẳng hướng về phía Giảng viên và Hội đồng.
2. **Âm lượng & Tốc độ nói:** Nói to, rõ ràng, tốc độ vừa phải (~130 từ/phút), không nói lí nhí, không nhìn chằm chằm vào màn hình mà thường xuyên giao tiếp bằng mắt (Eye-contact) với Giảng viên.
3. **Phối hợp đồng đội:** Khi một bạn đang nói, hai bạn còn lại đứng lắng nghe nghiêm túc, sẵn sàng thao tác chuột hoặc chuyển slide hỗ trợ.
4. **Thái độ khi trả lời phản biện:**
 - Lắng nghe trọn vẹn câu hỏi của Giảng viên, ghi chép nhanh từ khóa vào sổ tay, tuyệt đối không ngắt lời Giảng viên.
 - Bắt đầu câu trả lời bằng: *"Dạ, em xin cảm ơn câu hỏi rất hay của Cô ạ..."*.
 - Trả lời đúng trọng tâm câu hỏi, kết hợp chặt chẽ giữa **Nghiệp vụ thực tế** và **Giải pháp kỹ thuật phần mềm**.

---

*Tài liệu Kịch bản thuyết trình và kiểm thử thực hành hoàn tất ngày 20 tháng 11 năm 2026 – Tập thể Nhóm sinh viên đề tài Sangrimar Cafe & Lounge.*
