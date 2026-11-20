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

        db.update("INSERT INTO nhan_vien (ma_nv, ho_ten, so_dien_thoai, ten_dang_nhap, mat_khau, vai_tro, trang_thai) VALUES " +
                "(1, 'Nguyễn Văn Duy (Quản lý)', '0901112222', 'quanly', 'quanly', 'QUAN_LY', 'DANG_LAM'), " +
                "(2, 'Trần Văn Quý (Thu ngân)', '0903334444', 'thungan', 'thungan', 'THU_NGAN', 'DANG_LAM'), " +
                "(3, 'Lê Tấn Đạt (Phục vụ)', '0905556666', 'phucvu', 'phucvu', 'PHUC_VU', 'DANG_LAM')");

        db.update("INSERT INTO ca_lam (ma_ca, ten_ca, gio_bat_dau, gio_ket_thuc, he_so_luong) VALUES " +
                "(1, 'Ca Sáng', '07:00:00', '12:00:00', 1.0), " +
                "(2, 'Ca Chiều', '12:00:00', '17:00:00', 1.0), " +
                "(3, 'Ca Tối', '17:00:00', '22:00:00', 1.2)");

        db.update("INSERT INTO phan_ca (ma_phan_ca, ma_nv, ma_ca, ngay_lam, ghi_chu) VALUES " +
                "(1, 2, 1, ?, 'Thu ngân trực ca sáng'), " +
                "(2, 3, 1, ?, 'Phục vụ bàn ca sáng'), " +
                "(3, 3, 2, ?, 'Phục vụ bàn ca chiều'), " +
                "(4, 2, 3, ?, 'Thu ngân trực ca tối')",
                yesterday, yesterday, yesterday, yesterday);

        db.update("INSERT INTO phan_ca (ma_phan_ca, ma_nv, ma_ca, ngay_lam, ghi_chu) VALUES " +
                "(5, 2, 1, ?, 'Thu ngân trực ca sáng'), " +
                "(6, 3, 1, ?, 'Phục vụ bàn ca sáng'), " +
                "(7, 3, 2, ?, 'Phục vụ bàn ca chiều')",
                today, today, today);

        db.update("INSERT INTO phan_ca (ma_phan_ca, ma_nv, ma_ca, ngay_lam, ghi_chu) VALUES " +
                "(8, 3, 2, ?, 'Phục vụ bàn ca chiều')",
                tomorrow);

        db.update("INSERT INTO cham_cong (ma_cham_cong, ma_phan_ca, ma_nv, ma_ca, ngay_lam, gio_check_in, gio_check_out, so_phut_tre, so_phut_ve_som, so_gio_lam, gio_cong_quy_doi, trang_thai, ghi_chu) VALUES " +
                "(1, 1, 2, 1, ?, ?, ?, 0, 0, 5.0, 5.0, 'DUNG_GIO', 'Hoàn thành ca đúng giờ')",
                yesterday, yesterday.atTime(6, 55), yesterday.atTime(12, 2));

        db.update("INSERT INTO cham_cong (ma_cham_cong, ma_phan_ca, ma_nv, ma_ca, ngay_lam, gio_check_in, gio_check_out, so_phut_tre, so_phut_ve_som, so_gio_lam, gio_cong_quy_doi, trang_thai, ghi_chu) VALUES " +
                "(2, 2, 3, 1, ?, ?, ?, 22, 0, 4.63, 4.63, 'DI_TRE', 'Đi trễ 22 phút')",
                yesterday, yesterday.atTime(7, 22), yesterday.atTime(12, 0));

        db.update("INSERT INTO cham_cong (ma_cham_cong, ma_phan_ca, ma_nv, ma_ca, ngay_lam, gio_check_in, gio_check_out, so_phut_tre, so_phut_ve_som, so_gio_lam, gio_cong_quy_doi, trang_thai, ghi_chu) VALUES " +
                "(3, 3, 3, 2, ?, ?, NULL, 0, 0, 0.0, 0.0, 'QUEN_CHECK_OUT', 'Tự động đánh dấu quên check-out sau cuối ca')",
                yesterday, yesterday.atTime(11, 58));

        db.update("INSERT INTO cham_cong (ma_cham_cong, ma_phan_ca, ma_nv, ma_ca, ngay_lam, gio_check_in, gio_check_out, so_phut_tre, so_phut_ve_som, so_gio_lam, gio_cong_quy_doi, trang_thai, ghi_chu) VALUES " +
                "(4, 4, 2, 3, ?, NULL, NULL, 0, 0, 0.0, 0.0, 'VANG', 'Vắng mặt không check-in')",
                yesterday);

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

    @Test
    void checkInRejectedOutsideWindowAndAcceptedWithin30Minutes() throws Exception {
        setFixedTime(today, LocalTime.of(6, 25));
        mvc.perform(post("/api/cham-cong/check-in").session(phucVu)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(Map.of("maPhanCa", 6))))
                .andExpect(status().isBadRequest());

        setFixedTime(today, LocalTime.of(6, 40));
        mvc.perform(post("/api/cham-cong/check-in").session(phucVu)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(Map.of("maPhanCa", 6))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trangThai").value("DUNG_GIO"))
                .andExpect(jsonPath("$.soPhutTre").value(0))
                .andExpect(jsonPath("$.nhanVien.maNV").value(3));
    }

    @Test
    void checkInLateRecordsLateMinutesAndClampsWorkedHours() throws Exception {
        setFixedTime(today, LocalTime.of(7, 20));
        String response = mvc.perform(post("/api/cham-cong/check-in").session(phucVu)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(Map.of("maPhanCa", 6))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trangThai").value("DI_TRE"))
                .andExpect(jsonPath("$.soPhutTre").value(20))
                .andReturn().getResponse().getContentAsString();

        ChamCong cc = mapper.readValue(response, ChamCong.class);

        setFixedTime(today, LocalTime.of(12, 10));
        mvc.perform(post("/api/cham-cong/check-out/" + cc.getMaChamCong()).session(phucVu))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.soGioLam").value(4.67))
                .andExpect(jsonPath("$.gioCongQuyDoi").value(4.67))
                .andExpect(jsonPath("$.trangThai").value("DI_TRE"));
    }

    @Test
    void checkOutEarlyMarksVeSomAndCombinesWithDiTre() throws Exception {
        setFixedTime(today, LocalTime.of(6, 50));
        String response = mvc.perform(post("/api/cham-cong/check-in").session(phucVu)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(Map.of("maPhanCa", 6))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trangThai").value("DUNG_GIO"))
                .andReturn().getResponse().getContentAsString();

        ChamCong cc = mapper.readValue(response, ChamCong.class);

        setFixedTime(today, LocalTime.of(11, 30));
        mvc.perform(post("/api/cham-cong/check-out/" + cc.getMaChamCong()).session(phucVu))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trangThai").value("VE_SOM"))
                .andExpect(jsonPath("$.soPhutVeSom").value(30))
                .andExpect(jsonPath("$.soGioLam").value(4.50));
    }

    @Test
    void closeExpiredShiftsMarksAbsentAndForgotCheckout() throws Exception {
        setFixedTime(today, LocalTime.of(12, 35));

        mvc.perform(post("/api/cham-cong/chot-ca").session(quanLy))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.soCaDanhDauVang").value(greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$.soCaQuenCheckOut").value(greaterThanOrEqualTo(1)));

        ChamCong vang = chamCongRepository.findByPhanCa_MaPhanCa(6).orElseThrow();
        assertEquals("VANG", vang.getTrangThai());
        assertNull(vang.getGioCheckIn());
        assertEquals(0.0, vang.getSoGioLam());

        ChamCong quen = chamCongRepository.findByPhanCa_MaPhanCa(5).orElseThrow();
        assertEquals("QUEN_CHECK_OUT", quen.getTrangThai());
        assertNotNull(quen.getGioCheckIn());
        assertNull(quen.getGioCheckOut());
    }

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

    @Test
    void shiftSwapUpdatesAssigneeAndDetectsMissingCashier() throws Exception {
        DoiCaDTO swapDto = new DoiCaDTO(2, "Đổi ca do phục vụ có việc gia đình");
        mvc.perform(put("/api/phan-ca/8/doi-ca").session(quanLy)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(swapDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nhanVien.maNV").value(2));

        mvc.perform(get("/api/phan-ca/tuan").session(quanLy))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.maPhanCa == 7)].thieuThuNgan").value(hasItem(true)))
                .andExpect(jsonPath("$[?(@.maPhanCa == 5)].thieuThuNgan").value(hasItem(false)));
    }

    @Test
    void timesheetSummaryMultipliesShiftCoefficientAndIsolatesStaff() throws Exception {
        db.update("UPDATE cham_cong SET gio_check_in = ?, gio_check_out = ?, so_gio_lam = 5.0, gio_cong_quy_doi = 6.0, trang_thai = 'DUNG_GIO' WHERE ma_cham_cong = 4",
                yesterday.atTime(17, 0), yesterday.atTime(22, 0));

        mvc.perform(get("/api/cham-cong/bang-cong")
                .session(quanLy)
                .param("tuNgay", yesterday.toString())
                .param("denNgay", today.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.maNV == 2)].tongGioQuyDoi").value(hasItem(11.0)));

        mvc.perform(get("/api/cham-cong/bang-cong")
                .session(phucVu)
                .param("tuNgay", yesterday.toString())
                .param("denNgay", today.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].maNV").value(3));

        mvc.perform(post("/api/cham-cong/chot-ca").session(phucVu))
                .andExpect(status().isForbidden());

        mvc.perform(put("/api/cham-cong/1/dieu-chinh").session(phucVu)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(new DieuChinhChamCongDTO(null, null, "Hack"))))
                .andExpect(status().isForbidden());
    }
}
