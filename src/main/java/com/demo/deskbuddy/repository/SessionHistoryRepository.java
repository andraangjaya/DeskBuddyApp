package com.demo.deskbuddy.repository;

import com.demo.deskbuddy.domain.SessionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionHistoryRepository extends JpaRepository<SessionHistory, Long> {
}
