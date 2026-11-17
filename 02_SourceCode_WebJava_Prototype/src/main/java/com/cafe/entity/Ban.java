package com.cafe.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ban")
public class Ban implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ban")
    private Integer maBan;

    @Column(name = "ten_ban", nullable = false, length = 50)
    private String tenBan; // Bàn 1, Bàn 2, ...

    @Column(name = "vi_tri", length = 50)
    private String viTri; // Tầng 1, Tầng 2, Ngoài trời

    @Column(name = "trang_thai", nullable = false, length = 20)
    private String trangThai = "TRONG"; // TRONG, CO_KHACH

    public Ban() {
    }

    public Ban(Integer maBan, String tenBan, String viTri, String trangThai) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.viTri = viTri;
        this.trangThai = trangThai;
    }

    public Integer getMaBan() {
        return maBan;
    }

    public void setMaBan(Integer maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
