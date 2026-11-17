package com.cafe.controller;

import com.cafe.dto.OrderDTO;
import com.cafe.dto.ThanhToanDTO;
import com.cafe.entity.HoaDon;
import com.cafe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/danh-sach")
    public ResponseEntity<List<HoaDon>> layDanhSachHoaDon() {
        return ResponseEntity.ok(orderService.layDanhSachHoaDon());
    }

    @GetMapping("/{maHoaDon}")
    public ResponseEntity<?> layHoaDonTheoId(@PathVariable Integer maHoaDon) {
        try {
            return ResponseEntity.ok(orderService.layHoaDonTheoId(maHoaDon));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/tao-don")
    public ResponseEntity<?> taoDonHang(@RequestBody OrderDTO dto) {
        try {
            HoaDon hoaDon = orderService.taoDonHang(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(hoaDon);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/{maHoaDon}/thanh-toan")
    public ResponseEntity<?> thanhToanDonHang(@PathVariable Integer maHoaDon, @RequestBody ThanhToanDTO dto) {
        try {
            HoaDon hoaDon = orderService.thanhToanDonHang(maHoaDon, dto);
            return ResponseEntity.ok(hoaDon);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/{maHoaDon}/huy")
    public ResponseEntity<?> huyDonHang(@PathVariable Integer maHoaDon) {
        try {
            HoaDon hoaDon = orderService.huyDonHang(maHoaDon);
            return ResponseEntity.ok(hoaDon);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
