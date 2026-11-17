package com.cafe.controller;

import com.cafe.dto.*;
import com.cafe.entity.HoaDon;
import com.cafe.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired private OrderService orderService;
    private Integer staff(HttpSession s) {
        Object id=s.getAttribute("maNV"),role=s.getAttribute("vaiTro");
        if(!(id instanceof Integer))throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Vui lòng đăng nhập lại");
        if(!Set.of("STAFF","MANAGER","ADMIN").contains(String.valueOf(role)))throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Không có quyền bán hàng");
        return (Integer)id;
    }
    @GetMapping("/danh-sach") public List<HoaDon> all(HttpSession s){staff(s);return orderService.layDanhSachHoaDon();}
    @GetMapping("/{id}") public HoaDon get(@PathVariable Integer id,HttpSession s){staff(s);return orderService.layHoaDonTheoId(id);}
    @GetMapping("/theo-ban/{id}") public ResponseEntity<?> table(@PathVariable Integer id,HttpSession s){staff(s);HoaDon h=orderService.layDonTheoBan(id);return h==null?ResponseEntity.noContent().build():ResponseEntity.ok(h);}
    @GetMapping("/theo-yeu-cau/{key}") public ResponseEntity<?> byRequest(@PathVariable String key,HttpSession s){staff(s);HoaDon h=orderService.layDonTheoYeuCau(key);return h==null?ResponseEntity.noContent().build():ResponseEntity.ok(h);}
    @PostMapping("/tao-don") public ResponseEntity<HoaDon> create(@RequestBody OrderDTO dto,HttpSession s){dto.setMaNV(staff(s));return ResponseEntity.status(201).body(orderService.taoDonHang(dto));}
    @PutMapping("/{id}/mon") public HoaDon update(@PathVariable Integer id,@RequestBody OrderDTO dto,HttpSession s){staff(s);return orderService.capNhatDon(id,dto);}
    @PostMapping("/{id}/thanh-toan") public HoaDon pay(@PathVariable Integer id,@RequestBody ThanhToanDTO dto,HttpSession s){
        staff(s);
        if(dto.getChietKhau()!=null&&dto.getChietKhau()>0&&"STAFF".equals(s.getAttribute("vaiTro")))throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Chiết khấu cần tài khoản Manager hoặc Admin");
        return orderService.thanhToanDonHang(id,dto);
    }
    @PostMapping("/{id}/huy") public HoaDon cancel(@PathVariable Integer id,@RequestBody Map<String,Long> body,HttpSession s){staff(s);return orderService.huyDonHang(id,body.get("phienBan"));}
}
