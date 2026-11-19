package com.cafe.service;

import com.cafe.dto.CongThucDTO;
import com.cafe.entity.ChiTietHoaDon;
import com.cafe.entity.CongThuc;
import com.cafe.entity.HoaDon;
import com.cafe.entity.MonNuoc;
import com.cafe.entity.NguyenLieu;
import com.cafe.repository.CongThucRepository;
import com.cafe.repository.HoaDonRepository;
import com.cafe.repository.MonNuocRepository;
import com.cafe.repository.NguyenLieuRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class KhoService {

    @PersistenceContext
    private EntityManager entityManager;

    private final NguyenLieuRepository nguyenLieuRepository;
    private final CongThucRepository congThucRepository;
    private final MonNuocRepository monNuocRepository;
    private final HoaDonRepository hoaDonRepository;

    public KhoService(NguyenLieuRepository nguyenLieuRepository,
                      CongThucRepository congThucRepository,
                      MonNuocRepository monNuocRepository,
                      HoaDonRepository hoaDonRepository) {
        this.nguyenLieuRepository = nguyenLieuRepository;
        this.congThucRepository = congThucRepository;
        this.monNuocRepository = monNuocRepository;
        this.hoaDonRepository = hoaDonRepository;
    }

    /**
     * NGHIỆP VỤ LÕI TRỌNG TÂM:
     * Tự động trừ kho ngầm theo định mức công thức khi thanh toán hóa đơn.
     * Bọc trong @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
     * Đảm bảo tính toàn vẹn ACID cùng giao dịch thanh toán hóa đơn.
     * Sắp xếp mã nguyên liệu tăng dần trong TreeMap để triệt tiêu Deadlock 100%.
     */
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    public void truKhoTuDong(HoaDon hoaDon) {
        if (hoaDon == null || hoaDon.getDanhSachChiTiet() == null || hoaDon.getDanhSachChiTiet().isEmpty()) {
            throw new IllegalArgumentException("Hóa đơn không có món để trừ kho");
        }

        // Gộp nhu cầu của mọi dòng, khóa nguyên liệu theo mã tăng dần để tránh Deadlock và mất cập nhật
        Map<Integer, BigDecimal> nhuCau = new TreeMap<>();
        for (ChiTietHoaDon item : hoaDon.getDanhSachChiTiet()) {
            if (item.getSoLuong() == null || item.getSoLuong() <= 0) {
                throw new IllegalArgumentException("Số lượng món không hợp lệ");
            }
            List<CongThuc> recipes = congThucRepository.findByMonNuoc(item.getMonNuoc());
            if (recipes.isEmpty()) {
                throw new IllegalArgumentException("Món chưa có công thức: " + item.getMonNuoc().getTenMon() + ". Hãy thiết lập công thức trước khi thanh toán.");
            }
            for (CongThuc ct : recipes) {
                Double rate = ct.getDinhMuc();
                if (rate == null || !Double.isFinite(rate) || rate <= 0) {
                    throw new IllegalArgumentException("Định mức công thức phải lớn hơn 0");
                }
                BigDecimal lineAmount = BigDecimal.valueOf(rate).multiply(BigDecimal.valueOf(item.getSoLuong()));
                nhuCau.merge(ct.getNguyenLieu().getMaNL(), lineAmount, BigDecimal::add);
            }
        }

        // Lần lượt lấy khóa bi quan (SELECT ... FOR UPDATE) theo thứ tự maNL tăng dần
        Map<Integer, NguyenLieu> locked = new LinkedHashMap<>();
        for (Map.Entry<Integer, BigDecimal> entry : nhuCau.entrySet()) {
            NguyenLieu nl = nguyenLieuRepository.khoaNguyenLieu(entry.getKey())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nguyên liệu"));
            // refresh: công thức có thể đã nạp entity vào persistence context trước lúc lấy khóa
            entityManager.refresh(nl, LockModeType.PESSIMISTIC_WRITE);
            Double ton = nl.getSoLuongTon();
            if (ton == null || !Double.isFinite(ton) || BigDecimal.valueOf(ton).compareTo(entry.getValue()) < 0) {
                throw new IllegalArgumentException("Không đủ nguyên liệu '" + nl.getTenNL() + "': cần " + entry.getValue() + " " + nl.getDonViTinh() + ", còn " + ton);
            }
            locked.put(entry.getKey(), nl);
        }

        // Trừ tồn kho và lưu vào CSDL
        for (Map.Entry<Integer, BigDecimal> entry : nhuCau.entrySet()) {
            NguyenLieu nl = locked.get(entry.getKey());
            nl.setSoLuongTon(BigDecimal.valueOf(nl.getSoLuongTon()).subtract(entry.getValue()).doubleValue());
            nguyenLieuRepository.save(nl);
        }
    }

    public List<NguyenLieu> layTatCaNguyenLieu() {
        return nguyenLieuRepository.findAllByOrderByMaNLAsc();
    }

    public List<NguyenLieu> layDanhSachSapHet() {
        return nguyenLieuRepository.findNguyenLieuSapHet();
    }

    @Transactional
    public NguyenLieu nhapKho(Integer maNL, Double soLuongNhap) {
        NguyenLieu nl = nguyenLieuRepository.khoaNguyenLieu(maNL)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nguyên liệu có mã: " + maNL));
        if (soLuongNhap == null || !Double.isFinite(soLuongNhap) || soLuongNhap <= 0) {
            throw new IllegalArgumentException("Số lượng nhập phải lớn hơn 0");
        }
        nl.setSoLuongTon(nl.getSoLuongTon() + soLuongNhap);
        return nguyenLieuRepository.save(nl);
    }

    @Transactional
    public CongThuc thietLapCongThuc(CongThucDTO dto) {
        MonNuoc mn = monNuocRepository.findById(dto.getMaMon())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy món nước: " + dto.getMaMon()));
        NguyenLieu nl = nguyenLieuRepository.findById(dto.getMaNL())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nguyên liệu: " + dto.getMaNL()));

        Optional<CongThuc> existing = congThucRepository.findByMonNuocAndNguyenLieu(mn, nl);
        CongThuc ct;
        if (existing.isPresent()) {
            ct = existing.get();
            ct.setDinhMuc(dto.getDinhMuc());
            ct.setGhiChu(dto.getGhiChu());
        } else {
            ct = new CongThuc(null, mn, nl, dto.getDinhMuc(), dto.getGhiChu());
        }
        return congThucRepository.save(ct);
    }

    public List<CongThuc> layCongThucTheoMon(Integer maMon) {
        MonNuoc mn = monNuocRepository.findById(maMon)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy món nước"));
        return congThucRepository.findByMonNuoc(mn);
    }

    public Map<String, Object> getDashboardKPI() {
        Map<String, Object> kpi = new HashMap<>();
        List<NguyenLieu> sapHet = layDanhSachSapHet();
        List<HoaDon> hoaDons = hoaDonRepository.findByTrangThai("DA_THANH_TOAN");

        double tongDoanhThu = hoaDons.stream().mapToDouble(HoaDon::getTongTienThanhToan).sum();
        int tongDonHang = hoaDons.size();

        kpi.put("tongDoanhThu", tongDoanhThu);
        kpi.put("tongDonHang", tongDonHang);
        kpi.put("soNguyenLieuSapHet", sapHet.size());
        kpi.put("danhSachSapHet", sapHet);
        return kpi;
    }
}
