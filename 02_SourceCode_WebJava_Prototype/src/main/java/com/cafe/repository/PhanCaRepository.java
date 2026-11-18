package com.cafe.repository;

import com.cafe.entity.PhanCa;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhanCaRepository extends JpaRepository<PhanCa, Integer> {
    List<PhanCa> findByNgayLamBetweenOrderByNgayLamAsc(LocalDate tuNgay, LocalDate denNgay);
    List<PhanCa> findByNhanVien_MaNVAndNgayLamBetweenOrderByNgayLamAsc(Integer maNV, LocalDate tuNgay, LocalDate denNgay);
    Optional<PhanCa> findByNhanVien_MaNVAndCaLam_MaCaAndNgayLam(Integer maNV, Integer maCa, LocalDate ngayLam);
}
