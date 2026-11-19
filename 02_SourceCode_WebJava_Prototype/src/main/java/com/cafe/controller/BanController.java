package com.cafe.controller;

import com.cafe.entity.Ban;
import com.cafe.service.DonHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ban")
public class BanController {

    private final DonHangService donHangService;
    private final PhanQuyenSession access;

    public BanController(DonHangService donHangService, PhanQuyenSession access) {
        this.donHangService = donHangService;
        this.access = access;
    }

    @GetMapping
    public ResponseEntity<List<Ban>> layDanhSachBan(HttpSession session) {
        access.userId(session);
        return ResponseEntity.ok(donHangService.layDanhSachBan());
    }
}
