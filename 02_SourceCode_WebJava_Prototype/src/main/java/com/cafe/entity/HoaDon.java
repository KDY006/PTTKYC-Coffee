package com.cafe.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hoa_don")
public class HoaDon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hoa_don")
    private Integer maHoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_ban", nullable = true)
    private Ban ban;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_nv", nullable = false)
    private NhanVien nhanVien;

    @Column(name = "thoi_gian_tao", nullable = false)
    private LocalDateTime thoiGianTao = LocalDateTime.now();

    @Column(name = "loai_don", nullable = false, length = 20)
    private String loaiDon = "TAI_BAN"; // TAI_BAN, MANG_DI

    @Column(name = "tong_tien_goc", nullable = false)
    private Double tongTienGoc = 0.0;

    @Column(name = "chiet_khau", nullable = false)
    private Double chietKhau = 0.0;

    @Column(name = "tong_tien_thanh_toan", nullable = false)
    private Double tongTienThanhToan = 0.0;

    @Column(name = "phuong_thuc_tt", length = 20)
    private String phuongThucTT = "TIEN_MAT"; // TIEN_MAT, CHUYEN_KHOAN

    @Column(name = "trang_thai", nullable = false, length = 20)
    private String trangThai = "CHO_THANH_TOAN"; // CHO_THANH_TOAN, DA_THANH_TOAN, DA_HUY

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ChiTietHoaDon> danhSachChiTiet = new ArrayList<>();

    public HoaDon() {
    }

    public Integer getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(Integer maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LocalDateTime getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(LocalDateTime thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public String getLoaiDon() {
        return loaiDon;
    }

    public void setLoaiDon(String loaiDon) {
        this.loaiDon = loaiDon;
    }

    public Double getTongTienGoc() {
        return tongTienGoc;
    }

    public void setTongTienGoc(Double tongTienGoc) {
        this.tongTienGoc = tongTienGoc;
    }

    public Double getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(Double chietKhau) {
        this.chietKhau = chietKhau;
    }

    public Double getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public void setTongTienThanhToan(Double tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public String getPhuongThucTT() {
        return phuongThucTT;
    }

    public void setPhuongThucTT(String phuongThucTT) {
        this.phuongThucTT = phuongThucTT;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<ChiTietHoaDon> getDanhSachChiTiet() {
        return danhSachChiTiet;
    }

    public void setDanhSachChiTiet(List<ChiTietHoaDon> danhSachChiTiet) {
        this.danhSachChiTiet = danhSachChiTiet;
    }

    public void addChiTiet(ChiTietHoaDon chiTiet) {
        danhSachChiTiet.add(chiTiet);
        chiTiet.setHoaDon(this);
    }
}
