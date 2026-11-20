# BÁO CÁO ĐỒ ÁN CUỐI KỲ: HỆ THỐNG QUẢN LÝ QUÁN CÀ PHÊ
### (COFFEE POS & INVENTORY MANAGEMENT SYSTEM – SANGRIMAR CAFE & LOUNGE)

---

> **TRƯỜNG ĐẠI HỌC TÔN ĐỨC THẮNG**  
> **KHOA CÔNG NGHỆ THÔNG TIN**  
> **BỘ MÔN HỆ THỐNG THÔNG TIN**  
>
> **HỌC PHẦN:** Phân tích và Thiết kế Yêu cầu (Requirements Analysis and Design)  
> **MÃ HỌC PHẦN:** `502050`  
> **GIẢNG VIÊN HƯỚNG DẪN:** TS. Hồ Thị Linh  
>
> **DANH SÁCH THÀNH VIÊN NHÓM THỰC HIỆN:**
> 1. **Nguyễn Văn Duy** (MSSV: `52400010` – Nhóm trưởng – Tỷ lệ đóng góp: **$34\%$**)  
>    *Nhiệm vụ:* Kiến trúc Dữ liệu, Thiết kế Định mức BOM 12 món & Phân hệ Kho/KPI, Sổ nhật ký biến động kho, Luồng trừ kho chống Deadlock `@Transactional`, Tổng hợp Báo cáo & Điều phối Q&A.
> 2. **Trần Văn Quý** (MSSV: `52400007` – Thành viên – Tỷ lệ đóng góp: **$33\%$**)  
>    *Nhiệm vụ:* Kỹ nghệ Yêu cầu (MoSCoW, FR, NFR), Biểu đồ Use Case tổng quát, Phân hệ Bán hàng POS, Sơ đồ bàn 3 màu viền, Thực đơn Soft Reservation & Bottleneck, Bộ 3 mẫu phiếu in nghiệp vụ `@media print`.
> 3. **Lê Tấn Đạt** (MSSV: `52400105` – Thành viên – Tỷ lệ đóng góp: **$33\%$**)  
>    *Nhiệm vụ:* Khảo sát Hiện trạng & 4 Điểm nghẽn, Phân hệ Quản lý Nhân sự, Lưới phân ca tuần cân bằng vai trò, 7 Quy tắc Chấm công chuẩn hóa (BR-CC-01 $\rightarrow$ BR-CC-07), DDL CSDL 11 bảng chuẩn 3NF & Bộ kiểm thử tích hợp 19 test cases.
>
> *Thành phố Hồ Chí Minh, Tháng 11 Năm 2026*

---

## LỜI CAM ĐOAN

Chúng tôi xin cam đoan đây là công trình nghiên cứu, phân tích, thiết kế và lập trình thực nghiệm do chính nhóm chúng tôi thực hiện dưới sự định hướng và hướng dẫn khoa học của Giảng viên hướng dẫn – **TS. Hồ Thị Linh**. 

Mọi số liệu khảo sát hiện trạng, các bảng phân rã sự kiện nghiệp vụ, các biểu đồ mô hình hóa phân tích thiết kế chuẩn UML 2.5 bằng PlantUML, cơ sở dữ liệu quan hệ MySQL chuẩn 3NF (11 bảng) và toàn bộ mã nguồn ứng dụng Web Java Spring Boot nguyên khối (*All-in-one Modular Monolith Spring Boot Application*) được trình bày trong quyển báo cáo này đều là sản phẩm độc lập và trung thực của nhóm. Các tài liệu tham khảo, khung công nghệ và thư viện mã nguồn mở đều được trích dẫn nguồn gốc rõ ràng theo đúng quy chuẩn học thuật của Khoa Công nghệ Thông tin – Trường Đại học Tôn Đức Thắng. Nhóm xin chịu hoàn toàn trách nhiệm trước Bộ môn và Khoa về tính trung thực của bản báo cáo này.

*Đại diện nhóm tác giả:*  
**Nguyễn Văn Duy** *(Trưởng nhóm)*

---

## LỜI CẢM ƠN

Để hoàn thành đề tài đồ án cuối kỳ học phần *Phân tích và Thiết kế Yêu cầu (Mã môn: 502050)*, nhóm chúng tôi đã nhận được sự quan tâm, giảng dạy và hướng dẫn tận tình từ quý Thầy, Cô thuộc Khoa Công nghệ Thông tin – Trường Đại học Tôn Đức Thắng.

Đặc biệt, nhóm xin gửi lời tri ân sâu sắc và chân thành nhất đến **TS. Hồ Thị Linh** – Giảng viên trực tiếp phụ trách giảng dạy học phần. Trong suốt quá trình học tập trên giảng đường cũng như các tuần triển khai đồ án, Cô đã truyền đạt những kiến thức nền tảng vô cùng vững chắc về kỹ nghệ yêu cầu, phương pháp thu thập và phân rã sự kiện nghiệp vụ, kỹ thuật mô hình hóa UML 2.5 chuẩn mực quốc tế, cũng như tư duy chuyển dịch từ logic nghiệp vụ thực tế sang kiến trúc phần mềm vận hành ổn định. Những lời nhận xét sắc bén, những buổi phản biện gợi mở của Cô chính là kim chỉ nam giúp nhóm nhận diện sâu sắc các bài toán tranh chấp tài nguyên đồng thời, hoàn thiện cơ chế giao dịch nguyên tử `@Transactional` kết hợp thuật toán sắp xếp khóa chống Deadlock trong quản trị kho, và xây dựng thành công ứng dụng Web Java chạy thực tế trên máy chủ.

Nhóm cũng xin chân thành cảm ơn các bạn sinh viên trong lớp đã cùng trao đổi học thuật, chia sẻ kinh nghiệm lập trình thực tế và đóng góp ý kiến quý báu trong các buổi thảo luận. Mặc dù nhóm đã dành trọn tâm huyết và nỗ lực để hoàn thiện báo cáo một cách toàn diện nhất, bài làm khó tránh khỏi những thiếu sót nhất định; nhóm rất mong tiếp tục nhận được những lời chỉ dẫn và đóng góp quý báu của Cô để dự án ngày càng hoàn thiện hơn.

*Kính chúc Cô luôn dồi dào sức khỏe, tràn đầy niềm vui và gặt hái thêm nhiều thành công rực rỡ trong sự nghiệp nghiên cứu và giảng dạy!*

---

## MỤC LỤC TỔNG QUAN

