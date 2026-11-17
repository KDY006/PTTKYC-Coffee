package com.cafe.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.ConcurrencyFailureException;
import java.util.Map;
@RestControllerAdvice(assignableTypes={OrderController.class,MonNuocController.class,BanController.class})
public class PosExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> status(ResponseStatusException e){return ResponseEntity.status(e.getStatusCode()).body(Map.of("error",e.getReason()==null?"Không thực hiện được":e.getReason()));}
    @ExceptionHandler({DataIntegrityViolationException.class,ConcurrencyFailureException.class})
    public ResponseEntity<?> conflict(Exception e){return ResponseEntity.status(409).body(Map.of("error","Dữ liệu vừa thay đổi hoặc mã yêu cầu/tham chiếu đã dùng. Tải lại đơn để kiểm tra trước khi thử lại."));}
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> bad(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()==null?"Không thực hiện được yêu cầu":e.getMessage()));}
}
