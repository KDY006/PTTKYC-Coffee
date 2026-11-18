package com.cafe;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NhanSuIntegrationTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;
    @Autowired JdbcTemplate db;
    MockHttpSession admin, manager, staff;

    @BeforeEach
    void seed() {
        db.update("delete from phan_ca");
        db.update("delete from cham_cong");
        db.update("delete from chi_tiet_hoa_don");
        db.update("delete from hoa_don");
        db.update("delete from ca_lam");
        db.update("delete from nhan_vien");
        db.update("insert into nhan_vien(ma_nv,ho_ten,ten_dang_nhap,mat_khau,vai_tro,trang_thai) values(1,'Admin','admin','123456','ADMIN','DANG_LAM'),(2,'Manager','manager','123456','MANAGER','DANG_LAM'),(3,'Staff','staff','123456','STAFF','DANG_LAM')");
        db.update("insert into ca_lam(ma_ca,ten_ca,gio_bat_dau,gio_ket_thuc,he_so_luong) values(1,'Sáng','07:00:00','12:00:00',1)");
        admin = session(1, "ADMIN"); manager = session(2, "MANAGER"); staff = session(3, "STAFF");
    }

    @Test
    void managerSchedulesAndStaffCanOnlyAttendOwnShiftOnce() throws Exception {
        mvc.perform(post("/api/phan-ca").session(manager).contentType("application/json").content(mapper.writeValueAsBytes(Map.of("maNV", 3, "maCa", 1, "ngayLam", LocalDate.now().toString())))).andExpect(status().isCreated());
        mvc.perform(post("/api/cham-cong/check-in").session(staff).contentType("application/json").content("{\"maNV\":3,\"maCa\":1}")).andExpect(status().isOk()).andExpect(jsonPath("$.nhanVien.maNV").value(3));
        mvc.perform(post("/api/cham-cong/check-in").session(staff).contentType("application/json").content("{\"maNV\":3,\"maCa\":1}")).andExpect(status().isConflict());
        mvc.perform(get("/api/cham-cong/lich-su").session(staff)).andExpect(status().isOk()).andExpect(jsonPath("$[0].nhanVien.maNV").value(3));
    }

    @Test
    void staffCannotManageEmployeesAndAdminCanCreateOne() throws Exception {
        mvc.perform(get("/api/nhan-su/nhan-vien").session(staff)).andExpect(status().isForbidden());
        String payload = "{\"hoTen\":\"Nhân viên mới\",\"tenDangNhap\":\"staff_new\",\"matKhau\":\"123456\",\"vaiTro\":\"STAFF\",\"trangThai\":\"DANG_LAM\"}";
        mvc.perform(post("/api/nhan-su/nhan-vien").session(manager).contentType("application/json").content(payload)).andExpect(status().isForbidden());
        mvc.perform(post("/api/nhan-su/nhan-vien").session(admin).contentType("application/json").content(payload)).andExpect(status().isCreated()).andExpect(jsonPath("$.matKhau").doesNotExist());
    }

    private MockHttpSession session(int id, String role) {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("maNV", id);
        session.setAttribute("vaiTro", role);
        return session;
    }
}