- [LỜI CAM ĐOAN](#lời-cam-đoan)
- [LỜI CẢM ƠN](#lời-cảm-ơn)
- [DANH MỤC HÌNH ẢNH MINH CHỨNG & SƠ ĐỒ](#danh-mục-hình-ảnh-minh-chứng--sơ-đồ)
- [DANH MỤC BẢNG BIỂU](#danh-mục-bảng-biểu)
- [DANH MỤC THUẬT NGỮ & TỪ VIẾT TẮT](#danh-mục-thuật-ngữ--từ-viết-tắt)
- [CHƯƠNG 1: TỔNG QUAN ĐỀ TÀI & PHẠM VI HỆ THỐNG](#chương-1-tổng-quan-đề-tài--phạm-vi-hệ-thống)
  - [1.1. Bối cảnh và tính cấp thiết của đề tài](#11-bối-cảnh-và-tính-cấp-thiết-của-đề-tài)
  - [1.2. Mục tiêu nghiên cứu và xây dựng phần mềm](#12-mục-tiêu-nghiên-cứu-và-xây-dựng-phần-mềm)
  - [1.3. Phạm vi hệ thống (In-Scope và Out-of-Scope)](#13-phạm-vi-hệ-thống-in-scope-và-out-of-scope)
  - [1.4. Bảng đối chiếu hiện trạng thủ công và giải pháp chuyển đổi số](#14-bảng-đối-chiếu-hiện-trạng-thủ-công-và-giải-pháp-chuyển-đổi-số)
- [CHƯƠNG 2: KHẢO SÁT HIỆN TRẠNG & THU THẬP YÊU CẦU](#chương-2-khảo-sát-hiện-trạng--thu-thập-yêu-cầu)
  - [2.1. Khảo sát quy trình nghiệp vụ vận hành thực tế tại quán cà phê](#21-khảo-sát-quy-trình-nghiệp-vụ-vận-hành-thực-tế-tại-quán-cà-phê)
  - [2.2. Nhận diện các bên liên quan (Stakeholders) và tác nhân (Actors)](#22-nhận-diện-các-bên-liên-quan-stakeholders-và-tác-nhân-actors)
  - [2.3. Phân tích 4 "điểm nghẽn" nghiệp vụ chí mạng cần giải quyết](#23-phân-tích-4-điểm-nghẽn-nghiệp-vụ-chí-mạng-cần-giải-quyết)
  - [2.4. Bảng phân rã sự kiện nghiệp vụ (Event Decomposition Table)](#24-bảng-phân-rã-sự-kiện-nghiệp-vụ-event-decomposition-table)
  - [2.5. Danh mục yêu cầu chức năng cốt lõi (FR-01 đến FR-15)](#25-danh-mục-yêu-cầu-chức-năng-cốt-lõi-fr-01-đến-fr-15)
  - [2.6. Danh mục yêu cầu phi chức năng (NFR-01 đến NFR-06) kèm chỉ số đo lường](#26-danh-mục-yêu-cầu-phi-chức-năng-nfr-01-đến-nfr-06-kèm-chỉ-số-đo-lường)
  - [2.7. Bảng phân loại và ưu tiên hóa yêu cầu theo phương pháp MoSCoW](#27-bảng-phân-loại-và-ưu-tiên-hóa-yêu-cầu-theo-phương-pháp-moscow)
- [CHƯƠNG 3: MÔ HÌNH HÓA USE CASE & PHÂN TÍCH NGHIỆP VỤ HỆ THỐNG](#chương-3-mô-hình-hóa-use-case--phân-tích-nghiệp-vụ-hệ-thống)
  - [3.1. Biểu đồ Use Case tổng quát toàn hệ thống](#31-biểu-đồ-use-case-tổng-quát-toàn-hệ-thống)
  - [3.2. Bảng phân rã Use Case theo các phân hệ nghiệp vụ](#32-bảng-phân-rã-use-case-theo-các-phân-hệ-nghiệp-vụ)
  - [3.3. Đặc tả chi tiết 7 Use Case trọng tâm (Fully-Dressed Template 9 trường)](#33-đặc-tả-chi-tiết-7-use-case-trọng-tâm-fully-dressed-template-9-trường)
  - [3.4. Biểu đồ Hoạt động phân làn (Activity Diagrams with Swimlanes)](#34-biểu-đồ-hoạt-động-phân-làn-activity-diagrams-with-swimlanes)
- [CHƯƠNG 4: THIẾT KẾ KIẾN TRÚC PHẦN MỀM & BIỂU ĐỒ LỚP CHI TIẾT](#chương-4-thiết-kế-kiến-trúc-phần-mềm--biểu-đồ-lớp-chi-tiết)
  - [4.1. Kiến trúc phân tầng 3 tầng (3-Tier Layered Architecture)](#41-kiến-trúc-phân-tầng-3-tầng-3-tier-layered-architecture)
  - [4.2. Biểu đồ Triển khai hệ thống vật lý (Deployment Diagram)](#42-biểu-đồ-triển-khai-hệ-thống-vật-lý-deployment-diagram)
  - [4.3. Cấu trúc đóng gói mã nguồn Java Spring Boot (Package Diagram)](#43-cấu-trúc-đóng-gói-mã-nguồn-java-spring-boot-package-diagram)
  - [4.4. Biểu đồ Lớp thiết kế chi tiết (Design Class Diagram - 3 Tầng)](#44-biểu-đồ-lớp-thiết-kế-chi-tiết-design-class-diagram---3-tầng)
- [CHƯƠNG 5: THIẾT KẾ CƠ SỞ DỮ LIỆU & BẢNG TỪ ĐIỂN DỮ LIỆU](#chương-5-thiết-kế-cơ-sở-dữ-liệu--bảng-từ-điển-dữ-liệu)
  - [5.1. Mô hình Thực thể Quan hệ CSDL (ERD - Crow's Foot Notation)](#51-mô-hình-thực-thể-quan-hệ-csdl-erd---crows-foot-notation)
  - [5.2. Bảng Từ điển Dữ liệu chi tiết cho 11 bảng CSDL (Data Dictionary)](#52-bảng-từ-điển-dữ-liệu-chi-tiết-cho-11-bảng-csdl-data-dictionary)
  - [5.3. Ràng buộc toàn vẹn dữ liệu và chuẩn hóa dạng chuẩn 3NF](#53-ràng-buộc-toàn-vẹn-dữ-liệu-và-chuẩn-hóa-dạng-chuẩn-3nf)
- [CHƯƠNG 6: THIẾT KẾ ĐỘNG HỌC HỆ THỐNG & CƠ CHẾ GIAO DỊCH NÂNG CAO](#chương-6-thiết-kế-động-học-hệ-thống--cơ-chế-giao-dịch-nâng-cao)
  - [6.1. Biểu đồ Tuần tự tương tác đa tầng (Multi-tier Sequence Diagrams)](#61-biểu-đồ-tuần-tự-tương-tác-đa-tầng-multi-tier-sequence-diagrams)
  - [6.2. Biểu đồ Máy trạng thái đối tượng (State Machine Diagrams)](#62-biểu-đồ-máy-trạng-thái-đối-tượng-state-machine-diagrams)
  - [6.3. Các giải pháp kỹ thuật nâng cao giải quyết tranh chấp đồng thời](#63-các-giải-pháp-kỹ-thuật-nâng-cao-giải-quyết-tranh-chấp-đồng-thời)
- [CHƯƠNG 7: THIẾT KẾ GIAO DIỆN & KẾT QUẢ KIỂM THỬ THỰC NGHIỆM](#chương-7-thiết-kế-giao-diện--kết-quả-kiểm-thử-thực-nghiệm)
  - [7.1. Sơ đồ luồng điều hướng màn hình (UI Navigation Flow)](#71-sơ-đồ-luồng-điều-hướng-màn-hình-ui-navigation-flow)
  - [7.2. Bộ minh chứng giao diện nguyên mẫu thực nghiệm (Prototype UI Walkthrough)](#72-bộ-minh-chứng-giao-diện-nguyên-mẫu-thực-nghiệm-prototype-ui-walkthrough)
  - [7.3. Kế hoạch và kết quả kiểm thử tích hợp tự động Backend (19/19 Test Cases Passed)](#73-kế-hoạch-và-kết-quả-kiểm-thử-tích-hợp-tự-động-backend-1919-test-cases-passed)
  - [7.4. Kết quả kiểm thử hộp đen E2E trên giao diện Web (16/16 Scenarios Passed)](#74-kết-quả-kiểm-thử-hộp-đen-e2e-trên-giao-diện-web-1616-scenarios-passed)
  - [7.5. Bảng tổng hợp lỗi phát hiện và lịch sử khắc phục triệt để (BUG-01 đến BUG-06)](#75-bảng-tổng-hợp-lỗi-phát-hiện-và-lịch-sử-khắc-phục-triệt-để-bug-01-đến-bug-06)
- [CHƯƠNG 8: KẾT LUẬN & HƯỚNG PHÁT TRIỂN](#chương-8-kết-luận--hướng-phát-triển)
  - [8.1. Đánh giá mức độ hoàn thành so với mục tiêu đề ra](#81-đánh-giá-mức-độ-hoàn-thành-so-với-mục-tiêu-đề-ra)
  - [8.2. Ưu điểm nổi bật của hệ thống](#82-ưu-điểm-nổi-bật-của-hệ-thống)
  - [8.3. Hạn chế còn tồn tại](#83-hạn-chế-còn-tồn-tại)
  - [8.4. Chiến lược chuyển giao và triển khai phần mềm (Direct Cutover Strategy)](#84-chiến-lược-chuyển-giao-và-triển-khai-phần-mềm-direct-cutover-strategy)
  - [8.5. Hướng phát triển và mở rộng trong tương lai](#85-hướng-phát-triển-và-mở-rộng-trong-tương-lai)
- [CHƯƠNG 9: PHỤ LỤC & TÀI LIỆU THAM KHẢO](#chương-9-phụ-lục--tài-liệu-tham-khảo)
  - [9.1. Bảng phân công nhiệm vụ và tỷ lệ đóng góp của từng thành viên](#91-bảng-phân-công-nhiệm-vụ-và-tỷ-lệ-đóng-góp-của-từng-thành-viên)
  - [9.2. Kịch bản Demo trực tiếp (Live Demo Script) phục vụ bảo vệ đồ án](#92-kịch-bản-demo-trực-tiếp-live-demo-script-phục-vụ-bảo-vệ-đồ-án)
  - [9.3. Danh mục tài liệu tham khảo](#93-danh-mục-tài-liệu-tham-khảo)

---

## DANH MỤC HÌNH ẢNH MINH CHỨNG & SƠ ĐỒ

| Ký hiệu hình | Tên gọi sơ đồ / Hình ảnh minh chứng giao diện | Đường dẫn tệp tin thực tế |
| :---: | :--- | :--- |
| **Hình 3.1** | Biểu đồ Use Case tổng quát toàn hệ thống (UML 2.5) | [diagram/UseCaseTongQuat.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/UseCaseTongQuat.svg) |
| **Hình 3.2** | Biểu đồ Hoạt động phân làn: Luồng trừ kho tự động khi thanh toán | [diagram/Activity_TruKho.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_TruKho.svg) |
| **Hình 3.3** | Biểu đồ Hoạt động phân làn: Quy trình Bán hàng POS và Xuất hóa đơn | [diagram/Activity_POS.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_POS.svg) |
| **Hình 3.4** | Biểu đồ Hoạt động phân làn: Quy trình Chấm công ca làm việc nhân sự | [diagram/Activity_ChamCong.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_ChamCong.svg) |
| **Hình 4.1** | Biểu đồ Kiến trúc phân tầng và thành phần hệ thống (3-Tier Component) | [diagram/KienTrucTongThe.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/KienTrucTongThe.svg) |
| **Hình 4.2** | Biểu đồ Triển khai hệ thống trên môi trường máy chủ Localhost | [diagram/TrienKhai.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/TrienKhai.svg) |
| **Hình 4.3** | Biểu đồ Cấu trúc đóng gói mã nguồn Java Spring Boot (Package Diagram) | [diagram/Package_SpringBoot.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Package_SpringBoot.svg) |
| **Hình 4.4** | Biểu đồ Lớp thiết kế chi tiết 3 tầng (Design Class Diagram) | [diagram/ClassDiagram.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/ClassDiagram.svg) |
| **Hình 5.1** | Mô hình Thực thể Quan hệ CSDL 11 thực thể (ERD Crow's Foot Notation) | [diagram/ERD_DuLieu.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/ERD_DuLieu.svg) |
| **Hình 6.1** | Biểu đồ Tuần tự đa tầng luồng trừ kho tự động `@Transactional` chống Deadlock | [diagram/Sequence_TruKho.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_TruKho.svg) |
| **Hình 6.2** | Biểu đồ Tuần tự đa tầng 4 giai đoạn Bán hàng POS và In bill | [diagram/Sequence_POS.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_POS.svg) |
| **Hình 6.3** | Biểu đồ Tuần tự đa tầng quy trình Check-in và Chốt ca tự động Scheduler | [diagram/Sequence_ChamCong.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_ChamCong.svg) |
| **Hình 6.4** | Biểu đồ Máy trạng thái vòng đời Bàn, Hóa đơn và Chấm công (State Machine) | [diagram/StateMachine_TrangThai.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/StateMachine_TrangThai.svg) |
| **Hình 7.1** | Sơ đồ luồng điều hướng màn hình (UI Navigation Flow) | [02_SourceCode_WebJava_Prototype/BanGiao_UML/UI_Navigation_Flow.svg](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/BanGiao_UML/UI_Navigation_Flow.svg) |
| **Hình 7.2** | Giao diện Đăng nhập hệ thống (3 nút Quick-fill, chống brute-force) | [screenshots/01_dang_nhap.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/01_dang_nhap.png) |
| **Hình 7.3** | Màn hình Bán hàng POS & Sơ đồ bàn 3 màu viền thời gian thực | [screenshots/02_pos_phuc_vu_so_do_ban.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/02_pos_phuc_vu_so_do_ban.png) |
| **Hình 7.4** | Mở lại đơn hàng đang phục vụ của Bàn 03 (`DANG_DUNG_NUOC`) | [screenshots/03_table3_occupied_loaded.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/03_table3_occupied_loaded.png) |
| **Hình 7.5** | Tạo đơn hàng mới tại Bàn 02, kiểm tra số ly có thể làm & ghi chú | [screenshots/04_cart_table2.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/04_cart_table2.png) |
| **Hình 7.6** | Lưu đơn Bàn 02 thành công, bàn chuyển viền xanh lá kèm phiếu Bar | [screenshots/05_order_saved_table2.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/05_order_saved_table2.png) |
| **Hình 7.7** | Chuyển bàn sang Chờ thanh toán (viền vàng) & In Phiếu Tạm Tính | [screenshots/06_payment_modal_validation.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/06_payment_modal_validation.png) |
| **Hình 7.8** | Hóa đơn thanh toán chính thức hoàn tất (Tiền thối, đóng bàn về trống) | [screenshots/07_receipt_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/07_receipt_modal.png) |
| **Hình 7.9** | Màn hình Quản trị Kho: Tồn kho 13 nguyên liệu, 6 thẻ KPI & Nhãn đỏ cảnh báo | [screenshots/08_kho_alert_kpi.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/08_kho_alert_kpi.png) |
| **Hình 7.10** | Hộp thoại Quản lý Định mức BOM 12 món & Biên lợi nhuận gộp | [screenshots/09_recipe_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/09_recipe_modal.png) |
| **Hình 7.11** | Hộp thoại Nhập kho cập nhật Giá vốn Bình quân gia quyền (WAC) | [screenshots/10_nhapkho_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/10_nhapkho_modal.png) |
| **Hình 7.12** | Hộp thoại Kiểm kê cân bằng kho & Sổ nhật ký biến động kho (`lich_su_kho`) | [screenshots/11_kho_restocked.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/11_kho_restocked.png) |
| **Hình 7.13** | Màn hình Nhân sự Chế độ Cá nhân: Đồng hồ thời gian thực & Chấm công | [screenshots/12_nhansu_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/12_nhansu_screen.png) |
| **Hình 7.14** | Ghi nhận Check-in ca làm việc đối chiếu giờ vào gắn nhãn Đúng giờ/Đi trễ | [screenshots/13_attendance_checked_in.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/13_attendance_checked_in.png) |
| **Hình 7.15** | Màn hình Quản trị: Lưới phân ca tuần cảnh báo cờ thiếu Thu ngân | [screenshots/14_add_employee_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/14_add_employee_modal.png) |
| **Hình 7.16** | Modal Quản lý Điều chỉnh Chấm công ngoại lệ có lưu vết kiểm toán | [screenshots/15_manager_discount_receipt.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/15_manager_discount_receipt.png) |

---

## DANH MỤC BẢNG BIỂU

- **Bảng 1.1:** Đối chiếu hiện trạng thủ công (*As-Is*) và giải pháp hệ thống số hóa (*To-Be*)
- **Bảng 2.1:** Bảng phân rã sự kiện nghiệp vụ hệ thống (Event Decomposition Table)
- **Bảng 2.2:** Ma trận phân loại và ưu tiên hóa yêu cầu chức năng theo phương pháp MoSCoW
- **Bảng 2.3:** Bảng danh mục yêu cầu phi chức năng (NFR) kèm thang đo lường định lượng
- **Bảng 3.1:** Phân loại và tổng hợp danh mục 30 Use Case theo từng phân hệ
- **Bảng 5.1:** Từ điển dữ liệu chi tiết Bảng `nhan_vien` (Employee Table)
- **Bảng 5.2:** Từ điển dữ liệu chi tiết Bảng `ca_lam` (Work Shift Table)
- **Bảng 5.3:** Từ điển dữ liệu chi tiết Bảng `phan_ca` (Shift Assignment Schedule Table)
- **Bảng 5.4:** Từ điển dữ liệu chi tiết Bảng `cham_cong` (Attendance Record Table – Chuẩn 16 cột)
- **Bảng 5.5:** Từ điển dữ liệu chi tiết Bảng `ban` (Dining Table)
- **Bảng 5.6:** Từ điển dữ liệu chi tiết Bảng `mon_nuoc` (Beverage Menu Table)
- **Bảng 5.7:** Từ điển dữ liệu chi tiết Bảng `nguyen_lieu` (Raw Material Inventory Table)
- **Bảng 5.8:** Từ điển dữ liệu chi tiết Bảng `cong_thuc` (Bill of Materials Recipe Table)
- **Bảng 5.9:** Từ điển dữ liệu chi tiết Bảng `hoa_don` (Order Receipt Table)
- **Bảng 5.10:** Từ điển dữ liệu chi tiết Bảng `chi_tiet_hoa_don` (Order Item Line Table)
- **Bảng 5.11:** Từ điển dữ liệu chi tiết Bảng `lich_su_kho` (Inventory Transaction Audit Trail Table)
- **Bảng 7.1:** Kết quả kiểm thử tích hợp tự động Backend (19/19 Test Cases Passed 100%)
- **Bảng 7.2:** Kết quả kiểm thử hộp đen liên thông trên giao diện Web (16/16 E2E Scenarios Passed)
- **Bảng 7.3:** Bảng tổng hợp lỗi, phân loại mức độ và trạng thái khắc phục triệt để (BUG-01 đến BUG-06)
- **Bảng 9.1:** Bảng phân công nhiệm vụ và tỷ lệ đóng góp của từng thành viên trong nhóm

---

## DANH MỤC THUẬT NGỮ & TỪ VIẾT TẮT

* **POS (Point of Sale):** Điểm bán hàng / Phần mềm bán lẻ và quản lý giao dịch phục vụ tại bàn và tại quầy thu ngân.
* **BOM (Bill of Materials):** Bảng định mức kỹ thuật công thức cấu thành sản phẩm đồ uống từ các nguyên vật liệu hao phí.
* **ACID (Atomicity, Consistency, Isolation, Durability):** Bốn thuộc tính cốt lõi đảm bảo độ tin cậy và toàn vẹn của giao dịch CSDL.
* **RBAC (Role-Based Access Control):** Cơ chế kiểm soát quyền truy cập tài nguyên dựa trên vai trò người dùng (`QUAN_LY`, `THU_NGAN`, `PHUC_VU`).
* **WAC (Weighted Average Cost):** Phương pháp tính giá vốn hàng tồn kho theo bình quân gia quyền liên hoàn sau mỗi lần nhập hàng.
* **Soft Reservation:** Cơ chế giữ chỗ nguyên liệu ảo dựa trên các đơn hàng đang phục vụ nhằm xác định chính xác số ly có thể pha chế tiếp theo.
* **Bottleneck Ingredient:** Nguyên liệu thắt nút cổ chai – thành phần nguyên liệu cạn kiệt sớm nhất quyết định giới hạn phục vụ của món đồ uống.
* **ORM (Object-Relational Mapping):** Kỹ thuật ánh xạ giữa các bảng CSDL quan hệ và các lớp thực thể trong lập trình hướng đối tượng.
* **JPA (Java Persistence API):** Chuẩn giao diện quản lý dữ liệu đối tượng bền vững trong hệ sinh thái Java.
* **DTO (Data Transfer Object):** Đối tượng truyền tải dữ liệu giữa tầng Presentation và tầng Application Service.
* **Idempotency Key:** Khóa định danh duy nhất (UUID) bảo vệ hệ thống không thực hiện lặp lại một hành động khi nhận nhiều request do mạng lag.
* **Lost Update:** Sự cố mất mát dữ liệu khi hai giao dịch cùng đọc và ghi đè đồng thời lên cùng một bản ghi dữ liệu.

---

## CHƯƠNG 1: TỔNG QUAN ĐỀ TÀI & PHẠM VI HỆ THỐNG

### 1.1. Bối cảnh và tính cấp thiết của đề tài
Trong sự phát triển bùng nổ của ngành dịch vụ ăn uống (Food and Beverage – F&B) tại Việt Nam, mô hình quán cà phê phục vụ tại bàn kết hợp không gian thư giãn (*Specialty Coffee & Lounge*) đang chiếm lĩnh thị phần lớn. Tuy nhiên, qua khảo sát thực tế tại các quán quy mô vừa và nhỏ (từ 10 đến 25 bàn), phần lớn các cơ sở vẫn đang dựa dẫm vào các thao tác thủ công hoặc các phần mềm bán hàng rời rạc bộc lộ 4 khiếm khuyết nghiêm trọng:
1. Nhân viên phục vụ ghi chép order bằng giấy viết tay hoặc sổ tay mini. Vào khung giờ cao điểm, việc mang giấy từ bàn đến quầy bar và quầy thu ngân thường xuyên gây sót món, nhầm bàn, hoặc Bar hết nguyên liệu mà phục vụ vẫn nhận đơn của khách dẫn đến khiếu nại.
2. Thu ngân tính tiền và thối tiền thủ công; việc áp dụng giảm giá, chiết khấu cho khách quen hoặc quản lý hình thức chuyển khoản quét mã cá nhân không được kiểm soát chặt chẽ, dễ phát sinh thất thoát tài chính ngầm.
3. Người pha chế (Barista) pha chế đồ uống theo thói quen định lượng cảm tính. Cuối ngày, chủ quán kiểm kê nguyên liệu chỉ đếm số lượng lon sữa hoặc gói cà phê còn thừa mà hoàn toàn không thể đối chiếu xem lượng hao hụt thực tế có khớp với số ly đã bán hay do bị đổ vỡ, hư hỏng hoặc thất thoát nội bộ.
4. Lực lượng nhân sự quán cà phê phần lớn là sinh viên làm việc bán thời gian (part-time). Lịch học thay đổi liên tục, việc đổi ca diễn ra thường xuyên khiến bảng phân ca giấy bị gạch xóa nham nhở, dẫn đến việc thiếu nhân sự trong giờ cao điểm nhưng thừa người lúc vắng khách. Việc tính công cuối tháng xảy ra tranh cãi do thiếu căn cứ chấm công minh bạch.

Từ thực trạng trên, việc xây dựng một **"Hệ Thống Quản Lý Quán Cà Phê – Sangrimar Cafe & Lounge"** toàn diện, ứng dụng công nghệ phần mềm hiện đại nhằm số hóa đồng bộ và tự động hóa toàn bộ luồng nghiệp vụ từ **Gọi món tại bàn $\rightarrow$ Đẩy lệnh in quầy Bar $\rightarrow$ Thu ngân xuất hóa đơn $\rightarrow$ Tự động trừ kho theo định mức BOM $\rightarrow$ Cảnh báo tồn đỏ & kiểm kê $\rightarrow$ Chấm công nhân sự $\rightarrow$ Báo cáo KPI tài chính** là một yêu cầu cấp thiết, mang tính ứng dụng thực tiễn cao.

### 1.2. Mục tiêu nghiên cứu và xây dựng phần mềm
Đề tài hướng tới 4 mục tiêu trọng tâm:
* **Mục tiêu 1 – Chuẩn hóa nghiệp vụ Bán hàng POS & Phục vụ tại bàn:** Xây dựng sơ đồ bàn trực quan ($10$ bàn); hỗ trợ quy trình phục vụ tại bàn khép kín (Mở bàn, In phiếu pha chế Bar Ticket, Gọi thêm món tính theo độ lệch Delta, Yêu cầu thanh toán chuyển màu bàn, In Phiếu Tạm Tính Proforma Bill, Xác nhận thanh toán & Đóng bàn trả về trống).
* **Mục tiêu 2 – Tự động hóa quản trị Kho & Định mức công thức (BOM):** Thiết lập cơ chế bóc tách định mức nguyên liệu tiêu hao cho 12 món đồ uống từ 13 nguyên liệu chuẩn. Khi hóa đơn thanh toán thành công, hệ thống tự động thực thi trừ kho ngầm trong giao dịch nguyên tử `@Transactional` với thuật toán sắp xếp khóa chống Deadlock. Tự động lưu sổ nhật ký biến động kho (`lich_su_kho`), tính giá vốn Bình quân gia quyền (WAC) và hỗ trợ kiểm kê hao hụt cuối ngày.
* **Mục tiêu 3 – Số hóa quản trị Nhân sự & Chấm công công bằng:** Quản lý hồ sơ nhân viên theo 3 vai trò chuẩn; quản lý lịch phân ca tuần có cờ cảnh báo cân bằng vai trò (bắt buộc có Thu ngân/Quản lý); hiện thực hóa 7 quy tắc chấm công chuẩn hóa (BR-CC-01 $\rightarrow$ BR-CC-07) với `java.time.Clock` và tác vụ tự động chốt ca quá hạn (`ChotCaTuDongScheduler`).
* **Mục tiêu 4 – Hiện thực hóa nguyên mẫu phần mềm chất lượng cao:** Ứng dụng kiến trúc **3-Tier All-in-one Modular Monolith Spring Boot Application**, tuân thủ nguyên lý hướng đối tượng, tích hợp cơ sở dữ liệu quan hệ MySQL chuẩn 3NF (11 bảng), xử lý triệt để bài toán tranh chấp tài nguyên đồng thời và vượt qua 100% các kịch bản kiểm thử tích hợp tự động (19/19 Test Cases Passed).

### 1.3. Phạm vi hệ thống (In-Scope và Out-of-Scope)

#### 1.3.1. Phạm vi thực hiện (In-Scope)
* **Phân hệ Bán hàng POS:** Quản lý sơ đồ 10 bàn với 3 màu viền thời gian thực (Xanh dương: Trống, Xanh lá: Đang dùng nước, Vàng hổ phách: Chờ thanh toán); thực đơn thông minh Soft Reservation & Bottleneck che giấu tồn kho gốc; quy trình phục vụ tại bàn chuẩn SOP; bộ 3 mẫu phiếu in nghiệp vụ `@media print`; phân quyền thanh toán và chiết khấu.
* **Phân hệ Quản lý Kho, BOM & KPI (Trọng tâm Duy):** Quản lý danh mục 13 nguyên vật liệu; bảng định mức công thức pha chế BOM 12 món; thuật toán trừ kho tự động nguyên tử chống Deadlock; sổ nhật ký biến động kho `lich_su_kho`; nhập kho cập nhật giá vốn WAC; kiểm kê cân bằng kho ghi nhận hao hụt; bảng điều khiển KPI Dashboard 4 kỳ (`HOM_NAY`, `BAY_NGAY`, `THANG_NAY`, `TAT_CA`) và Top 5 món bán chạy.
* **Phân hệ Quản lý Nhân sự & Chấm công (Trọng tâm Đạt):** Quản lý hồ sơ nhân sự và phân quyền 3 vai trò (`QUAN_LY`, `THU_NGAN`, `PHUC_VU`); cấu hình 3 ca làm việc (Sáng, Chiều, Tối); lưới phân ca tuần kèm cảnh báo thiếu thu ngân; chấm công thời gian thực với 7 quy tắc BR-CC; chốt ca tự động quá hạn qua scheduler; quản lý điều chỉnh công ngoại lệ có lưu vết kiểm toán.
* **Phân hệ Xác thực & An ninh Hệ thống:** Đăng nhập, bảo mật phiên làm việc qua `HttpSession`, chống brute-force (khóa 60s sau 5 lần sai - HTTP 429), bảo vệ chống tự khóa quản trị viên cuối cùng.

#### 1.3.2. Ngoại phạm vi (Out-of-Scope)
* Không xây dựng phân hệ hạch toán kế toán chuyên sâu (không trích khấu hao tài sản cố định, không lập báo cáo tài chính kê khai thuế VAT cho cơ quan nhà nước).
* Không tích hợp trực tiếp với cổng thanh toán thẻ ngân hàng thực tế (Payment Gateway qua Webhook của VNPay, Momo) mà mô phỏng thanh toán tiền mặt và xác nhận chuyển khoản thủ công qua mã VietQR động.
* Không phát triển ứng dụng di động bản địa riêng (Native Mobile App trên iOS/Android); toàn bộ hệ thống vận hành tập trung trên nền tảng Web Responsive chạy mượt mà trên trình duyệt.

### 1.4. Bảng đối chiếu hiện trạng thủ công và giải pháp chuyển đổi số

**Bảng 1.1: Đối chiếu hiện trạng thủ công (As-Is) và giải pháp hệ thống số hóa (To-Be)**

| Tiêu chí so sánh | Quy trình thủ công hiện tại (*As-Is*) | Hệ thống Sangrimar Cafe & Lounge (*To-Be*) | Lợi ích nghiệp vụ đạt được |
| :--- | :--- | :--- | :--- |
| **Ghi nhận đơn hàng (POS)** | Phục vụ ghi giấy, dễ thất lạc, chữ viết tay khó đọc; thu ngân bấm máy tính tay. | Chọn món trực quan trên màn hình POS; bàn đổi 3 màu viền động; thực đơn hiển thị số ly có thể pha. | Rút ngắn $75\%$ thời gian tạo đơn; loại bỏ $100\%$ tình trạng nhầm bàn hoặc bán khống món đã hết. |
| **Truyền lệnh quầy Bar** | Phục vụ xé giấy chạy vào quầy bar; gọi thêm món ghi đè lên giấy cũ gây nhầm lẫn. | Tự động sinh phiếu pha chế Bar Ticket; gọi thêm món chỉ in đúng số lượng chênh lệch $\Delta$. | Quầy bar pha chế chính xác $100\%$, không bị làm trùng món hay sót món gọi thêm. |
| **Đối soát & Thanh toán** | Báo miệng tổng tiền; thu ngân tự ý sửa giảm giá hoặc hủy hóa đơn để trục lợi. | In Phiếu Tạm Tính cho khách kiểm tra trước; chỉ Quản lý mới được áp dụng chiết khấu; phân quyền RBAC chặt. | Triệt tiêu thất thoát tài chính; đối soát minh bạch $100\%$ tiền mặt và chuyển khoản VietQR. |
| **Quản trị tồn kho & BOM** | Đếm thủ công cuối ngày theo cảm tính; không rõ nguyên nhân hao hụt. | Bóc tách định mức BOM 12 món; tự động trừ kho ngầm chống Deadlock; lưu sổ nhật ký `lich_su_kho`. | Kiểm soát chính xác lượng nguyên liệu tiêu hao; đối chiếu thất thoát kho tức thì theo thời gian thực. |
| **Giá vốn & Hao hụt kho** | Lấy giá mua gần nhất hoặc ước lượng; hao hụt không được hạch toán cụ thể. | Tính giá vốn Bình quân gia quyền (WAC); kiểm kê kho bắt buộc nhập lý do và tính tổn thất tài chính. | Báo cáo chính xác biên lợi nhuận gộp từng món và minh bạch hóa thất thoát do đổ vỡ/hư hỏng. |
| **Chấm công & Phân ca** | Ký sổ tay hoặc ghi bảng phấn; đổi ca tùy tiện; tính giờ công cuối tháng cãi vã. | Nhân viên tự Check-in theo cửa sổ mở ca; hệ thống đối chiếu giờ thực tế; chốt ca quá hạn tự động. | Minh bạch $100\%$ dữ liệu chấm công; tự động tính giờ công quy đổi hệ số ca đêm chuẩn xác. |

---

## CHƯƠNG 2: KHẢO SÁT HIỆN TRẠNG & THU THẬP YÊU CẦU

### 2.1. Khảo sát quy trình nghiệp vụ vận hành thực tế tại quán cà phê
Để có số liệu thực tế phục vụ phân tích, nhóm đã tiến hành khảo sát trực tiếp tại các quán cà phê quy mô vừa và nhỏ tại khu vực Quận 7, TP. Hồ Chí Minh. Quy trình hoạt động hàng ngày bao gồm 4 chuỗi nghiệp vụ chính:
1. **Đầu ca làm việc (06:30 - 07:00):** Nhân viên phục vụ và pha chế đến quán, ký tên vào sổ điểm danh. Nhân viên kiểm tra sơ bộ bàn ghế, bật máy pha cà phê và kiểm tra các nguyên liệu cơ bản như sữa tươi, đá viên.
2. **Khung giờ vận hành bán hàng (07:00 - 23:00):**
   * Khách hàng vào quán, nhân viên phục vụ đón tiếp và dẫn vào bàn.
   * Nhân viên phục vụ đưa menu giấy, ghi lại các món khách chọn và các ghi chú đặc biệt (ít ngọt, không đá).
   * Phiếu order giấy được chuyển vào quầy Bar để pha chế. Khi khách gọi thêm món, nhân viên gạch xóa thêm trên phiếu.
   * Khi khách yêu cầu thanh toán, thu ngân tìm phiếu của bàn đó, tính tiền, nhận tiền mặt hoặc cho khách quét mã tài khoản cá nhân của thu ngân rồi thối lại tiền thừa.
3. **Nghiệp vụ nhập và kiểm kê nguyên liệu:** Định kỳ 2 - 3 ngày một lần, nhà cung cấp giao cà phê hạt, sữa đặc, trà, siro. Quản lý nhận hàng, ký phiếu giao nhận và xếp vào kho. Số liệu nhập chỉ được ghi chép vào một cuốn sổ riêng.
4. **Kết ca và cuối ngày (23:00 - 23:30):** Thu ngân kiểm đếm tiền mặt trong két đối chiếu với tổng các liên order đã lưu; người pha chế kiểm đếm sơ lược các nguyên liệu còn tồn trên kệ; nhân viên ký tên ra về.

### 2.2. Nhận diện các bên liên quan (Stakeholders) và tác nhân (Actors)
Hệ thống xác định 4 nhóm đối tượng liên quan:
* **Khách hàng (Customer):** Đối tượng thụ hưởng dịch vụ; mong muốn được phục vụ nhanh chóng, chính xác theo đúng khẩu vị (ghi chú pha chế), nhận Phiếu Tạm Tính kiểm tra rõ ràng trước khi trả tiền và nhận Hóa Đơn chính thức.
* **Nhân viên Phục vụ (`PHUC_VU` - Actor):** Mở bàn, tư vấn món theo số ly có thể làm, gửi lệnh pha chế quầy Bar, bấm yêu cầu thanh toán hoặc quay lại phục vụ; thực hiện chấm công cá nhân.
* **Nhân viên Thu ngân (`THU_NGAN` - Actor):** In Phiếu Tạm Tính, đối soát tiền mặt/chuyển khoản VietQR, xác nhận thanh toán & đóng bàn (kích hoạt trừ kho BOM tự động), hủy đơn hàng; thực hiện chấm công cá nhân.
* **Quản lý cửa hàng / Admin (`QUAN_LY` - Actor):** Toàn quyền kiểm soát hệ thống: áp dụng chiết khấu hóa đơn, quản trị kho nguyên liệu, thiết lập định mức BOM, kiểm kê hao hụt, phân ca tuần, duyệt điều chỉnh công và theo dõi KPI tài chính.
* **Tiến trình Hệ thống (`ChotCaTuDongScheduler` - Temporal Actor):** Tác nhân tự động chạy ngầm mỗi 15 phút để quét và chốt các ca làm việc đã quá hạn.

### 2.3. Phân tích 4 "điểm nghẽn" nghiệp vụ chí mạng cần giải quyết
1. **Điểm nghẽn 1 – Thất thoát nguyên liệu & Bán khống do lệch pha giữa Phục vụ và Quầy Bar:** Phục vụ nhận order theo cảm tính, không nắm được lượng tồn kho thực tế. Quầy Bar nhận đơn mới phát hiện hết nguyên liệu (hết sữa đặc, hết siro), buộc phục vụ phải quay lại xin lỗi khách để đổi món, phá vỡ trải nghiệm khách hàng.
2. **Điểm nghẽn 2 – Gian lận hóa đơn & Thất thoát dòng tiền tại quầy thu ngân:** Thu ngân thu tiền mặt xong nhưng không xuất bill hoặc bấm hủy đơn để chiếm đoạt tiền; tự ý áp dụng giảm giá cho người quen; hoặc cho khách chuyển khoản vào tài khoản cá nhân thay vì tài khoản quán.
3. **Điểm nghẽn 3 – Quản trị kho mù mờ, không tính được giá vốn và hao hụt:** Nhập hàng nhiều đợt với đơn giá biến động nhưng không tính được giá vốn bình quân; cuối tháng kiểm kê thấy thiếu hụt nguyên liệu nhưng không có sổ nhật ký lưu vết để quy trách nhiệm.
4. **Điểm nghẽn 4 – Xung đột ca làm việc và bất công trong chấm công:** Bảng phân ca giấy bị gạch xóa; nhân viên đến trễ hoặc về sớm không được ghi nhận chính xác; ca tối làm việc vất vả nhưng không có hệ số bù đắp; ca cao điểm thiếu thu ngân dẫn đến dồn ứ thanh toán.

### 2.4. Bảng phân rã sự kiện nghiệp vụ (Event Decomposition Table)
Theo phương pháp kỹ nghệ yêu cầu của học phần, các sự kiện tác động lên hệ thống được phân thành 3 loại: **Sự kiện bên ngoài (External Events)**, **Sự kiện trạng thái (State Events)**, và **Sự kiện thời gian (Temporal Events)**.

**Bảng 2.1: Bảng phân rã sự kiện nghiệp vụ hệ thống (Event Decomposition Table)**

| Mã SK | Tên sự kiện nghiệp vụ | Loại sự kiện | Tác nhân kích hoạt (Trigger/Source) | Phản ứng của hệ thống (System Response) | Use Case tương ứng |
| :---: | :--- | :---: | :--- | :--- | :--- |
| **E-01** | Người dùng đăng nhập hệ thống | External | Phục vụ / Thu ngân / Quản lý | Xác thực thông tin, tạo `HttpSession` bảo mật, điều hướng giao diện theo vai trò; khóa 60s nếu sai 5 lần. | `UC01: Đăng nhập` |
| **E-02** | Phục vụ mở bàn và tạo đơn mới | External | Nhân viên Phục vụ | Kiểm tra tồn kho khả dụng, tạo đơn `DANG_PHUC_VU`, chuyển bàn sang viền xanh lá `DANG_DUNG_NUOC`, sinh phiếu in Bar. | `UC06: Mở bàn & Tạo đơn` |
| **E-03** | Khách gọi thêm món vào đơn | External | Nhân viên Phục vụ | Tính chênh lệch $\Delta = \text{mới} - \text{đã in Bar}$, cập nhật đơn hàng và chỉ xuất phiếu in Bar cho phần bổ sung. | `UC06: Gọi thêm món` |
| **E-04** | Khách yêu cầu thanh toán | External | Nhân viên Phục vụ | Chuyển trạng thái đơn và bàn sang `CHO_THANH_TOAN` (viền vàng); khóa sửa món từ phục vụ. | `UC08: Yêu cầu thanh toán` |
| **E-05** | Khách muốn gọi thêm khi đang chờ bill | External | Nhân viên Phục vụ | Chuyển đơn và bàn từ `CHO_THANH_TOAN` quay lại `DANG_DUNG_NUOC` (viền xanh lá) để tiếp tục thêm món. | `UC09: Quay lại phục vụ` |
| **E-06** | Thu ngân in phiếu tạm tính | External | Nhân viên Thu ngân | Kết xuất Phiếu Tạm Tính (Proforma Bill) đầy đủ danh mục món, tiền và mã VietQR động để khách đối soát. | `UC10: In bill tạm tính` |
| **E-07** | Thu ngân xác nhận thanh toán | External | Nhân viên Thu ngân | Xác nhận tiền mặt/chuyển khoản, lưu `ma_nv_thu_ngan`, xuất Hóa Đơn nhiệt, đóng bàn về `TRONG` (viền xanh dương). | `UC11: Xác nhận thanh toán` |
| **E-08** | Hóa đơn được thanh toán xong | State | Hệ thống (Trigger ngầm) | Tự động bóc tách định mức BOM, khóa nguyên liệu theo thứ tự tăng dần chống Deadlock, trừ kho và ghi `lich_su_kho`. | `UC14: Tự động trừ kho` |
| **E-09** | Tồn kho chạm ngưỡng tối thiểu | State | Hệ thống (Trigger ngầm) | Bật nhãn đỏ cảnh báo `SẮP HẾT HÀNG`, cập nhật số lượng cảnh báo lên KPI Dashboard và thẻ món POS. | `UC15: Cảnh báo tồn kho` |
| **E-10** | Quản lý nhập hàng bổ sung | External | Quản lý cửa hàng | Nhập số lượng và đơn giá mới; tự động tính lại giá vốn WAC và ghi dòng `NHAP_KHO` vào sổ nhật ký kho. | `UC16: Nhập kho WAC` |
| **E-11** | Kiểm kê phát hiện chênh lệch | External | Quản lý cửa hàng | Nhập tồn thực tế và lý do; hệ thống tính $\Delta$, tính giá trị hao hụt và ghi dòng `KIEM_KE_HAO_HUT`. | `UC17: Kiểm kê hao hụt` |
| **E-12** | Nhân viên check-in vào ca | External | Toàn bộ nhân viên | Kiểm tra cửa sổ $[start - 30p, end)$; đối chiếu giờ máy chủ để gắn trạng thái `DUNG_GIO` hoặc `DI_TRE`. | `UC21: Check-in ca làm` |
| **E-13** | Nhân viên check-out ra ca | External | Toàn bộ nhân viên | Chặn check-out dưới 1 phút; tính giờ làm thực tế clamped; phát hiện `VE_SOM`; nhân hệ số lương ra giờ công quy đổi. | `UC22: Check-out ca làm` |
| **E-14** | Ca làm việc quá hạn chưa chốt | Temporal | Tiến trình Scheduler ngầm | Quét mỗi 15 phút: gắn `VANG` cho ca không check-in; gắn `QUEN_CHECK_OUT` cho ca quên check-out sau $end + 30p$. | `UC29: Chốt ca tự động` |
| **E-15** | Quản lý duyệt điều chỉnh công | External | Quản lý cửa hàng | Tiếp nhận giờ vào/ra điều chỉnh và lý do; lưu vết `nguoiDieuChinh`, `thoiGianDieuChinh`; tính lại giờ công. | `UC28: Điều chỉnh công` |

### 2.5. Danh mục yêu cầu chức năng cốt lõi (FR-01 đến FR-15)
Hệ thống bao phủ đầy đủ các yêu cầu chức năng theo chuẩn mực đề tài:

* **Nhóm 1 – Phân hệ Xác thực, Phân quyền & An ninh (Security & RBAC):**
  * **FR-01 (Đăng nhập xác thực):** Cho phép đăng nhập bằng tên đăng nhập và mật khẩu; kiểm tra vai trò người dùng và điều hướng trang tương ứng; tự động tạm khóa 60 giây khi đăng nhập sai quá 5 lần liên tiếp (HTTP 429).
  * **FR-02 (Quản lý phiên & Đăng xuất):** Quản lý phiên làm việc tập trung qua `HttpSession`; bảo vệ tuyệt đối mật khẩu người dùng (ẩn trường `matKhau` trong JSON phản hồi); hỗ trợ đăng xuất an toàn xóa sạch phiên trên máy chủ và trình duyệt.
* **Nhóm 2 – Phân hệ Bán hàng POS & Phục vụ tại bàn (POS Operations):**
  * **FR-03 (Sơ đồ bàn 3 màu viền thời gian thực):** Hiển thị sơ đồ 10 bàn với 3 màu viền chuẩn (Xanh dương: Trống, Xanh lá: Đang dùng nước kèm tổng tạm tính, Vàng hổ phách: Chờ thanh toán kèm tổng cần thu).
  * **FR-04 (Mở lại đơn & Khóa trạng thái bàn):** Khi nhấp vào bàn có khách, tự động tải lại chi tiết đơn hàng; chặn sửa món khi bàn đang ở trạng thái `CHO_THANH_TOAN` (HTTP 409).
  * **FR-05 (Thực đơn thông minh Soft Reservation & Bottleneck):** Tính toán và hiển thị số ly có thể làm theo nguyên liệu thắt nút cổ chai (Xanh: $>5$ ly, Vàng: $1-5$ ly, Đỏ: HẾT MÓN mờ thẻ khóa nút); che giấu hoàn toàn số lượng tồn kho nguyên liệu gốc.
  * **FR-06 (Gọi món & Ghi chú pha chế):** Cho phép chọn món, tăng/giảm số lượng và nhập ghi chú pha chế đặc biệt (ví dụ: "ít đường", "không đá").
  * **FR-07 (In lệnh quầy Bar theo độ lệch Delta):** Khi lưu đơn lần đầu, in toàn bộ món; khi gọi thêm món, chỉ in phiếu Bar cho các món mới phát sinh ($\Delta = \text{mới} - \text{đã in} > 0$).
  * **FR-08 (Yêu cầu thanh toán & Quay lại phục vụ):** Cho phép Phục vụ chuyển bàn sang `CHO_THANH_TOAN` để chờ thanh toán, hoặc chuyển ngược lại `DANG_DUNG_NUOC` nếu khách muốn gọi thêm.
  * **FR-09 (Phiếu Tạm Tính & VietQR động):** Cho phép Thu ngân xuất Phiếu Tạm Tính (Proforma Bill) kèm mã VietQR tự động để khách hàng đối soát trước khi thanh toán.
  * **FR-10 (Xác nhận thanh toán & Đóng bàn):** Hỗ trợ phương thức Tiền mặt và Chuyển khoản; kiểm tra tiền khách đưa hợp lệ; lưu định danh thu ngân; đóng bàn về `TRONG` và xuất Hóa Đơn nhiệt chính thức.
  * **FR-11 (Phân quyền chiết khấu RBAC):** Chỉ cho phép `QUAN_LY` áp dụng giảm giá hóa đơn; chặn tuyệt đối tài khoản `PHUC_VU` và `THU_NGAN` (HTTP 403).
* **Nhóm 3 – Phân hệ Kho nguyên liệu, Định mức BOM & KPI (Inventory & BOM):**
  * **FR-12 (Quản lý định mức BOM 12 món & Tự động trừ kho chống Deadlock):** Bóc tách công thức pha chế 12 món từ 13 nguyên liệu; tự động trừ kho nguyên tử trong giao dịch `@Transactional` với thuật toán sắp xếp thứ tự nguyên liệu tăng dần chống Deadlock 100%.
  * **FR-13 (Sổ nhật ký biến động kho & Cảnh báo tồn đỏ):** Tự động ghi vết mọi giao dịch nhập kho, xuất bán và kiểm kê vào bảng `lich_su_kho`; bật nhãn đỏ cảnh báo khi tồn $\le$ ngưỡng tối thiểu.
  * **FR-14 (Nhập kho WAC & Kiểm kê hao hụt):** Cập nhật đơn giá vốn theo phương pháp Bình quân gia quyền liên hoàn; hỗ trợ kiểm kê thực tế, tính chênh lệch và tổn thất hao hụt.
* **Nhóm 4 – Phân hệ Quản trị Nhân sự & Chấm công (HR & Attendance):**
  * **FR-15 (Chấm công thời gian thực & Phân ca tuần):** Áp dụng 7 quy tắc chấm công chuẩn hóa (BR-CC-01 $\rightarrow$ BR-CC-07) với `java.time.Clock`; lưới phân ca tuần kiểm soát cân bằng vai trò (cảnh báo thiếu thu ngân); tác vụ Scheduler tự động chốt ca quá hạn định kỳ mỗi 15 phút.

### 2.6. Danh mục yêu cầu phi chức năng (NFR-01 đến NFR-06) kèm chỉ số đo lường

**Bảng 2.2: Bảng danh mục yêu cầu phi chức năng (NFR) kèm thang đo lường định lượng**

| Mã NFR | Nhóm chất lượng | Tiêu chí yêu cầu chi tiết | Chỉ số đo lường định lượng (Metrics) | Phương pháp kiểm chứng |
| :---: | :--- | :--- | :--- | :--- |
| **NFR-01** | **Hiệu năng (Performance)** | Thời gian phản hồi của các API nghiệp vụ chính (tạo đơn, tải danh mục món, thanh toán, trừ kho). | Thời gian phản hồi API trung bình $< 300\text{ms}$; tải toàn bộ trang SPA $< 1.5\text{ giây}$. | Đo bằng Chrome DevTools Network & JMeter load test. |
| **NFR-02** | **Tính toàn vẹn dữ liệu (Integrity)** | Đảm bảo tính nhất quán của giao dịch trừ kho và thanh toán; không thất thoát hoặc sai lệch tồn kho. | Tỷ lệ thành công $100\%$ tuân thủ nguyên tử ACID; rollback $100\%$ khi có lỗi thiếu kho; triệt tiêu Deadlock 100%. | Thực thi 19 ca kiểm thử tích hợp tự động `@Transactional`. |
| **NFR-03** | **Bảo mật & Phân quyền (Security)** | Kiểm soát quyền truy cập RBAC 4 tầng; không rò rỉ mật khẩu người dùng trong JSON; chống brute-force. | $0\%$ rò rỉ trường `matKhau`; tạm khóa 60s khi sai 5 lần; chặn $100\%$ truy cập trái quyền (HTTP 403). | Kiểm thử MockMvc & Route Guard audit. |
| **NFR-04** | **Tính khả dụng (Usability)** | Giao diện Light Mode thân thiện in ấn A4, sử dụng tiếng Việt UTF-8 chuẩn mực, sơ đồ bàn 3 màu viền rõ ràng. | Nhân viên hoàn tất tạo đơn và thanh toán trong dưới $4$ thao tác nhấp chuột. | Đo đạc thời gian thao tác thực nghiệm của người dùng mẫu. |
| **NFR-05** | **Khả năng đồng thời (Concurrency)** | Chống xung đột khi nhiều nhân viên cùng thao tác trên 1 đơn hàng hoặc nhiều đơn cùng trừ 1 nguyên liệu sát ngưỡng. | $0\%$ đơn hàng bị ghi đè (Lost Update) nhờ `@Version`; $0\%$ bán khống nguyên liệu (Pessimistic Lock). | Kiểm thử đa luồng đồng thời (Concurrency Test). |
| **NFR-06** | **Tính khả chuyển (Portability)** | Chạy ổn định trên mọi nền tảng hệ điều hành hỗ trợ Java 17+ và các trình duyệt web hiện đại. | Hoạt động $100\%$ trên Windows 10/11, macOS, Linux với Chrome, Edge, Firefox. | Kiểm thử thực tế trên các môi trường khác nhau. |

### 2.7. Bảng phân loại và ưu tiên hóa yêu cầu theo phương pháp MoSCoW

**Bảng 2.3: Bảng phân loại và ưu tiên hóa yêu cầu chức năng theo phương pháp MoSCoW**

| Phân nhóm MoSCoW | Danh sách mã yêu cầu chức năng (FR) | Lý giải quyết định phân loại |
| :---: | :--- | :--- |
| **Must-Have (Bắt buộc phải có)** | `FR-01`, `FR-02`, `FR-03`, `FR-04`, `FR-05`, `FR-06`, `FR-07`, `FR-08`, `FR-09`, `FR-10`, `FR-11`, `FR-12`, `FR-13`, `FR-14`, `FR-15` | Đây là các chức năng nền tảng sống còn cấu thành quy trình vận hành quán cà phê khép kín (*Gọi món tại bàn $\rightarrow$ In Bar $\rightarrow$ Tạm tính $\rightarrow$ Thanh toán $\rightarrow$ Trừ kho chống Deadlock $\rightarrow$ Chấm công thời gian thực*). Nhóm cam kết và đã hiện thực hóa $100\%$ các chức năng này trên ứng dụng chạy thực tế. |
| **Should-Have (Nên có nếu đủ thời gian)** | Hỗ trợ tính năng tách bàn hoặc ghép bàn khi quán đông khách; phân ca tự động bằng thuật toán gợi ý AI. | Đã được chuẩn bị sẵn cấu trúc thực thể CSDL (bảng `hoa_don` có thể hỗ trợ liên kết bàn linh hoạt), sẽ mở rộng trong phiên bản tiếp theo. |
| **Could-Have (Có thể phát triển thêm)** | Tích hợp cổng thanh toán trực tuyến tự động qua Webhook ngân hàng thương mại; gửi tin nhắn thông báo cảnh báo kho qua Telegram bot. | Là tính năng gia tăng trải nghiệm người dùng, có thể tích hợp qua Webhook API mà không làm thay đổi kiến trúc nghiệp vụ cốt lõi. |
| **Won't-Have (Không thực hiện lần này)** | Phân hệ kế toán chuyên sâu (thuế VAT, khấu hao tài sản); tích hợp máy chấm công nhận diện khuôn mặt AI phần cứng. | Nằm ngoài phạm vi của đề tài đồ án học phần Phân tích Thiết kế Yêu cầu (Out-of-Scope). |

---

## CHƯƠNG 3: MÔ HÌNH HÓA USE CASE & PHÂN TÍCH NGHIỆP VỤ HỆ THỐNG

### 3.1. Biểu đồ Use Case tổng quát toàn hệ thống
Biểu đồ Use Case tổng quát mô tả ranh giới hệ thống (*System Boundary*), 4 tác nhân chính và toàn bộ 30 trường hợp sử dụng cùng mối quan hệ kế thừa và quan hệ phụ thuộc:

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 3.1:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\UseCaseTongQuat.svg`  
> ![Hình 3.1: Biểu đồ Use Case tổng quát toàn hệ thống](file:///d:/3.1.%20PTTKYC/CK/diagram/UseCaseTongQuat.svg)

#### Phân tích chi tiết Biểu đồ Use Case tổng quát (Hình 3.1):
1. **Ranh giới hệ thống (*System Boundary*):** Toàn bộ các Use Case được đóng gói chặt chẽ bên trong khối ranh giới "Hệ Thống Quản Lý Quán Cafe (Sangrimar Cafe & Lounge)".
2. **Các tác nhân (*Actors*) và phân cấp vai trò RBAC:**
   * **Nhân viên Phục vụ (`PHUC_VU`):** Tương tác với sơ đồ bàn, xem số ly có thể làm, mở bàn gọi món, gửi lệnh in Bar, yêu cầu thanh toán và chấm công cá nhân.
   * **Nhân viên Thu ngân (`THU_NGAN`):** Kế thừa toàn bộ quyền hạn của Phục vụ; có thêm quyền in Phiếu Tạm Tính, xác nhận thanh toán & đóng bàn, kích hoạt trừ kho tự động và hủy đơn hàng.
   * **Quản lý cửa hàng / Admin (`QUAN_LY`):** Kế thừa toàn bộ quyền hạn của Thu ngân; có thêm quyền áp dụng chiết khấu, quản trị danh mục nguyên liệu/món nước, thiết lập định mức BOM, nhập kho WAC, kiểm kê hao hụt, phân ca tuần, duyệt điều chỉnh công và xem báo cáo KPI.
   * **Tiến trình Hệ thống (`ChotCaTuDongScheduler`):** Tác nhân tự động kích hoạt định kỳ mỗi 15 phút để chốt ca làm việc quá hạn.
3. **Mối quan hệ phụ thuộc `<<include>>`:**
   * Mọi Use Case nghiệp vụ chính đều include Use Case `Đăng nhập hệ thống`.
   * Use Case `Xác nhận thanh toán & Đóng bàn` bắt buộc include `Tự động trừ kho & Ghi lịch sử kho`.
4. **Mối quan hệ mở rộng `<<extend>>`:**
   * Use Case `Áp dụng chiết khấu giảm giá` mở rộng cho `Xác nhận thanh toán` (chỉ Quản lý kích hoạt).
   * Use Case `Xem danh mục & Cảnh báo tồn tối thiểu` mở rộng cho `Tự động trừ kho` khi tồn kho chạm ngưỡng an toàn.

### 3.2. Bảng phân rã Use Case theo các phân hệ nghiệp vụ

**Bảng 3.1: Phân loại và tổng hợp danh mục 30 Use Case theo từng phân hệ**

| Phân hệ nghiệp vụ | Mã Use Case | Tên ca sử dụng (Use Case Name) | Tác nhân chính (Primary Actor) |
| :--- | :---: | :--- | :--- |
| **1. Xác thực & Phiên làm việc** | `UC01` | Đăng nhập hệ thống (chống brute-force) | Phục vụ, Thu ngân, Quản lý |
| | `UC02` | Đăng xuất hệ thống | Toàn bộ người dùng |
| | `UC03` | Xem thông tin phiên làm việc | Toàn bộ người dùng |
| **2. Bán hàng POS & Bàn** | `UC04` | Xem sơ đồ bàn, 3 màu viền & tiền tạm tính | Phục vụ, Thu ngân, Quản lý |
| | `UC05` | Xem thực đơn & số ly có thể làm (`soLyCoTheLam`) | Phục vụ, Thu ngân, Quản lý |
| | `UC06` | Mở bàn & Tạo đơn hàng mới | Phục vụ, Thu ngân, Quản lý |
| | `UC07` | In phiếu chế biến cho Quầy Bar (In Bar Ticket / In Bar Delta) | Phục vụ, Thu ngân, Quản lý |
| | `UC08` | Yêu cầu thanh toán (Bàn sang `CHO_THANH_TOAN`) | Phục vụ, Quản lý |
| | `UC09` | Quay lại phục vụ (Bàn trở về `DANG_DUNG_NUOC`) | Phục vụ, Quản lý |
| | `UC10` | In bill tạm tính (Pre-check Bill kèm VietQR động) | Thu ngân, Quản lý |
| | `UC11` | Xác nhận thanh toán (Tiền mặt / VietQR) & Đóng bàn | Thu ngân, Quản lý |
| | `UC12` | Áp dụng chiết khấu giảm giá hóa đơn | Quản lý (Chặn Phục vụ & Thu ngân) |
| | `UC13` | Hủy đơn hàng và giải phóng bàn về trống | Thu ngân, Quản lý |
| | `UC14` | Tự động trừ kho theo BOM & Ghi sổ nhật ký kho | Hệ thống (Kích hoạt ngầm) |
| **3. Quản trị Kho & Định mức BOM** | `UC15` | Xem danh mục nguyên liệu & Cảnh báo tồn tối thiểu | Quản lý cửa hàng |
| | `UC16` | Nhập kho nguyên liệu bổ sung (Tính giá vốn WAC) | Quản lý cửa hàng |
| | `UC17` | Kiểm kê kho & Cân bằng tồn (Ghi nhận hao hụt) | Quản lý cửa hàng |
| | `UC18` | Tra cứu lịch sử biến động kho (`lich_su_kho`) | Quản lý cửa hàng |
| | `UC19` | Quản lý định mức công thức món uống (BOM 12 món) | Quản lý cửa hàng |
| | `UC20` | Quản lý danh mục món nước (Thêm, sửa, ngừng bán) | Quản lý cửa hàng |
| **4. Quản trị Nhân sự & Chấm công** | `UC21` | Check-in ca làm việc thời gian thực (Cửa sổ 30p) | Toàn bộ nhân viên |
| | `UC22` | Check-out ca làm việc & Tính giờ công quy đổi hệ số | Toàn bộ nhân viên |
| | `UC23` | Xem lịch phân ca & Bảng công cá nhân | Phục vụ, Thu ngân |
| | `UC24` | Quản lý hồ sơ nhân viên (CRUD & Khóa mềm tài khoản) | Quản lý cửa hàng |
| | `UC25` | Quản lý ca làm việc (Giờ bắt đầu, kết thúc, hệ số lương) | Quản lý cửa hàng |
| | `UC26` | Phân ca tuần cho nhân viên (Cảnh báo thiếu Thu ngân) | Quản lý cửa hàng |
| | `UC27` | Điều phối & Đổi ca làm việc giữa nhân viên | Quản lý cửa hàng |
| | `UC28` | Điều chỉnh bản ghi chấm công ngoại lệ & Ghi lý do kiểm toán | Quản lý cửa hàng |
| | `UC29` | Chốt ca tự động & Ghi nhận Vắng / Quên check-out | Tiến trình `ChotCaTuDongScheduler` |
| | `UC30` | Xem báo cáo KPI Dashboard 4 kỳ & Bảng công tháng | Quản lý cửa hàng |

### 3.3. Đặc tả chi tiết 7 Use Case trọng tâm (Fully-Dressed Template 9 trường)

#### 3.3.1. Đặc tả UC01: Đăng nhập hệ thống & Phân quyền an ninh
1. **Tên Use Case:** Đăng nhập hệ thống (`UC01_DangNhap`).
2. **Tác nhân chính:** Phục vụ, Thu ngân, Quản lý.
3. **Mục đích:** Xác thực tài khoản người dùng, khởi tạo `HttpSession` bảo mật, điều hướng đến phân hệ tương ứng và kích hoạt cơ chế chống dò quét mật khẩu Brute-force.
4. **Tiền điều kiện:** Người dùng mở trình duyệt truy cập `index.html`; tài khoản có trạng thái `DANG_LAM` trong bảng `nhan_vien`.
5. **Hậu điều kiện:** Máy chủ lưu thông tin phiên; Client lưu `currentUser` vào storage; giao diện chuyển hướng đúng phân hệ.
6. **Luồng sự kiện chính:**
   * *Bước 1:* Người dùng nhập tên đăng nhập và mật khẩu (hoặc bấm 1 trong 3 nút Quick-fill demo).
   * *Bước 2:* Bấm nút "Đăng Nhập".
   * *Bước 3:* Trình duyệt gửi `POST /api/auth/login`.
   * *Bước 4:* Máy chủ kiểm tra bộ đếm số lần đăng nhập sai của địa chỉ IP/tài khoản. Nếu số lần sai liên tiếp $< 5$, tiếp tục xác thực.
   * *Bước 5:* Đối chiếu mật khẩu với CSDL. Tạo phiên `HttpSession`, đặt thuộc tính `nguoiDung`, `vaiTro`.
   * *Bước 6:* Đặt lại bộ đếm số lần sai về 0; trả về HTTP 200 OK kèm thông tin người dùng (ẩn trường `matKhau`).
   * *Bước 7:* Client điều hướng người dùng: `QUAN_LY` $\rightarrow$ `kho.html`, `THU_NGAN` $\rightarrow$ `banhang.html`, `PHUC_VU` $\rightarrow$ `banhang.html`.
7. **Luồng ngoại lệ:**
   * *E1 (Sai mật khẩu lần 1 đến 4):* Trả về HTTP 401 Unauthorized; tăng bộ đếm; giao diện báo alert đỏ.
   * *E2 (Sai mật khẩu 5 lần liên tiếp):* Máy chủ kích hoạt khóa tạm thời 60 giây; trả về HTTP 429 Too Many Requests kèm thời gian còn lại. Mọi request tiếp theo trong 60s bị từ chối ngay lập tức.
   * *E3 (Tài khoản bị khóa mềm):* Nếu `trangThai == 'DA_NGHI'`, trả về HTTP 403 Forbidden báo tài khoản đã bị vô hiệu hóa.
8. **Quy tắc nghiệp vụ:** Mật khẩu được bảo mật tuyệt đối, không rò rỉ trong JSON response.
9. **Yêu cầu phi chức năng:** Phản hồi đăng nhập $< 200\text{ms}$.

---

#### 3.3.2. Đặc tả UC06: Mở bàn, Gọi món & In lệnh Quầy Bar theo độ lệch Delta
1. **Tên Use Case:** Mở bàn, Gọi món & In lệnh Quầy Bar (`UC06_GoiMonVaInBar`).
2. **Tác nhân chính:** Nhân viên Phục vụ, Thu ngân.
3. **Mục đích:** Tiếp nhận yêu cầu gọi món tại bàn của khách, kiểm tra tồn kho khả dụng qua Soft Reservation, lưu đơn hàng `DANG_PHUC_VU`, đổi trạng thái bàn sang `DANG_DUNG_NUOC` (viền xanh lá) và xuất phiếu in Bar Ticket cho các món mới phát sinh.
4. **Tiền điều kiện:** Nhân viên đã đăng nhập, đang ở màn hình `banhang.html`; chọn một bàn đang trống (`TRONG`) hoặc đang phục vụ (`DANG_DUNG_NUOC`).
5. **Hậu điều kiện:** Hóa đơn được tạo/cập nhật; bàn chuyển viền xanh lá kèm tổng tạm tính; gán `soLuongDaInBar = soLuong`; trả về dữ liệu in Bar Ticket.
6. **Luồng sự kiện chính:**
   * *Bước 1:* Phục vụ nhấp vào thẻ bàn trên sơ đồ bàn.
   * *Bước 2:* Hệ thống hiển thị thực đơn đồ uống kèm chỉ số `soLyCoTheLam` (Xanh $>5$, Vàng $1-5$, Đỏ Hết món).
   * *Bước 3:* Phục vụ chọn món, tăng/giảm số lượng và nhập ghi chú pha chế (ít đường, không đá).
   * *Bước 4:* Phục vụ bấm nút "Lưu đơn / In Bar".
   * *Bước 5:* Client gửi `POST /api/order/tao-don` (nếu mở bàn mới) hoặc `PUT /api/order/{id}/mon` (nếu gọi thêm).
   * *Bước 6:* Máy chủ kiểm tra điều kiện tồn kho khả dụng: $\text{Số lượng gọi} \le \text{soLyCoTheLam}$. Nếu thỏa mãn, lưu CSDL.
   * *Bước 7:* Với món gọi thêm: tính chênh lệch $\Delta = \text{soLuong} - \text{soLuongDaInBar} > 0$. Gán lại `soLuongDaInBar = soLuong`.
   * *Bước 8:* Máy chủ cập nhật bàn sang `DANG_DUNG_NUOC` (viền xanh lá) và trả về đối tượng `phieuInBar` (chỉ chứa các món có $\Delta > 0$).
   * *Bước 9:* Giao diện mở hộp thoại in phiếu pha chế Bar Ticket để chuyển xuống quầy Bar.
7. **Luồng ngoại lệ:**
   * *E1 (Gọi quá số ly có thể làm):* Nếu số lượng yêu cầu vượt quá tồn kho khả dụng của nguyên liệu thắt nút cổ chai, trả về HTTP 400 Bad Request kèm thông báo lỗi chi tiết.
   * *E2 (Sửa món khi bàn đang chờ thanh toán):* Nếu bàn đang ở trạng thái `CHO_THANH_TOAN`, máy chủ chặn ngay với mã HTTP 409 Conflict.
8. **Quy tắc nghiệp vụ:** Phiếu Bar Ticket không hiển thị giá tiền, chỉ hiển thị tên bàn, giờ gọi, tên món, số lượng mới và ghi chú khẩu vị.
9. **Yêu cầu phi chức năng:** Lưu đơn và trả phiếu in $< 300\text{ms}$.

---

#### 3.3.3. Đặc tả UC08: Yêu cầu thanh toán & Khóa bàn Chờ thanh toán
1. **Tên Use Case:** Yêu cầu thanh toán (`UC08_YeuCauThanhToan`).
2. **Tác nhân chính:** Nhân viên Phục vụ.
3. **Mục đích:** Chuyển trạng thái bàn và hóa đơn sang Chờ thanh toán (`CHO_THANH_TOAN`), đổi viền bàn sang màu vàng hổ phách `#D97706` và khóa quyền sửa món của phục vụ để chống gian lận.
4. **Tiền điều kiện:** Bàn đang ở trạng thái `DANG_DUNG_NUOC` và hóa đơn có ít nhất 1 món.
5. **Hậu điều kiện:** Bàn và hóa đơn mang trạng thái `CHO_THANH_TOAN`; ô chọn món bị khóa; thẻ bàn hiển thị icon tiền kèm số tiền cần thu.
6. **Luồng sự kiện chính:**
   * *Bước 1:* Khi khách yêu cầu tính tiền, Phục vụ nhấp vào bàn của khách trên `banhang.html`.
   * *Bước 2:* Phục vụ bấm nút "Yêu cầu thanh toán".
   * *Bước 3:* Client gửi `POST /api/order/{id}/yeu-cau-thanh-toan`.
   * *Bước 4:* Máy chủ cập nhật `HoaDon.trangThai = CHO_THANH_TOAN`, ghi nhận `thoiGianYeuCauTT = now()`, cập nhật `Ban.trangThai = CHO_THANH_TOAN`.
   * *Bước 5:* Máy chủ trả về HTTP 200 OK. Thẻ bàn lập tức chuyển sang viền vàng hổ phách, phát tín hiệu cho thu ngân biết bàn đã sẵn sàng thanh toán.
7. **Luồng thay thế (Quay lại phục vụ):** Nếu khách đổi ý muốn gọi thêm món, Phục vụ bấm nút "Quay lại phục vụ". Hệ thống gửi `POST /api/order/{id}/quay-lai-phuc-vu`, đưa bàn và hóa đơn về `DANG_DUNG_NUOC` (viền xanh lá) để mở khóa quyền thêm món.
8. **Luồng ngoại lệ:** Bàn đang trống hoặc đã thanh toán bị từ chối yêu cầu.
9. **Quy tắc nghiệp vụ:** Khi ở trạng thái `CHO_THANH_TOAN`, phục vụ không thể thêm, bớt hoặc xóa món.

---

#### 3.3.4. Đặc tả UC10: In Phiếu Tạm Tính & Kiểm soát minh bạch
1. **Tên Use Case:** In Phiếu Tạm Tính (`UC10_InPhieuTamTinh`).
2. **Tác nhân chính:** Nhân viên Thu ngân, Quản lý.
3. **Mục đích:** Kết xuất Phiếu Tạm Tính (Proforma Bill) đầy đủ thông tin danh sách món, đơn giá, tổng tiền và mã VietQR động để nhân viên mang ra bàn cho khách kiểm tra trước khi thu tiền.
4. **Tiền điều kiện:** Hóa đơn đang ở trạng thái `DANG_PHUC_VU` hoặc `CHO_THANH_TOAN`.
5. **Hậu điều kiện:** Phiếu in tạm tính được hiển thị trong modal in ấn `@media print` với dòng ghi chú "PHIẾU TẠM TÍNH - QUÝ KHÁCH VUI LÒNG KIỂM TRA LẠI HÓA ĐƠN".
6. **Luồng sự kiện chính:**
   * *Bước 1:* Thu ngân bấm nút "In Phiếu Tạm Tính" trên thanh công cụ đơn hàng.
   * *Bước 2:* Hệ thống tổng hợp toàn bộ các món nước trong đơn, tính tổng tiền tạm tính.
   * *Bước 3:* Hệ thống gọi API sinh mã VietQR động chứa chính xác số tiền cần thanh toán và nội dung chuyển khoản là mã hóa đơn.
   * *Bước 4:* Giao diện hiển thị modal Phiếu Tạm Tính và gọi lệnh in trình duyệt `window.print()`.
7. **Luồng ngoại lệ:** Đơn hàng trống không cho phép in phiếu tạm tính.
8. **Quy tắc nghiệp vụ:** Phiếu Tạm Tính không phải là hóa đơn tài chính cuối cùng và không làm thay đổi trạng thái tồn kho của hệ thống.
9. **Yêu cầu phi chức năng:** Render phiếu in sắc nét, ẩn toàn bộ thanh điều hướng và nút bấm nền.

---

#### 3.3.5. Đặc tả UC11 & UC14: Xác nhận thanh toán, Đóng bàn & Trừ kho tự động chống Deadlock
1. **Tên Use Case:** Xác nhận thanh toán & Trừ kho tự động (`UC11_ThanhToanVaTruKho`).
2. **Tác nhân chính:** Nhân viên Thu ngân, Quản lý.
3. **Mục đích:** Thu tiền khách hàng (Tiền mặt hoặc Chuyển khoản), lưu định danh thu ngân chốt đơn, bóc tách định mức BOM trừ kho tự động nguyên tử chống Deadlock, ghi sổ nhật ký `lich_su_kho`, đóng bàn về trống và in Hóa Đơn chính thức.
4. **Tiền điều kiện:** Hóa đơn đang mở, có món; người dùng có vai trò `THU_NGAN` hoặc `QUAN_LY`.
5. **Hậu điều kiện:** Hóa đơn chuyển `DA_THANH_TOAN`; bàn chuyển về `TRONG` (viền xanh dương); kho nguyên liệu bị trừ; ghi dòng `XUAT_BAN_HANG` vào `lich_su_kho`.
6. **Luồng sự kiện chính:**
   * *Bước 1:* Thu ngân bấm nút "Xác nhận thanh toán & Đóng bàn" trên `banhang.html`.
   * *Bước 2:* Chọn phương thức Tiền mặt hoặc Chuyển khoản. Nếu tiền mặt: nhập tiền khách đưa, hệ thống tính tiền thối. Nếu chuyển khoản: tích xác nhận và nhập mã tham chiếu giao dịch.
   * *Bước 3:* Thu ngân bấm "Hoàn tất thanh toán".
   * *Bước 4:* Client gửi `POST /api/order/{id}/thanh-toan`.
   * *Bước 5:* `DonHangService` mở giao dịch `@Transactional(rollbackFor = Exception.class)`. Khóa bi quan hóa đơn bằng `FOR UPDATE`.
   * *Bước 6:* Gán `nhanVienThuNgan = Session.currentUser`, ghi nhận `thoiGianThanhToan = now()`.
   * *Bước 7:* Gọi `khoService.truKhoTuDong(hoaDon)`:
     - Tập hợp danh sách tất cả nguyên liệu cần tiêu hao qua bảng `cong_thuc`.
     - Sử dụng `TreeMap<Integer, BigDecimal>` để gom tổng tiêu hao theo từng `ma_nl` và tự động sắp xếp theo thứ tự mã nguyên liệu tăng dần: $\text{maNL}_1 < \text{maNL}_2 < \dots < \text{maNL}_k$.
     - Khóa từng dòng nguyên liệu bằng `SELECT ... FOR UPDATE` theo thứ tự đã sắp xếp (triệt tiêu điều kiện kẹt vòng Deadlock 100%).
     - Kiểm tra tồn kho: nếu bất kỳ nguyên liệu nào thiếu hàng $\rightarrow$ ném ngoại lệ, Rollback sạch toàn bộ giao dịch.
     - Cập nhật tồn kho mới: `soLuongTon = soLuongTon - tongTieuHao`.
     - Ghi tự động một bản ghi `LichSuKho` với `loaiGD = XUAT_BAN_HANG`, gắn mã hóa đơn và thu ngân thực hiện.
   * *Bước 8:* Cập nhật hóa đơn sang `DA_THANH_TOAN`, cập nhật bàn về `TRONG` (viền xanh dương).
   * *Bước 9:* Giao diện hiển thị Hóa Đơn Thanh Toán Chính Thức và sẵn sàng in nhiệt.
7. **Luồng ngoại lệ:**
   * *E1 (Thiếu tiền khách đưa):* Nếu tiền khách đưa nhỏ hơn tổng tiền phải thu, hệ thống chặn lại với thông báo lỗi.
   * *E2 (Phục vụ cố tình thanh toán):* Máy chủ phát hiện vai trò `PHUC_VU` và trả về HTTP 403 Forbidden.
   * *E3 (Thiếu nguyên liệu kho):* Giao dịch tự động Rollback 100%, bảo toàn toàn bộ số liệu CSDL.
8. **Quy tắc nghiệp vụ:** Bắt buộc lưu vết `ma_nv_thu_ngan` tách biệt với `ma_nv` mở bàn để phục vụ kiểm toán đối soát doanh thu.
9. **Yêu cầu phi chức năng:** Toàn bộ giao dịch thanh toán và trừ kho cam kết dưới $400\text{ms}$.

---

#### 3.3.6. Đặc tả UC16: Nhập kho nguyên liệu & Cập nhật giá vốn Bình quân gia quyền (WAC)
1. **Tên Use Case:** Nhập kho nguyên liệu WAC (`UC16_NhapKhoWAC`).
2. **Tác nhân chính:** Quản lý cửa hàng (`QUAN_LY`).
3. **Mục đích:** Tiếp nhận lô hàng nguyên liệu mới từ nhà cung cấp, cập nhật tồn kho và tự động tính lại đơn giá vốn theo công thức Bình quân gia quyền liên hoàn (Weighted Average Cost), ghi sổ nhật ký `lich_su_kho`.
4. **Tiền điều kiện:** Quản lý đã đăng nhập, mở trang `kho.html`.
5. **Hậu điều kiện:** Tồn kho tăng lên; đơn giá nhập mới được cập nhật theo WAC; tạo bản ghi `NHAP_KHO` trong `lich_su_kho`; nhãn đỏ cảnh báo tắt nếu tồn vượt ngưỡng an toàn.
6. **Luồng sự kiện chính:**
   * *Bước 1:* Quản lý bấm nút "Nhập kho" tại dòng nguyên liệu tương ứng trên `kho.html`.
   * *Bước 2:* Hệ thống mở modal `#restockModal`, cho phép chọn nhanh các preset (+100, +500, +1000) hoặc nhập số lượng tùy ý, kèm đơn giá nhập của lô hàng mới.
   * *Bước 3:* Hệ thống hiển thị xem trước thành tiền: $\text{Thành tiền} = \text{Số lượng nhập} \times \text{Đơn giá nhập mới}$.
   * *Bước 4:* Quản lý bấm "Xác nhận nhập kho".
   * *Bước 5:* Client gửi `POST /api/kho/nhap-kho`.
   * *Bước 6:* `KhoService` mở giao dịch `@Transactional`, khóa dòng nguyên liệu:
     $$\text{donGiaMoi} = \frac{(\text{tonTruoc} \times \text{donGiaCu}) + (\text{soLuongNhap} \times \text{donGiaNhapMoi})}{\text{tonTruoc} + \text{soLuongNhap}}$$
   * *Bước 7:* Cập nhật `soLuongTon = tonTruoc + soLuongNhap` và `donGiaNhap = donGiaMoi`.
   * *Bước 8:* Ghi một bản ghi vào `lich_su_kho` với loại `NHAP_KHO`, lưu rõ `tonTruoc`, `tonSau`, `donGia` và nhân sự thực hiện.
   * *Bước 9:* Giao diện hiển thị Toast thông báo thành công và tải lại bảng tồn kho.
7. **Luồng ngoại lệ:** Nhập số lượng $\le 0$ hoặc đơn giá $< 0$ bị từ chối với mã HTTP 400 Bad Request.
8. **Quy tắc nghiệp vụ:** Mọi biến động nhập kho đều phải lưu lại vết kiểm toán không thể xóa sửa.

---

#### 3.3.7. Đặc tả UC21 & UC22: Chấm công thời gian thực & Áp dụng 7 Quy tắc BR-CC
1. **Tên Use Case:** Chấm công thời gian thực (`UC21_UC22_ChamCong`).
2. **Tác nhân chính:** Toàn bộ nhân viên (Phục vụ, Thu ngân, Quản lý).
3. **Mục đích:** Ghi nhận giờ vào ca (Check-in) và giờ ra ca (Check-out) theo thời gian thực máy chủ, tự động áp dụng 7 quy tắc nghiệp vụ chấm công (BR-CC-01 $\rightarrow$ BR-CC-07) để xác định trạng thái chấp hành và quy đổi giờ công theo hệ số lương ca làm.
4. **Tiền điều kiện:** Nhân viên đã đăng nhập tài khoản của mình trên `nhansu.html`.
5. **Hậu điều kiện:** Bản ghi trong bảng `cham_cong` được tạo hoặc cập nhật với đầy đủ các trường phân tích (`so_phut_tre`, `so_phut_ve_som`, `so_gio_lam`, `gio_cong_quy_doi`, `trang_thai`).
6. **Luồng sự kiện chính:**
   * *Bước 1:* Nhân viên truy cập `nhansu.html` (Chế độ Cá nhân hiển thị ca làm việc hôm nay của đúng nhân viên đó).
   * *Bước 2 (Check-in):* Nhân viên bấm nút "Check-in Vào Ca".
   * *Bước 3:* Client gửi `POST /api/cham-cong/check-in` kèm `maPhanCa`.
   * *Bước 4:* `NhanSuService` kiểm tra các quy tắc:
     - `BR-CC-01`: Xác thực phiên làm việc, chỉ cho phép check-in ca của chính mình và đúng ngày hôm nay.
     - `BR-CC-02`: Kiểm tra cửa sổ check-in: chỉ cho phép trong khoảng $[\text{gio\_bat\_dau} - 30\text{ phút},\ \text{gio\_ket\_thuc})$. Ngoài cửa sổ từ chối HTTP 400.
     - `BR-CC-03`: Nếu thời gian vào trễ $\le 15$ phút $\rightarrow$ Gán `DUNG_GIO` (`so_phut_tre = 0`); nếu trễ $> 15$ phút $\rightarrow$ Gán `DI_TRE` (`so_phut_tre = \Delta_{in}`).
   * *Bước 5:* Lưu bản ghi vào bảng `cham_cong` và trả về giao diện.
   * *Bước 6 (Check-out):* Khi kết thúc ca làm, nhân viên bấm "Check-out Tan Ca".
   * *Bước 7:* Client gửi `POST /api/cham-cong/check-out/{id}`.
   * *Bước 8:* `NhanSuService` áp dụng `BR-CC-04`:
     - Chặn check-out nếu thời gian làm dưới 1 phút.
     - Tính giờ làm thực tế clamped: $t_{\text{start}} = \max(\text{in}, \text{start})$, $t_{\text{end}} = \min(\text{out}, \text{end})$.
     - Nếu về sớm $> 15$ phút $\rightarrow$ Gán `VE_SOM` (hoặc kết hợp `DI_TRE_VE_SOM`).
     - Tính `so_gio_lam = (t_{end} - t_{start})` và `gio_cong_quy_doi = so_gio_lam \times he_so_luong`.
   * *Bước 9:* Giao diện cập nhật thẻ ca hôm nay và bảng công cá nhân.
7. **Luồng ngoại lệ:** Check-in ngoài cửa sổ hoặc check-in lặp lại bị từ chối với thông báo hướng dẫn rõ ràng.
8. **Quy tắc nghiệp vụ:** Giờ vào sớm trước giờ bắt đầu ca không được tính dồn làm tăng giờ làm thực tế.
9. **Yêu cầu phi chức năng:** Phản hồi chấm công $< 150\text{ms}$.

---

### 3.4. Biểu đồ Hoạt động phân làn (Activity Diagrams with Swimlanes)

#### 3.4.1. Phân hệ Kho: Biểu đồ Hoạt động Luồng trừ kho tự động khi thanh toán
Biểu đồ mô tả phân làn luồng xử lý giữa 3 thực thể: **Thu ngân**, **Hệ thống Spring Boot**, và **Cơ sở dữ liệu MySQL**:

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 3.2:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Activity_TruKho.svg`  
> ![Hình 3.2: Biểu đồ Hoạt động Luồng trừ kho tự động khi thanh toán](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_TruKho.svg)

##### Phân tích chi tiết Biểu đồ Hoạt động Trừ kho (Hình 3.2):
* **Làn Thu ngân:** Bắt đầu bằng hành động bấm nút "Xác nhận thanh toán & Đóng bàn", chọn phương thức thanh toán, nhập tiền khách đưa hoặc mã tham chiếu chuyển khoản và gửi yêu cầu.
* **Làn Hệ thống Spring Boot:**
  1. Tiếp nhận request, mở khối giao dịch toàn vẹn `@Transactional`.
  2. Gom nhóm tổng lượng tiêu hao của từng nguyên liệu bằng `TreeMap` để tự động sắp xếp theo thứ tự `ma_nl` tăng dần.
  3. Bắt đầu vòng lặp duyệt từng nguyên liệu đã sắp xếp:
     - Gửi truy vấn khóa bi quan `SELECT ... FOR UPDATE` xuống MySQL.
     - Đối chiếu điều kiện rẽ nhánh: Kiểm tra `Số lượng tồn >= Tổng tiêu hao`?
       - Nếu **Không đủ:** Lập tức phát tín hiệu Rollback toàn bộ giao dịch, ném lỗi HTTP 400 Bad Request và dừng toàn bộ quá trình thanh toán.
       - Nếu **Đủ hàng:** Cập nhật trừ lùi tồn kho `soLuongTon = soLuongTon - tongTieuHao`.
       - Ghi bản ghi vào bảng `lich_su_kho` với loại `XUAT_BAN_HANG`.
  4. Sau khi trừ xong tất cả nguyên liệu: kiểm tra điều kiện cảnh báo tồn đỏ $\le$ ngưỡng tối thiểu để bật cờ cảnh báo.
  5. Cập nhật hóa đơn sang `DA_THANH_TOAN`, cập nhật bàn về `TRONG`.
  6. Commit giao dịch thành công và sinh dữ liệu Hóa Đơn chính thức cho thu ngân.
* **Làn CSDL MySQL:** Đảm bảo thực thi tuần tự các lệnh khóa độc quyền, ngăn chặn bán khống và cam kết bền vững dữ liệu (ACID).

---

#### 3.4.2. Phân hệ Bán hàng POS: Biểu đồ Hoạt động Chu trình Phục vụ Tại bàn (SOP)

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 3.3:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Activity_POS.svg`  
> ![Hình 3.3: Biểu đồ Hoạt động Quy trình Bán hàng POS và Xuất hóa đơn](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_POS.svg)

##### Phân tích chi tiết Biểu đồ Hoạt động POS (Hình 3.3):
* Sơ đồ chia làm 5 làn nghiệp vụ: **Khách hàng**, **Nhân viên Phục vụ**, **Quầy Pha chế (Bar)**, **Thu ngân**, và **Hệ thống Server**.
* Thể hiện chu trình chuẩn SOP 9 bước:
  1. Khách vào bàn $\rightarrow$ Phục vụ chọn bàn trên POS.
  2. Phục vụ tư vấn món dựa trên số ly có thể làm `soLyCoTheLam` $\rightarrow$ Thêm món vào giỏ kèm ghi chú khẩu vị.
  3. Bấm "Lưu đơn / In Bar" $\rightarrow$ Bàn chuyển viền xanh lá `DANG_DUNG_NUOC` $\rightarrow$ Quầy Bar nhận Bar Ticket pha chế.
  4. Khi khách gọi thêm món $\rightarrow$ Phục vụ cập nhật món $\rightarrow$ Hệ thống chỉ in Bar Delta cho phần chênh lệch phát sinh.
  5. Khách gọi tính tiền $\rightarrow$ Phục vụ bấm "Yêu cầu thanh toán" $\rightarrow$ Bàn chuyển viền vàng `CHO_THANH_TOAN`.
  6. Thu ngân in Phiếu Tạm Tính (Proforma Bill) $\rightarrow$ Mang ra bàn cho khách kiểm tra đối soát.
  7. Khách xác nhận $\rightarrow$ Thu ngân nhận tiền mặt hoặc quét VietQR.
  8. Thu ngân bấm thanh toán $\rightarrow$ Hệ thống trừ kho BOM, đóng bàn về xanh dương `TRONG`, xuất Hóa Đơn chính thức.

---

#### 3.4.3. Phân hệ Nhân sự: Biểu đồ Hoạt động Chấm công & Chốt ca tự động

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 3.4:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Activity_ChamCong.svg`  
> ![Hình 3.4: Biểu đồ Hoạt động Quy trình Chấm công ca làm việc nhân sự](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_ChamCong.svg)

##### Phân tích chi tiết Biểu đồ Hoạt động Chấm công (Hình 3.4):
* Bao gồm 4 luồng hoạt động chính:
  1. **Luồng Check-in:** Kiểm tra cửa sổ $[start - 30p, end)$. Nếu hợp lệ: tính độ lệch $\Delta_{in} = t_{in} - start$. Nếu $\Delta_{in} \le 15p \rightarrow$ Ghi nhận `DUNG_GIO`; nếu $\Delta_{in} > 15p \rightarrow$ Ghi nhận `DI_TRE`.
  2. **Luồng Check-out:** Chặn nếu $< 1p$. Tính giờ làm clamped $t_{start} = \max(in, start)$ và $t_{end} = \min(out, end)$. Nếu về sớm $> 15p \rightarrow$ Gán `VE_SOM` (hoặc `DI_TRE_VE_SOM`). Nhân `he_so_luong` ra `gio_cong_quy_doi`.
  3. **Luồng Chốt ca tự động (Scheduler):** Quét các ca qua mốc $end + 30p$. Nếu chưa check-in $\rightarrow$ Đánh dấu `VANG` (`gio_check_in = NULL`). Nếu có check-in nhưng quên check-out $\rightarrow$ Đánh dấu `QUEN_CHECK_OUT` (`gio_cong_quy_doi = 0.00`).
  4. **Luồng Điều chỉnh Quản lý:** Quản lý mở modal điều chỉnh giờ vào/ra, bắt buộc nhập lý do, hệ thống tính lại giờ công và lưu vết kiểm toán `nguoiDieuChinh`, `thoiGianDieuChinh`.

---

## CHƯƠNG 4: THIẾT KẾ KIẾN TRÚC PHẦN MỀM & BIỂU ĐỒ LỚP CHI TIẾT

### 4.1. Kiến trúc phân tầng 3 tầng (3-Tier Layered Architecture)
Hệ thống tuân thủ mô hình **Kiến trúc phân tầng 3 tầng chuẩn mực của Java Spring Boot**, đảm bảo nguyên tắc phân tách trách nhiệm cao (*Separation of Concerns*):

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 4.1:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\KienTrucTongThe.svg`  
> ![Hình 4.1: Biểu đồ Kiến trúc phân tầng và thành phần hệ thống](file:///d:/3.1.%20PTTKYC/CK/diagram/KienTrucTongThe.svg)

#### Phân tích chi tiết các tầng kiến trúc (Hình 4.1):
1. **Tầng Giao diện Người dùng (Presentation Tier - Web Client Browser):**
   * Hoạt động trực tiếp trên trình duyệt Client hiện đại.
   * Xây dựng bằng HTML5 ngữ nghĩa, CSS3 độ tương phản cao tối ưu in ấn A4 (`mossary.css`), và Vanilla JavaScript ES6+ bất đồng bộ (*Async/Await, Fetch API*).
   * Gồm 4 màn hình: `index.html` (Đăng nhập), `banhang.html` (POS sơ đồ bàn 3 màu viền), `kho.html` (Kho, Định mức BOM, Sổ nhật ký & KPI), `nhansu.html` (Chấm công 2 chế độ, Lưới phân ca tuần, Danh bạ nhân sự).
   * Giao tiếp với máy chủ qua giao thức HTTP/1.1 RESTful JSON trên cổng **`8080`**.
2. **Tầng Xử lý Nghiệp vụ (Application Tier - Spring Boot Server Port 8080):**
   * Đóng vai trò bộ não xử lý trung tâm:
     * **Controller Layer:** 9 REST Controllers tiếp nhận request, kiểm tra hợp lệ dữ liệu JSR-380, phân quyền RBAC và chuyển tiếp tới Service.
     * **Service Layer:** 3 Core Services (`DonHangService`, `KhoService`, `NhanSuService`) chứa toàn bộ quy tắc nghiệp vụ cốt lõi, quản lý giao dịch nguyên tử `@Transactional`.
     * **Scheduler & Time Abstraction:** `ChotCaTuDongScheduler` chạy ngầm định kỳ; tiêm `java.time.Clock` phục vụ kiểm thử dịch chuyển thời gian linh hoạt.
     * **Repository Layer:** 11 JPA Repositories kế thừa `JpaRepository`, thực thi các câu lệnh JPQL tối ưu và khóa dữ liệu `FOR UPDATE`.
3. **Tầng Cơ sở Dữ liệu (Database Tier - MySQL Server Port 3306):**
   * Hệ quản trị CSDL quan hệ **MySQL 8.0+ / MariaDB 10.4+** trên XAMPP.
   * Lưu trữ bền vững 11 bảng chuẩn hóa 3NF, bảo toàn tính toàn vẹn tham chiếu qua khóa ngoại và bảng mã `utf8mb4`.

### 4.2. Biểu đồ Triển khai hệ thống vật lý (Deployment Diagram)

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 4.2:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\TrienKhai.svg`  
> ![Hình 4.2: Biểu đồ Triển khai hệ thống trên môi trường máy chủ Localhost](file:///d:/3.1.%20PTTKYC/CK/diagram/TrienKhai.svg)

#### Phân tích chi tiết Biểu đồ Triển khai (Hình 4.2):
* **Máy trạm Client (PC / Tablet / POS Terminal):** Chạy trình duyệt Web tải các trang SPA tĩnh từ máy chủ và gửi nhận JSON. Kết nối máy in nhiệt hóa đơn K80 và máy in phiếu pha chế quầy Bar K58 qua driver in ấn hệ điều hành.
* **Máy chủ Ứng dụng (Application Server - Localhost):**
  * Môi trường thực thi: OpenJDK 17 LTS / OpenJDK 25.
  * Máy chủ nhúng: **Embedded Apache Tomcat 10.1.20** lắng nghe trên cổng TCP **`8080`**.
  * Chạy file ứng dụng Spring Boot đóng gói `quanly-cafe-0.0.1-SNAPSHOT.jar`.
* **Máy chủ Cơ sở Dữ liệu (Database Server - XAMPP):**
  * Tiến trình `mysqld.exe` lắng nghe trên cổng TCP **`3306`**.
  * Kết nối từ Spring Boot qua bể kết nối **HikariCP** tốc độ cao và trình điều khiển `mysql-connector-j` 8.3.0.

### 4.3. Cấu trúc đóng gói mã nguồn Java Spring Boot (Package Diagram)

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 4.3:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Package_SpringBoot.svg`  
> ![Hình 4.3: Biểu đồ Cấu trúc đóng gói mã nguồn Java Spring Boot](file:///d:/3.1.%20PTTKYC/CK/diagram/Package_SpringBoot.svg)

#### Phân tích chi tiết các Package:
* `com.cafe`: Chứa file cấu hình gốc `QuanLyCafeApplication.java` khai báo `@EnableScheduling` và `@Bean Clock`.
* `com.cafe.entity`: Chứa 11 lớp Entity JPA chuẩn 3NF ánh xạ 11 bảng CSDL.
* `com.cafe.dto`: Chứa 21 lớp DTO truyền tải dữ liệu phân tách rõ rệt giữa request và response.
* `com.cafe.repository`: Chứa 11 interface kế thừa `JpaRepository` với các truy vấn tùy biến khóa bi quan `FOR UPDATE`.
* `com.cafe.service`: Chứa 3 lớp dịch vụ nghiệp vụ chính (`DonHangService`, `KhoService`, `NhanSuService`).
* `com.cafe.scheduler`: Chứa lớp `ChotCaTuDongScheduler` thực thi quét ca quá hạn định kỳ.
* `com.cafe.controller`: Chứa 9 REST Controllers điều phối các luồng API của hệ thống.
* `src/main/resources/static`: Chứa toàn bộ tài nguyên giao diện Web tĩnh (`index.html`, `banhang.html`, `kho.html`, `nhansu.html`, css, js).

### 4.4. Biểu đồ Lớp thiết kế chi tiết (Design Class Diagram - 3 Tầng)

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 4.4:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\ClassDiagram.svg`  
> ![Hình 4.4: Biểu đồ Lớp thiết kế chi tiết 3 tầng](file:///d:/3.1.%20PTTKYC/CK/diagram/ClassDiagram.svg)

#### Phân tích chi tiết các mối quan hệ cấu trúc trong Biểu đồ Lớp (Hình 4.4):
1. **Quan hệ Hợp thành chặt chẽ (Composition - Hình thoi đặc):**
   * Giữa lớp `HoaDon` và `ChiTietHoaDon`: Chi tiết hóa đơn không thể tồn tại độc lập nếu thiếu hóa đơn chứa nó. Khi hóa đơn bị hủy hoặc xóa, toàn bộ các dòng chi tiết sẽ bị xóa theo (*CascadeType.ALL, orphanRemoval = true*).
2. **Lớp liên kết (Association Class):**
   * Lớp `CongThuc` đóng vai trò là một Association Class giải quyết mối quan hệ Nhiều – Nhiều ($M:N$) giữa thực thể `MonNuoc` và `NguyenLieu`, mang thuộc tính định lượng tiêu hao `dinhMuc : BigDecimal`.
3. **Quan hệ Liên kết Một - Nhiều:**
   * `Ban` (1) --- (0..N) `HoaDon`: Một bàn phục vụ nhiều lượt đơn theo thời gian.
   * `NhanVien` (1) --- (0..N) `HoaDon`: Phân định rõ nhân viên phục vụ tạo đơn (`nhanVien`) và nhân viên thu ngân chốt tiền (`nhanVienThuNgan`).
   * `NguyenLieu` (1) --- (0..N) `LichSuKho`: Mọi biến động xuất nhập đều tham chiếu về nguyên liệu tương ứng.
4. **Thuộc tính dẫn xuất (*Derived Attribute*):**
   * `/thanhTien` trong `ChiTietHoaDon` = `soLuong * donGiaBan`.
   * `/tongTienThanhToan` trong `HoaDon` = `tongTienGoc - chietKhau`.
5. **Thuộc tính quản lý phiên bản (Optimistic Locking):**
   * Trường `phienBan : Integer` trong `HoaDon` được gắn `@Version` để ngăn chặn xung đột ghi đè dữ liệu đồng thời.

---

## CHƯƠNG 5: THIẾT KẾ CƠ SỞ DỮ LIỆU & BẢNG TỪ ĐIỂN DỮ LIỆU

### 5.1. Mô hình Thực thể Quan hệ CSDL (ERD - Crow's Foot Notation)
Mô hình ERD được chuẩn hóa đạt chuẩn 3NF bao gồm 11 bảng quan hệ trên MySQL InnoDB:

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 5.1:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\ERD_DuLieu.svg`  
> ![Hình 5.1: Mô hình Thực thể Quan hệ CSDL 11 thực thể](file:///d:/3.1.%20PTTKYC/CK/diagram/ERD_DuLieu.svg)

#### Phân tích chi tiết Mô hình ERD (Hình 5.1):
* **Bản số quan hệ chuẩn mực:**
  * `nhan_vien` (1) --- (0..N) `phan_ca` và `ca_lam` (1) --- (0..N) `phan_ca`: Phân công ca làm việc theo tuần cho nhân sự.
  * `phan_ca` (1) --- (0..1) `cham_cong`: Mỗi phân ca có tối đa 1 bản ghi chấm công thực tế.
  * `ban` (1) --- (0..N) `hoa_don`: Bàn liên kết với hóa đơn; với đơn mang đi `ma_ban` nhận `NULL`.
  * `hoa_don` (1) --- (1..N) `chi_tiet_hoa_don`: Một hóa đơn chứa nhiều dòng chi tiết món.
  * `mon_nuoc` (1) --- (0..N) `cong_thuc` và `nguyen_lieu` (1) --- (0..N) `cong_thuc`: Bảng kết hợp cấu thành định mức BOM.
  * `nguyen_lieu` (1) --- (0..N) `lich_su_kho`: Bảng thứ 11 lưu vết mọi biến động nhập, xuất và hao hụt kho.

### 5.2. Bảng Từ điển Dữ liệu chi tiết cho 11 bảng CSDL (Data Dictionary)

#### 5.2.1. Bảng 5.1: Bảng `nhan_vien` (Hồ sơ nhân viên & Tài khoản)
*Khóa chính:* `ma_nv` | *Mục đích:* Lưu trữ thông tin nhân sự và phân quyền 3 vai trò.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_nv` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã định danh nhân viên duy nhất |
| `ho_ten` | `VARCHAR(100)` | | No | Không rỗng | Họ và tên đầy đủ |
| `so_dien_thoai`| `VARCHAR(20)` | | Yes | Chuỗi số liên hệ | Số điện thoại di động |
| `ten_dang_nhap`| `VARCHAR(50)` | | No | `UNIQUE`, không trùng lặp | Tên tài khoản đăng nhập |
| `mat_khau` | `VARCHAR(255)` | | No | Mật khẩu xác thực | Mật khẩu đăng nhập |
| `vai_tro` | `VARCHAR(20)` | | No | `'QUAN_LY'`, `'THU_NGAN'`, `'PHUC_VU'` | Vai trò phân quyền RBAC |
| `trang_thai` | `VARCHAR(20)` | | No | `'DANG_LAM'`, `'DA_NGHI'` | Trạng thái hoạt động (Khóa mềm) |

---

#### 5.2.2. Bảng 5.2: Bảng `ca_lam` (Danh mục Ca làm việc)
*Khóa chính:* `ma_ca` | *Mục đích:* Định nghĩa các khung ca làm việc và hệ số lương.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_ca` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã định danh ca làm việc |
| `ten_ca` | `VARCHAR(50)` | | No | "Ca Sáng", "Ca Chiều", "Ca Tối" | Tên gọi ca làm |
| `gio_bat_dau` | `TIME` | | No | Định dạng `HH:mm:ss` | Giờ bắt đầu ca làm việc |
| `gio_ket_thuc`| `TIME` | | No | `gio_ket_thuc > gio_bat_dau` | Giờ kết thúc ca làm việc |
| `he_so_luong` | `DECIMAL(3,2)` | | No | Default `1.00`, Ca Tối `1.20` | Hệ số lương quy đổi giờ công |

---

#### 5.2.3. Bảng 5.3: Bảng `phan_ca` (Lịch phân ca tuần)
*Khóa chính:* `ma_phan_ca` | *Khóa ngoại:* `ma_nv` $\rightarrow$ `nhan_vien`, `ma_ca` $\rightarrow$ `ca_lam`.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_phan_ca` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã phân ca duy nhất |
| `ma_nv` | `INT` | **FK** | No | Tham chiếu `nhan_vien(ma_nv)` | Nhân viên được phân ca |
| `ma_ca` | `INT` | **FK** | No | Tham chiếu `ca_lam(ma_ca)` | Ca làm việc phân công |
| `ngay_lam` | `DATE` | | No | Định dạng `YYYY-MM-DD` | Ngày làm việc trong tuần |
| `ghi_chu` | `VARCHAR(255)` | | Yes | Ghi chú điều phối ca | Ghi chú ca làm |

*Ràng buộc duy nhất (Unique Constraint):* `UNIQUE KEY uq_phan_ca (ma_nv, ngay_lam, ma_ca)`.

---

#### 5.2.4. Bảng 5.4: Bảng `cham_cong` (Bản ghi chấm công chuẩn hóa 16 cột)
*Khóa chính:* `ma_cham_cong` | *Khóa ngoại:* `ma_phan_ca` $\rightarrow$ `phan_ca`, `ma_nv` $\rightarrow$ `nhan_vien`, `ma_ca` $\rightarrow$ `ca_lam`, `nguoi_dieu_chinh` $\rightarrow$ `nhan_vien`.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_cham_cong` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã bản ghi chấm công duy nhất |
| `ma_phan_ca` | `INT` | **FK** | Yes | Tham chiếu `phan_ca(ma_phan_ca)` | Phân ca gốc liên kết (1:1) |
| `ma_nv` | `INT` | **FK** | No | Tham chiếu `nhan_vien(ma_nv)` | Nhân viên thực hiện chấm công |
| `ma_ca` | `INT` | **FK** | No | Tham chiếu `ca_lam(ma_ca)` | Ca làm việc chấm công |
| `ngay_lam` | `DATE` | | No | Ngày làm việc | Ngày ghi nhận công |
| `gio_check_in` | `DATETIME` | | Yes | Null khi vắng mặt | Thời điểm vào ca thực tế |
| `gio_check_out`| `DATETIME` | | Yes | Null khi đang làm/quên ra | Thời điểm ra ca thực tế |
| `so_phut_tre` | `INT` | | No | Default `0`, tính theo $\Delta_{in}$ | Số phút đi trễ quá 15 phút |
| `so_phut_ve_som`| `INT` | | No | Default `0`, tính theo $\Delta_{out}$ | Số phút về sớm trước ca |
| `so_gio_lam` | `DECIMAL(4,2)` | | No | Default `0.00`, giờ thực tế clamped | Số giờ làm việc thực tế |
| `gio_cong_quy_doi`|`DECIMAL(4,2)`| | No | `= so_gio_lam * he_so_luong` | Giờ công tính lương sau nhân hệ số |
| `trang_thai` | `VARCHAR(30)` | | No | `'DUNG_GIO'`, `'DI_TRE'`, `'VE_SOM'`, `'DI_TRE_VE_SOM'`, `'VANG'`, `'QUEN_CHECK_OUT'` | Trạng thái chấp hành chấm công |
| `ghi_chu` | `VARCHAR(255)` | | Yes | Tùy chọn lý do | Ghi chú nghiệp vụ |
| `nguoi_dieu_chinh`|`INT` | **FK** | Yes | Tham chiếu `nhan_vien(ma_nv)` | Quản lý thực hiện sửa công |
| `ly_do_dieu_chinh`|`VARCHAR(255)`| | Yes | Bắt buộc khi có điều chỉnh | Lý do giải trình sửa công |
| `thoi_gian_dieu_chinh`|`DATETIME`| | Yes | Thời điểm lưu kiểm toán | Dấu vết kiểm toán điều chỉnh |

*Ràng buộc duy nhất:* `UNIQUE KEY uq_cham_cong (ma_nv, ma_ca, ngay_lam)`.

---

#### 5.2.5. Bảng 5.5: Bảng `ban` (Sơ đồ bàn quán cà phê)
*Khóa chính:* `ma_ban` | *Mục đích:* Quản lý danh mục bàn và 3 trạng thái viền màu thời gian thực.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_ban` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã định danh bàn duy nhất |
| `ten_ban` | `VARCHAR(50)` | | No | `UNIQUE` (Bàn 01 đến Bàn 10) | Tên bàn hiển thị trên POS |
| `vi_tri` | `VARCHAR(100)`| | Yes | "Tầng trệt", "Sân vườn", "Lầu 1" | Vị trí không gian quán |
| `so_ghe` | `INT` | | No | Default `4` | Số lượng ghế tại bàn |
| `trang_thai` | `VARCHAR(30)` | | No | `'TRONG'`, `'DANG_DUNG_NUOC'`, `'CHO_THANH_TOAN'` | Trạng thái phục vụ (3 màu viền) |

---

#### 5.2.6. Bảng 5.6: Bảng `mon_nuoc` (Thực đơn đồ uống)
*Khóa chính:* `ma_mon` | *Mục đích:* Lưu trữ danh mục 12 món đồ uống chuẩn và giá niêm yết.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_mon` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã món nước duy nhất |
| `ten_mon` | `VARCHAR(100)`| | No | `UNIQUE` (Cà phê sữa, Trà đào...) | Tên gọi món nước |
| `danh_muc` | `VARCHAR(50)` | | Yes | "Cà phê", "Trà trái cây", "Đá xay" | Phân loại nhóm thức uống |
| `don_gia` | `DECIMAL(10,2)`| | No | `don_gia >= 0` | Đơn giá bán lẻ niêm yết (VNĐ) |
| `hinh_anh` | `VARCHAR(255)`| | Yes | Đường dẫn ảnh minh họa | Ảnh đại diện món |
| `trang_thai` | `VARCHAR(20)` | | No | `'DANG_BAN'`, `'NGUNG_BAN'` | Trạng thái kinh doanh |

---

#### 5.2.7. Bảng 5.7: Bảng `nguyen_lieu` (Kho 13 nguyên vật liệu pha chế)
*Khóa chính:* `ma_nl` | *Mục đích:* Quản lý tồn kho nguyên liệu phục vụ pha chế đồ uống.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_nl` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã nguyên liệu duy nhất |
| `ten_nl` | `VARCHAR(100)`| | No | `UNIQUE` (Hạt Robusta, Sữa đặc...) | Tên gọi nguyên vật liệu |
| `don_vi_tinh` | `VARCHAR(20)` | | No | `'g'`, `'ml'`, `'tui'`, `'hop'`, `'qua'` | Đơn vị đo lường hao phí |
| `soLuongTon` | `DECIMAL(10,2)`| | No | Default `0.00`, không được âm | Số lượng tồn kho thực tế |
| `mucTonToiThieu`|`DECIMAL(10,2)`| | No | Default `5.00` | Ngưỡng an toàn kích hoạt cảnh báo đỏ |
| `don_gia_nhap` | `DECIMAL(10,2)`| | No | Default `0.00`, cập nhật theo WAC | Giá vốn bình quân gia quyền |

---

#### 5.2.8. Bảng 5.8: Bảng `cong_thuc` (Định mức kỹ thuật BOM)
*Khóa chính:* `ma_cong_thuc` | *Khóa ngoại:* `ma_mon` $\rightarrow$ `mon_nuoc`, `ma_nl` $\rightarrow$ `nguyen_lieu`.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_cong_thuc`| `INT` | **PK** | No | `AUTO_INCREMENT` | Mã định mức duy nhất |
| `ma_mon` | `INT` | **FK** | No | Tham chiếu `mon_nuoc(ma_mon)` | Món nước được cấu hình |
| `ma_nl` | `INT` | **FK** | No | Tham chiếu `nguyen_lieu(ma_nl)` | Nguyên liệu thành phần |
| `dinh_muc` | `DECIMAL(10,2)`| | No | `dinh_muc > 0` | Lượng tiêu hao cho đúng 1 suất ly |
| `ghi_chu` | `VARCHAR(255)`| | Yes | Hướng dẫn kỹ thuật pha chế | Ghi chú công thức |

*Ràng buộc duy nhất:* `UNIQUE KEY uq_cong_thuc (ma_mon, ma_nl)`.

---

#### 5.2.9. Bảng 5.9: Bảng `hoa_don` (Hóa đơn giao dịch bán hàng)
*Khóa chính:* `ma_hoa_don` | *Khóa ngoại:* `ma_ban` $\rightarrow$ `ban`, `ma_nv` $\rightarrow$ `nhan_vien`, `ma_nv_thu_ngan` $\rightarrow$ `nhan_vien`.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_hoa_don` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã số hóa đơn duy nhất |
| `ma_ban` | `INT` | **FK** | Yes | Null đối với đơn mang đi | Bàn phục vụ đơn hàng |
| `ma_nv` | `INT` | **FK** | No | Tham chiếu `nhan_vien(ma_nv)` | Phục vụ tạo đơn hàng |
| `ma_nv_thu_ngan`|`INT` | **FK** | Yes | Tham chiếu `nhan_vien(ma_nv)` | Thu ngân chốt thanh toán |
| `thoi_gian_tao`| `DATETIME` | | No | Default `CURRENT_TIMESTAMP` | Thời điểm khách gọi món |
| `thoi_gian_yeu_cau_tt`|`DATETIME`| | Yes | Null khi đang dùng nước | Thời điểm chuyển sang chờ thanh toán |
| `thoi_gian_thanh_toan`|`DATETIME`| | Yes | Null khi chưa thanh toán | Thời điểm hoàn tất giao dịch |
| `loai_don` | `VARCHAR(20)` | | No | `'TAI_BAN'`, `'MANG_DI'` | Hình thức phục vụ |
| `tong_tien_goc`| `DECIMAL(12,2)`| | No | Default `0.00` | Tổng giá trị món trước giảm giá |
| `chiet_khau` | `DECIMAL(12,2)`| | No | Default `0.00` | Số tiền giảm giá được phê duyệt |
| `tong_tien_thanh_toan`|`DECIMAL(12,2)`| | No | `= tong_tien_goc - chiet_khau` | Tổng tiền thực thu của khách |
| `tien_khach_dua`|`DECIMAL(12,2)`| | Yes | Số tiền mặt khách đưa | Số tiền mặt nhận vào |
| `tien_thua` | `DECIMAL(12,2)`| | Yes | `= tien_khach_dua - phai_thu` | Tiền thừa trả lại cho khách |
| `phuong_thuc_tt`|`VARCHAR(20)`| | Yes | `'TIEN_MAT'`, `'CHUYEN_KHOAN'` | Hình thức thanh toán |
| `ma_tham_chieu`|`VARCHAR(100)`| | Yes | Mã giao dịch chuyển khoản ngân hàng | Mã tham chiếu đối soát |
| `xac_nhan_chuyen_khoan`|`BOOLEAN`| | No | Default `FALSE` | Cờ xác nhận tiền đã vào tài khoản |
| `trang_thai` | `VARCHAR(30)` | | No | `'DANG_PHUC_VU'`, `'CHO_THANH_TOAN'`, `'DA_THANH_TOAN'`, `'DA_HUY'` | Vòng đời trạng thái đơn |
| `yeu_cau_tao` | `VARCHAR(255)`| | Yes | Khóa Idempotency tạo đơn | Chống tạo trùng đơn khi mạng lag |
| `yeu_cau_thanh_toan`|`VARCHAR(255)`| | Yes | Khóa Idempotency thanh toán | Chống trừ kho 2 lần khi retry |
| `phien_ban` | `INT` | | No | Default `0`, `@Version` | Quản lý khóa lạc quan |

---

#### 5.2.10. Bảng 5.10: Bảng `chi_tiet_hoa_don` (Chi tiết dòng món trong đơn)
*Khóa chính:* `ma_cthd` | *Khóa ngoại:* `ma_hoa_don` $\rightarrow$ `hoa_don`, `ma_mon` $\rightarrow$ `mon_nuoc`.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_cthd` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã dòng chi tiết duy nhất |
| `ma_hoa_don` | `INT` | **FK** | No | Tham chiếu `hoa_don(ma_hoa_don)` | Hóa đơn chứa dòng món |
| `ma_mon` | `INT` | **FK** | No | Tham chiếu `mon_nuoc(ma_mon)` | Món nước được gọi |
| `so_luong` | `INT` | | No | `so_luong > 0`, tối đa 99 | Tổng số lượng ly hiện tại |
| `so_luong_da_in_bar`|`INT` | | No | Default `0` | Số lượng đã in phiếu xuống Bar |
| `don_gia_ban` | `DECIMAL(10,2)`| | No | Chụp đơn giá tại thời điểm gọi | Snapshot giá bán không bị đổi |
| `thanh_tien` | `DECIMAL(12,2)`| | No | `= so_luong * don_gia_ban` | Thành tiền của dòng món |
| `ghi_chu` | `VARCHAR(255)`| | Yes | Ví dụ: "ít đường, không đá" | Ghi chú yêu cầu pha chế |

*Ràng buộc duy nhất:* `UNIQUE KEY uq_cthd (ma_hoa_don, ma_mon)`.

---

#### 5.2.11. Bảng 5.11: Bảng `lich_su_kho` (Sổ nhật ký biến động kho – Bảng thứ 11)
*Khóa chính:* `ma_gd` | *Khóa ngoại:* `ma_nl` $\rightarrow$ `nguyen_lieu`, `ma_nv` $\rightarrow$ `nhan_vien`, `ma_hoa_don` $\rightarrow$ `hoa_don`.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_gd` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã giao dịch kho duy nhất |
| `ma_nl` | `INT` | **FK** | No | Tham chiếu `nguyen_lieu(ma_nl)` | Nguyên liệu phát sinh biến động |
| `ma_nv` | `INT` | **FK** | Yes | Tham chiếu `nhan_vien(ma_nv)` | Nhân sự thực hiện giao dịch |
| `ma_hoa_don` | `INT` | **FK** | Yes | Tham chiếu `hoa_don(ma_hoa_don)` | Hóa đơn bán lẻ liên kết (nếu xuất) |
| `loai_gd` | `VARCHAR(30)` | | No | `'NHAP_KHO'`, `'XUAT_BAN_HANG'`, `'KIEM_KE_HAO_HUT'` | Loại giao dịch biến động |
| `so_luong_thay_doi`|`DECIMAL(10,2)`| | No | Dương nếu nhập, âm nếu xuất/hao | Lượng biến động số học |
| `ton_truoc` | `DECIMAL(10,2)`| | No | Số tồn ngay trước giao dịch | Tồn kho trước biến động |
| `ton_sau` | `DECIMAL(10,2)`| | No | `= ton_truoc + so_luong_thay_doi` | Tồn kho sau biến động |
| `don_gia` | `DECIMAL(10,2)`| | No | Đơn giá vốn tại thời điểm phát sinh | Đơn giá áp dụng |
| `thanh_tien` | `DECIMAL(12,2)`| | No | `= |so_luong| * don_gia` | Giá trị tài chính của biến động |
| `nha_cung_cap` | `VARCHAR(100)`| | Yes | Tên nhà cung ứng khi nhập hàng | Nhà cung cấp nguyên liệu |
| `thoi_gian` | `DATETIME` | | No | Default `CURRENT_TIMESTAMP` | Thời điểm ghi nhận giao dịch |
| `ghi_chu` | `VARCHAR(255)`| | Yes | Lý do kiểm kê hoặc ghi chú nhập | Ghi chú kiểm toán |

---

### 5.3. Ràng buộc toàn vẹn dữ liệu và chuẩn hóa dạng chuẩn 3NF
* **Đạt chuẩn 1NF:** Tất cả các thuộc tính trong 11 bảng đều mang tính nguyên tử (*Atomic*), không chứa trường dữ liệu đa trị hay tập lặp.
* **Đạt chuẩn 2NF:** Tất cả các bảng đều có khóa chính đơn nhân tạo (`ma_nv`, `ma_mon`, `ma_hoa_don`...). Toàn bộ các thuộc tính không khóa đều phụ thuộc hàm đầy đủ vào khóa chính, không có phụ thuộc một phần vào khóa chính phức hợp.
* **Đạt chuẩn 3NF:** Không tồn tại phụ thuộc bắc cầu (*Transitive Dependency*). Các thuộc tính định danh người dùng, danh mục nguyên vật liệu, định mức công thức đều được tách riêng vào các bảng chuyên biệt và liên kết qua khóa ngoại chuẩn hóa.
* **Toàn vẹn tham chiếu (*Referential Integrity*):**
  * Sử dụng `ON DELETE RESTRICT` để ngăn chặn việc xóa nguyên liệu hoặc món nước đang tồn tại trong công thức BOM hoặc lịch sử giao dịch kho, bảo đảm không xuất hiện bản ghi mồ côi (*Orphan Records*).

---

## CHƯƠNG 6: THIẾT KẾ ĐỘNG HỌC HỆ THỐNG & CƠ CHẾ GIAO DỊCH NÂNG CAO

### 6.1. Biểu đồ Tuần tự tương tác đa tầng (Multi-tier Sequence Diagrams)

#### 6.1.1. Luồng Trừ kho tự động khi thanh toán chống Deadlock (@Transactional)

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 6.1:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Sequence_TruKho.svg`  
> ![Hình 6.1: Biểu đồ Tuần tự đa tầng luồng trừ kho tự động @Transactional](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_TruKho.svg)

##### Phân tích chi tiết Biểu đồ Tuần tự Trừ kho (Hình 6.1):
1. **Khởi phát:** Thu ngân bấm "Xác nhận thanh toán & Đóng bàn" trên `banhang.html`. Client gửi request `POST /api/order/{id}/thanh-toan`.
2. **Tiếp nhận & Mở giao dịch:** `DonHangController` gọi `donHangService.thanhToanDonHang(id, dto, sessionUser)`. Phương thức mở khối giao dịch toàn vẹn `@Transactional(rollbackFor = Exception.class)`.
3. **Khóa đơn hàng:** Thực thi truy vấn `SELECT * FROM hoa_don WHERE ma_hoa_don = ? FOR UPDATE` để ngăn các luồng khác can thiệp đồng thời vào đơn này.
4. **Ủy quyền phân hệ Kho:** Gọi `khoService.truKhoTuDong(hoaDon)`.
5. **Vòng lặp bóc tách định mức BOM:**
   * Tập hợp toàn bộ nguyên liệu qua `CongThucRepository.findByMonNuoc(monNuoc)`.
   * Gom tổng lượng tiêu hao bằng `TreeMap<Integer, BigDecimal>` theo `ma_nl` tăng dần.
   * Khóa từng dòng nguyên liệu bằng `SELECT * FROM nguyen_lieu WHERE ma_nl = ? FOR UPDATE`.
   * Kiểm tra điều kiện tồn kho: Nếu `soLuongTon < tongTieuHao`, ném ngay `ResponseStatusException(HttpStatus.BAD_REQUEST, "Kho khong du nguyen lieu...")`. Giao dịch tự động Rollback sạch 100%.
   * Nếu đủ hàng: Trừ tồn kho, lưu bản ghi `XUAT_BAN_HANG` vào `LichSuKhoRepository`.
6. **Cam kết & Đóng bàn:** Cập nhật hóa đơn sang `DA_THANH_TOAN`, cập nhật bàn về `TRONG`. Giao dịch Commit thành công. Trả dữ liệu xuất Hóa Đơn nhiệt chính thức.

---

#### 6.1.2. Luồng Bán hàng POS 4 giai đoạn: Mở bàn, In Bar Delta, Tạm tính & Thanh toán

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 6.2:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Sequence_POS.svg`  
> ![Hình 6.2: Biểu đồ Tuần tự 4 giai đoạn Bán hàng POS và In bill](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_POS.svg)

##### Phân tích chi tiết Biểu đồ Tuần tự POS (Hình 6.2):
* **Giai đoạn 1 (Mở bàn & Lưu đơn):** Phục vụ chọn bàn trống, thêm món $\rightarrow$ `POST /api/order/tao-don` $\rightarrow$ Lưu `HoaDon` (`DANG_PHUC_VU`), gán `soLuongDaInBar = soLuong`, chuyển bàn sang viền xanh lá, trả về `phieuInBar`.
* **Giai đoạn 2 (Gọi thêm món & In Bar Delta):** Phục vụ gọi thêm món $\rightarrow$ `PUT /api/order/{id}/mon` $\rightarrow$ Tính chênh lệch $\Delta = \text{soLuongMoi} - \text{soLuongDaInBar} > 0$ và chỉ trả về các món bổ sung trong `phieuInBarBoSung`.
* **Giai đoạn 3 (Yêu cầu thanh toán & In tạm tính):** Phục vụ gửi `POST /api/order/{id}/yeu-cau-thanh-toan` $\rightarrow$ Bàn và đơn chuyển `CHO_THANH_TOAN` (viền vàng). Thu ngân bấm "In Phiếu Tạm Tính" xuất bill kiểm tra kèm mã VietQR động.
* **Giai đoạn 4 (Xác nhận thanh toán & Đóng bàn):** Thu ngân gửi `POST /api/order/{id}/thanh-toan` $\rightarrow$ Trừ kho BOM, đóng bàn về xanh dương `TRONG`, in Hóa Đơn chính thức.

---

#### 6.1.3. Luồng Chấm công nhân sự: Check-in, Check-out & Chốt ca tự động Scheduler

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 6.3:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Sequence_ChamCong.svg`  
> ![Hình 6.3: Biểu đồ Tuần tự quy trình Check-in và Chốt ca tự động Scheduler](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_ChamCong.svg)

##### Phân tích chi tiết Biểu đồ Tuần tự Chấm công (Hình 6.3):
* Nhân viên gửi `POST /api/cham-cong/check-in` kèm `maPhanCa`. `NhanSuService` dùng Bean `Clock` lấy giờ máy chủ, kiểm tra cửa sổ 30 phút, tính số phút trễ $\Delta_{in}$ và lưu trạng thái `DUNG_GIO` hoặc `DI_TRE`.
* Khi ra ca, gửi `POST /api/cham-cong/check-out/{id}`. Hệ thống tính giờ làm clamped, kiểm tra về sớm và nhân hệ số lương ra giờ công quy đổi.
* Song song đó, tiến trình `ChotCaTuDongScheduler` kích hoạt mỗi 15 phút, gọi `nhanSuService.chotCaTuDong()` để quét các ca quá hạn, tự động đánh dấu `VANG` hoặc `QUEN_CHECK_OUT`.

---

### 6.2. Biểu đồ Máy trạng thái đối tượng (State Machine Diagrams)
Mô hình hóa vòng đời và sự chuyển đổi trạng thái của 3 thực thể cốt lõi: **Bàn (`Ban`)**, **Hóa đơn (`HoaDon`)**, và **Chấm công (`ChamCong`)**:

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 6.4:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\StateMachine_TrangThai.svg`  
> ![Hình 6.4: Biểu đồ Máy trạng thái vòng đời Bàn, Hóa đơn và Chấm công](file:///d:/3.1.%20PTTKYC/CK/diagram/StateMachine_TrangThai.svg)

#### Phân tích chi tiết Biểu đồ Máy trạng thái (Hình 6.4):
1. **Vòng đời Bàn (`Ban`):**
   * Khởi tạo: `TRONG` (Viền xanh dương `#0D6EFD`).
   * Sự kiện `taoDonHangMoi` $\rightarrow$ Chuyển sang `DANG_DUNG_NUOC` (Viền xanh lá `#198754`, hiển thị tiền tạm tính).
   * Sự kiện `yeuCauThanhToan` $\rightarrow$ Chuyển sang `CHO_THANH_TOAN` (Viền vàng `#D97706`, khóa sửa món).
   * Sự kiện `quayLaiPhucVu` $\rightarrow$ Quay lại `DANG_DUNG_NUOC` để gọi thêm món.
   * Sự kiện `xacNhanThanhToan` hoặc `huyDonHang` $\rightarrow$ Giải phóng bàn trở về `TRONG`.
2. **Vòng đời Hóa đơn (`HoaDon`):**
   * Khởi tạo: `DANG_PHUC_VU` (Cho phép thêm/bớt món, in Bar Delta).
   * Sự kiện `yeuCauThanhToan` $\rightarrow$ Chuyển sang `CHO_THANH_TOAN` (In bill tạm tính).
   * Sự kiện `quayLaiPhucVu` $\rightarrow$ Quay về `DANG_PHUC_VU`.
   * Sự kiện `thanhToanThanhCong` $\rightarrow$ Chuyển sang trạng thái kết thúc `DA_THANH_TOAN` (Bất biến, không thể sửa/xóa).
   * Sự kiện `huyDon` $\rightarrow$ Chuyển sang trạng thái kết thúc `DA_HUY`.
3. **Vòng đời Chấm công (`ChamCong`):**
   * Check-in trong ca: `DUNG_GIO` (trễ $\le 15$p) hoặc `DI_TRE` (trễ $> 15$p).
   * Check-out kết ca: có thể giữ nguyên hoặc chuyển sang `VE_SOM` / `DI_TRE_VE_SOM`.
   * Tác vụ Scheduler quét quá hạn: chuyển thành `VANG` (nếu không check-in) hoặc `QUEN_CHECK_OUT` (nếu quên check-out).
   * Quản lý điều chỉnh ngoại lệ: chuyển sang trạng thái hợp lệ có kèm lý do giải trình.

---

### 6.3. Các giải pháp kỹ thuật nâng cao giải quyết tranh chấp đồng thời

#### 6.3.1. Thuật toán Sắp xếp khóa chống Deadlock trong giao dịch Trừ kho
* **Bối cảnh nguy cơ Deadlock:** Giả sử hai đơn hàng được thanh toán đồng thời tại cùng một phần nghìn giây. Đơn 1 chứa (Món A: dùng Cà phê hạt `maNL=1` và Sữa đặc `maNL=2`). Đơn 2 chứa (Món B: dùng Sữa đặc `maNL=2` và Cà phê hạt `maNL=1`). Nếu không có trật tự khóa cố định, Đơn 1 khóa `maNL=1` và chờ `maNL=2`, trong khi Đơn 2 khóa `maNL=2` và chờ `maNL=1` $\rightarrow$ Xảy ra hiện tượng kẹt vòng Deadlock (*Circular Wait*), khiến hệ thống bị treo hoặc CSDL buộc phải giết chết một giao dịch.
* **Giải pháp đã hiện thực:** Trong phương thức `KhoService.truKhoTuDong`, hệ thống sử dụng `TreeMap<Integer, BigDecimal>` để gộp tổng hao phí và tự động sắp xếp theo thứ tự mã nguyên liệu tăng dần:
  ```java
  Map<Integer, BigDecimal> tongTieuHaoMap = new TreeMap<>();
  // Tự động sắp xếp tăng dần: maNL_1 < maNL_2 < maNL_3 ...
  for (Map.Entry<Integer, BigDecimal> entry : tongTieuHaoMap.entrySet()) {
      NguyenLieu nl = nguyenLieuRepository.findByIdForUpdate(entry.getKey())
          .orElseThrow(...);
      // Thực thi trừ kho an toàn
  }
  ```
  Nhờ việc mọi luồng thanh toán đều bắt buộc phải yêu cầu khóa theo cùng một thứ tự tăng dần duy nhất, điều kiện "Chờ đợi vòng tròn" của Coffman bị triệt tiêu hoàn toàn, bảo đảm **hệ thống không bao giờ xảy ra Deadlock 100%**.

#### 6.3.2. Cơ chế Khóa bi quan (`Pessimistic Locking`) chống bán khống nguyên liệu
* Để ngăn chặn hai thu ngân cùng bán món nước khi nguyên liệu chỉ còn đủ cho 1 ly, câu lệnh truy vấn khóa độc quyền được áp dụng tại repository:
  ```java
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT nl FROM NguyenLieu nl WHERE nl.maNL = :id")
  Optional<NguyenLieu> findByIdForUpdate(@Param("id") Integer id);
  ```
  MySQL đặt khóa độc quyền dòng dữ liệu (`SELECT ... FOR UPDATE`). Luồng thứ hai đến sau bắt buộc phải xếp hàng chờ luồng trước hoàn tất; khi đọc dữ liệu mới thấy tồn kho không đủ, hệ thống ném ngoại lệ và Rollback an toàn, bảo vệ tồn kho không bao giờ bị âm.

#### 6.3.3. Cơ chế Khóa lạc quan (`Optimistic Locking`) chống ghi đè đơn hàng
* Entity `HoaDon` được cấu hình trường `@Version private Integer phienBan;`. Khi Phục vụ A và Thu ngân B cùng mở Đơn #3: nếu Phục vụ A lưu trước, phiên bản tăng từ 0 lên 1. Khi Thu ngân B gửi request cập nhật với phiên bản cũ là 0, Hibernate phát hiện xung đột và ném `OptimisticLockException`. Spring Boot chuyển tiếp thành mã **HTTP 409 Conflict**, thông báo người dùng tải lại đơn để tránh mất mát thay đổi (*Lost Update*).

#### 6.3.4. Cơ chế Khóa Idempotency Key chống giao dịch trùng lặp
* Client sinh mã ngẫu nhiên UUID gán vào `yeuCauTao` và `yeuCauThanhToan`. Khi mạng chập chờn hoặc nhân viên nhấp đúp nút thanh toán, máy chủ kiểm tra mã này: nếu giao dịch đã được xử lý thành công trước đó, máy chủ lập tức trả về kết quả cũ mà không thực hiện trừ kho lần thứ hai.

#### 6.3.5. Cơ chế Chụp đơn giá bán (*Price Snapshot*)
* Bảng `chi_tiet_hoa_don` có cột riêng `don_gia_ban`. Khi món được thêm vào giỏ, hệ thống sao chép đơn giá niêm yết hiện tại vào cột này. Mọi thay đổi tăng giảm giá niêm yết của Quản lý sau thời điểm đó đều không làm thay đổi số tiền của các hóa đơn cũ, bảo đảm tính toàn vẹn chứng từ tài chính.

---

## CHƯƠNG 7: THIẾT KẾ GIAO DIỆN & KẾT QUẢ KIỂM THỬ THỰC NGHIỆM

### 7.1. Sơ đồ luồng điều hướng màn hình (UI Navigation Flow)

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 7.1:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype\BanGiao_UML\UI_Navigation_Flow.svg`  
> ![Hình 7.1: Sơ đồ luồng điều hướng màn hình UI Navigation Flow](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/BanGiao_UML/UI_Navigation_Flow.svg)

#### Phân tích Luồng điều hướng (Hình 7.1):
* Hệ thống bắt đầu tại màn hình Xác thực `index.html`. Sau khi đăng nhập thành công:
  * Tài khoản `PHUC_VU`: Chuyển đến `banhang.html`. Topbar tự động ẩn các tab quản trị "Kho & KPI", "Nhân sự". Chỉ có quyền mở bàn, gọi món, in Bar Ticket, yêu cầu thanh toán và quay lại phục vụ.
  * Tài khoản `THU_NGAN`: Chuyển đến `banhang.html`. Mở khóa quyền in Phiếu Tạm Tính, xác nhận thanh toán & đóng bàn, hủy đơn. Ô chiết khấu bị vô hiệu hóa (disabled).
  * Tài khoản `QUAN_LY`: Chuyển đến `kho.html`. Toàn quyền di chuyển qua lại cả 4 màn hình, mở khóa quyền nhập chiết khấu trên POS, quản trị kho và nhân sự.
* Cơ chế Route Guard kiểm tra quyền ngay khi nạp trang; nếu truy cập trái phép, tự động bật Toast cảnh báo và chuyển hướng quay lại trang được cấp phép.

---

### 7.2. Bộ minh chứng giao diện nguyên mẫu thực nghiệm (Prototype UI Walkthrough)
Toàn bộ hình ảnh dưới đây được chụp trực tiếp từ ứng dụng Web Java chạy trên máy chủ Localhost (`http://localhost:8080`), áp dụng giao diện Light Mode chuẩn mực in ấn A4, sử dụng 100% phông chữ tiếng Việt chuẩn UTF-8 và Bootstrap Icons:

#### 7.2.1. Giao diện Đăng nhập & An ninh hệ thống (`index.html`)

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH MINH CHỨNG 7.2:**  
> Ảnh chụp thực tế: `d:\3.1. PTTKYC\CK\screenshots\01_dang_nhap.png`  
> ![Hình 7.2: Giao diện Đăng nhập hệ thống](file:///d:/3.1.%20PTTKYC/CK/screenshots/01_dang_nhap.png)

##### Phân tích giao diện Đăng nhập (Hình 7.2):
* Thiết kế hiện đại mang thương hiệu **Sangrimar Cafe & Lounge**, bảng thông tin đăng nhập rõ ràng.
* Trang bị 3 nút **Quick-fill 1-chạm** phục vụ demo trực quan trước Hội đồng: `quanly / quanly` (Quản lý), `thungan / thungan` (Thu ngân), `phucvu / phucvu` (Phục vụ).
* Cơ chế an ninh chống dò quét: Nhập sai 5 lần liên tiếp $\rightarrow$ giao diện hiện thông báo khóa 60 giây và đếm ngược thời gian thực (HTTP 429).

---

#### 7.2.2. Giao diện Bán hàng POS & Sơ đồ bàn 3 màu viền thời gian thực (`banhang.html`)

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH MINH CHỨNG 7.3:**  
> Ảnh chụp thực tế: `d:\3.1. PTTKYC\CK\screenshots\02_pos_phuc_vu_so_do_ban.png`  
> ![Hình 7.3: Màn hình Bán hàng POS và Sơ đồ 10 bàn đổi 3 màu viền](file:///d:/3.1.%20PTTKYC/CK/screenshots/02_pos_phuc_vu_so_do_ban.png)

##### Phân tích màn hình Bán hàng POS (Hình 7.3):
* **Sơ đồ bàn chuẩn hóa 3 màu viền (2.5px solid):**
  - Viền Xanh dương (`#0D6EFD`): Bàn trống (`TRONG`), sẵn sàng đón khách.
  - Viền Xanh lá (`#198754`): Bàn đang dùng nước (`DANG_DUNG_NUOC`), hiển thị icon tiền + Tổng tiền tạm tính.
  - Viền Vàng hổ phách (`#D97706`): Bàn chờ thanh toán (`CHO_THANH_TOAN`), hiển thị icon tiền + Tổng cần thu.
  - Chiều cao ô bàn cố định `min-height: 92px`, chống tràn dòng khi doanh thu tạm tính lên đến hàng triệu đồng.
* **Thực đơn thông minh Soft Reservation:** Thẻ món hiển thị chỉ số `soLyCoTheLam` (Xanh: $>5$ ly, Vàng: $1-5$ ly, Đỏ: HẾT MÓN làm mờ thẻ và khóa nút chọn). Che giấu hoàn toàn số lượng gram/ml của kho gốc.

---

#### 7.2.3. Quy trình Phục vụ Tại bàn, In Bar Delta & Bộ 3 Mẫu Phiếu In

> [!NOTE]
> **VỊ TRÍ ĐẶT BỘ ẢNH MINH CHỨNG 7.4 ĐẾN 7.8 (LUỒNG POS LIÊN HOÀN & BỘ 3 PHIẾU IN):**  
> *Hình 7.4 – Tải lại đơn Bàn 03 đang phục vụ:* [screenshots/03_table3_occupied_loaded.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/03_table3_occupied_loaded.png)  
> *Hình 7.5 – Chọn bàn Bàn 02, thêm món kèm ghi chú:* [screenshots/04_cart_table2.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/04_cart_table2.png)  
> *Hình 7.6 – Lưu đơn Bàn 02 chuyển viền xanh lá & In Bar:* [screenshots/05_order_saved_table2.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/05_order_saved_table2.png)  
> *Hình 7.7 – Chuyển viền vàng chờ thanh toán & Modal thanh toán:* [screenshots/06_payment_modal_validation.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/06_payment_modal_validation.png)  
> *Hình 7.8 – Hóa đơn chính thức Sangrimar Cafe & Lounge hoàn tất:* [screenshots/07_receipt_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/07_receipt_modal.png)  
> ![Hình 7.8: Hóa đơn thanh toán chính thức](file:///d:/3.1.%20PTTKYC/CK/screenshots/07_receipt_modal.png)

##### Phân tích luồng Bán hàng thực nghiệm & Bộ 3 Phiếu In:
* Khi bấm Bàn 03 (Hình 7.4), giỏ hàng tải lại toàn bộ các món đang phục vụ.
* Tạo đơn tại Bàn 02 (Hình 7.5): thêm Cà phê sữa đá (ghi chú: "ít đường") và Trà đào. Tạm tính $67.000\text{ đ}$.
* Bấm "Lưu đơn / In Bar" (Hình 7.6): Bàn 02 chuyển viền xanh lá, xuất **Phiếu Pha Chế (Bar Ticket)** chuyển xuống quầy Bar. Nếu gọi thêm món, hệ thống chỉ xuất **Phiếu Bar Bổ Sung (In Bar Delta)** cho các món chênh lệch $\Delta$.
* Khi khách yêu cầu tính tiền (Hình 7.7): Bấm "Yêu cầu thanh toán", Bàn 02 chuyển sang viền vàng hổ phách `CHO_THANH_TOAN`. Thu ngân in **Phiếu Tạm Tính (Proforma Bill)** kèm mã VietQR động để khách kiểm tra.
* Xác nhận thanh toán (Hình 7.8): Thu ngân nhận tiền, hệ thống trừ kho BOM, đóng bàn về xanh dương `TRONG`, in **Hóa Đơn Thanh Toán Chính Thức (Official Receipt)** ghi nhận đầy đủ Phục vụ mở bàn và Thu ngân chốt tiền.

---

#### 7.2.4. Giao diện Quản trị Kho, Định mức BOM, WAC & Sổ nhật ký kho (`kho.html`)

> [!NOTE]
> **VỊ TRÍ ĐẶT BỘ ẢNH MINH CHỨNG 7.9 ĐẾN 7.12 (PHÂN HỆ KHO DUY PHỤ TRÁCH):**  
> *Hình 7.9 – Màn hình Kho, 6 thẻ KPI & Cảnh báo tồn đỏ:* [screenshots/08_kho_alert_kpi.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/08_kho_alert_kpi.png)  
> *Hình 7.10 – Modal Thiết lập định mức BOM 12 món:* [screenshots/09_recipe_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/09_recipe_modal.png)  
> *Hình 7.11 – Modal Nhập kho tính giá vốn WAC:* [screenshots/10_nhapkho_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/10_nhapkho_modal.png)  
> *Hình 7.12 – Modal Kiểm kê hao hụt & Sổ nhật ký `lich_su_kho`:* [screenshots/11_kho_restocked.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/11_kho_restocked.png)  
> ![Hình 7.9: Màn hình Quản trị Kho và 6 thẻ KPI Dashboard](file:///d:/3.1.%20PTTKYC/CK/screenshots/08_kho_alert_kpi.png)

##### Phân tích thực nghiệm Phân hệ Kho:
* **KPI Dashboard 4 kỳ & Bảng công thức (Hình 7.9):** 6 thẻ KPI hiển thị Doanh thu, Tiền mặt, Chuyển khoản, Chi phí nhập, Giá vốn hàng bán và Lợi nhuận gộp ước tính; bảng thống kê tổng giá trị tồn kho 13 nguyên liệu ($2.613.600\text{ đ}$) và Top 5 món bán chạy có thanh tiến độ trực quan.
* **Định mức BOM 12 món (Hình 7.10):** Cho phép cấu hình định mức tiêu hao từng nguyên liệu cho 1 ly nước, xem trước giá vốn/ly và biên lợi nhuận gộp.
* **Nhập kho cập nhật giá vốn WAC (Hình 7.11):** Modal `#restockModal` với các preset tiện ích (+100, +500, +1000) và xem trước thành tiền; tự động cập nhật đơn giá vốn bình quân gia quyền.
* **Kiểm kê hao hụt & Sổ nhật ký kho (Hình 7.12):** Modal `#auditModal` bắt buộc nhập lý do, tự động tính chênh lệch và tổn thất tài chính; Tab 3 hiển thị toàn bộ sổ nhật ký biến động kho (`lich_su_kho`) có bộ lọc theo loại giao dịch và nguyên liệu.

---

#### 7.2.5. Giao diện Quản trị Nhân sự & Chấm công thời gian thực 2 chế độ (`nhansu.html`)

> [!NOTE]
> **VỊ TRÍ ĐẶT BỘ ẢNH MINH CHỨNG 7.13 ĐẾN 7.16 (PHÂN HỆ NHÂN SỰ ĐẠT PHỤ TRÁCH):**  
> *Hình 7.13 – Chế độ Cá nhân: Đồng hồ thời gian thực & Chấm công:* [screenshots/12_nhansu_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/12_nhansu_screen.png)  
> *Hình 7.14 – Ghi nhận Check-in đối chiếu giờ gắn nhãn Đúng giờ/Đi trễ:* [screenshots/13_attendance_checked_in.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/13_attendance_checked_in.png)  
> *Hình 7.15 – Chế độ Quản trị: Lưới phân ca tuần cảnh báo thiếu Thu ngân:* [screenshots/14_add_employee_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/14_add_employee_modal.png)  
> *Hình 7.16 – Modal Quản lý Điều chỉnh Chấm công ngoại lệ lưu kiểm toán:* [screenshots/15_manager_discount_receipt.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/15_manager_discount_receipt.png)  
> ![Hình 7.13: Màn hình Chấm công Chế độ Cá nhân](file:///d:/3.1.%20PTTKYC/CK/screenshots/12_nhansu_screen.png)

##### Phân tích thực nghiệm Phân hệ Nhân sự:
* **Chế độ Cá nhân (Phục vụ & Thu ngân - Hình 7.13 & 7.14):** Ẩn hoàn toàn tính năng quản trị. Hiển thị thẻ ca làm hôm nay kèm đồng hồ thực tế, nút Check-in thông minh theo cửa sổ 30 phút, lịch tuần cá nhân và bảng công cá nhân.
* **Chế độ Quản trị (Quản lý - Hình 7.15 & 7.16):** 4 Tab con chuyên nghiệp:
  - Tab 1: Nhật ký chấm công toàn quán kèm nút "Chốt ca quá hạn" thủ công.
  - Tab 2: Lưới ma trận phân ca 7 ngày $\times$ 3 ca, tự động gắn cờ viền cam nét đứt cảnh báo `Thiếu Thu ngân` (`thieuThuNgan = true`), nút Đổi ca và Phân ca mới.
  - Tab 3: Bảng tổng hợp công theo kỳ (Tuần này / Tháng này / Tùy chọn) tự động nhân hệ số lương ca tối 1.2.
  - Tab 4: Danh bạ nhân sự CRUD có bảo vệ chống tự khóa quản trị viên cuối cùng.
  - Modal điều chỉnh công (Hình 7.16): Quản lý nhập giờ sửa và lý do bắt buộc, lưu vết kiểm toán minh bạch.

---

### 7.3. Kế hoạch và kết quả kiểm thử tích hợp tự động Backend (19/19 Test Cases Passed)
Toàn bộ mã nguồn kiểm thử tích hợp tự động Backend được tổ chức chuyên sâu trong 3 tệp tin kiểm thử tích hợp (`KhoKpiIntegrationTest.java`, `DonHangIntegrationTest.java`, `NhanSuChamCongIntegrationTest.java`) sử dụng JUnit 5, Spring MockMvc và cơ sở dữ liệu H2 In-Memory chạy trên cấu hình tách biệt `application-test.properties`.

* Lệnh thực thi kiểm thử toàn diện:
  ```powershell
  $env:JAVA_HOME = "F:\JDK" ; Set-Location "d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype" ; .\mvnw.cmd test
  ```
* **Kết quả thực tế:** `Tests run: 19, Failures: 0, Errors: 0, Skipped: 0` – **BUILD SUCCESS (100% PASSED)**.

**Bảng 7.1: Bảng tổng hợp 19 ca kiểm thử tích hợp tự động Backend**

| STT | Tệp tin kiểm thử | Phương thức kiểm thử | Nghiệp vụ kiểm tra chi tiết | Kết quả kỳ vọng | Kết quả thực tế |
| :---: | :--- | :--- | :--- | :---: | :---: |
| 1 | `KhoKpiIntegrationTest` | `nhapKhoUpdatesWeightedAverageCostAndLogsTransaction` | Nhập kho tính lại giá vốn WAC và tự động ghi dòng `NHAP_KHO` vào `lich_su_kho`. | HTTP 200 OK | **PASS (100%)** |
| 2 | `KhoKpiIntegrationTest` | `kiemKeKhoAdjustsStockRecordsDifferenceAndLogsAudit` | Kiểm kê cân bằng kho, tính tổn thất hao hụt và ghi dòng `KIEM_KE_HAO_HUT`. | HTTP 200 OK | **PASS (100%)** |
| 3 | `KhoKpiIntegrationTest` | `truKhoTuDongRollsBackWhenInsufficientStock` | Rollback sạch 100% cả tồn kho và lịch sử kho khi thiếu bất kỳ nguyên liệu nào. | HTTP 400 Bad Request | **PASS (100%)** |
| 4 | `KhoKpiIntegrationTest` | `truKhoTuDongSortedPessimisticLocksPreventDeadlock` | Sắp xếp nguyên liệu tăng dần theo `ma_nl`, khóa bi quan triệt tiêu Deadlock 100%. | Đa luồng thành công | **PASS (100%)** |
| 5 | `KhoKpiIntegrationTest` | `dashboardKpiCalculatesRevenueCogsAndProfit` | Tính toán chính xác Doanh thu thuần, Tiền mặt, Chuyển khoản, COGS, Hao hụt, Lợi nhuận gộp. | HTTP 200 OK | **PASS (100%)** |
| 6 | `KhoKpiIntegrationTest` | `bangCongThucReturnsRecipesWithCostMarginAndAvailableCups` | Trả về định mức 12 món kèm giá vốn/ly, biên lợi nhuận gộp và số ly khả dụng. | HTTP 200 OK | **PASS (100%)** |
| 7 | `DonHangIntegrationTest` | `waiterCanOrderAndRequestPaymentButCannotConfirmPayment` | Phục vụ tạo đơn và yêu cầu thanh toán thành công; bị chặn HTTP 403 khi cố thanh toán. | HTTP 201 / 200 / 403 | **PASS (100%)** |
| 8 | `DonHangIntegrationTest` | `menuCalculatesAvailableCupsWithSoftReservationWithoutExposingStock` | Che giấu tồn kho gốc; tính `soLyCoTheLam` theo Soft Reservation và Bottleneck. | HTTP 200 OK | **PASS (100%)** |
| 9 | `DonHangIntegrationTest` | `addMoreItemsOnlyPrintsDeltaToBarAndBlockedWhenWaitingPayment` | Gọi thêm món chỉ in Bar Delta; chặn sửa món khi bàn đang `CHO_THANH_TOAN` (409). | HTTP 200 / 409 Conflict | **PASS (100%)** |
| 10 | `DonHangIntegrationTest` | `cashierConfirmsCashAndTransferPaymentAndClosesTable` | Thu ngân thanh toán tiền mặt/chuyển khoản, trừ kho, ghi nhận thu ngân và đóng bàn. | HTTP 200 OK | **PASS (100%)** |
| 11 | `DonHangIntegrationTest` | `cashierCannotApplyDiscountOnlyManagerCan` | Chặn Thu ngân nhập chiết khấu (HTTP 403); chỉ Quản lý mới được áp dụng giảm giá. | HTTP 403 / 200 OK | **PASS (100%)** |
| 12 | `NhanSuChamCongIntegrationTest`| `checkInRejectedOutsideWindowAndAcceptedWithin30Minutes` | Từ chối check-in ngoài cửa sổ (HTTP 400); chấp nhận trong cửa sổ $[start-30p, end)$. | HTTP 400 / 200 OK | **PASS (100%)** |
| 13 | `NhanSuChamCongIntegrationTest`| `checkInLateRecordsLateMinutesAndClampsWorkedHours` | Check-in trễ $>15$p gắn nhãn `DI_TRE`; không tính giờ làm dồn trước giờ ca. | HTTP 200 OK | **PASS (100%)** |
| 14 | `NhanSuChamCongIntegrationTest`| `checkOutEarlyMarksVeSomAndCombinesWithDiTre` | Phát hiện về sớm $>15$p gắn `VE_SOM`; kết hợp thành `DI_TRE_VE_SOM`. | HTTP 200 OK | **PASS (100%)** |
| 15 | `NhanSuChamCongIntegrationTest`| `closeExpiredShiftsMarksAbsentAndForgotCheckout` | Scheduler quét quá hạn: chưa vào gắn `VANG`, quên ra gắn `QUEN_CHECK_OUT`. | HTTP 200 OK | **PASS (100%)** |
| 16 | `NhanSuChamCongIntegrationTest`| `managerAdjustsForgotCheckoutWithAuditTrailAndRecalculates` | Quản lý sửa ca quên check-out có lưu vết kiểm toán và tự tính lại giờ công. | HTTP 200 OK | **PASS (100%)** |
| 17 | `NhanSuChamCongIntegrationTest`| `shiftSwapUpdatesAssigneeAndDetectsMissingCashier` | Đổi ca cập nhật người làm; phát hiện ca thiếu Thu ngân gắn cờ `thieuThuNgan=true`. | HTTP 200 OK | **PASS (100%)** |
| 18 | `NhanSuChamCongIntegrationTest`| `timesheetSummaryMultipliesShiftCoefficientAndIsolatesStaff` | Bảng tổng hợp công nhân đúng hệ số lương 1.2 ca tối và cách ly dữ liệu cá nhân. | HTTP 200 OK | **PASS (100%)** |
| 19 | `QuanLyCafeApplicationTests` | `contextLoads` | Tải thành công Spring ApplicationContext và toàn bộ các Bean trong hệ thống. | Context Loaded | **PASS (100%)** |

---

### 7.4. Kết quả kiểm thử hộp đen E2E trên giao diện Web (16/16 Scenarios Passed)
Sử dụng công cụ tự động hóa Headless Chrome thực thi kịch bản liên thông từ đầu đến cuối trên trình duyệt:

**Bảng 7.2: Kết quả kiểm thử hộp đen E2E tự động trên giao diện Web**

| Mã Kịch bản | Phân hệ màn hình | Tên ca kiểm thử E2E | Thao tác thực hiện | Kết quả ghi nhận thực tế | Trạng thái |
| :---: | :--- | :--- | :--- | :--- | :---: |
| **TC-01** | Xác thực (`index.html`) | Hiển thị form đăng nhập | Mở trang chủ | Render form đăng nhập Sangrimar chuẩn, 3 nút Quick-fill. | **PASS** |
| **TC-01b** | Xác thực (`index.html`) | Chặn brute-force | Nhập sai 5 lần | Tự động tạm khóa 60s, đếm ngược thời gian thực (HTTP 429). | **PASS** |
| **TC-02** | Bán hàng (`banhang.html`) | Đăng nhập Phục vụ | Đăng nhập `phucvu` | Vào POS, ẩn tab Kho & Nhân sự, hiện badge "Lê Tấn Đạt \| PHUC_VU". | **PASS** |
| **TC-03** | Bán hàng (`banhang.html`) | Mở lại đơn bàn đang phục vụ | Bấm Bàn 03 | Giỏ hàng tự động tải lại Đơn #3 cùng các món đang dùng nước. | **PASS** |
| **TC-04** | Bán hàng (`banhang.html`) | Thêm món & Ghi chú | Chọn Bàn 02, chọn món | Giỏ hàng nhận món kèm ghi chú "ít đường", kiểm tra `soLyCoTheLam`. | **PASS** |
| **TC-05** | Bán hàng (`banhang.html`) | Lưu đơn & In Bar | Bấm "Lưu đơn / In Bar"| Tạo Đơn #4, Bàn 02 chuyển viền xanh lá, xuất Bar Ticket. | **PASS** |
| **TC-06** | Bán hàng (`banhang.html`) | Yêu cầu thanh toán | Bấm "Yêu cầu TT" | Bàn 02 chuyển sang viền vàng hổ phách, khóa quyền sửa món. | **PASS** |
| **TC-07** | Bán hàng (`banhang.html`) | In Phiếu Tạm Tính | Bấm "In Tạm Tính" | Modal mở Phiếu Tạm Tính kèm VietQR động để khách đối soát. | **PASS** |
| **TC-08** | Bán hàng (`banhang.html`) | Thu ngân thanh toán | Đăng nhập `thungan` chốt đơn | Tính đúng tiền thừa, đóng bàn về viền xanh dương, xuất Hóa Đơn chính thức. | **PASS** |
| **TC-09** | Kho & KPI (`kho.html`) | Trừ kho & Cảnh báo đỏ | Mở trang Kho | Sữa đặc bị trừ ngầm 30ml, chạm ngưỡng tối thiểu bật nhãn đỏ cảnh báo. | **PASS** |
| **TC-10** | Kho & KPI (`kho.html`) | Sổ nhật ký biến động | Mở Tab Lịch sử kho | Hiển thị dòng `XUAT_BAN_HANG` gắn đúng Đơn #4 và Thu ngân chốt. | **PASS** |
| **TC-11** | Kho & KPI (`kho.html`) | Nhập kho tính giá WAC | Nhập 500ml Sữa đặc | Cập nhật giá vốn WAC, tăng tồn kho, nhãn đỏ đổi thành xanh "Đủ hàng". | **PASS** |
| **TC-12** | Kho & KPI (`kho.html`) | Kiểm kê hao hụt | Nhập tồn kiểm kê | Bắt buộc nhập lý do, tự động tính chênh lệch và tổn thất tài chính. | **PASS** |
| **TC-13** | Nhân sự (`nhansu.html`) | Chấm công Cá nhân | Phục vụ Check-in | Đối chiếu giờ thực tế máy chủ, ghi nhận bản ghi Đúng giờ / Đi trễ. | **PASS** |
| **TC-14** | Nhân sự (`nhansu.html`) | Lưới phân ca cảnh báo | Quản lý xem lịch tuần | Hiển thị ô ca viền cam nét đứt cảnh báo `Thiếu Thu ngân` trực quan. | **PASS** |
| **TC-15** | Nhân sự (`nhansu.html`) | Điều chỉnh công ngoại lệ | Quản lý sửa giờ công | Bắt buộc nhập lý do, tính lại giờ công và lưu vết kiểm toán minh bạch. | **PASS** |
| **TC-16** | Toàn hệ thống | Đồng bộ giao diện & Icon | Quét toàn bộ 4 trang | 100% tiếng Việt UTF-8 chuẩn mực, 0 icon lỗi, responsive hoàn hảo. | **PASS** |

---

### 7.5. Bảng tổng hợp lỗi phát hiện và lịch sử khắc phục triệt để (BUG-01 đến BUG-06)

**Bảng 7.3: Bảng tổng hợp lỗi, phân loại mức độ và trạng thái khắc phục triệt để**

| Mã Bug | Phân hệ ảnh hưởng | Mức độ | Hiện tượng khi phát hiện | Giải pháp kỹ thuật đã áp dụng | Trạng thái nghiệm thu |
| :---: | :--- | :---: | :--- | :--- | :---: |
| **BUG-01** | Phân quyền RBAC 4 Tầng | **Major** | Tài khoản `PHUC_VU` có thể bấm vào tab Kho và Nhân sự; gọi API thanh toán qua Postman vẫn lọt. | 1. Ẩn tab trên giao diện theo `vaiTro`.<br>2. Route Guard chặn URL.<br>3. Kiểm tra vai trò trong Service ném HTTP 403 Forbidden.<br>4. Ràng buộc CSDL. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-02** | Trừ kho đồng thời | **Critical** | Hai đơn hàng thanh toán cùng lúc các món có chung nguyên liệu gây Deadlock treo CSDL. | Ứng dụng `TreeMap` sắp xếp khóa nguyên liệu tăng dần theo `ma_nl` kết hợp `SELECT ... FOR UPDATE`, triệt tiêu Deadlock 100%. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-03** | Hiển thị Bàn 03 POS | **Minor** | Khi Bàn 03 có doanh thu tạm tính lớn, chuỗi text bị rớt dòng làm vỡ chiều cao ô bàn. | Cập nhật CSS `.pos-table-btn` với `min-height: 92px`, `justify-content: space-between` và `.table-amount` dùng `white-space: nowrap`. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-04** | Header biến mất khi tải trang | **Major** | Khối topbar `#app-topbar-root` bị rỗng nếu API bất đồng bộ chậm hoặc `DOMContentLoaded` đã qua. | Bổ sung fallback null-safe trong `dung-chung.js`, gọi render đồng bộ ngay từ cached user, hỗ trợ cả 2 trạng thái `document.readyState`. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-05** | Chấm công qua nửa đêm & Hệ số lương | **Major** | Ca tối qua 24h tính sai giờ làm; không phân biệt ca đêm để trả thêm lương. | Tính giờ clamped bằng `java.time.Duration`, nhân `he_so_luong = 1.20` cho ca tối, lưu trường `gio_cong_quy_doi`. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-06** | Bảng mã CSDL & Mojibake tiếng Việt | **Critical** | Dữ liệu nạp từ script cũ bị lỗi font (`C├á ph├¬ ─æen`), mất dấu tiếng Việt và chứa emoji bàn phím. | Bổ sung `SET NAMES utf8mb4;`, cấu hình kết nối UTF-8, tái nạp qua lệnh `SOURCE` của MySQL, chuẩn hóa 100% Bootstrap Icons CSS. | **ĐÃ XỬ LÝ (VERIFIED)** |

---

## CHƯƠNG 8: KẾT LUẬN & HƯỚNG PHÁT TRIỂN

### 8.1. Đánh giá mức độ hoàn thành so với mục tiêu đề ra
Đối chiếu với các yêu cầu bắt buộc của Đề thi báo cáo cuối kỳ học phần *Phân tích và Thiết kế Yêu cầu (Mã môn: 502050)* do Khoa Công nghệ Thông tin – Trường Đại học Tôn Đức Thắng ban hành, nhóm tự đánh giá đã **hoàn thành $100\%$ các mục tiêu đề ra**:
1. **Về mặt tài liệu và mô hình hóa UML:** Xây dựng đầy đủ 13 sơ đồ thiết kế PlantUML chuẩn mực học thuật (UseCase, Class Diagram, ERD Crow's Foot 11 thực thể, 3 Activity Diagram phân làn, 3 Sequence Diagram đa tầng, State Machine, Component Diagram, Deployment Diagram và Package Diagram). Đặc tả chi tiết 7 Use Case cốt lõi theo mẫu Fully-Dressed Template 9 trường.
2. **Về mặt sản phẩm phần mềm Java chạy thực tế:** Xây dựng thành công ứng dụng Web Java nguyên khối **Spring Boot 3.2.5** kết hợp CSDL **MySQL 8.0** trên XAMPP. Tất cả các luồng nghiệp vụ cốt lõi: *Mở bàn gọi món POS*, *In lệnh pha chế Bar Ticket & Bar Delta*, *Phiếu Tạm Tính VietQR*, *Thanh toán xuất hóa đơn nhiệt*, *Tự động bóc tách định mức BOM trừ kho ngầm chống Deadlock*, *Lưu sổ nhật ký biến động kho `lich_su_kho`*, *Nhập kho WAC & Kiểm kê hao hụt*, *Chấm công thời gian thực 7 quy tắc BR-CC*, *Tiến trình Scheduler chốt ca quá hạn* và *Phân quyền RBAC 3 vai trò* đều vận hành ổn định và trơn tru.
3. **Về mặt kiểm thử chất lượng:** Vượt qua **$19/19$ ca kiểm thử tích hợp tự động Backend (100% PASS)** và **$16/16$ kịch bản kiểm thử hộp đen E2E trên trình duyệt Chrome (100% PASS)**. Khắc phục triệt để 6 lỗi kỹ thuật từ an ninh phân quyền đến hiển thị tiếng Việt có dấu.

### 8.2. Ưu điểm nổi bật của hệ thống
* **Tính toàn vẹn dữ liệu giao dịch cao (ACID) & Triệt tiêu Deadlock 100%:** Việc bọc toàn bộ luồng thanh toán và trừ kho trong giao dịch `@Transactional` kết hợp thuật toán sắp xếp nguyên liệu tăng dần bằng `TreeMap` và khóa bi quan `SELECT ... FOR UPDATE` đảm bảo kho không bao giờ bị bán khống và không bao giờ xảy ra lỗi kẹt Deadlock.
* **Quy trình phục vụ tại bàn chuẩn F&B (Full-service SOP):** Phân tách minh bạch giữa lệnh pha chế Bar Ticket (không hiện giá), Phiếu Tạm Tính cho khách kiểm tra đối soát, và Hóa Đơn chính thức; cơ chế in Bar Delta giúp quầy Bar không bao giờ bị làm trùng món.
* **Kiểm soát chi phí giá vốn & Thất thoát kho minh bạch:** Áp dụng công thức giá vốn Bình quân gia quyền liên hoàn (WAC); sổ nhật ký biến động kho `lich_su_kho` lưu vết đầy đủ mọi giao dịch nhập, xuất và kiểm kê hao hụt.
* **Chấm công thời gian thực công bằng & Lập lịch thông minh:** Áp dụng 7 quy tắc BR-CC nghiêm ngặt, tự động chốt ca quá hạn qua Scheduler định kỳ, lưới phân ca tuần cảnh báo cờ thiếu Thu ngân giúp tối ưu hóa nhân sự.
* **Giao diện hiện đại, chuyên nghiệp, tối ưu in ấn:** Giao diện Light Mode tương phản cao, bảng màu chuẩn F&B, sơ đồ bàn 3 màu viền trực quan, bộ 3 mẫu phiếu in `@media print` sắc nét.

### 8.3. Hạn chế còn tồn tại
* Hệ thống hiện mới mô phỏng xác nhận chuyển khoản bằng đối soát thủ công mã tham chiếu, chưa kết nối trực tiếp API Webhook tự động với các ngân hàng thương mại lớn.
* Chưa hỗ trợ tính năng tách bàn hoặc ghép nhiều bàn thành một hóa đơn thanh toán chung.
* Chưa trang bị hệ thống gửi tin nhắn thông báo cảnh báo kho khẩn cấp qua Telegram hoặc Email đến điện thoại của chủ quán khi vắng mặt.

### 8.4. Chiến lược chuyển giao và triển khai phần mềm (Direct Cutover Strategy)
Để đưa phần mềm vào ứng dụng thực tế tại quán Sangrimar Cafe & Lounge mà không làm gián đoạn kinh doanh, nhóm đề xuất chiến lược **Chuyển giao trực tiếp vào đầu tuần (Direct Cutover on Monday Morning)**:
1. **Giai đoạn chuẩn bị (Thứ Bảy & Chủ Nhật):** Tiến hành kiểm kê toàn bộ nguyên vật liệu thực tế trong kho quán; nạp danh mục 12 món đồ uống và cấu hình định mức BOM chính xác vào CSDL; tạo tài khoản phân quyền cho toàn bộ nhân viên.
2. **Giai đoạn đào tạo (Chiều Chủ Nhật, 2 tiếng):** Hướng dẫn nhân viên phục vụ, quầy bar và thu ngân thao tác thử nghiệm quy trình bán hàng tại bàn; hướng dẫn nhân viên cách Check-in/Check-out đầu ca.
3. **Thời điểm kích hoạt chính thức (06:30 sáng Thứ Hai):** Quán ngưng hoàn toàn việc ghi order giấy và chuyển đổi $100\%$ sang thao tác trên phần mềm. Quản lý trực tiếp tại quầy trong 3 ngày đầu để hỗ trợ và xử lý phát sinh.

### 8.5. Hướng phát triển và mở rộng trong tương lai
* **Mở rộng 1 – Tích hợp Webhook Ngân hàng tự động:** Khi khách quét mã VietQR chuyển khoản thành công, hệ thống ngân hàng bắn Webhook xác nhận tự động, hệ thống tự động chốt bill mà thu ngân không cần bấm xác nhận thủ công.
* **Mở rộng 2 – Quét mã QR gọi món tại bàn (Self-ordering QR Code):** Dán mã QR cố định tại mỗi bàn. Khách hàng dùng điện thoại cá nhân quét mã để xem menu và tự gửi order vào quầy bar mà không cần chờ nhân viên phục vụ.
* **Mở rộng 3 – Trí tuệ nhân tạo dự báo tồn kho (AI Predictive Inventory):** Dựa trên lịch sử bán hàng trong 6 tháng, ứng dụng thuật toán học máy dự báo lượng tiêu thụ nguyên vật liệu trong các dịp lễ, tết để tự động lập danh sách đề xuất nhập hàng tối ưu.

---

## CHƯƠNG 9: PHỤ LỤC & TÀI LIỆU THAM KHẢO

### 9.1. Bảng phân công nhiệm vụ và tỷ lệ đóng góp của từng thành viên

**Bảng 9.1: Bảng phân công nhiệm vụ và tỷ lệ đóng góp của từng thành viên trong nhóm**

| STT | Họ và tên thành viên | Mã số sinh viên (MSSV) | Vai trò phụ trách | Chi tiết các hạng mục công việc đã thực hiện | Tỷ lệ đóng góp | Chữ ký xác nhận |
| :---: | :--- | :---: | :---: | :--- | :---: | :---: |
| 1 | **Nguyễn Văn Duy** | `52400010` | **Nhóm trưởng** | - Phụ trách Kiến trúc Dữ liệu, ERD 11 bảng, Design Class Diagram 3 tầng.<br>- Phụ trách Phân hệ Kho nguyên liệu, Định mức BOM 12 món, Bảng `lich_su_kho`.<br>- Lập trình nghiệp vụ lõi trừ kho tự động chống Deadlock `@Transactional`, nhập kho WAC, kiểm kê hao hụt và KPI Dashboard.<br>- Tổng hợp quyển Báo cáo, chuẩn hóa 13 sơ đồ PlantUML và điều phối thuyết trình Q&A. | **34%** | *(Đã ký số)* |
| 2 | **Trần Văn Quý** | `52400007` | **Thành viên** | - Phụ trách Kỹ nghệ Yêu cầu chức năng (FR-01 $\rightarrow$ FR-15) và Bảng phân loại MoSCoW.<br>- Thiết kế Biểu đồ Use Case tổng quát và các sơ đồ phân hệ Bán hàng POS.<br>- Lập trình giao diện `banhang.html` (Sơ đồ 10 bàn 3 màu viền, Giỏ hàng, Modal thanh toán, Bộ 3 mẫu phiếu in nghiệp vụ `@media print`).<br>- Lập trình Controller và Service cho phân hệ Order & Chiết khấu. | **33%** | *(Đã ký số)* |
| 3 | **Lê Tấn Đạt** | `52400105` | **Thành viên** | - Phụ trách Khảo sát hiện trạng, nhận diện 4 điểm nghẽn và Yêu cầu phi chức năng (NFR).<br>- Thiết kế sơ đồ phân hệ Quản lý Nhân sự, Lưới phân ca tuần & 7 Quy tắc Chấm công BR-CC.<br>- Lập trình giao diện `index.html` và `nhansu.html` (Chấm công 2 chế độ, Lưới phân ca cảnh báo thiếu thu ngân).<br>- Xây dựng bộ dữ liệu mẫu chuẩn `database_mau.sql` v2.0 và thực hiện bộ kiểm thử tích hợp 19 Test Cases. | **33%** | *(Đã ký số)* |

*Xác nhận của toàn bộ thành viên nhóm:*  
Tất cả các thành viên trong nhóm đã tham gia đầy đủ, tích cực, phối hợp chặt chẽ và đóng góp đồng đều trong suốt quá trình thực hiện đồ án và cam kết thống nhất với bảng phân công trên.

---

### 9.2. Kịch bản Demo trực tiếp (Live Demo Script) phục vụ bảo vệ đồ án
Kịch bản được thiết kế cho cả 3 thành viên trình bày và demo liên hoàn trong khoảng thời gian **$12\text{--}15\text{ phút}$** trước Hội đồng chấm thi:

* **Phút 00:00 – 02:00 (Duy trình bày):**
  * Giới thiệu thành viên nhóm, thương hiệu Sangrimar Cafe & Lounge, 4 điểm nghẽn vận hành thực tế.
  * Trình bày mục tiêu giải pháp và kiến trúc phân tầng 3 tầng All-in-one Modular Monolith Spring Boot.
* **Phút 02:00 – 05:00 (Quý trình bày & Demo Bán hàng POS):**
  * Trình bày Yêu cầu FR, Bảng MoSCoW, Biểu đồ Use Case tổng quát và Chu trình phục vụ tại bàn SOP.
  * **Thực hiện Demo trên web (`http://localhost:8080/banhang.html`):**
    1. Đăng nhập tài khoản `phucvu / phucvu` qua nút Quick-fill $\rightarrow$ chỉ rõ thanh điều hướng ẩn tab Kho và Nhân sự.
    2. Chọn Bàn 02 (đang viền xanh dương trống), chỉ rõ menu hiển thị `soLyCoTheLam` theo Soft Reservation.
    3. Chọn 1 Cà phê sữa đá (ghi chú: "ít đường") và 1 Trà đào cam sả $\rightarrow$ Bấm "Lưu đơn / In Bar" $\rightarrow$ Bàn 02 chuyển sang viền xanh lá `DANG_DUNG_NUOC`, popup xuất Phiếu Pha Chế Bar Ticket.
    4. Thêm 1 Bạc xỉu $\rightarrow$ Bấm lưu $\rightarrow$ Chứng minh hệ thống chỉ in Bar Delta cho 1 ly Bạc xỉu bổ sung.
    5. Bấm "Yêu cầu thanh toán" $\rightarrow$ Bàn chuyển sang viền vàng `CHO_THANH_TOAN`, khóa quyền sửa món của phục vụ.
    6. Thu ngân (`thungan`) in Phiếu Tạm Tính kèm VietQR động; bấm "Xác nhận thanh toán & Đóng bàn" $\rightarrow$ Nhập tiền khách đưa $100.000\text{ đ}$, tính đúng tiền thừa $33.000\text{ đ}$, xuất Hóa Đơn chính thức, Bàn 02 trả về viền xanh dương trống.
* **Phút 05:00 – 08:30 (Đạt trình bày & Demo Phân hệ Nhân sự & Chấm công):**
  * Trình bày Yêu cầu NFR, Thiết kế CSDL 11 bảng chuẩn 3NF và 7 Quy tắc Chấm công BR-CC.
  * **Thực hiện Demo trên web (`http://localhost:8080/nhansu.html`):**
    1. Minh họa Chế độ Cá nhân (`phucvu`): Đồng hồ thời gian thực, thẻ ca hôm nay, bấm "Check-in Vào Ca" $\rightarrow$ Hệ thống đối chiếu giờ máy chủ, gắn nhãn xanh `Đúng giờ` (hoặc đỏ `Đi trễ`).
    2. Đăng nhập tài khoản `quanly / quanly`, chuyển sang Chế độ Quản trị:
       - Tab 2 (Lịch phân ca tuần): Chỉ rõ ô ca Chủ Nhật thiếu Thu ngân hiển thị cờ viền cam nét đứt cảnh báo `Thiếu Thu ngân` (`thieuThuNgan = true`).
       - Tab 1 (Nhật ký chấm công): Minh họa nút "Điều chỉnh" ca làm quên check-out, nhập giờ và lý do bắt buộc $\rightarrow$ Hệ thống tính lại giờ công và lưu vết kiểm toán.
* **Phút 08:30 – 11:30 (Duy trình bày & Demo Phân hệ Kho nguyên liệu & KPI):**
  * Trình bày Biểu đồ Tuần tự trừ kho chống Deadlock (Hình 6.1) và Bảng thứ 11 `lich_su_kho`.
  * **Thực hiện Demo trên web (`http://localhost:8080/kho.html`):**
    1. Chỉ cho Hội đồng thấy dòng "Sữa đặc Ngôi Sao" vừa bị trừ ngầm $30\text{ ml}$ (do Quý vừa bán cà phê sữa ở bước trước), tồn kho giảm từ $120\text{ ml}$ xuống đúng $90\text{ ml}$.
    2. Do $90\text{ ml} \le 100\text{ ml}$, hệ thống tự động bật nhãn đỏ cảnh báo **`SẮP HẾT HÀNG`** và cập nhật thẻ KPI.
    3. Mở Tab 3 (Sổ nhật ký kho): Chỉ rõ bản ghi `XUAT_BAN_HANG` vừa được tạo tự động, liên kết đúng mã hóa đơn và thu ngân chốt đơn.
    4. Bấm "Nhập kho" tại dòng Sữa đặc, nhập thêm $500\text{ ml}$ với đơn giá mới $\rightarrow$ Chứng minh công thức WAC tính lại đơn giá vốn và tồn kho phục hồi lên $590\text{ ml}$, nhãn cảnh báo chuyển sang xanh "Đủ hàng".
    5. Mở modal "Kiểm kê kho": Nhập tồn thực tế $550\text{ ml}$, nhập lý do "Làm đổ hộp sữa" $\rightarrow$ Hệ thống tự tính tổn thất tài chính và lưu dòng `KIEM_KE_HAO_HUT`.
    6. Mở KPI Dashboard: Trình bày cơ cấu doanh thu Tiền mặt vs Chuyển khoản và Top 5 món bán chạy.
* **Phút 11:30 – 13:00 (Duy tổng kết):**
  * Trình bày bảng kết quả kiểm thử tích hợp tự động ($19/19$ Backend tests đạt $100\%$ PASS).
  * Nêu ưu điểm, hạn chế và chiến lược chuyển giao trực tiếp vào sáng Thứ Hai.
  * Cảm ơn Hội đồng và chuyển sang phần điều phối trả lời câu hỏi phản biện (Q&A).

---

### 9.3. Danh mục tài liệu tham khảo
1. **TS. Hồ Thị Linh (2024),** *Bài giảng Phân tích và Thiết kế Yêu cầu (Mã học phần: 502050)*, Khoa Công nghệ Thông tin, Trường Đại học Tôn Đức Thắng.
2. **Dennis, A., Wixom, B. H., & Tegarden, D. (2020),** *Systems Analysis and Design: An Object-Oriented Approach with UML (6th Edition)*, John Wiley & Sons.
3. **Larman, C. (2012),** *Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development (3rd Edition)*, Prentice Hall.
4. **Fowler, M. (2003),** *UML Distilled: A Brief Guide to the Standard Object Modeling Language (3rd Edition)*, Addison-Wesley Professional.
5. **Walls, C. (2022),** *Spring in Action (6th Edition)*, Manning Publications.
6. **Bauer, C., King, G., & Gregory, G. (2015),** *Java Persistence with Hibernate (2nd Edition)*, Manning Publications.
7. **Oracle Corporation (2024),** *MySQL 8.0 Reference Manual: InnoDB Locking and Transaction Model*, Oracle Documentation.
8. **Bootstrap Team (2023),** *Bootstrap v5.3 Documentation: Components, Forms & Grid System*, getbootstrap.com.
9. **PlantUML Team (2024),** *PlantUML Language Reference Guide: Standard UML 2.5 Modeling*, plantuml.com.

---
*Báo cáo được biên soạn hoàn chỉnh, kiểm duyệt nội dung và định dạng đúng quy chuẩn Khoa CNTT – ĐH Tôn Đức Thắng ngày 20/11/2026.*
