package com.cafe.controller;

import com.cafe.dto.CheckInDTO;
import com.cafe.entity.ChamCong;
import com.cafe.service.NhanSuService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cham-cong")
public class ChamCongController {
    private final NhanSuService nhanSuService;
    private final SessionAccess access;

    public ChamCongController(NhanSuService nhanSuService, SessionAccess access) { this.nhanSuService = nhanSuService; this.access = access; }

    @PostMapping("/check-in")
    public ChamCong checkIn(@RequestBody CheckInDTO dto, HttpSession session) { return nhanSuService.checkIn(access.userId(session), dto); }

    @PostMapping("/check-out/{id}")
    public ChamCong checkOut(@PathVariable Integer id, HttpSession session) {
        Integer userId = access.userId(session);
        boolean quanLy = "MANAGER".equals(access.role(session)) || "ADMIN".equals(access.role(session));
        return nhanSuService.checkOut(userId, quanLy, id);
    }

    @GetMapping("/lich-su")
    public List<ChamCong> layLichSuChamCong(@RequestParam(required = false) LocalDate tuNgay, @RequestParam(required = false) LocalDate denNgay, HttpSession session) {
        Integer userId = access.userId(session);
        boolean quanLy = "MANAGER".equals(access.role(session)) || "ADMIN".equals(access.role(session));
        return nhanSuService.layLichSuChamCong(userId, quanLy, tuNgay, denNgay);
    }
}
