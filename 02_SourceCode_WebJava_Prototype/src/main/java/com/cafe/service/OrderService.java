package com.cafe.service;

import com.cafe.dto.OrderDTO;
import com.cafe.dto.OrderItemDTO;
import com.cafe.dto.ThanhToanDTO;
import com.cafe.entity.*;
import com.cafe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    @Autowired
    private BanRepository banRepository;

    @Autowired
    private MonNuocRepository monNuocRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private KhoService khoService;

    public List<Ban> layDanhSachBan() {
        return banRepository.findAllByOrderByMaBanAsc();
    }

    public List<MonNuoc> layDanhSachMonNuoc() {
        return monNuocRepository.findByTrangThai("DANG_BAN");
    }

    public List<HoaDon> layDanhSachHoaDon() {
        return hoaDonRepository.findAllByOrderByMaHoaDonDesc();
    }

    public HoaDon layHoaDonTheoId(Integer maHoaDon) {
        return hoaDonRepository.findById(maHoaDon)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn: " + maHoaDon));
    }

    @Transactional
    public HoaDon taoDonHang(OrderDTO dto) {
        Ban ban = null;
        if ("TAI_BAN".equalsIgnoreCase(dto.getLoaiDon()) && dto.getMaBan() != null) {
            ban = banRepository.findById(dto.getMaBan())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy bàn: " + dto.getMaBan()));
            ban.setTrangThai("CO_KHACH");
            banRepository.save(ban);
        }

        NhanVien nv = nhanVienRepository.findById(dto.getMaNV() != null ? dto.getMaNV() : 1)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

        HoaDon hoaDon = new HoaDon();
        hoaDon.setBan(ban);
        hoaDon.setNhanVien(nv);
        hoaDon.setThoiGianTao(LocalDateTime.now());
        hoaDon.setLoaiDon(dto.getLoaiDon() != null ? dto.getLoaiDon() : "TAI_BAN");
        hoaDon.setTrangThai("CHO_THANH_TOAN");

        double tongTienGoc = 0.0;
        HoaDon savedHoaDon = hoaDonRepository.save(hoaDon);

        if (dto.getDanhSachMon() != null) {
            for (OrderItemDTO item : dto.getDanhSachMon()) {
                MonNuoc mn = monNuocRepository.findById(item.getMaMon())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy món nước: " + item.getMaMon()));
                
                double thanhTien = mn.getDonGia() * item.getSoLuong();
                tongTienGoc += thanhTien;

                ChiTietHoaDon cthd = new ChiTietHoaDon(null, savedHoaDon, mn, item.getSoLuong(), mn.getDonGia(), thanhTien, item.getGhiChu());
                savedHoaDon.addChiTiet(cthd);
                chiTietHoaDonRepository.save(cthd);
            }
        }

        savedHoaDon.setTongTienGoc(tongTienGoc);
        savedHoaDon.setTongTienThanhToan(tongTienGoc);
        return hoaDonRepository.save(savedHoaDon);
    }

    /**
     * Nghiệp vụ Thanh toán Hóa đơn:
     * 1. Cập nhật trạng thái Hóa đơn sang DA_THANH_TOAN
     * 2. Giải phóng Bàn về trạng thái TRONG
     * 3. KÍCH HOẠT DỊCH VỤ TRỪ KHO NGẦM CỦA DUY (khoService.truKhoTuDong)
     */
    @Transactional(rollbackFor = Exception.class)
    public HoaDon thanhToanDonHang(Integer maHoaDon, ThanhToanDTO dto) {
        HoaDon hoaDon = layHoaDonTheoId(maHoaDon);

        if ("DA_THANH_TOAN".equals(hoaDon.getTrangThai())) {
            throw new RuntimeException("Hóa đơn #" + maHoaDon + " đã được thanh toán trước đó!");
        }

        double chietKhau = dto.getChietKhau() != null ? dto.getChietKhau() : 0.0;
        double tongThanhToan = hoaDon.getTongTienGoc() - chietKhau;
        if (tongThanhToan < 0) tongThanhToan = 0.0;

        hoaDon.setChietKhau(chietKhau);
        hoaDon.setTongTienThanhToan(tongThanhToan);
        hoaDon.setPhuongThucTT(dto.getPhuongThucTT() != null ? dto.getPhuongThucTT() : "TIEN_MAT");
        hoaDon.setTrangThai("DA_THANH_TOAN");

        // Cập nhật bàn về TRONG nếu là đơn tại bàn
        if (hoaDon.getBan() != null) {
            Ban ban = hoaDon.getBan();
            ban.setTrangThai("TRONG");
            banRepository.save(ban);
        }

        // Kích hoạt trừ kho ngầm
        khoService.truKhoTuDong(hoaDon);

        return hoaDonRepository.save(hoaDon);
    }

    @Transactional
    public HoaDon huyDonHang(Integer maHoaDon) {
        HoaDon hoaDon = layHoaDonTheoId(maHoaDon);
        hoaDon.setTrangThai("DA_HUY");

        if (hoaDon.getBan() != null) {
            Ban ban = hoaDon.getBan();
            ban.setTrangThai("TRONG");
            banRepository.save(ban);
        }
        return hoaDonRepository.save(hoaDon);
    }
}
