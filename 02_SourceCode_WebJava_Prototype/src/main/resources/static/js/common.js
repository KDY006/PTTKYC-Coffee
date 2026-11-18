(function (window) {
  'use strict';

  var USER_KEY = 'mocan_java_user';
  var CARTS_KEY = 'mocan_java_table_carts';

  var MENU_IMAGE_MAP = {
    1: 'assets/images/cafe_den_da_1794937559459.jpg',
    2: 'assets/images/cafe_sua_da_1794937558363.jpg',
    3: 'assets/images/bac_xiu_cafe_1794969775813.jpg',
    4: 'assets/images/bac_xiu_cafe_1794969775813.jpg',
    5: 'assets/images/cafe_den_da_1794937559459.jpg',
    6: 'assets/images/tra_dao_cam_sa_1794937558193.jpg',
    7: 'assets/v_i_l_i_518c68ba.png',
    8: 'assets/__long_senn_v_ng_52d1415b.png',
    9: 'assets/tr__s_a_9eee8433.png',
    10: 'assets/tr__chanh_9341e931.png',
    11: 'assets/images/matcha_croissant_1794937559936.jpg',
    12: 'assets/images/cafe_sua_da_1794937558363.jpg',
    13: 'assets/images/bac_xiu_cafe_1794969775813.jpg',
    14: 'assets/images/croissant_pastry_1794969797589.jpg',
    15: 'assets/Gemini_Generated_Image_n16uibn16uibn16u_b4ca1ca1.png',
    16: 'assets/cam_f4973615.png',
    17: 'assets/d_a_h_u_a8a5c321.png',
    18: 'assets/t_o_8fe1ad66.png',
    19: 'assets/Gemini_Generated_Image_5apdl45apdl45apd_a4b90edb.png',
    20: 'assets/Gemini_Generated_Image_83j0q883j0q883j0_7d4a1b30.png'
  };

  function escapeHtml(val) {
    if (val === null || val === undefined) return '';
    return String(val)
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;')
      .replace(/'/g, '&#039;');
  }

  function getCurrentUser() {
    try {
      var raw = localStorage.getItem(USER_KEY);
      return raw ? JSON.parse(raw) : null;
    } catch (e) {
      return null;
    }
  }

  function setCurrentUser(user) {
    localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  function logout() {
    localStorage.removeItem(USER_KEY);
    localStorage.removeItem(CARTS_KEY);
    fetch('/api/auth/logout', { method: 'POST', credentials: 'same-origin' })
      .catch(function () {})
      .finally(function () { window.location.href = 'index.html'; });
  }

  function requireAuth() {
    var user = getCurrentUser();
    if (!user || !user.tenDangNhap) {
      window.location.href = 'index.html';
      return null;
    }
    return user;
  }

  function isPrivileged(user) {
    if (!user || !user.vaiTro) return false;
    var role = String(user.vaiTro).toUpperCase();
    return role === 'ADMIN' || role === 'MANAGER';
  }

  function getRoleTitle(user) {
    var role = user && user.vaiTro ? String(user.vaiTro).toUpperCase() : 'STAFF';
    if (role === 'ADMIN') return 'Admin · Toàn quyền';
    if (role === 'MANAGER') return 'Manager · Quản lý';
    return 'Staff · Thu ngân & Ca';
  }

  function getRoleShort(user) {
    var role = user && user.vaiTro ? String(user.vaiTro).toUpperCase() : 'STAFF';
    if (role === 'ADMIN') return 'Admin';
    if (role === 'MANAGER') return 'Manager';
    return 'Staff';
  }

  function getInitials(name) {
    var parts = String(name || 'NV').trim().split(/\s+/);
    if (parts.length === 1) return parts[0].slice(0, 2).toUpperCase();
    return (parts[0][0] + parts[parts.length - 1][0]).toUpperCase();
  }

  async function apiFetch(url, options) {
    var opts = options || {};
    var headers = Object.assign({}, opts.headers || {});
    var response = await fetch(url, Object.assign({}, opts, {
      headers: headers,
      credentials: 'same-origin'
    }));
    var data = null;
    try {
      data = await response.json();
    } catch (e) {
      data = {};
    }
    if (!response.ok) {
      var err = new Error(data.error || 'Yêu cầu thất bại.');
      err.status = response.status;
      err.data = data;
      if (response.status === 401) {
        localStorage.removeItem(USER_KEY);
        localStorage.removeItem(CARTS_KEY);
      }
      throw err;
    }
    return data;
  }

  function formatVnd(value) {
    var num = Number(value) || 0;
    return new Intl.NumberFormat('vi-VN').format(Math.round(num)) + ' ₫';
  }

  function formatTimeShort(val) {
    if (!val) return '--:--:--';
    var str = String(val);
    if (/^\d{2}:\d{2}:\d{2}$/.test(str)) return str;
    var parsed = new Date(str.replace(' ', 'T'));
    if (!isNaN(parsed.getTime())) {
      return parsed.toLocaleTimeString('vi-VN', {
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false
      });
    }
    var match = str.match(/(\d{2}:\d{2}:\d{2})/);
    return match ? match[1] : str;
  }

  function formatDateVi(val) {
    if (!val) return '';
    var d = new Date(String(val).replace(' ', 'T'));
    if (isNaN(d.getTime())) return String(val);
    return d.toLocaleDateString('vi-VN');
  }

  function resolveMenuImage(menuItem) {
    if (menuItem && (menuItem.hinhAnh || menuItem.hinh_anh)) {
      var p = String(menuItem.hinhAnh || menuItem.hinh_anh);
      return p.startsWith('/') ? p.slice(1) : p;
    }
    var id = Number(menuItem && (menuItem.maMon || menuItem.ma_mon));
    return MENU_IMAGE_MAP[id] || 'assets/images/cafe_sua_da_1794937558363.jpg';
  }

  async function loadSessionUser() {
    var sessionUser = await apiFetch('/api/auth/me');
    var cached = getCurrentUser() || {};
    var user = Object.assign({}, cached, sessionUser);
    if (!user.hoTen) user.hoTen = user.tenDangNhap || 'Nhân viên';
    setCurrentUser(user);
    return user;
  }

  function loadTableCarts() {
    try {
      var raw = localStorage.getItem(CARTS_KEY);
      return raw ? JSON.parse(raw) : {};
    } catch (e) {
      return {};
    }
  }

  function saveTableCarts(carts) {
    localStorage.setItem(CARTS_KEY, JSON.stringify(carts || {}));
  }

  function inferIngredientCategory(tenNl) {
    var name = String(tenNl || '').toLowerCase();
    if (name.includes('cà phê')) return 'Cà phê';
    if (name.includes('sữa') || name.includes('kem')) return 'Sữa & Kem';
    if (name.includes('trà') || name.includes('siro') || name.includes('matcha')) return 'Trà & Siro';
    return 'Topping & Đường';
  }

  function renderAppShell(config) {
    var activeRoute = config.activeRoute;
    var user = config.user;
    var lowStockCount = Number(config.lowStockCount || 0);
    var onRefresh = config.onRefresh;

    var routeTitles = {
      banhang: 'Bán hàng & Quầy POS',
      kho: 'Kho nguyên liệu & KPI',
      nhansu: 'Nhân sự & Chấm công'
    };

    var navItems = [
      { route: 'banhang', href: 'banhang.html', label: 'Bán hàng (POS)', icon: 'bi-bag-check' },
      { route: 'kho', href: 'kho.html', label: 'Kho & KPI', icon: 'bi-box-seam', badge: lowStockCount },
      { route: 'nhansu', href: 'nhansu.html', label: 'Nhân sự & Chấm công', icon: 'bi-people' }
    ];

    var sidebarContainer = document.getElementById('app-sidebar-root');
    if (sidebarContainer) {
      sidebarContainer.innerHTML =
        '<aside class="app-sidebar">' +
          '<a href="banhang.html" class="app-sidebar-brand">' +
            '<span class="brand-badge"><i class="bi bi-cup-hot-fill"></i></span>' +
          '</a>' +
          '<div class="px-4 pt-4 pb-2 text-uppercase fw-semibold" style="font-size:11px;letter-spacing:0.06em;color:var(--text-muted);">Phân hệ vận hành</div>' +
          '<nav class="flex-grow-1 px-3 py-1 d-flex flex-column gap-1">' +
            navItems.map(function (item) {
              var isActive = activeRoute === item.route;
              var badgeHtml = item.badge > 0
                ? '<span class="badge bg-danger rounded-2 px-2 py-1 tabular-nums" style="font-size:11px;">' + item.badge + '</span>'
                : '';
              return '<a href="' + item.href + '" class="sidebar-nav-link ' + (isActive ? 'active' : '') + '">' +
                '<span class="d-flex align-items-center gap-2">' +
                  '<i class="bi ' + item.icon + '"></i>' +
                  '<span>' + item.label + '</span>' +
                '</span>' +
                badgeHtml +
              '</a>';
            }).join('') +
          '</nav>' +
          '<div class="p-3 m-3 moss-card" style="background-color:var(--bg-surface-hover);">' +
            '<div class="d-flex align-items-center gap-3 mb-3">' +
              '<div class="rounded-circle d-flex align-items-center justify-content-center fw-bold text-white flex-shrink-0" style="width:38px;height:38px;background-color:var(--accent-primary);font-size:0.75rem;">' +
                escapeHtml(getInitials(user.hoTen)) +
              '</div>' +
              '<div class="min-w-0">' +
                '<div class="fw-semibold text-truncate" style="font-size:0.85rem;">' + escapeHtml(user.hoTen) + '</div>' +
                '<div class="d-flex align-items-center gap-1" style="font-size:11px;color:var(--text-secondary);">' +
                  '<i class="bi ' + (isPrivileged(user) ? 'bi-shield-check text-warning' : 'bi-person-check text-success') + '"></i>' +
                  '<span>' + escapeHtml(getRoleTitle(user)) + '</span>' +
                '</div>' +
              '</div>' +
            '</div>' +
            '<div class="d-grid gap-2 pt-2 border-top" style="border-color:var(--border-subtle) !important;">' +
              '<button type="button" id="btn-refresh-mysql" class="btn btn-sm bg-white border rounded-3 d-flex align-items-center justify-content-center gap-2 fw-medium" style="border-color:var(--border-subtle);font-size:0.75rem;">' +
                '<i class="bi bi-arrow-clockwise text-success"></i><span>Đồng bộ từ MySQL</span>' +
              '</button>' +
              '<button type="button" id="btn-sidebar-logout" class="btn btn-sm btn-link text-decoration-none d-flex align-items-center justify-content-center gap-2 fw-medium" style="color:var(--text-secondary);font-size:0.75rem;">' +
                '<i class="bi bi-box-arrow-right"></i><span>Đăng xuất / Đổi tài khoản</span>' +
              '</button>' +
            '</div>' +
          '</div>' +
        '</aside>';
    }

    var topbarContainer = document.getElementById('app-topbar-root');
    if (topbarContainer) {
      topbarContainer.innerHTML =
        '<header class="app-topbar px-3 px-sm-4 d-flex align-items-center justify-content-between gap-3">' +
          '<div class="d-flex align-items-center gap-2 min-w-0">' +
            '<button class="btn btn-sm d-lg-none border" type="button" data-bs-toggle="collapse" data-bs-target="#mobileNavDrawer" aria-label="Mở menu">' +
              '<i class="bi bi-list fs-5"></i>' +
            '</button>' +
            '<div class="d-flex align-items-center gap-2 text-truncate">' +
              '<span class="d-none d-sm-inline fw-medium" style="font-size:0.75rem;color:var(--text-secondary);">' +
                escapeHtml(getRoleShort(user)) + ' (' + escapeHtml(user.tenDangNhap) + ')' +
              '</span>' +
              '<span class="d-none d-sm-inline" style="color:var(--border-strong);">/</span>' +
              '<h1 class="font-display fw-semibold mb-0 text-truncate" style="font-size:1.125rem;">' +
                escapeHtml(routeTitles[activeRoute] || '') +
              '</h1>' +
            '</div>' +
          '</div>' +
          '<nav class="d-none d-md-flex align-items-center gap-4">' +
            navItems.map(function (item) {
              return '<a href="' + item.href + '" class="topbar-nav-link ' + (activeRoute === item.route ? 'active' : '') + '">' + item.label + '</a>';
            }).join('') +
          '</nav>' +
          '<div>' +
            '<button type="button" id="btn-topbar-logout" class="btn btn-forest btn-sm px-3 py-2 d-flex align-items-center gap-2" style="font-size:0.75rem;">' +
              '<i class="bi bi-box-arrow-right"></i><span>Đăng xuất</span>' +
            '</button>' +
          '</div>' +
        '</header>' +
        '<div class="collapse d-lg-none bg-white border-bottom px-3 py-3" id="mobileNavDrawer" style="border-color:var(--border-subtle) !important;">' +
          '<div class="d-flex flex-column gap-1 mb-3">' +
            navItems.map(function (item) {
              var isActive = activeRoute === item.route;
              return '<a href="' + item.href + '" class="sidebar-nav-link ' + (isActive ? 'active' : '') + '">' +
                '<span class="d-flex align-items-center gap-2"><i class="bi ' + item.icon + '"></i><span>' + item.label + '</span></span>' +
                (item.badge > 0 ? '<span class="badge bg-danger">' + item.badge + '</span>' : '') +
              '</a>';
            }).join('') +
          '</div>' +
          '<div class="d-flex align-items-center justify-content-between pt-2 border-top" style="border-color:var(--border-subtle) !important;">' +
            '<span style="font-size:0.75rem;color:var(--text-secondary);">' + escapeHtml(user.hoTen) + ' · ' + escapeHtml(getRoleShort(user)) + '</span>' +
            '<button type="button" id="btn-mobile-logout" class="btn btn-outline-secondary btn-sm">Đăng xuất</button>' +
          '</div>' +
        '</div>';
    }

    var logoutBtns = ['btn-sidebar-logout', 'btn-topbar-logout', 'btn-mobile-logout'];
    logoutBtns.forEach(function (id) {
      var el = document.getElementById(id);
      if (el) el.addEventListener('click', logout);
    });

    var refreshBtn = document.getElementById('btn-refresh-mysql');
    if (refreshBtn && typeof onRefresh === 'function') {
      refreshBtn.addEventListener('click', function () {
        onRefresh();
      });
    }
  }

  window.MocAn = {
    escapeHtml: escapeHtml,
    getCurrentUser: getCurrentUser,
    setCurrentUser: setCurrentUser,
    logout: logout,
    requireAuth: requireAuth,
    isPrivileged: isPrivileged,
    apiFetch: apiFetch,
    formatVnd: formatVnd,
    formatTimeShort: formatTimeShort,
    formatDateVi: formatDateVi,
    resolveMenuImage: resolveMenuImage,
    loadTableCarts: loadTableCarts,
    saveTableCarts: saveTableCarts,
    inferIngredientCategory: inferIngredientCategory,
    loadSessionUser: loadSessionUser,
    renderAppShell: renderAppShell
  };
})(window);
