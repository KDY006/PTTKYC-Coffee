package com.cafe.service;

import com.cafe.dto.CongThucDTO;
import com.cafe.entity.*;
import com.cafe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class KhoService {
    @jakarta.persistence.PersistenceContext
    private jakarta.persistence.EntityManager entityManager;

    @Autowired
    private NguyenLieuRepository nguyenLieuRepository;

    @Autowired
    private CongThucRepository congThucRepository;

    @Autowired
    private MonNuocRepository monNuocRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    /**
     * NGHIỆP VỤ LÕI TRỌNG TÂM CỦA DUY:
     * Tự động trừ kho ngầm theo định mức công thức khi thanh toán hóa đơn.
     * Bọc trong @Transactional(rollbackFor = Exception.class) để đảm bảo tính toàn vẹn ACID.
     */
    @Transactional(propagation=org.springframework.transaction.annotation.Propagation.MANDATORY, rollbackFor=Exception.class)
    public void truKhoTuDong(HoaDon hoaDon) {
        if(hoaDon==null||hoaDon.getDanhSachChiTiet()==null||hoaDon.getDanhSachChiTiet().isEmpty())
            throw new IllegalArgumentException("Hóa đơn không có món để trừ kho");
        // Gộp nhu cầu của mọi dòng, khóa nguyên liệu theo mã tăng dần để tránh mất cập nhật.
        Map<Integer, java.math.BigDecimal> nhuCau=new TreeMap<>();
        for(ChiTietHoaDon item:hoaDon.getDanhSachChiTiet()) {
            if(item.getSoLuong()==null||item.getSoLuong()<=0)throw new IllegalArgumentException("Số lượng món không hợp lệ");
            List<CongThuc> recipes=congThucRepository.findByMonNuoc(item.getMonNuoc());
            if(recipes.isEmpty())throw new IllegalArgumentException("Món chưa có công thức: "+item.getMonNuoc().getTenMon()+". Hãy thiết lập công thức trước khi thanh toán.");
            for(CongThuc ct:recipes) {
                Double rate=ct.getDinhMuc();
                if(rate==null||!Double.isFinite(rate)||rate<=0)throw new IllegalArgumentException("Định mức công thức phải lớn hơn 0");
                nhuCau.merge(ct.getNguyenLieu().getMaNL(),java.math.BigDecimal.valueOf(rate).multiply(java.math.BigDecimal.valueOf(item.getSoLuong())),java.math.BigDecimal::add);
            }
        }
        Map<Integer,NguyenLieu> locked=new LinkedHashMap<>();
        for(Map.Entry<Integer,java.math.BigDecimal> entry:nhuCau.entrySet()) {
            NguyenLieu nl=nguyenLieuRepository.khoaNguyenLieu(entry.getKey()).orElseThrow(()->new IllegalArgumentException("Không tìm thấy nguyên liệu"));
            // refresh: công thức có thể đã nạp entity vào persistence context trước lúc lấy khóa.
            entityManager.refresh(nl,jakarta.persistence.LockModeType.PESSIMISTIC_WRITE);
            Double ton=nl.getSoLuongTon();
            if(ton==null||!Double.isFinite(ton)||java.math.BigDecimal.valueOf(ton).compareTo(entry.getValue())<0)
                throw new IllegalArgumentException("Không đủ nguyên liệu '"+nl.getTenNL()+"': cần "+entry.getValue()+" "+nl.getDonViTinh()+", còn "+ton);
            locked.put(entry.getKey(),nl);
        }
        for(Map.Entry<Integer,java.math.BigDecimal> entry:nhuCau.entrySet()) {
            NguyenLieu nl=locked.get(entry.getKey());
            nl.setSoLuongTon(java.math.BigDecimal.valueOf(nl.getSoLuongTon()).subtract(entry.getValue()).doubleValue());
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
        if(soLuongNhap==null||!Double.isFinite(soLuongNhap)||soLuongNhap<=0)throw new IllegalArgumentException("Số lượng nhập phải lớn hơn 0");
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
