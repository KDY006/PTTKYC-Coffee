package com.cafe.controller;

import com.cafe.entity.CaLam;
import com.cafe.entity.NhanVien;
import com.cafe.service.NhanSuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/nhan-su")
@CrossOrigin(origins = "*")
public class NhanSuController {

    @Autowired
    private NhanSuService nhanSuService;

    @GetMapping("/nhan-vien")
    public ResponseEntity<List<NhanVien>> layDanhSachNhanVien() {
        return ResponseEntity.ok(nhanSuService.layDanhSachNhanVien());
    }

    @PostMapping("/nhan-vien")
    public ResponseEntity<?> themNhanVien(@RequestBody NhanVien nv) {
        try {
            return ResponseEntity.ok(nhanSuService.themNhanVien(nv));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/nhan-vien/{id}")
    public ResponseEntity<?> xoaNhanVien(@PathVariable Integer id) {
        try {
            nhanSuService.xoaNhanVien(id);
            return ResponseEntity.ok(Map.of("message", "Đã xóa nhân viên thành công!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/ca-lam")
    public ResponseEntity<List<CaLam>> layDanhSachCaLam() {
        return ResponseEntity.ok(nhanSuService.layDanhSachCaLam());
    }
}
