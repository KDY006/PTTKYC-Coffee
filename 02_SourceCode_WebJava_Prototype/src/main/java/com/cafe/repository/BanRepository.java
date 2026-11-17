package com.cafe.repository;

import com.cafe.entity.Ban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BanRepository extends JpaRepository<Ban, Integer> {
    List<Ban> findByTrangThai(String trangThai);
    List<Ban> findAllByOrderByMaBanAsc();
}
