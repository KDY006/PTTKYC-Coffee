package com.cafe.repository;

import com.cafe.entity.NguyenLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NguyenLieuRepository extends JpaRepository<NguyenLieu, Integer> {
    
    // Tìm các nguyên liệu có tồn kho nhỏ hơn hoặc bằng mức tồn tối thiểu (Cảnh báo đỏ)
    @Query("SELECT nl FROM NguyenLieu nl WHERE nl.soLuongTon <= nl.mucTonToiThieu")
    List<NguyenLieu> findNguyenLieuSapHet();

    List<NguyenLieu> findAllByOrderByMaNLAsc();

    @org.springframework.data.jpa.repository.Lock(jakarta.persistence.LockModeType.PESSIMISTIC_WRITE)
    @org.springframework.data.jpa.repository.Query("select x from NguyenLieu x where x.maNL = :id")
    java.util.Optional<NguyenLieu> khoaNguyenLieu(@org.springframework.data.repository.query.Param("id") Integer id);
}
