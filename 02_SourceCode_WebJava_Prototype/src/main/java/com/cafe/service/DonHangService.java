package com.cafe.service;

import com.cafe.dto.ChiTietDonHangDTO;
import com.cafe.dto.DonHangDTO;
import com.cafe.dto.ThanhToanDTO;
import com.cafe.entity.Ban;
import com.cafe.entity.ChiTietHoaDon;
import com.cafe.entity.HoaDon;
import com.cafe.entity.MonNuoc;
import com.cafe.entity.NhanVien;
import com.cafe.repository.BanRepository;
import com.cafe.repository.HoaDonRepository;
import com.cafe.repository.MonNuocRepository;
import com.cafe.repository.NhanVienRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Nghiệp vụ Bán hàng & Quản lý Đơn hàng (POS):
 * - Đơn giá và tổng tiền được chụp ảnh và tính toán tại máy chủ.
 * - Lưu đơn (Pha 1) và thanh toán (Pha 2) riêng biệt.
 * - Sử dụng giao dịch nguyên tử @Transactional và cơ chế khóa chống Deadlock.
 */
@Service
@Transactional(readOnly = true)
public class DonHangService {

    public static final String OPEN = "CHO_THANH_TOAN";

    private final HoaDonRepository hoaDonRepository;
    private final BanRepository banRepository;
    private final MonNuocRepository monNuocRepository;
    private final NhanVienRepository nhanVienRepository;
    private final KhoService khoService;

    public DonHangService(HoaDonRepository hoaDonRepository,
                          BanRepository banRepository,
                          MonNuocRepository monNuocRepository,
                          NhanVienRepository nhanVienRepository,
                          KhoService khoService) {
        this.hoaDonRepository = hoaDonRepository;
        this.banRepository = banRepository;
        this.monNuocRepository = monNuocRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.khoService = khoService;
    }

