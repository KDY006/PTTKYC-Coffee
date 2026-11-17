package com.cafe.service;

import com.cafe.dto.CheckInDTO;
import com.cafe.dto.LoginDTO;
import com.cafe.entity.*;
import com.cafe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class NhanSuService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private CaLamRepository caLamRepository;

    @Autowired
    private ChamCongRepository chamCongRepository;

    public NhanVien dangNhap(LoginDTO dto) {
        return nhanVienRepository.findByTenDangNhapAndMatKhau(dto.getTenDangNhap(), dto.getMatKhau())
                .orElseThrow(() -> new RuntimeException("Sai tên đăng nhập hoặc mật khẩu!"));
    }

    public List<NhanVien> layDanhSachNhanVien() {
        return nhanVienRepository.findAll();
    }

    public List<NhanVien> layTatCaNhanVien() {
        return layDanhSachNhanVien();
    }

    @Transactional
    public NhanVien themNhanVien(NhanVien nv) {
        if (nv.getTrangThai() == null) {
            nv.setTrangThai("DANG_LAM");
        }
        return nhanVienRepository.save(nv);
    }

    @Transactional
    public void xoaNhanVien(Integer maNV) {
        nhanVienRepository.deleteById(maNV);
    }

    public List<CaLam> layDanhSachCaLam() {
        return caLamRepository.findAll();
    }

    /**
     * Nghiệp vụ Check-in Chấm công ca làm:
     * Đối chiếu giờ Check-in thực tế với giờ bắt đầu ca:
     * - Trễ <= 15 phút: DUNG_GIO
     * - Trễ > 15 phút: DI_TRE
     */
    @Transactional
    public ChamCong checkIn(CheckInDTO dto) {
        NhanVien nv = nhanVienRepository.findById(dto.getMaNV())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
        CaLam cl = caLamRepository.findById(dto.getMaCa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ca làm"));

        LocalDateTime now = LocalDateTime.now();
        LocalTime gioVao = now.toLocalTime();
        LocalTime gioBatDauCa = cl.getGioBatDau();

        long diffMinutes = Duration.between(gioBatDauCa, gioVao).toMinutes();
        String trangThai;
        if (diffMinutes <= 15) {
            trangThai = "DUNG_GIO";
        } else {
            trangThai = "DI_TRE";
        }

        ChamCong cc = new ChamCong(null, nv, cl, LocalDate.now(), now, null, trangThai,
                "Vào ca lúc " + gioVao.toString() + (diffMinutes > 15 ? " (Trễ " + diffMinutes + " phút)" : ""));
        return chamCongRepository.save(cc);
    }

    @Transactional
    public ChamCong checkOut(Integer maChamCong) {
        ChamCong cc = chamCongRepository.findById(maChamCong)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bản ghi chấm công: " + maChamCong));
        cc.setGioCheckOut(LocalDateTime.now());
        return chamCongRepository.save(cc);
    }

    public List<ChamCong> layLichSuChamCong() {
        return chamCongRepository.findAll();
    }
}
