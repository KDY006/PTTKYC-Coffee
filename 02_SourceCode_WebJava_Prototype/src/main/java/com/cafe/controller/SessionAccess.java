package com.cafe.controller;

import jakarta.servlet.http.HttpSession;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class SessionAccess {
    public Integer userId(HttpSession session) {
        Object id = session.getAttribute("maNV");
        if (!(id instanceof Integer)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Vui lòng đăng nhập lại");
        return (Integer) id;
    }
    public String role(HttpSession session) { userId(session); return String.valueOf(session.getAttribute("vaiTro")); }
    public Integer requireAny(HttpSession session, String... roles) {
        if (!Set.of(roles).contains(role(session))) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Bạn không có quyền thực hiện thao tác này");
        return userId(session);
    }
    public Integer requireManager(HttpSession session) { return requireAny(session, "MANAGER", "ADMIN"); }
    public Integer requireAdmin(HttpSession session) { return requireAny(session, "ADMIN"); }
}
