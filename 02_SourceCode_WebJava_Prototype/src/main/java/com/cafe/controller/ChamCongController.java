package com.cafe.controller;

import com.cafe.dto.CheckInDTO;
import com.cafe.entity.ChamCong;
import com.cafe.service.NhanSuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cham-cong")
@CrossOrigin(origins = "*")
public class ChamCongController {

    @Autowired
    private NhanSuService nhanSuService;

    @PostMapping("/check-in")
    public ResponseEntity<?> checkIn(@RequestBody CheckInDTO dto) {
        try {
            ChamCong cc = nhanSuService.checkIn(dto);
            return ResponseEntity.ok(cc);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/check-out/{id}")
    public ResponseEntity<?> checkOut(@PathVariable Integer id) {
        try {
            ChamCong cc = nhanSuService.checkOut(id);
            return ResponseEntity.ok(cc);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/lich-su")
    public ResponseEntity<List<ChamCong>> layLichSuChamCong() {
        return ResponseEntity.ok(nhanSuService.layLichSuChamCong());
    }
}
