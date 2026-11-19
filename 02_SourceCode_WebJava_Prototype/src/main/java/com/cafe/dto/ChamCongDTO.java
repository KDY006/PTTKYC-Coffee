package com.cafe.dto;

public class ChamCongDTO {

    private Integer maNV;
    private Integer maCa;

    public ChamCongDTO() {
    }

    public ChamCongDTO(Integer maNV, Integer maCa) {
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
