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
    @Transactional(rollbackFor = Exception.class)
    public void truKhoTuDong(HoaDon hoaDon) {
        if (hoaDon == null || hoaDon.getDanhSachChiTiet() == null) {
            return;
        }

        System.out.println(">>> [KHO SERVICE] Bat dau tu dong tru kho cho Hoa Don #" + hoaDon.getMaHoaDon());

        for (ChiTietHoaDon chiTiet : hoaDon.getDanhSachChiTiet()) {
            MonNuoc monNuoc = chiTiet.getMonNuoc();
            int soLuongMon = chiTiet.getSoLuong();

            List<CongThuc> danhSachCongThuc = congThucRepository.findByMonNuoc(monNuoc);
            if (danhSachCongThuc.isEmpty()) {
                System.out.println(">>> [CANH BAO] Mon '" + monNuoc.getTenMon() + "' chua duoc thiet lap dinh muc cong thuc!");
                continue;
            }

            for (CongThuc congThuc : danhSachCongThuc) {
                NguyenLieu nguyenLieu = congThuc.getNguyenLieu();
                double dinhMuc = congThuc.getDinhMuc();
                double luongCanTru = dinhMuc * soLuongMon;

                // Kiểm tra điều kiện tồn kho (Invariant Check)
                if (nguyenLieu.getSoLuongTon() < luongCanTru) {
                    throw new RuntimeException("LỖI TRỪ KHO: Nguyên liệu '" + nguyenLieu.getTenNL() 
                            + "' không đủ tồn (Cần: " + luongCanTru + " " + nguyenLieu.getDonViTinh() 
                            + ", Hiện còn: " + nguyenLieu.getSoLuongTon() + " " + nguyenLieu.getDonViTinh() + ")");
                }

                // Thực hiện trừ lùi tồn kho
                double tonKhoMoi = nguyenLieu.getSoLuongTon() - luongCanTru;
                nguyenLieu.setSoLuongTon(tonKhoMoi);
                nguyenLieuRepository.save(nguyenLieu);

                System.out.println(" -> Đã trừ " + luongCanTru + " " + nguyenLieu.getDonViTinh() + " '" + nguyenLieu.getTenNL() 
                        + "' | Tồn mới: " + tonKhoMoi + (nguyenLieu.isSapHet() ? " [CẢNH BÁO SẮP HẾT!]" : ""));
            }
        }
        System.out.println(">>> [KHO SERVICE] Hoan tat tru kho thanh cong cho Hoa Don #" + hoaDon.getMaHoaDon());
    }

    public List<NguyenLieu> layTatCaNguyenLieu() {
        return nguyenLieuRepository.findAllByOrderByMaNLAsc();
    }

    public List<NguyenLieu> layDanhSachSapHet() {
        return nguyenLieuRepository.findNguyenLieuSapHet();
    }

    @Transactional
    public NguyenLieu nhapKho(Integer maNL, Double soLuongNhap) {
        NguyenLieu nl = nguyenLieuRepository.findById(maNL)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nguyên liệu có mã: " + maNL));
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
