package com.cafe.controller;

import com.cafe.entity.MonNuoc;
import com.cafe.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mon-nuoc")
public class MonNuocController {

    private final OrderService orderService;
    private final SessionAccess access;

    public MonNuocController(OrderService orderService, SessionAccess access) {
        this.orderService = orderService;
        this.access = access;
    }

    @GetMapping
    public ResponseEntity<List<MonNuoc>> layDanhSachMonNuoc(
            @RequestParam(required = false) String danhMuc,
            @RequestParam(required = false) String q,
            HttpSession session) {
        access.userId(session);
        return ResponseEntity.ok(orderService.layDanhSachMonNuoc(danhMuc, q));
    }
}
