package com.cafe.scheduler;

import com.cafe.service.NhanSuService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ChotCaTuDongScheduler {

    private final NhanSuService nhanSuService;

    public ChotCaTuDongScheduler(NhanSuService nhanSuService) {
        this.nhanSuService = nhanSuService;
    }

    /**
     * Tự động quét và chốt ca quá hạn mỗi 15 phút (BR-CC-05):
     * - Suất phân ca không check-in sau cuối ca + 30 phút -> Đánh dấu VANG
     * - Bản ghi chấm công quên check-out sau cuối ca + 30 phút -> Chuyển QUEN_CHECK_OUT
     */
    @Scheduled(cron = "0 */15 * * * *")
    public void runAutoChotCa() {
        try {
            nhanSuService.chotCaQuaHan();
        } catch (Exception e) {
            System.err.println("Lỗi khi tự động chốt ca quá hạn: " + e.getMessage());
        }
    }
}
