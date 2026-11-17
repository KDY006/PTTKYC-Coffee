package com.cafe.dto;

import java.util.List;

public class OrderDTO {
    private Integer maBan;
    private Integer maNV;
    private String loaiDon; // TAI_BAN, MANG_DI
    private List<OrderItemDTO> danhSachMon;

    public OrderDTO() {
    }

    public Integer getMaBan() {
        return maBan;
    }

    public void setMaBan(Integer maBan) {
        this.maBan = maBan;
    }

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public String getLoaiDon() {
        return loaiDon;
    }

    public void setLoaiDon(String loaiDon) {
        this.loaiDon = loaiDon;
    }

    public List<OrderItemDTO> getDanhSachMon() {
        return danhSachMon;
    }

    public void setDanhSachMon(List<OrderItemDTO> danhSachMon) {
        this.danhSachMon = danhSachMon;
    }
}
