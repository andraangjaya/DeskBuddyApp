package com.demo.deskbuddy.repository;

import com.demo.deskbuddy.domain.DistractionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistractionHistoryRepository extends JpaRepository<DistractionHistory, Long> {
}