    private ResponseStatusException bad(String message) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }

    private ResponseStatusException conflict(String message) {
        return new ResponseStatusException(HttpStatus.CONFLICT, message);
    }

    public List<Ban> layDanhSachBan() {
        return banRepository.findAllByOrderByMaBanAsc();
    }

    public List<MonNuoc> layDanhSachMonNuoc() {
        return layDanhSachMonNuoc(null, null);
    }

    public List<MonNuoc> layDanhSachMonNuoc(String danhMuc, String q) {
        String search = (q == null) ? "" : q.strip().toLowerCase(Locale.ROOT);
        return monNuocRepository.findByTrangThai("DANG_BAN").stream()
                .filter(m -> danhMuc == null || danhMuc.isBlank() || danhMuc.equals(m.getDanhMuc()))
                .filter(m -> m.getTenMon().toLowerCase(Locale.ROOT).contains(search))
                .toList();
    }

    public List<HoaDon> layDanhSachHoaDon() {
        return hoaDonRepository.findAllByOrderByMaHoaDonDesc();
    }

    public HoaDon layHoaDonTheoId(Integer id) {
        HoaDon h = hoaDonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy hóa đơn"));
        h.getDanhSachChiTiet().size(); // Trigger Lazy Load
        return h;
    }

    public HoaDon layDonTheoBan(Integer id) {
        Ban b = banRepository.findById(id).orElseThrow(() -> bad("Không tìm thấy bàn"));
        return hoaDonRepository.findFirstByBanAndTrangThaiOrderByMaHoaDonDesc(b, OPEN).orElse(null);
    }

    public HoaDon layDonTheoYeuCau(String request) {
        return hoaDonRepository.findByYeuCauTao(validateKey(request)).orElse(null);
    }

    private Ban khoaBan(Integer id) {
        return banRepository.khoaBan(id).orElseThrow(() -> bad("Không tìm thấy bàn"));
    }

    private HoaDon khoaDon(Integer id) {
        return hoaDonRepository.khoaHoaDon(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy hóa đơn"));
    }

    private void kiemTraDonMo(HoaDon h) {
        if (!OPEN.equals(h.getTrangThai())) {
            throw conflict("Chỉ được thao tác đơn đang chờ thanh toán");
        }
    }

    private void kiemTraPhien(HoaDon h, Long version) {
        if (version == null || !Objects.equals(h.getPhienBan(), version)) {
            throw conflict("Đơn đã thay đổi ở phiên khác. Hãy tải lại đơn trước khi tiếp tục.");
        }
    }

    private String validateKey(String key) {
        try {
            return UUID.fromString(key).toString();
        } catch (Exception e) {
            throw bad("Thiếu mã yêu cầu hợp lệ; tải lại giao diện");
        }
    }

    /** CSDL dùng DOUBLE: tính toán trung gian bằng BigDecimal, chỉ chấp nhận đồng nguyên. */
    private BigDecimal validateTien(Double value, String label) {
        if (value == null || !Double.isFinite(value) || value < 0 || value > 1_000_000_000_000d || value != Math.rint(value)) {
            throw bad(label + " phải là số đồng nguyên không âm (tối đa 1.000 tỷ)");
        }
        return BigDecimal.valueOf(value);
    }

    private List<ChiTietHoaDon> chuanBiMon(DonHangDTO dto, HoaDon owner) {
        if (dto.getDanhSachMon() == null || dto.getDanhSachMon().isEmpty() || dto.getDanhSachMon().size() > 100) {
            throw bad("Đơn phải có từ 1 đến 100 dòng món");
        }
        List<ChiTietHoaDon> out = new ArrayList<>();
        for (ChiTietDonHangDTO item : dto.getDanhSachMon()) {
            if (item == null || item.getMaMon() == null || item.getSoLuong() == null || item.getSoLuong() < 1 || item.getSoLuong() > 99) {
                throw bad("Món không hợp lệ hoặc số lượng ngoài 1–99");
            }
            String note = (item.getGhiChu() == null) ? "" : item.getGhiChu().strip();
            if (note.length() > 255) {
                throw bad("Ghi chú tối đa 255 ký tự");
            }
            MonNuoc m = monNuocRepository.findById(item.getMaMon())
                    .orElseThrow(() -> bad("Không tìm thấy món"));
            if (!"DANG_BAN".equals(m.getTrangThai())) {
                throw bad("Món đã ngừng bán: " + m.getTenMon());
            }

            // Giữ đơn giá snapshot đã chụp của món hiện có, kể cả khi danh mục đổi giá sau đó
            Double price = owner.getDanhSachChiTiet().stream()
                    .filter(l -> l.getMonNuoc().getMaMon().equals(m.getMaMon()))
                    .map(ChiTietHoaDon::getDonGiaBan)
                    .findFirst()
                    .orElse(m.getDonGia());

            BigDecimal amount = validateTien(price, "Đơn giá").multiply(BigDecimal.valueOf(item.getSoLuong()));
            out.add(new ChiTietHoaDon(null, owner, m, item.getSoLuong(), price, amount.doubleValue(), note));
        }
        return out;
    }

    private void thayMon(HoaDon h, List<ChiTietHoaDon> lines) {
        h.getDanhSachChiTiet().clear(); // orphanRemoval tự động xóa chi tiết cũ trong cùng transaction
        BigDecimal total = BigDecimal.ZERO;
        for (ChiTietHoaDon line : lines) {
            h.addChiTiet(line);
            total = total.add(BigDecimal.valueOf(line.getThanhTien()));
        }
        if (total.compareTo(BigDecimal.valueOf(1_000_000_000_000L)) > 0) {
            throw bad("Tổng đơn vượt giới hạn");
        }
        h.setTongTienGoc(total.doubleValue());
        h.setChietKhau(0d);
        h.setTongTienThanhToan(total.doubleValue());
    }

    @Transactional(rollbackFor = Exception.class)
    public HoaDon taoDonHang(DonHangDTO dto) {
        String request = validateKey(dto.getYeuCauTao());
        Optional<HoaDon> previous = hoaDonRepository.findByYeuCauTao(request);
        if (previous.isPresent()) {
            if (!Objects.equals(previous.get().getNhanVien().getMaNV(), dto.getMaNV())) {
                throw conflict("Mã yêu cầu thuộc nhân viên khác");
            }
            return previous.get(); // Idempotent: Mất kết nối mạng rồi gửi lại -> trả đơn đã tạo
        }

        if (!"TAI_BAN".equals(dto.getLoaiDon()) && !"MANG_DI".equals(dto.getLoaiDon())) {
            throw bad("Loại đơn không hợp lệ");
        }

        Ban ban = null;
        if ("TAI_BAN".equals(dto.getLoaiDon())) {
            if (dto.getMaBan() == null) {
                throw bad("Đơn tại bàn phải chọn bàn");
            }
            ban = khoaBan(dto.getMaBan());
            if (hoaDonRepository.findFirstByBanAndTrangThaiOrderByMaHoaDonDesc(ban, OPEN).isPresent()) {
                throw conflict("Bàn đã có đơn mở. Hãy mở lại đơn của bàn.");
            }
        } else if (dto.getMaBan() != null) {
            throw bad("Đơn mang đi không gắn bàn");
        }

        NhanVien nv = nhanVienRepository.findById(dto.getMaNV())
                .orElseThrow(() -> bad("Không tìm thấy nhân viên"));
        if (!"DANG_LAM".equals(nv.getTrangThai())) {
            throw bad("Nhân viên đã ngừng làm việc");
        }

        HoaDon h = new HoaDon();
        h.setBan(ban);
        h.setNhanVien(nv);
        h.setLoaiDon(dto.getLoaiDon());
        h.setTrangThai(OPEN);
        h.setYeuCauTao(request);
        h.setThoiGianTao(LocalDateTime.now());
        thayMon(h, chuanBiMon(dto, h));

        if (ban != null) {
            ban.setTrangThai("CO_KHACH");
            banRepository.save(ban);
        }
        return hoaDonRepository.saveAndFlush(h);
    }

    @Transactional(rollbackFor = Exception.class)
    public HoaDon capNhatDon(Integer id, DonHangDTO dto) {
        HoaDon h = khoaDon(id);
        kiemTraDonMo(h);
        kiemTraPhien(h, dto.getPhienBan());

        if (!Objects.equals(h.getLoaiDon(), dto.getLoaiDon()) ||
                !Objects.equals(h.getBan() == null ? null : h.getBan().getMaBan(), dto.getMaBan())) {
            throw bad("Không đổi bàn/loại đơn khi cập nhật món");
        }

        thayMon(h, chuanBiMon(dto, h));
        return hoaDonRepository.saveAndFlush(h);
    }

    @Transactional(rollbackFor = Exception.class)
    public HoaDon thanhToanDonHang(Integer id, ThanhToanDTO dto) {
        HoaDon h = khoaDon(id);
        String request = validateKey(dto.getYeuCauThanhToan());

        // Idempotent: nếu đơn đã thanh toán với cùng request key thì trả về ngay, không trừ kho lần 2
        if ("DA_THANH_TOAN".equals(h.getTrangThai()) && request.equals(h.getYeuCauThanhToan())) {
            return h;
        }

        kiemTraDonMo(h);
        kiemTraPhien(h, dto.getPhienBan());

        if (h.getDanhSachChiTiet().isEmpty()) {
            throw bad("Không thanh toán đơn trống");
        }

        BigDecimal discount = validateTien(dto.getChietKhau() == null ? 0d : dto.getChietKhau(), "Chiết khấu");
        BigDecimal total = validateTien(h.getTongTienGoc(), "Tổng tiền");
        if (discount.compareTo(total) > 0) {
            throw bad("Chiết khấu không được lớn hơn tổng đơn");
        }
        total = total.subtract(discount);

        String method = dto.getPhuongThucTT();
        double received;
        if ("TIEN_MAT".equals(method)) {
            BigDecimal cash = validateTien(dto.getTienKhachDua(), "Tiền khách đưa");
            if (cash.compareTo(total) < 0) {
                throw bad("Tiền khách đưa chưa đủ");
            }
            received = cash.doubleValue();
        } else if ("CHUYEN_KHOAN".equals(method)) {
            String ref = (dto.getMaThamChieu() == null) ? "" : dto.getMaThamChieu().strip();
            if (!Boolean.TRUE.equals(dto.getXacNhanChuyenKhoan()) || ref.isEmpty() || ref.length() > 100) {
                throw bad("Cần kiểm tra tiền vào tài khoản và nhập mã tham chiếu (tối đa 100 ký tự)");
            }
            if (hoaDonRepository.existsByMaThamChieu(ref)) {
                throw conflict("Mã tham chiếu chuyển khoản đã dùng");
            }
            h.setMaThamChieu(ref);
            received = total.doubleValue();
        } else {
            throw bad("Phương thức thanh toán không hợp lệ");
        }

        // Cùng transaction: lỗi trừ kho khiến toàn bộ thay đổi hóa đơn/bàn rollback
        khoService.truKhoTuDong(h);

        h.setChietKhau(discount.doubleValue());
        h.setTongTienThanhToan(total.doubleValue());
        h.setPhuongThucTT(method);
        h.setTienKhachDua(received);
        h.setTrangThai("DA_THANH_TOAN");
        h.setThoiGianThanhToan(LocalDateTime.now());
        h.setYeuCauThanhToan(request);

        traBan(h);
        return hoaDonRepository.saveAndFlush(h);
    }

    private void traBan(HoaDon h) {
        if (h.getBan() != null) {
            Ban b = khoaBan(h.getBan().getMaBan());
            boolean coDonKhac = hoaDonRepository.findFirstByBanAndTrangThaiOrderByMaHoaDonDesc(b, OPEN).isPresent();
            b.setTrangThai(coDonKhac ? "CO_KHACH" : "TRONG");
            banRepository.save(b);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public HoaDon huyDonHang(Integer id, Long version) {
        HoaDon h = khoaDon(id);
        if ("DA_HUY".equals(h.getTrangThai())) {
            return h;
        }
        kiemTraDonMo(h);
        kiemTraPhien(h, version);
        h.setTrangThai("DA_HUY");
        traBan(h);
        return hoaDonRepository.saveAndFlush(h);
    }
}
