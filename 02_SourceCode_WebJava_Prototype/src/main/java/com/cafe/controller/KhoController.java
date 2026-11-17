package com.cafe.controller;

import com.cafe.dto.CongThucDTO;
import com.cafe.dto.NhapKhoDTO;
import com.cafe.entity.CongThuc;
import com.cafe.entity.NguyenLieu;
import com.cafe.service.KhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kho")
@CrossOrigin(origins = "*")
public class KhoController {

    @Autowired
    private KhoService khoService;

    @GetMapping("/nguyen-lieu")
    public ResponseEntity<List<NguyenLieu>> layDanhSachNguyenLieu() {
        return ResponseEntity.ok(khoService.layTatCaNguyenLieu());
    }

    @GetMapping("/sap-het")
    public ResponseEntity<List<NguyenLieu>> layDanhSachSapHet() {
        return ResponseEntity.ok(khoService.layDanhSachSapHet());
    }

    @PostMapping("/nhap-kho")
    public ResponseEntity<?> nhapKho(@RequestBody NhapKhoDTO dto) {
        try {
            NguyenLieu nl = khoService.nhapKho(dto.getMaNL(), dto.getSoLuongNhap());
            return ResponseEntity.ok(nl);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/cong-thuc")
    public ResponseEntity<?> thietLapCongThuc(@RequestBody CongThucDTO dto) {
        try {
            CongThuc ct = khoService.thietLapCongThuc(dto);
            return ResponseEntity.ok(ct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/cong-thuc/{maMon}")
    public ResponseEntity<List<CongThuc>> layCongThucTheoMon(@PathVariable Integer maMon) {
        return ResponseEntity.ok(khoService.layCongThucTheoMon(maMon));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboardKPI() {
        return ResponseEntity.ok(khoService.getDashboardKPI());
    }
}
