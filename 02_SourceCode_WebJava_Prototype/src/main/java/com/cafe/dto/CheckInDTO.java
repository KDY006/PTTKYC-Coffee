package com.cafe.dto;

public class CheckInDTO {
    private Integer maNV;
    private Integer maCa;

    public CheckInDTO() {
    }

    public CheckInDTO(Integer maNV, Integer maCa) {
        this.maNV = maNV;
        this.maCa = maCa;
    }

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public Integer getMaCa() {
        return maCa;
    }

    public void setMaCa(Integer maCa) {
        this.maCa = maCa;
    }
}
