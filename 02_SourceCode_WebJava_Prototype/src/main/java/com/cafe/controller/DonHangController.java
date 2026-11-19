package com.cafe.controller;

import com.cafe.dto.DonHangDTO;
import com.cafe.dto.ThanhToanDTO;
import com.cafe.entity.HoaDon;
import com.cafe.service.DonHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/api/order", "/api/don-hang"})
public class DonHangController {

    private final DonHangService donHangService;
    private final PhanQuyenSession access;

    public DonHangController(DonHangService donHangService, PhanQuyenSession access) {
        this.donHangService = donHangService;
        this.access = access;
    }

    private Integer requireStaffOrHigher(HttpSession s) {
        return access.requireAny(s, "STAFF", "MANAGER", "ADMIN");
    }

    @GetMapping("/danh-sach")
    public List<HoaDon> layDanhSach(HttpSession s) {
        requireStaffOrHigher(s);
        return donHangService.layDanhSachHoaDon();
    }

    @GetMapping("/{id}")
    public HoaDon layTheoId(@PathVariable Integer id, HttpSession s) {
        requireStaffOrHigher(s);
        return donHangService.layHoaDonTheoId(id);
    }

    @GetMapping("/theo-ban/{id}")
    public ResponseEntity<?> layTheoBan(@PathVariable Integer id, HttpSession s) {
        requireStaffOrHigher(s);
        HoaDon h = donHangService.layDonTheoBan(id);
        return h == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(h);
    }

    @GetMapping("/theo-yeu-cau/{key}")
    public ResponseEntity<?> layTheoYeuCau(@PathVariable String key, HttpSession s) {
        requireStaffOrHigher(s);
        HoaDon h = donHangService.layDonTheoYeuCau(key);
        return h == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(h);
    }

    @PostMapping("/tao-don")
    public ResponseEntity<HoaDon> taoDonHang(@RequestBody DonHangDTO dto, HttpSession s) {
        dto.setMaNV(requireStaffOrHigher(s));
        return ResponseEntity.status(HttpStatus.CREATED).body(donHangService.taoDonHang(dto));
    }

    @PutMapping("/{id}/mon")
    public HoaDon capNhatDon(@PathVariable Integer id, @RequestBody DonHangDTO dto, HttpSession s) {
        requireStaffOrHigher(s);
        return donHangService.capNhatDon(id, dto);
    }

    @PostMapping("/{id}/thanh-toan")
    public HoaDon thanhToan(@PathVariable Integer id, @RequestBody ThanhToanDTO dto, HttpSession s) {
        requireStaffOrHigher(s);
        if (dto.getChietKhau() != null && dto.getChietKhau() > 0 && "STAFF".equals(access.role(s))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Chiết khấu cần tài khoản Manager hoặc Admin");
        }
        return donHangService.thanhToanDonHang(id, dto);
    }

    @PostMapping("/{id}/huy")
    public HoaDon huyDonHang(@PathVariable Integer id, @RequestBody Map<String, Long> body, HttpSession s) {
        requireStaffOrHigher(s);
        return donHangService.huyDonHang(id, body != null ? body.get("phienBan") : null);
    }
}
