package com.cafe.controller;

import com.cafe.entity.MonNuoc;
import com.cafe.service.DonHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mon-nuoc")
public class MonNuocController {

    private final DonHangService donHangService;
    private final PhanQuyenSession access;

    public MonNuocController(DonHangService donHangService, PhanQuyenSession access) {
        this.donHangService = donHangService;
        this.access = access;
    }

    @GetMapping
    public ResponseEntity<List<MonNuoc>> layDanhSachMonNuoc(
            @RequestParam(required = false) String danhMuc,
            @RequestParam(required = false) String q,
            HttpSession session) {
        access.userId(session);
        return ResponseEntity.ok(donHangService.layDanhSachMonNuoc(danhMuc, q));
    }
}
