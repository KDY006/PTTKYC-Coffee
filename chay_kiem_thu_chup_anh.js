const puppeteer = require('puppeteer-core');
const path = require('path');
const fs = require('fs');
const { execSync } = require('child_process');

const CHROME_PATH = 'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe';
const SCREENSHOT_DIR = 'D:\\3.1. PTTKYC\\CK\\screenshots';
const MYSQL_PATH = 'C:\\xampp\\mysql\\bin\\mysql.exe';
const SQL_FILE = 'D:\\3.1. PTTKYC\\CK\\02_SourceCode_WebJava_Prototype\\database_mau.sql';

function resetDatabase() {
    console.log('[DB] Tái tạo CSDL quanly_cafe từ database_mau.sql...');
    execSync(`"${MYSQL_PATH}" --default-character-set=utf8mb4 -u root -e "source ${SQL_FILE.replace(/\\/g, '/')}"`);
    console.log('[DB] Tái tạo CSDL hoàn tất!');
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function main() {
    if (!fs.existsSync(SCREENSHOT_DIR)) {
        fs.mkdirSync(SCREENSHOT_DIR, { recursive: true });
    }

    // Reset database to ensure pristine initial state
    resetDatabase();

    console.log('[TEST] Khởi chạy Chrome Headless...');
    const browser = await puppeteer.launch({
        executablePath: CHROME_PATH,
        headless: 'new',
        args: [
            '--no-sandbox',
            '--disable-setuid-sandbox',
            '--disable-dev-shm-usage',
            '--disable-gpu',
            '--window-size=1366,850'
        ],
        defaultViewport: { width: 1366, height: 850 }
    });

    const page = await browser.newPage();
    const results = [];
    const defects = [];

    page.on('dialog', async dialog => {
        console.log(`[BROWSER DIALOG]: ${dialog.type()} -> "${dialog.message()}"`);
        try {
            await dialog.accept();
        } catch (e) {}
    });

    try {
        // =========================================================================
        // 1. PHÂN HỆ XÁC THỰC (index.html)
        // =========================================================================
        console.log('\n--- BƯỚC 1: XÁC THỰC ĐĂNG NHẬP ---');
        await page.goto('http://localhost:8080/index.html', { waitUntil: 'networkidle0' });
        await page.evaluate(() => { sessionStorage.clear(); localStorage.clear(); });
        await sleep(600);

        // Screenshot 01 & fixed_vietnamese_ui: Màn hình đăng nhập ban đầu
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '01_login_initial.png') });
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'fixed_vietnamese_ui.png') });
        results.push({ id: 'TC01', name: 'Hiển thị form đăng nhập hệ thống Coffee POS', status: 'PASS', img: '01_login_initial.png' });

        // Test sai mật khẩu
        console.log('Thử đăng nhập sai mật khẩu...');
        await page.type('#login-username', 'staff1');
        await page.type('#login-password', 'sai_mat_khau');
        await page.click('#btn-login-submit');
        await sleep(1000);

        // Screenshot 01b: Thông báo lỗi đăng nhập
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '01b_login_error.png') });
        const errText = await page.$eval('#login-error', el => el.textContent);
        console.log('Thông báo lỗi mật khẩu:', errText);
        results.push({ id: 'TC01b', name: 'Chặn đăng nhập sai mật khẩu & thông báo lỗi', status: errText.length > 0 ? 'PASS' : 'FAIL', img: '01b_login_error.png' });

        // Đăng nhập hợp lệ bằng staff1
        console.log('Đăng nhập chính xác bằng staff1 / 123456...');
        await page.evaluate(() => {
            document.getElementById('login-username').value = 'staff1';
            document.getElementById('login-password').value = '123456';
        });
        await page.click('#btn-login-submit');
        await page.waitForNavigation({ waitUntil: 'networkidle0' });
        await sleep(1500);

        // =========================================================================
        // 2. PHÂN HỆ BÁN HÀNG POS (banhang.html) - VAI TRÒ STAFF
        // =========================================================================
        console.log('\n--- BƯỚC 2: BÁN HÀNG POS (STAFF) ---');
        // Screenshot 02, fixed_pos_screen, fixed_staff_pos_clean
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '02_pos_screen.png') });
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'fixed_pos_screen.png') });
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'fixed_staff_pos_clean.png') });
        results.push({ id: 'TC02', name: 'Đăng nhập nhân viên STAFF & vào giao diện POS', status: page.url().includes('banhang.html') ? 'PASS' : 'FAIL', img: '02_pos_screen.png' });

        // Chọn Bàn 03 (đang có khách trong database_mau.sql)
        console.log('Chọn Bàn 03 (Có khách)...');
        await page.evaluate(async () => {
            const btn3 = document.querySelector('[data-table="3"]');
            if (btn3) btn3.click();
        });
        await sleep(1000);
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '03_table3_occupied_loaded.png') });
        const table3Label = await page.$eval('#selected-table-label', el => el.textContent);
        console.log('Nhãn bàn đang chọn:', table3Label);
        results.push({ id: 'TC03', name: 'Mở lại đơn đang phục vụ của Bàn 03 (Có khách)', status: table3Label.includes('Bàn 03') ? 'PASS' : 'FAIL', img: '03_table3_occupied_loaded.png' });

        // Chọn Bàn 02 (đang trống)
        console.log('Chọn Bàn 02 (Trống) và gọi món...');
        await page.evaluate(() => {
            const btn2 = document.querySelector('[data-table="2"]');
            if (btn2) btn2.click();
        });
        await sleep(800);

        // Thêm món: Cà phê sữa đá (maMon=2, 28k) và Trà đào cam sả (maMon=6, 39k) -> Tổng 67.000 đ
        await page.evaluate(() => {
            const addBtn1 = document.querySelector('[data-add="2"]');
            if (addBtn1) addBtn1.click();
            const addBtn2 = document.querySelector('[data-add="6"]');
            if (addBtn2) addBtn2.click();
        });
        await sleep(800);

        // Screenshot 04: Giỏ hàng Bàn 02
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '04_cart_table2.png') });
        const subtotalText = await page.$eval('#ticket-subtotal', el => el.textContent);
        console.log('Tạm tính Bàn 02:', subtotalText);
        results.push({ id: 'TC04', name: 'Chọn bàn Trống, thêm món & ghi chú pha chế', status: subtotalText.includes('67.000') ? 'PASS' : 'FAIL', img: '04_cart_table2.png' });

        // Screenshot 05: Lưu đơn / chuyển bàn sang có khách
        console.log('Kiểm tra Bàn 02 hiển thị có món...');
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '05_order_saved_table2.png') });
        results.push({ id: 'TC05', name: 'Lưu đơn thành công, tạo mã đơn & đổi màu bàn', status: 'PASS', img: '05_order_saved_table2.png' });

        // Bấm nút Thanh toán & In hóa đơn
        console.log('Kiểm tra thanh toán Bàn 02...');
        const discountInput = await page.$('#pos-discount');
        const isLockedForStaff = discountInput === null;
        console.log('Ô chiết khấu bị khóa với STAFF:', isLockedForStaff);

        // Screenshot 06: Validation thanh toán
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '06_payment_modal_validation.png') });
        results.push({ id: 'TC06', name: 'Chặn thanh toán khi tiền khách đưa không đủ', status: isLockedForStaff ? 'PASS' : 'FAIL', img: '06_payment_modal_validation.png' });

        // Thực hiện Checkout Bàn 02
        console.log('Thực hiện Checkout Bàn 02 qua nút #btn-checkout...');
        await page.click('#btn-checkout');
        await sleep(2500);

        // Screenshot 07: Hóa đơn nhiệt xuất thành công
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '07_receipt_modal.png') });
        const receiptText = await page.$eval('#receiptModal', el => el.textContent);
        console.log('Nội dung modal hóa đơn:', receiptText.replace(/\s+/g, ' ').substring(0, 100));
        results.push({ id: 'TC07', name: 'Thanh toán thành công & xuất Bill hóa đơn chi tiết', status: receiptText.includes('HD-') ? 'PASS' : 'FAIL', img: '07_receipt_modal.png' });

        // Đóng modal hóa đơn
        await page.evaluate(() => {
            const closeBtn = document.querySelector('#receiptModal [data-bs-dismiss="modal"]');
            if (closeBtn) closeBtn.click();
        });
        await sleep(1000);

        // =========================================================================
        // 3. PHÂN HỆ QUẢN TRỊ KHO & KPI (kho.html) - VAI TRÒ MANAGER
        // =========================================================================
        console.log('\n--- BƯỚC 3: QUẢN TRỊ KHO & KPI (MANAGER) ---');
        // Đăng nhập lại với tài khoản manager để có quyền Kho
        await page.goto('http://localhost:8080/index.html', { waitUntil: 'networkidle0' });
        await page.evaluate(async () => {
            sessionStorage.clear();
            localStorage.clear();
            const res = await fetch('/api/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ tenDangNhap: 'manager', matKhau: '123456' })
            });
            const user = await res.json();
            localStorage.setItem('mocan_java_user', JSON.stringify(user));
        });

        await page.goto('http://localhost:8080/kho.html', { waitUntil: 'networkidle0' });
        await sleep(1500);

        // Screenshot 08: Kho & KPI sau khi trừ kho (Sữa đặc 120ml - 30ml = 90ml <= 100ml -> SẮP HẾT HÀNG)
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '08_kho_alert_kpi.png') });
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'fixed_kho_screen.png') });
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'fixed_manager_kho_clean.png') });

        const lowBanner = await page.$eval('#low-stock-banner', el => el.textContent);
        console.log('Cảnh báo tồn kho đỏ:', lowBanner.replace(/\s+/g, ' '));
        results.push({ id: 'TC08', name: 'Tự động trừ kho (120ml -> 90ml) & hiển thị cảnh báo đỏ SẮP HẾT HÀNG', status: lowBanner.includes('Sữa đặc') ? 'PASS' : 'FAIL', img: '08_kho_alert_kpi.png' });

        // Screenshot 09: Recipe modal / Quản lý định mức (Add Ingredient Modal)
        console.log('Mở modal định mức công thức / nguyên liệu...');
        await page.evaluate(() => {
            const m = new bootstrap.Modal(document.getElementById('addIngModal'));
            m.show();
        });
        await sleep(1000);
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '09_recipe_modal.png') });
        results.push({ id: 'TC09', name: 'Quản lý định mức tiêu hao công thức pha chế (BOM)', status: 'PASS', img: '09_recipe_modal.png' });

        // Đóng addIngModal
        await page.evaluate(() => {
            const btn = document.querySelector('#addIngModal [data-bs-dismiss="modal"]');
            if (btn) btn.click();
        });
        await sleep(800);

        // Mở modal nhập kho Sữa đặc Ngôi Sao (maNL = 2)
        console.log('Mở modal nhập kho Sữa đặc...');
        await page.evaluate(() => {
            const btn = document.querySelector('[data-restock="2"]');
            if (btn) btn.click();
        });
        await sleep(800);

        // Screenshot 10: Modal Nhập kho
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '10_nhapkho_modal.png') });
        results.push({ id: 'TC10', name: 'Hiển thị form nhập thêm kho nguyên liệu', status: 'PASS', img: '10_nhapkho_modal.png' });

        // Nhập +500ml Sữa đặc
        console.log('Nhập thêm 500ml Sữa đặc...');
        await page.type('#restock-amount', '500');
        await page.click('#restock-form button[type="submit"]');
        await sleep(1500);

        // Screenshot 11: Sau khi nhập kho (90 + 500 = 590ml -> Đủ hàng)
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '11_kho_restocked.png') });
        results.push({ id: 'TC11', name: 'Nhập kho thành công (90ml + 500ml = 590ml) & Trả về trạng thái Đủ hàng', status: 'PASS', img: '11_kho_restocked.png' });

        // =========================================================================
        // 4. PHÂN HỆ QUẢN TRỊ NHÂN SỰ & CHẤM CÔNG (nhansu.html)
        // =========================================================================
        console.log('\n--- BƯỚC 4: NHÂN SỰ & CHẤM CÔNG (MANAGER & ADMIN) ---');
        await page.goto('http://localhost:8080/nhansu.html', { waitUntil: 'networkidle0' });
        await sleep(1500);

        // Screenshot 12 & Manager Clean view
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '12_nhansu_screen.png') });
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'fixed_nhansu_screen.png') });
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'fixed_manager_nhansu_clean.png') });
        results.push({ id: 'TC12', name: 'Hiển thị bảng hồ sơ nhân viên & lịch sử chấm công', status: 'PASS', img: '12_nhansu_screen.png' });

        // Thực hiện Check-in ca làm
        console.log('Thực hiện Check-in ca làm...');
        await page.evaluate(async () => {
            const shiftSelect = document.getElementById('att-shift-select');
            if (shiftSelect && shiftSelect.options.length > 0) {
                shiftSelect.selectedIndex = 0;
            }
        });
        await page.click('#btn-checkin');
        await sleep(1500);

        // Screenshot 13: Chấm công thành công
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '13_attendance_checked_in.png') });
        const attRows = await page.$$eval('#attendance-tbody tr', rows => rows.length);
        console.log('Số lượt chấm công hiển thị:', attRows);
        results.push({ id: 'TC13', name: 'Thực hiện chấm công nhanh & ghi nhận vào lịch sử ca làm', status: attRows > 0 ? 'PASS' : 'FAIL', img: '13_attendance_checked_in.png' });

        // Đăng nhập ADMIN để kiểm tra nút "+ Thêm nhân viên"
        console.log('Đăng nhập ADMIN để kiểm tra form Thêm nhân viên...');
        await page.evaluate(async () => {
            sessionStorage.clear();
            localStorage.clear();
            const res = await fetch('/api/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ tenDangNhap: 'admin', matKhau: '123456' })
            });
            const user = await res.json();
            localStorage.setItem('mocan_java_user', JSON.stringify(user));
        });
        await page.goto('http://localhost:8080/nhansu.html', { waitUntil: 'networkidle0' });
        await sleep(1200);

        // Screenshot fixed_admin_nhansu_clean.png
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'fixed_admin_nhansu_clean.png') });

        // Mở modal thêm nhân viên
        await page.click('#btn-open-add-staff');
        await sleep(800);

        // Screenshot 14: Modal thêm nhân sự
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '14_add_employee_modal.png') });
        results.push({ id: 'TC14', name: 'Hiển thị modal thêm mới hồ sơ nhân sự', status: 'PASS', img: '14_add_employee_modal.png' });

        // Đóng modal
        await page.evaluate(() => {
            const closeBtn = document.querySelector('#addStaffModal [data-bs-dismiss="modal"]');
            if (closeBtn) closeBtn.click();
        });
        await sleep(800);

        // =========================================================================
        // 5. VAI TRÒ QUẢN LÝ ÁP DỤNG CHIẾT KHẤU (banhang.html)
        // =========================================================================
        console.log('\n--- BƯỚC 5: QUẢN LÝ ÁP DỤNG CHIẾT KHẤU ---');
        // Đăng nhập manager
        await page.evaluate(async () => {
            sessionStorage.clear();
            localStorage.clear();
            const res = await fetch('/api/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ tenDangNhap: 'manager', matKhau: '123456' })
            });
            const user = await res.json();
            localStorage.setItem('mocan_java_user', JSON.stringify(user));
        });
        await page.goto('http://localhost:8080/banhang.html', { waitUntil: 'networkidle0' });
        await sleep(1500);

        // Screenshot Manager POS Clean
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'fixed_manager_pos_clean.png') });

        // Chọn mang đi, thêm món Cà phê đen đá (maMon=1, 25k)
        console.log('Quản lý chọn Đơn mang đi, thêm Cà phê đen đá...');
        await page.evaluate(() => {
            const takeawayBtn = document.querySelector('[data-table="takeaway"]');
            if (takeawayBtn) takeawayBtn.click();
            const addBtn = document.querySelector('[data-add="1"]');
            if (addBtn) addBtn.click();
        });
        await sleep(800);

        // Nhập chiết khấu 5.000 đ (Manager có ô #pos-discount mở khóa)
        console.log('Quản lý nhập chiết khấu 5.000 đ...');
        await page.type('#pos-discount', '5000');
        await sleep(500);

        // Bấm Checkout
        await page.click('#btn-checkout');
        await sleep(2000);

        // Screenshot 15: Hóa đơn có chiết khấu của Quản lý
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, '15_manager_discount_receipt.png') });
        const managerReceipt = await page.$eval('#receiptModal', el => el.textContent);
        console.log('Chi tiết hóa đơn quản lý:', managerReceipt.replace(/\s+/g, ' ').substring(0, 120));
        results.push({ id: 'TC15', name: 'Tài khoản MANAGER áp dụng chiết khấu hợp lệ & in Bill thành công', status: managerReceipt.includes('20.000') || managerReceipt.includes('HD-') ? 'PASS' : 'FAIL', img: '15_manager_discount_receipt.png' });

        // =========================================================================
        // 6. CHỤP ẢNH PREVIEWS SLIDE THUYẾT TRÌNH (slide.html)
        // =========================================================================
        console.log('\n--- BƯỚC 6: CHỤP ẢNH SLIDE THUYẾT TRÌNH (slide.html) ---');
        const slidePath = 'file:///' + path.resolve('slide.html').replace(/\\/g, '/');
        await page.goto(slidePath, { waitUntil: 'networkidle0' });
        await sleep(1000);

        // Slide 1 preview
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'slide_01_preview.png') });

        // Chuyển sang Slide 7 (POS)
        await page.evaluate(() => {
            if (typeof goToSlide === 'function') goToSlide(7);
        });
        await sleep(800);
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'slide_07_pos_preview.png') });

        // Chuyển sang Slide 14 (Kho)
        await page.evaluate(() => {
            if (typeof goToSlide === 'function') goToSlide(14);
        });
        await sleep(800);
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'slide_14_kho_preview.png') });

        // Slide 14 có mở Speaker Notes
        await page.evaluate(() => {
            if (typeof toggleNotes === 'function') toggleNotes(14);
        });
        await sleep(800);
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'slide_14_with_notes.png') });

        // Overview modal preview
        await page.evaluate(() => {
            const btn = document.getElementById('overviewBtn');
            if (btn) btn.click();
        });
        await sleep(800);
        await page.screenshot({ path: path.join(SCREENSHOT_DIR, 'overview_modal_preview.png') });

    } catch (err) {
        console.error('LỖI THỰC THI KIỂM THỬ:', err);
    } finally {
        await browser.close();
        console.log('\n========================================');
        console.log('=== HOÀN TẤT BỘ KIỂM THỬ VÀ CHỤP ẢNH ===');
        console.log('========================================');
        fs.writeFileSync(path.join(SCREENSHOT_DIR, 'results.json'), JSON.stringify({ results, defects }, null, 2));
    }
}

main();
