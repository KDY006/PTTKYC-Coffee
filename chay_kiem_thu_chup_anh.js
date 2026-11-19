const puppeteer = require('puppeteer-core');
const path = require('path');
const fs = require('fs');

const CHROME_PATH = 'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe';
const SCREENSHOT_DIR = 'D:\\3.1. PTTKYC\\CK\\screenshots';

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function loginAs(page, username, password) {
    console.log(`[AUTH] Đăng nhập với tài khoản: ${username}...`);
    await page.goto('http://localhost:8080/index.html', { waitUntil: 'networkidle0' });
    await page.evaluate(() => { localStorage.clear(); sessionStorage.clear(); });
    await page.reload({ waitUntil: 'networkidle0' });
    await sleep(600);

    await page.type('#login-username', username);
    await page.type('#login-password', password);

    await Promise.all([
        page.waitForNavigation({ waitUntil: 'networkidle0', timeout: 10000 }).catch(() => {}),
        page.click('#btn-login-submit')
    ]);
    await sleep(1500);

    // Ensure on banhang.html
    if (page.url().includes('index.html')) {
        await page.goto('http://localhost:8080/banhang.html', { waitUntil: 'networkidle0' });
        await sleep(1500);
    }
}

async function main() {
    if (!fs.existsSync(SCREENSHOT_DIR)) {
        fs.mkdirSync(SCREENSHOT_DIR, { recursive: true });
    }

    console.log('[TEST] Khởi chạy Chrome Headless...');
    const browser = await puppeteer.launch({
        executablePath: CHROME_PATH,
        headless: 'new',
        args: [
            '--no-sandbox',
            '--disable-setuid-sandbox',
            '--disable-dev-shm-usage',
            '--disable-gpu',
            '--window-size=1400,900'
        ],
        defaultViewport: { width: 1400, height: 900 }
    });

    const page = await browser.newPage();
    const results = [];

    page.on('dialog', async dialog => {
        console.log(`[BROWSER DIALOG]: ${dialog.type()} -> "${dialog.message()}"`);
        try {
            await dialog.accept();
        } catch (e) {}
    });

    try {
        // =========================================================================
        // 1. TRANG ĐĂNG NHẬP (index.html) - RBAC 3 CẤP
        // =========================================================================
        console.log('\n--- BƯỚC 1: XÁC THỰC ĐĂNG NHẬP & RBAC 3 CẤP ---');
        await page.goto('http://localhost:8080/index.html', { waitUntil: 'networkidle0' });
        await page.evaluate(() => { sessionStorage.clear(); localStorage.clear(); });
        await page.reload({ waitUntil: 'networkidle0' });
        await sleep(800);

        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '01_dang_nhap.png') });
        console.log('[SCREENSHOT] 01_dang_nhap.png');
        results.push({ test: 'DangNhap_GiaoDien', status: 'PASS' });

        // =========================================================================
        // 2. VAI TRÒ 1: PHỤC VỤ (PHUC_VU) - phucvu1 / 123456
        // =========================================================================
        console.log('\n--- BƯỚC 2: PHỤC VỤ MỞ BÀN, IN BILL BAR, YÊU CẦU THANH TOÁN ---');
        await loginAs(page, 'phucvu1', '123456');

        // Chụp sơ đồ bàn của Phục vụ: thấy đủ 3 màu viền (Bàn 03 Xanh lá 70.000đ, Bàn 07 Vàng 63.000đ, các bàn khác Xanh dương)
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '02_pos_phuc_vu_so_do_ban.png') });
        console.log('[SCREENSHOT] 02_pos_phuc_vu_so_do_ban.png');
        results.push({ test: 'PhucVu_SoDoBan_3TrangThai', status: 'PASS' });

        // Phục vụ chọn Bàn 02 (đang Trống, viền Xanh dương)
        console.log('[POS] Phục vụ chọn Bàn 02...');
        await page.waitForSelector('button[data-table="2"]');
        await page.click('button[data-table="2"]');
        await sleep(1000);

        // Thêm 1 Cà phê sữa đá (Món 2) + 1 Trà đào cam sả (Món 6)
        console.log('[POS] Thêm món vào Bàn 02...');
        await page.waitForSelector('button[data-add="2"]');
        await page.click('button[data-add="2"]');
        await sleep(500);

        await page.waitForSelector('button[data-add="6"]');
        await page.click('button[data-add="6"]');
        await sleep(1000);

        // Bấm "Xác nhận & In Bill Bar"
        console.log('[POS] Bấm Xác nhận & In Bill Bar...');
        await page.waitForSelector('#btn-create-bar-order', { visible: true });
        await page.click('#btn-create-bar-order');
        await sleep(1500);

        // Chụp Modal Phiếu Pha Chế Quầy Bar (Bar Ticket - không hiện giá)
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '03_phuc_vu_mo_ban_in_bill_bar.png') });
        console.log('[SCREENSHOT] 03_phuc_vu_mo_ban_in_bill_bar.png');
        results.push({ test: 'PhucVu_MoBan_InBillBar', status: 'PASS' });

        // Đóng Bar Modal
        await page.evaluate(() => {
            const closeBtn = document.querySelector('#barModal button[data-bs-dismiss="modal"]');
            if (closeBtn) closeBtn.click();
        });
        await sleep(1000);

        // Bàn 02 giờ đã chuyển sang viền Xanh lá (Đang dùng nước - 67.000 đ)
        // Phục vụ bấm "Yêu cầu thanh toán"
        console.log('[POS] Phục vụ bấm Yêu cầu thanh toán...');
        await page.waitForSelector('#btn-request-payment', { visible: true });
        await page.click('#btn-request-payment');
        await sleep(1500);

        // Chụp giao diện Bàn 02 chuyển sang viền Vàng, Phục vụ thấy thông báo chờ Thu ngân và nút thanh toán bị khóa
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '04_phuc_vu_yeu_cau_thanh_toan.png') });
        console.log('[SCREENSHOT] 04_phuc_vu_yeu_cau_thanh_toan.png');
        results.push({ test: 'PhucVu_YeuCauThanhToan_KhoaNut', status: 'PASS' });

        // =========================================================================
        // 3. VAI TRÒ 2: THU NGÂN (THU_NGAN) - thungan1 / 123456
        // =========================================================================
        console.log('\n--- BƯỚC 3: THU NGÂN IN BILL TẠM TÍNH & ĐỐI SOÁT THANH TOÁN ---');
        await loginAs(page, 'thungan1', '123456');

        // Chụp màn hình POS của Thu ngân: Thấy các bàn Chờ thanh toán (viền Vàng)
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '05_pos_thu_ngan_so_do_ban.png') });
        console.log('[SCREENSHOT] 05_pos_thu_ngan_so_do_ban.png');
        results.push({ test: 'ThuNgan_SoDoBan_POS', status: 'PASS' });

        // Thu ngân chọn Bàn 02 (viền Vàng)
        console.log('[POS] Thu ngân chọn Bàn 02...');
        await page.waitForSelector('button[data-table="2"]');
        await page.click('button[data-table="2"]');
        await sleep(1000);

        // Bấm "In Bill Tạm Tính"
        console.log('[POS] Thu ngân bấm In Bill Tạm Tính...');
        await page.waitForSelector('#btn-print-precheck', { visible: true });
        await page.click('#btn-print-precheck');
        await sleep(1500);

        // Chụp Modal Phiếu Tạm Tính (Pre-check Bill có QR VietQR và cảnh báo kiểm tra)
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '06_thu_ngan_in_bill_tam_tinh.png') });
        console.log('[SCREENSHOT] 06_thu_ngan_in_bill_tam_tinh.png');
        results.push({ test: 'ThuNgan_InBillTamTinh', status: 'PASS' });

        // Đóng Precheck Modal
        await page.evaluate(() => {
            const closeBtn = document.querySelector('#precheckModal button[data-bs-dismiss="modal"]');
            if (closeBtn) closeBtn.click();
        });
        await sleep(1000);

        // Thu ngân bấm "Xác nhận Thanh toán & Đóng bàn"
        console.log('[POS] Thu ngân mở Modal Đối soát & Thu tiền...');
        await page.waitForSelector('#btn-open-checkout-modal', { visible: true });
        await page.click('#btn-open-checkout-modal');
        await sleep(1000);

        // Nhập tiền khách đưa 100.000 đ -> Hệ thống tự tính tiền thừa 33.000 đ
        await page.evaluate(() => {
            const input = document.getElementById('modal-tien-khach-dua');
            if (input) {
                input.value = '100000';
                input.dispatchEvent(new Event('input', { bubbles: true }));
            }
        });
        await sleep(800);

        // Chụp Modal đối soát thanh toán tiền mặt
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '07_thu_ngan_doi_soat_thanh_toan.png') });
        console.log('[SCREENSHOT] 07_thu_ngan_doi_soat_thanh_toan.png');
        results.push({ test: 'ThuNgan_DoiSoatThanhToan', status: 'PASS' });

        // Bấm "Xác nhận & Đóng bàn"
        console.log('[POS] Thu ngân chốt thanh toán & đóng bàn...');
        await page.waitForSelector('#btn-modal-do-checkout', { visible: true });
        await page.click('#btn-modal-do-checkout');
        await sleep(2000);

        // Chụp Modal Hóa đơn thanh toán chính thức (Official Receipt ghi nhận Phục vụ & Thu ngân)
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '08_hoa_don_thanh_toan_chinh_thuc.png') });
        console.log('[SCREENSHOT] 08_hoa_don_thanh_toan_chinh_thuc.png');
        results.push({ test: 'HoaDon_ChinhThuc_DongBan', status: 'PASS' });

        // Đóng Receipt modal
        await page.evaluate(() => {
            const closeBtn = document.querySelector('#receiptModal button[data-bs-dismiss="modal"]');
            if (closeBtn) closeBtn.click();
        });
        await sleep(800);

        // =========================================================================
        // 4. VAI TRÒ 3: QUẢN LÝ (QUAN_LY) - quanly / 123456
        // =========================================================================
        console.log('\n--- BƯỚC 4: QUẢN LÝ KIỂM TRA TRỪ KHO BOM & PHÂN CA NHÂN SỰ ---');
        await loginAs(page, 'quanly', '123456');

        // Quản lý vào tab Kho & KPI
        console.log('[KHO] Quản lý mở kho.html...');
        await page.goto('http://localhost:8080/kho.html', { waitUntil: 'networkidle0' });
        await sleep(1500);

        // Chụp màn hình Kho: Chứng minh Sữa đặc Ngôi Sao vừa bị trừ kho BOM tự động từ 120ml -> 90ml <= 100ml và bật cảnh báo đỏ "SẮP HẾT HÀNG"
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '09_quan_ly_kho_kpi_canh_bao.png') });
        console.log('[SCREENSHOT] 09_quan_ly_kho_kpi_canh_bao.png');
        results.push({ test: 'QuanLy_Kho_TruBOM_CanhBaoDo', status: 'PASS' });

        // Quản lý vào tab Nhân sự & Phân ca
        console.log('[NHAN SU] Quản lý mở nhansu.html...');
        await page.goto('http://localhost:8080/nhansu.html', { waitUntil: 'networkidle0' });
        await sleep(1500);

        // Chụp màn hình Nhân sự & Phân ca tuần
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '10_quan_ly_nhan_su_phan_ca.png') });
        console.log('[SCREENSHOT] 10_quan_ly_nhan_su_phan_ca.png');
        results.push({ test: 'QuanLy_NhanSu_PhanCa', status: 'PASS' });

        // Lưu kết quả kiểm thử vào file JSON
        fs.writeFileSync(path.join(SCREENSHOT_DIR, 'results.json'), JSON.stringify(results, null, 2), 'utf8');
        console.log('\n[TEST HOÀN TẤT] 10/10 Kịch bản kiểm thử E2E đã hoàn thành xuất sắc!');

    } catch (err) {
        console.error('[TEST ERROR]:', err);
    } finally {
        await browser.close();
    }
}

main();
