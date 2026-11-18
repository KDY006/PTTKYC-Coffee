(function () {
  'use strict';

  var user;
  var ingredients = [];
  var orders = [];
  var dashboard = {};
  var lowOnly = false;
  var q = '';
  var target = null;
  var modal = null;

  function esc(x) {
    return MocAn.escapeHtml(x);
  }

  function low(x) {
    return Number(x.soLuongTon) <= Number(x.mucTonToiThieu);
  }

  function notice(id, text) {
    var e = document.getElementById(id);
    if (e) {
      e.innerHTML = esc(text);
      e.classList.remove('d-none');
    }
  }

  async function load() {
    try {
      user = await MocAn.loadSessionUser();

      // ROLE GUARD: Only Manager and Admin can access Inventory & KPI
      if (!MocAn.isPrivileged(user)) {
        alert('Tài khoản nhân viên (Staff) không có quyền truy cập trang Quản lý Kho & KPI! Đang chuyển hướng về Bán hàng...');
        window.location.href = 'banhang.html';
        return;
      }

      var r = await Promise.all([
        MocAn.apiFetch('/api/kho/nguyen-lieu'),
        MocAn.apiFetch('/api/order/danh-sach'),
        MocAn.apiFetch('/api/kho/dashboard')
      ]);

      ingredients = r[0] || [];
      orders = r[1] || [];
      dashboard = r[2] || {};
      render();
    } catch (e) {
      if (e.status === 401) {
        location.href = 'index.html';
      } else {
        notice('kho-error', e.message);
      }
    }
  }

  function render() {
    var lows = ingredients.filter(low);
    var canEdit = MocAn.isPrivileged(user);
    var shown = ingredients.filter(function (x) {
      return (!lowOnly || low(x)) && (!q || x.tenNL.toLowerCase().includes(q.toLowerCase()));
    });

    MocAn.renderAppShell({
      activeRoute: 'kho',
      user: user,
      lowStockCount: lows.length,
      onRefresh: load
    });

    document.getElementById('kpi-strip').innerHTML = [
      ['Doanh thu hôm nay', MocAn.formatVnd(dashboard.tongDoanhThu || 0)],
      ['Tổng đơn đã thanh toán', dashboard.tongDonHang || orders.length],
      ['Nguyên liệu cảnh báo', lows.length],
      ['Tổng nguyên liệu', ingredients.length]
    ].map(function (x) {
      return '<div class="col-12 col-sm-6 col-lg-3">' +
        '<div class="moss-card p-3">' +
          '<div style="font-size:.78rem;color:var(--text-secondary)">' + x[0] + '</div>' +
          '<div class="font-display fw-bold mt-2" style="font-size:1.45rem">' + x[1] + '</div>' +
        '</div>' +
      '</div>';
    }).join('');

    document.getElementById('low-filter-count').textContent = lows.length;
    document.getElementById('btn-filter-low').classList.toggle('btn-danger', lowOnly);
    document.getElementById('add-ing-btn-wrap').innerHTML = '<span class="badge-muted">Định mức công thức tự động theo món</span>';

    var banner = document.getElementById('low-stock-banner');
    if (banner) {
      banner.className = lows.length ? '' : 'd-none';
      banner.innerHTML = lows.length
        ? '<div class="alert alert-danger mb-0 d-flex align-items-center gap-2"><i class="bi bi-exclamation-triangle-fill"></i><span>Cảnh báo sắp hết hàng: <strong>' +
          esc(lows.map(function (x) { return x.tenNL + ' (' + x.soLuongTon + ' ' + x.donViTinh + ')'; }).join(', ')) +
          '</strong></span></div>'
        : '';
    }

    document.getElementById('ing-cat-tabs').innerHTML = '<span class="text-muted" style="font-size:.8rem">Dữ liệu tồn kho thời gian thực liên kết công thức BOM</span>';

    document.getElementById('ingredients-tbody').innerHTML = shown.map(function (x) {
      var isLow = low(x);
      return '<tr class="' + (isLow ? 'row-low-stock' : '') + '">' +
        '<td class="fw-semibold">' + esc(x.tenNL) + '</td>' +
        '<td>' + esc(MocAn.inferIngredientCategory(x.tenNL)) + '</td>' +
        '<td class="text-end fw-bold ' + (isLow ? 'text-danger' : 'text-dark') + '">' + x.soLuongTon + ' ' + esc(x.donViTinh) + '</td>' +
        '<td class="text-end text-muted">≤ ' + x.mucTonToiThieu + ' ' + esc(x.donViTinh) + '</td>' +
        '<td>' + (isLow ? '<span class="badge bg-danger">Sắp hết hàng</span>' : '<span class="badge bg-success">Đủ hàng</span>') + '</td>' +
        '<td class="text-end font-mono">' + MocAn.formatVnd(x.donGiaNhap) + '</td>' +
        '<td class="text-end">' + (canEdit ? '<button class="btn btn-sm btn-outline-primary" data-restock="' + x.maNL + '"><i class="bi bi-box-arrow-in-down me-1"></i>Nhập kho</button>' : '') + '</td>' +
      '</tr>';
    }).join('') || '<tr><td colspan="7" class="text-center py-4 text-muted">Không có nguyên liệu phù hợp.</td></tr>';

    var top = {};
    orders.forEach(function (o) {
      (o.danhSachChiTiet || []).forEach(function (d) {
        var m = d.monNuoc;
        if (!top[m.maMon]) top[m.maMon] = { name: m.tenMon, qty: 0 };
        top[m.maMon].qty += d.soLuong;
      });
    });

    document.getElementById('top-selling-list').innerHTML = Object.values(top)
      .sort(function (a, b) { return b.qty - a.qty; })
      .slice(0, 6)
      .map(function (x) {
        return '<div class="d-flex justify-content-between align-items-center py-1 border-bottom"><b>' + esc(x.name) + '</b><span class="badge bg-light text-dark">' + x.qty + ' ly</span></div>';
      }).join('') || '<p class="text-muted small">Chưa có hóa đơn bán ra hôm nay.</p>';

    document.getElementById('deduction-log-list').innerHTML = orders.slice(0, 8).map(function (o) {
      return '<div class="p-2 border rounded-3 mb-2 bg-light"><b>Đơn HD-' + o.maHoaDon + '</b><br><small class="text-muted">' +
        esc((o.danhSachChiTiet || []).map(function (d) { return d.soLuong + ' ' + d.monNuoc.tenMon; }).join(', ')) +
        '</small></div>';
    }).join('') || '<p class="text-muted small">Chưa có dữ liệu xuất kho.</p>';
  }

  document.getElementById('btn-filter-low').addEventListener('click', function () {
    lowOnly = !lowOnly;
    render();
  });

  document.getElementById('ing-search').addEventListener('input', function (e) {
    q = e.target.value;
    render();
  });

  document.getElementById('ingredients-tbody').addEventListener('click', function (e) {
    var b = e.target.closest('[data-restock]');
    if (!b) return;
    target = ingredients.find(function (x) { return String(x.maNL) === b.dataset.restock; });
    if (!target) return;
    document.getElementById('restock-modal-title').textContent = 'Nhập kho · ' + target.tenNL;
    document.getElementById('restock-modal-info').textContent = 'Tồn hiện tại: ' + target.soLuongTon + ' ' + target.donViTinh;
    document.getElementById('restock-amount').value = '';
    modal.show();
  });

  document.getElementById('restock-form').addEventListener('submit', async function (e) {
    e.preventDefault();
    try {
      var amount = Number(document.getElementById('restock-amount').value);
      if (!amount || amount <= 0) {
        alert('Số lượng nhập phải lớn hơn 0');
        return;
      }
      await MocAn.apiFetch('/api/kho/nhap-kho', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ maNL: target.maNL, soLuongNhap: amount })
      });
      modal.hide();
      notice('kho-feedback', 'Đã nhập bổ sung ' + amount + ' ' + target.donViTinh + ' ' + target.tenNL + ' thành công.');
      load();
    } catch (x) {
      notice('kho-error', x.message);
    }
  });

  modal = new bootstrap.Modal(document.getElementById('restockModal'));
  load();
})();
