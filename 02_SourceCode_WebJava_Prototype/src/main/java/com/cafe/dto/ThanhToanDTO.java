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

    private Double tienKhachDua = null;
    public Double getTienKhachDua() { return tienKhachDua; }
    public void setTienKhachDua(Double value) { tienKhachDua = value; }

    private Boolean xacNhanChuyenKhoan = false;
    public Boolean getXacNhanChuyenKhoan() { return xacNhanChuyenKhoan; }
    public void setXacNhanChuyenKhoan(Boolean value) { xacNhanChuyenKhoan = value; }

    private String maThamChieu = null;
    public String getMaThamChieu() { return maThamChieu; }
    public void setMaThamChieu(String value) { maThamChieu = value; }

    private String yeuCauThanhToan = null;
    public String getYeuCauThanhToan() { return yeuCauThanhToan; }
    public void setYeuCauThanhToan(String value) { yeuCauThanhToan = value; }

    private Long phienBan = null;
    public Long getPhienBan() { return phienBan; }
    public void setPhienBan(Long value) { phienBan = value; }
}
