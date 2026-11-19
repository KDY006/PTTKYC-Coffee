package com.cafe;

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

import java.time.LocalDate;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NhanSuIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JdbcTemplate db;

    @Autowired
    private com.cafe.service.NhanSuService nhanSuService;

    private MockHttpSession quanLy;
    private MockHttpSession thuNgan;
    private MockHttpSession phucVu;

    @BeforeEach
    void seed() {
        java.time.Instant instant = java.time.LocalDateTime.of(java.time.LocalDate.now(), java.time.LocalTime.of(7, 5))
                .atZone(java.time.ZoneId.systemDefault()).toInstant();
        nhanSuService.setClock(java.time.Clock.fixed(instant, java.time.ZoneId.systemDefault()));

        db.update("delete from lich_su_kho");
        db.update("delete from phan_ca");
        db.update("delete from cham_cong");
        db.update("delete from chi_tiet_hoa_don");
        db.update("delete from hoa_don");
        db.update("delete from ca_lam");
        db.update("delete from nhan_vien");
        db.update("insert into nhan_vien(ma_nv,ho_ten,ten_dang_nhap,mat_khau,vai_tro,trang_thai) values" +
                "(1,'Nguyễn Văn Duy','quanly','123456','QUAN_LY','DANG_LAM')," +
                "(2,'Trần Văn Quý','thungan1','123456','THU_NGAN','DANG_LAM')," +
                "(3,'Lê Tấn Đạt','phucvu1','123456','PHUC_VU','DANG_LAM')");
        db.update("insert into ca_lam(ma_ca,ten_ca,gio_bat_dau,gio_ket_thuc,he_so_luong) values(1,'Sáng','07:00:00','12:00:00',1)");

        quanLy = session(1, "QUAN_LY");
        thuNgan = session(2, "THU_NGAN");
        phucVu = session(3, "PHUC_VU");
    }

    @Test
    void quanLySchedulesAndStaffCanOnlyAttendOwnShiftOnce() throws Exception {
        mvc.perform(post("/api/phan-ca").session(quanLy)
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(Map.of("maNV", 3, "maCa", 1, "ngayLam", LocalDate.now().toString()))))
                .andExpect(status().isCreated());

        mvc.perform(post("/api/cham-cong/check-in").session(phucVu)
                .contentType("application/json")
                .content("{\"maNV\":3,\"maCa\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nhanVien.maNV").value(3));

        mvc.perform(post("/api/cham-cong/check-in").session(phucVu)
                .contentType("application/json")
                .content("{\"maNV\":3,\"maCa\":1}"))
                .andExpect(status().isConflict());

        mvc.perform(get("/api/cham-cong/lich-su").session(phucVu))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nhanVien.maNV").value(3));
    }

    @Test
    void phucVuAndThuNganCannotManageEmployeesOnlyQuanLyCan() throws Exception {
        mvc.perform(get("/api/nhan-su/nhan-vien").session(phucVu)).andExpect(status().isForbidden());
        mvc.perform(get("/api/nhan-su/nhan-vien").session(thuNgan)).andExpect(status().isForbidden());

        String payload = "{\"hoTen\":\"Nhân viên mới\",\"tenDangNhap\":\"staff_new\",\"matKhau\":\"123456\",\"vaiTro\":\"PHUC_VU\",\"trangThai\":\"DANG_LAM\"}";
        mvc.perform(post("/api/nhan-su/nhan-vien").session(thuNgan).contentType("application/json").content(payload)).andExpect(status().isForbidden());
        mvc.perform(post("/api/nhan-su/nhan-vien").session(phucVu).contentType("application/json").content(payload)).andExpect(status().isForbidden());

        mvc.perform(post("/api/nhan-su/nhan-vien").session(quanLy).contentType("application/json").content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.matKhau").doesNotExist());
    }

    @Test
    void staffCannotAccessKhoDashboardOrRestock() throws Exception {
        mvc.perform(get("/api/kho/dashboard").session(phucVu)).andExpect(status().isForbidden());
        mvc.perform(post("/api/kho/nhap-kho").session(phucVu).contentType("application/json").content("{\"maNL\":1,\"soLuongNhap\":10}")).andExpect(status().isForbidden());
        mvc.perform(get("/api/kho/dashboard").session(thuNgan)).andExpect(status().isForbidden());
        mvc.perform(get("/api/kho/dashboard").session(quanLy)).andExpect(status().isOk());
    }

    private MockHttpSession session(int id, String role) {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("maNV", id);
        session.setAttribute("vaiTro", role);
        return session;
    }
}
