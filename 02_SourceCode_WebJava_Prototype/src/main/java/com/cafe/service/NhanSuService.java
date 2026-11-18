package com.cafe.service;

import com.cafe.dto.CheckInDTO;
import com.cafe.dto.LoginDTO;
import com.cafe.dto.PhanCaDTO;
import com.cafe.entity.*;
import com.cafe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class NhanSuService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private CaLamRepository caLamRepository;

    @Autowired
    private ChamCongRepository chamCongRepository;

    @Autowired
    private PhanCaRepository phanCaRepository;

    private ResponseStatusException bad(String message) { return new ResponseStatusException(HttpStatus.BAD_REQUEST, message); }
    private ResponseStatusException conflict(String message) { return new ResponseStatusException(HttpStatus.CONFLICT, message); }

    public NhanVien dangNhap(LoginDTO dto) {
        if (dto == null || dto.getTenDangNhap() == null || dto.getMatKhau() == null) throw bad("Thiếu tên đăng nhập hoặc mật khẩu");
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
        if (nv == null) throw bad("Thiếu dữ liệu nhân viên");
        chuanHoaNhanVien(nv, true);
        if (nhanVienRepository.findByTenDangNhap(nv.getTenDangNhap()).isPresent()) throw conflict("Tên đăng nhập đã tồn tại");
        return nhanVienRepository.save(nv);
    }

    @Transactional
    public NhanVien capNhatNhanVien(Integer maNV, NhanVien changes) {
        NhanVien current = nhanVienRepository.findById(maNV).orElseThrow(() -> bad("Không tìm thấy nhân viên"));
        if (changes == null) throw bad("Thiếu dữ liệu nhân viên");
        current.setHoTen(changes.getHoTen());
        current.setSoDienThoai(changes.getSoDienThoai());
        current.setTenDangNhap(changes.getTenDangNhap());
        if (changes.getMatKhau() != null && !changes.getMatKhau().isBlank()) current.setMatKhau(changes.getMatKhau());
        current.setVaiTro(changes.getVaiTro());
        current.setTrangThai(changes.getTrangThai());
        chuanHoaNhanVien(current, false);
        nhanVienRepository.findByTenDangNhap(current.getTenDangNhap()).filter(other -> !other.getMaNV().equals(maNV)).ifPresent(other -> { throw conflict("Tên đăng nhập đã tồn tại"); });
        return nhanVienRepository.save(current);
    }

    @Transactional
    public NhanVien ngungLamViec(Integer maNV) {
        NhanVien nv = nhanVienRepository.findById(maNV).orElseThrow(() -> bad("Không tìm thấy nhân viên"));
        nv.setTrangThai("DA_NGHI");
        return nhanVienRepository.save(nv);
    }

    public List<CaLam> layDanhSachCaLam() {
        return caLamRepository.findAllByOrderByGioBatDauAsc();
    }

    /**
     * Nghiệp vụ Check-in Chấm công ca làm:
     * Đối chiếu giờ Check-in thực tế với giờ bắt đầu ca:
     * - Trễ <= 15 phút: DUNG_GIO
     * - Trễ > 15 phút: DI_TRE
     */
    @Transactional
    public ChamCong checkIn(Integer maNhanVienDangNhap, CheckInDTO dto) {
        if (dto == null || dto.getMaCa() == null) throw bad("Hãy chọn ca làm việc");
        if (dto.getMaNV() != null && !dto.getMaNV().equals(maNhanVienDangNhap)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Chỉ được chấm công cho chính mình");
        NhanVien nv = nhanVienRepository.findById(maNhanVienDangNhap)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
        if (!"DANG_LAM".equals(nv.getTrangThai())) throw bad("Tài khoản đã ngừng làm việc");
        CaLam cl = caLamRepository.findById(dto.getMaCa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ca làm"));

        LocalDateTime now = LocalDateTime.now();
        if (phanCaRepository.findByNhanVien_MaNVAndCaLam_MaCaAndNgayLam(nv.getMaNV(), cl.getMaCa(), now.toLocalDate()).isEmpty()) throw bad("Bạn chưa được phân ca này hôm nay");
        if (chamCongRepository.findByNhanVien_MaNVAndCaLam_MaCaAndNgayLam(nv.getMaNV(), cl.getMaCa(), now.toLocalDate()).isPresent()) throw conflict("Ca này đã được check-in");
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
    public ChamCong checkOut(Integer maNhanVienDangNhap, boolean quanLy, Integer maChamCong) {
        ChamCong cc = chamCongRepository.findById(maChamCong)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bản ghi chấm công: " + maChamCong));
        if (!quanLy && !cc.getNhanVien().getMaNV().equals(maNhanVienDangNhap)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Chỉ được check-out cho chính mình");
        if (cc.getGioCheckOut() != null) throw conflict("Ca này đã check-out");
        cc.setGioCheckOut(LocalDateTime.now());
        return chamCongRepository.save(cc);
    }

    public List<ChamCong> layLichSuChamCong(Integer maNhanVienDangNhap, boolean quanLy, LocalDate tuNgay, LocalDate denNgay) {
        LocalDate from = tuNgay == null ? LocalDate.now().minusDays(30) : tuNgay;
        LocalDate to = denNgay == null ? LocalDate.now() : denNgay;
        if (from.isAfter(to)) throw bad("Khoảng ngày không hợp lệ");
        return quanLy ? chamCongRepository.findByNgayLamBetweenOrderByNgayLamDescGioCheckInDesc(from, to) : chamCongRepository.findByNhanVien_MaNVAndNgayLamBetweenOrderByNgayLamDescGioCheckInDesc(maNhanVienDangNhap, from, to);
    }

    @Transactional
    public PhanCa phanCa(PhanCaDTO dto) {
        if (dto == null || dto.getMaNV() == null || dto.getMaCa() == null || dto.getNgayLam() == null) throw bad("Cần chọn nhân viên, ca và ngày làm");
        if (dto.getNgayLam().isBefore(LocalDate.now())) throw bad("Không thể phân ca cho ngày đã qua");
        NhanVien nv = nhanVienRepository.findById(dto.getMaNV()).orElseThrow(() -> bad("Không tìm thấy nhân viên"));
        if (!"DANG_LAM".equals(nv.getTrangThai())) throw bad("Không thể phân ca cho nhân viên đã nghỉ");
        CaLam ca = caLamRepository.findById(dto.getMaCa()).orElseThrow(() -> bad("Không tìm thấy ca làm"));
        String note = dto.getGhiChu() == null ? null : dto.getGhiChu().strip();
        if (note != null && note.length() > 255) throw bad("Ghi chú tối đa 255 ký tự");
        PhanCa item = phanCaRepository.findByNhanVien_MaNVAndCaLam_MaCaAndNgayLam(nv.getMaNV(), ca.getMaCa(), dto.getNgayLam()).orElse(new PhanCa());
        item.setNhanVien(nv); item.setCaLam(ca); item.setNgayLam(dto.getNgayLam()); item.setGhiChu(note);
        return phanCaRepository.save(item);
    }

    public List<PhanCa> lichTuan(Integer maNhanVienDangNhap, boolean quanLy, LocalDate ngayTrongTuan) {
        LocalDate base = ngayTrongTuan == null ? LocalDate.now() : ngayTrongTuan;
        LocalDate from = base.with(DayOfWeek.MONDAY), to = from.plusDays(6);
        return quanLy ? phanCaRepository.findByNgayLamBetweenOrderByNgayLamAsc(from, to) : phanCaRepository.findByNhanVien_MaNVAndNgayLamBetweenOrderByNgayLamAsc(maNhanVienDangNhap, from, to);
    }

    @Transactional
    public void xoaPhanCa(Integer maPhanCa) { phanCaRepository.deleteById(maPhanCa); }

    private void chuanHoaNhanVien(NhanVien nv, boolean requirePassword) {
        nv.setHoTen(text(nv.getHoTen(), "Họ tên", 100));
        nv.setTenDangNhap(text(nv.getTenDangNhap(), "Tên đăng nhập", 50));
        if (!nv.getTenDangNhap().matches("[A-Za-z0-9._-]{3,50}")) throw bad("Tên đăng nhập chỉ gồm chữ, số, dấu chấm, gạch dưới hoặc gạch ngang");
        if (requirePassword && (nv.getMatKhau() == null || nv.getMatKhau().length() < 6)) throw bad("Mật khẩu phải có ít nhất 6 ký tự");
        if (nv.getMatKhau() != null && nv.getMatKhau().length() > 255) throw bad("Mật khẩu quá dài");
        String phone = nv.getSoDienThoai() == null ? null : nv.getSoDienThoai().strip();
        if (phone != null && !phone.isEmpty() && !phone.matches("[0-9+ -]{8,15}")) throw bad("Số điện thoại không hợp lệ");
        nv.setSoDienThoai(phone == null || phone.isEmpty() ? null : phone);
        if (!List.of("STAFF", "MANAGER", "ADMIN").contains(nv.getVaiTro())) throw bad("Vai trò không hợp lệ");
        if (nv.getTrangThai() == null) nv.setTrangThai("DANG_LAM");
        if (!List.of("DANG_LAM", "DA_NGHI").contains(nv.getTrangThai())) throw bad("Trạng thái nhân viên không hợp lệ");
    }

    private String text(String value, String label, int max) {
        String result = value == null ? "" : value.strip();
        if (result.isEmpty() || result.length() > max) throw bad(label + " phải có từ 1 đến " + max + " ký tự");
        return result;
    }
}
