package com.cafe.repository;

import com.cafe.entity.Ban;
import com.cafe.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    List<HoaDon> findByTrangThai(String trangThai);
    Optional<HoaDon> findFirstByBanAndTrangThaiOrderByMaHoaDonDesc(Ban ban, String trangThai);
    List<HoaDon> findByThoiGianTaoBetween(LocalDateTime start, LocalDateTime end);
    List<HoaDon> findAllByOrderByMaHoaDonDesc();
}
