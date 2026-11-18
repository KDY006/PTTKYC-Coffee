package com.cafe.controller;

import com.cafe.entity.CaLam;
import com.cafe.service.NhanSuService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ca-lam")
public class CaLamController {
    private final NhanSuService nhanSuService;
    private final SessionAccess access;
    public CaLamController(NhanSuService nhanSuService, SessionAccess access) { this.nhanSuService = nhanSuService; this.access = access; }
    @GetMapping public List<CaLam> layDanhSach(HttpSession session) { access.userId(session); return nhanSuService.layDanhSachCaLam(); }
}
