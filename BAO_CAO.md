# BÁO CÁO ĐỒ ÁN CUỐI KỲ: HỆ THỐNG QUẢN LÝ QUÁN CÀ PHÊ
### (COFFEE POS & INVENTORY MANAGEMENT SYSTEM)

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
> 1. **Nguyễn Văn Duy** (MSSV: `521H0001` – Nhóm trưởng – Tỷ lệ đóng góp: **$34\%$**)  
>    *Nhiệm vụ:* Kiến trúc Dữ liệu, Thiết kế Định mức BOM & Phân hệ Kho, Tổng hợp Báo cáo & Điều phối Q&A.
> 2. **Trần Văn Quý** (MSSV: `521H0002` – Thành viên – Tỷ lệ đóng góp: **$33\%$**)  
>    *Nhiệm vụ:* Phân tích Yêu cầu, Phân hệ Bán hàng POS, Sơ đồ bàn, Hóa đơn thanh toán, Kịch bản nghiệp vụ Order.
> 3. **Lê Tấn Đạt** (MSSV: `521H0003` – Thành viên – Tỷ lệ đóng góp: **$33\%$**)  
>    *Nhiệm vụ:* Khảo sát Hiện trạng, Phân hệ Quản lý Nhân sự, Ca làm việc, Nghiệp vụ Chấm công, Dữ liệu mẫu & Kiểm thử.
>
> *Thành phố Hồ Chí Minh, Tháng 11 Năm 2026*

---

## LỜI CAM ĐOAN

Chúng tôi xin cam đoan đây là công trình nghiên cứu, phân tích, thiết kế và lập trình thực nghiệm do chính nhóm chúng tôi thực hiện dưới sự định hướng và hướng dẫn khoa học của Giảng viên hướng dẫn – TS. Hồ Thị Linh. 

Mọi số liệu khảo sát hiện trạng, các biểu đồ mô hình hóa phân tích thiết kế bằng PlantUML, cơ sở dữ liệu quan hệ MySQL và toàn bộ mã nguồn ứng dụng Web Java Spring Boot nguyên khối (*All-in-one Spring Boot Application*) được trình bày trong báo cáo này đều là sản phẩm độc lập của nhóm. Các tài liệu, thư viện công nghệ và tài liệu tham khảo bên ngoài đều được trích dẫn nguồn gốc rõ ràng theo quy chuẩn học thuật của Trường Đại học Tôn Đức Thắng. Nhóm xin chịu hoàn toàn trách nhiệm trước Bộ môn và Khoa Công nghệ Thông tin về tính trung thực của báo cáo này.

*Đại diện nhóm tác giả:*  
**Nguyễn Văn Duy** *(Trưởng nhóm)*

---

## LỜI CẢM ƠN

Để hoàn thành đề tài đồ án cuối kỳ học phần *Phân tích và Thiết kế Yêu cầu (Mã môn: 502050)*, nhóm chúng tôi đã nhận được sự quan tâm, giảng dạy và hướng dẫn tận tình từ quý Thầy, Cô thuộc Khoa Công nghệ Thông tin – Trường Đại học Tôn Đức Thắng.

Đặc biệt, nhóm xin gửi lời tri ân sâu sắc và chân thành nhất đến **TS. Hồ Thị Linh** – Giảng viên trực tiếp phụ trách giảng dạy học phần. Trong suốt quá trình học tập trên lớp cũng như các tuần làm việc đồ án, Cô đã truyền đạt những kiến thức nền tảng vô cùng vững chắc về kỹ nghệ yêu cầu, phương pháp thu thập và phân rã sự kiện, kỹ thuật mô hình hóa UML 2.5 chuẩn mực, cũng như tư duy chuyển đổi logic nghiệp vụ sang kiến trúc phần mềm thực tế. Những lời góp ý tỉ mỉ, những buổi phản biện gợi mở của Cô chính là kim chỉ nam giúp nhóm định hình rõ ranh giới hệ thống, hoàn thiện cơ chế giao dịch nguyên tử `@Transactional` kết hợp khóa chống bán khống, và xây dựng thành công ứng dụng Web Java mẫu chạy thực nghiệm ổn định.

Nhóm cũng xin chân thành cảm ơn các bạn sinh viên trong lớp đã cùng thảo luận, chia sẻ kinh nghiệm thực nghiệm lập trình và đóng góp ý kiến quý báu trong các buổi thảo luận chuyên đề. Dù đã có nhiều cố gắng, báo cáo khó tránh khỏi những thiếu sót nhất định; nhóm rất mong tiếp tục nhận được những lời chỉ dẫn và đóng góp của Cô để dự án ngày càng hoàn thiện hơn.

