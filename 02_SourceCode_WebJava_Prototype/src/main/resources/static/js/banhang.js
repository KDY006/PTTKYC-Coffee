'use strict';
// UI giữ bản nháp; server quyết định giá, quyền và trạng thái thực tế.
const $=id=>document.getElementById(id);
const money=n=>Number(n||0).toLocaleString('vi-VN')+' đ';
const escapeHtml=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
const storageKey='cafePosDraftV2';
let tables=[],menu=[],user=null,cart=[],table=null,type='TAI_BAN',order=null,dirty=false,busy=false;
let createKey=crypto.randomUUID(),paymentKey=crypto.randomUUID(),paymentUncertain=false,saveUncertain=false;
let paymentModal,receiptModal;
function persist(){sessionStorage.setItem(storageKey,JSON.stringify({employee:user?.maNV,cart,table,type,order,dirty,createKey,paymentKey,paymentUncertain,saveUncertain}));}
function notice(msg,ok=false){$('message').className='alert '+(ok?'alert-success':'alert-danger');$('message').textContent=msg;}
async function api(url,options={}){
    const response=await fetch(url,{...options,headers:{'Content-Type':'application/json',...options.headers}});
    const text=await response.text();let data=null;try{data=text?JSON.parse(text):null;}catch{throw Error('Máy chủ trả dữ liệu không hợp lệ. Kiểm tra lại đơn trước khi thử tiếp.');}
    if(response.status===401){localStorage.removeItem('currentUser');location.href='index.html';throw Error('Vui lòng đăng nhập lại');}
    if(!response.ok){const error=Error(data?.error||data?.message||'Không thực hiện được yêu cầu ('+response.status+')');error.status=response.status;throw error;}
    return data;
}
const send=(method,data)=>({method,body:JSON.stringify(data)});
async function task(work){if(busy)return;busy=true;$('workspace').disabled=true;$('logoutBtn').disabled=true;try{await work();}catch(e){notice(e.message);}finally{busy=false;$('workspace').disabled=false;$('logoutBtn').disabled=false;renderCart();}}
function subtotal(){return cart.reduce((s,i)=>s+i.donGia*i.soLuong,0);}
function reset(){cart=[];table=null;type='TAI_BAN';order=null;dirty=false;createKey=crypto.randomUUID();paymentKey=crypto.randomUUID();paymentUncertain=false;saveUncertain=false;persist();renderAll();}
function applyOrder(h){order=h;type=h.loaiDon;table=h.ban;cart=h.danhSachChiTiet.map(l=>({maMon:l.monNuoc.maMon,tenMon:l.monNuoc.tenMon,donGia:l.donGiaBan,soLuong:l.soLuong,ghiChu:l.ghiChu||''}));dirty=false;createKey=h.yeuCauTao||crypto.randomUUID();paymentKey=h.yeuCauThanhToan||crypto.randomUUID();paymentUncertain=false;saveUncertain=false;persist();renderAll();}
function canLeave(){if(saveUncertain){notice('Chưa rõ kết quả lưu. Bấm Tải lại để kiểm tra trước.');return false;}if(paymentUncertain){notice('Chưa rõ kết quả thanh toán. Bấm Tải lại để kiểm tra đơn trước.');return false;}return !dirty||confirm('Có thay đổi chưa lưu. Bỏ bản nháp và chuyển đơn?');}
function editable(){if(saveUncertain){notice('Chưa rõ kết quả lưu. Hãy Tải lại trước.');return false;}if(paymentUncertain){notice('Cần tải lại trạng thái sau lần thanh toán chưa rõ kết quả.');return false;}return !order||order.trangThai==='CHO_THANH_TOAN';}
function changed(){dirty=true;paymentKey=crypto.randomUUID();persist();renderCart();}
async function refresh(){tables=await api('/api/ban');renderTables();await history();}
function renderTables(){
    $('tableList').replaceChildren();
    for(const b of tables){const col=document.createElement('div');col.className='col';const btn=document.createElement('button');btn.type='button';btn.className='btn w-100 table-btn '+(b.trangThai==='CO_KHACH'?'btn-outline-danger':'btn-outline-success')+(type==='TAI_BAN'&&table?.maBan===b.maBan?' selected-table':'');btn.textContent=b.tenBan+' - '+(b.trangThai==='CO_KHACH'?'Có khách':'Trống');btn.addEventListener('click',()=>task(()=>selectTable(b)));col.append(btn);$('tableList').append(col);}
    $('dineBtn').className='btn btn-sm '+(type==='TAI_BAN'?'btn-primary':'btn-outline-primary');
    $('takeBtn').className='btn btn-sm '+(type==='MANG_DI'?'btn-primary':'btn-outline-primary');
}
async function selectTable(b){
    if(type==='TAI_BAN'&&table?.maBan===b.maBan)return;
    if(!canLeave())return;
    const h=await api('/api/order/theo-ban/'+b.maBan);
    if(h)applyOrder(h);else{reset();table=b;persist();renderAll();if(b.trangThai==='CO_KHACH')notice('Bàn đang đánh dấu có khách nhưng chưa có đơn mở. Bạn có thể lưu đơn mới cho bàn này.');}
}
function renderMenu(){const q=$('search').value.trim().toLocaleLowerCase('vi');const cat=$('category').value;$('menuList').replaceChildren();
    const items=menu.filter(m=>(!cat||m.danhMuc===cat)&&m.tenMon.toLocaleLowerCase('vi').includes(q));
    for(const m of items){const col=document.createElement('div');col.className='col';const btn=document.createElement('button');btn.type='button';btn.className='btn btn-light border w-100 menu-card';btn.innerHTML='<strong>'+escapeHtml(m.tenMon)+'</strong><div class="text-danger">'+money(m.donGia)+'</div><small class="text-muted">'+escapeHtml(m.danhMuc)+'</small>';btn.addEventListener('click',()=>add(m));col.append(btn);$('menuList').append(col);}if(!items.length)$('menuList').textContent='Không tìm thấy món.';
}
function add(m){if(busy||!editable())return;if(type==='TAI_BAN'&&!table){notice('Hãy chọn bàn hoặc chuyển sang Mang đi trước khi gọi món.');return;}const item=cart.find(l=>l.maMon===m.maMon);if(item){if(item.soLuong>=99){notice('Tối đa 99 ly mỗi dòng.');return;}item.soLuong++;}else cart.push({maMon:m.maMon,tenMon:m.tenMon,donGia:m.donGia,soLuong:1,ghiChu:''});changed();}
function renderCart(){
    $('orderTitle').textContent=order?'ĐƠN #'+order.maHoaDon:'GIỎ HÀNG MỚI';$('context').textContent=type==='MANG_DI'?'Mang đi':table?.tenBan||'Chưa chọn bàn';
    $('orderStatus').textContent=order?(order.trangThai==='CHO_THANH_TOAN'?'Đang phục vụ':'Đơn đã kết thúc')+(dirty?' - Có thay đổi chưa lưu':' - Đã lưu'):(dirty?'Bản nháp chưa lưu':'Chọn bàn hoặc Mang đi để bắt đầu');
    $('cartItems').replaceChildren();if(!cart.length)$('cartItems').textContent='Chưa có món nào được chọn.';
    cart.forEach((i,index)=>{const row=document.createElement('div');row.className='border-bottom py-2';row.innerHTML='<div class="d-flex justify-content-between gap-2"><strong>'+escapeHtml(i.tenMon)+'</strong><span>'+money(i.donGia*i.soLuong)+'</span></div><small class="text-muted">'+money(i.donGia)+'/món</small><div class="d-flex gap-2 align-items-center mt-1"><button class="btn btn-sm btn-outline-secondary minus" aria-label="Giảm số lượng">-</button><strong>'+i.soLuong+'</strong><button class="btn btn-sm btn-outline-secondary plus" aria-label="Tăng số lượng">+</button><button class="btn btn-sm btn-outline-danger remove">Hủy món</button></div><input class="form-control form-control-sm notes mt-2" maxlength="255" placeholder="Ghi chú pha chế" aria-label="Ghi chú pha chế">';
        row.querySelector('.notes').value=i.ghiChu;
        row.querySelector('.notes').addEventListener('input',e=>{if(!editable())return;i.ghiChu=e.target.value;dirty=true;persist();$('orderStatus').textContent='Có thay đổi ghi chú chưa lưu';});
        const remove=()=>{if(order&&!confirm('Xác nhận đã trao đổi với pha chế và muốn hủy món?'))return;cart.splice(index,1);changed();};
        row.querySelector('.minus').onclick=()=>{if(!editable())return;if(i.soLuong===1)remove();else{i.soLuong--;changed();}};
        row.querySelector('.plus').onclick=()=>{if(editable()&&i.soLuong<99){i.soLuong++;changed();}};
        row.querySelector('.remove').onclick=()=>{if(editable())remove();};
        if(order&&order.trangThai!=='CHO_THANH_TOAN')row.querySelectorAll('button,input').forEach(e=>e.disabled=true);
        $('cartItems').append(row);
    });
    $('total').textContent=money(subtotal());const closed=order&&order.trangThai!=='CHO_THANH_TOAN';
    $('saveBtn').disabled=busy||closed||paymentUncertain||saveUncertain||!cart.length||(!dirty&&!!order)||(type==='TAI_BAN'&&!table);
    $('payBtn').disabled=busy||closed||paymentUncertain||saveUncertain||!cart.length||(type==='TAI_BAN'&&!table);
    $('cancelBtn').disabled=busy||closed||paymentUncertain||saveUncertain||(!order&&!cart.length);
}
function renderAll(){renderTables();renderCart();}
function orderPayload(){return {maBan:type==='TAI_BAN'?table?.maBan:null,loaiDon:type,danhSachMon:cart.map(i=>({maMon:i.maMon,soLuong:i.soLuong,ghiChu:i.ghiChu})),yeuCauTao:createKey,phienBan:order?.phienBan};}
async function save(){
    if(!cart.length)throw Error('Đơn trống: thêm món hoặc hủy đơn.');if(type==='TAI_BAN'&&!table)throw Error('Hãy chọn bàn.');if(!dirty&&order)return;
    persist();
    try {
        const h=await api(order?'/api/order/'+order.maHoaDon+'/mon':'/api/order/tao-don',send(order?'PUT':'POST',orderPayload()));
        applyOrder(h);notice('Đã lưu đơn #'+h.maHoaDon+'. Bàn và món đã được ghi nhận.',true);await refresh().catch(e=>notice('Đơn đã lưu, nhưng chưa tải lại được danh sách: '+e.message));
    } catch(e) {
        // Không cho sửa bản nháp khi chưa biết POST/PUT đã commit hay chưa.
        if(!e.status){saveUncertain=true;persist();}
        throw e;
    }
}
async function history(){const all=await api('/api/order/danh-sach');$('orderList').replaceChildren();
    for(const h of all.slice(0,60)){const btn=document.createElement('button');btn.className='list-group-item list-group-item-action small';btn.textContent='#'+h.maHoaDon+' - '+(h.ban?.tenBan||'Mang đi')+' - '+h.trangThai+' - '+money(h.tongTienThanhToan);btn.onclick=()=>task(async()=>{if(!canLeave())return;const fresh=await api('/api/order/'+h.maHoaDon);if(fresh.trangThai==='DA_THANH_TOAN'){showReceipt(fresh);return;}if(fresh.trangThai==='DA_HUY'){notice('Đơn đã hủy.');return;}applyOrder(fresh);});$('orderList').append(btn);}
}
async function openPayment(){await save();$('payOrder').textContent='Đơn #'+order.maHoaDon+' - '+(order.ban?.tenBan||'Mang đi');$('discount').value=0;$('discount').disabled=user.vaiTro==='STAFF';$('method').value='TIEN_MAT';$('cash').value=order.tongTienGoc;$('reference').value='';$('confirmed').checked=false;$('payError').textContent='';paymentKey=crypto.randomUUID();persist();paymentValues();paymentModal.show();}
function paymentValues(){const discount=Number($('discount').value),total=order?.tongTienGoc||0;const valid=Number.isSafeInteger(discount)&&discount>=0&&discount<=total;const due=total-discount;$('due').textContent=valid?money(due):'Chiết khấu không hợp lệ';$('change').textContent=valid?money(Math.max(0,Number($('cash').value)-due)):'-';const transfer=$('method').value==='CHUYEN_KHOAN';$('cashSection').hidden=transfer;$('transferSection').hidden=!transfer;}
async function pay(){
    if(busy)return;const discount=Number($('discount').value),cash=Number($('cash').value),method=$('method').value;
    if(!Number.isSafeInteger(discount)||discount<0||discount>order.tongTienGoc){$('payError').textContent='Chiết khấu phải là số đồng nguyên từ 0 đến tổng tiền.';return;}
    if(method==='TIEN_MAT'&&(!Number.isSafeInteger(cash)||cash<order.tongTienGoc-discount)){$('payError').textContent='Tiền khách đưa chưa đủ hoặc không hợp lệ.';return;}
    if(method==='CHUYEN_KHOAN'&&(!$('confirmed').checked||!$('reference').value.trim())){$('payError').textContent='Cần mã tham chiếu và xác nhận tiền vào tài khoản.';return;}
    // Persist before POST: response lost/reload can be reconciled using the saved order ID.
    paymentUncertain=true;persist();$('confirmPay').disabled=true;$('closePay').disabled=true;
    await task(async()=>{
        try {
            const paid=await api('/api/order/'+order.maHoaDon+'/thanh-toan',send('POST',{phienBan:order.phienBan,chietKhau:discount,phuongThucTT:method,tienKhachDua:cash,xacNhanChuyenKhoan:$('confirmed').checked,maThamChieu:$('reference').value.trim(),yeuCauThanhToan:paymentKey}));
            paymentUncertain=false;paymentModal.hide();reset();showReceipt(paid);notice('Thanh toán thành công. Hóa đơn đã lưu; có thể in lại từ lịch sử.',true);await refresh().catch(e=>notice('Thanh toán đã thành công, nhưng chưa tải lại được danh sách: '+e.message));
        } catch(e) {
            // GET resolves even an HTTP failure that occurred after the database committed.
            try {const current=await api('/api/order/'+order.maHoaDon);paymentUncertain=false;if(current.trangThai==='DA_THANH_TOAN'){paymentUncertain=false;paymentModal.hide();reset();showReceipt(current);await refresh().catch(e=>notice('Hóa đơn đã thanh toán, chưa tải lại được danh sách: '+e.message));return;}applyOrder(current);}
            catch{paymentUncertain=true;persist();}
            $('payError').textContent=e.message+(paymentUncertain?' Chưa rõ kết quả; đóng hộp thoại và Tải lại trước khi tiếp tục.':' Đơn vẫn chưa thanh toán.');
        }
    });
    $('confirmPay').disabled=false;$('closePay').disabled=false;
}
function showReceipt(h){
    const date=h.thoiGianThanhToan||h.thoiGianTao;const received=h.tienKhachDua;
    $('receiptBody').innerHTML='<h3 class="text-center">COFFEE POS</h3><p>HÓA ĐƠN BÁN HÀNG #'+h.maHoaDon+'<br>'+escapeHtml(h.ban?.tenBan||'Mang đi')+'<br>Thu ngân: '+escapeHtml(h.nhanVien?.hoTen)+'<br>'+escapeHtml(date?new Date(date).toLocaleString('vi-VN'):'')+'</p><table class="w-100"><thead><tr><th>Món</th><th>SL</th><th>Đơn giá</th><th>Thành tiền</th></tr></thead><tbody>'+h.danhSachChiTiet.map(l=>'<tr><td>'+escapeHtml(l.monNuoc.tenMon)+(l.ghiChu?'<br><small>'+escapeHtml(l.ghiChu)+'</small>':'')+'</td><td>'+l.soLuong+'</td><td>'+money(l.donGiaBan)+'</td><td>'+money(l.thanhTien)+'</td></tr>').join('')+'</tbody></table><p class="mt-3">Tạm tính: '+money(h.tongTienGoc)+'<br>Chiết khấu: '+money(h.chietKhau)+'<br><strong>Tổng: '+money(h.tongTienThanhToan)+'</strong><br>Phương thức: '+(h.phuongThucTT==='TIEN_MAT'?'Tiền mặt':'Chuyển khoản - xác nhận thủ công')+'<br>Khách đưa: '+(received==null?'Không ghi nhận (hóa đơn cũ)':money(received))+'<br>Tiền thừa: '+(received==null?'Không ghi nhận':money(received-h.tongTienThanhToan))+(h.maThamChieu?'<br>Tham chiếu: '+escapeHtml(h.maThamChieu):'')+'</p><p class="text-center small">Cảm ơn quý khách!<br>Bill nội bộ, không phải hóa đơn thuế.</p>';
    receiptModal.show();
}
async function reloadCurrent(){if(dirty&&!paymentUncertain&&!saveUncertain&&!confirm('Tải lại sẽ bỏ thay đổi chưa lưu. Tiếp tục?'))return;if(order){const h=await api('/api/order/'+order.maHoaDon);if(h.trangThai==='DA_THANH_TOAN'){reset();showReceipt(h);}else if(h.trangThai==='DA_HUY'){reset();notice('Đơn đã hủy.');}else applyOrder(h);}else {const h=await api('/api/order/theo-yeu-cau/'+createKey);if(h)applyOrder(h);else {saveUncertain=false;persist();if(table){const existing=await api('/api/order/theo-ban/'+table.maBan);if(existing)applyOrder(existing);}}}await refresh();}
async function cancel(){if(!editable())return;if(!confirm(order?'Hủy toàn bộ đơn đã lưu và trả bàn về trống?':'Xóa toàn bộ giỏ nháp?'))return;if(order)await api('/api/order/'+order.maHoaDon+'/huy',send('POST',{phienBan:order.phienBan}));reset();await refresh();notice('Đã hủy/xóa đơn.',true);}
async function init(){
    try{const cu=JSON.parse(localStorage.getItem('currentUser')||'null');if(cu?.vaiTro==='STAFF'){$('navKho')?.classList.add('d-none');$('navNhanSu')?.classList.add('d-none');}}catch{}
    if(!window.bootstrap){notice('Không tải được Bootstrap. Kiểm tra kết nối mạng rồi tải lại trang.');return;}
    paymentModal=new bootstrap.Modal($('paymentModal'));
    $('paymentModal').addEventListener('hide.bs.modal',e=>{if(busy&&paymentUncertain)e.preventDefault();});
    receiptModal=new bootstrap.Modal($('receiptModal'));
    try{user=await api('/api/auth/me');if(user.vaiTro==='STAFF'){$('navKho')?.classList.add('d-none');$('navNhanSu')?.classList.add('d-none');}const savedUser=JSON.parse(localStorage.getItem('currentUser')||'null');$('userName').textContent=(savedUser?.maNV===user.maNV?savedUser.hoTen:'Nhân viên #'+user.maNV)+' | '+user.vaiTro;menu=await api('/api/mon-nuoc');for(const cat of [...new Set(menu.map(m=>m.danhMuc))]){const opt=document.createElement('option');opt.value=cat;opt.textContent=cat;$('category').append(opt);}renderMenu();
        let draft;try{draft=JSON.parse(sessionStorage.getItem(storageKey));}catch{}
        if(draft?.employee===user.maNV){({cart,table,type,order,dirty,createKey,paymentKey,paymentUncertain,saveUncertain=false}=draft);if(order){const fresh=await api('/api/order/'+order.maHoaDon);if(fresh.trangThai==='DA_THANH_TOAN'){reset();showReceipt(fresh);}else if(fresh.trangThai==='DA_HUY'){reset();}else if(!dirty||fresh.phienBan!==order.phienBan){applyOrder(fresh);}else {paymentUncertain=false;saveUncertain=false;persist();}}else if(saveUncertain){const recovered=await api('/api/order/theo-yeu-cau/'+createKey);if(recovered)applyOrder(recovered);else {saveUncertain=false;persist();}}}
        await refresh();renderAll();
    }catch(e){notice(e.message);}
}
$('search').oninput=renderMenu;$('category').onchange=renderMenu;
$('saveBtn').onclick=()=>task(save);$('payBtn').onclick=()=>task(openPayment);$('cancelBtn').onclick=()=>task(cancel);$('reloadBtn').onclick=()=>task(reloadCurrent);$('historyBtn').onclick=()=>task(history);
$('takeBtn').onclick=()=>{if(busy||!canLeave())return;reset();type='MANG_DI';persist();renderAll();};$('dineBtn').onclick=()=>{if(busy||type==='TAI_BAN'||!canLeave())return;reset();};
$('discount').oninput=paymentValues;$('cash').oninput=paymentValues;$('method').onchange=paymentValues;$('confirmPay').onclick=pay;$('printBtn').onclick=()=>window.print();
$('logoutBtn').onclick=()=>task(async()=>{if(!canLeave())return;await api('/api/auth/logout',{method:'POST'});localStorage.removeItem('currentUser');sessionStorage.removeItem(storageKey);location.href='index.html';});
window.addEventListener('beforeunload',e=>{if(dirty){e.preventDefault();e.returnValue='';}});
init();
