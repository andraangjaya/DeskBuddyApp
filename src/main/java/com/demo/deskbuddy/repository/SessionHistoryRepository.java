package com.demo.deskbuddy.repository;

import com.demo.deskbuddy.domain.SessionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface SessionHistoryRepository extends JpaRepository<SessionHistory, Long> {
    Optional<SessionHistory> findByStudentIdAndSession(Long student_id, Integer session);
    Optional<SessionHistory> findByStudentIdAndSessionAndSessionDateBetween(Long student_id, Integer session, Instant start, Instant end);

}
