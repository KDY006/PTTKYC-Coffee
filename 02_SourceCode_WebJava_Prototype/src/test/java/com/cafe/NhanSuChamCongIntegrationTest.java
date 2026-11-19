package com.cafe;

import com.cafe.dto.DieuChinhChamCongDTO;
import com.cafe.dto.DoiCaDTO;
import com.cafe.entity.ChamCong;
import com.cafe.repository.ChamCongRepository;
import com.cafe.repository.PhanCaRepository;
import com.cafe.service.NhanSuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.*;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class NhanSuChamCongIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JdbcTemplate db;

    @Autowired
    private NhanSuService nhanSuService;

    @Autowired
    private ChamCongRepository chamCongRepository;

    @Autowired
    private PhanCaRepository phanCaRepository;

    private MockHttpSession quanLy;
    private MockHttpSession thuNgan;
    private MockHttpSession phucVu;

    private final LocalDate today = LocalDate.of(2026, 11, 20);
    private final LocalDate yesterday = today.minusDays(1);
    private final LocalDate tomorrow = today.plusDays(1);

    private void setFixedTime(LocalDate date, LocalTime time) {
        Instant instant = LocalDateTime.of(date, time).atZone(ZoneId.systemDefault()).toInstant();
        nhanSuService.setClock(Clock.fixed(instant, ZoneId.systemDefault()));
    }

    @BeforeEach
    void seed() {
        setFixedTime(today, LocalTime.of(8, 0));

        db.update("DELETE FROM lich_su_kho");
        db.update("DELETE FROM cham_cong");
        db.update("DELETE FROM phan_ca");
        db.update("DELETE FROM chi_tiet_hoa_don");
        db.update("DELETE FROM hoa_don");
        db.update("DELETE FROM ca_lam");
        db.update("DELETE FROM nhan_vien");

        // 1. 3 Nhân viên chuẩn
        db.update("INSERT INTO nhan_vien (ma_nv, ho_ten, so_dien_thoai, ten_dang_nhap, mat_khau, vai_tro, trang_thai) VALUES " +
                "(1, 'Nguyễn Văn Duy (Quản lý)', '0901112222', 'quanly', 'quanly', 'QUAN_LY', 'DANG_LAM'), " +
                "(2, 'Trần Văn Quý (Thu ngân)', '0903334444', 'thungan', 'thungan', 'THU_NGAN', 'DANG_LAM'), " +
                "(3, 'Lê Tấn Đạt (Phục vụ)', '0905556666', 'phucvu', 'phucvu', 'PHUC_VU', 'DANG_LAM')");

        // 2. 3 Ca làm việc chuẩn
        db.update("INSERT INTO ca_lam (ma_ca, ten_ca, gio_bat_dau, gio_ket_thuc, he_so_luong) VALUES " +
                "(1, 'Ca Sáng', '07:00:00', '12:00:00', 1.0), " +
                "(2, 'Ca Chiều', '12:00:00', '17:00:00', 1.0), " +
                "(3, 'Ca Tối', '17:00:00', '22:00:00', 1.2)");

        // 3. Phân ca mẫu (Khớp database_mau.sql)
        // Hôm qua
        db.update("INSERT INTO phan_ca (ma_phan_ca, ma_nv, ma_ca, ngay_lam, ghi_chu) VALUES " +
                "(1, 2, 1, ?, 'Thu ngân trực ca sáng'), " +
                "(2, 3, 1, ?, 'Phục vụ bàn ca sáng'), " +
                "(3, 3, 2, ?, 'Phục vụ bàn ca chiều'), " +
                "(4, 2, 3, ?, 'Thu ngân trực ca tối')",
                yesterday, yesterday, yesterday, yesterday);

        // Hôm nay
        db.update("INSERT INTO phan_ca (ma_phan_ca, ma_nv, ma_ca, ngay_lam, ghi_chu) VALUES " +
                "(5, 2, 1, ?, 'Thu ngân trực ca sáng'), " +
                "(6, 3, 1, ?, 'Phục vụ bàn ca sáng'), " +
                "(7, 3, 2, ?, 'Phục vụ bàn ca chiều')",
                today, today, today);

        // Ngày mai
        db.update("INSERT INTO phan_ca (ma_phan_ca, ma_nv, ma_ca, ngay_lam, ghi_chu) VALUES " +
                "(8, 3, 2, ?, 'Phục vụ bàn ca chiều')",
                tomorrow);

        // 4. Chấm công mẫu (Khớp database_mau.sql)
        // 1: Thu ngân ca sáng hôm qua: DUNG_GIO 5.0h
        db.update("INSERT INTO cham_cong (ma_cham_cong, ma_phan_ca, ma_nv, ma_ca, ngay_lam, gio_check_in, gio_check_out, so_phut_tre, so_phut_ve_som, so_gio_lam, gio_cong_quy_doi, trang_thai, ghi_chu) VALUES " +
                "(1, 1, 2, 1, ?, ?, ?, 0, 0, 5.0, 5.0, 'DUNG_GIO', 'Hoàn thành ca đúng giờ')",
                yesterday, yesterday.atTime(6, 55), yesterday.atTime(12, 2));

        // 2: Phục vụ ca sáng hôm qua: DI_TRE 22p, 4.63h
        db.update("INSERT INTO cham_cong (ma_cham_cong, ma_phan_ca, ma_nv, ma_ca, ngay_lam, gio_check_in, gio_check_out, so_phut_tre, so_phut_ve_som, so_gio_lam, gio_cong_quy_doi, trang_thai, ghi_chu) VALUES " +
                "(2, 2, 3, 1, ?, ?, ?, 22, 0, 4.63, 4.63, 'DI_TRE', 'Đi trễ 22 phút')",
                yesterday, yesterday.atTime(7, 22), yesterday.atTime(12, 0));

        // 3: Phục vụ ca chiều hôm qua: QUEN_CHECK_OUT
        db.update("INSERT INTO cham_cong (ma_cham_cong, ma_phan_ca, ma_nv, ma_ca, ngay_lam, gio_check_in, gio_check_out, so_phut_tre, so_phut_ve_som, so_gio_lam, gio_cong_quy_doi, trang_thai, ghi_chu) VALUES " +
                "(3, 3, 3, 2, ?, ?, NULL, 0, 0, 0.0, 0.0, 'QUEN_CHECK_OUT', 'Tự động đánh dấu quên check-out sau cuối ca')",
                yesterday, yesterday.atTime(11, 58));

        // 4: Thu ngân ca tối hôm qua: VANG
        db.update("INSERT INTO cham_cong (ma_cham_cong, ma_phan_ca, ma_nv, ma_ca, ngay_lam, gio_check_in, gio_check_out, so_phut_tre, so_phut_ve_som, so_gio_lam, gio_cong_quy_doi, trang_thai, ghi_chu) VALUES " +
                "(4, 4, 2, 3, ?, NULL, NULL, 0, 0, 0.0, 0.0, 'VANG', 'Vắng mặt không check-in')",
                yesterday);

        // 5: Thu ngân ca sáng hôm nay: Đang làm
        db.update("INSERT INTO cham_cong (ma_cham_cong, ma_phan_ca, ma_nv, ma_ca, ngay_lam, gio_check_in, gio_check_out, so_phut_tre, so_phut_ve_som, so_gio_lam, gio_cong_quy_doi, trang_thai, ghi_chu) VALUES " +
                "(5, 5, 2, 1, ?, ?, NULL, 0, 0, 0.0, 0.0, 'DUNG_GIO', 'Check-in đúng giờ ca sáng')",
                today, today.atTime(6, 57));

        quanLy = session(1, "QUAN_LY");
        thuNgan = session(2, "THU_NGAN");
        phucVu = session(3, "PHUC_VU");
    }

    private MockHttpSession session(int id, String role) {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("maNV", id);
        session.setAttribute("vaiTro", role);
        return session;
    }

    /**
     * Test 1: checkInRejectedOutsideWindowAndAcceptedWithin30Minutes (BR-CC-02)
     * - 06:25 -> 400 Bad Request
     * - 06:40 -> 200 OK, DUNG_GIO
     */
    @Test
    void checkInRejectedOutsideWindowAndAcceptedWithin30Minutes() throws Exception {
        // Ca Sáng 07:00 - 12:00, cửa sổ check-in: 06:30 -> 12:00
        // Tại 06:25: Chưa mở cửa sổ check-in -> HTTP 400
        setFixedTime(today, LocalTime.of(6, 25));
        mvc.perform(post("/api/cham-cong/check-in").session(phucVu)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(Map.of("maPhanCa", 6))))
                .andExpect(status().isBadRequest());

        // Tại 06:40: Nằm trong cửa sổ [06:30, 12:00) -> HTTP 200 OK, DUNG_GIO
        setFixedTime(today, LocalTime.of(6, 40));
        mvc.perform(post("/api/cham-cong/check-in").session(phucVu)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(Map.of("maPhanCa", 6))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trangThai").value("DUNG_GIO"))
                .andExpect(jsonPath("$.soPhutTre").value(0))
                .andExpect(jsonPath("$.nhanVien.maNV").value(3));
    }

    /**
     * Test 2: checkInLateRecordsLateMinutesAndClampsWorkedHours (BR-CC-03, BR-CC-04)
     * - Check-in 07:20, Check-out 12:10 -> DI_TRE, soPhutTre = 20, soGioLam = 4.67
     */
    @Test
    void checkInLateRecordsLateMinutesAndClampsWorkedHours() throws Exception {
        // Check-in lúc 07:20 (trễ 20 phút so với 07:00)
        setFixedTime(today, LocalTime.of(7, 20));
        String response = mvc.perform(post("/api/cham-cong/check-in").session(phucVu)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(Map.of("maPhanCa", 6))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trangThai").value("DI_TRE"))
                .andExpect(jsonPath("$.soPhutTre").value(20))
                .andReturn().getResponse().getContentAsString();

        ChamCong cc = mapper.readValue(response, ChamCong.class);

        // Check-out lúc 12:10 (sau giờ kết thúc 12:00) -> Clamped tKetThuc = 12:00
        // Worked: 07:20 -> 12:00 = 280 phút = 4.67 giờ
        setFixedTime(today, LocalTime.of(12, 10));
        mvc.perform(post("/api/cham-cong/check-out/" + cc.getMaChamCong()).session(phucVu))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.soGioLam").value(4.67))
                .andExpect(jsonPath("$.gioCongQuyDoi").value(4.67))
                .andExpect(jsonPath("$.trangThai").value("DI_TRE"));
    }

    /**
     * Test 3: checkOutEarlyMarksVeSomAndCombinesWithDiTre (BR-CC-04)
     * - Check-in 06:50, Check-out 11:30 -> VE_SOM, soPhutVeSom = 30, soGioLam = 4.50
     */
    @Test
    void checkOutEarlyMarksVeSomAndCombinesWithDiTre() throws Exception {
        // Check-in lúc 06:50 (sớm hơn 07:00 -> tBatDau = 07:00)
        setFixedTime(today, LocalTime.of(6, 50));
        String response = mvc.perform(post("/api/cham-cong/check-in").session(phucVu)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(Map.of("maPhanCa", 6))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trangThai").value("DUNG_GIO"))
                .andReturn().getResponse().getContentAsString();

        ChamCong cc = mapper.readValue(response, ChamCong.class);

        // Check-out lúc 11:30 (sớm 30 phút so với 12:00 > 15 phút) -> VE_SOM, soPhutVeSom = 30
        // Worked: 07:00 -> 11:30 = 270 phút = 4.50 giờ
        setFixedTime(today, LocalTime.of(11, 30));
        mvc.perform(post("/api/cham-cong/check-out/" + cc.getMaChamCong()).session(phucVu))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trangThai").value("VE_SOM"))
                .andExpect(jsonPath("$.soPhutVeSom").value(30))
                .andExpect(jsonPath("$.soGioLam").value(4.50));
    }

    /**
     * Test 4: closeExpiredShiftsMarksAbsentAndForgotCheckout (BR-CC-05)
     * - Sau cuối ca + 30 phút sinh VANG và QUEN_CHECK_OUT
     */
    @Test
    void closeExpiredShiftsMarksAbsentAndForgotCheckout() throws Exception {
        // Ca Sáng hôm nay kết thúc 12:00, mốc +30m là 12:30.
        // phan_ca 5 (Thu ngân) đã check-in nhưng chưa check-out.
        // phan_ca 6 (Phục vụ) chưa check-in.
        setFixedTime(today, LocalTime.of(12, 35));

        mvc.perform(post("/api/cham-cong/chot-ca").session(quanLy))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.soCaDanhDauVang").value(greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$.soCaQuenCheckOut").value(greaterThanOrEqualTo(1)));

        // Kiểm tra phan_ca 6 được sinh bản ghi VANG
        ChamCong vang = chamCongRepository.findByPhanCa_MaPhanCa(6).orElseThrow();
        assertEquals("VANG", vang.getTrangThai());
        assertNull(vang.getGioCheckIn());
        assertEquals(0.0, vang.getSoGioLam());

        // Kiểm tra phan_ca 5 chuyển sang QUEN_CHECK_OUT
        ChamCong quen = chamCongRepository.findByPhanCa_MaPhanCa(5).orElseThrow();
        assertEquals("QUEN_CHECK_OUT", quen.getTrangThai());
        assertNotNull(quen.getGioCheckIn());
        assertNull(quen.getGioCheckOut());
    }

    /**
     * Test 5: managerAdjustsForgotCheckoutWithAuditTrailAndRecalculates (BR-CC-06)
     * - quanly điều chỉnh bản ghi #3 (phucvu, Ca Chiều hôm qua) sang 17:00 kèm lý do
     * -> lưu nguoiDieuChinh = 1, soGioLam = 5.00, gioCongQuyDoi = 5.00
     */
    @Test
    void managerAdjustsForgotCheckoutWithAuditTrailAndRecalculates() throws Exception {
        DieuChinhChamCongDTO dto = new DieuChinhChamCongDTO();
        dto.setGioCheckOut(yesterday.atTime(17, 0));
        dto.setLyDoDieuChinh("Nhân viên quên quẹt thẻ ra ca lúc giao ban");

        mvc.perform(put("/api/cham-cong/3/dieu-chinh").session(quanLy)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nguoiDieuChinh.maNV").value(1))
                .andExpect(jsonPath("$.soGioLam").value(5.00))
                .andExpect(jsonPath("$.gioCongQuyDoi").value(5.00))
                .andExpect(jsonPath("$.trangThai").value("DUNG_GIO"))
                .andExpect(jsonPath("$.lyDoDieuChinh").value("Nhân viên quên quẹt thẻ ra ca lúc giao ban"));
    }

    /**
     * Test 6: shiftSwapUpdatesAssigneeAndDetectsMissingCashier (BR-CC-07)
     * - Đổi ca ngày mai (ma_phan_ca = 8) từ phucvu sang thungan -> 200 OK
     * - Lịch tuần báo thieuThuNgan = true tại Ca Chiều hôm nay
     */
    @Test
    void shiftSwapUpdatesAssigneeAndDetectsMissingCashier() throws Exception {
        // Đổi ca ngày mai (ma_phan_ca = 8) sang ma_nv = 2 (thungan)
        DoiCaDTO swapDto = new DoiCaDTO(2, "Đổi ca do phục vụ có việc gia đình");
        mvc.perform(put("/api/phan-ca/8/doi-ca").session(quanLy)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(swapDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nhanVien.maNV").value(2));

        // Lịch tuần báo thieuThuNgan = true tại Ca Chiều hôm nay (ma_phan_ca = 7 chỉ có phucvu)
        mvc.perform(get("/api/phan-ca/tuan").session(quanLy))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.maPhanCa == 7)].thieuThuNgan").value(hasItem(true)))
                .andExpect(jsonPath("$[?(@.maPhanCa == 5)].thieuThuNgan").value(hasItem(false)));
    }

    /**
     * Test 7: timesheetSummaryMultipliesShiftCoefficientAndIsolatesStaff
     * - Ca Tối (he_so_luong = 1.2) làm 5.0 giờ tính đúng 6.0 giờ công
     * - phucvu gọi /bang-cong chỉ thấy dòng của chính mình và nhận 403 khi gọi /chot-ca hay /dieu-chinh
     */
    @Test
    void timesheetSummaryMultipliesShiftCoefficientAndIsolatesStaff() throws Exception {
        // Cập nhật bản ghi #4 (Ca Tối hôm qua của Thu ngân) hoàn thành đủ 5 giờ: 17:00 -> 22:00 (he_so_luong = 1.2)
        db.update("UPDATE cham_cong SET gio_check_in = ?, gio_check_out = ?, so_gio_lam = 5.0, gio_cong_quy_doi = 6.0, trang_thai = 'DUNG_GIO' WHERE ma_cham_cong = 4",
                yesterday.atTime(17, 0), yesterday.atTime(22, 0));

        // Quản lý xem bảng công: Thấy Thu ngân có 6.0 giờ công từ ca tối + 5.0 từ ca sáng = 11.0
        mvc.perform(get("/api/cham-cong/bang-cong")
                .session(quanLy)
                .param("tuNgay", yesterday.toString())
                .param("denNgay", today.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.maNV == 2)].tongGioQuyDoi").value(hasItem(11.0)));

        // Phục vụ xem bảng công: Chỉ thấy dòng của chính mình (size = 1, maNV = 3)
        mvc.perform(get("/api/cham-cong/bang-cong")
                .session(phucVu)
                .param("tuNgay", yesterday.toString())
                .param("denNgay", today.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].maNV").value(3));

        // Phục vụ cố gọi các endpoint quản trị: nhận HTTP 403 Forbidden
        mvc.perform(post("/api/cham-cong/chot-ca").session(phucVu))
                .andExpect(status().isForbidden());

        mvc.perform(put("/api/cham-cong/1/dieu-chinh").session(phucVu)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(new DieuChinhChamCongDTO(null, null, "Hack"))))
                .andExpect(status().isForbidden());
    }
}
