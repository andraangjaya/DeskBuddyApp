package com.demo.deskbuddy.repository;

import com.demo.deskbuddy.domain.DistractionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistractionHistoryRepository extends JpaRepository<DistractionHistory, Long> {
    Optional<DistractionHistory> findBySessionHistoryIdAndDistractionStartIsNotNullAndDistractionEndIsNull(Long sessionHistoryId);
    List<DistractionHistory> findAllBySessionHistoryId(Long sessionHistoryId);
}
