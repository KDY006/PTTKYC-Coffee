package com.cafe.controller;

import com.cafe.dto.PhanCaDTO;
import com.cafe.entity.PhanCa;
import com.cafe.service.NhanSuService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phan-ca")
public class PhanCaController {
    private final NhanSuService nhanSuService;
    private final SessionAccess access;
    public PhanCaController(NhanSuService nhanSuService, SessionAccess access) { this.nhanSuService = nhanSuService; this.access = access; }

    @PostMapping
    public ResponseEntity<PhanCa> phanCa(@RequestBody PhanCaDTO dto, HttpSession session) {
        access.requireManager(session);
        return ResponseEntity.status(201).body(nhanSuService.phanCa(dto));
    }

    @GetMapping("/tuan")
    public List<PhanCa> lichTuan(@RequestParam(required = false) LocalDate ngay, HttpSession session) {
        Integer userId = access.userId(session);
        boolean quanLy = "MANAGER".equals(access.role(session)) || "ADMIN".equals(access.role(session));
        return nhanSuService.lichTuan(userId, quanLy, ngay);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoaPhanCa(@PathVariable Integer id, HttpSession session) {
        access.requireManager(session);
        nhanSuService.xoaPhanCa(id);
        return ResponseEntity.noContent().build();
    }
}
