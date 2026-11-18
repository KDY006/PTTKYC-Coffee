(function () {
  'use strict';

  var user;
  var state = { tables: [], menu: [], orders: [], ingredients: [] };
  var selected = 'takeaway';
  var area = 'Tất cả';
  var category = 'Tất cả';
  var query = '';
  var payment = 'TIEN_MAT';
  var discount = 0;
  var carts = MocAn.loadTableCarts();
  var modal = null;

  function id() {
    return crypto.randomUUID ? crypto.randomUUID() : (Date.now() + '-' + Math.random());
  }

  function esc(v) {
    return MocAn.escapeHtml(v);
  }

  function items(o) {
    return (o.danhSachChiTiet || []).map(function (x) {
      return {
        maMon: x.monNuoc.maMon,
        name: x.monNuoc.tenMon,
        price: x.donGiaBan,
        quantity: x.soLuong,
        note: x.ghiChu || ''
      };
    });
  }

  function allTables() {
    return state.tables.map(function (t) {
      return {
        key: String(t.maBan),
        id: t.maBan,
        name: t.tenBan,
        area: t.viTri || 'Khu vực',
        status: t.trangThai
      };
    }).concat([{ key: 'takeaway', id: null, name: 'Mang đi', area: 'Mang đi', status: 'TRONG' }]);
  }

  function table() {
    return allTables().find(function (t) { return t.key === selected; }) || allTables()[0];
  }

  function cart() {
    return carts[selected] || [];
  }

  function saveCart(value) {
    carts[selected] = value;
    MocAn.saveTableCarts(carts);
    render();
  }

  function total() {
    return cart().reduce(function (s, x) {
      return s + Number(x.price) * Number(x.quantity);
    }, 0);
  }

  function orderPayload() {
    var t = table();
    return {
      maBan: t.id,
      loaiDon: t.id ? 'TAI_BAN' : 'MANG_DI',
      danhSachMon: cart().map(function (x) {
        return {
          maMon: x.maMon,
          soLuong: x.quantity,
          ghiChu: x.note || ''
        };
      })
    };
  }

  function err(message) {
    var e = document.getElementById('pos-error');
    if (e) {
      e.textContent = message;
      e.classList.remove('d-none');
    }
  }

  function clearErr() {
    var e = document.getElementById('pos-error');
    if (e) e.classList.add('d-none');
  }

  async function load() {
    try {
      user = await MocAn.loadSessionUser();
      var r = await Promise.all([
        MocAn.apiFetch('/api/ban'),
        MocAn.apiFetch('/api/mon-nuoc'),
        MocAn.apiFetch('/api/order/danh-sach'),
        MocAn.apiFetch('/api/kho/nguyen-lieu')
      ]);
      state.tables = r[0] || [];
      state.menu = r[1] || [];
      state.orders = r[2] || [];
      state.ingredients = r[3] || [];
      render();
    } catch (e) {
      if (e.status === 401) {
        location.href = 'index.html';
      } else {
        err(e.message);
      }
    }
  }

  function renderTabs() {
    var areas = ['Tất cả'].concat(Array.from(new Set(allTables().map(function (x) { return x.area; }))));
    document.getElementById('area-tabs').innerHTML = areas.map(function (x) {
      return '<button class="moss-tab-btn ' + (area === x ? 'active' : '') + '" data-area="' + esc(x) + '">' + esc(x) + '</button>';
    }).join('');

    var cats = ['Tất cả'].concat(Array.from(new Set(state.menu.map(function (x) { return x.danhMuc; }))));
    document.getElementById('category-tabs').innerHTML = cats.map(function (x) {
      return '<button class="moss-tab-btn ' + (category === x ? 'active' : '') + '" data-cat="' + esc(x) + '">' + esc(x) + '</button>';
    }).join('');
  }

  function renderTables() {
    document.getElementById('tables-grid').innerHTML = allTables()
      .filter(function (x) { return area === 'Tất cả' || x.area === area; })
      .map(function (x) {
        var c = carts[x.key] || [];
        var count = c.reduce(function (s, y) { return s + y.quantity; }, 0);
        var occupied = x.status === 'CO_KHACH' || count > 0;
        return '<div class="col-6 col-sm-4 col-md-2">' +
          '<button class="pos-table-btn ' + (selected === x.key ? 'selected' : (occupied ? 'occupied' : '')) + '" data-table="' + x.key + '">' +
            '<div class="fw-bold">' + esc(x.name) + '</div>' +
            '<div class="mt-2" style="font-size:.72rem">' +
              (count ? count + ' món' : esc(x.area + (occupied ? ' · Có khách' : ' · Trống'))) +
            '</div>' +
          '</button>' +
        '</div>';
      }).join('');
  }

  function renderMenu() {
    var q = query.toLowerCase();
    var t = table();
    var data = state.menu.filter(function (m) {
      return (category === 'Tất cả' || m.danhMuc === category) &&
        (!q || (m.tenMon + ' ' + m.danhMuc).toLowerCase().includes(q)) &&
        m.trangThai !== 'HET_HANG';
    });

    document.getElementById('menu-grid').innerHTML = data.map(function (m) {
      return '<div class="col-12 col-sm-6 col-md-4">' +
        '<div class="menu-card">' +
          '<div class="menu-card-media">' +
            '<img src="' + esc(MocAn.resolveMenuImage(m)) + '" alt="">' +
            '<div class="menu-card-overlay">' +
              '<span>' + esc(m.danhMuc) + '</span>' +
              '<span class="font-mono">' + MocAn.formatVnd(m.donGia) + '</span>' +
            '</div>' +
          '</div>' +
          '<div class="p-3">' +
            '<h3 class="font-display fw-semibold" style="font-size:.95rem">' + esc(m.tenMon) + '</h3>' +
            '<button class="btn btn-forest w-100 mt-3" data-add="' + m.maMon + '">' +
              '<i class="bi bi-plus-lg me-1"></i>Thêm vào ' + esc(t.name) +
            '</button>' +
          '</div>' +
        '</div>' +
      '</div>';
    }).join('') || '<div class="col-12 text-center py-4 text-muted">Không tìm thấy món phù hợp.</div>';
  }

  function renderTicket() {
    var t = table();
    var c = cart();
    var sub = total();
    var isPriv = MocAn.isPrivileged(user);
    var disc = isPriv ? Math.min(discount, sub) : 0;

    document.getElementById('selected-table-label').textContent = t.name + ' · ' + t.area;
    document.getElementById('ticket-title').textContent = 'Phiếu gọi món · ' + t.name;
    document.getElementById('ticket-subtitle').textContent = t.id ? 'Tại bàn · ' + t.area : 'Khách mang đi';
    document.getElementById('btn-clear-cart').classList.toggle('d-none', !c.length);

    document.getElementById('cart-items-container').innerHTML = c.length
      ? c.map(function (x) {
          return '<div class="d-flex justify-content-between gap-2 py-2 border-bottom">' +
            '<div>' +
              '<div class="fw-semibold">' + esc(x.name) + '</div>' +
              '<small class="text-muted">' + MocAn.formatVnd(x.price) + ' × ' + x.quantity + '</small>' +
            '</div>' +
            '<div class="d-flex gap-2 align-items-center">' +
              '<button class="btn btn-sm border" data-qty="' + x.maMon + '" data-delta="-1">−</button>' +
              '<b class="tabular-nums">' + x.quantity + '</b>' +
              '<button class="btn btn-sm border" data-qty="' + x.maMon + '" data-delta="1">+</button>' +
            '</div>' +
          '</div>';
        }).join('')
      : '<div class="text-center py-5" style="color:var(--text-secondary)">Chưa có món trong phiếu.</div>';

    // Role-based Discount Control: Manager/Admin can enter discount; Staff is locked
    var lockIcon = document.getElementById('discount-lock-icon');
    if (lockIcon) lockIcon.classList.toggle('d-none', isPriv);

    document.getElementById('discount-control-wrap').innerHTML = isPriv
      ? '<input id="pos-discount" type="number" min="0" class="form-control form-control-sm moss-input text-end" value="' + (discount || '') + '" placeholder="0" style="width:130px">'
      : '<span class="badge bg-light text-danger border py-1 px-2"><i class="bi bi-lock-fill me-1"></i>Chỉ Quản lý được chiết khấu</span>';

    document.getElementById('ticket-subtotal').textContent = MocAn.formatVnd(sub);
    document.getElementById('ticket-discount-row').classList.toggle('d-none', !disc);
    document.getElementById('ticket-discount').textContent = '-' + MocAn.formatVnd(disc);
    document.getElementById('ticket-total').textContent = MocAn.formatVnd(sub - disc);
    document.getElementById('btn-checkout').disabled = !c.length;

    var third = document.querySelector('[data-pay="THE"]');
    if (third) third.closest('.col-4').remove();
    document.querySelectorAll('#payment-methods > div').forEach(function (x) { x.className = 'col-6'; });

    document.querySelectorAll('[data-pay]').forEach(function (b) {
      b.classList.toggle('btn-forest', b.dataset.pay === payment);
      b.classList.toggle('bg-light', b.dataset.pay !== payment);
    });
  }

  function renderOrders() {
    document.getElementById('orders-count-label').textContent = state.orders.length + ' đơn';
    document.getElementById('recent-orders-tbody').innerHTML = state.orders.slice(0, 10).map(function (o) {
      return '<tr>' +
        '<td><code>HD-' + o.maHoaDon + '</code></td>' +
        '<td>' + esc(o.ban ? o.ban.tenBan : 'Mang đi') + '</td>' +
        '<td>' + esc(items(o).map(function (x) { return x.quantity + ' ' + x.name; }).join(', ')) + '</td>' +
        '<td><span class="badge bg-light text-dark border">' + esc(o.phuongThucTT || '') + '</span></td>' +
        '<td>' + esc(o.nhanVien ? o.nhanVien.hoTen : '') + '</td>' +
        '<td class="font-mono">' + MocAn.formatTimeShort(o.thoiGianTao) + '</td>' +
        '<td class="text-end font-mono fw-bold">' + MocAn.formatVnd(o.tongTienThanhToan) + '</td>' +
      '</tr>';
    }).join('') || '<tr><td colspan="7" class="text-center py-4 text-muted">Chưa có đơn hàng nào hôm nay.</td></tr>';
  }

  function render() {
    MocAn.renderAppShell({
      activeRoute: 'banhang',
      user: user,
      lowStockCount: state.ingredients.filter(function (x) { return Number(x.soLuongTon) <= Number(x.mucTonToiThieu); }).length,
      onRefresh: load
    });
    renderTabs();
    renderTables();
    renderMenu();
    renderTicket();
    renderOrders();
  }

  async function selectTable(key) {
    selected = key;
    clearErr();
    var t = table();
    if (t.id) {
      try {
        var r = await fetch('/api/order/theo-ban/' + t.id, { credentials: 'same-origin' });
        if (r.status === 200) {
          var o = await r.json();
          carts[key] = items(o);
          MocAn.saveTableCarts(carts);
        }
      } catch (e) {}
    }
    render();
  }

  async function checkout() {
    clearErr();
    if (!cart().length) return;
    var button = document.getElementById('btn-checkout');
    button.disabled = true;

    try {
      var payload = orderPayload();
      payload.yeuCauTao = id();

      // 1. Tạo đơn hàng
      var order = await MocAn.apiFetch('/api/order/tao-don', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      // 2. Thanh toán đơn hàng
      var effectiveDiscount = MocAn.isPrivileged(user) ? discount : 0;
      var pay = {
        chietKhau: effectiveDiscount,
        phuongThucTT: payment,
        phienBan: order.phienBan,
        yeuCauThanhToan: id()
      };

      if (payment === 'TIEN_MAT') {
        pay.tienKhachDua = Math.max(order.tongTienGoc - pay.chietKhau, 0);
      } else {
        pay.xacNhanChuyenKhoan = true;
        pay.maThamChieu = 'POS-' + Date.now();
      }

      order = await MocAn.apiFetch('/api/order/' + order.maHoaDon + '/thanh-toan', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(pay)
      });

      carts[selected] = [];
      MocAn.saveTableCarts(carts);
      discount = 0;

      document.getElementById('receipt-meta').textContent = 'Hóa đơn HD-' + order.maHoaDon + ' đã thanh toán thành công.';
      document.getElementById('receipt-deductions').innerHTML =
        '<div class="d-flex justify-content-between"><span>Phương thức</span><strong>' + (order.phuongThucTT || payment) + '</strong></div>' +
        '<div class="d-flex justify-content-between mt-1"><span>Tổng thanh toán</span><strong class="text-success font-mono fs-5">' + MocAn.formatVnd(order.tongTienThanhToan) + '</strong></div>';

      modal.show();
      await load();
    } catch (e) {
      err(e.message);
    } finally {
      button.disabled = false;
    }
  }

  document.addEventListener('click', function (e) {
    var b = e.target.closest('[data-area],[data-cat],[data-table],[data-add],[data-qty],[data-pay]');
    if (!b) return;

    if (b.dataset.area) {
      area = b.dataset.area;
      render();
    }
    if (b.dataset.cat) {
      category = b.dataset.cat;
      render();
    }
    if (b.dataset.table) {
      selectTable(b.dataset.table);
    }
    if (b.dataset.add) {
      var m = state.menu.find(function (x) { return String(x.maMon) === b.dataset.add; });
      if (m) {
        var c = cart().slice();
        var x = c.find(function (z) { return z.maMon === m.maMon; });
        if (x) {
          x.quantity++;
        } else {
          c.push({ maMon: m.maMon, name: m.tenMon, price: m.donGia, quantity: 1 });
        }
        saveCart(c);
      }
    }
    if (b.dataset.qty) {
      var c2 = cart().map(function (x) { return Object.assign({}, x); });
      var y = c2.find(function (x) { return String(x.maMon) === b.dataset.qty; });
      if (y) {
        y.quantity += Number(b.dataset.delta);
        saveCart(c2.filter(function (x) { return x.quantity > 0; }));
      }
    }
    if (b.dataset.pay) {
      payment = b.dataset.pay;
      renderTicket();
    }
  });

  document.getElementById('menu-search').addEventListener('input', function (e) {
    query = e.target.value;
    renderMenu();
  });

  document.getElementById('btn-clear-cart').addEventListener('click', function () {
    saveCart([]);
  });

  document.getElementById('btn-checkout').addEventListener('click', checkout);

  document.addEventListener('input', function (e) {
    if (e.target.id === 'pos-discount') {
      if (MocAn.isPrivileged(user)) {
        discount = Math.max(0, Number(e.target.value) || 0);
        renderTicket();
      }
    }
  });

  modal = new bootstrap.Modal(document.getElementById('receiptModal'));
  load();
})();
