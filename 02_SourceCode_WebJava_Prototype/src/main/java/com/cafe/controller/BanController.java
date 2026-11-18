package com.cafe.controller;

import com.cafe.entity.Ban;
import com.cafe.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ban")
public class BanController {

    private final OrderService orderService;
    private final SessionAccess access;

    public BanController(OrderService orderService, SessionAccess access) {
        this.orderService = orderService;
        this.access = access;
    }

    @GetMapping
    public ResponseEntity<List<Ban>> layDanhSachBan(HttpSession session) {
        access.userId(session);
        return ResponseEntity.ok(orderService.layDanhSachBan());
    }
}
