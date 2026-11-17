package com.cafe.repository;

import com.cafe.entity.Ban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BanRepository extends JpaRepository<Ban, Integer> {
    List<Ban> findByTrangThai(String trangThai);
    List<Ban> findAllByOrderByMaBanAsc();

    @org.springframework.data.jpa.repository.Lock(jakarta.persistence.LockModeType.PESSIMISTIC_WRITE)
    @org.springframework.data.jpa.repository.Query("select x from Ban x where x.maBan = :id")
    java.util.Optional<Ban> khoaBan(@org.springframework.data.repository.query.Param("id") Integer id);
}
