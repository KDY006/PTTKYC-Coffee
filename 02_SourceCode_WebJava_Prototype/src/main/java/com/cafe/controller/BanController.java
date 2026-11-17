package com.cafe.controller;

import com.cafe.entity.Ban;
import com.cafe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ban")
@CrossOrigin(origins = "*")
public class BanController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Ban>> layDanhSachBan() {
        return ResponseEntity.ok(orderService.layDanhSachBan());
    }
}
