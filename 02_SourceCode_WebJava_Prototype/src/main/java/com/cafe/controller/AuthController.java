package com.cafe.controller;

import com.cafe.dto.LoginDTO;
import com.cafe.entity.NhanVien;
import com.cafe.service.NhanSuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private NhanSuService nhanSuService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto, jakarta.servlet.http.HttpSession session) {
        try {
            NhanVien nv = nhanSuService.dangNhap(dto);
            if (!"DANG_LAM".equals(nv.getTrangThai())) throw new RuntimeException("Tài khoản đã ngừng làm việc");
            session.setAttribute("maNV", nv.getMaNV());
            session.setAttribute("vaiTro", nv.getVaiTro());
            return ResponseEntity.ok(nv);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(jakarta.servlet.http.HttpSession session) {
        Object id = session.getAttribute("maNV");
        if (id == null) return ResponseEntity.status(401).body(Map.of("error", "Vui lòng đăng nhập lại"));
        return ResponseEntity.ok(Map.of("maNV", id, "vaiTro", session.getAttribute("vaiTro")));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(jakarta.servlet.http.HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("message", "Đã đăng xuất"));
    }
}