*Kính chúc Cô luôn dồi dào sức khỏe, hạnh phúc và gặt hái thêm nhiều thành công trong sự nghiệp giáo dục!*

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
  - [2.3. Phân tích 3 "điểm nghẽn" nghiệp vụ chí mạng cần giải quyết](#23-phân-tích-3-điểm-nghẽn-nghiệp-vụ-chí-mạng-cần-giải-quyết)
  - [2.4. Bảng phân rã sự kiện nghiệp vụ (Event Decomposition Table)](#24-bảng-phân-rã-sự-kiện-nghiệp-vụ-event-decomposition-table)
  - [2.5. Danh mục 15 yêu cầu chức năng cốt lõi (FR-01 đến FR-15)](#25-danh-mục-15-yêu-cầu-chức-năng-cốt-lõi-fr-01-đến-fr-15)
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
  - [5.2. Bảng Từ điển Dữ liệu chi tiết cho 9 bảng CSDL (Data Dictionary)](#52-bảng-từ-điển-dữ-liệu-chi-tiết-cho-9-bảng-csdl-data-dictionary)
  - [5.3. Ràng buộc toàn vẹn dữ liệu và chuẩn hóa dạng chuẩn 3NF](#53-ràng-buộc-toàn-vẹn-dữ-liệu-và-chuẩn-hóa-dạng-chuẩn-3nf)
- [CHƯƠNG 6: THIẾT KẾ ĐỘNG HỌC HỆ THỐNG & CƠ CHẾ GIAO DỊCH NÂNG CAO](#chương-6-thiết-kế-động-học-hệ-thống--cơ-chế-giao-dịch-nâng-cao)
  - [6.1. Biểu đồ Tuần tự tương tác đa tầng (Multi-tier Sequence Diagrams)](#61-biểu-đồ-tuần-tự-tương-tác-đa-tầng-multi-tier-sequence-diagrams)
  - [6.2. Biểu đồ Máy trạng thái đối tượng (State Machine Diagrams)](#62-biểu-đồ-máy-trạng-thái-đối-tượng-state-machine-diagrams)
  - [6.3. Các giải pháp kỹ thuật nâng cao giải quyết tranh chấp đồng thời](#63-các-giải-pháp-kỹ-thuật-nâng-cao-giải-quyết-tranh-chấp-đồng-thời)
- [CHƯƠNG 7: THIẾT KẾ GIAO DIỆN & KẾT QUẢ KIỂM THỬ THỰC NGHIỆM](#chương-7-thiết-kế-giao-diện--kết-quả-kiểm-thử-thực-nghiệm)
  - [7.1. Sơ đồ luồng điều hướng màn hình (UI Navigation Flow)](#71-sơ-đồ-luồng-điều-hướng-màn-hình-ui-navigation-flow)
  - [7.2. Bộ minh chứng giao diện nguyên mẫu thực nghiệm (Prototype UI Walkthrough)](#72-bộ-minh-chứng-giao-diện-nguyên-mẫu-thực-nghiệm-prototype-ui-walkthrough)
  - [7.3. Kế hoạch và kết quả kiểm thử tích hợp tự động Backend (14/14 Test Cases Passed)](#73-kế-hoạch-và-kết-quả-kiểm-thử-tích-hợp-tự-động-backend-1414-test-cases-passed)
  - [7.4. Kết quả kiểm thử hộp đen E2E tự động trên giao diện Web (16/16 Scenarios Passed)](#74-kết-quả-kiểm-thử-hộp-đên-e2e-tự-động-trên-giao-diện-web-1616-scenarios-passed)
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
| **Hình 5.1** | Mô hình Thực thể Quan hệ CSDL 9 thực thể (ERD Crow's Foot Notation) | [diagram/ERD_DuLieu.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/ERD_DuLieu.svg) |
| **Hình 6.1** | Biểu đồ Tuần tự đa tầng luồng trừ kho tự động `@Transactional` | [diagram/Sequence_TruKho.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_TruKho.svg) |
| **Hình 6.2** | Biểu đồ Tuần tự đa tầng quy trình Bán hàng POS và Lưu đơn | [diagram/Sequence_POS.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_POS.svg) |
| **Hình 6.3** | Biểu đồ Tuần tự đa tầng quy trình Check-in ca làm việc nhân sự | [diagram/Sequence_ChamCong.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_ChamCong.svg) |
| **Hình 6.4** | Biểu đồ Máy trạng thái vòng đời Bàn và Hóa đơn (State Machine) | [diagram/StateMachine_TrangThai.svg](file:///d:/3.1.%20PTTKYC/CK/diagram/StateMachine_TrangThai.svg) |
| **Hình 7.1** | Sơ đồ luồng điều hướng màn hình (UI Navigation Flow) | [02_SourceCode_WebJava_Prototype/BanGiao_UML/UI_Navigation_Flow.svg](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/BanGiao_UML/UI_Navigation_Flow.svg) |
| **Hình 7.2** | Giao diện Đăng nhập hệ thống (Chuẩn tiếng Việt, không icon lỗi) | [screenshots/fixed_vietnamese_ui.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_vietnamese_ui.png) |
| **Hình 7.3** | Màn hình Bán hàng POS và Sơ đồ 10 bàn đổi màu động thời gian thực | [screenshots/fixed_pos_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_pos_screen.png) |
| **Hình 7.4** | Mở lại đơn hàng đang phục vụ của Bàn 03 (`CO_KHACH`) | [screenshots/03_table3_occupied_loaded.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/03_table3_occupied_loaded.png) |
| **Hình 7.5** | Tạo đơn hàng mới tại Bàn 02, thêm món kèm ghi chú pha chế "ít đường" | [screenshots/04_cart_table2.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/04_cart_table2.png) |
| **Hình 7.6** | Lưu đơn Đơn #4 thành công, Bàn 02 chuyển sang trạng thái viền đỏ | [screenshots/05_order_saved_table2.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/05_order_saved_table2.png) |
| **Hình 7.7** | Hộp thoại Thanh toán: Chặn tiền khách đưa chưa đủ và khóa ô chiết khấu | [screenshots/06_payment_modal_validation.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/06_payment_modal_validation.png) |
| **Hình 7.8** | Hóa đơn bán lẻ COFFEE POS #4 in nhiệt hoàn tất (Tiền thối: 33.000 đ) | [screenshots/07_receipt_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/07_receipt_modal.png) |
| **Hình 7.9** | Màn hình Quản trị Kho: Tồn Sữa đặc giảm còn 90ml, nhãn đỏ SẮP HẾT HÀNG | [screenshots/fixed_kho_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_kho_screen.png) |
| **Hình 7.10** | Hộp thoại Quản lý Định mức Công thức pha chế (Recipe - BOM) | [screenshots/09_recipe_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/09_recipe_modal.png) |
| **Hình 7.11** | Hộp thoại Nhập thêm kho nguyên liệu (Placeholder nhập số lượng) | [screenshots/10_nhapkho_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/10_nhapkho_modal.png) |
| **Hình 7.12** | Tồn kho Sữa đặc khôi phục lên 590ml, nhãn khôi phục xanh "Đủ hàng" | [screenshots/11_kho_restocked.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/11_kho_restocked.png) |
| **Hình 7.13** | Màn hình Quản lý Nhân sự & Bảng Chấm công thời gian thực | [screenshots/fixed_nhansu_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_nhansu_screen.png) |
| **Hình 7.14** | Ghi nhận Check-in ca làm việc thành công, gắn nhãn đúng giờ/đi trễ | [screenshots/13_attendance_checked_in.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/13_attendance_checked_in.png) |
| **Hình 7.15** | Hộp thoại Thêm mới hồ sơ nhân sự phân quyền vai trò | [screenshots/14_add_employee_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/14_add_employee_modal.png) |
| **Hình 7.16** | Tài khoản Quản lý áp dụng chiết khấu 5.000 đ xuất hóa đơn thành công | [screenshots/15_manager_discount_receipt.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/15_manager_discount_receipt.png) |

---

## DANH MỤC BẢNG BIỂU

- **Bảng 1.1:** Đối chiếu hiện trạng thủ công (*As-Is*) và giải pháp hệ thống số hóa (*To-Be*)
- **Bảng 2.1:** Bảng phân rã sự kiện nghiệp vụ hệ thống (Event Decomposition Table)
- **Bảng 2.2:** Ma trận phân loại và ưu tiên hóa yêu cầu chức năng theo phương pháp MoSCoW
- **Bảng 2.3:** Bảng danh mục yêu cầu phi chức năng (NFR) kèm thang đo lường định lượng
- **Bảng 3.1:** Phân loại và tổng hợp danh mục Use Case theo từng phân hệ
- **Bảng 5.1:** Từ điển dữ liệu chi tiết Bảng `nhan_vien` (Employee Table)
- **Bảng 5.2:** Từ điển dữ liệu chi tiết Bảng `ca_lam` (Work Shift Table)
- **Bảng 5.3:** Từ điển dữ liệu chi tiết Bảng `cham_cong` (Attendance Record Table)
- **Bảng 5.4:** Từ điển dữ liệu chi tiết Bảng `ban` (Dining Table)
- **Bảng 5.5:** Từ điển dữ liệu chi tiết Bảng `mon_nuoc` (Beverage Menu Table)
- **Bảng 5.6:** Từ điển dữ liệu chi tiết Bảng `nguyen_lieu` (Raw Material Table)
- **Bảng 5.7:** Từ điển dữ liệu chi tiết Bảng `cong_thuc` (Bill of Materials Recipe Table)
- **Bảng 5.8:** Từ điển dữ liệu chi tiết Bảng `hoa_don` (Order Receipt Table)
- **Bảng 5.9:** Từ điển dữ liệu chi tiết Bảng `chi_tiet_hoa_don` (Order Item Line Table)
- **Bảng 7.1:** Kết quả kiểm thử tích hợp tự động Backend (14/14 Integration Test Cases)
- **Bảng 7.2:** Kết quả kiểm thử hộp đen liên thông trên giao diện Web (16/16 E2E Test Cases)
- **Bảng 7.3:** Bảng tổng hợp lỗi, phân loại mức độ và trạng thái khắc phục triệt để (BUG-01 đến BUG-06)
- **Bảng 9.1:** Bảng phân công nhiệm vụ và tỷ lệ đóng góp của từng thành viên trong nhóm

---

## DANH MỤC THUẬT NGỮ & TỪ VIẾT TẮT

* **POS (Point of Sale):** Điểm bán hàng / Phần mềm bán lẻ và quản lý giao dịch tại quầy thu ngân.
* **BOM (Bill of Materials):** Bảng định mức công thức cấu thành sản phẩm đồ uống từ các nguyên vật liệu.
* **ACID (Atomicity, Consistency, Isolation, Durability):** Bốn thuộc tính cốt lõi đảm bảo độ tin cậy của giao dịch cơ sở dữ liệu.
* **RBAC (Role-Based Access Control):** Cơ chế kiểm soát quyền truy cập tài nguyên dựa trên vai trò người dùng (`ADMIN`, `MANAGER`, `STAFF`).
* **ORM (Object-Relational Mapping):** Kỹ thuật ánh xạ giữa các bảng CSDL quan hệ và các lớp thực thể trong lập trình hướng đối tượng.
* **JPA (Java Persistence API):** Chuẩn giao diện quản lý dữ liệu đối tượng bền vững trong hệ sinh thái Java.
* **DTO (Data Transfer Object):** Đối tượng truyền tải dữ liệu giữa tầng Presentation và tầng Application Service.
* **E2E Testing (End-to-End Testing):** Phương pháp kiểm thử liên thông toàn diện từ giao diện người dùng đến cơ sở dữ liệu.
* **Idempotency Key:** Khóa định danh duy nhất (UUID) bảo vệ hệ thống không thực hiện lặp lại một hành động khi nhận nhiều request giống nhau do lỗi mạng.
* **Lost Update:** Sự cố mất mát dữ liệu khi hai giao dịch cùng đọc và ghi đè đồng thời lên cùng một bản ghi.

---

## CHƯƠNG 1: TỔNG QUAN ĐỀ TÀI & PHẠM VI HỆ THỐNG

### 1.1. Bối cảnh và tính cấp thiết của đề tài
Trong sự phát triển bùng nổ của ngành dịch vụ ăn uống (Food and Beverage – F&B) tại Việt Nam, mô hình quán cà phê vừa và nhỏ (chuỗi cửa hàng độc lập, quán cà phê sân vườn, quán cà phê acoustic) đóng góp một phần đáng kể vào doanh thu bán lẻ. Tuy nhiên, qua quá trình khảo sát thực tế, phần lớn các quán cà phê quy mô này vẫn đang dựa dẫm vào các thao tác thủ công thô sơ:
1. Nhân viên phục vụ ghi chép order của khách bằng giấy viết tay hoặc sổ order mini. Vào các khung giờ cao điểm (sáng sớm $07:30\text{--}09:00$ hoặc buổi tối cuối tuần), việc chuyển giấy từ bàn phục vụ đến quầy pha chế và quầy thu ngân thường xuyên xảy ra tình trạng sót món, nhầm bàn, hoặc khách đợi quá lâu dẫn đến bức xúc.
2. Thu ngân sử dụng máy tính bấm tay để tính tiền và thối tiền. Việc áp dụng các chương trình giảm giá, chiết khấu cho khách quen hoặc quản lý hình thức chuyển khoản qua tài khoản cá nhân thường không được kiểm soát chặt chẽ, dễ phát sinh thất thoát tài chính ngầm.
3. Người pha chế (Bartender/Barista) pha chế đồ uống theo thói quen định lượng cảm tính. Cuối ngày, chủ quán kiểm kê nguyên liệu chỉ đếm số lượng lon sữa hoặc gói hạt cà phê còn thừa, mà hoàn toàn không thể đối chiếu xem lượng hao hụt thực tế có khớp với số lượng đồ uống đã bán hay do bị đổ vỡ, hư hỏng hoặc thất thoát nội bộ.
4. Lực lượng nhân sự quán cà phê phần lớn là sinh viên làm việc bán thời gian (part-time). Lịch học của sinh viên thay đổi liên tục, việc đổi ca diễn ra thường xuyên khiến bảng phân ca giấy bị gạch xóa nham nhở, dẫn đến việc thiếu nhân sự trong giờ cao điểm nhưng lại thừa người trong giờ vắng khách. Đến kỳ tính lương cuối tháng, chủ quán phải cộng nhẩm từng dòng chấm công thủ công rất dễ xảy ra khiếu nại, sai sót.

Từ thực trạng trên, việc xây dựng một **"Hệ Thống Quản Lý Quán Cà Phê"** toàn diện, ứng dụng công nghệ phần mềm hiện đại nhằm số hóa đồng bộ và tự động hóa toàn bộ luồng nghiệp vụ từ **Gọi món tại bàn $\rightarrow$ Thu ngân xuất hóa đơn $\rightarrow$ Tự động trừ kho theo định mức công thức $\rightarrow$ Cảnh báo hết hàng $\rightarrow$ Chấm công nhân sự $\rightarrow$ Báo cáo KPI** là một yêu cầu cấp thiết, mang tính ứng dụng thực tiễn cao.

### 1.2. Mục tiêu nghiên cứu và xây dựng phần mềm
Đề tài hướng tới 4 mục tiêu trọng tâm:
* **Mục tiêu 1 – Chuẩn hóa nghiệp vụ Bán hàng POS:** Xây dựng giao diện bàn trực quan ($10$ bàn); hỗ trợ gọi món tại chỗ và mang đi (*Takeaway*); ghi nhận ghi chú pha chế (ít đường, không đá); tự động tính toán tổng tiền, chiết khấu và tiền thối chính xác; in phiếu thanh toán hóa đơn nhiệt.
* **Mục tiêu 2 – Tự động hóa quản trị Kho & Định mức công thức (BOM):** Thiết lập cơ chế bóc tách định mức nguyên liệu tiêu hao cho từng suất đồ uống. Khi hóa đơn thanh toán thành công, hệ thống tự động thực thi trừ kho ngầm trong cùng một giao dịch nguyên tử `@Transactional`. Tự động kích hoạt cảnh báo trực quan khi tồn kho chạm ngưỡng tối thiểu an toàn.
* **Mục tiêu 3 – Số hóa quản lý Nhân sự & Chấm công:** Quản lý hồ sơ nhân viên theo vai trò; quản lý lịch phân ca tuần; chức năng Check-in/Check-out thời gian thực tự động đối chiếu giờ làm để gắn nhãn *Đúng giờ* hoặc *Đi trễ*.
* **Mục tiêu 4 – Hiện thực hóa nguyên mẫu phần mềm chất lượng cao:** Ứng dụng kiến trúc **3-Tier All-in-one Spring Boot Application**, tuân thủ nguyên lý hướng đối tượng, tích hợp cơ sở dữ liệu quan hệ MySQL chuẩn 3NF, xử lý triệt để bài toán tranh chấp tài nguyên đồng thời và vượt qua 100% các kịch bản kiểm thử nghiêm ngặt.

### 1.3. Phạm vi hệ thống (In-Scope và Out-of-Scope)
Để đảm bảo đề tài hoàn thành đúng tiến độ và tập trung sâu vào các nghiệp vụ cốt lõi, phạm vi hệ thống được xác định rõ:

#### 1.3.1. Phạm vi thực hiện (In-Scope)
* **Phân hệ Bán hàng POS:** Quản lý sơ đồ 10 bàn; hỗ trợ tạo đơn tại bàn và mang đi; lưu đơn chờ thanh toán; mở lại đơn theo bàn; cập nhật số lượng món; áp dụng chiết khấu (phân quyền cho Quản lý); thanh toán tiền mặt/chuyển khoản và in hóa đơn bán lẻ.
* **Phân hệ Quản lý Kho & Định mức BOM (Trọng tâm Duy):** Quản lý danh mục nguyên vật liệu (CRUD); quản lý bảng định mức công thức pha chế (BOM); tự động trừ tồn kho theo giao dịch bán lẻ; cơ chế bật nhãn đỏ cảnh báo `SẮP HẾT HÀNG`; chức năng nhập thêm nguyên liệu vào kho; bảng điều khiển KPI Dashboard (Tổng doanh thu, số đơn, số nguyên liệu chạm ngưỡng báo động).
* **Phân hệ Quản lý Nhân sự & Ca làm việc (Trọng tâm Đạt):** Quản lý hồ sơ nhân sự; cấu hình ca làm việc (Sáng, Chiều, Tối); chức năng chấm công Check-in / Check-out tự động nhận diện đi trễ; theo dõi lịch sử ca làm.
* **Phân hệ Xác thực & Phân quyền RBAC:** Đăng nhập, quản lý phiên làm việc (`HttpSession`), phân quyền 3 vai trò: `ADMIN` (Quản trị toàn quyền), `MANAGER` (Quản lý kho, công thức, chiết khấu, nhân sự) và `STAFF` (Thu ngân, phục vụ, chấm công).

#### 1.3.2. Ngoại phạm vi (Out-of-Scope)
* Không xây dựng phân hệ hạch toán kế toán chuyên sâu (không trích khấu hao tài sản cố định, không lập báo cáo tài chính kê khai thuế VAT cho cơ quan nhà nước).
* Không tích hợp trực tiếp với cổng thanh toán thẻ ngân hàng thực tế (Payment Gateway qua Webhook của VNPay, Momo) mà mô phỏng thanh toán tiền mặt và xác nhận chuyển khoản thủ công qua mã VietQR tĩnh.
* Không phát triển ứng dụng di động bản địa riêng (Native Mobile App trên iOS/Android); toàn bộ hệ thống vận hành tập trung trên nền tảng Web Responsive chạy mượt mà trên trình duyệt.

### 1.4. Bảng đối chiếu hiện trạng thủ công và giải pháp chuyển đổi số

**Bảng 1.1: Đối chiếu hiện trạng thủ công (As-Is) và giải pháp hệ thống số hóa (To-Be)**

| Tiêu chí so sánh | Quy trình thủ công hiện tại (*As-Is*) | Hệ thống số hóa mới (*To-Be*) | Lợi ích nghiệp vụ đạt được |
| :--- | :--- | :--- | :--- |
| **Ghi nhận đơn hàng (POS)** | Phục vụ ghi giấy, dễ thất lạc, chữ viết tay khó đọc; thu ngân bấm máy tính tay. | Chọn món trực quan trên màn hình cảm ứng POS; bàn đổi màu động (`TRONG` xanh $\leftrightarrow$ `CO_KHACH` đỏ). | Rút ngắn $70\%$ thời gian tạo đơn; loại bỏ $100\%$ tình trạng nhầm bàn hoặc thất lạc order. |
| **Tính toán thanh toán** | Tính nhẩm hoặc cộng tay, dễ sai sót tiền thối; khó kiểm soát chiết khấu. | Hệ thống tự tính tự động tổng tiền, tiền thối; chỉ Manager mới có quyền nhập chiết khấu. | Triệt tiêu sai lệch tiền thối; ngăn chặn gian lận chiết khấu từ nhân viên thu ngân. |
| **Quản trị tồn kho** | Đếm thủ công cuối ngày theo cảm tính; không rõ nguyên nhân hao hụt. | Bóc tách định mức BOM; tự động trừ kho ngầm theo từng ly nước bán ra trong giao dịch `@Transactional`. | Kiểm soát chính xác lượng nguyên liệu tiêu hao; đối chiếu thất thoát kho tức thì theo thời gian thực. |
| **Cảnh báo hết nguyên liệu** | Đến khi pha chế mở hộp hết nguyên liệu mới báo; dễ phải từ chối khách. | Tự động kích hoạt nhãn đỏ `SẮP HẾT HÀNG` và cảnh báo trên KPI Dashboard khi chạm ngưỡng tồn tối thiểu. | Chủ động lập kế hoạch nhập hàng trước $24\text{h}$, không bao giờ bị đứt gãy nguyên liệu pha chế. |
| **Chấm công nhân sự** | Ký sổ tay hoặc ghi bảng phấn; sinh viên đổi ca khó theo dõi, cuối tháng cãi vã giờ công. | Nhân viên tự Check-in trên web; server lấy giờ thực tế đối chiếu ca làm, tự gắn nhãn *Đúng giờ* / *Đi trễ*. | Minh bạch $100\%$ dữ liệu chấm công; tự động tổng hợp báo cáo ngày công không tốn nhân lực. |

---

## CHƯƠNG 2: KHẢO SÁT HIỆN TRẠNG & THU THẬP YÊU CẦU

### 2.1. Khảo sát quy trình nghiệp vụ vận hành thực tế tại quán cà phê
Để có dữ liệu thực tế phục vụ phân tích, nhóm đã tiến hành khảo sát trực tiếp tại một số quán cà phê mô hình vừa và nhỏ tại khu vực Quận 7, TP. Hồ Chí Minh. Quy trình hoạt động hàng ngày của một quán cà phê bao gồm 4 chuỗi nghiệp vụ chính:
1. **Đầu ca làm việc (06:30 - 07:00):** Nhân viên phục vụ và pha chế đến quán, ký tên vào sổ điểm danh. Nhân viên kiểm tra sơ bộ bàn ghế, bật máy pha cà phê và kiểm tra một số nguyên liệu cơ bản như sữa tươi, đá viên.
2. **Khung giờ vận hành bán hàng (07:00 - 22:00):**
   - Khách hàng vào quán có thể chọn ngồi tại bàn hoặc gọi mang đi.
   - Nhân viên phục vụ đến bàn đưa menu giấy, ghi lại các món khách chọn và các ghi chú đặc biệt (ít ngọt, không đá, sữa chua không đường).
   - Tờ phiếu order được xé làm 2 liên: một liên cắm vào thanh order tại quầy pha chế, một liên chuyển đến quầy thu ngân.
   - Nhân viên pha chế nhìn phiếu làm đồ uống, khi hoàn tất bấm chuông để nhân viên phục vụ bê ra bàn cho khách.
   - Khi khách gọi thanh toán, thu ngân tìm phiếu của bàn đó, tính tiền, nhận tiền mặt từ khách và thối lại tiền thừa.
3. **Nghiệp vụ nhập nguyên liệu:** Định kỳ 2 - 3 ngày một lần, các nhà cung cấp (hạt cà phê, sữa đặc, trà, siro) giao hàng đến. Quản lý nhận hàng, ký phiếu giao nhận và xếp vào kho. Số liệu nhập chỉ được ghi chép vào một cuốn sổ riêng.
4. **Kết ca và cuối ngày (22:00 - 22:30):** Thu ngân kiểm đếm tiền mặt trong két đối chiếu với tổng các liên order đã lưu; người pha chế kiểm đếm sơ lược các nguyên liệu còn tồn trên kệ; nhân viên ký tên ra về.

### 2.2. Nhận diện các bên liên quan (Stakeholders) và tác nhân (Actors)
Hệ thống xác định 4 nhóm đối tượng liên quan:
* **Khách hàng (Customer):** Đối tượng thụ hưởng dịch vụ; mong muốn được phục vụ nhanh chóng, chính xác theo đúng khẩu vị (ghi chú pha chế) và nhận hóa đơn thanh toán rõ ràng, minh bạch.
* **Nhân viên (Staff - Actor):** Bao gồm nhân viên phục vụ, nhân viên thu ngân và nhân viên pha chế. Cần công cụ tạo đơn hàng nhanh, dễ dùng, trực quan và thực hiện chấm công hàng ngày.
* **Quản lý cửa hàng (Manager - Actor):** Chịu trách nhiệm giám sát toàn bộ hoạt động của quán: phê duyệt chiết khấu, thiết lập định mức công thức pha chế (BOM), theo dõi cảnh báo kho và nhập thêm nguyên vật liệu, phân ca và giám sát chấm công.
* **Chủ quán / Quản trị viên (Admin - Actor):** Có toàn quyền tối cao trên hệ thống: phân quyền nhân sự, cấu hình danh mục món và giá bán, xem báo cáo doanh thu tổng quan toàn quán.

### 2.3. Phân tích 3 "điểm nghẽn" nghiệp vụ chí mạng cần giải quyết
1. **Điểm nghẽn 1 – Sai sót và tắc nghẽn thông tin tại quầy thu ngân & bàn gọi món:**  
   Vào giờ cao điểm, việc truyền đạt thông tin món bằng giấy viết tay hoặc truyền miệng dẫn đến tỷ lệ sai món lên đến $15\%$. Khách chuyển bàn nhưng nhân viên không cập nhật kịp thời, dẫn đến thanh toán nhầm hóa đơn của bàn khác.
2. **Điểm nghẽn 2 – Không thể kiểm soát hao hụt và thất thoát kho nguyên vật liệu:**  
   Do thiếu công thức định mức số hóa, chủ quán không có căn cứ định lượng để kiểm tra người pha chế dùng bao nhiêu ml sữa hay bao nhiêu gam cà phê mỗi ngày. Thất thoát nguyên liệu thường chiếm từ $8\text{--}12\%$ tổng chi phí nguyên liệu hàng tháng.
3. **Điểm nghẽn 3 – Xung đột ca làm việc và tính toán ngày công thiếu chính xác:**  
   Bảng phân ca giấy dễ bị ghi đè, trùng lịch hoặc thiếu người; nhân viên đi trễ về sớm không có ghi nhận chính xác thời gian thực tế, tạo ra sự thiếu công bằng trong nội bộ nhân viên.

### 2.4. Bảng phân rã sự kiện nghiệp vụ (Event Decomposition Table)
Theo phương pháp kỹ nghệ yêu cầu của học phần, các sự kiện tác động lên hệ thống được phân thành 3 loại: **Sự kiện bên ngoài (External Events)**, **Sự kiện trạng thái (State Events)**, và **Sự kiện thời gian (Temporal Events)**.

**Bảng 2.1: Bảng phân rã sự kiện nghiệp vụ hệ thống (Event Decomposition Table)**

| Mã SK | Tên sự kiện nghiệp vụ | Loại sự kiện | Tác nhân kích hoạt (Trigger/Source) | Phản ứng của hệ thống (System Response) | Use Case tương ứng |
| :---: | :--- | :---: | :--- | :--- | :--- |
| **E-01** | Người dùng đăng nhập hệ thống | External | Staff / Manager / Admin | Xác thực thông tin, tạo phiên làm việc bảo mật và điều hướng giao diện theo vai trò. | `UC01: Đăng nhập` |
| **E-02** | Khách hàng vào quán gọi món | External | Staff (Phục vụ) | Tạo đơn hàng mới, cập nhật bàn sang `CO_KHACH`, lưu chi tiết danh sách món và ghi chú. | `UC03: Tạo đơn hàng` |
| **E-03** | Khách đổi món hoặc hủy món | External | Staff / Khách hàng | Cập nhật lại số lượng món trong đơn hoặc xóa món khỏi giỏ hàng đang mở. | `UC04: Cập nhật món` |
| **E-04** | Khách yêu cầu thanh toán đơn | External | Staff / Manager | Kiểm tra tiền nhận, tính tiền thừa, cập nhật đơn sang `DA_THANH_TOAN`, trả bàn về `TRONG`, in Bill. | `UC06: Thanh toán hóa đơn` |
| **E-05** | Hóa đơn được thanh toán xong | State | Hệ thống (Trigger ngầm) | Tự động bóc tách định mức BOM, trừ số lượng tồn kho nguyên liệu tương ứng trong CSDL. | `UC06: Tự động trừ kho` |
| **E-06** | Tồn kho chạm ngưỡng tối thiểu | State | Hệ thống (Trigger ngầm) | Tự động bật nhãn cảnh báo đỏ `SẮP HẾT HÀNG` và cập nhật chỉ số lên KPI Dashboard. | `UC05: Cảnh báo tồn kho` |
| **E-07** | Quản lý nhập thêm hàng về kho | External | Manager / Admin | Tiếp nhận số lượng nhập thực tế, cộng dồn vào tồn kho CSDL và xóa nhãn cảnh báo đỏ. | `UC05: Nhập kho nguyên liệu` |
| **E-08** | Nhân viên đến ca làm việc | External | Staff | Ghi nhận thời gian Check-in thực tế, đối chiếu giờ ca làm để gắn trạng thái *Đúng giờ* / *Đi trễ*. | `UC07: Chấm công nhân sự` |
| **E-09** | Đến giờ chốt ca làm việc | Temporal | Hệ thống (Thời gian thực) | Tự động cập nhật đồng hồ ca làm và khóa phiên chấm công của ca trước. | `UC07: Quản lý ca làm` |

### 2.5. Danh mục 15 yêu cầu chức năng cốt lõi (FR-01 đến FR-15)
Hệ thống bao phủ đầy đủ 15 yêu cầu chức năng theo chuẩn mực đề thi:

* **Nhóm 1 – Phân hệ Xác thực & Phân quyền (Security & Access Control):**
  * **FR-01 (Đăng nhập xác thực):** Hệ thống phải cho phép người dùng đăng nhập bằng tên đăng nhập và mật khẩu, xác thực thông qua API bảo mật phía máy chủ và phân quyền vai trò.
  * **FR-02 (Quản lý phiên làm việc & Đăng xuất):** Hệ thống phải duy trì phiên làm việc qua `HttpSession`, bảo vệ dữ liệu nhạy cảm (ẩn trường mật khẩu khi trả về JSON) và xóa sạch phiên khi người dùng đăng xuất.
* **Nhóm 2 – Phân hệ Bán hàng POS & Quản lý bàn (POS Operations):**
  * **FR-03 (Sơ đồ bàn động):** Hệ thống phải hiển thị sơ đồ 10 bàn với màu sắc trực quan (Xanh: Trống, Đỏ: Có khách, Viền xanh dương: Đang chọn).
  * **FR-04 (Mở lại đơn đang phục vụ):** Khi người dùng nhấp vào một bàn đang có khách, hệ thống phải tự động tải lại toàn bộ danh mục món đang phục vụ của bàn đó lên giỏ hàng.
  * **FR-05 (Gọi món tại bàn & Mang đi):** Hệ thống phải hỗ trợ gọi món linh hoạt theo 2 chế độ: gắn với mã bàn hoặc chế độ mang đi (*Takeaway*).
  * **FR-06 (Ghi chú pha chế):** Cho phép nhập ghi chú riêng cho từng dòng đồ uống (ví dụ: "ít đường", "không đá", "mang về").
  * **FR-07 (Cập nhật & Hủy đơn):** Cho phép tăng/giảm số lượng món, hủy món trong giỏ nháp hoặc hủy toàn bộ đơn hàng đang mở để giải phóng bàn về trống (không trừ kho).
  * **FR-08 (Phân quyền chiết khấu):** Cho phép áp dụng giảm giá hóa đơn; hệ thống bắt buộc kiểm tra phân quyền RBAC: chỉ cho phép `MANAGER` hoặc `ADMIN` nhập chiết khấu, chặn tuyệt đối tài khoản `STAFF`.
  * **FR-09 (Thanh toán & Xuất hóa đơn):** Hỗ trợ phương thức Tiền mặt và Chuyển khoản QR; kiểm tra tiền khách đưa hợp lệ; cập nhật trạng thái đơn thành `DA_THANH_TOAN`, giải phóng bàn về `TRONG` và xuất hóa đơn nhiệt.
* **Nhóm 3 – Phân hệ Kho nguyên liệu & Định mức BOM (Inventory & Recipe):**
  * **FR-10 (Quản lý danh mục nguyên liệu):** Quản lý thông tin nguyên vật liệu (Mã, tên, đơn vị tính, số lượng tồn, ngưỡng tồn tối thiểu, đơn giá nhập).
  * **FR-11 (Thiết lập định mức công thức BOM):** Cho phép thiết lập bảng định mức pha chế cho từng món nước, quy định chi tiết lượng tiêu hao của từng nguyên liệu thành phần cho 1 ly đồ uống.
  * **FR-12 (Tự động trừ kho nguyên tử):** Khi hóa đơn thanh toán thành công, hệ thống phải tự động bóc tách công thức và trừ số lượng tồn kho tương ứng trong CSDL trong cùng giao dịch `@Transactional`.
  * **FR-13 (Cảnh báo tồn kho đỏ & Nhập kho):** Tự động bật nhãn cảnh báo đỏ `SẮP HẾT HÀNG` khi lượng tồn $\le$ ngưỡng an toàn; cho phép thủ kho mở popup nhập thêm số lượng tồn kho thực tế.
* **Nhóm 4 – Phân hệ Nhân sự & Chấm công (HR & Attendance):**
  * **FR-14 (Quản lý hồ sơ nhân viên):** Quản lý danh sách nhân sự (Thêm mới, cập nhật, xóa, phân vai trò `ADMIN`/`MANAGER`/`STAFF`).
  * **FR-15 (Chấm công ca làm thời gian thực):** Nhân viên thực hiện Check-in/Check-out ca làm; hệ thống tự động đối chiếu thời gian máy chủ với giờ bắt đầu ca để phân loại trạng thái *Đúng giờ* hoặc *Đi trễ*.

### 2.6. Danh mục yêu cầu phi chức năng (NFR-01 đến NFR-06) kèm chỉ số đo lường

**Bảng 2.2: Bảng danh mục yêu cầu phi chức năng (NFR) kèm thang đo lường định lượng**

| Mã NFR | Nhóm chất lượng | Tiêu chí yêu cầu chi tiết | Chỉ số đo lường định lượng (Metrics) | Phương pháp kiểm chứng |
| :---: | :--- | :--- | :--- | :--- |
| **NFR-01** | **Hiệu năng (Performance)** | Thời gian phản hồi của các API nghiệp vụ chính (tạo đơn, tải danh mục món, thanh toán, trừ kho). | Thời gian xử lý trung bình $< 500\text{ms}$; tải toàn bộ trang web $< 2\text{ giây}$. | Đo bằng Chrome DevTools Network & JMeter load test. |
| **NFR-02** | **Tính toàn vẹn dữ liệu (Integrity)** | Đảm bảo tính nhất quán của giao dịch trừ kho và thanh toán; không thất thoát hoặc sai lệch tồn kho. | Tỷ lệ thành công $100\%$ tuân thủ nguyên tử ACID; rollback $100\%$ khi có lỗi thiếu kho. | Thực thi 14 ca kiểm thử tích hợp tự động `@Transactional`. |
| **NFR-03** | **Bảo mật & Phân quyền (Security)** | Kiểm soát quyền truy cập RBAC; không rò rỉ mật khẩu người dùng trong JSON; bảo vệ chống truy cập URL trái phép. | $0\%$ rò rỉ trường `matKhau`; chặn $100\%$ tài khoản `STAFF` truy cập trang quản trị Kho & Nhân sự. | Unit test MockMvc & E2E Route Guard audit. |
| **NFR-04** | **Tính khả dụng (Usability)** | Giao diện thân thiện, sử dụng tiếng Việt có dấu chuẩn mực, không phụ thuộc icon rỗng, thao tác nhanh. | Nhân viên thu ngân hoàn tất tạo đơn và thanh toán trong dưới $4$ thao tác nhấp chuột. | Đo đạc thời gian thao tác thực nghiệm của người dùng mẫu. |
| **NFR-05** | **Khả năng đồng thời (Concurrency)** | Chống xung đột khi nhiều thu ngân cùng thao tác trên 1 đơn hàng hoặc nhiều đơn cùng trừ 1 nguyên liệu sát ngưỡng. | $0\%$ đơn hàng bị ghi đè dữ liệu (Lost Update); $0\%$ bán khống nguyên liệu (Oversell). | Kiểm thử đa luồng (Multi-threading Test với 2 luồng đồng thời). |
| **NFR-06** | **Tính khả chuyển (Portability)** | Chạy ổn định trên mọi nền tảng hệ điều hành hỗ trợ Java 17+ và các trình duyệt web hiện đại. | Hoạt động $100\%$ trên Windows 10/11, macOS, Linux với Chrome, Edge, Firefox, Safari. | Kiểm thử thực tế trên các môi trường khác nhau. |

### 2.7. Bảng phân loại và ưu tiên hóa yêu cầu theo phương pháp MoSCoW

**Bảng 2.3: Bảng phân loại và ưu tiên hóa yêu cầu chức năng theo phương pháp MoSCoW**

| Phân nhóm MoSCoW | Danh sách mã yêu cầu chức năng (FR) | Lý giải quyết định phân loại |
| :---: | :--- | :--- |
| **Must-Have (Bắt buộc phải có)** | `FR-01`, `FR-02`, `FR-03`, `FR-04`, `FR-05`, `FR-06`, `FR-07`, `FR-08`, `FR-09`, `FR-10`, `FR-11`, `FR-12`, `FR-13`, `FR-14`, `FR-15` | Đây là các chức năng nền tảng sống còn cấu thành quy trình vận hành quán cà phê khép kín (*Gọi món $\rightarrow$ Trừ kho $\rightarrow$ Chấm công*). Nhóm cam kết và đã hiện thực hóa $100\%$ các chức năng này trên ứng dụng chạy thực tế. |
| **Should-Have (Nên có nếu đủ thời gian)** | Quản lý lịch sử thay đổi công thức (BOM Versioning); Cho phép tách bàn / ghép bàn khi quán đông khách. | Đã được chuẩn bị sẵn cấu trúc thực thể CSDL (bảng `chi_tiet_hoa_don` hỗ trợ lưu lịch sử), sẽ được mở rộng trong phiên bản tiếp theo. |
| **Could-Have (Có thể phát triển thêm)** | Tích hợp hiển thị mã VietQR động tự động điền đúng số tiền thanh toán; Báo cáo doanh thu biểu đồ dạng cột (Chart.js). | Là tính năng gia tăng trải nghiệm người dùng, có thể tích hợp qua thư viện JS frontend mà không ảnh hưởng backend lõi. |
| **Won't-Have (Không thực hiện lần này)** | Phân hệ kế toán chuyên sâu (thuế VAT, khấu hao); Tích hợp máy quét vân tay phần cứng chuyên dụng để chấm công. | Nằm ngoài phạm vi của đề tài đồ án học phần Phân tích Thiết kế Yêu cầu (Out-of-Scope). |

---

## CHƯƠNG 3: MÔ HÌNH HÓA USE CASE & PHÂN TÍCH NGHIỆP VỤ HỆ THỐNG

### 3.1. Biểu đồ Use Case tổng quát toàn hệ thống
Biểu đồ Use Case tổng quát mô tả ranh giới hệ thống (*System Boundary*), các tác nhân chính và toàn bộ các trường hợp sử dụng cùng mối quan hệ phụ thuộc `<<include>>` và mở rộng `<<extend>>`.

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 3.1:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\UseCaseTongQuat.svg`  
> ![Hình 3.1: Biểu đồ Use Case tổng quát toàn hệ thống](file:///d:/3.1.%20PTTKYC/CK/diagram/UseCaseTongQuat.svg)

#### Phân tích chi tiết Biểu đồ Use Case tổng quát (Hình 3.1):
1. **Ranh giới hệ thống (*System Boundary*):** Toàn bộ các Use Case được đóng gói chặt chẽ bên trong khối ranh giới "Hệ Thống Quản Lý Quán Cà Phê".
2. **Các tác nhân (*Actors*):**
   * **Nhân viên (Staff):** Đóng vai trò là người trực tiếp tương tác với phân hệ Bán hàng POS và Chấm công cá nhân. Kế thừa quyền hạn cơ bản từ hệ thống.
   * **Quản lý (Manager):** Có toàn bộ quyền hạn của Nhân viên, đồng thời sở hữu quyền quản trị Phân hệ Kho, thiết lập công thức BOM, duyệt chiết khấu hóa đơn và xếp ca nhân viên.
   * **Quản trị viên (Admin):** Có quyền hạn cao nhất, kế thừa toàn bộ vai trò của Manager và có thêm quyền quản lý tài khoản người dùng và xem toàn bộ báo cáo doanh thu.
3. **Mối quan hệ phụ thuộc `<<include>>`:**
   * Mọi Use Case nghiệp vụ chính (*Tạo đơn hàng*, *Thanh toán hóa đơn*, *Quản lý kho*, *Chấm công*) đều có quan hệ `<<include>>` bắt buộc tới Use Case `Xác thực đăng nhập`. Điều này đảm bảo tính an toàn dữ liệu: người dùng không thể thực hiện bất kỳ hành động nào nếu chưa được hệ thống cấp quyền.
   * Use Case `Thanh toán hóa đơn` bắt buộc `<<include>>` Use Case `Tự động trừ kho` khi giao dịch thành công.
4. **Mối quan hệ mở rộng `<<extend>>`:**
   * Use Case `Áp dụng chiết khấu` mở rộng cho `Thanh toán hóa đơn` thông qua điểm mở rộng (*Extension point: Khách hàng có thẻ thành viên hoặc có chương trình khuyến mãi*). Chỉ tài khoản có vai trò `MANAGER` hoặc `ADMIN` mới kích hoạt được nhánh này.
   * Use Case `Cảnh báo tồn tối thiểu` mở rộng cho `Tự động trừ kho` khi số lượng tồn của nguyên liệu giảm xuống dưới mức ngưỡng cho phép.

### 3.2. Bảng phân rã Use Case theo các phân hệ nghiệp vụ

**Bảng 3.1: Phân loại và tổng hợp danh mục Use Case theo từng phân hệ**

| Mã Use Case | Tên trường hợp sử dụng (Use Case Name) | Phân hệ trực thuộc | Tác nhân chính (Primary Actor) | Tác nhân phụ (Secondary Actor) |
| :---: | :--- | :--- | :--- | :--- |
| **UC01** | Xác thực đăng nhập & Phân quyền | Xác thực & Hệ thống | Nhân viên, Quản lý, Admin | Máy chủ bảo mật |
| **UC02** | Quản lý sơ đồ bàn trực quan | Bán hàng POS | Thu ngân, Phục vụ | - |
| **UC03** | Tạo đơn hàng gọi món (Bàn / Mang đi) | Bán hàng POS | Thu ngân, Phục vụ | Bộ phận pha chế |
| **UC04** | Cập nhật & Hủy món trong đơn | Bán hàng POS | Thu ngân, Phục vụ | Bộ phận pha chế |
| **UC05** | Thiết lập định mức công thức pha chế (BOM) | Quản lý Kho | Quản lý cửa hàng, Admin | - |
| **UC06** | Thanh toán hóa đơn & Trừ kho tự động | Bán hàng POS & Kho | Thu ngân, Quản lý | CSDL MySQL (Trigger) |
| **UC07** | Check-in / Check-out chấm công ca làm | Quản lý Nhân sự | Toàn bộ nhân viên | Hệ thống thời gian thực |
| **UC08** | Nhập thêm nguyên liệu vào kho | Quản lý Kho | Quản lý cửa hàng | Nhà cung cấp |
| **UC09** | Quản lý hồ sơ nhân viên (CRUD) | Quản lý Nhân sự | Quản trị viên (Admin) | - |
| **UC10** | Xem bảng điều khiển KPI & Báo cáo | Báo cáo Thống kê | Quản lý, Admin | - |

### 3.3. Đặc tả chi tiết 7 Use Case trọng tâm (Fully-Dressed Template 9 trường)
Theo chuẩn mực học thuật bài giảng của TS. Hồ Thị Linh, nhóm trình bày đặc tả chi tiết 7 Use Case quan trọng nhất theo khuôn mẫu đầy đủ 9 trường thông tin:

---

#### 3.3.1. Đặc tả UC01: Xác thực đăng nhập & Điều hướng phân quyền
1. **Tên Use Case:** Xác thực đăng nhập & Điều hướng phân quyền (`UC01_DangNhap`).
2. **Tác nhân chính (Primary Actor):** Nhân viên (Staff), Quản lý (Manager), Quản trị viên (Admin).
3. **Mục đích (Brief Description):** Cho phép người dùng nhập thông tin tài khoản hợp lệ để đăng nhập vào hệ thống, khởi tạo phiên làm việc bảo mật và tự động điều hướng đến phân hệ tương ứng với quyền hạn.
4. **Tiền điều kiện (Pre-conditions):** Người dùng đã mở trình duyệt và truy cập trang `index.html`; tài khoản đã tồn tại trong bảng `nhan_vien` với trạng thái `DANG_LAM`.
5. **Hậu điều kiện (Post-conditions):** Hệ thống tạo `HttpSession` trên server; Client lưu thông tin người dùng vào `localStorage`; giao diện chuyển hướng đúng phân hệ.
6. **Luồng sự kiện chính (Basic Flow):**
   * *Bước 1:* Người dùng nhập Tên đăng nhập và Mật khẩu vào form đăng nhập trên `index.html`.
   * *Bước 2:* Người dùng bấm nút "Đăng Nhập".
   * *Bước 3:* Trình duyệt gửi request `POST /api/auth/login` kèm payload JSON lên máy chủ Spring Boot.
   * *Bước 4:* `XacThucController` tiếp nhận và chuyển tiếp dữ liệu đến `NhanSuService`.
   * *Bước 5:* `NhanSuService` đối chiếu thông tin với CSDL: kiểm tra tên đăng nhập và khớp mật khẩu.
   * *Bước 6:* Máy chủ tạo phiên làm việc bảo mật `HttpSession`, lưu `maNV` và `vaiTro` vào phiên.
   * *Bước 7:* Máy chủ trả về HTTP 200 OK kèm JSON thông tin người dùng (đã loại bỏ trường mật khẩu).
   * *Bước 8:* Client lưu thông tin người dùng vào `localStorage` và điều hướng:
     - Nếu `vaiTro === 'STAFF'` $\rightarrow$ chuyển hướng sang `banhang.html`.
     - Nếu `vaiTro === 'MANAGER'` $\rightarrow$ chuyển hướng sang `kho.html`.
     - Nếu `vaiTro === 'ADMIN'` $\rightarrow$ chuyển hướng sang `banhang.html` (có đầy đủ tab quản trị).
7. **Luồng sự kiện thay thế (Alternative Flow):**
   * *7a. Nhớ trạng thái giỏ hàng nháp:* Nếu nhân viên đăng nhập lại đúng tài khoản trước đó đang có đơn nháp chưa lưu, `banhang.html` tự động khôi phục lại giỏ hàng từ `sessionStorage`.
8. **Luồng ngoại lệ (Exception Flow):**
   * *E1 (Sai tài khoản hoặc mật khẩu):* Tại Bước 5, nếu thông tin không khớp, máy chủ trả về HTTP 401 Unauthorized kèm thông báo lỗi. Trình duyệt hiển thị alert đỏ "Sai tên đăng nhập hoặc mật khẩu!", giữ nguyên trang để người dùng nhập lại.
   * *E2 (Tài khoản bị khóa):* Nếu `trang_thai !== 'DANG_LAM'`, máy chủ từ chối đăng nhập và báo lỗi tài khoản đã bị vô hiệu hóa.
   * *E3 (Mất kết nối máy chủ):* Nếu không kết nối được port 8080, giao diện hiển thị cảnh báo lỗi mạng.
9. **Quy tắc nghiệp vụ & Yêu cầu đặc biệt (Business Rules):**
   * Mật khẩu phải được bảo vệ, tuyệt đối không trả trường `matKhau` về Client.
   * Giới hạn tối đa 5 lần đăng nhập sai liên tiếp để chống tấn công Brute-force.

---

#### 3.3.2. Đặc tả UC02: Quản lý sơ đồ bàn trực quan
1. **Tên Use Case:** Quản lý sơ đồ bàn trực quan (`UC02_SoDoBan`).
2. **Tác nhân chính:** Nhân viên phục vụ / Thu ngân.
3. **Mục đích:** Hiển thị trực quan trạng thái của 10 bàn trong quán (trống hoặc có khách), hỗ trợ chọn bàn phục vụ nhanh chóng.
4. **Tiền điều kiện:** Người dùng đã đăng nhập thành công và đang ở trang `banhang.html`.
5. **Hậu điều kiện:** Trạng thái các bàn được cập nhật đồng bộ từ CSDL; bàn được chọn hiển thị viền nổi bật.
6. **Luồng sự kiện chính:**
   * *Bước 1:* Khi `banhang.html` được tải, mã JavaScript tự động gọi `GET /api/ban`.
   * *Bước 2:* Máy chủ truy vấn bảng `ban` và trả về danh sách 10 bàn kèm trạng thái (`TRONG` hoặc `CO_KHACH`).
   * *Bước 3:* Giao diện render 10 thẻ bàn: bàn trống có viền xanh lá, bàn có khách có viền đỏ.
   * *Bước 4:* Nhân viên bấm vào một bàn cụ thể:
     - Nếu bấm bàn trống: Bàn được chọn hiển thị viền xanh dương nổi bật, giỏ hàng chuyển sang tiêu đề của bàn đó.
     - Nếu bấm bàn có khách: Kích hoạt gọi Use Case `UC04: Tải lại đơn đang phục vụ`.
   * *Bước 5:* Nhân viên có thể bấm nút "Tại bàn" hoặc "+ Đơn mang đi" để chuyển đổi linh hoạt chế độ phục vụ.
7. **Luồng sự kiện thay thế:**
   * *5a. Bấm nút "Tải lại":* Khi nghi ngờ bàn có sự thay đổi từ thiết bị khác, nhân viên bấm "Tải lại", hệ thống gọi lại API cập nhật trạng thái mới nhất từ server.
8. **Luồng ngoại lệ:**
   * *E1 (Chưa lưu đơn bàn cũ):* Nếu nhân viên đang có giỏ hàng chưa lưu mà bấm chuyển sang bàn khác, hệ thống hiện hộp thoại xác nhận: "Có thay đổi chưa lưu. Bỏ bản nháp và chuyển đơn?". Nếu nhân viên chọn Hủy, giao diện giữ nguyên ở bàn hiện tại.
9. **Quy tắc nghiệp vụ:**
   * Hệ thống cấu hình cố định 10 bàn chuẩn (`Bàn 01` đến `Bàn 10`).

---

#### 3.3.3. Đặc tả UC03: Tạo đơn hàng gọi món (Tại bàn & Mang đi)
1. **Tên Use Case:** Tạo đơn hàng gọi món (`UC03_TaoDonHang`).
2. **Tác nhân chính:** Nhân viên thu ngân / Phục vụ.
3. **Mục đích:** Tạo một đơn hàng mới, thêm các món nước từ thực đơn vào giỏ hàng kèm ghi chú pha chế và lưu xuống CSDL.
4. **Tiền điều kiện:** Nhân viên đã đăng nhập và đang mở màn hình `banhang.html`; đã chọn một bàn trống hoặc chọn chế độ "Đơn mang đi".
5. **Hậu điều kiện:** Bản ghi `HoaDon` mới được tạo trong MySQL với trạng thái `CHO_THANH_TOAN`; các dòng `ChiTietHoaDon` được ghi nhận; bàn chuyển sang trạng thái `CO_KHACH` (viền đỏ).
6. **Luồng sự kiện chính:**
   * *Bước 1:* Nhân viên chọn bàn trống trên sơ đồ bàn (ví dụ: Bàn 02).
   * *Bước 2:* Nhân viên duyệt thực đơn đồ uống hoặc nhập tên món vào ô tìm kiếm (ví dụ: tìm "cà phê").
   * *Bước 3:* Nhân viên nhấp chuột vào món uống để thêm vào giỏ hàng. Món xuất hiện trong giỏ với số lượng mặc định là 1.
   * *Bước 4:* Nhân viên có thể bấm nút `+` hoặc `-` để điều chỉnh số lượng món.
   * *Bước 5:* Nhân viên nhập ghi chú đặc biệt cho pha chế vào ô "Ghi chú pha chế" (ví dụ: "ít đường, nhiều đá").
   * *Bước 6:* Hệ thống tính toán tổng tạm tính hiển thị tức thì.
   * *Bước 7:* Nhân viên bấm nút "Lưu đơn".
   * *Bước 8:* Client sinh mã khóa duy nhất `yeuCauTao` (UUID) và gửi request `POST /api/order/tao-don`.
   * *Bước 9:* `DonHangService` kiểm tra hợp lệ, tạo thực thể `HoaDon` mới, lưu các dòng `ChiTietHoaDon`, chụp lại đơn giá bán tại thời điểm hiện tại và chuyển trạng thái bàn sang `CO_KHACH`.
   * *Bước 10:* Giao diện hiển thị thông báo thành công: "Đã lưu đơn #X. Bàn và món đã được ghi nhận", thẻ bàn trên giao diện chuyển sang viền đỏ có khách.
7. **Luồng sự kiện thay thế:**
   * *3a. Tạo đơn mang đi:* Tại Bước 1, nhân viên bấm nút "+ Đơn mang đi". Hệ thống cho phép tạo đơn mà không cần chọn mã bàn.
8. **Luồng ngoại lệ:**
   * *E1 (Giỏ hàng trống):* Nếu nhân viên bấm "Lưu đơn" khi chưa chọn món nào, hệ thống chặn lại và báo lỗi: "Đơn trống: thêm món hoặc hủy đơn".
   * *E2 (Chưa chọn bàn):* Ở chế độ Tại bàn, nếu chưa chọn bàn mà bấm thêm món, hệ thống cảnh báo: "Hãy chọn bàn hoặc chuyển sang Mang đi trước khi gọi món".
   * *E3 (Bàn đã bị đặt trước):* Nếu trong lúc thao tác, một thu ngân khác đã lưu đơn cho bàn này, máy chủ trả về lỗi HTTP 409 Conflict. Hệ thống thông báo bàn đã có khách và yêu cầu tải lại.
9. **Quy tắc nghiệp vụ:**
   * Mỗi dòng món có số lượng tối đa là 99 ly.
   * Đơn giá trong chi tiết hóa đơn là đơn giá chụp tại thời điểm lưu (*Price Snapshot*), không thay đổi khi danh mục món đổi giá sau này.

---

#### 3.3.4. Đặc tả UC04: Cập nhật & Hủy món trong đơn hàng
1. **Tên Use Case:** Cập nhật & Hủy món trong đơn hàng (`UC04_CapNhatHuyMon`).
2. **Tác nhân chính:** Nhân viên phục vụ / Thu ngân.
3. **Mục đích:** Cho phép thêm món mới, tăng giảm số lượng hoặc hủy món đối với một đơn hàng đang phục vụ chưa thanh toán.
4. **Tiền điều kiện:** Bàn đang có khách và đơn hàng đang ở trạng thái `CHO_THANH_TOAN`.
5. **Hậu điều kiện:** Dữ liệu đơn hàng trong CSDL được cập nhật; số phiên bản `phien_ban` được tăng thêm 1; nếu hủy toàn bộ đơn thì bàn được trả về `TRONG` (không trừ kho).
6. **Luồng sự kiện chính:**
   * *Bước 1:* Nhân viên bấm vào bàn đang có khách (viền đỏ).
   * *Bước 2:* Hệ thống gửi `GET /api/order/theo-ban/{id}` và tải toàn bộ các món đang gọi lên giỏ hàng.
   * *Bước 3:* Nhân viên thêm món mới từ thực đơn hoặc bấm `+`/`-` để thay đổi số lượng món cũ.
   * *Bước 4:* Nhân viên bấm "Lưu đơn".
   * *Bước 5:* Client gửi request `PUT /api/order/{maHoaDon}/mon` kèm số phiên bản `phienBan`.
   * *Bước 6:* Máy chủ kiểm tra phiên bản (Optimistic Locking). Nếu khớp, cập nhật lại danh sách chi tiết hóa đơn, tính lại tổng tiền gốc và tăng số phiên bản lên 1.
   * *Bước 7:* Giao diện thông báo lưu thành công và cập nhật tổng tiền mới.
7. **Luồng sự kiện thay thế (Hủy toàn bộ đơn hàng):**
   * *3a.* Khách hủy bàn không uống nữa, nhân viên bấm "Hủy đơn / Xóa giỏ".
   * *3b.* Hệ thống hiện xác nhận: "Hủy toàn bộ đơn đã lưu và trả bàn về trống?".
   * *3c.* Nhân viên bấm Đồng ý, máy chủ chuyển trạng thái hóa đơn sang `DA_HUY` và trả bàn về `TRONG`. Nguyên liệu kho không bị ảnh hưởng.
8. **Luồng ngoại lệ:**
   * *E1 (Xung đột phiên bản khi có người khác cập nhật trước):* Tại Bước 6, nếu số `phienBan` gửi lên cũ hơn phiên bản trong CSDL, máy chủ trả về HTTP 409 Conflict. Hệ thống cảnh báo: "Đơn hàng đã được cập nhật từ thiết bị khác, vui lòng tải lại!".
   * *E2 (Đơn đã thanh toán trước đó):* Không thể chỉnh sửa hoặc hủy đơn đã có trạng thái `DA_THANH_TOAN`.
9. **Quy tắc nghiệp vụ:**
   * Việc hủy món đã báo pha chế bắt buộc phải có sự xác nhận của bộ phận pha chế để tránh lãng phí nguyên liệu.

---

#### 3.3.5. Đặc tả UC05: Thiết lập định mức công thức pha chế (BOM - Recipe)
1. **Tên Use Case:** Thiết lập định mức công thức pha chế (`UC05_ThietLapCongThuc`).
2. **Tác nhân chính:** Quản lý cửa hàng (Manager), Quản trị viên (Admin).
3. **Mục đích:** Định nghĩa cấu thành định lượng nguyên vật liệu hao phí để làm ra một suất đồ uống hoàn chỉnh.
4. **Tiền điều kiện:** Người dùng đăng nhập với vai trò `MANAGER` hoặc `ADMIN`, đang ở màn hình `kho.html`.
5. **Hậu điều kiện:** Bản ghi định mức mới được lưu vào bảng `cong_thuc` trong CSDL; dùng làm căn cứ tự động trừ kho khi bán hàng.
6. **Luồng sự kiện chính:**
   * *Bước 1:* Quản lý bấm nút "Thiết Lập Định Mức Công Thức" trên trang `kho.html`.
   * *Bước 2:* Hệ thống mở Modal thiết lập công thức, tự động tải danh sách món nước và danh sách nguyên vật liệu vào hai ô chọn thả xuống (*Dropdown*).
   * *Bước 3:* Quản lý chọn món nước cần cấu hình (ví dụ: "Cà phê sữa đá").
   * *Bước 4:* Hệ thống hiển thị bảng các nguyên liệu hiện đã có trong công thức của món đó (ví dụ: Cốt cà phê: 25ml, Sữa tươi: 15ml).
   * *Bước 5:* Quản lý chọn thêm nguyên liệu thành phần còn thiếu (ví dụ: "Sữa đặc Ngôi Sao").
   * *Bước 6:* Quản lý nhập định mức tiêu hao cho 1 ly (ví dụ: `30` ml) và ghi chú (tùy chọn).
   * *Bước 7:* Quản lý bấm nút "Lưu Định Mức Vào CSDL".
   * *Bước 8:* Hệ thống gửi request `POST /api/kho/cong-thuc` lên máy chủ.
   * *Bước 9:* `KhoService` lưu bản ghi vào bảng `cong_thuc`.
   * *Bước 10:* Giao diện hiển thị Toast thông báo: "Lưu định mức công thức thành công!" và tải lại bảng danh sách nguyên liệu của món ngay trong modal.
7. **Luồng ngoại lệ:**
   * *E1 (Nhập định mức không hợp lệ):* Nếu nhập định mức $\le 0$, hệ thống chặn lại và báo: "Vui lòng nhập định mức tiêu hao lớn hơn 0!".
   * *E2 (Tài khoản nhân viên truy cập):* Nếu tài khoản `STAFF` cố tình truy cập link `kho.html`, Route Guard tự động chặn, bật alert cảnh báo và chuyển hướng quay lại `banhang.html`.
8. **Quy tắc nghiệp vụ:**
   * Một món đồ uống có thể bao gồm nhiều nguyên liệu cấu thành. Mỗi cặp (Mã món, Mã nguyên liệu) là duy nhất.

---

#### 3.3.6. Đặc tả UC06: Thanh toán hóa đơn & Trừ kho tự động (@Transactional)
1. **Tên Use Case:** Thanh toán hóa đơn & Trừ kho tự động (`UC06_ThanhToanVaTruKho`).
2. **Tác nhân chính:** Thu ngân (Staff), Quản lý (Manager).
3. **Mục đích:** Thu tiền khách hàng, áp dụng chiết khấu (nếu có), xuất hóa đơn thanh toán nhiệt, giải phóng bàn về trống và tự động trừ kho ngầm theo đúng định mức BOM.
4. **Tiền điều kiện:** Đơn hàng đang mở, có ít nhất một món nước và đang ở trạng thái `CHO_THANH_TOAN`.
5. **Hậu điều kiện:** Đơn hàng chuyển sang `DA_THANH_TOAN`; bàn chuyển về `TRONG`; tồn kho nguyên liệu trong bảng `nguyen_lieu` bị giảm tương ứng; nếu tồn $\le$ ngưỡng tối thiểu thì bật cảnh báo đỏ `SẮP HẾT HÀNG`.
6. **Luồng sự kiện chính:**
   * *Bước 1:* Thu ngân bấm nút "Thanh toán & Xuất hóa đơn" trên `banhang.html`.
   * *Bước 2:* Hệ thống tự động lưu đơn nháp (nếu có thay đổi) và mở hộp thoại "Thanh toán hóa đơn".
   * *Bước 3:* Thu ngân chọn phương thức thanh toán: "Tiền mặt" hoặc "Chuyển khoản".
   * *Bước 4:* Nếu là tài khoản `MANAGER`/`ADMIN`, ô chiết khấu được mở để nhập số tiền giảm giá; nếu là `STAFF`, ô chiết khấu tự động bị vô hiệu hóa (*Disabled*).
   * *Bước 5:* Với phương thức Tiền mặt: Thu ngân nhập "Tiền khách đưa". Hệ thống tính toán và hiển thị ngay số "Tiền thừa" trả khách.
   * *Bước 6:* Thu ngân bấm "Xác nhận thanh toán".
   * *Bước 7:* Client gửi `POST /api/order/{maHoaDon}/thanh-toan` kèm mã `yeuCauThanhToan` (UUID), `phienBan`, chiết khấu và tiền khách đưa.
   * *Bước 8:* `DonHangService` mở giao dịch `@Transactional(rollbackFor = Exception.class)`.
   * *Bước 9:* Hệ thống khóa bi quan dòng hóa đơn và kiểm tra:
     - Kiểm tra tiền khách đưa $\ge$ số tiền phải thu.
     - Kiểm tra phân quyền chiết khấu: chặn nếu `STAFF` gửi chiết khấu $> 0$.
   * *Bước 10:* Hệ thống gọi hàm `khoService.truKhoTuDong(hoaDon)`:
     - Duyệt từng món trong hóa đơn $\rightarrow$ tra cứu bảng `cong_thuc`.
     - Với từng nguyên liệu: Khóa dòng nguyên liệu bằng `SELECT ... FOR UPDATE` theo thứ tự khóa chính tăng dần.
     - Tính tổng lượng tiêu hao = $\sum (\text{Định mức} \times \text{Số lượng món bán})$.
     - Kiểm tra: Nếu `soLuongTon < tongTieuHao` $\rightarrow$ Ném ngoại lệ, Rollback toàn bộ giao dịch.
     - Ngược lại: Cập nhật `soLuongTon = soLuongTon - tongTieuHao`.
   * *Bước 11:* Cập nhật hóa đơn sang `DA_THANH_TOAN`, ghi nhận thời gian thanh toán và cập nhật bàn về `TRONG`.
   * *Bước 12:* Giao dịch Commit thành công. Máy chủ trả về thông tin hóa đơn hoàn tất.
   * *Bước 13:* Giao diện đóng modal thanh toán, giải phóng giỏ hàng, hiển thị Popup Hóa đơn nhiệt (Thermal Receipt) với nút "In hóa đơn". Thẻ bàn chuyển lại màu xanh lá `TRONG`.
7. **Luồng ngoại lệ:**
   * *E1 (Tiền khách đưa không đủ):* Tại Bước 5, nếu tiền khách đưa nhỏ hơn tổng tiền phải thu, hệ thống hiện lỗi đỏ: "Tiền khách đưa chưa đủ hoặc không hợp lệ" và chặn không cho xác nhận.
   * *E2 (Kho không đủ nguyên liệu - Rollback toàn bộ):* Tại Bước 10, nếu tồn kho một nguyên liệu nào đó không đủ, hệ thống ném ngoại lệ Runtime. Giao dịch `@Transactional` tự động Rollback $100\%$: tồn kho nguyên liệu giữ nguyên, hóa đơn vẫn ở trạng thái `CHO_THANH_TOAN`, bàn vẫn ở trạng thái `CO_KHACH`. Hệ thống hiển thị thông báo lỗi chi tiết cho thu ngân.
   * *E3 (Món chưa có định mức công thức):* Nếu có món chưa được thiết lập công thức trong bảng `cong_thuc`, hệ thống chặn thanh toán và yêu cầu quản lý thiết lập công thức trước.
   * *E4 (Staff tự ý gửi chiết khấu qua API):* Nếu cố tình can thiệp mã gửi chiết khấu, máy chủ phát hiện quyền `STAFF` và chặn ngay lập tức với mã HTTP 403 Forbidden.
8. **Quy tắc nghiệp vụ:**
   * Toàn bộ thao tác thanh toán, cập nhật trạng thái đơn, đổi màu bàn và trừ kho phải nằm trong một giao dịch cơ sở dữ liệu duy nhất để đảm bảo tính toàn vẹn tuyệt đối.

---

#### 3.3.7. Đặc tả UC07: Check-in / Check-out chấm công ca làm việc
1. **Tên Use Case:** Check-in / Check-out chấm công ca làm việc (`UC07_ChamCong`).
2. **Tác nhân chính:** Toàn bộ nhân viên (Staff, Manager, Admin).
3. **Mục đích:** Ghi nhận thời gian đến làm việc và kết thúc ca làm của nhân viên, tự động đối chiếu thời gian máy chủ để xác định nhân viên đi đúng giờ hay đi trễ.
4. **Tiền điều kiện:** Nhân viên đã đăng nhập và mở màn hình `nhansu.html`.
5. **Hậu điều kiện:** Bản ghi mới được tạo trong bảng `cham_cong` với thời gian Check-in thực tế và trạng thái `DUNG_GIO` hoặc `DI_TRE`.
6. **Luồng sự kiện chính:**
   * *Bước 1:* Nhân viên truy cập trang `nhansu.html`, đồng hồ kỹ thuật số hiển thị giờ thời gian thực tế của hệ thống.
   * *Bước 2:* Tại khối "CHẤM CÔNG CA LÀM VIỆC NHANH", nhân viên chọn tên của mình trong danh sách thả xuống.
   * *Bước 3:* Nhân viên chọn ca làm việc hôm nay (Ca Sáng: 07:00 - 12:00, Ca Chiều: 12:00 - 17:00, Ca Tối: 17:00 - 22:00).
   * *Bước 4:* Nhân viên bấm nút "Check-in Vào Ca".
   * *Bước 5:* Hệ thống gửi request `POST /api/cham-cong/check-in` kèm `maNV` và `maCa`.
   * *Bước 6:* `ChamCongService` lấy giờ hiện tại của máy chủ, đối chiếu với `gioBatDau` của ca làm:
     - Nếu $\text{Giờ Check-in} \le \text{Giờ bắt đầu ca} + 15\text{ phút} \rightarrow$ Gán trạng thái `DUNG_GIO`.
     - Nếu $\text{Giờ Check-in} > \text{Giờ bắt đầu ca} + 15\text{ phút} \rightarrow$ Gán trạng thái `DI_TRE`.
   * *Bước 7:* Hệ thống lưu bản ghi vào bảng `cham_cong` và trả về kết quả.
   * *Bước 8:* Giao diện hiển thị Toast thông báo: "Check-in thành công! Trạng thái: ĐÚNG GIỜ" (hoặc "ĐI TRỄ"), đồng thời cập nhật tức thì dòng mới vào bảng "LỊCH SỬ CHẤM CÔNG" bên phải.
   * *Bước 9:* Khi hết giờ làm việc, nhân viên tìm dòng chấm công của mình trong bảng lịch sử và bấm nút "Check-out".
   * *Bước 10:* Hệ thống gửi `POST /api/cham-cong/check-out/{id}`, ghi nhận giờ Check-out thực tế và hiển thị nhãn giờ ra ca hoàn tất.
7. **Luồng ngoại lệ:**
   * *E1 (Check-in trùng lặp):* Nếu nhân viên đã Check-in ca làm này trong ngày hôm nay mà chưa Check-out, hệ thống chặn lại và thông báo đã điểm danh ca này rồi.
8. **Quy tắc nghiệp vụ:**
   * Ngưỡng cho phép đi trễ tối đa là 15 phút. Quá 15 phút bị tính là `DI_TRE` làm căn cứ xét thưởng/phạt cuối tháng.

---

### 3.4. Biểu đồ Hoạt động phân làn (Activity Diagrams with Swimlanes)

#### 3.4.1. Phân hệ Kho: Biểu đồ Hoạt động Luồng trừ kho tự động khi thanh toán
Biểu đồ mô tả phân làn luồng xử lý giữa 3 thực thể: **Thu ngân**, **Hệ thống Spring Boot**, và **Cơ sở dữ liệu MySQL**:

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 3.2:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Activity_TruKho.svg`  
> ![Hình 3.2: Biểu đồ Hoạt động Luồng trừ kho tự động khi thanh toán](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_TruKho.svg)

##### Phân tích chi tiết Biểu đồ Hoạt động Trừ kho (Hình 3.2):
* **Làn Thu ngân:** Bắt đầu bằng hành động bấm nút "Thanh toán & Xuất hóa đơn", nhập tiền khách đưa và xác nhận thanh toán.
* **Làn Hệ thống Spring Boot:**
  1. Mở khối giao dịch toàn vẹn `@Transactional`.
  2. Bắt đầu vòng lặp duyệt từng dòng món nước trong chi tiết hóa đơn.
  3. Với mỗi món, tra cứu bảng `cong_thuc` để tìm danh sách nguyên liệu và định mức tiêu hao tương ứng.
  4. Thực hiện vòng lặp con duyệt từng nguyên liệu: tính tổng tiêu hao = định mức $\times$ số lượng món.
  5. Đối chiếu điều kiện rẽ nhánh: Kiểm tra `Số lượng tồn >= Tổng tiêu hao`?
     - Nếu **Không đủ:** Lập tức phát tín hiệu Rollback toàn bộ giao dịch, ném thông báo lỗi thiếu nguyên liệu và dừng toàn bộ quá trình thanh toán.
     - Nếu **Đủ hàng:** Gửi lệnh cập nhật trừ lùi tồn kho xuống MySQL.
  6. Sau khi trừ lùi, kiểm tra điều kiện phụ: `Số lượng tồn sau trừ <= Ngưỡng tồn tối thiểu`?
     - Nếu đúng: Bật cờ cảnh báo đỏ `SẮP HẾT HÀNG`.
  7. Cập nhật hóa đơn sang `DA_THANH_TOAN`, giải phóng bàn về `TRONG`.
  8. Commit giao dịch thành công và sinh hóa đơn nhiệt cho thu ngân.
* **Làn CSDL MySQL:** Thực thi các câu lệnh `SELECT ... FOR UPDATE` để khóa hàng nguyên liệu, ghi bản ghi trừ kho và cam kết lưu trữ bền vững (ACID).

---

#### 3.4.2. Phân hệ Bán hàng POS: Biểu đồ Hoạt động Quy trình bán hàng và thanh toán

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 3.3:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Activity_POS.svg`  
> ![Hình 3.3: Biểu đồ Hoạt động Quy trình Bán hàng POS và Xuất hóa đơn](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_POS.svg)

##### Phân tích chi tiết Biểu đồ Hoạt động POS (Hình 3.3):
* Sơ đồ chia làm 3 làn: **Nhân viên phục vụ/Thu ngân**, **Giao diện Client Web (banhang.html)**, và **Hệ thống Server Spring Boot**.
* Quy trình thể hiện rõ nút rẽ nhánh điều kiện: Bàn đang trống hay bàn đang có khách. Nếu bàn có khách, giao diện tải lại đơn cũ; nếu bàn trống, cho phép chọn món tạo đơn mới.
* Thể hiện luồng kiểm tra tiền khách đưa: Nếu chưa đủ tiền, modal thanh toán chặn lại và yêu cầu nhập lại; nếu đủ tiền, tính tiền thừa và hoàn tất giao dịch.

---

#### 3.4.3. Phân hệ Nhân sự: Biểu đồ Hoạt động Chấm công ca làm việc

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 3.4:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Activity_ChamCong.svg`  
> ![Hình 3.4: Biểu đồ Hoạt động Quy trình Chấm công ca làm việc nhân sự](file:///d:/3.1.%20PTTKYC/CK/diagram/Activity_ChamCong.svg)

##### Phân tích chi tiết Biểu đồ Hoạt động Chấm công (Hình 3.4):
* Điểm nổi bật nhất của biểu đồ là nút quyết định rẽ nhánh logic thời gian (*Decision Node*):
  $$\Delta t = \text{Thời gian Check-in thực tế} - \text{Giờ bắt đầu ca}$$
* Nếu $\Delta t \le 15\text{ phút} \rightarrow$ Nhánh ghi nhận trạng thái **Đúng giờ** (Nhãn xanh).
* Nếu $\Delta t > 15\text{ phút} \rightarrow$ Nhánh ghi nhận trạng thái **Đi trễ** (Nhãn đỏ).
* Bản ghi được lưu tức thì vào CSDL và cập nhật đồng bộ lên bảng lịch sử trực quan.

---

## CHƯƠNG 4: THIẾT KẾ KIẾN TRÚC PHẦN MỀM & BIỂU ĐỒ LỚP CHI TIẾT

### 4.1. Kiến trúc phân tầng 3 tầng (3-Tier Layered Architecture)
Hệ thống tuân thủ chặt chẽ mô hình **Kiến trúc phân tầng 3 tầng chuẩn mực của Java Spring Boot**, đảm bảo nguyên tắc phân tách trách nhiệm cao (*High Cohesion, Low Coupling*):

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 4.1:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\KienTrucTongThe.svg`  
> ![Hình 4.1: Biểu đồ Kiến trúc phân tầng và thành phần hệ thống](file:///d:/3.1.%20PTTKYC/CK/diagram/KienTrucTongThe.svg)

#### Phân tích chi tiết các tầng kiến trúc (Hình 4.1):
1. **Tầng Giao diện Người dùng (Presentation Tier - Web Client):**
   * Hoạt động trực tiếp trên trình duyệt Client (Chrome, Edge, Firefox).
   * Sử dụng công nghệ: HTML5 ngữ nghĩa, Bootstrap 5.3 (hỗ trợ Responsive đa kích thước màn hình), CSS tùy biến đồng bộ Google Fonts `Roboto`, và JavaScript ES6 thuần (*Vanilla JS*) sử dụng Fetch API bất đồng bộ (*Async/Await*).
   * Gồm 4 màn hình chuyên biệt: `index.html` (Đăng nhập), `banhang.html` (POS), `kho.html` (Kho & Định mức), `nhansu.html` (Nhân sự & Chấm công).
   * Giao tiếp với Application Tier thông qua giao thức truyền tải siêu văn bản an toàn HTTP/RESTful với định dạng dữ liệu nhẹ chuẩn **JSON**.
2. **Tầng Xử lý Nghiệp vụ (Application Tier - Spring Boot Server Port 8080):**
   * Đóng vai trò là "bộ não" trung tâm xử lý toàn bộ logic nghiệp vụ của hệ thống:
     * **Controller Layer:** Tiếp nhận các HTTP Request, xác thực tính hợp lệ của dữ liệu đầu vào (*Validation*), xử lý phân quyền người dùng và định tuyến lời gọi đến tầng Service thích hợp.
     * **Service Layer:** Chứa toàn bộ các quy tắc nghiệp vụ cốt lõi (Business Logic): tính toán tiền hóa đơn, áp dụng chiết khấu theo vai trò, quản lý giao dịch nguyên tử trừ kho `@Transactional`, kiểm tra ngưỡng tồn kho tối thiểu, và tính toán giờ công đi trễ.
     * **Repository Layer:** Sử dụng công nghệ **Spring Data JPA** kết hợp **Hibernate ORM**. Cung cấp các phương thức truy vấn tối ưu, hỗ trợ các câu lệnh khóa bi quan (`LockModeType.PESSIMISTIC_WRITE`) và khóa lạc quan (`@Version`).
3. **Tầng Dữ liệu (Database Tier - MySQL Server Port 3306):**
   * Sử dụng Hệ quản trị CSDL quan hệ **MySQL 8.0 / MariaDB** (chạy trên nền tảng máy chủ XAMPP).
   * Lưu trữ bền vững toàn bộ 9 bảng dữ liệu quan hệ, đảm bảo tính toàn vẹn khóa ngoại, chỉ mục tìm kiếm (*Indexes*) và bảng mã chuẩn hóa `utf8mb4` hỗ trợ tiếng Việt có dấu trọn vẹn.

### 4.2. Biểu đồ Triển khai hệ thống vật lý (Deployment Diagram)
Biểu đồ triển khai mô tả cấu hình phần cứng, các node mạng, tiến trình runtime và giao thức kết nối thực tế trên môi trường máy chủ:

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 4.2:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\TrienKhai.svg`  
> ![Hình 4.2: Biểu đồ Triển khai hệ thống trên môi trường máy chủ Localhost](file:///d:/3.1.%20PTTKYC/CK/diagram/TrienKhai.svg)

#### Phân tích chi tiết Biểu đồ Triển khai (Hình 4.2):
* **Thiết bị trạm Client (PC / Laptop / Tablet của Thu ngân & Quản lý):** Cài đặt trình duyệt Web hiện đại (Google Chrome v120+). Tải các tài nguyên tĩnh HTML/CSS/JS và thực thi mã JavaScript phía client.
* **Máy chủ ứng dụng (Application Server - Localhost):**
  * Môi trường thực thi Java: OpenJDK 17 / 21 / 25 LTS (`F:\JDK`).
  * Web Server nhúng: **Apache Tomcat 10.1** lắng nghe trên cổng TCP mặc định **`8080`**.
  * Chạy file thực thi đóng gói `quanly-cafe-0.0.1-SNAPSHOT.jar`.
* **Máy chủ Cơ sở Dữ liệu (Database Server - XAMPP):**
  * Tiến trình `mysqld.exe` lắng nghe trên cổng TCP **`3306`**.
  * Kết nối từ máy chủ Spring Boot đến MySQL thông qua thư viện điều khiển chuẩn **MySQL Connector/J** với chuỗi kết nối:
    `jdbc:mysql://localhost:3306/quanly_cafe?characterEncoding=UTF-8&connectionCollation=utf8mb4_unicode_ci`

### 4.3. Cấu trúc đóng gói mã nguồn Java Spring Boot (Package Diagram)
Biểu đồ đóng gói thể hiện cấu trúc phân chia thư mục mã nguồn Java trong dự án theo chuẩn thiết kế phần mềm sạch (*Clean Architecture*):

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 4.3:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Package_SpringBoot.svg`  
> ![Hình 4.3: Biểu đồ Cấu trúc đóng gói mã nguồn Java Spring Boot](file:///d:/3.1.%20PTTKYC/CK/diagram/Package_SpringBoot.svg)

#### Phân tích chi tiết các Package:
* `com.cafe`: Chứa lớp khởi chạy ứng dụng `QuanLyCafeApplication.java`.
* `com.cafe.entity`: Chứa 9 lớp thực thể ánh xạ JPA tương ứng với 9 bảng CSDL (`NhanVien`, `CaLam`, `ChamCong`, `Ban`, `MonNuoc`, `NguyenLieu`, `CongThuc`, `HoaDon`, `ChiTietHoaDon`).
* `com.cafe.repository`: Chứa 9 interface kế thừa `JpaRepository<T, ID>` để thực hiện các thao tác CRUD và các truy vấn tùy biến.
* `com.cafe.service`: Chứa các lớp cài đặt logic nghiệp vụ (`DonHangService`, `KhoService`, `NhanSuService`).
* `com.cafe.controller`: Chứa các REST Controller tiếp nhận và trả lời HTTP Request cho từng phân hệ.
* `com.cafe.dto`: Chứa các đối tượng đóng gói dữ liệu truyền tải (`ThanhToanDTO`, `OrderRequestDTO`, `ChiTietRequestDTO`).
* `src/main/resources/static`: Chứa toàn bộ các tài nguyên giao diện Web tĩnh (HTML, CSS, JS, Favicon).

### 4.4. Biểu đồ Lớp thiết kế chi tiết (Design Class Diagram - 3 Tầng)
Biểu đồ Lớp thiết kế chi tiết mô hình hóa cấu trúc lớp hoàn chỉnh của hệ thống, bao gồm các thuộc tính kèm kiểu dữ liệu, các phương thức, phạm vi truy cập (`+` public, `-` private) và quan hệ cấu trúc giữa các lớp:

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 4.4:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\ClassDiagram.svg`  
> ![Hình 4.4: Biểu đồ Lớp thiết kế chi tiết 3 tầng](file:///d:/3.1.%20PTTKYC/CK/diagram/ClassDiagram.svg)

#### Phân tích chi tiết các mối quan hệ cấu trúc trong Biểu đồ Lớp (Hình 4.4):
1. **Quan hệ Hợp thành chặt chẽ (Composition - Hình thoi đặc):**
   * Giữa lớp `HoaDon` và `ChiTietHoaDon`: Một hóa đơn bao gồm nhiều dòng chi tiết món. Quan hệ ở đây là Composition vì một dòng `ChiTietHoaDon` không thể tồn tại độc lập nếu thiếu `HoaDon`. Khi một hóa đơn bị hủy hoặc xóa khỏi hệ thống, toàn bộ các dòng chi tiết thuộc hóa đơn đó sẽ bị xóa theo (*CascadeType.ALL, orphanRemoval = true*).
2. **Lớp liên kết (Association Class):**
   * Lớp `CongThuc` đóng vai trò là một Association Class giải quyết mối quan hệ Nhiều – Nhiều ($M:N$) giữa thực thể `MonNuoc` (Đồ uống) và `NguyenLieu` (Nguyên vật liệu). Lớp này mang thuộc tính nghiệp vụ đặc thù là `dinhMuc : double` (định lượng hao phí cho 1 ly) và `ghiChu : String`.
3. **Quan hệ Liên kết (Association - Một - Nhiều):**
   * Một `Ban` có thể có nhiều `HoaDon` theo dòng thời gian, nhưng tại một thời điểm chỉ có tối đa một hóa đơn đang mở (`CHO_THANH_TOAN`).
   * Một `NhanVien` tạo nhiều `HoaDon` và có nhiều bản ghi `ChamCong`.
   * Một `CaLam` chứa nhiều bản ghi `ChamCong` của các nhân viên trong ca đó.
4. **Thuộc tính dẫn xuất (*Derived Attribute*):**
   * Thuộc tính `/thanhTien` trong lớp `ChiTietHoaDon` được tính toán tự động bằng công thức:
     $$\text{thanhTien} = \text{soLuong} \times \text{donGiaBan}$$
   * Thuộc tính `/tongTienThanhToan` trong `HoaDon`:
     $$\text{tongTienThanhToan} = \text{tongTienGoc} - \text{chietKhau}$$
5. **Thuộc tính quản lý phiên bản (Optimistic Locking):**
   * Lớp `HoaDon` chứa trường `phienBan : Long` được gắn annotation `@Version`. Mỗi lần đơn hàng được cập nhật, Hibernate tự động tăng giá trị này lên 1 để phát hiện xung đột đồng thời.

---

## CHƯƠNG 5: THIẾT KẾ CƠ SỞ DỮ LIỆU & BẢNG TỪ ĐIỂN DỮ LIỆU

### 5.1. Mô hình Thực thể Quan hệ CSDL (ERD - Crow's Foot Notation)
Mô hình ERD được thiết kế theo chuẩn ký pháp chân quạ (*Crow's Foot Notation*), mô tả 9 bảng dữ liệu, khóa chính (PK), khóa ngoại (FK) và bản số quan hệ:

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 5.1:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\ERD_DuLieu.svg`  
> ![Hình 5.1: Mô hình Thực thể Quan hệ CSDL 9 thực thể](file:///d:/3.1.%20PTTKYC/CK/diagram/ERD_DuLieu.svg)

#### Phân tích chi tiết Mô hình ERD (Hình 5.1):
* **Bản số quan hệ chuẩn mực:**
  * `nhan_vien` (1) --- (0..N) `hoa_don`: Một nhân viên có thể lập nhiều hóa đơn; một hóa đơn bắt buộc phải do đúng một nhân viên lập.
  * `ban` (1) --- (0..N) `hoa_don`: Một bàn có thể có nhiều hóa đơn trong lịch sử; với đơn tại bàn, một hóa đơn gắn với đúng một bàn. Với đơn mang đi (*Takeaway*), khóa ngoại `ma_ban` cho phép nhận giá trị `NULL`.
  * `hoa_don` (1) --- (1..N) `chi_tiet_hoa_don`: Một hóa đơn hợp lệ bắt buộc phải có ít nhất một dòng chi tiết món.
  * `mon_nuoc` (1) --- (0..N) `chi_tiet_hoa_don`: Một món nước có thể xuất hiện trong nhiều chi tiết hóa đơn.
  * `mon_nuoc` (1) --- (0..N) `cong_thuc` và `nguyen_lieu` (1) --- (0..N) `cong_thuc`: Bảng `cong_thuc` đóng vai trò là bảng kết hợp giải quyết quan hệ nhiều - nhiều giữa món nước và nguyên liệu.
  * `ca_lam` (1) --- (0..N) `cham_cong` và `nhan_vien` (1) --- (0..N) `cham_cong`: Quản lý lịch sử chấm công của từng nhân viên theo từng ca làm.

### 5.2. Bảng Từ điển Dữ liệu chi tiết cho 9 bảng CSDL (Data Dictionary)

#### 5.2.1. Bảng 5.1: Bảng `nhan_vien` (Hồ sơ nhân viên)
*Khóa chính:* `ma_nv` | *Mục đích:* Lưu trữ thông tin nhân sự và tài khoản phân quyền.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_nv` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã định danh nhân viên duy nhất |
| `ho_ten` | `VARCHAR(100)` | | No | Không rỗng | Họ và tên đầy đủ của nhân viên |
| `so_dien_thoai`| `VARCHAR(15)` | | Yes | Chuỗi số điện thoại liên lạc | Số điện thoại di động |
| `ten_dang_nhap`| `VARCHAR(50)` | | No | `UNIQUE`, không trùng lặp | Tên đăng nhập vào hệ thống |
| `mat_khau` | `VARCHAR(255)` | | No | Mật khẩu xác thực tài khoản | Mật khẩu đăng nhập |
| `vai_tro` | `VARCHAR(20)` | | No | `'ADMIN'`, `'MANAGER'`, `'STAFF'` | Vai trò phân quyền trên hệ thống |
| `trang_thai` | `VARCHAR(20)` | | No | `'DANG_LAM'`, `'DA_NGHI'` | Trạng thái công tác của nhân viên |

---

#### 5.2.2. Bảng 5.2: Bảng `ca_lam` (Ca làm việc)
*Khóa chính:* `ma_ca` | *Mục đích:* Cấu hình các ca làm việc trong ngày của quán cà phê.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_ca` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã định danh ca làm việc |
| `ten_ca` | `VARCHAR(50)` | | No | Ví dụ: "Ca Sáng", "Ca Chiều" | Tên gọi ca làm việc |
| `gio_bat_dau` | `TIME` | | No | Định dạng `HH:mm:ss` | Giờ bắt đầu ca làm việc |
| `gio_ket_thuc`| `TIME` | | No | `gio_ket_thuc > gio_bat_dau` | Giờ kết thúc ca làm việc |
| `he_so_luong` | `DOUBLE` | | No | Default `1.0` | Hệ số lương ca làm (ca đêm hệ số cao) |

---

#### 5.2.3. Bảng 5.3: Bảng `cham_cong` (Bản ghi chấm công)
*Khóa chính:* `ma_cham_cong` | *Khóa ngoại:* `ma_nv` $\rightarrow$ `nhan_vien`, `ma_ca` $\rightarrow$ `ca_lam`.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_cham_cong` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã bản ghi chấm công duy nhất |
| `ma_nv` | `INT` | **FK** | No | Tham chiếu `nhan_vien(ma_nv)` | Nhân viên thực hiện chấm công |
| `ma_ca` | `INT` | **FK** | No | Tham chiếu `ca_lam(ma_ca)` | Ca làm việc đăng ký chấm công |
| `ngay_lam` | `DATE` | | No | Ngày làm việc thực tế | Ngày thực hiện ca làm |
| `gio_check_in` | `DATETIME` | | No | Giờ bấm Check-in thực tế | Thời điểm vào ca làm |
| `gio_check_out`| `DATETIME` | | Yes | Null khi đang trong ca | Thời điểm kết thúc ca làm |
| `trang_thai` | `VARCHAR(20)` | | No | `'DUNG_GIO'`, `'DI_TRE'` | Trạng thái chấp hành giờ giấc |
| `ghi_chu` | `VARCHAR(255)` | | Yes | Tùy chọn lý do đi trễ | Ghi chú thêm nếu có |

---

#### 5.2.4. Bảng 5.4: Bảng `ban` (Sơ đồ bàn quán cà phê)
*Khóa chính:* `ma_ban` | *Mục đích:* Quản lý danh mục bàn và trạng thái phục vụ thời gian thực.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_ban` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã định danh bàn (Bàn 01 - Bàn 10) |
| `ten_ban` | `VARCHAR(50)` | | No | Tên bàn hiển thị trên POS | Tên gọi hiển thị |
| `trang_thai` | `VARCHAR(20)` | | No | `'TRONG'`, `'CO_KHACH'` | Trạng thái phục vụ hiện tại của bàn |

---

#### 5.2.5. Bảng 5.5: Bảng `mon_nuoc` (Thực đơn đồ uống)
*Khóa chính:* `ma_mon` | *Mục đích:* Lưu trữ danh mục đồ uống và giá bán niêm yết.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_mon` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã món đồ uống duy nhất |
| `ten_mon` | `VARCHAR(100)`| | No | Ví dụ: "Cà phê sữa đá" | Tên món nước hiển thị trên thực đơn |
| `danh_muc` | `VARCHAR(50)` | | No | "Cà phê", "Trà", "Đá xay", "Nước ép"| Phân loại nhóm đồ uống |
| `don_gia` | `DOUBLE` | | No | `don_gia >= 0` | Đơn giá niêm yết bán lẻ (VNĐ) |
| `trang_thai` | `VARCHAR(20)` | | No | `'DANG_BAN'`, `'NGUNG_BAN'` | Trạng thái kinh doanh món |

---

#### 5.2.6. Bảng 5.6: Bảng `nguyen_lieu` (Kho nguyên vật liệu)
*Khóa chính:* `ma_nl` | *Mục đích:* Quản lý tồn kho nguyên liệu phục vụ pha chế đồ uống.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_nl` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã nguyên liệu duy nhất |
| `ten_nl` | `VARCHAR(100)`| | No | Ví dụ: "Sữa đặc Ngôi Sao" | Tên gọi nguyên vật liệu |
| `don_vi_tinh` | `VARCHAR(20)` | | No | `'ml'`, `'g'`, `'lon'`, `'hop'` | Đơn vị đo lường hao phí |
| `so_luong_ton` | `DOUBLE` | | No | `so_luong_ton >= 0` | Số lượng tồn kho thực tế hiện tại |
| `muc_ton_toi_thieu`|`DOUBLE` | | No | Mức kích hoạt cảnh báo đỏ | Ngưỡng an toàn tối thiểu |
| `don_gia_nhap` | `DOUBLE` | | Yes | Giá mua đầu vào trung bình | Đơn giá vốn nguyên liệu |

---

#### 5.2.7. Bảng 5.7: Bảng `cong_thuc` (Định mức công thức BOM)
*Khóa chính:* `ma_cong_thuc` | *Khóa ngoại:* `ma_mon` $\rightarrow$ `mon_nuoc`, `ma_nl` $\rightarrow$ `nguyen_lieu`.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_cong_thuc`| `INT` | **PK** | No | `AUTO_INCREMENT` | Mã định mức công thức duy nhất |
| `ma_mon` | `INT` | **FK** | No | Tham chiếu `mon_nuoc(ma_mon)` | Món nước được cấu hình |
| `ma_nl` | `INT` | **FK** | No | Tham chiếu `nguyen_lieu(ma_nl)` | Nguyên liệu thành phần cấu thành |
| `dinh_muc` | `DOUBLE` | | No | `dinh_muc > 0` | Lượng tiêu hao cho đúng 1 suất ly |
| `ghi_chu` | `VARCHAR(255)`| | Yes | Tùy chọn hướng dẫn pha chế | Ghi chú kỹ thuật pha chế |

---

#### 5.2.8. Bảng 5.8: Bảng `hoa_don` (Hóa đơn giao dịch bán hàng)
*Khóa chính:* `ma_hoa_don` | *Khóa ngoại:* `ma_ban` $\rightarrow$ `ban`, `ma_nv` $\rightarrow$ `nhan_vien`.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_hoa_don` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã số hóa đơn bán lẻ duy nhất |
| `ma_ban` | `INT` | **FK** | Yes | Null đối với đơn mang đi (*Takeaway*)| Bàn phục vụ đơn hàng |
| `ma_nv` | `INT` | **FK** | No | Tham chiếu `nhan_vien(ma_nv)` | Thu ngân / Phục vụ tạo đơn |
| `thoi_gian_tao`| `DATETIME` | | No | Default `CURRENT_TIMESTAMP` | Thời điểm khách gọi món |
| `thoi_gian_thanh_toan`|`DATETIME`| | Yes | Null khi đơn đang chờ thanh toán | Thời điểm thanh toán và xuất bill |
| `tong_tien_goc`| `DOUBLE` | | No | Tổng giá trị các món trước giảm giá | Tổng tiền gốc của đơn |
| `chiet_khau` | `DOUBLE` | | No | Default `0.0`, `chiet_khau >= 0` | Số tiền giảm giá được phê duyệt |
| `tong_tien_thanh_toan`|`DOUBLE`| | No | `= tong_tien_goc - chiet_khau` | Số tiền thực tế phải thu của khách |
| `phuong_thuc_tt`|`VARCHAR(20)`| | Yes | `'TIEN_MAT'`, `'CHUYEN_KHOAN'` | Hình thức thanh toán |
| `tien_khach_dua`|`DOUBLE` | | Yes | Dùng tính tiền thối | Số tiền mặt khách hàng đưa |
| `tien_thua` | `DOUBLE` | | Yes | `= tien_khach_dua - phai_thu` | Tiền thừa trả lại cho khách |
| `trang_thai` | `VARCHAR(20)` | | No | `'CHO_THANH_TOAN'`, `'DA_THANH_TOAN'`, `'DA_HUY'` | Trạng thái vòng đời của đơn hàng |
| `loai_don` | `VARCHAR(20)` | | No | `'TAI_BAN'`, `'MANG_DI'` | Hình thức phục vụ |
| `yeuCauTao` | `VARCHAR(100)`| | Yes | Khóa Idempotency Key tạo đơn | Chống tạo trùng đơn khi mạng lag |
| `yeuCauThanhToan`|`VARCHAR(100)`| | Yes | Khóa Idempotency Key thanh toán | Chống trừ kho lặp lại khi retry |
| `phien_ban` | `BIGINT` | | No | Default `0`, `@Version` Hibernate | Phiên bản phục vụ Khóa lạc quan |

---

#### 5.2.9. Bảng 5.9: Bảng `chi_tiet_hoa_don` (Chi tiết dòng món trong hóa đơn)
*Khóa chính:* `ma_chi_tiet` | *Khóa ngoại:* `ma_hoa_don` $\rightarrow$ `hoa_don`, `ma_mon` $\rightarrow$ `mon_nuoc`.

| Tên cột | Kiểu dữ liệu | Khóa | Nullable | Ràng buộc mặc định / Giá trị hợp lệ | Ý nghĩa nghiệp vụ |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `ma_chi_tiet` | `INT` | **PK** | No | `AUTO_INCREMENT` | Mã dòng chi tiết món duy nhất |
| `ma_hoa_don` | `INT` | **FK** | No | Tham chiếu `hoa_don(ma_hoa_don)` | Hóa đơn chứa dòng món này |
| `ma_mon` | `INT` | **FK** | No | Tham chiếu `mon_nuoc(ma_mon)` | Món nước được gọi |
| `so_luong` | `INT` | | No | `so_luong > 0`, tối đa 99 | Số lượng ly khách gọi |
| `don_gia_ban` | `DOUBLE` | | No | Đơn giá chụp tại thời điểm gọi món | Snapshot giá bán không bị đổi |
| `thanh_tien` | `DOUBLE` | | No | `= so_luong * don_gia_ban` | Thành tiền của dòng món |
| `ghi_chu` | `VARCHAR(255)`| | Yes | Ví dụ: "ít đường, không đá" | Yêu cầu pha chế đặc biệt |

---

### 5.3. Ràng buộc toàn vẹn dữ liệu và chuẩn hóa dạng chuẩn 3NF
* **Đạt chuẩn 1NF:** Tất cả các thuộc tính trong 9 bảng đều mang tính nguyên tử (*Atomic*), không chứa mảng hay tập lặp.
* **Đạt chuẩn 2NF:** Tất cả các bảng đều có khóa chính đơn (`ma_nv`, `ma_mon`, `ma_hoa_don`...), không có bất kỳ thuộc tính không khóa nào phụ thuộc một phần vào khóa chính.
* **Đạt chuẩn 3NF:** Không tồn tại phụ thuộc bắc cầu (*Transitive Dependency*). Các thông tin món nước, nguyên vật liệu được tách riêng vào bảng danh mục và liên kết thông qua khóa ngoại chuẩn hóa.
* **Ràng buộc khóa ngoại (*Referential Integrity*):**
  * `ON DELETE CASCADE` được áp dụng cho mối quan hệ Hợp thành giữa `hoa_don` và `chi_tiet_hoa_don`.
  * `ON DELETE RESTRICT` được áp dụng khi xóa nguyên liệu hoặc món nước đang có trong công thức nhằm ngăn chặn việc dữ liệu bị mồ côi.

---

## CHƯƠNG 6: THIẾT KẾ ĐỘNG HỌC HỆ THỐNG & CƠ CHẾ GIAO DỊCH NÂNG CAO

### 6.1. Biểu đồ Tuần tự tương tác đa tầng (Multi-tier Sequence Diagrams)

#### 6.1.1. Luồng Trừ kho tự động khi thanh toán với giao dịch nguyên tử `@Transactional`
Đây là biểu đồ tuần tự phức tạp và quan trọng nhất trong đề tài của Nhóm trưởng Duy, mô tả chi tiết tương tác đa tầng từ giao diện người dùng qua các lớp Java đến CSDL MySQL:

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 6.1:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Sequence_TruKho.svg`  
> ![Hình 6.1: Biểu đồ Tuần tự đa tầng luồng trừ kho tự động @Transactional](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_TruKho.svg)

##### Phân tích chi tiết Biểu đồ Tuần tự Trừ kho (Hình 6.1):
1. **Khởi phát:** Thu ngân bấm nút "Thanh toán & Xuất hóa đơn" trên giao diện `banhang.html`. Client gửi request `POST /api/order/{maHoaDon}/thanh-toan`.
2. **Tiếp nhận & Mở giao dịch:** `DonHangController` chuyển tiếp đến `DonHangService`. Phương thức `thanhToanDonHang` mở khối giao dịch toàn vẹn:
   `@Transactional(rollbackFor = Exception.class)`.
3. **Khóa dòng hóa đơn:** `DonHangService` gửi truy vấn `SELECT * FROM hoa_don WHERE ma_hoa_don = ? FOR UPDATE` để ngăn chặn các luồng khác can thiệp đồng thời vào đơn này.
4. **Ủy quyền phân hệ Kho:** `DonHangService` gọi `khoService.truKhoTuDong(hoaDon)` với thuộc tính lan truyền giao dịch `@Transactional(propagation = Propagation.MANDATORY)` (bắt buộc phải chạy trong giao dịch đang mở).
5. **Vòng lặp bóc tách định mức BOM:**
   * Hệ thống lặp qua từng `ChiTietHoaDon` trong hóa đơn.
   * `KhoService` gọi `CongThucRepository.findByMonNuoc(monNuoc)` lấy danh sách nguyên liệu cấu thành.
   * Với từng nguyên liệu: Khóa dòng nguyên liệu bằng `SELECT * FROM nguyen_lieu WHERE ma_nl = ? FOR UPDATE` (sắp xếp tăng dần theo `ma_nl` để chống Deadlock).
   * Kiểm tra điều kiện tồn kho: Nếu phát hiện `soLuongTon < tongTieuHao`, hệ thống lập tức ném ngoại lệ `ResponseStatusException(HttpStatus.BAD_REQUEST, "Kho không đủ nguyên liệu...")`.
   * Giao dịch tự động Rollback toàn bộ: mọi thay đổi trước đó trên hóa đơn và các nguyên liệu khác đều bị hủy bỏ, bảo toàn dữ liệu.
6. **Cập nhật & Cam kết:** Nếu đủ hàng, trừ lùi số lượng tồn, cập nhật hóa đơn sang `DA_THANH_TOAN`, bàn về `TRONG`. Giao dịch Commit thành công.
7. **Phản hồi:** Trả dữ liệu hóa đơn hoàn tất về `banhang.html` để render phiếu in nhiệt. Đồng thời khi `kho.html` được tải lại, hệ thống hiển thị nhãn cảnh báo đỏ nếu nguyên liệu chạm ngưỡng tối thiểu.

---

#### 6.1.2. Luồng Bán hàng POS: Tạo đơn hàng và cập nhật trạng thái bàn

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 6.2:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Sequence_POS.svg`  
> ![Hình 6.2: Biểu đồ Tuần tự quy trình Bán hàng POS và Lưu đơn](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_POS.svg)

##### Phân tích chi tiết Biểu đồ Tuần tự POS (Hình 6.2):
* Thể hiện luồng tương tác giữa Thu ngân $\rightarrow$ `banhang.html` $\rightarrow$ `DonHangController` $\rightarrow$ `DonHangService` $\rightarrow$ `BanRepository` $\rightarrow$ `HoaDonRepository` $\rightarrow$ `MySQL`.
* Minh họa việc bắt giữ mã `yeuCauTao` để chống tạo trùng đơn (Idempotency).
* Thể hiện việc cập nhật trạng thái bàn sang `CO_KHACH` và kích hoạt tăng số phiên bản `phien_ban` của đơn hàng.

---

#### 6.1.3. Luồng Chấm công nhân sự: Check-in thời gian thực

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 6.3:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\Sequence_ChamCong.svg`  
> ![Hình 6.3: Biểu đồ Tuần tự quy trình Check-in ca làm việc](file:///d:/3.1.%20PTTKYC/CK/diagram/Sequence_ChamCong.svg)

##### Phân tích chi tiết Biểu đồ Tuần tự Chấm công (Hình 6.3):
* Nhân viên chọn mã nhân viên và mã ca làm trên `nhansu.html` $\rightarrow$ gửi `POST /api/cham-cong/check-in`.
* `ChamCongService` truy vấn thông tin ca làm từ `CaLamRepository` để lấy `gioBatDau`.
* Lấy thời gian máy chủ `LocalDateTime.now()` để đối chiếu: nếu đến trễ $> 15$ phút, gán nhãn `DI_TRE`; ngược lại gán `DUNG_GIO`.
* Lưu bản ghi vào bảng `cham_cong` và trả về kết quả cho giao diện hiển thị Toast thông báo.

---

### 6.2. Biểu đồ Máy trạng thái đối tượng (State Machine Diagrams)
Mô hình hóa vòng đời và sự chuyển đổi trạng thái của hai thực thể quan trọng nhất hệ thống: **Bàn (`Ban`)** và **Hóa đơn (`HoaDon`)**:

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 6.4:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\diagram\StateMachine_TrangThai.svg`  
> ![Hình 6.4: Biểu đồ Máy trạng thái vòng đời Bàn và Hóa đơn](file:///d:/3.1.%20PTTKYC/CK/diagram/StateMachine_TrangThai.svg)

#### Phân tích chi tiết Biểu đồ Máy trạng thái (Hình 6.4):
1. **Vòng đời trạng thái Bàn (`Ban`):**
   * Trạng thái khởi tạo: `TRONG` (Bàn trống, hiển thị viền xanh lá trên POS).
   * Chuyển trạng thái sang `CO_KHACH` (Viền đỏ): Xảy ra khi có sự kiện `Lưu đơn thành công` (`POST /api/order/tao-don`).
   * Chuyển trạng thái từ `CO_KHACH` quay về `TRONG`: Xảy ra khi một trong hai sự kiện diễn ra:
     - `Thanh toán hoàn tất`: Hóa đơn được thanh toán đầy đủ và trừ kho thành công.
     - `Hủy đơn hàng`: Thu ngân xác nhận hủy toàn bộ đơn đang mở trên bàn đó.
2. **Vòng đời trạng thái Hóa đơn (`HoaDon`):**
   * Trạng thái khởi tạo: `CHO_THANH_TOAN` (Đơn hàng đang phục vụ, cho phép thêm/bớt món, tăng số phiên bản).
   * Chuyển sang trạng thái kết thúc `DA_THANH_TOAN`: Xảy ra khi thu ngân thực hiện thanh toán, trừ kho thành công và xuất bill. Trạng thái này là bất biến, không cho phép chỉnh sửa hay xóa món nữa.
   * Chuyển sang trạng thái kết thúc `DA_HUY`: Xảy ra khi thu ngân bấm "Hủy đơn", đơn được đóng lại và bàn được giải phóng về trống (nguyên liệu kho không bị trừ).

---

### 6.3. Các giải pháp kỹ thuật nâng cao giải quyết tranh chấp đồng thời

#### 6.3.1. Cơ chế Khóa bi quan (`Pessimistic Locking`) chống bán khống nguyên liệu
* **Nguy cơ bài toán:** Khi quán cà phê đông khách, hai thu ngân tại hai máy POS khác nhau cùng thanh toán hai đơn hàng có chứa "Cà phê sữa đá" tại cùng một giây. Nếu tồn kho Sữa đặc chỉ còn $40\text{ ml}$ (trong khi mỗi ly cần $30\text{ ml}$, tổng cần $60\text{ ml}$): nếu không có cơ chế khóa, cả 2 luồng cùng đọc thấy $40\text{ ml} \ge 30\text{ ml}$ và cùng đồng ý trừ, dẫn đến tồn kho bị âm $-20\text{ ml}$ (Bán khống - Overselling).
* **Giải pháp đã cài đặt:** Trong `KhoService`, trước khi trừ nguyên liệu, hệ thống thực thi câu lệnh:
  ```java
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT nl FROM NguyenLieu nl WHERE nl.maNL = :id")
  Optional<NguyenLieu> findByIdForUpdate(@Param("id") Integer id);
  ```
  Câu lệnh này ép MySQL đặt khóa độc quyền `SELECT ... FOR UPDATE` trên dòng nguyên liệu. Luồng thứ hai đến sau bắt buộc phải xếp hàng chờ luồng thứ nhất hoàn tất giao dịch. Khi luồng thứ nhất thanh toán xong, tồn kho còn $10\text{ ml}$; luồng thứ hai được mở khóa đọc thấy $10\text{ ml} < 30\text{ ml}$ sẽ lập tức bị chặn lại và Rollback an toàn.

#### 6.3.2. Cơ chế Khóa lạc quan (`Optimistic Locking`) chống ghi đè đơn hàng
* **Nguy cơ bài toán:** Thu ngân A mở Đơn #3 để thêm bánh ngọt, cùng lúc đó Thu ngân B mở Đơn #3 để hủy ly trà đào. Nếu cả hai cùng bấm Lưu, thao tác của người bấm sau sẽ ghi đè và làm mất mát thay đổi của người bấm trước (*Lost Update*).
* **Giải pháp đã cài đặt:** Entity `HoaDon` trang bị trường `@Version private Long phienBan;`. Khi tải đơn, client giữ số phiên bản hiện tại. Khi gửi request cập nhật, client gửi kèm `phienBan`. Nếu phiên bản trong CSDL đã bị người khác tăng lên, Hibernate tự động ném ngoại lệ `OptimisticLockException` và Spring Boot trả về lỗi **HTTP 409 Conflict**. Giao diện cảnh báo thu ngân đơn hàng đã có thay đổi mới và yêu cầu tải lại trước khi thao tác tiếp.

#### 6.3.3. Cơ chế Khóa Idempotency Key chống thao tác trùng lặp
* Khi mạng wifi chập chờn hoặc nhân viên vô tình nhấp đúp nút "Lưu đơn" hoặc "Thanh toán", Client sinh chuỗi ngẫu nhiên chuẩn UUID (ví dụ: `c8a1b2c3-...`) gán vào `yeuCauTao` hoặc `yeuCauThanhToan`.
* Phía server kiểm tra: nếu mã yêu cầu này đã được xử lý trong vòng đời của đơn, server lập tức trả về kết quả của lần xử lý trước đó mà không thực hiện tạo thêm đơn mới hay trừ kho lần thứ hai.

#### 6.3.4. Cơ chế Chụp đơn giá bán (*Price Snapshot*)
* Bảng `chi_tiet_hoa_don` có cột riêng `don_gia_ban`. Khi thêm món vào giỏ, hệ thống sao chép đơn giá niêm yết hiện tại của món nước vào cột này. Ngay cả khi người quản lý vào trang quản trị tăng giá Cà phê sữa từ $29.000\text{ đ}$ lên $35.000\text{ đ}$ vào ngày mai, toàn bộ các hóa đơn cũ và đơn đang mở hôm nay vẫn giữ nguyên đúng mức giá $29.000\text{ đ}$ đã bán, đảm bảo tính bất biến của chứng từ tài chính.

---

## CHƯƠNG 7: THIẾT KẾ GIAO DIỆN & KẾT QUẢ KIỂM THỬ THỰC NGHIỆM

### 7.1. Sơ đồ luồng điều hướng màn hình (UI Navigation Flow)

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH SƠ ĐỒ 7.1:**  
> Sơ đồ được lưu trữ tại thư mục dự án: `d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype\BanGiao_UML\UI_Navigation_Flow.svg`  
> ![Hình 7.1: Sơ đồ luồng điều hướng màn hình UI Navigation Flow](file:///d:/3.1.%20PTTKYC/CK/02_SourceCode_WebJava_Prototype/BanGiao_UML/UI_Navigation_Flow.svg)

#### Phân tích Luồng điều hướng (Hình 7.1):
* Hệ thống bắt đầu tại màn hình Xác thực `index.html`. Sau khi đăng nhập thành công, hệ thống phân luồng:
  * Nếu là `STAFF` $\rightarrow$ Tự động chuyển tới `banhang.html`. Thanh Navbar tự động ẩn hai tab "Kho & KPI" và "Nhân sự". Nút "Thoát" gọi API đăng xuất để hủy phiên.
  * Nếu là `MANAGER` $\rightarrow$ Chuyển tới `kho.html`. Có quyền tự do di chuyển qua lại giữa cả 3 màn hình (`banhang.html`, `kho.html`, `nhansu.html`).
  * Nếu là `ADMIN` $\rightarrow$ Chuyển tới `banhang.html` với toàn bộ quyền hạn mở khóa.
* Nếu người dùng chưa đăng nhập hoặc tài khoản `STAFF` cố tình gõ link `kho.html` trên trình duyệt, Route Guard ở đầu trang phát hiện và chuyển hướng cưỡng bức quay lại trang hợp lệ.

---

### 7.2. Bộ minh chứng giao diện nguyên mẫu thực nghiệm (Prototype UI Walkthrough)
Tất cả các hình ảnh minh chứng dưới đây được chụp trực tiếp từ ứng dụng Web Java chạy thực tế trên máy chủ Localhost (`http://localhost:8080`), đã được chuẩn hóa phông chữ Google Fonts `Roboto` tiếng Việt có dấu sắc nét, loại bỏ hoàn toàn các icon/emoji phi chuẩn:

---

#### 7.2.1. Giao diện Đăng nhập & Xác thực hệ thống (`index.html`)

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH MINH CHỨNG 7.2:**  
> Ảnh chụp thực tế: `d:\3.1. PTTKYC\CK\screenshots\fixed_vietnamese_ui.png`  
> ![Hình 7.2: Giao diện Đăng nhập hệ thống](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_vietnamese_ui.png)

##### Phân tích giao diện Đăng nhập (Hình 7.2):
* Thiết kế dạng thẻ Card hiện đại, nổi bật thương hiệu **COFFEE POS** cùng mô tả "Hệ thống quản lý bán hàng và kho nguyên liệu".
* Bảng hướng dẫn tài khoản mẫu rõ ràng: `admin / 123456`, `manager / 123456`, `staff1 / 123456`.
* Nếu người dùng nhập sai mật khẩu, hộp thông báo lỗi màu đỏ xuất hiện tức thì: "Sai tên đăng nhập hoặc mật khẩu!" mà không tải lại trang.

---

#### 7.2.2. Giao diện Bán hàng POS & Sơ đồ bàn động thời gian thực (`banhang.html`)

> [!NOTE]
> **VỊ TRÍ ĐẶT HÌNH ẢNH MINH CHỨNG 7.3:**  
> Ảnh chụp thực tế: `d:\3.1. PTTKYC\CK\screenshots\fixed_pos_screen.png`  
> ![Hình 7.3: Màn hình Bán hàng POS và Sơ đồ 10 bàn đổi màu động](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_pos_screen.png)

##### Phân tích màn hình Bán hàng POS (Hình 7.3):
* **Thanh Header:** Hiển thị thương hiệu COFFEE POS, các tab điều hướng, tên người dùng đăng nhập kèm vai trò (`Nguyễn Văn Duy (Nhóm trưởng) | ADMIN`) và nút "Thoát" hủy phiên an toàn.
* **Khu vực Sơ đồ bàn (Góc trên bên trái):** Lưới 10 bàn hiển thị động theo trạng thái CSDL. Bàn trống có viền xanh lá (Bàn 01, 02, 04, 05, 06, 07, 08, 09, 10); bàn đang phục vụ có viền đỏ (Bàn 03 - Có khách). Cung cấp các nút chuyển chế độ "Tại bàn", "+ Đơn mang đi" và "Tải lại".
* **Khu vực Thực đơn đồ uống (Góc dưới bên trái):** Danh mục 20 món nước hiển thị dạng thẻ card kèm giá niêm yết (Cà phê đen đá: 25.000 đ, Cà phê sữa đá: 29.000 đ, Bạc xỉu Sài Gòn: 32.000 đ...); hỗ trợ thanh tìm kiếm nhanh theo tên món và bộ lọc theo danh mục (Cà phê, Trà, Đá xay, Nước ép).
* **Khu vực Giỏ hàng (Cột bên phải):** Hiển thị chi tiết đơn hàng, tạm tính tổng tiền, các nút chức năng "Lưu đơn", "Thanh toán & Xuất hóa đơn", "Hủy đơn / Xóa giỏ" và danh sách lịch sử các đơn hàng đã lưu gần nhất.

---

#### 7.2.3. Quy trình Gọi món, Lưu đơn và Xuất hóa đơn nhiệt

> [!NOTE]
> **VỊ TRÍ ĐẶT BỘ ẢNH MINH CHỨNG 7.4 ĐẾN 7.8 (LUỒNG POS LIÊN HOÀN):**  
> *Hình 7.4 – Tải lại đơn Bàn 03:* [screenshots/03_table3_occupied_loaded.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/03_table3_occupied_loaded.png)  
> *Hình 7.5 – Giỏ hàng Bàn 02 kèm ghi chú:* [screenshots/04_cart_table2.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/04_cart_table2.png)  
> *Hình 7.6 – Bàn 02 đổi sang viền đỏ sau khi lưu:* [screenshots/05_order_saved_table2.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/05_order_saved_table2.png)  
> *Hình 7.7 – Modal Thanh toán & Xác thực tiền khách đưa:* [screenshots/06_payment_modal_validation.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/06_payment_modal_validation.png)  
> *Hình 7.8 – Hóa đơn nhiệt COFFEE POS hoàn tất:* [screenshots/07_receipt_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/07_receipt_modal.png)  
> ![Hình 7.8: Hóa đơn bán lẻ COFFEE POS #4 in nhiệt](file:///d:/3.1.%20PTTKYC/CK/screenshots/07_receipt_modal.png)

##### Phân tích luồng Bán hàng thực nghiệm:
* Khi bấm vào Bàn 03 đang có khách (Hình 7.4), giỏ hàng tự động nạp lại Đơn #3 cùng các món đang gọi với tổng tiền $63.000\text{ đ}$.
* Khi chọn Bàn 02 và gọi món (Hình 7.5), nhân viên thêm Cà phê sữa đá (ghi chú: "ít đường") và Trà đào cam sả. Tạm tính là $67.000\text{ đ}$.
* Bấm "Lưu đơn" (Hình 7.6), hệ thống sinh Đơn #4, Bàn 02 lập tức chuyển viền đỏ "Có khách".
* Mở popup thanh toán (Hình 7.7): Thu ngân nhập tiền khách đưa $10.000\text{ đ} < 67.000\text{ đ}$, hệ thống chặn lại và báo lỗi đỏ: "Tiền khách đưa chưa đủ hoặc không hợp lệ". Ô chiết khấu bị khóa hoàn toàn khi đăng nhập tài khoản STAFF.
* Nhập đúng tiền khách đưa $100.000\text{ đ}$ và xác nhận (Hình 7.8): Hệ thống xuất hóa đơn nhiệt chuyên nghiệp: hiển thị đầy đủ thông tin thu ngân, ngày giờ, danh sách món, tiền khách đưa $100.000\text{ đ}$, tiền thừa trả khách $33.000\text{ đ}$. Bàn 02 tự động giải phóng về trạng thái xanh "Trống".

---

#### 7.2.4. Giao diện Quản trị Kho, Định mức BOM & Cảnh báo tồn đỏ (`kho.html`)

> [!NOTE]
> **VỊ TRÍ ĐẶT BỘ ẢNH MINH CHỨNG 7.9 ĐẾN 7.12 (PHÂN HỆ KHO DUY PHỤ TRÁCH):**  
> *Hình 7.9 – Màn hình Kho & Nhãn đỏ SẮP HẾT HÀNG:* [screenshots/fixed_kho_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_kho_screen.png)  
> *Hình 7.10 – Hộp thoại Thiết lập Định mức BOM:* [screenshots/09_recipe_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/09_recipe_modal.png)  
> *Hình 7.11 – Hộp thoại Nhập thêm kho:* [screenshots/10_nhapkho_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/10_nhapkho_modal.png)  
> *Hình 7.12 – Tồn kho khôi phục nhãn xanh Đủ hàng:* [screenshots/11_kho_restocked.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/11_kho_restocked.png)  
> ![Hình 7.9: Màn hình Quản trị Kho và Nhãn cảnh báo đỏ](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_kho_screen.png)

##### Phân tích thực nghiệm Phân hệ Kho:
* **Tự động trừ kho & Kích hoạt cảnh báo đỏ (Hình 7.9):** Sau khi Đơn #4 được thanh toán (chứa 1 ly Cà phê sữa đá có định mức tiêu hao $30\text{ ml}$ Sữa đặc), lượng tồn kho của "Sữa đặc Ngôi Sao" tự động bị trừ ngầm từ $120\text{ ml}$ giảm xuống còn $90\text{ ml}$. Do $90\text{ ml} \le 100\text{ ml}$ (ngưỡng tối thiểu), hệ thống tự động bật nhãn đỏ cảnh báo **`SẮP HẾT HÀNG`** kèm hiệu ứng nhấp nháy; trên khối thẻ KPI, chỉ số "Nguyên liệu sắp hết" tự động cập nhật số lượng cảnh báo là `1`.
* **Quản lý Định mức công thức BOM (Hình 7.10):** Hộp thoại cho phép chọn món nước (Cà phê sữa đá), chọn nguyên liệu (Sữa đặc), nhập định mức tiêu hao $30\text{ ml}$ và bấm "Lưu Định Mức Vào CSDL" thành công mà không gây reload trang.
* **Nhập thêm kho nguyên liệu (Hình 7.11 & 7.12):** Thủ kho bấm "Nhập kho" tại dòng Sữa đặc, nhập thêm $500\text{ ml}$ và xác nhận. Tồn kho cập nhật lên $590\text{ ml} > 100\text{ ml}$, nhãn cảnh báo đỏ lập tức biến mất và khôi phục nhãn màu xanh **"Đủ hàng"**.

---

#### 7.2.5. Giao diện Quản lý Nhân sự & Chấm công ca làm việc (`nhansu.html`)

> [!NOTE]
> **VỊ TRÍ ĐẶT BỘ ẢNH MINH CHỨNG 7.13 ĐẾN 7.16 (PHÂN HỆ NHÂN SỰ ĐẠT PHỤ TRÁCH):**  
> *Hình 7.13 – Màn hình Quản lý Nhân sự & Đồng hồ thời gian thực:* [screenshots/fixed_nhansu_screen.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_nhansu_screen.png)  
> *Hình 7.14 – Ghi nhận Check-in thành công đối chiếu giờ ca:* [screenshots/13_attendance_checked_in.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/13_attendance_checked_in.png)  
> *Hình 7.15 – Modal Thêm mới hồ sơ nhân sự phân quyền:* [screenshots/14_add_employee_modal.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/14_add_employee_modal.png)  
> *Hình 7.16 – Hóa đơn có áp dụng chiết khấu của Quản lý:* [screenshots/15_manager_discount_receipt.png](file:///d:/3.1.%20PTTKYC/CK/screenshots/15_manager_discount_receipt.png)  
> ![Hình 7.13: Màn hình Quản lý Nhân sự và Chấm công thời gian thực](file:///d:/3.1.%20PTTKYC/CK/screenshots/fixed_nhansu_screen.png)

##### Phân tích thực nghiệm Phân hệ Nhân sự:
* **Chấm công thời gian thực (Hình 7.13 & 7.14):** Đồng hồ kỹ thuật số chạy thời gian thực tế. Nhân viên chọn tên và ca làm việc, bấm "Check-in Vào Ca". Máy chủ đối chiếu giờ: nhân viên Phạm Thu Trang vào ca lúc 07:22 (ca bắt đầu 07:00, trễ quá 15 phút) $\rightarrow$ gắn nhãn đỏ `Đi trễ`; nhân viên Lê Tấn Đạt vào ca lúc 06:55 $\rightarrow$ gắn nhãn xanh `Đúng giờ`. Cung cấp nút "Check-out" khi kết thúc ca.
* **Thêm mới nhân viên (Hình 7.15):** Modal cho phép tạo nhân viên mới có kiểm tra trùng lặp tên đăng nhập.
* **Phân quyền Quản lý áp dụng chiết khấu (Hình 7.16):** Khi đăng nhập tài khoản `manager`, ô chiết khấu trên POS mở khóa cho phép nhập giảm giá $5.000\text{ đ}$. Hóa đơn xuất thành công phản ánh chính xác số tiền giảm giá và số tiền thu thực tế.

---

### 7.3. Kế hoạch và kết quả kiểm thử tích hợp tự động Backend (14/14 Test Cases Passed)
Toàn bộ mã nguồn kiểm thử tích hợp tự động được viết trong tệp tin `DonHangIntegrationTest.java` (và `NhanSuIntegrationTest.java`) sử dụng JUnit 5, Spring MockMvc và cơ sở dữ liệu H2 In-Memory tách biệt (`application-test.properties`), đảm bảo việc chạy test tự động an toàn tuyệt đối, không làm thay đổi CSDL MySQL thật trên XAMPP.

* Lệnh thực thi kiểm thử:
  ```powershell
  $env:JAVA_HOME = "F:\JDK" ; Set-Location "d:\3.1. PTTKYC\CK\02_SourceCode_WebJava_Prototype" ; .\mvnw.cmd test
  ```
* **Kết quả thực tế:** `Tests run: 14, Failures: 0, Errors: 0, Skipped: 0` – **100% BUILD SUCCESS**.

**Bảng 7.1: Bảng tổng hợp 14 ca kiểm thử tích hợp tự động Backend (DonHangIntegrationTest)**

| STT | Tên phương thức kiểm thử trong mã nguồn | Nghiệp vụ kiểm tra chi tiết | Kết quả kỳ vọng | Kết quả thực tế |
| :---: | :--- | :--- | :---: | :---: |
| 1 | `loginSessionDoesNotExposePassword` | Xác thực đăng nhập tạo `HttpSession` và ẩn tuyệt đối trường `matKhau` trong JSON profile trả về. | HTTP 200 OK | **PASS (100%)** |
| 2 | `requiresSessionAndUsesServerIdentity` | Chặn truy cập trái phép khi chưa đăng nhập và định danh nhân viên bằng session phía server. | HTTP 401 / 201 | **PASS (100%)** |
| 3 | `saveReloadUpdateCancel` | Luồng liên thông: Lưu đơn $\rightarrow$ Mở lại đơn theo bàn $\rightarrow$ Cập nhật số lượng $\rightarrow$ Hủy đơn trả bàn về `TRONG` (không trừ kho). | HTTP 201 / 200 / 409 | **PASS (100%)** |
| 4 | `createRetryReturnsSameOrderAndTableRejectsSecond` | Kiểm tra tính bất biến Idempotency khi gửi lại cùng `yeuCauTao` và chặn tạo đơn thứ 2 trên cùng 1 bàn. | HTTP 201 / 409 Conflict | **PASS (100%)** |
| 5 | `rejectsInvalidAndEmptyOrders` | Chặn các đơn hàng không hợp lệ: số lượng âm, đơn tại bàn thiếu mã bàn hoặc giỏ hàng rỗng. | HTTP 400 Bad Request | **PASS (100%)** |
| 6 | `paymentCashStockAndRetryOnce` | Chặn thanh toán thiếu tiền; thanh toán đủ tiền trừ kho tự động ($120\text{ml} \rightarrow 90\text{ml}$); chống trừ kho 2 lần khi retry. | HTTP 400 / 200 / 409 | **PASS (100%)** |
| 7 | `insufficientStockRollsBackWholePayment` | Khi nguyên liệu không đủ: Rollback toàn bộ giao dịch `@Transactional`, giữ nguyên tồn kho $120\text{ml}$, đơn vẫn `CHO_THANH_TOAN`. | HTTP 400 Bad Request | **PASS (100%)** |
| 8 | `missingRecipeBlocked` | Chặn thanh toán và rollback an toàn nếu trong đơn có món chưa được thiết lập định mức công thức. | HTTP 400 Bad Request | **PASS (100%)** |
| 9 | `discountValidationAndRole` | Phân quyền chiết khấu RBAC: chặn `STAFF` nhập chiết khấu (`403`), chặn chiết khấu âm (`400`), cho phép `MANAGER` áp dụng hợp lệ. | HTTP 403 / 400 / 200 | **PASS (100%)** |
| 10 | `transferRequiresConfirmationAndUniqueReference` | Thanh toán chuyển khoản bắt buộc tích xác nhận tiền vào tài khoản và kiểm tra mã tham chiếu duy nhất. | HTTP 400 / 200 / 409 | **PASS (100%)** |
| 11 | `pricesRemainSnapshots` | Đơn giá món trong đơn hàng đang mở giữ nguyên theo snapshot ngay cả khi danh mục đổi giá bán mới. | HTTP 200 OK | **PASS (100%)** |
| 12 | `twoConcurrentPaymentsOnlyDeductOnce` | Kiểm thử đa luồng (2 luồng thanh toán cùng lúc cho 1 đơn): Khóa bi quan và `@Version` đảm bảo chỉ 1 luồng thành công. | 1 Success / 1 Conflict | **PASS (100%)** |
| 13 | `twoOrdersCannotOversellSharedIngredient` | Kiểm thử đa luồng 2 đơn tranh chấp nguyên liệu giới hạn: chặn bán khống (Oversell), chỉ 1 đơn thành công, đơn thiếu bị Rollback. | 1 Success / 1 Rollback | **PASS (100%)** |
| 14 | `duplicateProductLinesAggregateStock` | Gộp định mức nhiều dòng trùng món trong cùng một đơn trước khi thực hiện giao dịch trừ kho. | HTTP 400 Rollback an toàn | **PASS (100%)** |

---

### 7.4. Kết quả kiểm thử hộp đen E2E tự động trên giao diện Web (16/16 Scenarios Passed)
Sử dụng công cụ tự động hóa Headless Google Chrome (`puppeteer-core`) thực thi kịch bản người dùng liên thông từ đầu đến cuối trên trình duyệt:

**Bảng 7.2: Kết quả kiểm thử hộp đen E2E tự động trên giao diện Web**

| Mã Kịch bản | Phân hệ màn hình | Tên ca kiểm thử E2E | Thao tác thực hiện | Kết quả ghi nhận thực tế | Trạng thái |
| :---: | :--- | :--- | :--- | :--- | :---: |
| **TC-01** | Xác thực (`index.html`) | Hiển thị form đăng nhập | Truy cập trang chủ | Render form đăng nhập chuẩn đẹp, nhận phím tốt. | **PASS** |
| **TC-01b** | Xác thực (`index.html`) | Chặn mật khẩu sai | Nhập `admin / sai_pass` | Hiện alert đỏ cảnh báo, không chuyển trang. | **PASS** |
| **TC-02** | Bán hàng (`banhang.html`) | Đăng nhập tài khoản STAFF | Đăng nhập `staff1` | Điều hướng vào POS, hiện badge "Lê Tấn Đạt \| STAFF". | **PASS** |
| **TC-03** | Bán hàng (`banhang.html`) | Mở đơn bàn có khách | Bấm Bàn 03 (đang đỏ) | Giỏ hàng tự động tải lại Đơn #3 cùng danh sách món. | **PASS** |
| **TC-04** | Bán hàng (`banhang.html`) | Thêm món & Ghi chú | Chọn Bàn 02, chọn 2 món | Giỏ hàng nhận món kèm ghi chú "ít đường", tính đúng tạm tính. | **PASS** |
| **TC-05** | Bán hàng (`banhang.html`) | Bấm nút Lưu đơn | Bấm "Lưu đơn" | Lưu CSDL Đơn #4, Bàn 02 chuyển sang viền đỏ có khách. | **PASS** |
| **TC-06** | Bán hàng (`banhang.html`) | Kiểm tra hợp lệ tiền nhận | Nhập thiếu tiền trả khách | Modal chặn lại báo đỏ "Tiền khách đưa chưa đủ". | **PASS** |
| **TC-07** | Bán hàng (`banhang.html`) | Thanh toán & Xuất hóa đơn | Nhập $100.000\text{ đ}$ | Xuất Bill COFFEE POS #4, tính đúng tiền thừa $33.000\text{ đ}$, bàn về xanh. | **PASS** |
| **TC-08** | Kho & KPI (`kho.html`) | Trừ kho & Cảnh báo tồn đỏ | Mở trang Kho | Sữa đặc giảm còn 90ml, nhãn đỏ `SẮP HẾT HÀNG` bật sáng. | **PASS** |
| **TC-09** | Kho & KPI (`kho.html`) | Thiết lập định mức BOM | Mở modal công thức | Modal nạp danh sách nguyên liệu và định mức chi tiết. | **PASS** |
| **TC-10** | Kho & KPI (`kho.html`) | Mở form nhập kho | Bấm "Nhập kho" Sữa đặc | Modal mở ra với placeholder thân thiện, đơn vị tính ml. | **PASS** |
| **TC-11** | Kho & KPI (`kho.html`) | Nhập thêm 500ml nguyên liệu | Nhập 500ml bấm xác nhận | Tồn kho tăng lên 590ml, nhãn đỏ đổi thành xanh "Đủ hàng". | **PASS** |
| **TC-12** | Nhân sự (`nhansu.html`) | Màn hình nhân sự & Đồng hồ | Mở trang Nhân sự | Hiển thị danh sách 5 nhân viên và đồng hồ thời gian thực. | **PASS** |
| **TC-13** | Nhân sự (`nhansu.html`) | Check-in ca làm việc | Chọn ca bấm Check-in | Đối chiếu giờ thực tế máy chủ, ghi nhận bản ghi chấm công. | **PASS** |
| **TC-14** | Nhân sự (`nhansu.html`) | Thêm mới nhân viên | Mở modal thêm nhân sự | Modal mở ra đầy đủ các trường thông tin và vai trò. | **PASS** |
| **TC-15** | Bán hàng (`banhang.html`) | Phân quyền chiết khấu | Đăng nhập `manager` | Ô chiết khấu mở khóa, xuất Bill giảm $5.000\text{ đ}$ thành công. | **PASS** |
| **TC-16** | Toàn hệ thống | Chuẩn hóa phông chữ & Icon | Quét toàn bộ 4 trang | 100% tiếng Việt có dấu sắc nét, 0 icon lỗi bàn phím. | **PASS** |

---

### 7.5. Bảng tổng hợp lỗi phát hiện và lịch sử khắc phục triệt để (BUG-01 đến BUG-06)

**Bảng 7.3: Bảng tổng hợp lỗi, phân loại mức độ và trạng thái khắc phục triệt để**

| Mã Bug | Phân hệ ảnh hưởng | Mức độ | Hiện tượng khi phát hiện | Giải pháp kỹ thuật đã áp dụng | Trạng thái nghiệm thu |
| :---: | :--- | :---: | :--- | :--- | :---: |
| **BUG-01** | Điều hướng / Phân quyền RBAC | **Major** | Tài khoản `STAFF` có thể bấm vào tab Kho và Nhân sự trên Navbar `banhang.html`; gõ URL trực tiếp vẫn vào được. | 1. Ẩn tab Kho và Nhân sự trên Navbar khi `vaiTro === 'STAFF'`.<br>2. Bổ sung script Route Guard ngay trong `<head>` của `kho.html` và `nhansu.html` để chặn và chuyển hướng cưỡng bức. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-02** | Quản lý phiên làm việc (Session) | **Major** | Nút "Thoát" tại `kho.html` và `nhansu.html` chỉ dùng thẻ `<a href="index.html">`, không hủy `HttpSession` trên server. | Đổi thành nút bấm gọi API `POST /api/auth/logout`, xóa sạch `currentUser` trong `localStorage` và `sessionStorage.clear()`. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-03** | Trải nghiệm người dùng (UX) | **Minor** | Thao tác nhập kho, chấm công dùng hàm `window.alert()` làm khựng giao diện người dùng. | Tích hợp Bootstrap 5 Toast (`#liveToast`) tự động ẩn sau 3 giây, hiển thị mượt mà ở góc phải màn hình. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-04** | Nhập liệu giao diện | **Trivial** | Mở popup nhập kho thì ô số lượng gán cứng số 500, không phù hợp cho đơn vị kg hoặc hộp. | Xóa bỏ gán cứng `value="500"`, chuyển thành ô trống kèm `placeholder="Nhập số lượng..."` thân thiện. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-05** | Tài nguyên tĩnh Web | **Trivial** | Trình duyệt báo lỗi Console đỏ: `GET /favicon.ico 404 (Not Found)` tại tất cả các trang. | Bổ sung file icon tách cà phê chuẩn `favicon.ico` (32-bit ICO) vào thư mục `static/` và liên kết `<link rel="icon">`. | **ĐÃ XỬ LÝ (VERIFIED)** |
| **BUG-06** | CSDL & Tiếng Việt có dấu (Encoding Mojibake) | **Critical** | Dữ liệu nạp từ CSDL (tên món, tên bàn, tên nhân viên) bị hiển thị thành ký tự lạ dạng Mojibake (`C├á ph├¬ ─æen ─æ├í`, `Nguy├¬n V─ân Duy`); còn chứa icon emoji không gõ được từ bàn phím. | 1. Bổ sung `SET NAMES utf8mb4;` và `DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci` vào `database_mau.sql`.<br>2. Thêm `connectionCollation=utf8mb4_unicode_ci` vào chuỗi kết nối MySQL.<br>3. Nạp lại CSDL sạch sẽ bằng lệnh `SOURCE` của MySQL với cờ UTF-8.<br>4. Nhúng Google Fonts `Roboto` hỗ trợ 100% tiếng Việt có dấu, xóa sạch toàn bộ emoji/icon bàn phím không gõ được. | **ĐÃ XỬ LÝ (VERIFIED)** |

---

## CHƯƠNG 8: KẾT LUẬN & HƯỚNG PHÁT TRIỂN

### 8.1. Đánh giá mức độ hoàn thành so với mục tiêu đề ra
Đối chiếu với các yêu cầu bắt buộc của Đề thi báo cáo cuối kỳ học phần *Phân tích và Thiết kế Yêu cầu (Mã môn: 502050)* do Khoa Công nghệ Thông tin – Trường Đại học Tôn Đức Thắng ban hành, nhóm tự đánh giá đã **hoàn thành $100\%$ các mục tiêu đặt ra**:
1. **Về mặt tài liệu và mô hình hóa UML:** Xây dựng đầy đủ 13 sơ đồ thiết kế PlantUML chuẩn mực học thuật (UseCase, Class Diagram, ERD Crow's Foot 9 thực thể, 3 Activity Diagram phân làn, 3 Sequence Diagram đa tầng, State Machine, Component Diagram, Deployment Diagram và Package Diagram). Đặc tả chi tiết 7 Use Case cốt lõi theo mẫu Fully-Dressed Template 9 trường.
2. **Về mặt sản phẩm phần mềm Java chạy thực tế:** Xây dựng thành công ứng dụng Web Java nguyên khối **Spring Boot 3.2.5** kết hợp CSDL **MySQL 8.0** trên XAMPP. Tất cả các luồng nghiệp vụ cốt lõi: *Tạo đơn POS*, *Lưu đơn mở lại đơn*, *Thanh toán xuất hóa đơn nhiệt*, *Tự động bóc tách định mức BOM trừ kho ngầm*, *Bật cảnh báo đỏ khi chạm ngưỡng tối thiểu*, *Nhập thêm kho*, *Check-in/Check-out chấm công thời gian thực* và *Phân quyền RBAC* đều hoạt động ổn định và trơn tru.
3. **Về mặt kiểm thử chất lượng:** Vượt qua **$14/14$ ca kiểm thử tích hợp tự động Backend (100% PASS)** và **$16/16$ kịch bản kiểm thử hộp đen E2E trên trình duyệt Chrome (100% PASS)**. Khắc phục triệt để 6 lỗi kỹ thuật từ bảo mật phân quyền đến bảng mã hiển thị tiếng Việt có dấu.

### 8.2. Ưu điểm nổi bật của hệ thống
* **Tính toàn vẹn dữ liệu giao dịch cao (ACID):** Việc bọc toàn bộ luồng thanh toán và trừ kho trong giao dịch `@Transactional(rollbackFor = Exception.class)` kết hợp khóa bi quan `SELECT ... FOR UPDATE` đảm bảo kho không bao giờ bị bán khống (Overselling) và triệt tiêu nguy cơ sai lệch số liệu kế toán.
* **Chống xung đột ghi đè dữ liệu đồng thời:** Cơ chế Khóa lạc quan `@Version` bảo vệ các đơn hàng đang mở không bị các thu ngân ghi đè dữ liệu lẫn nhau khi thao tác cùng thời điểm.
* **Giao diện hiện đại, trực quan, tốc độ phản hồi cực nhanh:** Giao diện Web được thiết kế theo phong cách cà phê hiện đại, áp dụng font chữ `Roboto` hiển thị tiếng Việt sắc nét, sơ đồ bàn đổi màu động tức thì giúp nhân viên mới làm quen và thao tác thành thạo chỉ sau 5 phút hướng dẫn.
* **Kiến trúc All-in-one gọn nhẹ, dễ triển khai:** Toàn bộ backend, frontend và CSDL được đóng gói tinh gọn, có thể chạy trực tiếp trên bất kỳ máy tính nào chỉ với Java và XAMPP mà không đòi hỏi hạ tầng phức tạp.

### 8.3. Hạn chế còn tồn tại
* Hệ thống hiện mới mô phỏng thanh toán chuyển khoản bằng xác nhận thủ công, chưa kết nối trực tiếp API Webhook tự động với các ngân hàng thương mại hoặc cổng trung gian thanh toán.
* Chưa hỗ trợ tính năng tách bàn hoặc gộp nhiều bàn thành một hóa đơn thanh toán chung.
* Chưa trang bị hệ thống gửi tin nhắn thông báo cảnh báo kho qua Telegram hoặc Email đến điện thoại của chủ quán khi vắng mặt.

### 8.4. Chiến lược chuyển giao và triển khai phần mềm (Direct Cutover Strategy)
Để đưa phần mềm vào ứng dụng thực tế tại quán cà phê mà không làm gián đoạn việc kinh doanh, nhóm đề xuất chiến lược **Chuyển giao trực tiếp vào đầu tuần (Direct Cutover on Monday Morning)**:
1. **Giai đoạn chuẩn bị (Thứ Bảy & Chủ Nhật):** Tiến hành kiểm kê toàn bộ nguyên vật liệu thực tế trong kho quán; nạp danh mục 20 món nước và thiết lập định mức công thức BOM chính xác vào CSDL; tạo tài khoản phân quyền cho toàn bộ nhân viên.
2. **Giai đoạn đào tạo (Chiều Chủ Nhật, 2 tiếng):** Hướng dẫn nhân viên phục vụ, thu ngân và pha chế thao tác thử nghiệm trên máy POS; hướng dẫn cách Check-in/Check-out đầu ca.
3. **Thời điểm kích hoạt chính thức (06:30 sáng Thứ Hai):** Bắt đầu ngày mới, quán ngưng hoàn toàn việc ghi order giấy và chuyển đổi $100\%$ sang thao tác trên phần mềm COFFEE POS. Quản lý trực tiếp tại quầy trong 3 ngày đầu để hỗ trợ và xử lý phát sinh.

### 8.5. Hướng phát triển và mở rộng trong tương lai
* **Mở rộng 1 – Tích hợp mã VietQR động:** Khi khách chọn chuyển khoản, hệ thống tự động sinh mã QR ngân hàng chuẩn NAPAS 247 có nhúng sẵn số tiền chính xác và nội dung chuyển khoản là mã hóa đơn. Khi tiền vào tài khoản, hệ thống tự động xác nhận thanh toán qua Webhook.
* **Mở rộng 2 – Quét mã QR gọi món tại bàn (Self-ordering QR Code):** Dán mã QR cố định tại mỗi bàn. Khách hàng dùng điện thoại cá nhân quét mã để xem menu và tự gửi order vào quầy bar mà không cần chờ nhân viên phục vụ.
* **Mở rộng 3 – Trí tuệ nhân tạo dự báo tồn kho (AI Predictive Inventory):** Dựa trên lịch sử bán hàng trong 6 tháng, ứng dụng thuật toán học máy dự báo lượng tiêu thụ nguyên vật liệu trong các dịp lễ, tết để tự động lập danh sách đề xuất nhập hàng tối ưu.

---

## CHƯƠNG 9: PHỤ LỤC & TÀI LIỆU THAM KHẢO

### 9.1. Bảng phân công nhiệm vụ và tỷ lệ đóng góp của từng thành viên

**Bảng 9.1: Bảng phân công nhiệm vụ và tỷ lệ đóng góp của từng thành viên trong nhóm**

| STT | Họ và tên thành viên | Mã số sinh viên (MSSV) | Vai trò phụ trách | Chi tiết các hạng mục công việc đã thực hiện | Tỷ lệ đóng góp | Chữ ký xác nhận |
| :---: | :--- | :---: | :---: | :--- | :---: | :---: |
| 1 | **Nguyễn Văn Duy** | `521H0001` | **Nhóm trưởng** | - Phụ trách Kiến trúc Dữ liệu, ERD, Design Class Diagram 3 tầng.<br>- Phụ trách Phân hệ Kho nguyên liệu & Thiết kế định mức BOM.<br>- Lập trình nghiệp vụ lõi trừ kho tự động `@Transactional` và cảnh báo tồn đỏ.<br>- Tổng hợp quyển Báo cáo, chuẩn hóa 13 sơ đồ PlantUML và điều phối thuyết trình Q&A. | **34%** | *(Đã ký số)* |
| 2 | **Trần Văn Quý** | `521H0002` | **Thành viên** | - Phụ trách Phân tích yêu cầu chức năng (FR) và lập bảng phân loại MoSCoW.<br>- Thiết kế Biểu đồ Use Case tổng quát và các sơ đồ phân hệ Bán hàng POS.<br>- Lập trình giao diện `banhang.html` (Sơ đồ 10 bàn, Giỏ hàng, Modal thanh toán, In bill nhiệt).<br>- Lập trình Controller và Service cho phân hệ Order & Chiết khấu. | **33%** | *(Đã ký số)* |
| 3 | **Lê Tấn Đạt** | `521H0003` | **Thành viên** | - Phụ trách Khảo sát hiện trạng, nhận diện Stakeholders và Yêu cầu phi chức năng (NFR).<br>- Thiết kế sơ đồ phân hệ Quản lý Nhân sự & Chấm công ca làm việc.<br>- Lập trình giao diện `index.html` và `nhansu.html` (Check-in/Check-out thời gian thực).<br>- Xây dựng bộ dữ liệu mẫu chuẩn `database_mau.sql` và thực hiện bộ kiểm thử 14 Test Cases. | **33%** | *(Đã ký số)* |

*Xác nhận của toàn bộ thành viên nhóm:*  
Tất cả các thành viên trong nhóm đã tham gia đầy đủ, tích cực, phối hợp chặt chẽ và đóng góp đồng đều trong suốt quá trình thực hiện đồ án và cam kết thống nhất với bảng phân công trên.

---

### 9.2. Kịch bản Demo trực tiếp (Live Demo Script) phục vụ bảo vệ đồ án
Kịch bản được thiết kế cho cả 3 thành viên trình bày và demo liên hoàn trong khoảng thời gian **$12\text{--}15\text{ phút}$** trước Hội đồng chấm thi:

* **Phút 00:00 – 02:30 (Duy trình bày):**
  * Giới thiệu thành viên nhóm, bối cảnh đề tài và 3 điểm nghẽn nghiệp vụ thực tế.
  * Trình bày mục tiêu giải pháp phần mềm và kiến trúc 3 tầng All-in-one Spring Boot.
* **Phút 02:30 – 05:00 (Quý trình bày & Demo Bán hàng POS):**
  * Trình bày Biểu đồ Use Case tổng quát và quy trình Bán hàng POS (Hình 3.1 & 3.3).
  * **Thực hiện Demo trên web:**
    1. Mở `http://localhost:8080/index.html`, đăng nhập tài khoản `staff1 / 123456`.
    2. Chỉ rõ hệ thống điều hướng vào `banhang.html`, hai tab Kho và Nhân sự bị ẩn theo phân quyền.
    3. Chọn Bàn 02 (đang xanh), chọn món: *1 Cà phê sữa đá* (nhập ghi chú "ít đường") và *1 Trà đào cam sả*.
    4. Bấm "Lưu đơn" $\rightarrow$ Bàn 02 chuyển sang viền đỏ có khách (Đơn #4).
    5. Bấm "Thanh toán & Xuất hóa đơn", nhập tiền khách đưa $100.000\text{ đ}$, bấm xác nhận $\rightarrow$ Popup hóa đơn nhiệt xuất hiện, tiền thừa $33.000\text{ đ}$, Bàn 02 quay lại màu xanh trống.
* **Phút 05:00 – 08:00 (Duy trình bày & Demo Phân hệ Kho nguyên liệu):**
  * Trình bày Biểu đồ Tuần tự đa tầng luồng trừ kho tự động `@Transactional` (Hình 6.1).
  * **Thực hiện Demo trên web:**
    1. Đăng nhập tài khoản `manager / 123456`, mở trang `http://localhost:8080/kho.html`.
    2. Chỉ cho Hội đồng thấy dòng "Sữa đặc Ngôi Sao" vừa bị trừ ngầm $30\text{ ml}$ (do Quý vừa bán 1 ly cà phê sữa ở bước trước), tồn kho giảm từ $120\text{ ml}$ xuống đúng $90\text{ ml}$.
    3. Do $90\text{ ml} \le 100\text{ ml}$, hệ thống tự động bật nhãn đỏ cảnh báo **`SẮP HẾT HÀNG`** và cập nhật thẻ KPI "Nguyên liệu sắp hết: 1".
    4. Mở modal "Thiết lập định mức công thức" minh họa bảng BOM của món Cà phê sữa đá.
    5. Bấm nút "Nhập kho" tại dòng Sữa đặc, nhập thêm $500\text{ ml}$ và xác nhận $\rightarrow$ Tồn kho cập nhật lên $590\text{ ml}$, nhãn cảnh báo đỏ biến mất, chuyển về nhãn xanh "Đủ hàng".
* **Phút 08:00 – 10:30 (Đạt trình bày & Demo Phân hệ Nhân sự):**
  * Trình bày Yêu cầu phi chức năng (NFR), Biểu đồ Hoạt động chấm công (Hình 3.4) và CSDL ERD 9 bảng.
  * **Thực hiện Demo trên web:**
    1. Mở trang `http://localhost:8080/nhansu.html`.
    2. Chỉ rõ đồng hồ chạy thời gian thực tế của hệ thống.
    3. Chọn nhân viên Lê Tấn Đạt và Ca Chiều, bấm "Check-in Vào Ca".
    4. Hệ thống đối chiếu giờ máy chủ và hiển thị dòng chấm công mới với nhãn xanh `Đúng giờ`.
    5. Minh họa chức năng bấm "Check-out" khi tan ca và mở modal "Thêm Nhân Viên" mới.
* **Phút 10:30 – 12:00 (Duy tổng kết):**
  * Trình bày bảng kết quả kiểm thử tự động ($14/14$ Backend tests và $16/16$ E2E tests đạt $100\%$).
  * Nêu ưu điểm, hạn chế và chiến lược chuyển giao trực tiếp vào sáng Thứ Hai.
  * Cảm ơn Hội đồng và chuyển sang phần điều phối trả lời câu hỏi phản biện (Q&A).

---

### 9.3. Danh mục tài liệu tham khảo
1. **TS. Hồ Thị Linh (2024),** *Bài giảng Phân tích và Thiết kế Yêu cầu (Mã môn: 502050)*, Khoa Công nghệ Thông tin, Trường Đại học Tôn Đức Thắng.
2. **Dennis, A., Wixom, B. H., & Tegarden, D. (2020),** *Systems Analysis and Design: An Object-Oriented Approach with UML (6th Edition)*, John Wiley & Sons.
3. **Larman, C. (2012),** *Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development (3rd Edition)*, Prentice Hall.
4. **Fowler, M. (2003),** *UML Distilled: A Brief Guide to the Standard Object Modeling Language (3rd Edition)*, Addison-Wesley Professional.
5. **Walls, C. (2022),** *Spring in Action (6th Edition)*, Manning Publications.
6. **Bauer, C., King, G., & Gregory, G. (2015),** *Java Persistence with Hibernate (2nd Edition)*, Manning Publications.
7. **Oracle Corporation (2024),** *MySQL 8.0 Reference Manual: InnoDB Locking and Transaction Model*, Oracle Documentation.
8. **Bootstrap Team (2023),** *Bootstrap v5.3 Documentation: Components, Forms & Grid System*, getbootstrap.com.
9. **PlantUML Team (2024),** *PlantUML Language Reference Guide: Standard UML 2.5 Modeling*, plantuml.com.

---
*Báo cáo được biên soạn hoàn chỉnh, kiểm duyệt nội dung và định dạng đúng quy chuẩn Khoa CNTT – ĐH Tôn Đức Thắng ngày 18/11/2026.*
