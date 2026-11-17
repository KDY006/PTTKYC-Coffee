package com.cafe.controller;

import com.cafe.entity.MonNuoc;
import com.cafe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mon-nuoc")
@CrossOrigin(origins = "*")
public class MonNuocController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<MonNuoc>> layDanhSachMonNuoc(@RequestParam(required=false) String danhMuc, @RequestParam(required=false) String q) {
        return ResponseEntity.ok(orderService.layDanhSachMonNuoc(danhMuc,q));
    }
}
