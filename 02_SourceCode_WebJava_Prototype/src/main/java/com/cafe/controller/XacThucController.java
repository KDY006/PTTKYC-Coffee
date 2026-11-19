package com.cafe.controller;

import com.cafe.dto.DangNhapDTO;
import com.cafe.entity.NhanVien;
import com.cafe.service.NhanSuService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping({"/api/auth", "/api/xac-thuc"})
public class XacThucController {

    private final NhanSuService nhanSuService;

    public XacThucController(NhanSuService nhanSuService) {
        this.nhanSuService = nhanSuService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DangNhapDTO dto, HttpSession session) {
        try {
            NhanVien nv = nhanSuService.dangNhap(dto);
            if (!"DANG_LAM".equals(nv.getTrangThai())) {
                throw new RuntimeException("Tài khoản đã ngừng làm việc");
            }
            session.setAttribute("maNV", nv.getMaNV());
            session.setAttribute("vaiTro", nv.getVaiTro());
            return ResponseEntity.ok(nv);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        Object id = session.getAttribute("maNV");
        if (id == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Vui lòng đăng nhập lại"));
        }
        return ResponseEntity.ok(Map.of("maNV", id, "vaiTro", session.getAttribute("vaiTro")));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("message", "Đã đăng xuất"));
    }
}
