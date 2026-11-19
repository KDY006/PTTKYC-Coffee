(function () {
  'use strict';

  var user;
  var shifts = [];
  var attendance = [];
  var staff = [];
  var modal = null;

  function esc(x) {
    return MocAn.escapeHtml(x);
  }

  function role() {
    return String(user && user.vaiTro || '').toUpperCase();
  }

  function privileged() {
    return role() === 'ADMIN' || role() === 'MANAGER';
  }

  function msg(id, text) {
    var e = document.getElementById(id);
    if (e) {
      e.textContent = text;
      e.classList.remove('d-none');
    }
  }

  async function load() {
    try {
      user = await MocAn.loadSessionUser();

      // ROLE GUARD: Staff cannot access HR Management Page
      if (!privileged()) {
        alert('Tài khoản nhân viên (Staff) không có quyền truy cập trang Quản lý Nhân sự! Đang chuyển hướng về Bán hàng...');
        window.location.href = 'banhang.html';
        return;
      }

      var today = new Date().toISOString().slice(0, 10);
      var qs = '?tuNgay=' + today + '&denNgay=' + today;

      var r = await Promise.all([
        MocAn.apiFetch('/api/ca-lam'),
        MocAn.apiFetch('/api/cham-cong/lich-su' + qs),
        privileged() ? MocAn.apiFetch('/api/nhan-su/nhan-vien') : Promise.resolve([]),
        MocAn.apiFetch('/api/kho/nguyen-lieu')
      ]);

      shifts = r[0] || [];
      attendance = r[1] || [];
      staff = r[2] || [];
      render(r[3] || []);
    } catch (e) {
      if (e.status === 401) {
        location.href = 'index.html';
      } else {
        msg('hr-error', e.message);
      }
    }
  }

  function render(ings) {
    var lowStockCount = ings.filter(function (x) {
      return Number(x.soLuongTon) <= Number(x.mucTonToiThieu);
    }).length;

    MocAn.renderAppShell({
      activeRoute: 'nhansu',
      user: user,
      lowStockCount: lowStockCount,
      onRefresh: load
    });

    document.getElementById('hr-role-eyebrow').textContent = role() === 'ADMIN'
      ? 'Quản trị nhân sự & Chấm công toàn quyền'
      : 'Quản lý ca kíp & Chấm công cửa hàng';

    document.getElementById('hr-role-sub').textContent = 'Phiên làm việc: ' + esc(user.hoTen) + ' (' + role() + ')';

    document.getElementById('att-staff-wrap').innerHTML =
      '<div class="form-control moss-input bg-light fw-medium">' + esc(user.hoTen || user.tenDangNhap) + '</div>';

    document.getElementById('att-shift-select').innerHTML = shifts.map(function (s) {
      return '<option value="' + s.maCa + '">' + esc(s.tenCa) + ' (' + String(s.gioBatDau).slice(0, 5) + ' - ' + String(s.gioKetThuc).slice(0, 5) + ')</option>';
    }).join('') || '<option value="">Chưa có ca làm</option>';

    var workingCount = attendance.filter(function (a) { return !a.gioCheckOut; }).length;

    document.getElementById('hr-kpi-strip').innerHTML = [
      ['Ca làm việc', shifts.length],
      ['Đã check-in', attendance.length],
      ['Đang làm việc', workingCount],
      ['Tổng nhân sự', staff.length || 1]
    ].map(function (x) {
      return '<div class="col-6 col-lg-3">' +
        '<div class="moss-card p-3">' +
          '<div style="font-size:0.78rem;color:var(--text-secondary);">' + x[0] + '</div>' +
          '<div class="font-display fw-bold fs-3 mt-1">' + x[1] + '</div>' +
        '</div>' +
      '</div>';
    }).join('');

    document.getElementById('attendance-tbody').innerHTML = attendance.map(function (a) {
      var n = a.nhanVien || {};
      var c = a.caLam || {};
      var working = !a.gioCheckOut;
      var isLate = a.trangThai === 'DI_TRE';
      var statusBadge = isLate
        ? '<span class="badge bg-danger">Đi trễ</span>'
        : '<span class="badge bg-success">Đúng giờ</span>';

      return '<tr>' +
        '<td><div class="fw-semibold">' + esc(n.hoTen) + '</div><small class="text-muted">' + esc(n.tenDangNhap) + '</small></td>' +
        '<td><span class="badge bg-light text-dark border">' + esc(n.vaiTro) + '</span></td>' +
        '<td>' + esc(c.tenCa) + '</td>' +
        '<td>' + esc(a.ngayLam) + '</td>' +
        '<td class="font-mono">' + MocAn.formatTimeShort(a.gioCheckIn) + '</td>' +
        '<td class="font-mono">' + (a.gioCheckOut ? MocAn.formatTimeShort(a.gioCheckOut) : '<span class="text-primary fw-medium">Đang trong ca</span>') + '</td>' +
        '<td>' + statusBadge + '</td>' +
        '<td class="text-end">' +
          (working && (privileged() || n.maNV === user.maNV)
            ? '<button class="btn btn-sm btn-outline-danger" data-out="' + a.maChamCong + '"><i class="bi bi-box-arrow-right me-1"></i>Check-out</button>'
            : '<span class="text-muted small">Đã xong</span>') +
        '</td>' +
      '</tr>';
    }).join('') || '<tr><td colspan="8" class="text-center py-4 text-muted">Chưa có lượt chấm công nào hôm nay.</td></tr>';

    var sec = document.getElementById('staff-directory-section');
    if (sec) {
      sec.classList.toggle('d-none', !privileged());
      if (privileged()) {
        var addBtn = document.getElementById('btn-open-add-staff');
        if (addBtn) addBtn.classList.toggle('d-none', role() !== 'ADMIN');

        document.getElementById('staff-tbody').innerHTML = staff.map(function (s) {
          var roleBadgeClass = s.vaiTro === 'ADMIN' ? 'bg-dark' : (s.vaiTro === 'MANAGER' ? 'bg-primary' : 'bg-success');
          var statusBadgeClass = s.trangThai === 'DANG_LAM' ? 'text-success' : 'text-danger';
          return '<tr>' +
            '<td class="fw-semibold">' + esc(s.hoTen) + '</td>' +
            '<td><code>' + esc(s.tenDangNhap) + '</code></td>' +
            '<td><span class="badge ' + roleBadgeClass + '">' + esc(s.vaiTro) + '</span></td>' +
            '<td>' + esc(s.soDienThoai || '--') + '</td>' +
            '<td><span class="fw-medium ' + statusBadgeClass + '">' + (s.trangThai === 'DANG_LAM' ? 'Đang làm việc' : 'Đã nghỉ việc') + '</span></td>' +
          '</tr>';
        }).join('') || '<tr><td colspan="5" class="text-center py-4 text-muted">Chưa có nhân viên.</td></tr>';
      }
    }
  }

  document.getElementById('btn-checkin').addEventListener('click', async function () {
    try {
      var shiftVal = Number(document.getElementById('att-shift-select').value);
      if (!shiftVal) {
        alert('Vui lòng chọn ca làm việc');
        return;
      }
      await MocAn.apiFetch('/api/cham-cong/check-in', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          maCa: shiftVal,
          ghiChu: document.getElementById('att-note-input').value
        })
      });
      msg('hr-notice', 'Check-in thành công!');
      load();
    } catch (e) {
      msg('hr-error', e.message);
    }
  });

  document.getElementById('attendance-tbody').addEventListener('click', async function (e) {
    var b = e.target.closest('[data-out]');
    if (!b) return;
    try {
      await MocAn.apiFetch('/api/cham-cong/check-out/' + b.dataset.out, { method: 'POST' });
      msg('hr-notice', 'Đã check-out kết thúc ca làm việc.');
      load();
    } catch (x) {
      msg('hr-error', x.message);
    }
  });

  var btnAddStaff = document.getElementById('btn-open-add-staff');
  if (btnAddStaff) {
    btnAddStaff.addEventListener('click', function () {
      if (role() === 'ADMIN' && modal) modal.show();
    });
  }

  var formAddStaff = document.getElementById('add-staff-form');
  if (formAddStaff) {
    formAddStaff.addEventListener('submit', async function (e) {
      e.preventDefault();
      try {
        await MocAn.apiFetch('/api/nhan-su/nhan-vien', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            tenDangNhap: document.getElementById('new-staff-username').value.trim(),
            hoTen: document.getElementById('new-staff-fullname').value.trim(),
            vaiTro: document.getElementById('new-staff-role').value,
            soDienThoai: document.getElementById('new-staff-phone').value.trim(),
            matKhau: document.getElementById('new-staff-password').value,
            trangThai: 'DANG_LAM'
          })
        });
        if (modal) modal.hide();
        load();
      } catch (x) {
        var errEl = document.getElementById('add-staff-error');
        if (errEl) {
          errEl.textContent = x.message;
          errEl.classList.remove('d-none');
        }
      }
    });
  }

  var liveClockEl = document.getElementById('live-clock');
  if (liveClockEl) {
    setInterval(function () {
      liveClockEl.textContent = new Date().toLocaleTimeString('vi-VN');
    }, 1000);
  }

  var modalEl = document.getElementById('addStaffModal');
  if (modalEl) {
    modal = new bootstrap.Modal(modalEl);
  }

  load();
})();
