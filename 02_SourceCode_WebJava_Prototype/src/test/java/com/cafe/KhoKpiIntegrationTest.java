package com.cafe;

import com.cafe.dto.KiemKeKhoDTO;
import com.cafe.dto.NhapKhoDTO;
import com.cafe.dto.ThanhToanDTO;
import com.cafe.service.DonHangService;
import com.cafe.service.KhoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class KhoKpiIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JdbcTemplate db;

    @Autowired
    private KhoService khoService;

    @Autowired
    private DonHangService donHangService;

    private MockHttpSession waiter;   // PHUC_VU
    private MockHttpSession cashier;  // THU_NGAN
    private MockHttpSession manager;  // QUAN_LY

    @BeforeEach
    void seed() {
        for (String t : List.of("lich_su_kho", "phan_ca", "chi_tiet_hoa_don", "hoa_don", "cong_thuc", "nguyen_lieu", "mon_nuoc", "ban", "cham_cong", "nhan_vien")) {
            db.update("delete from " + t);
        }

        db.update("insert into nhan_vien(ma_nv, ho_ten, ten_dang_nhap, mat_khau, vai_tro, trang_thai) values" +
                "(1, 'Lê Tấn Đạt', 'phucvu', '123456', 'PHUC_VU', 'DANG_LAM')," +
                "(2, 'Trần Văn Quý', 'thungan', '123456', 'THU_NGAN', 'DANG_LAM')," +
                "(3, 'Nguyễn Văn Duy', 'quanly', '123456', 'QUAN_LY', 'DANG_LAM')");

        db.update("insert into ban(ma_ban, ten_ban, vi_tri, so_ghe, trang_thai) values" +
                "(1, 'Bàn 01', 'Tầng 1', 2, 'TRONG')," +
                "(2, 'Bàn 02', 'Tầng 1', 4, 'TRONG')");

        db.update("insert into mon_nuoc(ma_mon, ten_mon, danh_muc, don_gia, trang_thai) values" +
                "(1, 'Cà phê đen đá', 'Cà phê', 25000, 'DANG_BAN')," +
                "(2, 'Cà phê sữa đá', 'Cà phê', 29000, 'DANG_BAN')," +
                "(5, 'Trà đào cam sả', 'Trà', 38000, 'DANG_BAN')");

        db.update("insert into nguyen_lieu(ma_nl, ten_nl, don_vi_tinh, so_luong_ton, muc_ton_toi_thieu, don_gia_nhap) values" +
                "(1, 'Cốt cà phê Robusta', 'ml', 2500, 500, 150)," +
                "(2, 'Sữa đặc Ngôi Sao', 'ml', 120, 100, 80)," +
                "(3, 'Sữa tươi thanh trùng', 'ml', 3000, 500, 35)," +
                "(4, 'Trà đen ủ lạnh', 'ml', 1500, 300, 200)," +
                "(5, 'Siro đào Teisseire', 'ml', 1200, 200, 180)," +
                "(6, 'Đào ngâm đóng lon', 'g', 2000, 300, 120)," +
                "(7, 'Đường nước mía', 'ml', 4000, 500, 25)");

        db.update("insert into cong_thuc(ma_cong_thuc, ma_mon, ma_nl, dinh_muc) values" +
                "(1, 1, 1, 30)," +
                "(2, 1, 7, 10)," +
                "(3, 2, 1, 25)," +
                "(4, 2, 2, 30)," +
                "(5, 2, 7, 5)," +
                "(6, 5, 4, 15)," +
                "(7, 5, 5, 25)," +
                "(8, 5, 6, 40)");

        waiter = session(1, "PHUC_VU");
        cashier = session(2, "THU_NGAN");
        manager = session(3, "QUAN_LY");
    }

    private MockHttpSession session(int id, String role) {
        MockHttpSession s = new MockHttpSession();
        s.setAttribute("maNV", id);
        s.setAttribute("vaiTro", role);
        return s;
    }

    @Test
    @DisplayName("1. Nhập kho cập nhật đơn giá vốn bình quân gia quyền và lưu nhật ký lich_su_kho")
    void restockUpdatesWeightedAverageCostAndLogsHistory() throws Exception {
        // quanly nhập 500 ml Sữa đặc (cũ 120 ml giá 80 đ) với giá mới 90 đ
        // Tồn sau = 120 + 500 = 620 ml
        // Đơn giá bình quân = (120 * 80 + 500 * 90) / 620 = 54600 / 620 = 88.06 đ
        NhapKhoDTO dto = new NhapKhoDTO(2, 500.0, 90.0, "Vinamilk Q7", "Nhập lô mới bổ sung");

        var res = mvc.perform(post("/api/kho/nhap-kho")
                        .session(manager)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode json = mapper.readTree(res.getResponse().getContentAsString());
        assertEquals(620.0, json.get("soLuongTon").asDouble(), 0.001);
        assertEquals(88.06, json.get("donGiaNhap").asDouble(), 0.01);

        // Kiểm tra bảng lich_su_kho
        Integer count = db.queryForObject("select count(*) from lich_su_kho where loai_gd = 'NHAP_KHO' and ma_nl = 2", Integer.class);
        assertEquals(1, count);

        Double thayDoi = db.queryForObject("select so_luong_thay_doi from lich_su_kho where loai_gd = 'NHAP_KHO' and ma_nl = 2", Double.class);
        assertEquals(500.0, thayDoi, 0.001);

        Double thanhTien = db.queryForObject("select thanh_tien from lich_su_kho where loai_gd = 'NHAP_KHO' and ma_nl = 2", Double.class);
        assertEquals(45000.0, thanhTien, 0.01); // 500 * 90
    }

    @Test
    @DisplayName("2. Kiểm kê hao hụt ghi nhận số chênh lệch, cập nhật tồn và kích hoạt cảnh báo sắp hết")
    void inventoryAuditRecordsLossAndTriggersLowStockAlert() throws Exception {
        // Sữa đặc cũ: 120 ml, ngưỡng tối thiểu 100 ml.
        // Kiểm kê thực tế xuống 80 ml kèm lý do "Sữa quá hạn" -> hao hụt -40 ml -> tồn = 80 <= 100 -> sapHet = true
        KiemKeKhoDTO dto = new KiemKeKhoDTO(2, 80.0, "Sữa quá hạn");

        var res = mvc.perform(post("/api/kho/kiem-ke")
                        .session(manager)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode json = mapper.readTree(res.getResponse().getContentAsString());
        assertEquals(80.0, json.get("soLuongTon").asDouble(), 0.001);
        assertTrue(json.get("sapHet").asBoolean());

        // Kiểm tra bảng lich_su_kho
        Double soLuongThayDoi = db.queryForObject("select so_luong_thay_doi from lich_su_kho where loai_gd = 'KIEM_KE_HAO_HUT' and ma_nl = 2", Double.class);
        assertEquals(-40.0, soLuongThayDoi, 0.001);

        Double thanhTien = db.queryForObject("select thanh_tien from lich_su_kho where loai_gd = 'KIEM_KE_HAO_HUT' and ma_nl = 2", Double.class);
        assertEquals(3200.0, thanhTien, 0.01); // 40 * 80đ
    }

    @Test
    @DisplayName("3. Kiểm kê từ chối khi lý do để trống hoặc số lượng thực tế bằng số tồn hiện tại (HTTP 400)")
    void inventoryAuditRejectsEmptyReasonOrSameQuantity() throws Exception {
        // 1. Lý do để trống
        KiemKeKhoDTO dtoEmptyReason = new KiemKeKhoDTO(2, 80.0, "   ");
        mvc.perform(post("/api/kho/kiem-ke")
                        .session(manager)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoEmptyReason)))
                .andExpect(status().isBadRequest());

        // 2. Số lượng không đổi (120 ml == 120 ml)
        KiemKeKhoDTO dtoSameQty = new KiemKeKhoDTO(2, 120.0, "Kiểm tra thấy đủ");
        mvc.perform(post("/api/kho/kiem-ke")
                        .session(manager)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoSameQty)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("4. Thanh toán tự động ghi nhật ký XUAT_BAN_HANG; khi thiếu kho rollback sạch 100%")
    void paymentAutoLogsSaleDeductionAndRollsBackHistoryOnFailure() throws Exception {
        // Tạo hóa đơn cho Bàn 01: 2 ly Cà phê sữa đá (cần 2*30 = 60ml Sữa đặc, tồn hiện tại 120ml)
        db.update("insert into hoa_don(ma_hoa_don, ma_ban, ma_nv, loai_don, tong_tien_goc, chiet_khau, tong_tien_thanh_toan, tien_thua, phien_ban, trang_thai, thoi_gian_tao) values(10, 1, 1, 'TAI_BAN', 58000, 0, 58000, 0, 0, 'CHO_THANH_TOAN', now())");
        db.update("insert into chi_tiet_hoa_don(ma_cthd, ma_hoa_don, ma_mon, so_luong, don_gia_ban, thanh_tien) values(101, 10, 2, 2, 29000, 58000)");

        ThanhToanDTO pay = new ThanhToanDTO();
        pay.setPhuongThucTT("TIEN_MAT");
        pay.setTienKhachDua(60000.0);
        pay.setChietKhau(0.0);

        mvc.perform(post("/api/order/10/thanh-toan")
                        .session(cashier)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pay)))
                .andExpect(status().isOk());

        // Kiểm tra tồn kho Sữa đặc: 120 - 60 = 60 ml
        Double tonSua = db.queryForObject("select so_luong_ton from nguyen_lieu where ma_nl = 2", Double.class);
        assertEquals(60.0, tonSua, 0.001);

        // Kiểm tra lich_su_kho có dòng XUAT_BAN_HANG gắn ma_hoa_don = 10
        Integer countLSK = db.queryForObject("select count(*) from lich_su_kho where loai_gd = 'XUAT_BAN_HANG' and ma_hoa_don = 10 and ma_nl = 2", Integer.class);
        assertEquals(1, countLSK);

        // Trường hợp THIẾU KHO: Tạo hóa đơn Đơn 11 yêu cầu 5 ly Cà phê sữa (cần 5*30 = 150ml Sữa đặc, nhưng chỉ còn 60ml)
        db.update("insert into hoa_don(ma_hoa_don, ma_ban, ma_nv, loai_don, tong_tien_goc, chiet_khau, tong_tien_thanh_toan, tien_thua, phien_ban, trang_thai, thoi_gian_tao) values(11, 2, 1, 'TAI_BAN', 145000, 0, 145000, 0, 0, 'CHO_THANH_TOAN', now())");
        db.update("insert into chi_tiet_hoa_don(ma_cthd, ma_hoa_don, ma_mon, so_luong, don_gia_ban, thanh_tien) values(111, 11, 2, 5, 29000, 145000)");

        ThanhToanDTO payFail = new ThanhToanDTO();
        payFail.setPhuongThucTT("TIEN_MAT");
        payFail.setTienKhachDua(150000.0);

        mvc.perform(post("/api/order/11/thanh-toan")
                        .session(cashier)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payFail)))
                .andExpect(status().isBadRequest());

        // Rollback sạch: không có dòng nào của đơn 11 trong lich_su_kho và tồn kho vẫn giữ nguyên 60 ml
        Integer countLSKFail = db.queryForObject("select count(*) from lich_su_kho where ma_hoa_don = 11", Integer.class);
        assertEquals(0, countLSKFail);

        Double tonSuaSauFail = db.queryForObject("select so_luong_ton from nguyen_lieu where ma_nl = 2", Double.class);
        assertEquals(60.0, tonSuaSauFail, 0.001);
    }

    @Test
    @DisplayName("5. Dashboard KPI lọc đa kỳ, tính đúng Lợi nhuận gộp và Top 5 món bán chạy")
    void dashboardFiltersByTimeRangeAndCalculatesTopSellers() throws Exception {
        // Nạp thêm nguyên liệu còn thiếu cho đủ 13 nguyên liệu chuẩn
        db.update("insert into nguyen_lieu(ma_nl, ten_nl, don_vi_tinh, so_luong_ton, muc_ton_toi_thieu, don_gia_nhap) values" +
                "(8, 'Bột matcha Uji', 'g', 500, 100, 450)," +
                "(9, 'Cốt dừa Bến Tre', 'ml', 1000, 200, 65)," +
                "(10, 'Hạt sen Huế', 'g', 800, 200, 120)," +
                "(11, 'Trà ô long rang', 'ml', 1500, 300, 160)," +
                "(12, 'Vải ngâm', 'g', 1800, 300, 110)," +
                "(13, 'Bột bắp làm bánh', 'g', 2220, 500, 200)");

        // Tạo Đơn 1: 54.000 đ (Tiền mặt, 1 Cà phê đen đá 25k, 1 Cà phê sữa đá 29k)
        db.update("insert into hoa_don(ma_hoa_don, ma_ban, ma_nv, loai_don, tong_tien_goc, chiet_khau, tong_tien_thanh_toan, tien_thua, phien_ban, trang_thai, phuong_thuc_tt, thoi_gian_tao, thoi_gian_thanh_toan) " +
                "values(1, 1, 1, 'TAI_BAN', 54000, 0, 54000, 0, 0, 'DA_THANH_TOAN', 'TIEN_MAT', now(), now())");
        db.update("insert into chi_tiet_hoa_don(ma_cthd, ma_hoa_don, ma_mon, so_luong, don_gia_ban, thanh_tien) values" +
                "(1, 1, 1, 1, 25000, 25000)," +
                "(2, 1, 2, 1, 29000, 29000)");

        // Tạo Đơn 2: 71.000 đ (Chuyển khoản, 2 Trà đào cam sả 76k - chiết khấu 5k = 71k)
        db.update("insert into hoa_don(ma_hoa_don, ma_ban, ma_nv, loai_don, tong_tien_goc, chiet_khau, tong_tien_thanh_toan, tien_thua, phien_ban, trang_thai, phuong_thuc_tt, thoi_gian_tao, thoi_gian_thanh_toan) " +
                "values(2, 2, 1, 'TAI_BAN', 76000, 5000, 71000, 0, 0, 'DA_THANH_TOAN', 'CHUYEN_KHOAN', now(), now())");
        db.update("insert into chi_tiet_hoa_don(ma_cthd, ma_hoa_don, ma_mon, so_luong, don_gia_ban, thanh_tien) values" +
                "(3, 2, 5, 2, 38000, 76000)");

        // Ghi nhật ký kho:
        // Chi phí nhập kho: 1.381.925 đ
        db.update("insert into lich_su_kho(ma_nl, ma_nv, loai_gd, so_luong_thay_doi, ton_truoc, ton_sau, don_gia, thanh_tien, thoi_gian) values" +
                "(1, 3, 'NHAP_KHO', 2555, 0, 2555, 150, 1381925, now())");

        // Giá vốn bán hàng: 35.625 đ
        db.update("insert into lich_su_kho(ma_nl, ma_nv, ma_hoa_don, loai_gd, so_luong_thay_doi, ton_truoc, ton_sau, don_gia, thanh_tien, thoi_gian) values" +
                "(1, 2, 1, 'XUAT_BAN_HANG', -55, 2555, 2500, 150, 35625, now())");

        // Hao hụt: 700 đ
        db.update("insert into lich_su_kho(ma_nl, ma_nv, loai_gd, so_luong_thay_doi, ton_truoc, ton_sau, don_gia, thanh_tien, thoi_gian, ghi_chu) values" +
                "(3, 3, 'KIEM_KE_HAO_HUT', -20, 3020, 3000, 35, 700, now(), 'Đánh bọt sữa')");

        var res = mvc.perform(get("/api/kho/dashboard?phamVi=HOM_NAY")
                        .session(manager))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode json = mapper.readTree(res.getResponse().getContentAsString());

        // Kiểm tra đúng từng số liệu trong đặc tả
        assertEquals(125000.0, json.get("tongDoanhThu").asDouble(), 0.01);
        assertEquals(2, json.get("tongDonHang").asInt());
        assertEquals(5000.0, json.get("tongChietKhau").asDouble(), 0.01);
        assertEquals(54000.0, json.get("doanhThuTienMat").asDouble(), 0.01);
        assertEquals(1, json.get("soDonTienMat").asInt());
        assertEquals(71000.0, json.get("doanhThuChuyenKhoan").asDouble(), 0.01);
        assertEquals(1, json.get("soDonChuyenKhoan").asInt());

        assertEquals(1381925.0, json.get("chiPhiNhapKho").asDouble(), 0.01);
        assertEquals(35625.0, json.get("giaVonBanHang").asDouble(), 0.01);
        assertEquals(700.0, json.get("giaTriHaoHut").asDouble(), 0.01);
        assertEquals(88675.0, json.get("loiNhuanGop").asDouble(), 0.01); // 125.000 - 35.625 - 700 = 88.675

        assertEquals(2613600.0, json.get("tongGiaTriTonKho").asDouble(), 0.01);
        assertEquals(13, json.get("tongNguyenLieu").asInt());

        // Top 1: Trà đào cam sả (2 ly, 76.000 đ)
        JsonNode top = json.get("topMonBanChay");
        assertTrue(top.size() >= 3);
        assertEquals(5, top.get(0).get("maMon").asInt());
        assertEquals("Trà đào cam sả", top.get(0).get("tenMon").asText());
        assertEquals(2, top.get(0).get("tongSoLuong").asInt());
        assertEquals(76000.0, top.get(0).get("tongDoanhThu").asDouble(), 0.01);
    }

    @Test
    @DisplayName("6. Phục vụ và Thu ngân bị chặn HTTP 403 Forbidden trên toàn bộ API /api/kho/**")
    void cashierAndWaiterBlockedFromKhoAndAuditEndpoints() throws Exception {
        // PHUC_VU truy cập
        mvc.perform(get("/api/kho/nguyen-lieu").session(waiter))
                .andExpect(status().isForbidden());
        mvc.perform(get("/api/kho/dashboard").session(waiter))
                .andExpect(status().isForbidden());
        mvc.perform(get("/api/kho/bang-cong-thuc").session(waiter))
                .andExpect(status().isForbidden());
        mvc.perform(get("/api/kho/lich-su").session(waiter))
                .andExpect(status().isForbidden());

        // THU_NGAN truy cập
        mvc.perform(get("/api/kho/nguyen-lieu").session(cashier))
                .andExpect(status().isForbidden());
        mvc.perform(get("/api/kho/dashboard").session(cashier))
                .andExpect(status().isForbidden());
        mvc.perform(get("/api/kho/bang-cong-thuc").session(cashier))
                .andExpect(status().isForbidden());
        mvc.perform(get("/api/kho/lich-su").session(cashier))
                .andExpect(status().isForbidden());

        // Không đăng nhập -> HTTP 401
        mvc.perform(get("/api/kho/nguyen-lieu"))
                .andExpect(status().isUnauthorized());
    }
}
