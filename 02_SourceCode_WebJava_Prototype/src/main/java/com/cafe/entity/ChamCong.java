package com.cafe.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cham_cong")
public class ChamCong implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_cham_cong")
    private Integer maChamCong;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_nv", nullable = false)
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_ca", nullable = false)
    private CaLam caLam;

    @Column(name = "ngay_lam", nullable = false)
    private LocalDate ngayLam;

    @Column(name = "gio_check_in", nullable = false)
    private LocalDateTime gioCheckIn;

    @Column(name = "gio_check_out")
    private LocalDateTime gioCheckOut;

    @Column(name = "trang_thai", nullable = false, length = 20)
    private String trangThai; // DUNG_GIO, DI_TRE, VANG

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    public ChamCong() {
    }

    public ChamCong(Integer maChamCong, NhanVien nhanVien, CaLam caLam, LocalDate ngayLam, LocalDateTime gioCheckIn, LocalDateTime gioCheckOut, String trangThai, String ghiChu) {
        this.maChamCong = maChamCong;
        this.nhanVien = nhanVien;
        this.caLam = caLam;
        this.ngayLam = ngayLam;
        this.gioCheckIn = gioCheckIn;
        this.gioCheckOut = gioCheckOut;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public Integer getMaChamCong() {
        return maChamCong;
    }

    public void setMaChamCong(Integer maChamCong) {
        this.maChamCong = maChamCong;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public CaLam getCaLam() {
        return caLam;
    }

    public void setCaLam(CaLam caLam) {
        this.caLam = caLam;
    }

    public LocalDate getNgayLam() {
        return ngayLam;
    }

    public void setNgayLam(LocalDate ngayLam) {
        this.ngayLam = ngayLam;
    }

    public LocalDateTime getGioCheckIn() {
        return gioCheckIn;
    }

    public void setGioCheckIn(LocalDateTime gioCheckIn) {
        this.gioCheckIn = gioCheckIn;
    }

    public LocalDateTime getGioCheckOut() {
        return gioCheckOut;
    }

    public void setGioCheckOut(LocalDateTime gioCheckOut) {
        this.gioCheckOut = gioCheckOut;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
