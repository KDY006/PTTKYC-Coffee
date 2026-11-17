package com.cafe.dto;

public class NhapKhoDTO {
    private Integer maNL;
    private Double soLuongNhap;

    public NhapKhoDTO() {
    }

    public NhapKhoDTO(Integer maNL, Double soLuongNhap) {
        this.maNL = maNL;
        this.soLuongNhap = soLuongNhap;
    }

    public Integer getMaNL() {
        return maNL;
    }

    public void setMaNL(Integer maNL) {
        this.maNL = maNL;
    }

    public Double getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(Double soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }
}
