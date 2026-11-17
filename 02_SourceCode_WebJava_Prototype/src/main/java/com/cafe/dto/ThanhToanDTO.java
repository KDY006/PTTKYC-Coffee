package com.cafe.dto;

public class ThanhToanDTO {
    private Double chietKhau = 0.0;
    private String phuongThucTT = "TIEN_MAT"; // TIEN_MAT, CHUYEN_KHOAN

    public ThanhToanDTO() {
    }

    public ThanhToanDTO(Double chietKhau, String phuongThucTT) {
        this.chietKhau = chietKhau;
        this.phuongThucTT = phuongThucTT;
    }

    public Double getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(Double chietKhau) {
        this.chietKhau = chietKhau;
    }

    public String getPhuongThucTT() {
        return phuongThucTT;
    }

    public void setPhuongThucTT(String phuongThucTT) {
        this.phuongThucTT = phuongThucTT;
    }
}
