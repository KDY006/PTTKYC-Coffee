package com.cafe.controller;

import com.cafe.dto.CongThucDTO;
import com.cafe.dto.NhapKhoDTO;
import com.cafe.entity.CongThuc;
import com.cafe.entity.NguyenLieu;
import com.cafe.service.KhoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kho")
public class KhoController {

    private final KhoService khoService;
    private final PhanQuyenSession access;

    public KhoController(KhoService khoService, PhanQuyenSession access) {
        this.khoService = khoService;
        this.access = access;
    }

    @GetMapping("/nguyen-lieu")
    public ResponseEntity<List<NguyenLieu>> layDanhSachNguyenLieu(HttpSession session) {
        access.userId(session);
        return ResponseEntity.ok(khoService.layTatCaNguyenLieu());
    }

    @GetMapping("/sap-het")
    public ResponseEntity<List<NguyenLieu>> layDanhSachSapHet(HttpSession session) {
        access.requireManager(session);
        return ResponseEntity.ok(khoService.layDanhSachSapHet());
    }

    @PostMapping("/nhap-kho")
    public ResponseEntity<?> nhapKho(@RequestBody NhapKhoDTO dto, HttpSession session) {
        access.requireManager(session);
        try {
            NguyenLieu nl = khoService.nhapKho(dto.getMaNL(), dto.getSoLuongNhap());
            return ResponseEntity.ok(nl);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/cong-thuc")
    public ResponseEntity<?> thietLapCongThuc(@RequestBody CongThucDTO dto, HttpSession session) {
        access.requireManager(session);
        try {
            CongThuc ct = khoService.thietLapCongThuc(dto);
            return ResponseEntity.ok(ct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/cong-thuc/{maMon}")
    public ResponseEntity<List<CongThuc>> layCongThucTheoMon(@PathVariable Integer maMon, HttpSession session) {
        access.userId(session);
        return ResponseEntity.ok(khoService.layCongThucTheoMon(maMon));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboardKPI(HttpSession session) {
        access.requireManager(session);
        return ResponseEntity.ok(khoService.getDashboardKPI());
    }
}
