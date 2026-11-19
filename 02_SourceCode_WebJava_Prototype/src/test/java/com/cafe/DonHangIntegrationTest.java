package com.cafe;

import com.cafe.dto.ThanhToanDTO;
import com.cafe.service.DonHangService;
import com.fasterxml.jackson.databind.JsonNode;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Kiểm thử tích hợp Đơn hàng & POS:
 * Kiểm tra toàn bộ vòng đời đơn hàng, khóa lạc quan @Version, khóa bi quan chống bán khống,
 * tính Idempotency và phân quyền bán hàng.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DonHangIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JdbcTemplate db;

    @Autowired
    private DonHangService service;

    private MockHttpSession staff;
    private MockHttpSession manager;

    @BeforeEach
    void seed() {
        for (String t : List.of("phan_ca", "chi_tiet_hoa_don", "hoa_don", "cong_thuc", "nguyen_lieu", "mon_nuoc", "ban", "cham_cong", "nhan_vien")) {
            db.update("delete from " + t);
        }
        db.update("insert into nhan_vien(ma_nv,ho_ten,ten_dang_nhap,mat_khau,vai_tro,trang_thai) values(1,'Staff','staff','123456','STAFF','DANG_LAM'),(2,'Manager','manager','123456','MANAGER','DANG_LAM')");
        db.update("insert into ban(ma_ban,ten_ban,trang_thai) values(1,'Bàn 01','TRONG'),(2,'Bàn 02','TRONG')");
        db.update("insert into mon_nuoc(ma_mon,ten_mon,danh_muc,don_gia,trang_thai) values(1,'Cà phê sữa','Cà phê',29000,'DANG_BAN'),(2,'Trà đào','Trà',38000,'DANG_BAN'),(3,'Món thiếu công thức','Trà',20000,'DANG_BAN')");
        db.update("insert into nguyen_lieu(ma_nl,ten_nl,don_vi_tinh,so_luong_ton,muc_ton_toi_thieu,don_gia_nhap) values(1,'Sữa đặc','ml',120,100,80),(2,'Trà','g',100,10,20)");
        db.update("insert into cong_thuc(ma_cong_thuc,ma_mon,ma_nl,dinh_muc) values(1,1,1,30),(2,2,2,15)");
        staff = session(1, "STAFF");
        manager = session(2, "MANAGER");
    }

    private MockHttpSession session(int id, String role) {
        MockHttpSession s = new MockHttpSession();
        s.setAttribute("maNV", id);
        s.setAttribute("vaiTro", role);
        return s;
    }

    private JsonNode call(String method, String path, Object body, MockHttpSession s, int status) throws Exception {
        var req = switch (method) {
            case "POST" -> post(path);
            case "PUT" -> put(path);
            default -> get(path);
        };
        if (s != null) req.session(s);
        if (body != null) req.contentType("application/json").content(mapper.writeValueAsBytes(body));
        String text = mvc.perform(req).andExpect(status().is(status)).andReturn().getResponse().getContentAsString();
        return text.isBlank() ? mapper.nullNode() : mapper.readTree(text);
    }

    private Map<String, Object> payload(String key, Integer table, int product, int quantity) {
        Map<String, Object> p = new HashMap<>();
        p.put("yeuCauTao", key);
        p.put("maBan", table);
        p.put("maNV", 2);
        p.put("loaiDon", table == null ? "MANG_DI" : "TAI_BAN");
        p.put("danhSachMon", List.of(Map.of("maMon", product, "soLuong", quantity, "ghiChu", "ít đường")));
        return p;
    }

    private JsonNode create(Integer table, int product, int quantity) throws Exception {
        return call("POST", "/api/order/tao-don", payload(UUID.randomUUID().toString(), table, product, quantity), staff, 201);
    }

    private Map<String, Object> payment(JsonNode h, String key, double cash) {
        return new HashMap<>(Map.of(
                "yeuCauThanhToan", key,
                "phienBan", h.get("phienBan").asLong(),
                "chietKhau", 0,
                "phuongThucTT", "TIEN_MAT",
                "tienKhachDua", cash
        ));
    }

    private double stock() {
        return db.queryForObject("select so_luong_ton from nguyen_lieu where ma_nl=1", Double.class);
    }

    @Test
    void loginSessionDoesNotExposePassword() throws Exception {
        var r = mvc.perform(post("/api/auth/login")
                .contentType("application/json")
                .content("{\"tenDangNhap\":\"staff\",\"matKhau\":\"123456\"}"))
                .andExpect(status().isOk())
                .andReturn();
        assertFalse(mapper.readTree(r.getResponse().getContentAsString()).has("matKhau"));
        assertEquals(1, r.getRequest().getSession().getAttribute("maNV"));
    }

    @Test
    void requiresSessionAndUsesServerIdentity() throws Exception {
        call("GET", "/api/order/danh-sach", null, null, 401);
        JsonNode h = create(1, 1, 1);
        assertEquals(1, h.at("/nhanVien/maNV").asInt());
        assertFalse(h.get("nhanVien").has("matKhau"));
    }

    @Test
    void saveReloadUpdateCancel() throws Exception {
        JsonNode h = create(1, 1, 1);
        assertEquals(29000, h.get("tongTienGoc").asInt());
        assertEquals("CO_KHACH", db.queryForObject("select trang_thai from ban where ma_ban=1", String.class));
        JsonNode loaded = call("GET", "/api/order/theo-ban/1", null, staff, 200);
        assertEquals(h.get("maHoaDon"), loaded.get("maHoaDon"));
        var p = payload(UUID.randomUUID().toString(), 1, 1, 2);
        p.put("phienBan", h.get("phienBan").asLong());
        JsonNode updated = call("PUT", "/api/order/" + h.get("maHoaDon") + "/mon", p, staff, 200);
        assertEquals(58000, updated.get("tongTienGoc").asInt());
        call("PUT", "/api/order/" + h.get("maHoaDon") + "/mon", p, staff, 409);
        call("POST", "/api/order/" + h.get("maHoaDon") + "/huy", Map.of("phienBan", updated.get("phienBan").asLong()), staff, 200);
        assertEquals("TRONG", db.queryForObject("select trang_thai from ban where ma_ban=1", String.class));
        assertEquals(120, stock());
        call("POST", "/api/order/" + h.get("maHoaDon") + "/thanh-toan", payment(updated, UUID.randomUUID().toString(), 100000), staff, 409);
    }

    @Test
    void createRetryReturnsSameOrderAndTableRejectsSecond() throws Exception {
        var p = payload(UUID.randomUUID().toString(), 1, 1, 1);
        var h = call("POST", "/api/order/tao-don", p, staff, 201);
        assertEquals(h.get("maHoaDon"), call("POST", "/api/order/tao-don", p, staff, 201).get("maHoaDon"));
        call("POST", "/api/order/tao-don", payload(UUID.randomUUID().toString(), 1, 2, 1), staff, 409);
        assertEquals(1, db.queryForObject("select count(*) from hoa_don", Integer.class));
    }

    @Test
    void rejectsInvalidAndEmptyOrders() throws Exception {
        call("POST", "/api/order/tao-don", payload(UUID.randomUUID().toString(), 1, 1, 0), staff, 400);
        var p = payload(UUID.randomUUID().toString(), null, 1, 1);
        p.put("loaiDon", "TAI_BAN");
        call("POST", "/api/order/tao-don", p, staff, 400);
        p.put("loaiDon", "MANG_DI");
        p.put("danhSachMon", List.of());
        call("POST", "/api/order/tao-don", p, staff, 400);
        assertEquals(0, db.queryForObject("select count(*) from hoa_don", Integer.class));
    }

    @Test
    void paymentCashStockAndRetryOnce() throws Exception {
        JsonNode h = create(2, 1, 1);
        var pay = payment(h, UUID.randomUUID().toString(), 50000);
        String path = "/api/order/" + h.get("maHoaDon") + "/thanh-toan";
        pay.put("tienKhachDua", 28000);
        call("POST", path, pay, staff, 400);
        assertEquals(120, stock());
        pay.put("tienKhachDua", 50000);
        JsonNode paid = call("POST", path, pay, staff, 200);
        assertEquals("DA_THANH_TOAN", paid.get("trangThai").asText());
        assertEquals(21000, paid.get("tienKhachDua").asDouble() - paid.get("tongTienThanhToan").asDouble());
        assertEquals(90, stock());
        call("POST", path, pay, staff, 200);
        assertEquals(90, stock());
        pay.put("yeuCauThanhToan", UUID.randomUUID().toString());
        call("POST", path, pay, staff, 409);
        call("POST", "/api/order/" + h.get("maHoaDon") + "/huy", Map.of("phienBan", paid.get("phienBan").asLong()), staff, 409);
    }

    @Test
    void insufficientStockRollsBackWholePayment() throws Exception {
        JsonNode h = create(1, 1, 5);
        call("POST", "/api/order/" + h.get("maHoaDon") + "/thanh-toan", payment(h, UUID.randomUUID().toString(), 200000), staff, 400);
        assertEquals(120, stock());
        assertEquals("CHO_THANH_TOAN", db.queryForObject("select trang_thai from hoa_don", String.class));
        assertEquals("CO_KHACH", db.queryForObject("select trang_thai from ban where ma_ban=1", String.class));
    }

    @Test
    void missingRecipeBlocked() throws Exception {
        JsonNode h = create(null, 3, 1);
        call("POST", "/api/order/" + h.get("maHoaDon") + "/thanh-toan", payment(h, UUID.randomUUID().toString(), 50000), staff, 400);
        assertEquals(120, stock());
    }

    @Test
    void discountValidationAndRole() throws Exception {
        JsonNode h = create(null, 1, 1);
        var p = payment(h, UUID.randomUUID().toString(), 50000);
        String path = "/api/order/" + h.get("maHoaDon") + "/thanh-toan";
        p.put("chietKhau", 1000);
        call("POST", path, p, staff, 403);
        p.put("chietKhau", -1);
        call("POST", path, p, manager, 400);
        p.put("chietKhau", 30000);
        call("POST", path, p, manager, 400);
        p.put("chietKhau", 1000);
        assertEquals(28000, call("POST", path, p, manager, 200).get("tongTienThanhToan").asInt());
    }

    @Test
    void transferRequiresConfirmationAndUniqueReference() throws Exception {
        JsonNode h = create(null, 1, 1);
        var p = payment(h, UUID.randomUUID().toString(), 0);
        p.put("phuongThucTT", "CHUYEN_KHOAN");
        p.put("maThamChieu", "TX001");
        String path = "/api/order/" + h.get("maHoaDon") + "/thanh-toan";
        call("POST", path, p, staff, 400);
        p.put("xacNhanChuyenKhoan", true);
        call("POST", path, p, staff, 200);
        JsonNode h2 = create(null, 1, 1);
        p.put("phienBan", h2.get("phienBan").asLong());
        p.put("yeuCauThanhToan", UUID.randomUUID().toString());
        call("POST", "/api/order/" + h2.get("maHoaDon") + "/thanh-toan", p, staff, 409);
        assertEquals(90, stock());
    }

    @Test
    void pricesRemainSnapshots() throws Exception {
        JsonNode h = create(null, 1, 1);
        db.update("update mon_nuoc set don_gia=35000 where ma_mon=1");
        var p = payload(UUID.randomUUID().toString(), null, 1, 2);
        p.put("phienBan", h.get("phienBan").asLong());
        assertEquals(58000, call("PUT", "/api/order/" + h.get("maHoaDon") + "/mon", p, staff, 200).get("tongTienGoc").asInt());
    }

    @Test
    void twoConcurrentPaymentsOnlyDeductOnce() throws Exception {
        JsonNode h = create(null, 1, 1);
        int id = h.get("maHoaDon").asInt();
        long version = h.get("phienBan").asLong();
        ExecutorService pool = Executors.newFixedThreadPool(2);
        CountDownLatch start = new CountDownLatch(1);
        try {
            List<Future<Boolean>> results = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                results.add(pool.submit(() -> {
                    start.await();
                    ThanhToanDTO p = new ThanhToanDTO();
                    p.setPhienBan(version);
                    p.setYeuCauThanhToan(UUID.randomUUID().toString());
                    p.setTienKhachDua(50000d);
                    try {
                        service.thanhToanDonHang(id, p);
                        return true;
                    } catch (org.springframework.web.server.ResponseStatusException e) {
                        return false;
                    }
                }));
            }
            start.countDown();
            int successes = 0;
            for (var r : results) {
                if (r.get(15, TimeUnit.SECONDS)) successes++;
            }
            assertEquals(1, successes);
            assertEquals(90, stock());
        } finally {
            pool.shutdownNow();
        }
    }

    @Test
    void twoOrdersCannotOversellSharedIngredient() throws Exception {
        JsonNode a = create(null, 1, 3);
        JsonNode b = create(null, 1, 3);
        ExecutorService pool = Executors.newFixedThreadPool(2);
        CountDownLatch start = new CountDownLatch(1);
        try {
            List<Future<Boolean>> jobs = new ArrayList<>();
            for (JsonNode h : List.of(a, b)) {
                jobs.add(pool.submit(() -> {
                    start.await();
                    ThanhToanDTO p = new ThanhToanDTO();
                    p.setPhienBan(h.get("phienBan").asLong());
                    p.setYeuCauThanhToan(UUID.randomUUID().toString());
                    p.setTienKhachDua(100000d);
                    try {
                        service.thanhToanDonHang(h.get("maHoaDon").asInt(), p);
                        return true;
                    } catch (IllegalArgumentException e) {
                        return false;
                    }
                }));
            }
            start.countDown();
            int success = 0;
            for (var f : jobs) {
                if (f.get(15, TimeUnit.SECONDS)) success++;
            }
            assertEquals(1, success);
            assertEquals(30, stock());
            assertEquals(1, db.queryForObject("select count(*) from hoa_don where trang_thai='DA_THANH_TOAN'", Integer.class));
        } finally {
            pool.shutdownNow();
        }
    }

    @Test
    void duplicateProductLinesAggregateStock() throws Exception {
        var p = payload(UUID.randomUUID().toString(), null, 1, 1);
        p.put("danhSachMon", List.of(Map.of("maMon", 1, "soLuong", 3), Map.of("maMon", 1, "soLuong", 2)));
        JsonNode h = call("POST", "/api/order/tao-don", p, staff, 201);
        call("POST", "/api/order/" + h.get("maHoaDon") + "/thanh-toan", payment(h, UUID.randomUUID().toString(), 200000), staff, 400);
        assertEquals(120, stock());
    }
}
