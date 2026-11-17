package com.cafe.repository;

import com.cafe.entity.ChamCong;
import com.cafe.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChamCongRepository extends JpaRepository<ChamCong, Integer> {
    List<ChamCong> findByNgayLam(LocalDate ngayLam);
    List<ChamCong> findByNhanVienAndNgayLam(NhanVien nhanVien, LocalDate ngayLam);
    List<ChamCong> findByNhanVienOrderByNgayLamDesc(NhanVien nhanVien);
}
